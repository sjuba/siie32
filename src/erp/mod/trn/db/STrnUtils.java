/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.mod.trn.db;

import erp.client.SClientInterface;
import erp.data.SDataConstantsSys;
import erp.lib.SLibConstants;
import erp.mod.SModConsts;
import erp.mod.SModSysConsts;
import erp.mod.bps.db.SBpsUtils;
import erp.mod.bps.db.SDbBizPartner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import sa.gui.util.SUtilConsts;
import sa.lib.SLibConsts;
import sa.lib.SLibTimeUtils;
import sa.lib.SLibUtils;
import sa.lib.db.SDbRegistry;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiSession;

/**
 *
 * @author Sergio Flores
 */
public abstract class STrnUtils {

    public static String getDpsCategoryName(final int idCategory, final int number) {
        String name = "";

        switch (idCategory) {
            case SModSysConsts.TRNS_CT_DPS_PUR:
                name = number == SUtilConsts.NUM_SNG ? "Compra" : "Compras";
                break;
            case SModSysConsts.TRNS_CT_DPS_SAL:
                name = number == SUtilConsts.NUM_SNG ? "Venta" : "Ventas";
                break;
            default:
        }

        return name;
    }

    public static int getBizPartnerCategoryId(final int idDpsCategory) {
        int idCategory = SLibConsts.UNDEFINED;

        switch (idDpsCategory) {
            case SModSysConsts.TRNS_CT_DPS_PUR:
                idCategory = SModSysConsts.BPSS_CT_BP_SUP;
                break;
            case SModSysConsts.TRNS_CT_DPS_SAL:
                idCategory = SModSysConsts.BPSS_CT_BP_CUS;
                break;
            default:
        }

        return idCategory;
    }

    public static double obtainLastPriceForSupplierItem(final SGuiSession session, final int nDpsCategory, final int pnItemId, final String psBizPartnersIds) throws java.lang.Exception {
        double dItemDiscountUnitaryCur = 0;
        String sql = "";
        ResultSet resultSet = null;

        sql = "SELECT d.fid_bp_r, d.dt_doc, de.price_u_cur, de.disc_u_cur " +
            "FROM trn_dps AS d " +
            "INNER JOIN trn_dps_ety AS de ON d.id_year = " + session.getCurrentYear() + " AND d.b_del = FALSE AND de.b_del = FALSE AND d.id_year = de.id_year AND " +
            "d.id_doc = de.id_doc AND d.fid_bp_r IN (" + psBizPartnersIds + ") AND de.fid_item = " + pnItemId + " AND " +
            (nDpsCategory == SModSysConsts.TRNS_CT_DPS_PUR ? "d.fid_ct_dps = " + SDataConstantsSys.TRNU_TP_DPS_PUR_ORD[0] + " AND " + "d.fid_cl_dps = " + SDataConstantsSys.TRNU_TP_DPS_PUR_ORD[1] + " AND " +
            "d.fid_tp_dps = " + SDataConstantsSys.TRNU_TP_DPS_PUR_ORD[2] : "d.fid_ct_dps = " + SDataConstantsSys.TRNU_TP_DPS_SAL_ORD[0] + " AND " +
            "d.fid_cl_dps = " + SDataConstantsSys.TRNU_TP_DPS_SAL_ORD[1] + " AND " +
            "d.fid_tp_dps = " + SDataConstantsSys.TRNU_TP_DPS_SAL_ORD[2]) + " " +
            " AND d.fid_st_dps = " + SDataConstantsSys.TRNS_ST_DPS_EMITED + " " +
            "GROUP BY d.id_year, d.id_doc " +
            "ORDER BY de.tot_r ASC, d.dt_doc DESC ";

        resultSet = session.getStatement().executeQuery(sql);
        if (resultSet.next()) {
            dItemDiscountUnitaryCur = resultSet.getObject(1) == null ? 0d : resultSet.getDouble(1);
        }

        return dItemDiscountUnitaryCur;
    }

    public static String formatDocNumber(final String series, final String number) {
        return (series.isEmpty() ? "" : series + "-") + number;
    }
    
    public static int obtainNextNumberForDps(SGuiSession session, String series, int[] docClassTypeKey) throws java.lang.Exception {
        int number = 0;
        String sql = "";
        ResultSet resultSet = null;

        sql = "SELECT MAX(CONVERT(num, UNSIGNED INTEGER)) + 1 AS f_num FROM trn_dps " +
                "WHERE fid_ct_dps = " + ((int[]) docClassTypeKey)[0] + " AND fid_cl_dps = " + ((int[]) docClassTypeKey)[1] + " AND " +
                (((int[]) docClassTypeKey).length <= 2 ? "" : "fid_tp_dps = " + ((int[]) docClassTypeKey)[2] + " AND ") +
                "num_ser = '" + series + "' AND b_del = 0 ";

        resultSet = session.getStatement().executeQuery(sql);
        if (resultSet.next()) {
            if (resultSet.getObject("f_num") != null) {
                number = resultSet.getInt("f_num");
            }
        }

        return number;
    }

    public static double obtainExchangeRate(SGuiSession session, int currencyId, Date date) throws java.lang.Exception {
        double rate = 0;
        String sql = "";
        ResultSet resultSet = null;

        sql = "SELECT exc_rate FROM fin_exc_rate WHERE id_cur = " + currencyId + " AND " +
                "id_dt = '" + SLibUtils.DbmsDateFormatDate.format(date) + "' AND b_del = FALSE ";

        resultSet = session.getStatement().executeQuery(sql);
        if (resultSet.next()) {
            rate = resultSet.getDouble("exc_rate");
        }

        return rate;
    }

    public static java.lang.String obtainCostCenterItem(SGuiSession session, int nItemId) throws java.lang.Exception {
        String sql = "";
        ResultSet resultSet = null;
        String costCenter = "";

        sql = "SELECT cci.id_tp_link, cci.fid_cc " +
                "FROM fin_cc_item AS cci WHERE cci.b_del = 0 AND (" +
                "(cci.id_tp_link = " + SDataConstantsSys.TRNS_TP_LINK_ITEM + " AND cci.id_ref = " + nItemId + ") OR " +
                "(cci.id_tp_link = " + SDataConstantsSys.TRNS_TP_LINK_MFR + " AND cci.id_ref IN (SELECT i.fid_mfr FROM erp.itmu_item AS i WHERE i.id_item = " + nItemId + ")) OR " +
                "(cci.id_tp_link = " + SDataConstantsSys.TRNS_TP_LINK_BRD + " AND cci.id_ref IN (SELECT i.fid_brd FROM erp.itmu_item AS i WHERE i.id_item = " + nItemId + ")) OR " +
                "(cci.id_tp_link = " + SDataConstantsSys.TRNS_TP_LINK_LINE + " AND cci.id_ref IN (SELECT i.fid_line_n FROM erp.itmu_item AS i WHERE i.id_item = " + nItemId + ")) OR " +
                "(cci.id_tp_link = " + SDataConstantsSys.TRNS_TP_LINK_IGEN + " AND cci.id_ref IN (SELECT i.fid_igen FROM erp.itmu_item AS i WHERE i.id_item = " + nItemId + ")) OR " +
                "(cci.id_tp_link = " + SDataConstantsSys.TRNS_TP_LINK_IGRP + " AND cci.id_ref IN (SELECT igen.fid_igrp FROM erp.itmu_igen AS igen INNER JOIN erp.itmu_item AS i ON igen.id_igen = i.fid_igen AND i.id_item = " + nItemId + ")) OR " +
                "(cci.id_tp_link = " + SDataConstantsSys.TRNS_TP_LINK_IFAM + " AND cci.id_ref IN (SELECT igrp.fid_ifam FROM erp.itmu_igrp AS igrp INNER JOIN erp.itmu_igen AS igen ON igrp.id_igrp = igen.fid_igrp INNER JOIN erp.itmu_item AS i ON igen.id_igen = i.fid_igen AND i.id_item = " + nItemId + ")) OR " +
                "(cci.id_tp_link = " + SDataConstantsSys.TRNS_TP_LINK_TP_ITEM + " AND cci.id_ref IN (SELECT itp.tp_idx FROM erp.itmu_igen AS igen INNER JOIN erp.itmu_item AS i ON igen.id_igen = i.fid_igen AND i.id_item = " + nItemId + " INNER JOIN erp.itms_tp_item AS itp ON igen.fid_ct_item = itp.id_ct_item AND igen.fid_cl_item = itp.id_cl_item AND igen.fid_tp_item = itp.id_tp_item)) OR " +
                "(cci.id_tp_link = " + SDataConstantsSys.TRNS_TP_LINK_CL_ITEM + " AND cci.id_ref IN (SELECT icl.cl_idx FROM erp.itmu_igen AS igen INNER JOIN erp.itmu_item AS i ON igen.id_igen = i.fid_igen AND i.id_item = " + nItemId + " INNER JOIN erp.itms_cl_item AS icl ON igen.fid_ct_item = icl.id_ct_item AND igen.fid_cl_item = icl.id_cl_item)) OR " +
                "(cci.id_tp_link = " + SDataConstantsSys.TRNS_TP_LINK_CT_ITEM + " AND cci.id_ref IN (SELECT ict.ct_idx FROM erp.itmu_igen AS igen INNER JOIN erp.itmu_item AS i ON igen.id_igen = i.fid_igen AND i.id_item = " + nItemId + " INNER JOIN erp.itms_ct_item AS ict ON igen.fid_ct_item = ict.id_ct_item)) OR " +
                "(cci.id_tp_link = " + SDataConstantsSys.TRNS_TP_LINK_ALL + " AND cci.id_ref = " + SLibConstants.UNDEFINED + ")) " +
                "ORDER BY cci.id_tp_link DESC ";

        resultSet = session.getStatement().executeQuery(sql);
        if (resultSet.next()) {
            costCenter = resultSet.getString("cci.fid_cc");
        }

        return costCenter;
    }

    public static int[] getDpsKey(final SGuiSession session, final int idDpsCategory, final int[] keyBizPartner, final String series, final String number) {
        int[] key = null;
        String sql = "";
        ResultSet resultSet = null;

        try {
            sql = "SELECT id_year, id_doc "
                    + "FROM " + SModConsts.TablesMap.get(SModConsts.TRN_DPS) + " "
                    + "WHERE fid_ct_dps = " + idDpsCategory + " AND fid_bp_r = " + keyBizPartner[0] + " AND b_del = 0 AND "
                    + "num_ser = '" + series + "' AND num = '" + number + "' ";
            resultSet = session.getStatement().executeQuery(sql);
            if (resultSet.next()) {
                key = new int[] { resultSet.getInt(1), resultSet.getInt(2) };
            }
        }
        catch (Exception e) {
            SLibUtils.showException(STrnUtils.class.getName(), e);
        }

        return key;
    }
    
    public static boolean existDpsItemConfig(final SGuiSession session, final int[] keyDpsType, final int idItem) throws Exception {
        String sql = "";
        boolean exists = false;
        ArrayList<int[]> linkTypes = new ArrayList<>();
        Statement statement = session.getStatement().getConnection().createStatement();
        Statement statementCfg = session.getStatement().getConnection().createStatement();
        ResultSet resultSet = null;
        ResultSet resultSetCfg = null;

        // Read configuration for item:

        sql = "SELECT fk_tp_link, fk_ref " 
                + "FROM " + SModConsts.TablesMap.get(SModConsts.TRNU_TP_DPS_SRC_ITEM) + " "
                + "WHERE b_del = 0 AND id_ct_dps = " + keyDpsType[0] + " AND "
                + "id_cl_dps = " + keyDpsType[1]  + " AND "
                + "id_tp_dps = " + keyDpsType[2]  + " " 
                + "ORDER BY fk_tp_link ASC; ";
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            linkTypes.add(new int[] { resultSet.getInt(1), resultSet.getInt(2) });
        }

        // Read item by link type:

        for (int[] linkType : linkTypes) {
            sql = "";
            
            switch(linkType[0]) {
                case SDataConstantsSys.TRNS_TP_LINK_ITEM:
                    sql = "SELECT c.id_ct_dps, c.id_cl_dps, c.id_tp_dps, c.id_cfg, c.fk_tp_link, c.fk_ref "
                            + "FROM " + SModConsts.TablesMap.get(SModConsts.TRNU_TP_DPS_SRC_ITEM) + " AS c "
                            + "INNER JOIN erp.itmu_item AS i ON c.fk_ref = i.id_item "
                            + "WHERE c.b_del = 0 AND i.id_item = " + idItem + " AND c.id_ct_dps = " + keyDpsType[0] + " AND "
                            + "c.id_cl_dps = " + keyDpsType[1]  + " AND "
                            + "c.id_tp_dps = " + keyDpsType[2]  + "; ";
                    break;

                case SDataConstantsSys.TRNS_TP_LINK_MFR:
                    sql = "SELECT c.id_ct_dps, c.id_cl_dps, c.id_tp_dps, c.id_cfg, c.fk_tp_link, c.fk_ref "
                            + "FROM " + SModConsts.TablesMap.get(SModConsts.TRNU_TP_DPS_SRC_ITEM) + " AS c, erp.itmu_mfr AS mfr "
                            + "INNER JOIN erp.itmu_item AS i ON mfr.id_mfr = i.fid_mfr "
                            + "WHERE c.b_del = 0 AND c.fk_tp_link = " + linkType[0] + " AND c.fk_ref = " + linkType[1] + " AND  i.id_item = " + idItem + " AND i.fid_mfr = " + linkType[1] + " AND c.id_ct_dps = " + keyDpsType[0] + " AND "
                            + "c.id_cl_dps = " + keyDpsType[1]  + " AND "
                            + "c.id_tp_dps = " + keyDpsType[2]  + "; ";
                    break;

                case SDataConstantsSys.TRNS_TP_LINK_BRD:
                    sql = "SELECT c.id_ct_dps, c.id_cl_dps, c.id_tp_dps, c.id_cfg, c.fk_tp_link, c.fk_ref "
                            + "FROM " + SModConsts.TablesMap.get(SModConsts.TRNU_TP_DPS_SRC_ITEM) + " AS c, erp.itmu_brd AS brd "
                            + "INNER JOIN erp.itmu_item AS i ON brd.id_brd = i.fid_brd "
                            + "WHERE c.b_del = 0 AND c.fk_tp_link = " + linkType[0] + " AND c.fk_ref = " + linkType[1] + " AND  i.id_item = " + idItem + " AND i.fid_brd = " + linkType[1] + " AND c.id_ct_dps = " + keyDpsType[0] + " AND "
                            + "c.id_cl_dps = " + keyDpsType[1]  + " AND "
                            + "c.id_tp_dps = " + keyDpsType[2]  + "; ";
                    break;
                case SDataConstantsSys.TRNS_TP_LINK_LINE:
                    sql = "SELECT c.id_ct_dps, c.id_cl_dps, c.id_tp_dps, c.id_cfg, c.fk_tp_link, c.fk_ref "
                            + "FROM " + SModConsts.TablesMap.get(SModConsts.TRNU_TP_DPS_SRC_ITEM) + " AS c, erp.itmu_line AS l "
                            + "INNER JOIN erp.itmu_item AS i ON l.id_line = i.fid_line_n "
                            + "WHERE c.b_del = 0 AND c.fk_tp_link = " + linkType[0] + " AND c.fk_ref = " + linkType[1] + " AND  i.id_item = " + idItem + " AND i.fid_line_n = " + linkType[1] + " AND c.id_ct_dps = " + keyDpsType[0] + " AND "
                            + "c.id_cl_dps = " + keyDpsType[1]  + " AND "
                            + "c.id_tp_dps = " + keyDpsType[2]  + "; ";
                    break;
                case SDataConstantsSys.TRNS_TP_LINK_IGEN:
                    sql = "SELECT c.id_ct_dps, c.id_cl_dps, c.id_tp_dps, c.id_cfg, c.fk_tp_link, c.fk_ref "
                            + "FROM " + SModConsts.TablesMap.get(SModConsts.TRNU_TP_DPS_SRC_ITEM) + " AS c, erp.itmu_igen AS g "
                            + "INNER JOIN erp.itmu_item AS i ON g.id_igen = i.fid_igen "
                            + "WHERE c.b_del = 0 AND c.fk_tp_link = " + linkType[0] + " AND c.fk_ref = " + linkType[1] + " AND  i.id_item = " + idItem + " AND i.fid_igen = " + linkType[1] + " AND c.id_ct_dps = " + keyDpsType[0] + " AND "
                            + "c.id_cl_dps = " + keyDpsType[1]  + " AND "
                            + "c.id_tp_dps = " + keyDpsType[2]  + "; ";
                    break;
                case SDataConstantsSys.TRNS_TP_LINK_IGRP:
                    sql = "SELECT c.id_ct_dps, c.id_cl_dps, c.id_tp_dps, c.id_cfg, c.fk_tp_link, c.fk_ref "
                            + "FROM " + SModConsts.TablesMap.get(SModConsts.TRNU_TP_DPS_SRC_ITEM) + " AS c, erp.itmu_igrp AS grp "
                            + "INNER JOIN erp.itmu_igen AS gen ON grp.id_igrp = gen.fid_igrp "
                            + "INNER JOIN erp.itmu_item AS i ON gen.id_igen = i.fid_igen "
                            + "WHERE c.b_del = 0 AND c.fk_tp_link = " + linkType[0] + " AND c.fk_ref = " + linkType[1] + " AND  i.id_item = " + idItem + " AND gen.fid_igrp = " + linkType[1] + " AND c.id_ct_dps = " + keyDpsType[0] + " AND "
                            + "c.id_cl_dps = " + keyDpsType[1]  + " AND "
                            + "c.id_tp_dps = " + keyDpsType[2]  + "; ";
                    break;
                case SDataConstantsSys.TRNS_TP_LINK_IFAM:
                    sql = "SELECT c.id_ct_dps, c.id_cl_dps, c.id_tp_dps, c.id_cfg, c.fk_tp_link, c.fk_ref "
                            + "FROM " + SModConsts.TablesMap.get(SModConsts.TRNU_TP_DPS_SRC_ITEM) + " AS c, erp.itmu_ifam AS fam "
                            + "INNER JOIN erp.itmu_igrp AS grp ON fam.id_ifam = grp.fid_ifam "
                            + "INNER JOIN erp.itmu_igen AS gen ON gen.fid_igrp = grp.id_igrp "
                            + "INNER JOIN erp.itmu_item AS i ON gen.id_igen = i.fid_igen "
                            + "WHERE c.b_del = 0 AND c.fk_tp_link = " + linkType[0] + " AND c.fk_ref = " + linkType[1] + " AND  i.id_item = " + idItem + " AND grp.fid_ifam = " + linkType[1] + " AND c.id_ct_dps = " + keyDpsType[0] + " AND "
                            + "c.id_cl_dps = " + keyDpsType[1]  + " AND "
                            + "c.id_tp_dps = " + keyDpsType[2]  + "; ";
                    break;
                case SDataConstantsSys.TRNS_TP_LINK_TP_ITEM:
                    sql = "SELECT c.id_ct_dps, c.id_cl_dps, c.id_tp_dps, c.id_cfg, c.fk_tp_link, c.fk_ref "
                            + "FROM " + SModConsts.TablesMap.get(SModConsts.TRNU_TP_DPS_SRC_ITEM) + " AS c, erp.itms_tp_item AS tp "
                            + "INNER JOIN erp.itmu_igen AS gen ON gen.fid_tp_item = tp.id_tp_item "
                            + "INNER JOIN erp.itmu_item AS i ON gen.id_igen = i.fid_igen "
                            + "WHERE c.b_del = 0 AND c.fk_tp_link = " + linkType[0] + " AND c.fk_ref = " + linkType[1] + " AND  i.id_item = " + idItem + " AND gen.fid_tp_item = " + linkType[1] + " AND c.id_ct_dps = " + keyDpsType[0] + " AND "
                            + "c.id_cl_dps = " + keyDpsType[1]  + " AND "
                            + "c.id_tp_dps = " + keyDpsType[2]  + "; ";
                    break;
                case SDataConstantsSys.TRNS_TP_LINK_CL_ITEM:
                    sql = "SELECT c.id_ct_dps, c.id_cl_dps, c.id_tp_dps, c.id_cfg, c.fk_tp_link, c.fk_ref "
                            + "FROM " + SModConsts.TablesMap.get(SModConsts.TRNU_TP_DPS_SRC_ITEM) + " AS c, erp.itms_cl_item AS cl "
                            + "INNER JOIN erp.itmu_igen AS gen ON gen.fid_cl_item = cl.id_cl_item "
                            + "INNER JOIN erp.itmu_item AS i ON gen.id_igen = i.fid_igen "
                            + "WHERE c.b_del = 0 AND c.fk_tp_link = " + linkType[0] + " AND c.fk_ref = " + linkType[1] + " AND  i.id_item = " + idItem + " AND gen.fid_cl_item = " + linkType[1] + " AND c.id_ct_dps = " + keyDpsType[0] + " AND "
                            + "c.id_cl_dps = " + keyDpsType[1]  + " AND "
                            + "c.id_tp_dps = " + keyDpsType[2]  + "; ";
                    break;
                case SDataConstantsSys.TRNS_TP_LINK_CT_ITEM:
                    sql = "SELECT c.id_ct_dps, c.id_cl_dps, c.id_tp_dps, c.id_cfg, c.fk_tp_link, c.fk_ref "
                            + "FROM " + SModConsts.TablesMap.get(SModConsts.TRNU_TP_DPS_SRC_ITEM) + " AS c, erp.itms_ct_item AS ct "
                            + "INNER JOIN erp.itmu_igen AS gen ON gen.fid_ct_item = ct.id_ct_item "
                            + "INNER JOIN erp.itmu_item AS i ON gen.id_igen = i.fid_igen "
                            + "WHERE c.b_del = 0 AND c.fk_tp_link = " + linkType[0] + " AND c.fk_ref = " + linkType[1] + " AND  i.id_item = " + idItem + " AND gen.fid_ct_item = " + linkType[1] + " AND c.id_ct_dps = " + keyDpsType[0] + " AND "
                            + "c.id_cl_dps = " + keyDpsType[1]  + " AND "
                            + "c.id_tp_dps = " + keyDpsType[2]  + "; ";
                    break;

                case SDataConstantsSys.TRNS_TP_LINK_ALL:
                    sql = "SELECT c.id_ct_dps, c.id_cl_dps, c.id_tp_dps, c.id_cfg, c.fk_tp_link, c.fk_ref "
                            + "FROM " + SModConsts.TablesMap.get(SModConsts.TRNU_TP_DPS_SRC_ITEM) + " AS c  "
                            + "WHERE c.b_del = 0 AND c.fk_ref = 0; ";
                    break;
                    
                default:
            }

            resultSetCfg = statementCfg.executeQuery(sql);
            while (resultSetCfg.next()) {
                exists = true;
                break;
            }
        }

        return exists; 
    }
    
    /**
     * Gets net balance of prepayments for provided business partner from invoices and credit notes.
     * @param session Current user's system session.
     * @param idBizPartner Business partner's (customer or provider) primary key.
     * @param keyDps Business partner's current document in order to descart its prepayments if any.
     * @param idCurrency Currency for retrieving prepayments' balance amount. When <code>undefined</code> value is provided, currency is local currency.
     */
    public static double getPrepaymentsBalance(final SGuiSession session, final int idDpsCategory, final int idBizPartner, final int[] keyDps, final int idCurrency) throws Exception {
        double balance = 0;
        String sql = "";
        ResultSet resultSet = null;
        
        sql = "SELECT "
                + "COALESCE(SUM(IF(de.ops_type = " + SDataConstantsSys.TRNX_OPS_TYPE_OPS_PREPAY + ", " + (idCurrency == SLibConsts.UNDEFINED ? "de.stot_r" : "de.stot_cur_r") + ", 0.0)), 0.0) AS _ops_prepay, "
                + "COALESCE(SUM(IF(de.ops_type = " + SDataConstantsSys.TRNX_OPS_TYPE_ADJ_PREPAY + ", " + (idCurrency == SLibConsts.UNDEFINED ? "de.stot_r" : "de.stot_cur_r") + ", 0.0)), 0.0) AS _adj_prepay, "
                + "COALESCE(SUM(IF(de.ops_type = " + SDataConstantsSys.TRNX_OPS_TYPE_OPS_OPS_APP_PREPAY + ", " + (idCurrency == SLibConsts.UNDEFINED ? "de.disc_doc" : "de.disc_doc_cur") + ", 0.0)), 0.0) AS _ops_ops_app_prepay, "
                + "COALESCE(SUM(IF(de.ops_type = " + SDataConstantsSys.TRNX_OPS_TYPE_ADJ_OPS_APP_PREPAY + ", " + (idCurrency == SLibConsts.UNDEFINED ? "de.disc_doc" : "de.disc_doc_cur") + ", 0.0)), 0.0) AS _adj_ops_app_prepay, "
                + "COALESCE(SUM(IF(de.ops_type = " + SDataConstantsSys.TRNX_OPS_TYPE_ADJ_APP_PREPAY + ", " + (idCurrency == SLibConsts.UNDEFINED ? "de.stot_r" : "de.stot_cur_r") + ", 0.0)), 0.0) AS _adj_app_prepay "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.TRN_DPS) + " AS d "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.TRN_DPS_ETY) + " AS de ON d.id_year = de.id_year AND d.id_doc = de.id_doc "
                + "WHERE d.fid_ct_dps = " + idDpsCategory + " AND d.fid_bp_r = " + idBizPartner + " AND "
                + "de.ops_type NOT IN (" + SDataConstantsSys.TRNX_OPS_TYPE_OPS_OPS + ", " + SDataConstantsSys.TRNX_OPS_TYPE_ADJ_OPS + ") AND "
                + "NOT (d.id_year = " + keyDps[0] + " AND d.id_doc = " + keyDps[1] + ") AND "
                + (idCurrency == SLibConsts.UNDEFINED ? "" : "d.fid_cur = " + idCurrency + " AND ")
                + "NOT d.b_del AND NOT de.b_del AND d.fid_st_dps = " + SModSysConsts.TRNS_ST_DPS_EMITED + "; ";
        resultSet = session.getStatement().executeQuery(sql);
        if (resultSet.next()) {
            balance = SLibUtils.round(
                    + resultSet.getDouble("_ops_prepay")            // + prepayments in invoices
                    - resultSet.getDouble("_adj_prepay")            // - prepayments in credit notes
                    - resultSet.getDouble("_ops_ops_app_prepay")    // - prepayments applied as discounts in invoices
                    + resultSet.getDouble("_adj_ops_app_prepay")    // + prepayments applied as discounts in credit notes
                    - resultSet.getDouble("_adj_app_prepay"),       // - prepayments applied in credit notes
                    SLibUtils.getDecimalFormatAmount().getMaximumFractionDigits());
        }
        
        return balance;
    }
    
    public static int mirrowOperationsType(final int operationsType) {
        int mirrowed = SLibConsts.UNDEFINED;
        
        switch (operationsType) {
            case SDataConstantsSys.TRNX_OPS_TYPE_OPS_OPS:
                mirrowed = SDataConstantsSys.TRNX_OPS_TYPE_ADJ_OPS;
                break;
            case SDataConstantsSys.TRNX_OPS_TYPE_OPS_OPS_APP_PREPAY:
                mirrowed = SDataConstantsSys.TRNX_OPS_TYPE_ADJ_OPS_APP_PREPAY;
                break;
            case SDataConstantsSys.TRNX_OPS_TYPE_OPS_PREPAY:
                mirrowed = SDataConstantsSys.TRNX_OPS_TYPE_ADJ_PREPAY;
                break;
            case SDataConstantsSys.TRNX_OPS_TYPE_ADJ_OPS:
                mirrowed = SDataConstantsSys.TRNX_OPS_TYPE_OPS_OPS;
                break;
            case SDataConstantsSys.TRNX_OPS_TYPE_ADJ_OPS_APP_PREPAY:
                mirrowed = SDataConstantsSys.TRNX_OPS_TYPE_OPS_OPS_APP_PREPAY;
                break;
            case SDataConstantsSys.TRNX_OPS_TYPE_ADJ_PREPAY:
                mirrowed = SDataConstantsSys.TRNX_OPS_TYPE_OPS_PREPAY;
                break;
            default:
                mirrowed = operationsType;
        }
        
        return mirrowed;
    }
    
    /**
     * Obtain amount in currency local accumulated by user and for date cut.
     * @param connection Connection database.
     * @param keyDps Dps primaryKey for ommit
     * @param dateAuxLimit Date auxiliar for obtain limit date.
     * @param userId User ID.
     * @return
     * @throws Exception 
     */
    public static double getOrderAmountMonthUser(final Connection connection, final int[] keyDps, final Date dateAuxLimit, final int userId) throws Exception {
        double amount = 0;
        String sql = "";
        ResultSet resultSet = null;
        
        sql = "SELECT COALESCE(SUM(de.stot_r), 0.0) AS _amt "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.TRN_DPS) + " AS d "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.TRN_DPS_ETY) + " AS de ON d.id_year = de.id_year AND d.id_doc = de.id_doc "
                + "WHERE NOT d.b_del AND NOT de.b_del AND d.fid_usr_new = " + userId + " AND d.dt >= '" + SLibUtils.DbmsDateFormatDate.format(SLibTimeUtils.getBeginOfMonth(dateAuxLimit)) + "' AND d.dt <= '" + SLibUtils.DbmsDateFormatDate.format(SLibTimeUtils.getEndOfMonth(dateAuxLimit)) + "' AND "
                + "d.fid_ct_dps = " + SDataConstantsSys.TRNU_TP_DPS_PUR_ORD[0] + " AND d.fid_cl_dps = " + SDataConstantsSys.TRNU_TP_DPS_PUR_ORD[1] + " AND "
                + "d.id_year = " + keyDps[0] + " AND d.id_doc <> " + keyDps[1]  + " ";
        
        resultSet = connection.createStatement().executeQuery(sql);
        if (resultSet.next()) {
            amount = resultSet.getDouble("_amt");
        }
        
        return amount;
    }
    
    /**
     * Obtain amount in currency local accumulated by functional area and for date cut.
     * @param connection Connection database.
     * @param keyDps Dps primaryKey for ommit
     * @param dateAuxLimit Date auxiliar for obtain limit date.
     * @param funcAreaId Functional area ID.
     * @return
     * @throws Exception 
     */
    public static double getOrderAmountMonthFunctionalArea(final Connection connection, final int[] keyDps, final Date dateAuxLimit, final int funcAreaId) throws Exception {
        double amount = 0;
        String sql = "";
        ResultSet resultSet = null;
        
        sql = "SELECT COALESCE(SUM(de.stot_r), 0.0) AS _amt "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.TRN_DPS) + " AS d "
                + "INNER JOIN " + SModConsts.TablesMap.get(SModConsts.TRN_DPS_ETY) + " AS de ON d.id_year = de.id_year AND d.id_doc = de.id_doc "
                + "WHERE NOT d.b_del AND NOT de.b_del AND d.fid_func = " + funcAreaId + " AND d.dt >= '" + SLibUtils.DbmsDateFormatDate.format(SLibTimeUtils.getBeginOfMonth(dateAuxLimit)) + "' AND d.dt <= '" + SLibUtils.DbmsDateFormatDate.format(SLibTimeUtils.getEndOfMonth(dateAuxLimit)) + "' AND "
                + "d.fid_ct_dps = " + SDataConstantsSys.TRNU_TP_DPS_PUR_ORD[0] + " AND d.fid_cl_dps = " + SDataConstantsSys.TRNU_TP_DPS_PUR_ORD[1] + " AND "
                + "d.id_year = " + keyDps[0] + " AND d.id_doc <> " + keyDps[1]  + " ";
        
        resultSet = connection.createStatement().executeQuery(sql);
        if (resultSet.next()) {
            amount = resultSet.getDouble("_amt");
        }
        
        return amount;
    }
    
    public static double getMaxLimitMonthFunctionalArea(final Connection connection, final int funcAreaId) throws Exception {
        double amount = 0;
        String sql = "";
        ResultSet resultSet = null;
        
        sql = "SELECT exp_mon "
                + "FROM " + SModConsts.TablesMap.get(SModConsts.CFGU_FUNC) + " "
                + "WHERE id_func = " + funcAreaId + " ";
        
        resultSet = connection.createStatement().executeQuery(sql);
        if (resultSet.next()) {
            amount = resultSet.getDouble("exp_mon");
        }
        
        return amount;
    }

    /**
     * Checks if item needs to be added to document from source document.
     */
    public static boolean checkItemStandaloneDoc(final SGuiSession session, final int[] keyDpsType, final int idItem, final boolean hasSourceDps) throws Exception {
        boolean can = existDpsItemConfig(session, keyDpsType, idItem);

        if (can && !hasSourceDps) {
            throw new Exception("El ítem '" + (String) session.readField(SModConsts.ITMU_ITEM, new int[] { idItem }, SDbRegistry.FIELD_NAME) + "' necesita ser agregado desde un documento origen.");
        }
        
        return true;
    }
    
    public static void printDelivery(final SGuiClient client, final int idDelivery) throws Exception {
        HashMap<String, Object> params = null;
        
        SDbDelivery delivery = (SDbDelivery) client.getSession().readRegistry(SModConsts.TRN_DVY, new int[] { idDelivery });
        SDbDps dps = (SDbDps) client.getSession().readRegistry(SModConsts.TRN_DPS, delivery.getKeyDps());
        SDbBizPartner bizPartnerCom = (SDbBizPartner) client.getSession().readRegistry(SModConsts.BPSU_BP, new int[] { client.getSession().getConfigCompany().getCompanyId() });
        SDbBizPartner bizPartnerBpr = (SDbBizPartner) client.getSession().readRegistry(SModConsts.BPSU_BP, new int[] { dps.getFkBizPartnerId_r() });
        
        String[] addressComMain = SBpsUtils.getAddress(client.getSession(), new int[] { bizPartnerCom.getRegHeadquarters().getPkBizPartnerBranchId(), 1 });
        String[] addressComAlt = bizPartnerCom.getRegHeadquarters().getPkBizPartnerBranchId() == dps.getFkCompanyBranchId() ? 
                new String[] { "(MISMO DOMICILIO)" } :
                SBpsUtils.getAddress(client.getSession(), new int[] { dps.getFkCompanyBranchId(), 1 });
        
        String[] addressBprMain = SBpsUtils.getAddress(client.getSession(), dps.getKeyBizPartnerBranchAddress());
        String[] addressBprAlt = SLibUtils.compareKeys(dps.getKeyBizPartnerBranchAddress(), dps.getKeyBizPartnerBranchAddressAlt()) ? 
                new String[] { "(MISMO DOMICILIO)" } :
                SBpsUtils.getAddress(client.getSession(), dps.getKeyBizPartnerBranchAddressAlt());
        
        params = client.createReportParams();
        params.put("nDeliveryId", idDelivery);
        params.put("sDeliveryNumber", delivery.getNumber());
        params.put("tDeliveryDate", delivery.getDate());
        params.put("sInvoiceNumber", dps.getDpsNumber());
        params.put("tInvoiceDate", dps.getDate());
        params.put("sComName", bizPartnerCom.getBizPartner());
        params.put("sComFiscalId", bizPartnerCom.getFiscalId());
        params.put("sComMainAddress1", addressComMain[0]);
        params.put("sComMainAddress2", addressComMain[1]);
        params.put("sComMainAddress3", addressComMain[2]);
        params.put("sComAltAddress1", addressComAlt[0]);
        params.put("sComAltAddress2", addressComAlt.length == 1 ? "" : addressComAlt[1]);
        params.put("sComAltAddress3", addressComAlt.length == 1 ? "" : addressComAlt[2]);
        params.put("sBprName", bizPartnerBpr.getBizPartner());
        params.put("sBprFiscalId", bizPartnerBpr.getFiscalId());
        params.put("sBprMainAddress1", addressBprMain[0]);
        params.put("sBprMainAddress2", addressBprMain[1]);
        params.put("sBprMainAddress3", addressBprMain[2]);
        params.put("sBprAltAddress1", addressBprAlt[0]);
        params.put("sBprAltAddress2", addressBprAlt.length == 1 ? "" : addressBprAlt[1]);
        params.put("sBprAltAddress3", addressBprAlt.length == 1 ? "" : addressBprAlt[2]);
        params.put("sXmlBaseDir", ((SClientInterface) client).getSessionXXX().getParamsCompany().getXmlBaseDirectory());
        
        client.getSession().printReport(SModConsts.TRN_DVY, SLibConsts.UNDEFINED, null, params);
    }
}
