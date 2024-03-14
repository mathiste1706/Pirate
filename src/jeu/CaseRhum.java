package jeu;

import java.util.Random;

import affichage.JournalDeBord;
import personnages.Pirate;

public class CaseRhum extends CaseSpeciale {

	public CaseRhum() {
		super(Effet.RHUM);
	}

	@Override
	protected void appliquerEffet(Pirate pirate, Plateau plateau, Random random, JournalDeBord journal) {
		int de;
		
		journal.appliquerEffetRhum(pirate);
		
		de=De.lanceDe(random);
		journal.lancerDe(pirate, de);
		
		de*=-1;		//Modification du signe pour reculer
		journal.deplacement(pirate, de, plateau.getNbCases());
		pirate.deplacerPirate(plateau.getNbCases(), de);
		
		journal.descCase(pirate, plateau.getListeCases()[pirate.getPosition()-1]);
		
		plateau.getListeCases()[pirate.getPosition()-1].appliquerEffet(pirate, plateau, random, journal);

	}

}
