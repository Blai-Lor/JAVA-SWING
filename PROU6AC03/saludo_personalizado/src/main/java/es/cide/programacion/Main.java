package es.cide.programacion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Saludo Personalizado");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 300); //Tamaño de la ventana
        ventana.setLayout(new FlowLayout());

        JComboBox<String> comboBox = new JComboBox<>(); //ComboBox para seleccionar el sistema operativo
        comboBox.addItem("Sr."); //Añade Sr. al combobox
        comboBox.addItem("Sra."); //Añade Sra. al combobox
        comboBox.addItem("Dr."); //Añade Dr. al combobox
        JTextArea areaTexto = new JTextArea(); //Area de texto en la que ponemos el nombre
        areaTexto.setColumns(5); //Ancho del area de texto
        JLabel etiqueta = new JLabel("Introduce tu nombre:"); //Indicamos que ponga el nombre
        
        comboBox.addActionListener(new ActionListener() { //El slider cambia
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = (String) comboBox.getSelectedItem(); //Obtenemos el item seleccionado del combobox
                JOptionPane.showMessageDialog(ventana, "Hola, Bienvenido/a " + text + " " + areaTexto.getText()); //Aparece el mensaje de saludo personalizado
            }
        });

        ventana.add(etiqueta);
        ventana.add(areaTexto);
        ventana.add(comboBox);
        ventana.setVisible(true);
    }
}