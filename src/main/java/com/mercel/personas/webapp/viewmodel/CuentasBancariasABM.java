package com.mercel.personas.webapp.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;

import com.mercel.personas.dto.CuentaBancariaDTO;
import com.mercel.personas.dto.PersonaDTO;

public class CuentasBancariasABM {
    private CuentaBancariaDTO cuenta = new CuentaBancariaDTO();
    private PersonaDTO persona = new PersonaDTO();
	
    @Init
	public void onCreate(){
    	setPersona((PersonaDTO) Executions.getCurrent().getAttribute("persona"));
	}

    @Command
	public void save() {
    	if (getPersona().getCuentasBancarias().size() == 0){
			List<CuentaBancariaDTO> listaCtas = new ArrayList<CuentaBancariaDTO>();
			cuenta.setId(0);
			listaCtas.add(cuenta);
			getPersona().setCuentasBancarias(listaCtas);
		}else{
			getPersona().getCuentasBancarias().add(cuenta);
		}
		BindUtils.postGlobalCommand(null, null, "refrescarCuentas", null);
	}

	public CuentaBancariaDTO getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaBancariaDTO cuenta) {
		this.cuenta = cuenta;
	}

	public PersonaDTO getPersona() {
		return persona;
	}

	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
	}  
}