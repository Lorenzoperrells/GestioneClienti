package it.epicode.beservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.services.StatoFatturaService;

@RestController
@RequestMapping("/statofatturactrl")
public class StatoFatturaController {
		@Autowired
		StatoFatturaService service;
		
		@PostMapping("/save")
		public void save(@RequestBody StatoFattura sf) {
			service.save(sf);
		}
		@GetMapping("/delete/{id}")
		public void remove(@PathVariable Long id) {
			service.remove(id);
		}
		@PostMapping("/update/{id}")
		public void update(@PathVariable Long id, @RequestBody StatoFattura sf) {
			service.update(id, sf);
		}
		

	}

