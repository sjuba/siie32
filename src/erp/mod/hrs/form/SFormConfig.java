/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.mod.hrs.form;

import erp.mod.SModConsts;
import erp.mod.SModSysConsts;
import erp.mod.hrs.db.SDbConfig;
import erp.mod.hrs.db.SDbDeduction;
import erp.mod.hrs.db.SDbEarning;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbRegistry;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanForm;

/**
 *
 * @author Sergio Flores
 */
public class SFormConfig extends SBeanForm {

    private SDbConfig moRegistry;

    /**
     * Creates new form SFormConfig
     */
    public SFormConfig(SGuiClient client, String title) {
        setFormSettings(client, SGuiConsts.BEAN_FORM_EDIT, SModConsts.HRS_CFG, SLibConsts.UNDEFINED, title);
        initComponents();
        initComponentsCustom();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jlDateOperations = new javax.swing.JLabel();
        moDateDateOperations = new sa.lib.gui.bean.SBeanFieldDate();
        jPanel9 = new javax.swing.JPanel();
        jlFirstDayWeek = new javax.swing.JLabel();
        moIntFirstDayWeek = new sa.lib.gui.bean.SBeanFieldInteger();
        jlFirstDayWeekHelp = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jlLimitMwzReference = new javax.swing.JLabel();
        moIntLimitMwzReference = new sa.lib.gui.bean.SBeanFieldInteger();
        jPanel29 = new javax.swing.JPanel();
        jlBajioAffinityGroup = new javax.swing.JLabel();
        moTextBajioAffinityGroup = new sa.lib.gui.bean.SBeanFieldText();
        jlBajioAffinityGroupHelp = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jlPayrollTaxRate = new javax.swing.JLabel();
        moDecPayrollTaxRate = new sa.lib.gui.bean.SBeanFieldDecimal();
        jPanel11 = new javax.swing.JPanel();
        moBoolFornightStandard = new sa.lib.gui.bean.SBeanFieldBoolean();
        jPanel12 = new javax.swing.JPanel();
        moBoolTaxSubsidyEarning = new sa.lib.gui.bean.SBeanFieldBoolean();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jlMwzType = new javax.swing.JLabel();
        moKeyMwzType = new sa.lib.gui.bean.SBeanFieldKey();
        jlMwzTypeHelp = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jlMwzReferenceType = new javax.swing.JLabel();
        moKeyMwzReferenceType = new sa.lib.gui.bean.SBeanFieldKey();
        jlMwzReferenceTypeHelp = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jlTaxComputationType = new javax.swing.JLabel();
        moKeyTaxComputationType = new sa.lib.gui.bean.SBeanFieldKey();
        jlTaxComputationTypeHelp = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jlBank = new javax.swing.JLabel();
        moKeyBank = new sa.lib.gui.bean.SBeanFieldKey();
        jlBankHelp = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jlEarningEarning_n = new javax.swing.JLabel();
        moKeyEarningEarning_n = new sa.lib.gui.bean.SBeanFieldKey();
        jlEarningEarning_nHelp = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jlEarningVacations_n = new javax.swing.JLabel();
        moKeyEarningVacations_n = new sa.lib.gui.bean.SBeanFieldKey();
        jlEarningVacations_nHelp = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jlEarningTax_n = new javax.swing.JLabel();
        moKeyEarningTax_n = new sa.lib.gui.bean.SBeanFieldKey();
        jlEarningTax_nHelp = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jlEarningTaxSubsidy_n = new javax.swing.JLabel();
        moKeyEarningTaxSubsidy_n = new sa.lib.gui.bean.SBeanFieldKey();
        jlEarningTaxSubsidy_nHelp = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jlEarningSsContribution_n = new javax.swing.JLabel();
        moKeyEarningSsContribution_n = new sa.lib.gui.bean.SBeanFieldKey();
        jlEarningSsContribution_nHelp = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jlDeductionTax_n = new javax.swing.JLabel();
        moKeyDeductionTax_n = new sa.lib.gui.bean.SBeanFieldKey();
        jlDeductionTax_nHelp = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jlDeductionTaxSubsidy_n = new javax.swing.JLabel();
        moKeyDeductionTaxSubsidy_n = new sa.lib.gui.bean.SBeanFieldKey();
        jlDeductionTaxSubsidy_nHelp = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jlDeductionSsContribution_n = new javax.swing.JLabel();
        moKeyDeductionSsContribution_n = new sa.lib.gui.bean.SBeanFieldKey();
        jlDeductionSsContribution_nHelp = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración general:"));
        jPanel6.setLayout(new java.awt.GridLayout(7, 1, 0, 5));

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDateOperations.setText("Inicio operaciones:*");
        jlDateOperations.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlDateOperations);
        jPanel4.add(moDateDateOperations);

        jPanel6.add(jPanel4);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlFirstDayWeek.setText("1er. día semana:*");
        jlFirstDayWeek.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlFirstDayWeek);

        moIntFirstDayWeek.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel9.add(moIntFirstDayWeek);

        jlFirstDayWeekHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlFirstDayWeekHelp.setText("(1=Dom, 2=Lun, ..., 6=Sáb)");
        jlFirstDayWeekHelp.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel9.add(jlFirstDayWeekHelp);

        jPanel6.add(jPanel9);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlLimitMwzReference.setText("Tope cot. SMAR:*");
        jlLimitMwzReference.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel10.add(jlLimitMwzReference);

        moIntLimitMwzReference.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel10.add(moIntLimitMwzReference);

        jPanel6.add(jPanel10);

        jPanel29.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlBajioAffinityGroup.setText("Grupo afinidad:");
        jlBajioAffinityGroup.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel29.add(jlBajioAffinityGroup);

        moTextBajioAffinityGroup.setText("sBeanFieldText1");
        jPanel29.add(moTextBajioAffinityGroup);

        jlBajioAffinityGroupHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlBajioAffinityGroupHelp.setText("(Bajío)");
        jlBajioAffinityGroupHelp.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel29.add(jlBajioAffinityGroupHelp);

        jPanel6.add(jPanel29);

        jPanel30.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlPayrollTaxRate.setText("Imp. sobre nóminas:");
        jlPayrollTaxRate.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel30.add(jlPayrollTaxRate);
        jPanel30.add(moDecPayrollTaxRate);

        jPanel6.add(jPanel30);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        moBoolFornightStandard.setText("Quincenas fijas de 15 días");
        moBoolFornightStandard.setPreferredSize(new java.awt.Dimension(205, 23));
        jPanel11.add(moBoolFornightStandard);

        jPanel6.add(jPanel11);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        moBoolTaxSubsidyEarning.setText("Subsidio impuesto es percepción");
        moBoolTaxSubsidyEarning.setPreferredSize(new java.awt.Dimension(205, 23));
        jPanel12.add(moBoolTaxSubsidyEarning);

        jPanel6.add(jPanel12);

        jPanel2.add(jPanel6);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuración adicional:"));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel14.setLayout(new java.awt.GridLayout(1, 2));

        jPanel15.setLayout(new java.awt.GridLayout(8, 0, 0, 5));

        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlMwzType.setText("Área geo.:*");
        jlMwzType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel17.add(jlMwzType);

        moKeyMwzType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel17.add(moKeyMwzType);

        jlMwzTypeHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlMwzTypeHelp.setText("(Área geográfica)");
        jlMwzTypeHelp.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel17.add(jlMwzTypeHelp);

        jPanel15.add(jPanel17);

        jPanel18.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlMwzReferenceType.setText("Área geo. ref.:*");
        jlMwzReferenceType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel18.add(jlMwzReferenceType);

        moKeyMwzReferenceType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel18.add(moKeyMwzReferenceType);

        jlMwzReferenceTypeHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlMwzReferenceTypeHelp.setText("(Área geográfica referencia)");
        jlMwzReferenceTypeHelp.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel18.add(jlMwzReferenceTypeHelp);

        jPanel15.add(jPanel18);

        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlTaxComputationType.setText("Cálc. impto. def.:*");
        jlTaxComputationType.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel19.add(jlTaxComputationType);

        moKeyTaxComputationType.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel19.add(moKeyTaxComputationType);

        jlTaxComputationTypeHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlTaxComputationTypeHelp.setText("(Cálculo impuesto default)");
        jlTaxComputationTypeHelp.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel19.add(jlTaxComputationTypeHelp);

        jPanel15.add(jPanel19);

        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlBank.setText("Banco def.:*");
        jlBank.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel20.add(jlBank);

        moKeyBank.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel20.add(moKeyBank);

        jlBankHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlBankHelp.setText("(Banco default)");
        jlBankHelp.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel20.add(jlBankHelp);

        jPanel15.add(jPanel20);

        jPanel14.add(jPanel15);

        jPanel16.setLayout(new java.awt.GridLayout(8, 1, 0, 5));

        jPanel27.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlEarningEarning_n.setText("Per. normal:");
        jlEarningEarning_n.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel27.add(jlEarningEarning_n);

        moKeyEarningEarning_n.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel27.add(moKeyEarningEarning_n);

        jlEarningEarning_nHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlEarningEarning_nHelp.setText("(Percepción normal)");
        jlEarningEarning_nHelp.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel27.add(jlEarningEarning_nHelp);

        jPanel16.add(jPanel27);

        jPanel28.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlEarningVacations_n.setText("Per. vacaciones:");
        jlEarningVacations_n.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel28.add(jlEarningVacations_n);

        moKeyEarningVacations_n.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel28.add(moKeyEarningVacations_n);

        jlEarningVacations_nHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlEarningVacations_nHelp.setText("(Vacaciones)");
        jlEarningVacations_nHelp.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel28.add(jlEarningVacations_nHelp);

        jPanel16.add(jPanel28);

        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlEarningTax_n.setText("Per. impto.:");
        jlEarningTax_n.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel21.add(jlEarningTax_n);

        moKeyEarningTax_n.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel21.add(moKeyEarningTax_n);

        jlEarningTax_nHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlEarningTax_nHelp.setText("(Impuesto)");
        jlEarningTax_nHelp.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel21.add(jlEarningTax_nHelp);

        jPanel16.add(jPanel21);

        jPanel22.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlEarningTaxSubsidy_n.setText("Per. sub. impto.:");
        jlEarningTaxSubsidy_n.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel22.add(jlEarningTaxSubsidy_n);

        moKeyEarningTaxSubsidy_n.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel22.add(moKeyEarningTaxSubsidy_n);

        jlEarningTaxSubsidy_nHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlEarningTaxSubsidy_nHelp.setText("(Subsidio impuesto)");
        jlEarningTaxSubsidy_nHelp.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel22.add(jlEarningTaxSubsidy_nHelp);

        jPanel16.add(jPanel22);

        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlEarningSsContribution_n.setText("Per. ret. SS:");
        jlEarningSsContribution_n.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel23.add(jlEarningSsContribution_n);

        moKeyEarningSsContribution_n.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel23.add(moKeyEarningSsContribution_n);

        jlEarningSsContribution_nHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlEarningSsContribution_nHelp.setText("(Retención SS)");
        jlEarningSsContribution_nHelp.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel23.add(jlEarningSsContribution_nHelp);

        jPanel16.add(jPanel23);

        jPanel24.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlDeductionTax_n.setText("Ded. impto.:");
        jlDeductionTax_n.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel24.add(jlDeductionTax_n);

        moKeyDeductionTax_n.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel24.add(moKeyDeductionTax_n);

        jlDeductionTax_nHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlDeductionTax_nHelp.setText("(Impuesto)");
        jlDeductionTax_nHelp.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel24.add(jlDeductionTax_nHelp);

        jPanel16.add(jPanel24);

        jPanel25.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlDeductionTaxSubsidy_n.setText("Ded. sub. impto.:");
        jlDeductionTaxSubsidy_n.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel25.add(jlDeductionTaxSubsidy_n);

        moKeyDeductionTaxSubsidy_n.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel25.add(moKeyDeductionTaxSubsidy_n);

        jlDeductionTaxSubsidy_nHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlDeductionTaxSubsidy_nHelp.setText("(Subsidio impuesto)");
        jlDeductionTaxSubsidy_nHelp.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel25.add(jlDeductionTaxSubsidy_nHelp);

        jPanel16.add(jPanel25);

        jPanel26.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 0));

        jlDeductionSsContribution_n.setText("Ded. ret. SS:");
        jlDeductionSsContribution_n.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel26.add(jlDeductionSsContribution_n);

        moKeyDeductionSsContribution_n.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel26.add(moKeyDeductionSsContribution_n);

        jlDeductionSsContribution_nHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlDeductionSsContribution_nHelp.setText("(Retención SS)");
        jlDeductionSsContribution_nHelp.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel26.add(jlDeductionSsContribution_nHelp);

        jPanel16.add(jPanel26);

        jPanel14.add(jPanel16);

        jPanel13.add(jPanel14, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel13, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jlBajioAffinityGroup;
    private javax.swing.JLabel jlBajioAffinityGroupHelp;
    private javax.swing.JLabel jlBank;
    private javax.swing.JLabel jlBankHelp;
    private javax.swing.JLabel jlDateOperations;
    private javax.swing.JLabel jlDeductionSsContribution_n;
    private javax.swing.JLabel jlDeductionSsContribution_nHelp;
    private javax.swing.JLabel jlDeductionTaxSubsidy_n;
    private javax.swing.JLabel jlDeductionTaxSubsidy_nHelp;
    private javax.swing.JLabel jlDeductionTax_n;
    private javax.swing.JLabel jlDeductionTax_nHelp;
    private javax.swing.JLabel jlEarningEarning_n;
    private javax.swing.JLabel jlEarningEarning_nHelp;
    private javax.swing.JLabel jlEarningSsContribution_n;
    private javax.swing.JLabel jlEarningSsContribution_nHelp;
    private javax.swing.JLabel jlEarningTaxSubsidy_n;
    private javax.swing.JLabel jlEarningTaxSubsidy_nHelp;
    private javax.swing.JLabel jlEarningTax_n;
    private javax.swing.JLabel jlEarningTax_nHelp;
    private javax.swing.JLabel jlEarningVacations_n;
    private javax.swing.JLabel jlEarningVacations_nHelp;
    private javax.swing.JLabel jlFirstDayWeek;
    private javax.swing.JLabel jlFirstDayWeekHelp;
    private javax.swing.JLabel jlLimitMwzReference;
    private javax.swing.JLabel jlMwzReferenceType;
    private javax.swing.JLabel jlMwzReferenceTypeHelp;
    private javax.swing.JLabel jlMwzType;
    private javax.swing.JLabel jlMwzTypeHelp;
    private javax.swing.JLabel jlPayrollTaxRate;
    private javax.swing.JLabel jlTaxComputationType;
    private javax.swing.JLabel jlTaxComputationTypeHelp;
    private sa.lib.gui.bean.SBeanFieldBoolean moBoolFornightStandard;
    private sa.lib.gui.bean.SBeanFieldBoolean moBoolTaxSubsidyEarning;
    private sa.lib.gui.bean.SBeanFieldDate moDateDateOperations;
    private sa.lib.gui.bean.SBeanFieldDecimal moDecPayrollTaxRate;
    private sa.lib.gui.bean.SBeanFieldInteger moIntFirstDayWeek;
    private sa.lib.gui.bean.SBeanFieldInteger moIntLimitMwzReference;
    private sa.lib.gui.bean.SBeanFieldKey moKeyBank;
    private sa.lib.gui.bean.SBeanFieldKey moKeyDeductionSsContribution_n;
    private sa.lib.gui.bean.SBeanFieldKey moKeyDeductionTaxSubsidy_n;
    private sa.lib.gui.bean.SBeanFieldKey moKeyDeductionTax_n;
    private sa.lib.gui.bean.SBeanFieldKey moKeyEarningEarning_n;
    private sa.lib.gui.bean.SBeanFieldKey moKeyEarningSsContribution_n;
    private sa.lib.gui.bean.SBeanFieldKey moKeyEarningTaxSubsidy_n;
    private sa.lib.gui.bean.SBeanFieldKey moKeyEarningTax_n;
    private sa.lib.gui.bean.SBeanFieldKey moKeyEarningVacations_n;
    private sa.lib.gui.bean.SBeanFieldKey moKeyMwzReferenceType;
    private sa.lib.gui.bean.SBeanFieldKey moKeyMwzType;
    private sa.lib.gui.bean.SBeanFieldKey moKeyTaxComputationType;
    private sa.lib.gui.bean.SBeanFieldText moTextBajioAffinityGroup;
    // End of variables declaration//GEN-END:variables

    private void initComponentsCustom() {
        SGuiUtils.setWindowBounds(this, 960, 600);

        moDateDateOperations.setDateSettings(miClient, SGuiUtils.getLabelName(jlDateOperations), true);
        moIntFirstDayWeek.setIntegerSettings(SGuiUtils.getLabelName(jlFirstDayWeek.getText()), SGuiConsts.GUI_TYPE_INT, true);
        moIntFirstDayWeek.setMinInteger(1);
        moIntFirstDayWeek.setMaxInteger(7);
        moIntLimitMwzReference.setIntegerSettings(SGuiUtils.getLabelName(jlLimitMwzReference.getText()), SGuiConsts.GUI_TYPE_INT, true);
        moIntLimitMwzReference.setMinInteger(1);
        moIntLimitMwzReference.setMaxInteger(100);
        moTextBajioAffinityGroup.setTextSettings(SGuiUtils.getLabelName(jlBajioAffinityGroup.getText()), 5, 0);
        moDecPayrollTaxRate.setDecimalSettings(SGuiUtils.getLabelName(jlPayrollTaxRate.getText()), SGuiConsts.GUI_TYPE_DEC_PER_DISC, false);
        moBoolFornightStandard.setBooleanSettings(SGuiUtils.getLabelName(moBoolFornightStandard.getText()), true);
        moBoolTaxSubsidyEarning.setBooleanSettings(SGuiUtils.getLabelName(moBoolTaxSubsidyEarning.getText()), true);
        moKeyMwzType.setKeySettings(miClient, SGuiUtils.getLabelName(jlMwzType), true);
        moKeyMwzReferenceType.setKeySettings(miClient, SGuiUtils.getLabelName(jlMwzReferenceType), true);
        moKeyTaxComputationType.setKeySettings(miClient, SGuiUtils.getLabelName(jlTaxComputationType), true);
        moKeyBank.setKeySettings(miClient, SGuiUtils.getLabelName(jlBank), true);
        moKeyEarningEarning_n.setKeySettings(miClient, SGuiUtils.getLabelName(jlEarningEarning_n), false);
        moKeyEarningVacations_n.setKeySettings(miClient, SGuiUtils.getLabelName(jlEarningVacations_n), false);
        moKeyEarningTax_n.setKeySettings(miClient, SGuiUtils.getLabelName(jlEarningTax_n), false);
        moKeyEarningTaxSubsidy_n.setKeySettings(miClient, SGuiUtils.getLabelName(jlEarningTaxSubsidy_n), false);
        moKeyEarningSsContribution_n.setKeySettings(miClient, SGuiUtils.getLabelName(jlEarningSsContribution_n), false);
        moKeyDeductionTax_n.setKeySettings(miClient, SGuiUtils.getLabelName(jlDeductionTax_n), false);
        moKeyDeductionTaxSubsidy_n.setKeySettings(miClient, SGuiUtils.getLabelName(jlDeductionTaxSubsidy_n), false);
        moKeyDeductionSsContribution_n.setKeySettings(miClient, SGuiUtils.getLabelName(jlDeductionSsContribution_n), false);

        moFields.addField(moDateDateOperations);
        moFields.addField(moIntFirstDayWeek);
        moFields.addField(moIntLimitMwzReference);
        moFields.addField(moTextBajioAffinityGroup);
        moFields.addField(moDecPayrollTaxRate);
        moFields.addField(moBoolFornightStandard);
        moFields.addField(moBoolTaxSubsidyEarning);
        moFields.addField(moKeyMwzType);
        moFields.addField(moKeyMwzReferenceType);
        moFields.addField(moKeyTaxComputationType);
        moFields.addField(moKeyBank);
        moFields.addField(moKeyEarningEarning_n);
        moFields.addField(moKeyEarningVacations_n);
        moFields.addField(moKeyEarningTax_n);
        moFields.addField(moKeyEarningTaxSubsidy_n);
        moFields.addField(moKeyEarningSsContribution_n);
        moFields.addField(moKeyDeductionTax_n);
        moFields.addField(moKeyDeductionTaxSubsidy_n);
        moFields.addField(moKeyDeductionSsContribution_n);

        moFields.setFormButton(jbSave);
    }

    @Override
    public void addAllListeners() {

    }

    @Override
    public void removeAllListeners() {

    }

    @Override
    public void reloadCatalogues() {
        miClient.getSession().populateCatalogue(moKeyMwzType, SModConsts.HRSU_TP_MWZ, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyMwzReferenceType, SModConsts.HRSU_TP_MWZ, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyTaxComputationType, SModConsts.HRSS_TP_TAX_COMP, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyBank, SModConsts.HRSS_BANK, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyEarningEarning_n, SModConsts.HRS_EAR, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyEarningVacations_n, SModConsts.HRS_EAR, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyEarningTax_n, SModConsts.HRS_EAR, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyEarningTaxSubsidy_n, SModConsts.HRS_EAR, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyEarningSsContribution_n, SModConsts.HRS_EAR, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyDeductionTax_n, SModConsts.HRS_DED, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyDeductionTaxSubsidy_n, SModConsts.HRS_DED, SLibConsts.UNDEFINED, null);
        miClient.getSession().populateCatalogue(moKeyDeductionSsContribution_n, SModConsts.HRS_DED, SLibConsts.UNDEFINED, null);
    }

    @Override
    public void setRegistry(SDbRegistry registry) throws Exception {
        moRegistry = (SDbConfig) registry;

        mnFormResult = SLibConsts.UNDEFINED;
        mbFirstActivation = true;

        removeAllListeners();
        reloadCatalogues();

        if (moRegistry.isRegistryNew()) {
            moRegistry.initPrimaryKey();
            moRegistry.setSystem(false);    // all editable registries are non-system
            jtfRegistryKey.setText("");
        }
        else {
            jtfRegistryKey.setText(SLibUtils.textKey(moRegistry.getPrimaryKey()));
        }

        moDateDateOperations.setValue(moRegistry.getDateOperations());
        moIntFirstDayWeek.setValue(moRegistry.getFirstDayWeek());
        moIntLimitMwzReference.setValue(moRegistry.getLimitMwzReference());
        moTextBajioAffinityGroup.setValue(moRegistry.getBajioAffinityGroup());
        moDecPayrollTaxRate.setValue(moRegistry.getPayrollTaxRate());
        moBoolFornightStandard.setValue(moRegistry.isFornightStandard());
        moBoolTaxSubsidyEarning.setValue(moRegistry.isTaxSubsidyEarning());
        moKeyMwzType.setValue(new int[] { moRegistry.getFkMwzTypeId() });
        moKeyMwzReferenceType.setValue(new int[] { moRegistry.getFkMwzReferenceTypeId() });
        moKeyTaxComputationType.setValue(new int[] { moRegistry.getFkTaxComputationTypeId() });
        moKeyBank.setValue(new int[] { moRegistry.getFkBankId() });
        moKeyEarningEarning_n.setValue(new int[] { moRegistry.getFkEarningEarningId_n() });
        moKeyEarningVacations_n.setValue(new int[] { moRegistry.getFkEarningVacationsId_n() });
        moKeyEarningTax_n.setValue(new int[] { moRegistry.getFkEarningTaxId_n() });
        moKeyEarningTaxSubsidy_n.setValue(new int[] { moRegistry.getFkEarningTaxSubsidyId_n() });
        moKeyEarningSsContribution_n.setValue(new int[] { moRegistry.getFkEarningSsContributionId_n() });
        moKeyDeductionTax_n.setValue(new int[] { moRegistry.getFkDeductionTaxId_n() });
        moKeyDeductionTaxSubsidy_n.setValue(new int[] { moRegistry.getFkDeductionTaxSubsidyId_n() });
        moKeyDeductionSsContribution_n.setValue(new int[] { moRegistry.getFkDeductionSsContributionId_n() });

        setFormEditable(true);

        moKeyEarningTax_n.setEnabled(false);
        moKeyEarningSsContribution_n.setEnabled(false);
        moKeyDeductionTaxSubsidy_n.setEnabled(false);

        addAllListeners();
    }

    @Override
    public SDbRegistry getRegistry() throws Exception {
        SDbConfig registry = moRegistry.clone();

        if (registry.isRegistryNew()) {
            moRegistry.setPkConfigId(1);
        }

        registry.setDateOperations(moDateDateOperations.getValue());
        registry.setFirstDayWeek(moIntFirstDayWeek.getValue());
        registry.setLimitMwzReference(moIntLimitMwzReference.getValue());
        registry.setBajioAffinityGroup(moTextBajioAffinityGroup.getValue());
        registry.setPayrollTaxRate(moDecPayrollTaxRate.getValue());
        registry.setFornightStandard(moBoolFornightStandard.getValue());
        registry.setTaxSubsidyEarning(moBoolTaxSubsidyEarning.getValue());
        registry.setFkMwzTypeId(moKeyMwzType.getValue()[0]);
        registry.setFkMwzReferenceTypeId(moKeyMwzReferenceType.getValue()[0]);
        registry.setFkTaxComputationTypeId(moKeyTaxComputationType.getValue()[0]);
        registry.setFkBankId(moKeyBank.getValue()[0]);
        registry.setFkEarningEarningId_n(moKeyEarningEarning_n.getSelectedIndex() <= 0 ? SLibConsts.UNDEFINED : moKeyEarningEarning_n.getValue()[0]);
        registry.setFkEarningVacationsId_n(moKeyEarningVacations_n.getSelectedIndex() <= 0 ? SLibConsts.UNDEFINED : moKeyEarningVacations_n.getValue()[0]);
        registry.setFkEarningTaxId_n(moKeyEarningTax_n.getSelectedIndex() <= 0 ? SLibConsts.UNDEFINED : moKeyEarningTax_n.getValue()[0]);
        registry.setFkEarningTaxSubsidyId_n(moKeyEarningTaxSubsidy_n.getSelectedIndex() <= 0 ? SLibConsts.UNDEFINED : moKeyEarningTaxSubsidy_n.getValue()[0]);
        registry.setFkEarningSsContributionId_n(moKeyEarningSsContribution_n.getSelectedIndex() <= 0 ? SLibConsts.UNDEFINED : moKeyEarningSsContribution_n.getValue()[0]);
        registry.setFkDeductionTaxId_n(moKeyDeductionTax_n.getSelectedIndex() <= 0 ? SLibConsts.UNDEFINED : moKeyDeductionTax_n.getValue()[0]);
        registry.setFkDeductionTaxSubsidyId_n(moKeyDeductionTaxSubsidy_n.getSelectedIndex() <= 0 ? SLibConsts.UNDEFINED : moKeyDeductionTaxSubsidy_n.getValue()[0]);
        registry.setFkDeductionSsContributionId_n(moKeyDeductionSsContribution_n.getSelectedIndex() <= 0 ? SLibConsts.UNDEFINED : moKeyDeductionSsContribution_n.getValue()[0]);

        return registry;
    }

    @Override
    public SGuiValidation validateForm() {
        SDbEarning earning = null;
        SDbDeduction deduction = null;
        SGuiValidation validation = moFields.validateFields();

        if (validation.isValid()) {
            if (!moBoolTaxSubsidyEarning.getValue()) {
                validation.setMessage(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + moBoolTaxSubsidyEarning.getText() + "'.");
                validation.setComponent(moBoolTaxSubsidyEarning);
            }
            else {
                if (moKeyEarningEarning_n.getSelectedIndex() > 0) {
                    earning = (SDbEarning) miClient.getSession().readRegistry(SModConsts.HRS_EAR, moKeyEarningEarning_n.getValue());
                    if (earning.getFkEarningTypeId() != SModSysConsts.HRSS_TP_EAR_EAR) {
                        validation.setMessage(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + SGuiUtils.getLabelName(jlEarningEarning_n) + "'.");
                        validation.setComponent(moKeyEarningEarning_n);
                    }
                }
                if (validation.isValid()) {
                    if (moKeyEarningVacations_n.getSelectedIndex() > 0) {
                        earning = (SDbEarning) miClient.getSession().readRegistry(SModConsts.HRS_EAR, moKeyEarningVacations_n.getValue());
                        if (earning.getFkEarningTypeId() != SModSysConsts.HRSS_TP_EAR_EAR) {
                            validation.setMessage(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + SGuiUtils.getLabelName(jlEarningVacations_n) + "'.");
                            validation.setComponent(moKeyEarningVacations_n);
                        }
                    }
                    if (validation.isValid()) {
                        if (moKeyEarningTax_n.getSelectedIndex() > 0) {
                            earning = (SDbEarning) miClient.getSession().readRegistry(SModConsts.HRS_EAR, moKeyEarningTax_n.getValue());
                            if (earning.getFkEarningTypeId() != SLibConsts.UNDEFINED) {
                                validation.setMessage(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + SGuiUtils.getLabelName(jlEarningTax_n) + "'.");
                                validation.setComponent(moKeyEarningTax_n);
                            }
                        }
                        if (validation.isValid()) {
                            if (moKeyEarningTaxSubsidy_n.getSelectedIndex() > 0) {
                                earning = (SDbEarning) miClient.getSession().readRegistry(SModConsts.HRS_EAR, moKeyEarningTaxSubsidy_n.getValue());
                                if (earning.getFkEarningTypeId() != SModSysConsts.HRSS_TP_EAR_TAX_SUB) {
                                    validation.setMessage(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + SGuiUtils.getLabelName(jlEarningTaxSubsidy_n) + "'.");
                                    validation.setComponent(moKeyEarningTaxSubsidy_n);
                                }
                            }
                            if (validation.isValid()) {
                                if (moKeyEarningSsContribution_n.getSelectedIndex() > 0) {
                                    earning = (SDbEarning) miClient.getSession().readRegistry(SModConsts.HRS_EAR, moKeyEarningSsContribution_n.getValue());
                                    if (earning.getFkEarningTypeId() != SLibConsts.UNDEFINED) {
                                        validation.setMessage(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + SGuiUtils.getLabelName(jlEarningSsContribution_n) + "'.");
                                        validation.setComponent(moKeyEarningSsContribution_n);
                                    }
                                }
                                if (validation.isValid()) {
                                    if (moKeyDeductionTax_n.getSelectedIndex() > 0) {
                                        deduction = (SDbDeduction) miClient.getSession().readRegistry(SModConsts.HRS_DED, moKeyDeductionTax_n.getValue());
                                        if (deduction.getFkDeductionTypeId() != SModSysConsts.HRSS_TP_DED_TAX) {
                                            validation.setMessage(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + SGuiUtils.getLabelName(jlDeductionTax_n) + "'.");
                                            validation.setComponent(moKeyDeductionTax_n);
                                        }
                                    }
                                    if (validation.isValid()) {
                                        if (moKeyDeductionTaxSubsidy_n.getSelectedIndex() > 0) {
                                            deduction = (SDbDeduction) miClient.getSession().readRegistry(SModConsts.HRS_DED, moKeyDeductionTaxSubsidy_n.getValue());
                                            if (deduction.getFkDeductionTypeId() != SLibConsts.UNDEFINED) {
                                                validation.setMessage(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + SGuiUtils.getLabelName(jlDeductionTaxSubsidy_n) + "'.");
                                                validation.setComponent(moKeyDeductionTaxSubsidy_n);
                                            }
                                        }
                                        if (validation.isValid()) {
                                            if (moKeyDeductionSsContribution_n.getSelectedIndex() > 0) {
                                                deduction = (SDbDeduction) miClient.getSession().readRegistry(SModConsts.HRS_DED, moKeyDeductionSsContribution_n.getValue());
                                                if (deduction.getFkDeductionTypeId() != SModSysConsts.HRSS_TP_DED_SSC) {
                                                    validation.setMessage(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + SGuiUtils.getLabelName(jlDeductionSsContribution_n) + "'.");
                                                    validation.setComponent(moKeyDeductionSsContribution_n);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return validation;
    }
}
