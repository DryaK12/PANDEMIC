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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
public class PanelCargarPartida extends JFrame implements ActionListener {
	private JPanel panel;
	private JTextArea textArea;
    public int id_jugador;
    public int id_partida;
    public int lineaCargar = 0;
    public int lineaBorrar = 0;
    


	public PanelCargarPartida(int id_jugador) {
	
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("PANDEMIC");
		setResizable(false);
		setBounds(100, 100, 600, 600);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\imagenes utilizadas\\pandemic2.png"));
System.out.println("id jugador:"+id_jugador);        
        
        System.out.println(id_jugador);



		ImageIcon imagenDeFondo = new ImageIcon(".\\imagenes utilizadas\\fondoCargarPartida.jpg");

		///////////////////////////////////////////////
	
		///////////////////////////////////////////////////////////////////////////////
		ImageIcon iconoNormal3 = new ImageIcon(".\\imagenes utilizadas\\botonVolverCargar.png");
		ImageIcon iconoPresionado3 = new ImageIcon(".\\imagenes utilizadas\\botonVolverCargar2.png");
		ImageIcon iconoSobre3 = new ImageIcon(".\\imagenes utilizadas\\botonVolverCargar2.png");
		JButton botonVolver = new JButton(iconoNormal3); // NOMBRE DEL BOTON MAS LA CREACION
		botonVolver.setBorder(null);
		botonVolver.setOpaque(true);
		botonVolver.setBackground(new Color(0, 0, 0, 0));
		botonVolver.setBounds(410, 340, iconoNormal3.getIconWidth(), iconoNormal3.getIconHeight());
		botonVolver.setIcon(iconoNormal3);
		botonVolver.setPressedIcon(iconoPresionado3);
		botonVolver.setRolloverIcon(iconoSobre3);
		botonVolver.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				botonVolver.setIcon(iconoPresionado3);
				botonVolver.setBorderPainted(false);
				botonVolver.setContentAreaFilled(false);
			}

			public void mouseEntered(MouseEvent e) {
				botonVolver.setIcon(iconoSobre3);
				botonVolver.setBorderPainted(false);
				botonVolver.setContentAreaFilled(false);
			}

			public void mouseExited(MouseEvent e) {
				botonVolver.setIcon(iconoNormal3);
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

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(150, 100, 390, 190);
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

		// Establecer la ScrollBarUI personalizada
		scrollBar.setUI(ui);
		getContentPane().add(scrollPane);

		textArea = new JTextArea(32, 25);

		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Arial", Font.BOLD, 18)); // cambiar la fuente a Arial y poner en negrita con tamaño
															// 20
		textArea.setForeground(Color.WHITE);
		textArea.setOpaque(false);
		
		

	int numpartidas=numeropartidas(id_jugador);
	String[] Partidas = new String[numpartidas];
	int[] Partidasid = new int[numpartidas];
		
		
		cargarpartidas(Partidas, id_jugador,Partidasid);
		
		
	
		for (int i=0;i<numpartidas;i++) { // aquí
			ImageIcon iconoNormal1 = new ImageIcon(".\\imagenes utilizadas\\cargarPartidaJuego.png");
			ImageIcon iconoPresionado1 = new ImageIcon(".\\imagenes utilizadas\\cargarPartidaJuego2.png");
			ImageIcon iconoSobre1 = new ImageIcon(".\\imagenes utilizadas\\cargarPartidaJuego2.png");
			JButton botonCargar = new JButton(iconoNormal1); // NOMBRE DEL BOTON MAS LA CREACION
			botonCargar.setBorder(null);
			botonCargar.setOpaque(true);
			botonCargar.setBackground(new Color(0, 0, 0, 0));
			botonCargar.setBounds(180, lineaCargar, iconoNormal1.getIconWidth(), iconoNormal1.getIconHeight());
			botonCargar.setIcon(iconoNormal1);
			botonCargar.setPressedIcon(iconoPresionado1);
			botonCargar.setRolloverIcon(iconoSobre1);
			botonCargar.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					botonCargar.setIcon(iconoPresionado1);
					botonCargar.setBorderPainted(false);
					botonCargar.setContentAreaFilled(false);
				}

				public void mouseEntered(MouseEvent e) {
					botonCargar.setIcon(iconoSobre1);
					botonCargar.setBorderPainted(false);
					botonCargar.setContentAreaFilled(false);
				}

				public void mouseExited(MouseEvent e) {
					botonCargar.setIcon(iconoNormal1);
					botonCargar.setBorderPainted(false);
					botonCargar.setContentAreaFilled(false);
				}
			});

			botonCargar.addActionListener(this);
			id_partida = Partidasid[i];
			botonCargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int id_partida = (int) ((JButton) e.getSource()).getClientProperty("id_partida");
			        System.out.println("Se hizo clic en el botón de cargar la partida " + id_partida);
					Audio.sonidoBoton();
					dispose();
					PanelNuevaPartida PanelNuevaPartida = new PanelNuevaPartida(id_jugador,id_partida);
					PanelNuevaPartida.setVisible(true);

	//aqui pondras tus huevos 
				
				}
			});
			botonCargar.putClientProperty("id_partida", id_partida);
			ImageIcon iconoNormal2 = new ImageIcon(".\\imagenes utilizadas\\borrarpartida.png");
			ImageIcon iconoPresionado2 = new ImageIcon(".\\imagenes utilizadas\\borrarpartida2.png");
			ImageIcon iconoSobre2 = new ImageIcon(".\\imagenes utilizadas\\borrarpartida2.png");
			JButton botonBorrar = new JButton(iconoNormal1); // NOMBRE DEL BOTON MAS LA CREACION
			botonBorrar.setBorder(null);
			botonBorrar.setOpaque(true);
			botonBorrar.setBackground(new Color(0, 0, 0, 0));
			botonBorrar.setBounds(275, lineaBorrar, iconoNormal2.getIconWidth(), iconoNormal2.getIconHeight());
			botonBorrar.setIcon(iconoNormal2);
			botonBorrar.setPressedIcon(iconoPresionado2);
			botonBorrar.setRolloverIcon(iconoSobre2);
			botonBorrar.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					botonBorrar.setIcon(iconoPresionado1);
					botonBorrar.setBorderPainted(false);
					botonBorrar.setContentAreaFilled(false);
				}

				public void mouseEntered(MouseEvent e) {
					botonBorrar.setIcon(iconoSobre2);
					botonBorrar.setBorderPainted(false);
					botonBorrar.setContentAreaFilled(false);
				}

				public void mouseExited(MouseEvent e) {
					botonBorrar.setIcon(iconoNormal2);
					botonBorrar.setBorderPainted(false);
					botonBorrar.setContentAreaFilled(false);
				}
			});

			botonBorrar.addActionListener(this);

			botonBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Audio.sonidoBoton();
					// AQUI BORRARAS

				}
			});

			textArea.append(Partidas[i] + "\n \n");
		textArea.add(botonCargar);
		textArea.add(botonBorrar);
		lineaBorrar = lineaBorrar +46;
		lineaCargar = lineaCargar+46;
		
		}

		

		
		
		JLabel labelDeFondo = new JLabel(imagenDeFondo);
		labelDeFondo.setBounds(0, 0, 600, 600);
		getContentPane().add(labelDeFondo);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	public int numeropartidas(int idusuario) {
		// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
	   	String DB_URL =  "jdbc:oracle:thin:@192.168.3.26:1521:xe";
	       String USER =  "DAW_PNDC22_23_DAMO";
	       String PASS = "DM123";
	       Connection con = null;
	       Statement stmt = null;
	       int count=0;
	      
	   
	       try {
	           //STEP 1: Register JDBC driver
	           Class.forName("oracle.jdbc.driver.OracleDriver");

	           //STEP 2: Open a connection
	           System.out.println("conexion may fraindd...");
				con = DriverManager.getConnection(DB_URL, USER, PASS);
				 System.out.println("Consultando el numero de partidas del usuario actual");
		            stmt = con.createStatement();
		            String sql = "Select  count(*) from partida where id_jugador="+idusuario;
		            Statement st = con.createStatement();
	   			st.execute(sql);
	   			ResultSet rs = st.executeQuery(sql); 	 	
System.out.println(idusuario);
				if (rs.isBeforeFirst()) {
					while (rs.next()) {
						 int count2 = rs.getInt(1);
				            count = count2;
				            System.out.println(count2);
	   			 
	   			
	   			}
					
				}
	   			System.out.println("Consultando ...");
	   } catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}
		   
		   
		return count;
		
	}
	
	
	public void cargarpartidas(String partidas[], int idusuario,int partidasid[]) {
		
		// Si estáis desde casa, la url será oracle.ilerna.com y no 192.168.3.26
	   	String DB_URL =  "jdbc:oracle:thin:@192.168.3.26:1521:xe";
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
		            String sql = "Select * from partida where id_jugador="+idusuario; 
		            Statement st = con.createStatement();
	   			st.execute(sql);
	   			ResultSet rs = st.executeQuery(sql); 	 	

				if (rs.isBeforeFirst()) {
					int contador=0;
					while (rs.next()) {
						
						
							
								
						partidas[contador] = rs.getString("NOMBREPARTIDA");
						partidasid[contador] = rs.getInt("IDPARTIDA");
	   		
	   			contador++;
	   			
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