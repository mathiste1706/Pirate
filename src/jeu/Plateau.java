package jeu;


public class Plateau {

	private int nbCases=30;
	private Case[] listeCases;
	
	protected Plateau() {
		listeCases=new Case[nbCases];
		for (int i=0;i<listeCases.length-1;i++) {
			listeCases[i]=new Case(Effet.NEUTRE);
		}
		listeCases[nbCases-1]=new Case(Effet.VICTOIRE);
	}
	protected Plateau(int nbCases, Case[] listeCases) {
		this.nbCases=nbCases;
		this.listeCases=listeCases;
	}
	public int getNbCases() {
		return nbCases;
	}
	public Case[] getListeCases() {
		return listeCases;
	}
	
	
}
 