package it.epicode.beservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.StatoFattura;
@Component
public interface StatoFatturaRepository extends JpaRepository<StatoFattura,Long> {
	public Boolean existsByNome(String nome);

	public StatoFattura getByNome(String nome);
}
