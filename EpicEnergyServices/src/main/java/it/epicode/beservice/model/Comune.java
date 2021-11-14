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
public class Comune {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@ManyToOne
	private Provincia provincia;
	
	public Comune() {}

	public Comune(String nome, Provincia provincia) {
		this.nome = nome;
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return  nome + " in  provincia di " + provincia.getNome() + " "+ provincia.getSigla();
	}

	
}
