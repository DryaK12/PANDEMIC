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
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class PanelNombrePartida extends JFrame implements ActionListener  {
	private JTextField textField;
    private int id_jugador;
    private int id_partida;

	public PanelNombrePartida(int id_jugador) {

		

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("PANDEMIC");
		setResizable(false);
		setBounds(520, 270, 557, 309);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				".\\imagenes utilizadas\\pandemic2.png"));
		ImageIcon imagenDeFondo = new ImageIcon(
				".\\imagenes utilizadas\\fondoNombrePartida.jpg");
		

		textField = new JTextField();
		textField.setBounds(255, 110, 150, 25);
		textField.setOpaque(false);
  
       
		textField.setFont(new Font("Arial", Font.BOLD, 15)); // cambiar la fuente a Arial y poner en negrita con tamaño 20
		textField.setForeground(Color.WHITE); // cambiar el color de la letra a verde

        getContentPane().add(textField);
		
		
		
		
		
		
		/////////////////////////////////////////////////
	
		  ImageIcon iconoNormal1 = new ImageIcon(".\\imagenes utilizadas\\cargarpartida.png");
			ImageIcon iconoPresionado1 = new ImageIcon(".\\imagenes utilizadas\\cargarpartida2.png");
			ImageIcon iconoSobre1 = new ImageIcon(".\\imagenes utilizadas\\cargarpartida2.png");
			JButton botonCrearPartida = new JButton(iconoNormal1); // NOMBRE DEL BOTON MAS LA CREACION		
			botonCrearPartida.setBorder(null);
			botonCrearPartida.setOpaque(true);
			botonCrearPartida.setBackground(new Color(0,0,0,0));
			botonCrearPartida.setBounds(240, 155, iconoNormal1.getIconWidth(), iconoNormal1.getIconHeight());
			botonCrearPartida.setIcon(iconoNormal1);
			botonCrearPartida.setPressedIcon(iconoPresionado1);
			botonCrearPartida.setRolloverIcon(iconoSobre1);
			botonCrearPartida.addMouseListener(new MouseAdapter() {
			    public void mousePressed(MouseEvent e) {
			    	botonCrearPartida.setIcon(iconoPresionado1);
			    	botonCrearPartida.setBorderPainted(false);
			    	botonCrearPartida.setContentAreaFilled(false);
			    }

			    public void mouseEntered(MouseEvent e) {
			    	botonCrearPartida.setIcon(iconoSobre1);
			    	botonCrearPartida.setBorderPainted(false);
			    	botonCrearPartida.setContentAreaFilled(false);
			    }

			    public void mouseExited(MouseEvent e) {
			    	botonCrearPartida.setIcon(iconoNormal1);
			    	botonCrearPartida.setBorderPainted(false);
			    	botonCrearPartida.setContentAreaFilled(false);
			    }
			});
						
			botonCrearPartida.addActionListener(this);
            getContentPane().add(botonCrearPartida);
       
            botonCrearPartida.addActionListener(new ActionListener() {
   			public void actionPerformed(ActionEvent e) {
   			   String nompartida = textField.getText();
   			   
   			   
   			crearnuevapartida(nompartida, id_jugador);
   			id_partida=consultarpartida(nompartida);
   				Audio.sonidoBoton();
   				 dispose();    		
   				 PanelNuevaPartida PanelNuevaPartida = new PanelNuevaPartida(id_jugador,id_partida);
				 PanelNuevaPartida.setVisible(true);
   			}
   		});
			
			
	////////////////////////////////////////////////////////
            ImageIcon iconoNormal2 = new ImageIcon(".\\imagenes utilizadas\\volver.png");
     		ImageIcon iconoPresionado2 = new ImageIcon(".\\imagenes utilizadas\\volver2.png");
     		ImageIcon iconoSobre2 = new ImageIcon(".\\imagenes utilizadas\\volver2.png");
     		JButton botonVolver = new JButton(iconoNormal2); // NOMBRE DEL BOTON MAS LA CREACION		
     		botonVolver.setBorder(null);
     		botonVolver.setOpaque(true);
     		botonVolver.setBackground(new Color(0,0,0,0));
     		botonVolver.setBounds(290, 190, iconoNormal2.getIconWidth(), iconoNormal2.getIconHeight());
     		botonVolver.setIcon(iconoNormal2);
     		botonVolver.setPressedIcon(iconoPresionado2);
     		botonVolver.setRolloverIcon(iconoSobre2);
     		botonVolver.addMouseListener(new MouseAdapter() {
     		    public void mousePressed(MouseEvent e) {
     		    	botonVolver.setIcon(iconoPresionado2);
     		    	botonVolver.setBorderPainted(false);
     		    	botonVolver.setContentAreaFilled(false);
     		    }

     		    public void mouseEntered(MouseEvent e) {
     		    	botonVolver.setIcon(iconoSobre2);
     		    	botonVolver.setBorderPainted(false);
     		    	botonVolver.setContentAreaFilled(false);
     		    }

     		    public void mouseExited(MouseEvent e) {
     		    	botonVolver.setIcon(iconoNormal2);
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
    				
    				 Menu Menu = new Menu(id_jugador); // EL MAIN DONDE SE LLAMA AL MENU
 		            Menu.setVisible(true);    			}
    		});
        
        
			
			
		
		JLabel labelDeFondo = new JLabel(imagenDeFondo);
		getContentPane().add(labelDeFondo);

		
		
		
		
		
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void crearnuevapartida(String nompartida, int id_jugador) {
		
		
		

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
	            
	            
	            System.out.println(id_jugador+"  "+nompartida);
	            String sql = "INSERT INTO partida(idpartida,fechadeinicio, id_jugador,nombrepartida) VALUES (secuencia_idpartida.NEXTVAL,SYSDATE,"+id_jugador+",'"+nompartida+"')";
	            Statement st = con.createStatement();
    			st.execute(sql);
    			
    			System.out.println("Partida registrada correctamente");
    } catch (ClassNotFoundException e) {
		System.out.println("No se ha encontrado el driver " + e);
	} catch (SQLException e) {
		System.out.println("Error en las credenciales o en la URL " + e);
	}

    
    		
	}
	public int consultarpartida(String nompartida) {
		int idpartida=0;
		
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
			 System.out.println("Consultando el numero de partida");
	            stmt = con.createStatement();
	            
	            
	            String sql = "select idpartida from partida where nombrepartida='"+nompartida+"'";
	            Statement st = con.createStatement();
	   			st.execute(sql);
	   			ResultSet rs = st.executeQuery(sql); 	 	

				if (rs.isBeforeFirst()) {
					while (rs.next()) {
	   			
	   			 idpartida = rs.getInt("idpartida");
	   			 
	   		
	   			}
					
				}
	   			System.out.println("Consultando ...");
	   } catch (ClassNotFoundException e) {
			System.out.println("No se ha encontrado el driver " + e);
		} catch (SQLException e) {
			System.out.println("Error en las credenciales o en la URL " + e);
		}
		
		return idpartida;
	}

}
