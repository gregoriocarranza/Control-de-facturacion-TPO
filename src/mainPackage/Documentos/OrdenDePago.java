package mainPackage.Documentos;

import mainPackage.FormasDePago.Cheque;
import mainPackage.FormasDePago.FormaDePago;

import java.util.Collection;
import java.util.Date;

public class OrdenDePago extends Documentos {
    private FormaDePago formaPago;
    private Collection<Cheque> cheques;
    private Date fechaVencimiento;
    private boolean pagado;
    private Collection<Factura> facturas;
}
