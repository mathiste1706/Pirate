package jeu;

import java.util.Random;

import affichage.JournalDeBord;
import personnages.Pirate;

public class Case {
	private static int compteur=0;
	private int numero;

	public Case() {
		compteur++;
		numero=compteur;
	}

	public int getNumero() {
		return numero;
	}
	
	protected void appliquerEffet( Pirate pirate, Plateau plateau, Random random, JournalDeBord journal) {
		journal.appliquerEffet(this, pirate);
	}
	
}
