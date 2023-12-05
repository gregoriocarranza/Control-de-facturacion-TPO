package mainPackage.Entidades;

import mainPackage.DTOS.ProductoDTO;

import mainPackage.Documentos.Factura;
import mainPackage.Enum.TipoDeIva;
import mainPackage.Enum.TipoDeUnidad;
import mainPackage.Productos_y_detalles.Producto;
import mainPackage.Productos_y_detalles.Rubro;
import mainPackage.util.ProductStorage;


import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Proveedor   {
    private int id;
    private String nombre;
    private int cuit;
    private List<Producto> productos=new ArrayList<>();
    private List<Factura> facturas= new ArrayList<>();
    private String categoriaFiscal;
    private LocalDate inicioDeActividades;
    private Collection<String> cuentaCorriente=new ArrayList<>();;
    private static Proveedor INSTANCE = null;
    private ProductStorage productoDAO;
    public Proveedor(int id, String name, int cuit, String categoriaFiscal, Collection<String> cuentaCorriente, List<Producto> productos,LocalDate inicioDeActividades) throws Exception {
        this.id = id;
        this.nombre = name;
        this.cuit = cuit;
        this.categoriaFiscal = categoriaFiscal;
        this.cuentaCorriente = cuentaCorriente;
        this.productos = productos;
        this.inicioDeActividades = inicioDeActividades;
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
    public List<Producto> getProductos()  {
        return productos;
    }
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    public void addProducto(Producto producto) {
        productos.add(producto);
    }
    public List<Factura> getFacturas() {
        return facturas;
    }
    public void setFacturas(List<Factura> facturas) {
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
    public LocalDate getInicioDeActividades() {
        return inicioDeActividades;
    }
    public void setInicioDeActividades(LocalDate inicioDeActividades) {
        this.inicioDeActividades = inicioDeActividades;
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
