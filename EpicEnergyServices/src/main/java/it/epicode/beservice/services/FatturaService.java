package it.epicode.beservice.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Fattura;
import it.epicode.beservice.repository.FatturaRepository;


@Service
public class FatturaService {
	@Autowired
	FatturaRepository fr;

	public void save(Fattura f) {
		fr.save(f);
	}
	public void remove(Long id) {
		fr.deleteById(id);
	}
	public void update(Long id, Fattura f) {
		Fattura fUpdate = fr.getById(id);
		fUpdate.setAnno(f.getAnno());
		fUpdate.setCliente(f.getCliente());
		fUpdate.setData(f.getData());
		fUpdate.setImporto(f.getImporto());
		fUpdate.setStato(f.getStato());
		fUpdate.setNumero(f.getNumero());
		fr.save(fUpdate);
		
	}
	public List<Fattura> getByCliente(String cliente, Pageable pageable){
		return fr.getByCliente(cliente, pageable);
	}
	public List<Fattura> getByStato(String stato,Pageable pageable){
		return fr.getByStato(stato, pageable);
	}
	public List<Fattura> getByData(LocalDate data,Pageable pageable){
		return fr.getByData(data, pageable);
	}
	public List<Fattura> getByAnno(String anno,Pageable pageable){
		return fr.getByAnno(anno, pageable);
	}
	public List<Fattura> getByRangeImporto(Double minimo,Double massimo, Pageable pageable){
		return fr.getByRangeImporto(minimo, massimo, pageable);
	}
	public Fattura getById(Long id) {
		return fr.getById(id);
	}
}
