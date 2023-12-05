package mainPackage.UI.Forms;


import mainPackage.Controller_P.Controller;
import mainPackage.UI.MainMenu;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;

public class FormularioDeFacturas extends JFrame implements ActionListener {
    private JTextField ccField, cuitField, razonSocialField, nombreField, direccionField, telefonoField, correoField, importeTotalField, ingresosBrutosField;
    private JButton guardarButton, cancelarButton;
    private MainMenu mainMenu;
    private Controller controller;

    public FormularioDeFacturas(String title) throws Exception {
        super(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(400, 300);

        controller = Controller.getInstance();
        mainMenu = MainMenu.getInstance();
        setLocationRelativeTo(mainMenu);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 5, 5)); // GridLayout con 2 columnas

        formPanel.add(new JLabel("Nombre:"));
        nombreField = new JTextField(10);
        formPanel.add(nombreField);

        formPanel.add(new JLabel("Direccion:"));
        direccionField = new JTextField(10);
        formPanel.add(direccionField);

        formPanel.add(new JLabel("Telefono:"));
        telefonoField = new JTextField(10);
        formPanel.add(telefonoField);

        formPanel.add(new JLabel("Correo:"));
        correoField = new JTextField(10);
        formPanel.add(correoField);

        formPanel.add(new JLabel("CUIT:"));
        cuitField = new JTextField(10);
        formPanel.add(cuitField);

        formPanel.add(new JLabel("Cuenta Corriente:"));
        ccField = new JTextField(10);
        formPanel.add(ccField);

        formPanel.add(new JLabel("Razon Social:"));
        razonSocialField = new JTextField(10);
        formPanel.add(razonSocialField);

        formPanel.add(new JLabel("Ingresos Brutos:"));
        ingresosBrutosField = new JTextField(10);
        formPanel.add(ingresosBrutosField);

        formPanel.add(new JLabel("Importe Total:"));
        importeTotalField = new JTextField(10);
        formPanel.add(importeTotalField);


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

    private void saveFactura() {
        try {
            String nombre = nombreField.getText();
            String direccion = direccionField.getText();
            int telefono = Integer.parseInt(telefonoField.getText());
            String correo = correoField.getText();
            String CC = ccField.getText();
            int cuit = Integer.parseInt(cuitField.getText());
            String razonSocial = razonSocialField.getText();
            float ingresosBrutos = Float.parseFloat(ingresosBrutosField.getText());
            float importeTotal = Float.parseFloat(importeTotalField.getText());

            boolean succes=controller.createFactura(LocalDate.now(),importeTotal, CC,cuit,razonSocial,nombre,direccion,telefono,correo,ingresosBrutos);
            if (!succes){
                JOptionPane.showMessageDialog(this, "No existe nadie con esta cuenta corrienteFactura","Error", JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(this, "Factura guardada con éxito!");
                dispose();
            }
        } catch (NumberFormatException e) {System.out.println(e);
            JOptionPane.showMessageDialog(this, "Error al guardar la factura. Verifique los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        nombreField.setText("");
        telefonoField.setText("");
        correoField.setText("");
        direccionField.setText("");
        cuitField.setText("");
        ccField.setText("");
        razonSocialField.setText("");
        ingresosBrutosField.setText("");
        importeTotalField.setText("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guardarButton) {
            System.out.println("Guardando factura...");
            saveFactura();
        } else if (e.getSource() == cancelarButton) {
            System.out.println("Cancelando...");
            dispose();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FormularioDeFacturas form = null;
            try {
                form = new FormularioDeFacturas("Ingreso de Factura");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            form.setVisible(true);
        });
    }
}
