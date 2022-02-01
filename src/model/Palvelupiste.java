package model;

import java.util.LinkedList;
import java.util.PriorityQueue;

import eduni.distributions.Normal;
import framework.Kello;
import framework.Tapahtuma;
import framework.Tapahtumalista;

public class Palvelupiste {

	public PriorityQueue<Asiakas> jono = new PriorityQueue<Asiakas>();
	protected LinkedList<Asiakas[]> tilausJono = new LinkedList<Asiakas[]>(); 
	protected Normal generator;
	protected Tapahtumalista tapahtumalista;
	protected TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
	protected final int työntekijäLKM = 5;

	protected boolean varattu = false;

	public Palvelupiste(Normal generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		generator.reseed();
	}

	public void lisaaJonoon(Asiakas a) { // Jonon 1. asiakas aina palvelussa
		jono.add(a);

	}
	
	public Asiakas[] otaTilausJonosta() {
		varattu = false;
		
		return tilausJono.poll();
	}
	public void lisaaTilausJonoon(Asiakas[] tilaus) {
		tilausJono.add(tilaus);
	}
	
	public Asiakas otaJonosta() { // Poistetaan palvelussa ollut
		varattu = false;
		return jono.poll();
	}

	public void aloitaPalvelu() {

			System.out.println("Aloitetaan uusi palvelu paketille " + jono.peek().getId() + " prioriteetilla "+jono.peek().getPriority());

			varattu = true;
			double palveluaika = generator.sample();
			tapahtumalista
					.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika));
		
	}

	public boolean onVarattu() {
		return varattu;
	}

	public boolean onJonossa() {
		return jono.size() != 0;
	}
	
	public boolean onTilausJonossa() {
		return tilausJono.size() != 0;
	}

}