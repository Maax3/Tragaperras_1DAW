package mvc;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class Vista extends JFrame{

	//Acceso
	private static Vista miVista;
	//inicio
	public JPanel laminaInicial;
	public JButton comenzar;
	//juego
	public JPanel laminaJuego;
	public JLabel creditos;
	public JLabel titulo;
	public JLabel maxPuntos;
	public JLabel puntos;
	public JButton palanca;
	public JButton ayuda;
	//CSS
	private MatteBorder borde = new MatteBorder(5,5,4,4,new ImageIcon("imagenes/borde1.gif"));
	private Color rojo = new Color(221,30,63);
	private Color negro = new Color(21,21,21);
	private Font fuenteMono = new Font(Font.MONOSPACED, Font.BOLD, 32);
	
	private Vista() {
		this.setSize(800, 600);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Tragaperras");
		this.setDefaultCloseOperation(Vista.EXIT_ON_CLOSE);
		addLaminaInicial();
	}
	
	//Patron Singleton
	//Crea un unico punto de acceso haciendo que todas las demas clases compartan la misma vista
	public static Vista getInstance() {
		if (miVista == null) {
				miVista = new Vista();
		}
		return miVista;
	}
	
	
	public void addLaminaInicial() {
		laminaInicial = new JPanel();
		laminaInicial.setLayout(null);
		laminaInicial.setBackground(negro);
		laminaInicial.setBorder(borde);
		addIMG("imagenes/casino.png",200, 50, 400, 290);
		addIMG("imagenes/coins.gif",220,20, 350, 170);
		addIMG("imagenes/coins.gif",120,300, 350, 170);
		addIMG("imagenes/coins.gif",290,300, 350, 170);
		addBotonComenzar();
		this.add(laminaInicial);
		
	}	
	//Cualquier IMG de la lamina inicial
	public void addIMG(String img, int posX, int posY, int ancho, int alto) {
		JLabel imagen = new JLabel(new ImageIcon(img));
		imagen.setOpaque(false);
		imagen.setBackground(negro);
		imagen.setBounds(posX,posY,ancho,alto);
		laminaInicial.add(imagen);
	}
		
	public void addBotonComenzar() {
		comenzar = new JButton("COMENZAR");
		comenzar.setBounds(295,470,200,50);
		comenzar.setFont(fuenteMono);
		comenzar.setFocusable(false);
		comenzar.setForeground(rojo);
		comenzar.setBorder(new LineBorder(rojo,2));
		comenzar.setBackground(null);
		laminaInicial.add(comenzar);
	}
		
	public void addBotonPalanca() {
		palanca = new JButton("PALANCA");
		palanca.setBounds(700,190,80,175);
		palanca.setHorizontalAlignment(JButton.RIGHT);
		palanca.setContentAreaFilled(false); //evita el dichoso fondo 'azul' cuando pulsas el boton
		palanca.setBorder(new EmptyBorder(0,0,0,0)); //quita los bordes al boton
		palanca.setFocusable(false); //evita mas bordes
		palanca.setIcon(new ImageIcon("imagenes/palanca02.png"));
		laminaJuego.add(palanca);
	}
		
	//--------------------------------SEGUNDA LAMINA---------------------------------
		
	public void addLaminaJuego() {
		laminaJuego = new JPanel();
		laminaJuego.setLayout(null);
		laminaJuego.setBackground(new Color(45,31,30));
		laminaJuego.setBorder(new MatteBorder(10,0,10,0,new ImageIcon("imagenes/borde2.gif")));
		addBotonPalanca();
		titulo();
		addLabelCreditos();
		addLabelScore();
		addLabelScore2();
		addBotonAyuda();
		this.add(laminaJuego);
	}
		
	/*
	 * Vista para los JLabels que contienen las frutas
	 */
	public JLabel addSlot(int posX,String fruta) {
		JLabel slot = new JLabel();
		slot.setOpaque(true);
		slot.setBounds(posX, 200, 170, 170);
		slot.setBorder(new LineBorder(Color.WHITE,2));
		slot.setBackground(Color.GRAY);
		slot.setVerticalAlignment(JLabel.CENTER);
		slot.setHorizontalAlignment(JLabel.CENTER);
		slot.setIcon(new ImageIcon(fruta));
		laminaJuego.add(slot);
		return slot;
	}
	
	public void titulo() {
		titulo = new JLabel("CELIA CASINO");
		titulo.setForeground(rojo);
		titulo.setOpaque(true);
		titulo.setBackground(new Color(0, 34, 46));
		titulo.setFont(fuenteMono);
		titulo.setBounds(250, 90, 300, 80);
		titulo.setBorder(new BevelBorder(BevelBorder.LOWERED,Color.GREEN,Color.YELLOW));
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setVerticalAlignment(JLabel.CENTER);
		laminaJuego.add(titulo);
	}
	
	public JRadioButton addBotonesPuntuacion(int posX) {
		JRadioButton btn = new JRadioButton();
		btn.setBounds(posX,400,80,80);
		btn.setBackground(null); //transparenta el boton
		btn.setHorizontalAlignment(JRadioButton.CENTER);
		btn.setVerticalAlignment(JRadioButton.CENTER);
		laminaJuego.add(btn);
		return btn;
	}
	
	
	//Superposicion improvisada de un JLabel con IMG y otro JLabel de texto
	public void addLabelCreditos() {
		creditos = new JLabel();
		JLabel numero = new JLabel();
		
		creditos.setBounds(50, 436, 50, 50);
		creditos.setFont(new Font(Font.SANS_SERIF,Font.BOLD,32));
		creditos.setForeground(negro);
		creditos.setText("50");
		creditos.setHorizontalAlignment(JLabel.CENTER);
		numero.setIcon(new ImageIcon("imagenes/creditos.png"));
		numero.setBounds(20,400,120,120);
		numero.setBackground(null);
		
		laminaJuego.add(creditos);
		laminaJuego.add(numero);
	}
	
	public void addLabelScore() {
		maxPuntos = new JLabel();
		maxPuntos.setBounds(20, 10, 200, 50);
		maxPuntos.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		maxPuntos.setForeground(rojo);
		laminaJuego.add(maxPuntos);
	}
	
	public void addLabelScore2() {
		puntos = new JLabel();
		puntos.setBounds(20, 30, 200, 50);
		puntos.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		puntos.setForeground(Color.YELLOW);
		laminaJuego.add(puntos);
	}
	
	public void addBotonAyuda() {
		ayuda = new JButton();
		ayuda.setActionCommand("help");
		ayuda.setBounds(680,450,100,100);
		ayuda.setBackground(null); //transparenta el boton
		ayuda.setIcon(new ImageIcon("imagenes/reglas1.png"));
		ayuda.setFocusable(false);
		ayuda.setBorder(null);
		ayuda.setContentAreaFilled(false);
		ayuda.setHorizontalAlignment(JRadioButton.CENTER);
		ayuda.setVerticalAlignment(JRadioButton.CENTER);
		laminaJuego.add(ayuda);
	}
		
}
