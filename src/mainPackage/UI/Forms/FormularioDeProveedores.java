package mainPackage.UI.Forms;
import mainPackage.Controller_P.Controller;
import mainPackage.UI.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class FormularioDeProveedores extends JFrame implements ActionListener {
    private JTextField nombreField, cuitField, categoriaFiscalField, cuentaCorrienteField, inicioActividadesField;
    private JButton guardarButton, cancelarButton;
    private MainMenu mainMenu;
    private Controller controller;

    public FormularioDeProveedores(String title) throws Exception {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 300);

        controller = Controller.getInstance();
        mainMenu = MainMenu.getInstance();
        setLocationRelativeTo(mainMenu);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField(10);
        formPanel.add(nombreField);

        formPanel.add(new JLabel("CUIT:"));
        cuitField = new JTextField(10);
        formPanel.add(cuitField);

        formPanel.add(new JLabel("Categoría Fiscal:"));
        categoriaFiscalField = new JTextField(10);
        formPanel.add(categoriaFiscalField);

        formPanel.add(new JLabel("Cuenta Corriente:"));
        cuentaCorrienteField = new JTextField(10);
        formPanel.add(cuentaCorrienteField);

        formPanel.add(new JLabel("Inicio de Actividades (AAAA-MM-DD):"));
        inicioActividadesField = new JTextField(10);
        formPanel.add(inicioActividadesField);

        // Agregar botones
        guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(this);
        formPanel.add(guardarButton);

        cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(this);
        formPanel.add(cancelarButton);

        // Agregar el panel al frame
        add(formPanel, BorderLayout.CENTER);
    }

    private void saveProveedor() {
        try {
            String nombre = nombreField.getText();
            int cuit = Integer.parseInt(cuitField.getText());
            String categoriaFiscal = categoriaFiscalField.getText();
            String cuentaCorriente = cuentaCorrienteField.getText();
            LocalDate inicioDeActividades = LocalDate.parse(inicioActividadesField.getText(),DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            boolean success = controller.createProveedor(nombre, cuit, categoriaFiscal, cuentaCorriente, inicioDeActividades);
            if (!success){
                JOptionPane.showMessageDialog(this, "El cuit ya existe","Error", JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this, "Proveedor guardada con éxito!");
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el proveedor. Verifique los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        nombreField.setText("");
        cuitField.setText("");
        categoriaFiscalField.setText("");
        cuentaCorrienteField.setText("");
        inicioActividadesField.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guardarButton) {
            saveProveedor();
        } else if (e.getSource() == cancelarButton) {
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                FormularioDeProveedores form = new FormularioDeProveedores("Registro de Proveedor");
                form.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}

