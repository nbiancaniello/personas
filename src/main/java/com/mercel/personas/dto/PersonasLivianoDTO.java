package com.mercel.personas.dto;

import java.util.Calendar;
import java.util.Date;

public class PersonasLivianoDTO{
	private Integer id;
	private String nombre;
	private String apellido;
	private TipoDocumentoDTO tipo_doc;
	private Integer nro_doc;
	private Date fecha_nacimiento;
	
	public PersonasLivianoDTO(){
	}
	
	public PersonasLivianoDTO (Integer id, String nombre, String apellido, TipoDocumentoDTO tipo_doc, Integer nro_doc, Date fecha_nacimiento){
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipo_doc = tipo_doc;
		this.nro_doc = nro_doc;
		
		Calendar dCal = Calendar.getInstance();
		dCal.setTime(fecha_nacimiento);
		this.fecha_nacimiento = dCal.getTime();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public TipoDocumentoDTO getTipo_doc() {
		return tipo_doc;
	}
	public void setTipo_doc(TipoDocumentoDTO tipo_doc) {
		this.tipo_doc = tipo_doc;
	}
	public Integer getNro_doc() {
		return nro_doc;
	}
	public void setNro_doc(Integer nro_doc) {
		this.nro_doc = nro_doc;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

}
