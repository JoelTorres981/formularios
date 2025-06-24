package formulario4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class registro extends JFrame {
    private JPanel Panel;
    private JTextField nombreText;
    private JTextField apellidoText;
    private JTextField edadText;
    private JTextField telefonoText;
    private JRadioButton masculinoRadioButton;
    private JRadioButton femeninoRadioButton;
    private JCheckBox futbolCheckBox;
    private JCheckBox basquetCheckBox;
    private JCheckBox tenisCheckBox;
    private JCheckBox natacionCheckBox;
    private JComboBox<String> comboBox1;
    private JButton registrarButton;
    private JTable table1;
    private JButton limpiarButton;

    private ButtonGroup generoButtonGroup;

    private DefaultTableModel tableModel;

    public registro() {
        setTitle("Registro");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(Panel);
        setVisible(true);

        generoButtonGroup = new ButtonGroup();
        generoButtonGroup.add(masculinoRadioButton);
        generoButtonGroup.add(femeninoRadioButton);

        String[] columnNames = {"Nombre Completo", "Edad", "Teléfono", "Género", "Deportes", "Barrio"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table1.setModel(tableModel);

        if (registrarButton != null) {
            registrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registerPerson();
                }
            });
        }

        if (limpiarButton != null) {
            limpiarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearForm();
                }
            });
        }
    }

    private void registerPerson() {
        String name = nombreText.getText();
        String lastName = apellidoText.getText();
        String ageStr = edadText.getText();
        String phone = telefonoText.getText();

        String gender = "";
        if (masculinoRadioButton.isSelected()) {
            gender = "Hombre";
        } else if (femeninoRadioButton.isSelected()) {
            gender = "Mujer";
        }

        ArrayList<String> selectedSports = new ArrayList<>();
        if (futbolCheckBox.isSelected()) {
            selectedSports.add("Fútbol");
        }
        if (basquetCheckBox.isSelected()) {
            selectedSports.add("Básquet");
        }
        if (tenisCheckBox.isSelected()) {
            selectedSports.add("Tenis");
        }
        if (natacionCheckBox.isSelected()) {
            selectedSports.add("Natación");
        }
        String sports = selectedSports.isEmpty() ? "Ninguno" : String.join(", ", selectedSports);

        String neighborhood = (String) comboBox1.getSelectedItem();

        if (name.isEmpty() || lastName.isEmpty() || ageStr.isEmpty() || phone.isEmpty() || gender.isEmpty() || neighborhood == null || neighborhood.isEmpty()) {
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            if (age <= 0) {
                return;
            }

            String fullName = name + " " + lastName;

            Object[] rowData = {
                    fullName,
                    age,
                    phone,
                    gender,
                    sports,
                    neighborhood
            };
            tableModel.addRow(rowData);

            clearForm();

        } catch (NumberFormatException ex) {
            return;
        }
    }

    private void clearForm() {
        nombreText.setText("");
        apellidoText.setText("");
        edadText.setText("");
        telefonoText.setText("");

        generoButtonGroup.clearSelection();

        futbolCheckBox.setSelected(false);
        basquetCheckBox.setSelected(false);
        tenisCheckBox.setSelected(false);
        natacionCheckBox.setSelected(false);

        comboBox1.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new registro();
    }
}