package formulario1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;

public class encuesta extends JFrame{
    private JPanel Panel;
    private JTextField nombreText;
    private JTextField apellidoText;
    private JTextField nacimientoText;
    private JRadioButton hombreRadioButton;
    private JRadioButton mujerRadioButton;
    private JButton calcularEdadButton;
    private JButton limpiarButton;
    private JLabel resultadoLabel;

    public encuesta() {
        setTitle("Encuesta");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(Panel);
        setVisible(true);

        ButtonGroup generoGroup = new ButtonGroup();
        generoGroup.add(hombreRadioButton);
        generoGroup.add(mujerRadioButton);

        calcularEdadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarEdad();
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    private void verificarEdad() {
        String nombre = nombreText.getText();
        String apellido = apellidoText.getText();
        String anoNacimientoStr = nacimientoText.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || anoNacimientoStr.isEmpty()) {
            JOptionPane.showMessageDialog(Panel, "Por favor, complete todos los campos (Nombre, Apellido, Año de Nacimiento).", "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int anoNacimiento = Integer.parseInt(anoNacimientoStr);
            int currentYear = Year.now().getValue();
            int edad = currentYear - anoNacimiento;

            String mensajeEdad;
            if (edad >= 18) {
                mensajeEdad = "Mayor de edad";
            } else {
                mensajeEdad = "Menor de edad";
            }

            String resultado = "Nombre: " + nombre + " " + apellido + "\n" +
                    "Edad: " + edad + " años → " + mensajeEdad;

            resultadoLabel.setText("<html>" + resultado.replaceAll("\n", "<br>") + "</html>");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(Panel, "Por favor, ingrese un año de nacimiento válido (número).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        nombreText.setText("");
        apellidoText.setText("");
        nacimientoText.setText("");
        hombreRadioButton.setSelected(false);
        mujerRadioButton.setSelected(false);
        resultadoLabel.setText("");
    }
    public static void main(String[] args) {
        new encuesta();
    }
}

