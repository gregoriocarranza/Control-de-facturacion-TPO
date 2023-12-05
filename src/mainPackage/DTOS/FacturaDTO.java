package mainPackage.DTOS;

import mainPackage.Documentos.Factura;
import mainPackage.Documentos.OrdenDePago;
import mainPackage.Entidades.Proveedor;

import java.time.LocalDate;

public class FacturaDTO {
    private int id;
    private float importeTotal;
    private String razonSocial;
    private String cuentaCorriente;
    private LocalDate fechaDeEmision;

    private LocalDate fechaVencimiento;
    private int cuit;

    public FacturaDTO(int id, Factura factura, Proveedor proveedor) {
        this.id = id;
        this.fechaDeEmision = factura.getFechaDeEmision();
        this.razonSocial = factura.getRazonSocial();
        this.importeTotal = factura.getImporteTotal();
        this.cuentaCorriente = factura.getCuentaCorriente();
        this.cuit=proveedor.getCuit();
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", razonSocial=" + razonSocial +
                ", fechaDeEmision=" + (fechaDeEmision != null ? fechaDeEmision.toString() : "null") +
                ", importeTotal=" + importeTotal +
                ", cuentaCorriente='" + cuentaCorriente + '\'' +
                ", fechaVencimiento=" + (fechaVencimiento != null ? fechaVencimiento.toString() : "null") +
                ", cuit=" + cuit +
                "}";
    }


}
