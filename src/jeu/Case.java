package jeu;


public class Case {
	private static int compteur=0;
	private int numero;

	public Case() {
		compteur++;
		numero=compteur;
	}

	public int getNumero() {
		return numero;
	}
	
	
	protected boolean isCaseSpeciale() {
	
		return false;
	}
	
}
