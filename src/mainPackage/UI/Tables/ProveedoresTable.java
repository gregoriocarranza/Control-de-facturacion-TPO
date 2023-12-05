package mainPackage.UI.Tables;


import mainPackage.Controller_P.Controller;
import mainPackage.Documentos.Factura;
import mainPackage.Entidades.Proveedor;
import mainPackage.UI.MainMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ProveedoresTable extends JFrame implements ActionListener {

    private Object[][] data;
    private String[] columnNames = {"id","Nombre","Cuit", "CC","Categoria Fiscal","Cantidad de productos"};
    private DefaultTableModel tableModel;
    private JTable table;
    private Controller controller;
    private MainMenu mainMenu;
    public ProveedoresTable(String title) throws Exception {
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

        Object[][] data = new Object[proveedores.size()][columnNames.length];
        for (int i = 0; i < proveedores.size(); i++) {
            data[i][0] = proveedores.get(i).getId();
            data[i][1] = proveedores.get(i).getNombre();
            data[i][2] = proveedores.get(i).getCuit();
            data[i][3] = proveedores.get(i).getCuentaCorriente();
            data[i][4] = proveedores.get(i).getCategoriaFiscal();
            data[i][5] = proveedores.get(i).getProductos().size();


        }
        return data;
    }

    public void actionPerformed(ActionEvent ae) {

    }
    public static void main(String[] args) throws Exception {
        ProveedoresTable myApp = new ProveedoresTable("Productos Table");
        myApp.setVisible(true);
    }
}
