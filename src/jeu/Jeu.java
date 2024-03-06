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
		
		
		for (int i=0; i<nbJoueurs;i++) {
			listePirates[i]=new Pirate(Nom.values()[i].toString(), Couleur.values()[i]);
		}
	}
	
	public int lanceDe() {
		return random.nextInt(6)+1;
	}
	
	public void deplacerPirate(Pirate pirate, int valeurDe) {
		int nvNum=pirate.getPosition()+valeurDe;
		if (nvNum>plateau.getNbCases()) {
			nvNum=plateau.getNbCases()-(nvNum-plateau.getNbCases());
		}
		pirate.setPosition(nvNum);
	}
	
}