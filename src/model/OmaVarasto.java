package model;

import framework.Tapahtuma;
import framework.Varasto;
import framework.Kello;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import controller.IKontrolleriMtoV;
import eduni.distributions.Normal;
import framework.Saapumisprosessi;

/**
 * OmaVarasto-luokka toimii simulaattorin moottorina. OmaVarasto siirtaa tietoa
 * kontrolleriin.
 * 
 * @author Jesper Oja
 *
 */
public class OmaVarasto extends Varasto {

	private Saapumisprosessi saapumisprosessi;
	private Asiakas tilastot = new Asiakas();
	SessionFactory istuntotehdas = null;
	Session istunto = null;

	/**
	 * OmaVarasto luodaan kontrollerin avulla. Varasto-luokkaan luodaan valmiit
	 * palvelupisteet, joissa kayttaja pystyy muuttamaan Normal-jakauman arvoja.
	 * 
	 * @param kontrolleri on mallin ja kayttoliittyman valikasi.
	 */

	public OmaVarasto(IKontrolleriMtoV kontrolleri) {

		super(kontrolleri);
		tyontekijat = new Palvelupiste[8];
		palvelupisteet = new Palvelupiste[4];

		tyontekijat[0] = new Palvelupiste(new Normal(8, 3), tapahtumalista, TapahtumanTyyppi.Tyontekija1);
		tyontekijat[1] = new Palvelupiste(new Normal(9, 4), tapahtumalista, TapahtumanTyyppi.Tyontekija2);
		tyontekijat[2] = new Palvelupiste(new Normal(7, 4), tapahtumalista, TapahtumanTyyppi.Tyontekija3);
		tyontekijat[3] = new Palvelupiste(new Normal(6, 4), tapahtumalista, TapahtumanTyyppi.Tyontekija4);
		tyontekijat[4] = new Palvelupiste(new Normal(8, 5), tapahtumalista, TapahtumanTyyppi.Tyontekija5);
		tyontekijat[5] = new Palvelupiste(new Normal(10, 5), tapahtumalista, TapahtumanTyyppi.Tyontekija6);
		tyontekijat[6] = new Palvelupiste(new Normal(11, 6), tapahtumalista, TapahtumanTyyppi.Tyontekija7);
		tyontekijat[7] = new Palvelupiste(new Normal(7, 5), tapahtumalista, TapahtumanTyyppi.Tyontekija8);

		palvelupisteet[0] = new Vastaanotto(new Normal(1, 1), tapahtumalista, TapahtumanTyyppi.Vastaanotto);

		palvelupisteet[1] = new Hyllytys(new Normal(5, 2), tapahtumalista, TapahtumanTyyppi.Hyllytys);

		palvelupisteet[2] = new Kerays(new Normal(8, 4), tapahtumalista, TapahtumanTyyppi.Keräily);

		palvelupisteet[3] = new Postitus(new Normal(7, 5), tapahtumalista, TapahtumanTyyppi.Postitus);

		switch (kontrolleri.palvelupisteet()) {
		case 0:
			palvelupisteet[0] = new Vastaanotto(jakauma, tapahtumalista, TapahtumanTyyppi.Vastaanotto);
			break;
		case 1:
			tyontekijat[0] = new Palvelupiste(jakauma, tapahtumalista, TapahtumanTyyppi.Tyontekija1);
			tyontekijat[1] = new Palvelupiste(jakauma, tapahtumalista, TapahtumanTyyppi.Tyontekija2);
			tyontekijat[2] = new Palvelupiste(jakauma, tapahtumalista, TapahtumanTyyppi.Tyontekija3);
			tyontekijat[3] = new Palvelupiste(jakauma, tapahtumalista, TapahtumanTyyppi.Tyontekija4);
			tyontekijat[4] = new Palvelupiste(jakauma, tapahtumalista, TapahtumanTyyppi.Tyontekija5);
			tyontekijat[5] = new Palvelupiste(jakauma, tapahtumalista, TapahtumanTyyppi.Tyontekija6);
			tyontekijat[6] = new Palvelupiste(jakauma, tapahtumalista, TapahtumanTyyppi.Tyontekija7);
			tyontekijat[7] = new Palvelupiste(jakauma, tapahtumalista, TapahtumanTyyppi.Tyontekija8);
			break;
		case 2:
			palvelupisteet[1] = new Hyllytys(jakauma, tapahtumalista, TapahtumanTyyppi.Hyllytys);
			break;
		case 3:
			palvelupisteet[2] = new Kerays(jakauma, tapahtumalista, TapahtumanTyyppi.Keräily);
			break;
		case 4:
			palvelupisteet[3] = new Postitus(jakauma, tapahtumalista, TapahtumanTyyppi.Postitus);
			break;
		}

		saapumisprosessi = new Saapumisprosessi(2, tapahtumalista, TapahtumanTyyppi.Saapuminen);
		
		try {
			istuntotehdas = new Configuration().configure().buildSessionFactory();
			istunto = istuntotehdas.openSession();

		} catch (Exception e) {
			System.err.println("Istuntotehtaan luominen ei 	onnistunut.");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	/**
	 * Generoi uuden saapumistapahtuman
	 */

	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava();
	}

	/**
	 * Asiakas-siirretaan palvelupisteelta toiselle.
	 * 
	 * @param t Tapahtuma, jota kasitellaan
	 */
	@Override
	protected void suoritaTapahtuma(Tapahtuma t) {
		Asiakas a;
		Asiakas[] b;
		switch (t.getTyyppi()) {

		case Saapuminen:
			palvelupisteet[0].lisaaJonoon(new Asiakas());
			kontrolleri.visualisoiAsiakasSaapumis();
			saapumisprosessi.generoiSeuraava();

			break;
		case Vastaanotto:
			int counter = 0;
			for (int i = 0; i < tyontekijaLKM; i++) {
				if (!tyontekijat[i].onVarattu() && palvelupisteet[0].onJonossa()) {
					a = palvelupisteet[0].otaJonosta();
					tyontekijat[i].lisaaJonoon(a);
					
					try (Session istunto = istuntotehdas.openSession()){
						istunto.beginTransaction();
						istunto.saveOrUpdate(a);
						istunto.getTransaction().commit();
						istunto.close();
					} catch (Exception e) {
						if(istunto.getTransaction()!=null)
							istunto.beginTransaction().rollback();
						System.err.print("Työntekijän tallentaminen ei onnistunut " + e);
						e.printStackTrace();
					}
					
				} else {
					counter++;
				}
			}
			if (counter == tyontekijaLKM) {
				System.out.println("Kaikki ty�ntekij�t ovat varattuja");
				tapahtumalista.lisaa(
						new Tapahtuma(TapahtumanTyyppi.Vastaanotto, Kello.getInstance().getAika() + Math.random()));
			}
			break;
		case Tyontekija1:

			a = tyontekijat[0].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakasPoistoJonosta1();
			kontrolleri.visualisoiAsiakasHyllytys();
			break;
		case Tyontekija2:

			a = tyontekijat[1].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakasPoistoJonosta1();
			kontrolleri.visualisoiAsiakasHyllytys();
			break;
		case Tyontekija3:

			a = tyontekijat[2].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakasPoistoJonosta1();
			kontrolleri.visualisoiAsiakasHyllytys();
			break;
		case Tyontekija4:

			a = tyontekijat[3].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakasPoistoJonosta1();
			kontrolleri.visualisoiAsiakasHyllytys();
			break;
		case Tyontekija5:

			a = tyontekijat[4].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakasPoistoJonosta1();
			kontrolleri.visualisoiAsiakasHyllytys();
			break;
		case Tyontekija6:

			a = tyontekijat[5].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakasPoistoJonosta1();
			kontrolleri.visualisoiAsiakasHyllytys();
			break;
		case Tyontekija7:

			a = tyontekijat[6].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakasPoistoJonosta1();
			kontrolleri.visualisoiAsiakasHyllytys();
			break;
		case Tyontekija8:

			a = tyontekijat[7].otaJonosta();
			palvelupisteet[1].lisaaJonoon(a);
			kontrolleri.visualisoiAsiakasPoistoJonosta1();
			kontrolleri.visualisoiAsiakasHyllytys();
			break;
		case Hyllytys:
			b = palvelupisteet[1].otaTilausJonosta();
			for (Asiakas asiakas : b) {
				kontrolleri.visualisoiAsiakasPoistoJonosta2();
				kontrolleri.visualisoiAsiakasKeräys();
			}
			palvelupisteet[2].lisaaTilausJonoon(b);

			break;
		case Keräily:
			b = palvelupisteet[2].otaTilausJonosta();
			for (Asiakas asiakas : b) {
				kontrolleri.visualisoiAsiakasPoistoJonosta3();
				kontrolleri.visualisoiAsiakasPostitus();
			}
			palvelupisteet[3].lisaaTilausJonoon(b);

			break;
		case Postitus:
			b = palvelupisteet[3].otaTilausJonosta();
			for (Asiakas asiakas : b) {
				asiakas.setPoistumisaika(Kello.getInstance().getAika());
				asiakas.raportti();
				// Visualisoi asiakkaan poistumisen
				kontrolleri.visualisoiAsiakasPoistoJonosta4();
				kontrolleri.visualisoiAsiakasLähtee();
				
				try (Session istunto = istuntotehdas.openSession()){
					istunto.beginTransaction();
					istunto.saveOrUpdate(asiakas);
					istunto.getTransaction().commit();
					istunto.close();
				} catch (Exception e) {
					if(istunto.getTransaction()!=null)
						istunto.beginTransaction().rollback();
					System.err.print("Asiakkaan tallentaminen ei onnistui " + e);
					e.printStackTrace();
				}
			}
		}
		
		for (int i = 0;i<palvelupisteet.length;i++) {
			try (Session istunto = istuntotehdas.openSession()){
				istunto.beginTransaction();
				istunto.saveOrUpdate(palvelupisteet[i]);
				istunto.getTransaction().commit();
				istunto.close();
			} catch (Exception e) {
				if(istunto.getTransaction()!=null)
					istunto.beginTransaction().rollback();
				System.err.print("Palvelupisteen tallentaminen ei onnistunut " + e);
				e.printStackTrace();
			}
		}
		for (int i = 0;i<tyontekijat.length;i++) {
			try (Session istunto = istuntotehdas.openSession()){
				istunto.beginTransaction();
				istunto.saveOrUpdate(tyontekijat[i]);
				istunto.getTransaction().commit();
				istunto.close();
			} catch (Exception e) {
				if(istunto.getTransaction()!=null)
					istunto.beginTransaction().rollback();
				System.err.print("Työntekijän tallentaminen ei onnistunut " + e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Naytetaan kayttajalle simulaatiotuloksia.
	 */
	@Override
	public void tulokset() {
		kontrolleri.naytaLoppuaika(Kello.getInstance().getAika());
		kontrolleri.naytaPakettienMaaraSaapuneet(tilastot.getSaapuneidenMaara()-1);
		kontrolleri.naytaPakettienMaaraLahteneet((int)tilastot.getCounter());
		kontrolleri.naytaPakettienAikaViipyi(tilastot.getKeskimaarainenAika());
		istuntotehdas.close();
	}

}
