/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package erp.mfin.form;

import erp.data.SDataConstants;
import erp.data.SDataConstantsSys;
import erp.data.SDataReadDescriptions;
import erp.data.SDataUtilities;
import erp.lib.SLibConstants;
import erp.lib.SLibUtilities;
import erp.lib.form.SFormComponentItem;
import erp.lib.form.SFormField;
import erp.lib.form.SFormUtilities;
import erp.lib.form.SFormValidation;
import erp.mfin.data.SDataAccount;
import erp.mfin.data.SDataAccountItemEntry;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JButton;

/**
 *
 * @author  Sergio Flores
 */
public class SFormAccountItemEntry extends javax.swing.JDialog implements erp.lib.form.SFormInterface, java.awt.event.ActionListener {

    private int mnFormType;
    private int mnFormResult;
    private int mnFormStatus;
    private boolean mbFirstTime;
    private boolean mbResetingForm;
    private java.util.Vector<erp.lib.form.SFormField> mvFields;
    private erp.client.SClientInterface miClient;

    private erp.mfin.data.SDataAccountItemEntry moAccountItemEntry;
    private erp.lib.form.SFormField moFieldPercentage;
    private erp.lib.form.SFormField moFieldFkBookkeepingRegistryTypeId;
    private erp.mfin.form.SPanelAccount moPanelFkAccountId_n;
    private erp.mfin.form.SPanelAccount moPanelFkCostCenterId_n;

    /** Creates new form SFormAccountItemEntry */
    public SFormAccountItemEntry(erp.client.SClientInterface client) {
        super(client.getFrame(), true);
        miClient = client;
        mnFormType = SDataConstants.FIN_ACC_ITEM_ETY;
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

        jpRegistry = new javax.swing.JPanel();
        jpAccounts = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jlAccountItem = new javax.swing.JLabel();
        jtfAccountItem = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jlAccountItemType = new javax.swing.JLabel();
        jtfAccountItemType = new javax.swing.JTextField();
        jpAccountsConfig = new javax.swing.JPanel();
        jpPanelAccounts = new javax.swing.JPanel();
        jlDummyAccount_n = new javax.swing.JLabel();
        jlDummyCostCenter_n = new javax.swing.JLabel();
        jpConfig = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlDummy = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jlFkBookkeepingRegistryTypeId = new javax.swing.JLabel();
        jcbFkBookkeepingRegistryTypeId = new javax.swing.JComboBox<SFormComponentItem>();
        jPanel8 = new javax.swing.JPanel();
        jlPercentage = new javax.swing.JLabel();
        jtfPercentage = new javax.swing.JTextField();
        jpControls = new javax.swing.JPanel();
        jbOk = new javax.swing.JButton();
        jbCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalle de cuentas contables para ítems"); // NOI18N
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jpRegistry.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del registro:"));
        jpRegistry.setLayout(new java.awt.BorderLayout(0, 1));

        jpAccounts.setLayout(new java.awt.GridLayout(3, 1, 0, 1));

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 0));

        jlAccountItem.setText("Cuentas contables:");
        jlAccountItem.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel4.add(jlAccountItem);

        jtfAccountItem.setEditable(false);
        jtfAccountItem.setText("ACCOUNTS");
        jtfAccountItem.setFocusable(false);
        jtfAccountItem.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel4.add(jtfAccountItem);

        jpAccounts.add(jPanel4);

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 0));

        jlAccountItemType.setText("Tipo de cuentas contables:");
        jlAccountItemType.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel5.add(jlAccountItemType);

        jtfAccountItemType.setEditable(false);
        jtfAccountItemType.setText("ACCOUNTS TYPE");
        jtfAccountItemType.setFocusable(false);
        jtfAccountItemType.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel5.add(jtfAccountItemType);

        jpAccounts.add(jPanel5);

        jpRegistry.add(jpAccounts, java.awt.BorderLayout.NORTH);

        jpAccountsConfig.setLayout(new java.awt.BorderLayout(0, 1));

        jpPanelAccounts.setLayout(new java.awt.GridLayout(2, 1, 0, 1));

        jlDummyAccount_n.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlDummyAccount_n.setText("[Panel cuenta contable]");
        jlDummyAccount_n.setPreferredSize(new java.awt.Dimension(100, 50));
        jpPanelAccounts.add(jlDummyAccount_n);

        jlDummyCostCenter_n.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlDummyCostCenter_n.setText("[Panel centro costo]");
        jlDummyCostCenter_n.setPreferredSize(new java.awt.Dimension(100, 50));
        jpPanelAccounts.add(jlDummyCostCenter_n);

        jpAccountsConfig.add(jpPanelAccounts, java.awt.BorderLayout.PAGE_START);

        jpConfig.setLayout(new java.awt.BorderLayout(0, 1));

        jPanel1.setLayout(new java.awt.GridLayout(3, 1, 0, 1));
        jPanel1.add(jlDummy);

        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 0));

        jlFkBookkeepingRegistryTypeId.setText("Tipo de asiento contable: *");
        jlFkBookkeepingRegistryTypeId.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel9.add(jlFkBookkeepingRegistryTypeId);

        jcbFkBookkeepingRegistryTypeId.setEnabled(false);
        jcbFkBookkeepingRegistryTypeId.setPreferredSize(new java.awt.Dimension(300, 23));
        jPanel9.add(jcbFkBookkeepingRegistryTypeId);

        jPanel1.add(jPanel9);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 0));

        jlPercentage.setText("Porcentaje de asignación: *");
        jlPercentage.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel8.add(jlPercentage);

        jtfPercentage.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfPercentage.setText("0.00 %");
        jtfPercentage.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jtfPercentage);

        jPanel1.add(jPanel8);

        jpConfig.add(jPanel1, java.awt.BorderLayout.NORTH);

        jpAccountsConfig.add(jpConfig, java.awt.BorderLayout.CENTER);

        jpRegistry.add(jpAccountsConfig, java.awt.BorderLayout.CENTER);

        getContentPane().add(jpRegistry, java.awt.BorderLayout.CENTER);

        jpControls.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jbOk.setText("Aceptar"); // NOI18N
        jbOk.setToolTipText("[Ctrl + Enter]");
        jbOk.setPreferredSize(new java.awt.Dimension(75, 23));
        jpControls.add(jbOk);

        jbCancel.setText("Cancelar"); // NOI18N
        jbCancel.setToolTipText("[Escape]");
        jpControls.add(jbCancel);

        getContentPane().add(jpControls, java.awt.BorderLayout.PAGE_END);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 400);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        windowActivated();
    }//GEN-LAST:event_formWindowActivated

    private void initComponentsExtra() {
        try {
            moPanelFkAccountId_n = new SPanelAccount(miClient, SDataConstants.FIN_ACC, false, false, false);
            moPanelFkCostCenterId_n = new SPanelAccount(miClient, SDataConstants.FIN_CC, false, false, false);
        }
        catch (Exception e) {
            SLibUtilities.renderException(this, e);
        }

        jpPanelAccounts.remove(jlDummyAccount_n);
        jpPanelAccounts.remove(jlDummyCostCenter_n);
        jpPanelAccounts.add(moPanelFkAccountId_n);
        jpPanelAccounts.add(moPanelFkCostCenterId_n);

        moFieldPercentage = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, true, jtfPercentage, jlPercentage);
        moFieldPercentage.setIsPercent(true);
        moFieldPercentage.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsPercentageFormat());
        moFieldFkBookkeepingRegistryTypeId = new SFormField(miClient, SLibConstants.DATA_TYPE_KEY, true, jcbFkBookkeepingRegistryTypeId, jlFkBookkeepingRegistryTypeId);

        mvFields = new Vector<SFormField>();
        mvFields.add(moFieldPercentage);
        mvFields.add(moFieldFkBookkeepingRegistryTypeId);

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
            if (moPanelFkAccountId_n.getFieldAccount().getComponent().isEnabled()) {
                moPanelFkAccountId_n.getFieldAccount().getComponent().requestFocus();
            }
            else if (jcbFkBookkeepingRegistryTypeId.isEnabled()) {
                jcbFkBookkeepingRegistryTypeId.requestFocus();
            }
            else {
                jtfPercentage.requestFocus();
            }
        }
    }

    private void actionEdit(boolean edit) {

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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbOk;
    private javax.swing.JComboBox<SFormComponentItem> jcbFkBookkeepingRegistryTypeId;
    private javax.swing.JLabel jlAccountItem;
    private javax.swing.JLabel jlAccountItemType;
    private javax.swing.JLabel jlDummy;
    private javax.swing.JLabel jlDummyAccount_n;
    private javax.swing.JLabel jlDummyCostCenter_n;
    private javax.swing.JLabel jlFkBookkeepingRegistryTypeId;
    private javax.swing.JLabel jlPercentage;
    private javax.swing.JPanel jpAccounts;
    private javax.swing.JPanel jpAccountsConfig;
    private javax.swing.JPanel jpConfig;
    private javax.swing.JPanel jpControls;
    private javax.swing.JPanel jpPanelAccounts;
    private javax.swing.JPanel jpRegistry;
    private javax.swing.JTextField jtfAccountItem;
    private javax.swing.JTextField jtfAccountItemType;
    private javax.swing.JTextField jtfPercentage;
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

        moAccountItemEntry = null;

        jtfAccountItem.setText("");
        jtfAccountItemType.setText("");

        moPanelFkAccountId_n.resetPanel();
        moPanelFkCostCenterId_n.resetPanel();
        moFieldFkBookkeepingRegistryTypeId.setFieldValue(new int[] { SDataConstantsSys.FINS_TP_BKR_ALL });
    }

    @Override
    public void formRefreshCatalogues() {
        SFormUtilities.populateComboBox(miClient, jcbFkBookkeepingRegistryTypeId, SDataConstants.FINS_TP_BKR);
    }

    @Override
    public erp.lib.form.SFormValidation formValidate() {
        int nSystemTypeId = SDataConstantsSys.FINS_TP_ACC_SYS_NA;
        String message = "";
        SDataAccount account = null;
        SFormValidation validation = new SFormValidation();

        for (int i = 0; i < mvFields.size(); i++) {
            if (!((erp.lib.form.SFormField) mvFields.get(i)).validateField()) {
                validation.setIsError(true);
                validation.setComponent(mvFields.get(i).getComponent());
                break;
            }
        }

        if (!validation.getIsError()) {
            // Additional validation:

            if (!moPanelFkAccountId_n.isEmptyAccountId()) {
                // Account has been specified and must be validated:

                account = moPanelFkAccountId_n.getCurrentInputAccount();
                message = SDataUtilities.validateAccount(miClient, account, null);

                if (message.length() > 0) {
                    validation.setMessage(message);
                    validation.setComponent(moPanelFkAccountId_n.getFieldAccount().getComponent());
                }
                else {
                    // Validate account system type:

                    if (account.getLevel() == 1) {
                        nSystemTypeId = account.getFkAccountSystemTypeId();
                    }
                    else {
                        nSystemTypeId = moPanelFkAccountId_n.getDataAccountMajor().getFkAccountSystemTypeId();
                    }

                    switch (moAccountItemEntry.getPkAccountItemTypeId()) {
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_ASSET:
                            if (nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_INV &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_ASSET_FIX) {
                                message = "'" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_INV }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_ASSET_FIX }) + "'";
                            }
                            break;

                        case SDataConstantsSys.FINS_TP_ACC_ITEM_INV_WAR_PUR:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_INV_WAR_SAL:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_INV_CONSIG_PUR:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_INV_CONSIG_SAL:
                            if (nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_INV) {
                                message = "'" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_INV }) + "'";
                            }
                            break;

                        case SDataConstantsSys.FINS_TP_ACC_ITEM_INV_MFG_CO:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_INV_MFG_BP:
                            if (nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_INV) {
                                message = "'" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_INV }) + "'";
                            }
                            break;

                        case SDataConstantsSys.FINS_TP_ACC_ITEM_PUR:
                            if (nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_INV &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_SUP &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_PUR &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_ASSET_FIX) {
                                message = "'" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_INV }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_SUP }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_PUR }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_ASSET_FIX }) + "'";
                            }
                            break;

                        case SDataConstantsSys.FINS_TP_ACC_ITEM_PUR_ADJ_DEV:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_PUR_ADJ_DISC:
                            if (nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_INV &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_SUP &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_PUR &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_PUR_ADJ &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_ASSET_FIX) {
                                message = "'" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_INV }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_SUP }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_PUR }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_PUR_ADJ }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_ASSET_FIX }) + "'";
                            }
                            break;

                        case SDataConstantsSys.FINS_TP_ACC_ITEM_SAL:
                            if (nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_CUS &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_SAL &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_PUR) {
                                message = "'" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_CUS }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_SAL }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_PUR }) + "'";
                            }
                            break;

                        case SDataConstantsSys.FINS_TP_ACC_ITEM_SAL_ADJ_DEV:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_SAL_ADJ_DISC:
                            if (nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_CUS &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_SAL &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_SAL_ADJ &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_PUR &&
                                nSystemTypeId != SDataConstantsSys.FINS_TP_ACC_SYS_PUR_ADJ) {
                                message = "'" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_CUS }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_SAL }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_SAL_ADJ }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_PUR }) + "'";
                                message += " o '" + SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_ACC_SYS, new int[] { SDataConstantsSys.FINS_TP_ACC_SYS_PUR_ADJ }) + "'";
                            }
                            break;

                        case SDataConstantsSys.FINS_TP_ACC_ITEM_COGS:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_INV_OPEN:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_INV_END:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_ADJ_INC:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_ADJ_DEC:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_CHG_PUR:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_CHG_SAL:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_MFG_CO:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_MFG_BP:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_WAR_PUR:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_WAR_SAL:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_CONSIG_PUR:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_CONSIG_SAL:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_DEPREC:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_DEPREC_REC:
                        case SDataConstantsSys.FINS_TP_ACC_ITEM_DEPREC_EXP:
                            break;

                        default:
                            message = SLibConstants.MSG_ERR_UTIL_UNKNOWN_OPTION;
                    }

                    if (message.length() > 0) {
                        validation.setMessage("El tipo de cuenta de sistema de la cuenta contable deber ser:\n" + message + ".");
                        validation.setComponent(moPanelFkAccountId_n.getFieldAccount().getComponent());
                    }
                    else {
                        if (!moPanelFkCostCenterId_n.isEmptyAccountId()) {
                            // Cost center has been specified and must be validated:

                            message = SDataUtilities.validateCostCenter(miClient, moPanelFkCostCenterId_n.getCurrentInputCostCenter(), null);

                            if (message.length() > 0) {
                                validation.setMessage(message);
                                validation.setComponent(moPanelFkCostCenterId_n.getFieldAccount().getComponent());
                            }
                        }
                    }
                }
            }
            else if (!moPanelFkCostCenterId_n.isEmptyAccountId()) {
                validation.setMessage("Si no se especificó cuenta contable, tampoco se debe especificar centro de costo.");
                validation.setComponent(moPanelFkCostCenterId_n.getFieldAccount().getComponent());
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
        moAccountItemEntry = (SDataAccountItemEntry) registry;

        jtfAccountItem.setText(moAccountItemEntry.getDbmsAccountItem());
        jtfAccountItemType.setText(moAccountItemEntry.getDbmsAccountItemType());

        moFieldPercentage.setFieldValue(moAccountItemEntry.getPercentage());
        moFieldFkBookkeepingRegistryTypeId.setFieldValue(new int[] { moAccountItemEntry.getFkBookkeepingRegistryTypeId() });
        moPanelFkAccountId_n.getFieldAccount().setFieldValue(moAccountItemEntry.getFkAccountId_n().length() == 0 ? moPanelFkAccountId_n.getEmptyAccountId() : moAccountItemEntry.getFkAccountId_n());
        moPanelFkCostCenterId_n.getFieldAccount().setFieldValue(moAccountItemEntry.getFkCostCenterId_n().length() == 0 ? moPanelFkCostCenterId_n.getEmptyAccountId() : moAccountItemEntry.getFkCostCenterId_n());

        moPanelFkAccountId_n.refreshPanel();
        moPanelFkCostCenterId_n.refreshPanel();
    }

    @Override
    public erp.lib.data.SDataRegistry getRegistry() {
        if (moAccountItemEntry == null) {
            miClient.showMsgBoxWarning(SLibConstants.MSG_ERR_GUI_FORM_EDIT_ONLY);
        }
        else {
            moAccountItemEntry.setPercentage(moFieldPercentage.getDouble());
            moAccountItemEntry.setFkAccountId_n(moPanelFkAccountId_n.getFieldAccount().getString());
            moAccountItemEntry.setFkCostCenterId_n(moPanelFkCostCenterId_n.getFieldAccount().getString());
            moAccountItemEntry.setFkBookkeepingRegistryTypeId(moFieldFkBookkeepingRegistryTypeId.getKeyAsIntArray()[0]);

            moAccountItemEntry.setDbmsAccount_n(SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FIN_ACC, new Object[] { moAccountItemEntry.getFkAccountId_n() }));
            moAccountItemEntry.setDbmsCostCenter_n(SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FIN_CC, new Object[] { moAccountItemEntry.getFkCostCenterId_n() }));
            moAccountItemEntry.setDbmsBookkeepingRegistryType(SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINS_TP_BKR, new int[] { moAccountItemEntry.getFkBookkeepingRegistryTypeId() }));
        }

        return moAccountItemEntry;
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
            JButton button = (JButton) e.getSource();

            if (button == jbOk) {
                actionOk();
            }
            else if (button == jbCancel) {
                actionCancel();
            }
        }
    }
}
