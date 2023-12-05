package mainPackage.UI.Tables;


import mainPackage.Controller_P.Controller;
import mainPackage.Documentos.Factura;
import mainPackage.Entidades.Proveedor;
import mainPackage.Productos_y_detalles.Producto;
import mainPackage.UI.MainMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;


public class FacturaTable extends JFrame implements ActionListener {

    private Object[][] data;
    private String[] columnNames = {"id","Nombre","Fecha de emision", "importe total","CC"};
    private DefaultTableModel tableModel;
    private JTable table;
    private Controller controller;
    private MainMenu mainMenu;
    public FacturaTable(String title) throws Exception {
        super(title);
        setBounds(10,10,700,300);
        controller = Controller.getInstance();
        data = convertDtoToData(controller.getAllProveedores());
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600,280));
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        add(panel,BorderLayout.CENTER);
        mainMenu = MainMenu.getInstance();
        setLocationRelativeTo(mainMenu);
    }


    public Object[][] convertDtoToData(List<Proveedor> proveedores) {
        int totalFacturas = 0;
        for (Proveedor proveedor : proveedores) {
            totalFacturas += proveedor.getFacturas().size();
        }
        Object[][] data = new Object[totalFacturas][columnNames.length];
        int filaActual = 0;
        for (Proveedor proveedor : proveedores) {
            for (Factura factura : proveedor.getFacturas()) {
                data[filaActual][0] = factura.getId();
                data[filaActual][1] = factura.getNombre();
                data[filaActual][2] = factura.getFechaDeEmision();
                data[filaActual][3] = factura.getImporteTotal();
                data[filaActual][4] = factura.getCuentaCorriente();
                filaActual++;
            }
        }
        return data;
    }

    public void actionPerformed(ActionEvent ae) {

    }
    public static void main(String[] args) throws Exception {
        FacturaTable myApp = new FacturaTable("Productos Table");
        myApp.setVisible(true);
    }
}
