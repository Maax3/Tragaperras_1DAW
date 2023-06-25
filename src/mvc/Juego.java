package mvc;
import javax.swing.JRadioButton;

public class Juego {
	
	Vista v = Vista.getInstance();
	private int puntosTotales = 0;
	public static final int DEFAULT = 2;
	public static final int MEJORAx4 = 4;
	public static final int MEJORAx10 = 10;
	
	/*
	 * La idea era extraer el numero de las diferentes imagenes/fruta[].png y usarlo para clasificar el resultado
	 * en un switch.
	 * Por tanto, las variables slot1,slot2,slot3 son las ultimas imagenes que salen en los labels traducidas a numeros.
	 * Las variables vienen del metodo addPuntuacion() de la clase Controlador;
	 * 
	 * En este metodo: 
	 * Se hace una separacion de 3 else if para dividir todos los posibles casos en una unica variable
	 * que luego asignara una puntuacion determinada en funcion de su valor
	 * 
	 */

	public void puntuacion(int slot1, int slot2, int slot3) {
	
		if (slot1 == slot2 && slot1 == slot3)
			asignarPuntuacion(slot1,"triple");

		else if (slot2 == slot3 || slot3 == slot2)
			 asignarPuntuacion(slot2,"doble");

		else if ((slot1 == slot2 || slot1 == slot3))
			asignarPuntuacion(slot1,"doble");

	}

	
	// combo == han coincidido 2 frutas o 3 frutas
	private void asignarPuntuacion(int fruta, String combo) {
		
		int puntos = 0;
		
		switch (fruta) {
		case 1:
		case 2:
		case 3:
		case 6:
			if (combo.equals("triple")) {
				puntos = 100;
				setText("¡¡100 puntos!!");
			}else {
				setText("0 puntos "+"\uD83D\uDE2D");
			}
			break;
		
		case 4:
			if (combo.equals("doble")) {
				puntos = 25;
				setText("¡¡+25 puntos!!");
			} else {
				puntos = 60;
				setText("¡¡+60 puntos!!");
			}
			break;

		case 5:
			if (combo.equals("doble")) {
				puntos = 15;
				setText("¡¡+15 puntos!!");
			} else {
				puntos = 40;
				setText("¡¡+40 puntos!!");
			}
			break;

		case 7:
			if (combo.equals("doble")) {
				puntos = 80;
				setText("¡¡+80 puntos!!");
			} else {
				puntos = 777;
				setText("¡¡777 puntos!!");
			}
			break;

		case 8:
			if (combo.equals("doble")) {
				puntos = 60;
				setText("¡¡+60 puntos!!");
			} else {
				puntos = 666;
				setText("¡¡666 puntos!!");
			}
			break;
		}
		
		setPuntos(puntos);
	}
	
	
	private void setText(String txt) {
		v.titulo.setText(txt);
	}
	
	//como switch solo da un unico resultado, la variable puntos se puede usar como parametro
	//y comprobar en funcion del boton seleccionado si es necesario multiplicar el resultado o no

	private void setPuntos(int p) {
		JRadioButton [] r = Controlador.getBotonesRadio();
		
		if (r[1].isSelected()) {
			p *= MEJORAx4;
			v.titulo.setText("¡"+p+" puntos!");
		}
		if (r[2].isSelected()) {
			p *= MEJORAx10;
			v.titulo.setText("¡"+p+" puntos!");
		}
		puntosTotales += p;
	}
	
	public int getPuntos() {
		return puntosTotales;
	}
	
	//se usa a la hora de resetear la partida en FinDePartida() del Controlador.
	public void resetPuntos(int p) {
		puntosTotales = p;
	}
	


	

}
