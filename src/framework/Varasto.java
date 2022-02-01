package framework;

import model.Palvelupiste;

public abstract class Varasto {
	private double simulointiaika = 0;
	
	private Kello kello;
	
	protected Tapahtumalista tapahtumalista;
	protected Palvelupiste[] palvelupisteet;
	
	public Varasto() {
		kello = Kello.getInstance();
		
		tapahtumalista = new Tapahtumalista();
	}
	
	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}
	
	public void aja() {
		alustukset();
		while(simuloidaan()) {
			System.out.println("\nA-vaihe: kello on " + nykyaika());
			kello.setAika(nykyaika());
			
			System.out.println("\nB-vaihe:");
			suoritaBTapahtumat();
			
			System.out.println("\nC-vaihe:");
			yritaCTapahtumat();
		}
	}
	
	private void suoritaBTapahtumat(){
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()){
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	private void yritaCTapahtumat(){
		for (Palvelupiste p: palvelupisteet){
			if (!p.onVarattu() && p.onJonossa() || !p.onVarattu() && p.onTilausJonossa()){
				p.aloitaPalvelu();
			}
		}
	}
	
	private double nykyaika(){
		return tapahtumalista.getSeuraavanAika();
	}
	
	private boolean simuloidaan(){
		return kello.getAika() < simulointiaika;
	}
	
	protected abstract void alustukset();
	protected abstract void suoritaTapahtuma(Tapahtuma t);
	public abstract void tulokset();
}
