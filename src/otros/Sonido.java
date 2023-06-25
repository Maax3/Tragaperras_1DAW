package otros;

import java.io.File;
import javax.sound.sampled.*;

public class Sonido {

	private static Clip cancion;

	/*
	 * Paso1: importas el archivo con File (debe ser WAV) 
	 * Paso2: creas el Flujo de sonido con AudioInputStream y su metodo .getAudio... 
	 * Paso3: Usas la interfaz Clip para: 
	 * --> conseguir una replica del archivo 
	 * --> abrirlo 
	 * --> reproducirlo
	 * PD: Para que el metodo de apagar/encender funcione en otras clases es
	 * necesario globalizar 'Clip'
	 */

	//metodo para clips - sonidos cortos.
	public static void audio(String nombreFichero) {
		try {
			File f = new File(nombreFichero);
			AudioInputStream sound = AudioSystem.getAudioInputStream(f);
			Clip audio = AudioSystem.getClip();
			audio.open(sound);
			audio.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//pasale cualquier nombre de cancion como: "miCancion.wav"
	public static void encenderCancion(String nombre) {
		File f = new File(nombre);
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(f);
			cancion = AudioSystem.getClip();
			cancion.open(audio);
			cancion.loop(Clip.LOOP_CONTINUOUSLY);
			cancion.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void apagarCancion() {

		if (cancion.isRunning()) {
			cancion.stop();
			cancion.flush();
		}
	}

}
