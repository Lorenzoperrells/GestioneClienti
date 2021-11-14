package it.epicode.beservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class Provincia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sigla;
	@ManyToOne
	private Regione regione;

	public Provincia() {
	}

	public Provincia(String nome, String sigla,Regione regione) {
		this.nome = nome;
		this.sigla = sigla;
		this.regione = regione;
	}

}
