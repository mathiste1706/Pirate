package jeu;

import java.util.Random;

import affichage.JournalDeBord;
import personnages.Pirate;

public abstract class CaseSpeciale extends Case {

	private Effet effet;
	
	protected CaseSpeciale(Effet effet) {
		super();
		this.effet=effet;
	}

	public Effet getEffet() {
		return effet;
	}
	
	protected abstract void appliquerEffet(Pirate pirate, Plateau plateau, Random random, JournalDeBord journal);

	@Override
	protected boolean isCaseSpeciale() {
		
		return true;
	}
}
