/**
 * 
 */
package util;

import java.util.ArrayList;
import java.util.Date;

import tda.Individu;

/**
 * @author lefebvre_b
 *
 */
public class Personne implements Individu {
	
	private String nom;
	private ArrayList<String> prenoms;
	private Date dateNaissance;
	private int reference;
	private int parent1;
	private int parent2;
	
	public Personne(String nom, ArrayList<String> prenoms, Date date, int ref) {
		this.nom = nom;
		this.prenoms = prenoms;
		this.dateNaissance = date;
		this.reference = ref;
		this.parent1 = -1;
		this.parent2 = -1;
	}

	public Personne(String nom, ArrayList<String> prenoms, Date date) {
		this(nom, prenoms, date, -1);
	}
	
	/* (non-Javadoc)
	 * @see tda.Individu#definirParent1(int)
	 */
	@Override
	public void definirParent1(int ref) {
		this.parent1 = ref;
	}

	/* (non-Javadoc)
	 * @see tda.Individu#definirParent2(int)
	 */
	@Override
	public void definirParent2(int ref) {
		this.parent2 = ref;
	}

	/* (non-Javadoc)
	 * @see tda.Individu#definirLaReference(int)
	 */
	@Override
	public void definirLaReference(int ref) {
		this.reference = ref;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * Deux personnes sont égales si elles ont la même référence
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Personne))
			return false;
		Personne other = (Personne) obj;
		if (reference != other.reference)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see tda.Individu#laDate()
	 */
	@Override
	public Date laDate() {
		return dateNaissance;
	}

	/* (non-Javadoc)
	 * @see tda.Individu#laReference()
	 */
	@Override
	public int laReference() {
		return reference;
	}

	/**
	 * @return, retourne le nom de la personne
	 */
	public String leNom() {
		return nom;
	}

	/* (non-Javadoc)
	 * @see tda.Individu#leParent1()
	 */
	public int leParent1() {
		return parent1;
	}
	
	/* (non-Javadoc)
	 * @see tda.Individu#leParent2()
	 */
	public int leParent2() {
		return parent2;
	}
	
	/**
	 * @return, retourne la liste des prénoms de la personne
	 */
	public ArrayList<String> lesPrenoms() {
		return prenoms;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Personne(" + reference + ", " + nom + ", " + prenoms + ", " + dateNaissance + ")";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * Avec cette méthode, les personnes peuvent être classées en fonction de leur date de naissance
	 */
	@Override
	public int compareTo(Individu p) {
		return this.laDate().compareTo(p.laDate());
	}
}