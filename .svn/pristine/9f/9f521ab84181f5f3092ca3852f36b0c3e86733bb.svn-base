package com.mercel.personas.webapp.viewmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Window;

import com.mercel.personas.dto.CuentaBancariaDTO;
import com.mercel.personas.dto.PersonaDTO;
import com.mercel.personas.dto.PersonasLivianoDTO;
import com.mercel.personas.dto.TipoDocumentoDTO;
import com.mercel.personas.model.Persona;
import com.mercel.personas.service.PersonaServiceImpl;
import com.mercel.personas.service.TipoDocumentoServiceImpl;

public class PersonasABM extends SelectorComposer<Component> {
    private static final long serialVersionUID = 1L;
    private List<TipoDocumentoDTO> tipoDocumentoList = new ArrayList<TipoDocumentoDTO>();
    private TipoDocumentoServiceImpl tipoDocumentoServiceImpl = new TipoDocumentoServiceImpl();
    private PersonaDTO persona;
    private List<String> listadoErrores = new ArrayList<String>();
    private PersonaServiceImpl personaServiceImpl = new PersonaServiceImpl();
    private Window winAgregarCta;
    
    @Init
	public void init(@BindingParam("Persona")PersonasLivianoDTO persona, @BindingParam("esNuevo")Boolean esNuevo){
    	
    	if (!esNuevo){
	    	setPersona(personaServiceImpl.getById(persona.getId()));
    	}else{
    		setPersona(new PersonaDTO());
    		//persona.setId(0);
    	}
    	setTipoDocumentoList(tipoDocumentoServiceImpl.getTipoDocumentos());
	}

    @Command
    @NotifyChange({"listadoErrores"})
	public void save(@ContextParam(ContextType.VIEW) Window comp) {
  
    	setListadoErrores(personaServiceImpl.validarDatos(persona));
    	
    	if (listadoErrores.isEmpty()) {
    		personaServiceImpl.save(persona);
    		comp.detach();
    		BindUtils.postGlobalCommand(null, null, "refrescarListado", null);
    	}
	}
    
    @Command
	public void close(@ContextParam(ContextType.VIEW) Window comp) {
		comp.detach();
	}
    
    @Command
    public void verCuentas(@BindingParam("persona") Persona persona) {
		Executions.getCurrent().setAttribute("persona", this.persona);
		winAgregarCta = (Window) Executions.createComponents("/CuentasBancariasABM.zul", null, null);
    }
    
    @GlobalCommand
    @NotifyChange({"persona"})
    public void refrescarCuentas(){
    	winAgregarCta.detach();
    }
    
    @Command
	@NotifyChange("persona")
	public void eliminarCta( @BindingParam("cuentaBancaria") CuentaBancariaDTO ctaToRemove, @BindingParam("persona") PersonaDTO persona) {

		// Saco la cuenta bancaria de la lista de cuentas bancarias, al darle
		// accion la borra fisicamente de la BD
		for (Iterator<CuentaBancariaDTO> iter = getPersona().getCuentasBancarias().listIterator(); iter.hasNext();) {
			CuentaBancariaDTO ctaBancaria = iter.next();
			if (ctaToRemove.getId() == ctaBancaria.getId()) {
				iter.remove();
			}
		}
	}
	
	public PersonaDTO getPersona() {
		return persona;
	}

	public void setPersona(PersonaDTO persona) {
		this.persona = persona;
	}

	public List<TipoDocumentoDTO> getTipoDocumentoList() {
		return tipoDocumentoList;
	}

	public void setTipoDocumentoList(List<TipoDocumentoDTO> tipoDocumentoList) {
		this.tipoDocumentoList = tipoDocumentoList;
	}

	public List<String> getListadoErrores() {
		return listadoErrores;
	}

	public void setListadoErrores(List<String> listadoErrores) {
		this.listadoErrores = listadoErrores;
	}
}