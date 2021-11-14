package it.epicode.beservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Indirizzo;
@Component
public interface IndirizzoRepository extends JpaRepository<Indirizzo,Long> {
	public Boolean existsByVia(String via);
	public Indirizzo getByVia(String via);
}
