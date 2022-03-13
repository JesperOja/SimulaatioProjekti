package model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import eduni.distributions.Normal;
import eduni.distributions.Uniform;
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
public class Hyllytys extends Palvelupiste {
	@Transient
	private Uniform uniform = new Uniform(1, 7);
	@Transient
	private Asiakas[] tilausPR1;
	@Transient
	private Asiakas[] tilausPR2;
	@Transient
	private Asiakas[] tilausPR3;
	@Transient
	private Asiakas[] tilausPR4;
	@Transient
	private Asiakas[] tilausPR5;
	@Transient
	private Asiakas[] tilausPR6;
	@Transient
	private static int counter1 = 0;
	@Transient
	private static int counter2 = 0;
	@Transient
	private static int counter3 = 0;
	@Transient
	private static int counter4 = 0;
	@Transient
	private static int counter5 = 0;
	@Transient
	private static int counter6 = 0;

	/**
	 * Hyllytyksen konstruktorissa maaritellaan paketin ominaisuudet kyseissa
	 * palvelupisteessa.
	 * 
	 * @param generator      maaraa palveluajan
	 * @param tapahtumalista lista tapahtumista
	 * @param tyyppi         tapahtuman tyyppi, johon paketti on menossa
	 */
	public Hyllytys(Normal generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);
		uniform.reseed();
		tilausPR1 = new Asiakas[(int) uniform.sample()];
		tilausPR2 = new Asiakas[(int) uniform.sample()];
		tilausPR3 = new Asiakas[(int) uniform.sample()];
		tilausPR4 = new Asiakas[(int) uniform.sample()];
		tilausPR5 = new Asiakas[(int) uniform.sample()];
		tilausPR6 = new Asiakas[(int) uniform.sample()];
	}

	/**
	 * Paketti lisataan hyllytyksen jonoon.
	 * 
	 * @param a jonoon lisattava paketti
	 */
	@Override
	public void lisaaJonoon(Asiakas a) {
		jono.add(a);
	}

	/**
	 * Paketeista tehty tilaus (eli monta pakettia) poistetaan jonosta.
	 * 
	 * @return poistettava jono
	 */
	public Asiakas[] otaTilausJonosta() {
		varattu = false;

		return tilausJono.poll();
	}

	/**
	 * Aloitetaan palvelu seuraavalle pakettitilaukselle.
	 */
	@Override
	public void aloitaPalvelu() {
		int prio = jono.peek().getPriority();
		System.out.println("Hyllytett��n paketti " + jono.peek().getId());
		switch (prio) {
		case 1:
			if (counter1 < tilausPR1.length) {
				tilausPR1[counter1] = jono.poll();
				counter1++;
			}

			if (counter1 == tilausPR1.length) {
				counter1 = 0;
				lisaaTilaukseen(tilausPR1);

				tilausPR1 = new Asiakas[(int) uniform.sample()];
			}

			break;
		case 2:
			if (counter2 < tilausPR2.length) {
				tilausPR2[counter2] = jono.poll();
				counter2++;
			}

			if (counter2 == tilausPR2.length) {
				counter2 = 0;
				lisaaTilaukseen(tilausPR2);
				tilausPR2 = new Asiakas[(int) uniform.sample()];
			}
			break;
		case 3:
			if (counter3 < tilausPR3.length) {
				tilausPR3[counter3] = jono.poll();
				counter3++;
			}

			if (counter3 == tilausPR3.length) {
				counter3 = 0;
				lisaaTilaukseen(tilausPR3);
				tilausPR3 = new Asiakas[(int) uniform.sample()];
			}
			break;
		case 4:
			if (counter4 < tilausPR4.length) {
				tilausPR4[counter4] = jono.poll();
				counter4++;
			}

			if (counter4 == tilausPR4.length) {
				counter4 = 0;
				lisaaTilaukseen(tilausPR4);
				tilausPR4 = new Asiakas[(int) uniform.sample()];
			}
			break;
		case 5:
			if (counter5 < tilausPR5.length) {
				tilausPR5[counter5] = jono.poll();
				counter5++;
			}

			if (counter5 == tilausPR5.length) {
				counter5 = 0;
				lisaaTilaukseen(tilausPR5);
				tilausPR5 = new Asiakas[(int) uniform.sample()];
			}
			break;
		case 6:
			if (counter6 < tilausPR6.length) {
				tilausPR6[counter6] = jono.poll();
				counter6++;
			}

			if (counter6 == tilausPR6.length) {

				counter6 = 0;
				lisaaTilaukseen(tilausPR6);

				tilausPR6 = new Asiakas[(int) uniform.sample()];
			}
			break;
		}

	}

	/**
	 * Lisataan paketit tilaukseen. Palveluaika maaraytyy jakauman mukaan.
	 * 
	 * @param tilaus pakettitilaus
	 */
	private void lisaaTilaukseen(Asiakas[] tilaus) {

		System.out.println("Tilaus prioriteetill� " + tilaus[0].getPriority() + " on valmis ker�tt�v�ksi! ");
		varattu = true;

		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
		tilausJono.add(tilaus);
	}

}
