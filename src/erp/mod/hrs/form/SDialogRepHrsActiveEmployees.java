/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.mod.hrs.form;

import erp.mod.SModConsts;
import sa.lib.SLibConsts;
import sa.lib.SLibTimeUtils;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanDialogReport;

/**
 *
 * @author Juan Barajas
 */
public class SDialogRepHrsActiveEmployees extends SBeanDialogReport {
   
    /**
     * Creates new form SDialogRepHrsActiveEmployees
     * @param client
     * @param title
     */
    public SDialogRepHrsActiveEmployees(SGuiClient client, String title) {
        setFormSettings(client, SModConsts.HRSR_ACT_EMP, SLibConsts.UNDEFINED, title);
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

        moGroupOrderByEmployee = new javax.swing.ButtonGroup();
        moGroupOrderByDepartament = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jlDateStart = new javax.swing.JLabel();
        moDateDateStart = new sa.lib.gui.bean.SBeanFieldDate();
        jPanel12 = new javax.swing.JPanel();
        jlDateEnd = new javax.swing.JLabel();
        moDateDateEnd = new sa.lib.gui.bean.SBeanFieldDate();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        moRadOrderByNumEmployee = new sa.lib.gui.bean.SBeanFieldRadio();
        moRadOrderByNameEmployee = new sa.lib.gui.bean.SBeanFieldRadio();
        jPanel19 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        moRadOrderByNumDepartament = new sa.lib.gui.bean.SBeanFieldRadio();
        moRadOrderByNameDepartament = new sa.lib.gui.bean.SBeanFieldRadio();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Parámetros del reporte:"));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDateStart.setText("Fecha inicial:*");
        jlDateStart.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel11.add(jlDateStart);
        jPanel11.add(moDateDateStart);

        jPanel2.add(jPanel11);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDateEnd.setText("Fecha final:*");
        jlDateEnd.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel12.add(jlDateEnd);
        jPanel12.add(moDateDateEnd);

        jPanel2.add(jPanel12);

        jPanel1.add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordenamiento empleado:"));
        jPanel8.setLayout(new java.awt.GridLayout(2, 1));

        moGroupOrderByEmployee.add(moRadOrderByNumEmployee);
        moRadOrderByNumEmployee.setText("Número del empleado");
        jPanel8.add(moRadOrderByNumEmployee);

        moGroupOrderByEmployee.add(moRadOrderByNameEmployee);
        moRadOrderByNameEmployee.setText("Nombre del empleado");
        jPanel8.add(moRadOrderByNameEmployee);

        jPanel4.add(jPanel8, java.awt.BorderLayout.NORTH);

        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordenamiento departamento:"));
        jPanel10.setLayout(new java.awt.GridLayout(2, 1));

        moGroupOrderByDepartament.add(moRadOrderByNumDepartament);
        moRadOrderByNumDepartament.setText("Número del departamento");
        jPanel10.add(moRadOrderByNumDepartament);

        moGroupOrderByDepartament.add(moRadOrderByNameDepartament);
        moRadOrderByNameDepartament.setText("Nombre del departamento");
        jPanel10.add(moRadOrderByNameDepartament);

        jPanel19.add(jPanel10, java.awt.BorderLayout.NORTH);

        jPanel4.add(jPanel19, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel jlDateEnd;
    private javax.swing.JLabel jlDateStart;
    private sa.lib.gui.bean.SBeanFieldDate moDateDateEnd;
    private sa.lib.gui.bean.SBeanFieldDate moDateDateStart;
    private javax.swing.ButtonGroup moGroupOrderByDepartament;
    private javax.swing.ButtonGroup moGroupOrderByEmployee;
    private sa.lib.gui.bean.SBeanFieldRadio moRadOrderByNameDepartament;
    private sa.lib.gui.bean.SBeanFieldRadio moRadOrderByNameEmployee;
    private sa.lib.gui.bean.SBeanFieldRadio moRadOrderByNumDepartament;
    private sa.lib.gui.bean.SBeanFieldRadio moRadOrderByNumEmployee;
    // End of variables declaration//GEN-END:variables
    
    private String getOrderBy() {
        String orderBy = "";
        
        if (moRadOrderByNumEmployee.isSelected()) {
            orderBy = "ORDER BY CAST(emp.num AS UNSIGNED INTEGER), bp.id_bp, ";
        }
        else if (moRadOrderByNameEmployee.isSelected()) {
            orderBy = "ORDER BY bp.bp, bp.id_bp, ";
        }
        
        if (moRadOrderByNumDepartament.isSelected()) {
            orderBy += "dep.code, dep.id_dep, ";
        }
        else if (moRadOrderByNameDepartament.isSelected()) {
            orderBy += "dep.name, dep.id_dep, ";
        }
        orderBy += "l.dt_hire, l.dt_dis_n; ";
        
        return orderBy;
    }
    
    private void initComponentsCustom() {
        SGuiUtils.setWindowBounds(this, 480, 300);

        moDateDateStart.setDateSettings(miClient, SGuiUtils.getLabelName(jlDateStart.getText()), true);
        moDateDateEnd.setDateSettings(miClient, SGuiUtils.getLabelName(jlDateEnd.getText()), true);
        
        moFields.addField(moDateDateStart);
        moFields.addField(moDateDateEnd);
        moFields.addField(moRadOrderByNumEmployee);
        moFields.addField(moRadOrderByNameEmployee);
        moFields.addField(moRadOrderByNumDepartament);
        moFields.addField(moRadOrderByNameDepartament);;

        moFields.setFormButton(jbPrint);
        
        moDateDateStart.setValue(SLibTimeUtils.getBeginOfYear(miClient.getSession().getCurrentDate()));
        moDateDateEnd.setValue(SLibTimeUtils.getEndOfYear(miClient.getSession().getCurrentDate()));
        moRadOrderByNameEmployee.setSelected(true);
        moRadOrderByNameDepartament.setSelected(true);
    }

    @Override
    public SGuiValidation validateForm() {
        SGuiValidation validation = moFields.validateFields();
        
        if (validation.isValid()) {
            validation = SGuiUtils.validateDateRange(moDateDateStart, moDateDateEnd);
        }
        
        return validation;
    }
    
    @Override
    public void createParamsMap() {
        moParamsMap = miClient.createReportParams();
        
        moParamsMap.put("sTitle", "REPORTE DE EMPLEADOS ACTIVOS POR PERIODO");
        moParamsMap.put("nDaysPassed", SLibTimeUtils.getDaysDiff(moDateDateEnd.getValue(), moDateDateStart.getValue()) + 1);
        moParamsMap.put("tDateStart", moDateDateStart.getValue());
        moParamsMap.put("tDateEnd", moDateDateEnd.getValue());
        moParamsMap.put("sSqlOrderBy", getOrderBy());
    }
}
