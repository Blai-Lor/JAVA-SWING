package es.cide.programacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Main {
    private static double operacion1 = 0; //Primer numero introducido
    private static String simboloOperacion = "="; //Para el simbolo de la operacion
    private static boolean borraNumero = true; //Borra lo que hay al poner un nuevo numero
    
    public static void hacerOperacion(double numero) { //Metodo para hacer las operaciones
        if (simboloOperacion.equals("+")) { //Metodo de suma
            operacion1 = operacion1 + numero;
        } else if (simboloOperacion.equals("-")) { //Metodo de resta
            operacion1 = operacion1 - numero;
        } else if (simboloOperacion.equals("*")) { //Metodo de multiplicacion
            operacion1 = operacion1 * numero;
        } else if (simboloOperacion.equals("/")) { //Metodo de la division
            if (numero != 0) { //Si es diferente a 0, hace la division
                operacion1 = operacion1 / numero;
            } else { //Si es 0 muestra el mensaje de error
                JOptionPane.showMessageDialog(null, "Error: No es posible hacer esta operacion");
            }
        } else if (simboloOperacion.equals("=")) { //Metodo igual para que nos de el resultado 
            operacion1 = numero;
        }
    }

    public static void botonOperacionHistorial(String botonPulsado, JTextField areaOperacion, JTextField areaHistorial) {
        if (botonPulsado.matches("[0-9]")) { //Comprueba si el boton pulsado es un numero que permitimos
            if (borraNumero) { //Si es un nuevo numero, lo muestra en areaOperacion y borra el anterior
                areaOperacion.setText(botonPulsado); //Muestra el numero pulsado
                borraNumero = false; 
            } else { //Junta el numero nuevo con el anterior
                areaOperacion.setText(areaOperacion.getText() + botonPulsado);
            }
        } else if (botonPulsado.equals("C")) { //Si ponemos la C 
            areaOperacion.setText("0"); //Limpia el area de la operacion y aparece 0
            areaHistorial.setText(""); //Limpia el historias
            operacion1 = 0; 
            simboloOperacion = "="; 
            borraNumero = true; //Lo borra
        } else {
            try { //Si es una operacion la calcula y muestra el resultado
                double numero = Double.parseDouble(areaOperacion.getText()); //Pasa el numero puesto a un double para poder calcularlo despues
                
                hacerOperacion(numero); //Hace la operacion con el numero introducido antes de cambiar el simbolo
                simboloOperacion = botonPulsado; //Pone el simbolo de la operacion para la siguiente entrada
                borraNumero = true; //Borra el numero al poner la nueva operacion
                
                if (botonPulsado.equals("=")) { //Si ponemos el igual, muestra el resultado final en el historial y en la operacion
                    areaHistorial.setText(areaHistorial.getText() + numero + " = " + operacion1); //Aparecera el numero, el igual y el resultado en el historial 
                    areaOperacion.setText(String.valueOf(operacion1)); //Muestra el resultado en la operacion 
                } else { //Si la operacion es diferente al igual, muestra el numero y la operacion en el historial con el resultado
                    areaHistorial.setText(areaHistorial.getText() + numero + " " + botonPulsado + " "); //Aparecera el numero, la operacion y un espacio en el historial
                    areaOperacion.setText(String.valueOf(operacion1)); //Muestra el resultado parcial en la operacion
                }
            } catch (NumberFormatException e) { //Si hay un error ponemos 0 
                areaOperacion.setText("0");
            }
        }
    }

    public static void main(String[] args) {
        try{ //LookAndFeel
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame pantallaPrincipal = new JFrame("Calculadora amb estat - Blai"); 
        pantallaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pantallaPrincipal.setSize(400, 500); 
        pantallaPrincipal.setLayout(new BorderLayout(10, 10)); 

        JTextField areaVisualizacionHistorica = new JTextField(); //Area de visualizacion historica 
        areaVisualizacionHistorica.setHorizontalAlignment(JTextField.LEFT); //El texto estara en la derecha
        areaVisualizacionHistorica.setEditable(false);

        JTextField areaEntradaPrincipal = new JTextField("0"); //Area de entrada principal por defecto aparece 0 
        areaEntradaPrincipal.setHorizontalAlignment(JTextField.RIGHT); //El texto estara en la derecha
        areaEntradaPrincipal.setEditable(false);
        

        JPanel panelSuperior = new JPanel(new GridLayout(2, 1)); //En el panel superior estaran los dos areas 
        panelSuperior.add(areaVisualizacionHistorica); 
        panelSuperior.add(areaEntradaPrincipal);
        pantallaPrincipal.add(panelSuperior, BorderLayout.NORTH); //Aparece el panel historal y el de operaciones

        JPanel panelBotones = new JPanel(new GridLayout(4, 4, 5, 5)); //Panel de los botones
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        
        JButton boton7 = new JButton("7"); //Boton 7
        boton7.addActionListener(e -> botonOperacionHistorial("7", areaEntradaPrincipal, areaVisualizacionHistorica));
        boton7.setFocusable(false); //Para que funcione con teclado y raton

        JButton boton8 = new JButton("8"); //Boton 8
        boton8.addActionListener(e -> botonOperacionHistorial("8", areaEntradaPrincipal, areaVisualizacionHistorica));
        boton8.setFocusable(false); //Para que funcione con teclado y raton


        JButton boton9 = new JButton("9"); //Boton 9
        boton9.addActionListener(e -> botonOperacionHistorial("9", areaEntradaPrincipal, areaVisualizacionHistorica));
        boton9.setFocusable(false); //Para que funcione con teclado y raton

        JButton botonDivision = new JButton("/"); //Boton Division
        botonDivision.setBackground(new Color(235, 134, 52)); //Color de fondo
        botonDivision.addActionListener(e -> botonOperacionHistorial("/", areaEntradaPrincipal, areaVisualizacionHistorica));
        botonDivision.setFocusable(false); //Para que funcione con teclado y raton

        JButton boton4 = new JButton("4"); //Boton 4
        boton4.addActionListener(e -> botonOperacionHistorial("4", areaEntradaPrincipal, areaVisualizacionHistorica));
        boton4.setFocusable(false); //Para que funcione con teclado y raton

        JButton boton5 = new JButton("5"); //Boton 5
        boton5.addActionListener(e -> botonOperacionHistorial("5", areaEntradaPrincipal, areaVisualizacionHistorica));
        boton5.setFocusable(false); //Para que funcione con teclado y raton

        JButton boton6 = new JButton("6"); //Boton 6 
        boton6.addActionListener(e -> botonOperacionHistorial("6", areaEntradaPrincipal, areaVisualizacionHistorica));
        boton6.setFocusable(false); //Para que funcione con teclado y raton

        JButton botonMultiplicacion = new JButton("*"); //Boton Multiplicacion
        botonMultiplicacion.setBackground(new Color(235, 134, 52)); //Color de fondo
        botonMultiplicacion.addActionListener(e -> botonOperacionHistorial("*", areaEntradaPrincipal, areaVisualizacionHistorica));
        botonMultiplicacion.setFocusable(false); //Para que funcione con teclado y raton

        JButton boton1 = new JButton("1"); //Boton 1
        boton1.addActionListener(e -> botonOperacionHistorial("1", areaEntradaPrincipal, areaVisualizacionHistorica));
        boton1.setFocusable(false); //Para que funcione con teclado y raton

        JButton boton2 = new JButton("2"); //Boton 2
        boton2.addActionListener(e -> botonOperacionHistorial("2", areaEntradaPrincipal, areaVisualizacionHistorica));
        boton2.setFocusable(false); //Para que funcione con teclado y raton

        JButton boton3 = new JButton("3"); //Boton 3
        boton3.addActionListener(e -> botonOperacionHistorial("3", areaEntradaPrincipal, areaVisualizacionHistorica));
        boton3.setFocusable(false); //Para que funcione con teclado y raton

        JButton botonRestar = new JButton("-"); //Boton Resta
        botonRestar.setBackground(new Color(235, 134, 52)); //color de fondo 
        botonRestar.addActionListener(e -> botonOperacionHistorial("-", areaEntradaPrincipal, areaVisualizacionHistorica));
        botonRestar.setFocusable(false); //Para que funcione con teclado y raton

        JButton botonC = new JButton("C"); //Boton C
        botonC.setBackground(new Color(235, 52, 52)); //Color de fondo
        botonC.addActionListener(e -> botonOperacionHistorial("C", areaEntradaPrincipal, areaVisualizacionHistorica));
        botonC.setFocusable(false); //Para que funcione con teclado y raton

        JButton boton0 = new JButton("0"); //Boton 0
        boton0.addActionListener(e -> botonOperacionHistorial("0", areaEntradaPrincipal, areaVisualizacionHistorica));
        boton0.setFocusable(false); //Para que funcione con teclado y raton

        JButton botonIgual = new JButton("="); //Boton Igual
        botonIgual.setBackground(new Color(52, 116, 235)); //Color de fondo
        botonIgual.addActionListener(e -> botonOperacionHistorial("=", areaEntradaPrincipal, areaVisualizacionHistorica));
        botonIgual.setFocusable(false); //Para que funcione con teclado y raton

        JButton botonSumar = new JButton("+"); //Boton Suma
        botonSumar.setBackground(new Color(235, 134, 52)); //color de fondo 
        botonSumar.addActionListener(e -> botonOperacionHistorial("+", areaEntradaPrincipal, areaVisualizacionHistorica));
        botonSumar.setFocusable(false); //Para que funcione con teclado y raton

        //Añadimos los botones en el orden visual del enunciado
        panelBotones.add(boton7); 
        panelBotones.add(boton8); 
        panelBotones.add(boton9); 
        panelBotones.add(botonDivision);

        panelBotones.add(boton4); 
        panelBotones.add(boton5); 
        panelBotones.add(boton6); 
        panelBotones.add(botonMultiplicacion);

        panelBotones.add(boton1); 
        panelBotones.add(boton2); 
        panelBotones.add(boton3); 
        panelBotones.add(botonRestar);

        panelBotones.add(botonC); 
        panelBotones.add(boton0); 
        panelBotones.add(botonIgual); 
        panelBotones.add(botonSumar);

        pantallaPrincipal.add(panelBotones, BorderLayout.CENTER); //Aparece el panel de los botones 

        pantallaPrincipal.addKeyListener(new KeyAdapter() { //Ponemos KeyListener para el teclado
            @Override
            public void keyPressed(KeyEvent e) { //Si se pulsa una tecla
                char teclaTeclado = e.getKeyChar(); //Guarda la tecla al pulsarla en un char
                String pulsado = String.valueOf(teclaTeclado).toUpperCase(); //Pasa el char a String, lo pone enmayusculas y lo guarda en la variable pulsado
                String caracteresCorrectos = "0123456789+-*/=C"; //Variable con los caracteres
                if (e.getKeyCode() == KeyEvent.VK_ENTER) { //Al poner enter, hace lo mismo que el boton igual
                    botonOperacionHistorial("=", areaEntradaPrincipal, areaVisualizacionHistorica); 
                } else if (caracteresCorrectos.indexOf(pulsado) != -1) { //Si el caracter pulsado es correcto
                    botonOperacionHistorial(pulsado, areaEntradaPrincipal, areaVisualizacionHistorica); //Lo manda al metodo para que haga lo mismo que si se pulsa el boton
                }
            }
        });

        pantallaPrincipal.setFocusable(true);
        pantallaPrincipal.setVisible(true);
    }
}