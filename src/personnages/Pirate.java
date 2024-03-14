package personnages;


public class Pirate {
	private int pv=5;
	private Identite identite;
	private Couleur couleur;
	private Arme arme=Arme.POINGS;
	private int position=1;
	
	public Pirate(Identite identite, Couleur couleur) {
		this.identite=identite;
		this.couleur=couleur;
	}
	

	public int getPv() {
		return pv;
	}
	public Identite getIdentite() {
		return identite;
	}
	
	public Couleur getCouleur() {
		return couleur;
	}
	
	public Arme getArme() {
		return arme;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void setPv(int pv) {
		this.pv=pv;
	}
	
	public void setArme(Arme arme) {
		this.arme=arme;
	}
	
	public void deplacerPirate(int nbCases, int valeurDe) {
		int nvNum;
		if (valeurDe<0) {
			nvNum=getPosition()-Math.abs(valeurDe);
			// Recule plus loin que la case Depart
			 if (nvNum<1) {
				nvNum=1;
			}
		}
		else {
			nvNum= position+valeurDe;
			
			// Depasse la case arrivee
			if (nvNum>nbCases) {
				nvNum=nbCases-(nvNum-nbCases);
			}
		}
		
		position=nvNum;
	}
	
	
	public int calculerDegat(int difference) {
		int degat;
		if (difference>=10) {
			degat=3;
		}
		else if (difference>=5) {
			degat=2;
		}
		else {
			degat=1;
		}
		return degat;
	}
	
	public void infligerDegat(int difference) {
		int nvPv;
		
		nvPv=pv-calculerDegat(difference);
		if (nvPv<0) {
			nvPv=0;
		}
		pv=nvPv;
	}
}
