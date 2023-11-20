package DTOS;
import java.util.Date;
import java.util.Collection;
import Documentos.Documentos;
import Documentos.Factura;
import Documentos.OrdenDeCompra;
import Documentos.OrdenDePago;
import Entidades.Proveedor;

public class CuentaCorrienteDTO {
    private Collection<Factura> facturas;
    private Collection<OrdenDeCompra> ordenDeCompra;
    private Collection<OrdenDePago> ordenDePago;
    private Proveedor proveedor;

    public CuentaCorrienteDTO(Collection<Factura> facturas, Collection<OrdenDeCompra> ordenDeCompra,
                              Collection<OrdenDePago> ordenDePago, Proveedor proveedor) {
        this.facturas = facturas;
        this.ordenDeCompra = ordenDeCompra;
        this.ordenDePago = ordenDePago;
        this.proveedor = proveedor;
    }

    // Métodos para acceder y modificar los campos, como getters y setters, van aquí...

    public static CuentaCorrienteDTO createCuentaCorrienteDTO(Collection<Factura> facturas, Collection<OrdenDeCompra> ordenDeCompra,
                                                              Collection<OrdenDePago> ordenDePago, Proveedor proveedor) {
        return new CuentaCorrienteDTO(facturas, ordenDeCompra, ordenDePago, proveedor);
    }
}
