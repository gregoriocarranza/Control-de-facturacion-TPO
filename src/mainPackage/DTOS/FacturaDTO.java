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
    private int cuit;

    public FacturaDTO(int id, Factura factura, Proveedor proveedor) {
        this.id = id;
        this.fechaDeEmision = factura.getFechaDeEmision();
        this.cuit=proveedor.getCuit();
        this.cuentaCorriente = factura.getCuentaCorriente();
        this.razonSocial = factura.getRazonSocial();
        this.importeTotal = factura.getImporteTotal();

    }
    public float getImporteTotal() {
        return importeTotal;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getCuentaCorriente() {
        return cuentaCorriente;
    }

    public LocalDate getFechaDeEmision() {
        return fechaDeEmision;
    }

    public int getCuit() {
        return cuit;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", razonSocial=" + razonSocial +
                ", fechaDeEmision=" + (fechaDeEmision != null ? fechaDeEmision.toString() : "null") +
                ", importeTotal=" + importeTotal +
                ", cuentaCorriente='" + cuentaCorriente + '\'' +
                ", cuit=" + cuit +
                "}";
    }


}
