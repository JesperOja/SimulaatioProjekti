package model;


import eduni.distributions.Uniform;
import framework.Kello;
import framework.Tapahtuma;


public class Asiakas implements Comparable<Asiakas>{
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static double sum = 0;
	private int priority;
	private static double counter = 0;
	private Uniform uniform = new Uniform(1, 7);
	
	public Asiakas(){
	    id = i++;
	    priority = (int)uniform.sample();
		saapumisaika = Kello.getInstance().getAika();
		System.out.println("Uusi asiakas nro " + id + " saapui klo "+saapumisaika);
	}
	
	
	public double getPoistumisaika() {
		return poistumisaika;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}
	
	public int getId() {
		return id;
	}
	
	public void raportti(){
		counter++;
		System.out.println( "\nPaketti "+id+ " valmis! ");
		System.out.println( "Paketti "+id+ " prioriteetti oli " +priority);
		System.out.println("Paketti "+id+ " saapui: " +saapumisaika);
		System.out.println("Paketti "+id+ " poistui: " +poistumisaika);
		System.out.println("Paketti "+id+ " viipyi: " +(poistumisaika-saapumisaika));
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/counter;
		System.out.println("Pakettien läpimenoaikojen keskiarvo tähän asti "+ keskiarvo);
	}

	@Override
	public int compareTo(Asiakas o) {
		if(this.priority <= o.priority && this.id < o.id)
			return -1;
		else if(this.priority == o.priority)
			return 0;
		else if(this.priority<o.priority && this.id>o.id)
			return 0;
		else
			return 1;
	}

}
