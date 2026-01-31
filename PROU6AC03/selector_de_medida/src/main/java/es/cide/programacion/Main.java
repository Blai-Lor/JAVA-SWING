package es.cide.programacion;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Selector de Medida");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 300); //Tamaño de la ventana
        ventana.setLayout(new FlowLayout());

        JSlider slider = new JSlider(JSlider.HORIZONTAL); //Slider horizontal
        slider.setMinimum(10); //Tamaño minimo
        slider.setMaximum(50); //Tamaño maximo

        JLabel text = new JLabel("Texto"); //Texto que cambia de tamaño
        slider.addChangeListener(new ChangeListener() { //El slider cambia
            @Override
            public void stateChanged(ChangeEvent e) { //El estado cambia
                text.setFont(text.getFont().deriveFont((float) slider.getValue())); //El tamaño del texto cambia
            }
        });
        
        ventana.add(slider);
        ventana.add(text);
        ventana.setVisible(true);
    }
}