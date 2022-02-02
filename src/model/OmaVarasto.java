package model;

import framework.Tapahtuma;
import framework.Varasto;

import framework.Kello;

import eduni.distributions.Normal;
import eduni.distributions.Uniform;
import framework.Saapumisprosessi;

public class OmaVarasto extends Varasto {

	private Saapumisprosessi saapumisprosessi;
	public Palvelupiste[] työntekijät;

	public OmaVarasto() {

		palvelupisteet = new Palvelupiste[4];
		työntekijät = new Palvelupiste[6];

		palvelupisteet[1] = new Hyllytys(new Normal(5, 1), tapahtumalista, TapahtumanTyyppi.Hyllytys);
		palvelupisteet[0] = new Vastaanotto(new Normal(5, 2), tapahtumalista, TapahtumanTyyppi.Vastaanotto);
		palvelupisteet[3] = new Postitus(new Normal(6, 2), tapahtumalista, TapahtumanTyyppi.Postitus);
		palvelupisteet[2] = new Keräys(new Normal(3, 1), tapahtumalista, TapahtumanTyyppi.Keräily);

		työntekijät[0] = new Palvelupiste(new Normal(6,1), tapahtumalista, TapahtumanTyyppi.Vastaanotto);
		työntekijät[1] = new Palvelupiste(new Normal(6,1), tapahtumalista, TapahtumanTyyppi.Vastaanotto);
		työntekijät[2] = new Palvelupiste(new Normal(6,1), tapahtumalista, TapahtumanTyyppi.Vastaanotto);
		työntekijät[3] = new Palvelupiste(new Normal(6,1), tapahtumalista, TapahtumanTyyppi.Vastaanotto);
		työntekijät[4] = new Palvelupiste(new Normal(6,1), tapahtumalista, TapahtumanTyyppi.Vastaanotto);
		työntekijät[5] = new Palvelupiste(new Normal(6,1), tapahtumalista, TapahtumanTyyppi.Vastaanotto);
		
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
		case Työntekijä1:
			
			a = työntekijät[0].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			break;
		case Hyllytys:
			b= palvelupisteet[1].otaTilausJonosta();
			palvelupisteet[2].lisaaTilausJonoon(b);
			break;
		case Keräily:
			b = palvelupisteet[2].otaTilausJonosta();
			palvelupisteet[3].lisaaTilausJonoon(b);

			break;
		case Postitus:
			b = palvelupisteet[3].otaTilausJonosta();
			for(Asiakas asiakas: b) {
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
