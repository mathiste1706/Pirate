package jeu;

import personnages.Arme;

public class Case {
	private int numero=0;
	private Effet effet;
	private Arme arme;

	public Case(Effet effet) {
		numero++;
		this.effet=effet;
	}
	
	public Case(Effet effet, Arme arme) {
		numero++;
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
