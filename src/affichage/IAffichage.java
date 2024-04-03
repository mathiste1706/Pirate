package affichage;


import jeu.Case;
import jeu.CaseArme;
import personnages.Pirate;

public interface IAffichage {

	void contexte(Pirate[] listePirates);

	void aQuiTour(Pirate pirate);

	void lancerDe(Pirate pirate, int de);

	void deplacement(Pirate pirate, int de, int nbCases);
	
	void deplacementRhum(Pirate pirate);
	
	void descCase(Pirate pirate, Case caseActuelle);
	
	void appliquerEffetRhum(Pirate pirate);
	
	void appliquerEffetArme(CaseArme caseActuelle, Pirate pirate);

	void debutDuel(Pirate pirateI, Pirate pirateJ, int deI, int deJ);

	void finDuel(Pirate gagnant, Pirate perdant, int degat);

	void finDuelEgalite(Pirate pirateI, Pirate pirateJ);
	
	void gagnantBarque(Pirate pirate);

	void gagnantParDuel(Pirate pirate);
	
	void tousMort();
	 
	void aPeri(Pirate pirate);
}
