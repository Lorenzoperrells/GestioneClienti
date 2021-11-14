package it.epicode.beservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Fattura;
@Component
public interface FatturaRepository extends JpaRepository<Fattura,Long> {
	@Query("SELECT f FROM Fattura f WHERE f.cliente.ragioneSociale=:cliente")
	public List<Fattura> getByCliente(String cliente, Pageable pageable);
	@Query("SELECT f FROM Fattura f WHERE f.stato.nome=:stato")
	public List<Fattura> getByStato(String stato,Pageable pageable);
	@Query("SELECT f FROM Fattura f WHERE f.data=:data")
	public List<Fattura> getByData(LocalDate data,Pageable pageable);
	@Query("SELECT f FROM Fattura f WHERE f.anno=:anno")
	public List<Fattura> getByAnno(String anno,Pageable pageable);
	@Query ("SELECT f FROM Fattura f WHERE f.importo BETWEEN :minimo AND :massimo")
	public List<Fattura> getByRangeImporto(Double minimo,Double massimo, Pageable pageable);
	public Fattura getById(Long id);

}
