package it.epicode.beservice.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data

public class StatoFattura {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	public StatoFattura() {
	}
	public StatoFattura(String nome) {
		this.nome=nome;
		
	}
	@Override
	public String toString() {
		return nome ;
	}
	
}
