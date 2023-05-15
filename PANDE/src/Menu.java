import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends JFrame implements ActionListener  {
	private JButton nuevaPartida;
	private JButton cargarPartida;
	private JButton informacion;
	private JButton resumenPuntuaciones;
	private JButton autores;
	private JButton version;
	private JButton salir;
    private int id_jugador;
	public Menu(int id_jugador) {
		// EN ESTA SECCION PERSONALIZAMOS EL PANEL PRINCIPAL
		setTitle("PANDEMIC");
			Audio.musicaMenu();
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				".\\imagenes utilizadas\\pandemic2.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.GREEN));
		setBounds(100, 100, 1200, 710);
		ImageIcon imagenDeFondo = new ImageIcon(
				".\\imagenes utilizadas\\epico2.png"); 
		getContentPane().setLayout(null);
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		ImageIcon iconoNormal1 = new ImageIcon(".\\imagenes utilizadas\\botonnuevapartida.png");
		ImageIcon iconoPresionado1 = new ImageIcon(".\\imagenes utilizadas\\botonnuevapartida2.png");
		ImageIcon iconoSobre1 = new ImageIcon(".\\imagenes utilizadas\\botonnuevapartida2.png");
		JButton nuevaPartida = new JButton(iconoNormal1); // NOMBRE DEL BOTON MAS LA CREACION		
		
		nuevaPartida.setBorder(null);
		nuevaPartida.setOpaque(true);
		nuevaPartida.setBackground(new Color(0,0,0,0));
		nuevaPartida.setBounds(100, 418, iconoNormal1.getIconWidth(), iconoNormal1.getIconHeight());
		nuevaPartida.setIcon(iconoNormal1);
		nuevaPartida.setPressedIcon(iconoPresionado1);
		nuevaPartida.setRolloverIcon(iconoSobre1);
		nuevaPartida.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	nuevaPartida.setIcon(iconoPresionado1);
		    	nuevaPartida.setBorderPainted(false);
		    	nuevaPartida.setContentAreaFilled(false);
		    }

		    public void mouseEntered(MouseEvent e) {
		    	nuevaPartida.setIcon(iconoSobre1);
		    	nuevaPartida.setBorderPainted(false);
		    	nuevaPartida.setContentAreaFilled(false);
		    }

		    public void mouseExited(MouseEvent e) {
		    	nuevaPartida.setIcon(iconoNormal1);
		    	nuevaPartida.setBorderPainted(false);
		    	nuevaPartida.setContentAreaFilled(false);
		    }
		});
	
		nuevaPartida.addActionListener(new ActionListener() { // HACEMOS QUE EL BOTON REALICE UNA ACCION CUANDO ES
																// PULSADO
			public void actionPerformed(ActionEvent e) {
				
				Audio.sonidoBoton();
				dispose();
				PanelNombrePartida PanelNombre = new PanelNombrePartida(id_jugador);
				PanelNombre.setVisible(true);
			
			}
		});
		getContentPane().add(nuevaPartida); // AÑADIMOS EL BOTON AL PANEL

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// BOTON CARGAR PARTIDA
		ImageIcon iconoNormal2 = new ImageIcon(".\\imagenes utilizadas\\botoncargarpartida.png");
		ImageIcon iconoPresionado2 = new ImageIcon(".\\imagenes utilizadas\\botoncargarpartida2.png");
		ImageIcon iconoSobre2 = new ImageIcon(".\\imagenes utilizadas\\botoncargarpartida2.png");
		JButton cargarPartida = new JButton(iconoNormal2); // NOMBRE DEL BOTON MAS LA CREACION		
		cargarPartida.setBorder(null);
		cargarPartida.setOpaque(true);
		cargarPartida.setBackground(new Color(0,0,0,0));
		cargarPartida.setBounds(450, 418, iconoNormal2.getIconWidth(), iconoNormal2.getIconHeight());
		cargarPartida.setIcon(iconoNormal2);
		cargarPartida.setPressedIcon(iconoPresionado2);
		cargarPartida.setRolloverIcon(iconoSobre2);
		cargarPartida.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	cargarPartida.setBorderPainted(false);
		    	cargarPartida.setContentAreaFilled(false);
		    }

		    public void mouseEntered(MouseEvent e) {
		    	cargarPartida.setIcon(iconoSobre2);
		    	cargarPartida.setBorderPainted(false);
		    	cargarPartida.setContentAreaFilled(false);
		    }

		    public void mouseExited(MouseEvent e) {
		    	cargarPartida.setIcon(iconoNormal2);
		    	cargarPartida.setBorderPainted(false);
		    	cargarPartida.setContentAreaFilled(false);
		    }
		});
	
		cargarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				dispose();
				PanelCargarPartida panelCargarPartida = new PanelCargarPartida(id_jugador);
				panelCargarPartida.setVisible(true);
			}
		});
		getContentPane().add(cargarPartida);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// BOTON DESARROLLADORES
		ImageIcon iconoNormal3 = new ImageIcon(".\\imagenes utilizadas\\botonautores.png");
		ImageIcon iconoPresionado3 = new ImageIcon(".\\imagenes utilizadas\\botonautores2.png");
		ImageIcon iconoSobre3 = new ImageIcon(".\\imagenes utilizadas\\botonautores2.png");
		JButton autores = new JButton(iconoNormal3);
		autores.setBorder(null);
		autores.setOpaque(true);
		autores.setBackground(new Color(0,0,0,0));
		autores.setBounds(500, 500, iconoNormal3.getIconWidth(), iconoNormal3.getIconHeight());
		autores.setIcon(iconoNormal3);
		autores.setPressedIcon(iconoPresionado3);
		autores.setRolloverIcon(iconoSobre3);
		autores.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	autores.setIcon(iconoPresionado3);
		    	autores.setBorderPainted(false);
		    	autores.setContentAreaFilled(false);
		    }

		    public void mouseEntered(MouseEvent e) {
		    	autores.setIcon(iconoSobre3);
		    	autores.setBorderPainted(false);
		    	autores.setContentAreaFilled(false);
		    }

		    public void mouseExited(MouseEvent e) {
		    	autores.setIcon(iconoNormal3);
		    	autores.setBorderPainted(false);
		    	autores.setContentAreaFilled(false);
		    }
		});
		autores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				// Crea una instancia del nuevo panel y lo muestra
				PanelAutores panelAutores = new PanelAutores();
				panelAutores.setVisible(true);
			}
		});
		getContentPane().add(autores);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// BOTON DE INFORMACION
		ImageIcon iconoNormal4 = new ImageIcon(".\\imagenes utilizadas\\botoninfo.png");
		ImageIcon iconoPresionado4 = new ImageIcon(".\\imagenes utilizadas\\botoninfo2.png");
		ImageIcon iconoSobre4 = new ImageIcon(".\\imagenes utilizadas\\botoninfo2.png");

		JButton informacion = new JButton(iconoNormal4);
		informacion.setBorder(null);
		informacion.setOpaque(true);
		informacion.setBackground(new Color(0,0,0,0));
		informacion.setBounds(800, 418, iconoNormal4.getIconWidth(), iconoNormal4.getIconHeight());
		informacion.setIcon(iconoNormal4);
		informacion.setPressedIcon(iconoPresionado4);
		informacion.setRolloverIcon(iconoSobre4);
		informacion.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	informacion.setIcon(iconoPresionado4);
		    	informacion.setBorderPainted(false);
		    	informacion.setContentAreaFilled(false);
		    }

		    public void mouseEntered(MouseEvent e) {
		    	informacion.setIcon(iconoSobre4);
		    	informacion.setBorderPainted(false);
		    	informacion.setContentAreaFilled(false);
		    }

		    public void mouseExited(MouseEvent e) {
		    	informacion.setIcon(iconoNormal4);
		    	informacion.setBorderPainted(false);
		    	informacion.setContentAreaFilled(false);
		    }
		});
		
		informacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				// Crea una instancia del nuevo panel y lo muestra
				
				PanelInformacion panelInformacion = new PanelInformacion();
				panelInformacion.setVisible(true);
				
				
			}
		});
		getContentPane().add(informacion);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// BOTON DE PUNTUACIONES
		ImageIcon iconoNormal5 = new ImageIcon(".\\imagenes utilizadas\\botonpuntuaciones.png");
		ImageIcon iconoPresionado5 = new ImageIcon(".\\imagenes utilizadas\\botonpuntuaciones2.png");
		ImageIcon iconoSobre5 = new ImageIcon(".\\imagenes utilizadas\\botonpuntuaciones2.png");
		JButton puntuaciones = new JButton(iconoNormal5);
		puntuaciones.setBorder(null);
		puntuaciones.setOpaque(true);
		puntuaciones.setBackground(new Color(0,0,0,0));
		puntuaciones.setBounds(100, 500, iconoNormal5.getIconWidth(), iconoNormal5.getIconHeight());		
		puntuaciones.setIcon(iconoNormal5);
		puntuaciones.setPressedIcon(iconoPresionado5);
		puntuaciones.setRolloverIcon(iconoSobre5);	
		puntuaciones.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	puntuaciones.setIcon(iconoPresionado5);
		    	puntuaciones.setBorderPainted(false);
		    	puntuaciones.setContentAreaFilled(false);
		    }

		    public void mouseEntered(MouseEvent e) {
		    	puntuaciones.setIcon(iconoSobre5);
		    	puntuaciones.setBorderPainted(false);
		    	puntuaciones.setContentAreaFilled(false);
		    }

		    public void mouseExited(MouseEvent e) {
		    	puntuaciones.setIcon(iconoNormal5);
		    	puntuaciones.setBorderPainted(false);
		    	puntuaciones.setContentAreaFilled(false);
		    }
		});
		puntuaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				// Crea una instancia del nuevo panel y lo muestra
				Puntuaciones panelPuntuaciones = new Puntuaciones();
				panelPuntuaciones.setVisible(true);
			}
		});
		getContentPane().add(puntuaciones);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// BOTON SALIR
		ImageIcon iconoNormal6 = new ImageIcon(".\\imagenes utilizadas\\botonsalir.png");
		ImageIcon iconoPresionado6 = new ImageIcon(".\\imagenes utilizadas\\botonsalir2.png");
		ImageIcon iconoSobre6 = new ImageIcon(".\\imagenes utilizadas\\botonsalir2.png");
		JButton botonSalir = new JButton(iconoNormal6);
		botonSalir.setBorder(null);
		botonSalir.setOpaque(true);
		botonSalir.setBackground(new Color(0,0,0,0));
		botonSalir.setBounds(525, 582, iconoNormal6.getIconWidth(), iconoNormal6.getIconHeight());
		botonSalir.setIcon(iconoNormal6);
		botonSalir.setPressedIcon(iconoPresionado6);
		botonSalir.setRolloverIcon(iconoSobre6);
		botonSalir.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	botonSalir.setIcon(iconoPresionado6);
		    	botonSalir.setBorderPainted(false);
		    	botonSalir.setContentAreaFilled(false);
		    }

		    public void mouseEntered(MouseEvent e) {
		    	botonSalir.setIcon(iconoSobre6);
		    	botonSalir.setBorderPainted(false);
		    	botonSalir.setContentAreaFilled(false);
		    }

		    public void mouseExited(MouseEvent e) {
		    	botonSalir.setIcon(iconoNormal6);
		    	botonSalir.setBorderPainted(false);
		    	botonSalir.setContentAreaFilled(false);
		    }
		});
	
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				dispose(); // EN ESTE CASO EN VEZ DE ABRIR UN NUEVO PANEL CIERRA EL PANEL EN EL QUE ESTAMOS
			}
		});
		getContentPane().add(botonSalir);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// BOTON VERSION
		
		ImageIcon iconoNormal7 = new ImageIcon(".\\imagenes utilizadas\\botonversion.png");
		ImageIcon iconoPresionado7 = new ImageIcon(".\\imagenes utilizadas\\botonversion2.png");
		ImageIcon iconoSobre7 = new ImageIcon(".\\imagenes utilizadas\\botonversion2.png");

		JButton version = new JButton(iconoNormal7);
		version.setBorder(null);
		version.setOpaque(true);
		version.setBackground(new Color(0,0,0,0));
		version.setBounds(845, 500, iconoNormal7.getIconWidth(), iconoNormal7.getIconHeight());
		version.setIcon(iconoNormal7);
		version.setPressedIcon(iconoPresionado7);
		version.setRolloverIcon(iconoSobre7);
		version.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	version.setIcon(iconoPresionado7);
		    	version.setBorderPainted(false);
		    	version.setContentAreaFilled(false);
		    }

		    public void mouseEntered(MouseEvent e) {
		    	version.setIcon(iconoSobre7);
		    	version.setBorderPainted(false);
		    	version.setContentAreaFilled(false);
		    }

		    public void mouseExited(MouseEvent e) {
		    	version.setIcon(iconoNormal7);
		    	version.setBorderPainted(false);
		    	version.setContentAreaFilled(false);
		    }
		});
	
		getContentPane().add(version);
		version.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Crea una instancia del nuevo panel y lo muestra
				Audio.sonidoBoton();
				PanelVersion panelVersion = new PanelVersion();
				panelVersion.setVisible(true);
				
			}
		});

		JLabel labelDeFondo = new JLabel(imagenDeFondo); // CREAMOS UN LABEL PARA LA IMAGEN DE FONDO
		labelDeFondo.setBackground(Color.GREEN); // EL COLOR DEL BORDE DEL PANEL EN VERDE
		labelDeFondo.setBounds(0, 0, 1184, 671); // UBICACION Y TAMAÑO DEL LABEL DEL FONDO
		getContentPane().add(labelDeFondo); // LO AÑADIMOS AL PANEL

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	}





		
	public void actionPerformed(ActionEvent e) {
		// SI ESTO NO ESTA CREADO SALE ERROR EN EL EL PUBLIC VOID DEL MENU AUNQUE SE
		// INICIARA IGUAL PERO PORSIACASO SE DEJA CREADO. :)

	}
}
