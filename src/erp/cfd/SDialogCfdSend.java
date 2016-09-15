/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.cfd;

import erp.lib.SLibUtilities;
import erp.mbps.data.SDataBizPartner;
import erp.mod.SModConsts;
import erp.mtrn.data.SDataCfd;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import sa.lib.SLibConsts;
import sa.lib.SLibUtils;
import sa.lib.db.SDbRegistry;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.gui.SGuiUtils;
import sa.lib.gui.SGuiValidation;
import sa.lib.gui.bean.SBeanFormDialog;

/**
 *
 * @author Edwin Carmona
 */
public class SDialogCfdSend extends SBeanFormDialog implements ActionListener {
    
    public static final int VAL_IS_EMAIL_EDITED = 1;
    public static final int VAL_EMAIL = 2;

    private SDataCfd moCfd;     // XXX obsolete framework, no alternative options yet!
    private SDataBizPartner moBizPartner;    // XXX obsolete framework, no alternative options yet!
    private int mnBizPartnerBranch;
    
    /**
     * Creates new form SFormCfdSend
     */
    public SDialogCfdSend(SGuiClient client, String title, SDataCfd cfd, SDataBizPartner bizPartner, int bizPartnerBranch) {
        setFormSettings(client, SGuiConsts.BEAN_FORM_EDIT, SModConsts.TRN_CFD, SLibConsts.UNDEFINED, title);
        moCfd = cfd;
        moBizPartner = bizPartner;
        mnBizPartnerBranch = bizPartnerBranch;
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jlCfdNumber = new javax.swing.JLabel();
        mtfCfdNumber = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jlBizPartner = new javax.swing.JLabel();
        mtfBizPartner = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jlEmail = new javax.swing.JLabel();
        moTextEmail = new sa.lib.gui.bean.SBeanFieldText();
        jbEmailEdit = new javax.swing.JButton();
        jlAddingMultipleMailHelp = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jlDummy1 = new javax.swing.JLabel();
        jlDummy2 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(320, 200));
        setModal(true);
        setResizable(false);
        setSize(new java.awt.Dimension(320, 200));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones para el CFDI:"));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.GridLayout(4, 1, 0, 5));

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlCfdNumber.setText("Folio CFDI:");
        jlCfdNumber.setMaximumSize(new java.awt.Dimension(200, 23));
        jlCfdNumber.setMinimumSize(new java.awt.Dimension(200, 23));
        jlCfdNumber.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel2.add(jlCfdNumber);
        jlCfdNumber.getAccessibleContext().setAccessibleName("");

        mtfCfdNumber.setEditable(false);
        mtfCfdNumber.setFocusable(false);
        mtfCfdNumber.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel2.add(mtfCfdNumber);

        jPanel7.add(jPanel2);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlBizPartner.setText("Asociado negocios:");
        jlBizPartner.setMaximumSize(new java.awt.Dimension(200, 23));
        jlBizPartner.setMinimumSize(new java.awt.Dimension(200, 23));
        jlBizPartner.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel4.add(jlBizPartner);

        mtfBizPartner.setEditable(false);
        mtfBizPartner.setFocusable(false);
        mtfBizPartner.setPreferredSize(new java.awt.Dimension(350, 23));
        jPanel4.add(mtfBizPartner);

        jPanel7.add(jPanel4);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlEmail.setText("E-mail destinatario:*");
        jlEmail.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel5.add(jlEmail);

        moTextEmail.setMaxLength(255);
        moTextEmail.setPreferredSize(new java.awt.Dimension(275, 23));
        jPanel5.add(moTextEmail);

        jbEmailEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_std_edit.gif"))); // NOI18N
        jbEmailEdit.setToolTipText("Modificar e-mail destinatarios");
        jbEmailEdit.setFocusable(false);
        jbEmailEdit.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel5.add(jbEmailEdit);

        jlAddingMultipleMailHelp.setForeground(new java.awt.Color(109, 109, 109));
        jlAddingMultipleMailHelp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlAddingMultipleMailHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_view_help.png"))); // NOI18N
        jlAddingMultipleMailHelp.setToolTipText("Para varios correos separarlos con \";\", sin espacios, p. ej., \"ejemplo1@mail.com;ejemplo2@mail.com\"");
        jlAddingMultipleMailHelp.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel5.add(jlAddingMultipleMailHelp);

        jPanel7.add(jPanel5);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlDummy1.setMaximumSize(new java.awt.Dimension(200, 23));
        jlDummy1.setMinimumSize(new java.awt.Dimension(200, 23));
        jlDummy1.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlDummy1);

        jlDummy2.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jlDummy2.setText("Cambios al e-mail se guardan en el asociado de negocios.");
        jlDummy2.setPreferredSize(new java.awt.Dimension(350, 23));
        jPanel8.add(jlDummy2);

        jPanel7.add(jPanel8);

        jPanel6.add(jPanel7, java.awt.BorderLayout.NORTH);

        getContentPane().add(jPanel6, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
    }//GEN-LAST:event_formWindowActivated

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JButton jbEmailEdit;
    private javax.swing.JLabel jlAddingMultipleMailHelp;
    private javax.swing.JLabel jlBizPartner;
    private javax.swing.JLabel jlCfdNumber;
    private javax.swing.JLabel jlDummy1;
    private javax.swing.JLabel jlDummy2;
    private javax.swing.JLabel jlEmail;
    private sa.lib.gui.bean.SBeanFieldText moTextEmail;
    private javax.swing.JTextField mtfBizPartner;
    private javax.swing.JTextField mtfCfdNumber;
    // End of variables declaration//GEN-END:variables

    /*
     * Private methods
     */
    
    private void initComponentsCustom() {
        SGuiUtils.setWindowBounds(this, 480, 300);

        jbSave.setText("Aceptar");
        moTextEmail.setTextSettings(SGuiUtils.getLabelName(jlEmail), 255);
        moTextEmail.setTextCaseType(SLibConsts.UNDEFINED);
        
        moFields.addField(moTextEmail);
        
        moFields.setFormButton(jbSave);

        mtfCfdNumber.setText(moCfd.getCfdNumber());
        mtfBizPartner.setText(moBizPartner.getBizPartner());
        
        mtfCfdNumber.setCaretPosition(0);
        mtfBizPartner.setCaretPosition(0);
        
        if (mnBizPartnerBranch == SLibConsts.UNDEFINED) {
            moTextEmail.setText(moBizPartner.getDbmsHqBranch().getDbmsBizPartnerBranchContacts().get(0).getEmail01());
        }
        else {
            moTextEmail.setText(moBizPartner.getDbmsBizPartnerBranch(new int[] { mnBizPartnerBranch }).getDbmsBizPartnerBranchContacts().get(0).getEmail01());
        }
        
        enableEmailFields(moTextEmail.getText().isEmpty());
        addAllListeners();
    }
    
    private void enableEmailFields(boolean edit) {
        jbEmailEdit.setEnabled(!edit);
        moTextEmail.setEditable(edit);
    }
    
    private void actionPerformedEmailEdit() {
        enableEmailFields(true);
        moTextEmail.requestFocus();
    }

    /*
     * Implemented or overriden methods
     */
    
    @Override
    public void addAllListeners() {
        jbEmailEdit.addActionListener(this);
    }

    @Override
    public void removeAllListeners() {
        jbEmailEdit.removeActionListener(this);
    }

    @Override
    public void reloadCatalogues() {
        
    }

    @Override
    public void setRegistry(SDbRegistry registry) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SDbRegistry getRegistry() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public SGuiValidation validateForm() {
        String[] emails;
        String result = "";
        SGuiValidation validation = moFields.validateFields();
        
        if (validation.isValid()) {
            emails = SLibUtils.textExplode(moTextEmail.getValue(), ";");
            for (String email: emails) {
                result = SLibUtilities.validateEmail(email);    // XXX implement this funtion into new framework!
                if (!result.isEmpty()) {
                    validation.setMessage(result);
                    validation.setComponent(moTextEmail);
                    break;
                }
            }
        }
        
        return validation;
    }
    
    @Override
    public Object getValue(final int type) {
        Object value = null;
        
        switch (type) {
            case VAL_IS_EMAIL_EDITED:
                value = moTextEmail.isEditable();
                break;
            case VAL_EMAIL:
                value = moTextEmail.getValue();
                break;
            default:
        }
        
        return value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();

            if (button == jbEmailEdit) {
                actionPerformedEmailEdit();
            }
        }
    }
}
