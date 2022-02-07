package model;

import eduni.distributions.Normal;
import framework.Kello;
import framework.Tapahtuma;
import framework.Tapahtumalista;

public class Postitus extends Palvelupiste{

	public Postitus(Normal generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);
		
	}
	public Asiakas[] otaTilausJonosta() {
		varattu = false;
		
		return tilausJono.poll();
	}
	public void lisaaTilausJonoon(Asiakas[] tilaus) {
		tilausJono.add(tilaus);
	}
	
	@Override
	public void aloitaPalvelu() {
		System.out.println("Aloitetaan postittaa tilausta prioriteelilla " + tilausJono.getFirst()[0].getPriority());

		varattu = true;
		double palveluaika = generator.sample();
		tapahtumalista
				.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
	
	}

}
