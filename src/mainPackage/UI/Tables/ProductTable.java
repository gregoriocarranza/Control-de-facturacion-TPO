package mainPackage.UI.Tables;


import mainPackage.Controller_P.Controller;
import mainPackage.Productos_y_detalles.Producto;
import mainPackage.UI.MainMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ProductTable extends JFrame implements ActionListener {

    private Object[][] data;
    private String[] columnNames = {"id","Nombre","Rubro","Precio","Tipo de unidad", "Tipo de iva"};
    private DefaultTableModel tableModel;
    private JTable table;
    private Controller controller;

    private MainMenu mainMenu;
    public ProductTable(String title) throws Exception {
        super(title);
        setBounds(10,10,700,300);
        controller = Controller.getInstance();
        data = convertDtoToData(controller.getAllProducts());
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


    public Object[][] convertDtoToData(List<Producto> list) {
        Object[][] data = new Object[list.size()][columnNames.length];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getId();
            data[i][1] = list.get(i).getNombre();
            data[i][2] = list.get(i).getRubro().getNombre();
            data[i][3] = list.get(i).getPrecio();
            data[i][4] = list.get(i).getTipoDeUnidad();
            data[i][5] = list.get(i).getTipoDeIVA();

        }
        return data;
    }

    public void actionPerformed(ActionEvent ae) {

    }
    public static void main(String[] args) throws Exception {
        ProductTable myApp = new ProductTable("Productos Table");
        myApp.setVisible(true);
    }
}
