package framework;

import model.Palvelupiste;
import controller.IKontrolleriMtoV;
import eduni.distributions.Normal;

public abstract class Varasto extends Thread implements IVarasto {
	private double simulointiaika = 0;
	private long viive = 0;
	protected static int tyontekijaLKM = 5;
	private Kello kello;
	protected static Normal jakauma;

	protected Tapahtumalista tapahtumalista;
	protected Palvelupiste[] palvelupisteet;
	protected Palvelupiste[] tyontekijat;

	protected IKontrolleriMtoV kontrolleri;

	public Varasto(IKontrolleriMtoV kontrolleri) {

		this.kontrolleri = kontrolleri;

		kello = Kello.getInstance();

		tapahtumalista = new Tapahtumalista();
	}

	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}

	@Override
	public void setViive(long viive) {
		this.viive = viive;
	}

	@Override
	public long getViive() {
		return viive;
	}

	@Override
	public void setTyontekijaLKM(int lkm) {
		if(lkm>8) {
			Varasto.tyontekijaLKM = 8;
		}else if(lkm<1) {
			Varasto.tyontekijaLKM = 1;
		}else {
			Varasto.tyontekijaLKM = lkm;
		}
	}

	/**
	 * Asetetaan uusi jakauma tietylle palvelulle
	 * 
	 * @param int jakaumaArvo1 kayttajan syottama 1 arvo
	 * @param int jakaumaArvo2 kayttajan syottama 2 arvo
	 * 
	 */

	public void setJakauma(int jakaumaArvo1, int jakaumaArvo2) {
		Varasto.jakauma = new Normal(jakaumaArvo1, jakaumaArvo2);
		}

	/**
	 * 
	 */
	@Override
	public void run() {
		alustukset();
		while (simuloidaan()) {
			viive(); // UUSI
			kello.setAika(nykyaika());
			suoritaBTapahtumat();
			yritaCTapahtumat();
		}
		tulokset();

	}

	private void suoritaBTapahtumat() {
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()) {
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	private void yritaCTapahtumat() {
		for (Palvelupiste p : palvelupisteet) {

			if (!p.onVarattu() && p.onJonossa() || !p.onVarattu() && p.onTilausJonossa()) {
				p.aloitaPalvelu();
			}
		}
		for (Palvelupiste t : tyontekijat) {
			if (!t.onVarattu() && t.onJonossa()) {
				t.aloitaPalvelu();
			}
		}

	}

	private double nykyaika() {
		return tapahtumalista.getSeuraavanAika();
	}

	private boolean simuloidaan() {
		Trace.out(Trace.Level.INFO, "Kello on: " + kello.getAika());
		return kello.getAika() < simulointiaika;
	}

	private void viive() {
		Trace.out(Trace.Level.INFO, "Viive " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected abstract void alustukset();

	protected abstract void suoritaTapahtuma(Tapahtuma t);

	protected abstract void tulokset();
}
