package jeu;

import personnages.Arme;

public class Plateau {

	private int nbCases=30;
	private Case[] listeCases;
	
	protected Plateau() {
		listeCases=new Case[nbCases];
		for (int i=0;i<listeCases.length-1;i++) {
			if (i==9 || i==5 ||i==20 ||i==25) {
				listeCases[i]=new Case(Effet.RHUM);
			}
			else if (i==5 || i==23) {
				listeCases[i]=new Case(Effet.ARME, new Arme("pistolet", 2));
			}
			
			else if (i==11 || i==26) {
				listeCases[i]=new Case(Effet.ARME, new Arme("sabre", 1));
			}
			
			else if (i==16) {
				listeCases[i]=new Case(Effet.ARME, new Arme("mousquet", 3));
			}
			else {
				listeCases[i]=new Case(Effet.NEUTRE);
			}
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
 