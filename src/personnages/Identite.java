package personnages;

public enum Identite {
	JACK("Jack le borgne", "Il"), JILL("Jill jambe de bois", "Elle"), JAMES("James crochet", "Il"), JEAN("Jean", "Il"), 
	BRIAN("O'Brian", "Il"), BARBE_NOIRE("BARBE NOIRE", "Il"), ANNE("Anne Bonny", "Elle"), WILLIAM("William Kid", "Il"), 
	HELEN("Helen Blamey", "Elle"), OPAL("Opal Blamey", "Elle");

	private String nom;
	private String pronom;
	private Identite(String nom, String pronom) {
	this.nom = nom;
	this.pronom=pronom;
	}

	public String getNom() {
	return nom;
	}
	
	public String getPronom() {
		return pronom;
	}
}
