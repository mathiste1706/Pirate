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
		
		journal.deplacementRhum(pirate);
		pirate.reculerPirate(de);
		
		journal.descCase(pirate, plateau.getListeCases()[pirate.getPosition()-1]);
		

	}

}
