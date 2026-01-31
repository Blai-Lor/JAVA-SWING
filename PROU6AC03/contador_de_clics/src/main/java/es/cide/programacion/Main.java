package es.cide.programacion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

    private static int contador = 0; //Creamos el contador

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Contador de clicks");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(450, 250); //Tamaño 
        ventana.setLayout(new FlowLayout());

        JButton boton = new JButton("Cada vez que pulses el boton se suma"); //Este es el boton
        JLabel etiqueta = new JLabel("Clicks: " + contador); //Muestra el contador

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                contador++; //Suma el contador
                etiqueta.setText("Clicks: " + contador); //Muestra el contador
            }
        });

        ventana.add(boton); //Insertamos el boton
        ventana.add(etiqueta); //Insertamos la etiqueta
        ventana.setVisible(true); //Hacemos que sea visible
    }
}