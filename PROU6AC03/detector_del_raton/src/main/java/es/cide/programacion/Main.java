package es.cide.programacion;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Detector de Raton");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 300); //Tamaño de la ventana
        ventana.setLayout(new GridLayout(1, 1));

        JLabel label = new JLabel("Posición del ratón - X: 0 Y: 0"); //Texto que muestra la posicion de las coordenadas del raton
        JPanel panel = new JPanel(); 
        panel.setSize(400, 300); //Tamaño del panel
        panel.addMouseMotionListener(new MouseAdapter() { //Detecta el movimiento del raton
            @Override
            public void mouseMoved(MouseEvent e) {
                int x = e.getX(); //Coordenada X
                int y = e.getY(); //Coordenada Y
                label.setText("Posición del ratón - X: " + x + " Y: " + y); //Actualiza el texto con la posicion del raton
            }
        });
        
        ventana.add(panel);
        panel.add(label);
        ventana.setVisible(true);
    }
}