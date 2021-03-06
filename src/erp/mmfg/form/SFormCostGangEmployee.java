/*
 * 
 */

package erp.mmfg.form;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.AbstractAction;

import erp.data.SDataConstants;
import erp.data.SDataConstantsSys;
import erp.lib.form.SFormComponentItem;
import erp.lib.form.SFormField;
import erp.lib.form.SFormValidation;
import erp.lib.form.SFormUtilities;
import erp.lib.SLibConstants;

/**
 *
 * @author  Néstor Ávalos
 */
public class SFormCostGangEmployee extends javax.swing.JDialog implements erp.lib.form.SFormInterface, java.awt.event.ActionListener {
    
    private int mnFormType;
    private int mnFormResult;
    private int mnFormStatus;
    private boolean mbFirstTime;
    private boolean mbResetingForm;
    private java.util.Vector<erp.lib.form.SFormField> mvFields;
    private erp.client.SClientInterface miClient;
    
    private erp.lib.form.SFormField moFieldFkCobId;
    private erp.lib.form.SFormField moFieldFkEntityId;
    
    private boolean mbGang;
    private int mnFkCobId;

    /** Creates new form DFormCompany */
    public SFormCostGangEmployee(erp.client.SClientInterface client) {
        super(client.getFrame(), true);
        miClient = client;
        // mnFormType = SDataConstants.MFGU_GANG;

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

        jpData = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlFkCobId = new javax.swing.JLabel();
        jlDummy1 = new javax.swing.JLabel();
        jtfFkCobId = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jlFkEntityId = new javax.swing.JLabel();
        jcbFkEntityId = new javax.swing.JComboBox<SFormComponentItem>();
        jlDummy2 = new javax.swing.JLabel();
        jbFkEntityId = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jbOk = new javax.swing.JButton();
        jbCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entidad"); // NOI18N
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jpData.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jpData.setLayout(new java.awt.GridLayout(5, 1));

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 1));

        jlFkCobId.setText("Sucursal:");
        jlFkCobId.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel1.add(jlFkCobId);

        jlDummy1.setText("jLabel1");
        jlDummy1.setPreferredSize(new java.awt.Dimension(1, 23));
        jPanel1.add(jlDummy1);

        jtfFkCobId.setEditable(false);
        jtfFkCobId.setText("COMPANY BRANCH");
        jtfFkCobId.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel1.add(jtfFkCobId);

        jpData.add(jPanel1);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 1));

        jlFkEntityId.setText("Orden producción: *");
        jlFkEntityId.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jlFkEntityId);

        jcbFkEntityId.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel3.add(jcbFkEntityId);

        jlDummy2.setText("jLabel1");
        jlDummy2.setPreferredSize(new java.awt.Dimension(1, 23));
        jPanel3.add(jlDummy2);

        jbFkEntityId.setText("...");
        jbFkEntityId.setToolTipText("Seleccionar sucursal");
        jbFkEntityId.setFocusable(false);
        jbFkEntityId.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel3.add(jbFkEntityId);

        jpData.add(jPanel3);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 1));
        jpData.add(jPanel4);

        getContentPane().add(jpData, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jbOk.setText("Aceptar"); // NOI18N
        jbOk.setToolTipText("[Ctrl + Enter]");
        jbOk.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel2.add(jbOk);

        jbCancel.setText("Cancelar"); // NOI18N
        jbCancel.setToolTipText("[Escape]");
        jPanel2.add(jbCancel);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-438)/2, (screenSize.height-225)/2, 438, 225);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        windowActivated();
    }//GEN-LAST:event_formWindowActivated
    
    private void initComponentsExtra() {
        mvFields = new Vector<SFormField>();
        
        moFieldFkCobId = new erp.lib.form.SFormField(miClient, SLibConstants.DATA_TYPE_STRING, false, jtfFkCobId, jlFkCobId);
        moFieldFkEntityId = new erp.lib.form.SFormField(miClient, SLibConstants.DATA_TYPE_KEY, true, jcbFkEntityId, jlFkEntityId);
        
        mvFields.add(moFieldFkCobId);
        mvFields.add(moFieldFkEntityId);
        
        jbOk.addActionListener(this);
        jbCancel.addActionListener(this);
        jbFkEntityId.addActionListener(this);

        AbstractAction actionOk = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { actionOk(); }
        };

        SFormUtilities.putActionMap(getRootPane(), actionOk, "ok", KeyEvent.VK_ENTER, KeyEvent.CTRL_DOWN_MASK);

        AbstractAction actionCancel = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) { actionCancel(); }
        };

        SFormUtilities.putActionMap(getRootPane(), actionCancel, "cancel", KeyEvent.VK_ESCAPE, 0);
    }

     private void windowActivated() {
        if (mbFirstTime) {
            mbFirstTime = false;
            jcbFkEntityId.requestFocus();
        }
     }

    private void actionEdit(boolean edit) {
        
    }
    
    private void actionOk() {
        erp.lib.form.SFormValidation validation = formValidate();
        
        if (validation.getIsError()) {
            if (validation.getComponent() != null) {
                validation.getComponent().requestFocus();
            }
            if (validation.getMessage().length() > 0) {
                miClient.showMsgBoxWarning(validation.getMessage());
            }
        }
        else {
            mnFormResult = SLibConstants.FORM_RESULT_OK;
            setVisible(false);
        }
    }
    
    private void actionCancel() {
        mnFormResult = SLibConstants.FORM_RESULT_CANCEL;
        setVisible(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbFkEntityId;
    private javax.swing.JButton jbOk;
    private javax.swing.JComboBox<SFormComponentItem> jcbFkEntityId;
    private javax.swing.JLabel jlDummy1;
    private javax.swing.JLabel jlDummy2;
    private javax.swing.JLabel jlFkCobId;
    private javax.swing.JLabel jlFkEntityId;
    private javax.swing.JPanel jpData;
    private javax.swing.JTextField jtfFkCobId;
    // End of variables declaration//GEN-END:variables

    @Override
    public void formClearRegistry() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void formReset() {
        mnFormResult = SLibConstants.UNDEFINED;
        mnFormStatus = SLibConstants.UNDEFINED;
        mbFirstTime = true;
                
        for (int i = 0; i < mvFields.size(); i++) {
            ((erp.lib.form.SFormField) mvFields.get(i)).resetField();
        }
    }
    
    @Override
    public void formRefreshCatalogues() {

        if (mbGang) {
            SFormUtilities.populateComboBox(miClient, jcbFkEntityId, SDataConstants.MFGU_GANG);
        }
        else {
            SFormUtilities.populateComboBox(miClient, jcbFkEntityId, SDataConstants.BPSX_BP_EMP);
        }
    }
    
    @Override
    public erp.lib.form.SFormValidation formValidate() {
        erp.lib.form.SFormValidation validation = new SFormValidation();
        
        for (int i = 0; i < mvFields.size(); i++) {
            if (!((erp.lib.form.SFormField) mvFields.get(i)).validateField()) {
                validation.setIsError(true);
                validation.setComponent(((erp.lib.form.SFormField) mvFields.get(i)).getComponent());
                break;
            }
        }
        
        return validation;
    }
    
    @Override
    public void setFormStatus(int status) {
        mnFormStatus = status;
    }
    
    @Override
    public void setFormVisible(boolean visible) {
        setVisible(visible);
    }
    
    @Override
    public int getFormStatus() {
        return mnFormStatus;
    }
    
    @Override
    public int getFormResult() {
        return mnFormResult;
    }
    
    @Override
    public void setRegistry(erp.lib.data.SDataRegistry registry) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public erp.lib.data.SDataRegistry getRegistry() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void setValue(int type, java.lang.Object value) {
        switch (type) {
            case 1:
                mbGang = (Boolean) value;

                if (mbGang) {
                    jlFkEntityId.setText("Cuadrilla: *");
                }
                else {
                    jlFkEntityId.setText("Empleado: *");
                }
                break;
            case 2:
                jtfFkCobId.setText((String) value);
                break;
        }
    }
    
    @Override
    public java.lang.Object getValue(int type) {
        Object oValue = null;

        switch (type) {
            case 1:
                oValue = moFieldFkEntityId.getKeyAsIntArray()[0];
                break;
        }

        return oValue;
    }
    
    @Override
    public javax.swing.JLabel getTimeoutLabel() {
        return null;
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() instanceof javax.swing.JButton) {
            javax.swing.JButton button = (javax.swing.JButton) e.getSource();
            
            if (button == jbOk) {
                actionOk();
            }
            else if (button == jbCancel) {
                actionCancel();
            }
        }        
    }
}
