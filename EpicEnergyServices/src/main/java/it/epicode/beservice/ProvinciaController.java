package it.epicode.beservice;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.services.ProvinciaService;
import it.epicode.beservice.services.RegioneService;

@RestController
@RequestMapping("/provinciactrl")
public class ProvinciaController {
	@Autowired
	ProvinciaService service;
	@Autowired 
	RegioneService rs;

	@PostMapping("/save")
	public void save(@RequestBody Provincia p) {
		service.save(p);
	}

	@GetMapping("/delete/{id}")
	public void remove(@PathVariable Long id) {
		service.remove(id);
	}

	@PostMapping("/update/{id}")
	public void update(@PathVariable Long id, @RequestBody Provincia p) {
		service.update(id, p);
	}

	@GetMapping("/popola")
	public void popola() {
		File f = new File("src/main/resources/csv/province-italiane.csv");
		try {
			String str = FileUtils.readFileToString(f, "UTF-8");
			List<String> lstCartStr = Arrays.asList(str.split("\\r?\\n"));
			for (String prov : lstCartStr) {
				String[] p = prov.split(";");
				Provincia pr = new Provincia(p[1], p[0],rs.getByNome(p[2]));
				service.save(pr);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	@GetMapping("/getbynome")
	public Provincia getByNome(@RequestParam String nome) {
		return service.getByNome(nome);
	}
}
