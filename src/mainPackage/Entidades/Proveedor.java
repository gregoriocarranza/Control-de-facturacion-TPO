package mainPackage.Entidades;

import mainPackage.DTOS.ProductoDTO;

import mainPackage.Documentos.Factura;
import mainPackage.Productos_y_detalles.Producto;
import mainPackage.util.ProductStorage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Proveedor   {
    private int id;
    private String nombre;
    private Collection<Producto> productos;
    private Collection<Factura> facturas;
    private String categoriaFiscal;
    private Collection<String> cuentaCorriente;
    private static Proveedor INSTANCE = null;
    private final Collection<Producto> productoList;
    private ProductStorage productoDAO;
    private Proveedor() throws Exception {

        this.productoDAO = new ProductStorage("./src/mainPackage/DataStorage/products.json");
        this.productoList= productoDAO.getAllProducts();
    }

    public static synchronized Proveedor getInstance() throws Exception {
        if (INSTANCE == null) {
            INSTANCE = new Proveedor();
        }
        return INSTANCE;
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

    public Collection<Producto> getProductos() throws IOException {
        Collection<Producto> productoDTOList = new ArrayList<>();
        for (Producto producto : this.productos) {
            Producto productoEncontrado = productoDAO.getById(producto.getId());
            if (productoEncontrado != null) {
//                productoDTOList.add(new ProductoDTO(productoEncontrado));
                productoDTOList.add(productoEncontrado);
            }
        }
        return productoDTOList;
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

}
