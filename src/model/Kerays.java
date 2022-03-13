package model;

import javax.persistence.Entity;

import eduni.distributions.Normal;
import framework.Kello;
import framework.Tapahtuma;
import framework.Tapahtumalista;

/**
 * Paketti liikkuu simulaation aika palvelupisteelta toiselle.Kerays on yksi
 * palvelupisteista. Kerayksessa voi olla paketeista muodostunutta jonoa.
 *
 * @author Jesper Oja
 *
 */
@Entity
public class Kerays extends Palvelupiste {

	/**
	 * Kerayksen konstruktorissa maaritellaan paketin ominaisuudet kyseissa
	 * palvelupisteessa.
	 * 
	 * @param generator      maaraa palveluajan
	 * @param tapahtumalista lista tapahtumista
	 * @param tyyppi         tapahtuman tyyppi, johon paketti on menossa
	 */
	public Kerays(Normal generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);
	}

	/**
	 * Aloitetaan palvelu seuraavalle tilaukselle.
	 */
	@Override
	public void aloitaPalvelu() {

		System.out.println("Aloitetaan ker��m��n tilausta prioriteelilla " + tilausJono.getFirst()[0].getPriority());

		varattu = true;
		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));

	}
}
