package jeu;

import java.util.Random;

public class De {

	// Pour ne pas avoir de constructeur implicite
	private De() {
		
	}
	
	public static int lanceDe(Random random) {
		int de=random.nextInt(6)+1;
		de+=random.nextInt(6)+1;
		return de;
	}

}
