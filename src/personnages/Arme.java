package personnages;

public enum Arme {
	POINGS("Poings", 1), SABRE("sabre", 2), PISTOLET("pistolet", 4), MOUSQUET("mousquet", 6);
	
	private String nom;
	private int force;

	Arme(String nom, int force) {
	
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
