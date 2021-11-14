package it.epicode.beservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.StatoFattura;
import it.epicode.beservice.repository.StatoFatturaRepository;

@Service
public class StatoFatturaService {
	@Autowired
	StatoFatturaRepository sfr;

	public void save(StatoFattura sf) {
		sfr.save(sf);
	}
	public void remove(Long id) {
		sfr.deleteById(id);
	}
	public void update(Long id, StatoFattura sf) {
		StatoFattura sfUpdate = sfr.getById(id);
		sfUpdate.setNome(sf.getNome());
		sfr.save(sfUpdate);
		}
	public Boolean existsByNome(String nome) {
		return sfr.existsByNome(nome);
	}
	public StatoFattura getByNome(String nome) {
		return sfr.getByNome(nome);
	}
}
