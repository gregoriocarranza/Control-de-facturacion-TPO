package mainPackage.Controller_P;
import mainPackage.DTOS.CompulsaDePreciosDTO;
import mainPackage.DTOS.FacturaDTO;
import mainPackage.DTOS.OrdenDePagoDTO;
import mainPackage.Documentos.Factura;
import mainPackage.Documentos.OrdenDePago;
import mainPackage.Entidades.Proveedor;
import mainPackage.Enum.CategoriaFiscal;
import mainPackage.Enum.TipoDeIva;
import mainPackage.Enum.TipoDeUnidad;
import mainPackage.FormasDePago.Cheque;
import mainPackage.FormasDePago.Efectivo;
import mainPackage.Productos_y_detalles.Producto;
import mainPackage.Productos_y_detalles.Rubro;

import java.io.IOException;
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

    public List<CompulsaDePreciosDTO> calcularCompulsaDePrecios(String productName){
        Collection<Proveedor> proveedores = getAllProveedores();
        List<CompulsaDePreciosDTO> compulsaDePreciosDTOS = new ArrayList<>();
        for (Proveedor proveedor:proveedores){
            for (Producto producto:proveedor.getProductos()){
                if (producto.getNombre().equals(productName)){
                    compulsaDePreciosDTOS.add(new CompulsaDePreciosDTO(compulsaDePreciosDTOS.size(),producto,proveedor));
                }
            }
        }
        return compulsaDePreciosDTOS;
    }
    public List<OrdenDePago> ordenesDePagoEmitidas(LocalDate fecha){
        Collection<OrdenDePago> ordenesDePago = getAllOrdenesDePago();
        List<OrdenDePago> ordenDePagoDTO = new ArrayList<>();
        for (OrdenDePago ordenDePago:ordenesDePago){
            if(ordenDePago.getFechaDeEmision().equals(fecha)){
                ordenDePagoDTO.add(ordenDePago);
            }
        }
        return ordenDePagoDTO;
    }
    public List<FacturaDTO> FacturasEmitidasPorDiaYProveedor(LocalDate fecha, int cuit){
        Collection<Proveedor> proveedores =getAllProveedores();
        List<FacturaDTO> facturasEmitidas = new ArrayList<>();

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

    public List<FacturaDTO> FacturasNoPagas(LocalDate fecha,int cuit){
        Collection<OrdenDePago> ordenesDePago = getAllOrdenesDePago();
        Collection<Factura> facturasPagadas = new ArrayList<>();
        List<FacturaDTO> facturasImpagas = new ArrayList<>();
        Proveedor proveedorEncontrado = null;

        for (Proveedor proveedor:proveedores){
            if (proveedor.getCuit()==cuit){
                proveedorEncontrado=proveedor;
            }
        }
        for (OrdenDePago ordenDePago : ordenesDePago) {
            for (Factura factura : ordenDePago.getFacturas()) {
                if (!facturasPagadas.contains(factura) && ordenDePago.isPagado()) {
                    facturasPagadas.add(factura);
                }
            }
        }

        if (proveedorEncontrado!=null){
            for (Factura factura : proveedorEncontrado.getFacturas()) {
                if (!facturasPagadas.contains(factura)) {
                    if(factura.getFechaDeEmision().equals(fecha)){
                        facturasImpagas.add(new FacturaDTO(facturasImpagas.size(),factura,proveedorEncontrado));
                    }


                }
            }
        }
        return facturasImpagas;
    }

    public Collection<Factura> getAllFacturasNoPagas() {
        Collection<Proveedor> proveedores =getAllProveedores();
        Collection<OrdenDePago> ordenesDePago = getAllOrdenesDePago();
        Collection<Factura> facturasPagadas = new ArrayList<>();
        List<Factura> facturasImpagas = new ArrayList<>();

        for (OrdenDePago ordenDePago : ordenesDePago) {
            for (Factura factura : ordenDePago.getFacturas()) {
                if (!facturasPagadas.contains(factura)||ordenDePago.isPagado()) {
                    facturasPagadas.add(factura);
                }
            }
        }
        for (Proveedor proveedor:proveedores){
            for (Factura factura : proveedor.getFacturas()) {
                if (!facturasPagadas.contains(factura)) {
                    facturasImpagas.add(factura);
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

    public List<Rubro> getAllRubros()   {
        rubros = new ArrayList<>();
        for (Producto producto:productos){
            if(!rubros.contains(producto.getRubro())){
                rubros.add(producto.getRubro());
            }
        }
        return rubros;
    }
    public Boolean createFactura(LocalDate emision,float total,String CC,int cuit,String razonSocial,String name,String direc,int telf,String mail,float ingresosBrutos){
        Collection<Proveedor> proveedores =getAllProveedores();
        Proveedor proveedorEncontrado = null;
        for (Proveedor proveedor:proveedores){
            if (proveedor.getCuentaCorriente().contains(CC)) {
                proveedorEncontrado=proveedor;
            }
        }
        if (proveedorEncontrado!=null){
            Factura factura=new Factura(facturas.size(), emision, total, CC, cuit, razonSocial, name, direc, telf, mail, ingresosBrutos,proveedorEncontrado.getInicioDeActividades());
            facturas.add(factura);
            proveedorEncontrado.addFactura(factura);
            return true;
        }
        return false;
    }

    public Boolean createProveedor(String name, int cuit, CategoriaFiscal categoriaFiscal, String CC, LocalDate inicioDeActividades) throws Exception {
        Collection<Proveedor> proveedores =getAllProveedores();
        Proveedor proveedorEncontrado = null;
        for (Proveedor proveedor:proveedores){
            if (proveedor.getCuentaCorriente().contains(CC)) {
                proveedorEncontrado=proveedor;
            }
        }
        if (proveedorEncontrado==null){
            List<Producto> productos1 = new ArrayList<>();
            Proveedor proveedor= new Proveedor(proveedores.size(),name,cuit, categoriaFiscal, List.of(new String[]{CC}),new ArrayList<>(),inicioDeActividades);
            proveedores.add(proveedor);
            return true;
        }
        return false;
    }

    public boolean createProducto(String nombre, TipoDeUnidad tipoDeUnidad, float precio, TipoDeIva tipoDeIVA, Rubro rubro, Proveedor proveedor) throws IOException {
        Producto producto= new Producto(productos.size(),nombre,tipoDeUnidad, precio, tipoDeIVA,rubro);
        productos.add(producto);
        proveedor.addProducto(producto);
        return true;

    }

    public boolean createOrdenDePago(List<Factura> factura, float importeTotal, Proveedor proveedor, Efectivo pagoEnEfectivo, Collection<Cheque> pagoEnCheque, LocalDate fechaVencimiento, boolean pagado) {
        ordenesDePago.add(new OrdenDePago(ordenesDePago.size(),LocalDate.now(),importeTotal,proveedor.getCuentaCorriente().stream().iterator().next(),pagoEnEfectivo,pagoEnCheque, fechaVencimiento,pagado,factura));
        return true;
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

        List<Producto> productos1 = new ArrayList<>();
        productos1.add(productos.get(1));

        List<Producto> productos2 = new ArrayList<>();
        productos2.add(productos.get(2));
        productos2.add(productos.get(3));

        List<Producto> productos3 = new ArrayList<>();
        productos3.add(productos.get(0));

        proveedores.add(new Proveedor(proveedores.size(),"Hospital italiano",987654321, CategoriaFiscal.Monotributista, List.of(new String[]{"a4c257ae-84e3-49d6-ab6b-376b4c2bc334"}),productos1,LocalDate.now().minusMonths(2)));
        proveedores.add(new Proveedor(proveedores.size(),"Policia Federal",123456789, CategoriaFiscal.ResponsableInscripto, List.of(new String[]{"042b8867-0d65-492d-ac0d-962b88060ee9"}),productos2,LocalDate.now().minusMonths(1)));
        proveedores.add(new Proveedor(proveedores.size(),"VaulTech",192837465, CategoriaFiscal.ResponsableInscripto, List.of(new String[]{"74d0d951-6960-49b9-ac48-5e452766ad46", "fb471140-d155-4e64-b05f-7b888a83a374"}), productos3,LocalDate.now()));
        return proveedores;
    }
    public static List<OrdenDePago> initOrdenesDePago() throws Exception {
        ordenesDePago = new ArrayList<>();
        List<Cheque> listaCheques = new ArrayList<>();
        Cheque nuevoCheque1 = new Cheque(1500.0f,new Date(),new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000),"Juan Pérez");
        listaCheques.add(nuevoCheque1);
        ordenesDePago.add(new OrdenDePago(ordenesDePago.size(),LocalDate.now().minusDays(1),1000.0f,"042b8867-0d65-492d-ac0d-962b88060ee9",null,listaCheques, LocalDate.now().minusDays(1),true,new ArrayList<>()));
        Cheque nuevoCheque2 = new Cheque(123500.0f,new Date(),new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000),"Gregorio Carranza Torres");
        Efectivo nuevoEfectivo1 = new Efectivo(1500.0f,"Dolars");
        listaCheques.add(nuevoCheque2);
        ordenesDePago.add(new OrdenDePago(ordenesDePago.size(),LocalDate.now(),1000.0f,"a4c257ae-84e3-49d6-ab6b-376b4c2bc334",nuevoEfectivo1,listaCheques, LocalDate.now().plusDays(2),false,new ArrayList<>()));
        ordenesDePago.add(new OrdenDePago(ordenesDePago.size(),LocalDate.now(),1000.0f,"74d0d951-6960-49b9-ac48-5e452766ad46",nuevoEfectivo1,new ArrayList<>(), LocalDate.now().plusDays(4),false,new ArrayList<>()));

        return ordenesDePago;
    }

    public static List<Factura> initFacturas() throws Exception {
        facturas = new ArrayList<>();
        facturas.add(new Factura(facturas.size(), LocalDate.now().minusDays(1), 1234.0f, proveedores.get(1).getCuentaCorriente().iterator().next(), proveedores.get(1).getCuit(), "Empresa S.A.", "Gregorio", "Calle falsa", 123456789, "info@empresa.com", 200.0f, proveedores.get(1).getInicioDeActividades()));
        facturas.add(new Factura(facturas.size(), LocalDate.now(), 1000.0f, proveedores.get(0).getCuentaCorriente().iterator().next(), proveedores.get(0).getCuit(), "Empresa S.A.", "Gregorio", "Calle falsa", 123456789, "info@empresa.com", 200.0f, proveedores.get(0).getInicioDeActividades()));
        facturas.add(new Factura(facturas.size(), LocalDate.now(), 299.0f, proveedores.get(0).getCuentaCorriente().iterator().next(), proveedores.get(0).getCuit(), "Empresa S.A.", "Gregorio", "Calle falsa", 123456789, "info@empresa.com", 200.0f, proveedores.get(0).getInicioDeActividades()));
        facturas.add(new Factura(facturas.size(), LocalDate.now().minusDays(1), 299.0f, proveedores.get(2).getCuentaCorriente().iterator().next(), proveedores.get(2).getCuit(), "Empresa S.A.", "Gregorio", "Calle falsa", 123456789, "info@empresa.com", 200.0f, proveedores.get(2).getInicioDeActividades()));
        facturas.add(new Factura(facturas.size(), LocalDate.now().minusDays(0), 299.0f, proveedores.get(2).getCuentaCorriente().iterator().next(), proveedores.get(2).getCuit(), "Empresa S.A.", "Gregorio", "Calle falsa", 123456789, "info@empresa.com", 200.0f, proveedores.get(2).getInicioDeActividades()));

        for (Factura factura: facturas){
            for (Proveedor proveedor:proveedores){
                if (factura.getCuit()==proveedor.getCuit()){
                    proveedor.addFactura(factura);
                }
            }
        }
        ordenesDePago.get(0).getFacturas().add(facturas.get(0));
        ordenesDePago.get(1).getFacturas().add(facturas.get(1));
        ordenesDePago.get(1).getFacturas().add(facturas.get(2));

        return facturas;
    }



}
