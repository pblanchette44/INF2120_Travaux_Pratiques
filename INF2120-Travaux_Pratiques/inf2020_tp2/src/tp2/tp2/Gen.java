package tp2;

import tda.Individu;
import tda.Liste;
import tda.Genealogie;

import util.ListeChaine;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Gen<U extends Individu> implements Genealogie<U> {

	private ListeChaine<U> genealogie;

	/**
	 * Constructeur, initialise l'ArrayList genealogie;
	 */
	public Gen() {
		genealogie = new ListeChaine<U>();
	}

	/**
	 * Méthode pour retrouver le nombre d'individu dans la genealogie.
	 * 
	 * @return un entier;
	 */
	@Override
	public int nombreIndividus() {
		int size = genealogie.longueur();
		return size;
	}

	/**
	 * Methode pour obtenir la genealogie au complet;
	 * 
	 * @return une ListeChaine.
	 */
	@Override
	public ListeChaine<U> lesIndividus() {
		return genealogie;
	}

	/**
	 * Cherche un individu en particulier dans la genealogie, en fonction de sa
	 * position dans la liste;
	 * 
	 * @param pos,
	 *            un entier correspond à un individu dans la généalogie;
	 * @return un objet individu;
	 */
	@Override
	public U lIndividu(int pos) throws ArrayIndexOutOfBoundsException {
		U tempIndividu;

		try {
			tempIndividu = genealogie.elementPosition(pos);
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		} catch (NoSuchElementException a) {
			return null;
		}
		return tempIndividu;
	}

	/**
	 * Retourne un la liste des parents d'un individu.
	 * 
	 * @param moi,
	 *            un individu présent dans la généalogie;
	 * @return un ArrayList d'individus correspondant aux parents de l'individu
	 *         entré;
	 */
	@Override
	public ListeChaine<U> lesParents(U moi) {

		ListeChaine<U> parents = new ListeChaine<U>();

		if (moi.leParent1() != -1) {
			parents.insererFin(this.lIndividu(moi.leParent1()));
		} else {
			parents.insererFin(null);
		}

		if (moi.leParent2() != -1) {
			parents.insererFin(this.lIndividu(moi.leParent2()));
		} else {
			parents.insererFin(null);
		}

		return parents;
	}

	/**
	 * Obtient la liste d'individu dans la genealogie qui partage les même
	 * parents que l'individu entré en paramête;
	 * 
	 * @param pos,un
	 *            individu dans la généalogie;
	 * @return un ArrayList d'individus;
	 */
	@Override
	public ListeChaine<U> laFratrie(U moi) {
		
		ListeChaine<U> laFratrie = new ListeChaine<U>();

		laFratrie = lesEnfants(lIndividu(moi.leParent1()), lIndividu(moi.leParent2()));

		
		laFratrie.retirerPosition(moi.laReference());

		return laFratrie;
	}

	public int trouverPosition(U p1, ListeChaine<U> liste) {
		int position = 0;
		for (U e:liste)
		{
			if(e.equals(p1))
			{
				position = e.laReference();
			} 
	
		}
		
		return position;
	}
	

	public ListeChaine<U> comparerParents(U p1, ListeChaine<U> possibleFratrie) {
		
		ListeChaine<U> laFratrie = new ListeChaine<U>();
		for (U e : possibleFratrie) {
			if (e.leParent1() == p1.leParent1() && e.leParent2() == p1.leParent2() && e.leParent1() == p1.leParent2() && e!=p1) {
				laFratrie.insererFin(e);
			}
		}

		return laFratrie;
	}

	/**
	 * Obtient la liste d'individu dans la genealogie qui possèdes les 2 parents
	 * entrés en paramêtres;
	 * 
	 * @param p1,un
	 *            individu dans la généalogie;
	 * @param p2,
	 *            un individu dans la généalogie;
	 * @return un ArrayList d'individus;
	 */
	@Override
	public ListeChaine<U> lesEnfants(U p1, U p2) throws NullPointerException {

		ListeChaine<U> enfants = new ListeChaine<U>();
		
		try {
			for (int i = 0; i < this.nombreIndividus(); i++) {
				if (this.lIndividu(i).leParent1() == p1.laReference()
						&& this.lIndividu(i).leParent2() == p2.laReference()) {
					enfants.insererFin(this.lIndividu(i));
				} else if (this.lIndividu(i).leParent1() == p2.laReference()
						&& this.lIndividu(i).leParent2() == p1.laReference()) {
					enfants.insererFin(this.lIndividu(i));
				}
			}
		} catch (NullPointerException e) {
			try {
				enfants = this.lesEnfants(p1);
			} catch (NullPointerException a) {
				System.out.println("Le parent 1 n'existe pas");
				enfants = this.lesEnfants(p2);
			}

			try {
				enfants = this.lesEnfants(p2);
			} catch (NullPointerException a) {
				System.out.println("Le parent 2 n'existe pas");
				enfants = this.lesEnfants(p1);
			}
		}
		enfants.tri();

		return enfants;

	}

	public ListeChaine<U> lesEnfants(U p1) {
		ListeChaine<U> enfants = new ListeChaine<U>();

		for (int i = 0; i < this.nombreIndividus(); i++) {
			if (this.lIndividu(i).leParent1() == p1.laReference()
					|| this.lIndividu(i).leParent2() == p1.laReference()) {
				enfants.insererFin(this.lIndividu(i));
			}
		}
		return enfants;
	}

	public ListeChaine<U> dateOrder(ListeChaine<U> laListe) {
		for (int i = 0; i < laListe.longueur() - 1; i++) {
			if (laListe.elementPosition(i).laDate().after(laListe.elementPosition(i + 1).laDate())) {
				// 4 5 6 1
				U tempU = laListe.elementPosition(i);
				laListe.insererPosition(laListe.elementPosition(i + 1), i);
				laListe.insererPosition(tempU, i + 1);
			}

		}
		return laListe;
	}

	/**
	 * Ajoute un individu à la généalogie.
	 * 
	 * @param moi,un
	 *            individu;
	 */
	@Override
	public void ajout(U moi) {
		genealogie.insererFin(moi);
		moi.definirLaReference(genealogie.longueur() - 1);
	}

	/**
	 * Définit le parent numéro 1 d'un individu choisit.
	 * 
	 * @param moi,un
	 *            individu;
	 * @param parent1,
	 *            un autre individu dans la généalogie;
	 */
	@Override
	public void definirParent1(U moi, U parent1) throws NullPointerException {
		try {
			moi.definirParent1(parent1.laReference());
		} catch (NullPointerException e) {
			moi.definirParent1(-1);
		}

	}

	/**
	 * Définit le parent numéro 2 d'un individu choisit.
	 * 
	 * @param moi,un
	 *            individu;
	 * @param parent2,
	 *            un autre individu dans la généalogie;
	 */
	@Override
	public void definirParent2(U moi, U parent2) throws NullPointerException {
		try {
			moi.definirParent2(parent2.laReference());
		} catch (NullPointerException e) {
			moi.definirParent2(-1);
		}
	}

	@Override
	public ListeChaine<U> lesPetitsEnfants(U p1, U p2) {

		System.out.println("TestDebug");
		System.out.println(lesEnfants(p1, p2).toString());
		System.out.println(lesEnfants(p1, p2));
		ListeChaine<U> lesPetitsEnfants = obtenirPetitsEnfants(lesEnfants(p1, p2));

		lesPetitsEnfants.tri();

		return lesPetitsEnfants;
	}

	private ListeChaine<U> obtenirPetitsEnfants(ListeChaine<U> tempEnfants) {

		ListeChaine<U> lesPetitsEnfants = new ListeChaine<U>();
		for (U e : tempEnfants) {
			lesPetitsEnfants = insererPetitsEnfants(lesPetitsEnfants, lesEnfants(e));
		}

		return lesPetitsEnfants;
	}

	private ListeChaine<U> insererPetitsEnfants(ListeChaine<U> lesPetitsEnfants, ListeChaine<U> tempList) {

		for (U a : tempList) {
			lesPetitsEnfants.insererFin(a);
		}

		return lesPetitsEnfants;
	}

}
