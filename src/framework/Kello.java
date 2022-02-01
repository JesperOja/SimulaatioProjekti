package framework;

public class Kello {
	private double aika;
	private static Kello getInstance;
	private Kello() {}
	
	public static Kello getInstance() {
		if(getInstance == null) {
			getInstance = new Kello();
		}
		return getInstance;
	}
	
	public void setAika(double aika) {
		this.aika = aika;
	}
	
	public double getAika() {
		return aika;
	}
}
