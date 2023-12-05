package mainPackage.Entidades;

import mainPackage.DTOS.ProductoDTO;

import mainPackage.Documentos.Factura;
import mainPackage.Enum.TipoDeIva;
import mainPackage.Enum.TipoDeUnidad;
import mainPackage.Productos_y_detalles.Producto;
import mainPackage.Productos_y_detalles.Rubro;
import mainPackage.util.ProductStorage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Proveedor   {
    private int id;
    private String nombre;
    private int cuit;
    private Collection<Producto> productos;
    private Collection<Factura> facturas= new ArrayList<>();
    private String categoriaFiscal;
    private Collection<String> cuentaCorriente;
    private static Proveedor INSTANCE = null;
    private ProductStorage productoDAO;
    public Proveedor(int id, String name, int cuit, String categoriaFiscal, Collection<String> cuentaCorriente, Collection<Producto> productos) throws Exception {
        this.id = id;
        this.nombre = name;
        this.cuit = cuit;
        this.categoriaFiscal = categoriaFiscal;
        this.cuentaCorriente = cuentaCorriente;
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getCuit() {
        return cuit;
    }
    public void setCuit(int cuit) {
        this.cuit = cuit;
    }

    public Collection<Producto> getProductos()  {
        return productos;
    }
    public void setProductos(Collection<Producto> productos) {
        this.productos = productos;
    }

    public Collection<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(Collection<Factura> facturas) {
        this.facturas = facturas;
    }
    public void addFactura(Factura factura) {
        facturas.add(factura);
    }
    public String getCategoriaFiscal() {
        return categoriaFiscal;
    }

    public void setCategoriaFiscal(String categoriaFiscal) {
        this.categoriaFiscal = categoriaFiscal;
    }

    public Collection<String> getCuentaCorriente() {
        return cuentaCorriente;
    }

    public void setCuentaCorriente(Collection<String> cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }
    public String toString() {
        return "{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoriaFiscal='" + categoriaFiscal + '\'' +
                ", cuentaCorriente=" + cuentaCorriente +
                ", productos=" + productos +
                ", facturas=" + facturas +
                '}';
    }
}
