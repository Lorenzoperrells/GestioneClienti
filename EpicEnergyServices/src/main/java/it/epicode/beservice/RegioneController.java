package it.epicode.beservice;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Regione;
import it.epicode.beservice.services.RegioneService;

@RestController
@RequestMapping("/regionectrl")
public class RegioneController {
	@Autowired
	RegioneService service;
	
	@GetMapping("/popola")
	public void popola() {
		File f = new File("src/main/resources/csv/province-italiane.csv");
		try {
			String str = FileUtils.readFileToString(f, "UTF-8");
			List<String> lstCartStr = Arrays.asList(str.split("\\r?\\n"));
			for (String prov : lstCartStr) {
				String[] p = prov.split(";");
				if(!service.existsByNome(p[2])) {
				Regione r = new Regione(p[2]);
				service.save(r);
			}
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
