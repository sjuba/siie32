/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * STabFilterBizPartner.java
 *
 * Created on 5/02/2010, 06:36:11 PM
 */

package erp.table;

import erp.data.SDataConstants;
import erp.data.SDataUtilities;
import erp.lib.table.*;
import erp.lib.SLibConstants;
import erp.musr.data.SDataUser;
import erp.musr.form.SDialogFilterUser;

/**
 *
 * @author Juan Barajas
 */
public class STabFilterUsers extends javax.swing.JPanel {

    private erp.client.SClientInterface miClient;
    private erp.lib.table.STableTab moTab;
    private int mnUserId;
    private erp.lib.table.STableSetting moSetting;
    private erp.musr.form.SDialogFilterUser moDialogFilterUser;

    /** Creates new form STabFilterUser */
    public STabFilterUsers(erp.client.SClientInterface client, erp.lib.table.STableTab tableTab) {
        miClient = client;
        moTab = tableTab;

        initComponents();
        initComponentsExtra();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtfUser = new javax.swing.JTextField();
        jbUser = new javax.swing.JButton();

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        jtfUser.setEditable(false);
        jtfUser.setText("NAME");
        jtfUser.setToolTipText("Usuario");
        jtfUser.setPreferredSize(new java.awt.Dimension(65, 23));
        add(jtfUser);

        jbUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_std_filter_bp.gif"))); // NOI18N
        jbUser.setToolTipText("Seleccionar usuario");
        jbUser.setPreferredSize(new java.awt.Dimension(23, 23));
        jbUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbUserActionPerformed(evt);
            }
        });
        add(jbUser);
    }// </editor-fold>//GEN-END:initComponents

    private void jbUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbUserActionPerformed
        actionUser();
    }//GEN-LAST:event_jbUserActionPerformed

    private void initComponentsExtra() {
        mnUserId = SLibConstants.UNDEFINED;
        moSetting = new STableSetting(SFilterConstants.SETTING_FILTER_BP, mnUserId);
        moDialogFilterUser = new SDialogFilterUser(miClient);
        
        renderText();
    }

    private void actionUser() {
        moDialogFilterUser.formRefreshCatalogues();
        moDialogFilterUser.formReset();
        moDialogFilterUser.setUserId(mnUserId);
        moDialogFilterUser.setFormVisible(true);

        if (moDialogFilterUser.getFormResult() == erp.lib.SLibConstants.FORM_RESULT_OK) {
            mnUserId = moDialogFilterUser.getUserId();
            moSetting.setSetting(mnUserId);
            moTab.updateSetting(moSetting);
            renderText();
        }
    }

    private void renderText() {
        SDataUser user = new SDataUser();

        if (mnUserId > SLibConstants.UNDEFINED) {
            user = (SDataUser) SDataUtilities.readRegistry(miClient, SDataConstants.USRU_USR, new int[] { mnUserId }, SLibConstants.EXEC_MODE_SILENT);

            jtfUser.setText(user.getUser());
        }
        else {
            jtfUser.setText(SLibConstants.TXT_ALL);
        }
        jtfUser.setCaretPosition(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbUser;
    private javax.swing.JTextField jtfUser;
    // End of variables declaration//GEN-END:variables

    public void setUserId(int nUserId) {
        mnUserId = nUserId;

        renderText();
    }

    public void removeButtonUser() {
        remove(jbUser);
    }
}
