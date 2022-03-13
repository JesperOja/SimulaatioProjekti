package framework; 

import java.util.PriorityQueue;

/**
 * Tapahtumalista-luokka, jossa kasitellaan listaa PriorityQueue avulla.
 *
 * @author Jesper Oja
 */

public class Tapahtumalista {
	private PriorityQueue<Tapahtuma> lista = new PriorityQueue<Tapahtuma>();

	public Tapahtumalista() {

	}

	/**
	 * Listalta poistetaan ensimmainen tapahtuma.
	 *
	 * @return listan ensimmainen tapahtuma
	 */

	public Tapahtuma poista() {
		System.out.println("Tapahtumalistasta poisto " + lista.peek().getTyyppi() + " " + lista.peek().getAika());
		return lista.remove();
	}

	/**
	 * Listalle lisataan uusi tapahtuma, joka annetaan parametrina.
	 *
	 * @param t listalle lisattava tapahtuma.
	 */

	public void lisaa(Tapahtuma t) {
		System.out.println("Tapahtumalistaan lisätään uusi " + t.getTyyppi() + " " + t.getAika());
		lista.add(t);
	}

	/**
	 * Haetaan listalta ensimmaisena olevan tapahtuman aika.
	 *
	 * @return ensimmaisen tapahtuman kellonaika.
	 */

	public double getSeuraavanAika() {
		return lista.peek().getAika();
	}

}
