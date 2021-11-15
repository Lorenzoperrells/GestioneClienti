package it.epicode.beservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Cliente;
@Component
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
	
	
	@Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale=:fatturato")
	public List<Cliente> getByFatturato(Double fatturato,Pageable pageable); 
	@Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto=:data")
	public List<Cliente> getByDataUltimoContatto(LocalDate data,Pageable pageable);
	@Query("SELECT c FROM Cliente c WHERE c.dataInserimento=:data")
    public List<Cliente> getByDataInserimento(LocalDate data, Pageable pageable);
	@Query("SELECT c FROM Cliente c WHERE c.ragioneSociale LIKE %:nome%")
	public List<Cliente> getByParteDelNome (String nome,Pageable pageable);
	public Cliente getByRagioneSociale(String cliente);
	public Cliente getById(Long id);
	@Query ("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN :minimo AND :massimo")
	public List<Cliente> getByRangeFatturatoAnnuale(Double minimo,Double massimo, Pageable pageable);
	@Query("SELECT c FROM Cliente c WHERE c.indirizzoSedeOperativa.comune.provincia.regione.nome LIKE %:regione%")
	public List<Cliente> getClienteByRegione(String regione);
	@Query("SELECT c FROM Cliente c WHERE c.indirizzoSedeOperativa.comune.provincia.nome LIKE %:nome%")
	public List<Cliente> getClienteByProvincia(String nome);
	@Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN :minimo AND :massimo ORDER BY c.fatturatoAnnuale DESC")
	public List<Cliente> getByRangeFatturatoAnnualeOrderByFatturatoAnnuale(double minimo, double massimo, Pageable pag);
	public Boolean existsByRagioneSociale(String ragioneSociale);
}	
