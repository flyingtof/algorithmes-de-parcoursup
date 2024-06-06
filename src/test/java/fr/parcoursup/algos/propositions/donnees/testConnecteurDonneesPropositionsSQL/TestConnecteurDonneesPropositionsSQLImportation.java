package fr.parcoursup.algos.propositions.donnees.testConnecteurDonneesPropositionsSQL;

import fr.parcoursup.algos.donnees.ConnecteurSQL;
import fr.parcoursup.algos.exceptions.AccesDonneesException;
import fr.parcoursup.algos.propositions.algo.*;
import fr.parcoursup.algos.propositions.donnees.ConnecteurDonneesPropositionsSQL;
import org.junit.Test;
import org.powermock.reflect.Whitebox;


import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertThrows;

public class TestConnecteurDonneesPropositionsSQLImportation extends TestConnecteurDonneesPropositionsSQL {

    public TestConnecteurDonneesPropositionsSQLImportation(String name) {

        super(name);

    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // Helpers
    //
    ///////////////////////////////////////////////////////////////////////////
    int recupere_nombre_jours_depuis_debut_campagne(
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
    ) throws Exception {

        int nbJoursCampagne = connecteurDonneesPropositions.getNbJoursCampagne();

        return nbJoursCampagne;

    }

    @Test
    public void test_recuperation_verification_nombre_jours_depuis_debut_campagne_avec_connecteur_prod_doit_retourner_nombre_superieur_a_zero() throws Exception {
        this.setBddDateDebutCampagne("20/06/2023:0000");
        int nbJoursCampagne;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            nbJoursCampagne = this.recupere_nombre_jours_depuis_debut_campagne(connecteurDonneesPropositions);

        }
        assertTrue(nbJoursCampagne > 0);
    }
    
    
    int recupere_nombre_jours_campagne_a_date_pivot_internats(
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
    ) throws Exception {
        int nbJoursCampagneDatePivotInternats = connecteurDonneesPropositions.getNbJoursCampagneDatePivotInternats();
        return nbJoursCampagneDatePivotInternats;
    }

    int recupere_nombre_jours_campagne_debut_gdd(
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
    ) throws Exception {
        int nbJrs = connecteurDonneesPropositions.getNbJoursCampagneDateDebutGDD();
        return nbJrs;
    }

    int recupere_nombre_jours_campagne_fin_ord_gdd(
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
    ) throws Exception {
        int nbJrs = connecteurDonneesPropositions.getNbJoursCampagneFinOrdonnancementGDD();
        return nbJrs;
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // Tests
    //
    ///////////////////////////////////////////////////////////////////////////
    @Test
    public void test_recuperation_verification_nombre_jours_ecoules_entre_dates_debut_campagne_et_ouverture_complete_internats_avec_connecteur_prod_doit_reussir() throws Exception {

        /* the modification of these values will fail the h2 tests unless the values used in
         the definition of f_propGetNbJrsFromParam in db-setup/h2/create-schema.sql
         are updated */
        this.setBddDateDebutCampagne("01/06/2023:0000");//35
        this.setBddDateOuvertureCompleteInternats("30/06/2023:0000");//334
        this.setBddDateDebutGDD("25/06/2023:2359");//582
        this.setBddDateFinOrdGDD("18/05/2023:0000");//437

        int nbJoursCampagneDatePivotInternats;
        int nbJoursDebutGDD;
        int nbJoursFinOrdGDD;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            nbJoursCampagneDatePivotInternats = this.recupere_nombre_jours_campagne_a_date_pivot_internats(
                    connecteurDonneesPropositions
            );
            nbJoursDebutGDD = this.recupere_nombre_jours_campagne_debut_gdd(
                    connecteurDonneesPropositions
            );
            nbJoursFinOrdGDD = this.recupere_nombre_jours_campagne_fin_ord_gdd(
                    connecteurDonneesPropositions
            );
        }

         /* the modification of these values will fail the h2 tests unless the values used in
         the definition of f_propGetNbJrsFromParam in db-setup/h2/create-schema.sql
         are updated */
        assertEquals(30, nbJoursCampagneDatePivotInternats);
        assertEquals(45, nbJoursDebutGDD);
        assertEquals(48, nbJoursFinOrdGDD);

    }

    AlgoPropositionsEntree recupere_donnees_candidats_avec_repondeur_automatique(
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions,
            Parametres parametres
    ) throws Exception {

        AlgoPropositionsEntree entree = new AlgoPropositionsEntree(parametres);

        Whitebox.setInternalState(
                connecteurDonneesPropositions,
                "entree",
                entree
        );

        Whitebox.invokeMethod(
                connecteurDonneesPropositions,
                "recupererCandidatsAvecRepondeurAutomatique"
        );

        return entree;

    }

    @Test
    public void test_recuperation_verification_candidats_avec_repondeur_automatique_avec_connecteur_toutes_donnees_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        AlgoPropositionsEntree entree;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            entree = this.recupere_donnees_candidats_avec_repondeur_automatique(
                    connecteurDonneesPropositions,
                    parametres
            );
        }

        assertEquals(entree.candidatsAvecRepondeurAutomatique.size(), 1);
        // 1 candidat avec répondeur automatique dans le scénario établi

    }

    @Test
    public void test_recuperation_verification_candidats_avec_repondeur_automatique_avec_connecteur_prod_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        AlgoPropositionsEntree entree;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            entree = this.recupere_donnees_candidats_avec_repondeur_automatique(
                    connecteurDonneesPropositions,
                    parametres
            );
        }

        assertEquals(entree.candidatsAvecRepondeurAutomatique.size(), 1);
        // 1 candidat avec répondeur automatique dans le scénario établi

    }


    @Test
    public void test_recuperation_verification_groupes_affectation_avec_connecteur_toutes_donnees_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        Map<GroupeAffectationUID, GroupeAffectation> groupesAffectation;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            groupesAffectation = this.recupere_groupes_affectation(
                    connecteurDonneesPropositions,
                    parametres
            );
        }

        assertEquals(groupesAffectation.size(), 2);
        
    }

    
    Map<GroupeAffectationUID, GroupeAffectation> recupere_groupes_affectation(
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions,
            Parametres parametres
    ) throws Exception {

        Map<GroupeAffectationUID, GroupeAffectation> groupesAffectation = Whitebox.invokeMethod(
                connecteurDonneesPropositions,
                "recupererGroupesAffectation",
                parametres,
                false // paramètre retroCompatibitilite
        );

        return groupesAffectation;

    }
    
    
    @Test
    public void test_recuperation_verification_groupes_affectation_avec_connecteur_prod_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        Map<GroupeAffectationUID, GroupeAffectation> groupesAffectation;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            groupesAffectation = this.recupere_groupes_affectation(
                    connecteurDonneesPropositions,
                    parametres
            );
        }

        assertEquals(groupesAffectation.size(), 2);

    }

    Map<GroupeInternatUID, GroupeInternat> recupere_internats(
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
    ) throws Exception {

        Map<GroupeInternatUID, GroupeInternat> internats = Whitebox.invokeMethod(
                connecteurDonneesPropositions,
                "recupererInternats"
        );

        return internats;

    }

    @Test
    public void test_recuperation_verification_internats_avec_connecteur_toutes_donnees_doit_reussir() throws Exception {

        Map<GroupeInternatUID, GroupeInternat> internats;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            internats = this.recupere_internats(connecteurDonneesPropositions);
        }

        assertEquals(internats.size(), 2);

    }

    @Test
    public void test_recuperation_verification_internats_avec_connecteur_prod_doit_reussir() throws Exception {

        Map<GroupeInternatUID, GroupeInternat> internats;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            internats = this.recupere_internats(connecteurDonneesPropositions);
        }

        assertEquals(internats.size(), 2);

    }

    AlgoPropositionsEntree recupere_voeux_avec_demande_internat(
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions,
            Parametres parametres,
            boolean seulementVoeuxEnAttente
    ) throws Exception {

        AlgoPropositionsEntree entree = new AlgoPropositionsEntree(parametres);

        Whitebox.setInternalState(connecteurDonneesPropositions, "entree", entree);

        Map<GroupeAffectationUID, GroupeAffectation> groupesAffectation = Whitebox.invokeMethod(
                connecteurDonneesPropositions,
                "recupererGroupesAffectation",
                parametres,
                false // paramètre retroCompatibitilite
        );

        for (GroupeAffectation g : groupesAffectation.values()) {
            entree.ajouter(g);
        }

        Map<GroupeInternatUID, GroupeInternat> internats = Whitebox.invokeMethod(
                connecteurDonneesPropositions,
                "recupererInternats"
        );

        for (GroupeInternat internat : internats.values()) {
            entree.ajouter(internat);
        }


        connecteurDonneesPropositions.recupererVoeuxAvecInternatsAClassementPropre(
                entree.internatsIndex,
                seulementVoeuxEnAttente,
                true
        );

        return entree;

    }

    @Test
    public void test_recuperation_verification_tous_voeux_avec_demande_internat_avec_connecteur_toutes_donnees_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        AlgoPropositionsEntree entree;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            entree = this.recupere_voeux_avec_demande_internat(
                    connecteurDonneesPropositions,
                    parametres,
                    false // paramètre seulementVoeuxEnAttente
            );
        }

        assertEquals(entree.voeux.size(), 4);
        // 3 voeux en attente + 1 voeu affecté dans le scénario établi

    }

    @Test
    public void test_recuperation_verification_tous_voeux_avec_demande_internat_avec_connecteur_prod_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        AlgoPropositionsEntree entree;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            entree = this.recupere_voeux_avec_demande_internat(
                    connecteurDonneesPropositions,
                    parametres,
                    false // paramètre seulementVoeuxEnAttente
            );
        }

        assertEquals(entree.voeux.size(), 4);
        // 3 voeux en attente + 1 voeu affecté dans le scénario établi

    }

    @Test
    public void test_recuperation_verification_voeux_en_attente_seulement_avec_demande_internat_avec_connecteur_toutes_donnees_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        AlgoPropositionsEntree entree;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            entree = this.recupere_voeux_avec_demande_internat(
                    connecteurDonneesPropositions,
                    parametres,
                    true // paramètre seulementVoeuxEnAttente
            );
        }

        assertEquals(3, entree.voeux.size());
        // 3 voeux en attente dans le scénario établi

    }

    @Test
    public void test_recuperation_verification_voeux_en_attente_seulement_avec_demande_internat_avec_connecteur_prod_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        AlgoPropositionsEntree entree;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            entree = this.recupere_voeux_avec_demande_internat(
                    connecteurDonneesPropositions,
                    parametres,
                    true // paramètre seulementVoeuxEnAttente
            );
        }

        assertEquals(entree.voeux.size(), 3);
        // 3 voeux en attente dans le scénario établi

    }

    AlgoPropositionsEntree recupere_voeux_sans_demande_internat(
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions,
            Parametres parametres,
            boolean seulementVoeuxEnAttente
    ) throws Exception {

        AlgoPropositionsEntree entree = new AlgoPropositionsEntree(parametres);

        Whitebox.setInternalState(connecteurDonneesPropositions, "entree", entree);


        Map<GroupeAffectationUID, GroupeAffectation> groupesAffectation =
                connecteurDonneesPropositions.recupererGroupesAffectation(
                        parametres,
                        false// paramètre retroCompatibitilite
                );

        for (GroupeAffectation g : groupesAffectation.values()) {
            entree.ajouter(g);
        }

        Map<GroupeInternatUID, GroupeInternat> internats
                = connecteurDonneesPropositions.recupererInternats();

        for (GroupeInternat internat : internats.values()) {
            entree.ajouter(internat);
        }

        connecteurDonneesPropositions.recupererVoeuxSansInternatAClassementPropre(
                seulementVoeuxEnAttente,
                true
        );

        return entree;

    }

    @Test
    public void test_recuperation_verification_tous_voeux_en_attente_sans_demande_internat_avec_connecteur_toutes_donnees_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        AlgoPropositionsEntree entree;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            entree = this.recupere_voeux_sans_demande_internat(
                    connecteurDonneesPropositions,
                    parametres,
                    false // paramètre seulementVoeuxEnAttente
            );
        }

        assertEquals(entree.voeux.size(), 1);
        // 1 voeu affecté dans le scénario établi

    }

    @Test
    public void test_recuperation_verification_tous_voeux_en_attente_sans_demande_internat_avec_connecteur_prod_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        AlgoPropositionsEntree entree;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            entree = this.recupere_voeux_sans_demande_internat(
                    connecteurDonneesPropositions,
                    parametres,
                    false // paramètre seulementVoeuxEnAttente
            );
        }

        assertEquals(entree.voeux.size(), 1);
        // 1 voeu affecté dans le scénario établi

    }

    @Test
    public void test_recuperation_verification_voeux_en_attente_seulement_sans_demande_internat_avec_connecteur_toutes_donnees_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        AlgoPropositionsEntree entree;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            entree = this.recupere_voeux_sans_demande_internat(
                    connecteurDonneesPropositions,
                    parametres,
                    true // paramètre seulementVoeuxEnAttente
            );
        }

        assertEquals(entree.voeux.size(), 0);
        // aucun voeu en attente dans le scénario établi

    }

    @Test
    public void test_recuperation_verification_voeux_en_attente_seulement_sans_demande_internat_avec_connecteur_prod_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        AlgoPropositionsEntree entree;
        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            entree = this.recupere_voeux_sans_demande_internat(
                    connecteurDonneesPropositions,
                    parametres,
                    true // paramètre seulementVoeuxEnAttente
            );
        }

        assertEquals(entree.voeux.size(), 0);
        // aucun voeu en attente dans le scénario établi

    }

    public AlgoPropositionsEntree recupere_propositions_actuelles(
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions,
            Parametres parametres
    ) throws Exception {

        AlgoPropositionsEntree entree = new AlgoPropositionsEntree(parametres);

        Whitebox.setInternalState(connecteurDonneesPropositions, "entree", entree);

        for (GroupeInternat internat : connecteurDonneesPropositions.recupererInternats().values()) {
            entree.ajouter(internat);
        }
        Whitebox.invokeMethod(
                connecteurDonneesPropositions,
                "recupererPropositions",
                entree.internatsIndex,
                false
        );

        return entree;

    }

    @Test
    public void test_recuperation_propositions_actuelles_avec_connecteur_toutes_donnees_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            AlgoPropositionsEntree entree = this.recupere_propositions_actuelles(
                    connecteurDonneesPropositions,
                    parametres
            );
        }

    }

    @Test
    public void test_recuperation_propositions_actuelles_avec_connecteur_prod_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            AlgoPropositionsEntree entree = this.recupere_propositions_actuelles(
                    connecteurDonneesPropositions,
                    parametres
            );
        }

    }

    public AlgoPropositionsEntree recupere_donnees(
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions,
            Parametres parametres
    ) throws Exception {

        AlgoPropositionsEntree entree = new AlgoPropositionsEntree(parametres);

        Whitebox.setInternalState(connecteurDonneesPropositions, "entree", entree);

        Whitebox.invokeMethod(
                connecteurDonneesPropositions,
                "recupererDonnees"
        );

        return entree;

    }

    @Test
    public void test_recuperation_donnees_avec_connecteur_prod_doit_reussir_si_base_verouillee() throws Exception {

        this.setValeurFlagInterruptionFluxDonnees(1);
        // on active le flag FLAG_INTERRUP_DONNEES dans la table G_PAR

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            // rappel : par défaut le connecteur est configuré avec : verifierInterruptionFluxDonneesEntrantes = true
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            AlgoPropositionsEntree entree = this.recupere_donnees(
                    connecteurDonneesPropositions,
                    parametres
            );
        }

    }

    @Test
    public void test_recuperation_donnees_avec_connecteur_prod_doit_echouer_si_base_verouillee() throws Exception {

        this.setValeurFlagInterruptionFluxDonnees(0);
        // on desactive le flag FLAG_INTERRUP_DONNEES dans la table G_PAR

        Throwable exception = assertThrows(AccesDonneesException.class, () -> {

            Parametres parametres = new Parametres(
                    this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                    this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                    this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
            );

            try (ConnecteurSQL connecteurSQL
                    = getConnecteurDonneesProd()) {
                // rappel : par défaut le connecteur est configuré avec : verifierInterruptionFluxDonneesEntrantes = true
                ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                        = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
                // rappel : le connecteur "prod" est configuré avec : verifierInterruptionFluxDonneesEntrantes = true
                AlgoPropositionsEntree entree = this.recupere_donnees(
                        connecteurDonneesPropositions,
                        parametres
                );
            }

        });

    }

    public Set<VoeuUID> test_recuperation_proposition_anterieure_dans_meme_formation(
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions,
            Parametres parametres
    ) throws Exception {

        AlgoPropositionsEntree entree = new AlgoPropositionsEntree(parametres);

        Whitebox.setInternalState(connecteurDonneesPropositions, "entree", entree);

        Set<VoeuUID> voeuxAvecPropositionAnterieureDansMemeFormation = Whitebox.invokeMethod(
                connecteurDonneesPropositions,
                "recupererVoeuxAvecPropositionAnterieureDansMemeFormation"
        );

        return voeuxAvecPropositionAnterieureDansMemeFormation;

    }

    @Test
    public void test_recuperation_voeux_avec_proposition_anterieure_dans_meme_formation_avec_connecteur_toutes_donnees_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            Set<VoeuUID> voeuxAvecPropositionAnterieureDansMemeFormation = this.test_recuperation_proposition_anterieure_dans_meme_formation(
                    connecteurDonneesPropositions,
                    parametres
            );
        }

    }

    @Test
    public void test_recuperation_voeux_avec_proposition_anterieure_dans_meme_formation_avec_connecteur_prod_doit_reussir() throws Exception {

        Parametres parametres = new Parametres(
                this.recupereBddNombreJoursEcoulesDepuisDebutCampagne(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtOuvertureCompleteInternats(),
                this.recupereBddNombreTotalJoursEntreDebutCampagneEtDebutGDD()
        );

        try (ConnecteurSQL connecteurSQL
                = getConnecteurDonneesProd()) {
            ConnecteurDonneesPropositionsSQL connecteurDonneesPropositions
                    = new ConnecteurDonneesPropositionsSQL(connecteurSQL.connection());
            Set<VoeuUID> voeuxAvecPropositionAnterieureDansMemeFormation = this.test_recuperation_proposition_anterieure_dans_meme_formation(
                    connecteurDonneesPropositions,
                    parametres
            );
        }

    }

}
