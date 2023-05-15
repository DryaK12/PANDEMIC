import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

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

public class Puntuaciones extends JFrame {
	private JPanel panel;
	private JTextArea textArea;
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	public Puntuaciones() {
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("RANKING");
		setResizable(false);
		setBounds(100, 100, 360, 660);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				".\\imagenes utilizadas\\pandemic2.png"));
		ImageIcon imagenDeFondo = new ImageIcon(
				".\\imagenes utilizadas\\Puntuacion.jpg");
		
		System.setOut(ps);
	    Ranking.select();
	    String xd = baos.toString();		        
	    System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
	        
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(60, 58, 250, 448);
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

	            Color color = new Color(0, 0, 255, 128);
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
	    textArea.setEditable(false);
	    textArea.setLineWrap(true);
	    textArea.setForeground(Color.WHITE);
	    textArea.setOpaque(false);
	    textArea.append(xd);
		
				
		
		JLabel labelDeFondo = new JLabel(imagenDeFondo); // CREAMOS UN LABEL PARA LA IMAGEN DE FONDO
		labelDeFondo.setBounds(0, 0, 350, 622); // UBICACION Y TAMAÑO DEL LABEL DEL FONDO
		getContentPane().add(labelDeFondo); // LO AÑADIMOS AL PANEL
	}
}
