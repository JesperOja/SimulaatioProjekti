package model;

import java.util.LinkedList;
import java.util.PriorityQueue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.DiscriminatorFormula;

import eduni.distributions.Normal;
import framework.Kello;
import framework.Tapahtuma;
import framework.Tapahtumalista;

/**
 * Paketti liikkuu simulaation aika palvelupisteelta toiselle. Palvelupisteella
 * voi olla paketeista muodostunutta jonoa.
 *
 * @author Jesper Oja
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table (name = "Palvelupisteet")
@DiscriminatorFormula("'Palvelupisteet'")
public class Palvelupiste {
	@Transient
	public PriorityQueue<Asiakas> jono = new PriorityQueue<Asiakas>();
	@Transient
	protected LinkedList<Asiakas[]> tilausJono = new LinkedList<Asiakas[]>();
	@Transient
	protected Normal generator;
	@Transient
	protected Tapahtumalista tapahtumalista;
	@Id
	@GeneratedValue
	protected int id;
	@Enumerated(EnumType.STRING)
	@Column(name = "Palvelupiste")
	protected TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
	@Column(name = "Palveluaika", columnDefinition = "Decimal(10,2) default '100.00'")
	protected double palveluaika;
	@Transient
	protected boolean varattu = false;
	@Transient
	protected double sample;

	/**
	 * Palvelupisteen konstruktorissa maaritellaan paketin ominaisuudet
	 * palvelupisteessa.
	 * 
	 * @param generator      maaraa palveluajan
	 * @param tapahtumalista lista tapahtumista
	 * @param tyyppi         tapahtuman tyyppi, johon paketti on menossa
	 */

	public Palvelupiste(Normal generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;

	}

	/**
	 * Palvelupisteen konstruktori
	 */

	public Palvelupiste() {

	}

	/**
	 * Paketti lisataan palvelupisteen jonoon.
	 * 
	 * @param a jonoon lisattava paketti
	 */
	public void lisaaJonoon(Asiakas a) { // Jonon 1. asiakas aina palvelussa
		jono.add(a);

	}

	/**
	 * Otetaan asiakkaista muodostettu tilaus pois tilausjonosta.
	 * 
	 * @return poistettava tilaus
	 */
	public Asiakas[] otaTilausJonosta() {
		varattu = false;

		return tilausJono.poll();
	}

	/**
	 * Paketeista tehty tilaus (eli monta pakettia) lisataan jonoon.
	 * 
	 * @param tilaus lisattava tilaus
	 */
	public void lisaaTilausJonoon(Asiakas[] tilaus) {
		tilausJono.add(tilaus);
	}

	/**
	 * Poistetaan jonon ensimmainen paketti ja asetetaan palvelupiste vapaaksi.
	 * 
	 * @return poistettava paketti
	 */
	public Asiakas otaJonosta() {
		varattu = false;
		return jono.poll();
	}

	/**
	 * Aloitetaan palvelu seuraavalle paketille.
	 */
	public void aloitaPalvelu() {

		System.out.println("Aloitetaan uusi palvelu paketille " + jono.peek().getId() + " prioriteetilla "
				+ jono.peek().getPriority());

		varattu = true;
		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));

	}

	/**
	 * Onko palvelupisteessa paketti eli onko palvelupiste varattu.
	 * 
	 * @return kylla tai ei
	 */

	public boolean onVarattu() {
		return varattu;
	}

	/**
	 * Onko palvelupisteessa jonoa.
	 * 
	 * @return kylla tai ei
	 */
	public boolean onJonossa() {
		return jono.size() != 0;
	}

	/**
	 * Onko tilausjonossa tilauksia
	 * 
	 * @return kylla tai ei
	 */
	public boolean onTilausJonossa() {
		return tilausJono.size() != 0;
	}

	/**
	 * Palauttaa palvelupisteen nimen, johon paketti siirretaan.
	 * 
	 * @return palvelupisteen nimi, johon paketti siirtyy
	 */

	public TapahtumanTyyppi getTyyppi() {
		return skeduloitavanTapahtumanTyyppi;
	}

}
