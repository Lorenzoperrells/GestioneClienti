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

import it.epicode.beservice.model.Comune;
import it.epicode.beservice.services.ComuneService;
import it.epicode.beservice.services.ProvinciaService;

@RestController
@RequestMapping("/comunectrl")
public class ComuneController {

		@Autowired
		ComuneService service;
		@Autowired
		ProvinciaService proService;
		
		@PostMapping("/save")
		public void save(@RequestBody Comune c) {
			service.save(c);
		}
		@GetMapping("/delete/{id}")
		public void remove(@PathVariable Long id) {
			service.remove(id);
		}
		@PostMapping("/update/{id}")
		public void update(@PathVariable Long id, @RequestBody Comune c) {
			service.update(id, c);
		}
		@GetMapping("/popola")
		public void popola() {
			File f = new File("src/main/resources/csv/comuni-italiani.csv");
			try {
				String str = FileUtils.readFileToString(f, "UTF-8");
				List<String> lstCartStr = Arrays.asList(str.split("\\r?\\n"));
				for (String com : lstCartStr) {
					String[] c = com.split(";");
					Comune comune = new Comune(c[2],proService.getByNome(c[3]));
					service.save(comune);
					
					
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		@GetMapping("/getbynome")
		public Comune getByNome(@RequestParam String nome) {
			return service.getBynome(nome);
		}
		 
	}
