package view;

/**
 * Rajapinta Simulaattorin gui:lle. Kontrolleri voi kayttaa naita metodeja.
 * 
 * @author Laura Immonen
 *
 */

public interface ISimulaattorinUI {

	/**
	 * Palauttaa simulaatioajan.
	 * 
	 * @return double simulaation aika
	 */
	public double getAika();

	/**
	 * Palauttaa annetun viiveen.
	 * 
	 * @return long simulaation viive
	 */
	public long getViive();

	/**
	 * Palauttaa tyontekijoiden maaran.
	 * 
	 * @return int tyontekija maara
	 */
	public int getTyontekijoidenLKM();

	// Kontrolleri antaa kayttoliittymalle tuloksia, joita Moottori tuottaa
	/**
	 * Asettaa kayttajan syottaman lopetusajan.
	 * 
	 * @param aika1 simulaation lopetusaika
	 */
	public void setLoppuaika(double aika1);
	
	/**
	 * Asetetaan saapuneiden pakettien lkm
	 * @param kpl1 saapuneiden pakettien lkm
	 */
	public void setPakettienMaaraSaapuneet(int kpl1);

	/**
	 * Asetetaan lähteneiden pakettien lkm
	 * @param kpl2 lähteneiden pakettien lkm
	 */
	public void setPakettienMaaraLahteneet(int kpl2);
	
	/**
	 * Asetetaan lähteneiden pakettien keskimääräinen läpimenoaika
	 * @param aika2 keskimääräinen läpimenoaika
	 */
	public void setPakettienAikaViipyi(double aika2);
	
	/**
	 * Palauttaa visualisointi-olion, jolla toteutetaan visuaalisointi.
	 * 
	 * @return visualisointi-olio
	 */
	public IVisualisointi getVisualisointi();

	/**
	 * Palauttaa kayttajan syottaman jakauman arvon nro 1
	 * 
	 * @return int jakauma-arvo
	 */
	public int getJakaumaArvo1();

	/**
	 * Palauttaa kayttajan syottaman jakauman arvon nro 2
	 * 
	 * @return int jakauma-arvo
	 */
	public int getJakaumaArvo2();

	/**
	 * Palauttaa kayttajan valitseman palvelupisteen
	 * 
	 * @return int palvelupiste
	 */
	public int getPalvelupiste();

}
