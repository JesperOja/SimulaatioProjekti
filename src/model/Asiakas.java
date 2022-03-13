package model;

import eduni.distributions.Uniform;
import framework.Kello;
import framework.Trace;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Asiakas-luokka, josta luodaan simulattoriin paketit.
 * 
 * @author Jesper Oja
 */

@Entity
@Table(name = "Asiakkaat")
public class Asiakas implements Comparable<Asiakas> {
	@Column(name = "Saapumisaika", columnDefinition = "Decimal(10,2) default '100.00'")
	private double saapumisaika;
	@Column(name = "Poistumisaika", columnDefinition = "Decimal(10,2) default '100.00'")
	private double poistumisaika;
	@Id
	@Column(name = "ID")
	private int id;
	@Transient
	private static int i = 1;
	@Transient
	private static double sum = 0;
	@Column(name = "Priority")
	private int priority;
	@Transient
	private static double counter = 0;
	@Transient
	private Uniform uniform = new Uniform(1, 7);
	@Transient
	private static double keskiarvo;
	/**
	 * Konstruktorissa luodaan uusi paketti, jonka id on yhta suurempi kuin
	 * edellisen. Jokaiselle paketille asetetaan jakauman mukaan jokin prioriteetti.
	 * Saapumisaika vastaa simulaation aikaa, milloin paketti on luotu.
	 */

	public Asiakas() {
		id = i++;
		priority = (int) uniform.sample();
		saapumisaika = Kello.getInstance().getAika();
		System.out.println("Uusi asiakas nro " + id + " saapui klo " + saapumisaika);
	}

	/**
	 * Palauttaa paketin poistumisajan.
	 * 
	 * @return paketin poistumisaika.
	 */

	public double getPoistumisaika() {
		return poistumisaika;
	}

	/**
	 * Palauttaa paketin prioriteetin.
	 * 
	 * @return prioriteetti.
	 */

	public int getPriority() {
		return priority;
	}

	/**
	 * Asettaa paketin poistumisajan.
	 * 
	 * @param poistumisaika aika, jolloin paketti poistui.
	 */

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	/**
	 * Palauttaa paketin saapumisajan.
	 * 
	 * @return saapumisaika.
	 */

	public double getSaapumisaika() {
		return saapumisaika;
	}

	/**
	 * Asettaa paketin saapumisajan.
	 * 
	 * @param saapumisaika aika, jolloin paketti poistui.
	 */

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}

	/**
	 * Palauttaa paketin id:n.
	 * 
	 * @return id.
	 */

	public int getId() {
		return id;
	}
	
	/**
	 * Haetaan varastosta poistuneiden pakettien lukumäärä
	 * @return double - poistuneiden pakettien lukumäärä
	 */
	public double getCounter() {
		return counter;
	}
	
	/**
	 * Haetaan varastoon saapuneiden pakettien määrä
	 * @return int - saapuneiden pakettien määrä
	 */
	public int getSaapuneidenMaara() {
		return i;
	}
	
	/**
	 * Haetaan asiakkaan keskimääräinen läpimenoaika
	 * @return double - keskiarvo
	 */
	public double getKeskimaarainenAika() {
		return keskiarvo;
	}

	/**
	 * Raportin tulostaminen.
	 */

	public void raportti() {
		counter++;
		
		Trace.out(Trace.Level.INFO, "\nPaketti " + id + " valmis!");
		Trace.out(Trace.Level.INFO, "Paketti " + id + " prioriteetti oli " + priority);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " saapui:" + saapumisaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " poistui:" + poistumisaika);
		Trace.out(Trace.Level.INFO, "Asiakas " + id + " viipyi:" + (poistumisaika - saapumisaika));

		sum += (poistumisaika - saapumisaika);
		keskiarvo = sum / counter;
		System.out.println("Pakettien l�pimenoaikojen keskiarvo t�h�n asti " + keskiarvo);
	}

	/**
	 * Asetetaan paketti jonoon prioriteetin ja id mukaan pienemmasta suurempaan.
	 * 
	 * @param o paketti, jota verrataan
	 */
	@Override
	public int compareTo(Asiakas o) {
		if (this.priority <= o.priority && this.id < o.id)
			return -1;
		else if (this.priority == o.priority)
			return 0;
		else if (this.priority < o.priority && this.id > o.id)
			return 0;
		else
			return 1;
	}

}
