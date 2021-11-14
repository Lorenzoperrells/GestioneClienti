package it.epicode.beservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Comune;
import it.epicode.beservice.repository.ComuneRepository;

@Service
public class ComuneService {
	@Autowired
	ComuneRepository comuneRepository;
	
	public void save(Comune c) {
		comuneRepository.save(c);
	}
	public void remove(Long id) {
		comuneRepository.deleteById(id);
	}
	
		public void update(Long id, Comune c) {
			Comune cUpdate = comuneRepository.getById(id);
			cUpdate.setNome(c.getNome());
			cUpdate.setProvincia(c.getProvincia());
			comuneRepository.save(cUpdate);
			
		}
	public Comune getBynome(String nome) {
		return comuneRepository.getByNome(nome);
	}
}
