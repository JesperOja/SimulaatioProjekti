package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

import eduni.distributions.Normal;
import eduni.distributions.Uniform;
import framework.Kello;
import framework.Tapahtuma;
import framework.Tapahtumalista;

public class Hyllytys extends Palvelupiste {

	private Uniform uniform = new Uniform(1, 6);
	private Asiakas[] tilausPR1;
	private Asiakas[] tilausPR2;
	private Asiakas[] tilausPR3;
	private Asiakas[] tilausPR4;
	private Asiakas[] tilausPR5;
	private Asiakas[] tilausPR6;
	private static int counter1 = 0;
	private static int counter2 = 0;
	private static int counter3 = 0;
	private static int counter4 = 0;
	private static int counter5 = 0;
	private static int counter6 = 0;

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

	@Override
	public void lisaaJonoon(Asiakas a) {
		jono.add(a);
	}

	public Asiakas[] otaTilausJonosta() {
		varattu = false;

		return tilausJono.poll();
	}

	@Override
	public void aloitaPalvelu() {
		int prio = jono.peek().getPriority();
		System.out.println("Hyllytettään paketti " + jono.peek().getId());
		switch (prio) {
		case 1:
			if (counter1 < tilausPR1.length) {
				tilausPR1[counter1] = jono.poll();
				counter1++;
			}

			if (counter1 == tilausPR1.length) {

				System.out.println("Tilaus prioriteetillä " + tilausPR1[0].getPriority() + " on valmis kerättäväksi! ");
				counter1 = 0;
				varattu = true;
				double palveluaika = generator.sample();
				tapahtumalista.lisaa(
						new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
				tilausJono.add(tilausPR1);
				tilausPR1 = new Asiakas[(int) uniform.sample()];
			}
			break;
		case 2:
			if (counter2 < tilausPR2.length) {
				tilausPR2[counter2] = jono.poll();
				counter2++;
			}

			if (counter2 == tilausPR2.length) {

				System.out.println("Tilaus prioriteetillä " + tilausPR2[0].getPriority() + " on valmis kerättäväksi! ");
				counter2 = 0;
				varattu = true;
				double palveluaika = generator.sample();
				tapahtumalista.lisaa(
						new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
				tilausJono.add(tilausPR2);
				tilausPR2 = new Asiakas[(int) uniform.sample()];
			}
			break;
		case 3:
			if (counter3 < tilausPR3.length) {
				tilausPR3[counter3] = jono.poll();
				counter3++;
			}

			if (counter3 == tilausPR3.length) {

				System.out.println("Tilaus prioriteetillä " + tilausPR3[0].getPriority() + " on valmis kerättäväksi! ");
				counter3 = 0;
				varattu = true;
				double palveluaika = generator.sample();
				tapahtumalista.lisaa(
						new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
				tilausJono.add(tilausPR3);
				tilausPR3 = new Asiakas[(int) uniform.sample()];
			}
			break;
		case 4:
			if (counter4 < tilausPR4.length) {
				tilausPR4[counter4] = jono.poll();
				counter4++;
			}

			if (counter4 == tilausPR4.length) {

				System.out.println("Tilaus prioriteetillä " + tilausPR4[0].getPriority() + " on valmis kerättäväksi! ");
				counter4 = 0;
				varattu = true;
				double palveluaika = generator.sample();
				tapahtumalista.lisaa(
						new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
				tilausJono.add(tilausPR4);
				tilausPR4 = new Asiakas[(int) uniform.sample()];
			}
			break;
		case 5:
			if (counter5 < tilausPR5.length) {
				tilausPR5[counter5] = jono.poll();
				counter5++;
			}

			if (counter5 == tilausPR5.length) {

				System.out.println("Tilaus prioriteetillä " + tilausPR5[0].getPriority() + " on valmis kerättäväksi! ");
				counter5 = 0;
				varattu = true;
				double palveluaika = generator.sample();
				tapahtumalista.lisaa(
						new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
				tilausJono.add(tilausPR5);
				tilausPR5 = new Asiakas[(int) uniform.sample()];
			}
			break;
		case 6:
			if (counter6 < tilausPR6.length) {
				tilausPR6[counter6] = jono.poll();
				counter6++;
			}

			if (counter6 == tilausPR6.length) {
				System.out.println("Tilaus prioriteetillä " + tilausPR6[0].getPriority() + " on valmis kerättäväksi! ");
				counter6 = 0;
				varattu = true;
				double palveluaika = generator.sample();
				tapahtumalista.lisaa(
						new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
				tilausJono.add(tilausPR6);
				tilausPR6 = new Asiakas[(int) uniform.sample()];
			}
			break;
		}

	}
/*
	private void lisääTilaukseen(Asiakas[] tilaus, int counter) {

		tilaus[counter] = jono.poll();
	}
	
	 * if(counter == tilaus.length) { System.out.println("\n\n   " + counter +
	 * "   \n\n"); System.out.println("Tilaus prioriteetillä "+
	 * tilaus[0].getPriority()+" on valmis kerättäväksi! "); counter = 0; varattu =
	 * true; double palveluaika = generator.sample(); tapahtumalista.lisaa(new
	 * Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+
	 * palveluaika)); tilausJono.add(tilaus); tilaus = new
	 * Asiakas[(int)uniform.sample()]; }
	 * 
	 * 
	 * }
	 */

}
