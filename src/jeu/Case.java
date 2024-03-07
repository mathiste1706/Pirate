package jeu;

import personnages.Arme;

public class Case {
	private static int compteur=0;
	private int numero;
	private Effet effet;
	private Arme arme;

	public Case(Effet effet) {
		compteur++;
		numero=compteur;
		this.effet=effet;
	}
	
	public Case(Effet effet, Arme arme) {
		compteur++;
		numero=compteur;
		this.effet=effet;
		this.arme=arme;
	}

	public int getNumero() {
		return numero;
	}

	public Effet getEffet() {
		return effet;
	}
	
	public Arme getArme() {
		return arme;
	}
	
	
}
