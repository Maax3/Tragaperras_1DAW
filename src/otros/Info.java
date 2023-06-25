package otros;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import mvc.Vista;

/*
 * Clase que informa sobre todas combinaciones de frutas y su valor.
 */

@SuppressWarnings("serial")
public class Info extends JFrame {
	private Vista v = Vista.getInstance();
	private JPanel p;
	
	public Info () {
		this.setSize(500, 500);
		this.setLocation(v.getX()-500,v.getY()+50);
		this.setResizable(false);
		this.setTitle("Info");
		this.setDefaultCloseOperation(Vista.DISPOSE_ON_CLOSE);
		addPanel();
	}
	
	public void iniciar() {
		this.setVisible(true);
	}
	
	public void addPanel() {
		p = new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(45,31,30));
		p.setBorder((new BevelBorder(BevelBorder.RAISED,Color.GREEN,Color.YELLOW)));
		addTitulo();
		addReglas();
		this.add(p);	
	}
	
	public void addTitulo() {
		JLabel titulo = new JLabel();
		titulo.setBounds(200, 5, 85, 85);
		p.add(titulo);
	}
	
	public void addReglas() {
		JLabel reglas = new JLabel();
		reglas.setBounds(20, 25, 450, 420);
		reglas.setText("<html>"
				+ "<style>"
				+ "h1 {"
				+ "    border: red;"
				+ "    border-style: dashed;"
				+ "	   color: yellow;"
				+ "    text-transform: uppercase;"
				+ "    text-align: center;"
				+ "	   font-size: 22px;}"
				+ "ul {color:white; font-size:13px;}"
				+ "</style>"
				
				+ "		<h1>Combinaciones de frutas</h1>"
				+ "		<ul>"
				+ "			<li>Si aparecen 3 <FONT COLOR=green>'777'</FONT>, ganas 777 puntos.</li>"
				+ "			<li>Si aparecen 2 <FONT COLOR=green>'77'</FONT>, ganas 80 puntos. </li>"
				+ "			<li>Si aparecen 3 &nbsp<FONT COLOR=red>❤️</FONT>ganas 666 puntos. </li>"
				+ "			<li>Si aparecen 2 &nbsp<FONT COLOR=red>❤️</FONT>ganas 60 puntos.</li>"
				+ "			<li>Si aparecen 3 simbolos <FONT COLOR=orange>'cascabel'</FONT>, ganas 60 puntos. </li>"
				+ "			<li>Si aparecen 2 simbolos <FONT COLOR=orange>'cascabel'</FONT>, ganas 25 puntos.</li>"
				+ "			<li>Si aparecen 3 simbolos <FONT COLOR=GRAY>'bar'</FONT>, ganas 40 puntos. </li>"
				+ "			<li>Si aparecen 2 simbolos <FONT COLOR=GRAY>'bar'</FONT>, ganas 15 puntos.</li>"
				+ "			<li>Si aparecen 3 simbolos de una misma <FONT COLOR=#ff00ff>fruta</FONT>, ganas 100 puntos. </li>"
				+ "			<li><FONT COLOR=RED>El combo doble con frutas no da puntos.</FONT></li>"
				+ "			<li><FONT COLOR=RED>X2</FONT> multiplica los puntos 'x2'.</li>"
				+ "			<li><FONT COLOR=RED>X10</FONT> multiplica los puntos 'x10'.</li>"
				+ "		</ul></html>");
		
		reglas.setHorizontalAlignment(JLabel.CENTER);
		p.add(reglas);
	}
}
