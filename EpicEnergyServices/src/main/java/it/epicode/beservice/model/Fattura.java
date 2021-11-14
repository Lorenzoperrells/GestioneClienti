package it.epicode.beservice.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.CascadeType;
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
public class Fattura {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private LocalDate data;
	private String numero;
	private String anno;
	private Double importo;
	@ManyToOne
	private StatoFattura stato;
	@ManyToOne(cascade=CascadeType.REMOVE)
	private Cliente cliente;
	public Fattura() {
	}
	public Fattura(LocalDate data, String numero, Double importo, StatoFattura stato, Cliente cliente) {
		this.data = data;
		this.numero = numero;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
		this.anno = data.format(formatter);
		this.importo = importo;
		this.stato = stato;
		this.cliente = cliente;
	}
	
}
