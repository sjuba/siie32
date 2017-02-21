/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.mod.fin.util;

import erp.client.SClientInterface;
import erp.lib.SLibUtilities;
import erp.mod.SModSysConsts;
import erp.mod.cfg.db.SDbMms;
import erp.mod.fin.db.SDbBankLayout;
import erp.mod.fin.db.SFinConsts;
import static erp.mtrn.data.STrnUtilities.getMms;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import sa.lib.SLibUtils;
import sa.lib.db.SDbConsts;
import sa.lib.gui.SGuiClient;
import sa.lib.gui.SGuiConsts;
import sa.lib.mail.SMail;
import sa.lib.mail.SMailSender;

/**
 *
 * @author Edwin Carmona
 */
public class STreasuryBankLayoutRequest {
    
    private SGuiClient miClient;
    
    private final SDbBankLayout moBankLayout;

    public STreasuryBankLayoutRequest(SGuiClient oClient,final SDbBankLayout layout) {
        miClient = oClient;
        moBankLayout = layout;
    }
    
    public boolean makeRequestToTreasury() {
        SDbBankLayout oBankLayout = null;
        SLayoutParameters parameters = null;
        SDialogComments dialogComments = null;
        File pdf = null;
        
        String comment = "";
        boolean done = false;
        
        dialogComments = new SDialogComments(miClient, "Comentarios");
        dialogComments.setVisible(true);
        
        if (dialogComments.getFormResult() == SGuiConsts.FORM_RESULT_OK) {
            moBankLayout.setAuthorizationRequests(moBankLayout.getAuthorizationRequests() + 1);
            comment = dialogComments.getComment();
            
            oBankLayout = SFinUtils.loadPaymentsXml(miClient, moBankLayout);
            if (moBankLayout != null) {
                parameters = SFinUtils.getLayoutParameters(miClient, oBankLayout);
                pdf = STreasuryBankLayoutFile.createDocument(miClient, parameters ,SFinUtils.populateRows(miClient, oBankLayout.getBankPaymentRows(), oBankLayout.getXmlRows()));

                if (pdf != null) {
                    done = sendMail(parameters, comment, pdf);

                    if (done) {
                        try {
                            if (oBankLayout.getLayoutStatus() == SFinConsts.LAY_BANK_NEW_ST) {
                                SFinUtils.changeLayoutStatus(miClient, oBankLayout, SFinConsts.LAY_BANK_APPROVED_ST);
                            }
                            SFinUtils.increaseLayoutRequest(miClient, oBankLayout);
                        }
                        catch (Exception e) {
                            SLibUtils.printException(this, e);
                        }
                    }
                }
            }
        }
        return done;
    }
    
    private boolean sendMail(SLayoutParameters parameters, String comment, File pdf) {
        SDbMms mms = null;
        SMailSender sender = null;
        ArrayList<String> toRecipients = null;
        ArrayList<String> recipientsCc = null;
        SMail mail = null;
        String body = "";
        String subject = "";
        boolean sent = false;
        
        subject = "SOLIC. AUT. " + ((SClientInterface) miClient).getSessionXXX().getCompany().getDbmsDataCompany().getBizPartnerCommercial() + "#" + parameters.getFolio() + "-" + parameters.getAuthRequests();
        
        body = parameters.getCompanyName() + "\n" +
               "Fecha y hora de solicitud: " + SLibUtils.DateFormatDatetime.format(parameters.getDateTimeRequest()) + "\n\n" +
               "FECHA DE APLICACIÓN: " + SLibUtils.DateFormatDate.format(parameters.getApplicationDate()) + "\n\n" +
               "Banco: " + parameters.getBank() + "\n" +
               "Cuenta bancaria: " + parameters.getBankAccount() + "\n" +
               "Tipo de pago: " + parameters.getTypePayment() + "\n" +
               "Total: " + SLibUtils.DecimalFormatValue2D.format( parameters.getOriginalTotal()) + " " + parameters.getCurrency() + "\n\n\n" +
               comment;
        
        mms = getMms((SClientInterface) miClient, SModSysConsts.CFGS_TP_MMS_FIN_PAY_AUTH_REQ);
        
        if (mms != null && mms.getQueryResultId() == SDbConsts.READ_OK) {
            sender = new SMailSender(mms.getHost(), mms.getPort(), mms.getProtocol(), mms.isStartTls(), mms.isAuth(), mms.getUser(), mms.getUserPassword(), mms.getUser());
            
            if (mms.getRecipientTo() != null && !mms.getRecipientTo().isEmpty()) {
                toRecipients = new ArrayList<String>(Arrays.asList(SLibUtils.textExplode(mms.getRecipientTo(), ";")));
                
                if (!toRecipients.isEmpty()) {
                    if (mms.getRecipientCarbonCopy() != null && !mms.getRecipientCarbonCopy().isEmpty()) {
                        recipientsCc = new ArrayList<String>(Arrays.asList(SLibUtilities.textExplode(mms.getRecipientCarbonCopy(), ";")));
                        mail = new SMail(sender, subject, body, toRecipients, recipientsCc);
                    }
                    else {
                        mail = new SMail(sender, subject, body, toRecipients);
                    }
                    
                    if (pdf != null) {
                        mail.getAttachments().add(pdf);
                        try {
                            mail.send();
                            sent = true;
                        }
                        catch (Exception e) {
                            SLibUtils.showException(this, e);
                        }
                    }
                    else {
                        miClient.showMsgBoxWarning("No existe PDF para envío.");
                    }
                }
                else {
                    miClient.showMsgBoxWarning("No existe ningún correo-e configurado para envío de solicitudes.");
                }
            }
            else {
                miClient.showMsgBoxWarning("No existe ningún correo-e configurado para envío de solicitudes.");
            }    
        }
        else {
                miClient.showMsgBoxWarning("No existe ninguna configuración para envío de solicitudes.");
        }
        
        return sent;
    }
    
}
