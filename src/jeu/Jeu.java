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
		boolean plusDeUnEnVie=true;
		boolean arrivee=false;
		
		// Remplissage de listePirate
		for (int i=0; i<nbJoueurs;i++) {
			listePirates[i]=new Pirate(Identite.values()[i], Couleur.values()[i]);
		}
		
		Affichage.contexte(listePirates);
		
		while (plusDeUnEnVie && !arrivee) {
			
			for (int i=0; i<listePirates.length && (!arrivee && plusDeUnEnVie);i++) {
				// Tour d'un pirate
					tour1Pirate(listePirates[i]);;
					
					if (plateau.getListeCases()[listePirates[i].getPosition()-1].getEffet()==Effet.VICTOIRE) {
						arrivee=true;
						Affichage.gagnantBarque(listePirates[i].getIdentite().getNom());
					}
					else {
						// Duel (les pirates se trouvent a une distance de 3 cases)
						plusDeUnEnVie=duel(listePirates[i]);
					}
				}
			if (!plusDeUnEnVie) {
				gagnantDuel();
				}
				
			}
	}
	
	private int lanceDe() {
		int de=random.nextInt(6)+1;
		de+=random.nextInt(6)+1;
		return de;
	}
	
	private void deplacerPirate(Pirate pirate, int valeurDe) {
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
	
	private void appliquerEffet(Pirate pirate, Plateau plateau) {
		int de;
		Case caseActuelle=plateau.getListeCases()[pirate.getPosition()-1];
		if (caseActuelle.getEffet()==Effet.RHUM) {
			de=lanceDe();
			Affichage.lancerDe(pirate.getIdentite().getNom(), de);
			
			de*=-1;		//Modification du signe pour reculer
			Affichage.deplacement(pirate, de, plateau.getNbCases());
			deplacerPirate(pirate, de);
			caseActuelle=plateau.getListeCases()[pirate.getPosition()-1];
			Affichage.descCase(caseActuelle, pirate);
		}
		else if (pirate.getArme()!=null &&caseActuelle.getEffet()==Effet.ARME && (caseActuelle.getArme().getForce()>pirate.getArme().getForce())){
				pirate.setArme(caseActuelle.getArme());
			
		}
	}
	
	private void tour1Pirate(Pirate pirate) {
		
		int de;
	
		if (pirate.getPv()>0) {
			Affichage.aQuiTour(pirate);
			
			de=lanceDe();
			Affichage.lancerDe(pirate.getIdentite().getNom(), de);
			
			Affichage.deplacement(pirate, de, plateau.getNbCases());
			deplacerPirate(pirate,de);
			Affichage.descCase(plateau.getListeCases()[pirate.getPosition()-1], pirate);
			
			
			appliquerEffet(pirate, plateau);
		}
	}
	
	private int calculerDegat(int difference) {
		int degat;
		if (difference>=10) {
			degat=3;
		}
		else if (difference>=5) {
			degat=2;
		}
		else {
			degat=1;
		}
		return degat;
	}
	
	private void infligerDegat(int difference, Pirate pirate) {
		int nvPv;
		
		
		nvPv=pirate.getPv()-calculerDegat(difference);
		if (nvPv<0) {
			nvPv=0;
		}
		pirate.setPv(nvPv);
	}
	
	
	
	// Duel (les pirates se trouvent a une distance de 3 cases)
	private boolean duel(Pirate initiateur) {
		boolean plusDeUnEnVie = false;
		int deDuelInitiateur;
		int deDuelReac;
		int degatInitiateur;
		int degatReac;
		
		int indexReac = 0;
		Pirate reac=null;
		Pirate[] listeReac=new Pirate[nbJoueurs];
		
		for (int j=0; j<listePirates.length;j++) {
			if (!initiateur.equals(listePirates[j]) && listePirates[j].getPv()>0 && Math.abs(initiateur.getPosition()-listePirates[j].getPosition())<4) {
			
				listeReac[indexReac]=listePirates[j];
				
				indexReac++;
			}
		}
		reac=listeReac[random.nextInt(listeReac.length)];
		
		if (reac!=null) {
			deDuelInitiateur=lanceDe();
			deDuelReac=lanceDe();
			
			degatInitiateur=deDuelInitiateur+initiateur.getArme().getForce();
			degatReac=deDuelReac+reac.getArme().getForce();
			
			Affichage.debutDuel(initiateur, reac, deDuelInitiateur, deDuelReac);
			
			//Pirate reac perd
			if (degatInitiateur>degatReac) {
			
				infligerDegat(degatInitiateur-degatReac, reac);
				Affichage.finDuel(initiateur.getIdentite().getNom(), reac, calculerDegat(degatInitiateur-degatReac));
			}
			// Pirate initiateur perd
			else if (degatReac>degatInitiateur) {
				
				infligerDegat(degatReac-degatInitiateur, initiateur);
				Affichage.finDuel(reac.getIdentite().getNom(), initiateur, calculerDegat(degatReac-degatInitiateur));
				
			}
			// Egalite
			else {
				infligerDegat(0, initiateur);
				infligerDegat(0, reac);
				Affichage.finDuelEgalite(initiateur, reac);
				}
			}
		
				
		// Teste si plus de un pirate est en vie
		for (int j=0; j<listePirates.length;j++)
			if (initiateur.equals(listePirates[j]) && listePirates[j].getPv()>0) {
				plusDeUnEnVie=true;
			}
		return plusDeUnEnVie;
	}
	
	private void gagnantDuel() {
		boolean trouve=false;
		for (int i=0;i<listePirates.length && !trouve;i++) {
			if (listePirates[i].getPv()>0) {
				Affichage.gagnantAllDead(listePirates[i].getIdentite());
				trouve=true;
			}
		}
	}
}