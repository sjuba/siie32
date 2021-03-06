/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package erp.lib.table;

import erp.lib.SLibConstants;
import erp.lib.SLibTimeUtilities;

/**
 *
 * @author Sergio Flores
 */
public class STabFilterDatePeriodRange extends javax.swing.JPanel {

    private erp.client.SClientInterface miClient;
    private erp.lib.table.STableTabInterface miTableTab;

    private java.util.Date mtDateBegin;
    private java.util.Date mtDateEnd;
    private erp.lib.table.STableSetting moSetting;

    /** Creates new form DTabFilterDatePeriodRange */
    public STabFilterDatePeriodRange(erp.client.SClientInterface client, erp.lib.table.STableTabInterface tableTab) {
        miClient = client;
        miTableTab = tableTab;

        initComponents();
        initComponentsExtra();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfDateRange = new javax.swing.JTextField();
        jbDate = new javax.swing.JButton();
        jbSystemDate = new javax.swing.JButton();
        jbSystemYearMonth = new javax.swing.JButton();
        jbSystemYear = new javax.swing.JButton();

        setToolTipText("Rango de fechas");
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        jtfDateRange.setEditable(false);
        jtfDateRange.setText("01/01/2000 - 31/12/2000");
        jtfDateRange.setToolTipText("Período");
        jtfDateRange.setFocusable(false);
        jtfDateRange.setPreferredSize(new java.awt.Dimension(130, 20));
        add(jtfDateRange);

        jbDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/cal_cal.gif"))); // NOI18N
        jbDate.setToolTipText("Seleccionar rango de fechas");
        jbDate.setPreferredSize(new java.awt.Dimension(23, 23));
        jbDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDateActionPerformed(evt);
            }
        });
        add(jbDate);

        jbSystemDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/cal_date_day.gif"))); // NOI18N
        jbSystemDate.setToolTipText("Hoy");
        jbSystemDate.setPreferredSize(new java.awt.Dimension(23, 23));
        jbSystemDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSystemDateActionPerformed(evt);
            }
        });
        add(jbSystemDate);

        jbSystemYearMonth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/cal_date_month.gif"))); // NOI18N
        jbSystemYearMonth.setToolTipText("Mes actual");
        jbSystemYearMonth.setPreferredSize(new java.awt.Dimension(23, 23));
        jbSystemYearMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSystemYearMonthActionPerformed(evt);
            }
        });
        add(jbSystemYearMonth);

        jbSystemYear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/cal_date_year.gif"))); // NOI18N
        jbSystemYear.setToolTipText("Año actual");
        jbSystemYear.setPreferredSize(new java.awt.Dimension(23, 23));
        jbSystemYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSystemYearActionPerformed(evt);
            }
        });
        add(jbSystemYear);
    }// </editor-fold>//GEN-END:initComponents

    private void jbDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDateActionPerformed
        actionDateRange();
    }//GEN-LAST:event_jbDateActionPerformed

    private void jbSystemDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSystemDateActionPerformed
        mtDateBegin = miClient.getSessionXXX().getWorkingDate();
        mtDateEnd = miClient.getSessionXXX().getWorkingDate();
        moSetting.setSetting(new java.util.Date[] { mtDateBegin, mtDateEnd });
        miTableTab.updateSetting(moSetting);
        renderDateRange();
}//GEN-LAST:event_jbSystemDateActionPerformed

    private void jbSystemYearMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSystemYearMonthActionPerformed
        mtDateBegin = SLibTimeUtilities.getBeginOfMonth(miClient.getSessionXXX().getWorkingDate());
        mtDateEnd = SLibTimeUtilities.getEndOfMonth(miClient.getSessionXXX().getWorkingDate());
        moSetting.setSetting(new java.util.Date[] { mtDateBegin, mtDateEnd });
        miTableTab.updateSetting(moSetting);
        renderDateRange();
}//GEN-LAST:event_jbSystemYearMonthActionPerformed

    private void jbSystemYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSystemYearActionPerformed
        mtDateBegin = SLibTimeUtilities.getBeginOfYear(miClient.getSessionXXX().getWorkingDate());
        mtDateEnd = SLibTimeUtilities.getEndOfYear(miClient.getSessionXXX().getWorkingDate());
        moSetting.setSetting(new java.util.Date[] { mtDateBegin, mtDateEnd });
        miTableTab.updateSetting(moSetting);
        renderDateRange();
}//GEN-LAST:event_jbSystemYearActionPerformed

    private void initComponentsExtra() {
        moSetting = null;

        mtDateBegin = SLibTimeUtilities.getBeginOfMonth(miClient.getSessionXXX().getWorkingDate());
        mtDateEnd = SLibTimeUtilities.getEndOfMonth(miClient.getSessionXXX().getWorkingDate());
        moSetting = new STableSetting(STableConstants.SETTING_FILTER_PERIOD, new java.util.Date[] { mtDateBegin, mtDateEnd });
        miTableTab.addSetting(moSetting);
        renderDateRange();
    }

    private void renderDateRange() {
        jtfDateRange.setText(miClient.getSessionXXX().getFormatters().getDateFormat().format(mtDateBegin) + " - " + miClient.getSessionXXX().getFormatters().getDateFormat().format(mtDateEnd));
    }

    private void actionDateRange() {
        miClient.getGuiDateRangePickerXXX().formReset();
        miClient.getGuiDateRangePickerXXX().setDateBegin(mtDateBegin);
        miClient.getGuiDateRangePickerXXX().setDateEnd(mtDateEnd);
        miClient.getGuiDateRangePickerXXX().setVisible(true);

        if (miClient.getGuiDateRangePickerXXX().getFormResult() == SLibConstants.FORM_RESULT_OK) {
            mtDateBegin = miClient.getGuiDateRangePickerXXX().getDateBegin();
            mtDateEnd = miClient.getGuiDateRangePickerXXX().getDateEnd();
            moSetting.setSetting(new java.util.Date[] { mtDateBegin, mtDateEnd });
            miTableTab.updateSetting(moSetting);
            renderDateRange();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbDate;
    private javax.swing.JButton jbSystemDate;
    private javax.swing.JButton jbSystemYear;
    private javax.swing.JButton jbSystemYearMonth;
    private javax.swing.JTextField jtfDateRange;
    // End of variables declaration//GEN-END:variables

}
