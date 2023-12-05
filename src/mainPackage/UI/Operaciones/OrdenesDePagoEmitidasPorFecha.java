package mainPackage.UI.Operaciones;

import mainPackage.Controller_P.Controller;
import mainPackage.Documentos.OrdenDePago;
import mainPackage.UI.MainMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrdenesDePagoEmitidasPorFecha extends JFrame implements ActionListener {

    private String[] columnNames = {"Fecha de emision","Fecha de vencimiento", "importe total","CC","Pago en efecitvo","Pago en cheque","Pagado","Cantidad de facturas"};
    private JTextField searchField;
    private JButton searchButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private Controller controller;
    private MainMenu mainMenu;
    public OrdenesDePagoEmitidasPorFecha(String title) throws Exception {
        super(title);
        setBounds(10, 10, 800, 300);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Ingrese una fecha (AAAA-MM-DD):"));
        searchField = new JTextField(10);
        searchButton = new JButton("Buscar");
        searchButton.addActionListener(this);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);


        controller = Controller.getInstance();

        tableModel = new DefaultTableModel(null, columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        mainMenu = MainMenu.getInstance();
        setLocationRelativeTo(mainMenu);
    }

    public Object[][] convertDtoToData(List<OrdenDePago> ordenes) {

        Object[][] data = new Object[ordenes.size()][columnNames.length];
        for (int i = 0; i < ordenes.size(); i++) {
            data[i][0] = ordenes.get(i).getFechaDeEmision();
            data[i][1] = ordenes.get(i).getFechaVencimiento();
            data[i][2] = ordenes.get(i).getImporteTotal();
            data[i][3] = ordenes.get(i).getCuentaCorriente();
            data[i][4] = ordenes.get(i).getPagoEnEfectivo() != null;
            data[i][5] = ordenes.get(i).getCheques().size() > 0;
            data[i][6] = ordenes.get(i).isPagado();
            data[i][7] = ordenes.get(i).getFacturas().size();

        }
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            LocalDate fecha = LocalDate.parse(searchField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            try {
                Object[][] data = convertDtoToData(controller.ordenesDePagoEmitidas(fecha));
                tableModel.setDataVector(data, columnNames);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al calcular la compulsa de precios.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                OrdenesDePagoEmitidasPorFecha frame = new OrdenesDePagoEmitidasPorFecha("Compulsa de Precios");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
