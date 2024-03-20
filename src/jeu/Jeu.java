package jeu;

import java.util.Random;

import personnages.Couleur;
import personnages.Identite;
import personnages.Pirate;
import affichage.JournalDeBord;

public class Jeu {
	private int nbJoueurs=2;
	private Pirate[] listePirates;
	private Plateau plateau=new Plateau();
	private JournalDeBord journal=new JournalDeBord();
	private Random random=new Random();
	
	public int getNbJoueurs() {
		return nbJoueurs;
	}
	
	public Jeu(int nbJoueurs){
		this.nbJoueurs=nbJoueurs;
		listePirates=new Pirate[nbJoueurs];
	}
	public Jeu() {
		listePirates=new Pirate[nbJoueurs];
	}
	
	public Pirate[] getListePirates() {
		return listePirates;
	}
	
	public Plateau getPlateau() {
		return plateau;
	}
	
	public void start() {
		boolean plusDeUnEnVie=true;
		boolean arrivee=false;
		int indexReac;
		
		listePirates=remplissageListePirate();
		
		
		journal.contexte(listePirates);
		
		while (plusDeUnEnVie && !arrivee) {
			
			for (int i=0; i<listePirates.length && (!arrivee && plusDeUnEnVie);i++) {
				
				Pirate[] listeReac=new Pirate[nbJoueurs-1];
				
				// Tour d'un pirate
					tour1Pirate(listePirates[i]);
					
					if (listePirates[i].getPosition()==plateau.getNbCases()) {
						arrivee=true;
						journal.gagnantBarque(listePirates[i]);
					}
					else {
						// Duel (les pirates se trouvent a une distance de 3 cases)
						
						indexReac=checkDuel(listePirates[i], listeReac);
			
						if (indexReac!=-1) {		// S'il y a au moins un pirate a une distance de 3 cases
							plusDeUnEnVie=duel(listePirates[i], listeReac, indexReac);
						}
					}
				}
			if (!plusDeUnEnVie) {
				gagnantDuel();
				}
				
			}
	}
	
	private Pirate[] remplissageListePirate() {
		
		for (int i=0; i<nbJoueurs;i++) {
			listePirates[i]=new Pirate(Identite.values()[i], Couleur.values()[i]);
		}
		return listePirates;
		
	}
	
	
	private void tour1Pirate(Pirate pirate) {
		
		int de;
	
		if (pirate.getPv()>0) {
			journal.aQuiTour(pirate);
			
			de=De.lanceDe(random);
			journal.lancerDe(pirate, de);
			
			journal.deplacement(pirate, de, plateau.getNbCases());
			
			pirate.deplacerPirate(plateau.getNbCases(), de);
			
			journal.descCase(pirate, plateau.getListeCases()[pirate.getPosition()-1]);
			if (plateau.getListeCases()[pirate.getPosition()-1].isCaseSpeciale()) {
				((CaseSpeciale) plateau.getListeCases()[pirate.getPosition()-1]).appliquerEffet(pirate, plateau, random, journal);
			}
			else {
				journal.appuieSurEntree();
			}
		}
	}
	
	
	
	private int checkDuel(Pirate initiateur, Pirate[] listeReac) {
		int indexReac=-1;
		if (initiateur.getPv()>0) {
			for (int j=0; j<listePirates.length;j++) {
				if (!initiateur.equals(listePirates[j]) && listePirates[j].getPv()>0 && Math.abs(initiateur.getPosition()-listePirates[j].getPosition())<4) {
					
					indexReac++;	
					listeReac[indexReac]=listePirates[j];
							
				}
			}
		}
		return indexReac;
				
	}
	
	// Duel (les pirates se trouvent a une distance de 3 cases)
	private boolean duel(Pirate initiateur, Pirate[] listeReac, int indexReac) {
		boolean plusDeUnEnVie = false;
		int deDuelInitiateur;
		int deDuelReac;
		int degatInitiateur;
		int degatReac;
		int enVie=0;
		
		Pirate reac=listeReac[random.nextInt(indexReac+1)];
		
		
		deDuelInitiateur=De.lanceDe(random);
		deDuelReac=De.lanceDe(random);
		
		degatInitiateur=deDuelInitiateur+initiateur.getArme().getForce();
		degatReac=deDuelReac+reac.getArme().getForce();
		
		journal.debutDuel(initiateur, reac, deDuelInitiateur, deDuelReac);
		
		//Pirate reac perd
		if (degatInitiateur>degatReac) {
		
			reac.infligerDegat(degatInitiateur-degatReac);
			journal.finDuel(initiateur, reac, reac.calculerDegat(degatInitiateur-degatReac) );
		}
		// Pirate initiateur perd
		else if (degatReac>degatInitiateur) {
			
			initiateur.infligerDegat(degatReac-degatInitiateur);
			journal.finDuel(reac, initiateur, initiateur.calculerDegat(degatReac-degatInitiateur));
			
		}
		// Egalite
		else {
			initiateur.infligerDegat(0);
			reac.infligerDegat(0);
			journal.finDuelEgalite(initiateur, reac);
			}
		
				
		// Teste si plus de un pirate est en vie
		for (int j=0; j<listePirates.length && enVie<3;j++)
			if (listePirates[j].getPv()>0) {
				enVie++;
			}
		if (enVie>1) {
			plusDeUnEnVie=true;
		}
		return plusDeUnEnVie;
	}
	
	private void gagnantDuel() {
		boolean trouve=false;
		for (int i=0;i<listePirates.length && !trouve;i++) {
			if (listePirates[i].getPv()>0) {
				journal.gagnantParDuel(listePirates[i]);
				trouve=true;
			}
		}
		if (!trouve) {
			journal.tousMort();
		}
	}
}