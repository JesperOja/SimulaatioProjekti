package model;

import framework.Tapahtuma;
import framework.Varasto;

import framework.Kello;

import eduni.distributions.Normal;
import eduni.distributions.Uniform;
import framework.Saapumisprosessi;

public class OmaVarasto extends Varasto {

	private Saapumisprosessi saapumisprosessi;

	public OmaVarasto() {

		palvelupisteet = new Palvelupiste[4];
		

		palvelupisteet[1] = new Hyllytys(new Normal(5, 1), tapahtumalista, TapahtumanTyyppi.Hyllytys);
		palvelupisteet[0] = new Vastaanotto(new Normal(5, 2), tapahtumalista, TapahtumanTyyppi.Vastaanotto);
		palvelupisteet[3] = new Postitus(new Normal(6, 2), tapahtumalista, TapahtumanTyyppi.Postitus);
		palvelupisteet[2] = new Keräys(new Normal(3, 1), tapahtumalista, TapahtumanTyyppi.Keräily);

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
			
			a = palvelupisteet[0].otaJonosta();
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
