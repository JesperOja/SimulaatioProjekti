package model;

import eduni.distributions.Normal;
import eduni.distributions.Uniform;
import framework.Kello;
import framework.Tapahtuma;
import framework.Tapahtumalista;

public class Hyllytys extends Palvelupiste {

	private Uniform uniform = new Uniform(1, 7);
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
				counter1 = 0;
				lisääTilaukseen(tilausPR1);

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
				lisääTilaukseen(tilausPR2);
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
				lisääTilaukseen(tilausPR3);
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
				lisääTilaukseen(tilausPR4);
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
				lisääTilaukseen(tilausPR5);
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
				lisääTilaukseen(tilausPR6);

				tilausPR6 = new Asiakas[(int) uniform.sample()];
			}
			break;
		}

	}

	private void lisääTilaukseen(Asiakas[] tilaus) {

		System.out.println("Tilaus prioriteetillä " + tilaus[0].getPriority() + " on valmis kerättäväksi! ");
		varattu = true;

		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
		tilausJono.add(tilaus);
	}

}
