

enum COULEUR { PIQUE, COEUR, CARREAU, TREFLE };

public class Carte {

	// Variables de classe

	static public final String[]	NOMS_COULEURS	= { "Pique", "Coeur", "Carreau", "Trefle"};

	static public final String[]	NOMS_RANGS		= { "Joker", "As", "2",
			"3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi" };

	// Variables d'instance

	private final COULEUR  couleur;

	private final int      rang;

	private final int      force;

	public Carte(COULEUR couleur, int rang) {
		this.couleur = couleur;
		this.rang = (rang >= 1 && rang <= 13 ? rang : 0);
		this.force = this.rang;
	}

	public Carte(COULEUR couleur, int rang, int force) {
		this.couleur = couleur;
		this.rang = (rang >= 1 && rang <= 13 ? rang : 0);
		this.force = force;
	}

      
	public COULEUR getCouleur() {
		return this.couleur;
	}

	public int getRang() {
		return this.rang;
	}

	public int getForce() {
		return this.force;
	}

	public String getNomCouleur() {
		switch (this.couleur) {
			case PIQUE:
				return Carte.NOMS_COULEURS[0];
			case COEUR:
				return Carte.NOMS_COULEURS[1];
			case CARREAU:
				return Carte.NOMS_COULEURS[2];
			case TREFLE:
				return Carte.NOMS_COULEURS[3];
		}
		return "<erreur, la couleur de carte n'est pas correct>"; // impossible, mais nÃ©cessaire !
	}

	public String getNomRang() {
		return Carte.NOMS_RANGS[this.rang];
	}

	public String getNomComplet() {
	    if (getRang()==0) return getNomRang();
	    else return getNomRang() + " de " + getNomCouleur();
	}

}