import java.util.ArrayList;

import tda;

public class Gen<U extends Individu> implements Genealogie {

	private ArrayList<U> genealogie;

	public Gen() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return, retourne le nombre d'individus de la généalogie
	 */
	public int nombreIndividus() {
		return genealogie.size();
	}

	/**
	 * @return, retourne un tableau liste formé des individus de la généalogie
	 */
	public ArrayList<U> lesIndividus() {
		return genealogie;
	}

	/**
	 * @param pos,
	 *            un entier qui est la référence d'un individu dans la
	 *            généalogie @return, retourne l'individu correspondant
	 */
	public U lIndividu(int pos) {
		return genealogie.indexOf(pos);
	}

	/**
	 * @param moi,
	 *            un individu @return, retourne la liste des deux individus
	 *            parents, cette liste peut comporter des éléments nuls si les
	 *            parents correspondants ne sont pas connus
	 */
	public ArrayList<U> lesParents(U moi) {
		ArrayList<U> listeParents;

		listeParents.set(0, genealogie.get(genealogie.get(genealogie.indexOf(moi)).leParent1));
		listeParents.set(1, genealogie.get(genealogie.get(genealogie.indexOf(moi)).leParent2));

		return listeParents;
	}

	/**
	 * @param moi,
	 *            un individu @return, retourne la liste des individus en age
	 *            décroissant, qui ont les mêmes parents que moi, moi ne figure
	 *            pas dans cette liste
	 */
	public ArrayList<U> laFratrie(U moi) {
		return lesEnfants(moi.leParent1(),moi.leParent2());
	}

	/**
	 * @param p1,
	 *            le premier parent
	 * @param pi2,
	 *            le second parent
	 * @return retourne la liste des enfants de ces parents en age décroissant,
	 *         si l'un des parents est null on retourne la liste des enfants de
	 *         l'autre parent
	 */
	public ArrayList<U> lesEnfants(U p1, U p2) {
		
		ArrayList<U> lesEnfants;
		ArrayList<U> lesParents;

		// Sets the list to compare
		if (p1 == null) {
			lesParents.add(p2);
		} else if (p2 = null) {
			lesParents.add(p1);
		} else if (p1 != null && p2 != null) {
			lesParents.add(p1);
			lesParents.add(p2);
		}

		// search for children of formated list
		for (int i = 0; i < lesIndividus().size(); i++) {
			if (lesParents(genealogie.get(i)).equals(lesParents)) {
				lesEnfants.add(genealogie.get(i));
			}
		}
		
		// order the kids in decreasing age
		

		return lesEnfants;
	}

	/**
	 * @param moi,
	 *            un individu qui est ajouté à la généalogie et dont les parents
	 *            sont inconnus
	 */
	public void ajout(U moi)
	{
		genealogie.add(moi);
	}

	/**
	 * @param moi,
	 *            un individu
	 * @param parent1,
	 *            un individu, premier parent de moi
	 */
	public void definirParent1(U moi, U parent1)
	{
		genealogie.get(moi.laReference).definirParent1(parent1);
	}

	/**
	 * @param moi,
	 *            un individu
	 * @param parent2,
	 *            un individu second parent de moi
	 */
	public void definirParent2(U moi, U parent2)
	{
		genealogie.get(moi.laReference).definirParent2(parent2);
	}

}
