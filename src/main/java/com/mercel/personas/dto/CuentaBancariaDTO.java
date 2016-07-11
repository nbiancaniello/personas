package com.mercel.personas.dto;

public class CuentaBancariaDTO {
	
	private Integer id;
	private Integer nroCuenta;
	private String nombreBanco;
	
	public CuentaBancariaDTO(){
		
	}
	
	public CuentaBancariaDTO(Integer id, Integer nroCuenta, String nombreBanco){
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
}
