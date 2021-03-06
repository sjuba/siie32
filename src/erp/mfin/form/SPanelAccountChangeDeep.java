/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SPanelAccountChangeDeep.java
 *
 * Created on 31/03/2015
 */

package erp.mfin.form;

import erp.data.SDataUtilities;
import erp.gui.account.SAccountConsts;
import erp.gui.account.SAccountUtils;
import erp.mfin.data.SDataAccount;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import sa.lib.SLibUtils;
import sa.lib.gui.bean.SBeanFieldText;

/**
 *
 * @author Irving Sánchez
 */
public class SPanelAccountChangeDeep extends javax.swing.JPanel implements java.awt.event.ActionListener, java.awt.event.KeyListener {

    private erp.client.SClientInterface miClient;

    private erp.mfin.data.SDataAccount moAccount;
    private erp.mfin.data.SDataAccount moAccountMajor;
    private java.lang.String msEmptyAccountId;
    private java.util.Vector<Integer> mvAccountLevels;
    private SBeanFieldText[] maoTextCodeLevelStds;
    /**
     * Creates new form SPanelAccountChangeDeep
     *
     * @param client Client interface.
     */
    public SPanelAccountChangeDeep(erp.client.SClientInterface client) throws java.lang.Exception {
        miClient = client;
        initComponents();
        initComponentsExtra();
        
        msEmptyAccountId = SDataUtilities.createNewFormattedAccountId(miClient, miClient.getSessionXXX().getParamsErp().getDeepAccounts());
        mvAccountLevels = SDataUtilities.getAccountLevels(msEmptyAccountId);
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
        jPanel2 = new javax.swing.JPanel();
        jlAccountId = new javax.swing.JLabel();
        moTextCodeLevel1 = new sa.lib.gui.bean.SBeanFieldText();
        moTextCodeLevel2 = new sa.lib.gui.bean.SBeanFieldText();
        jPanel3 = new javax.swing.JPanel();
        jlAccount = new javax.swing.JLabel();
        jtfAccount = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 1, 0, 1));

        jPanel2.setLayout(new java.awt.FlowLayout(0, 2, 0));

        jlAccountId.setForeground(java.awt.Color.blue);
        jlAccountId.setText("No. cuenta contable:*");
        jlAccountId.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel2.add(jlAccountId);

        moTextCodeLevel1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        moTextCodeLevel1.setText("000000");
        moTextCodeLevel1.setName("Code1"); // NOI18N
        moTextCodeLevel1.setPreferredSize(new java.awt.Dimension(45, 23));
        jPanel2.add(moTextCodeLevel1);

        moTextCodeLevel2.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        moTextCodeLevel2.setText("0");
        moTextCodeLevel2.setName("Code2"); // NOI18N
        moTextCodeLevel2.setPreferredSize(new java.awt.Dimension(45, 23));
        jPanel2.add(moTextCodeLevel2);

        jPanel1.add(jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 2, 0, 0));
        jPanel3.setLayout(new java.awt.BorderLayout(2, 0));

        jlAccount.setText("Cuenta contable:");
        jlAccount.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel3.add(jlAccount, java.awt.BorderLayout.WEST);

        jtfAccount.setEditable(false);
        jtfAccount.setText("MAJOR ACCOUNT");
        jtfAccount.setFocusable(false);
        jtfAccount.setPreferredSize(new java.awt.Dimension(175, 23));
        jPanel3.add(jtfAccount, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void initComponentsExtra() {
        maoTextCodeLevelStds = new SBeanFieldText[SAccountConsts.LEVELS];
        
        maoTextCodeLevelStds[0] = moTextCodeLevel1;
        maoTextCodeLevelStds[1] = moTextCodeLevel2;
        maoTextCodeLevelStds[2] = new SBeanFieldText();
        maoTextCodeLevelStds[3] = new SBeanFieldText();
        maoTextCodeLevelStds[4] = new SBeanFieldText();
        maoTextCodeLevelStds[5] = new SBeanFieldText();
        maoTextCodeLevelStds[6] = new SBeanFieldText();
        maoTextCodeLevelStds[7] = new SBeanFieldText();
        
        maoTextCodeLevelStds[1].addActionListener(this);
        maoTextCodeLevelStds[1].addKeyListener(this);
        
        maoTextCodeLevelStds[1].setMaxLength(4);
        
        maoTextCodeLevelStds[0].setEditable(false);
    }

    private void keyReleased(SBeanFieldText fieldText) {
        computeActionPerformed(fieldText);
    }
    
    private void computeActionPerformed(SBeanFieldText fieldText) {
        int position = 0;
        DecimalFormat decimalFormat = new DecimalFormat(SLibUtils.textRepeat("0", SAccountUtils.getDigits(miClient.getSessionXXX().getParamsCompany().getMaskAccount(), 1)));
        
        if (fieldText.getValue().length() == decimalFormat.getMinimumIntegerDigits()) {
            position = fieldText.getCaretPosition();
            fieldText.setValue(decimalFormat.format(SLibUtils.parseInt(fieldText.getValue())));
            fieldText.setCaretPosition(position);
        }
    }

    private void updateAccount() {
        Object[] accountsAndDescription = null;
        
        moAccount = null;

        jtfAccount.setText("");

        if (msEmptyAccountId.compareTo(maoTextCodeLevelStds[0].getText()) != 0) {
            if(moAccountMajor != null) {
                accountsAndDescription = SDataUtilities.getInputAccountsAndDescription(miClient, moAccountMajor.getPkAccountIdXXX(), mvAccountLevels);
                moAccountMajor = (SDataAccount) accountsAndDescription[0];
                moAccount = (SDataAccount) accountsAndDescription[1];
                jtfAccount.setText((String) accountsAndDescription[2]);
                moTextCodeLevel1.setValue(SAccountUtils.subtractCodeUsr(moAccountMajor.getPkAccountIdXXX(), 1));            
            }           
        }
        getStringAccountId();

        jtfAccount.setCaretPosition(0);
        jtfAccount.setToolTipText(jtfAccount.getText().length() == 0 ? null : jtfAccount.getText());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jlAccount;
    private javax.swing.JLabel jlAccountId;
    private javax.swing.JTextField jtfAccount;
    private sa.lib.gui.bean.SBeanFieldText moTextCodeLevel1;
    private sa.lib.gui.bean.SBeanFieldText moTextCodeLevel2;
    // End of variables declaration//GEN-END:variables
    
    public void setDataAccountMajor(erp.mfin.data.SDataAccount o) { 
        moAccountMajor = o; 
        updateAccount();
    }
    
    public erp.mfin.data.SDataAccount getDataAccountMajor() { return moAccountMajor; }
    public java.lang.String getEmptyAccountId() { return msEmptyAccountId; }
    public javax.swing.JLabel getFieldAccountLabel() { return jlAccountId; }
    public SBeanFieldText getFieldAccountNumber1() { return moTextCodeLevel1; }
    public SBeanFieldText getFieldAccountNumber2() { return moTextCodeLevel2; }

    public void refreshPanel() {
        updateAccount();
    }
    public void resetPanel() {
        moTextCodeLevel2.setText("0");
    }

    /**
     * SPanelAccount has two SDataAccount objects:
     *
     * a) account,
     * b) major account.
     *
     * In some cases, real input is done at major account if deep of this account is one.
     * @return If exists, account, otherwise major account.
     */
    public erp.mfin.data.SDataAccount getCurrentInputAccount() {
        return moAccount != null ? moAccount : moAccountMajor;
    }
    
    public java.lang.String getStringAccountId() {
        return SAccountUtils.convertCodeUsr(miClient.getSessionXXX().getParamsCompany().getMaskAccount(), SAccountUtils.composeCodeStd(maoTextCodeLevelStds));
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof SBeanFieldText) {
            computeActionPerformed((SBeanFieldText) evt.getSource());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent evt) {
        if (evt.getSource() instanceof SBeanFieldText) {
            keyReleased((SBeanFieldText) evt.getSource());
        }
    }
}
