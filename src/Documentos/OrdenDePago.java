package Documentos;

import FormasDePago.Cheque;
import FormasDePago.FormaDePago;

import java.util.Collection;
import java.util.Date;

public class OrdenDePago extends Documentos {
    private FormaDePago formaPago;
    private Collection<Cheque> cheques;
    private Date fechaVencimiento;
    private boolean pagado;
    private Collection<Factura> facturas;
}
