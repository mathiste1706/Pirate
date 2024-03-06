package personnages;

public enum Nom {
	JACK("Jack le borgne"), JILL("Jill jambe de bois"), JAMES("James crochet"), JEAN("Jean"), 
	BRIAN("O'Brian"), BARBE_NOIRE("BARBE NOIRE"), ANNE("Anne Bonny"), WILLIAM("William Kid"), 
	HELEN("Helen Blamey"), OPAL("Opal Blamey");

	private String chaine;
	private Nom(String chaine) {
	this.chaine = chaine;
	}
	@Override
	public String toString() {
	return chaine;
	}
}
