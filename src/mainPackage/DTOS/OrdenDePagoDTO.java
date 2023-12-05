package mainPackage.DTOS;

import mainPackage.Documentos.OrdenDePago;
import mainPackage.Entidades.Proveedor;
import mainPackage.Productos_y_detalles.Producto;

import java.time.LocalDate;
import java.util.Date;

public class OrdenDePagoDTO {
    private int id;
    private float importeTotal;
    private String cuentaCorriente;
    private LocalDate fechaDeEmision;

    private LocalDate fechaVencimiento;
    private boolean pagado;

    public OrdenDePagoDTO(int id, OrdenDePago ordenDePago) {
        this.id = id;
        this.fechaDeEmision = ordenDePago.getFechaDeEmision();
        this.importeTotal = ordenDePago.getImporteTotal();
        this.cuentaCorriente = ordenDePago.getCuentaCorriente();
        this.fechaVencimiento = ordenDePago.getFechaVencimiento();
        this.pagado = ordenDePago.isPagado();
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", fechaDeEmision=" + (fechaDeEmision != null ? fechaDeEmision.toString() : "null") +
                ", importeTotal=" + importeTotal +
                ", cuentaCorriente='" + cuentaCorriente + '\'' +
                ", fechaVencimiento=" + (fechaVencimiento != null ? fechaVencimiento.toString() : "null") +
                ", pagado=" + pagado +
                "}";
    }


}
