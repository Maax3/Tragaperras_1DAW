package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import otros.Info;
import otros.Score;
import otros.Sonido;


public class Controlador implements ActionListener {

	private Vista vista;
	//Atributos para los TimerTasks
	private static int limitador =  0;
	private static int repetidor =  0;
	private static int aux = 0;
	//Atributos de frutas
	private String [] frutas = new String [8]; 
	private JLabel [] labelFrutas = new JLabel[3];
	//Atributos de x2,x4,x10
	private ButtonGroup btnGroup = new ButtonGroup();
	private static JRadioButton [] btnRadio = new JRadioButton [3];
	private String [] btnIconos = new String [6];
	//Atributos del juego
	Juego juego;
	File fichero;
	private int creditos = 50;
	
	
	public void iniciar() {
		vista = Vista.getInstance();
		vista.setVisible(true);
		juego = new Juego();
		Sonido.encenderCancion("audio/intro.wav");
		crearFrutas();
		crearIconos();
		vista.comenzar.addActionListener(this);
		
	}
	
	/*
	 * La idea es la siguiente:
	 * Se crea una vista generica para todos los slots addSlot() que devuelve una instancia de JLabel
	 * Se hace un add de cada instancia a un Array de Jlabels
	 * Ese array de JLabels se utiliza en spin() para poder hacer referencia a cada slot y moverlos
	 * 
	 * Por ultimo, la variable distancia realiza la separacion entre cada JLabel 
	 * y devolverFruta() inicia el JLabel con una fruta aleatoria.
	 */
	
	public void addSlots() {
		JLabel lab;
		int distancia = 85;
		for (int i = 0; i < labelFrutas.length; i++) {
			lab = vista.addSlot(distancia, devolverFruta());
			distancia += 220;
			labelFrutas[i] = lab;
		}
	}
	
	//rellena el array de imagenes de fruta para evitar poner un array de 8 con "imagenes/fruta1.png"
	public void crearFrutas() {
		for (int i = 0; i < frutas.length; i++) {
			frutas[i] = "imagenes/fruta"+(i+1)+".png";
		}
	}
	//crea un array de iconos que luego se usa en crearBotonesPuntuacion() para asignar el boton seleccionado/no seleccionado
	public void crearIconos() {
		for (int i = 0; i < btnIconos.length; i++) {
			btnIconos[i] = "imagenes/x"+(i)+".png";
		}
	}
	
	//Crea un grupo de radioButtons y hace un add su grupo de botones
	//En este metodo se le asigna un nombre a cada boton, un icono por defecto y otro cuando este esta seleccionado
	//Al mismo tiempo se pone como <selecionado> al boton "x2" y se le da CSS/separacion a cada boton
	public void crearBotonesPuntuacion() {
		JRadioButton b;
		int separacion = 200;
		for (int i = 0; i < btnRadio.length; i++) {
			b = vista.addBotonesPuntuacion(separacion);
			separacion += 150;
			b.setIcon(new ImageIcon(btnIconos[i])); //icono por defecto
			b.setSelectedIcon(new ImageIcon(btnIconos[i+3])); //icono cuando esta seleccionado
			b.addActionListener(this);
			btnRadio[i] = b;
			btnGroup.add(b);
				if(i == 0) {
					b.setSelected(true);
				}
		}
		
			
	}
	
	//==> Actua como un pasador entre la primera y segunda lamina.
	//A partir de aqui se inician todos los componentes de la 2da lamina
	public void cambiarLamina() {
		vista.laminaInicial.setVisible(false);
		vista.addLaminaJuego(); //se inicia el JPanel de la segunda pantalla
		addSlots(); 
		crearBotonesPuntuacion();
		vista.palanca.addActionListener(this);
		vista.ayuda.addActionListener(this);
		crearFichero();
		Sonido.audio("audio/comenzar.wav");
		Sonido.apagarCancion();
		Sonido.encenderCancion(elegirCancion());
	}
	
	public void crearFichero() {
		try {
		fichero = new File("score.txt");
		if(!fichero.exists()) 
			fichero.createNewFile();
		
		Scanner sc = new Scanner(fichero);
		while (sc.hasNext()) {
			if (sc.nextInt() > 0)
				vista.maxPuntos.setText("MAX SCORE: "+Score.getScore(fichero));
			}
				sc.close();
		}
		catch (IOException e) {
				e.printStackTrace();
		}
	}
	
	public String elegirCancion() {
		int n = (int) (Math.random()*2);
			return "audio/cancion"+n+".wav";
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()) {
		
			case "COMENZAR":
				cambiarLamina();
				
				break;
				
			case "PALANCA":
				
				moverPalanca("imagenes/palanca02.png",0);
				moverPalanca("imagenes/palanca01.png",25);
					if(getTirada()) {
						resetearSpin();
						spin(0); //num ==> el label de la fruta: 1ero, 2do, o tercero
						spin(1); 
						spin(2);
						Sonido.audio("audio/palanca.wav");
						vista.titulo.setText("");
					}
				
				break;
			case "help":
				 Info info = new Info();
				 	info.iniciar();
				break;
		
		}
		
	}
	/*
	 * Este metodo se ejecuta en Spin(), recorre el array de labels para obtener la ultima imagen de cada slot
	 * Despues se utiliza un casting y el metodo subString para sacar el numero de la ruta de la img.
	 * Ejemplo: imagenes/fruta4.png --> 4
	 * Por ultimo, los numeros obtenidos se almacenan en arrayFrutas y se envian a juego.puntuacion para que puntue correctamente
	 */
	public void addPuntuacion() {
	
		String frutas;
		int numFruta;
		int [] arrayFrutas = new int [3];
		
		for (int i = 0; i < labelFrutas.length; i++) {
	
			frutas = ""+labelFrutas[i].getIcon();
			numFruta = Integer.parseInt(frutas.substring(14, 15));
			arrayFrutas[i] = numFruta;
		}
		
		juego.puntuacion(arrayFrutas[0], arrayFrutas[1], arrayFrutas[2]);
	}
	
	public boolean comprobarTirada() {
		boolean verificar = true;	
		if (creditos == 0) {
				verificar = false;
				finDePartida();
		}
		return verificar;
	}
	

	/*
	 * El primer if comprueba si es posibe retirar creditos usando X4 o MAX.
	 * Si no es posible dara un error, y se comprobaran si los creditos ya estan a 0.
	 * Si estan a 0, se lanza finDepartida() a traves de comprobarTirada()
	 * 
	 * Si es posible retirar creditos de X4 o MAX y los creditos siguen positivos (comprobarTirada == true)
	 * Entonces, se retiraran los creditos segun el RadioButton seleccionado "x2","x4","x10"
	 * 
	 * Sino, se llamara a finDePartida()
	 * 
	 */
	 public boolean getTirada() {
	 boolean verificar = false;
	 
		if ((creditos - Juego.MEJORAx10) < 0 && btnRadio[2].isSelected()
				|| (creditos - Juego.MEJORAx4) < 0 && btnRadio[1].isSelected()){
			//setScore(false)
			JOptionPane.showMessageDialog(vista,"Tus "+creditos+" creditos no son suficientes para realizar esta accion", "Creditos Insuficientes", JOptionPane.ERROR_MESSAGE);
			comprobarTirada();
		}
			else if (creditos > 0) {
				verificar = true;
		
				if(btnRadio[0].isSelected())
					 creditos -= Juego.DEFAULT;
		
				else if(btnRadio[1].isSelected()) 
					 creditos -= Juego.MEJORAx4;
				
				else 
					creditos -= Juego.MEJORAx10;
			 }	
		else
			finDePartida();
		
		vista.creditos.setText(String.valueOf(creditos));
		return verificar;
	}
	
	/*
	 * Controla el score y creditos actuales || sale/resetea d/el programa
	 */
	
	public void finDePartida() {
		int finPartida = JOptionPane.showConfirmDialog(vista, "¿Jugar otra vez?", "Creditos Insuficientes", JOptionPane.YES_NO_OPTION);
		Score.setScore(fichero, juego.getPuntos());
		vista.maxPuntos.setText("MAX SCORE: "+Score.getScore(fichero));
	
		if (finPartida == 0) {
			vista.creditos.setLocation(50, 436);
			creditos = 50;
			vista.creditos.setText("50");
			juego.resetPuntos(0);
			vista.puntos.setText("");
		} else 
			System.exit(0);
	}
	
	
	//slot ==> el label de la fruta: 1ero, 2do, o tercero
	public void spin(int slot) {
		Timer tarea = new Timer();
		TimerTask task = new TimerTask() {
		
			public void run() {
			
				labelFrutas[slot].setIcon(new ImageIcon(devolverFruta())); //asigna una imagen aleatoria de frutas
				limitador++;
				
				/* cuando el limitador llegue a su tope maximo: 
				 * --> se cancela la tarea actual
				 * --> cuando el repetidor llegue a tu tope maximo (3), las frutas dejan de girar
				*/
						
				if (limitador >= 24) {
					tarea.cancel();
					limitador = 0;
					repetidor++;
					
				}
				if (repetidor > 2) {
					tarea.cancel();
					addPuntuacion();
					vista.puntos.setText("SCORE: "+juego.getPuntos());
				}
			}
		};
					tarea.scheduleAtFixedRate(task,5,48);
				
					
	}
	
	/*
	 * Misma idea que la de las frutas, se programa una tarea para intercambiar entre 2 imagenes
	 * Una vez que se llega a su tope maximo, se para. 
	 */
	
	public void moverPalanca(String palanca, int delay) {
		Timer tarea = new Timer();
		TimerTask task = new TimerTask() {
			
			public void run() {
				
				vista.palanca.setIcon(new ImageIcon(palanca));
				aux++;
				
				if (aux == 2) {
					tarea.cancel();
					aux = 0;
				}
			}
			
		};
			tarea.schedule(task,delay,250);
	}
	
	//Devuelve un numero aleatorio para seleccionar una fruta al azar dentro del array de frutas
	public int seleccionarFruta() {
		return (int) (Math.random()*8);
	}
	
	//Devuelve una fruta al azar para que cada vez que se inicie una ventana nueva, las frutas sean distintas.
	public String devolverFruta() {
		return frutas[seleccionarFruta()];
	}
	
	//Resetea todas las variables del metodo spin() para que funcione correctamente
	public void resetearSpin() {
		limitador =  0;
		repetidor =  0;
	}
	
	//Devuelve un array de botones que se usa en la clase Juego para comprobar si es necesario aumentar los puntos o no
	public static JRadioButton [] getBotonesRadio() {
		return btnRadio;
	}

}
