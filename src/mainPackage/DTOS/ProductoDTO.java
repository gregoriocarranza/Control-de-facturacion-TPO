package mainPackage.DTOS;

import java.io.IOException;

import mainPackage.Enum.TipoDeIva;
import mainPackage.Enum.TipoDeUnidad;
import mainPackage.Productos_y_detalles.Producto;

public class ProductoDTO {
    private int id;
    private String nombre;
    private TipoDeUnidad tipoDeUnidad;
    private float precio;
    private TipoDeIva tipoDeIVA;
    private RubroDTO rubro;


    public ProductoDTO(Producto producto)   {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.tipoDeUnidad = producto.getTipoDeUnidad();
        this.precio = producto.getPrecio();
        this.tipoDeIVA = producto.getTipoDeIVA();
        this.rubro = producto.getRubro() ;
    }

    public String toString() {
        return "{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipoDeUnidad=" + tipoDeUnidad +
                ", precio=" + precio +
                ", tipoDeIVA=" + tipoDeIVA +
                ", Rubro='" + rubro + '\'' +
                '}';
    }
}
