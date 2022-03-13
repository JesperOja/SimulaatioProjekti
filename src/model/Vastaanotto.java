package model;

import javax.persistence.Entity;

import eduni.distributions.*;
import framework.Kello;
import framework.Tapahtuma;
import framework.Tapahtumalista;

/**
 * Paketti liikkuu simulaation aika palvelupisteelta toiselle.Hyllytys on yksi
 * palvelupisteista. Hyllytyksessa voi olla paketeista muodostunutta jonoa.
 *
 * @author Jesper Oja
 *
 */
@Entity
public class Vastaanotto extends Palvelupiste {
	/**
	 * Vastaanoton konstruktorissa maaritellaan paketin ominaisuudet kyseissa
	 * palvelupisteessa.
	 * 
	 * @param generator      maaraa palveluajan
	 * @param tapahtumalista lista tapahtumista
	 * @param tyyppi         tapahtuman tyyppi, johon paketti on menossa
	 */

	public Vastaanotto(Normal generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);

	}

	/**
	 * Aloitetaan palvelu seuraavalle paketille.
	 */
	@Override
	public void aloitaPalvelu() {
		System.out.println("Aloitetaan uusi palvelu paketille " + jono.peek().getId() + " prioriteetilla "
				+ jono.peek().getPriority());

		varattu = true;

		tapahtumalista
				.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + generator.sample()));

	}

}
