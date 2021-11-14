package it.epicode.beservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.services.IndirizzoService;
@RestController
	@RequestMapping("/indirizzoctrl")
public class IndirizzoController {
	
		@Autowired
		IndirizzoService service;
		
		@PostMapping("/update/{id}")
		public void update(@PathVariable Long id, @RequestBody Indirizzo i) {
			service.update(id, i);
		}
		@PostMapping("/save")
		public void save(@RequestBody Indirizzo i) {
			service.save(i);
		}
		@GetMapping("/delete/{id}")
		public void remove(@PathVariable Long id) {
			service.remove(id);
		}
		@GetMapping("/exist")
		public boolean exist(@RequestParam String via) {
			return service.existByVia(via);
		}
		

	}

