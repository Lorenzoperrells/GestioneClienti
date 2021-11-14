package it.epicode.beservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Regione;
@Component
public interface RegioneRepository extends JpaRepository<Regione,Long>{
	public Boolean existsByNome(String nome);
	public Regione getByNome(String nome);
	@Query("SELECT r.nome FROM Regione r ORDER BY r.nome")
	public List<String> getNomeRegione();

}
