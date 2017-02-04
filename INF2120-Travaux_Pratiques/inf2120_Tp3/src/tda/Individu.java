/**
 * 
 */
package tda;

import java.util.Date;

/**
 * @author lefebvre_b
 *
 */
public interface Individu extends Comparable<Individu> {
	
	/**
	 * @return, retourne la date de naissance de l'individu
	 */
	public Date laDate();
	
	/**
	 * @return, retourne un entier référençant de manière unique l'individu,
	 * peut être -1 si la référence n'est pas connue.
	 */
	public int laReference();
	
	/**
	 * @param ref, un entier permettant de définir de manière unique la référence de l'individu
	 */
	public void definirLaReference(int ref);
	
	/**
	 * @return, retourne le premier parent de l'individu
	 * -1 si le premier parent n'est pas connu
	 */
	public Individu leParent1();
	
	/**
	 * @return, retourne le second parent de l'individu
	 * -1 si le second parent n'est pas connu
	 */
	public Individu leParent2();
	
	/**
	 * @param p, le premier parent
	 */
	public void definirParent1(Individu p);
	
	/**
	 * @param p, le second parent
	 */
	public void definirParent2(Individu p);
	

}
