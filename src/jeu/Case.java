package jeu;

public class Case {
	private int numero=0;
	private Effet effet;

	public Case(Effet effet) {
		numero++;
		this.effet=effet;
	}

	public int getNumero() {
		return numero;
	}

	public Effet getEffet() {
		return effet;
	}
	
	
}
