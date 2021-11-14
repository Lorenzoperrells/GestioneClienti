package it.epicode.beservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Indirizzo;
import it.epicode.beservice.repository.IndirizzoRepository;

@Service
public class IndirizzoService {
	@Autowired
	IndirizzoRepository ir;
	
	public void save(Indirizzo i) {
		ir.save(i);
	}
	public void remove(Long id) {
		ir.deleteById(id);
	}
	public void update(Long id,Indirizzo i) {
		Indirizzo iUpdate = ir.getById(id);
		iUpdate.setCap(i.getCap());
		iUpdate.setCivico(i.getCivico());
		iUpdate.setComune(i.getComune());
		iUpdate.setLocalita(i.getLocalita());
		iUpdate.setVia(i.getVia());
		ir.save(iUpdate);
	}
	   public boolean existByVia(String via) {
	    	return ir.existsByVia(via);
	    }
	   public Indirizzo getByVia(String via) {
		   return ir.getByVia(via);
	   }
}
