package it.epicode.beservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Provincia;
import it.epicode.beservice.repository.ProvinciaRepository;

@Service
public class ProvinciaService {
	@Autowired
	ProvinciaRepository pr;

	public void save(Provincia p) {
		pr.save(p);
	}

	public void remove(Long id) {
		pr.deleteById(id);
	}
	public void update(Long id, Provincia p) {
	Provincia pUpdate = pr.getById(id);
	pUpdate.setNome(p.getNome());
	pUpdate.setSigla(p.getSigla());
	pr.save(pUpdate);
	}
	public Provincia getByNome(String nome) {
		return pr.getbyNome(nome);
	}
	public List<Provincia> getByRegione(String regione){
		return pr.getByNomeRegione(regione);
	}
}
