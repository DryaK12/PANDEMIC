import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
	
   
    public static void sonidoBoton() {
    	try {
    	AudioInputStream sonido = AudioSystem.getAudioInputStream(new File(".\\sonidos\\sonidoBoton.wav"));
    	Clip clip = AudioSystem.getClip();
    	clip.open(sonido);
    	
    	clip.start();
    	
    }
    	catch (Exception xd) {
    		System.out.println("Error al reproducir sonido "+ xd.getMessage());
    	}
    	
    	
    	
    	    	
     
}
    
    
    public static void musicaMenu() {
        try {
         
        	AudioInputStream musica = AudioSystem.getAudioInputStream(new File(".\\sonidos\\menuMusic.wav"));

            // Obtener el clip de audio y reproducirlo en un bucle continuo
            Clip clip = AudioSystem.getClip();
            clip.open(musica);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}