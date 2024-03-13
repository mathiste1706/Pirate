package affichage;

import java.util.Scanner;

import jeu.Case;
import jeu.CaseArme;
import jeu.CaseRhum;
import personnages.Identite;
import personnages.Pirate;

public interface IAffichage {

	void contexte(Pirate[] listePirates);

	void aQuiTour(Pirate pirate);

	void lancerDe(Pirate pirate, int de);

	void deplacement(Pirate pirate, int de, int nbCases);
	
	void appliquerEffet(Case caseActuelle, Pirate pirate);
	
	void appliquerEffet(CaseRhum caseActuelle, Pirate pirate);
	
	void appliquerEffet(CaseArme caseActuelle, Pirate pirate);

	void debutDuel(Pirate pirateI, Pirate pirateJ, int deI, int deJ);

	void finDuel(Pirate gagnant, Pirate perdant, int degat);

	void finDuelEgalite(Pirate pirateI, Pirate pirateJ);
	
	void gagnantBarque(Pirate pirate);

	void gagnantParDuel(Identite identite);
	
	void tousMort();
	 
	void aPeri(Pirate pirate);
}
