package es.cide.programacion;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Eleccion Sistema Operativo");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 300); //Tamaño de la ventana
        ventana.setLayout(new FlowLayout());

        JLabel label = new JLabel();
        ButtonGroup grupoBoton = new ButtonGroup();
        JRadioButton windows = new JRadioButton("Windows"); //Boton de Windows
        JRadioButton linux = new JRadioButton("Linux"); //Boton de Linux
        JRadioButton mac = new JRadioButton("MacOS"); //Boton de MacOS

        ImageIcon iconoWindows = new ImageIcon("eleccion_sistema_operativo/src/main/resources/windows.png"); //Imagen de Windows
        ImageIcon tamañoWindows = new ImageIcon(iconoWindows.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)); //Tamaño de la imagen
        ImageIcon iconoLinux = new ImageIcon("eleccion_sistema_operativo/src/main/resources/linux.png"); //Imagen de Linux
        ImageIcon tamañoLinux = new ImageIcon(iconoLinux.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)); //Tamaño de la imagen
        ImageIcon iconoMac = new ImageIcon("eleccion_sistema_operativo/src/main/resources/macos.png"); //Imagen de MacOS
        ImageIcon tamañoMac = new ImageIcon(iconoMac.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)); //Tamaño de la imagen

        windows.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setIcon(tamañoWindows); //Al seleccionar el boton de Windows, aparece la imagen
            }
        });
        linux.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setIcon(tamañoLinux); //Al seleccionar el boton de Linux, aparece la imagen
            }
        });
        mac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setIcon(tamañoMac); //Al seleccionar el boton de MacOS, aparece la imagen
            }
        });

        grupoBoton.add(windows);
        grupoBoton.add(linux);
        grupoBoton.add(mac);
        ventana.add(windows);
        ventana.add(linux);
        ventana.add(mac);
        ventana.add(label);
        ventana.setVisible(true);
    }
}