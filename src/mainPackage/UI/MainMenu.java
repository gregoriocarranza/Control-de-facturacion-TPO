package mainPackage.UI;

import mainPackage.UI.Forms.*;
import mainPackage.UI.Operaciones.CompulsaDePrecios;
import mainPackage.UI.Operaciones.FacturasEmitidasPorFechaYProveedor;
import mainPackage.UI.Operaciones.FacturasNoPagasPorFechaYProveedor;
import mainPackage.UI.Operaciones.OrdenesDePagoEmitidasPorFecha;
import mainPackage.UI.Tables.FacturaTable;
import mainPackage.UI.Tables.OrdenesDePagoTable;
import mainPackage.UI.Tables.ProductTable;
import mainPackage.UI.Tables.ProveedoresTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    private JMenuBar menuBar;
    private JMenu settings,menu0,menu1, menu2, menu3,menu4;
    private JMenuItem menuProveedor1,menuProveedor2,menuProductos1,menuProductos2,menuFacturas1,menuFacturas2,menuOrdenes1,menuOrdenes2,menuOperaciones1,menuOperaciones2,menuOperaciones3,menuOperaciones4;
    private JMenuItem menuSettings1;
    private static MainMenu INSTANCE = null;
    private ImageIcon backgroundImage = new ImageIcon("./src/mainPackage/UI/Views/background.jpeg");
    public MainMenu(){
        menuBar=new JMenuBar();
        setJMenuBar(menuBar);
        settings=new JMenu("Settings");
        menuBar.add(settings);

        menuSettings1=new JMenuItem("Exit");
        settings.add(menuSettings1);
        menuSettings1.addActionListener(this);

        menu4=new JMenu("Operaciones");
        menuBar.add(menu4);

        menuOperaciones1=new JMenuItem("Calcular compulsa de precios");
        menuOperaciones2=new JMenuItem("Ordenes de pago emitidas (fecha)");
        menuOperaciones3=new JMenuItem("Facturas emitidas (fecha y proveedor)");
        menuOperaciones4=new JMenuItem("Facturas no pagas (fecha y proveedor)");

        menu4.add(menuOperaciones1);
        menu4.add(menuOperaciones2);
        menu4.add(menuOperaciones3);
        menu4.add(menuOperaciones4);

        menuOperaciones1.addActionListener(this);
        menuOperaciones2.addActionListener(this);
        menuOperaciones3.addActionListener(this);
        menuOperaciones4.addActionListener(this);

        menu0=new JMenu("Proveedor");
        menuBar.add(menu0);

        menuProveedor1=new JMenuItem("Ver Proveedores");
        menuProveedor2=new JMenuItem("Agregar Proveedor");

        menu0.add(menuProveedor1);
        menu0.add(menuProveedor2);

        menuProveedor1.addActionListener(this);
        menuProveedor2.addActionListener(this);

        menu1=new JMenu("Productos");
        menuBar.add(menu1);

        menuProductos1=new JMenuItem("Ver productos");
        menuProductos2=new JMenuItem("Agregar productos");

        menu1.add(menuProductos1);
        menu1.add(menuProductos2);

        menuProductos1.addActionListener(this);
        menuProductos2.addActionListener(this);

        menu2=new JMenu("Facturas");
        menuBar.add(menu2);

        menuFacturas1=new JMenuItem("Ver Facturas");
        menuFacturas2=new JMenuItem("Agregar Facturas");

        menu2.add(menuFacturas1);
        menu2.add(menuFacturas2);

        menuFacturas1.addActionListener(this);
        menuFacturas2.addActionListener(this);

        menu3=new JMenu("Ordenes de pago");
        menuBar.add(menu3);

        menuOrdenes1=new JMenuItem("Ver Ordenes de pago");
        menuOrdenes2=new JMenuItem("Agregar Ordenes de pago");

        menu3.add(menuOrdenes1);
        menu3.add(menuOrdenes2);

        menuOrdenes1.addActionListener(this);
        menuOrdenes2.addActionListener(this);


        BackgroundPanel backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(new BorderLayout());

        initPantalla();
    }
    class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
    public static synchronized MainMenu getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MainMenu();
        }
        return INSTANCE;
    }
    private void initPantalla() {
        setTitle("Sistema de proveedores");
        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String command = e.getActionCommand();
            switch (command) {
                case "Exit":
                    System.exit(0);
                    break;
                case "Calcular compulsa de precios":
                    CompulsaDePrecios compulsaDePrecios = new CompulsaDePrecios(command);
                    compulsaDePrecios.setVisible(true);
                    break;
                case "Ordenes de pago emitidas (fecha)":
                    OrdenesDePagoEmitidasPorFecha ordenesPagoFecha = new OrdenesDePagoEmitidasPorFecha(command);
                    ordenesPagoFecha.setVisible(true);
                    break;
                case "Facturas emitidas (fecha y proveedor)":
                    FacturasEmitidasPorFechaYProveedor facturasEmitidasPorFechaYProveedor = new FacturasEmitidasPorFechaYProveedor(command);
                    facturasEmitidasPorFechaYProveedor.setVisible(true);
                    break;
                case "Facturas no pagas (fecha y proveedor)":
                    FacturasNoPagasPorFechaYProveedor facturasNoPagasPorFechaYProveedor = new FacturasNoPagasPorFechaYProveedor(command);
                    facturasNoPagasPorFechaYProveedor.setVisible(true);
                    break;
                case "Ver Proveedores":
                    ProveedoresTable proveedoresTable = new ProveedoresTable(command);
                    proveedoresTable.setVisible(true);
                    break;
                case "Agregar Proveedor":
                    FormularioDeProveedores formularioDeProveedores = new FormularioDeProveedores(command);
                    formularioDeProveedores.setVisible(true);
                    break;
                case "Ver productos":
                    ProductTable productTable = new ProductTable(command);
                    productTable.setVisible(true);
                    break;
                case "Agregar productos":
                    FormularioDeProductos formularioDeProductos = new FormularioDeProductos(command);
                    formularioDeProductos.setVisible(true);
                    break;
                case "Ver Facturas":
                    FacturaTable facturaTable = new FacturaTable(command);
                    facturaTable.setVisible(true);
                    break;
                case "Agregar Facturas":
                    FormularioDeFacturas formularioFacturas = new FormularioDeFacturas(command);
                    formularioFacturas.setVisible(true);
                    break;
                case "Ver Ordenes de pago":
                    OrdenesDePagoTable ordenesPagoTable = new OrdenesDePagoTable(command);
                    ordenesPagoTable.setVisible(true);
                    break;
                case "Agregar Ordenes de pago":
                    FormularioDeOrdenDePago formularioDeOrdenDePago = new FormularioDeOrdenDePago(command);
                    formularioDeOrdenDePago.setVisible(true);
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Acción no reconocida: " + command,"Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    public static void main(String[] args) {
        new MainMenu();
    }
}
