package es.cide.programacion;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Sumadora Express");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(450, 250); //Tamaño de la ventana
        ventana.setLayout(new GridLayout(4, 4));
        
        JTextField num1 = new JTextField(); //Aqui va el primer numero
        JTextField num2 = new JTextField(); //Aqui va el segundo numero
        JTextArea resultado = new JTextArea(); //Aqui aparece el resultado
        JButton botonSumar = new JButton("Sumar"); //Boton sumar
        botonSumar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero1 = Integer.parseInt(num1.getText()); //Ponemos el primer numero
                    int numero2 = Integer.parseInt(num2.getText()); //Ponemos el segundo numero
                    int suma = numero1 + numero2; //Hace la operacion de suma
                    resultado.setText("La suma es: " + suma); //Muestra el resultado
                } catch (NumberFormatException ex) {
                    resultado.setText("Error"); //Muestra el error
                }
            }
        });
        
        ventana.add(num1);
        ventana.add(num2);
        ventana.add(resultado);
        ventana.add(botonSumar);
        ventana.setVisible(true);
    }
}