package es.cide.programacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Lista de la Compra");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(450, 250); //Tamaño de la ventana
        ventana.setLayout(new BorderLayout());

        JTextField texto = new JTextField(); //Texto
        texto.setColumns(20);
        JButton boton = new JButton("Guardar en la lista"); //Boton de guardar en la lista

        DefaultListModel<String> model = new DefaultListModel<>(); //Modelo de la lista
        JList<String> lista = new JList<>(model); //Lista que muestra los elementos guardados

        boton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                String text = texto.getText(); //Obtiene el texto del JTextField
                model.addElement(text); //Añade el texto a la lista
                texto.setText(""); //Deja en blanco la zona del texto
            }
        });
        
        ventana.add(lista, BorderLayout.EAST);
        ventana.add(texto, BorderLayout.WEST);
        ventana.add(boton, BorderLayout.NORTH);
        ventana.setVisible(true);
    }
}