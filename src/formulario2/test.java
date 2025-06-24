package formulario2;

import formulario1.encuesta;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test extends JFrame {
    private JPanel Panel;

    private JButton resultadoButton;
    private JButton limpiarButton;
    private JButton inactivarButton;

    private JRadioButton pa1;
    private JRadioButton pb1;
    private JRadioButton pc1;
    private ButtonGroup respuestas1;

    private JRadioButton pa2;
    private JRadioButton pb2;
    private JRadioButton pc2;
    private ButtonGroup respuestas2;

    private JRadioButton pv3;
    private JRadioButton pf3;
    private ButtonGroup respuestas3;

    private JLabel puntajeLabel;

    public test(){
        setTitle("Test");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(Panel);
        setVisible(true);

        respuestas1 = new ButtonGroup();
        respuestas1.add(pa1);
        respuestas1.add(pb1);
        respuestas1.add(pc1);

        respuestas2 = new ButtonGroup();
        respuestas2.add(pa2);
        respuestas2.add(pb2);
        respuestas2.add(pc2);

        respuestas3 = new ButtonGroup();
        respuestas3.add(pv3);
        respuestas3.add(pf3);

        resultadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int puntaje=0;
                if (pb1.isSelected()){
                    puntaje += 5;
                }
                if (pc2.isSelected()){
                    puntaje += 5;
                }
                if (pv3.isSelected()){
                    puntaje += 5;
                }
                puntajeLabel.setText("Puntaje total: "+puntaje+" /15");
            }
        });

        inactivarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pa1.setEnabled(false);
                pb1.setEnabled(false);
                pc1.setEnabled(false);
                pa2.setEnabled(false);
                pb2.setEnabled(false);
                pc2.setEnabled(false);
                pv3.setEnabled(false);
                pf3.setEnabled(false);
                resultadoButton.setEnabled(false);
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                respuestas1.clearSelection();
                respuestas2.clearSelection();
                respuestas3.clearSelection();

                puntajeLabel.setText("");

                pa1.setEnabled(true);
                pb1.setEnabled(true);
                pc1.setEnabled(true);
                pa2.setEnabled(true);
                pb2.setEnabled(true);
                pc2.setEnabled(true);
                pv3.setEnabled(true);
                pf3.setEnabled(true);
                resultadoButton.setEnabled(true);
            }
        });
    }

    public static void main(String[] args) {
        new test();
    }
}