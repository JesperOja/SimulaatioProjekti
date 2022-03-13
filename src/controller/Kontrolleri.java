package controller;

import javafx.application.Platform;
import framework.IVarasto;
import model.OmaVarasto;
import view.ISimulaattorinUI;

/**
 * Kontrolleri siirtaa datan moottorin ja kayttoliittyman valilla
 * 
 * @author Laura Immonen
 * @author Svyatoslav Beletskiy
 */

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV {

	private IVarasto moottori;
	private ISimulaattorinUI ui;

	/**
	 * Konstruktori kontrollerille
	 * 
	 * @param ui kayttoliittyman olio
	 */
	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
	}

	// Moottorin ohjausta:

	/**
	 * Luodaan uusi saie. Simulointiajan, viiveen, nayton tyhjennys, tyontekijoiden
	 * asetus. Lopuksi saie kaynnistetaan.
	 */

	@Override
	public void kaynnistaSimulointi() {
		moottori = new OmaVarasto(this);
		moottori.setSimulointiaika(ui.getAika());
		moottori.setViive(ui.getViive());
		moottori.setTyontekijaLKM(ui.getTyontekijoidenLKM());
		moottori.setJakauma(ui.getJakaumaArvo1(), ui.getJakaumaArvo2());
		ui.getVisualisointi().tyhjennaNaytto();
		((Thread) moottori).start();

	}

	/**
	 * Hidastaa simulaattorin toimintaa, viiven avulla.
	 */
	@Override
	public void hidasta() {
		moottori.setViive((long) (moottori.getViive() * 1.10));
	}

	/**
	 * Nopeuttaa simulaattorin toimintaa, viiven avulla.
	 */

	@Override
	public void nopeuta() {
		moottori.setViive((long) (moottori.getViive() * 0.9));
	}
	// Simulointitulosten valittamista kayttoliittymaan.
	// Koska FX-ui:n paivitykset tulevat moottorisaikeesta, ne pitaa ohjata
	// JavaFX-saikeeseen:

	/**
	 * Naytetaan kayttajaliittymassa loppuaika
	 * 
	 * @param aika1 loppuaika
	 */
	@Override
	public void naytaLoppuaika(double aika1) {
		Platform.runLater(() -> ui.setLoppuaika(aika1));
	}
	
	@Override
	public void naytaPakettienMaaraSaapuneet(int kpl1) {
		Platform.runLater(() -> ui.setPakettienMaaraSaapuneet(kpl1));
	}
	
	@Override
	public void naytaPakettienMaaraLahteneet(int kpl2) {
		Platform.runLater(() -> ui.setPakettienMaaraLahteneet(kpl2));
	}
	
	@Override
	public void naytaPakettienAikaViipyi(double aika2) {
		Platform.runLater(() -> ui.setPakettienAikaViipyi(aika2));
	}

	/**
	 * Visualisoi paketin saapumista
	 */
	@Override
	public void visualisoiAsiakasSaapumis() {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.getVisualisointi().pakettiSaapuu();
			}
		});
	}

	/**
	 * Visualisoi paketin hyllytysta
	 */
	@Override
	public void visualisoiAsiakasHyllytys() {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.getVisualisointi().pakettiHyllytys();
			}
		});
	}

	/**
	 * Visualisoi paketin poistoa jonosta 1
	 */

	@Override
	public void visualisoiAsiakasPoistoJonosta1() {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.getVisualisointi().pakettiPoistuuJonosta1();
			}
		});
	}

	/**
	 * Visualisoi paketin keraysta
	 */
	@Override
	public void visualisoiAsiakasKeräys() {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.getVisualisointi().pakettiKeräys();
			}
		});
	}

	/**
	 * Visualisoi paketin poistoa jonosta 2
	 */

	@Override
	public void visualisoiAsiakasPoistoJonosta2() {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.getVisualisointi().pakettiPoistuuJonosta2();
			}
		});
	}

	/**
	 * Visualisoi paketin postituksen
	 */
	@Override
	public void visualisoiAsiakasPostitus() {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.getVisualisointi().pakettiPostitus();
			}
		});
	}

	/**
	 * Visualisoi paketin poistoa jonosta 3
	 */
	@Override
	public void visualisoiAsiakasPoistoJonosta3() {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.getVisualisointi().pakettiPoistuuJonosta3();
			}
		});
	}

	/**
	 * Visualisoi paketin poistoa
	 */
	@Override
	public void visualisoiAsiakasLähtee() {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.getVisualisointi().pakettiLähtee();
			}
		});
	}

	/**
	 * Visualisoi paketin poistoa jonosta 4
	 */
	@Override
	public void visualisoiAsiakasPoistoJonosta4() {
		Platform.runLater(new Runnable() {
			public void run() {
				ui.getVisualisointi().pakettiPoistuuJonosta4();
			}
		});
	}

	/**
	 * Asettaa kayttajan syotetyn tyontekija maaran.
	 */
	@Override
	public void tyontekijaLKM() {
		moottori.setTyontekijaLKM(ui.getTyontekijoidenLKM());

	}

	/**
	 * Asettaa kayttajan syottamat jakauma-arvot
	 */

	@Override
	public void jakaumaArvot() {
		moottori = new OmaVarasto(this);
		moottori.setJakauma(ui.getJakaumaArvo1(), ui.getJakaumaArvo2());

	}

	/**
	 * Asettaa kayttajan valitseman palvelupisteen.
	 */

	public int palvelupisteet() {

		return ui.getPalvelupiste();
	}

}
