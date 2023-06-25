package mvc;

public class TragaperrasAPP {

	public static void main(String[] args) {
		// Delay para el splashArt
				try {
				    Thread.sleep(3000); 
				} catch (InterruptedException e) {
				    e.printStackTrace();
				}
		
		Controlador c = new Controlador();
			c.iniciar();
	}

}
