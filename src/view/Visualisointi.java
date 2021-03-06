package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Visualisoi simulaattoria.
 * 
 * @author Svyatoslav Beletskiy
 *
 */

public class Visualisointi extends Canvas implements IVisualisointi {

	private GraphicsContext gc;

	int a = 0;

	double i1 = 0;
	double i2 = 0;
	double i3 = 0;
	double i4 = 0;
	double i5 = 0;

	double j1 = 20;
	double j2 = 20;
	double j3 = 20;
	double j4 = 20;
	double j5 = 20;

	/**
	 * Luodaan canvas-naytto kayttoliittymaan.
	 * 
	 * @param w leveys
	 * @param h korkeus
	 */
	public Visualisointi(int w, int h) {
		super(w, h);
		gc = this.getGraphicsContext2D();
		tyhjennaNaytto();
	}

	/**
	 * Tyhjennetaan canvas ja asetetaan palvelupisteiden nimet.
	 */
	public void tyhjennaNaytto() {
		gc.setFill(Color.CYAN);
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());
		gc.setFill(Color.BLACK);
		gc.fillText("SAAPUVAT", 20, 15);
		gc.fillText("HYLLYTYS", 140, 15);
		gc.fillText("KERÄYS", 260, 15);
		gc.fillText("POSTITUS", 380, 15);
		gc.fillText("LÄHTENEET", 500, 15);
	}

	/**
	 * Visualisoi paketin saapumista vastaanottoon.
	 */
	public void pakettiSaapuu() {
		gc.setFill(Color.web("#ff0a0a"));
		gc.fillOval(i1 + 20, j1, 10, 10);

		i1 = (i1 + 10) % 100;
		if (i1 <= 0) {
			j1 += 10;
			i1 = 0;
		}
	}

	/**
	 * Visualisoi paketin poistumista jonosta 1 (vastaanotto).
	 */
	public void pakettiPoistuuJonosta1() {
		gc.setFill(Color.CYAN);
		i1 = (i1 - 10) % 100;
		if (i1 < 0 && j1 > 20) {
			i1 = 90;
			j1 -= 10;
		}
		gc.fillRect(i1 + 20, j1, 10, 10);
	}

	/**
	 * Visualisoi paketin siirtymista hyllytykseen.
	 */
	public void pakettiHyllytys() {
		gc.setFill(Color.web("#F76E11"));
		gc.fillOval(i2 + 140, j2, 10, 10);

		i2 = (i2 + 10) % 100;
		if (i2 <= 0) {
			i2 = 0;
			j2 += 10;
		}
	}

	/**
	 * Visualisoi paketin poistumista jonosta 2 (hyllytys).
	 */
	public void pakettiPoistuuJonosta2() {
		gc.setFill(Color.CYAN);
		i2 = (i2 - 10) % 100;
		if (i2 < 0 && j2 > 20) {
			i2 = 90;
			j2 -= 10;
		}
		gc.fillRect(i2 + 140, j2, 10, 10);
	}

	/**
	 * Visualisoi paketin siirtymista keraykseen
	 */
	public void pakettiKeräys() {
		gc.setFill(Color.web("#ebff0a"));
		gc.fillOval(i3 + 260, j3, 10, 10);

		i3 = (i3 + 10) % 100;
		if (i3 <= 0) {
			i3 = 0;
			j3 += 10;
		}
	}

	/**
	 * Visualisoi paketin poistumista jonosta 3 (kerays).
	 */
	public void pakettiPoistuuJonosta3() {
		gc.setFill(Color.CYAN);
		i3 = (i3 - 10) % 100;
		if (i3 < 0 && j3 > 20) {
			j3 -= 10;
			i3 = 90;
		}
		gc.fillRect(i3 + 260, j3, 10, 10);
	}

	/**
	 * Visualisoi paketin siirtymista postitukseen
	 */
	public void pakettiPostitus() {
		gc.setFill(Color.web("#A3DA8D"));
		gc.fillOval(i4 + 380, j4, 10, 10);

		i4 = (i4 + 10) % 100;
		if (i4 <= 0)
			j4 += 10;
	}

	/**
	 * Visualisoi paketin poistumista jonosta 4 (postitus).
	 */
	public void pakettiPoistuuJonosta4() {
		gc.setFill(Color.CYAN);
		i4 = (i4 - 10) % 100;
		if (i4 < 0 && j4 > 20) {
			j4 -= 10;
			i4 = 90;
		}
		gc.fillRect(i4 + 380, j4, 10, 10);
	}

	/**
	 * Visualisoi paketin poistumista varastolta.
	 */
	public void pakettiLähtee() {
		gc.setFill(Color.web("#209c05"));
		gc.fillOval(i5 + 500, j5, 10, 10);

		i5 = (i5 + 10) % 100;
		if (i5 <= 0)
			j5 += 10;
	}
}
