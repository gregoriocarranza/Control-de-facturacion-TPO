package mainPackage.UI.Tables;


import mainPackage.Controller_P.Controller;
import mainPackage.Documentos.Factura;
import mainPackage.Documentos.OrdenDePago;
import mainPackage.Entidades.Proveedor;
import mainPackage.UI.MainMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class OrdenesDePagoTable extends JFrame implements ActionListener {

    private Object[][] data;
    private String[] columnNames = {"id","Fecha de emision","Fecha de vencimiento", "importe total","CC","Pago en efecitvo","Pago en cheque","Pagado","Id de facturas asociadas"};
    private DefaultTableModel tableModel;
    private JTable table;
    private Controller controller;
    private MainMenu mainMenu;
    public OrdenesDePagoTable(String title) throws Exception {
        super(title);
        setBounds(10,10,1100,300);
        controller = Controller.getInstance();
        data = convertDtoToData(controller.getAllOrdenesDePago());
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000,280));
        JPanel panel = new JPanel();
        panel.add(scrollPane);
        add(panel,BorderLayout.CENTER);
        mainMenu = MainMenu.getInstance();
        setLocationRelativeTo(mainMenu);
    }


    public Object[][] convertDtoToData(List<OrdenDePago> ordenes) {

        Object[][] data = new Object[ordenes.size()][columnNames.length];
        for (int i = 0; i < ordenes.size(); i++) {
                data[i][0] = ordenes.get(i).getId();
                data[i][1] = ordenes.get(i).getFechaDeEmision();
                data[i][2] = ordenes.get(i).getFechaVencimiento();
                data[i][3] = ordenes.get(i).getImporteTotal();
                data[i][4] = ordenes.get(i).getCuentaCorriente();
                data[i][5] = ordenes.get(i).getPagoEnEfectivo() != null;
                data[i][6] = !ordenes.get(i).getCheques().isEmpty();
                data[i][7] = ordenes.get(i).isPagado();
                ArrayList<Integer> idFacturas = new ArrayList<>();
                for (Factura factura : ordenes.get(i).getFacturas()) {
                    idFacturas.add(factura.getId());
                }
                data[i][8] = !idFacturas.isEmpty() ? idFacturas: "Sin facturas asociadas";


        }
        return data;
    }

    public void actionPerformed(ActionEvent ae) {

    }
    public static void main(String[] args) throws Exception {
        OrdenesDePagoTable myApp = new OrdenesDePagoTable("Productos Table");
        myApp.setVisible(true);
    }
}
