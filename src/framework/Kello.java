package framework;

/**
 * Kello Singleton-luokka, jonka avulla hallitaan aikaa.
 *
 * @Author Jesper Oja
 */

public class Kello {
	private double aika;
	private static Kello getInstance;

	private Kello() {
	}

	public static Kello getInstance() {
		if (getInstance == null) {
			getInstance = new Kello();
		}
		return getInstance;
	}

	/**
	 * Asettaa kellonajan.
	 *
	 * @param kellonaika.
	 */

	public void setAika(double aika) {
		this.aika = aika;
	}

	/**
	 * Palauttaa kellonajan.
	 *
	 * @return kellonaika.
	 */

	public double getAika() {
		return aika;
	}
}
