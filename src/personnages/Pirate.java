package personnages;

import jeu.Case;

public class Pirate {
	private int pv=5;
	private String nom;
	private Couleur couleur;
	private Arme arme;
	private Case casePlateau;
	
	public Pirate(String nom) {
		this.nom=nom;
	}
	
	public int getPv() {
		return pv;
	}
	public String getNom() {
		return nom;
	}
	
	public Couleur getCouleur() {
		return couleur;
	}
	
	public Arme getArme() {
		return arme;
	}
	
	public Case getCasePlateau() {
		return casePlateau;
	}
	
	public void setCasePlateau(Case casePlateau) {
		this.casePlateau=casePlateau;
	}
	
}
