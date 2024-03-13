package jeu;

import personnages.Arme;

public class Plateau {

	private int nbCases=30;
	private Case[] listeCases;
	
	protected Plateau() {
		listeCases=new Case[nbCases];
		for (int i=0;i<listeCases.length;i++) {
			if (i==9 || i==5 ||i==20 ||i==25) {
				listeCases[i]=new CaseRhum();
			}
			
			else if (i==11 || i==26) {
				listeCases[i]=new CaseArme(new Arme("sabre", 2));
			}
			
			else if (i==7 || i==23) {
				listeCases[i]=new CaseArme(new Arme("pistolet", 4));
			}
			
			else if (i==16) {
				listeCases[i]=new CaseArme(new Arme("mousquet", 6));
			}
			else {
				listeCases[i]=new Case();
			}
		}
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
 