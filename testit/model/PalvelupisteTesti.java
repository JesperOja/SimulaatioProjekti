package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import eduni.distributions.Normal;
import framework.Tapahtuma;
import framework.Tapahtumalista;

@DisplayName("Palvelupiste-luokan testaus")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PalvelupisteTesti {

	private Palvelupiste palvelupiste;
	private Asiakas asiakas;
	private Palvelupiste vastaanotto;
	private Tapahtumalista tapahtumalista;
	private Tapahtuma tapahtuma;

	@Test
	@DisplayName("Pystyyko asiakkaan laittamaan jonoon?")
	public void testJono() {
		palvelupiste = new Palvelupiste();
		asiakas = new Asiakas();
		palvelupiste.lisaaJonoon(asiakas);

		assertEquals(true, palvelupiste.onJonossa(), "Asiakas ei ole jonossa.");
	}

	@Test
	@DisplayName("Onko palvelu aloitettu?")
	public void testPalvelunAloitus() {
		palvelupiste = new Palvelupiste();
		asiakas = new Asiakas();
		tapahtumalista = new Tapahtumalista();
		vastaanotto = new Vastaanotto(new Normal(10, 3), tapahtumalista, TapahtumanTyyppi.Vastaanotto);
		tapahtuma = new Tapahtuma(TapahtumanTyyppi.Vastaanotto, 0);

		tapahtumalista.lisaa(tapahtuma);
		vastaanotto.lisaaJonoon(asiakas);

		if (!vastaanotto.onVarattu()) {
			vastaanotto.aloitaPalvelu();
		}

		assertEquals(true, vastaanotto.onVarattu(), "Paketille ei ole aloitettu palvelua.");
	}

}