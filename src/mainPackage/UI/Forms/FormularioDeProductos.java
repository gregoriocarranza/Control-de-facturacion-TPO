package mainPackage.UI.Forms;

import mainPackage.Controller_P.Controller;
import mainPackage.Entidades.Proveedor;
import mainPackage.Productos_y_detalles.Producto;
import mainPackage.Productos_y_detalles.Rubro;
import mainPackage.Enum.TipoDeIva;
import mainPackage.Enum.TipoDeUnidad;
import mainPackage.UI.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FormularioDeProductos extends JFrame implements ActionListener {
    private JTextField nombreField, precioField;
    private JComboBox<TipoDeUnidad> tipoDeUnidadComboBox;
    private JComboBox<TipoDeIva> tipoDeIVAComboBox;
    private JComboBox<Rubro> rubroComboBox;
    private JComboBox<Proveedor> ProveedorComboBox;
    private JButton guardarButton, cancelarButton;
    private MainMenu mainMenu;
    private Controller controller;

    public FormularioDeProductos(String title) throws Exception {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 300);

        controller = Controller.getInstance();
        mainMenu = MainMenu.getInstance();
        setLocationRelativeTo(mainMenu);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField(5);
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Proveedor:"));
        ProveedorComboBox = new JComboBox<>(controller.getAllProveedores().toArray(new Proveedor[0]));
        formPanel.add(ProveedorComboBox);

        formPanel.add(new JLabel("Precio:"));
        precioField = new JTextField(5);
        formPanel.add(precioField);

        formPanel.add(new JLabel("Tipo de Unidad:"));
        tipoDeUnidadComboBox = new JComboBox<>(TipoDeUnidad.values());
        formPanel.add(tipoDeUnidadComboBox);

        formPanel.add(new JLabel("Tipo de IVA:"));
        tipoDeIVAComboBox = new JComboBox<>(TipoDeIva.values());
        formPanel.add(tipoDeIVAComboBox);

        formPanel.add(new JLabel("Rubro:"));
        rubroComboBox = new JComboBox<>(controller.getAllRubros().toArray(new Rubro[0]));
        formPanel.add(rubroComboBox);

        guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(this);
        formPanel.add(guardarButton);

        cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(this);
        formPanel.add(cancelarButton);

        add(formPanel, BorderLayout.CENTER);
    }

    private void saveProducto() throws IOException {
        String nombre = nombreField.getText();
        Proveedor proveedor = (Proveedor) ProveedorComboBox.getSelectedItem();
        float precio = Float.parseFloat(precioField.getText());
        TipoDeUnidad tipoDeUnidad = (TipoDeUnidad) tipoDeUnidadComboBox.getSelectedItem();
        TipoDeIva tipoDeIVA = (TipoDeIva) tipoDeIVAComboBox.getSelectedItem();
        Rubro rubro = (Rubro) rubroComboBox.getSelectedItem();


            boolean success = controller.createProducto(nombre, tipoDeUnidad, precio, tipoDeIVA, rubro,proveedor);
            if (!success){
                JOptionPane.showMessageDialog(this, "Error al crear el producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this, "Producto guardado con éxito!");
                dispose();
            }
    }

    private void clearForm() {
        nombreField.setText("");
        precioField.setText("");
        tipoDeUnidadComboBox.setSelectedIndex(0);
        tipoDeIVAComboBox.setSelectedIndex(0);
        rubroComboBox.setSelectedIndex(0);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guardarButton) {
            try {
                saveProducto();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == cancelarButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                FormularioDeProductos form = new FormularioDeProductos("Registro de Producto");
                form.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
