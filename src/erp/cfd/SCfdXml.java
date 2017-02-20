/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package erp.cfd;

import cfd.DElement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Juan Barajas
 */
public interface SCfdXml {

    public int getCfdTipoCfdXml();
    public String getCfdSerie();
    public String getCfdFolio();
    public String getCfdReferencia();
    public Date getCfdFecha();
    public String getCfdFormaDePago();
    public int getCfdCondicionesDePago();
    public double getCfdSubTotal();
    public double getCfdDescuento();
    public String getCfdMotivoDescuento();
    public double getCfdTipoCambio();
    public String getCfdMoneda();
    public double getCfdTotal();
    public int getCfdTipoDeComprobante();
    public int getCfdMetodoDePago();
    public String getCfdNumCtaPago();
    public String getCfdNumConfirmacion();
    public int getEmisor();
    public int getSucursalEmisor();
    public int getReceptor();
    public int getSucursalReceptor();
    public ArrayList<DElement> getCfdElementRegimenFiscal();
    public String getCfdTipoRelacion();
    public String getCfdUsoCfdi();
    public cfd.DElement getCfdElementAddenda();
    public cfd.DElement getCfdElementComplemento();

    public ArrayList<SCfdDataCfdiRelacionado> getCfdCfdiRelacionados();
    public ArrayList<SCfdDataConcepto> getCfdConceptos();
    public ArrayList<SCfdDataImpuesto> getCfdImpuestos();
}
