/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.mod.hrs.form;

import erp.mod.SModConsts;
import erp.mod.SModSysConsts;
import erp.mod.hrs.db.SDbAbsence;
import erp.mod.hrs.db.SDbAbsenceConsumption;
import erp.mod.hrs.db.SDbBenefitTable;
import erp.mod.hrs.db.SHrsBenefit;
import erp.mod.hrs.db.SHrsBenefitTableByAnniversary;
import erp.mod.hrs.db.SHrsPayrollReceipt;
import erp.mod.hrs.db.SHrsUtils;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import sa.lib.SLibConsts;
import sa.lib.SLibTimeUtils;
import sa.lib.SLibUtils;
import sa.lib.db.SDbRegistry;
import sa.lib.grid.SGridColumnForm;
import sa.lib.grid.SGridConsts;
import sa.lib.grid.SGridPaneForm;
import sa.lib.grid.SGridRow;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanFormDialog;

/**
 *
 * @author Juan Barajas
 */
public class SDialogPayrollEmployeeAbsence extends SBeanFormDialog implements ActionListener, FocusListener, ListSelectionListener  {

    protected SDbAbsence moAbsence;
    
    protected SHrsPayrollReceipt moReceipt;
    protected SGridPaneForm moGridAbsenceRow;
    
    protected int mnAbsenceConsumptionPendingDays;
    protected int mnDiferenceDays;
    protected SDbBenefitTable moBenefit;
    protected SHrsBenefit moHrsBenefit;
    protected ArrayList<SHrsBenefit> maHrsBenefits;
    protected ArrayList<SHrsBenefitTableByAnniversary> maBenefitTableByAnniversary;
    
    /**
     * Creates new form SDialogPayrollEmployeeAbsence
     * @param client
     * @param title
     */
    public SDialogPayrollEmployeeAbsence(SGuiClient client, String title) {
        setFormSettings(client, SGuiConsts.BEAN_FORM_EDIT, SModConsts.HRSX_PAY_REC_EAR, SLibConsts.UNDEFINED, title);
        initComponents();
        initComponentsCustom();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jpAbsence = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jpBenefit = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jlAnniversary = new javax.swing.JLabel();
        moIntBenefitAnn = new sa.lib.gui.bean.SBeanFieldInteger();
        moIntBenefitYear = new sa.lib.gui.bean.SBeanFieldInteger();
        jPanel5 = new javax.swing.JPanel();
        jlDaysToPaidTable = new javax.swing.JLabel();
        moIntDaysToPaidTable = new sa.lib.gui.bean.SBeanFieldInteger();
        jPanel11 = new javax.swing.JPanel();
        jlDaysPayed = new javax.swing.JLabel();
        moDecDaysPayed = new sa.lib.gui.bean.SBeanFieldDecimal();
        jpAbsenceConsumption = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jlDateStart = new javax.swing.JLabel();
        moDateDateStart = new sa.lib.gui.bean.SBeanFieldDate();
        jPanel9 = new javax.swing.JPanel();
        jlDateEnd = new javax.swing.JLabel();
        moDateDateEnd = new sa.lib.gui.bean.SBeanFieldDate();
        jPanel10 = new javax.swing.JPanel();
        jlEffectiveDays = new javax.swing.JLabel();
        moIntEffectiveDays = new sa.lib.gui.bean.SBeanFieldInteger();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jpAbsence.setBorder(javax.swing.BorderFactory.createTitledBorder("Incidencias:"));
        jpAbsence.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jpAbsence, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jpBenefit.setBorder(javax.swing.BorderFactory.createTitledBorder("Consumo prestación:"));
        jpBenefit.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.GridLayout(3, 0));

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAnniversary.setText("Aniversario:");
        jlAnniversary.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel4.add(jlAnniversary);

        moIntBenefitAnn.setToolTipText("Año aniversario");
        moIntBenefitAnn.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel4.add(moIntBenefitAnn);

        moIntBenefitYear.setToolTipText("Año aniversario");
        moIntBenefitYear.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel4.add(moIntBenefitYear);

        jPanel6.add(jPanel4);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDaysToPaidTable.setText("Días:");
        jlDaysToPaidTable.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel5.add(jlDaysToPaidTable);

        moIntDaysToPaidTable.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel5.add(moIntDaysToPaidTable);

        jPanel6.add(jPanel5);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDaysPayed.setText("Días pagados:");
        jlDaysPayed.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel11.add(jlDaysPayed);

        moDecDaysPayed.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel11.add(moDecDaysPayed);

        jPanel6.add(jPanel11);

        jpBenefit.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel3.add(jpBenefit, java.awt.BorderLayout.WEST);

        jpAbsenceConsumption.setBorder(javax.swing.BorderFactory.createTitledBorder("Consumo incidencia:"));
        jpAbsenceConsumption.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDateStart.setText("Fecha inicial:*");
        jlDateStart.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlDateStart);

        moDateDateStart.setFocusable(false);
        jPanel8.add(moDateDateStart);

        jPanel2.add(jPanel8);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDateEnd.setText("Fecha final:*");
        jlDateEnd.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel9.add(jlDateEnd);
        jPanel9.add(moDateDateEnd);

        jPanel2.add(jPanel9);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlEffectiveDays.setText("Días efectivos:*");
        jlEffectiveDays.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel10.add(jlEffectiveDays);

        moIntEffectiveDays.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel10.add(moIntEffectiveDays);

        jPanel2.add(jPanel10);

        jpAbsenceConsumption.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.add(jpAbsenceConsumption, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel jlAnniversary;
    private javax.swing.JLabel jlDateEnd;
    private javax.swing.JLabel jlDateStart;
    private javax.swing.JLabel jlDaysPayed;
    private javax.swing.JLabel jlDaysToPaidTable;
    private javax.swing.JLabel jlEffectiveDays;
    private javax.swing.JPanel jpAbsence;
    private javax.swing.JPanel jpAbsenceConsumption;
    private javax.swing.JPanel jpBenefit;
    private sa.lib.gui.bean.SBeanFieldDate moDateDateEnd;
    private sa.lib.gui.bean.SBeanFieldDate moDateDateStart;
    private sa.lib.gui.bean.SBeanFieldDecimal moDecDaysPayed;
    private sa.lib.gui.bean.SBeanFieldInteger moIntBenefitAnn;
    private sa.lib.gui.bean.SBeanFieldInteger moIntBenefitYear;
    private sa.lib.gui.bean.SBeanFieldInteger moIntDaysToPaidTable;
    private sa.lib.gui.bean.SBeanFieldInteger moIntEffectiveDays;
    // End of variables declaration//GEN-END:variables

    private void initComponentsCustom() {
        SGuiUtils.setWindowBounds(this, 720, 450);
        
        moAbsence = null;
        mnAbsenceConsumptionPendingDays = 0;

        moDateDateStart.setDateSettings(miClient, SGuiUtils.getLabelName(jlDateStart.getText()), true);
        moDateDateEnd.setDateSettings(miClient, SGuiUtils.getLabelName(jlDateEnd.getText()), true);
        moIntEffectiveDays.setIntegerSettings(SGuiUtils.getLabelName(jlEffectiveDays.getText()), SGuiConsts.GUI_TYPE_INT, true);
        moIntBenefitAnn.setIntegerSettings(SGuiUtils.getLabelName(jlAnniversary.getText()), SGuiConsts.GUI_TYPE_INT, false);
        moIntBenefitYear.setIntegerSettings(SGuiUtils.getLabelName(jlAnniversary.getText()), SGuiConsts.GUI_TYPE_INT_CAL_YEAR, false);
        moIntDaysToPaidTable.setIntegerSettings(SGuiUtils.getLabelName(jlDaysToPaidTable.getText()), SGuiConsts.GUI_TYPE_INT, false);
        moDecDaysPayed.setDecimalSettings(SGuiUtils.getLabelName(jlDaysPayed.getText()), SGuiConsts.GUI_TYPE_DEC_QTY, false);
        
        moFields.addField(moDateDateStart);
        moFields.addField(moDateDateEnd);
        moFields.addField(moIntEffectiveDays);
        /*
        moFields.addField(moIntBenefitAnn);
        moFields.addField(moIntBenefitYear);
        moFields.addField(moIntDaysToPaidTable);
        moFields.addField(moDecDaysPayed);
        */
        
        moFields.setFormButton(jbSave);
        
        moGridAbsenceRow = new SGridPaneForm(miClient, SModConsts.HRS_ABS, SLibConsts.UNDEFINED, "Incidencias") {
            @Override
            public void initGrid() {
                setRowButtonsEnabled(false);
            }

            @Override
            public ArrayList<SGridColumnForm> createGridColumns() {
                ArrayList<SGridColumnForm> gridColumnsForm = new ArrayList<SGridColumnForm>();

                gridColumnsForm.add(new SGridColumnForm(SGridConsts.COL_TYPE_TEXT, "Clase incidencia"));
                gridColumnsForm.add(new SGridColumnForm(SGridConsts.COL_TYPE_TEXT, "Tipo incidencia"));
                gridColumnsForm.add(new SGridColumnForm(SGridConsts.COL_TYPE_TEXT_CODE_CAT, "Folio"));
                gridColumnsForm.add(new SGridColumnForm(SGridConsts.COL_TYPE_DATE, "Fecha inicial"));
                gridColumnsForm.add(new SGridColumnForm(SGridConsts.COL_TYPE_DATE, "Fecha final"));
                gridColumnsForm.add(new SGridColumnForm(SGridConsts.COL_TYPE_INT_RAW, "Días efectivos"));
                gridColumnsForm.add(new SGridColumnForm(SGridConsts.COL_TYPE_INT_RAW, "Días pendientes"));
                
                return gridColumnsForm;
            }
        };

        moGridAbsenceRow.setForm(null);
        moGridAbsenceRow.setPaneFormOwner(null);
        //mvFormGrids.add(moGridAbsenceRow);
        jpAbsence.add(moGridAbsenceRow, BorderLayout.CENTER);
        
        reloadCatalogues();
        addAllListeners();
        
        moDateDateStart.setEditable(false);
        moIntBenefitAnn.setEditable(false);
        moIntBenefitYear.setEditable(false);
        moIntDaysToPaidTable.setEditable(false);
        moDecDaysPayed.setEditable(false);
    }
    
    private void loadBenefitTables() throws Exception {
        ArrayList<SDbBenefitTable> aBenefitTables = new ArrayList<SDbBenefitTable>();
        
        if (moReceipt.getHrsPayroll().getConfig().getFkEarningVacationsId_n() == SLibConsts.UNDEFINED) {
            throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN + " (Configuración vacaciones)");
        }
        
        moBenefit = SHrsUtils.getBenefitTableByEarning(miClient.getSession(), moReceipt.getHrsPayroll().getConfig().getFkEarningVacationsId_n(), moReceipt.getHrsEmployee().getEmployee().getFkPaymentTypeId(), moReceipt.getHrsPayroll().getPayroll().getDateEnd());
        
        if (moBenefit == null) {
            throw new Exception(SLibConsts.ERR_MSG_OPTION_UNKNOWN + " (Tabla de prestaciones adecuada para la fecha de corte)");
        }
        aBenefitTables.add(moBenefit);
        
        maBenefitTableByAnniversary = SHrsUtils.getBenefitTablesAnniversarys(aBenefitTables);
    }
    
    private void readHrsBenefitAcummulate(int seniority, int benefitYear) {
        try {
            loadBenefitTables();
            
            maHrsBenefits = SHrsUtils.readHrsBenefits(miClient.getSession(), moReceipt.getHrsEmployee().getEmployee(), SModSysConsts.HRSS_TP_BEN_VAC, seniority, benefitYear, moReceipt.getHrsPayroll().getPayroll().getPkPayrollId(), maBenefitTableByAnniversary, null, moReceipt.getReceipt().getPaymentDaily());
            
            moIntBenefitAnn.setValue(seniority);
            moIntBenefitYear.setValue(benefitYear);
            
            for (SHrsBenefit hrsBenefit : maHrsBenefits) {
                if (SLibUtils.compareKeys(hrsBenefit.getPrimaryBenefitType(), new int[] { SModSysConsts.HRSS_TP_BEN_VAC, seniority, benefitYear })) {
                    hrsBenefit.setValuePayedReceipt(moReceipt.getBenefitValue(SModSysConsts.HRSS_TP_BEN_VAC, hrsBenefit.getBenefitAnn(), hrsBenefit.getBenefitYear()));
                    hrsBenefit.setAmountPayedReceipt(moReceipt.getBenefitAmount(SModSysConsts.HRSS_TP_BEN_VAC, hrsBenefit.getBenefitAnn(), hrsBenefit.getBenefitYear()));
                    moIntDaysToPaidTable.setValue((int) hrsBenefit.getValue());
                    moDecDaysPayed.setValue(hrsBenefit.getValuePayed() + hrsBenefit.getValuePayedReceipt());
                }
            }
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
    }
    
    private void createHrsBenefit() {
        double amountSys = 0;
        moHrsBenefit = new SHrsBenefit(mnFormType,  moIntBenefitAnn.getValue(), moIntBenefitYear.getValue());
        
        for (SHrsBenefit hrsBenefit : maHrsBenefits) {
            if (SLibUtils.compareKeys(hrsBenefit.getPrimaryBenefitType(), new int[] { SModSysConsts.HRSS_TP_BEN_VAC, moIntBenefitAnn.getValue(), moIntBenefitYear.getValue() })) {
                moHrsBenefit.setValue(hrsBenefit.getValue());
                moHrsBenefit.setValuePayed(hrsBenefit.getValuePayed());
                moHrsBenefit.setAmount(hrsBenefit.getAmount());
                moHrsBenefit.setAmountPayed(hrsBenefit.getAmountPayed());
            }
        }
        amountSys = SLibUtils.round(moIntEffectiveDays.getValue() * moReceipt.getReceipt().getPaymentDaily(), SLibUtils.DecimalFormatValue2D.getMaximumFractionDigits());
        
        moHrsBenefit.setFactorAmount(0d);
        moHrsBenefit.setEditAmount(false);
        moHrsBenefit.setValuePayedReceipt(moIntEffectiveDays.getValue());
        moHrsBenefit.setAmountPayedReceipt(amountSys);
        moHrsBenefit.setAmountPayedReceiptSys(amountSys);
    }
    
    private void populateAbsence() {
        Vector<SGridRow> rows = new Vector<SGridRow>();
        int pendingDays = 0;

        for (SDbAbsence absence : moReceipt.getHrsEmployee().getAbsences()) {
            pendingDays = absence.getEffectiveDays() - SHrsUtils.getConsumptionPreviousDays(absence, moReceipt.getHrsEmployee());
            
            if (pendingDays > 0) {
                absence.setAuxPendingDays(pendingDays);
                rows.add(absence);
            }
        }
        
        moGridAbsenceRow.populateGrid(rows, this);
        moGridAbsenceRow.clearSortKeys();
        moGridAbsenceRow.setSelectedGridRow(0);
        moGridAbsenceRow.getTable().requestFocus();
    }
    
    private void loadAbsenceSelected() {
        Date dateConsumptionLast = null;
        moAbsence = new SDbAbsence();
        
        if (moGridAbsenceRow.getSelectedGridRow() == null) {
            miClient.showMsgBoxWarning(SGridConsts.MSG_SELECT_ROW);
        }
        else {
            moAbsence = (SDbAbsence) moGridAbsenceRow.getSelectedGridRow();
            dateConsumptionLast = SHrsUtils.getConsumptionDateLast(moAbsence, moReceipt.getHrsEmployee());
            
            mnAbsenceConsumptionPendingDays = moAbsence.getEffectiveDays() - SHrsUtils.getConsumptionPreviousDays(moAbsence, moReceipt.getHrsEmployee());

            moDateDateStart.setValue(dateConsumptionLast == null ? moAbsence.getDateStart() : SLibTimeUtils.addDate(dateConsumptionLast, 0, 0, 1));
            
            if (moAbsence.getFkAbsenceClassId() == SModSysConsts.HRSU_CL_ABS_VAC) {
                readHrsBenefitAcummulate(moAbsence.getBenefitsAniversary(), moAbsence.getBenefitsYear());
            }
            else {
                moIntBenefitAnn.setValue(0);
                moIntBenefitYear.setValue(0);
                moIntDaysToPaidTable.setValue(0);
                moDecDaysPayed.setValue(0d);
            }
        }
    }
    
    private void calculateWorkingDays() {
        mnDiferenceDays = 0;
        
        if (moDateDateStart.getValue() != null && moDateDateEnd.getValue() != null) {
            mnDiferenceDays = (int) SLibTimeUtils.getDaysDiff(moDateDateEnd.getValue(), moDateDateStart.getValue()) + 1;
        }

        moIntEffectiveDays.setValue(mnDiferenceDays);
    }
    
    @Override
    public void addAllListeners() {
        moDateDateEnd.getComponent().addFocusListener(this);
    }

    @Override
    public void removeAllListeners() {
        moDateDateEnd.getComponent().removeFocusListener(this);
    }

    @Override
    public void reloadCatalogues() {
    }

    @Override
    public void setRegistry(SDbRegistry registry) throws Exception {
    }

    @Override
    public SDbRegistry getRegistry() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SGuiValidation validateForm() {
        String msg = "";
        SGuiValidation validation = moFields.validateFields();
        
        try {
            if (validation.isValid()) {
                if (moAbsence.getFkAbsenceClassId() == SModSysConsts.HRSU_CL_ABS_VAC) {
                    createHrsBenefit();

                    msg = moHrsBenefit.validate(SHrsBenefit.VALID_DAYS_TO_PAID, SHrsBenefit.VALIDATION_ABSENCE_TYPE);
                    if (!msg.isEmpty()) {
                        validation.setMessage(msg);
                        validation.setComponent(moIntEffectiveDays);
                    }
                    
                    if (validation.isValid()) {
                        msg = moHrsBenefit.validate(SHrsBenefit.VALID_DAYS_TO_PAID_TOTAL, SHrsBenefit.VALIDATION_ABSENCE_TYPE);
                        
                        if (!msg.isEmpty()) {
                            if (miClient.showMsgBoxConfirm(msg + "\n" + SGuiConsts.MSG_CNF_CONT) == JOptionPane.NO_OPTION) {
                                validation.setMessage(msg);
                                validation.setComponent(moIntEffectiveDays);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            SLibUtils.showException(this, e);
        }
        
        return validation;
    }

    @Override
    public void setValue(final int type, final Object value) {
        switch (type) {
            case SModConsts.HRS_PAY_RCP:
                moReceipt = (SHrsPayrollReceipt) value;
                populateAbsence();
                break;
            default:
                break;
        }
    }

    @Override
    public Object getValue(final int type) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void actionSave() {
        SDbAbsenceConsumption absenceConsumption = null;
        
        if (SGuiUtils.computeValidation(miClient, validateForm())) {
            try {
                if (moDateDateStart.getValue() == null) {
                    miClient.showMsgBoxWarning("No se ha especificado un valor para la fecha inicial.");
                }
                else {
                    absenceConsumption = moReceipt.getHrsEmployee().getHrsPayrollReceipt().createAbsenceConsumption(moAbsence, moDateDateStart.getValue(), moDateDateEnd.getValue(), moIntEffectiveDays.getValue());

                    moReceipt.getHrsEmployee().getHrsPayrollReceipt().addAbsenceConsumption(moAbsence, absenceConsumption);
                    moReceipt.getHrsEmployee().getHrsPayrollReceipt().updateHrsPayrollReceiptEarningAbsence(absenceConsumption, true);

                    mnFormResult = SGuiConsts.FORM_RESULT_OK;
                    dispose();
                }
            }
            catch (Exception e) {
                SLibUtils.showException(this, e);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) e.getSource();

            if (field == moDateDateEnd.getComponent()) {
                calculateWorkingDays();
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (moGridAbsenceRow.getTable().getSelectedRowCount() != -1) {
                loadAbsenceSelected();
            }
        }
    }
}
