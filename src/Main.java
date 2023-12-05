import mainPackage.Controller_P.Controller;
import mainPackage.DTOS.ProductoDTO;
import mainPackage.DTOS.ProveedorDTO;
import mainPackage.Entidades.Proveedor;
import mainPackage.Enum.TipoDeIva;
import mainPackage.Enum.TipoDeUnidad;
import mainPackage.Productos_y_detalles.Producto;
import mainPackage.Productos_y_detalles.Rubro;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    private static ArrayList<Producto> productos = new ArrayList<>();
    private static ArrayList<Rubro> rubros = new ArrayList<>();
    private static ArrayList<Proveedor> proveedores = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando programa......");
        Controller controller = Controller.getInstance();
//      System.out.println(controller.ordenesDePagoEmitidas(LocalDate.now().minusDays(1)));
//      System.out.println("\n");
//      System.out.println(controller.calcularCompulsaDePrecios("Vacunas"));
//      System.out.println("\n");
//      System.out.println(controller.facturasNoPagas(LocalDate.now().minusDays(1),192837465));
//      System.out.println("\n");
//      System.out.println(controller.FacturasEmitidasPorDiaYProveedor(LocalDate.now().minusDays(1),192837465));


   }

}