package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import eduni.distributions.*;
import framework.Kello;
import framework.Tapahtuma;
import framework.Tapahtumalista;

public class Vastaanotto extends Palvelupiste{
	private LinkedList<Asiakas> työntekijät = new LinkedList<Asiakas>();
	private LinkedList<Tapahtuma> ajat = new LinkedList<Tapahtuma>();
	
	
	public Vastaanotto(Normal generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);
		
	}
	
	public LinkedList<Asiakas> getTyöntekijät() {
		return työntekijät;
	}
	@Override
	public void lisaaJonoon(Asiakas a) {
		jono.add(a);
		
	}
	
	@Override
	public Asiakas otaJonosta() {
		
		varattu = false;
		return jono.poll();

	}
	
	@Override
	public void aloitaPalvelu() {
		System.out.println("Aloitetaan uusi palvelu paketille " + jono.peek().getId());


			varattu = true;
		
		
		double palveluaika = generator.sample();
		
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
		ajat.add(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
	}
	
}	
