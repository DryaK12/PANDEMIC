import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.ToolTipManager;
import javax.swing.JOptionPane;

public class PanelNuevaPartida extends JFrame implements ActionListener {
	private BufferedWriter bufferW;
	private FileWriter fileW;
	private String ciudades, distanciaCiudades;
	private String frase;
	private String[] ciudadesColindantes, coordenadasXY, datos;
	private JButton ciudadess;
	private JFrame panel;
	private JButton botonSeleccionado;
	private HashMap<JButton, Integer> contadores = new HashMap<>();
	public String ciudadSeleccionada; // Variable para almacenar el nombre de la ciudad seleccionada

	public int id_jugador;
	public int enfermedadesfinal = 50;
	public int infecciones1 = 8;
	public int infecciones2 = 4;
	public int brotestotal = 12;
	public boolean partida = true;
	public int idciudad = 1;
	public int InfeccionCiudad = 1;

	public PanelNuevaPartida(int id_jugador, int idpartida) {

		int acciones = 4;
		int rondas = 0;
InfeccionCiudad=consultarciudadactual(idciudad,idpartida);
	
		  if(rondas==0) { int contador=0; while (contador!=infecciones1) { contador++;
		  infectar(rondas,idpartida);
		  
		  
		  }
		  
		  } else if(rondas>0) { int contador=0; while (contador!=infecciones2) {
		  contador++; infectar(rondas,idpartida);
		  
		  
		 }
		  
		  }
		 
		  
		  if(acciones==0) { rondas++; }
		  
		  
		 
		 

		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("PANDEMIC");
		setResizable(true);
		setBounds(0, 0, 1550, 1200);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\imagenes utilizadas\\pandemic2.png"));
		ImageIcon imagenDeFondo = new ImageIcon(".\\imagenes utilizadas\\mapa.jpg");

		ImageIcon iconoUbicacion = new ImageIcon(".\\imagenes utilizadas\\posicion.png");

		ciudades = ".\\archivos\\ciudades.txt";
		distanciaCiudades = ".\\archivos\\ciudadesRedactadas.txt";

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
				ciudadesColindantes = datos[3].split(",");
				int posicionX = Integer.parseInt(coordenadasXY[0]);
				int posicionY = Integer.parseInt(coordenadasXY[1]);

				JButton puntoCity = new JButton(iconoUbicacion);
				puntoCity.setBorder(null);
				puntoCity.setOpaque(true);
				puntoCity.setBackground(new Color(0, 0, 0, 0));
				puntoCity.setBounds(posicionX, posicionY, iconoUbicacion.getIconWidth(),
						iconoUbicacion.getIconHeight());
				puntoCity.setIcon(iconoUbicacion);
				puntoCity.setPressedIcon(iconoUbicacion);
				puntoCity.setRolloverIcon(iconoUbicacion);
				puntoCity.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						puntoCity.setIcon(iconoUbicacion);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseEntered(MouseEvent e) {
						puntoCity.setIcon(iconoUbicacion);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}

					public void mouseExited(MouseEvent e) {
						puntoCity.setIcon(iconoUbicacion);
						puntoCity.setBorderPainted(false);
						puntoCity.setContentAreaFilled(false);
					}
				});
				puntoCity.setToolTipText(datos[0]);
				ToolTipManager.sharedInstance().setInitialDelay(0);
				ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
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

								idciudad = consultaridciudad(ciudadSeleccionada);

								System.out.println(idciudad);
							}
						}
						// Seleccionar el botón actual
						botonSeleccionado = puntoCity;
						botonSeleccionado.setSelected(true);
						ciudadSeleccionada = puntoCity.getToolTipText(); // Asignar valor del tooltip a
																			// ciudadSeleccionada

					}
				});

				getContentPane().add(puntoCity);

				if (InfeccionCiudad > 0) {

					JButton puntoCityInf = new JButton(iconoUbicacion);
					puntoCityInf.setBorder(null);
					puntoCityInf.setOpaque(true);
					puntoCityInf.setBackground(new Color(0, 0, 0, 0));
					puntoCityInf.setBounds(posicionX, posicionY, iconoUbicacion.getIconWidth() + 10,
							iconoUbicacion.getIconHeight() + 10);
					puntoCityInf.setIcon(iconoUbicacion);
					puntoCityInf.setPressedIcon(iconoUbicacion);
					puntoCityInf.setRolloverIcon(iconoUbicacion);
					puntoCityInf.addMouseListener(new MouseAdapter() {
						public void mousePressed(MouseEvent e) {
							puntoCityInf.setIcon(iconoUbicacion);
							puntoCityInf.setBorderPainted(false);
							puntoCityInf.setContentAreaFilled(false);
						}

						public void mouseEntered(MouseEvent e) {
							puntoCityInf.setIcon(iconoUbicacion);
							puntoCityInf.setBorderPainted(false);
							puntoCityInf.setContentAreaFilled(false);
						}

						public void mouseExited(MouseEvent e) {
							puntoCityInf.setIcon(iconoUbicacion);
							puntoCityInf.setBorderPainted(false);
							puntoCityInf.setContentAreaFilled(false);
						}
					});
					puntoCityInf.setToolTipText("Esta ciudad esta infectada por " + InfeccionCiudad + " enfermedades");
					ToolTipManager.sharedInstance().setInitialDelay(0);
					ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
					getContentPane().add(puntoCityInf);
					puntoCityInf.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// Desseleccionar botón previamente seleccionado
							if (botonSeleccionado != null) {
								botonSeleccionado.setSelected(false);
								if (botonSeleccionado != null) {

								}
							}
							// Seleccionar el botón actual
							botonSeleccionado.setSelected(true);

						}
					});
					getContentPane().add(puntoCityInf);
				}

			}
			// CERRAMOS
			fileR.close();
			bufferR.close();

		} catch (IOException xd) {
			System.out.println("Error al leer el fichero.");
			xd.printStackTrace();
		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton BotonRojo = new JButton("");
		BotonRojo.setEnabled(false);
		BotonRojo.setBackground(Color.RED);
		BotonRojo.setForeground(Color.WHITE);
		BotonRojo.setBounds(100, 200, 50, 50);
		BotonRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				if (botonSeleccionado != null) {
					desinfectarvacuna(idciudad, idpartida);
				}

			}

		});
		getContentPane().add(BotonRojo);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton BotonVerde = new JButton("");
		BotonVerde.setEnabled(false);
		BotonVerde.setBackground(Color.GREEN);
		BotonVerde.setForeground(Color.WHITE);
		BotonVerde.setBounds(100, 250, 50, 50);
		BotonVerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				if (botonSeleccionado != null) {
					desinfectarvacuna(idciudad, idpartida);
				}

			}

		});
		getContentPane().add(BotonVerde);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JButton BotonAmarillo = new JButton("");
		BotonAmarillo.setEnabled(false);
		BotonAmarillo.setBackground(Color.YELLOW);
		BotonAmarillo.setForeground(Color.WHITE);
		BotonAmarillo.setBounds(100, 300, 50, 50);
		BotonAmarillo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				if (botonSeleccionado != null) {
					desinfectarvacuna(idciudad, idpartida);
				}

			}

		});
		getContentPane().add(BotonAmarillo);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JButton BotonAzul = new JButton("");
		BotonAzul.setEnabled(false);
		BotonAzul.setBackground(Color.BLUE);
		BotonAzul.setForeground(Color.WHITE);
		BotonAzul.setBounds(100, 350, 50, 50);
		BotonAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				if (botonSeleccionado != null) {
					desinfectarvacuna(idciudad, idpartida);
				}

			}

		});
		getContentPane().add(BotonAzul);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		ImageIcon imagenDeFondo2 = new ImageIcon(".\\imagenes utilizadas\\fondobotonesmapa.jpg");
		JProgressBar barraAlfa = new JProgressBar(0, 100);
		barraAlfa.setBounds(900, 860, 200, 25);
		barraAlfa.setForeground(Color.BLUE);
		barraAlfa.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		barraAlfa.setOpaque(false);
		barraAlfa.setBackground(null);
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
				int valorActual = barraAlfa.getValue();
				if (valorActual < 100) {
					barraAlfa.setValue(valorActual + 25);
				} else {
					//AQUI PONES LA FUNCION PARA GUARDAR LOS DATOS DE LAS VACUNAS
					BotonAzul.setEnabled(true);
				}
			}
		});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JProgressBar barraBeta = new JProgressBar(0, 100);
		barraBeta.setBounds(1220, 860, 200, 25);
		barraBeta.setForeground(Color.RED);
		barraBeta.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		barraBeta.setOpaque(false);
		barraBeta.setBackground(null);
		getContentPane().add(barraBeta);

		ImageIcon iconoNormal2 = new ImageIcon(".\\imagenes utilizadas\\botonbeta.png");
		ImageIcon iconoPresionado2 = new ImageIcon(".\\imagenes utilizadas\\botonbeta2.png");
		ImageIcon iconoSobre2 = new ImageIcon(".\\imagenes utilizadas\\botonbeta2.png");
		JButton botonBeta = new JButton(iconoNormal1); // NOMBRE DEL BOTON MAS LA CREACION
		botonBeta.setBorder(null);
		botonBeta.setOpaque(true);
		botonBeta.setBackground(new Color(0, 0, 0, 0));
		botonBeta.setBounds(1260, 890, iconoNormal2.getIconWidth(), iconoNormal2.getIconHeight());
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
				int valorActual = barraBeta.getValue();
				if (valorActual < 100) {
					barraBeta.setValue(valorActual + 25);
				} else {
					//AQUI PONES LA FUNCION PARA GUARDAR LOS DATOS DE LAS VACUNAS
					BotonRojo.setEnabled(true);
				}
			}
		});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JProgressBar barraDelta = new JProgressBar(0, 100);
		barraDelta.setBounds(900, 955, 200, 25);
		barraDelta.setForeground(Color.YELLOW);
		barraDelta.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		barraDelta.setOpaque(false);
		barraDelta.setBackground(null);
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
				int valorActual = barraDelta.getValue();
				if (valorActual < 100) {
					barraDelta.setValue(valorActual + 25);
				} else {
					//AQUI PONES LA FUNCION PARA GUARDAR LOS DATOS DE LAS VACUNAS
					BotonAmarillo.setEnabled(true);
				}
			}
		});
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		JProgressBar barraGama = new JProgressBar(0, 100);
		barraGama.setBounds(1220, 955, 200, 25);
		barraGama.setForeground(Color.GREEN);
		barraGama.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		barraGama.setOpaque(false);
		barraGama.setBackground(null);
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
				int valorActual = barraGama.getValue();
				if (valorActual < 100) {
					barraGama.setValue(valorActual + 25);
				} else {
					//AQUI PONES LA FUNCION PARA GUARDAR LOS DATOS DE LAS VACUNAS
					BotonVerde.setEnabled(true);
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
				Audio.sonidoBoton();
				desinfectar(idciudad, idpartida);

			}

		});

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ImageIcon iconoNormal6 = new ImageIcon(".\\imagenes utilizadas\\curarciudad.png");
		ImageIcon iconoPresionado6 = new ImageIcon(".\\imagenes utilizadas\\curarciudad2.png");
		ImageIcon iconoSobre6 = new ImageIcon(".\\imagenes utilizadas\\curarciudad2.png");
		JButton botonCurarCiudad = new JButton(iconoNormal6); // NOMBRE DEL BOTON MAS LA CREACION
		botonCurarCiudad.setBorder(null);
		botonCurarCiudad.setOpaque(true);
		botonCurarCiudad.setBackground(new Color(0, 0, 0, 0));
		botonCurarCiudad.setBounds(400, 975, iconoNormal6.getIconWidth(), iconoNormal6.getIconHeight());
		botonCurarCiudad.setIcon(iconoNormal6);
		botonCurarCiudad.setPressedIcon(iconoPresionado6);
		botonCurarCiudad.setRolloverIcon(iconoSobre6);
		botonCurarCiudad.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				botonCurarCiudad.setIcon(iconoPresionado6);
				botonCurarCiudad.setBorderPainted(false);
				botonCurarCiudad.setContentAreaFilled(false);
			}

			public void mouseEntered(MouseEvent e) {
				botonCurarCiudad.setIcon(iconoSobre6);
				botonCurarCiudad.setBorderPainted(false);
				botonCurarCiudad.setContentAreaFilled(false);
			}

			public void mouseExited(MouseEvent e) {
				botonCurarCiudad.setIcon(iconoNormal6);
				botonCurarCiudad.setBorderPainted(false);
				botonCurarCiudad.setContentAreaFilled(false);
			}
		});

		botonCurarCiudad.addActionListener(this);
		getContentPane().add(botonCurarCiudad);

		botonCurarCiudad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Audio.sonidoBoton();
				if (botonSeleccionado != null) {
					desinfectarvacuna(idciudad, idpartida);
				}

			}

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

			}

		});

///////////////////////////////////////////////////////////////////////////////////////////////////////

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

	public int consultaridciudad(String nomciudad) {
		int id_ciudad = 0;

		// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
		String DB_URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
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
			String sql = "Select  idciudad from ciudad where nombreciudad='" + nomciudad + "'";
			Statement st = con.createStatement();
			st.execute(sql);
			ResultSet rs = st.executeQuery(sql);

			if (rs.isBeforeFirst()) {
				while (rs.next()) {

					id_ciudad = rs.getInt("idciudad");

				}

			}
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}

		return id_ciudad;
	}

	public void infectar(int rondas, int idpartida) {

		Random rand = new Random();

		int idciudad = 0;
		do {
			idciudad = rand.nextInt(51) + 1;
		} while (idciudad == 8 || idciudad == 2);
		int idenfermedad = 0;

		idenfermedad = rand.nextInt(4) + 1;

		// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
		String DB_URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
		String USER = "DAW_PNDC22_23_DAMO";
		String PASS = "DM123";
		Connection con = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// STEP 2: Open a connection
			System.out.println("conexion may fraindd...");
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Insertando la nueva infeccion en nuestra bd");
			stmt = con.createStatement();
			String sql = "INSERT INTO infecciones (idinfeccion, idpartida, idciudad, idenfermedad) VALUES (seq_infeccion.NEXTVAL, "
					+ idpartida + ", " + idciudad + ", " + idenfermedad + ")";
			Statement st = con.createStatement();
			st.execute(sql);

			System.out.println("Infeccion registrada correctamente");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}

	}

	public void desinfectar(int idciudad, int idpartida) {

		// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
		String DB_URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
		String USER = "DAW_PNDC22_23_DAMO";
		String PASS = "DM123";
		Connection con = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// STEP 2: Open a connection
			System.out.println("conexion may fraindd...");
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Insertando la nueva desinfeccion en nuestra bd");
			stmt = con.createStatement();
			String sql = "INSERT INTO desinfecciones (iddesinfecciones, idpartida, idciudad, idenfermedad, idinfeccion) VALUES (secuencia_desinfecciones.NEXTVAL, "
					+ idpartida + " , " + idciudad + ", (SELECT idenfermedad FROM infecciones WHERE idpartida = "
					+ idpartida + " AND idciudad = " + idciudad
					+ " AND ROWNUM = 1), (SELECT idinfeccion FROM infecciones WHERE idpartida = " + idpartida
					+ " AND idciudad = " + idciudad + " AND ROWNUM = 1))";
			Statement st = con.createStatement();
			st.execute(sql);

			System.out.println("desnfeccion registrada correctamente");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}

	}

	public void desinfectarvacuna(int idciudad, int idpartida) {

		// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
		String DB_URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
		String USER = "DAW_PNDC22_23_DAMO";
		String PASS = "DM123";
		Connection con = null;
		Statement stmt = null;

		try {
			// STEP 1: Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// STEP 2: Open a connection
			System.out.println("conexion may fraindd...");
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Insertando la nueva desinfeccion en nuestra bd");
			stmt = con.createStatement();
			String sql = "INSERT INTO desinfecciones (iddesinfecciones, idpartida, idciudad, idenfermedad, idinfeccion) SELECT secuencia_desinfecciones.NEXTVAL, i.idpartida, i.idciudad, i.idenfermedad, i.idinfeccion FROM infecciones i WHERE i.idpartida = "
					+ idpartida + " AND i.idciudad = " + idciudad + "";
			Statement st = con.createStatement();
			st.execute(sql);

			System.out.println("desnfeccion registrada correctamente");
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}

	}

	public int consultarciudadactual(int idciudad, int idpartida) {

		// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
		String DB_URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
		String USER = "DAW_PNDC22_23_DAMO";
		String PASS = "DM123";
		Connection con = null;
		Statement stmt = null;

		int numenfermedadesactual = -1; // Inicializamos la variable a -1

		try {
			// STEP 1: Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// STEP 2: Open a connection
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = con.createStatement();
			String sql = "SELECT idciudad, idpartida, COUNT(*) - (SELECT COUNT(*) FROM desinfecciones WHERE idciudad = "
					+ idciudad + " AND idpartida = " + idpartida + ") FROM infecciones WHERE idciudad = " + idciudad
					+ " AND idpartida = " + idpartida + " GROUP BY idciudad, idpartida";
			Statement st = con.createStatement();
			st.execute(sql);
			ResultSet rs = st.executeQuery(sql);
			if (rs.isBeforeFirst()) {
				ResultSetMetaData rsmd = rs.getMetaData(); // Obtenemos los metadatos
				int numCols = rsmd.getColumnCount();
				for (int i = 1; i <= numCols; i++) {
					String colName = rsmd.getColumnName(i);
					System.out.println("Nombre de columna: " + colName);
				}
				while (rs.next()) {
					int numeroActualInfecciones = rs.getInt(3);
					numenfermedadesactual += numeroActualInfecciones; // Sumamos el valor de la columna a la variable
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}
		System.out.println("Número de enfermedades actuales: " + numenfermedadesactual);
		return numenfermedadesactual;

	}

}
