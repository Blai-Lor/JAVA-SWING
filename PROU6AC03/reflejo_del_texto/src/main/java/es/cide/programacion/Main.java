package es.cide.programacion;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Reflejo del texto");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(450, 250); //Tamaño 
        ventana.setLayout(new GridLayout(2, 2));

        JTextField textField = new JTextField(); //Campo de texto
        textField.setSize(150, 150); //Tamaño
        JLabel label = new JLabel(); //Etiqueta que reflejara el texto
        label.setSize(150, 150); //Tamaño

        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { updateLabel(); } 
            public void removeUpdate(DocumentEvent e) { updateLabel(); }
            public void insertUpdate(DocumentEvent e) { updateLabel(); }

            private void updateLabel(){
                label.setText(textField.getText()); //Actualiza el texto de la etiqueta con el del campo de texto
            }
        });
        
        ventana.add(textField);
        ventana.add(label);
        ventana.setVisible(true);
    }
}