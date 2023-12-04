import mainPackage.Controller_P.Controller;
import mainPackage.DTOS.ProductoDTO;
import mainPackage.DTOS.ProveedorDTO;
import mainPackage.Productos_y_detalles.Producto;

import java.io.IOException;
import java.util.Collection;

public class Main {


    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        Controller controller = Controller.getInstance();
//        Collection<ProductoDTO> products = controller.getAllProducts();
//        System.out.println(products);


        System.out.println(controller.getAllProveedores());



    }
}