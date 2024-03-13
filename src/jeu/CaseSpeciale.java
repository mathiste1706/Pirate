package jeu;

public abstract class CaseSpeciale extends Case {

	private Effet effet;
	
	protected CaseSpeciale(Effet effet) {
		super();
		this.effet=effet;
	}

	public Effet getEffet() {
		return effet;
	}

}
