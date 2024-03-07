package affichage;

import jeu.Case;
import jeu.Effet;
import personnages.Pirate;

public class Affichage {
	
	public String AQuiTour(Pirate pirate) {
		StringBuilder texte=new StringBuilder();
		texte.append("C'est au tour de "+pirate.getNom()+".\n");
		return texte.toString();
	}
	public  String deplacement(String nomPirate, int de) {
		StringBuilder texte=new StringBuilder();
		texte.append(nomPirate);
		if (de<0) {
			texte.append(" recule ");
		}
		else {
			texte.append(" avance ");
		}
		texte.append("de "+Math.abs(de));
		
		if (Math.abs(de)==1) {
			texte.append(" case");
		}
		else {
			texte.append("cases");
		}
		return texte.toString();
	}
	
	public String desCase(Case caseActuelle, Pirate pirate) {
		StringBuilder texte=new StringBuilder();
		texte.append(", tombe sur la case numero "+caseActuelle.getNumero());
		
		if (caseActuelle.getEffet()==Effet.RHUM) {
			texte.append(" et trouve du rhum!"+pirate.getNom()+" boit et ");
		}
		
		if (caseActuelle.getEffet()==Effet.ARME) {
			texte.append(" et trouve une arme! C'est un "+caseActuelle.getArme().getNom());
			if (caseActuelle.getArme().getForce())>pirate.getArme().getForce()){
				texte.append(pirate.getNom()+"l'a prend");
			}
		}
		
		return null;
		
	}

}
