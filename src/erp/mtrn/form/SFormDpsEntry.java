/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package erp.mtrn.form;

import erp.data.SDataConstants;
import erp.data.SDataConstantsSys;
import erp.data.SDataReadDescriptions;
import erp.data.SDataUtilities;
import erp.data.SProcConstants;
import erp.form.SFormOptionPicker;
import erp.gui.session.SSessionCustom;
import erp.lib.SLibConstants;
import erp.lib.SLibTimeUtilities;
import erp.lib.SLibUtilities;
import erp.lib.form.SFormComponentItem;
import erp.lib.form.SFormField;
import erp.lib.form.SFormUtilities;
import erp.lib.form.SFormValidation;
import erp.lib.table.STableColumnForm;
import erp.lib.table.STableConstants;
import erp.lib.table.STablePane;
import erp.lib.table.STablePaneGrid;
import erp.lib.table.STableRow;
import erp.mbps.data.SDataBizPartner;
import erp.mbps.data.SDataBizPartnerBranch;
import erp.mfin.form.SPanelAccount;
import erp.mitm.data.SDataItem;
import erp.mitm.data.SDataItemBizPartnerDescription;
import erp.mitm.data.SDataItemForeignLanguage;
import erp.mitm.data.SDataUnitType;
import erp.mod.SModConsts;
import erp.mod.SModSysConsts;
import erp.mod.itm.db.SItmConsts;
import erp.mod.trn.db.STrnUtils;
import erp.mtrn.data.SDataDps;
import erp.mtrn.data.SDataDpsDpsAdjustment;
import erp.mtrn.data.SDataDpsDpsLink;
import erp.mtrn.data.SDataDpsEntry;
import erp.mtrn.data.SDataDpsEntryCommissions;
import erp.mtrn.data.SDataDpsEntryCommissionsRow;
import erp.mtrn.data.SDataDpsEntryNotes;
import erp.mtrn.data.SDataDpsEntryNotesRow;
import erp.mtrn.data.SDataDpsEntryPrice;
import erp.mtrn.data.SDataDpsEntryPriceRow;
import erp.mtrn.data.SDataDpsEntryTax;
import erp.mtrn.data.SDataDpsEntryTaxRow;
import erp.mtrn.data.STrnUtilities;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import sa.lib.gui.SGuiConsts;

/**
 *
 * @author  Sergio Flores
 */
public class SFormDpsEntry extends javax.swing.JDialog implements erp.lib.form.SFormInterface, java.awt.event.ActionListener, java.awt.event.FocusListener, java.awt.event.ItemListener, javax.swing.event.CellEditorListener {

    private int mnFormType;
    private int mnFormResult;
    private int mnFormStatus;
    private boolean mbFirstTime;
    private boolean mbResetingForm;
    private boolean mbUpdatingForm;
    private boolean mbIsDoc;
    private boolean mbIsSales;
    private boolean mbIsAdj;
    private boolean mbIsCon;
    private java.util.Vector<SFormField> mvFields;
    private erp.client.SClientInterface miClient;

    private erp.mtrn.data.SDataDpsEntry moDpsEntry;
    private erp.mitm.data.SDataItem moItem;
    private erp.lib.form.SFormField moFieldFkItemId;
    private erp.lib.form.SFormField moFieldKey;
    private erp.lib.form.SFormField moFieldConcept;
    private erp.lib.form.SFormField moFieldFkOriginalUnitId;
    private erp.lib.form.SFormField moFieldIsDiscountDocApplying;
    private erp.lib.form.SFormField moFieldIsDiscountUnitaryPercentage;
    private erp.lib.form.SFormField moFieldDiscountUnitaryPercentage;
    private erp.lib.form.SFormField moFieldIsDiscountEntryPercentage;
    private erp.lib.form.SFormField moFieldDiscountEntryPercentage;
    private erp.lib.form.SFormField moFieldOriginalQuantity;
    private erp.lib.form.SFormField moFieldOriginalPriceUnitaryCy;
    private erp.lib.form.SFormField moFieldOriginalDiscountUnitaryCy;
    private erp.lib.form.SFormField moFieldDiscountEntryCy;
    private erp.lib.form.SFormField moFieldDiscountDocCy;
    private erp.lib.form.SFormField moFieldSurplusPercentage;
    private erp.lib.form.SFormField moFieldIsDpsPriceVariable;
    
    private erp.lib.form.SFormField moFieldDpsContractBase;
    private erp.lib.form.SFormField moFieldDpsContractFuture;
    private erp.lib.form.SFormField moFieldDpsContractFactor;
    
    private erp.lib.form.SFormField moFieldContractPriceReferenceNumbrer;
    private erp.lib.form.SFormField moFieldContractPriceYear;
    private erp.lib.form.SFormField moFieldContractPriceMonth;
    private erp.lib.form.SFormField moFieldPriceOriginalQuantity;
    private erp.lib.form.SFormField moFieldPriceOriginalPriceUnitaryCy;
    private erp.lib.form.SFormField moFieldContractBase;
    private erp.lib.form.SFormField moFieldContractFuture;
    private erp.lib.form.SFormField moFieldContractFactor;   
    
    private erp.lib.form.SFormField moFieldFkVehicleTypeId_n;
    private erp.lib.form.SFormField moFieldVehicleNumber;
    private erp.lib.form.SFormField moFieldSecuritySeal;
    private erp.lib.form.SFormField moFieldTicket;
    private erp.lib.form.SFormField moFieldLength;
    private erp.lib.form.SFormField moFieldSurface;
    private erp.lib.form.SFormField moFieldVolume;
    private erp.lib.form.SFormField moFieldMass;
    private erp.lib.form.SFormField moFieldWeightGross;
    private erp.lib.form.SFormField moFieldWeightDelivery;
    private erp.lib.form.SFormField moFieldIsDiscountRetailChain;
    private erp.lib.form.SFormField moFieldIsTaxesAutomaticApplying;
    private erp.lib.form.SFormField moFieldIsInventoriable;
    private erp.lib.form.SFormField moFieldIsDeleted;
    private erp.lib.form.SFormField moFieldFkTaxRegionId;
    private erp.lib.form.SFormField moFieldFkItemReferenceId_n;
    private erp.lib.form.SFormField moFieldReference;
    private erp.lib.form.SFormField moFieldAddendaNumberPosition;
    private erp.lib.form.SFormField moFieldAddendaCenter;
    private erp.lib.form.SFormField moFieldAddendaEntryNumber;
    private erp.lib.form.SFormField moFieldAddendaFkBarcode;
    private erp.lib.form.SFormField moFieldAddendaOrder;
    private erp.lib.form.SFormField moFieldAddendaCages;
    private erp.lib.form.SFormField moFieldAddendaCagePriceUnitary;
    private erp.lib.form.SFormField moFieldAddendaParts;
    private erp.lib.form.SFormField moFieldAddendaPartPriceUnitary;    

    private int mnAuxCurrentUnitTypeId;
    private int mnAuxCurrentUnitAlternativeTypeId;
    private double mdAuxCurrentValue;
    private Object moAuxCurrentUnitKey;
    private erp.lib.table.STablePane moPaneTaxes;
    private erp.lib.table.STablePane moPaneCommissions;
    private erp.lib.table.STablePaneGrid moPaneGridNotes;
    private erp.lib.table.STablePaneGrid moPaneGridPrices;
    private erp.mtrn.form.SDialogPriceUnitaryWizard moDialogPriceUnitaryWizard;
    private erp.mtrn.form.SDialogItemPriceHistory moDialogItemPriceHistory;
    private erp.mtrn.form.SFormDpsEntryNotes moFormNotes;
    private erp.mfin.form.SPanelAccount moPanelFkCostCenterId_n;

    private erp.mtrn.data.SDataDps moParamDps;
    private erp.mbps.data.SDataBizPartner moParamBizPartner;
    private erp.mbps.data.SDataBizPartnerBranch moParamBizPartnerBranch;
    private erp.mitm.data.SDataItemBizPartnerDescription moDataItemBizPartnerDescription;
    private int mnParamAdjustmentTypeId;

    private int[] manItemClassFilterKey;

    private final int COL_TAX_CUR = 5;   // column index for amount in taxes pane

    private boolean mbEnableDataAddenda;
    private boolean mbRightPriceListForPurchases;
    private boolean mbRightPriceListForSales;
    private boolean mbAllowDiscount;
    private int mnPricePolicyForPurchases;
    private int mnPricePolicyForSales;
    private double mdQuantitySrcOrig;
    private double mdQuantityDesOrig;
    private double mdQuantityPrc;
    private boolean mbIsLastPrc;
    private erp.mmkt.data.SParamsItemPriceList moParamsItemPriceList;
    
    private int mnAuxPriceEditIndex;
    private SDataDpsEntryPrice moAuxPriceEdit;

    /** Creates new form DFormDpsEntry */
    public SFormDpsEntry(erp.client.SClientInterface client) {
        super(client.getFrame(), true);
        miClient = client;
        mnFormType = SDataConstants.TRN_DPS_ETY;

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
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jlFkItemId = new javax.swing.JLabel();
        jcbFkItemId = new javax.swing.JComboBox<SFormComponentItem>();
        jbFkItemId = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jlKey = new javax.swing.JLabel();
        jtfKey = new javax.swing.JTextField();
        jbKey = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jlConcept = new javax.swing.JLabel();
        jtfConcept = new javax.swing.JTextField();
        jbConcept = new javax.swing.JButton();
        jbItemBizPartnerDescription = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jlFkOriginalUnitId = new javax.swing.JLabel();
        jcbFkOriginalUnitId = new javax.swing.JComboBox<SFormComponentItem>();
        jbFkOriginalUnitId = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jckIsDiscountDocApplying = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jckIsInventoriable = new javax.swing.JCheckBox();
        jckIsBulk = new javax.swing.JCheckBox();
        jPanel16 = new javax.swing.JPanel();
        jckIsDiscountUnitaryPercentage = new javax.swing.JCheckBox();
        jtfDiscountUnitaryPercentage = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jckIsDiscountRetailChain = new javax.swing.JCheckBox();
        jPanel17 = new javax.swing.JPanel();
        jckIsDiscountEntryPercentage = new javax.swing.JCheckBox();
        jtfDiscountEntryPercentage = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jckIsDeleted = new javax.swing.JCheckBox();
        jPanel15 = new javax.swing.JPanel();
        jlOriginalQuantity = new javax.swing.JLabel();
        jtfOriginalQuantity = new javax.swing.JTextField();
        jtfOriginalUnitSymbolRo = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jlQuantity = new javax.swing.JLabel();
        jtfQuantityRo = new javax.swing.JTextField();
        jtfUnitSymbolRo = new javax.swing.JTextField();
        jckAuxPreserveQuantity = new javax.swing.JCheckBox();
        jPanel20 = new javax.swing.JPanel();
        jlOriginalPriceUnitaryCy = new javax.swing.JLabel();
        jtfOriginalPriceUnitaryCy = new javax.swing.JTextField();
        jtfCurrencyKeyPriceUnitaryCyRo = new javax.swing.JTextField();
        jbPriceUnitaryCyWizard = new javax.swing.JButton();
        jbPriceHistory = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jlOriginalDiscountUnitaryCy = new javax.swing.JLabel();
        jtfOriginalDiscountUnitaryCy = new javax.swing.JTextField();
        jtfCurrencyKeyDiscountUnitaryCyRo = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jlCurrency = new javax.swing.JLabel();
        jtfCurrencySystemKeyRo = new javax.swing.JTextField();
        jtfCurrencyKeyRo = new javax.swing.JTextField();
        jlPriceUnitary = new javax.swing.JLabel();
        jtfPriceUnitaryRo = new javax.swing.JTextField();
        jtfPriceUnitaryCyRo = new javax.swing.JTextField();
        jlDiscountUnitary = new javax.swing.JLabel();
        jtfDiscountUnitaryRo = new javax.swing.JTextField();
        jtfDiscountUnitaryCyRo = new javax.swing.JTextField();
        jlDiscountEntry = new javax.swing.JLabel();
        jtfDiscountEntryRo = new javax.swing.JTextField();
        jtfDiscountEntryCy = new javax.swing.JTextField();
        jlSubtotalProvisional_r = new javax.swing.JLabel();
        jtfSubtotalProvisional_rRo = new javax.swing.JTextField();
        jtfSubtotalProvisionalCy_rRo = new javax.swing.JTextField();
        jlDiscountDoc = new javax.swing.JLabel();
        jtfDiscountDocRo = new javax.swing.JTextField();
        jtfDiscountDocCy = new javax.swing.JTextField();
        jlSubtotal_r = new javax.swing.JLabel();
        jtfSubtotal_rRo = new javax.swing.JTextField();
        jtfSubtotalCy_rRo = new javax.swing.JTextField();
        jlTaxCharged_r = new javax.swing.JLabel();
        jtfTaxCharged_rRo = new javax.swing.JTextField();
        jtfTaxChargedCy_rRo = new javax.swing.JTextField();
        jlTaxRetained_r = new javax.swing.JLabel();
        jtfTaxRetained_rRo = new javax.swing.JTextField();
        jtfTaxRetainedCy_rRo = new javax.swing.JTextField();
        jlTotal_r = new javax.swing.JLabel();
        jtfTotal_rRo = new javax.swing.JTextField();
        jtfTotalCy_rRo = new javax.swing.JTextField();
        jlPriceUnitaryReal_r = new javax.swing.JLabel();
        jtfPriceUnitaryReal_rRo = new javax.swing.JTextField();
        jtfPriceUnitaryRealCy_rRo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jlFkItemReferenceId_n = new javax.swing.JLabel();
        jcbFkItemReferenceId_n = new javax.swing.JComboBox<SFormComponentItem>();
        jbFkItemReferenceId_n = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        jlReference = new javax.swing.JLabel();
        jtfReference = new javax.swing.JTextField();
        jpCostCenter = new javax.swing.JPanel();
        jlDummyCostCenter = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        jpTaxes = new javax.swing.JPanel();
        jpTaxInfo = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jlTaxIdentityEmisor = new javax.swing.JLabel();
        jtfTaxIdentityEmisorRo = new javax.swing.JTextField();
        jlTaxIdentityReceptor = new javax.swing.JLabel();
        jtfTaxIdentityReceptorRo = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jlFkTaxRegionId = new javax.swing.JLabel();
        jcbFkTaxRegionId = new javax.swing.JComboBox<SFormComponentItem>();
        jbFkTaxRegionId = new javax.swing.JButton();
        jPanel34 = new javax.swing.JPanel();
        jckIsTaxesAutomaticApplying = new javax.swing.JCheckBox();
        jpCommissions = new javax.swing.JPanel();
        jpExtraData = new javax.swing.JPanel();
        jpExtraDataContract = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jlFkVehicleTypeId_n = new javax.swing.JLabel();
        jcbFkVehicleTypeId_n = new javax.swing.JComboBox<SFormComponentItem>();
        jPanel43 = new javax.swing.JPanel();
        jlVehicleNumber = new javax.swing.JLabel();
        jtfVehicleNumber = new javax.swing.JTextField();
        jPanel45 = new javax.swing.JPanel();
        jlSecuritySeal = new javax.swing.JLabel();
        jtfSecuritySeal = new javax.swing.JTextField();
        jPanel47 = new javax.swing.JPanel();
        jlTicket = new javax.swing.JLabel();
        jtfTicket = new javax.swing.JTextField();
        jpExtraDataUnits = new javax.swing.JPanel();
        jpExtraDataUnitsNorth = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jlLength = new javax.swing.JLabel();
        jtfLength = new javax.swing.JTextField();
        jtfLengthUnitSymbolRo = new javax.swing.JTextField();
        jPanel28 = new javax.swing.JPanel();
        jlSurface = new javax.swing.JLabel();
        jtfSurface = new javax.swing.JTextField();
        jtfSurfaceUnitSymbolRo = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        jlVolume = new javax.swing.JLabel();
        jtfVolume = new javax.swing.JTextField();
        jtfVolumeUnitSymbolRo = new javax.swing.JTextField();
        jPanel30 = new javax.swing.JPanel();
        jlMass = new javax.swing.JLabel();
        jtfMass = new javax.swing.JTextField();
        jtfMassUnitSymbolRo = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        jlWeigthGross = new javax.swing.JLabel();
        jtfWeigthGrossRo = new javax.swing.JTextField();
        jtfWeigthGrossUnitSymbolRo = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        jlWeigthDelivery = new javax.swing.JLabel();
        jtfWeigthDeliveryRo = new javax.swing.JTextField();
        jtfWeigthDeliveryUnitSymbolRo = new javax.swing.JTextField();
        jpExtraDataOther = new javax.swing.JPanel();
        jpExtraDataOtherNorth = new javax.swing.JPanel();
        jpExtraDataOtherFillment = new javax.swing.JPanel();
        jckIsSurplusPercentageApplying = new javax.swing.JCheckBox();
        jtfSurplusPercentage = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPricesData = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jckIsDpsReqMonthDelivery = new javax.swing.JCheckBox();
        jlDpsContractBase = new javax.swing.JLabel();
        jtfDpsContractBase = new javax.swing.JTextField();
        jlDpsContractFuture = new javax.swing.JLabel();
        jtfDpsContractFuture = new javax.swing.JTextField();
        jlDpsContractFactor = new javax.swing.JLabel();
        jtfDpsContractFactor = new javax.swing.JTextField();
        jckIsPriceConfirm = new javax.swing.JCheckBox();
        jPanel51 = new javax.swing.JPanel();
        jPanel59 = new javax.swing.JPanel();
        jPanel67 = new javax.swing.JPanel();
        jlIsPriceVariable = new javax.swing.JLabel();
        jckIsDirectPrice = new javax.swing.JCheckBox();
        jPanel39 = new javax.swing.JPanel();
        jlContractPriceNumbrerReference = new javax.swing.JLabel();
        jtfContractPriceNumbrerReference = new javax.swing.JTextField();
        jPanel63 = new javax.swing.JPanel();
        jlContractPriceYear = new javax.swing.JLabel();
        jtfContractPriceYear = new javax.swing.JTextField();
        jPanel65 = new javax.swing.JPanel();
        jlContractPriceMonth = new javax.swing.JLabel();
        jtfContractPriceMonth = new javax.swing.JTextField();
        jPanel72 = new javax.swing.JPanel();
        jlPriceOriginalQuantity = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        jtfPriceOriginalQuantity = new javax.swing.JTextField();
        jtfPriceOriginalUnitSymbol = new javax.swing.JTextField();
        jPanel77 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jckChangePrice = new javax.swing.JCheckBox();
        jPanel73 = new javax.swing.JPanel();
        jlPriceOriginalPriceUnitaryCy = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        jtfPriceOriginalPriceUnitaryCy = new javax.swing.JTextField();
        jtfPriceCurrencyKeyPriceUnitaryCy = new javax.swing.JTextField();
        jPanel74 = new javax.swing.JPanel();
        jlContractBase = new javax.swing.JLabel();
        jtfContractBase = new javax.swing.JTextField();
        jPanel75 = new javax.swing.JPanel();
        jlContractFuture = new javax.swing.JLabel();
        jtfContractFuture = new javax.swing.JTextField();
        jPanel76 = new javax.swing.JPanel();
        jlContractFactor = new javax.swing.JLabel();
        jtfContractFactor = new javax.swing.JTextField();
        jPanel78 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jbPriceNew = new javax.swing.JButton();
        jPanel80 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jbPriceSave = new javax.swing.JButton();
        jPanel79 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jbClearPriceFields = new javax.swing.JButton();
        jpPrices = new javax.swing.JPanel();
        jpNotesControls1 = new javax.swing.JPanel();
        jbGridPriceNew = new javax.swing.JButton();
        jbGridPriceEdit = new javax.swing.JButton();
        jbGridPriceDelete = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jtbGridPriceFilter = new javax.swing.JToggleButton();
        jpNotes = new javax.swing.JPanel();
        jpNotesControls = new javax.swing.JPanel();
        jbNotesNew = new javax.swing.JButton();
        jbNotesEdit = new javax.swing.JButton();
        jbNotesDelete = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jtbNotesFilter = new javax.swing.JToggleButton();
        jbSystemNotes = new javax.swing.JButton();
        jpAddendaData = new javax.swing.JPanel();
        jPanel37 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jPanel68 = new javax.swing.JPanel();
        jlAddendaNumberPosition = new javax.swing.JLabel();
        jtfAddendaNumberPosition = new javax.swing.JTextField();
        jPanel69 = new javax.swing.JPanel();
        jlAddendaCenter = new javax.swing.JLabel();
        jtfAddendaCenter = new javax.swing.JTextField();
        jPanel36 = new javax.swing.JPanel();
        jlAddendaEntryNumber = new javax.swing.JLabel();
        jtfAddendaEntryNumber = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jlAddendaFkBarcode = new javax.swing.JLabel();
        jcbAddendaFkBarcode = new javax.swing.JComboBox<SFormComponentItem>();
        jPanel44 = new javax.swing.JPanel();
        jlAddendaOrder = new javax.swing.JLabel();
        jtfAddendaOrder = new javax.swing.JTextField();
        jPanel48 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jPanel70 = new javax.swing.JPanel();
        jlAddendaCages = new javax.swing.JLabel();
        jtfAddendaCages = new javax.swing.JTextField();
        jPanel71 = new javax.swing.JPanel();
        jlAddendaCagePriceUnitary = new javax.swing.JLabel();
        jtfAddendaCagePriceUnitary = new javax.swing.JTextField();
        jPanel50 = new javax.swing.JPanel();
        jlAddendaParts = new javax.swing.JLabel();
        jtfAddendaParts = new javax.swing.JTextField();
        jPanel52 = new javax.swing.JPanel();
        jlAddendaPartPriceUnitary = new javax.swing.JLabel();
        jtfAddendaPartPriceUnitary = new javax.swing.JTextField();
        jpControls = new javax.swing.JPanel();
        jbOk = new javax.swing.JButton();
        jbCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Partida de documento"); // NOI18N
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jpRegistry.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la partida:"));
        jPanel22.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridLayout(11, 1, 0, 1));

        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlFkItemId.setText("Ítem partida: *");
        jlFkItemId.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel6.add(jlFkItemId);

        jcbFkItemId.setMaximumRowCount(16);
        jcbFkItemId.setPreferredSize(new java.awt.Dimension(425, 23));
        jPanel6.add(jcbFkItemId);

        jbFkItemId.setText("...");
        jbFkItemId.setToolTipText("Seleccionar ítem");
        jbFkItemId.setFocusable(false);
        jbFkItemId.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel6.add(jbFkItemId);

        jPanel4.add(jPanel6);

        jPanel7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlKey.setText("Clave: *");
        jlKey.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel7.add(jlKey);

        jtfKey.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel7.add(jtfKey);

        jbKey.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_std_action.gif"))); // NOI18N
        jbKey.setToolTipText("Copiar clave del ítem");
        jbKey.setFocusable(false);
        jbKey.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel7.add(jbKey);

        jLabel8.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel7.add(jLabel8);

        jPanel4.add(jPanel7);

        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlConcept.setText("Concepto: *");
        jlConcept.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel8.add(jlConcept);

        jtfConcept.setPreferredSize(new java.awt.Dimension(425, 23));
        jPanel8.add(jtfConcept);

        jbConcept.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_std_action.gif"))); // NOI18N
        jbConcept.setToolTipText("Copiar concepto del ítem");
        jbConcept.setFocusable(false);
        jbConcept.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel8.add(jbConcept);

        jbItemBizPartnerDescription.setText("...");
        jbItemBizPartnerDescription.setToolTipText("Seleccionar descripción propia del asociado de negocios");
        jbItemBizPartnerDescription.setFocusable(false);
        jbItemBizPartnerDescription.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel8.add(jbItemBizPartnerDescription);

        jPanel4.add(jPanel8);

        jPanel10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlFkOriginalUnitId.setText("Unidad partida: *");
        jlFkOriginalUnitId.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel10.add(jlFkOriginalUnitId);

        jcbFkOriginalUnitId.setMaximumRowCount(12);
        jcbFkOriginalUnitId.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel10.add(jcbFkOriginalUnitId);

        jbFkOriginalUnitId.setText("...");
        jbFkOriginalUnitId.setToolTipText("Seleccionar unidad");
        jbFkOriginalUnitId.setFocusable(false);
        jbFkOriginalUnitId.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel10.add(jbFkOriginalUnitId);

        jPanel4.add(jPanel10);

        jPanel19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jckIsDiscountDocApplying.setText("Aplica descuento docto.");
        jckIsDiscountDocApplying.setFocusable(false);
        jckIsDiscountDocApplying.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel19.add(jckIsDiscountDocApplying);

        jLabel7.setPreferredSize(new java.awt.Dimension(155, 23));
        jPanel19.add(jLabel7);

        jckIsInventoriable.setText("Inventariable");
        jckIsInventoriable.setEnabled(false);
        jckIsInventoriable.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel19.add(jckIsInventoriable);

        jckIsBulk.setText("A granel");
        jckIsBulk.setEnabled(false);
        jckIsBulk.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel19.add(jckIsBulk);

        jPanel4.add(jPanel19);

        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jckIsDiscountUnitaryPercentage.setText("Descuento unitario en %:");
        jckIsDiscountUnitaryPercentage.setFocusable(false);
        jckIsDiscountUnitaryPercentage.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel16.add(jckIsDiscountUnitaryPercentage);

        jtfDiscountUnitaryPercentage.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfDiscountUnitaryPercentage.setText("0.00%");
        jtfDiscountUnitaryPercentage.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel16.add(jtfDiscountUnitaryPercentage);

        jLabel1.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel16.add(jLabel1);

        jckIsDiscountRetailChain.setText("Descuento cadena comercial");
        jckIsDiscountRetailChain.setEnabled(false);
        jckIsDiscountRetailChain.setPreferredSize(new java.awt.Dimension(165, 23));
        jPanel16.add(jckIsDiscountRetailChain);

        jPanel4.add(jPanel16);

        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jckIsDiscountEntryPercentage.setText("Descuento partida en %:");
        jckIsDiscountEntryPercentage.setFocusable(false);
        jckIsDiscountEntryPercentage.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel17.add(jckIsDiscountEntryPercentage);

        jtfDiscountEntryPercentage.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfDiscountEntryPercentage.setText("0.00%");
        jtfDiscountEntryPercentage.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel17.add(jtfDiscountEntryPercentage);

        jLabel9.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel17.add(jLabel9);

        jckIsDeleted.setText("Registro eliminado");
        jckIsDeleted.setFocusable(false);
        jckIsDeleted.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel17.add(jckIsDeleted);

        jPanel4.add(jPanel17);

        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlOriginalQuantity.setText("Cantidad: *");
        jlOriginalQuantity.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel15.add(jlOriginalQuantity);

        jtfOriginalQuantity.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfOriginalQuantity.setText("0.0000");
        jtfOriginalQuantity.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel15.add(jtfOriginalQuantity);

        jtfOriginalUnitSymbolRo.setEditable(false);
        jtfOriginalUnitSymbolRo.setText("UN");
        jtfOriginalUnitSymbolRo.setFocusable(false);
        jtfOriginalUnitSymbolRo.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel15.add(jtfOriginalUnitSymbolRo);

        jPanel4.add(jPanel15);

        jPanel23.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlQuantity.setText("Cantidad equivalente:");
        jlQuantity.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel23.add(jlQuantity);

        jtfQuantityRo.setEditable(false);
        jtfQuantityRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfQuantityRo.setText("0.0000");
        jtfQuantityRo.setFocusable(false);
        jtfQuantityRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel23.add(jtfQuantityRo);

        jtfUnitSymbolRo.setEditable(false);
        jtfUnitSymbolRo.setText("UN");
        jtfUnitSymbolRo.setFocusable(false);
        jtfUnitSymbolRo.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel23.add(jtfUnitSymbolRo);

        jckAuxPreserveQuantity.setText("Preservar cantidad");
        jckAuxPreserveQuantity.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel23.add(jckAuxPreserveQuantity);

        jPanel4.add(jPanel23);

        jPanel20.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlOriginalPriceUnitaryCy.setText("Precio unitario: *");
        jlOriginalPriceUnitaryCy.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel20.add(jlOriginalPriceUnitaryCy);

        jtfOriginalPriceUnitaryCy.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfOriginalPriceUnitaryCy.setText("0,000,000.0000");
        jtfOriginalPriceUnitaryCy.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel20.add(jtfOriginalPriceUnitaryCy);

        jtfCurrencyKeyPriceUnitaryCyRo.setEditable(false);
        jtfCurrencyKeyPriceUnitaryCyRo.setText("CUR");
        jtfCurrencyKeyPriceUnitaryCyRo.setFocusable(false);
        jtfCurrencyKeyPriceUnitaryCyRo.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel20.add(jtfCurrencyKeyPriceUnitaryCyRo);

        jbPriceUnitaryCyWizard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_std_wizard.gif"))); // NOI18N
        jbPriceUnitaryCyWizard.setToolTipText("Calcular precio unitario [Ctrl + W]");
        jbPriceUnitaryCyWizard.setFocusable(false);
        jbPriceUnitaryCyWizard.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel20.add(jbPriceUnitaryCyWizard);

        jbPriceHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_std_action.gif"))); // NOI18N
        jbPriceHistory.setFocusable(false);
        jbPriceHistory.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel20.add(jbPriceHistory);

        jPanel4.add(jPanel20);

        jPanel21.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlOriginalDiscountUnitaryCy.setText("Descuento unitario:");
        jlOriginalDiscountUnitaryCy.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel21.add(jlOriginalDiscountUnitaryCy);

        jtfOriginalDiscountUnitaryCy.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfOriginalDiscountUnitaryCy.setText("0,000,000.0000");
        jtfOriginalDiscountUnitaryCy.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel21.add(jtfOriginalDiscountUnitaryCy);

        jtfCurrencyKeyDiscountUnitaryCyRo.setEditable(false);
        jtfCurrencyKeyDiscountUnitaryCyRo.setText("CUR");
        jtfCurrencyKeyDiscountUnitaryCyRo.setFocusable(false);
        jtfCurrencyKeyDiscountUnitaryCyRo.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel21.add(jtfCurrencyKeyDiscountUnitaryCyRo);

        jPanel4.add(jPanel21);

        jPanel22.add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel22, java.awt.BorderLayout.CENTER);

        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("Valor de la partida:"));
        jPanel24.setPreferredSize(new java.awt.Dimension(400, 200));
        jPanel24.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.GridLayout(11, 3, 5, 1));

        jlCurrency.setText("Moneda:");
        jlCurrency.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jlCurrency);

        jtfCurrencySystemKeyRo.setEditable(false);
        jtfCurrencySystemKeyRo.setText("ERP");
        jtfCurrencySystemKeyRo.setFocusable(false);
        jtfCurrencySystemKeyRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfCurrencySystemKeyRo);

        jtfCurrencyKeyRo.setEditable(false);
        jtfCurrencyKeyRo.setText("CUR");
        jtfCurrencyKeyRo.setFocusable(false);
        jtfCurrencyKeyRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfCurrencyKeyRo);

        jlPriceUnitary.setText("Precio unitario:");
        jlPriceUnitary.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jlPriceUnitary);

        jtfPriceUnitaryRo.setEditable(false);
        jtfPriceUnitaryRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfPriceUnitaryRo.setText("0,000,000.0000");
        jtfPriceUnitaryRo.setFocusable(false);
        jtfPriceUnitaryRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfPriceUnitaryRo);

        jtfPriceUnitaryCyRo.setEditable(false);
        jtfPriceUnitaryCyRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfPriceUnitaryCyRo.setText("0,000,000.0000");
        jtfPriceUnitaryCyRo.setFocusable(false);
        jtfPriceUnitaryCyRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfPriceUnitaryCyRo);

        jlDiscountUnitary.setText("Descuento unitario (-):");
        jlDiscountUnitary.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jlDiscountUnitary);

        jtfDiscountUnitaryRo.setEditable(false);
        jtfDiscountUnitaryRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfDiscountUnitaryRo.setText("0,000,000.0000");
        jtfDiscountUnitaryRo.setFocusable(false);
        jtfDiscountUnitaryRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfDiscountUnitaryRo);

        jtfDiscountUnitaryCyRo.setEditable(false);
        jtfDiscountUnitaryCyRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfDiscountUnitaryCyRo.setText("0,000,000.0000");
        jtfDiscountUnitaryCyRo.setFocusable(false);
        jtfDiscountUnitaryCyRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfDiscountUnitaryCyRo);

        jlDiscountEntry.setText("Descuento partida (-):");
        jlDiscountEntry.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jlDiscountEntry);

        jtfDiscountEntryRo.setEditable(false);
        jtfDiscountEntryRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfDiscountEntryRo.setText("0,000,000,000.00");
        jtfDiscountEntryRo.setFocusable(false);
        jtfDiscountEntryRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfDiscountEntryRo);

        jtfDiscountEntryCy.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfDiscountEntryCy.setText("0,000,000,000.00");
        jtfDiscountEntryCy.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfDiscountEntryCy);

        jlSubtotalProvisional_r.setText("Subtotal provisional:");
        jlSubtotalProvisional_r.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jlSubtotalProvisional_r);

        jtfSubtotalProvisional_rRo.setEditable(false);
        jtfSubtotalProvisional_rRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfSubtotalProvisional_rRo.setText("0,000,000,000.00");
        jtfSubtotalProvisional_rRo.setFocusable(false);
        jtfSubtotalProvisional_rRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfSubtotalProvisional_rRo);

        jtfSubtotalProvisionalCy_rRo.setEditable(false);
        jtfSubtotalProvisionalCy_rRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfSubtotalProvisionalCy_rRo.setText("0,000,000,000.00");
        jtfSubtotalProvisionalCy_rRo.setFocusable(false);
        jtfSubtotalProvisionalCy_rRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfSubtotalProvisionalCy_rRo);

        jlDiscountDoc.setText("Descuento docto. (-):");
        jlDiscountDoc.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jlDiscountDoc);

        jtfDiscountDocRo.setEditable(false);
        jtfDiscountDocRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfDiscountDocRo.setText("0,000,000,000.00");
        jtfDiscountDocRo.setFocusable(false);
        jtfDiscountDocRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfDiscountDocRo);

        jtfDiscountDocCy.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfDiscountDocCy.setText("0,000,000,000.00");
        jtfDiscountDocCy.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfDiscountDocCy);

        jlSubtotal_r.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlSubtotal_r.setText("Subtotal partida:");
        jlSubtotal_r.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jlSubtotal_r);

        jtfSubtotal_rRo.setEditable(false);
        jtfSubtotal_rRo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jtfSubtotal_rRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfSubtotal_rRo.setText("0,000,000,000.00");
        jtfSubtotal_rRo.setFocusable(false);
        jtfSubtotal_rRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfSubtotal_rRo);

        jtfSubtotalCy_rRo.setEditable(false);
        jtfSubtotalCy_rRo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jtfSubtotalCy_rRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfSubtotalCy_rRo.setText("0,000,000,000.00");
        jtfSubtotalCy_rRo.setFocusable(false);
        jtfSubtotalCy_rRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfSubtotalCy_rRo);

        jlTaxCharged_r.setText("Imptos. trasladados:");
        jlTaxCharged_r.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jlTaxCharged_r);

        jtfTaxCharged_rRo.setEditable(false);
        jtfTaxCharged_rRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfTaxCharged_rRo.setText("0,000,000,000.00");
        jtfTaxCharged_rRo.setFocusable(false);
        jtfTaxCharged_rRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfTaxCharged_rRo);

        jtfTaxChargedCy_rRo.setEditable(false);
        jtfTaxChargedCy_rRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfTaxChargedCy_rRo.setText("0,000,000,000.00");
        jtfTaxChargedCy_rRo.setFocusable(false);
        jtfTaxChargedCy_rRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfTaxChargedCy_rRo);

        jlTaxRetained_r.setText("Imptos. retenidos (-):");
        jlTaxRetained_r.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jlTaxRetained_r);

        jtfTaxRetained_rRo.setEditable(false);
        jtfTaxRetained_rRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfTaxRetained_rRo.setText("0,000,000,000.00");
        jtfTaxRetained_rRo.setFocusable(false);
        jtfTaxRetained_rRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfTaxRetained_rRo);

        jtfTaxRetainedCy_rRo.setEditable(false);
        jtfTaxRetainedCy_rRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfTaxRetainedCy_rRo.setText("0,000,000,000.00");
        jtfTaxRetainedCy_rRo.setFocusable(false);
        jtfTaxRetainedCy_rRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfTaxRetainedCy_rRo);

        jlTotal_r.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlTotal_r.setText("Total partida:");
        jlTotal_r.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jlTotal_r);

        jtfTotal_rRo.setEditable(false);
        jtfTotal_rRo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jtfTotal_rRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfTotal_rRo.setText("0,000,000,000.00");
        jtfTotal_rRo.setFocusable(false);
        jtfTotal_rRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfTotal_rRo);

        jtfTotalCy_rRo.setEditable(false);
        jtfTotalCy_rRo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jtfTotalCy_rRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfTotalCy_rRo.setText("0,000,000,000.00");
        jtfTotalCy_rRo.setFocusable(false);
        jtfTotalCy_rRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfTotalCy_rRo);

        jlPriceUnitaryReal_r.setForeground(java.awt.Color.gray);
        jlPriceUnitaryReal_r.setText("Precio unitario neto:");
        jlPriceUnitaryReal_r.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jlPriceUnitaryReal_r);

        jtfPriceUnitaryReal_rRo.setEditable(false);
        jtfPriceUnitaryReal_rRo.setForeground(java.awt.Color.gray);
        jtfPriceUnitaryReal_rRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfPriceUnitaryReal_rRo.setText("0,000,000.0000");
        jtfPriceUnitaryReal_rRo.setFocusable(false);
        jtfPriceUnitaryReal_rRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfPriceUnitaryReal_rRo);

        jtfPriceUnitaryRealCy_rRo.setEditable(false);
        jtfPriceUnitaryRealCy_rRo.setForeground(java.awt.Color.gray);
        jtfPriceUnitaryRealCy_rRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfPriceUnitaryRealCy_rRo.setText("0,000,000.0000");
        jtfPriceUnitaryRealCy_rRo.setFocusable(false);
        jtfPriceUnitaryRealCy_rRo.setPreferredSize(new java.awt.Dimension(125, 23));
        jPanel9.add(jtfPriceUnitaryRealCy_rRo);

        jPanel5.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel24.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel1.add(jPanel24, java.awt.BorderLayout.EAST);

        jPanel2.add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Miscelánea:"));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jPanel35.setLayout(new java.awt.GridLayout(2, 1, 0, 1));

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlFkItemReferenceId_n.setText("Ítem referencia:");
        jlFkItemReferenceId_n.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel12.add(jlFkItemReferenceId_n);

        jcbFkItemReferenceId_n.setMaximumRowCount(12);
        jcbFkItemReferenceId_n.setPreferredSize(new java.awt.Dimension(425, 23));
        jPanel12.add(jcbFkItemReferenceId_n);

        jbFkItemReferenceId_n.setText("...");
        jbFkItemReferenceId_n.setToolTipText("Seleccionar ítem referencia");
        jbFkItemReferenceId_n.setFocusable(false);
        jbFkItemReferenceId_n.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel12.add(jbFkItemReferenceId_n);

        jLabel10.setPreferredSize(new java.awt.Dimension(40, 23));
        jPanel12.add(jLabel10);

        jPanel35.add(jPanel12);

        jPanel40.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlReference.setText("Referencia:");
        jlReference.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel40.add(jlReference);

        jtfReference.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel40.add(jtfReference);

        jPanel35.add(jPanel40);

        jPanel18.add(jPanel35, java.awt.BorderLayout.NORTH);

        jPanel3.add(jPanel18, java.awt.BorderLayout.WEST);

        jpCostCenter.setBorder(javax.swing.BorderFactory.createTitledBorder("Centro de costo-beneficio:"));
        jpCostCenter.setPreferredSize(new java.awt.Dimension(400, 50));
        jpCostCenter.setLayout(new java.awt.BorderLayout());

        jlDummyCostCenter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jlDummyCostCenter.setText("[Panel centro de costo-beneficio]");
        jlDummyCostCenter.setPreferredSize(new java.awt.Dimension(250, 50));
        jpCostCenter.add(jlDummyCostCenter, java.awt.BorderLayout.NORTH);

        jPanel3.add(jpCostCenter, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jpRegistry.add(jPanel2, java.awt.BorderLayout.NORTH);

        jpTaxes.setLayout(new java.awt.BorderLayout());

        jpTaxInfo.setBorder(javax.swing.BorderFactory.createTitledBorder("Impuestos de la partida:"));
        jpTaxInfo.setLayout(new java.awt.BorderLayout(10, 0));

        jPanel14.setLayout(new java.awt.GridLayout(2, 2, 5, 1));

        jlTaxIdentityEmisor.setText("Identidad de impuestos del emisor:");
        jlTaxIdentityEmisor.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel14.add(jlTaxIdentityEmisor);

        jtfTaxIdentityEmisorRo.setEditable(false);
        jtfTaxIdentityEmisorRo.setText("TAX IDENTITY");
        jtfTaxIdentityEmisorRo.setFocusable(false);
        jPanel14.add(jtfTaxIdentityEmisorRo);

        jlTaxIdentityReceptor.setText("Identidad de impuestos del receptor:");
        jlTaxIdentityReceptor.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel14.add(jlTaxIdentityReceptor);

        jtfTaxIdentityReceptorRo.setEditable(false);
        jtfTaxIdentityReceptorRo.setText("TAX IDENTITY");
        jtfTaxIdentityReceptorRo.setFocusable(false);
        jPanel14.add(jtfTaxIdentityReceptorRo);

        jpTaxInfo.add(jPanel14, java.awt.BorderLayout.WEST);

        jPanel33.setLayout(new java.awt.GridLayout(2, 1));

        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlFkTaxRegionId.setText("Región de impuestos de la partida: *");
        jlFkTaxRegionId.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel13.add(jlFkTaxRegionId);

        jcbFkTaxRegionId.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel13.add(jcbFkTaxRegionId);

        jbFkTaxRegionId.setText("...");
        jbFkTaxRegionId.setToolTipText("Seleccionar región de impuestos");
        jbFkTaxRegionId.setFocusable(false);
        jbFkTaxRegionId.setPreferredSize(new java.awt.Dimension(23, 23));
        jPanel13.add(jbFkTaxRegionId);

        jPanel33.add(jPanel13);

        jPanel34.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jckIsTaxesAutomaticApplying.setText("Cálculo automático de impuestos");
        jckIsTaxesAutomaticApplying.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel34.add(jckIsTaxesAutomaticApplying);

        jPanel33.add(jPanel34);

        jpTaxInfo.add(jPanel33, java.awt.BorderLayout.CENTER);

        jpTaxes.add(jpTaxInfo, java.awt.BorderLayout.NORTH);

        jTabbedPane.addTab("Impuestos", jpTaxes);

        jpCommissions.setLayout(new java.awt.BorderLayout());
        jTabbedPane.addTab("Comisiones", jpCommissions);

        jpExtraData.setLayout(new java.awt.BorderLayout());

        jpExtraDataContract.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos adicionales:"));
        jpExtraDataContract.setLayout(new java.awt.BorderLayout());

        jPanel38.setLayout(new java.awt.GridLayout(6, 1, 0, 1));

        jPanel41.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 0));

        jlFkVehicleTypeId_n.setText("Tipo vehículo:");
        jlFkVehicleTypeId_n.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel41.add(jlFkVehicleTypeId_n);

        jcbFkVehicleTypeId_n.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel41.add(jcbFkVehicleTypeId_n);

        jPanel38.add(jPanel41);

        jPanel43.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 0));

        jlVehicleNumber.setText("Número vehículo:");
        jlVehicleNumber.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel43.add(jlVehicleNumber);

        jtfVehicleNumber.setText("VEHICLE NUMBER");
        jtfVehicleNumber.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel43.add(jtfVehicleNumber);

        jPanel38.add(jPanel43);

        jPanel45.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 0));

        jlSecuritySeal.setText("Sello de seguridad:");
        jlSecuritySeal.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel45.add(jlSecuritySeal);

        jtfSecuritySeal.setText("SECURITY SEAL");
        jtfSecuritySeal.setPreferredSize(new java.awt.Dimension(150, 23));
        jPanel45.add(jtfSecuritySeal);

        jPanel38.add(jPanel45);

        jPanel47.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 0));

        jlTicket.setText("Boleto:");
        jlTicket.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel47.add(jlTicket);

        jtfTicket.setText("TICKET");
        jtfTicket.setPreferredSize(new java.awt.Dimension(107, 23));
        jPanel47.add(jtfTicket);

        jPanel38.add(jPanel47);

        jpExtraDataContract.add(jPanel38, java.awt.BorderLayout.NORTH);

        jpExtraData.add(jpExtraDataContract, java.awt.BorderLayout.EAST);

        jpExtraDataUnits.setBorder(javax.swing.BorderFactory.createTitledBorder("Unidades físicas de la partida:"));
        jpExtraDataUnits.setLayout(new java.awt.BorderLayout());

        jpExtraDataUnitsNorth.setLayout(new java.awt.GridLayout(6, 1, 0, 1));

        jPanel27.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlLength.setText("Longitud: *");
        jlLength.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel27.add(jlLength);

        jtfLength.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfLength.setText("0.0000");
        jtfLength.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel27.add(jtfLength);

        jtfLengthUnitSymbolRo.setEditable(false);
        jtfLengthUnitSymbolRo.setText("UN");
        jtfLengthUnitSymbolRo.setFocusable(false);
        jtfLengthUnitSymbolRo.setPreferredSize(new java.awt.Dimension(25, 23));
        jPanel27.add(jtfLengthUnitSymbolRo);

        jpExtraDataUnitsNorth.add(jPanel27);

        jPanel28.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlSurface.setText("Superficie: *");
        jlSurface.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel28.add(jlSurface);

        jtfSurface.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfSurface.setText("0.0000");
        jtfSurface.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel28.add(jtfSurface);

        jtfSurfaceUnitSymbolRo.setEditable(false);
        jtfSurfaceUnitSymbolRo.setText("UN");
        jtfSurfaceUnitSymbolRo.setFocusable(false);
        jtfSurfaceUnitSymbolRo.setPreferredSize(new java.awt.Dimension(25, 23));
        jPanel28.add(jtfSurfaceUnitSymbolRo);

        jpExtraDataUnitsNorth.add(jPanel28);

        jPanel29.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlVolume.setText("Volumen: *");
        jlVolume.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel29.add(jlVolume);

        jtfVolume.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfVolume.setText("0.0000");
        jtfVolume.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel29.add(jtfVolume);

        jtfVolumeUnitSymbolRo.setEditable(false);
        jtfVolumeUnitSymbolRo.setText("UN");
        jtfVolumeUnitSymbolRo.setFocusable(false);
        jtfVolumeUnitSymbolRo.setPreferredSize(new java.awt.Dimension(25, 23));
        jPanel29.add(jtfVolumeUnitSymbolRo);

        jpExtraDataUnitsNorth.add(jPanel29);

        jPanel30.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlMass.setText("Masa: *");
        jlMass.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel30.add(jlMass);

        jtfMass.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfMass.setText("0.0000");
        jtfMass.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel30.add(jtfMass);

        jtfMassUnitSymbolRo.setEditable(false);
        jtfMassUnitSymbolRo.setText("UN");
        jtfMassUnitSymbolRo.setFocusable(false);
        jtfMassUnitSymbolRo.setPreferredSize(new java.awt.Dimension(25, 23));
        jPanel30.add(jtfMassUnitSymbolRo);

        jpExtraDataUnitsNorth.add(jPanel30);

        jPanel31.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlWeigthGross.setText("Peso bruto:");
        jlWeigthGross.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel31.add(jlWeigthGross);

        jtfWeigthGrossRo.setEditable(false);
        jtfWeigthGrossRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfWeigthGrossRo.setText("0.0000");
        jtfWeigthGrossRo.setFocusable(false);
        jtfWeigthGrossRo.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel31.add(jtfWeigthGrossRo);

        jtfWeigthGrossUnitSymbolRo.setEditable(false);
        jtfWeigthGrossUnitSymbolRo.setText("UN");
        jtfWeigthGrossUnitSymbolRo.setFocusable(false);
        jtfWeigthGrossUnitSymbolRo.setPreferredSize(new java.awt.Dimension(25, 23));
        jPanel31.add(jtfWeigthGrossUnitSymbolRo);

        jpExtraDataUnitsNorth.add(jPanel31);

        jPanel32.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlWeigthDelivery.setText("Peso flete:");
        jlWeigthDelivery.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel32.add(jlWeigthDelivery);

        jtfWeigthDeliveryRo.setEditable(false);
        jtfWeigthDeliveryRo.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfWeigthDeliveryRo.setText("0.0000");
        jtfWeigthDeliveryRo.setFocusable(false);
        jtfWeigthDeliveryRo.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel32.add(jtfWeigthDeliveryRo);

        jtfWeigthDeliveryUnitSymbolRo.setEditable(false);
        jtfWeigthDeliveryUnitSymbolRo.setText("UN");
        jtfWeigthDeliveryUnitSymbolRo.setFocusable(false);
        jtfWeigthDeliveryUnitSymbolRo.setPreferredSize(new java.awt.Dimension(25, 23));
        jPanel32.add(jtfWeigthDeliveryUnitSymbolRo);

        jpExtraDataUnitsNorth.add(jPanel32);

        jpExtraDataUnits.add(jpExtraDataUnitsNorth, java.awt.BorderLayout.NORTH);

        jpExtraData.add(jpExtraDataUnits, java.awt.BorderLayout.WEST);

        jpExtraDataOther.setBorder(javax.swing.BorderFactory.createTitledBorder("Surtido y vinculación de la partida:"));
        jpExtraDataOther.setLayout(new java.awt.BorderLayout());

        jpExtraDataOtherNorth.setLayout(new java.awt.GridLayout(4, 1, 1, 0));

        jpExtraDataOtherFillment.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 0));

        jckIsSurplusPercentageApplying.setText("Aplica excedente al surtir o vincular la partida:");
        jckIsSurplusPercentageApplying.setPreferredSize(new java.awt.Dimension(250, 23));
        jpExtraDataOtherFillment.add(jckIsSurplusPercentageApplying);

        jtfSurplusPercentage.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfSurplusPercentage.setText("0.00%");
        jtfSurplusPercentage.setPreferredSize(new java.awt.Dimension(75, 23));
        jpExtraDataOtherFillment.add(jtfSurplusPercentage);

        jpExtraDataOtherNorth.add(jpExtraDataOtherFillment);

        jPanel25.setLayout(new java.awt.GridLayout(2, 1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel3.setText("Surtir una partida:");
        jLabel3.setPreferredSize(new java.awt.Dimension(105, 23));
        jPanel25.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel4.setText("Asociar la partida con movimientos de almacén (entrada o salida).");
        jLabel4.setPreferredSize(new java.awt.Dimension(318, 23));
        jPanel25.add(jLabel4);

        jpExtraDataOtherNorth.add(jPanel25);

        jPanel26.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel5.setText("Vincular una partida:");
        jLabel5.setPreferredSize(new java.awt.Dimension(105, 23));
        jPanel26.add(jLabel5);

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel6.setText("Asociar la partida con otro documento (e.g. de pedido a factura).");
        jLabel6.setPreferredSize(new java.awt.Dimension(328, 23));
        jPanel26.add(jLabel6);

        jpExtraDataOtherNorth.add(jPanel26);

        jpExtraDataOther.add(jpExtraDataOtherNorth, java.awt.BorderLayout.NORTH);

        jpExtraData.add(jpExtraDataOther, java.awt.BorderLayout.CENTER);

        jTabbedPane.addTab("Datos adicionales", jpExtraData);

        jPricesData.setLayout(new java.awt.BorderLayout());

        jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos generales:"));
        jPanel46.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 5, 1));

        jckIsDpsReqMonthDelivery.setSelected(true);
        jckIsDpsReqMonthDelivery.setText("Requiere entregas mensuales");
        jckIsDpsReqMonthDelivery.setEnabled(false);
        jckIsDpsReqMonthDelivery.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jckIsDpsReqMonthDelivery.setPreferredSize(new java.awt.Dimension(175, 23));
        jPanel46.add(jckIsDpsReqMonthDelivery);

        jlDpsContractBase.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlDpsContractBase.setText("Base:");
        jlDpsContractBase.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel46.add(jlDpsContractBase);

        jtfDpsContractBase.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfDpsContractBase.setText("0,000.0000");
        jtfDpsContractBase.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel46.add(jtfDpsContractBase);

        jlDpsContractFuture.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlDpsContractFuture.setText("Futuro:");
        jlDpsContractFuture.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel46.add(jlDpsContractFuture);

        jtfDpsContractFuture.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfDpsContractFuture.setText("0,000.0000");
        jtfDpsContractFuture.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel46.add(jtfDpsContractFuture);

        jlDpsContractFactor.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jlDpsContractFactor.setText("Factor ajuste:");
        jlDpsContractFactor.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel46.add(jlDpsContractFactor);

        jtfDpsContractFactor.setEditable(false);
        jtfDpsContractFactor.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfDpsContractFactor.setText("0,000.0000");
        jtfDpsContractFactor.setFocusable(false);
        jtfDpsContractFactor.setPreferredSize(new java.awt.Dimension(75, 23));
        jPanel46.add(jtfDpsContractFactor);

        jckIsPriceConfirm.setText("Requiere confirmación de precio");
        jckIsPriceConfirm.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel46.add(jckIsPriceConfirm);

        jPricesData.add(jPanel46, java.awt.BorderLayout.NORTH);

        jPanel51.setBorder(javax.swing.BorderFactory.createTitledBorder("Entregas mensuales:"));
        jPanel51.setLayout(new java.awt.BorderLayout());

        jPanel59.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING));

        jPanel67.setLayout(new java.awt.GridLayout(2, 0, 2, 0));

        jlIsPriceVariable.setText("Tipo precio:");
        jlIsPriceVariable.setPreferredSize(new java.awt.Dimension(65, 23));
        jPanel67.add(jlIsPriceVariable);

        jckIsDirectPrice.setSelected(true);
        jckIsDirectPrice.setText("Directo");
        jckIsDirectPrice.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jckIsDirectPrice.setPreferredSize(new java.awt.Dimension(65, 23));
        jPanel67.add(jckIsDirectPrice);

        jPanel59.add(jPanel67);

        jPanel39.setLayout(new java.awt.GridLayout(2, 0, 2, 0));

        jlContractPriceNumbrerReference.setText("No. Órd.:");
        jlContractPriceNumbrerReference.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel39.add(jlContractPriceNumbrerReference);

        jtfContractPriceNumbrerReference.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel39.add(jtfContractPriceNumbrerReference);

        jPanel59.add(jPanel39);

        jPanel63.setLayout(new java.awt.GridLayout(2, 0, 2, 0));

        jlContractPriceYear.setText("Año:");
        jlContractPriceYear.setPreferredSize(new java.awt.Dimension(40, 23));
        jPanel63.add(jlContractPriceYear);

        jtfContractPriceYear.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfContractPriceYear.setText("2014");
        jtfContractPriceYear.setPreferredSize(new java.awt.Dimension(40, 23));
        jPanel63.add(jtfContractPriceYear);

        jPanel59.add(jPanel63);

        jPanel65.setLayout(new java.awt.GridLayout(2, 0, 2, 0));

        jlContractPriceMonth.setText("Mes:");
        jlContractPriceMonth.setPreferredSize(new java.awt.Dimension(30, 23));
        jPanel65.add(jlContractPriceMonth);

        jtfContractPriceMonth.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfContractPriceMonth.setText("05");
        jtfContractPriceMonth.setPreferredSize(new java.awt.Dimension(30, 23));
        jPanel65.add(jtfContractPriceMonth);

        jPanel59.add(jPanel65);

        jPanel72.setPreferredSize(new java.awt.Dimension(120, 46));
        jPanel72.setLayout(new java.awt.GridLayout(2, 0, 2, 0));

        jlPriceOriginalQuantity.setText("Cantidad:");
        jlPriceOriginalQuantity.setPreferredSize(new java.awt.Dimension(62, 23));
        jPanel72.add(jlPriceOriginalQuantity);

        jPanel53.setPreferredSize(new java.awt.Dimension(121, 23));
        jPanel53.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 2, 0));

        jtfPriceOriginalQuantity.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfPriceOriginalQuantity.setText("0,000,000.0000");
        jtfPriceOriginalQuantity.setPreferredSize(new java.awt.Dimension(85, 23));
        jPanel53.add(jtfPriceOriginalQuantity);

        jtfPriceOriginalUnitSymbol.setEditable(false);
        jtfPriceOriginalUnitSymbol.setText("UN");
        jtfPriceOriginalUnitSymbol.setFocusable(false);
        jtfPriceOriginalUnitSymbol.setPreferredSize(new java.awt.Dimension(30, 23));
        jPanel53.add(jtfPriceOriginalUnitSymbol);

        jPanel72.add(jPanel53);

        jPanel59.add(jPanel72);

        jPanel77.setLayout(new java.awt.GridLayout(2, 0, 2, 0));

        jLabel2.setText("Cambiar precio:");
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 23));
        jPanel77.add(jLabel2);
        jPanel77.add(jckChangePrice);

        jPanel59.add(jPanel77);

        jPanel73.setPreferredSize(new java.awt.Dimension(120, 46));
        jPanel73.setLayout(new java.awt.GridLayout(2, 0, 2, 0));

        jlPriceOriginalPriceUnitaryCy.setText("Precio u. $:");
        jlPriceOriginalPriceUnitaryCy.setPreferredSize(new java.awt.Dimension(62, 23));
        jPanel73.add(jlPriceOriginalPriceUnitaryCy);

        jPanel54.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 2, 0));

        jtfPriceOriginalPriceUnitaryCy.setEditable(false);
        jtfPriceOriginalPriceUnitaryCy.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfPriceOriginalPriceUnitaryCy.setText("0,000,000.0000");
        jtfPriceOriginalPriceUnitaryCy.setFocusable(false);
        jtfPriceOriginalPriceUnitaryCy.setPreferredSize(new java.awt.Dimension(85, 23));
        jPanel54.add(jtfPriceOriginalPriceUnitaryCy);

        jtfPriceCurrencyKeyPriceUnitaryCy.setEditable(false);
        jtfPriceCurrencyKeyPriceUnitaryCy.setText("CUR");
        jtfPriceCurrencyKeyPriceUnitaryCy.setFocusable(false);
        jtfPriceCurrencyKeyPriceUnitaryCy.setPreferredSize(new java.awt.Dimension(30, 23));
        jPanel54.add(jtfPriceCurrencyKeyPriceUnitaryCy);

        jPanel73.add(jPanel54);

        jPanel59.add(jPanel73);

        jPanel74.setLayout(new java.awt.GridLayout(2, 0, 2, 0));

        jlContractBase.setText("Base:");
        jlContractBase.setPreferredSize(new java.awt.Dimension(70, 23));
        jPanel74.add(jlContractBase);

        jtfContractBase.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfContractBase.setText("0,000.0000");
        jtfContractBase.setPreferredSize(new java.awt.Dimension(70, 23));
        jPanel74.add(jtfContractBase);

        jPanel59.add(jPanel74);

        jPanel75.setLayout(new java.awt.GridLayout(2, 0, 2, 0));

        jlContractFuture.setText("Futuro:");
        jlContractFuture.setPreferredSize(new java.awt.Dimension(65, 23));
        jPanel75.add(jlContractFuture);

        jtfContractFuture.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfContractFuture.setText("0,000.0000");
        jtfContractFuture.setPreferredSize(new java.awt.Dimension(70, 23));
        jPanel75.add(jtfContractFuture);

        jPanel59.add(jPanel75);

        jPanel76.setLayout(new java.awt.GridLayout(2, 0, 2, 0));

        jlContractFactor.setText("Factor ajuste:");
        jlContractFactor.setPreferredSize(new java.awt.Dimension(70, 23));
        jPanel76.add(jlContractFactor);

        jtfContractFactor.setEditable(false);
        jtfContractFactor.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jtfContractFactor.setText("0,000.0000");
        jtfContractFactor.setFocusable(false);
        jtfContractFactor.setPreferredSize(new java.awt.Dimension(70, 23));
        jPanel76.add(jtfContractFactor);

        jPanel59.add(jPanel76);

        jPanel78.setLayout(new java.awt.GridLayout(2, 0, 2, 0));
        jPanel78.add(jLabel19);

        jbPriceNew.setText("Nuevo");
        jbPriceNew.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jbPriceNew.setPreferredSize(new java.awt.Dimension(65, 23));
        jPanel78.add(jbPriceNew);

        jPanel59.add(jPanel78);

        jPanel80.setLayout(new java.awt.GridLayout(2, 0, 2, 0));
        jPanel80.add(jLabel21);

        jbPriceSave.setText("Guardar");
        jbPriceSave.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jbPriceSave.setPreferredSize(new java.awt.Dimension(65, 23));
        jPanel80.add(jbPriceSave);

        jPanel59.add(jPanel80);

        jPanel79.setLayout(new java.awt.GridLayout(2, 0, 2, 0));
        jPanel79.add(jLabel20);

        jbClearPriceFields.setText("Limpiar");
        jbClearPriceFields.setMargin(new java.awt.Insets(2, 0, 2, 0));
        jbClearPriceFields.setPreferredSize(new java.awt.Dimension(65, 23));
        jPanel79.add(jbClearPriceFields);

        jPanel59.add(jPanel79);

        jPanel51.add(jPanel59, java.awt.BorderLayout.NORTH);

        jpPrices.setLayout(new java.awt.BorderLayout());

        jpNotesControls1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        jbGridPriceNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_std_new.gif"))); // NOI18N
        jbGridPriceNew.setToolTipText("Crear entrega");
        jbGridPriceNew.setPreferredSize(new java.awt.Dimension(23, 23));
        jpNotesControls1.add(jbGridPriceNew);

        jbGridPriceEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_std_edit.gif"))); // NOI18N
        jbGridPriceEdit.setToolTipText("Modificar entrega");
        jbGridPriceEdit.setPreferredSize(new java.awt.Dimension(23, 23));
        jpNotesControls1.add(jbGridPriceEdit);

        jbGridPriceDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_std_delete.gif"))); // NOI18N
        jbGridPriceDelete.setToolTipText("Eliminar entrega");
        jbGridPriceDelete.setPreferredSize(new java.awt.Dimension(23, 23));
        jpNotesControls1.add(jbGridPriceDelete);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setPreferredSize(new java.awt.Dimension(3, 23));
        jpNotesControls1.add(jSeparator2);

        jtbGridPriceFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/switch_filter_off.gif"))); // NOI18N
        jtbGridPriceFilter.setToolTipText("Filtrar entregas eliminadas");
        jtbGridPriceFilter.setPreferredSize(new java.awt.Dimension(23, 23));
        jtbGridPriceFilter.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/switch_filter_on.gif"))); // NOI18N
        jpNotesControls1.add(jtbGridPriceFilter);

        jpPrices.add(jpNotesControls1, java.awt.BorderLayout.NORTH);

        jPanel51.add(jpPrices, java.awt.BorderLayout.CENTER);

        jPricesData.add(jPanel51, java.awt.BorderLayout.CENTER);

        jTabbedPane.addTab("Entregas mensuales", jPricesData);

        jpNotes.setLayout(new java.awt.BorderLayout());

        jpNotesControls.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 0));

        jbNotesNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_std_new.gif"))); // NOI18N
        jbNotesNew.setToolTipText("Crear notas [Ctrl + N]");
        jbNotesNew.setPreferredSize(new java.awt.Dimension(23, 23));
        jpNotesControls.add(jbNotesNew);

        jbNotesEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_std_edit.gif"))); // NOI18N
        jbNotesEdit.setToolTipText("Modificar notas [Ctrl + M]");
        jbNotesEdit.setPreferredSize(new java.awt.Dimension(23, 23));
        jpNotesControls.add(jbNotesEdit);

        jbNotesDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/icon_std_delete.gif"))); // NOI18N
        jbNotesDelete.setToolTipText("Eliminar notas [Ctrl + D]");
        jbNotesDelete.setPreferredSize(new java.awt.Dimension(23, 23));
        jpNotesControls.add(jbNotesDelete);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(3, 23));
        jpNotesControls.add(jSeparator1);

        jtbNotesFilter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/switch_filter_off.gif"))); // NOI18N
        jtbNotesFilter.setToolTipText("Filtrar notas eliminadas [Ctrl + F]");
        jtbNotesFilter.setPreferredSize(new java.awt.Dimension(23, 23));
        jtbNotesFilter.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/erp/img/switch_filter_on.gif"))); // NOI18N
        jpNotesControls.add(jtbNotesFilter);

        jbSystemNotes.setText("...");
        jbSystemNotes.setToolTipText("Seleccionar notas de sistema");
        jbSystemNotes.setPreferredSize(new java.awt.Dimension(23, 23));
        jpNotesControls.add(jbSystemNotes);

        jpNotes.add(jpNotesControls, java.awt.BorderLayout.NORTH);

        jTabbedPane.addTab("Notas", jpNotes);

        jpAddendaData.setBorder(javax.swing.BorderFactory.createTitledBorder("Información para addenda:"));
        jpAddendaData.setLayout(new java.awt.BorderLayout());

        jPanel37.setLayout(new java.awt.BorderLayout());

        jPanel42.setLayout(new java.awt.GridLayout(7, 1, 0, 2));

        jPanel68.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel68.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAddendaNumberPosition.setText("Número de posición: *");
        jlAddendaNumberPosition.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel68.add(jlAddendaNumberPosition);

        jtfAddendaNumberPosition.setText("NUMBER");
        jtfAddendaNumberPosition.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel68.add(jtfAddendaNumberPosition);

        jPanel42.add(jPanel68);

        jPanel69.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAddendaCenter.setText("Centro: *");
        jlAddendaCenter.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel69.add(jlAddendaCenter);

        jtfAddendaCenter.setText("CENTER");
        jtfAddendaCenter.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel69.add(jtfAddendaCenter);

        jPanel42.add(jPanel69);

        jPanel36.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAddendaEntryNumber.setText("Número de partida: *");
        jlAddendaEntryNumber.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel36.add(jlAddendaEntryNumber);

        jtfAddendaEntryNumber.setText("NUMBER");
        jtfAddendaEntryNumber.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel36.add(jtfAddendaEntryNumber);

        jPanel42.add(jPanel36);

        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAddendaFkBarcode.setText("Código de barras: *");
        jlAddendaFkBarcode.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel11.add(jlAddendaFkBarcode);

        jcbAddendaFkBarcode.setEditable(true);
        jcbAddendaFkBarcode.setMaximumRowCount(12);
        jcbAddendaFkBarcode.setPreferredSize(new java.awt.Dimension(200, 23));
        jPanel11.add(jcbAddendaFkBarcode);

        jPanel42.add(jPanel11);

        jPanel44.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAddendaOrder.setText("Folio pedido: *");
        jlAddendaOrder.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel44.add(jlAddendaOrder);

        jtfAddendaOrder.setText("NUMBER");
        jtfAddendaOrder.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel44.add(jtfAddendaOrder);

        jPanel42.add(jPanel44);

        jPanel37.add(jPanel42, java.awt.BorderLayout.NORTH);

        jpAddendaData.add(jPanel37, java.awt.BorderLayout.WEST);

        jPanel48.setLayout(new java.awt.BorderLayout());

        jPanel49.setLayout(new java.awt.GridLayout(7, 1, 0, 2));

        jPanel70.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel70.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAddendaCages.setText("Cajas entregadas: *");
        jlAddendaCages.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel70.add(jlAddendaCages);

        jtfAddendaCages.setText("BOXES DELIVERY");
        jtfAddendaCages.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel70.add(jtfAddendaCages);

        jPanel49.add(jPanel70);

        jPanel71.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAddendaCagePriceUnitary.setText("Precio unitario caja: *");
        jlAddendaCagePriceUnitary.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel71.add(jlAddendaCagePriceUnitary);

        jtfAddendaCagePriceUnitary.setText("UNITARY PRICE");
        jtfAddendaCagePriceUnitary.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel71.add(jtfAddendaCagePriceUnitary);

        jPanel49.add(jPanel71);

        jPanel50.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAddendaParts.setText("Piezas entregadas: *");
        jlAddendaParts.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel50.add(jlAddendaParts);

        jtfAddendaParts.setText("PARTS DELIVERED");
        jtfAddendaParts.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel50.add(jtfAddendaParts);

        jPanel49.add(jPanel50);

        jPanel52.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jlAddendaPartPriceUnitary.setText("Precio unitario pieza: *");
        jlAddendaPartPriceUnitary.setPreferredSize(new java.awt.Dimension(110, 23));
        jPanel52.add(jlAddendaPartPriceUnitary);

        jtfAddendaPartPriceUnitary.setText("UNITARY PRICE");
        jtfAddendaPartPriceUnitary.setPreferredSize(new java.awt.Dimension(100, 23));
        jPanel52.add(jtfAddendaPartPriceUnitary);

        jPanel49.add(jPanel52);

        jPanel48.add(jPanel49, java.awt.BorderLayout.NORTH);

        jpAddendaData.add(jPanel48, java.awt.BorderLayout.CENTER);

        jTabbedPane.addTab("Datos CFD", jpAddendaData);

        jpRegistry.add(jTabbedPane, java.awt.BorderLayout.CENTER);
        jTabbedPane.getAccessibleContext().setAccessibleName("Precios");

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

        setSize(new java.awt.Dimension(1040, 679));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        windowActivated();
    }//GEN-LAST:event_formWindowActivated

    private void initComponentsExtra() {
        int i = 0;
        STableColumnForm[] aoTableColumns = null;
        moParamsItemPriceList = null;

        mbRightPriceListForPurchases = miClient.getSessionXXX().getUser().hasRight(miClient, SDataConstantsSys.PRV_MKT_PLIST_PUR).HasRight;
        mbRightPriceListForSales = miClient.getSessionXXX().getUser().hasRight(miClient, SDataConstantsSys.PRV_MKT_PLIST_SAL).HasRight;
        mnPricePolicyForPurchases = miClient.getSessionXXX().getParamsCompany().getPricePolicyForPurchases();
        mnPricePolicyForSales = miClient.getSessionXXX().getParamsCompany().getPricePolicyForSales();

        mvFields = new Vector<>();
        moFieldFkItemId = new SFormField(miClient, SLibConstants.DATA_TYPE_KEY, true, jcbFkItemId, jlFkItemId);
        moFieldFkItemId.setPickerButton(jbFkItemId);
        moFieldKey = new SFormField(miClient, SLibConstants.DATA_TYPE_STRING, true, jtfKey, jlKey);
        moFieldKey.setLengthMax(35);
        moFieldConcept = new SFormField(miClient, SLibConstants.DATA_TYPE_STRING, true, jtfConcept, jlConcept);
        moFieldConcept.setLengthMax(130);
        moFieldFkOriginalUnitId = new SFormField(miClient, SLibConstants.DATA_TYPE_KEY, true, jcbFkOriginalUnitId, jlFkOriginalUnitId);
        moFieldFkOriginalUnitId.setPickerButton(jbFkOriginalUnitId);
        moFieldIsDiscountDocApplying = new SFormField(miClient, SLibConstants.DATA_TYPE_BOOLEAN, true, jckIsDiscountDocApplying);
        moFieldIsDiscountUnitaryPercentage = new SFormField(miClient, SLibConstants.DATA_TYPE_BOOLEAN, true, jckIsDiscountUnitaryPercentage);
        moFieldDiscountUnitaryPercentage = new SFormField(miClient, SLibConstants.DATA_TYPE_FLOAT, false, jtfDiscountUnitaryPercentage, jckIsDiscountUnitaryPercentage);
        moFieldDiscountUnitaryPercentage.setIsPercent(true);
        moFieldDiscountUnitaryPercentage.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsPercentageFormat());
        moFieldIsDiscountEntryPercentage = new SFormField(miClient, SLibConstants.DATA_TYPE_BOOLEAN, true, jckIsDiscountEntryPercentage);
        moFieldDiscountEntryPercentage = new SFormField(miClient, SLibConstants.DATA_TYPE_FLOAT, false, jtfDiscountEntryPercentage, jckIsDiscountEntryPercentage);
        moFieldDiscountEntryPercentage.setIsPercent(true);
        moFieldDiscountEntryPercentage.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsPercentageFormat());
        moFieldOriginalQuantity = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, true, jtfOriginalQuantity, jlOriginalQuantity);
        moFieldOriginalQuantity.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsQuantityFormat());
        moFieldOriginalPriceUnitaryCy = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, false, jtfOriginalPriceUnitaryCy, jlOriginalPriceUnitaryCy);
        moFieldOriginalPriceUnitaryCy.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsValueUnitaryFormat());
        moFieldOriginalDiscountUnitaryCy = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, false, jtfOriginalDiscountUnitaryCy, jlOriginalDiscountUnitaryCy);
        moFieldOriginalDiscountUnitaryCy.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsValueUnitaryFormat());
        moFieldDiscountEntryCy = new SFormField(miClient, SLibConstants.DATA_TYPE_FLOAT, false, jtfDiscountEntryCy, jlDiscountEntry);
        moFieldDiscountDocCy = new SFormField(miClient, SLibConstants.DATA_TYPE_FLOAT, false, jtfDiscountDocCy, jlDiscountDoc);
        moFieldSurplusPercentage = new SFormField(miClient, SLibConstants.DATA_TYPE_FLOAT, false, jtfSurplusPercentage, jckIsSurplusPercentageApplying);
        moFieldSurplusPercentage.setIsPercent(true);
        moFieldSurplusPercentage.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsPercentageFormat());
        
        moFieldIsDpsPriceVariable = new SFormField(miClient, SLibConstants.DATA_TYPE_BOOLEAN, false, jckIsDpsReqMonthDelivery);
        moFieldIsDpsPriceVariable.setBoolean(true);
        
        moFieldDpsContractBase = new SFormField(miClient, SLibConstants.DATA_TYPE_FLOAT, false, jtfDpsContractBase, jlDpsContractBase);
        moFieldDpsContractBase.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsValueFormat());
        moFieldDpsContractFuture = new SFormField(miClient, SLibConstants.DATA_TYPE_FLOAT, false, jtfDpsContractFuture, jlDpsContractFuture);
        moFieldDpsContractFuture.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsValueFormat());
        moFieldDpsContractFactor = new SFormField(miClient, SLibConstants.DATA_TYPE_FLOAT, false, jtfDpsContractFactor, jlDpsContractFactor);
        moFieldDpsContractFactor.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsValueFormat());
        moFieldDpsContractFactor.setDefaultValue(0.10d);
        moFieldContractPriceReferenceNumbrer = new SFormField(miClient, SLibConstants.DATA_TYPE_STRING, false, jtfContractPriceNumbrerReference, jlContractPriceNumbrerReference);
        moFieldContractPriceYear = new SFormField(miClient, SLibConstants.DATA_TYPE_INTEGER, false, jtfContractPriceYear, jlContractPriceYear);
        moFieldContractPriceYear.setDecimalFormat(miClient.getSessionXXX().getFormatters().getYearFormat());
        moFieldContractPriceYear.setDefaultValue(SLibTimeUtilities.digestYearMonth(miClient.getSession().getCurrentDate())[0]);
        moFieldContractPriceYear.setIntegerMin(2000);
        moFieldContractPriceYear.setIntegerMax(2100);
        moFieldContractPriceMonth = new SFormField(miClient, SLibConstants.DATA_TYPE_INTEGER, false, jtfContractPriceMonth, jlContractPriceMonth);
        moFieldContractPriceMonth.setDecimalFormat(miClient.getSessionXXX().getFormatters().getMonthFormat());
        moFieldContractPriceMonth.setDefaultValue(SLibTimeUtilities.digestYearMonth(miClient.getSession().getCurrentDate())[1]);
        moFieldContractPriceMonth.setIntegerMin(1);
        moFieldContractPriceMonth.setIntegerMax(12);
        moFieldPriceOriginalQuantity = new SFormField(miClient, SLibConstants.DATA_TYPE_FLOAT, false, jtfPriceOriginalQuantity, jlPriceOriginalQuantity);
        moFieldPriceOriginalQuantity.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsQuantityFormat());
        moFieldPriceOriginalPriceUnitaryCy = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, false, jtfPriceOriginalPriceUnitaryCy, jlPriceOriginalPriceUnitaryCy);
        moFieldPriceOriginalPriceUnitaryCy.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsValueUnitaryFormat());
        moFieldContractBase = new SFormField(miClient, SLibConstants.DATA_TYPE_FLOAT, false, jtfContractBase, jlContractBase);
        moFieldContractBase.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsValueFormat());
        moFieldContractFuture = new SFormField(miClient, SLibConstants.DATA_TYPE_FLOAT, false, jtfContractFuture, jlContractFuture);
        moFieldContractFuture.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsValueFormat());
        moFieldContractFactor = new SFormField(miClient, SLibConstants.DATA_TYPE_FLOAT, false, jtfContractFactor, jlContractFactor);
        moFieldContractFactor.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsValueFormat());
                
        moFieldFkVehicleTypeId_n = new SFormField(miClient, SLibConstants.DATA_TYPE_KEY, false, jcbFkVehicleTypeId_n, jlFkVehicleTypeId_n);
        moFieldVehicleNumber = new SFormField(miClient, SLibConstants.DATA_TYPE_STRING, false, jtfVehicleNumber, jlVehicleNumber);
        moFieldVehicleNumber.setLengthMax(15);
        moFieldSecuritySeal = new SFormField(miClient, SLibConstants.DATA_TYPE_STRING, false, jtfSecuritySeal, jlSecuritySeal);
        moFieldSecuritySeal.setLengthMax(50);
        moFieldTicket = new SFormField(miClient, SLibConstants.DATA_TYPE_STRING, false, jtfTicket, jlTicket);
        moFieldTicket.setLengthMax(25);
        moFieldLength = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, true, jtfLength, jlLength);
        moFieldLength.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsLengthFormat());
        moFieldSurface = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, true, jtfSurface, jlSurface);
        moFieldSurface.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsSurfaceFormat());
        moFieldVolume = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, true, jtfVolume, jlVolume);
        moFieldVolume.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsVolumeFormat());
        moFieldMass = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, true, jtfMass, jlMass);
        moFieldMass.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsMassFormat());
        moFieldWeightGross = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, false, jtfWeigthGrossRo, jlWeigthGross);
        moFieldWeightGross.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsWeigthGrossFormat());
        moFieldWeightDelivery = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, false, jtfWeigthDeliveryRo, jlWeigthDelivery);
        moFieldWeightDelivery.setDecimalFormat(miClient.getSessionXXX().getFormatters().getDecimalsWeightDeliveryFormat());
        moFieldIsDiscountRetailChain = new SFormField(miClient, SLibConstants.DATA_TYPE_BOOLEAN, true, jckIsDiscountRetailChain);
        moFieldIsTaxesAutomaticApplying = new SFormField(miClient, SLibConstants.DATA_TYPE_BOOLEAN, true, jckIsTaxesAutomaticApplying);
        moFieldIsInventoriable = new SFormField(miClient, SLibConstants.DATA_TYPE_BOOLEAN, true, jckIsInventoriable);
        moFieldIsDeleted = new SFormField(miClient, SLibConstants.DATA_TYPE_BOOLEAN, true, jckIsDeleted);
        moFieldFkTaxRegionId = new SFormField(miClient, SLibConstants.DATA_TYPE_KEY, true, jcbFkTaxRegionId, jlFkTaxRegionId);
        moFieldFkTaxRegionId.setPickerButton(jbFkTaxRegionId);
        moFieldFkItemReferenceId_n = new SFormField(miClient, SLibConstants.DATA_TYPE_KEY, true, jcbFkItemReferenceId_n, jlFkItemReferenceId_n);
        moFieldFkItemReferenceId_n.setPickerButton(jbFkItemReferenceId_n);
        moFieldReference = new SFormField(miClient, SLibConstants.DATA_TYPE_STRING, false, jtfReference, jlReference);
        moFieldReference.setLengthMax(25);
        moFieldAddendaNumberPosition = new SFormField(miClient, SLibConstants.DATA_TYPE_INTEGER, true, jtfAddendaNumberPosition, jlAddendaNumberPosition);
        moFieldAddendaNumberPosition.setTabbedPaneIndex(5, jTabbedPane);
        moFieldAddendaNumberPosition.setLengthMax(5);
        moFieldAddendaCenter = new SFormField(miClient, SLibConstants.DATA_TYPE_STRING, true, jtfAddendaCenter, jlAddendaCenter);
        moFieldAddendaCenter.setTabbedPaneIndex(5, jTabbedPane);
        moFieldAddendaCenter.setLengthMax(15);
        moFieldAddendaEntryNumber = new SFormField(miClient, SLibConstants.DATA_TYPE_INTEGER, true, jtfAddendaEntryNumber, jlAddendaEntryNumber);
        moFieldAddendaEntryNumber.setTabbedPaneIndex(5, jTabbedPane);
        moFieldAddendaEntryNumber.setLengthMax(5);
        moFieldAddendaFkBarcode = new SFormField(miClient, SLibConstants.DATA_TYPE_STRING, false, (JTextField) jcbAddendaFkBarcode.getEditor().getEditorComponent(), jlAddendaFkBarcode);
        moFieldAddendaFkBarcode.setLengthMax(20);
        moFieldAddendaFkBarcode.setTabbedPaneIndex(5, jTabbedPane);
        moFieldAddendaOrder = new SFormField(miClient, SLibConstants.DATA_TYPE_STRING, false, jtfAddendaOrder, jlAddendaOrder);
        moFieldAddendaOrder.setLengthMax(20);
        moFieldAddendaOrder.setTabbedPaneIndex(5, jTabbedPane);
        moFieldAddendaCages = new SFormField(miClient, SLibConstants.DATA_TYPE_INTEGER, false, jtfAddendaCages, jlAddendaCages);
        moFieldAddendaCages.setTabbedPaneIndex(5, jTabbedPane);
        moFieldAddendaCagePriceUnitary = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, false, jtfAddendaCagePriceUnitary, jlAddendaCagePriceUnitary);
        moFieldAddendaCagePriceUnitary.setTabbedPaneIndex(5, jTabbedPane);
        moFieldAddendaParts = new SFormField(miClient, SLibConstants.DATA_TYPE_INTEGER, false, jtfAddendaParts, jlAddendaParts);
        moFieldAddendaParts.setTabbedPaneIndex(5, jTabbedPane);
        moFieldAddendaPartPriceUnitary = new SFormField(miClient, SLibConstants.DATA_TYPE_DOUBLE, false, jtfAddendaPartPriceUnitary, jlAddendaPartPriceUnitary);
        moFieldAddendaPartPriceUnitary.setTabbedPaneIndex(5, jTabbedPane);

        mvFields.add(moFieldFkItemId);
        mvFields.add(moFieldKey);
        mvFields.add(moFieldConcept);
        mvFields.add(moFieldFkOriginalUnitId);
        mvFields.add(moFieldIsDiscountDocApplying);
        mvFields.add(moFieldIsDiscountUnitaryPercentage);
        mvFields.add(moFieldDiscountUnitaryPercentage);
        mvFields.add(moFieldIsDiscountEntryPercentage);
        mvFields.add(moFieldDiscountEntryPercentage);
        mvFields.add(moFieldOriginalQuantity);
        mvFields.add(moFieldOriginalPriceUnitaryCy);
        mvFields.add(moFieldOriginalDiscountUnitaryCy);
        mvFields.add(moFieldDiscountEntryCy);
        mvFields.add(moFieldDiscountDocCy);
        mvFields.add(moFieldSurplusPercentage);
        
        /*
        mvFields.add(moFieldIsDpsPriceVariable);
        mvFields.add(moFieldDpsContractBase);
        mvFields.add(moFieldDpsContractFuture);
        mvFields.add(moFieldDpsContractFactor);
        mvFields.add(moFieldDpsContractExchangeRate);
        */
        
        mvFields.add(moFieldContractPriceReferenceNumbrer);
        mvFields.add(moFieldContractPriceYear);
        mvFields.add(moFieldContractPriceMonth);
        mvFields.add(moFieldPriceOriginalQuantity);
        mvFields.add(moFieldPriceOriginalPriceUnitaryCy);
        mvFields.add(moFieldContractBase);
        mvFields.add(moFieldContractFuture);
        mvFields.add(moFieldContractFactor);
        
        mvFields.add(moFieldFkVehicleTypeId_n);
        mvFields.add(moFieldVehicleNumber);
        mvFields.add(moFieldSecuritySeal);
        mvFields.add(moFieldTicket);
        mvFields.add(moFieldLength);
        mvFields.add(moFieldSurface);
        mvFields.add(moFieldVolume);
        mvFields.add(moFieldMass);
        mvFields.add(moFieldWeightGross);
        mvFields.add(moFieldWeightDelivery);
        mvFields.add(moFieldIsDiscountRetailChain);
        mvFields.add(moFieldIsTaxesAutomaticApplying);
        mvFields.add(moFieldIsInventoriable);
        mvFields.add(moFieldIsDeleted);
        mvFields.add(moFieldFkTaxRegionId);
        mvFields.add(moFieldFkItemReferenceId_n);
        mvFields.add(moFieldReference);
        mvFields.add(moFieldAddendaNumberPosition);
        mvFields.add(moFieldAddendaCenter);
        mvFields.add(moFieldAddendaEntryNumber);
        mvFields.add(moFieldAddendaFkBarcode);
        mvFields.add(moFieldAddendaOrder);
        mvFields.add(moFieldAddendaCages);
        mvFields.add(moFieldAddendaCagePriceUnitary);
        mvFields.add(moFieldAddendaParts);
        mvFields.add(moFieldAddendaPartPriceUnitary);

        // Taxes pane:

        moPaneTaxes = new STablePane(miClient);
        jpTaxes.add(moPaneTaxes, BorderLayout.CENTER);

        i = 0;
        aoTableColumns = new STableColumnForm[9];
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_STRING, "Impuesto", 200);
        aoTableColumns[i] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Tasa", STableConstants.WIDTH_PERCENTAGE);
        aoTableColumns[i++].setCellRenderer(miClient.getSessionXXX().getFormatters().getTableCellRendererPercentage());
        aoTableColumns[i] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Valor u.", STableConstants.WIDTH_VALUE_UNITARY);
        aoTableColumns[i++].setCellRenderer(miClient.getSessionXXX().getFormatters().getTableCellRendererValueUnitary());
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Valor", STableConstants.WIDTH_VALUE);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Monto $", STableConstants.WIDTH_VALUE);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Monto mon $", STableConstants.WIDTH_VALUE);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_STRING, "Tipo impuesto", 150);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_STRING, "Cálculo impuesto", 150);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_STRING, "Aplicación impuesto", 150);

        for (i = 0; i < aoTableColumns.length; i++) {
            moPaneTaxes.addTableColumn(aoTableColumns[i]);
        }

        // Commissions pane:

        moPaneCommissions = new STablePane(miClient);
        jpCommissions.add(moPaneCommissions, BorderLayout.CENTER);

        i = 0;
        aoTableColumns = new STableColumnForm[6];
        aoTableColumns[i] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Porcentaje", STableConstants.WIDTH_PERCENTAGE);
        aoTableColumns[i++].setCellRenderer(miClient.getSessionXXX().getFormatters().getTableCellRendererPercentage());
        aoTableColumns[i] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Valor u.", STableConstants.WIDTH_VALUE_UNITARY);
        aoTableColumns[i++].setCellRenderer(miClient.getSessionXXX().getFormatters().getTableCellRendererValueUnitary());
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Valor", STableConstants.WIDTH_VALUE);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Monto $", STableConstants.WIDTH_VALUE);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Monto mon $", STableConstants.WIDTH_VALUE);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_STRING, "Tipo comisión", 150);

        for (i = 0; i < aoTableColumns.length; i++) {
            moPaneCommissions.addTableColumn(aoTableColumns[i]);
        }

        // Notes pane:

        moPaneGridNotes = new STablePaneGrid(miClient);
        moPaneGridNotes.setDoubleClickAction(this, "publicActionNotesEdit");
        jpNotes.add(moPaneGridNotes, BorderLayout.CENTER);
        
        i = 0;
        aoTableColumns = new STableColumnForm[11];
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_STRING, "Notas", 500);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_BOOLEAN, "Todos los docs.", STableConstants.WIDTH_BOOLEAN_2X);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_BOOLEAN, "Impresión", STableConstants.WIDTH_BOOLEAN_2X);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_BOOLEAN, "XML CFD", STableConstants.WIDTH_BOOLEAN_2X);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_BOOLEAN, "Eliminado", STableConstants.WIDTH_BOOLEAN);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_STRING, "Usr. creación", STableConstants.WIDTH_USER);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_DATE_TIME, "Creación", STableConstants.WIDTH_DATE_TIME);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_STRING, "Usr. modificación", STableConstants.WIDTH_USER);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_DATE_TIME, "Modificación", STableConstants.WIDTH_DATE_TIME);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_STRING, "Usr. eliminación", STableConstants.WIDTH_USER);
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_DATE_TIME, "Eliminación", STableConstants.WIDTH_DATE_TIME);

        for (i = 0; i < aoTableColumns.length; i++) {
            moPaneGridNotes.addTableColumn(aoTableColumns[i]);
        }
        
        // Prices pane:
        
        moPaneGridPrices = new STablePaneGrid(miClient);
        moPaneGridPrices.setDoubleClickAction(this, "publicActionPriceEdit");
        jpPrices.add(moPaneGridPrices, BorderLayout.CENTER);
        
        i = 0;
        aoTableColumns = new STableColumnForm[10];
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_STRING, "No. Órd.", STableConstants.WIDTH_DOC_NUM_REF);
        aoTableColumns[i] = new STableColumnForm(SLibConstants.DATA_TYPE_INTEGER, "Año", STableConstants.WIDTH_YEAR);
        aoTableColumns[i++].setCellRenderer(miClient.getSessionXXX().getFormatters().getTableCellRendererYear());
        aoTableColumns[i] = new STableColumnForm(SLibConstants.DATA_TYPE_INTEGER, "Mes", STableConstants.WIDTH_YEAR);
        aoTableColumns[i++].setCellRenderer(miClient.getSessionXXX().getFormatters().getTableCellRendererMonth());
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_BOOLEAN, "Precio directo", STableConstants.WIDTH_BOOLEAN_3X);
        aoTableColumns[i] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Cantidad", STableConstants.WIDTH_QUANTITY_2X);
        aoTableColumns[i++].setCellRenderer(miClient.getSessionXXX().getFormatters().getTableCellRendererQuantity());
        aoTableColumns[i] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Precio u. $", STableConstants.WIDTH_QUANTITY_2X);
        aoTableColumns[i++].setCellRenderer(miClient.getSessionXXX().getFormatters().getTableCellRendererValueUnitary());
        aoTableColumns[i] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Base", STableConstants.WIDTH_VALUE_2X);
        aoTableColumns[i++].setCellRenderer(miClient.getSessionXXX().getFormatters().getTableCellRendererNumberDouble());
        aoTableColumns[i] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Futuro", STableConstants.WIDTH_VALUE_2X);
        aoTableColumns[i++].setCellRenderer(miClient.getSessionXXX().getFormatters().getTableCellRendererNumberDouble());
        aoTableColumns[i] = new STableColumnForm(SLibConstants.DATA_TYPE_DOUBLE, "Factor ajuste", STableConstants.WIDTH_VALUE_2X);
        aoTableColumns[i++].setCellRenderer(miClient.getSessionXXX().getFormatters().getTableCellRendererValueUnitary());
        aoTableColumns[i++] = new STableColumnForm(SLibConstants.DATA_TYPE_BOOLEAN, "Eliminado", STableConstants.WIDTH_BOOLEAN);
        
        for (i = 0; i < aoTableColumns.length; i++) {
            moPaneGridPrices.addTableColumn(aoTableColumns[i]);
        }
        
        // Complimentary dialogs and forms:

        moDialogPriceUnitaryWizard = new SDialogPriceUnitaryWizard(miClient);
        moDialogItemPriceHistory = new SDialogItemPriceHistory(miClient);
        moFormNotes = new SFormDpsEntryNotes(miClient);

        // Miscellaneous:

        try {
            moPanelFkCostCenterId_n = new SPanelAccount(miClient, SDataConstants.FIN_CC, false, false, false);
            moPanelFkCostCenterId_n.setLabelsWidth(100);
        }
        catch (Exception e) {
            SLibUtilities.renderException(this, e);
        }

        jpCostCenter.remove(jlDummyCostCenter);
        jpCostCenter.add(moPanelFkCostCenterId_n, BorderLayout.NORTH);

        // Action listeners:

        jbOk.addActionListener(this);
        jbCancel.addActionListener(this);
        jbKey.addActionListener(this);
        jbConcept.addActionListener(this);
        jbFkItemId.addActionListener(this);
        jbItemBizPartnerDescription.addActionListener(this);
        jbFkOriginalUnitId.addActionListener(this);
        jbPriceUnitaryCyWizard.addActionListener(this);
        jbPriceHistory.addActionListener(this);
        jbNotesNew.addActionListener(this);
        jbNotesEdit.addActionListener(this);
        jbNotesDelete.addActionListener(this);
        jbSystemNotes.addActionListener(this);
        jbFkTaxRegionId.addActionListener(this);
        jbFkItemReferenceId_n.addActionListener(this);
        jtbNotesFilter.addActionListener(this);
        
        jbPriceNew.addActionListener(this);
        jbPriceSave.addActionListener(this);
        jbClearPriceFields.addActionListener(this);
        jbGridPriceNew.addActionListener(this);
        jbGridPriceEdit.addActionListener(this);
        jbGridPriceDelete.addActionListener(this);
        jtbGridPriceFilter.addActionListener(this);

        // Focus listeners:

        jcbFkOriginalUnitId.addFocusListener(this);
        jtfDiscountUnitaryPercentage.addFocusListener(this);
        jtfDiscountEntryPercentage.addFocusListener(this);
        jtfOriginalQuantity.addFocusListener(this);
        jtfOriginalPriceUnitaryCy.addFocusListener(this);
        jtfOriginalDiscountUnitaryCy.addFocusListener(this);
        jtfDiscountEntryCy.addFocusListener(this);
        jtfDiscountDocCy.addFocusListener(this);
        jtfContractBase.addFocusListener(this);
        jtfContractFuture.addFocusListener(this);
        jtfContractFactor.addFocusListener(this);
                
        // Item listeners:

        jckIsDiscountDocApplying.addItemListener(this);
        jckIsDiscountUnitaryPercentage.addItemListener(this);
        jckIsDiscountEntryPercentage.addItemListener(this);
        jckIsSurplusPercentageApplying.addItemListener(this);
        jckIsTaxesAutomaticApplying.addItemListener(this);
        jcbFkItemId.addItemListener(this);
        jcbFkOriginalUnitId.addItemListener(this);
        jcbFkTaxRegionId.addItemListener(this);
        
        jckIsDpsReqMonthDelivery.addItemListener(this);
        jckIsDirectPrice.addItemListener(this);
        jckChangePrice.addItemListener(this);

        SFormUtilities.createActionMap(rootPane, this, "publicPriceUnitaryCyWizard", "priceUnitaryCyWizard", KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK);
        SFormUtilities.createActionMap(rootPane, this, "publicActionNotesNew", "notesNew", KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        SFormUtilities.createActionMap(rootPane, this, "publicActionNotesEdit", "notesEdit", KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK);
        SFormUtilities.createActionMap(rootPane, this, "publicActionNotesDelete", "notesDelete", KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK);
        SFormUtilities.createActionMap(rootPane, this, "publicActionNotesFilter", "notesFilter", KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK);
        
        SFormUtilities.createActionMap(rootPane, this, "publicActionPriceNew", "priceNew", KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
        SFormUtilities.createActionMap(rootPane, this, "publicActionPriceEdit", "priceEdit", KeyEvent.VK_M, KeyEvent.CTRL_DOWN_MASK);
        SFormUtilities.createActionMap(rootPane, this, "publicActionPriceDelete", "priceDelete", KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK);
        SFormUtilities.createActionMap(rootPane, this, "publicActionPriceFilter", "priceFilter", KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK);

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
            if (mnFormStatus == SLibConstants.FORM_STATUS_READ_ONLY) {
                jbCancel.requestFocus();
                enablePriceContractFields(false);
            }
            else {
                if (jcbFkItemId.isEnabled()) {
                    jcbFkItemId.requestFocus();
                }
                else if (jtfOriginalPriceUnitaryCy.isFocusable() && !jtfOriginalQuantity.isFocusable()) {
                    jtfOriginalPriceUnitaryCy.requestFocus();
                }
                else if (jtfOriginalQuantity.isFocusable() ) {
                    jtfOriginalQuantity.requestFocus();
                }
                else {
                    jbCancel.requestFocus();
                }
            }

            if (mbIsSales && (mbIsDoc || mbIsAdj) && mbEnableDataAddenda) {
                jTabbedPane.setEnabledAt(5, true);
                renderAddendaDatas();
            }
            else {
                jTabbedPane.setEnabledAt(5, false);
            } 
            
            if (mbIsCon) {
                moFieldIsDpsPriceVariable.setBoolean(true);
                enablePriceContractFields(true);
                
                if (moParamDps.getFkCurrencyId() == SModSysConsts.CFGU_CUR_USD) {
                    moFieldDpsContractFactor.setDouble(0.10d);
                }else{
                    moFieldDpsContractFactor.setDouble(0.0d);
                }
                
                jTabbedPane.setEnabledAt(3, true);
            }
            else {
                jTabbedPane.setEnabledAt(3, false);
                moFieldIsDpsPriceVariable.setBoolean(false);
                moFieldDpsContractFactor.setDouble(0.0d);
                enablePriceContractFields(false);
            }
        }
    }

    private boolean isTextFieldForDpsEntryTotal(javax.swing.JTextField textField) {
        return textField == jtfDiscountUnitaryPercentage || textField == jtfDiscountEntryPercentage ||
                    textField == jtfOriginalQuantity || textField == jtfOriginalPriceUnitaryCy || textField == jtfOriginalDiscountUnitaryCy ||
                    textField == jtfDiscountEntryCy || textField == jtfDiscountDocCy;
    }

    private void calculateTotal() {
        int i = 0;

        moDpsEntry.setIsDiscountDocApplying(moFieldIsDiscountDocApplying.getBoolean());
        moDpsEntry.setIsDiscountUnitaryPercentage(moFieldIsDiscountUnitaryPercentage.getBoolean());
        moDpsEntry.setIsDiscountUnitaryPercentageSystem(moFieldIsDiscountUnitaryPercentage.getBoolean()); // XXX
        moDpsEntry.setIsDiscountEntryPercentage(moFieldIsDiscountEntryPercentage.getBoolean());
        moDpsEntry.setDiscountUnitaryPercentage(!moDpsEntry.getIsDiscountUnitaryPercentage() ? 0 : moFieldDiscountUnitaryPercentage.getFloat());
        moDpsEntry.setDiscountUnitaryPercentageSystem(!moDpsEntry.getIsDiscountUnitaryPercentageSystem() ? 0 : moFieldDiscountUnitaryPercentage.getFloat()); // XXX
        moDpsEntry.setDiscountEntryPercentage(!moDpsEntry.getIsDiscountEntryPercentage() ? 0 : moFieldDiscountEntryPercentage.getFloat());

        moDpsEntry.setOriginalQuantity(moFieldOriginalQuantity.getDouble());
        moDpsEntry.setOriginalPriceUnitaryCy(moFieldOriginalPriceUnitaryCy.getDouble());
        moDpsEntry.setOriginalPriceUnitarySystemCy(moFieldOriginalPriceUnitaryCy.getDouble()); // XXX
        moDpsEntry.setOriginalDiscountUnitaryCy(moFieldOriginalDiscountUnitaryCy.getDouble());
        moDpsEntry.setOriginalDiscountUnitarySystemCy(moFieldOriginalDiscountUnitaryCy.getDouble()); // XXX

        moDpsEntry.setDiscountEntryCy(moFieldDiscountEntryCy.getDouble());
        moDpsEntry.setDiscountDocCy(moFieldDiscountDocCy.getDouble());

        moDpsEntry.setIsTaxesAutomaticApplying(moFieldIsTaxesAutomaticApplying.getBoolean());
        
        moDpsEntry.setAuxPreserveQuantity(jckAuxPreserveQuantity.isSelected());

        if (moParamDps == null || moItem == null || moFieldFkOriginalUnitId.getKey() == null || moFieldFkTaxRegionId.getKey() == null) {
            // There is not any way to calculate DPS entry's value:

            moDpsEntry.setFkItemId(0);
            moDpsEntry.setFkUnitId(0);
            moDpsEntry.setFkOriginalUnitId(0);
            moDpsEntry.setFkTaxRegionId(0);

            moDpsEntry.setDbmsFkItemGenericId(0);

            moDpsEntry.resetValue();
            moPaneTaxes.clearTableRows();
            moPaneCommissions.clearTableRows();
        }
        else {
            // Calculate DPS entry's value:

            moDpsEntry.setFkItemId(moItem.getPkItemId());
            moDpsEntry.setFkUnitId(moItem.getFkUnitId());
            moDpsEntry.setFkOriginalUnitId(moFieldFkOriginalUnitId.getKeyAsIntArray()[0]);
            moDpsEntry.setFkTaxRegionId(moFieldFkTaxRegionId.getKeyAsIntArray()[0]);

            moDpsEntry.setDbmsFkItemGenericId(moItem.getFkItemGenericId());

            moDpsEntry.calculateTotal(miClient, moParamDps.getDate(),
                    moParamDps.getFkTaxIdentityEmisorTypeId(), moParamDps.getFkTaxIdentityReceptorTypeId(),
                    moParamDps.getIsDiscountDocPercentage(), moParamDps.getDiscountDocPercentage(), moParamDps.getExchangeRate());

            moPaneTaxes.clearTableRows();
            for (i = 0; i < moDpsEntry.getDbmsEntryTaxes().size(); i++) {
                moPaneTaxes.addTableRow(new SDataDpsEntryTaxRow(moDpsEntry.getDbmsEntryTaxes().get(i)));
            }
            moPaneTaxes.renderTableRows();
            moPaneTaxes.setTableRowSelection(0);

            moPaneCommissions.clearTableRows();
            for (i = 0; i < moDpsEntry.getDbmsEntryCommissions().size(); i++) {
                moPaneCommissions.addTableRow(new SDataDpsEntryCommissionsRow(moDpsEntry.getDbmsEntryCommissions().get(i)));
            }
            moPaneCommissions.renderTableRows();
            moPaneCommissions.setTableRowSelection(0);
        }

        if (moItem == null || mnParamAdjustmentTypeId == SDataConstantsSys.TRNS_TP_DPS_ADJ_DISC) {
            moFieldLength.setFieldValue(0d);
            moFieldSurface.setFieldValue(0d);
            moFieldVolume.setFieldValue(0d);
            moFieldMass.setFieldValue(0d);
            moFieldWeightGross.setFieldValue(0d);
            moFieldWeightDelivery.setFieldValue(0d);
        }
        else {
            if (moItem.getDbmsDataItemGeneric().getIsLengthApplying() && (moFieldLength.getDouble() == 0 || (!moItem.getIsLengthVariable() && !moItem.getDbmsDataItemGeneric().getIsLengthVariable()))) {
                moFieldLength.setFieldValue(moDpsEntry.getQuantity() * moItem.getLength());
            }
            if (moItem.getDbmsDataItemGeneric().getIsSurfaceApplying() && (moFieldSurface.getDouble() == 0 || (!moItem.getIsSurfaceVariable() && !moItem.getDbmsDataItemGeneric().getIsSurfaceVariable()))) {
                moFieldSurface.setFieldValue(moDpsEntry.getQuantity() * moItem.getSurface());
            }
            if (moItem.getDbmsDataItemGeneric().getIsVolumeApplying() && (moFieldVolume.getDouble() == 0 || (!moItem.getIsVolumeVariable() && !moItem.getDbmsDataItemGeneric().getIsVolumeVariable()))) {
                moFieldVolume.setFieldValue(moDpsEntry.getQuantity() * moItem.getVolume());
            }
            if (moItem.getDbmsDataItemGeneric().getIsMassApplying() && (moFieldMass.getDouble() == 0 || (!moItem.getIsMassVariable() && !moItem.getDbmsDataItemGeneric().getIsMassVariable()))) {
                moFieldMass.setFieldValue(moDpsEntry.getQuantity() * moItem.getMass());
            }
            if (moItem.getDbmsDataItemGeneric().getIsWeightGrossApplying()) {
                moFieldWeightGross.setFieldValue(moDpsEntry.getQuantity() * moItem.getWeightGross());
            }
            if (moItem.getDbmsDataItemGeneric().getIsWeightDeliveryApplying()) {
                moFieldWeightDelivery.setFieldValue(moDpsEntry.getQuantity() * moItem.getWeightDelivery());
            }
        }

        renderDpsEntryValue();
    }

    private void renderBasicSettings() {
        SDataUnitType type = new SDataUnitType();

        jtfCurrencySystemKeyRo.setText(miClient.getSessionXXX().getParamsErp().getDbmsDataCurrency().getKey());

        type = (SDataUnitType) SDataUtilities.readRegistry(miClient, SDataConstants.ITMU_TP_UNIT, new int[] { SDataConstantsSys.ITMU_TP_UNIT_LEN }, SLibConstants.EXEC_MODE_VERBOSE);
        jtfLengthUnitSymbolRo.setText(type == null ? "?" : type.getUnitBase());

        type = (SDataUnitType) SDataUtilities.readRegistry(miClient, SDataConstants.ITMU_TP_UNIT, new int[] { SDataConstantsSys.ITMU_TP_UNIT_SURF }, SLibConstants.EXEC_MODE_VERBOSE);
        jtfSurfaceUnitSymbolRo.setText(type == null ? "?" : type.getUnitBase());

        type = (SDataUnitType) SDataUtilities.readRegistry(miClient, SDataConstants.ITMU_TP_UNIT, new int[] { SDataConstantsSys.ITMU_TP_UNIT_VOL }, SLibConstants.EXEC_MODE_VERBOSE);
        jtfVolumeUnitSymbolRo.setText(type == null ? "?" : type.getUnitBase());

        type = (SDataUnitType) SDataUtilities.readRegistry(miClient, SDataConstants.ITMU_TP_UNIT, new int[] { SDataConstantsSys.ITMU_TP_UNIT_MASS }, SLibConstants.EXEC_MODE_VERBOSE);
        jtfMassUnitSymbolRo.setText(type == null ? "?" : type.getUnitBase());

        jtfWeigthGrossUnitSymbolRo.setText(type == null ? "?" : type.getUnitBase());
        jtfWeigthDeliveryUnitSymbolRo.setText(type == null ? "?" : type.getUnitBase());
    }

    private void renderDpsEntryValue() {
        DecimalFormat format = miClient.getSessionXXX().getFormatters().getDecimalsValueFormat();
        DecimalFormat formatUnitary = miClient.getSessionXXX().getFormatters().getDecimalsValueUnitaryFormat();

        jtfQuantityRo.setText(miClient.getSessionXXX().getFormatters().getDecimalsQuantityFormat().format(moDpsEntry.getQuantity()));
        jtfPriceUnitaryCyRo.setText(formatUnitary.format(moDpsEntry.getPriceUnitaryCy()));
        jtfDiscountUnitaryCyRo.setText(formatUnitary.format(moDpsEntry.getDiscountUnitaryCy()));
        jtfDiscountEntryCy.setText(format.format(moDpsEntry.getDiscountEntryCy()));
        jtfSubtotalProvisionalCy_rRo.setText(format.format(moDpsEntry.getSubtotalProvisionalCy_r()));
        jtfDiscountDocCy.setText(format.format(moDpsEntry.getDiscountDocCy()));
        jtfSubtotalCy_rRo.setText(format.format(moDpsEntry.getSubtotalCy_r()));
        jtfTaxChargedCy_rRo.setText(format.format(moDpsEntry.getTaxChargedCy_r()));
        jtfTaxRetainedCy_rRo.setText(format.format(moDpsEntry.getTaxRetainedCy_r()));
        jtfTotalCy_rRo.setText(format.format(moDpsEntry.getTotalCy_r()));
        jtfPriceUnitaryRealCy_rRo.setText(formatUnitary.format(moDpsEntry.getPriceUnitaryRealCy_r()));

        if (moParamDps == null || moParamDps.getFkCurrencyId() == miClient.getSessionXXX().getParamsErp().getFkCurrencyId()) {
            jtfPriceUnitaryRo.setText("");
            jtfDiscountUnitaryRo.setText("");
            jtfDiscountEntryRo.setText("");
            jtfSubtotalProvisional_rRo.setText("");
            jtfDiscountDocRo.setText("");
            jtfSubtotal_rRo.setText("");
            jtfTaxCharged_rRo.setText("");
            jtfTaxRetained_rRo.setText("");
            jtfTotal_rRo.setText("");
            jtfPriceUnitaryReal_rRo.setText("");
        }
        else {
            jtfPriceUnitaryRo.setText(formatUnitary.format(moDpsEntry.getPriceUnitary()));
            jtfDiscountUnitaryRo.setText(formatUnitary.format(moDpsEntry.getDiscountUnitary()));
            jtfDiscountEntryRo.setText(format.format(moDpsEntry.getDiscountEntry()));
            jtfSubtotalProvisional_rRo.setText(format.format(moDpsEntry.getSubtotalProvisional_r()));
            jtfDiscountDocRo.setText(format.format(moDpsEntry.getDiscountDoc()));
            jtfSubtotal_rRo.setText(format.format(moDpsEntry.getSubtotal_r()));
            jtfTaxCharged_rRo.setText(format.format(moDpsEntry.getTaxCharged_r()));
            jtfTaxRetained_rRo.setText(format.format(moDpsEntry.getTaxRetained_r()));
            jtfTotal_rRo.setText(format.format(moDpsEntry.getTotal_r()));
            jtfPriceUnitaryReal_rRo.setText(formatUnitary.format(moDpsEntry.getPriceUnitaryReal_r()));
        }
    }

    private void renderItem(boolean preserveFields, boolean calculate) {
        boolean isDiscountRetailChain = false;
        Object keyItem = null;
        Object keyTaxRegion = null;
        String item = "";
        String keyItemBp = "";
        int unitItemBp = 0;

        mbUpdatingForm = true;

        if (preserveFields) {
            isDiscountRetailChain = moFieldIsDiscountRetailChain.getBoolean();
            keyItem = moFieldFkItemId.getKey();
            keyTaxRegion = moFieldFkTaxRegionId.getKey();
        }

        // Every time a different item is selected, form fields are cleared:

        for (int i = 0; i < mvFields.size(); i++) {
            mvFields.get(i).resetField();
        }

        // Restore values currently selected:

        if (preserveFields) {
            moFieldIsDiscountRetailChain.setFieldValue(isDiscountRetailChain);
            moFieldFkItemId.setFieldValue(keyItem);
            moFieldFkTaxRegionId.setFieldValue(keyTaxRegion);
        }

        // Default values:

        jbItemBizPartnerDescription.setEnabled(false);
        moFieldIsTaxesAutomaticApplying.setFieldValue(true);
        moPanelFkCostCenterId_n.resetPanel();

        // Render item:

        if (moItem == null) {
            // Clear original unit combo box:

            mnAuxCurrentUnitTypeId = SDataConstantsSys.UNDEFINED;
            mnAuxCurrentUnitAlternativeTypeId = SDataConstantsSys.UNDEFINED;
            
            jcbFkOriginalUnitId.removeAllItems();

            // Clear fields:

            jtfKey.setToolTipText(null);
            jtfConcept.setToolTipText(null);

            jckIsBulk.setSelected(false);
            jckAuxPreserveQuantity.setSelected(false);
            jckIsSurplusPercentageApplying.setSelected(false);

            jcbAddendaFkBarcode.removeAllItems();
            
            // Disable fields:

            //jckIsBulk.setEnabled(false); // allways remains disabled
            
            jtfKey.setEditable(false);
            jtfKey.setFocusable(false);
            jbKey.setEnabled(false);
            jtfConcept.setEditable(false);
            jtfConcept.setFocusable(false);
            jbConcept.setEnabled(false);
            jcbFkOriginalUnitId.setEnabled(false);
            jbFkOriginalUnitId.setEnabled(false);

            jckAuxPreserveQuantity.setEnabled(false);
            
            jckIsDiscountDocApplying.setEnabled(false);
            jckIsDiscountUnitaryPercentage.setEnabled(false);
            jckIsDiscountEntryPercentage.setEnabled(false);

            jtfOriginalQuantity.setEditable(false);
            jtfOriginalQuantity.setFocusable(false);
            jtfOriginalPriceUnitaryCy.setEditable(false);
            jtfOriginalPriceUnitaryCy.setFocusable(false);
            jtfOriginalDiscountUnitaryCy.setEditable(false);
            jtfOriginalDiscountUnitaryCy.setFocusable(false);
            jbPriceUnitaryCyWizard.setEnabled(false);
            jbPriceHistory.setEnabled(false);

            jtfDiscountEntryCy.setEditable(false);
            jtfDiscountEntryCy.setFocusable(false);
            jtfDiscountDocCy.setEditable(false);
            jtfDiscountDocCy.setFocusable(false);

            jtfLength.setEnabled(false);
            jtfSurface.setEnabled(false);
            jtfVolume.setEnabled(false);
            jtfMass.setEnabled(false);

            jckIsSurplusPercentageApplying.setEnabled(false);
            jtfSurplusPercentage.setEditable(false);
            jtfSurplusPercentage.setFocusable(false);

            jcbFkItemReferenceId_n.setEnabled(false);
            jbFkItemReferenceId_n.setEnabled(false);

            jcbAddendaFkBarcode.setEnabled(false);
            jtfAddendaOrder.setEnabled(false);
            jtfAddendaCages.setEnabled(false);
            jtfAddendaCagePriceUnitary.setEnabled(false);
            jtfAddendaParts.setEnabled(false);
            jtfAddendaPartPriceUnitary.setEnabled(false);

            moPaneTaxes.clearTableRows();
            moPaneCommissions.clearTableRows();

            moParamsItemPriceList = null;
        }
        else {
            // Item concept:

            if (moParamDps.getFkLanguajeId() != miClient.getSessionXXX().getParamsErp().getFkLanguageId()) {
                for (SDataItemForeignLanguage description : moItem.getDbmsItemForeignLanguageDescriptions()) {
                    if (moParamDps.getFkLanguajeId() == description.getPkLanguageId() && !description.getIsDeleted()) {
                        item = description.getItem().length() <= moFieldConcept.getLengthMax() ? description.getItem() : description.getItemShort();
                        break;
                    }
                }
            }

            if (moParamBizPartner.getDbmsItemBizPartnerDescription().size() > 0) {
                for (SDataItemBizPartnerDescription description : moParamBizPartner.getDbmsItemBizPartnerDescription()) {
                    if (moItem.getPkItemId() == description.getPkItemId() && !description.getIsDeleted()) {
                        keyItemBp = description.getKey();
                        item = description.getItem().length() <= moFieldConcept.getLengthMax() ? description.getItem() : description.getItemShort();
                        unitItemBp = description.getFkUnitId();

                        jbItemBizPartnerDescription.setEnabled(true);
                        break;
                    }
                }
            }

            if (item.length() == 0) {
                item = moItem.getItem().length() <= moFieldConcept.getLengthMax() ? moItem.getItem() : moItem.getItemShort();
            }

            // Initialize original unit combo box:

            if (mnAuxCurrentUnitTypeId != moItem.getDbmsDataUnit().getFkUnitTypeId() || (mnAuxCurrentUnitTypeId == moItem.getDbmsDataUnit().getFkUnitTypeId() &&
                    mnAuxCurrentUnitAlternativeTypeId != moItem.getFkUnitAlternativeTypeId())) {
                mnAuxCurrentUnitTypeId = moItem.getDbmsDataUnit().getFkUnitTypeId();
                mnAuxCurrentUnitAlternativeTypeId = moItem.getFkUnitAlternativeTypeId();

                if (moItem.getFkUnitAlternativeTypeId() != SDataConstantsSys.ITMU_TP_UNIT_NA) {
                    SFormUtilities.populateComboBox(miClient, jcbFkOriginalUnitId, SDataConstants.ITMU_UNIT, new Object[] { moItem.getDbmsDataUnit().getFkUnitTypeId(), moItem.getFkUnitAlternativeTypeId() });
                }
                else {
                    SFormUtilities.populateComboBox(miClient, jcbFkOriginalUnitId, SDataConstants.ITMU_UNIT, new int[] { moItem.getDbmsDataUnit().getFkUnitTypeId() });
                }
            }

            // Initialize surplus default:

            jckIsSurplusPercentageApplying.setSelected(false);
            if (moItem.getSurplusPercentage() > 0) {
                jckIsSurplusPercentageApplying.setSelected(true);
                activateSurplusPercentage();
                moFieldSurplusPercentage.setFieldValue(moItem.getSurplusPercentage());
            }
            else if (moItem.getDbmsDataItemGeneric().getSurplusPercentage() > 0) {
                jckIsSurplusPercentageApplying.setSelected(true);
                activateSurplusPercentage();
                moFieldSurplusPercentage.setFieldValue(moItem.getDbmsDataItemGeneric().getSurplusPercentage());
            }

            // Initializa addenda fields:

            if (mbIsSales && (mbIsDoc || mbIsAdj) && mbEnableDataAddenda) {
                SFormUtilities.populateComboBox(miClient, jcbAddendaFkBarcode, SDataConstants.ITMU_ITEM_BARC, new int[] { moItem.getPkItemId() });
                jcbAddendaFkBarcode.removeItemAt(0);
                renderAddendaDatas();
            }
            
            // Initialize fields:

            jckIsBulk.setSelected(moItem.getDbmsIsBulk());

            moFieldKey.setFieldValue(keyItemBp.length() == 0 ? moItem.getKey() : keyItemBp);
            moFieldConcept.setFieldValue(item);
            moFieldFkOriginalUnitId.setFieldValue(new int[] { unitItemBp == 0 ? moItem.getFkUnitId() : unitItemBp });
            moFieldIsInventoriable.setFieldValue(moItem.getDbmsIsInventoriable());

            jtfKey.setCaretPosition(0);
            jtfConcept.setCaretPosition(0);

            if (!moItem.getDbmsDataItemGeneric().getIsItemKeyEditable()) {
                jtfKey.setToolTipText(jtfKey.getText());
            }
            if (!moItem.getDbmsDataItemGeneric().getIsItemNameEditable()) {
                jtfConcept.setToolTipText(jtfConcept.getText());
            }

            // Unitary discount settings:

            if (moItem.getDbmsIsFreeDiscountUnitary()) {
                moFieldIsDiscountUnitaryPercentage.setFieldValue(false);
                moFieldOriginalDiscountUnitaryCy.setFieldValue(0d);
            }

            // Entry discount settings:

            if (moItem.getDbmsIsFreeDiscountEntry()) {
                moFieldIsDiscountEntryPercentage.setFieldValue(false);
                moFieldDiscountEntryCy.setFieldValue(0d);
            }

            // Document discount settings:

            if (!moParamDps.getIsDiscountDocApplying() || moItem.getDbmsIsFreeDiscountDoc()) {
                moFieldIsDiscountDocApplying.setFieldValue(false);
                moFieldDiscountDocCy.setFieldValue(0d);
            }

            // Obtain item price with discount included or with discount separate:

            try {
                moParamsItemPriceList = SDataUtilities.obtainItemPrice(miClient,
                        moParamBizPartner.getPkBizPartnerId(),
                        (moParamDps.getFkDpsCategoryId() == SDataConstantsSys.TRNS_CT_DPS_SAL ?
                        moParamBizPartner.getDbmsCategorySettingsCus().getFkBizPartnerCategoryId() :
                        moParamBizPartner.getDbmsCategorySettingsSup().getFkBizPartnerCategoryId()),
                        (moParamDps.getFkDpsCategoryId() == SDataConstantsSys.TRNS_CT_DPS_SAL ?
                        moParamBizPartner.getDbmsCategorySettingsCus().getFkBizPartnerTypeId() :
                        moParamBizPartner.getDbmsCategorySettingsSup().getFkBizPartnerTypeId()),
                        moParamDps.getFkDpsCategoryId(), moParamDps.getDateDoc(), moItem.getPkItemId(), moParamDps.getFkCurrencyId());

                // Check document currency:

                if (moParamDps.getFkCurrencyId() == miClient.getSessionXXX().getParamsErp().getFkCurrencyId()) {
                    moFieldOriginalPriceUnitaryCy.setDouble(moParamsItemPriceList.getItemPrice());
                    moFieldOriginalDiscountUnitaryCy.setDouble(moParamsItemPriceList.getItemDiscount());
                }
                else {
                    moFieldOriginalPriceUnitaryCy.setDouble(moParamDps.getExchangeRate() == 0 ? 0 : moParamsItemPriceList.getItemPrice() / moParamDps.getExchangeRate());
                    moFieldOriginalDiscountUnitaryCy.setDouble(moParamDps.getExchangeRate() == 0 ? 0 : moParamsItemPriceList.getItemDiscount() / moParamDps.getExchangeRate());
                }
            }
            catch (Exception e) {
                SLibUtilities.printOutException(this, e);
            }

            enableItemFields();
        }

        renderFieldsStatus();

        if (calculate) {
            calculateTotal(); // actually this clears all entry's value fields
        }

        mbUpdatingForm = false;
    }

    private void renderItemBizPartnerDescription(int[] key) {
        moDataItemBizPartnerDescription = (SDataItemBizPartnerDescription) SDataUtilities.readRegistry(miClient, SDataConstants.ITMU_CFG_ITEM_BP, key, SLibConstants.EXEC_MODE_SILENT);

        moFieldKey.setFieldValue(moDataItemBizPartnerDescription.getKey());
        moFieldConcept.setFieldValue(moDataItemBizPartnerDescription.getItem());
        moFieldFkOriginalUnitId.setFieldValue(new int[] { moDataItemBizPartnerDescription.getFkUnitId() });
    }

    private void renderOriginalUnitSymbol() {
        if (jcbFkOriginalUnitId.getSelectedIndex() <= 0) {
            jtfOriginalUnitSymbolRo.setText("");            
            jtfPriceOriginalUnitSymbol.setText("");
        }
        else {
            jtfOriginalUnitSymbolRo.setText((String) ((SFormComponentItem) jcbFkOriginalUnitId.getSelectedItem()).getComplement());
            jtfPriceOriginalUnitSymbol.setText((String) ((SFormComponentItem) jcbFkOriginalUnitId.getSelectedItem()).getComplement());
        }
    }

    private void renderUnitSymbol() {
        if (moItem == null) {
            jtfUnitSymbolRo.setText("");
        }
        else {
            jtfUnitSymbolRo.setText(moItem.getDbmsDataUnit().getSymbol());
        }
    }

    private void renderFieldsStatus() {
        itemStateIsDiscountUnitaryPercentage(false);
        itemStateIsDiscountEntryPercentage(false);
        itemStateIsDiscountDocApplying(false);
        itemStateIsSurplusPercentageApplying(false);
        itemStateIsTaxesAutomaticApplying(false);
        renderOriginalUnitSymbol();
        renderUnitSymbol();
    }

    private void enableItemFields() {
        if (moItem != null) {
            mbAllowDiscount = true;
            
            // Validate if user can modify unitary price and unitary discount:

            jtfOriginalPriceUnitaryCy.setEditable(true);
            jtfOriginalPriceUnitaryCy.setFocusable(true);
            jtfOriginalDiscountUnitaryCy.setEditable(true);
            jtfOriginalDiscountUnitaryCy.setFocusable(true);
            jtfDiscountEntryCy.setEditable(true);
            jtfDiscountEntryCy.setFocusable(true);
            jtfDiscountDocCy.setEditable(true);
            jtfDiscountDocCy.setFocusable(true);

            jbPriceUnitaryCyWizard.setEnabled(true);
            jbPriceHistory.setEnabled(true);

            if ((moParamsItemPriceList != null && moParamsItemPriceList.getItemPriceFound()) ||
                    (moDpsEntry.getContractPriceMonth() != SLibConstants.UNDEFINED && moDpsEntry.getContractPriceMonth() != SLibConstants.UNDEFINED)) {
                if ((mbIsSales &&
                    mnPricePolicyForSales > 0 &&
                    !mbRightPriceListForSales &&
                    SLibUtilities.compareKeys(new int[] { moItem.getDbmsDataItemGeneric().getFkItemCategoryId(), moItem.getDbmsDataItemGeneric().getFkItemClassId() }, SDataConstantsSys.ITMS_CL_ITEM_SAL_PRO)) ||
                    (!mbIsSales &&
                    mnPricePolicyForPurchases > 0 &&
                    !mbRightPriceListForPurchases &&
                    SLibUtilities.compareKeys(new int[] { moItem.getDbmsDataItemGeneric().getFkItemCategoryId(), moItem.getDbmsDataItemGeneric().getFkItemClassId() }, SDataConstantsSys.ITMS_CL_ITEM_PUR_CON)) || 
                        (moDpsEntry.getContractPriceMonth() != SLibConstants.UNDEFINED && moDpsEntry.getContractPriceMonth() != SLibConstants.UNDEFINED)) {

                    mbAllowDiscount = !mbIsSales;

                    jtfOriginalPriceUnitaryCy.setEditable(false);
                    jtfOriginalPriceUnitaryCy.setFocusable(false);
                    jtfOriginalDiscountUnitaryCy.setEditable(false);
                    jtfOriginalDiscountUnitaryCy.setFocusable(false);
                    jtfDiscountEntryCy.setEditable(false);
                    jtfDiscountEntryCy.setFocusable(false);
                    jtfDiscountDocCy.setEditable(false);
                    jtfDiscountDocCy.setFocusable(false);

                    jbPriceUnitaryCyWizard.setEnabled(false);
                    jbPriceHistory.setEnabled(false);
                }
            }

            if (moItem.getDbmsIsFreeDiscountUnitary()) {
                jckIsDiscountUnitaryPercentage.setEnabled(false);
            }
            else {
                jckIsDiscountUnitaryPercentage.setEnabled(mbAllowDiscount); // XXX requires user right to be enabled

            }

            if (moItem.getDbmsIsFreeDiscountEntry()) {
                jckIsDiscountEntryPercentage.setEnabled(false);
            }
            else {
                jckIsDiscountEntryPercentage.setEnabled(mbAllowDiscount); // XXX requires user right to be enabled
            }

            if (!moParamDps.getIsDiscountDocApplying() || moItem.getDbmsIsFreeDiscountDoc()) {
                jckIsDiscountDocApplying.setEnabled(false);
                jckIsDiscountDocApplying.setSelected(false);
            }
            else {
                jckIsDiscountDocApplying.setEnabled(mbAllowDiscount); // XXX requires user right to be enabled
                jckIsDiscountDocApplying.setSelected(mbAllowDiscount);
            }

            // Enable remaining fields:

            jtfKey.setEditable(moItem.getDbmsDataItemGeneric().getIsItemKeyEditable());
            jtfKey.setFocusable(moItem.getDbmsDataItemGeneric().getIsItemKeyEditable());
            jbKey.setEnabled(moItem.getDbmsDataItemGeneric().getIsItemKeyEditable());
            jtfConcept.setEditable(moItem.getDbmsDataItemGeneric().getIsItemNameEditable());
            jtfConcept.setFocusable(moItem.getDbmsDataItemGeneric().getIsItemNameEditable());
            jbConcept.setEnabled(moItem.getDbmsDataItemGeneric().getIsItemNameEditable());
            jcbFkOriginalUnitId.setEnabled(true);
            jbFkOriginalUnitId.setEnabled(true);

            jtfOriginalQuantity.setEditable(true);
            jtfOriginalQuantity.setFocusable(true);
            
            jckAuxPreserveQuantity.setEnabled(moItem.getFkUnitAlternativeTypeId() != SDataConstantsSys.ITMU_TP_UNIT_NA);
            jckAuxPreserveQuantity.setSelected(jckAuxPreserveQuantity.isEnabled() && moItem.getUnitAlternativeBaseEquivalence() == 0);

            if (moItem.getDbmsDataItemGeneric().getIsItemReferenceRequired()) {
                moFieldFkItemReferenceId_n.setFieldValue(new int[] { moItem.getDbmsFkDefaultItemRefId_n() });
            }

            jcbFkItemReferenceId_n.setEnabled(moItem.getDbmsDataItemGeneric().getIsItemReferenceRequired());
            jbFkItemReferenceId_n.setEnabled(moItem.getDbmsDataItemGeneric().getIsItemReferenceRequired());

            moPanelFkCostCenterId_n.enableFields(true);

            try {
                moPanelFkCostCenterId_n.getFieldAccount().setString(SDataUtilities.obtainCostCenterItem(miClient, moItem.getPkItemId()));
            }
            catch (Exception e) {
                SLibUtilities.renderException(this, e);
            }
            finally {
                moPanelFkCostCenterId_n.refreshPanel();
            }

            jtfLength.setEnabled(moItem.getDbmsDataItemGeneric().getIsLengthApplying() && (moItem.getIsLengthVariable() || moItem.getDbmsDataItemGeneric().getIsLengthVariable()));
            jtfSurface.setEnabled(moItem.getDbmsDataItemGeneric().getIsSurfaceApplying() && (moItem.getIsSurfaceVariable() || moItem.getDbmsDataItemGeneric().getIsSurfaceVariable()));
            jtfVolume.setEnabled(moItem.getDbmsDataItemGeneric().getIsVolumeApplying() && (moItem.getIsVolumeVariable() || moItem.getDbmsDataItemGeneric().getIsVolumeVariable()));
            jtfMass.setEnabled(moItem.getDbmsDataItemGeneric().getIsMassApplying() && (moItem.getIsMassVariable() || moItem.getDbmsDataItemGeneric().getIsMassVariable()));

            jckIsSurplusPercentageApplying.setEnabled(true);
        }
    }

    private void renderAddendaDatas() {
        if (jbOk.isEnabled()) {
            switch(moParamBizPartner.getDbmsCategorySettingsCus().getFkCfdAddendaTypeId()) {
                case SDataConstantsSys.BPSS_TP_CFD_ADD_NA:
                    break;
                    
                case SDataConstantsSys.BPSS_TP_CFD_ADD_SORIANA:
                    jlAddendaFkBarcode.setEnabled(mbEnableDataAddenda);
                    jcbAddendaFkBarcode.setEnabled(mbEnableDataAddenda);
                    break;
                    
                case SDataConstantsSys.BPSS_TP_CFD_ADD_LOREAL:
                    jlAddendaEntryNumber.setEnabled(mbEnableDataAddenda);
                    jtfAddendaEntryNumber.setEnabled(mbEnableDataAddenda);
                    break;
                    
                case SDataConstantsSys.BPSS_TP_CFD_ADD_BACHOCO:
                    jlAddendaNumberPosition.setEnabled(mbEnableDataAddenda);
                    jtfAddendaNumberPosition.setEnabled(mbEnableDataAddenda);
                    jlAddendaCenter.setEnabled(mbEnableDataAddenda);
                    jtfAddendaCenter.setEnabled(mbEnableDataAddenda);
                    break;
                    
                case SDataConstantsSys.BPSS_TP_CFD_ADD_ELEKTRA:
                    jlAddendaOrder.setEnabled(mbEnableDataAddenda);
                    jtfAddendaOrder.setEnabled(mbEnableDataAddenda);
                    jlAddendaFkBarcode.setEnabled(mbEnableDataAddenda);
                    jcbAddendaFkBarcode.setEnabled(mbEnableDataAddenda);
                    jlAddendaCages.setEnabled(mbEnableDataAddenda);
                    jtfAddendaCages.setEnabled(mbEnableDataAddenda);
                    jlAddendaCagePriceUnitary.setEnabled(mbEnableDataAddenda);
                    jtfAddendaCagePriceUnitary.setEnabled(mbEnableDataAddenda);
                    jlAddendaParts.setEnabled(mbEnableDataAddenda);
                    jtfAddendaParts.setEnabled(mbEnableDataAddenda);
                    jlAddendaPartPriceUnitary.setEnabled(mbEnableDataAddenda);
                    jtfAddendaPartPriceUnitary.setEnabled(mbEnableDataAddenda);
                    break;
                    
                default:
            }
        }
    }

    private void setAddendaDatas() {
        String sCenter = "";
        
        switch(moParamBizPartner.getDbmsCategorySettingsCus().getFkCfdAddendaTypeId()) {
            case SDataConstantsSys.BPSS_TP_CFD_ADD_NA:
                break;
                
            case SDataConstantsSys.BPSS_TP_CFD_ADD_SORIANA:
                if (moDpsEntry.getDbmsDpsAddSorianaBarCode().length() > 0) {
                    jcbAddendaFkBarcode.setSelectedItem(moDpsEntry.getDbmsDpsAddSorianaBarCode());
                    moFieldAddendaFkBarcode.setFieldValue(moDpsEntry.getDbmsDpsAddSorianaBarCode());
                }
                else {
                    jcbAddendaFkBarcode.setSelectedItem("");
                    moFieldAddendaFkBarcode.setFieldValue(moDpsEntry.getDbmsDpsAddSorianaBarCode());
                }
                break;
                
            case SDataConstantsSys.BPSS_TP_CFD_ADD_LOREAL:
                if (moDpsEntry.getDbmsDpsAddLorealEntryNumber() > 0) {
                    moFieldAddendaEntryNumber.setFieldValue(moDpsEntry.getDbmsDpsAddLorealEntryNumber());
                }
                else {
                    moFieldAddendaEntryNumber.setFieldValue(0);
                }
                break;
                
            case SDataConstantsSys.BPSS_TP_CFD_ADD_BACHOCO:
                if (moDpsEntry.getDbmsDpsAddBachocoCenter().length() > 0) {
                    moFieldAddendaNumberPosition.setFieldValue(moDpsEntry.getDbmsDpsAddBachocoNumberPosition());
                    sCenter = moDpsEntry.getDbmsDpsAddBachocoCenter();
                }
                moFieldAddendaCenter.setFieldValue(sCenter);
                break;
                
            case SDataConstantsSys.BPSS_TP_CFD_ADD_ELEKTRA:
                moFieldAddendaOrder.setFieldValue(moDpsEntry.getDbmsDpsAddElektraOrder());

                if (moDpsEntry.getDbmsDpsAddElektraBarcode().length() > 0) {
                    jcbAddendaFkBarcode.setSelectedItem(moDpsEntry.getDbmsDpsAddElektraBarcode());
                    moFieldAddendaFkBarcode.setFieldValue(moDpsEntry.getDbmsDpsAddElektraBarcode());
                }
                else {
                    jcbAddendaFkBarcode.setSelectedItem("");
                    moFieldAddendaFkBarcode.setFieldValue(moDpsEntry.getDbmsDpsAddElektraBarcode());
                }

                moFieldAddendaCages.setFieldValue(moDpsEntry.getDbmsDpsAddElektraCages());
                moFieldAddendaCagePriceUnitary.setFieldValue(moDpsEntry.getDbmsDpsAddElektraCagePriceUnitary());
                moFieldAddendaParts.setFieldValue(moDpsEntry.getDbmsDpsAddElektraParts());
                moFieldAddendaPartPriceUnitary.setFieldValue(moDpsEntry.getDbmsDpsAddElektraPartPriceUnitary());
                break;
                
            default:
        }
    }

    private void setDpsType() {
       mbIsDoc = false;
       mbIsSales = false;
       mbIsAdj = false;
       mbIsCon = false;

       int[] manDpsClassKey = new int[] { moParamDps.getFkDpsCategoryId(), moParamDps.getFkDpsClassId() };
       int[] manDpsTypeKey = new int[] { moParamDps.getFkDpsCategoryId(), moParamDps.getFkDpsClassId(), moParamDps.getFkDpsTypeId() };

       mbIsDoc = SLibUtilities.compareKeys(SDataConstantsSys.TRNS_CL_DPS_PUR_DOC, manDpsClassKey) ||
                SLibUtilities.compareKeys(SDataConstantsSys.TRNS_CL_DPS_SAL_DOC, manDpsClassKey);
       mbIsSales = SLibUtilities.compareKeys(SDataConstantsSys.TRNS_CL_DPS_SAL_EST, manDpsClassKey) ||
                SLibUtilities.compareKeys(SDataConstantsSys.TRNS_CL_DPS_SAL_ORD, manDpsClassKey) ||
                SLibUtilities.compareKeys(SDataConstantsSys.TRNS_CL_DPS_SAL_DOC, manDpsClassKey) ||
                SLibUtilities.compareKeys(SDataConstantsSys.TRNS_CL_DPS_SAL_ADJ, manDpsClassKey);
       mbIsAdj = SLibUtilities.compareKeys(SDataConstantsSys.TRNS_CL_DPS_PUR_ADJ, manDpsClassKey) ||
                SLibUtilities.compareKeys(SDataConstantsSys.TRNS_CL_DPS_SAL_ADJ, manDpsClassKey);
       mbIsCon = SLibUtilities.compareKeys(SDataConstantsSys.TRNU_TP_DPS_SAL_CON, manDpsTypeKey) ||
                SLibUtilities.compareKeys(SDataConstantsSys.TRNU_TP_DPS_PUR_CON, manDpsTypeKey);
    }

    private void setTaxesColumnEditable(boolean editable) {
        int index = moPaneTaxes.getTable().getSelectedRow();

        moPaneTaxes.getTableColumn(COL_TAX_CUR).setEditable(editable);
        moPaneTaxes.renderTableRows();
        if (index != -1) {
            moPaneTaxes.setTableRowSelection(index);
        }
    }

    private void updateFormEditStatus(boolean edit) {
        if (!edit) {
            mnFormStatus = SLibConstants.FORM_STATUS_READ_ONLY;

            jcbFkItemId.setEnabled(false);
            jbFkItemId.setEnabled(false);
            jtfKey.setEditable(false);
            jtfKey.setFocusable(false);
            jbKey.setEnabled(false);
            jtfConcept.setEditable(false);
            jtfConcept.setFocusable(false);
            jbConcept.setEnabled(false);
            jbItemBizPartnerDescription.setEnabled(false);
            jcbFkOriginalUnitId.setEnabled(false);
            jbFkOriginalUnitId.setEnabled(false);
            jckIsDeleted.setEnabled(false);

            jckIsDiscountDocApplying.setEnabled(false);
            jckIsDiscountUnitaryPercentage.setEnabled(false);
            jtfDiscountUnitaryPercentage.setEditable(false);
            jtfDiscountUnitaryPercentage.setFocusable(false);
            jckIsDiscountEntryPercentage.setEnabled(false);
            jtfDiscountEntryPercentage.setEditable(false);
            jtfDiscountEntryPercentage.setFocusable(false);

            jtfOriginalQuantity.setEditable(false);
            jtfOriginalQuantity.setFocusable(false);
            jtfOriginalPriceUnitaryCy.setEditable(false);
            jtfOriginalPriceUnitaryCy.setFocusable(false);
            jtfOriginalDiscountUnitaryCy.setEditable(false);
            jtfOriginalDiscountUnitaryCy.setFocusable(false);
            jbPriceUnitaryCyWizard.setEnabled(false);
            jbPriceHistory.setEnabled(false);
            jckAuxPreserveQuantity.setEnabled(false);

            jtfDiscountEntryCy.setEditable(false);
            jtfDiscountEntryCy.setFocusable(false);
            jtfDiscountDocCy.setEditable(false);
            jtfDiscountDocCy.setFocusable(false);

            jcbFkItemReferenceId_n.setEnabled(false);
            jbFkItemReferenceId_n.setEnabled(false);
            jtfReference.setEditable(false);
            jtfReference.setFocusable(false);

            moPanelFkCostCenterId_n.enableFields(false);

            jcbFkTaxRegionId.setEnabled(false);
            jbFkTaxRegionId.setEnabled(false);
            jckIsTaxesAutomaticApplying.setEnabled(false);

            jtfLength.setEnabled(false);
            jtfSurface.setEnabled(false);
            jtfVolume.setEnabled(false);
            jtfMass.setEnabled(false);

            jckIsSurplusPercentageApplying.setEnabled(false);
            jtfSurplusPercentage.setEditable(false);
            jtfSurplusPercentage.setFocusable(false);
            
            jcbFkVehicleTypeId_n.setEnabled(false);
            jtfVehicleNumber.setEditable(false);
            jtfSecuritySeal.setEditable(false);
            jtfTicket.setEditable(false);

            jbNotesNew.setEnabled(false);
            jbNotesEdit.setEnabled(false);
            jbNotesDelete.setEnabled(false);
            jbSystemNotes.setEnabled(false);

            jbOk.setEnabled(false);
            jlAddendaNumberPosition.setEnabled(false);
            jtfAddendaNumberPosition.setEnabled(false);
            jlAddendaCenter.setEnabled(false);
            jtfAddendaCenter.setEnabled(false);
            jlAddendaEntryNumber.setEnabled(false);
            jtfAddendaEntryNumber.setEnabled(false);
            jlAddendaFkBarcode.setEnabled(false);
            jcbAddendaFkBarcode.setEnabled(false);
            jtfAddendaOrder.setEnabled(false);
            jtfAddendaCages.setEnabled(false);
            jtfAddendaCagePriceUnitary.setEnabled(false);
            jtfAddendaParts.setEnabled(false);
            jtfAddendaPartPriceUnitary.setEnabled(false);

            setTaxesColumnEditable(false);
        }
        else {
            mnFormStatus = SLibConstants.FORM_STATUS_EDIT;

            jcbFkItemId.setEnabled(true);
            jbFkItemId.setEnabled(true);
            jckIsDeleted.setEnabled(!moDpsEntry.getIsRegistryNew());

            jtfReference.setEditable(true);
            jtfReference.setFocusable(true);
            
            jcbFkTaxRegionId.setEnabled(true);
            jbFkTaxRegionId.setEnabled(true);
            jckIsTaxesAutomaticApplying.setEnabled(true);

            jcbFkVehicleTypeId_n.setEnabled(true);
            jtfVehicleNumber.setEditable(true);
            jtfSecuritySeal.setEditable(true);
            jtfTicket.setEditable(true);

            jbNotesNew.setEnabled(true);
            jbNotesEdit.setEnabled(true);
            jbNotesDelete.setEnabled(true);
            jbSystemNotes.setEnabled(true);

            jbOk.setEnabled(true);

            enableItemFields();
            renderFieldsStatus();
        }
    }

    private void updateDpsEntryTaxRow() {
        int index = moPaneTaxes.getTable().getSelectedRow();
        SDataDpsEntryTaxRow entryTaxRow = (SDataDpsEntryTaxRow) moPaneTaxes.getSelectedTableRow();
        SDataDpsEntryTax entryTax = (SDataDpsEntryTax) entryTaxRow.getData();

        entryTax.setTaxCy(entryTaxRow.getValues() == null ? 0d : ((Number) entryTaxRow.getValues().get(COL_TAX_CUR)).doubleValue());
        calculateTotal();

        moPaneTaxes.setTableRowSelection(index);
    }

    private void itemStateIsDiscountUnitaryPercentage(boolean calculate) {
        if (moFieldIsDiscountUnitaryPercentage.getBoolean()) {
            jtfDiscountUnitaryPercentage.setEditable(mbAllowDiscount);
            jtfDiscountUnitaryPercentage.setFocusable(mbAllowDiscount);
            jtfOriginalDiscountUnitaryCy.setEditable(false);
            jtfOriginalDiscountUnitaryCy.setFocusable(false);

            jtfDiscountUnitaryPercentage.requestFocus();
        }
        else {
            jtfDiscountUnitaryPercentage.setEditable(false);
            jtfDiscountUnitaryPercentage.setFocusable(false);
            jtfOriginalDiscountUnitaryCy.setEditable(moItem == null ? false : !moItem.getDbmsIsFreeDiscountUnitary() && mbAllowDiscount);
            jtfOriginalDiscountUnitaryCy.setFocusable(moItem == null ? false : !moItem.getDbmsIsFreeDiscountUnitary() && mbAllowDiscount);

            moFieldDiscountUnitaryPercentage.setFieldValue(0d);
        }

        if (calculate) {
            calculateTotal();
        }
    }

    private void itemStateIsDiscountEntryPercentage(boolean calculate) {
        if (moFieldIsDiscountEntryPercentage.getBoolean()) {
            jtfDiscountEntryPercentage.setEditable(mbAllowDiscount);
            jtfDiscountEntryPercentage.setFocusable(mbAllowDiscount);
            jtfDiscountEntryCy.setEditable(false);
            jtfDiscountEntryCy.setFocusable(false);

            jtfDiscountEntryPercentage.requestFocus();
        }
        else {
            jtfDiscountEntryPercentage.setEditable(false);
            jtfDiscountEntryPercentage.setFocusable(false);
            jtfDiscountEntryCy.setEditable(moItem == null ? false : !moItem.getIsFreeDiscountEntry() && mbAllowDiscount);
            jtfDiscountEntryCy.setFocusable(moItem == null ? false : !moItem.getIsFreeDiscountEntry() && mbAllowDiscount);

            moFieldDiscountEntryPercentage.setFieldValue(0d);
        }

        if (calculate) {
            calculateTotal();
        }
    }

    private void itemStateIsDiscountDocApplying(boolean calculate) {
        if (moFieldIsDiscountDocApplying.getBoolean()) {
            jtfDiscountDocCy.setEditable(moParamDps == null ? false : !moParamDps.getIsDiscountDocPercentage() && mbAllowDiscount);
            jtfDiscountDocCy.setFocusable(moParamDps == null ? false : !moParamDps.getIsDiscountDocPercentage() && mbAllowDiscount);
        }
        else {
            jtfDiscountDocCy.setEditable(false);
            jtfDiscountDocCy.setFocusable(false);

            moFieldDiscountDocCy.setFieldValue(0d);
        }

        if (calculate) {
            calculateTotal();
        }
    }

    private void itemStateIsSurplusPercentageApplying(boolean calculate) {
        if (jckIsSurplusPercentageApplying.isSelected()) {
            jtfSurplusPercentage.setEditable(true);
            jtfSurplusPercentage.setFocusable(true);

            jtfSurplusPercentage.requestFocus();
        }
        else {
            jtfSurplusPercentage.setEditable(false);
            jtfSurplusPercentage.setFocusable(false);
        }
    }

    private void itemStateIsTaxesAutomaticApplying(boolean calculate) {
        setTaxesColumnEditable(!jckIsTaxesAutomaticApplying.isSelected());

        if (calculate && jckIsTaxesAutomaticApplying.isSelected()) {
            calculateTotal();
        }
    }

    private void itemChangedFkItemId(boolean calculate) {
        if (jcbFkItemId.getSelectedIndex() <= 0) {
            moItem = null;
        }
        else {
            moItem = (SDataItem) SDataUtilities.readRegistry(miClient, SDataConstants.ITMU_ITEM, moFieldFkItemId.getKeyAsIntArray(), SLibConstants.EXEC_MODE_VERBOSE);
        }

        renderItem(true, calculate);
    }

    private void itemChangedFkOriginalUnitId() {
        renderOriginalUnitSymbol();
        calculateTotal();
    }

    private void itemChangedFkTaxRegionId() {
        mbUpdatingForm = true;

        moFieldIsTaxesAutomaticApplying.setFieldValue(true);
        itemStateIsTaxesAutomaticApplying(true);

        mbUpdatingForm = false;
    }

    private void actionKey() {
        if (moItem != null) {
            moFieldKey.setFieldValue(moItem.getKey());
            jtfKey.setCaretPosition(0);
        }
    }

    private void actionConcept() {
        if (moItem != null) {
            moFieldConcept.setFieldValue(moItem.getItem());
            jtfConcept.setCaretPosition(0);
        }
    }

    private void actionItemBizPartnerDescription() {
        SFormOptionPicker picker = (SFormOptionPicker) miClient.getOptionPicker(SDataConstants.ITMU_CFG_ITEM_BP);

        picker.formReset();
        picker.setFilterKey(new int[] { moItem.getPkItemId(), moParamBizPartner.getPkBizPartnerId() });
        picker.formRefreshOptionPane();
        picker.setFormVisible(true);

        if (picker.getFormResult() == SLibConstants.FORM_RESULT_OK) {
            renderItemBizPartnerDescription((int []) picker.getSelectedPrimaryKey());
        }
    }

    private void actionFkItemId() {
        if (miClient.pickOption(SDataConstants.ITMX_ITEM_IOG, moFieldFkItemId, manItemClassFilterKey) == SLibConstants.FORM_RESULT_OK) {
            itemChangedFkItemId(true);
        }
    }

    private void actionFkOriginalUnitId() {
        if (miClient.pickOption(SDataConstants.ITMU_UNIT, moFieldFkOriginalUnitId, new int[] { moItem.getDbmsDataItemGeneric().getFkUnitTypeId() }) == SLibConstants.FORM_RESULT_OK) {
            itemChangedFkOriginalUnitId();
        }
    }

    private void actionPriceUnitaryCyWizard() {
        moDialogPriceUnitaryWizard.setParams(moFieldOriginalQuantity.getDouble(), jtfOriginalQuantity.isEditable(), jtfOriginalUnitSymbolRo.getText(), jtfCurrencyKeyPriceUnitaryCyRo.getText());
        moDialogPriceUnitaryWizard.setVisible(true);

        if (moDialogPriceUnitaryWizard.getFormResult() == SLibConstants.FORM_RESULT_OK) {
            moFieldOriginalQuantity.setFieldValue(moDialogPriceUnitaryWizard.getQuantity());
            moFieldOriginalPriceUnitaryCy.setFieldValue(moDialogPriceUnitaryWizard.getPriceUnitary());
            calculateTotal(); // calculate total only if value has changed
        }

        jtfOriginalPriceUnitaryCy.requestFocus();
    }

    private void actionPriceHistory() {
        moDialogItemPriceHistory.refreshForm();
        moDialogItemPriceHistory.showPriceHistory(moParamDps.getFkDpsCategoryId() == SDataConstantsSys.TRNS_CT_DPS_PUR ? true : false,
            moFieldFkItemId.getKeyAsIntArray()[0], moParamBizPartner.getPkBizPartnerId(), new int[] { moParamDps.getFkDpsCategoryId(), moParamDps.getFkDpsClassId()});
    }

    private void actionNotesNew() {
        if (jbNotesNew.isEnabled()) {
            moFormNotes.formReset();
            moFormNotes.setFormVisible(true);

            if (moFormNotes.getFormResult() == SLibConstants.FORM_RESULT_OK) {
                moPaneGridNotes.addTableRow(new SDataDpsEntryNotesRow(moFormNotes.getRegistry()));
                moPaneGridNotes.renderTableRows();
                moPaneGridNotes.setTableRowSelection(moPaneGridNotes.getTableGuiRowCount() - 1);
            }
        }
    }

    private void actionNotesEdit() {
        if (jbNotesEdit.isEnabled()) {
            int index = moPaneGridNotes.getTable().getSelectedRow();

            if (index != -1) {
                moFormNotes.formReset();
                moFormNotes.setRegistry((SDataDpsEntryNotes) moPaneGridNotes.getSelectedTableRow().getData());
                moFormNotes.setFormVisible(true);

                if (moFormNotes.getFormResult() == SLibConstants.FORM_RESULT_OK) {
                    moPaneGridNotes.setTableRow(new SDataDpsEntryNotesRow(moFormNotes.getRegistry()), index);
                    moPaneGridNotes.renderTableRows();
                    moPaneGridNotes.setTableRowSelection(index < moPaneGridNotes.getTableGuiRowCount() ? index : moPaneGridNotes.getTableGuiRowCount() - 1);
                }
            }
        }
    }

    private void actionNotesDelete() {
        if (jbNotesDelete.isEnabled()) {
            int index = moPaneGridNotes.getTable().getSelectedRow();

            if (index != -1) {
                if (miClient.showMsgBoxConfirm(SLibConstants.MSG_CNF_REG_DELETE) == JOptionPane.YES_OPTION) {
                    SDataDpsEntryNotes notes = (SDataDpsEntryNotes) moPaneGridNotes.getTableRow(index).getData();

                    if (notes.getIsDeleted()) {
                        miClient.showMsgBoxWarning(SLibConstants.MSG_ERR_GUI_REG_ALREADY_DELETE);
                    }
                    else {
                        if (notes.getIsRegistryNew()) {
                            moPaneGridNotes.removeTableRow(index);
                        }
                        else {
                            notes.setIsDeleted(true);
                            notes.setFkUserDeleteId(miClient.getSession().getUser().getPkUserId());
                            notes.setIsRegistryEdited(true);
                            notes.setFkUserEditId(miClient.getSession().getUser().getPkUserId());

                            moPaneGridNotes.setTableRow(new SDataDpsEntryNotesRow(notes), index);
                        }

                        moPaneGridNotes.renderTableRows();
                        moPaneGridNotes.setTableRowSelection(index < moPaneGridNotes.getTableGuiRowCount() ? index : moPaneGridNotes.getTableGuiRowCount() - 1);
                    }
                }
            }
        }
    }

    private void actionNotesFilter() {
        if (jtbNotesFilter.isEnabled()) {
            int index = moPaneGridNotes.getTable().getSelectedRow();

            moPaneGridNotes.setGridViewStatus(!jtbNotesFilter.isSelected() ? STableConstants.VIEW_STATUS_ALL : STableConstants.VIEW_STATUS_ALIVE);
            moPaneGridNotes.renderTableRows();
            moPaneGridNotes.setTableRowSelection(index < moPaneGridNotes.getTableGuiRowCount() ? index : moPaneGridNotes.getTableGuiRowCount() - 1);
        }
    }

    private void actionSystemNotes() {
        String option = "";

        option = miClient.pickOption(SDataConstants.TRN_SYS_NTS, new int[] { moParamDps.getFkDpsCategoryId(), moParamDps.getFkDpsClassId(), moParamDps.getFkDpsTypeId(), moParamDps.getFkCurrencyId() });

        if (!option.isEmpty()) {
            SDataDpsEntryNotes notes = new SDataDpsEntryNotes();

            notes.setNotes(option);
            notes.setIsAllDocs(true);
            notes.setIsPrintable(true);
            notes.setFkUserNewId(miClient.getSession().getUser().getPkUserId());

            moPaneGridNotes.addTableRow(new SDataDpsEntryNotesRow(notes));

            moPaneGridNotes.renderTableRows();
            moPaneGridNotes.setTableRowSelection(0);
        }
    }

    private void actionPriceNew() {
        mnAuxPriceEditIndex = -1;
        
        moAuxPriceEdit = new SDataDpsEntryPrice();
        
        if (jbGridPriceNew.isEnabled() || jbPriceNew.isEnabled()) {
            enableDeliveryPriceButtonFields(false);
            enablePriceEntryContractFields(true);
            enableDeliveryPriceFields(jckIsDirectPrice.isSelected());
            enablePriceGridFields(false);
            
            moFieldPriceOriginalQuantity.setDouble(0d);
            if (!jckIsDirectPrice.isSelected()) {
                moFieldContractBase.setDouble(moFieldDpsContractBase.getDouble());
                moFieldContractFuture.setDouble(moFieldDpsContractFuture.getDouble());
                moFieldContractFactor.setDouble(moFieldDpsContractFactor.getDouble());
            }
            else {
                moFieldContractBase.setDouble(0.0d);
                moFieldContractFuture.setDouble(0.0d);
                moFieldContractFactor.setDouble(0.0d);
            }

            calculateEntryPrice();
            moAuxPriceEdit.setOriginalPriceUnitaryCySystem(moFieldPriceOriginalPriceUnitaryCy.getDouble());
            jtfContractPriceNumbrerReference.requestFocus();
        }
    }

    private void actionPriceEdit() {
        if (jbGridPriceEdit.isEnabled()) {
            mnAuxPriceEditIndex = moPaneGridPrices.getTable().convertRowIndexToModel(moPaneGridPrices.getTable().getSelectedRow());

            if (mnAuxPriceEditIndex != -1) {
                moAuxPriceEdit = (SDataDpsEntryPrice) moPaneGridPrices.getSelectedTableRow().getData();
                
                enableDeliveryPriceButtonFields(false);
                enablePriceEntryContractFields(true);
                enableDeliveryPriceFields(true);
                enablePriceGridFields(false);

                moAuxPriceEdit.setIsRegistryEdited(true);
                
                jtfContractPriceYear.setEditable(false);
                jtfContractPriceMonth.setEditable(false);
                
                jckIsDirectPrice.setSelected(moAuxPriceEdit.getIsPriceVariable());
                moFieldContractPriceReferenceNumbrer.setString(moAuxPriceEdit.getReferenceNumber());
                moFieldContractPriceYear.setInteger(moAuxPriceEdit.getContractPriceYear());
                moFieldContractPriceMonth.setInteger(moAuxPriceEdit.getContractPriceMonth());
                moFieldPriceOriginalQuantity.setDouble(moAuxPriceEdit.getOriginalQuantity());
                jckChangePrice.setSelected(moAuxPriceEdit.getOriginalPriceUnitaryCy() != moAuxPriceEdit.getOriginalPriceUnitaryCySystem());
                moFieldPriceOriginalPriceUnitaryCy.setDouble(moAuxPriceEdit.getOriginalPriceUnitaryCy());
                moFieldContractBase.setDouble(moAuxPriceEdit.getContractBase());
                moFieldContractFuture.setDouble(moAuxPriceEdit.getContractFuture());
                moFieldContractFactor.setDouble(moAuxPriceEdit.getContractFactor());
                
            }
        }
    }

    private void actionPriceDelete() {
        if (jbGridPriceDelete.isEnabled()) {
            mnAuxPriceEditIndex = moPaneGridPrices.getTable().getSelectedRow();

            if (mnAuxPriceEditIndex != -1) {
                if (miClient.showMsgBoxConfirm(SLibConstants.MSG_CNF_REG_DELETE) == JOptionPane.YES_OPTION) {
                    moAuxPriceEdit = (SDataDpsEntryPrice) moPaneGridPrices.getTableRow(mnAuxPriceEditIndex).getData();

                    if (moAuxPriceEdit.getIsDeleted()) {
                        miClient.showMsgBoxWarning(SLibConstants.MSG_ERR_GUI_REG_ALREADY_DELETE);
                    }
                    else {
                        if (moAuxPriceEdit.getUserNewTs() == null) {
                            moPaneGridPrices.removeTableRow(mnAuxPriceEditIndex);
                        }
                        else {
                            moAuxPriceEdit.setIsDeleted(true);
                            moAuxPriceEdit.setFkUserDeleteId(miClient.getSession().getUser().getPkUserId());
                            moAuxPriceEdit.setIsRegistryEdited(true);
                            moAuxPriceEdit.setFkUserEditId(miClient.getSession().getUser().getPkUserId());

                            moPaneGridPrices.setTableRow(new SDataDpsEntryPriceRow(moAuxPriceEdit), mnAuxPriceEditIndex);
                        }

                        moPaneGridPrices.renderTableRows();
                        moPaneGridPrices.setTableRowSelection(mnAuxPriceEditIndex < moPaneGridPrices.getTableGuiRowCount() ? mnAuxPriceEditIndex : moPaneGridPrices.getTableGuiRowCount() - 1);
                    }
                    moAuxPriceEdit = null;
                }                
            }
        }
    }

    private void actionPriceFilter() {
        if (jtbGridPriceFilter.isEnabled()) {
            mnAuxPriceEditIndex = moPaneGridPrices.getTable().getSelectedRow();
            moPaneGridPrices.setGridViewStatus(!jtbGridPriceFilter.isSelected() ? STableConstants.VIEW_STATUS_ALL : STableConstants.VIEW_STATUS_ALIVE);
            moPaneGridPrices.renderTableRows();
            moPaneGridPrices.setTableRowSelection(mnAuxPriceEditIndex < moPaneGridPrices.getTableGuiRowCount() ? mnAuxPriceEditIndex : moPaneGridPrices.getTableGuiRowCount() - 1);
        }
    }

    private void actionPriceSave() {
        if(jbPriceSave.isEnabled()){
            if (moAuxPriceEdit != null) {
                if (moFieldContractPriceReferenceNumbrer.getString().isEmpty()) {
                        miClient.showMsgBoxWarning(SGuiConsts.ERR_MSG_FIELD_REQ + "'" + moFieldContractPriceReferenceNumbrer.getFieldName() + "'.");
                        jtfContractPriceNumbrerReference.requestFocus();
                        return;
                }
                
                if (!moFieldContractPriceYear.validateFieldForcing() || !moFieldContractPriceMonth.validateFieldForcing()) {
                    return;
                }
                
                if (moFieldPriceOriginalQuantity.getDouble() < 0) {
                        miClient.showMsgBoxWarning(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + moFieldPriceOriginalQuantity.getFieldName() + "'.");
                        jtfPriceOriginalQuantity.requestFocus();
                        return;
                }
                
                if (moFieldPriceOriginalPriceUnitaryCy.getDouble() < 0) {
                        miClient.showMsgBoxWarning(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + moFieldPriceOriginalPriceUnitaryCy.getFieldName() + "'.");
                        jtfPriceOriginalPriceUnitaryCy.requestFocus();
                        return;
                }
                
                if (!jckIsDirectPrice.isSelected()) {
                    if (moFieldContractBase.getDouble() <= 0) {
                        miClient.showMsgBoxWarning(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + moFieldContractBase.getFieldName() + "'.");
                        jtfContractBase.requestFocus();
                        return;
                    }
                    if (moFieldContractFuture.getDouble() <= 0) {
                        miClient.showMsgBoxWarning(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + moFieldContractFuture.getFieldName() + "'.");
                        jtfContractFuture.requestFocus();
                        return;
                    }
                    if (moFieldContractFactor.getDouble() <= 0) {
                        miClient.showMsgBoxWarning(SGuiConsts.ERR_MSG_FIELD_DIF + "'" + moFieldContractFactor.getFieldName() + "'.");
                        jtfContractFactor.requestFocus();
                        return;
                    }
                }
                
                SDataDpsEntryPrice entryPrice = null;
                boolean canSave = true;
                
                for (int i = 0; i < moPaneGridPrices.getGridRows().size(); i++) {
                    entryPrice = (SDataDpsEntryPrice) moPaneGridPrices.getGridRows().get(i).getData();
                    if (entryPrice.getContractPriceYear() == moFieldContractPriceYear.getInteger() && entryPrice.getContractPriceMonth() == moFieldContractPriceMonth.getInteger()) {
                        canSave =(!entryPrice.getIsDeleted() && entryPrice.getIsRegistryEdited())|| (entryPrice.getIsDeleted() && entryPrice.getIsRegistryEdited()) || entryPrice.getIsDeleted();
                    }
                }
                
                if (canSave) {
                    moAuxPriceEdit.setReferenceNumber(moFieldContractPriceReferenceNumbrer.getString());
                    moAuxPriceEdit.setIsPriceVariable(jckIsDirectPrice.isSelected());
                    moAuxPriceEdit.setContractPriceYear(moFieldContractPriceYear.getInteger());
                    moAuxPriceEdit.setContractPriceMonth(moFieldContractPriceMonth.getInteger());
                    moAuxPriceEdit.setOriginalQuantity(moFieldPriceOriginalQuantity.getDouble());

                    moAuxPriceEdit.setOriginalPriceUnitaryCy(moFieldPriceOriginalPriceUnitaryCy.getDouble());
                    moAuxPriceEdit.setContractBase(moFieldContractBase.getDouble());
                    moAuxPriceEdit.setContractFuture(moFieldContractFuture.getDouble());
                    moAuxPriceEdit.setContractFactor(moFieldContractFactor.getDouble());
                    moAuxPriceEdit.setIsDeleted(false);
                    
                    if (moAuxPriceEdit.getUserNewTs() == null) {
                        moAuxPriceEdit.setFkUserNewId(miClient.getSession().getUser().getPkUserId());
                        
                        if (moAuxPriceEdit.getIsRegistryEdited()) {
                            moAuxPriceEdit.setIsRegistryEdited(false);
                            moPaneGridPrices.setTableRow(new SDataDpsEntryPriceRow(moAuxPriceEdit), moPaneGridPrices.getTable().convertRowIndexToView(mnAuxPriceEditIndex));
                            moPaneGridPrices.renderTableRows();
                            moPaneGridPrices.setTableRowSelection(mnAuxPriceEditIndex < 0 ? moPaneGridPrices.getTableGuiRowCount() - 1 : mnAuxPriceEditIndex);
                            
                        }
                        else {
                            moPaneGridPrices.addTableRow(new SDataDpsEntryPriceRow(moAuxPriceEdit));
                            moPaneGridPrices.renderTableRows(); 
                            moPaneGridPrices.setTableRowSelection(mnAuxPriceEditIndex < 0 ? moPaneGridPrices.getTableGuiRowCount() - 1 : mnAuxPriceEditIndex);

                        }
                    }
                    else {
                        moAuxPriceEdit.setFkUserEditId(miClient.getSession().getUser().getFkUserTypeId());
                        moPaneGridPrices.setTableRow(new SDataDpsEntryPriceRow(moAuxPriceEdit), moPaneGridPrices.getTable().convertRowIndexToView(mnAuxPriceEditIndex));
                        moPaneGridPrices.renderTableRows();
                        moPaneGridPrices.setTableRowSelection(mnAuxPriceEditIndex < 0 ? moPaneGridPrices.getTableGuiRowCount() - 1 : mnAuxPriceEditIndex);
                    }
                    actionPriceClearFields();
                }
                else {
                    miClient.showMsgBoxWarning("Ya existe un registro para ese periodo de entrega.");
                }
            }
            else {
                miClient.showMsgBoxWarning("Capturar previamente un periodo de entrega.");
            }
        }
    }

    private void actionPriceClearFields() {
        if (jbClearPriceFields.isEnabled()) {
            enableDeliveryPriceButtonFields(true);
            enablePriceEntryContractFields(false);
            enableDeliveryPriceFields(false);
            enablePriceGridFields(true);
        
            moFieldContractPriceReferenceNumbrer.setString("");
            moFieldContractPriceYear.setInteger(SLibTimeUtilities.digestYearMonth(miClient.getSession().getCurrentDate())[0]);
            moFieldContractPriceMonth.setInteger(SLibTimeUtilities.digestYearMonth(miClient.getSession().getCurrentDate())[1]);
            jckIsDirectPrice.setSelected(true);
            moFieldPriceOriginalQuantity.setDouble(0d);
            jckChangePrice.setSelected(false);
            moFieldPriceOriginalPriceUnitaryCy.setDouble(0d);
            moFieldContractBase.setDouble(moFieldDpsContractBase.getDouble());
            moFieldContractFuture.setDouble(moFieldDpsContractFuture.getDouble());
            moFieldContractFactor.setDouble(moFieldDpsContractFactor.getDouble());
            if (moAuxPriceEdit != null) {
                moAuxPriceEdit.setIsRegistryEdited(false);
                moAuxPriceEdit = null;
                mnAuxPriceEditIndex = -1;
            }
            jbPriceNew.requestFocus();
        }    
    }
    
    private void calculateEntryPrice() {
        double price = 0;
        
        if (jcbFkItemId.getSelectedIndex() > 0 && jbPriceSave.isEnabled()) {
            if (!jckChangePrice.isSelected()) {
                if (!jckIsDirectPrice.isSelected()) {
                    double conversionFactor = ((SSessionCustom) miClient.getSession().getSessionCustom()).getUnitsFactorForQuantity(0, SModSysConsts.ITMU_UNIT_KG, SModSysConsts.ITMU_UNIT_LB);
                    double conversionOriginalQuantity = ((SSessionCustom) miClient.getSession().getSessionCustom()).getUnitsFactorForQuantity(moItem.getPkItemId(), SModSysConsts.ITMU_UNIT_MT_TON, ((int[]) moFieldFkOriginalUnitId.getKey())[0]);
                    price = STrnUtilities.calculateDpsEntryPriceUnitary(moFieldContractBase.getDouble(), moFieldContractFuture.getDouble(), conversionFactor, moFieldContractFactor.getDouble(), conversionOriginalQuantity);
                }
                else {
                    price = moFieldOriginalPriceUnitaryCy.getDouble();
                }
                moFieldPriceOriginalPriceUnitaryCy.setDouble(price);
            }
        }
    }

    private void actionFkTaxRegionId() {
        if (miClient.pickOption(SDataConstants.FINU_TAX_REG, moFieldFkTaxRegionId, null) == SLibConstants.FORM_RESULT_OK) {
            itemChangedFkTaxRegionId();
        }
    }

    private void actionFkItemReferenceId_n() {
        miClient.pickOption(SDataConstants.ITMX_ITEM_IOG, moFieldFkItemReferenceId_n, null);
    }

    private void actionEdit() {

    }

    private void actionOk() {
        SFormValidation validation = null;

        jbOk.requestFocus();    // this forces all pending focus lost function to be called

        validation = formValidate();

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

    public void publicPriceUnitaryCyWizard() {
        actionPriceUnitaryCyWizard();
    }

    public void publicPriceHistory() {
        actionPriceHistory();
    }

    public void publicActionNotesNew() {
        if (jTabbedPane.getSelectedIndex() == 4) {
            actionNotesNew();
        }
    }

    public void publicActionNotesEdit() {
        if (jTabbedPane.getSelectedIndex() == 4) {
            actionNotesEdit();
        }
    }

    public void publicActionNotesDelete() {
        if (jTabbedPane.getSelectedIndex() == 4) {
            actionNotesDelete();
        }
    }

    public void publicActionNotesFilter() {
        if (jTabbedPane.getSelectedIndex() == 4) {
            jtbNotesFilter.setSelected(!jtbNotesFilter.isSelected());
        }
    }
    
    public void publicActionPriceNew() {
        if (jTabbedPane.getSelectedIndex() == 3) {
            actionPriceNew();
        }
    }

    public void publicActionPriceEdit() {
        if (jTabbedPane.getSelectedIndex() == 3) {
            actionPriceEdit();
        }
    }

    public void publicActionPriceDelete() {
        if (jTabbedPane.getSelectedIndex() == 3) {
            actionPriceDelete();
        }
    }

    public void publicActionPrcieFilter() {
        if (jTabbedPane.getSelectedIndex() == 3) {
            jtbNotesFilter.setSelected(!jtbNotesFilter.isSelected());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPricesData;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbClearPriceFields;
    private javax.swing.JButton jbConcept;
    private javax.swing.JButton jbFkItemId;
    private javax.swing.JButton jbFkItemReferenceId_n;
    private javax.swing.JButton jbFkOriginalUnitId;
    private javax.swing.JButton jbFkTaxRegionId;
    private javax.swing.JButton jbGridPriceDelete;
    private javax.swing.JButton jbGridPriceEdit;
    private javax.swing.JButton jbGridPriceNew;
    private javax.swing.JButton jbItemBizPartnerDescription;
    private javax.swing.JButton jbKey;
    private javax.swing.JButton jbNotesDelete;
    private javax.swing.JButton jbNotesEdit;
    private javax.swing.JButton jbNotesNew;
    private javax.swing.JButton jbOk;
    private javax.swing.JButton jbPriceHistory;
    private javax.swing.JButton jbPriceNew;
    private javax.swing.JButton jbPriceSave;
    private javax.swing.JButton jbPriceUnitaryCyWizard;
    private javax.swing.JButton jbSystemNotes;
    private javax.swing.JComboBox<SFormComponentItem> jcbAddendaFkBarcode;
    private javax.swing.JComboBox<SFormComponentItem> jcbFkItemId;
    private javax.swing.JComboBox<SFormComponentItem> jcbFkItemReferenceId_n;
    private javax.swing.JComboBox<SFormComponentItem> jcbFkOriginalUnitId;
    private javax.swing.JComboBox<SFormComponentItem> jcbFkTaxRegionId;
    private javax.swing.JComboBox<SFormComponentItem> jcbFkVehicleTypeId_n;
    private javax.swing.JCheckBox jckAuxPreserveQuantity;
    private javax.swing.JCheckBox jckChangePrice;
    private javax.swing.JCheckBox jckIsBulk;
    private javax.swing.JCheckBox jckIsDeleted;
    private javax.swing.JCheckBox jckIsDirectPrice;
    private javax.swing.JCheckBox jckIsDiscountDocApplying;
    private javax.swing.JCheckBox jckIsDiscountEntryPercentage;
    private javax.swing.JCheckBox jckIsDiscountRetailChain;
    private javax.swing.JCheckBox jckIsDiscountUnitaryPercentage;
    private javax.swing.JCheckBox jckIsDpsReqMonthDelivery;
    private javax.swing.JCheckBox jckIsInventoriable;
    private javax.swing.JCheckBox jckIsPriceConfirm;
    private javax.swing.JCheckBox jckIsSurplusPercentageApplying;
    private javax.swing.JCheckBox jckIsTaxesAutomaticApplying;
    private javax.swing.JLabel jlAddendaCagePriceUnitary;
    private javax.swing.JLabel jlAddendaCages;
    private javax.swing.JLabel jlAddendaCenter;
    private javax.swing.JLabel jlAddendaEntryNumber;
    private javax.swing.JLabel jlAddendaFkBarcode;
    private javax.swing.JLabel jlAddendaNumberPosition;
    private javax.swing.JLabel jlAddendaOrder;
    private javax.swing.JLabel jlAddendaPartPriceUnitary;
    private javax.swing.JLabel jlAddendaParts;
    private javax.swing.JLabel jlConcept;
    private javax.swing.JLabel jlContractBase;
    private javax.swing.JLabel jlContractFactor;
    private javax.swing.JLabel jlContractFuture;
    private javax.swing.JLabel jlContractPriceMonth;
    private javax.swing.JLabel jlContractPriceNumbrerReference;
    private javax.swing.JLabel jlContractPriceYear;
    private javax.swing.JLabel jlCurrency;
    private javax.swing.JLabel jlDiscountDoc;
    private javax.swing.JLabel jlDiscountEntry;
    private javax.swing.JLabel jlDiscountUnitary;
    private javax.swing.JLabel jlDpsContractBase;
    private javax.swing.JLabel jlDpsContractFactor;
    private javax.swing.JLabel jlDpsContractFuture;
    private javax.swing.JLabel jlDummyCostCenter;
    private javax.swing.JLabel jlFkItemId;
    private javax.swing.JLabel jlFkItemReferenceId_n;
    private javax.swing.JLabel jlFkOriginalUnitId;
    private javax.swing.JLabel jlFkTaxRegionId;
    private javax.swing.JLabel jlFkVehicleTypeId_n;
    private javax.swing.JLabel jlIsPriceVariable;
    private javax.swing.JLabel jlKey;
    private javax.swing.JLabel jlLength;
    private javax.swing.JLabel jlMass;
    private javax.swing.JLabel jlOriginalDiscountUnitaryCy;
    private javax.swing.JLabel jlOriginalPriceUnitaryCy;
    private javax.swing.JLabel jlOriginalQuantity;
    private javax.swing.JLabel jlPriceOriginalPriceUnitaryCy;
    private javax.swing.JLabel jlPriceOriginalQuantity;
    private javax.swing.JLabel jlPriceUnitary;
    private javax.swing.JLabel jlPriceUnitaryReal_r;
    private javax.swing.JLabel jlQuantity;
    private javax.swing.JLabel jlReference;
    private javax.swing.JLabel jlSecuritySeal;
    private javax.swing.JLabel jlSubtotalProvisional_r;
    private javax.swing.JLabel jlSubtotal_r;
    private javax.swing.JLabel jlSurface;
    private javax.swing.JLabel jlTaxCharged_r;
    private javax.swing.JLabel jlTaxIdentityEmisor;
    private javax.swing.JLabel jlTaxIdentityReceptor;
    private javax.swing.JLabel jlTaxRetained_r;
    private javax.swing.JLabel jlTicket;
    private javax.swing.JLabel jlTotal_r;
    private javax.swing.JLabel jlVehicleNumber;
    private javax.swing.JLabel jlVolume;
    private javax.swing.JLabel jlWeigthDelivery;
    private javax.swing.JLabel jlWeigthGross;
    private javax.swing.JPanel jpAddendaData;
    private javax.swing.JPanel jpCommissions;
    private javax.swing.JPanel jpControls;
    private javax.swing.JPanel jpCostCenter;
    private javax.swing.JPanel jpExtraData;
    private javax.swing.JPanel jpExtraDataContract;
    private javax.swing.JPanel jpExtraDataOther;
    private javax.swing.JPanel jpExtraDataOtherFillment;
    private javax.swing.JPanel jpExtraDataOtherNorth;
    private javax.swing.JPanel jpExtraDataUnits;
    private javax.swing.JPanel jpExtraDataUnitsNorth;
    private javax.swing.JPanel jpNotes;
    private javax.swing.JPanel jpNotesControls;
    private javax.swing.JPanel jpNotesControls1;
    private javax.swing.JPanel jpPrices;
    private javax.swing.JPanel jpRegistry;
    private javax.swing.JPanel jpTaxInfo;
    private javax.swing.JPanel jpTaxes;
    private javax.swing.JToggleButton jtbGridPriceFilter;
    private javax.swing.JToggleButton jtbNotesFilter;
    private javax.swing.JTextField jtfAddendaCagePriceUnitary;
    private javax.swing.JTextField jtfAddendaCages;
    private javax.swing.JTextField jtfAddendaCenter;
    private javax.swing.JTextField jtfAddendaEntryNumber;
    private javax.swing.JTextField jtfAddendaNumberPosition;
    private javax.swing.JTextField jtfAddendaOrder;
    private javax.swing.JTextField jtfAddendaPartPriceUnitary;
    private javax.swing.JTextField jtfAddendaParts;
    private javax.swing.JTextField jtfConcept;
    private javax.swing.JTextField jtfContractBase;
    private javax.swing.JTextField jtfContractFactor;
    private javax.swing.JTextField jtfContractFuture;
    private javax.swing.JTextField jtfContractPriceMonth;
    private javax.swing.JTextField jtfContractPriceNumbrerReference;
    private javax.swing.JTextField jtfContractPriceYear;
    private javax.swing.JTextField jtfCurrencyKeyDiscountUnitaryCyRo;
    private javax.swing.JTextField jtfCurrencyKeyPriceUnitaryCyRo;
    private javax.swing.JTextField jtfCurrencyKeyRo;
    private javax.swing.JTextField jtfCurrencySystemKeyRo;
    private javax.swing.JTextField jtfDiscountDocCy;
    private javax.swing.JTextField jtfDiscountDocRo;
    private javax.swing.JTextField jtfDiscountEntryCy;
    private javax.swing.JTextField jtfDiscountEntryPercentage;
    private javax.swing.JTextField jtfDiscountEntryRo;
    private javax.swing.JTextField jtfDiscountUnitaryCyRo;
    private javax.swing.JTextField jtfDiscountUnitaryPercentage;
    private javax.swing.JTextField jtfDiscountUnitaryRo;
    private javax.swing.JTextField jtfDpsContractBase;
    private javax.swing.JTextField jtfDpsContractFactor;
    private javax.swing.JTextField jtfDpsContractFuture;
    private javax.swing.JTextField jtfKey;
    private javax.swing.JTextField jtfLength;
    private javax.swing.JTextField jtfLengthUnitSymbolRo;
    private javax.swing.JTextField jtfMass;
    private javax.swing.JTextField jtfMassUnitSymbolRo;
    private javax.swing.JTextField jtfOriginalDiscountUnitaryCy;
    private javax.swing.JTextField jtfOriginalPriceUnitaryCy;
    private javax.swing.JTextField jtfOriginalQuantity;
    private javax.swing.JTextField jtfOriginalUnitSymbolRo;
    private javax.swing.JTextField jtfPriceCurrencyKeyPriceUnitaryCy;
    private javax.swing.JTextField jtfPriceOriginalPriceUnitaryCy;
    private javax.swing.JTextField jtfPriceOriginalQuantity;
    private javax.swing.JTextField jtfPriceOriginalUnitSymbol;
    private javax.swing.JTextField jtfPriceUnitaryCyRo;
    private javax.swing.JTextField jtfPriceUnitaryRealCy_rRo;
    private javax.swing.JTextField jtfPriceUnitaryReal_rRo;
    private javax.swing.JTextField jtfPriceUnitaryRo;
    private javax.swing.JTextField jtfQuantityRo;
    private javax.swing.JTextField jtfReference;
    private javax.swing.JTextField jtfSecuritySeal;
    private javax.swing.JTextField jtfSubtotalCy_rRo;
    private javax.swing.JTextField jtfSubtotalProvisionalCy_rRo;
    private javax.swing.JTextField jtfSubtotalProvisional_rRo;
    private javax.swing.JTextField jtfSubtotal_rRo;
    private javax.swing.JTextField jtfSurface;
    private javax.swing.JTextField jtfSurfaceUnitSymbolRo;
    private javax.swing.JTextField jtfSurplusPercentage;
    private javax.swing.JTextField jtfTaxChargedCy_rRo;
    private javax.swing.JTextField jtfTaxCharged_rRo;
    private javax.swing.JTextField jtfTaxIdentityEmisorRo;
    private javax.swing.JTextField jtfTaxIdentityReceptorRo;
    private javax.swing.JTextField jtfTaxRetainedCy_rRo;
    private javax.swing.JTextField jtfTaxRetained_rRo;
    private javax.swing.JTextField jtfTicket;
    private javax.swing.JTextField jtfTotalCy_rRo;
    private javax.swing.JTextField jtfTotal_rRo;
    private javax.swing.JTextField jtfUnitSymbolRo;
    private javax.swing.JTextField jtfVehicleNumber;
    private javax.swing.JTextField jtfVolume;
    private javax.swing.JTextField jtfVolumeUnitSymbolRo;
    private javax.swing.JTextField jtfWeigthDeliveryRo;
    private javax.swing.JTextField jtfWeigthDeliveryUnitSymbolRo;
    private javax.swing.JTextField jtfWeigthGrossRo;
    private javax.swing.JTextField jtfWeigthGrossUnitSymbolRo;
    // End of variables declaration//GEN-END:variables

    /*
     * Public methods
     */

    public void hasDpsLinksButIsEditable() {
        jcbFkItemId.setEnabled(false);
        jbFkItemId.setEnabled(false);
        jtfKey.setEditable(false);
        jtfKey.setFocusable(false);
        jbKey.setEnabled(false);
        jtfConcept.setEditable(false);
        jtfConcept.setFocusable(false);
        jbConcept.setEnabled(false);
        jcbFkOriginalUnitId.setEnabled(false);
        jbFkOriginalUnitId.setEnabled(false);
        jckIsDeleted.setEnabled(false);

        jckIsDiscountDocApplying.setEnabled(false);
        jckIsDiscountUnitaryPercentage.setEnabled(false);
        jtfDiscountUnitaryPercentage.setEditable(false);
        jtfDiscountUnitaryPercentage.setFocusable(false);
        jckIsDiscountEntryPercentage.setEnabled(false);
        jtfDiscountEntryPercentage.setEditable(false);
        jtfDiscountEntryPercentage.setFocusable(false);

        jtfOriginalQuantity.setEditable(true);
        jtfOriginalQuantity.setFocusable(true);
        /*
        jtfOriginalPriceUnitaryCy.setEditable(false);
        jtfOriginalPriceUnitaryCy.setFocusable(false);
        jtfOriginalDiscountUnitaryCy.setEditable(false);
        jtfOriginalDiscountUnitaryCy.setFocusable(false);
        jbPriceUnitaryCyWizard.setEnabled(false);

        jtfDiscountEntryCy.setEditable(false);
        jtfDiscountEntryCy.setFocusable(false);
        jtfDiscountDocCy.setEditable(false);
        jtfDiscountDocCy.setFocusable(false);
        */

        jcbFkItemReferenceId_n.setEnabled(false);
        jbFkItemReferenceId_n.setEnabled(false);
        moPanelFkCostCenterId_n.enableFields(true);

        /*
        jcbFkTaxRegionId.setEnabled(false);
        jbFkTaxRegionId.setEnabled(false);
        jckIsTaxesAutomaticApplying.setEnabled(false);
        */

        jtfLength.setEnabled(false);
        jtfSurface.setEnabled(false);
        jtfVolume.setEnabled(false);
        jtfMass.setEnabled(false);
    }

    public void hasDpsAdjustmentsAsAdjButIsEditable() {
        jcbFkItemId.setEnabled(false);
        jbFkItemId.setEnabled(false);
        /*
        jtfKey.setEditable(false);
        jtfKey.setFocusable(false);
        jbKey.setEnabled(false);
        jtfConcept.setEditable(false);
        jtfConcept.setFocusable(false);
        jbConcept.setEnabled(false);
        */
        jcbFkOriginalUnitId.setEnabled(false);
        jbFkOriginalUnitId.setEnabled(false);
        jckIsDeleted.setEnabled(false);

        /*
        jckIsDiscountDocApplying.setEnabled(false);
        jckIsDiscountUnitaryPercentage.setEnabled(false);
        jtfDiscountUnitaryPercentage.setEditable(false);
        jtfDiscountUnitaryPercentage.setFocusable(false);
        jckIsDiscountEntryPercentage.setEnabled(false);
        jtfDiscountEntryPercentage.setEditable(false);
        jtfDiscountEntryPercentage.setFocusable(false);
        */

        jtfOriginalQuantity.setEditable(true);
        jtfOriginalQuantity.setFocusable(true);
        /*
        jtfOriginalPriceUnitaryCy.setEditable(false);
        jtfOriginalPriceUnitaryCy.setFocusable(false);
        jtfOriginalDiscountUnitaryCy.setEditable(false);
        jtfOriginalDiscountUnitaryCy.setFocusable(false);
        jbPriceUnitaryCyWizard.setEnabled(false);

        jtfDiscountEntryCy.setEditable(false);
        jtfDiscountEntryCy.setFocusable(false);
        jtfDiscountDocCy.setEditable(false);
        jtfDiscountDocCy.setFocusable(false);
        */

        jcbFkItemReferenceId_n.setEnabled(false);
        jbFkItemReferenceId_n.setEnabled(false);
        moPanelFkCostCenterId_n.enableFields(true);

        /*
        jcbFkTaxRegionId.setEnabled(false);
        jbFkTaxRegionId.setEnabled(false);
        jckIsTaxesAutomaticApplying.setEnabled(false);
        */

        jtfLength.setEnabled(false);
        jtfSurface.setEnabled(false);
        jtfVolume.setEnabled(false);
        jtfMass.setEnabled(false);
    }
    
    public void enablePriceContractFields (boolean enabled) {
        boolean isFormEdit = mnFormStatus != SLibConstants.FORM_STATUS_READ_ONLY;
        boolean isCurUsd = ((moParamDps != null) && (moParamDps.getFkCurrencyId() == SModSysConsts.CFGU_CUR_USD));
        boolean isDpsReqMonthDelivery = jckIsDpsReqMonthDelivery.isSelected();
        
        jckIsPriceConfirm.setEnabled(isFormEdit && enabled && isDpsReqMonthDelivery);
        jckIsPriceConfirm.setFocusable(isFormEdit && enabled && isDpsReqMonthDelivery);
        jtfDpsContractBase.setEditable(isFormEdit && enabled && isCurUsd && isDpsReqMonthDelivery);
        jtfDpsContractBase.setFocusable(isFormEdit && enabled && isCurUsd && isDpsReqMonthDelivery);
        jtfDpsContractFuture.setEditable(isFormEdit && enabled && isCurUsd && isDpsReqMonthDelivery);
        jtfDpsContractFuture.setFocusable(isFormEdit && enabled && isCurUsd && isDpsReqMonthDelivery);
        
        jckIsDirectPrice.setEnabled(isFormEdit && enabled && isCurUsd);
        
        enablePriceEntryContractFields(false);
        enableDeliveryPriceFields(false);
        enablePriceGridFields(enabled);
        enableDeliveryPriceButtonFields(enabled);
        
    }
    
    public void enablePriceEntryContractFields (boolean enabled) {
        boolean isFormEdit = mnFormStatus != SLibConstants.FORM_STATUS_READ_ONLY;
        boolean isCurUsd = ((moParamDps != null) && (moParamDps.getFkCurrencyId() == SModSysConsts.CFGU_CUR_USD));
        boolean isDpsReqMonthDelivery = jckIsDpsReqMonthDelivery.isSelected();
        
        jckIsDirectPrice.setEnabled(isCurUsd && isFormEdit && !enabled && isDpsReqMonthDelivery);
        jtfContractPriceNumbrerReference.setEditable(isFormEdit && enabled && isDpsReqMonthDelivery);
        jtfContractPriceYear.setEditable(isFormEdit && enabled && isDpsReqMonthDelivery);
        jtfContractPriceMonth.setEditable(isFormEdit && enabled && isDpsReqMonthDelivery);
        jtfPriceOriginalQuantity.setEditable(isFormEdit && enabled && isDpsReqMonthDelivery);
        jtfContractPriceNumbrerReference.setFocusable(isFormEdit && enabled && isDpsReqMonthDelivery);
        jtfContractPriceYear.setFocusable(isFormEdit && enabled && isDpsReqMonthDelivery);
        jtfContractPriceMonth.setFocusable(isFormEdit && enabled && isDpsReqMonthDelivery);
        jtfPriceOriginalQuantity.setFocusable(isFormEdit && enabled && isDpsReqMonthDelivery);
    }
    
    public void enableDeliveryPriceFields(boolean enabled){
        boolean bCurUSD = ((moParamDps != null) && (moParamDps.getFkCurrencyId() == SModSysConsts.CFGU_CUR_USD));
        boolean isDpsReqMonthDelivery = jckIsDpsReqMonthDelivery.isSelected();
        boolean isSaveButtonEnabled = jbPriceSave.isEnabled();
        boolean isFormEdit = mnFormStatus != SLibConstants.FORM_STATUS_READ_ONLY;
        
        jckChangePrice.setEnabled(isDpsReqMonthDelivery && isSaveButtonEnabled);
        
        jtfContractBase.setEditable(isFormEdit && bCurUSD && isDpsReqMonthDelivery && isSaveButtonEnabled && !enabled);
        jtfContractBase.setFocusable(isFormEdit && bCurUSD && isDpsReqMonthDelivery && isSaveButtonEnabled && !enabled);
        jtfContractFuture.setEditable(isFormEdit && bCurUSD && isDpsReqMonthDelivery && isSaveButtonEnabled && !enabled);
        jtfContractFuture.setFocusable(isFormEdit && bCurUSD && isDpsReqMonthDelivery && isSaveButtonEnabled && !enabled);
    }
    
    public void enableDeliveryPriceButtonFields(boolean enabled) {
        boolean isDpsReqMonthDelivery = jckIsDpsReqMonthDelivery.isSelected();
        boolean isFormEdit = mnFormStatus != SLibConstants.FORM_STATUS_READ_ONLY;
        
        jbPriceNew.setEnabled(isFormEdit && enabled && isDpsReqMonthDelivery);
        jbPriceSave.setEnabled(isFormEdit && !enabled && isDpsReqMonthDelivery);
        jbClearPriceFields.setEnabled(isFormEdit && !enabled && isDpsReqMonthDelivery);
    }
    
    public void enableChangeEntryPrice(boolean enabled) {
        boolean isDpsReqMonthDelivery = jckIsDpsReqMonthDelivery.isSelected();
        boolean isFormEdit = mnFormStatus != SLibConstants.FORM_STATUS_READ_ONLY;
        
        jtfPriceOriginalPriceUnitaryCy.setEditable(isFormEdit && enabled && isDpsReqMonthDelivery);
        jtfPriceOriginalPriceUnitaryCy.setFocusable(isFormEdit && enabled && isDpsReqMonthDelivery);
        
        calculateEntryPrice();
    }
    
    public void enablePriceGridFields(boolean enabled){
        boolean isFormEdit = mnFormStatus != SLibConstants.FORM_STATUS_READ_ONLY;
        
        jbGridPriceNew.setEnabled(isFormEdit && false);
        jbGridPriceEdit.setEnabled(isFormEdit && enabled);
        jbGridPriceDelete.setEnabled(isFormEdit && enabled);
        jtbGridPriceFilter.setEnabled(isFormEdit && enabled);
        moPaneGridPrices.setEnabled(isFormEdit && enabled);
    }
    
    public void setQuantityLimit(double dQuantityDes, double dQuantityAdj, double dQuantityPrc, boolean isLastPrc) {
        Vector<SDataDpsDpsLink> vDbmsDpsLinksAsSource = null;
        Vector<SDataDpsDpsLink> vDbmsDpsLinksAsDestiny = null;
        Vector<SDataDpsDpsAdjustment> vDbmsDpsAdjustmentsAsDps = null;
        Vector<SDataDpsDpsAdjustment> vDbmsDpsAdjustmentsAsAdjustment = null;
        
        mdQuantityPrc = dQuantityPrc;
        mbIsLastPrc = isLastPrc;

        if (SDataUtilities.callProcedureVal(miClient, SProcConstants.TRN_DPS_ETY_COUNT_DIOG, moDpsEntry.getPrimaryKey(), SLibConstants.EXEC_MODE_SILENT) > 0) {
            mdQuantitySrcOrig = moDpsEntry.getOriginalQuantity();
        }

        if (SDataUtilities.callProcedureVal(miClient, SProcConstants.TRN_DPS_ETY_COUNT_SHIP, moDpsEntry.getPrimaryKey(), SLibConstants.EXEC_MODE_SILENT) > 0) {
            mdQuantitySrcOrig = moDpsEntry.getOriginalQuantity();
        }

        if (moDpsEntry.hasDpsLinksAsSource()) {
            vDbmsDpsLinksAsSource = moDpsEntry.getDbmsDpsLinksAsSource();

            for (SDataDpsDpsLink link : vDbmsDpsLinksAsSource) {
                try {
                    mdQuantitySrcOrig = STrnUtilities.obtainQuantityLimit(miClient, SDataConstants.TRN_DPS_DPS_SUPPLY, link.getDbmsSourceDpsEntryKey(), null);
                }
                catch (java.lang.Exception e) {
                    SLibUtilities.renderException(this, e);
                }
            }
        }

        if (moDpsEntry.hasDpsLinksAsDestiny()) {
            vDbmsDpsLinksAsDestiny = moDpsEntry.getDbmsDpsLinksAsDestiny();

            for (SDataDpsDpsLink link : vDbmsDpsLinksAsDestiny) {
                try {
                    mdQuantityDesOrig = STrnUtilities.obtainQuantityLimit(miClient, SDataConstants.TRN_DPS_DPS_SUPPLY, link.getDbmsSourceDpsEntryKey(), link.getDbmsDestinyDpsEntryKey()) - dQuantityDes;
                }
                catch (java.lang.Exception e) {
                    SLibUtilities.renderException(this, e);
                }
            }
        }

        if (mdQuantitySrcOrig == 0 && (moDpsEntry.hasDpsAdjustmentsAsDoc() || SDataUtilities.callProcedureVal(miClient, SProcConstants.TRN_DPS_ETY_COUNT_ADJ_DOC, moDpsEntry.getPrimaryKey(), SLibConstants.EXEC_MODE_SILENT) > 0)) {
            vDbmsDpsAdjustmentsAsDps = moDpsEntry.getDbmsDpsAdjustmentsAsDps();

            for (SDataDpsDpsAdjustment adjustment : vDbmsDpsAdjustmentsAsDps) {
                try {
                    mdQuantitySrcOrig = STrnUtilities.obtainQuantityLimit(miClient, SDataConstants.TRN_DPS_DPS_ADJ, adjustment.getDbmsDpsEntryKey(), null);
                }
                catch (java.lang.Exception e) {
                    SLibUtilities.renderException(this, e);
                }
            }
        }

        if (moDpsEntry.hasDpsAdjustmentsAsAdjustment() || SDataUtilities.callProcedureVal(miClient, SProcConstants.TRN_DPS_ETY_COUNT_ADJ_ADJ, moDpsEntry.getPrimaryKey(), SLibConstants.EXEC_MODE_SILENT) > 0) {
            vDbmsDpsAdjustmentsAsAdjustment = moDpsEntry.getDbmsDpsAdjustmentsAsAdjustment();

            for (SDataDpsDpsAdjustment adjustment : vDbmsDpsAdjustmentsAsAdjustment) {
                try {
                    mdQuantityDesOrig = STrnUtilities.obtainQuantityLimit(miClient, SDataConstants.TRN_DPS_DPS_ADJ, adjustment.getDbmsDpsEntryKey(), adjustment.getDbmsDpsAdjustmentEntryKey()) - dQuantityAdj;
                }
                catch (java.lang.Exception e) {
                    SLibUtilities.renderException(this, e);
                }
            }
        }
    }

    public void setEnableDataAddenda(boolean b) {
        mbEnableDataAddenda = b;
    }

    public void activateSurplusPercentage() {
        jckIsSurplusPercentageApplying.setEnabled(true);
        itemStateIsSurplusPercentageApplying(false);
    }

    public void activateDiscountRetailChain() {
        jckIsDiscountRetailChain.setSelected(true);
    }

    /*
     * Overriden methods
     */

    @Override
    public void formClearRegistry() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void formReset() {
        mbResetingForm = true;

        mbAllowDiscount = true;
        
        mnFormResult = SLibConstants.UNDEFINED;
        mnFormStatus = SLibConstants.UNDEFINED;
        mbFirstTime = true;

        moDpsEntry = new SDataDpsEntry();
        moDpsEntry.setFkDpsAdjustmentTypeId(SDataConstantsSys.TRNS_STP_DPS_ADJ_NA_NA[0]);
        moDpsEntry.setFkDpsAdjustmentSubtypeId(SDataConstantsSys.TRNS_STP_DPS_ADJ_NA_NA[1]);
        moDpsEntry.setDbmsDpsAdjustmentType(SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.TRNS_TP_DPS_ADJ, new int[] { moDpsEntry.getFkDpsAdjustmentTypeId() }));
        moDpsEntry.setFkDpsEntryTypeId(SDataConstantsSys.TRNS_TP_DPS_ETY_ORDY);
        moDpsEntry.setDbmsDpsEntryType(SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.TRNS_TP_DPS_ETY, new int[] { moDpsEntry.getFkDpsEntryTypeId() }));

        moItem = null;
        moParamDps = null;
        moParamBizPartner = null;
        moParamBizPartnerBranch = null;
        moDataItemBizPartnerDescription = null;
        mnParamAdjustmentTypeId = SDataConstantsSys.UNDEFINED;

        manItemClassFilterKey = null;

        mnAuxCurrentUnitTypeId = SLibConstants.UNDEFINED;
        mnAuxCurrentUnitAlternativeTypeId = SLibConstants.UNDEFINED;
        mdAuxCurrentValue = 0;
        moAuxCurrentUnitKey = null;

        moPaneTaxes.createTable();
        moPaneTaxes.clearTableRows();
        moPaneCommissions.createTable();
        moPaneCommissions.clearTableRows();

        moPaneGridNotes.createTable();
        moPaneGridNotes.clearTableRows();
        moPaneGridNotes.setGridViewStatus(STableConstants.VIEW_STATUS_ALIVE);
        jtbNotesFilter.setSelected(true);
        
        moPaneGridPrices.createTable();
        moPaneGridPrices.clearTableRows();
        moPaneGridPrices.setGridViewStatus(STableConstants.VIEW_STATUS_ALIVE);
        jtbGridPriceFilter.setSelected(true);

        // Add cell editor listener to just created taxes table:
        
        moPaneTaxes.getTable().getColumnModel().getColumn(COL_TAX_CUR).setCellEditor(moPaneTaxes.getTable().getDefaultEditor(Number.class));
        moPaneTaxes.getTable().getColumnModel().getColumn(COL_TAX_CUR).getCellEditor().addCellEditorListener(this);

        renderBasicSettings();
        renderItem(false, true); // this function actually clears all form fields, moItem already set to null

        jckIsDeleted.setEnabled(false);
        
        jckIsDpsReqMonthDelivery.setSelected(false);
        jckIsDpsReqMonthDelivery.setEnabled(false);
        jckIsDirectPrice.setSelected(true);
        
        jTabbedPane.setSelectedIndex(0);
        jTabbedPane.setEnabledAt(5, false);
        jTabbedPane.setEnabledAt(3, false);
        updateFormEditStatus(true);

        jtfAddendaNumberPosition.setEnabled(false);
        jtfAddendaCenter.setEnabled(false);
        jtfAddendaEntryNumber.setEnabled(false);
        jcbAddendaFkBarcode.setEnabled(false);
        moFieldAddendaFkBarcode.setFieldValue("");
        jtfAddendaOrder.setEnabled(false);
        jtfAddendaCages.setEnabled(false);
        jtfAddendaCagePriceUnitary.setEnabled(false);
        jtfAddendaParts.setEnabled(false);
        jtfAddendaPartPriceUnitary.setEnabled(false);

        mdQuantitySrcOrig = 0;
        mdQuantityDesOrig = 0;

        mbEnableDataAddenda = false;

        mbResetingForm = false;
        
        moFieldDpsContractBase.setDouble(0d);
        moFieldDpsContractFuture.setDouble(0d);
        moFieldDpsContractFactor.setDouble(0.10d);
        jckIsPriceConfirm.setSelected(false);
        
        actionPriceClearFields();
    }

    @Override
    public void formRefreshCatalogues() {
        mbResetingForm = true;

        SFormUtilities.populateComboBox(miClient, jcbFkItemId, SDataConstants.ITMU_ITEM);
        SFormUtilities.populateComboBox(miClient, jcbFkTaxRegionId, SDataConstants.FINU_TAX_REG);
        SFormUtilities.populateComboBox(miClient, jcbFkItemReferenceId_n, SDataConstants.ITMU_ITEM);
        SFormUtilities.populateComboBox(miClient, jcbFkVehicleTypeId_n, SModConsts.LOGU_TP_VEH);

        mbResetingForm = false;
    }

    @Override
    public erp.lib.form.SFormValidation formValidate() {
        double originalQuantity = 0.0d; 
        String message = "";
        SFormValidation validation = new SFormValidation();

        for (int i = 0; i < mvFields.size(); i++) {
            if (!((SFormField) mvFields.get(i)).validateField()) {
                validation.setIsError(true);
                validation.setComponent(mvFields.get(i).getComponent());
                break;
            }
        }

        if (!validation.getIsError()) {
            if (moItem.getFkItemStatusId() == SModSysConsts.ITMS_ST_ITEM_INA) {
                validation.setMessage(SItmConsts.MSG_ERR_ST_ITEM_INA + "\n" + SLibConstants.MSG_ERR_GUI_FIELD_VALUE_DIF + "'" + jlFkItemId.getText() + "'."); // validate that item is not inactive
                validation.setComponent(jcbFkItemId);
            }
            else if (moItem.getFkItemStatusId() == SModSysConsts.ITMS_ST_ITEM_RES && STrnUtilities.getIogCatForDpsClass(moParamDps.getDpsClassKey()) == SModSysConsts.TRNS_CT_IOG_IN) {
                validation.setMessage(SItmConsts.MSG_ERR_ST_ITEM_RES + "\n" + SLibConstants.MSG_ERR_GUI_FIELD_VALUE_DIF + "'" + jlFkItemId.getText() + "'."); // falidate that item is not restricted on in moves
                validation.setComponent(jcbFkItemId);
            }
            else if (moFieldIsDiscountUnitaryPercentage.getBoolean() && moFieldDiscountUnitaryPercentage.getDouble() == 0) {
                validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jckIsDiscountUnitaryPercentage.getText() + "'.");
                validation.setComponent(jtfDiscountUnitaryPercentage);
            }
            else if (moFieldIsDiscountEntryPercentage.getBoolean() && moFieldDiscountEntryPercentage.getDouble() == 0) {
                validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jckIsDiscountEntryPercentage.getText() + "'.");
                validation.setComponent(jtfDiscountEntryPercentage);
            }
            else if (SLibUtilities.belongsTo(moParamDps.getDpsTypeKey(), new int[][] { SDataConstantsSys.TRNU_TP_DPS_PUR_ORD, SDataConstantsSys.TRNU_TP_DPS_SAL_ORD }) && moDpsEntry.getContractPriceYear() != SLibConstants.UNDEFINED && moDpsEntry.getContractPriceMonth() != SLibConstants.UNDEFINED) {
                if (moFieldOriginalQuantity.getDouble() > mdQuantityPrc) {
                    message = "De acuerdo con la entrega mensual actual";
                    
                    if (mbIsLastPrc) {
                        message += ", considerando el excedente permitido en la partida del documento origen";
                    }
                    validation.setMessage(message + ":\nel valor máximo permitido para el campo '" + jlOriginalQuantity.getText() + "' es " +
                            miClient.getSessionXXX().getFormatters().getDecimalsSurfaceFormat().format(mdQuantityPrc) + " " + jtfOriginalUnitSymbolRo.getText() + ".");
                    validation.setComponent(jtfOriginalQuantity);
                }                
            }
            else if (mdQuantitySrcOrig > 0 && moFieldOriginalQuantity.getDouble() < mdQuantitySrcOrig) {
                validation.setMessage("El valor mínimo permitido para el campo '" + jlOriginalQuantity.getText() + "' es " +
                        miClient.getSessionXXX().getFormatters().getDecimalsSurfaceFormat().format(mdQuantitySrcOrig) + " " + jtfOriginalUnitSymbolRo.getText() + ".");
                validation.setComponent(jtfOriginalQuantity);
            }
            else if (mdQuantityDesOrig > 0 && moFieldOriginalQuantity.getDouble() > mdQuantityDesOrig) {
                validation.setMessage("El valor máximo permitido para el campo '" + jlOriginalQuantity.getText() + "' es " +
                        miClient.getSessionXXX().getFormatters().getDecimalsSurfaceFormat().format(mdQuantityDesOrig) + " " + jtfOriginalUnitSymbolRo.getText() + ".");
                validation.setComponent(jtfOriginalQuantity);
            }
            else if (moFieldOriginalQuantity.getDouble() == 0d) {
                validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jlOriginalQuantity.getText() + "'.");
                validation.setComponent(jtfOriginalQuantity);
            }
            else if (!moItem.getDbmsIsBulk() && moFieldOriginalQuantity.getDouble() != Math.round(moFieldOriginalQuantity.getDouble())) {
                validation.setMessage("El valor para el campo '" + jlOriginalQuantity.getText() + "' debe ser entero.");
                validation.setComponent(jtfOriginalQuantity);
            }
            else if (((SLibUtilities.parseDouble(jtfTotalCy_rRo.getText()) == 0d && jtfOriginalPriceUnitaryCy.isEditable()) ||
                    ((SLibUtilities.parseDouble(jtfTotalCy_rRo.getText()) == 0d && !jtfOriginalPriceUnitaryCy.isEditable()) && moParamsItemPriceList.getItemPriceFound())) &&
                    miClient.showMsgBoxConfirm("¿Está seguro que se desea dejar sin valor al campo '" + jlOriginalPriceUnitaryCy.getText() + "'?") != JOptionPane.YES_OPTION) {
                validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jlOriginalPriceUnitaryCy.getText() + "'.");
                validation.setComponent(jtfOriginalPriceUnitaryCy);
            }
            else if (moFieldOriginalPriceUnitaryCy.getDouble() == 0d && !jtfOriginalPriceUnitaryCy.isEditable() && !moParamsItemPriceList.getItemPriceFound() &&
                (SLibUtilities.compareKeys(new int[] { moItem.getDbmsDataItemGeneric().getFkItemCategoryId(), moItem.getDbmsDataItemGeneric().getFkItemClassId() }, SDataConstantsSys.ITMS_CL_ITEM_PUR_CON) ||
                SLibUtilities.compareKeys(new int[] { moItem.getDbmsDataItemGeneric().getFkItemCategoryId(), moItem.getDbmsDataItemGeneric().getFkItemClassId() }, SDataConstantsSys.ITMS_CL_ITEM_SAL_PRO))) {
                validation.setMessage("El valor para el campo '" + jlOriginalPriceUnitaryCy.getText() + "' debe ser mayor a 0.");
                validation.setComponent(jtfOriginalPriceUnitaryCy);
            }
            else if (moPanelFkCostCenterId_n.isEmptyAccountId()) {
                validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + moPanelFkCostCenterId_n.getFieldAccountLabel().getText() + "'.");
                validation.setComponent(moPanelFkCostCenterId_n.getFieldAccount().getComponent());
            }
            else {
                try {
                    // Validate that reference does not exist yet:
                    
                    SDataUtilities.validateDpsEtyReference(miClient.getSession(), moParamDps.getDpsClassKey(), moFieldReference.getString(), (int[]) moParamDps.getPrimaryKey());
                }
                catch (Exception e) {
                    message = e.getMessage();
                }
                
                if (!message.isEmpty()) {
                    validation.setMessage(message);
                    validation.setComponent(jtfReference);
                }
                else {
                    // Validate cost center:
                    
                    message = SDataUtilities.validateCostCenter(miClient, moPanelFkCostCenterId_n.getCurrentInputCostCenter(), moParamDps.getDate());

                    if (!message.isEmpty()) {
                        validation.setMessage(message);
                        validation.setComponent(moPanelFkCostCenterId_n.getFieldAccount().getComponent());
                    }
                }
            }
        }

        if (!validation.getIsError()) {
            if (moFieldFkVehicleTypeId_n.getKeyAsIntArray()[0] > 0) {
                if (moFieldVehicleNumber.getString().isEmpty()) {
                    validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jlVehicleNumber.getText() + "'.");
                    validation.setComponent(jtfVehicleNumber);
                    jTabbedPane.setSelectedIndex(2);
                }
            }
            else if (!moFieldVehicleNumber.getString().isEmpty()) {
                validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jlFkVehicleTypeId_n.getText() + "'.");
                validation.setComponent(jcbFkVehicleTypeId_n);
                jTabbedPane.setSelectedIndex(2);
            }
        }

        if (!validation.getIsError()) {
            if (moFieldAddendaNumberPosition.getInteger() % 10 != 0) {
                validation.setMessage("El valor para el campo " + "'" + jlAddendaNumberPosition.getText() + "' debe ser multiplo de 10.");
                validation.setComponent(jtfAddendaNumberPosition);
            }
            else if (jcbAddendaFkBarcode.isEnabled() && moFieldAddendaFkBarcode.getString().length() == 0) {
                validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jlAddendaFkBarcode.getText() + "'.");
                jTabbedPane.setSelectedIndex(5);
                validation.setComponent(jcbAddendaFkBarcode);
            }
        }

        if (!validation.getIsError()) {
            if (mbIsSales && moParamBizPartner.getDbmsCategorySettingsCus().getFkCfdAddendaTypeId() == SDataConstantsSys.BPSS_TP_CFD_ADD_ELEKTRA) {
                if (jtfAddendaOrder.isEnabled() && moFieldAddendaOrder.getString().length() == 0) {
                    validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jlAddendaOrder.getText() + "'.");
                    jTabbedPane.setSelectedIndex(5);
                    validation.setComponent(moFieldAddendaOrder.getComponent());
                }
                else if (jcbAddendaFkBarcode.isEnabled() && moFieldAddendaFkBarcode.getString().length() == 0) {
                    validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jlAddendaFkBarcode.getText() + "'.");
                    jTabbedPane.setSelectedIndex(5);
                    validation.setComponent(jcbAddendaFkBarcode);
                }
                else if (jtfAddendaCages.isEnabled() && moFieldAddendaCages.getInteger() <= 0) {
                    validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jlAddendaCages.getText() + "'.");
                    jTabbedPane.setSelectedIndex(5);
                    validation.setComponent(jtfAddendaCages);
                }
                else if (jtfAddendaCagePriceUnitary.isEnabled() && moFieldAddendaCagePriceUnitary.getDouble() <= 0) {
                    validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jlAddendaCagePriceUnitary.getText() + "'.");
                    jTabbedPane.setSelectedIndex(5);
                    validation.setComponent(jtfAddendaCagePriceUnitary);
                }
                else if (jtfAddendaParts.isEnabled() && moFieldAddendaParts.getInteger() <= 0) {
                    validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jlAddendaParts.getText() + "'.");
                    jTabbedPane.setSelectedIndex(5);
                    validation.setComponent(jtfAddendaParts);
                }
                else if (jtfAddendaPartPriceUnitary.isEnabled() && moFieldAddendaPartPriceUnitary.getDouble() <= 0) {
                    validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_EMPTY + "'" + jlAddendaPartPriceUnitary.getText() + "'.");
                    jTabbedPane.setSelectedIndex(5);
                    validation.setComponent(jtfAddendaPartPriceUnitary);
                }
            }
            
            if (!validation.getIsError() && mbIsCon && jckIsDpsReqMonthDelivery.isSelected()) {
                if (!validation.getIsError()) {
                    if (moFieldDpsContractBase.getDouble() < 0d) {
                        validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_VALUE_DIF + "'" + jlDpsContractBase.getText() + "'.");
                        validation.setComponent(jtfDpsContractBase);
                        jTabbedPane.setSelectedIndex(3);
                        
                    }
                }
                if (!validation.getIsError()) {
                    if (moFieldDpsContractFuture.getDouble() < 0d) {
                        validation.setMessage(SLibConstants.MSG_ERR_GUI_FIELD_VALUE_DIF + "'" + jlDpsContractFuture.getText() + "'.");
                        validation.setComponent(jtfDpsContractFuture);
                        jTabbedPane.setSelectedIndex(3);
                    }
                }
                if (!validation.getIsError()) {
                    if (moPaneGridPrices.getGridRows().size() <= 0) {
                        validation.setMessage("La partida tiene seleccionada la opción '" + jckIsDpsReqMonthDelivery.getText() + "', pero no se encontró ningúna entrega mensual programada.");
                        validation.setComponent(jckIsDpsReqMonthDelivery);
                        jTabbedPane.setSelectedIndex(3);
                    }
                }
                if (!validation.getIsError()) {
                    for (STableRow row : moPaneGridPrices.getGridRows()) {
                        if (!((SDataDpsEntryPrice) row.getData()).getIsDeleted()) {
                            originalQuantity += ((SDataDpsEntryPrice) row.getData()).getOriginalQuantity();
                        }
                    }
                    if (originalQuantity != moFieldOriginalQuantity.getDouble()) {
                        validation.setMessage("La suma de cantidades de '"+ jTabbedPane.getTitleAt(3) + "' (" + miClient.getSessionXXX().getFormatters().getDecimalsSurfaceFormat().format(originalQuantity) + ") debe coincidir con el valor especificado en el campo '" + jlOriginalQuantity.getText() + "' (" + jtfOriginalQuantity.getText() + ") de la partida.");
                        validation.setComponent(moPaneGridPrices);
                        jTabbedPane.setSelectedIndex(3);
                    }
                }
            }
            if (!validation.getIsError() && mbIsCon && !jckIsDpsReqMonthDelivery.isSelected() && moPaneGridPrices.getGridRows().size() > 0) {
                message = "La partida no tiene seleccionada la opción '" + jckIsDpsReqMonthDelivery.getText() + "', pero se encontraron entregas mensuales programadas.";
                if(miClient.showMsgBoxConfirm("¿Está seguro que se desea dejar sin valor al campo '" + jckIsDpsReqMonthDelivery.getText() + "'?") == JOptionPane.NO_OPTION){
                    validation.setMessage(message);
                    validation.setComponent(jckIsDpsReqMonthDelivery);
                    jTabbedPane.setSelectedIndex(3);
                }
            }
            
            if (!validation.getIsError()) {
                try {
                    STrnUtils.canBeUsedItemDps(miClient.getSession(), moParamDps.getDpsTypeKey(), moItem.getPkItemId(), moDpsEntry.hasDpsLinksAsDestiny());
                }
                catch (Exception e) {
                    validation.setMessage(e.getMessage());
                    validation.setComponent(jcbFkItemId);
                    SLibUtilities.printOutException(this, e);
                }
            }
        }

        return validation;
    }

    @Override
    public void setFormStatus(int status) {
        mnFormStatus = status;
        updateFormEditStatus(mnFormStatus == SLibConstants.FORM_STATUS_EDIT);
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
        mbResetingForm = true;

        moDpsEntry = (SDataDpsEntry) registry;

        moFieldFkItemId.setFieldValue(new int[] { moDpsEntry.getFkItemId() });

        itemChangedFkItemId(false);

        moFieldKey.setFieldValue(moDpsEntry.getConceptKey());
        moFieldConcept.setFieldValue(moDpsEntry.getConcept());
        moFieldFkOriginalUnitId.setFieldValue(new int[] { moDpsEntry.getFkOriginalUnitId() });
        moFieldIsDiscountDocApplying.setFieldValue(moDpsEntry.getIsDiscountDocApplying());
        moFieldIsDiscountUnitaryPercentage.setFieldValue(moDpsEntry.getIsDiscountUnitaryPercentage());
        moFieldDiscountUnitaryPercentage.setFieldValue(moDpsEntry.getDiscountUnitaryPercentage());
        moFieldIsDiscountEntryPercentage.setFieldValue(moDpsEntry.getIsDiscountEntryPercentage());
        moFieldDiscountEntryPercentage.setFieldValue(moDpsEntry.getDiscountEntryPercentage());
        moFieldOriginalQuantity.setFieldValue(moDpsEntry.getOriginalQuantity());
        moFieldOriginalPriceUnitaryCy.setFieldValue(moDpsEntry.getOriginalPriceUnitaryCy());
        moFieldOriginalDiscountUnitaryCy.setFieldValue(moDpsEntry.getOriginalDiscountUnitaryCy());
        moFieldDiscountEntryCy.setFieldValue(moDpsEntry.getDiscountEntryCy());
        moFieldDiscountDocCy.setFieldValue(moDpsEntry.getDiscountDocCy());
        moFieldLength.setFieldValue(moDpsEntry.getLength());
        moFieldSurface.setFieldValue(moDpsEntry.getSurface());
        moFieldVolume.setFieldValue(moDpsEntry.getVolume());
        moFieldMass.setFieldValue(moDpsEntry.getMass());
        moFieldWeightGross.setFieldValue(moDpsEntry.getWeightGross());
        moFieldWeightDelivery.setFieldValue(moDpsEntry.getWeightDelivery());
        moFieldSurplusPercentage.setFieldValue(moDpsEntry.getSurplusPercentage());
        moFieldIsDpsPriceVariable.setFieldValue(moDpsEntry.getIsPriceVariable());
        jckIsPriceConfirm.setSelected(moDpsEntry.getIsPriceConfirm());
        moFieldDpsContractBase.setFieldValue(moDpsEntry.getContractBase());
        moFieldDpsContractFuture.setFieldValue(moDpsEntry.getContractFuture());
        moFieldDpsContractFactor.setFieldValue(moDpsEntry.getContractFactor());
        moFieldFkVehicleTypeId_n.setFieldValue(new int[] { moDpsEntry.getFkVehicleTypeId_n() });
        moFieldVehicleNumber.setFieldValue(moDpsEntry.getVehicleNumber());
        moFieldSecuritySeal.setFieldValue(moDpsEntry.getSecuritySeal());
        moFieldTicket.setFieldValue(moDpsEntry.getTicket());
        moFieldIsDiscountRetailChain.setFieldValue(moDpsEntry.getIsDiscountRetailChain());
        moFieldIsTaxesAutomaticApplying.setFieldValue(moDpsEntry.getIsTaxesAutomaticApplying());
        moFieldIsInventoriable.setFieldValue(moDpsEntry.getIsInventoriable());
        moFieldIsDeleted.setFieldValue(moDpsEntry.getIsDeleted());
        moFieldFkTaxRegionId.setFieldValue(new int[] { moDpsEntry.getFkTaxRegionId() });
        moFieldFkItemReferenceId_n.setFieldValue(new int[] { moDpsEntry.getFkItemRefId_n() });
        moFieldReference.setFieldValue(moDpsEntry.getReference());

        jckIsSurplusPercentageApplying.setSelected(moDpsEntry.getSurplusPercentage() > 0);

        for (SDataDpsEntryTax tax : moDpsEntry.getDbmsEntryTaxes()) {
            moPaneTaxes.addTableRow(new SDataDpsEntryTaxRow(tax.clone()));
        }
        moPaneTaxes.renderTableRows();
        moPaneTaxes.setTableRowSelection(0);

        for (SDataDpsEntryCommissions commissions : moDpsEntry.getDbmsEntryCommissions()) {
            moPaneCommissions.addTableRow(new SDataDpsEntryCommissionsRow(commissions));
        }
        moPaneCommissions.renderTableRows();
        moPaneCommissions.setTableRowSelection(0);

        for (SDataDpsEntryNotes notes : moDpsEntry.getDbmsEntryNotes()) {
            if (notes.getPkNotesId() != SLibConstants.UNDEFINED) {
                moPaneGridNotes.addTableRow(new SDataDpsEntryNotesRow(notes.clone()));
            }
            else if (notes.getIsAllDocs()) {
                moPaneGridNotes.addTableRow(new SDataDpsEntryNotesRow(notes.clone()));
            }
        }
        moPaneGridNotes.renderTableRows();
        moPaneGridNotes.setTableRowSelection(0);

        for (SDataDpsEntryPrice price : moDpsEntry.getDbmsEntryPrices()) {
                moPaneGridPrices.addTableRow(new SDataDpsEntryPriceRow(price.clone()));
        }
        moPaneGridPrices.renderTableRows();
        moPaneGridPrices.setTableRowSelection(0);

        moPanelFkCostCenterId_n.getFieldAccount().setFieldValue(moDpsEntry.getFkCostCenterId_n().length() == 0 ? moPanelFkCostCenterId_n.getEmptyAccountId() : moDpsEntry.getFkCostCenterId_n());
        moPanelFkCostCenterId_n.refreshPanel();

        renderDpsEntryValue();
        renderFieldsStatus();
        jckIsDeleted.setEnabled(true);

        if (mbIsSales && (mbIsDoc || mbIsAdj)) {
            jTabbedPane.setEnabledAt(5, true);
            setAddendaDatas();
        }
        
        if (mbIsCon) {
            jTabbedPane.setEnabledAt(3, true);
            enablePriceContractFields(moDpsEntry.getIsPriceVariable());
        }

        mbResetingForm = false;
    }

    @Override
    public erp.lib.data.SDataRegistry getRegistry() {
        int i = 0;

        if (moDpsEntry.getIsRegistryNew()) {
            moDpsEntry.setFkUserNewId(miClient.getSession().getUser().getPkUserId());
        }
        else {
            moDpsEntry.setIsRegistryEdited(true);
            moDpsEntry.setFkUserEditId(miClient.getSession().getUser().getPkUserId());
        }

        calculateTotal();

        moDpsEntry.setConceptKey(moFieldKey.getString());
        moDpsEntry.setConcept(moFieldConcept.getString());
        moDpsEntry.setReference(moFieldReference.getString());

        moDpsEntry.setLength(moFieldLength.getDouble());
        moDpsEntry.setSurface(moFieldSurface.getDouble());
        moDpsEntry.setVolume(moFieldVolume.getDouble());
        moDpsEntry.setMass(moFieldMass.getDouble());
        moDpsEntry.setWeightGross(moFieldWeightGross.getDouble());
        moDpsEntry.setWeightDelivery(moFieldWeightDelivery.getDouble());
        moDpsEntry.setSurplusPercentage(moFieldSurplusPercentage.getDouble());
        moDpsEntry.setIsPriceVariable(moFieldIsDpsPriceVariable.getBoolean());
        moDpsEntry.setIsPriceConfirm(jckIsPriceConfirm.isSelected());
        moDpsEntry.setContractBase(!jckIsDpsReqMonthDelivery.isSelected() ? 0d : moFieldDpsContractBase.getDouble());
        moDpsEntry.setContractFuture(!jckIsDpsReqMonthDelivery.isSelected() ? 0d : moFieldDpsContractFuture.getDouble());
        moDpsEntry.setContractFactor(!jckIsDpsReqMonthDelivery.isSelected() ? 0d : moFieldDpsContractFactor.getDouble());
        moDpsEntry.setFkVehicleTypeId_n(moFieldFkVehicleTypeId_n.getKeyAsIntArray()[0]);
        moDpsEntry.setVehicleNumber(moFieldVehicleNumber.getString());
        moDpsEntry.setSecuritySeal(moFieldSecuritySeal.getString());
        moDpsEntry.setTicket(moFieldTicket.getString());

        moDpsEntry.setFkItemRefId_n(moFieldFkItemReferenceId_n.getKeyAsIntArray()[0]);
        moDpsEntry.setFkCostCenterId_n(moPanelFkCostCenterId_n.isEmptyAccountId() ? "" : moPanelFkCostCenterId_n.getFieldAccount().getString());
        moDpsEntry.setIsInventoriable(moFieldIsInventoriable.getBoolean());
        moDpsEntry.setIsDeleted(moFieldIsDeleted.getBoolean());

        moDpsEntry.getDbmsEntryNotes().clear();
        for (STableRow row : moPaneGridNotes.getGridRows()) {
            moDpsEntry.getDbmsEntryNotes().add((SDataDpsEntryNotes) row.getData());
        }
        
        if (jckIsDpsReqMonthDelivery.isSelected()) {
            moDpsEntry.getDbmsEntryPrices().clear();
            for (STableRow row : moPaneGridPrices.getGridRows()) {
                moDpsEntry.getDbmsEntryPrices().add((SDataDpsEntryPrice) row.getData());
            }
        }     

        moDpsEntry.getDbmsEntryTaxes().clear();
        for (i = 0; i < moPaneTaxes.getTableGuiRowCount(); i++) {
            moDpsEntry.getDbmsEntryTaxes().add((SDataDpsEntryTax) ((SDataDpsEntryTaxRow) moPaneTaxes.getTableRow(i)).getData());
        }

        moDpsEntry.getDbmsEntryCommissions().clear();
        for (i = 0; i < moPaneCommissions.getTableGuiRowCount(); i++) {
            moDpsEntry.getDbmsEntryCommissions().add((SDataDpsEntryCommissions) ((SDataDpsEntryCommissionsRow) moPaneCommissions.getTableRow(i)).getData());
        }

        moDpsEntry.setDbmsOriginalUnitSymbol(jtfOriginalUnitSymbolRo.getText());
        moDpsEntry.setDbmsUnitSymbol(jtfUnitSymbolRo.getText());
        moDpsEntry.setDbmsTaxRegion(((SFormComponentItem) jcbFkTaxRegionId.getSelectedItem()).getItem());
        moDpsEntry.setDbmsItemRef_n(!jcbFkItemReferenceId_n.isEnabled() || jcbFkItemReferenceId_n.getSelectedIndex() <= 0 ? "" : SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.ITMU_ITEM, moFieldFkItemReferenceId_n.getKeyAsIntArray()));
        moDpsEntry.setDbmsCostCenter_n(!moPanelFkCostCenterId_n.getFieldAccount().getComponent().isEnabled() ? "" : (moPanelFkCostCenterId_n.getCurrentInputCostCenter() == null ? "" : moPanelFkCostCenterId_n.getCurrentInputCostCenter().getCostCenter()));
        moDpsEntry.setDbmsCostCenterCode(!moPanelFkCostCenterId_n.getFieldAccount().getComponent().isEnabled() ? "" : (moPanelFkCostCenterId_n.getCurrentInputCostCenter() == null ? "" : moPanelFkCostCenterId_n.getCurrentInputCostCenter().getCode()));

        // Addenda data row:
        
        moDpsEntry.setDbmsDpsAddBachocoNumberPosition(moFieldAddendaNumberPosition.getInteger());
        moDpsEntry.setDbmsDpsAddBachocoCenter(moFieldAddendaCenter.getString());
        moDpsEntry.setDbmsDpsAddLorealEntryNumber(moFieldAddendaEntryNumber.getInteger());
        moDpsEntry.setDbmsDpsAddSorianaBarCode(moFieldAddendaFkBarcode.getString());
        moDpsEntry.setDbmsDpsAddElektraOrder(moFieldAddendaOrder.getString());
        moDpsEntry.setDbmsDpsAddElektraBarcode(moFieldAddendaFkBarcode.getString());
        moDpsEntry.setDbmsDpsAddElektraCages(moFieldAddendaCages.getInteger());
        moDpsEntry.setDbmsDpsAddElektraCagePriceUnitary(moFieldAddendaCagePriceUnitary.getDouble());
        moDpsEntry.setDbmsDpsAddElektraParts(moFieldAddendaParts.getInteger());
        moDpsEntry.setDbmsDpsAddElektraPartPriceUnitary(moFieldAddendaPartPriceUnitary.getDouble());

        return moDpsEntry;
    }

    @Override
    public void setValue(int type, java.lang.Object value) {
        mbUpdatingForm = true;

        switch (type) {
            case SDataConstants.TRN_DPS:
                if (value == null) {
                    moParamDps = null;

                    jtfCurrencyKeyRo.setText("");
                    jtfTaxIdentityEmisorRo.setText("");
                    jtfTaxIdentityReceptorRo.setText("");

                    manItemClassFilterKey = null;
                }
                else {
                    moParamDps = (SDataDps) value;

                    jtfCurrencyKeyRo.setText(SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.CFGU_CUR, new int[] { moParamDps.getFkCurrencyId() }, SLibConstants.DESCRIPTION_CODE));
                    jtfTaxIdentityEmisorRo.setText(SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINU_TAX_IDY, new int[] { moParamDps.getFkTaxIdentityEmisorTypeId() }));
                    jtfTaxIdentityReceptorRo.setText(SDataReadDescriptions.getCatalogueDescription(miClient, SDataConstants.FINU_TAX_IDY, new int[] { moParamDps.getFkTaxIdentityReceptorTypeId() }));
                    jtfCurrencyKeyRo.setCaretPosition(0);
                    jtfTaxIdentityEmisorRo.setCaretPosition(0);
                    jtfTaxIdentityReceptorRo.setCaretPosition(0);

                    if (moParamDps.isDocumentPur()) {
                        manItemClassFilterKey = SDataConstantsSys.ITMS_CL_ITEM_PUR_CON;
                    }
                    else {
                        manItemClassFilterKey = SDataConstantsSys.ITMS_CL_ITEM_SAL_PRO;
                    }
                }

                jtfCurrencyKeyPriceUnitaryCyRo.setText(jtfCurrencyKeyRo.getText());
                jtfCurrencyKeyDiscountUnitaryCyRo.setText(jtfCurrencyKeyRo.getText());
                jtfCurrencyKeyPriceUnitaryCyRo.setCaretPosition(0);
                jtfCurrencyKeyDiscountUnitaryCyRo.setCaretPosition(0);
                
                jtfPriceCurrencyKeyPriceUnitaryCy.setText(jtfCurrencyKeyRo.getText());

                break;

            case SDataConstants.BPSU_BP:
                if (value == null) {
                    moParamBizPartner = null;
                }
                else {
                    moParamBizPartner = (SDataBizPartner) value;
                }
                break;

            case SDataConstants.BPSU_BPB:
                if (value == null) {
                    moParamBizPartnerBranch = null;
                    moFieldFkTaxRegionId.setFieldValue(null);
                }
                else {
                    moParamBizPartnerBranch = (SDataBizPartnerBranch) value;
                    moFieldFkTaxRegionId.setFieldValue(new int[] { moParamBizPartnerBranch.getFkTaxRegionId_n() != 0 ? moParamBizPartnerBranch.getFkTaxRegionId_n() : miClient.getSessionXXX().getParamsCompany().getFkDefaultTaxRegionId_n() });
                }
                break;

            case SDataConstants.TRNS_TP_DPS_ADJ:
                if (value == null) {
                    mnParamAdjustmentTypeId = SDataConstantsSys.UNDEFINED;
                }
                else {
                    mnParamAdjustmentTypeId = (Integer) value;
                }
                break;

            case SDataConstants.FINU_TAX_REG:
                if (value == null) {
                    moFieldFkTaxRegionId.setFieldValue(null);
                }
                else {
                    moFieldFkTaxRegionId.setFieldValue(value);
                }
                break;

            default:
        }
        setDpsType();

        mbUpdatingForm = false;
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
            else if (button == jbKey) {
                actionKey();
            }
            else if (button == jbConcept) {
                actionConcept();
            }
            else if (button == jbItemBizPartnerDescription) {
                actionItemBizPartnerDescription();
            }
            else if (button == jbFkItemId) {
                actionFkItemId();
            }
            else if (button == jbFkOriginalUnitId) {
                actionFkOriginalUnitId();
            }
            else if (button == jbPriceUnitaryCyWizard) {
                actionPriceUnitaryCyWizard();
            }
            else if (button == jbPriceHistory) {
                actionPriceHistory();
            }
            else if (button == jbNotesNew) {
                actionNotesNew();
            }
            else if (button == jbNotesEdit) {
                actionNotesEdit();
            }
            else if (button == jbNotesDelete) {
                actionNotesDelete();
            }
            else if (button == jbSystemNotes) {
                actionSystemNotes();
            }
            else if (button == jbGridPriceNew || button == jbPriceNew) {
                actionPriceNew();
            }
            else if (button == jbGridPriceEdit) {
                actionPriceEdit();
            }
            else if (button == jbGridPriceDelete) {
                 actionPriceDelete();
            }
            else if (button == jbPriceSave) {
                actionPriceSave();
            }
            else if (button == jbClearPriceFields) {
                actionPriceClearFields();
            }
            else if (button == jbFkTaxRegionId) {
                actionFkTaxRegionId();
            }
            else if (button == jbFkItemReferenceId_n) {
                actionFkItemReferenceId_n();
            }
        }
        if (e.getSource() instanceof javax.swing.JToggleButton) {
            JToggleButton toggleButton = (JToggleButton) e.getSource();

            if (toggleButton == jtbNotesFilter) {
                actionNotesFilter();
            }
            if (toggleButton == jtbGridPriceFilter) {
                actionPriceFilter();
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() instanceof javax.swing.JTextField) {
            JTextField textField = (JTextField) e.getSource();

            if (isTextFieldForDpsEntryTotal(textField)) {
                mdAuxCurrentValue = SLibUtilities.parseDouble(textField.getText());
            }
        }
        else if (e.getSource() instanceof javax.swing.JComboBox) {
            JComboBox comboBox = (JComboBox) e.getSource();

            if (comboBox == jcbFkOriginalUnitId) {
                moAuxCurrentUnitKey = moFieldFkOriginalUnitId.getKey();
            }
        }
    }

    @Override
    public void focusLost(java.awt.event.FocusEvent e) {
        if (e.getSource() instanceof javax.swing.JTextField) {
            JTextField textField = (JTextField) e.getSource();

            if (isTextFieldForDpsEntryTotal(textField)) {
                if (mdAuxCurrentValue != SLibUtilities.parseDouble(textField.getText())) {
                    calculateTotal(); // calculate total only if value has changed
                }
                mdAuxCurrentValue = 0;
            } else if (textField == jtfContractBase || textField == jtfContractFuture || textField == jtfContractFactor){
                calculateEntryPrice();
            } 
        }
        else if (e.getSource() instanceof javax.swing.JComboBox) {
            JComboBox comboBox = (JComboBox) e.getSource();

            if (comboBox == jcbFkOriginalUnitId) {
                if (moAuxCurrentUnitKey != moFieldFkOriginalUnitId.getKey()) {
                    calculateTotal(); // calculate total only if value has changed
                }
                moAuxCurrentUnitKey = null;
            }
        }
    }
    
    @Override
    public void itemStateChanged(java.awt.event.ItemEvent e) {
        if (!mbResetingForm && !mbUpdatingForm) {
            if (e.getSource() instanceof javax.swing.JCheckBox) {
                JCheckBox checkBox = (JCheckBox) e.getSource();

                if (checkBox == jckIsDiscountDocApplying) {
                    itemStateIsDiscountDocApplying(true);
                }
                else if (checkBox == jckIsDiscountUnitaryPercentage) {
                    itemStateIsDiscountUnitaryPercentage(true);
                }
                else if (checkBox == jckIsDiscountEntryPercentage) {
                    itemStateIsDiscountEntryPercentage(true);
                }
                else if (checkBox == jckIsSurplusPercentageApplying) {
                    itemStateIsSurplusPercentageApplying(true);
                }
                else if (checkBox == jckIsTaxesAutomaticApplying) {
                    itemStateIsTaxesAutomaticApplying(true);
                }
                else if (checkBox == jckIsDpsReqMonthDelivery) {
                    enablePriceContractFields(checkBox.isSelected());
                }
                else if (checkBox == jckIsDirectPrice) {
                    enableDeliveryPriceFields(checkBox.isSelected());
                }
                else if (checkBox == jckChangePrice) {
                    enableChangeEntryPrice(checkBox.isSelected());
                }
            }
            else if (e.getSource() instanceof javax.swing.JComboBox && e.getStateChange() == ItemEvent.SELECTED) {
                JComboBox comboBox = (JComboBox)  e.getSource();

                if (comboBox == jcbFkItemId) {
                    itemChangedFkItemId(true);
                }
                else if (comboBox == jcbFkOriginalUnitId) {
                    itemChangedFkOriginalUnitId();
                }
                else if (comboBox == jcbFkTaxRegionId) {
                    itemChangedFkTaxRegionId();
                }
            }
        }
    }

    @Override
    public void editingStopped(ChangeEvent e) {
        updateDpsEntryTaxRow();
    }

    @Override
    public void editingCanceled(ChangeEvent e) {

    }
}
