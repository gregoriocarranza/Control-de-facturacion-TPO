package mainPackage.Documentos;

import mainPackage.Entidades.Proveedor;
import mainPackage.Productos_y_detalles.DetalleDeOrden;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

public class OrdenDeCompra extends Documentos {
    private Collection<DetalleDeOrden> detalle;

    public OrdenDeCompra(int id, LocalDate fechaDeEmision, float importeTotal, String cuentaCorriente, Collection<DetalleDeOrden> detalle) {
        super(id, fechaDeEmision, importeTotal, cuentaCorriente);
        this.detalle = detalle;
    }

    public Collection<DetalleDeOrden> getDetalle() {
        return detalle;
    }

    public void setDetalle(Collection<DetalleDeOrden> detalle) {
        this.detalle = detalle;
    }
}