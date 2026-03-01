package es.cide.programacion;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            //Menu principal
            JFrame menuFrame = new JFrame("POOng! - Menu Principal");
            menuFrame.setSize(400, 400);
            menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menuFrame.setLocationRelativeTo(null);

            //Panel principal
            JPanel panelPrincipal = new JPanel();
            panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS)); //BoxLayout para apilar elementos verticalmente
            panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

            //Titulo POOng
            JLabel labelTitulo = new JLabel("POOng!");
            labelTitulo.setFont(new Font("Courier New", Font.BOLD, 55)); //Fuente para el texto y el tamaño
            labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

            //Panel Jugador 1
            JPanel panelJugador1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelJugador1.add(new JLabel("Jugador 1: "));
            JTextField nombreJugador1 = new JTextField("Nombre", 10); //Aqui el jugador introducira su nombre
            panelJugador1.add(nombreJugador1);

            //Panel Jugador 2
            JPanel panelJugador2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelJugador2.add(new JLabel("Jugador 2: "));
            JTextField nombreJugador2 = new JTextField("Nombre", 10); //Aqui el jugador introducira su nombre
            panelJugador2.add(nombreJugador2);

            //Boton para comenzar el juego
            JButton botonComenzar = new JButton("Comenzar Juego");
            botonComenzar.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonComenzar.setFocusPainted(false);

            //Boton Instrucciones
            JButton botonInstrucciones = new JButton("Instrucciones");
            botonInstrucciones.setAlignmentX(Component.CENTER_ALIGNMENT);
            botonInstrucciones.addActionListener(e -> { //Mensaje con las instrcciones
                String textoInstrucciones = 
                "INSTRUCCIONES POOng!:\n\n" +
                "Jugador 1: Teclas 'W' (arriba) y 'S' (abajo)\n" +
                "Jugador 2: Flechas 'Arriba' y 'Abajo'\n" +
                "El objetivo del juego es hacer la maxima puntuacion y que tu rival no toque la pelota.";
                JOptionPane.showMessageDialog(menuFrame, textoInstrucciones, "Instrucciones", JOptionPane.INFORMATION_MESSAGE); //Enseña el mensaje
            });

            //Abre el juego y pone los nombres 
            botonComenzar.addActionListener(e -> {
                JFrame frameJuego = new JFrame("POOng! - Blai");
                //Pone los nombres de los JTextField en el juego
                CercleRebotant panelJuego = new CercleRebotant(nombreJugador1.getText(), nombreJugador2.getText());
                frameJuego.add(panelJuego);
                frameJuego.setExtendedState(JFrame.MAXIMIZED_BOTH); //Pantalla Completa
                frameJuego.setResizable(false);
                frameJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameJuego.setLocationRelativeTo(null);

                menuFrame.dispose(); //Cierra el menu
                frameJuego.setVisible(true); //Abre el juego
                
                panelJuego.requestFocusInWindow();
            });

            //Añadimos los elementos y le ponemos espacios para que se vea bien
            panelPrincipal.add(labelTitulo);
            panelPrincipal.add(Box.createRigidArea(new Dimension(0, 40))); //Separacion
            panelPrincipal.add(panelJugador1);
            panelPrincipal.add(panelJugador2);
            panelPrincipal.add(Box.createRigidArea(new Dimension(0, 30))); //Separacion
            panelPrincipal.add(botonComenzar);
            panelPrincipal.add(Box.createRigidArea(new Dimension(0, 10))); //Separacion
            panelPrincipal.add(botonInstrucciones);

            menuFrame.add(panelPrincipal);
            menuFrame.setVisible(true);
        });
    }

    public static class CercleRebotant extends JPanel implements ActionListener, KeyListener {
        private int x = -1, y = -1; //Coordenadas iniciales (se calculan al arrancar)
        private int velX = 5, velY = 5; //Velocidad del movimiento en X e Y
        private final int RADI = 15; //Radi del circulo
        private final int DELAY = 15; //Retardo del contador en milisegundos
        private Timer timer; //Temporizador para controlar la animacion

        private int rect1X = 25, rect1Y = 250; //Rectangulo jugador 1
        private int rect2X = -1, rect2Y = 250; //Rectangulo jugador 2
        private int velocidadY = 12; //Velocidad de los rectangulos
        String nombre1; //Nombre jugador 1
        String nombre2; //Nombre jugador 2
        private int puntuacion1 = 0; //Puntuacion jugador 1
        private int puntuacion2 = 0; //Puntuacion jugador 2

        public CercleRebotant(String n1, String n2) { //Constructor que inicializa el panel e inicializa el temporizador
            this.nombre1 = n1;
            this.nombre2 = n2;
            setBackground(new Color(74, 184, 194)); //Color de fondo
            timer = new Timer(DELAY, this); //Crea el temporizador con retardo
            timer.start(); //Inicializa el temporizador
            addKeyListener(this);
            setFocusable(true);
            requestFocusInWindow();
        }

        //Metodo para dibujar el circulo, la linea, los rectangulos y los jugadores dentro del panel
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g; //Conversion a graficos 2D
            
            //Para que siempre este en la misma posicion en todos los monitores
            int anchoPantalla = getWidth();
            int altoPantalla = getHeight();
            
            //Posicion del rectangulo de la derecha para que siempre este en el borde
            rect2X = anchoPantalla - 45;

            g2d.setColor(Color.WHITE); //Color del circulo
            g2d.fillOval(x, y, RADI * 2, RADI * 2); //Dibuja el circulo con las cordenadas y el radio
            g2d.setFont(new Font("Courier New", Font.BOLD, 25)); //Fuente de texto para los marcadores

            //Linea en el centro de separacion de los dos campos de los jugadores 
            g2d.fillRect(anchoPantalla / 2 - 2, 0, 4, altoPantalla);

            //Texto Jugador 1
            g2d.setColor(Color.WHITE); //Color del texto
            g2d.drawString(nombre1 + ": " + puntuacion1, anchoPantalla / 4, 40); //Nombre y puntuacion del jugador 1
            
            //Rectangulo Jugador 1
            g2d.fillRect(rect1X, rect1Y, 20, 100);

            //Texto Jugador 2
            g2d.setColor(Color.WHITE); //Color del texto
            g2d.drawString(nombre2 + ": " + puntuacion2, (anchoPantalla * 3) / 4 - 50, 40); //Nombre y puntuacion del jugador 2

            //Rectangulo Jugador 2
            g2d.fillRect(rect2X, rect2Y, 20, 100);
        }

        //Movimientos jugadores
        public void keyPressed(KeyEvent e) {
            int tecla = e.getKeyCode();
            if (tecla == KeyEvent.VK_W && rect1Y > 0) { //La subida y tope del rectangulo del jugador 1
                rect1Y -= velocidadY;
            } else if (tecla == KeyEvent.VK_S && rect1Y < getHeight() - 100) { //La bajada y tope del rectangulo del jugador 1
                rect1Y += velocidadY;
            } else if (tecla == KeyEvent.VK_UP && rect2Y > 0) { //La subida y tope del rectangulo del jugador 2
                rect2Y -= velocidadY;
            } else if (tecla == KeyEvent.VK_DOWN && rect2Y < getHeight() - 100) { //La bajada y tope del rectangulo del jugador 2
                rect2Y += velocidadY;
            }
            repaint();
        }

        @Override public void keyReleased(KeyEvent e) {

        }
        @Override public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //La pelota empieza en el centro 
            if (x == -1) {
                x = getWidth() / 2 - RADI;
                y = getHeight() / 2 - RADI;
                rect2X = getWidth() - 50;
            }

            x += velX; //Ajustamos la posicion del circulo
            y += velY;

            //Los rebotes de las paredes de arriba y abajo
            if (y + 2 * RADI >= getHeight() || y <= 0) { 
                velY = -velY; 
            }

            //Puntuacion jugador 1
            if (x + 2 * RADI >= getWidth()) {
                puntuacion1++; 
                //Al puntuar aparece la pelota en el centro
                x = getWidth() / 2 - RADI;
                y = getHeight() / 2 - RADI;
                velX = -velX; //Cambia la direccion en el saque
            }

            //Puntuacion jugador 2
            if (x <= 0) {
                puntuacion2++; 
                //Al puntuar aparece la pelota en el centro
                x = getWidth() / 2 - RADI;
                y = getHeight() / 2 - RADI;
                velX = -velX; //Cambia la direccion en el saque
            }

            //El rebote del rectangulo del jugador 1
            if (x <= rect1X + 20 && x >= rect1X && y + 2 * RADI >= rect1Y && y <= rect1Y + 100) {
                velX = Math.abs(velX); //Hace que la pelota vaya a la derecha
            }

            //El rebote del rectangulo del jugador 2
            if (x + 2 * RADI >= rect2X && x + 2 * RADI <= rect2X + 20 && y + 2 * RADI >= rect2Y && y <= rect2Y + 100) {
                velX = -Math.abs(velX); //Hace que la pelota vaya a la izquierda
            }

            repaint();
        }
    }
}