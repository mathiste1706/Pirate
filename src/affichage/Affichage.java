package affichage;

import java.util.Scanner;

import jeu.Case;
import jeu.Effet;
import personnages.Identite;
import personnages.Pirate;

public class Affichage {
	
	// Constructeur prive pr q'un constructeur ne soit pas creer automatiquement, alors que la classe est static
	private Affichage() {}
	
	private static Scanner reader=new Scanner(System.in);
	
	public static void contexte(Pirate[] listePirates) {
		StringBuilder texte=new StringBuilder();
		for (int i=0; i<listePirates.length-2;i++) {
			texte.append(listePirates[i].getIdentite().getNom()+", ");
		}
		texte.append(listePirates[listePirates.length-2].getIdentite().getNom()+" et "
		+listePirates[listePirates.length-1].getIdentite().getNom());
		texte.append(" sont "+(listePirates.length)+" pirates voulant prendre commandement du navire à l’etendard noir « Le sanguinaire ». "
				+ "\nL'equipage les a amenes de nuit sur une ile deserte et ont cache une seule barque.\n"
				+ "Le premier a trouver la barque pourra retourner au navire et devenir le capitaine inconteste!\n");
		System.out.println(texte.toString());
	}
	
	public static void aQuiTour(Pirate pirate) {
		System.out.println("C'est au tour de "+pirate.getIdentite().getNom()+". "+pirate.getIdentite().getPronom()+" a "
	+pirate.getPv()+" PV.");
		System.out.println("(Pour lancer les dees appuyer sur Entree)");
		reader.nextLine();
	}
	
	public static void lancerDe(String nomPirate, int de) {
		System.out.println(nomPirate+" lance les des et fait "+de+".");
	}
	
	
	public static void deplacement(Pirate pirate, int de, int nbCases) {
		StringBuilder texte=new StringBuilder();
		int nvNum=pirate.getPosition()+de;
		
		texte.append(pirate.getIdentite().getNom());
		if (de<0) {
			texte.append(" recule");
		}
		else if (nvNum>nbCases) {
			texte.append(" avance de "+(de-(nvNum-nbCases)));
			texte.append(accorderCase(de));
			
			texte.append(" et recule de "+(nvNum-nbCases));
			texte.append(accorderCase(de));
		}
		
		else {
			texte.append(" avance");
		}
		
		
		System.out.printf(texte.toString());
	}
	
	public static void  descCase(Case caseActuelle, Pirate pirate) {
		
		System.out.print(". "+pirate.getIdentite().getPronom()+ " tombe sur la case numero "+caseActuelle.getNumero());
		
		appliquerEffet(caseActuelle, pirate);
		System.out.println("\n(Appuyer sur Entree)");
		reader.nextLine();
	
	}
	private static String accorderCase(int de) {
		String texte;
		if (Math.abs(de)==1) {
			texte=" case";
		}
		else {
			texte=" cases";
		}
		return texte;
	}
	
	private static void appliquerEffet(Case caseActuelle, Pirate pirate) {
		StringBuilder texte=new StringBuilder();
		if (caseActuelle.getEffet()==Effet.RHUM) {
			texte.append(" et trouve du rhum! "+pirate.getIdentite().getNom()+" boit.\n");
		}
		
		else if (caseActuelle.getEffet()==Effet.ARME) {
			texte.append(" et trouve une arme! C'est un "+caseActuelle.getArme().getNom()+ 
					" de force "+caseActuelle.getArme().getForce()+".\n");
			if (pirate.getArme() ==null || caseActuelle.getArme().getForce()>pirate.getArme().getForce()){
				texte.append(pirate.getIdentite().getNom()+" l'a prend.\n");
			}
			else {
				texte.append(pirate.getIdentite().getNom()+ " en a deja une meilleure.\n");
			}
		}
		else {
			texte.append(".");
		}
		System.out.print(texte);
	}
	
	public static void debutDuel(Pirate pirateI, Pirate pirateJ, int deI, int deJ) {
		System.out.println(pirateI.getIdentite().getNom()+" et "+pirateJ.getIdentite().getNom()+" se battent en duel!");
		System.out.println("(Pour lancer les dees appuyer sur Entree)");
		reader.nextLine();
		
		lancerDe(pirateI.getIdentite().getNom(), deI);
		System.out.println(pirateI.getIdentite().getPronom()+ " donne un coup de force "+(deI+pirateI.getArme().getForce()));
		lancerDe(pirateJ.getIdentite().getNom(), deJ);
		System.out.println(pirateJ.getIdentite().getPronom()+ " donne un coup de force "+(deJ+pirateJ.getArme().getForce()));
		
		
	}
	
	public static void finDuel(String gagnant, String perdant, int degat) {
		System.out.print(gagnant+" gagne le duel et inflige "+degat);
		if (degat==1) {
			System.out.print(" point ");
		}
		else {
			System.out.print(" points ");
		}
		System.out.println("de degat a "+perdant);
		System.out.println("(Pour continuer taper sur Entree)");
		reader.nextLine();
	}
	
	public static void finDuelEgalite(String nomPirateI, String nomPirateJ) {
		System.out.println("Egalite! "+nomPirateI+" inflige 1 point de degat a "+nomPirateJ+" et "+nomPirateJ+" lui en inflige aussi 1.");
		
		System.out.println("(Pour continuer taper sur Entree)");
		reader.nextLine();
	}
	
	public static void gagnantBarque(String nomPirate) {
		System.out.println(nomPirate+" touve la barge! "+nomPirate+" est le nouveau capitaine!");
	}
	
	public static void gagnantAllDead(Identite identite) {
		System.out.print(identite.getNom()+" est le ");
		if (identite.getPronom().equals("Elle")) {
			System.out.print("derniere");
		}
		else {
			System.out.print("dernier");
		}
		System.out.println(" debout! "+identite.getNom()+" est le nouveau capitaine!");
	}
	

}
