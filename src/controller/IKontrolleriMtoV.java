package controller;

import eduni.distributions.Normal;

/**
 * Rajapinta, joka tarjotaan moottorille. Tiedot siirtyy mallista
 * kayttoliittymaan.
 * 
 * @author Laura Immonen
 * @author Svyatoslav Beletskiy
 *
 */
public interface IKontrolleriMtoV {

	/**
	 * Nayttaa loppuajan
	 * 
	 * @param aika loppuaika
	 */
	public void naytaLoppuaika(double aika);
	
	/**
	 * Näytää saapuneiden pakettien lukumäärän
	 * @param kpl1 saapuneiden pakettien lukumäärä
	 */
	public void naytaPakettienMaaraSaapuneet(int kpl1);
	
	/**
	 * Näyttää poistuneiden pakettien lukumäärän
	 * @param kpl2 poistuneiden pakettien lukumäärä
	 */
	public void naytaPakettienMaaraLahteneet(int kpl2);
	
	/**
	 * Näyttää pakettien keskimääräisen varastolla viipymisajan
	 * @param aika poistuneiden pakettien keskimääräinen viipymisaika
	 */
	public void naytaPakettienAikaViipyi(double aika);

	/**
	 * Visualisoi paketin saapumisen
	 */
	public void visualisoiAsiakasSaapumis();

	/**
	 * Visualisoi paketin hyllytyksen
	 */
	public void visualisoiAsiakasHyllytys();

	/**
	 * Visualisoi paketin poistumisen jonosta 1
	 */
	public void visualisoiAsiakasPoistoJonosta1();

	/**
	 * Visualisoi paketin kerayksen
	 */
	public void visualisoiAsiakasKeräys();

	/**
	 * Visualisoi paketin poistumisen jonosta 2
	 */
	public void visualisoiAsiakasPoistoJonosta2();

	/**
	 * Visualisoi paketin postituksen
	 */
	public void visualisoiAsiakasPostitus();

	/**
	 * Visualisoi paketin poistumisen jonosta 3
	 */
	public void visualisoiAsiakasPoistoJonosta3();

	/**
	 * Visualisoi paketin poistumisen
	 */
	public void visualisoiAsiakasLähtee();

	/**
	 * Visualisoi paketin poistumisen jonosta 4
	 */
	public void visualisoiAsiakasPoistoJonosta4();

	/**
	 * Hakee palvelupisteen indeksin perusteella
	 * 
	 * @return int palvelupiste
	 */
	public int palvelupisteet();


}
