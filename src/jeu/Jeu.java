package jeu;

import java.util.Random;

import personnages.Couleur;
import personnages.Nom;
import personnages.Pirate;

public class Jeu {
	private int nbJoueurs=2;
	private Pirate[] listePirates;
	private Plateau plateau=new Plateau();
	private Random random=new Random();
	
	public Jeu(int nbJoueurs){
		this.nbJoueurs=nbJoueurs;
		listePirates=new Pirate[nbJoueurs];
	}
	public Jeu() {
		listePirates=new Pirate[nbJoueurs];
	}
	public int getnbJoueurs() {
		return nbJoueurs;
	}
	
	public Pirate[] getListePirates() {
		return listePirates;
	}
	
	public Plateau getPlateau() {
		return plateau;
	}
	
	public void start() {
		boolean allDead=false;
		boolean win=false;
		int de=0;
		int enVie=0;
		
		// Remplissage de listePirate
		for (int i=0; i<nbJoueurs;i++) {
			listePirates[i]=new Pirate(Nom.values()[i].toString(), Couleur.values()[i]);
		}
		
		while (!allDead && !win) {
			enVie=0;
			for (int i=0; i<listePirates.length && !win && !allDead;i++) {
				// Tour d'un pirate
				if (listePirates[i].getPv()>0) {
					de=lanceDe();
					deplacerPirate(listePirates[i], de);
					enVie++;
					
					
					if (plateau.getListeCases()[listePirates[i].getPosition()-1].getEffet()==Effet.VICTOIRE) {
						win=true;
					}
				}
				
			}
				
			if (enVie==1) {
				allDead=true;
			}
		}
		System.out.println(win);
	}
	
	public int lanceDe() {
		int de=random.nextInt(6)+1;
		de+=random.nextInt(6)+1;
		return de;
	}
	
	public void deplacerPirate(Pirate pirate, int valeurDe) {
		int nvNum=pirate.getPosition()+valeurDe;
		if (nvNum>plateau.getNbCases()) {
			nvNum=plateau.getNbCases()-(nvNum-plateau.getNbCases());
		}
		pirate.setPosition(nvNum);
	}
	
}