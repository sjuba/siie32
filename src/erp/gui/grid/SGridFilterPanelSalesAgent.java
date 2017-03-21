/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.gui.grid;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import sa.lib.SLibConsts;
import sa.lib.db.SDbRegistry;
import sa.lib.grid.SGridConsts;
import sa.lib.grid.SGridFilter;
import sa.lib.grid.SGridFilterValue;
import sa.lib.grid.SGridPaneView;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiItem;
import sa.lib.gui.SGuiParams;
import sa.lib.gui.SGuiUtils;

/**
 *
 * @author Edwin Carmona
 * 
 * This class generates a generic filter that loads sales agents into a combo box
 * 
 */
public class SGridFilterPanelSalesAgent extends JPanel implements SGridFilter, ActionListener, ItemListener {

    private SGuiClient miClient;
    private SGridPaneView moPaneView;
    private int mnFilter;
    
    /**
     * Sales Agents Filter
     * 
     * @param client
     * @param paneView
     * @param filter This parameter can receive the constants:
     *  @SModConsts.BPSX_BP_X_SAL_AGT
     */
    public SGridFilterPanelSalesAgent(SGuiClient client, SGridPaneView paneView, int filter) {
        miClient = client;
        moPaneView = paneView;
        mnFilter = filter;
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

        moKeyFilter = new sa.lib.gui.bean.SBeanFieldKey();
        jbClearFilter = new javax.swing.JButton();

        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        moKeyFilter.setPreferredSize(new java.awt.Dimension(200, 23));
        add(moKeyFilter);

        jbClearFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sa/lib/img/cmd_std_delete_tmp.gif"))); // NOI18N
        jbClearFilter.setToolTipText("Quitar filtro");
        jbClearFilter.setPreferredSize(new java.awt.Dimension(23, 23));
        add(jbClearFilter);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbClearFilter;
    private sa.lib.gui.bean.SBeanFieldKey moKeyFilter;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */
    
    private void initComponentsCustom() {
        jbClearFilter.addActionListener(this);
        moKeyFilter.removeItemListener(this);
        
        miClient.getSession().populateCatalogue(moKeyFilter, mnFilter, SLibConsts.UNDEFINED, new SGuiParams(SDbRegistry.FIELD_CODE));
        
        actionClearFilter();
        moKeyFilter.addItemListener(this);
    }
    
    private void actionClearFilter() {
        jbClearFilter.setEnabled(false);
        
        if (moKeyFilter.getSelectedIndex() > 0) {
            moKeyFilter.setSelectedIndex(0);
            moKeyFilter.requestFocus();
        }
    }
    
    private void itemStateChangedFilter() {
        moPaneView.putFilter(mnFilter, new SGridFilterValue(mnFilter, SGridConsts.FILTER_DATA_TYPE_INT_ARRAY, ((SGuiItem) moKeyFilter.getSelectedItem()).getPrimaryKey()));
        jbClearFilter.setEnabled(moKeyFilter.getSelectedIndex() > 0);
    }
    
    /*
     * Protected methods
     */
    
    @Override
    public void initFilter(Object value) {
        int[] key = null;
        
        moKeyFilter.removeItemListener(this);
        
        key = value == null ? null : new int[] { ((int[]) value)[0] };
        SGuiUtils.locateItem(moKeyFilter, key);
        jbClearFilter.setEnabled(moKeyFilter.getSelectedIndex() > 0);
        moPaneView.getFiltersMap().put(mnFilter, new SGridFilterValue(mnFilter, SGridConsts.FILTER_DATA_TYPE_INT_ARRAY, moKeyFilter.getSelectedIndex() <= 0 ? null : key));
        
        moKeyFilter.addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            
            if (button == jbClearFilter) {
                actionClearFilter();
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof JComboBox) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox comboBox = (JComboBox) e.getSource();

                if (comboBox == moKeyFilter) {
                    itemStateChangedFilter();
                }
            }
        }
    }
}