
package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@DisplayName("Asiakas-luokan testaus")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AsiakasTesti {

	@Test
	@DisplayName("Onko id alustettu oikein?")
	public void testID() {
		Asiakas asiakas = new Asiakas();
		assertEquals(1, asiakas.getId(), "Id alustettu vaarin.");
	}

	@Test
	@DisplayName("Onko saapumisaika alustettu oikein?")
	public void testSaapumisaika() {
		Asiakas asiakas = new Asiakas();
		assertEquals(0, asiakas.getSaapumisaika(), "Saapumisaika alustettu vaarin.");
		// Simulaatio ei ole aloitettu, joten saapumisaika on 0.
	}

	@Test
	@DisplayName("Onko poistumisaika alustettu oikein?")
	public void testPoistumisaika() {
		Asiakas asiakas = new Asiakas();
		assertEquals(0, asiakas.getPoistumisaika(), "Poistumisaika alustettu vaarin.");
		// Simulaatio ei ole aloitettu, joten poistumisaika on 0.
	}

}