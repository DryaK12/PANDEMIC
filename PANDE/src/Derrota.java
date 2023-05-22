import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Derrota extends JFrame implements ActionListener { 
	public int idJugador;
	public JTextArea textArea;
public Derrota(int idpartida) {
	ranking(idpartida);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("PANDEMIC");
		setResizable(false);
		setBounds(200, 270, 350, 350);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				".\\imagenes utilizadas\\pandemic2.png"));
		ImageIcon iconoNormal2 = new ImageIcon(".\\imagenes utilizadas\\botonvolver.png");
 		ImageIcon iconoPresionado2 = new ImageIcon(".\\imagenes utilizadas\\botonvolver2.png");
 		ImageIcon iconoSobre2 = new ImageIcon(".\\imagenes utilizadas\\botonvolver2.png");
 		JButton botonVolver = new JButton(iconoNormal2); // NOMBRE DEL BOTON MAS LA CREACION		
 		botonVolver.setBorder(null);
 		botonVolver.setOpaque(true);
 		botonVolver.setBackground(new Color(0,0,0,0));
 		botonVolver.setBounds(90, 180, iconoNormal2.getIconWidth(), iconoNormal2.getIconHeight());
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
				// Crea una instancia del nuevo panel y lo muestra
				 Menu Menu = new Menu(idJugador);
				Menu.setVisible(true);
			}
		});
		textArea = new JTextArea();
		textArea.setBounds(90, 100, 200,200);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Arial", Font.BOLD, 20));
		textArea.setForeground(Color.WHITE);
		textArea.setOpaque(false);
		textArea.append("HAS PERDIDO");
		 getContentPane().add(textArea);
	

		
		
		
		
		
		
		
		ImageIcon imagenDeFondo = new ImageIcon(
				".\\imagenes utilizadas\\derrota.jpg");
		JLabel labelDeFondo = new JLabel(imagenDeFondo);
		getContentPane().add(labelDeFondo);

		
		
		
		
		
		
		
	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
public void ranking(int idpartida) {
	
	
	

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
            
            
            String sql = "INSERT INTO ranking (id, rondassuperadas, nombrepartida, fecha, resultado) VALUES ( ranking_seq.NEXTVAL, (SELECT rondasjugadas FROM partida WHERE idpartida = "+idpartida+"), (SELECT nombrepartida FROM partida WHERE idpartida = "+idpartida+"), SYSDATE, 'Derrota' )";
            Statement st = con.createStatement();
			st.execute(sql);
			
			System.out.println("Partida registrada correctamente");
} catch (ClassNotFoundException e) {
	System.out.println("No se ha encontrado el driver " + e);
} catch (SQLException e) {
	System.out.println("Error en las credenciales o en la URL " + e);
}


		
}
}
