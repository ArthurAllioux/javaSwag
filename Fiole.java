import java.util.*;

public class Fiole {
	

	public int hauteurMax;
	public ArrayList<Liquide> contenu;


	public Fiole (int hauteurMax) {
		this.hauteurMax = hauteurMax;
		this.contenu = new ArrayList<>();
	}

	public void remplirFiole(Liquide[] remplissage) {
		for (Liquide liquide : remplissage){
			this.contenu.add(liquide);

			// this.contenu.add(new Liquide((int) (Math.random() * 10)));
		}
	}
	public  ArrayList<Liquide> getContenu() {
		return this.contenu;
	}
	public int getQuantiteLiquide() {
		return this.contenu.size();
	}

	public void transferOne(Fiole fioleArrive){
		Liquide liquideTransfere = this.contenu.remove(this.contenu.size() -1 );
		fioleArrive.getContenu().add(fioleArrive.getContenu().size(),liquideTransfere);
	}

	public void transferMultiple (Fiole fioleArrive){
		Fiole fioleDepart = this;
		ArrayList<Liquide> contenuDepart =fioleDepart.getContenu();
		int i = fioleDepart.getQuantiteLiquide() -1 ;
		int topCouleur = contenuDepart.get(fioleDepart.getQuantiteLiquide()-1).getCouleur();
		while (topCouleur == contenuDepart.get(i).getCouleur() && fioleArrive.getQuantiteLiquide() < fioleDepart.hauteurMax) {
			this.transferOne(fioleArrive);
			i = fioleDepart.getQuantiteLiquide() -1 ;
		}
	}

	@Override
	public String toString() {
		return contenu.toString();
	}

	public static void main(String[] args) {
		Fiole f1 = new Fiole(5);
		Liquide[] remplissage = new Liquide[]{new Liquide((int) (Math.random() * 10)), new Liquide((int) (Math.random() * 10)), new Liquide((int) (Math.random() * 10)), new Liquide((int) (Math.random() * 10)), new Liquide((int) (Math.random() * 10))};
		Liquide[] remplissage2 = new Liquide[]{new Liquide((int) (Math.random() * 10)), new Liquide((int) (Math.random() * 10)), new Liquide((int) (Math.random() * 10)), new Liquide((int) (Math.random() * 10)), new Liquide((int) (Math.random() * 10))};
		Liquide[] remplissage3 = new Liquide[]{new Liquide((int) (Math.random() * 10)), new Liquide((int) (Math.random() * 10)),new Liquide((int) (Math.random() * 10)),new Liquide((int) (Math.random() * 10))};
		f1.remplirFiole(
			remplissage
		);
		Fiole f2 = new Fiole(5);
		f2.remplirFiole(remplissage2);
		Fiole f3 = new Fiole(5);
		f3.remplirFiole(remplissage3);
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3);
		f2.transferMultiple(f3);
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3);
	}

}
