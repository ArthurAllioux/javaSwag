
import java.util.ArrayList;

public class JeuCartes {

	// Variables d'instance
	/** Le jeu de cartes */
	private ArrayList<Carte>   jeu;

	/**
	 * Construit un jeu de cartes vide.
	 */
	public JeuCartes() {
		jeu = new ArrayList<Carte>();
	}

	/**
	 * Ajoute cette carte au jeu.
	 * @param carte La carte Ã  ajouter
	 * @return Toujours true
	 */
	private boolean addCarte(Carte carte) {
		return this.jeu.add(carte);
	}


	/**
	 * Ajoute toutes les cartes du rang <code>rangDebut</code> au rang
	 * <code>rangFin</code> et de couleur <code>couleur</code>. La force
	 * des cartes est initialisÃ©e par dÃ©faut.
	 * @param rangDebut Rang de la premiÃ¨re carte
	 * @param rangFin Rang de la derniÃ¨re carte
	 * @param couleur Couleur des cartes
	 */
	private void addAllCartes(int rangDebut, int rangFin, COULEUR couleur) {
		for (int i = rangDebut; i <= rangFin; i++) {
			addCarte(new Carte(couleur, i));
		}
	}


	/**
	 * Retourne une reprÃ©sentation textuelle du jeu de cartes
	 * @return La chaÃ®ne formatÃ©e reprÃ©sentant le jeu de cartes
	 */
	public String jeuToStringComplet() {
		// Ici, on utilise un StringBuffer car cette classe permet de construire
		// une chaÃ®ne petit Ã  petit en concatÃ©nant dynamiquement des
		// sous-chaÃ®nes Ã  la fin.
		// Ce choix optimise grandement le nombre d'objets temporaires devant
		// Ãªtre construits.
		StringBuffer s = new StringBuffer();
		String sep = ""; // sÃ©parateur d'Ã©lÃ©ments initialement vide
		s.append("Jeu de " + this.jeu.size());
		s.append(" carte" + (this.jeu.size() > 1 ? "s" : "")); // au pluriel ?
		s.append(" : [");

		for (Carte carte : this.jeu) {
			s.append(sep);
			s.append(carte.getNomComplet());
			if (sep == "")
				sep = ", "; // maintenant on dÃ©finit le sÃ©parateur d'Ã©lÃ©ments
		}
		s.append("]");
		// Ici, on doit maintenant utiliser la mÃ©thode String toString() du
		// StringBuffer
		return s.toString();
	}


	/**
	 * METHODE DE CLASSE: Construit et retourne un jeu de 52 cartes en indiquant
	 * si la force de l'As doit Ãªtre supÃ©rieure Ã  toutes les autres cartes (soit
	 * une force de 14), sinon sa force est la plus petite (soit 1). Les forces
	 * des autres cartes sont initialisÃ©es par dÃ©faut.
	 * @param forceAS_MAX <code>true</code> si la force de l'As est la plus
	 *            grande, <code>false</code> sinon
	 * @return Le jeu de cartes
	 */
	static public JeuCartes createJeuCartes(boolean forceAS_MAX) {

		JeuCartes jeu = new JeuCartes();
		
		jeu.addCarte(new Carte(COULEUR.PIQUE, 1, forceAS_MAX ? 14 : 1));
		jeu.addAllCartes(2, 13, COULEUR.PIQUE);
		jeu.addCarte(new Carte(COULEUR.COEUR, 1, forceAS_MAX ? 14 : 1));
		jeu.addAllCartes(2, 13, COULEUR.COEUR);
		jeu.addCarte(new Carte(COULEUR.CARREAU, 1, forceAS_MAX ? 14 : 1));
		jeu.addAllCartes(2, 13, COULEUR.CARREAU);
		jeu.addCarte(new Carte(COULEUR.TREFLE, 1, forceAS_MAX ? 14 : 1));
		jeu.addAllCartes(2, 13, COULEUR.TREFLE);
		
		return jeu;
	}

	/**
	 * MÃ©lange le jeu de cartes alÃ©atoirement. <i>
	 * 
	 * <pre>
	 * L'instruction suivante fournit une valeur alÃ©atoire entiÃ¨re
	 * comprise entre 0 et (MAX-1) :
	 *    int n = (int) (Math.random() * MAX);
	 * </pre>
	 * 
	 * </i>
	 */
	public void melangeJeu() {
		// On commence par construire une liste pour recevoir les cartes
		// mÃ©langÃ©es
		ArrayList<Carte> jeuMelange = new ArrayList<Carte>();
		int n;
		while (!this.jeu.isEmpty()) {
			// Calcul d'un indice alÃ©atoire dans le jeu de cartes
			n = (int) (Math.random() * this.jeu.size());
			// DÃ©placement d'une carte du jeu vers la liste mÃ©langÃ©es
			jeuMelange.add(this.jeu.remove(n));
		}
		// Remplacement du jeu par le nouveau jeu mÃ©langÃ©
		this.jeu = jeuMelange;
	}

	/**
	 * InsÃ¨re une carte donnÃ©e au cul du talon (i.e. en dessous).
	 * @param card La carte Ã  insÃ©rer
	 */
	public void insertCardCulTalon(Carte card) {
		// Le cul de talon est Ã  l'indice 0
		this.jeu.add(0, card);
	}

	/**
	 * Retourne la carte du dessus du jeu (considÃ©rÃ© comme un talon) en la
	 * supprimant de ce talon si demandÃ©. Si le talon est vide retourne
	 * <code>null</code>.
	 * @param mustBeRemoved Faut-il extraire la carte du talon ?
	 * @return La carte du dessus du talon ou <code>null</code> si le talon
	 *         est vide
	 */
	public Carte getOneCardAsTalon(boolean mustBeRemoved) {
		if (this.jeu.size() > 0) {
			// Retourner la derniÃ¨re carte du talon (lecture ou extraction)
			Carte carte = (mustBeRemoved
										? this.jeu.remove(this.jeu.size() - 1)
										: this.jeu.get(this.jeu.size() - 1));
			return carte;
		}
		else {
			// Il n'y a plus de carte !
			return null;
		}
	}

	/**
	 * UNUSED: Construit et retourne un tableau de cartes du dessus du jeu
	 * (considÃ©rÃ© comme un talon). Le tableau de cartes retournÃ© a toujours une
	 * dimension Ã©gale au nombre de cartes demandÃ©es. Il est complÃ©tÃ© par des
	 * Ã©lÃ©ments nuls si le talon n'a pas assez de cartes.
	 * <p>
	 * L'ordre des cartes du tableau retournÃ© est tel que la premiÃ¨re carte
	 * correspond Ã  la derniÃ¨re du talon (i.e. ordre inverse du talon).
	 * @param nbCartes Nombre de cartes Ã  rÃ©cupÃ©rer
	 * @param mustBeRemoved Faut-il extraire les cartes du talon ?
	 * @return Les cartes du dessus du talon en ordre inverse (complÃ©tÃ©es par
	 *         des Ã©lÃ©ments nuls si le talon n'a pas assez de cartes)
	 */
	public Carte[] getCardsAsTalon(int nbCartes, boolean mustBeRemoved) {
		// Petite prÃ©caution pour commencer !
		if (nbCartes < 0) {
			nbCartes = 0;
		}
		// On dimensionne le tableau Ã  retourner
		Carte[] cartes = new Carte[nbCartes];

		// Ici, on doit mÃ©moriser le nombre initial de cartes car il changera si
		// les cartes doivent Ãªtre extraites mais restera constant dans le cas
		// contraire
		int nbCartesTalon = this.jeu.size();
		// On boucle sur le nombre de cartes Ã  retourner
		for (int n = 0; n < nbCartes; n++) {
			if (n < nbCartesTalon) {
				// Ici, on forme l'indice de la carte concernÃ©e
				int indCarte = nbCartesTalon - 1 - n;
				cartes[n] = (mustBeRemoved
											? this.jeu.remove(indCarte)
											: this.jeu.get(indCarte));
			}
			else {
				// Il n'y a plus de carte dans le talon !
				cartes[n] = null;
			}
		}
		return cartes;
	}

	/**
	 * Teste s'il y a encore au moins une carte dans ce jeu.
	 * @return <code>true</code> s'il y a encore au moins une carte dans ce
	 *         jeu, <code>false</code> sinon
	 */
	public boolean isEmpty() {
		return this.jeu.isEmpty();
	}

}