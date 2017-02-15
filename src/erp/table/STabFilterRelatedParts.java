/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * STabFilterRelatedParts.java
 */

package erp.table;

import erp.lib.SLibConstants;
import erp.lib.table.STableSetting;
import erp.mod.trn.db.STrnConsts;
import javax.swing.JToggleButton;

/**
 *
 * @author Edwin Carmona
 */
public class STabFilterRelatedParts extends javax.swing.JPanel implements java.awt.event.ActionListener {

    private erp.client.SClientInterface miClient;
    private erp.lib.table.STableTab moTab;
    private erp.lib.table.STableSetting moSetting;

    private int mnDataType;

    /** Creates new form STabFilterRelatedParts */
    public STabFilterRelatedParts(erp.client.SClientInterface client, erp.lib.table.STableTab tableTab) {
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

        jtfFilterCurrency = new javax.swing.JTextField();
        jtbRelatedParty = new javax.swing.JToggleButton();

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        jtfFilterCurrency.setEditable(false);
        jtfFilterCurrency.setText("RELATED PARTS");
        jtfFilterCurrency.setToolTipText("Moneda");
        jtfFilterCurrency.setPreferredSize(new java.awt.Dimension(150, 23));
        add(jtfFilterCurrency);

        jtbRelatedParty.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/switch_rel_pty_on.gif"))); // NOI18N
        jtbRelatedParty.setPreferredSize(new java.awt.Dimension(23, 23));
        add(jtbRelatedParty);
    }// </editor-fold>//GEN-END:initComponents

    private void initComponentsExtra() {
        moSetting = new STableSetting(SFilterConstants.SETTING_FILTER_REL_PARTY, STrnConsts.TRN_BPS_WITH_REL_PARTY);
        
        jtbRelatedParty.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/switch_rel_pty_off.gif")));
        jtbRelatedParty.setToolTipText("Filtrar partes relacionadas");
        jtbRelatedParty.setPreferredSize(new java.awt.Dimension(23, 23));
        jtbRelatedParty.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/switch_rel_pty_on.gif")));
        
        jtbRelatedParty.addActionListener(this);
        
        jtbRelatedParty.setSelected(true);

        actionFilterRelatedParty();
    }
    
    private void actionFilterRelatedParty() {
        mnDataType = jtbRelatedParty.isSelected() ? STrnConsts.TRN_BPS_WITH_REL_PARTY : SLibConstants.UNDEFINED;
        moSetting.setSetting(mnDataType);
        moTab.updateSetting(moSetting);
        
        renderText();
    }

    private void renderText() {
        jtfFilterCurrency.setText(mnDataType == STrnConsts.TRN_BPS_WITH_REL_PARTY ? STrnConsts.TRN_TXT_REL_PARTS : STrnConsts.TRN_TXT_WITHOUT_REL_PARTS);
        jtfFilterCurrency.setCaretPosition(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton jtbRelatedParty;
    private javax.swing.JTextField jtfFilterCurrency;
    // End of variables declaration//GEN-END:variables
 
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {                                                 
        if (e.getSource() instanceof javax.swing.JToggleButton) {
            JToggleButton toggleButton = (JToggleButton) e.getSource();

            if (toggleButton == jtbRelatedParty) {
                actionFilterRelatedParty();
            }
        }
    }
}
