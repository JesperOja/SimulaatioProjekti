package model;

import eduni.distributions.Normal;
import framework.Kello;
import framework.Tapahtuma;
import framework.Tapahtumalista;

public class Keräys extends Palvelupiste{
	
	
	
	public Keräys(Normal generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);
	}

	@Override
	public void aloitaPalvelu() {
		
		System.out.println("Aloitetaan keräämään tilausta prioriteelilla " + tilausJono.getFirst()[0].getPriority() );

		varattu = true;
		double palveluaika = generator.sample();
		tapahtumalista
				.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
	
	}
}
