package jeu;

import java.util.Random;

import affichage.JournalDeBord;
import personnages.Arme;
import personnages.Pirate;

public class CaseArme extends CaseSpeciale {

	private Arme arme; 
	public CaseArme(Arme arme) {
		super(Effet.ARME);
		this.arme=arme;
	}
	
	public Arme getArme() {
		return arme;
	}

	public void setArme(Arme arme) {
		this.arme = arme;
	}

	@Override
	protected void appliquerEffet(Pirate pirate, Plateau plateau, Random random, JournalDeBord journal) {
		journal.appliquerEffetArme(this, pirate);
		if (arme.getForce()>pirate.getArme().getForce()){
			pirate.setArme(arme);
		
		}

	}	

}
