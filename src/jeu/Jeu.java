package jeu;

import java.util.Random;

import personnages.Couleur;
import personnages.Identite;
import personnages.Pirate;
import affichage.Affichage;

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
		boolean arrivee=false;
		int de=0;
		int enVie=0;
		String nomPirate="default";
		
		// Remplissage de listePirate
		for (int i=0; i<nbJoueurs;i++) {
			listePirates[i]=new Pirate(Identite.values()[i], Couleur.values()[i]);
		}
		
		Affichage.contexte(listePirates);
		
		while (!allDead && !arrivee) {
			enVie=0;
			for (int i=0; i<listePirates.length && !arrivee && !allDead;i++) {
				// Tour d'un pirate
				if (listePirates[i].getPv()>0) {
					Affichage.aQuiTour(listePirates[i].getIdentite().getNom());
					
					de=lanceDe();
					Affichage.lancerDe(listePirates[i].getIdentite().getNom(), de);
					
					Affichage.deplacement(listePirates[i], de, plateau.getNbCases());
					deplacerPirate(listePirates[i], de);
					Affichage.descCase(plateau.getListeCases()[listePirates[i].getPosition()-1], listePirates[i]);
					
					appliquerEffet(listePirates[i], plateau);
					enVie++;
					
					
					if (plateau.getListeCases()[listePirates[i].getPosition()-1].getEffet()==Effet.VICTOIRE) {
						arrivee=true;
					}
					
					nomPirate=listePirates[i].getIdentite().getNom();
				}
				
				
			}
				
			if (enVie==1) {
				allDead=true;
				
			}
		}
		Affichage.gagnant(nomPirate);
		
	}
	
	public int lanceDe() {
		int de=random.nextInt(6)+1;
		de+=random.nextInt(6)+1;
		return de;
	}
	
	public void deplacerPirate(Pirate pirate, int valeurDe) {
		int nvNum;
		if (valeurDe<0) {
			nvNum=pirate.getPosition()-Math.abs(valeurDe);
			// Recule plus loin que la case Depart
			 if (nvNum<1) {
				nvNum=1;
			}
		}
		else {
			nvNum= pirate.getPosition()+valeurDe;
			// Depasse la case arrivee
			if (nvNum>plateau.getNbCases()) {
				nvNum=plateau.getNbCases()-(nvNum-plateau.getNbCases());
			}
		}
		
		

		pirate.setPosition(nvNum);
	}
	
	public void appliquerEffet(Pirate pirate, Plateau plateau) {
		int de;
		Case caseActuelle=plateau.getListeCases()[pirate.getPosition()-1];
		if (caseActuelle.getEffet()==Effet.RHUM) {
			de=lanceDe();
			Affichage.lancerDe(pirate.getIdentite().getNom(), de);
			
			de*=-1;
			Affichage.deplacement(pirate, de, plateau.getNbCases());
			deplacerPirate(pirate, de);
			caseActuelle=plateau.getListeCases()[pirate.getPosition()-1];
			Affichage.descCase(caseActuelle, pirate);
		}
		else if (pirate.getArme()!=null &&caseActuelle.getEffet()==Effet.ARME && (caseActuelle.getArme().getForce()>pirate.getArme().getForce())){
				pirate.setArme(caseActuelle.getArme());
			
		}
	}
	
}