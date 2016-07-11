package com.mercel.personas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CUENTA_BANCARIA_NICOLAS")
public class CuentaBancaria {
	
	@SequenceGenerator(name="CTA_SEQ", sequenceName="SEQ_CUENTA_BANC_N", allocationSize = 1, initialValue= 1)
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY, generator="CTA_SEQ")	
	@Column(name = "ID_CUENTA_BANCARIA")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA")
	private Persona persona;
	
	@Column(name="N_CUENTA")
	private Integer nroCuenta;
	
	@Column(name="D_NOMBRE_BANCO")
	private String nombreBanco;
	
	public CuentaBancaria(){
		
	}
	
	public CuentaBancaria(Integer id, Integer nroCuenta, String nombreBanco){
		super();
		this.id = id;
		this.nroCuenta = nroCuenta;
		this.nombreBanco = nombreBanco;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(Integer nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getNombreBanco() {
		return nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}
