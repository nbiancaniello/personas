package com.mercel.personas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TIPO_DOCUMENTO")
public class TipoDocumento {
	
	@Id @GeneratedValue
	@Column(name="ID_TIPO_DOCUMENTO")
	private Integer id;
	
	@Column(name="D_TIPO_DOCUMENTO")
	private String desc;
	
	public TipoDocumento(){
		
	}
	
	public TipoDocumento(Integer id, String desc){
		super();
		this.id = id;
		this.desc = desc;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
