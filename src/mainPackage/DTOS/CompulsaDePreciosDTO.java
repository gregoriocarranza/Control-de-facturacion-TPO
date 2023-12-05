package mainPackage.DTOS;

import mainPackage.Entidades.Proveedor;
import mainPackage.Productos_y_detalles.Producto;
import mainPackage.Productos_y_detalles.Rubro;

public class CompulsaDePreciosDTO {
    private int id;
    private String nombreDeProveedor;
    private String nombreDeProducto;
    private float precioDeProducto;

    public CompulsaDePreciosDTO(int id, Producto producto, Proveedor proveedor) {
        this.id = id;
        this.nombreDeProveedor = proveedor.getNombre();
        this.nombreDeProducto = producto.getNombre();
        this.precioDeProducto = producto.getPrecio();
    }

    public String toString() {
        return "{"+"id=" + id +
                ", nombreDeProveedor='" + nombreDeProveedor + '\'' +
                ", nombreDeProducto='" + nombreDeProducto + '\''+
                ", precioDeProducto='" + precioDeProducto + '\''
                +"}" ;
    }

}
