package es.cide.programacion;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tauler d'Administració - [Blai]");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2,2,15,15));
        
        JPanel panell1 = new JPanel(); //Panel 1
        panell1.setBorder(new TitledBorder("Estat del Sistema")); //Titulo del panel
        panell1.setLayout(new GridLayout(2, 1));
        JPanel cpu = new JPanel(new BorderLayout()); //Creamos CPU
        cpu.add(new JLabel("CPU: 15% Ús", JLabel.CENTER), BorderLayout.AFTER_LAST_LINE); //JLabel.CENTER aparece centrado y el AFTER_LAST_LINE abajo del contenedor
        JPanel ram = new JPanel(new BorderLayout()); //Creamos RAM 
        ram.add(new JLabel("RAM: 4GB / 16GB", JLabel.CENTER), BorderLayout.BEFORE_FIRST_LINE); //JLabel.CENTER aparece en el centro y con el BEFORE_FIRST_LINE aparece arriba del contenedor
        panell1.add(cpu);
        panell1.add(ram);

        JPanel panell2 = new JPanel(); //Panel 2

        panell2.setBorder(new CompoundBorder(new TitledBorder("Accions Ràpides"), new EmptyBorder(50, 100,50, 100))); //Juntamos el titulo con los bordes
        panell2.setLayout(new GridLayout(3, 1, 0, 10));
        JButton iniciServei = new JButton("▶ Iniciar Servei"); //Creamos el boton de Iniciar Servei
        JButton aturarServei = new JButton( "■ Aturar Servei"); //Creamos el boton de Aturar Servei
        JButton reiniciarServei = new JButton("↻ Reiniciar"); //Creamos el boton de Reiniciar
        panell2.add(iniciServei);
        panell2.add(aturarServei);
        panell2.add(reiniciarServei); 

        JPanel panell3 = new JPanel(); //Panel 3
        panell3.setBorder(new TitledBorder("Paràmetres de Càrrega")); //Titulo del panel
        panell3.setLayout(new GridLayout(4, 1));
        JLabel limitConexio = new JLabel("Límit de Connexions (0-500):");
        JSlider slider = new JSlider(0, 500, 250);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(25);
        JLabel timeout = new JLabel("Timeout (segons):");
        JTextField valor = new JTextField("60"); //Aqui el 60 aparece por defecto
        panell3.add(limitConexio);
        panell3.add(slider);
        panell3.add(timeout);
        panell3.add(valor);

        JPanel panell4 = new JPanel(); //Panel 4
        panell4.setBorder(new TitledBorder("Logs del Servidor en Viu")); //Titulo del panel
        panell4.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea(10, 15);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); //Fuente y tamaño
        textArea.setText("[Info] Servei iniciat...\n [WARNING] Connexió lenta. IP 192.168.1.45 \n [INFO] Base de dades connectada \n [ERROR] Failed en el mòdul 'Auntenticación' (intent 1)");
        textArea.setEditable(false); //Para que no se pueda editar
        JScrollPane scroll = new JScrollPane(textArea); //En el scroll ponemos el textArea en el constructor
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panell4.add(scroll, BorderLayout.CENTER);

        //Añadimos los 4 paneles
        frame.add(panell1);
        frame.add(panell2);
        frame.add(panell3);
        frame.add(panell4);
        
        frame.setVisible(true);
    }
}