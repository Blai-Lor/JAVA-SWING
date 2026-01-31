package es.cide.programacion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Selector de Colores");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(450, 250); //Tamaño de la ventana
        ventana.setLayout(new FlowLayout());

        JPanel panel = new JPanel();
        JButton botonRojo = new JButton("Vermell"); //Nombre del boton
        botonRojo.setSize(100, 50); //Tamaño del boton
        JButton botonVerde = new JButton("Verd"); //Nombre del boton
        botonVerde.setSize(100, 50); //Tamaño del boton
        JButton botonAzul = new JButton("Blau"); //Nombre del boton
        botonAzul.setSize(100, 50); //Tamaño del boton

        botonRojo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.getContentPane().setBackground(new java.awt.Color(255, 0, 0)); //Cambiar el color del fondo a rojo
            }
        });

        botonVerde.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.getContentPane().setBackground(new java.awt.Color(0, 255, 0)); //Cambiar el color del fondo a verde
            }
        });

        botonAzul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventana.getContentPane().setBackground(new java.awt.Color(0, 0, 255)); //Cambiar el color del fondo a azul
            }
        });

        panel.add(botonRojo);
        panel.add(botonVerde);
        panel.add(botonAzul);
        ventana.add(panel);
        ventana.setVisible(true);
    }
    
}