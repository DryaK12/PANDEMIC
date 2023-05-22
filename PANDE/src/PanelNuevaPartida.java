import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ToolTipManager;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;



import javax.swing.JOptionPane;

public class PanelNuevaPartida extends JFrame implements ActionListener {
	private BufferedWriter bufferW;
	private FileWriter fileW;
	private String ciudades, distanciaCiudades;
	private String frase;
	private String[] ciudadesColindantes, coordenadasXY, datos, enfermedadesNum;
	private JButton ciudadess;
	private JFrame panel;
	private JButton botonSeleccionado;
	private HashMap<JButton, Integer> contadores = new HashMap<>();
	public String ciudadSeleccionada; // Variable para almacenar el nombre de la ciudad seleccionada
    public int brotesPartida =0;
	public int acciones = 4;
	public int rondas = 0;
	public int id_jugador;
	public int enfermedadesfinal = 50;
	public int infecciones1 = 8;
	public int infecciones2 = 4;
	public int brotestotal = 12;
	public boolean partida = true;
	public int idciudad;
	public int InfeccionCiudad = 0;
	public int infeccionesTotal = 0;
	public int contadorVacunas = 0;
	public Boolean vacunaGamma = false;
	public Boolean vacunaBeta = false;
	public Boolean vacunaAlfa = false;
	public Boolean vacunaDelta = false;
	public int numvacuna;
	public int brotesactuales;
	public String nombreArchivo = ".\\src\\sql.txt";
	public int[][] cordenadasXY = new int[55][3];
	public int brotes;
	public JTextArea textArea;
	public int Brotes=0;
	public String[][] ciudades1 = new String[49][5];
	

	public PanelNuevaPartida(int id_jugador, int idpartida) {

		int[] vacunas = new int[5];


		borrarContenidoArchivo(nombreArchivo);
		 cargarvacuna(idpartida,vacunas);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 400, 300, 300);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setViewportBorder(null);

		JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
		scrollBar.setOpaque(false);
		scrollBar.setBackground(new Color(0, 0, 0, 0));
// Crear una instancia de ScrollBarUI
		ScrollBarUI ui = new BasicScrollBarUI() {
// Sobrescribir el método paintTrack para hacer el fondo de la scrollbar
// transparente
			protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
				g.setColor(new Color(0, 0, 0, 0));
				g.fillRect(r.x, r.y, r.width, r.height);
			}

// Sobrescribir el método paintThumb para establecer el color y la opacidad de
// la barra de desplazamiento
			protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				Color color = new Color(255, 0, 0, 128);
				g2.setPaint(color);
				g2.fillRect(r.x, r.y, r.width, r.height);

				g2.dispose();
			}

// Sobrescribir el método paintTrackBorder para hacer el borde de la scrollbar
// transparente
			protected void paintTrackBorder(Graphics g, JComponent c, Rectangle r) {
				g.setColor(new Color(0, 0, 0, 0));
				g.fillRect(r.x, r.y, r.width, r.height);
			}
		};

		scrollBar.setUI(ui);
		getContentPane().add(scrollPane);

		textArea = new JTextArea();
		textArea.setBounds(40, 400, 300, 300);
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Arial", Font.BOLD, 15));
		textArea.setForeground(Color.WHITE);
		textArea.setOpaque(false);
		

		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("PANDEMIC");
		setResizable(true);
		setBounds(0, 0, 1550, 1200);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\imagenes utilizadas\\pandemic2.png"));
		ImageIcon imagenDeFondo = new ImageIcon(".\\imagenes utilizadas\\mapa.jpg");
		ImageIcon iconoRojo0 = new ImageIcon(".\\imagenes utilizadas\\rojo0.png");
		ImageIcon iconoAmarillo0 = new ImageIcon(".\\imagenes utilizadas\\amarillo0.png");
		ImageIcon iconoVerde0 = new ImageIcon(".\\imagenes utilizadas\\verde0.png");
		ImageIcon iconoAzul0 = new ImageIcon(".\\imagenes utilizadas\\azul0.png");
		ImageIcon icono0 = new ImageIcon(".\\imagenes utilizadas\\icono0.png");

		ciudades = ".\\archivos\\ciudades.txt";
		distanciaCiudades = ".\\archivos\\ciudadesRedactadas.txt";
		int contadorciudades = 0;

		try {
			fileW = new FileWriter(distanciaCiudades, true);
			bufferW = new BufferedWriter(fileW);
		} catch (IOException xd) {
			System.out.println("ERROR AL ESCRIBIR EN EL ARCHIVO");
			xd.printStackTrace();
		}

		try {

// LEEMOS EL ARCHIVO DE TXT
			FileReader fileR = new FileReader(ciudades);
			BufferedReader bufferR = new BufferedReader(fileR);
// datos[0] = nombre ciudad coordenadasXY[0] = coordenadas x coordenadasXY[1] =
// coordenadas y
			while ((frase = bufferR.readLine()) != null) {
// SEPARAMOS LAS FRASES DEL ARCHIVO DE TXT Y GUARDAMOS DATOS NECESARIOS EN LAS
// VARIABLES

				datos = frase.split(";");

				coordenadasXY = datos[2].split(",");
				enfermedadesNum = datos[1].split(";");
				ciudadesColindantes = datos[3].split(",");
				int enfermidad = Integer.parseInt(datos[1]);
				int posicionX = Integer.parseInt(coordenadasXY[0]);
				int posicionY = Integer.parseInt(coordenadasXY[1]);
				cordenadasXY[contadorciudades][0] = posicionX;
				cordenadasXY[contadorciudades][1] = posicionY;
				ciudades1[contadorciudades][4] = String.valueOf(enfermidad);
				ciudades1[contadorciudades][1] = datos[0];
				ciudades1[contadorciudades][3]= "0";
				

				
				
				contadorciudades++;
////////////////////////////////////////////////////////////////////////////////
			}
			actualizacionciudades(idpartida, ciudades1);
			rondas = consultarrondas1(idpartida, id_jugador);

			infeccionesTotal = consultarenfermedadesactual1(idpartida);

			brotesactuales = brotesactuales1(idpartida, id_jugador);

			brotes = brotesactuales;

			if (rondas <= 0) {
				int i = 0;

				while (i != 8) {
					i++;
					infectar(rondas, idpartida,ciudades1,nombreArchivo);
					infeccionesTotal++;
				}
				brotesactuales(idpartida, id_jugador);

			}
			
			
			
			actualizabotones(idpartida,  cordenadasXY);
			textArea.append("RONDA = " + rondas + "\n" + "ACCIONES = " + acciones + "\n" + "INFECCIONES =  "
					+ infeccionesTotal + "\n " + "brotesactuales = " + brotesactuales + "\n");
// CERRAMOS
			fileR.close();
			bufferR.close();

		} catch (IOException xd) {
			System.out.println("Error al leer el fichero.");
			xd.printStackTrace();
		}
		ImageIcon curartodo = new ImageIcon(".\\imagenes utilizadas\\botoncurartodo.jpg");
		JButton BotonVictoria = new JButton("");
		BotonVictoria.setBorder(null);
		BotonVictoria.setOpaque(true);
		BotonVictoria.setBackground(new Color(0, 0, 0, 0));
		BotonVictoria.setBounds(50, 50, curartodo.getIconWidth(), curartodo.getIconHeight());

		BotonVictoria.setIcon(curartodo);
		BotonVictoria.setPressedIcon(curartodo);
		BotonVictoria.setRolloverIcon(curartodo);
		BotonVictoria.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (contadorVacunas==4) {
				ejecutarSentenciasSQL(nombreArchivo);

				Audio.sonidoBoton();
				dispose();
				Victoria Victoria = new Victoria(idpartida); 
				Victoria.setVisible(true);
			}
		}
		});
		getContentPane().add(BotonVictoria);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton BotonRojo = new JButton("");
		BotonRojo.setEnabled(false);
		BotonRojo.setBackground(Color.RED);
		BotonRojo.setForeground(Color.WHITE);
		BotonRojo.setBounds(1160, 860, 40, 40);
		BotonRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				if (botonSeleccionado != null) {
					desinfectarvacuna(idciudad, idpartida,ciudades1,nombreArchivo);
				}
				if (acciones > 1) {
					acciones = acciones - 1;
					infeccionesTotal = infeccionesTotal - 4;
					
				} 	if (acciones <= 0 ){
					
					acciones = 4;
					infeccionesTotal = infeccionesTotal + 4;
					rondas = rondas + 1;
					int contador = 0;
					guardarrondas(id_jugador, idpartida, rondas);
					while (contador != 4) {
						contador++;
						infectar(rondas, idpartida,ciudades1,nombreArchivo);

					}
					actualizabotones(idpartida, cordenadasXY);
					textArea.append("RONDA = " + rondas + "\n" + "ACCIONES = " + acciones + "\n" + "INFECCIONES =  "
							+ infeccionesTotal + "\n " + "BROTES ACTUALES = " + brotesPartida + "\n");
				}
				if (infeccionesTotal >= enfermedadesfinal || brotestotal == brotesPartida) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Derrota Derrota = new Derrota(idpartida);
					Derrota.setVisible(true);
				}
				if (vacunaDelta == true && vacunaGamma == true && vacunaBeta == true && vacunaAlfa == true) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Victoria Victoria = new Victoria(idpartida);
					Victoria.setVisible(true);
				}
			}
		});
		getContentPane().add(BotonRojo);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton BotonVerde = new JButton("");
		BotonVerde.setEnabled(false);
		BotonVerde.setBackground(Color.GREEN);
		BotonVerde.setForeground(Color.WHITE);
		BotonVerde.setBounds(1160, 955, 40, 40);
		BotonVerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				if (botonSeleccionado != null) {
					desinfectarvacuna(idciudad, idpartida,ciudades1,nombreArchivo);
				}
				if (acciones > 1) {
					acciones = acciones - 1;
					infeccionesTotal = infeccionesTotal - 4;
					
				} 	if (acciones <= 0 ){
					
					acciones = 4;
					infeccionesTotal = infeccionesTotal + 4;
					rondas = rondas + 1;
					int contador = 0;
					guardarrondas(id_jugador, idpartida, rondas);
					while (contador != 4) {
						contador++;
						infectar(rondas, idpartida,ciudades1,nombreArchivo);

					}
					actualizabotones(idpartida, cordenadasXY);
					textArea.append("RONDA = " + rondas + "\n" + "ACCIONES = " + acciones + "\n" + "INFECCIONES =  "
							+ infeccionesTotal + "\n " + "BROTES ACTUALES = " + brotesPartida + "\n");
				}
				if (infeccionesTotal >= enfermedadesfinal || brotestotal == brotesPartida) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Derrota Derrota = new Derrota(idpartida);
					Derrota.setVisible(true);
				}
				if (vacunaDelta == true && vacunaGamma == true && vacunaBeta == true && vacunaAlfa == true) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Victoria Victoria = new Victoria(idpartida);
					Victoria.setVisible(true);
				}
			}

		});
		getContentPane().add(BotonVerde);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JButton BotonAmarillo = new JButton("");
		BotonAmarillo.setEnabled(false);
		BotonAmarillo.setBackground(Color.YELLOW);
		BotonAmarillo.setForeground(Color.WHITE);
		BotonAmarillo.setBounds(840, 955, 40, 40);
		BotonAmarillo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				if (botonSeleccionado != null) {
					desinfectarvacuna(idciudad, idpartida,ciudades1,nombreArchivo);
				}
				if (acciones > 1) {
					acciones = acciones - 1;
					infeccionesTotal = infeccionesTotal - 4;
					
				} 	if (acciones <= 0 ){
					
					acciones = 4;
					infeccionesTotal = infeccionesTotal + 4;
					rondas = rondas + 1;
					int contador = 0;
					guardarrondas(id_jugador, idpartida, rondas);
					while (contador != 4) {
						contador++;
						infectar(rondas, idpartida,ciudades1,nombreArchivo);

					}
					actualizabotones(idpartida, cordenadasXY);
					textArea.append("RONDA = " + rondas + "\n" + "ACCIONES = " + acciones + "\n" + "INFECCIONES =  "
							+ infeccionesTotal + "\n " + "BROTES ACTUALES = " + brotesPartida + "\n");
				}
				if (infeccionesTotal >= enfermedadesfinal || brotestotal == brotesPartida) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Derrota Derrota = new Derrota(idpartida);
					Derrota.setVisible(true);
				}
				if (vacunaDelta == true && vacunaGamma == true && vacunaBeta == true && vacunaAlfa == true) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Victoria Victoria = new Victoria(idpartida);
					Victoria.setVisible(true);
				}
			}

		});
		getContentPane().add(BotonAmarillo);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JButton BotonAzul = new JButton("");
		BotonAzul.setEnabled(false);
		BotonAzul.setBackground(Color.BLUE);
		BotonAzul.setForeground(Color.WHITE);
		BotonAzul.setBounds(840, 860, 40, 40);
		BotonAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				if (botonSeleccionado != null) {
					desinfectarvacuna(idciudad, idpartida,ciudades1,nombreArchivo);
				}
				if (acciones > 1) {
					acciones = acciones - 1;
					infeccionesTotal = infeccionesTotal - 4;
					
				} 	if (acciones <= 0 ){
					
					acciones = 4;
					infeccionesTotal = infeccionesTotal + 4;
					rondas = rondas + 1;
					int contador = 0;
					guardarrondas(id_jugador, idpartida, rondas);
					while (contador != 4) {
						contador++;
						infectar(rondas, idpartida,ciudades1,nombreArchivo);

					}
					actualizabotones(idpartida, cordenadasXY);
					textArea.append("RONDA = " + rondas + "\n" + "ACCIONES = " + acciones + "\n" + "INFECCIONES =  "
							+ infeccionesTotal + "\n " + "BROTES ACTUALES = " + brotesPartida + "\n");
				}
				if (infeccionesTotal >= enfermedadesfinal || brotestotal == brotesPartida) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Derrota Derrota = new Derrota(idpartida);
					Derrota.setVisible(true);
				}
				if (vacunaDelta == true && vacunaGamma == true && vacunaBeta == true && vacunaAlfa == true) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Victoria Victoria = new Victoria(idpartida);
					Victoria.setVisible(true);
				}
			}

		});
		getContentPane().add(BotonAzul);

///////////////////////////////////////////////////////////////////////////////////////////////////////////
		numvacuna = 1;
		int valorActual = vacunas[numvacuna];
		ImageIcon imagenDeFondo2 = new ImageIcon(".\\imagenes utilizadas\\fondobotonesmapa.jpg");
		JProgressBar barraAlfa = new JProgressBar(0, 100);
		barraAlfa.setBounds(900, 860, 200, 25);
		barraAlfa.setForeground(Color.BLUE);
		barraAlfa.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		barraAlfa.setOpaque(false);
		barraAlfa.setBackground(null);
		barraAlfa.setValue(valorActual);
		getContentPane().add(barraAlfa);
		ImageIcon iconoNormal1 = new ImageIcon(".\\imagenes utilizadas\\botonalfa.png");
		ImageIcon iconoPresionado1 = new ImageIcon(".\\imagenes utilizadas\\botonalfa2.png");
		ImageIcon iconoSobre1 = new ImageIcon(".\\imagenes utilizadas\\botonalfa2.png");
		JButton botonAlfa = new JButton(iconoNormal1); // NOMBRE DEL BOTON MAS LA CREACION
		botonAlfa.setBorder(null);
		botonAlfa.setOpaque(true);
		botonAlfa.setBackground(new Color(0, 0, 0, 0));
		botonAlfa.setBounds(935, 890, iconoNormal1.getIconWidth(), iconoNormal1.getIconHeight());
		botonAlfa.setIcon(iconoNormal1);
		botonAlfa.setPressedIcon(iconoPresionado1);
		botonAlfa.setRolloverIcon(iconoSobre1);
		botonAlfa.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				botonAlfa.setIcon(iconoPresionado1);
				botonAlfa.setBorderPainted(false);
				botonAlfa.setContentAreaFilled(false);
			}

			public void mouseEntered(MouseEvent e) {
				botonAlfa.setIcon(iconoSobre1);
				botonAlfa.setBorderPainted(false);
				botonAlfa.setContentAreaFilled(false);
			}

			public void mouseExited(MouseEvent e) {
				botonAlfa.setIcon(iconoNormal1);
				botonAlfa.setBorderPainted(false);
				botonAlfa.setContentAreaFilled(false);
			}
		});

		botonAlfa.addActionListener(this);
		getContentPane().add(botonAlfa);

		botonAlfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				numvacuna = 1;

				int valorActual = vacunas[numvacuna];
				if (valorActual < 100) {
					barraAlfa.setValue(valorActual + 25);
					vacunas[numvacuna] = barraAlfa.getValue();
					actualizarvacuna(vacunas, numvacuna, idpartida);

				} else {
//AQUI PONES LA FUNCION PARA GUARDAR LOS DATOS DE LAS 
					contadorVacunas++;
					BotonAzul.setEnabled(true);
					botonAlfa.setEnabled(false);

				}

				if (acciones > 1) {
					acciones = acciones - 4;
					
				} 	if (acciones <= 0 ){
					
					acciones = 4;
					infeccionesTotal = infeccionesTotal + 4;
					rondas = rondas + 1;
					int contador = 0;
					guardarrondas(id_jugador, idpartida, rondas);
					while (contador != 4) {
						contador++;
						infectar(rondas, idpartida,ciudades1,nombreArchivo);

					}
					actualizabotones(idpartida, cordenadasXY);
					textArea.append("RONDA = " + rondas + "\n" + "ACCIONES = " + acciones + "\n" + "INFECCIONES =  "
							+ infeccionesTotal + "\n " + "BROTES ACTUALES = " + brotesPartida + "\n");
				}
				if (infeccionesTotal >= enfermedadesfinal || brotestotal == brotesPartida) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Derrota Derrota = new Derrota(idpartida);
					Derrota.setVisible(true);
				}
				if (vacunaDelta == true && vacunaGamma == true && vacunaBeta == true && vacunaAlfa == true) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Victoria Victoria = new Victoria(idpartida);
					Victoria.setVisible(true);
				}
			}
		});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		numvacuna = 2;
		valorActual = vacunas[numvacuna];
		JProgressBar barraBeta = new JProgressBar(0, 100);
		barraBeta.setBounds(1220, 860, 200, 25);
		barraBeta.setForeground(Color.RED);
		barraBeta.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		barraBeta.setOpaque(false);
		barraBeta.setBackground(null);
		barraBeta.setValue(valorActual);
		getContentPane().add(barraBeta);

		ImageIcon iconoNormal2 = new ImageIcon(".\\imagenes utilizadas\\botonbeta.png");
		ImageIcon iconoPresionado2 = new ImageIcon(".\\imagenes utilizadas\\botonbeta2.png");
		ImageIcon iconoSobre2 = new ImageIcon(".\\imagenes utilizadas\\botonbeta2.png");
		JButton botonBeta = new JButton(iconoNormal1); // NOMBRE DEL BOTON MAS LA CREACION
		botonBeta.setBorder(null);
		botonBeta.setOpaque(true);
		botonBeta.setBackground(new Color(0, 0, 0, 0));
		botonBeta.setBounds(1220, 995, iconoNormal2.getIconWidth(), iconoNormal2.getIconHeight());
		botonBeta.setIcon(iconoNormal2);
		botonBeta.setPressedIcon(iconoPresionado2);
		botonBeta.setRolloverIcon(iconoSobre2);
		botonBeta.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				botonBeta.setIcon(iconoPresionado2);
				botonBeta.setBorderPainted(false);
				botonBeta.setContentAreaFilled(false);
			}

			public void mouseEntered(MouseEvent e) {
				botonBeta.setIcon(iconoSobre2);
				botonBeta.setBorderPainted(false);
				botonBeta.setContentAreaFilled(false);
			}

			public void mouseExited(MouseEvent e) {
				botonBeta.setIcon(iconoNormal2);
				botonBeta.setBorderPainted(false);
				botonBeta.setContentAreaFilled(false);
			}
		});

		botonBeta.addActionListener(this);
		getContentPane().add(botonBeta);

		botonBeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				numvacuna = 2;

				int valorActual = vacunas[numvacuna];
				if (valorActual < 100) {
					barraBeta.setValue(valorActual + 25);
					vacunas[numvacuna] = barraAlfa.getValue();
					actualizarvacuna(vacunas, numvacuna, idpartida);
				} else {
//AQUI PONES LA FUNCION PARA GUARDAR LOS DATOS DE LAS VACUNAS
					contadorVacunas++;
					BotonRojo.setEnabled(true);
					botonBeta.setEnabled(false);

				}
				if (acciones > 1) {
					actualizabotones(idpartida,  cordenadasXY);
					acciones = acciones - 4;
					infeccionesTotal = infeccionesTotal + 4;
					
				} 	if (acciones <= 0 ){
					
					acciones = 4;
					infeccionesTotal = infeccionesTotal + 4;
					rondas = rondas + 1;
					int contador = 0;
					guardarrondas(id_jugador, idpartida, rondas);
					while (contador != 4) {
						contador++;
						infectar(rondas, idpartida,ciudades1,nombreArchivo);

					}
					
					textArea.append("RONDA = " + rondas + "\n" + "ACCIONES = " + acciones + "\n" + "INFECCIONES =  "
							+ infeccionesTotal + "\n " + "BROTES ACTUALES = " + brotesPartida + "\n");
				}
				if (infeccionesTotal >= enfermedadesfinal || brotestotal == brotesPartida) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Derrota Derrota = new Derrota(idpartida);
					Derrota.setVisible(true);
				}
				if (vacunaDelta == true && vacunaGamma == true && vacunaBeta == true && vacunaAlfa == true) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Victoria Victoria = new Victoria(idpartida);
					Victoria.setVisible(true);
				}
			}
		});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		numvacuna = 3;
		valorActual = vacunas[numvacuna];
		JProgressBar barraDelta = new JProgressBar(0, 100);
		barraDelta.setBounds(900, 955, 200, 25);
		barraDelta.setForeground(Color.YELLOW);
		barraDelta.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		barraDelta.setOpaque(false);
		barraDelta.setBackground(null);
		barraDelta.setValue(valorActual);
		getContentPane().add(barraDelta);

		ImageIcon iconoNormal3 = new ImageIcon(".\\imagenes utilizadas\\botondelta.png");
		ImageIcon iconoPresionado3 = new ImageIcon(".\\imagenes utilizadas\\botondelta2.png");
		ImageIcon iconoSobre3 = new ImageIcon(".\\imagenes utilizadas\\botondelta2.png");
		JButton botonDelta = new JButton(iconoNormal3); // NOMBRE DEL BOTON MAS LA CREACION
		botonDelta.setBorder(null);
		botonDelta.setOpaque(true);
		botonDelta.setBackground(new Color(0, 0, 0, 0));
		botonDelta.setBounds(930, 990, iconoNormal3.getIconWidth(), iconoNormal3.getIconHeight());
		botonDelta.setIcon(iconoNormal3);
		botonDelta.setPressedIcon(iconoPresionado3);
		botonDelta.setRolloverIcon(iconoSobre3);
		botonDelta.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				botonDelta.setIcon(iconoPresionado3);
				botonDelta.setBorderPainted(false);
				botonDelta.setContentAreaFilled(false);
			}

			public void mouseEntered(MouseEvent e) {
				botonDelta.setIcon(iconoSobre3);
				botonDelta.setBorderPainted(false);
				botonDelta.setContentAreaFilled(false);
			}

			public void mouseExited(MouseEvent e) {
				botonDelta.setIcon(iconoNormal3);
				botonDelta.setBorderPainted(false);
				botonDelta.setContentAreaFilled(false);
			}
		});

		botonDelta.addActionListener(this);
		getContentPane().add(botonDelta);

		botonDelta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				numvacuna = 3;
				int valorActual = vacunas[numvacuna];
				if (valorActual < 100) {
					barraDelta.setValue(valorActual + 25);
					vacunas[numvacuna] = barraAlfa.getValue();
					actualizarvacuna(vacunas, numvacuna, idpartida);
				} else {
//AQUI PONES LA FUNCION PARA GUARDAR LOS DATOS DE LAS VACUNAS
					contadorVacunas++;
					BotonAmarillo.setEnabled(true);
					botonDelta.setEnabled(false);

				}
				if (acciones > 1) {
					acciones = acciones - 4;
					
				} 	if (acciones <= 0 ){
					
					acciones = 4;
					infeccionesTotal = infeccionesTotal + 4;
					rondas = rondas + 1;
					int contador = 0;
					guardarrondas(id_jugador, idpartida, rondas);
					while (contador != 4) {
						contador++;
						infectar(rondas, idpartida,ciudades1,nombreArchivo);

					}
					actualizabotones(idpartida, cordenadasXY);
					textArea.append("RONDA = " + rondas + "\n" + "ACCIONES = " + acciones + "\n" + "INFECCIONES =  "
							+ infeccionesTotal + "\n " + "BROTES ACTUALES = " + brotesPartida + "\n");
				}
				if (infeccionesTotal >= enfermedadesfinal || brotestotal == brotesPartida) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Derrota Derrota = new Derrota(idpartida);
					Derrota.setVisible(true);
				}
				if (vacunaDelta == true && vacunaGamma == true && vacunaBeta == true && vacunaAlfa == true) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Victoria Victoria = new Victoria(idpartida);
					Victoria.setVisible(true);
				}
			}
		});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		numvacuna = 4;
		valorActual=0;
		valorActual = vacunas[numvacuna];
		JProgressBar barraGama = new JProgressBar(0, 100);
		barraGama.setBounds(1220, 955, 200, 25);
		barraGama.setForeground(Color.GREEN);
		barraGama.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		barraGama.setOpaque(false);
		barraGama.setBackground(null);
		barraGama.setValue(valorActual);
		getContentPane().add(barraGama);
		
		ImageIcon iconoNormal4 = new ImageIcon(".\\imagenes utilizadas\\botongama.png");
		ImageIcon iconoPresionado4 = new ImageIcon(".\\imagenes utilizadas\\botongama2.png");
		ImageIcon iconoSobre4 = new ImageIcon(".\\imagenes utilizadas\\botongama2.png");
		JButton botonGama = new JButton(iconoNormal3); // NOMBRE DEL BOTON MAS LA CREACION
		botonGama.setBorder(null);
		botonGama.setOpaque(true);
		botonGama.setBackground(new Color(0, 0, 0, 0));
		botonGama.setBounds(1255, 990, iconoNormal4.getIconWidth(), iconoNormal4.getIconHeight());
		botonGama.setIcon(iconoNormal4);
		botonGama.setPressedIcon(iconoPresionado4);
		botonGama.setRolloverIcon(iconoSobre4);
		botonGama.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				botonGama.setIcon(iconoPresionado4);
				botonGama.setBorderPainted(false);
				botonGama.setContentAreaFilled(false);
			}

			public void mouseEntered(MouseEvent e) {
				botonGama.setIcon(iconoSobre4);
				botonGama.setBorderPainted(false);
				botonGama.setContentAreaFilled(false);
			}

			public void mouseExited(MouseEvent e) {
				botonGama.setIcon(iconoNormal4);
				botonGama.setBorderPainted(false);
				botonGama.setContentAreaFilled(false);
			}
		});

		botonGama.addActionListener(this);
		getContentPane().add(botonGama);

		botonGama.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				
				int valorActual = vacunas[numvacuna];

				if (valorActual < 100) {

					barraGama.setValue(valorActual + 25);
					vacunas[numvacuna] = barraAlfa.getValue();
					actualizarvacuna(vacunas, numvacuna, idpartida);
				} else {
//AQUI PONES LA FUNCION PARA GUARDAR LOS DATOS DE LAS VACUNAS
					contadorVacunas++;
					BotonVerde.setEnabled(true);
					botonGama.setEnabled(false);

				}
				if (acciones > 1) {
					acciones = acciones - 4;
				
				} 
				if (acciones <= 0 ){
					
					acciones = 4;
					infeccionesTotal = infeccionesTotal + 4;
					rondas = rondas + 1;
					int contador = 0;
					guardarrondas(id_jugador, idpartida, rondas);
					while (contador != 4) {
						contador++;
						infectar(rondas, idpartida,ciudades1,nombreArchivo);

					}
					actualizabotones(idpartida, cordenadasXY);
					textArea.append("RONDA = " + rondas + "\n" + "ACCIONES = " + acciones + "\n" + "INFECCIONES =  "
							+ infeccionesTotal + "\n " + "BROTES ACTUALES = " + brotesPartida + "\n");
				}
				if (infeccionesTotal >= enfermedadesfinal || brotestotal == brotesPartida) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Derrota Derrota = new Derrota(idpartida);
					Derrota.setVisible(true);
				}
				if (vacunaDelta == true && vacunaGamma == true && vacunaBeta == true && vacunaAlfa == true) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Victoria Victoria = new Victoria(idpartida);
					Victoria.setVisible(true);
				}
			}
		});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ImageIcon iconoNormal5 = new ImageIcon(".\\imagenes utilizadas\\curarenfemredad.png");
		ImageIcon iconoPresionado5 = new ImageIcon(".\\imagenes utilizadas\\curarenfermedad2.png");
		ImageIcon iconoSobre5 = new ImageIcon(".\\imagenes utilizadas\\curarenfermedad2.png");
		JButton botonCurarEnfermedad = new JButton(iconoNormal5); // NOMBRE DEL BOTON MAS LA CREACION
		botonCurarEnfermedad.setBorder(null);
		botonCurarEnfermedad.setOpaque(true);
		botonCurarEnfermedad.setBackground(new Color(0, 0, 0, 0));
		botonCurarEnfermedad.setBounds(350, 870, iconoNormal5.getIconWidth(), iconoNormal5.getIconHeight());
		botonCurarEnfermedad.setIcon(iconoNormal5);
		botonCurarEnfermedad.setPressedIcon(iconoPresionado5);
		botonCurarEnfermedad.setRolloverIcon(iconoSobre5);
		botonCurarEnfermedad.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				botonCurarEnfermedad.setIcon(iconoPresionado5);
				botonCurarEnfermedad.setBorderPainted(false);
				botonCurarEnfermedad.setContentAreaFilled(false);

			}

			public void mouseEntered(MouseEvent e) {
				botonCurarEnfermedad.setIcon(iconoSobre5);
				botonCurarEnfermedad.setBorderPainted(false);
				botonCurarEnfermedad.setContentAreaFilled(false);
			}

			public void mouseExited(MouseEvent e) {
				botonCurarEnfermedad.setIcon(iconoNormal5);
				botonCurarEnfermedad.setBorderPainted(false);
				botonCurarEnfermedad.setContentAreaFilled(false);
			}
		});

		botonCurarEnfermedad.addActionListener(this);
		getContentPane().add(botonCurarEnfermedad);

		botonCurarEnfermedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int j=0;
			for (int i=0;i<49;i++) {
				
				if(ciudades1[i][0].equals(String.valueOf(idciudad))) {
				
			j=i;		
			}
			}
			if(ciudades1[j][2].equals("0")) {
				
				textArea.append("La ciudad no está infectada aún...buscate otra");			} 
			
			else{
				
				
				Audio.sonidoBoton();
				desinfectar(idciudad, idpartida,ciudades1,nombreArchivo);
				if (acciones > 1) {
					infeccionesTotal = infeccionesTotal - 1;
					acciones = acciones - 1;
					
				} else {
					

					for (int i = 0; i < 49; i++) {
					
						Brotes = Integer.parseInt(ciudades1[i][3]);
						if ( Brotes >= 1) {
							brotesPartida++;
						}
					}
					acciones = 4;
					infeccionesTotal = infeccionesTotal + 4;
					rondas = rondas + 1;
					int contador = 0;
					guardarrondas(id_jugador, idpartida, rondas);
					while (contador != 4) {
						contador++;
						infectar(rondas, idpartida,ciudades1,nombreArchivo);

					}
					actualizabotones(idpartida, cordenadasXY);
					textArea.append("RONDA = " + rondas + "\n" + "ACCIONES = " + acciones + "\n" + "INFECCIONES =  "
							+ infeccionesTotal + "\n " + "BROTES ACTUALES = " + brotesPartida + "\n");
				}
				if (infeccionesTotal >= enfermedadesfinal || brotestotal == brotesPartida) {
					ejecutarSentenciasSQL(nombreArchivo);

					dispose();
					Derrota Derrota = new Derrota(idpartida);
					Derrota.setVisible(true);
				}
			}}
		});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		ImageIcon iconoNormal7 = new ImageIcon(".\\imagenes utilizadas\\botonguardarpartida.png");
		ImageIcon iconoPresionado7 = new ImageIcon(".\\imagenes utilizadas\\botonguardarpartida.png");
		ImageIcon iconoSobre7 = new ImageIcon(".\\imagenes utilizadas\\botonguardarpartida.png");
		JButton botonGuardarPartida = new JButton(iconoNormal7); // NOMBRE DEL BOTON MAS LA CREACION
		botonGuardarPartida.setBorder(null);
		botonGuardarPartida.setOpaque(true);
		botonGuardarPartida.setBackground(new Color(0, 0, 0, 0));
		botonGuardarPartida.setBounds(80, 968, iconoNormal7.getIconWidth(), iconoNormal7.getIconHeight());
		botonGuardarPartida.setIcon(iconoNormal7);
		botonGuardarPartida.setPressedIcon(iconoPresionado7);
		botonGuardarPartida.setRolloverIcon(iconoSobre7);
		botonGuardarPartida.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				botonGuardarPartida.setIcon(iconoPresionado7);
				botonGuardarPartida.setBorderPainted(false);
				botonGuardarPartida.setContentAreaFilled(false);
			}

			public void mouseEntered(MouseEvent e) {
				botonGuardarPartida.setIcon(iconoSobre7);
				botonGuardarPartida.setBorderPainted(false);
				botonGuardarPartida.setContentAreaFilled(false);
			}

			public void mouseExited(MouseEvent e) {
				botonGuardarPartida.setIcon(iconoNormal7);
				botonGuardarPartida.setBorderPainted(false);
				botonGuardarPartida.setContentAreaFilled(false);
			}
		});

		botonGuardarPartida.addActionListener(this);
		getContentPane().add(botonGuardarPartida);

		botonGuardarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				ejecutarSentenciasSQL(nombreArchivo);
			}

		});

///////////////////////////////////////////////////////////////////////////////////////////////////////
		ImageIcon iconoVolver = new ImageIcon(".\\imagenes utilizadas\\botonvolver.png");
 		ImageIcon iconoPresionadoVolver = new ImageIcon(".\\imagenes utilizadas\\botonvolver2.png");
 		ImageIcon iconoSobreVolver = new ImageIcon(".\\imagenes utilizadas\\botonvolver2.png");
 		JButton botonVolver = new JButton(iconoVolver); // NOMBRE DEL BOTON MAS LA CREACION		
 		botonVolver.setBorder(null);
 		botonVolver.setOpaque(true);
 		botonVolver.setBackground(new Color(0,0,0,0));
 		botonVolver.setBounds(170, 500, iconoVolver.getIconWidth(), iconoVolver.getIconHeight());
 		botonVolver.setIcon(iconoVolver);
 		botonVolver.setPressedIcon(iconoPresionadoVolver);
 		botonVolver.setRolloverIcon(iconoSobreVolver);
 		botonVolver.addMouseListener(new MouseAdapter() {
 		    public void mousePressed(MouseEvent e) {
 		    	botonVolver.setIcon(iconoPresionado2);
 		    	botonVolver.setBorderPainted(false);
 		    	botonVolver.setContentAreaFilled(false);
 		    }

 		    public void mouseEntered(MouseEvent e) {
 		    	botonVolver.setIcon(iconoSobreVolver);
 		    	botonVolver.setBorderPainted(false);
 		    	botonVolver.setContentAreaFilled(false);
 		    }

 		    public void mouseExited(MouseEvent e) {
 		    	botonVolver.setIcon(iconoVolver);
 		    	botonVolver.setBorderPainted(false);
 		    	botonVolver.setContentAreaFilled(false);
 		    }
 		});
 							
 		botonVolver.addActionListener(this);
         getContentPane().add(botonVolver);
    
         botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				 dispose();
				
				 Menu Menu = new Menu(id_jugador); 
		            Menu.setVisible(true);
			}
		});
///////////////////////////////////////////////////////////////////////////////////////////////////////
		JLabel labelDeFondo = new JLabel(imagenDeFondo);
		labelDeFondo.setBounds(0, 0, 1550, 850);
		getContentPane().add(labelDeFondo);
		JLabel labelDeFondo2 = new JLabel(imagenDeFondo2);
		labelDeFondo2.setBounds(0, 850, 1550, 300);
		getContentPane().add(labelDeFondo2);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
// TODO Auto-generated method stub

	}

	public void consultaridciudad(String nomciudad) {

		String sql = "Select  idciudad from ciudad where nombreciudad='" + nomciudad + "'";

		escribirEnArchivo(nombreArchivo, sql);

	}

	public  void infectar(int rondas, int idpartida,String ciudades[][],String nombreArchivo) {

		Random rand = new Random();

		int idciudad = 0;
		do {
			idciudad = rand.nextInt(51) + 1;
		} while (idciudad == 8 || idciudad == 2||idciudad==33);
		int idenfermedad = 0;
		for (int i = 0; i < 49; i++) {

			if (ciudades[i][0].equals(String.valueOf(idciudad)) ) {

				if (Integer.parseInt(ciudades[i][2]) <= 4) {
					int numeroactual = 0;
					numeroactual = Integer.parseInt(ciudades[i][2]);
					numeroactual = numeroactual + 1;
					ciudades[i][2] = String.valueOf(numeroactual);
					System.out.println("funcion de infectar :" +ciudades[i][2]);


				}
				if (Integer.parseInt(ciudades[i][2]) == 4) {

					int numeroactual = 0;
					numeroactual = Integer.parseInt(ciudades[i][3]);
					numeroactual = numeroactual + 1;
					ciudades[i][3] = String.valueOf(numeroactual);
				}

			}

		}
		idenfermedad = rand.nextInt(3 ) + 0;

		String sql = "INSERT INTO infecciones (idinfeccion, idpartida, idciudad, idenfermedad) VALUES (seq_infeccion.NEXTVAL, "
				+ idpartida + ", " + idciudad + ", " + idenfermedad + ")";

		escribirEnArchivo(nombreArchivo, sql);

	}

	public  void desinfectar(int idciudad, int idpartida,String ciudades[][],String nombreArchivo) {

		String sql = "INSERT INTO desinfecciones (iddesinfecciones, idpartida, idciudad, idenfermedad, idinfeccion) VALUES (secuencia_desinfecciones.NEXTVAL, "
				+ idpartida + " , " + idciudad + ", (SELECT idenfermedad FROM infecciones WHERE idpartida = "
				+ idpartida + " AND idciudad = " + idciudad
				+ " AND ROWNUM = 1), (SELECT idinfeccion FROM infecciones WHERE idpartida = " + idpartida
				+ " AND idciudad = " + idciudad + " AND ROWNUM = 1))";
		escribirEnArchivo(nombreArchivo, sql);

		for (int i = 0; i < 49; i++) {

			if (ciudades[i][0].equals(String.valueOf(idciudad)) ) {

				if (Integer.parseInt(ciudades[i][2]) <= 4) {
					int numeroactual = 0;
					numeroactual = Integer.parseInt(ciudades[i][2]);
					numeroactual = numeroactual - 1;
					ciudades[i][2] = String.valueOf(numeroactual);


				}
				if (Integer.parseInt(ciudades[i][2]) == 0) {

					int numeroactual = 0;
					numeroactual = Integer.parseInt(ciudades[i][3]);
					numeroactual = numeroactual - 1;
					ciudades[i][3] = String.valueOf(numeroactual);
				}

			}

		}

	}

	public void desinfectarvacuna(int idciudad, int idpartida,String ciudades[][],String nombreArchivo) {

		String sql = "INSERT INTO desinfecciones (iddesinfecciones, idpartida, idciudad, idenfermedad, idinfeccion) SELECT secuencia_desinfecciones.NEXTVAL, i.idpartida, i.idciudad, i.idenfermedad, i.idinfeccion FROM infecciones i WHERE i.idpartida = "
				+ idpartida + " AND i.idciudad = " + idciudad + "";

		escribirEnArchivo(nombreArchivo, sql);
		for (int i = 0; i < 49; i++) {

			if (ciudades[i][0].equals(String.valueOf(idciudad)) ) {
				if (Integer.parseInt(ciudades[i][2]) <= 4) {
					int numeroactual = 0;

					ciudades[i][2] = String.valueOf(numeroactual);

				}
				if (Integer.parseInt(ciudades[i][2]) == 0) {

					int numeroactual = 0;
					numeroactual = Integer.parseInt(ciudades[i][3]);
					numeroactual = numeroactual - 1;
					ciudades[i][3] = String.valueOf(numeroactual);
				}

			}

		}

	}

	public void consultarciudadactual(int idciudad, int idpartida) {

		String sql = "SELECT idciudad, idpartida, COUNT(*) - (SELECT COUNT(*) FROM desinfecciones WHERE idciudad = "
				+ idciudad + " AND idpartida = " + idpartida + ") FROM infecciones WHERE idciudad = " + idciudad
				+ " AND idpartida = " + idpartida + " GROUP BY idciudad, idpartida";

		escribirEnArchivo(nombreArchivo, sql);

	}

	public void consultarenfermedadesactual(int idpartida) {

		String sql = "SELECT  idpartida, COUNT(*) - (SELECT COUNT(*) FROM desinfecciones WHERE idpartida = " + idpartida
				+ ") FROM infecciones WHERE idpartida = " + idpartida + " GROUP BY  idpartida";

		escribirEnArchivo(nombreArchivo, sql);

	}

	public void consultarrondas(int idpartida, int id_jugador) {

		String sql = "SELECT  idpartida, rondasjugadas from partida where idpartida = " + idpartida + " and id_jugador="
				+ id_jugador + "";

		escribirEnArchivo(nombreArchivo, sql);

	}

	public void guardarrondas(int idjugador, int idpartida, int rondas) {

		String sql = "UPDATE partida SET rondasjugadas = " + rondas + "  WHERE idpartida =" + idpartida
				+ " and id_jugador = " + idjugador + " ";
		escribirEnArchivo(nombreArchivo, sql);

	}

	public void brotesactuales(int idpartida, int id_jugador) {

		String sql = "SELECT  brotes_actuales from partida where idpartida = " + idpartida + " and id_jugador="
				+ id_jugador + "";

		escribirEnArchivo(nombreArchivo, sql);

	}

	public void actualizarvacuna(int vacuna[], int numvacuna, int idpartida) {

		String sql = "UPDATE partida SET vacuna" + numvacuna + " = " + vacuna[numvacuna] + " WHERE idpartida="
				+ idpartida + "";
		escribirEnArchivo(nombreArchivo, sql);

	}

	public void vacunareturn(int vacuna[], int numvacuna, int idpartida) {

		String sql = "select vacuna" + numvacuna + " from partida where idpartida=" + idpartida + "";
		escribirEnArchivo(nombreArchivo, sql);

	}

	public static void escribirEnArchivo(String nombreArchivo, String texto) {
		try {
			File archivo = new File(nombreArchivo);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(archivo, true));
			bufferedWriter.write(texto);
			bufferedWriter.newLine(); // Agrega una nueva línea después de cada texto
			bufferedWriter.close();
			System.out.println("Texto escrito en el archivo.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void borrarContenidoArchivo(String nombreArchivo) {
		try {
			FileWriter fileWriter = new FileWriter(nombreArchivo);
			fileWriter.write(""); // Sobreescribe el contenido con una cadena vacía
			fileWriter.close();
			System.out.println("Contenido del archivo borrado.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int brotesactuales1(int idpartida, int id_jugador) {

		int numenfermedadesactual = 0; // Inicializamos la variable a -1
		// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
		String DB_URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";
		String USER = "DAW_PNDC22_23_DAMO";
		String PASS = "DM123";
		Connection con = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// STEP 2: Open a connection
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			String sql = "SELECT  brotes_actuales from partida where idpartida = " + idpartida + " and id_jugador="
					+ id_jugador + "";
			Statement st = con.createStatement();
			st.execute(sql);

			ResultSet rs = st.executeQuery(sql);
			if (rs.isBeforeFirst()) {
				ResultSetMetaData rsmd = rs.getMetaData(); // Obtenemos los metadatos
				int numCols = rsmd.getColumnCount();
				while (rs.next()) {
					int numeroActualInfecciones = rs.getInt("brotes_actuales");
					numenfermedadesactual = numeroActualInfecciones; // Sumamos el valor de la columna a la variable
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}
		return numenfermedadesactual;

	}

	public int consultarenfermedadesactual1(int idpartida) {

		int numenfermedadesactual = 0; // Inicializamos la variable a -1
		// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
		String DB_URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";
		String USER = "DAW_PNDC22_23_DAMO";
		String PASS = "DM123";
		Connection con = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// STEP 2: Open a connection
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = con.createStatement();
			String sql = "SELECT  idpartida, COUNT(*) - (SELECT COUNT(*) FROM desinfecciones WHERE idpartida = "
					+ idpartida + ") FROM infecciones WHERE idpartida = " + idpartida + " GROUP BY  idpartida";
			Statement st = con.createStatement();

			st.execute(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.isBeforeFirst()) {
				ResultSetMetaData rsmd = rs.getMetaData(); // Obtenemos los metadatos
				int numCols = rsmd.getColumnCount();

				while (rs.next()) {
					int numeroActualInfecciones = rs
							.getInt("COUNT(*)-(SELECTCOUNT(*)FROMdesinfeccionesWHEREidpartida=" + idpartida + ")");
					numenfermedadesactual = numeroActualInfecciones; // Sumamos el valor de la columna a la variable
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}
		return numenfermedadesactual;

	}

	public int consultarrondas1(int idpartida, int id_jugador) {

		int numenfermedadesactual = 0; // Inicializamos la variable a -1
		// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
		String DB_URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";
		String USER = "DAW_PNDC22_23_DAMO";
		String PASS = "DM123";
		Connection con = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// STEP 2: Open a connection
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = con.createStatement();
			String sql = "SELECT  idpartida, rondasjugadas from partida where idpartida = " + idpartida
					+ " and id_jugador=" + id_jugador + "";
			Statement st = con.createStatement();
			st.execute(sql);

			ResultSet rs = st.executeQuery(sql);
			if (rs.isBeforeFirst()) {
				ResultSetMetaData rsmd = rs.getMetaData(); // Obtenemos los metadatos
				int numCols = rsmd.getColumnCount();
				while (rs.next()) {
					int numeroActualInfecciones = rs.getInt("rondasjugadas");
					numenfermedadesactual = numeroActualInfecciones; // Sumamos el valor de la columna a la variable
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}
		return numenfermedadesactual;

	}

	public void actualizacionciudades(int idpartida, String ciudades1[][]) {

		int numenfermedadesactual = 0; // Inicializamos la variable a -1
		// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
		String DB_URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";
		String USER = "DAW_PNDC22_23_DAMO";
		String PASS = "DM123";
		Connection con = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// STEP 2: Open a connection
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			int index = 0;
			stmt = con.createStatement();
			for (int i = 0; i < 49; i++) {
				String sql = "SELECT c.idciudad, c.nombreciudad, (SELECT COUNT(*) FROM Infecciones i WHERE i.idCiudad = c.idciudad AND i.idPartida = "
						+ idpartida
						+ ") - (SELECT COUNT(*) FROM Desinfecciones d WHERE d.idCiudad = c.idciudad AND d.idPartida = "
						+ idpartida
						+ ") FROM ciudad c WHERE c.idciudad =(select idciudad from ciudad where nombreciudad='"
						+ ciudades1[i][1] + "') ";
				Statement st = con.createStatement();
				st.execute(sql);

				ResultSet rs = st.executeQuery(sql);

				while (rs.next()) {
					ciudades1[index][0] = String.valueOf(rs.getInt("IDCIUDAD"));
					ciudades1[index][2] = String.valueOf(rs.getInt(
							"(SELECTCOUNT(*)FROMINFECCIONESIWHEREI.IDCIUDAD=C.IDCIUDADANDI.IDPARTIDA=" + idpartida
									+ ")-(SELECTCOUNT(*)FROMDESINFECCIONESDWHERED.IDCIUDAD=C.IDCIUDADANDD.IDPARTIDA="
									+ idpartida + ")"));
					index++;
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}

	}

	public static void ejecutarSentenciasSQL(String nombreArchivo) {

		try {
			// Establecer la conexión a la base de datos Oracle

			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.ilerna.com:1521:xe",
					"DAW_PNDC22_23_DAMO", "DM123");

			// Leer el contenido del archivo
			FileReader fileReader = new FileReader(nombreArchivo);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String sentencia;

			// Ejecutar las sentencias SQL una por una
			Statement statement = connection.createStatement();
			while ((sentencia = bufferedReader.readLine()) != null) {
				if (!sentencia.trim().isEmpty()) {
					statement.executeUpdate(sentencia);
				}
			}

			statement.close();
			connection.close();
			bufferedReader.close();

			System.out.println("Sentencias SQL ejecutadas correctamente.");
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void actualizabotones(int idpartida,  int cordenadasXY[][]) {
		
		ImageIcon iconoAmarillo0 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\amarillo0.png");
		ImageIcon iconoAmarillo1 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\amarillo1.png");
		ImageIcon iconoAmarillo2 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\amarillo2.png");
		ImageIcon iconoAmarillo3 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\amarillo3.png");
		ImageIcon iconoAmarillo4 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\amarillo4.png");
		ImageIcon iconoRojo0 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\rojo0.png");
		ImageIcon iconoRojo1 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\rojo1.png");
		ImageIcon iconoRojo2 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\rojo2.png");
		ImageIcon iconoRojo3 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\rojo3.png");
		ImageIcon iconoRojo4 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\rojo4.png");
		ImageIcon iconoVerde0 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\verde0.png");
		ImageIcon iconoVerde1 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\verde1.png");
		ImageIcon iconoVerde2 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\verde2.png");
		ImageIcon iconoVerde3 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\verde3.png");
		ImageIcon iconoVerde4 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\verde4.png");
		ImageIcon iconoAzul0 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\azul0.png");
		ImageIcon iconoAzul1 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\azul1.png");
		ImageIcon iconoAzul2 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\azul2.png");
		ImageIcon iconoAzul3 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\azul3.png");
		ImageIcon iconoAzul4 = new ImageIcon(".\\imagenes utilizadas\\botonesciudades\\azul4.png");
		ImageIcon icono0 = new ImageIcon(".\\imagenes utilizadas\\icono0.png");
		
	
		
		for (int i = 0; i < 49; i++) {
			JButton puntoCity = new JButton();
			
		
			
System.out.println("nombre ciudad: "+ciudades1[i][1]+"  numero infecciones:  "+ciudades1[i][2]);
			if (ciudades1[i][2].equals("0") && ciudades1[i][4].equals("0")) {
				puntoCity.setIcon(icono0);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], icono0.getIconWidth(),
				icono0.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(icono0);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(icono0);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(icono0);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			}

			else if (ciudades1[i][2].equals("1") && ciudades1[i][4].equals("0")) {
				puntoCity.setIcon(iconoAzul1);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoAzul1.getIconWidth(),
						iconoAzul1.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoAzul1);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoAzul1);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoAzul1);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			} else if (ciudades1[i][2].equals("2") && ciudades1[i][4].equals("0")) {
				puntoCity.setIcon(iconoAzul2);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoAzul2.getIconWidth(),
						iconoAzul2.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoAzul2);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoAzul2);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoAzul2);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			} else if (ciudades1[i][2].equals("3") && ciudades1[i][4].equals("0")) {
				puntoCity.setIcon(iconoAzul3);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoAzul3.getIconWidth(),
						iconoAzul3.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoAzul3);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoAzul3);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoAzul3);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			} else if (ciudades1[i][2].equals("4") && ciudades1[i][4].equals("0")) {
				puntoCity.setIcon(iconoAzul4);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoAzul4.getIconWidth(),
						iconoAzul4.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoAzul4);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoAzul4);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoAzul4);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			}

/////////////////////////////////////////////////////////////////////
			else if (ciudades1[i][2].equals("0") && ciudades1[i][4].equals("1")) {
				puntoCity.setIcon(icono0);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], icono0.getIconWidth(),
						icono0.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(icono0);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(icono0);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(icono0);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
			
			}
			else if (ciudades1[i][2].equals("1") && ciudades1[i][4].equals("1")) {
				puntoCity.setIcon(iconoRojo1);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoRojo1.getIconWidth(),
						iconoRojo1.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoRojo1);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoRojo1);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoRojo1);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			} else if (ciudades1[i][2].equals("2") && ciudades1[i][4].equals("1")) {
				puntoCity.setIcon(iconoRojo2);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoRojo2.getIconWidth(),
						iconoRojo2.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoRojo2);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoRojo2);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoRojo2);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
			
			} else if (ciudades1[i][2].equals("3") && ciudades1[i][4].equals("1")) {
				puntoCity.setIcon(iconoRojo3);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoRojo3.getIconWidth(),
						iconoRojo3.getIconHeight());				
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoRojo3);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoRojo3);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoRojo3);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
			
			} else if (ciudades1[i][2].equals("4") && ciudades1[i][4].equals("1")) {
				puntoCity.setIcon(iconoRojo4);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoRojo4.getIconWidth(),
						iconoRojo4.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoRojo4);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoRojo4);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoRojo4);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			}

///////////////////////////////////////////////////////////////
			else if (ciudades1[i][2].equals("0") && ciudades1[i][4].equals("2")) {
				puntoCity.setIcon(icono0);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], icono0.getIconWidth(),
						icono0.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(icono0);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(icono0);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(icono0);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			}
			else if (ciudades1[i][2].equals("1") && ciudades1[i][4].equals("2")) {
				puntoCity.setIcon(iconoVerde1);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoVerde1.getIconWidth(),
						iconoVerde1.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoVerde1);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoVerde1);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoVerde1);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			} else if (ciudades1[i][2].equals("2") && ciudades1[i][4].equals("2")) {
				puntoCity.setIcon(iconoVerde2);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoVerde2.getIconWidth(),
						iconoVerde2.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoVerde2);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoVerde2);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoVerde2);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			} else if (ciudades1[i][2].equals("3") && ciudades1[i][4].equals("2")) {
				puntoCity.setIcon(iconoVerde3);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoVerde3.getIconWidth(),
						iconoVerde3.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoVerde3);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoVerde3);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoVerde3);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			} else if (ciudades1[i][2].equals("4") && ciudades1[i][4].equals("2")) {
				puntoCity.setIcon(iconoVerde4);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoVerde4.getIconWidth(),
						iconoVerde4.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoVerde4);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoVerde4);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoVerde4);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
			
			}
/////////////////////////////////////////////////////////////////////////////////////////////////////
			else if (ciudades1[i][2].equals("0") && ciudades1[i][4].equals("3")) {
				puntoCity.setIcon(icono0);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], icono0.getIconWidth(),
						icono0.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(icono0);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(icono0);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(icono0);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
			
			}
			else if (ciudades1[i][2].equals("1") && ciudades1[i][4].equals("3")) {
				puntoCity.setIcon(iconoAmarillo1);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoAmarillo1.getIconWidth(),
						iconoAmarillo1.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoAmarillo1);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoAmarillo1);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoAmarillo1);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			} else if (ciudades1[i][2].equals("2") && ciudades1[i][4].equals("3")) {
				puntoCity.setIcon(iconoAmarillo2);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoAmarillo2.getIconWidth(),
						iconoAmarillo2.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoAmarillo2);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoAmarillo2);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoAmarillo2);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			} else if (ciudades1[i][2].equals("3") && ciudades1[i][4].equals("3")) {
				puntoCity.setIcon(iconoAmarillo3);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoAmarillo3.getIconWidth(),
						iconoAmarillo3.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoAmarillo3);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoAmarillo3);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoAmarillo3);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
				
			} else if (ciudades1[i][2].equals("4") && ciudades1[i][4].equals("3")) {
				puntoCity.setIcon(iconoAmarillo4);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(cordenadasXY[i][0], cordenadasXY[i][1], iconoAmarillo4.getIconWidth(),
						iconoAmarillo4.getIconHeight());
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoAmarillo4);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoAmarillo4);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoAmarillo4);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				
			
			}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
			puntoCity.setVisible(true);
			puntoCity.setToolTipText(ciudades1[i][1]);
			getContentPane().add(puntoCity);

			puntoCity.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Audio.sonidoBoton();
// Desseleccionar botón previamente seleccionado
					if (botonSeleccionado != null) {
						botonSeleccionado.setSelected(false);

						if (botonSeleccionado != null) {
							System.out.println(ciudadSeleccionada);

						}
					}
// Seleccionar el botón actual
					botonSeleccionado = puntoCity;
					botonSeleccionado.setSelected(true);
					ciudadSeleccionada = puntoCity.getToolTipText(); // Asignar valor del tooltip a
// ciudadSeleccionada
					for (int i=0;i<49;i++) {
						
						
						if(ciudades1[i][1].equals(ciudadSeleccionada)) {
							
							idciudad=Integer.parseInt(ciudades1[i][0]);
							
						}
						
					}
					System.out.println(idciudad);

				}
			});

			

		}

	}
public void cargarvacuna(int idpartida,int vacunas[]) {
		
		// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
	   	String DB_URL =  "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";
	       String USER =  "DAW_PNDC22_23_DAMO";
	       String PASS = "DM123";
	       Connection con = null;
	       Statement stmt = null;
	      
	   
	       try {
	           //STEP 1: Register JDBC driver
	           Class.forName("oracle.jdbc.driver.OracleDriver");

	           //STEP 2: Open a connection
	           System.out.println("conexion may fraindd...");
				con = DriverManager.getConnection(DB_URL, USER, PASS);
				 System.out.println("Insertando el nuevo jugador en nuestra bd");
		            stmt = con.createStatement();
		            for(int i =1 ; i<5;i++) {
		            String sql = "Select vacuna"+i+" from partida where idpartida="+idpartida; 
		            Statement st = con.createStatement();
	   			st.execute(sql);
	   			ResultSet rs = st.executeQuery(sql); 	 	

				
					while (rs.next()) {
						
						
							
								
						vacunas[i] = rs.getInt("VACUNA"+i+"");
	   		
	   	
	   			
	   			}
					
			}
	   			System.out.println("Consultando ...");
	   } catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}
		   
		   
		
	}

}