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
import javax.swing.JProgressBar;

public class MapaDebajo extends JFrame implements ActionListener {
	

	public MapaDebajo() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("PANDEMIC");
		setResizable(false);
		setBounds(0, 0, 1550, 300);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\imagenes utilizadas\\pandemic2.png"));

		ImageIcon imagenDeFondo = new ImageIcon(".\\imagenes utilizadas\\fondobotonesmapa.jpg");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JProgressBar barraAlfa = new JProgressBar(0, 100);
		barraAlfa.setBounds(900, 32, 200, 25);
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
		botonAlfa.setBounds(935, 72, iconoNormal1.getIconWidth(), iconoNormal1.getIconHeight());
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
				int valorActual = barraAlfa.getValue();
				if (valorActual < 100) {
					barraAlfa.setValue(valorActual + 10);
				}

			}

		});
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JProgressBar barraBeta = new JProgressBar(0, 100);
		barraBeta.setBounds(1220, 32, 200, 25);
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
		botonBeta.setBounds(1260, 72, iconoNormal2.getIconWidth(), iconoNormal2.getIconHeight());
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
				int valorActual = barraBeta.getValue();
				if (valorActual < 100) {
					barraBeta.setValue(valorActual + 10);
				}

			}

		});
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JProgressBar barraDelta = new JProgressBar(0, 100);
		barraDelta.setBounds(900, 140, 200, 25);
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
		botonDelta.setBounds(930, 175, iconoNormal3.getIconWidth(), iconoNormal3.getIconHeight());
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
				int valorActual = barraDelta.getValue();
				if (valorActual < 100) {
					barraDelta.setValue(valorActual + 10);
				}

			}

		});
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
		JProgressBar barraGama = new JProgressBar(0, 100);
		barraGama.setBounds(1220, 140, 200, 25);
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
		botonGama.setBounds(1255, 175, iconoNormal4.getIconWidth(), iconoNormal4.getIconHeight());
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
				int valorActual = barraGama.getValue();
				if (valorActual < 100) {
					barraGama.setValue(valorActual + 10);
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
		botonCurarEnfermedad.setBounds(350, 71, iconoNormal5.getIconWidth(), iconoNormal5.getIconHeight());
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
		botonCurarCiudad.setBounds(400, 175, iconoNormal6.getIconWidth(), iconoNormal6.getIconHeight());
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
		botonGuardarPartida.setBounds(80, 140, iconoNormal7.getIconWidth(), iconoNormal7.getIconHeight());
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
				

			}

		});
		
		
		
		
		
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JLabel labelDeFondo = new JLabel(imagenDeFondo);
		labelDeFondo.setBounds(0, 0, 1550, 300);
		getContentPane().add(labelDeFondo);

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
