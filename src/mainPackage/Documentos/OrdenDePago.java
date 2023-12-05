package mainPackage.Documentos;

import mainPackage.FormasDePago.Cheque;
import mainPackage.FormasDePago.Efectivo;
import mainPackage.FormasDePago.FormaDePago;
import mainPackage.Entidades.Proveedor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public class OrdenDePago extends Documentos {
    private Efectivo pagoEnEfectivo;
    private Collection<Cheque> pagoEnCheque;
    private LocalDate fechaVencimiento;
    private boolean pagado;
    private Collection<Factura> facturas;

    public OrdenDePago(int id, LocalDate fechaDeEmision, float importeTotal, String cuentaCorriente, Efectivo pagoEnEfectivo, Collection<Cheque> pagoEnCheque, LocalDate fechaVencimiento, boolean pagado, Collection<Factura> facturas) {
        super(id, fechaDeEmision, importeTotal, cuentaCorriente);
        this.pagoEnEfectivo = pagoEnEfectivo;
        this.pagoEnCheque = pagoEnCheque;
        this.fechaVencimiento = fechaVencimiento;
        this.pagado = pagado;
        this.facturas = facturas;
    }

    public Efectivo getPagoEnEfectivo() {
        return pagoEnEfectivo;
    }

    public Collection<Cheque> getCheques() {
        return pagoEnCheque;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public boolean isPagado() {
        return pagado;
    }

    public Collection<Factura> getFacturas() {
        return facturas;
    }
    public void addFactura(Factura factura) {
        facturas.add(factura);
    }
    public void setFormaPago(Efectivo formaPago) {
        this.pagoEnEfectivo = formaPago;
    }

    public void setCheques(Collection<Cheque> cheques) {
        this.pagoEnCheque = cheques;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public void setFacturas(Collection<Factura> facturas) {
        this.facturas = facturas;
    }

    public String toString() {
        return "OrdenDePago{" +
                "id=" + id +
                ", fechaDeEmision=" + fechaDeEmision +
                ", importeTotal=" + importeTotal +
                ", cuentaCorriente='" + cuentaCorriente + '\'' +
                ", pagoEnEfectivo=" + (pagoEnEfectivo != null ? pagoEnEfectivo.toString() : "null") +
                ", pagoEnCheque=" + (pagoEnCheque != null ? pagoEnCheque.toString() : "null") +
                ", fechaVencimiento=" + fechaVencimiento +
                ", pagado=" + pagado +
                ", facturas=" + (facturas != null ? facturas.toString() : "null") +
                '}';
    }
}
