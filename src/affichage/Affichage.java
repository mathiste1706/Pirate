package affichage;

import java.util.Scanner;

import jeu.Case;
import jeu.Effet;
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
	
	public static void aQuiTour(String nomPirate) {
		System.out.println("C'est au tour de "+nomPirate+".");
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
			texte.append(" recule"
					);
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
			texte.append(" et trouve du rhum!"+pirate.getIdentite().getNom()+" boit.\n");
		}
		
		else if (caseActuelle.getEffet()==Effet.ARME) {
			texte.append(" et trouve une arme! C'est un "+caseActuelle.getArme().getNom()+ 
					" de force "+caseActuelle.getArme().getForce());
			if (caseActuelle.getArme().getForce()>pirate.getArme().getForce()){
				texte.append(pirate.getIdentite().getNom()+" l'a prend.\n");
			}
			else {
				texte.append(pirate.getIdentite().getNom()+ " en a deja une meilleur.\n");
			}
		}
		else {
			texte.append(".");
		}
		System.out.print(texte);
	}
	
	public static void gagnant(String nomPirate) {
		System.out.println(nomPirate+" a gagne! "+nomPirate+" est le nouveau capitaine!");
	}
	

}
