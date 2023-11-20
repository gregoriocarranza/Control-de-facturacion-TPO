package Entidades;

import Documentos.Factura;
import Productos_y_detalles.Producto;

import java.util.Collection;

public class Proveedor {
    private int id;
    private String nombre;
    private Collection<Producto> productos;
    private Collection<Factura> facturas;
    private String categoriaFiscal;
    private Collection<String> cuentaCorriente;

    public Proveedor() {
    }
    // Otros atributos...

    // Constructor, getters y setters...

    public void establecerPrecio(Producto producto, float precio) {
        // Lógica para establecer el precio de un producto
    }

    // Otros métodos...
}
