package it.epicode.beservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.services.ComuneService;
import it.epicode.beservice.services.IndirizzoService;
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
class IndirizzoControllerTest {
	@Autowired
	IndirizzoService testService;
	@Autowired
	ComuneService cs;
//	String provincia = new JSONObject()
//	         .put("id", "97").put("nome", "Torino").put("sigla","TO").toString();
//	String comune = new JSONObject()
//	         .put("id", "268").put("nome","Torino").put("provincia",provincia).toString();
//	String indirizzo = new JSONObject()
//	         .put("via", "via rossi").put("civico","86").put("cap","00133").put("localita","Montagna").put("comune",comune).toString();
//	this.restTemplate.postForObject("http://localhost:"+port+"/indirizzoctrl/save", indirizzo,String.class );

	@Test
	void testSave() {
			testService.save(new Indirizzo("via rossi","86","00143","Montagna",cs.getBynome("Torino")));
			assertTrue(testService.existByVia("via rossi"));
			}
}

