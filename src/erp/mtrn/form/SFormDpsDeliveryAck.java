/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.mtrn.form;

import erp.data.SDataConstants;
import erp.lib.SLibConstants;
import erp.lib.data.SDataRegistry;
import erp.lib.form.SFormValidation;
import erp.mtrn.data.SDataDps;
import erp.mtrn.data.SDataDpsDeliveryAck;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiUtils;

/**
 *
 * @author Daniel López
 */
public class SFormDpsDeliveryAck extends javax.swing.JDialog implements erp.lib.form.SFormInterface, java.awt.event.ActionListener {

    private int mnFormType;
    private int mnFormResult;
    private int mnFormStatus;
    private boolean mbFirstTime;
    private boolean mbResetingForm;
    private java.util.Vector<erp.lib.form.SFormField> mvFields;
    private erp.client.SClientInterface miClient;

    private java.io.File moFile;
    private erp.mtrn.data.SDataDpsDeliveryAck moDpsDeliveryAck;
    private erp.mtrn.form.SPanelDps moPanelDps;
    private erp.mtrn.data.SDataDps moParamDps;

    /**
     * Creates new form SDialogDpsDeliveryAck
     */
    public SFormDpsDeliveryAck(erp.client.SClientInterface client) {
        super(client.getFrame(), true);
        miClient = client;
        mnFormType = SDataConstants.TRN_DPS_ACK;
        
        initComponents();
        initComponentsExtra();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpDps = new javax.swing.JPanel();
        jlPanelDps = new javax.swing.JLabel();
        jpPicker = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jFile = new javax.swing.JLabel();
        jtfFile = new javax.swing.JTextField();
        jbPickFile = new javax.swing.JButton();
        jpControls = new javax.swing.JPanel();
        jbSave = new javax.swing.JButton();
        jbCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar acuse de entrega.");
        setMinimumSize(new java.awt.Dimension(357, 186));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jpDps.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jpDps.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jpDps.setMinimumSize(new java.awt.Dimension(357, 153));
        jpDps.setPreferredSize(new java.awt.Dimension(881, 339));
        jpDps.setLayout(new java.awt.BorderLayout());

        jlPanelDps.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlPanelDps.setText("[Panel de documento de compras-ventas]");
        jlPanelDps.setPreferredSize(new java.awt.Dimension(100, 200));
        jpDps.add(jlPanelDps, java.awt.BorderLayout.NORTH);

        jpPicker.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar archivo:"));
        jpPicker.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jpPicker.setMinimumSize(new java.awt.Dimension(345, 113));
        jpPicker.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jFile.setText("Ruta archivo:*");
        jFile.setMaximumSize(new java.awt.Dimension(84, 14));
        jFile.setMinimumSize(new java.awt.Dimension(84, 14));
        jFile.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel1.add(jFile);

        jtfFile.setEditable(false);
        jtfFile.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jtfFile.setToolTipText("Ruta del archivo seleccionado.");
        jtfFile.setEnabled(false);
        jtfFile.setPreferredSize(new java.awt.Dimension(600, 23));
        jtfFile.setSelectionEnd(6);
        jtfFile.setSelectionStart(6);
        jPanel1.add(jtfFile);

        jbPickFile.setText("...");
        jbPickFile.setToolTipText("Seleccionar archivo...");
        jbPickFile.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel1.add(jbPickFile);

        jpPicker.add(jPanel1, java.awt.BorderLayout.NORTH);

        jpDps.add(jpPicker, java.awt.BorderLayout.CENTER);

        getContentPane().add(jpDps, java.awt.BorderLayout.CENTER);

        jpControls.setMinimumSize(new java.awt.Dimension(161, 33));
        jpControls.setPreferredSize(new java.awt.Dimension(392, 33));
        jpControls.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jbSave.setText("Guardar");
        jbSave.setPreferredSize(new java.awt.Dimension(75, 23));
        jpControls.add(jbSave);

        jbCancel.setText("Cancelar");
        jpControls.add(jbCancel);

        getContentPane().add(jpControls, java.awt.BorderLayout.PAGE_END);

        setSize(new java.awt.Dimension(800, 500));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        windowActivated();
    }//GEN-LAST:event_formWindowActivated

    private void initComponentsExtra() {
        moPanelDps = new SPanelDps(miClient, "de origen");
        jpDps.remove(jlPanelDps);
        jpDps.add(moPanelDps, BorderLayout.NORTH);

        jbPickFile.addActionListener(this);
        jbSave.addActionListener(this);
        jbCancel.addActionListener(this);
    }

    /**
     * @param args the command line arguments
     */
    private void actionPickFile() {
        miClient.getFileChooser().setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (miClient.getFileChooser().showOpenDialog(miClient.getFrame()) == JFileChooser.APPROVE_OPTION) {
            moFile = new File(miClient.getFileChooser().getSelectedFile().getAbsolutePath());
            jtfFile.setText(moFile.getAbsolutePath());
        }
    }

    private void actionSave() {
        if (jtfFile.getText().equals("")) {
            miClient.showMsgBoxWarning(SLibConstants.MSG_ERR_UTIL_UNKNOWN_OPTION);
            jbPickFile.requestFocus();
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
    
        private void windowActivated() {
        if (mbFirstTime) {mbFirstTime = false;}
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jFile;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbPickFile;
    private javax.swing.JButton jbSave;
    private javax.swing.JLabel jlPanelDps;
    private javax.swing.JPanel jpControls;
    private javax.swing.JPanel jpDps;
    private javax.swing.JPanel jpPicker;
    private javax.swing.JTextField jtfFile;
    // End of variables declaration//GEN-END:variables

    @Override
    public void formClearRegistry() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void formReset() {
        mnFormResult = SLibConstants.UNDEFINED;
        mnFormStatus = SLibConstants.UNDEFINED;
        mbFirstTime = true;

        moFile = null;
        moDpsDeliveryAck = null;
        jtfFile.setText("");
//        for (int i = 0; i < mvFields.size(); i++) {
//            ((erp.lib.form.SFormField) mvFields.get(i)).resetField();
//        }
    }

    @Override
    public void formRefreshCatalogues() {

    }

    @Override
    public SFormValidation formValidate() {
        SFormValidation validation = new SFormValidation();

        for (int i = 0; i < mvFields.size(); i++) {
            if (!((erp.lib.form.SFormField) mvFields.get(i)).validateField()) {
                validation.setIsError(true);
                validation.setComponent(((erp.lib.form.SFormField) mvFields.get(i)).getComponent());
                break;
            }
        }

        if (!validation.getIsError()) {
            if (moFile == null) {
                validation.setMessage(SGuiConsts.ERR_MSG_FIELD_REQ + "'" + SGuiUtils.getLabelName(jFile) + "'.");
                validation.setComponent(jbPickFile);
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
    public void setRegistry(SDataRegistry registry) {
        moParamDps = (SDataDps) registry;
        moPanelDps.setDps(moParamDps, null);
    }

    @Override
    public SDataRegistry getRegistry() {
        if (moDpsDeliveryAck == null) {
            moDpsDeliveryAck = new SDataDpsDeliveryAck();
            moDpsDeliveryAck.setFkUserNewId(miClient.getSession().getUser().getPkUserId());
        }
        else {
            moDpsDeliveryAck.setFkUserEditId(miClient.getSession().getUser().getPkUserId());
        }

        //moDpsDeliveryAck.setPkDpsDeliveryAckId(...);
        //moDpsDeliveryAck.setNameUser(...);
        //moDpsDeliveryAck.setNameSystem(...);
        //moDpsDeliveryAck.setIsDeleted(...);
        moDpsDeliveryAck.setFkYearId(moParamDps.getPkYearId());
        moDpsDeliveryAck.setFkDocId(moParamDps.getPkDocId());
        //moDpsDeliveryAck.setFkUserNewId(...);
        //moDpsDeliveryAck.setFkUserEditId(...);
        //moDpsDeliveryAck.setFkUserDeleteId(...);
        //moDpsDeliveryAck.setUserNewTs(...);
        //moDpsDeliveryAck.setUserEditTs(...);
        //moDpsDeliveryAck.setUserDeleteTs(...);
                
        moDpsDeliveryAck.setAuxFile(moFile);
        
        moDpsDeliveryAck.setAuxPrefix(miClient.getSessionXXX().getCompany().getDbmsDataCompany().getFiscalId());

        return moDpsDeliveryAck;
    }

    @Override
    public void setValue(int type, Object value) {
        
    }

    @Override
    public Object getValue(int type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JLabel getTimeoutLabel() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof javax.swing.JButton) {
            JButton button = (javax.swing.JButton) e.getSource();
            if (button == jbPickFile) {
                actionPickFile();
            }
            else if (button == jbSave) {
                actionSave();
            }
            else if (button == jbCancel) {
                actionCancel();
            }
        }
    }
}
