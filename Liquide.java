public class Liquide {
	

	public static final String[] COULEUR_MAP = {"Rouge", "Bleu", "Vert"};
	private int couleur;


	public Liquide(int couleur) {
		this.couleur = couleur % COULEUR_MAP.length;
	}

	public String[] getCouleurMap () {
		return COULEUR_MAP;
	}
	public int getCouleur() {
		return this.couleur;
	}

	public String getNomCouleur() {
		return COULEUR_MAP[this.couleur % COULEUR_MAP.length];
	}

	@Override
	public String toString() {
		return getNomCouleur();
	}
	public static void main(String[] args) {
		Liquide test = new Liquide(6);
		System.out.println(test.getNomCouleur());
	}
}
