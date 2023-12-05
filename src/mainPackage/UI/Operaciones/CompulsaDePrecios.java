package mainPackage.UI.Operaciones;

import mainPackage.Controller_P.Controller;
import mainPackage.DTOS.CompulsaDePreciosDTO;
import mainPackage.UI.MainMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CompulsaDePrecios extends JFrame implements ActionListener {

    private String[] columnNames = {"Nombre del proveedor", "Nombre del producto", "Precio"};

    private JTextField searchField;
    private JButton searchButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private Controller controller;
    private MainMenu mainMenu;
    public CompulsaDePrecios(String title) throws Exception {
        super(title);
        setBounds(10, 10, 500, 300);
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Producto:"));
        searchField = new JTextField(20);
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

    public Object[][] convertDtoToData(List<CompulsaDePreciosDTO> list) {
        Object[][] data = new Object[list.size()][columnNames.length];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getNombreDeProveedor();
            data[i][1] = list.get(i).getNombreDeProducto();
            data[i][2] = list.get(i).getPrecioDeProducto();
        }
        return data;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String productName = searchField.getText();
            try {
                Object[][] data = convertDtoToData(controller.calcularCompulsaDePrecios(productName));
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
                CompulsaDePrecios frame = new CompulsaDePrecios("Compulsa de Precios");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
