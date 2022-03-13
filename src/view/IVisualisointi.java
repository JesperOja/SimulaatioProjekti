package view;

/**
 * Rajapinta visualisoinnille.
 * 
 * @author Svyatoslav Beletskiy
 *
 */
public interface IVisualisointi {
	/**
	 * Tyhjennetaan canvas ja asetetaan palvelupisteiden nimet.
	 */
	public void tyhjennaNaytto();

	/**
	 * Visualisoi paketin saapumista vastaanottoon.
	 */
	public void pakettiSaapuu();

	/**
	 * Visualisoi paketin siirtymista hyllytykseen.
	 */
	public void pakettiHyllytys();

	/**
	 * Visualisoi paketin poistumista jonosta 1 (vastaanotto).
	 */
	public void pakettiPoistuuJonosta1();

	/**
	 * Visualisoi paketin siirtymista keraykseen
	 */
	public void pakettiKeräys();

	/**
	 * Visualisoi paketin poistumista jonosta 2 (hyllytys).
	 */
	public void pakettiPoistuuJonosta2();

	/**
	 * Visualisoi paketin siirtymista postitukseen
	 */
	public void pakettiPostitus();

	/**
	 * Visualisoi paketin poistumista jonosta 3 (kerays).
	 */
	public void pakettiPoistuuJonosta3();

	/**
	 * Visualisoi paketin poistumista varastolta.
	 */
	public void pakettiLähtee();

	/**
	 * Visualisoi paketin poistumista jonosta 4 (postitus).
	 */
	public void pakettiPoistuuJonosta4();
}
