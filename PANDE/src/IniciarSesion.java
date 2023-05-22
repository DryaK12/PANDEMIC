import java.awt.Color;
import java.awt.Font;
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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class IniciarSesion extends JFrame implements ActionListener{

    private JLabel labelUsuario, labelContrasena, labelMensaje;
    private JTextField textFieldUsuario;
    private JPasswordField passwordFieldContrasena;
    public int id_jugador1;

    
    
    public IniciarSesion() {
    	
               
        setTitle("PANDEMIC");
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                ".\\imagenes utilizadas\\pandemic2.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.GREEN));
        setBounds(400, 100, 500, 650);
        ImageIcon imagenDeFondo = new ImageIcon(
                ".\\imagenes utilizadas\\fondo1.jpg"); 
        getContentPane().setLayout(null);
        
        // Agregar componentes para el inicio de sesión
       
        
        textFieldUsuario = new JTextField();
        textFieldUsuario.setBounds(240, 312, 150, 25);
        textFieldUsuario.setOpaque(false);
  
       
        textFieldUsuario.setFont(new Font("Arial", Font.BOLD, 15)); // cambiar la fuente a Arial y poner en negrita con tamaño 20
        textFieldUsuario.setForeground(Color.WHITE); // cambiar el color de la letra a verde

        getContentPane().add(textFieldUsuario);
        
        
        
        passwordFieldContrasena = new JPasswordField();
        passwordFieldContrasena.setBounds(274, 364, 150, 25);
        passwordFieldContrasena.setOpaque(false);
        
      
     
        passwordFieldContrasena.setFont(new Font("Arial", Font.BOLD, 15)); // cambiar la fuente a Arial y poner en negrita con tamaño 20
        passwordFieldContrasena.setForeground(Color.WHITE); // cambiar el color de la letra a verde
        getContentPane().add(passwordFieldContrasena);
        
        ///////////////////////////////////////////////////////////
        
        ImageIcon iconoNormal1 = new ImageIcon(".\\imagenes utilizadas\\botonsesion.png");
		ImageIcon iconoPresionado1 = new ImageIcon(".\\imagenes utilizadas\\botonsesion2.png");
		ImageIcon iconoSobre1 = new ImageIcon(".\\imagenes utilizadas\\botonsesion2.png");
		JButton botonIniciarSesion = new JButton(iconoNormal1); // NOMBRE DEL BOTON MAS LA CREACION		
		botonIniciarSesion.setBorder(null);
		botonIniciarSesion.setOpaque(true);
		botonIniciarSesion.setBackground(new Color(0,0,0,0));
		botonIniciarSesion.setBounds(170, 440, iconoNormal1.getIconWidth(), iconoNormal1.getIconHeight());
		botonIniciarSesion.setIcon(iconoNormal1);
		botonIniciarSesion.setPressedIcon(iconoPresionado1);
		botonIniciarSesion.setRolloverIcon(iconoSobre1);
		botonIniciarSesion.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent e) {
		    	botonIniciarSesion.setIcon(iconoPresionado1);
		    	botonIniciarSesion.setBorderPainted(false);
		    	botonIniciarSesion.setContentAreaFilled(false);
		    }

		    public void mouseEntered(MouseEvent e) {
		    	botonIniciarSesion.setIcon(iconoSobre1);
		    	botonIniciarSesion.setBorderPainted(false);
		    	botonIniciarSesion.setContentAreaFilled(false);
		    }

		    public void mouseExited(MouseEvent e) {
		    	botonIniciarSesion.setIcon(iconoNormal1);
		    	botonIniciarSesion.setBorderPainted(false);
		    	botonIniciarSesion.setContentAreaFilled(false);
		    }
		});
							
		botonIniciarSesion.addActionListener(new ActionListener() {
			private int id_jugador1;

			public void actionPerformed(ActionEvent e) {
		        String usuario = textFieldUsuario.getText();
		        String contrasena = new String(passwordFieldContrasena.getPassword());
		        Audio.sonidoBoton();
		        // Validar el nombre de usuario y la contraseña
		        int i=Comprobarusuario(usuario,contrasena);
		        id_jugador1= idusuario(usuario,contrasena);

		        if(i==1) {
		            labelMensaje.setText("Inicio de sesión exitoso");
		          id_jugador1= idusuario(usuario,contrasena);
		         
		          PanelCargarPartida panelCargarPartida = new PanelCargarPartida(id_jugador1);
		          Menu menu = new Menu(id_jugador1);

		            
		            dispose();
		            Menu Menu = new Menu(id_jugador1); // EL MAIN DONDE SE LLAMA AL MENU
		            Menu.setVisible(true);
		            
		        } else {
		            labelMensaje.setText("Nombre de usuario o contraseña incorrectos");
		        }
		    }
  		});
		
		
		
		
		
		
		
		
		
        botonIniciarSesion.addActionListener(this);
        getContentPane().add(botonIniciarSesion);
        
        
        
        
        
        
        
        
        
        
        
        
        ///////////////////////////////////////////////////////////
        
        ImageIcon iconoNormal2 = new ImageIcon(".\\imagenes utilizadas\\botoncrear.png");
     		ImageIcon iconoPresionado2 = new ImageIcon(".\\imagenes utilizadas\\botoncrear2.png");
     		ImageIcon iconoSobre2 = new ImageIcon(".\\imagenes utilizadas\\botoncrear2.png");
     		JButton botonCrearCuenta = new JButton(iconoNormal2); // NOMBRE DEL BOTON MAS LA CREACION		
     		botonCrearCuenta.setBorder(null);
     		botonCrearCuenta.setOpaque(true);
     		botonCrearCuenta.setBackground(new Color(0,0,0,0));
     		botonCrearCuenta.setBounds(170, 500, iconoNormal2.getIconWidth(), iconoNormal2.getIconHeight());
     		botonCrearCuenta.setIcon(iconoNormal2);
     		botonCrearCuenta.setPressedIcon(iconoPresionado2);
     		botonCrearCuenta.setRolloverIcon(iconoSobre2);
     		botonCrearCuenta.addMouseListener(new MouseAdapter() {
     		    public void mousePressed(MouseEvent e) {
     		    	botonCrearCuenta.setIcon(iconoPresionado2);
     		    	botonCrearCuenta.setBorderPainted(false);
     		    	botonCrearCuenta.setContentAreaFilled(false);
     		    }

     		    public void mouseEntered(MouseEvent e) {
     		    	botonCrearCuenta.setIcon(iconoSobre2);
     		    	botonCrearCuenta.setBorderPainted(false);
     		    	botonCrearCuenta.setContentAreaFilled(false);
     		    }

     		    public void mouseExited(MouseEvent e) {
     		    	botonCrearCuenta.setIcon(iconoNormal2);
     		    	botonCrearCuenta.setBorderPainted(false);
     		    	botonCrearCuenta.setContentAreaFilled(false);
     		    }
     		});
     							
     		botonCrearCuenta.addActionListener(this);
             getContentPane().add(botonCrearCuenta);
        
             botonCrearCuenta.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				Audio.sonidoBoton();
    				 dispose();
    				// Crea una instancia del nuevo panel y lo muestra
    				PanelCrearCuenta PanelCrearCuenta = new PanelCrearCuenta();
    				PanelCrearCuenta.setVisible(true);
    			}
    		});
        
        
        /////////////////////////////////////////////////////////////////////////////////////////////
             ImageIcon iconoNormal3 = new ImageIcon(".\\imagenes utilizadas\\botonSalirPrimero.png");
      		ImageIcon iconoPresionado3 = new ImageIcon(".\\imagenes utilizadas\\botonSalirPrimero2.png");
      		ImageIcon iconoSobre3 = new ImageIcon(".\\imagenes utilizadas\\botonSalirPrimero2.png");
      		JButton botonSalir = new JButton(iconoNormal3); // NOMBRE DEL BOTON MAS LA CREACION		
      		botonSalir.setBorder(null);
      		botonSalir.setOpaque(true);
      		botonSalir.setBackground(new Color(0,0,0,0));
      		botonSalir.setBounds(205, 565, iconoNormal3.getIconWidth(), iconoNormal3.getIconHeight());
      		botonSalir.setIcon(iconoNormal3);
      		botonSalir.setPressedIcon(iconoPresionado3);
      		botonSalir.setRolloverIcon(iconoSobre3);
      		botonSalir.addMouseListener(new MouseAdapter() {
      		    public void mousePressed(MouseEvent e) {
      		    	botonSalir.setIcon(iconoPresionado3);
      		    	botonSalir.setBorderPainted(false);
      		    	botonSalir.setContentAreaFilled(false);
      		    }

      		    public void mouseEntered(MouseEvent e) {
      		    	botonSalir.setIcon(iconoSobre3);
      		    	botonSalir.setBorderPainted(false);
      		    	botonSalir.setContentAreaFilled(false);
      		    }

      		    public void mouseExited(MouseEvent e) {
      		    	botonSalir.setIcon(iconoNormal3);
      		    	botonSalir.setBorderPainted(false);
      		    	botonSalir.setContentAreaFilled(false);
      		    }
      		});
      							
      		botonSalir.addActionListener(this);
              getContentPane().add(botonSalir);
         
              botonSalir.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				Audio.sonidoBoton();
     				 dispose();    				
     			}
     		});
         
        
        
        labelMensaje = new JLabel();
        labelMensaje.setBounds(95, 410, 400, 25);
        labelMensaje.setFont(new Font("Arial", Font.BOLD, 15));
        labelMensaje.setForeground(Color.WHITE);
        getContentPane().add(labelMensaje);
        
        JLabel labelDeFondo = new JLabel(imagenDeFondo);
        labelDeFondo.setBackground(Color.GREEN);
        labelDeFondo.setBounds(0, 0, 500, 650);
        getContentPane().add(labelDeFondo);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
    
	 public int Comprobarusuario (String username, String passwd) {
		   int n=0;
		   
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
		            String sql = "Select  username,contrasena from jugador where username='"+username+"' and contrasena='"+passwd+"'";
		            Statement st = con.createStatement();
	   			st.execute(sql);
	   			ResultSet rs = st.executeQuery(sql); 	 	

				if (rs.isBeforeFirst()) {
					while (rs.next()) {
	   			 String usuario = rs.getString("username");
	   			 String Contrasenaa = rs.getString("contrasena");
	   			 
	   			if(usuario.equals(usuario) && passwd.equals(Contrasenaa)) {
	   			 n=1;
	   				System.out.println(n);
					}
	   			}
					
				}
	   			System.out.println("Consultando ...");
	   } catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}
		   
		   return n;
	   }
	 public int idusuario (String username, String passwd) {
		   
		  int n=0;
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
		            String sql = "Select  id_jugador,username,contrasena from jugador where username='"+username+"' and contrasena='"+passwd+"'";
		            Statement st = con.createStatement();
	   			st.execute(sql);
	   			ResultSet rs = st.executeQuery(sql); 	 	

				if (rs.isBeforeFirst()) {
					while (rs.next()) {
	   			 String usuario = rs.getString("username");
	   			 String Contrasenaa = rs.getString("contrasena");
	   			 int id_jugador = rs.getInt("id_jugador");
	   			 
	   			if(usuario.equals(usuario) && passwd.equals(Contrasenaa)) {
		   			 n=id_jugador;

					}
	   			}
					
				}
	   			System.out.println("Consultando ...");
	   } catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}
		   
		   
		return n;
	   }
	  public int getMiVariable() {
	        return id_jugador1;
	    }
     
  
  
}