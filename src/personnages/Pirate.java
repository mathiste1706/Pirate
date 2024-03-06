package personnages;


public class Pirate {
	private int pv=5;
	private String nom;
	private Couleur couleur;
	private Arme arme;
	private int position=1;
	
	public Pirate(String nom, Couleur couleur) {
		this.nom=nom;
		this.couleur=couleur;
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
	
	public int getPosition() {
		return position;
	}
	
	public void setPosition(int position) {
		this.position=position;
	}
	
}
