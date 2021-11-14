package it.epicode.beservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Comune;
@Component
public interface ComuneRepository extends JpaRepository<Comune,Long>{
	
	public Comune getByNome(String nome);
}
