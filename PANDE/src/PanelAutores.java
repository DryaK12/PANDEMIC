import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelAutores extends JFrame {
	private JPanel panel;
	public PanelAutores() {
	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("DESARROLLADORES");
		setResizable(false);
		setBounds(390, 270, 626, 450);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				".\\imagenes utilizadas\\pandemic2.png"));
		ImageIcon imagenDeFondo = new ImageIcon(
				".\\imagenes utilizadas\\fondoautores.jpg");
		JLabel labelDeFondo = new JLabel(imagenDeFondo);
		labelDeFondo.setBounds(0, 0, 500, 671);
		getContentPane().add(labelDeFondo);

	}

}
