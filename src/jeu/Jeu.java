package jeu;



import personnages.Pirate;

public class Jeu {
	private int nbJoueurs=2;
	private Pirate[] listePirates;
	
	
	public Jeu(int nbJoueurs){
		this.nbJoueurs=nbJoueurs;
		listePirates=new Pirate[nbJoueurs];
	}
	public Jeu() {
		listePirates=new Pirate[nbJoueurs];
	}
	public int getnbJoueurs() {
		return nbJoueurs;
	}
	
	
	
}