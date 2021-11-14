package it.epicode.beservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Regione;
import it.epicode.beservice.repository.RegioneRepository;

@Service
public class RegioneService {
	@Autowired
	RegioneRepository rr;
	
	public Boolean existsByNome(String nome) {
		return rr.existsByNome(nome);
	}

	public void save(Regione r) {
		rr.save(r);
		
	}
	public Regione getByNome(String nome) {
		return rr.getByNome(nome);
	}
	public List<String> getNomeRegioni(){
		return rr.getNomeRegione();
		
	}

	public List<Regione> findAll() {
		return rr.findAll();
	}



	

}
