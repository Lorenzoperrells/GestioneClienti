package it.epicode.beservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import it.epicode.beservice.model.Provincia;
@Component
public interface ProvinciaRepository extends JpaRepository<Provincia,Long> {
@Query("SELECT p from Provincia p WHERE p.nome=:nome")
public Provincia getbyNome(String nome);
@Query("SELECT p from Provincia p WHERE p.regione.nome LIKE %:regione%")
public List<Provincia> getByNomeRegione(String regione);


}
