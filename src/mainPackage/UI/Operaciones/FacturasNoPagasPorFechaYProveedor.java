package mainPackage.UI.Operaciones;

import mainPackage.Controller_P.Controller;
import mainPackage.DTOS.FacturaDTO;
import mainPackage.UI.MainMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FacturasNoPagasPorFechaYProveedor extends JFrame implements ActionListener {

    private String[] columnNames = {"Razon social","CC","Fecha de emision", "importe total"};
    private JTextField searchField,searchField2;
    private JButton searchButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private Controller controller;
    private MainMenu mainMenu;
    public FacturasNoPagasPorFechaYProveedor(String title) throws Exception {
        super(title);
        setBounds(10, 10, 800, 300);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Ingrese una fecha (AAAA-MM-DD):"));
        searchField = new JTextField(15);
        searchPanel.add(searchField);
        searchPanel.add(new JLabel("Ingrese CUIT del proveedor:"));
        searchField2 = new JTextField(15);

        searchButton = new JButton("Buscar");
        searchButton.addActionListener(this);

        searchPanel.add(searchField2);
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

    public Object[][] convertDtoToData(List<FacturaDTO> factura) {

        Object[][] data = new Object[factura.size()][columnNames.length];
        for (int i = 0; i < factura.size(); i++) {
            data[i][0] = factura.get(i).getRazonSocial();
            data[i][1] = factura.get(i).getCuentaCorriente();
            data[i][2] = factura.get(i).getFechaDeEmision();
            data[i][3] = factura.get(i).getImporteTotal();

        }
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            LocalDate fecha = LocalDate.parse(searchField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            int cuit = Integer.parseInt(searchField2.getText());
            try {
                Object[][] data = convertDtoToData(controller.FacturasNoPagas(fecha,cuit));
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
                FacturasNoPagasPorFechaYProveedor frame = new FacturasNoPagasPorFechaYProveedor("Compulsa de Precios");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
