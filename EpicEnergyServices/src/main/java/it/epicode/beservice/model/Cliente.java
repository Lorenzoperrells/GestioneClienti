package it.epicode.beservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
@Entity
@Table
@Data
public class Cliente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String ragioneSociale;
	private String partitaIva;
	@Enumerated(EnumType.STRING)
	private TipoCliente tipoCliente;
	private String email;
	private String pec;
	private String telefono;
	private String nomeContatto;
	private String cognomeContatto;
	private String telefonoContatto;
	private String emailContatto;
	@OneToOne
	private Indirizzo indirizzoSedeOperativa;
	@OneToOne
	private Indirizzo indirizzoSedeLegale;
	private LocalDate dataInserimento;
	private LocalDate dataUltimoContatto;
	private double fatturatoAnnuale;
	public Cliente() {
	}
	public Cliente(String ragioneSociale, String partitaIva, TipoCliente tipoCliente, String email, String pec,
			String telefono, String nomeContatto, String cognomeContatto, String telefonoContatto, String emailContatto,
			Indirizzo indirizzoSedeOperativa, Indirizzo indirizzoSedeLegale,LocalDate dataUltimoContatto, double fatturatoAnnuale) {
		super();
		this.ragioneSociale = ragioneSociale;
		this.partitaIva = partitaIva;
		this.tipoCliente = tipoCliente;
		this.email = email;
		this.pec = pec;
		this.telefono = telefono;
		this.nomeContatto = nomeContatto;
		this.cognomeContatto = cognomeContatto;
		this.telefonoContatto = telefonoContatto;
		this.emailContatto = emailContatto;
		this.indirizzoSedeOperativa = indirizzoSedeOperativa;
		this.indirizzoSedeLegale = indirizzoSedeLegale;
		this.dataInserimento = LocalDate.now();
		this.dataUltimoContatto = dataUltimoContatto;
		this.fatturatoAnnuale = fatturatoAnnuale;
	}
	
	
}
