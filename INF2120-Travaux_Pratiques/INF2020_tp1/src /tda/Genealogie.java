/**
 * 
 */
package tda;

import java.util.ArrayList;

/**
 * @author Lefebvre Bernard
 *
 */
public interface Genealogie<U extends Individu> {
	
	/**
	 * @return, retourne le nombre d'individus de la généalogie
	 */
	public int nombreIndividus();
	
	/**
	 * @return, retourne un tableau liste formé des individus de la généalogie
	 */
	public ArrayList<U> lesIndividus();
	
	/**
	 * @param pos, un entier qui est la référence d'un individu dans la généalogie 
	 * @return, retourne l'individu correspondant
	 */
	public U lIndividu(int pos);
	
	/**
	 * @param mo, un individu
	 * @return, retourne la liste des deux individus parents, cette liste peut comporter
	 * des éléments nuls si les parents correspondants ne sont pas connus
	 */
	public ArrayList<U> lesParents(U moi);
	
	/**
	 * @param moi, un individu
	 * @return, retourne la liste des individus en age décroissant, qui ont les mêmes parents que moi,
	 * moi ne figure pas dans cette liste
	 */
	public ArrayList<U> laFratrie(U moi);
	
	/**
	 * @param p1, le premier parent
	 * @param pi2, le second parent
	 * @return retourne la liste des enfants de ces parents en age décroissant, si l'un des parents est null
	 * on retourne la liste des enfants de l'autre parent
	 */
	public ArrayList<U> lesEnfants(U p1, U p2);
		
	/**
	 * @param moi, un individu qui est ajouté à la généalogie et dont les parents sont inconnus
	 */
	public void ajout(U moi);

	/**
	 * @param moi, un individu
	 * @param parent1, un individu, premier parent de moi
	 */
	public void definirParent1(U moi, U parent1);
	
	/**
	 * @param moi, un individu
	 * @param parent2, un individu second parent de moi
	 */
	public void definirParent2(U moi, U parent2);
	
}
