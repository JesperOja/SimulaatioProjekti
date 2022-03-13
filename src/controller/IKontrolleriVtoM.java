package controller;


/**
 * Rajapinta, joka siirtaa tietoa kayttoliittymasta moottoriin.
 * 
 * @author Laura Immonen
 */

public interface IKontrolleriVtoM {

	/**
	 * Kaynnistaa simuloinnin.
	 */

	public void kaynnistaSimulointi();

	/**
	 * Nopeuttaa simuloinnin.
	 */

	public void nopeuta();

	/**
	 * Hidastaa simuloinnin.
	 */

	public void hidasta();

	/**
	 * Valittaa tyontekijamaaran moottorille.
	 */

	public void tyontekijaLKM();
	
	/**
	 * Valittaa jakauma-arvot moottorille.
	 */

	public void jakaumaArvot();


}
