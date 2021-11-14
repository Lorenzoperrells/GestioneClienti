package it.epicode.beservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.services.StatoFatturaService;
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
class StatoFatturaControllerTest {
@Autowired
	StatoFatturaService testService;

	@Test
	void testRemove() {
		String nome="Test";
		StatoFattura sf=new StatoFattura(nome);
		testService.save(sf);
		assertTrue(testService.existsByNome(nome));
		testService.remove(sf.getId());
		assertFalse(testService.existsByNome(nome));
	}
}
