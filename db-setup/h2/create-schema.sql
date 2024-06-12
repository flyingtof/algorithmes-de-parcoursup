--------------------------------------------------------
--------------------------------------------------------
--------------------------------------------------------
--------------------------------------------------------
-- CREATION DES TABLES
--------------------------------------------------------
--------------------------------------------------------
--------------------------------------------------------
--------------------------------------------------------


-------------------------------------------------------
--  Création de la table A_ADM
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "A_ADM"
(   "G_CN_COD" NUMBER(8,0) NOT NULL,
    "G_TI_COD" NUMBER(8,0),
    "G_TA_COD" NUMBER(8,0) NOT NULL,
    "A_TA_COD" NUMBER(3,0) NOT NULL,
    "A_SV_COD" NUMBER(3,0) NOT NULL,
    "C_GP_COD" NUMBER(8,0),
    "C_GI_COD" NUMBER(8,0),
    "I_RH_COD" NUMBER(3,0) NOT NULL,
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_CN_COD", "G_TA_COD", "I_RH_COD")
);

--------------------------------------------------------
--  Création de la table A_ADM
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "A_ADM_DEM"
(   "G_CN_COD" NUMBER(8,0) NOT NULL,
    "G_TA_COD" NUMBER(8,0) NOT NULL,
    "I_RH_COD" NUMBER(3,0) NOT NULL,
    "C_GP_COD" NUMBER(8,0),
    "G_TI_COD" NUMBER(8,0),
    "C_GI_COD" NUMBER(8,0),
   	"A_AD_TYP_DEM" NUMBER(3,0) NOT NULL,
    "EST_DEM_PROP" NUMBER(1,0) NOT NULL,
    "NB_JRS" NUMBER(3,0) NOT NULL,
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_CN_COD", "G_TA_COD", "I_RH_COD", "NB_JRS")
);

 --------------------------------------------------------
 --  Création de la table A_ADM_PRED_DER_APP
 --------------------------------------------------------

CREATE TABLE IF NOT EXISTS "A_ADM_PRED_DER_APP"
(   "G_TA_COD" NUMBER(8,0) NOT NULL,
    "C_GP_COD" NUMBER(8,0) NOT NULL,
    "A_RG_RAN_DER" NUMBER(8,0) NOT NULL,
    "NB_JRS" NUMBER(3,0) NOT NULL,
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_TA_COD", "C_GP_COD", "NB_JRS")
);

 --------------------------------------------------------
 --  Création de la table A_ADM_PROP
 --------------------------------------------------------

CREATE TABLE IF NOT EXISTS "A_ADM_PROP"
(   "G_CN_COD" NUMBER(8,0) NOT NULL,
    "G_TA_COD" NUMBER(8,0) NOT NULL,
    "I_RH_COD" NUMBER(1,0) NOT NULL,
    "C_GP_COD" NUMBER(8,0) NOT NULL,
    "G_TI_COD" NUMBER(8,0) NOT NULL,
    "C_GI_COD" NUMBER(8,0),
    "A_AM_FLG_MBC" NUMBER(1,0),
    "NB_JRS" NUMBER(3,0) NOT NULL,
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_CN_COD", "G_TA_COD", "I_RH_COD", "NB_JRS")
);

--------------------------------------------------------
--  DDL de la Table A_REC
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "A_REC"
(   "G_TI_COD" NUMBER(8,0) NOT NULL,
    "G_TA_COD" NUMBER(8,0) NOT NULL,
    "A_RC_CAP" NUMBER(5,0),
    "A_RC_CAP_INT_FIL" NUMBER(5,0),
    "A_RC_CAP_INT_GAR" NUMBER(5,0),
    "A_RC_CAP_INF" NUMBER(4,0),
    "A_RC_FLG_TAU_BRS" NUMBER(1,0),
    "A_RC_TAU_BRS_REC" NUMBER(3,0),
    "A_RC_FLG_TAU_NON_RES" NUMBER(1,0),
    "A_RC_TAU_NON_RES_REC" NUMBER(3,0),
    "A_RC_FLG_FIN_RES_PLA" NUMBER(1,0),
    "A_RC_CAP_INT_MIX" NUMBER(5,0),
    "C_JA_COD" NUMBER(6,0),
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_TI_COD", "G_TA_COD")
);

--------------------------------------------------------
--  Création de la table A_REC_GRP
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "A_REC_GRP"
(   "C_GP_COD" NUMBER(6,0) NOT NULL,
    "G_TI_COD" NUMBER(8,0) NOT NULL,
    "G_TA_COD" NUMBER(8,0) NOT NULL,
    "C_JA_COD" NUMBER(6,0) NOT NULL,
    "A_RG_PLA" NUMBER(5,0),
    "A_RG_NBR_SOU" NUMBER(5,0),
    "A_RG_RAN_LIM" NUMBER(5,0),
    "A_RG_FLG_ADM_STOP" NUMBER(1,0),
    "A_RG_NBR_ATT" NUMBER(6,0),
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("C_GP_COD", "G_TI_COD", "G_TA_COD")
);

--------------------------------------------------------
--  Création de la table A_REC_GRP_INT
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "A_REC_GRP_INT"
(   "C_GI_COD" NUMBER(6,0) NOT NULL,
    "G_TI_COD" NUMBER(8,0),
    "G_TA_COD" NUMBER(8,0),
    "G_EA_COD_INS" VARCHAR2(8 CHAR),
    "A_RI_NBR_SOU" NUMBER(5,0),
     "ETIQUETTE1" VARCHAR2(150 CHAR),
     A_RG_NBR_ATT" NUMBER(6,0),
    PRIMARY KEY("C_GI_COD")
);

--------------------------------------------------------
--  Création de la table A_REC_GRP_INT_PROP
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "A_REC_GRP_INT_PROP"
(   "C_GI_COD" NUMBER(8,0) NOT NULL,
    "G_TA_COD" NUMBER(8,0) NOT NULL,
    "G_TI_COD" NUMBER(8,0) NOT NULL,
    "C_GP_COD" NUMBER(8,0) NOT NULL,
    "A_RG_RAN_DER" NUMBER(8,0) NOT NULL,
    "A_RG_RAN_DER_INT" NUMBER(8,0),
    "NB_JRS" NUMBER(3,0) NOT NULL,
    "ETIQUETTE1" VARCHAR2(150 CHAR),
	"A_RG_NBR_ATT" NUMBER(6,0),
	"A_RG_FLG_ADM_STOP" NUMBER(1,0),
	"A_RG_POS_MAX_ADM_INT"  NUMBER(10,0),
    PRIMARY KEY("C_GI_COD", "G_TA_COD", "G_TI_COD", "C_GP_COD", "NB_JRS")
);

--------------------------------------------------------
--  Création de la table A_SIT_VOE
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "A_SIT_VOE"
(   "A_SV_COD" NUMBER(3,0) NOT NULL,
    "A_SV_FLG_AFF" NUMBER(1,0) NOT NULL,
    "A_SV_FLG_ATT" NUMBER(1,0) NOT NULL,
	"A_SV_FLG_CLO" NUMBER(1,0) NOT NULL,
    "A_SV_FLG_OUI" NUMBER(1,0) NOT NULL,
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("A_SV_COD")
);

--------------------------------------------------------
--  Création de la table A_VOE
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "A_VOE"
(   "G_CN_COD" NUMBER(8,0) NOT NULL,
    "G_TA_COD" NUMBER(8,0) NOT NULL,
    "I_RH_COD" NUMBER(3,0) NOT NULL,
    "A_SV_COD" NUMBER(3,0) NOT NULL,
    "A_VE_ORD" NUMBER(3,0),
    "A_VE_TYP_MAJ" NUMBER(3,0),
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_CN_COD", "G_TA_COD", "I_RH_COD")
);

--------------------------------------------------------
--  Création de la table A_VOE_PROP
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "A_VOE_PROP"
(   "G_CN_COD" NUMBER(8,0) NOT NULL,
    "G_TA_COD" NUMBER(8,0) NOT NULL,
    "I_RH_COD" NUMBER(3,0) NOT NULL,
    "NB_JRS" NUMBER(3,0) NOT NULL,
    "G_TI_COD" NUMBER(8,0),
    "C_GP_COD" NUMBER(8,0),
    "A_VE_RAN_LST_ATT" NUMBER(8,0) NOT NULL,
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_CN_COD", "G_TA_COD", "I_RH_COD", "NB_JRS")
);

--------------------------------------------------------
--  Création de la table C_CAN_GRP
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "C_CAN_GRP"
(   "G_CN_COD" NUMBER(8,0) NOT NULL,
    "C_GP_COD" NUMBER(6,0) NOT NULL,
    "I_IP_COD" NUMBER(3,0),
    "C_CG_RAN" NUMBER(5,0),
    "C_CG_ORD_APP" NUMBER(6,0),
    "C_CG_ORD_APP_AFF" NUMBER(6,0),
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_CN_COD", "C_GP_COD")
);

--------------------------------------------------------
--  Création de la table C_CAN_GRP_INT
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "C_CAN_GRP_INT"
(   "G_CN_COD" NUMBER(8,0) NOT NULL,
    "C_GI_COD" NUMBER(6,0) NOT NULL,
    "I_IP_COD" NUMBER(3,0),
    "C_CI_RAN" NUMBER(5,0),
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_CN_COD", "C_GI_COD")
);

--------------------------------------------------------
--  Création de la table C_GRP
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "C_GRP"
(   "C_GP_COD" NUMBER(6,0) NOT NULL,
    "C_GP_FLG_PAS_CLA" NUMBER(1,0),
    "C_JA_COD" NUMBER(6,0),
    "C_GP_ETA_CLA" NUMBER(3,0),
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("C_GP_COD")
);

--------------------------------------------------------
--  Création de la table C_JUR_ADM
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "C_JUR_ADM"
(   "C_JA_COD" NUMBER(6,0) NOT NULL,
    "G_TI_COD" NUMBER(8,0),
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("C_JA_COD")
);

--------------------------------------------------------
--  Création de la table G_CAN
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "G_CAN"
(   "G_CN_COD" NUMBER(8,0) NOT NULL,
    "G_IC_COD" NUMBER(3,0) NOT NULL,
    "G_CN_FLG_BRS_CER" NUMBER(1,0),
    "G_CN_BRS" NUMBER(1,0),
    "I_CL_COD_BAC" VARCHAR2(8 CHAR),
    "G_CN_FLG_RA" NUMBER(3,0),
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_CN_COD")
);

--------------------------------------------------------
--  Création de la table G_FIL
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "G_FIL"
(   "G_FL_COD" NUMBER(6,0) NOT NULL,
    "G_FL_COD_FI" NUMBER(6,0),
    "G_FL_FLG_APP" NUMBER(1,0),
    "G_FL_LIB" VARCHAR2(150 CHAR),
    "G_FL_SIG" VARCHAR2(20 CHAR),
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_FL_COD")
);


--------------------------------------------------------
--  Création de la table G_FOR
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "G_FOR"
(   "G_FR_COD" NUMBER(6,0) NOT NULL,
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_FR_COD")
);


-------------------------------------------------------
--  Création de la table G_PAR
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "G_PAR"
(   "G_PR_COD" NUMBER(3,0) NOT NULL,
    "G_PR_VAL" VARCHAR2(800 BYTE),
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_PR_COD")
);


--------------------------------------------------------
--  Création de la table G_TRI_AFF
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "G_TRI_AFF"
(   "G_TA_COD" NUMBER(8,0) NOT NULL,
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_TA_COD")
);

--------------------------------------------------------
--  Création de la table G_TRI_INS
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "G_TRI_INS"
(   "G_TI_COD" NUMBER(8,0) NOT NULL,
    "G_EA_COD_INS" VARCHAR2(8 CHAR) NOT NULL,
    "G_FR_COD_INS" NUMBER(6,0) NOT NULL,
    "G_FL_COD_INS" NUMBER(6,0) NOT NULL,
    "G_TI_FLG_PAR_EFF" NUMBER(1,0),
    "G_TI_CLA_INT_UNI" NUMBER(3,0),
    "G_TI_ETA_CLA" NUMBER(3,0),
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_TI_COD")
 );


--------------------------------------------------------
--  Création de la table I_INS
--------------------------------------------------------

CREATE TABLE IF NOT EXISTS "I_INS"
(   "G_CN_COD" NUMBER(8,0) NOT NULL,
    "G_TI_COD" NUMBER(8,0) NOT NULL ,
    "I_IS_FLC_SEC" NUMBER(1,0),
    "I_IS_VAL" NUMBER(1,0),
    "ETIQUETTE1" VARCHAR2(150 CHAR),
    PRIMARY KEY("G_CN_COD", "G_TI_COD")
);

--------------------------------------------------------
--  Création de la table J_ORD_APPEL_TMP
--------------------------------------------------------

CREATE LOCAL TEMPORARY TABLE "J_ORD_APPEL_TMP"
(   "C_GP_COD" NUMBER(6,0) NOT NULL,
    "G_CN_COD" NUMBER(8,0) NOT NULL,
    "C_CG_ORD_APP" NUMBER(6,0),
    PRIMARY KEY("C_GP_COD", "G_CN_COD")
);


--------------------------------------------------------
--------------------------------------------------------
--------------------------------------------------------
--------------------------------------------------------
-- CREATION DES FONCTIONS
--------------------------------------------------------
--------------------------------------------------------
--------------------------------------------------------
--------------------------------------------------------


--------------------------------------------------------
--  Création des fonctions de calcul de dates
-- différentes du code pl/sql car h2 ne suppporte
-- pas le stockage de fonctions pl/sql
--------------------------------------------------------

DROP ALIAS IF EXISTS f_propGetNbJrsFromParam;
CREATE ALIAS f_propGetNbJrsFromParam AS $$
int propGetNbJrsFromParam(Integer o_g_pr_cod) {
    switch(o_g_pr_cod) {
        case 35: return 1;
        case 334: return 30;
        case 316: return 45;
        case 437: return 48;
        default: return 12;
    }
}
$$;

DROP ALIAS IF EXISTS f_propGetNbJrsNow;
CREATE ALIAS f_propGetNbJrsNow AS $$
int propGetNbJrsNow() {
    return 34;
}
$$;



--------------------------------------------------------
--------------------------------------------------------
--------------------------------------------------------
--------------------------------------------------------
-- CREATION DES VUES
--------------------------------------------------------
--------------------------------------------------------
--------------------------------------------------------
--------------------------------------------------------


--------------------------------------------------------
--  Création des vues utilisées par l'algo d'admission
--------------------------------------------------------

 --------------------------------------------------------
 --  Création des vues utilisées par l'algo d'admission
 --------------------------------------------------------

 CREATE OR REPLACE VIEW V_PROP_CAN_RA
 AS SELECT  G_CN_COD FROM  G_CAN can WHERE  NVL(can.g_cn_flg_ra,0) = 1;
 COMMENT ON TABLE V_PROP_CAN_RA IS 'candidats ayant activé le répondeur automatique';

 CREATE OR REPLACE VIEW V_PROP_RAN_DER_APP
 AS SELECT
     rec.g_ta_cod g_ta_cod,
     rec.g_ti_cod g_ti_cod,
     rec.c_gp_cod c_gp_cod,
     MAX(NVL(C_CG_ORD_APP ,-1)) ran_der_app
  FROM  A_ADM_PROP  adm,  C_CAN_GRP  cla,  A_REC_GRP  rec
  WHERE adm.g_cn_cod=cla.g_cn_cod
  AND adm.c_gp_cod=cla.c_gp_cod
  AND adm.c_gp_cod=rec.c_gp_cod
  AND adm.g_ta_cod=rec.g_ta_cod
  AND adm.g_ti_cod=rec.g_ti_cod
  AND  rec.a_rg_nbr_sou > 0
  AND  cla.i_ip_cod=5
  AND  cla.c_cg_ord_app is not null
  GROUP BY  rec.g_ta_cod,rec.g_ti_cod,rec.c_gp_cod;

 COMMENT ON TABLE V_PROP_RAN_DER_APP IS 'rang du dernier appelé dans chaque groupe d''affectation';



 CREATE OR REPLACE VIEW V_PROP_REC_GRP
 AS SELECT
 c_gp_cod,
 rec.g_ti_cod g_ti_cod,
 rec.g_ta_cod g_ta_cod,
 A_RG_NBR_SOU capacite,
 NVL(a_rg_ran_lim,0) a_rg_ran_lim,
 NVL(rec.a_rg_flg_adm_stop,0) a_rg_flg_adm_stop,
 NVL(r.a_rc_flg_fin_res_pla,0) a_rc_flg_fin_res_pla
 FROM  A_REC_GRP  rec,  A_REC  r
 WHERE rec.g_ta_cod=r.g_ta_cod;

 COMMENT ON TABLE V_PROP_REC_GRP IS 'groupes, capacités, flag d''arrêt d''admission et de fin de réservation de places';


 CREATE OR REPLACE VIEW V_PROP_REC_GRP_INT
 AS SELECT C_GI_COD, NVL(g_ta_cod,0) g_ta_cod, NVL(g_ti_cod,0) g_ti_cod,A_RI_NBR_SOU  FROM  A_REC_GRP_INT;

 COMMENT ON TABLE V_PROP_REC_GRP_INT IS 'groupes internats';





  CREATE OR REPLACE VIEW V_PROP_VOE
  AS SELECT
  v.g_cn_cod,
  v.g_ta_cod,
  v.i_rh_cod,
  NVL(v.a_ve_ord,0) a_ve_ord,
  ti.g_ti_cod,
  ti.g_ea_cod_ins,
  case when ti.g_ti_cla_int_uni in (0,1) then 1 else 0 end flg_int_cla_prop,
  cg.c_gp_cod,
  cg.c_cg_ord_app,
  cg.c_cg_ord_app_aff,
  NVL(cg.c_cg_ord_app,cg.c_cg_ran) rang,
  sv.a_sv_flg_aff,
  sv.a_sv_flg_oui,
  sv.a_sv_cod,
  case when (cg.i_ip_cod=5 AND c.g_ic_cod >= 0 AND ti.g_ti_eta_cla=2) then 1 else 0 end flg_cla,
  case when (sv.a_sv_flg_att=1 OR sv.a_sv_flg_clo=1) then 1 else 0 end a_sv_flg_att_clo,
  case when (sv.a_sv_cod > -40) then 1 else 0 end flg_valid,
  case when (cg.C_CG_ORD_APP is not null) then 1 else 0 end flg_ord_app,
      --flag a_ve_typ_maj gardant trace des éventuelles modifs de classement.
      --Exemples:
      -- 0 : RAS
      -- 1 : Annulation de démission d'un voeu (on ne tient pas compte de ces candidats dans le calcul des listes d'attente)
      -- 10 : Modification de classement : Candidat non classé devient classé (on ne tient pas compte de ces candidats dans le calcul des listes d'attente)
      -- 30 : Utilisé dans la mise à jour des classements erronés pour les candidats qui passent de classés à non classés dans le
      --    classement corrigé (On tient compte de ces candidats dans le calcul des listes d'attente)
      -- 40 : Affectation par les CAES d'un candidat avec motif dérogatoire sur une CPGE avec internat.
  case when (NVL(v.a_ve_typ_maj,0) in (1,10,11,30,31)) then 1 else 0 end flg_ign_rang_att,--non pris en compte dans le calcul des rangs sur liste d'attente
  case when (NVL(v.a_ve_typ_maj,0) in (40,41)) then 1 else 0 end flg_ign_bar_int,--non pris en compte dans le calcul des barres à l'internat
  0 a_ve_ran_lst_att_vei --Rang dans la liste attente de la veille
  FROM
  G_CAN  c,
  A_VOE  v,
  A_SIT_VOE  sv,
  A_REC_GRP  rg,
  C_CAN_GRP  cg,
  G_TRI_INS  ti
  WHERE
   cg.C_CG_ORD_APP is not null
  AND  c.g_cn_cod=v.g_cn_cod
  AND  v.a_sv_cod=sv.a_sv_cod
  AND  v.g_cn_cod=cg.g_cn_cod
  AND  cg.c_gp_cod=rg.c_gp_cod
  AND  rg.g_ta_cod=v.g_ta_cod
  AND  rg.g_ti_cod=ti.g_ti_cod
ORDER BY g_ta_cod, c_gp_cod, c_cg_ord_app, a_sv_cod;

  COMMENT ON TABLE V_PROP_VOE IS
  'Voeux avec classements pédagogiques et info inscription. En prod admission, seuls les  voeux satisfaisants flg_cla=1 AND a_sv_flg_att_clo=1 AND flg_ord_app=1 sont récupérés.';

  CREATE OR REPLACE VIEW V_PROP_VOE_INT
  AS SELECT
  v.g_cn_cod,
  v.g_ta_cod,
  v.g_ti_cod,
  v.a_ve_ord,
  v.c_gp_cod,
  v.rang,
  v.c_cg_ord_app,
  v.c_cg_ord_app_aff,
  v.a_sv_flg_aff,
  v.a_sv_flg_oui,
  v.g_ea_cod_ins,
  v.a_sv_flg_att_clo,
  v.flg_cla,
  v.flg_valid,
  v.flg_ord_app,
  v.flg_ign_rang_att,
  v.flg_ign_bar_int,
  cgi.c_gi_cod,
  cgi.c_ci_ran
  FROM
  V_PROP_VOE v,
  A_REC_GRP_INT rgi,
  C_CAN_GRP_INT cgi
  WHERE
  v.i_rh_cod=1
  AND v.flg_int_cla_prop = 1
  AND v.g_cn_cod = cgi.g_cn_cod
  AND cgi.c_gi_cod = rgi.c_gi_cod
  AND  (
            (
            -- internat par établissement identifié par code établissement g_ea_cod_ins
            -- -> un seul groupe de classement
             rgi.g_ta_cod is null
             AND rgi.g_ti_cod is null
             AND rgi.g_ea_cod_ins is not null
             AND rgi.g_ea_cod_ins=v.g_ea_cod_ins
             )
         OR
            (
            -- internat par établissement identifié par code inscription g_ti_cod
            -- -> un seul groupe de classement
            rgi.g_ta_cod is null
            AND rgi.g_ti_cod is not null
            AND rgi.g_ea_cod_ins is null
            AND rgi.g_ti_cod=v.g_ti_cod)
         OR
            (
            -- internat par formation
            rgi.g_ta_cod is not null
            AND rgi.g_ta_cod=v.g_ta_cod
            AND rgi.g_ti_cod=v.g_ti_cod
            )
       );

  COMMENT ON TABLE V_PROP_VOE_INT IS
  'Voeux avec internats, avec classement pédagogique et classement internat. En prod admission, seuls les  voeux satisfaisants flg_cla=1 AND a_sv_flg_att_clo=1 AND flg_ord_app=1 sont récupérés.';

 CREATE OR REPLACE VIEW V_PROP_ADM
 AS SELECT rec.g_ta_cod g_ta_cod,
 rec.g_ti_cod g_ti_cod,
 rec.c_gp_cod c_gp_cod,
 adm.nb_jrs nb_jrs,
 NVL(C_CG_ORD_APP ,-1) c_cg_ord_app
     FROM  A_ADM_PROP  adm,  C_CAN_GRP  cla,  A_REC_GRP  rec
     WHERE adm.g_cn_cod=cla.g_cn_cod
     AND adm.c_gp_cod=cla.c_gp_cod
     AND adm.c_gp_cod=rec.c_gp_cod
     AND adm.g_ta_cod=rec.g_ta_cod
     AND adm.g_ti_cod=rec.g_ti_cod
     AND  rec.a_rg_nbr_sou > 0
     AND  cla.i_ip_cod=5
     AND  cla.c_cg_ord_app is not null;

 COMMENT ON TABLE V_PROP_ADM IS 'propositions d''admission générées par l''algorithme d''affectation, incluant  le rang dans l''ordre d''appel et le jour de la proposition';


 CREATE OR REPLACE VIEW V_PROP_PROP
 AS SELECT
             adm.G_CN_COD g_cn_cod,
             adm.G_TI_COD g_ti_cod,
             adm.g_ta_cod g_ta_cod,
             adm.I_RH_COD i_rh_cod,
             adm.C_GP_COD c_gp_cod,
             adm.C_GI_COD c_gi_cod,
             CASE WHEN adm.a_ta_cod in (1,8) THEN 1 ELSE 0 END flg_adm_pp,
             NVL(voe.a_ve_ord,0) a_ve_ord,
             sv.a_sv_flg_oui a_sv_flg_oui,
             sv.a_sv_flg_aff a_sv_flg_aff
         FROM   A_ADM  adm
         LEFT JOIN  A_SIT_VOE  sv
             ON  adm.a_sv_cod=sv.a_sv_cod
         LEFT JOIN  A_VOE  voe
             ON adm.g_cn_cod=voe.g_cn_cod  AND  adm.g_ta_cod=voe.g_ta_cod  AND  adm.i_rh_cod=voe.i_rh_cod,  G_TRI_INS  ti
         WHERE  sv.a_sv_flg_aff=1
         AND  adm.g_ti_cod=ti.g_ti_cod
         AND  adm.a_ta_cod != 2;

 COMMENT ON TABLE V_PROP_PROP IS 'récupère les propositions hors apprentissage, y compris les propositions refusées';

 
 CREATE OR REPLACE VIEW V_PROP_ATT_PROP_ANT AS
 SELECT  v.G_CN_COD,v.g_ta_cod,v.I_RH_COD
 FROM
 A_VOE v,
 A_SIT_VOE asv,
 A_ADM adm
 WHERE
 v.a_sv_cod = asv.a_sv_cod
 AND asv.a_sv_flg_att=1
 AND v.G_CN_COD=adm.G_CN_COD
 AND v.g_ta_cod=adm.g_ta_cod
 AND adm.A_TA_COD=1;

 COMMENT ON TABLE V_PROP_ATT_PROP_ANT IS 'voeux en attente dont les candidats ont déjà eu une proposition dans la même formation';
 