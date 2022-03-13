package model;

import javax.persistence.Entity;

import eduni.distributions.Normal;
import framework.Kello;
import framework.Tapahtuma;
import framework.Tapahtumalista;

/**
 * Paketti liikkuu simulaation aika palvelupisteelta toiselle.Postitus on yksi
 * palvelupisteista. Postituksessa voi olla paketeista muodostunutta jonoa seka
 * tilausjonoa.
 *
 * @author Jesper Oja
 *
 */
@Entity
public class Postitus extends Palvelupiste {
	/**
	 * Postituksen konstruktorissa maaritellaan paketin ominaisuudet kyseissa
	 * palvelupisteessa.
	 * 
	 * @param generator      maaraa palveluajan
	 * @param tapahtumalista lista tapahtumista
	 * @param tyyppi         tapahtuman tyyppi, johon paketti on menossa
	 */
	public Postitus(Normal generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);

	}

	/**
	 * Aloitetaan palvelu seuraavalle paketille.
	 */
	@Override
	public void aloitaPalvelu() {
		System.out.println("Aloitetaan postittaa tilausta prioriteelilla " + tilausJono.getFirst()[0].getPriority());

		varattu = true;
		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));

	}

}
