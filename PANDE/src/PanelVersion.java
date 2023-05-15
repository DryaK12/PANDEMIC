import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelVersion extends JFrame  {
	private JPanel panel;
	public PanelVersion() {

		

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("VERSION");
		setResizable(false);
		setBounds(520, 270, 350, 350);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				".\\imagenes utilizadas\\pandemic2.png"));
		ImageIcon imagenDeFondo = new ImageIcon(
				".\\imagenes utilizadas\\fondoversion.jpg");
		JLabel labelDeFondo = new JLabel(imagenDeFondo);
		getContentPane().add(labelDeFondo);

	}

}
