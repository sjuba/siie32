/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SFormDpsEntryNotes.java
 *
 * Created on 22/09/2009, 04:24:20 PM
 */

package erp.mtrn.form;

import erp.data.SDataConstants;
import erp.lib.SLibConstants;
import erp.lib.form.SFormField;
import erp.lib.form.SFormUtilities;
import erp.lib.form.SFormValidation;
import erp.mtrn.data.SDataDpsEntryNotes;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.AbstractAction;

/**
 *
 * @author Sergio Flores
 */
public class SFormDpsEntryNotes extends javax.swing.JDialog implements erp.lib.form.SFormInterface, java.awt.event.ActionListener {

    private int mnFormType;
    private int mnFormResult;
    private int mnFormStatus;
    private boolean mbFirstTime;
    private boolean mbResetingForm;
    private java.util.Vector<SFormField> mvFields;
    private erp.client.SClientInterface miClient;

    private erp.mtrn.data.SDataDpsEntryNotes moDpsEntryNotes;
    private erp.lib.form.SFormField moFieldNotes;
    private erp.lib.form.SFormField moFieldIsAllDocs;
    private erp.lib.form.SFormField moFieldIsPrintable;
    private erp.lib.form.SFormField moFieldIsCfd;
    private erp.lib.form.SFormField moFieldIsDeleted;

    /** Creates new form SFormDpsEntryNotes */
    public SFormDpsEntryNotes(erp.client.SClientInterface client) {
        super(client.getFrame(), true);
        miClient =  client;
        mnFormType = SDataConstants.TRN_DPS_NTS;

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

        jPanel1 = new javax.swing.JPanel();
        jbOk = new javax.swing.JButton();
        jbCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jlNotes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaNotes = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jckIsAllDocs = new javax.swing.JCheckBox();
        jckIsPrintable = new javax.swing.JCheckBox();
        jckIsCfd = new javax.swing.JCheckBox();
        jckIsDeleted = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Notas del detalle del documento");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(392, 33));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jbOk.setText("Aceptar");
        jbOk.setToolTipText("[Ctrl + Enter]");
        jbOk.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel1.add(jbOk);

        jbCancel.setText("Cancelar");
        jbCancel.setToolTipText("[Escape]");
        jPanel1.add(jbCancel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jlNotes.setText("Notas: *");
        jlNotes.setPreferredSize(new java.awt.Dimension(32, 23));
        jPanel2.add(jlNotes, java.awt.BorderLayout.NORTH);

        jtaNotes.setColumns(20);
        jtaNotes.setRows(5);
        jScrollPane1.setViewportView(jtaNotes);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jckIsAllDocs.setText("Para todos los documentos");
        jckIsAllDocs.setPreferredSize(new java.awt.Dimension(175, 23));
        jPanel3.add(jckIsAllDocs);

        jckIsPrintable.setText("Para impresión");
        jckIsPrintable.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jckIsPrintable);

        jckIsCfd.setText("Para XML CFD");
        jckIsCfd.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel3.add(jckIsCfd);

        jckIsDeleted.setText("Registro eliminado");
        jckIsDeleted.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel3.add(jckIsDeleted);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(600, 300));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        windowActivated();
    }//GEN-LAST:event_formWindowActivated

    private void initComponentsExtra() {
        mvFields = new Vector<SFormField>();

        moFieldNotes = new SFormField(miClient, SLibConstants.DATA_TYPE_STRING, true, jtaNotes, jlNotes);
        moFieldNotes.setLengthMax(1023);
        moFieldIsAllDocs = new SFormField(miClient, SLibConstants.DATA_TYPE_BOOLEAN, true, jckIsAllDocs);
        moFieldIsPrintable = new SFormField(miClient, SLibConstants.DATA_TYPE_BOOLEAN, true, jckIsPrintable);
        moFieldIsCfd = new SFormField(miClient, SLibConstants.DATA_TYPE_BOOLEAN, true, jckIsCfd);
        moFieldIsDeleted = new SFormField(miClient, SLibConstants.DATA_TYPE_BOOLEAN, true, jckIsDeleted);

        mvFields.add(moFieldNotes);
        mvFields.add(moFieldIsAllDocs);
        mvFields.add(moFieldIsPrintable);
        mvFields.add(moFieldIsCfd);
        mvFields.add(moFieldIsDeleted);

        jbOk.addActionListener(this);
        jbCancel.addActionListener(this);

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
            jtaNotes.requestFocus();
        }
    }

    private void actionOk() {
        SFormValidation validation = formValidate();

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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbOk;
    private javax.swing.JCheckBox jckIsAllDocs;
    private javax.swing.JCheckBox jckIsCfd;
    private javax.swing.JCheckBox jckIsDeleted;
    private javax.swing.JCheckBox jckIsPrintable;
    private javax.swing.JLabel jlNotes;
    private javax.swing.JTextArea jtaNotes;
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

        moDpsEntryNotes = null;

        for (int i = 0; i < mvFields.size(); i++) {
            ((erp.lib.form.SFormField) mvFields.get(i)).resetField();
        }

        moFieldIsAllDocs.setFieldValue(true);
        moFieldIsPrintable.setFieldValue(true);
        moFieldIsCfd.setFieldValue(false);

        jckIsDeleted.setEnabled(false);
    }

    @Override
    public void formRefreshCatalogues() {

    }

    @Override
    public erp.lib.form.SFormValidation formValidate() {
        SFormValidation validation = new SFormValidation();

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
        moDpsEntryNotes = (SDataDpsEntryNotes) registry;

        moFieldNotes.setFieldValue(moDpsEntryNotes.getNotes());
        moFieldIsAllDocs.setFieldValue(moDpsEntryNotes.getIsAllDocs());
        moFieldIsPrintable.setFieldValue(moDpsEntryNotes.getIsPrintable());
        moFieldIsCfd.setFieldValue(moDpsEntryNotes.getIsCfd());
        moFieldIsDeleted.setFieldValue(moDpsEntryNotes.getIsDeleted());

        jckIsDeleted.setEnabled(true);
    }

    @Override
    public erp.lib.data.SDataRegistry getRegistry() {
        if (moDpsEntryNotes == null) {
            moDpsEntryNotes = new SDataDpsEntryNotes();
            moDpsEntryNotes.setFkUserNewId(miClient.getSession().getUser().getPkUserId());
        }
        else {
            moDpsEntryNotes.setIsRegistryEdited(true);
            moDpsEntryNotes.setFkUserEditId(miClient.getSession().getUser().getPkUserId());
        }

        moDpsEntryNotes.setNotes(moFieldNotes.getString());
        moDpsEntryNotes.setIsAllDocs(moFieldIsAllDocs.getBoolean());
        moDpsEntryNotes.setIsPrintable(moFieldIsPrintable.getBoolean());
        moDpsEntryNotes.setIsCfd(moFieldIsCfd.getBoolean());
        moDpsEntryNotes.setIsDeleted(moFieldIsDeleted.getBoolean());

        return moDpsEntryNotes;
    }

    @Override
    public void setValue(int type, java.lang.Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public java.lang.Object getValue(int type) {
        throw new UnsupportedOperationException("Not supported yet.");
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
