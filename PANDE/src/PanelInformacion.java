import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class PanelInformacion extends JFrame {
	
	private JTextArea textArea;
	public PanelInformacion() {
	
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // QUE SE CIERRE AL PRESIONAR LA X DE LA TOLLBAR
		setTitle("PANDEMIC"); // NOMBRE QUE APARECE ARRIBA EN LA TOLLBAR
		setResizable(false); // QUE NO SE PUEDA CAMBIAR EL TAMAÑO DEL PANEL
		setBounds(100, 100, 1200, 710);// POSICION Y TAMAÑO DEL PANEL
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				".\\imagenes utilizadas\\pandemic2.png"));
																																																									// EL																													// DE																													// PANDEMIC																													// NUESTRO
		ImageIcon imagenDeFondo = new ImageIcon(
				".\\imagenes utilizadas\\fondoxd.jpg");
		
		  JScrollPane scrollPane = new JScrollPane();
		    scrollPane.setBounds(270, 200, 660, 400);
		    scrollPane.setOpaque(false);
		    scrollPane.getViewport().setOpaque(false); 
		    scrollPane.setBorder(null);
		    scrollPane.setViewportBorder(null);
		
		    JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
		    scrollBar.setOpaque(false);
		    scrollBar.setBackground(new Color(0, 0, 0, 0));
		 // Crear una instancia de ScrollBarUI
		    ScrollBarUI ui = new BasicScrollBarUI() {
		        // Sobrescribir el método paintTrack para hacer el fondo de la scrollbar transparente
		        protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
		            g.setColor(new Color(0, 0, 0, 0));
		            g.fillRect(r.x, r.y, r.width, r.height);
		        }

		        // Sobrescribir el método paintThumb para establecer el color y la opacidad de la barra de desplazamiento
		        protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
		            Graphics2D g2 = (Graphics2D) g.create();
		            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		            Color color = new Color(200, 255, 0, 128);
		            g2.setPaint(color);
		            g2.fillRect(r.x, r.y, r.width, r.height);

		            g2.dispose();
		        }

		        // Sobrescribir el método paintTrackBorder para hacer el borde de la scrollbar transparente
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
		    Font font = new Font("Arial", Font.BOLD, 16);
		    textArea.setFont(font);
		    textArea.setEditable(false);
		    textArea.setLineWrap(true);
		    textArea.setForeground(Color.GREEN);
		    textArea.setOpaque(false);
		    textArea.append("¡Bienvenido a Pandemic: 48 Ciudades! En este juego, tendrás que encontrar las cuatro vacunas necesarias para vencer a las enfermedades que están asolando el mundo. El objetivo es evitar que el número total de enfermedades activas alcance los 30 o que haya más de 8 brotes, ya que esto significará que has perdido la partida.\r\n"
		    		+ "\r\n"
		    		+ "Para empezar, colócate en una de las ciudades del tablero. Cada ciudad tiene sus propias ciudades colindantes que se conectan mediante líneas que representan las rutas de transporte. Durante la partida, cada jugador tendrá que tomar acciones para desarrollar vacunas y curar enfermedades, utilizando sus cuatro acciones disponibles por ronda (Recuerda que desarrollar una vacuna te quitará las 4 acciones y no podrás hacer nada hasta la siguiente ronda).\r\n"
		    		+ "\r\n"
		    		+ "Durante la primera ronda, se infectarán nueve ciudades. Al inicio de cada ronda siguiente, cuatro ciudades más serán infectadas. Si una ciudad ya tiene cuatro enfermedades activas, se producirá un brote, lo que significa que el número de enfermedades se reducirá a tres, y se infectarán también las ciudades colindantes con una enfermedad adicional.\r\n"
		    		+ "\r\n"
		    		+ "El jugador tendrá que encontrar las cuatro vacunas necesarias para ganar la partida. Para hacerlo, tendrás que desarrollar una vacuna, lo que te permitirá curar todas las enfermedades de un mismo tipo en una ciudad con una sola acción. Cada vez que cures una enfermedad, se reducirá el número de enfermedades activas en esa ciudad. Ten en cuenta que si una ciudad ya ha sido curada de todas sus enfermedades, no podrás volver a curarla hasta que no se infecte de nuevo.\r\n"
		    		+ "\r\n"
		    		+ "Si el número total de enfermedades activas alcanza los 30 o si hay más de 8 brotes, perderás la partida. Por lo tanto, tendrás que ser rápido y eficiente en tus acciones, trabajando en equipo para encontrar las vacunas y curar las enfermedades antes de que sea demasiado tarde.\r\n"
		    		+ "\r\n"
		    		+ "¡Buena suerte y que comience la partida de Pandemic: 48 Ciudades!");
		
		
		
		
		
		
		
		
		
		JLabel labelDeFondo = new JLabel(imagenDeFondo);		
		getContentPane().add(labelDeFondo); 

	}

}


