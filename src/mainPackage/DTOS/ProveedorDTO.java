package mainPackage.DTOS;
import mainPackage.Entidades.Proveedor;
import mainPackage.Enum.CategoriaFiscal;
import mainPackage.Productos_y_detalles.Producto;

import java.util.ArrayList;
import java.util.Collection;

public class ProveedorDTO {
    private int id;
    private String nombre;
    private CategoriaFiscal categoriaFiscal;
    private Collection<String> cuentaCorriente;
    private Collection<ProductoDTO> productos;


    public ProveedorDTO(Proveedor proveedor, Collection<Producto> productos) {
        this.id = proveedor.getId();
        this.nombre = proveedor.getNombre();
        this.categoriaFiscal = proveedor.getCategoriaFiscal();
        this.cuentaCorriente = proveedor.getCuentaCorriente();
        this.productos = new ArrayList<>();
        for (Producto producto : productos) {
            ProductoDTO productoDTO = new ProductoDTO(producto);
            this.productos.add(productoDTO);
        }
    }

    public String toString() {
        return "{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoriaFiscal='" + categoriaFiscal + '\'' +
                ", cuentaCorriente=" + cuentaCorriente +
                ", productos=" + productos +
                '}';
    }

}
