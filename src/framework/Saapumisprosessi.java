package framework;
import eduni.distributions.*;
import model.TapahtumanTyyppi;
public class Saapumisprosessi {
	
	private int aika;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi tyyppi;

	public Saapumisprosessi(int aika, Tapahtumalista tl, TapahtumanTyyppi tyyppi){
		this.aika = aika;
		this.tapahtumalista = tl;
		this.tyyppi = tyyppi;
	}

	public void generoiSeuraava(){
		Tapahtuma t = new Tapahtuma(tyyppi, Kello.getInstance().getAika()+aika);
		tapahtumalista.lisaa(t);
	}

}
