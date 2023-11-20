package Productos_y_detalles;

import java.util.Collection;

import Entidades.Proveedor;
import  Enum.TipoDeIva;
import  Enum.TipoDeUnidad;

public class Producto {
    private int id;
    private String nombre;
    private Collection<Proveedor> proveedores;
    private TipoDeUnidad tipoDeUnidad;
    private float precio;
    private TipoDeIva tipoDeIVA;
    private Rubro rubro;

    public Producto() {
    }

    public void establecerPrecio(Producto producto, float precio) {
        // Lógica para establecer el precio de un producto
    }

}
