package affichage;

import jeu.Case;
import jeu.CaseArme;
import jeu.CaseRhum;
import personnages.Identite;
import personnages.Pirate;

import java.util.Scanner;

public class JournalDeBord implements IAffichage {
	private static Scanner reader = new Scanner(System.in);


	public void contexte(Pirate[] listePirates) {
		StringBuilder texte = new StringBuilder();
		for (int i = 0; i < listePirates.length - 2; i++) {
			texte.append(listePirates[i].getIdentite().getNom() + ", ");
		}
		texte.append(listePirates[listePirates.length - 2].getIdentite().getNom() + " et "
				+ listePirates[listePirates.length - 1].getIdentite().getNom());
		texte.append(" sont " + (listePirates.length)
				+ " pirates voulant prendre commandement du navire à l’etendard noir « Le sanguinaire ». "
				+ "\nL'equipage les a amenes de nuit sur une ile deserte et ont cache une seule barque.\n"
				+ "Le premier a trouver la barque pourra retourner au navire et devenir le capitaine inconteste!\n");
		System.out.println(texte.toString());
	}

	public void aQuiTour(Pirate pirate) {
		System.out.println("C'est au tour de " + pirate.getIdentite().getNom() + ". " + pirate.getIdentite().getPronom()
				+ " a " + pirate.getPv() + " PV.");
		System.out.println("(Pour lancer les dees appuyer sur Entree)");
		reader.nextLine();
	}

	public void lancerDe(Pirate pirate, int de) {
		System.out.println(pirate.getIdentite().getNom() + " lance les des et fait " + de + ".");
	}

	public void deplacement(Pirate pirate, int de, int nbCases) {
		StringBuilder texte = new StringBuilder();
		int nvNum = pirate.getPosition() + de;

		texte.append(pirate.getIdentite().getNom());
		if (de < 0) {
			texte.append(" recule");
		} else if (nvNum > nbCases) {
			texte.append(" avance de " + (de - (nvNum - nbCases)));
			texte.append(accorderCase(de));

			texte.append(" et recule de " + (nvNum - nbCases));
			texte.append(accorderCase(de));
		}

		else {
			texte.append(" avance");
		}
		texte.append(". ");

		System.out.print(texte.toString());
	}
	
	public void descCase(Pirate pirate, Case caseActuelle) {
		System.out.print(pirate.getIdentite().getPronom() + " tombe sur la case numero " + caseActuelle.getNumero());
	}

	// Utilisation du polymorphisme pour appeler differentes methodes en fonction de si la case est
	// de type Case, CaseRhum, ou CaseArme
	public void appliquerEffet(Case caseActuelle, Pirate pirate) {

		// Pas d'effet, il n'y a que la demande d'appuyer sur Entree
		System.out.println("\n(Appuyer sur Entree)");
		reader.nextLine();

	}
	
	public void appliquerEffet(CaseRhum caseActuelle, Pirate pirate) {

		System.out.println (" et trouve du rhum! " + pirate.getIdentite().getNom() + " boit.");
		System.out.println("\n(Appuyer sur Entree)");
		reader.nextLine();

	}
	
	public void finEffetRhum() {
		System.out.println(".");
	}

	
	public void appliquerEffet(CaseArme caseActuelle, Pirate pirate) {

		System.out.print(" et trouve une arme! C'est un " + caseActuelle.getArme().getNom() + " de force "
				+ caseActuelle.getArme().getForce() + ".\n");
		if (caseActuelle.getArme().getForce() > pirate.getArme().getForce()) {
			System.out.print(pirate.getIdentite().getNom() + " l'a prend");
		} else {
			System.out.print(pirate.getIdentite().getNom() + " en a deja une meilleure");
		}
		System.out.println("\n(Appuyer sur Entree)");
		reader.nextLine();

	}
	

	private static String accorderCase(int de) {
		String texte;
		if (Math.abs(de) == 1) {
			texte = " case";
		} else {
			texte = " cases";
		}
		return texte;
	}


	public void debutDuel(Pirate pirateI, Pirate pirateJ, int deI, int deJ) {
		System.out.println(
				pirateI.getIdentite().getNom() + " et " + pirateJ.getIdentite().getNom() + " se battent en duel!");
		System.out.println("(Pour lancer les dees appuyer sur Entree)");
		reader.nextLine();

		lancerDe(pirateI, deI);
		System.out.println(pirateI.getIdentite().getPronom() + " donne un coup de force "
				+ (deI + pirateI.getArme().getForce()) + "\n");
		lancerDe(pirateJ, deJ);
		System.out.println(pirateJ.getIdentite().getPronom() + " donne un coup de force "
				+ (deJ + pirateJ.getArme().getForce()) + "\n");

	}

	public void finDuel(Pirate gagnant, Pirate perdant, int degat) {
		
		System.out.print(gagnant.getIdentite().getNom() + " gagne le duel et inflige " + degat);
		if (degat == 1) {
			System.out.print(" point ");
		} else {
			System.out.print(" points ");
		}
		System.out.println("de degat a " + perdant.getIdentite().getNom());

		aPeri(perdant);

		System.out.println("(Pour continuer taper sur Entree)");
		reader.nextLine();
	}

	public void finDuelEgalite(Pirate pirateI, Pirate pirateJ) {
		System.out.println("Egalite! " + pirateI.getIdentite().getNom() + " inflige 1 point de degat a "
				+ pirateJ.getIdentite().getNom() + " et " + pirateJ.getIdentite().getNom()
				+ " lui en inflige aussi 1.");
		
		aPeri(pirateI);
		aPeri(pirateJ);
		
		System.out.println("(Pour continuer taper sur Entree)");
		reader.nextLine();
	}
	

	public void gagnantBarque(Pirate pirate) {
		System.out.println(pirate.getIdentite().getNom() + " touve la barge! " + pirate.getIdentite().getNom() + " est le nouveau capitaine!");
	}

	public void gagnantParDuel(Identite identite) {
		System.out.print(identite.getNom() + " est ");
		if (identite.getPronom().equals("Elle")) {
			System.out.print("la derniere");
		} else {
			System.out.print("le dernier");
		}
		System.out.println(" debout! " + identite.getNom() + " est le nouveau capitaine!");
	}
	
	public void tousMort() {
		System.out.println("Tout les pirates sont morts, le capitaine ne sera pas l'un d'entre eux!");
	}
	
	public void aPeri(Pirate pirate) {
		if (pirate.getPv() == 0) {
			System.out.println(pirate.getIdentite().getNom() + " a peri!");
		}
	}

}
