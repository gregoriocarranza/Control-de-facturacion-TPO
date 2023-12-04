package mainPackage.Controller_P;
import mainPackage.DTOS.ProductoDTO;
import mainPackage.DTOS.ProveedorDTO;
import mainPackage.Entidades.Proveedor;
import mainPackage.Productos_y_detalles.Producto;
import mainPackage.util.ProductStorage;
import mainPackage.util.ProveedorStorage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Controller {

    private final Collection<Producto> productoList;
    private ProductStorage Product_storage;
    private final Collection<Proveedor> proveedorList;
    private ProveedorStorage Proveedor_storage;
    private static Controller INSTANCE = null;

    private Controller() throws Exception {
        this.Product_storage = new ProductStorage("./src/mainPackage/DataStorage/products.json");
        this.productoList= Product_storage.getAllProducts();

        this.Proveedor_storage = new ProveedorStorage("./src/mainPackage/DataStorage/proveedores.json");
        this.proveedorList= Proveedor_storage.getAllProveedores();


    }

    public static synchronized Controller getInstance() throws Exception {
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }

//    public Collection<ProductoDTO> getAllProducts() throws IOException {
//        Collection<ProductoDTO> productoDTOList=new ArrayList<>();
//        for (Producto producto: productoList) {
//            productoDTOList.add(new ProductoDTO(producto));
//        }
//        return productoDTOList;
//    }

    public Collection<ProveedorDTO> getAllProveedores() throws IOException {
        Collection<ProveedorDTO> proveedorDTOList=new ArrayList<>();
        Collection<ProductoDTO> productoDTOList=new ArrayList<>();
        for (Proveedor proveedor: proveedorList) {
            Collection<Producto> productosDelProveedor=  proveedor.getProductos();
            proveedorDTOList.add(new ProveedorDTO(proveedor,productosDelProveedor));
        }
        return proveedorDTOList;
    }
}

