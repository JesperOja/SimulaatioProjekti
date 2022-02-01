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
	private Asiakas[] tilaus = new Asiakas[(int)uniform.sample()];
	private int counter = 0;
	public Hyllytys(Normal generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);
		uniform.reseed();
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
		
		System.out.println("Aloitetaan uusi palvelu paketille " + jono.peek().getId());
		if(counter<tilaus.length) {
			tilaus[counter] = jono.poll();
			counter++;
		}
		
		if(counter == tilaus.length) {
			counter = 0;
			varattu = true;
		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
		tilausJono.add(tilaus);
		tilaus = new Asiakas[(int)uniform.sample()];
	}}
	
}
