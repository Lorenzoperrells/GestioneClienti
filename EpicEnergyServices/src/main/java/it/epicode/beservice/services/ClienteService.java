package it.epicode.beservice.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.beservice.model.Cliente;
import it.epicode.beservice.repository.ClienteRepository;
@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	public void save(Cliente c) {
		clienteRepository.save(c);
	}
	public void update(Long id,Cliente c) {
		Cliente cUpdate = clienteRepository.getById(id);
		cUpdate.setRagioneSociale(c.getRagioneSociale());
		cUpdate.setCognomeContatto(c.getCognomeContatto());
		cUpdate.setDataInserimento(c.getDataInserimento());
		cUpdate.setDataUltimoContatto(c.getDataUltimoContatto());
		cUpdate.setEmail(c.getEmail());
		cUpdate.setEmailContatto(c.getEmailContatto());
		cUpdate.setFatturatoAnnuale(c.getFatturatoAnnuale());
		cUpdate.setIndirizzoSedeLegale(c.getIndirizzoSedeLegale());
		cUpdate.setIndirizzoSedeOperativa(c.getIndirizzoSedeOperativa());
		cUpdate.setNomeContatto(c.getNomeContatto());
		cUpdate.setPartitaIva(c.getPartitaIva());
		cUpdate.setPec(c.getPec());
		cUpdate.setTelefono(c.getTelefono());
		cUpdate.setTelefonoContatto(c.getTelefonoContatto());
		cUpdate.setTipoCliente(c.getTipoCliente());
		clienteRepository.save(cUpdate);
	}
	public void remove(Long id) {
		clienteRepository.deleteById(id);
	}
	 public List<Cliente> myFindAllClientePageSizeSort(Integer page, Integer size, String sort) {
	        Pageable paging = PageRequest.of(page, size, Sort.by(sort));
	        Page<Cliente> pagedResult = clienteRepository.findAll(paging);
	        if (pagedResult.hasContent()) {
	            return pagedResult.getContent();
	        } else {
	            return new ArrayList<Cliente>();
	        }
	    }
	 public List<Cliente> getByFatturato(Double fatturato,Pageable pageable){
		 return clienteRepository.getByFatturato(fatturato,pageable);
	 }
	 public List<Cliente> getByDataUltimoContatto(LocalDate data,Pageable pageable){
		return clienteRepository.getByDataUltimoContatto(data,pageable);
	 }
	 public List<Cliente> getByDataInserimento(LocalDate data, Pageable pageable){
		 return clienteRepository.getByDataInserimento(data,pageable);
	 }
	 public List<Cliente> getByParteDelNome (String nome,Pageable pageable){
		 return clienteRepository.getByParteDelNome(nome,pageable);
	 }
	public Cliente getByNome(String cliente) {
		return clienteRepository.getByRagioneSociale(cliente);
	}
	public Cliente getById(Long id) {
		return clienteRepository.getById(id);
	}
	public List<Cliente> getByRangeFatturatoAnnuale(Double minimo,Double massimo,Pageable pageable){
		return clienteRepository.getByRangeFatturatoAnnuale(minimo,massimo, pageable);
	}
	public List<Cliente> getClienteByRegione(String regione){
		return clienteRepository.getClienteByRegione(regione);
	}
	public List<Cliente> getClienteByProvincia(String nome) {
		return clienteRepository.getClienteByProvincia(nome);
	}
	public List<Cliente> getByRangeFatturatoAnnualeOrderByFatturatoAnnuale(double minimo, double massimo, Pageable pag) {
		return clienteRepository.getByRangeFatturatoAnnualeOrderByFatturatoAnnuale(minimo,massimo,pag);
	}
    
}
