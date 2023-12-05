package mainPackage.Controller_P;
import mainPackage.DTOS.CompulsaDePreciosDTO;
import mainPackage.DTOS.FacturaDTO;
import mainPackage.DTOS.OrdenDePagoDTO;
import mainPackage.Documentos.Factura;
import mainPackage.Documentos.OrdenDePago;
import mainPackage.Entidades.Proveedor;
import mainPackage.Enum.TipoDeIva;
import mainPackage.Enum.TipoDeUnidad;
import mainPackage.FormasDePago.Cheque;
import mainPackage.FormasDePago.Efectivo;
import mainPackage.Productos_y_detalles.Producto;
import mainPackage.Productos_y_detalles.Rubro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class Controller {

    private static List<Producto> productos ;
    private static List<Rubro> rubros ;
    private static List<Proveedor> proveedores ;
    private static List<OrdenDePago> ordenesDePago ;
    private static List<Factura> facturas ;
    private static Controller INSTANCE = null;
    private Controller() throws Exception {
        this.rubros = initRubros();
        this.productos = initProducts();
        this.proveedores = initProveedores();
        this.ordenesDePago = initOrdenesDePago();
        this.facturas = initFacturas();
    }

    public static synchronized Controller getInstance() throws Exception {
        if (INSTANCE == null) {
            INSTANCE = new Controller();
        }
        return INSTANCE;
    }

    public Collection<CompulsaDePreciosDTO> calcularCompulsaDePrecios(String productName){
        Collection<Proveedor> proveedores = getAllProveedores();
        Collection<CompulsaDePreciosDTO> compulsaDePreciosDTOS = new ArrayList<>();
        for (Proveedor proveedor:proveedores){
            for (Producto producto:proveedor.getProductos()){
                if (producto.getNombre().equals(productName)){
                    compulsaDePreciosDTOS.add(new CompulsaDePreciosDTO(compulsaDePreciosDTOS.size(),producto,proveedor));
                }
            }
        }
        return compulsaDePreciosDTOS;
    }
    public Collection<OrdenDePagoDTO> ordenesDePagoEmitidas(LocalDate fecha){
        Collection<OrdenDePago> ordenesDePago = getAllOrdenesDePago();
        Collection<OrdenDePagoDTO> ordenDePagoDTO = new ArrayList<>();
        for (OrdenDePago ordenDePago:ordenesDePago){
            if(ordenDePago.getFechaDeEmision().equals(fecha)){
                ordenDePagoDTO.add(new OrdenDePagoDTO(ordenDePagoDTO.size(),ordenDePago));
            }
        }
        return ordenDePagoDTO;
    }
    public Collection<FacturaDTO> FacturasEmitidasPorDiaYProveedor(LocalDate fecha, int cuit){
        Collection<Proveedor> proveedores =getAllProveedores();
        Collection<FacturaDTO> facturasEmitidas = new ArrayList<>();

        for (Proveedor proveedor:proveedores){
            if (proveedor.getCuit()==cuit){
                for (Factura factura:proveedor.getFacturas()){
                    if(factura.getFechaDeEmision().equals(fecha)){
                        facturasEmitidas.add(new FacturaDTO(facturasEmitidas.size(),factura,proveedor));
                    }
                }
            }
        }
        return facturasEmitidas;
    }

    public Collection<FacturaDTO> facturasNoPagas(LocalDate fecha,int cuit){
        Collection<OrdenDePago> ordenesDePago = getAllOrdenesDePago();


        Collection<Factura> facturasPagadas = new ArrayList<>();
        Collection<FacturaDTO> facturasImpagas = new ArrayList<>();
        Proveedor proveedorEncontrado = null;

        for (Proveedor proveedor:proveedores){
            if (proveedor.getCuit()==cuit){
                proveedorEncontrado=proveedor;
            }
        }
        for (OrdenDePago ordenDePago : ordenesDePago) {
            for (Factura factura : ordenDePago.getFacturas()) {
                if (!facturasPagadas.contains(factura)) {
                    facturasPagadas.add(factura);
                }
            }
        }

        if (proveedorEncontrado!=null){
            for (Factura factura : proveedorEncontrado.getFacturas()) {
                if (!facturasPagadas.contains(factura)) {
                    facturasImpagas.add(new FacturaDTO(facturasImpagas.size(),factura,proveedorEncontrado));
                }
            }
        }
        return facturasImpagas;
    }
    public List<Proveedor> getAllProveedores()  {
        return proveedores;
    }
    public List<Producto> getAllProducts()   {
        return productos;
    }
    public List<OrdenDePago> getAllOrdenesDePago()   {
        return ordenesDePago;
    }
    public static List<Rubro> initRubros() throws Exception {
        rubros = new ArrayList<>();
        rubros.add(new Rubro(rubros.size(),"Salud", "Insumos para la salud"));
        rubros.add(new Rubro(rubros.size(),"Seguridad", "Insumos para la Seguridad"));
        rubros.add(new Rubro(rubros.size(),"Tecnologia", "Insumos para la Tecnologia"));
        return rubros;
    }
    public static List<Producto> initProducts() throws Exception {
        productos = new ArrayList<>();
        productos.add(new Producto(productos.size(),"Capacitor", TipoDeUnidad.UNIDAD,1800, TipoDeIva.IVA_2_5,rubros.get(2)));
        productos.add(new Producto(productos.size(),"Vacunas", TipoDeUnidad.UNIDAD,1800, TipoDeIva.IVA_2_5,rubros.get(0)));
        productos.add(new Producto(productos.size(),"Vacunas", TipoDeUnidad.UNIDAD,18200, TipoDeIva.IVA_2_5,rubros.get(0)));
        productos.add(new Producto(productos.size(),"Glock 9MM", TipoDeUnidad.UNIDAD,1800, TipoDeIva.IVA_2_5,rubros.get(1)));
        return productos;
    }
    public static List<Proveedor> initProveedores() throws Exception {
        proveedores = new ArrayList<>();
        proveedores.add(new Proveedor(proveedores.size(),"Hospital italiano",987654321, "?", List.of(new String[]{"a4c257ae-84e3-49d6-ab6b-376b4c2bc334"}),  List.of(productos.get(1))));
        proveedores.add(new Proveedor(proveedores.size(),"Policia Federal",123456789, "?", List.of(new String[]{"042b8867-0d65-492d-ac0d-962b88060ee9"}),  List.of(productos.get(3), productos.get(2))));
        proveedores.add(new Proveedor(proveedores.size(),"VaulTech",192837465, "?", List.of(new String[]{"74d0d951-6960-49b9-ac48-5e452766ad46", "fb471140-d155-4e64-b05f-7b888a83a374"}),  List.of(productos.get(0))));
        return proveedores;
    }
    public static List<OrdenDePago> initOrdenesDePago() throws Exception {
        ordenesDePago = new ArrayList<>();
        List<Cheque> listaCheques = new ArrayList<>();
        Cheque nuevoCheque1 = new Cheque(1500.0f,new Date(),new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000),"Juan Pérez");
        listaCheques.add(nuevoCheque1);
        ordenesDePago.add(new OrdenDePago(ordenesDePago.size(),LocalDate.now().minusDays(1),1000.0f,"042b8867-0d65-492d-ac0d-962b88060ee9",null,listaCheques, LocalDate.now().minusDays(1),false,new ArrayList<>()));
        Cheque nuevoCheque2 = new Cheque(123500.0f,new Date(),new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000),"Gregorio Carranza Torres");
        Efectivo nuevoEfectivo1 = new Efectivo(1500.0f,"Dolars");
        listaCheques.add(nuevoCheque2);
        ordenesDePago.add(new OrdenDePago(ordenesDePago.size(),LocalDate.now(),1000.0f,"a4c257ae-84e3-49d6-ab6b-376b4c2bc334",nuevoEfectivo1,listaCheques, LocalDate.now().plusDays(2),true,new ArrayList<>()));
        ordenesDePago.add(new OrdenDePago(ordenesDePago.size(),LocalDate.now(),1000.0f,"74d0d951-6960-49b9-ac48-5e452766ad46",nuevoEfectivo1,null, LocalDate.now().plusDays(4),false,new ArrayList<>()));

        return ordenesDePago;
    }

    public static List<Factura> initFacturas() throws Exception {
        facturas = new ArrayList<>();
        facturas.add(new Factura(facturas.size(), LocalDate.now().minusDays(1), 1234.0f, proveedores.get(1).getCuentaCorriente().iterator().next(), 123456789, "Empresa S.A.", "Gregorio", "Calle falsa", 123456789, "info@empresa.com", 200.0f, LocalDate.now()));
        facturas.add(new Factura(facturas.size(), LocalDate.now(), 1000.0f, proveedores.get(0).getCuentaCorriente().iterator().next(), 987654321, "Empresa S.A.", "Gregorio", "Calle falsa", 123456789, "info@empresa.com", 200.0f, LocalDate.now()));
        facturas.add(new Factura(facturas.size(), LocalDate.now().minusDays(1), 299.0f, proveedores.get(2).getCuentaCorriente().iterator().next(), 192837465, "Empresa S.A.", "Gregorio", "Calle falsa", 123456789, "info@empresa.com", 200.0f, LocalDate.now()));
        facturas.add(new Factura(facturas.size(), LocalDate.now().minusDays(1), 299.0f, proveedores.get(2).getCuentaCorriente().iterator().next(), 192837465, "Empresa S.A.", "Gregorio", "Calle falsa", 123456789, "info@empresa.com", 200.0f, LocalDate.now()));
        facturas.add(new Factura(facturas.size(), LocalDate.now().minusDays(0), 299.0f, proveedores.get(2).getCuentaCorriente().iterator().next(), 192837465, "Empresa S.A.", "Gregorio", "Calle falsa", 123456789, "info@empresa.com", 200.0f, LocalDate.now()));

        for (Factura factura: facturas){
            for (Proveedor proveedor:proveedores){
                if (factura.getCuit()==proveedor.getCuit()){
                    proveedor.addFactura(factura);
                }
            }
        }
        ordenesDePago.get(0).getFacturas().add(facturas.get(0));
        ordenesDePago.get(1).getFacturas().add(facturas.get(1));
        return facturas;
    }
}
