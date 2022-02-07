package model;

import framework.Tapahtuma;
import framework.Varasto;

import framework.Kello;

import eduni.distributions.Normal;
import framework.Saapumisprosessi;

public class OmaVarasto extends Varasto {

	private Saapumisprosessi saapumisprosessi;

	public OmaVarasto() {
		työntekijät = new Palvelupiste[8];
		
		työntekijät[0] = new Palvelupiste(new Normal(8,3), tapahtumalista, TapahtumanTyyppi.Työntekijä1);
		työntekijät[1] = new Palvelupiste(new Normal(9,4), tapahtumalista, TapahtumanTyyppi.Työntekijä2);
		työntekijät[2] = new Palvelupiste(new Normal(7,4), tapahtumalista, TapahtumanTyyppi.Työntekijä3);
		työntekijät[3] = new Palvelupiste(new Normal(6,4), tapahtumalista, TapahtumanTyyppi.Työntekijä4);
		työntekijät[4] = new Palvelupiste(new Normal(8,5), tapahtumalista, TapahtumanTyyppi.Työntekijä5);
		työntekijät[5] = new Palvelupiste(new Normal(10,5), tapahtumalista, TapahtumanTyyppi.Työntekijä6);
		työntekijät[6] = new Palvelupiste(new Normal(11,6), tapahtumalista, TapahtumanTyyppi.Työntekijä7);
		työntekijät[7] = new Palvelupiste(new Normal(7,5), tapahtumalista, TapahtumanTyyppi.Työntekijä8);
		
		palvelupisteet = new Palvelupiste[4];

		palvelupisteet[1] = new Hyllytys(new Normal(8, 3), tapahtumalista, TapahtumanTyyppi.Hyllytys);
		palvelupisteet[0] = new Vastaanotto(new Normal(5, 2), tapahtumalista, TapahtumanTyyppi.Vastaanotto);
		palvelupisteet[3] = new Postitus(new Normal(9, 3), tapahtumalista, TapahtumanTyyppi.Postitus);
		palvelupisteet[2] = new Keräys(new Normal(6, 5), tapahtumalista, TapahtumanTyyppi.Keräily);
		
		saapumisprosessi = new Saapumisprosessi(2, tapahtumalista, TapahtumanTyyppi.Saapuminen);

	}

	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava();
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) {
		Asiakas a;
		Asiakas[] b;
		switch (t.getTyyppi()) {

		case Saapuminen:
			palvelupisteet[0].lisaaJonoon(new Asiakas());
			saapumisprosessi.generoiSeuraava();

			break;
		case Vastaanotto:
			int counter = 0;
			for(int i = 0; i<Palvelupiste.työntekijäLKM; i++) {
				if(!työntekijät[i].onVarattu() && palvelupisteet[0].onJonossa()) {
					a = palvelupisteet[0].otaJonosta();
					työntekijät[i].lisaaJonoon(a);
				}else {
					counter++;
				}
			}
			if(counter == Palvelupiste.työntekijäLKM) {
				System.out.println("Jokainen työntekijä on varattu");
				tapahtumalista.lisaa(new Tapahtuma(TapahtumanTyyppi.Vastaanotto, Kello.getInstance().getAika()+Math.random()));
			}
			break;
		case Työntekijä1:

			a = työntekijät[0].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
		case Työntekijä2:

			a = työntekijät[1].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
		case Työntekijä3:

			a = työntekijät[2].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
		case Työntekijä4:

			a = työntekijät[3].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
		case Työntekijä5:

			a = työntekijät[4].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
		case Työntekijä6:

			a = työntekijät[5].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
		case Työntekijä7:

			a = työntekijät[6].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
		case Työntekijä8:

			a = työntekijät[7].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
		case Hyllytys:
			b = palvelupisteet[1].otaTilausJonosta();
			palvelupisteet[2].lisaaTilausJonoon(b);
			break;
		case Keräily:
			b = palvelupisteet[2].otaTilausJonosta();
			palvelupisteet[3].lisaaTilausJonoon(b);

			break;
		case Postitus:
			b = palvelupisteet[3].otaTilausJonosta();
			for (Asiakas asiakas : b) {
				asiakas.setPoistumisaika(Kello.getInstance().getAika());
				asiakas.raportti();
			}
		}
	}

	@Override
	public void tulokset() {
		System.out.println("\nSimulointi päättyi kello " + Kello.getInstance().getAika());
		System.out.println("Tulokset ... puuttuvat vielä");
	}

}
