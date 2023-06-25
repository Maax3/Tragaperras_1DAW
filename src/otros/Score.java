package otros;

import java.io.*;
import java.util.*;

public class Score {

	public static void setScore(File f, int score) {

		try {
			FileWriter fw = new FileWriter(f, true);
			PrintWriter syso = new PrintWriter(fw);
			syso.println(score);
			syso.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Busca el maximo valor del fichero y lo devuelve

	public static int getScore(File f) {
		int maxScore = 0;
		try {
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				maxScore = Math.max(maxScore, sc.nextInt());
			}
		} catch (Exception e) {
			System.out.println("Error de lectura");
			e.printStackTrace();
		}
		return maxScore;
	}

}
