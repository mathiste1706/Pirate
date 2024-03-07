package personnages;

public class Arme {

	private String nom;
	private int force;
	
	public Arme(String nom, int force) {
		this.nom = nom;
		this.force = force;
	}
	
	public String getNom() {
		return nom;
	}
	
	public int getForce() {
		return force;
	}
	
}
