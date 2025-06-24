package formulario3;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testCompras extends JFrame {

    private JPanel Panel;
    private JComboBox<String> productosBox;
    private JTextField cantidadText;
    private JLabel precioUnitarioText;
    private JLabel subtotalText;
    private JLabel ivaText;
    private JLabel descuentoText;
    private JLabel totalText;
    private JButton pagarButton;
    private JButton limpiarButton;
    private JTable table1;

    private DefaultTableModel tableModel;

    public testCompras() {
        setTitle("Test Compras");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(Panel);
        setVisible(true);

        String[] columnNames = {"Producto", "Cantidad", "Precio Unitario", "Subtotal", "IVA", "Descuento", "Total"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table1.setModel(tableModel);

        productosBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUnitPrice();
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        pagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processPurchase();
            }
        });

        updateUnitPrice();
    }

    private double getProductPrice(String productName) {
        switch (productName) {
            case "Martillo":
                return 10.00;
            case "Clavos":
                return 3.50;
            case "Pintura Blanca":
                return 15.00;
            case "Taladro":
                return 50.00;
            default:
                return 0.00;
        }
    }

    private void updateUnitPrice() {
        String selectedProduct = (String) productosBox.getSelectedItem();
        if (selectedProduct != null) {
            double price = getProductPrice(selectedProduct);
            precioUnitarioText.setText(String.format("Precio Unitario: $%.2f", price));
        } else {
            precioUnitarioText.setText("Precio Unitario: $0.00");
        }
    }

    private void processPurchase() {
        String selectedProduct = (String) productosBox.getSelectedItem();
        String quantityStr = cantidadText.getText();

        if (selectedProduct == null || quantityStr.isEmpty()) {
            JOptionPane.showMessageDialog(Panel, "Por favor, selecciona un producto y/o ingresa una cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityStr);
            if (quantity <= 0) {
                JOptionPane.showMessageDialog(Panel, "La cantidad debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double unitPrice = getProductPrice(selectedProduct);
            double subtotal = unitPrice * quantity;
            double iva = subtotal * 0.15;
            double discount = subtotal * 0.20;
            double totalPayable = subtotal + iva - discount;

            subtotalText.setText(String.format("$%.2f", subtotal));
            ivaText.setText(String.format("$%.2f", iva));
            descuentoText.setText(String.format("$%.2f", discount));
            totalText.setText(String.format("$%.2f", totalPayable));

            Object[] rowData = {
                    selectedProduct,
                    quantity,
                    String.format("$%.2f", unitPrice),
                    String.format("$%.2f", subtotal),
                    String.format("$%.2f", iva),
                    String.format("$%.2f", discount),
                    String.format("$%.2f", totalPayable)
            };
            tableModel.addRow(rowData);

            cantidadText.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(Panel, "Por favor, ingresa un número válido para la cantidad.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        cantidadText.setText("");
        productosBox.setSelectedIndex(0);
        precioUnitarioText.setText("$0.00");
        subtotalText.setText("$0.00");
        ivaText.setText("$0.00");
        descuentoText.setText("$0.00");
        totalText.setText("$0.00");

        tableModel.setRowCount(0);
    }

    public static void main(String[] args) {
        new testCompras();
    }
}