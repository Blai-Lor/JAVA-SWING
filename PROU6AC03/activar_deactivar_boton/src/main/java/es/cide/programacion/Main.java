package es.cide.programacion;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Activar/Desactivar Boton");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(450, 250); //Tamaño de la ventana
        ventana.setLayout(new FlowLayout());

        JCheckBox checkBox = new JCheckBox("Activa el botó");
        JButton boton = new JButton("Boton");
        boton.setEnabled(false); //El boton esta desactivado
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boton.setEnabled(checkBox.isSelected()); //Si el checkbox esta seleccionado, el boton se activa
            }
        });
        
        ventana.add(checkBox);
        ventana.add(boton);
        ventana.setVisible(true);
    }
}