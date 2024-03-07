package personnages;


public class Pirate {
	private int pv=5;
	private Identite identite;
	private Couleur couleur;
	private Arme arme;
	private int position=1;
	
	public Pirate(Identite identite, Couleur couleur) {
		this.identite=identite;
		this.couleur=couleur;
	}
	
	public int getPv() {
		return pv;
	}
	public Identite getIdentite() {
		return identite;
	}
	
	public Couleur getCouleur() {
		return couleur;
	}
	
	public Arme getArme() {
		return arme;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position=position;
	}
	
}
