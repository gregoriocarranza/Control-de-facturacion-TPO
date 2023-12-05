package mainPackage.UI.Forms;

import mainPackage.Controller_P.Controller;
import mainPackage.Documentos.Factura;
import mainPackage.Entidades.Proveedor;
import mainPackage.FormasDePago.Cheque;
import mainPackage.FormasDePago.Efectivo;
import mainPackage.Productos_y_detalles.Producto;
import mainPackage.UI.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FormularioDeOrdenDePago extends JFrame implements ActionListener {
    private JComboBox<Factura> facturaComboBox;
    private JComboBox<Proveedor> ProveedorComboBox;
    private JTextField importeTotalField ;
    private JTextField fechaVencimientoField;
    private JCheckBox pagadoCheckBox;
    private JButton guardarButton, cancelarButton;
    private MainMenu mainMenu;
    private Controller controller;

    public FormularioDeOrdenDePago(String title) throws Exception {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(500, 400);

        controller = Controller.getInstance();
        mainMenu = MainMenu.getInstance();
        setLocationRelativeTo(mainMenu);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5));

        formPanel.add(new JLabel("Factura:"));
        facturaComboBox = new JComboBox<>(controller.getAllFacturasNoPagas().toArray(new Factura[0]));
        formPanel.add(facturaComboBox);

        formPanel.add(new JLabel("Importe Total:"));
        importeTotalField = new JTextField(5);
        formPanel.add(importeTotalField);

        formPanel.add(new JLabel("Seleccione proveedor:"));
        ProveedorComboBox = new JComboBox<>(controller.getAllProveedores().toArray(new Proveedor[0]));
        formPanel.add(ProveedorComboBox);

        formPanel.add(new JLabel("Fecha de Vencimiento (AAAA-MM-DD):"));
        fechaVencimientoField = new JTextField(5);
        formPanel.add(fechaVencimientoField);

        formPanel.add(new JLabel("Pagado:"));
        pagadoCheckBox = new JCheckBox();
        formPanel.add(pagadoCheckBox);

        guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(this);
        formPanel.add(guardarButton);

        cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(this);
        formPanel.add(cancelarButton);

        add(formPanel, BorderLayout.CENTER);
    }

    private void saveOrdenDePago() {
        try {
            Factura factura = (Factura) facturaComboBox.getSelectedItem();
            float importeTotal = Float.parseFloat(importeTotalField.getText());
            Proveedor proveedor = (Proveedor) ProveedorComboBox.getSelectedItem();
            LocalDate fechaVencimiento = LocalDate.parse(fechaVencimientoField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            boolean pagado = pagadoCheckBox.isSelected();


            List<Factura> facturas = new ArrayList<>();
            List<Cheque> cheques = new ArrayList<>();
            facturas.add(factura);
            boolean success = controller.createOrdenDePago(facturas, importeTotal,  proveedor, null, cheques, fechaVencimiento, pagado);
            if (!success) {
                JOptionPane.showMessageDialog(this, "Error al crear la orden de pago.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Orden de pago guardada con éxito!");
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la orden de pago. Verifique los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        facturaComboBox.setSelectedIndex(0);
        importeTotalField.setText("");
        ProveedorComboBox.setSelectedIndex(0);
        fechaVencimientoField.setText("");
        pagadoCheckBox.setSelected(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guardarButton) {
            saveOrdenDePago();
        } else if (e.getSource() == cancelarButton) {
            dispose();
        }
    }

    public static void main
            (String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                FormularioDeOrdenDePago form = new FormularioDeOrdenDePago("Registro de OrdenDePago");
                form.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
