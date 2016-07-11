package com.mercel.personas.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.inssjp.commons.paging.PagingLoadConfig;
import com.mercel.personas.dto.PersonaDTO;
import com.mercel.personas.dto.PersonasLivianoDTO;
import com.mercel.personas.model.CuentaBancaria;
import com.mercel.personas.model.Persona;
import com.mercel.personas.persistence.HibernateDaoImpl;
import com.mercel.personas.persistence.PersonaDAO;
//import com.mercel.personas.persistence.PersonaInMemoryDAOImpl;
//import com.mercel.personas.persistence.PersonaJDBCDaoImpl;
//import com.mercel.personas.persistence.PersonaJSONDAOImpl;
//import com.mercel.personas.persistence.PersonaXMLDAOImpl;
import com.mercel.personas.validations.ValidadorDatos;


public class PersonaServiceImpl implements PersonaService {
	
	//PersonaDAO personaDAO = new PersonaInMemoryDAOImpl();
	//PersonaDAO personaDAO = new PersonaJSONDAOImpl();
	//PersonaDAO personaDAO = new PersonaXMLDAOImpl();
	//PersonaDAO personaDAO = new PersonaJDBCDaoImpl();
	PersonaDAO personaDAO = new HibernateDaoImpl();
	Mapper mapper = new DozerBeanMapper();

	public List<PersonasLivianoDTO> findPersonas(PagingLoadConfig paginado, String sortField, String sortOrder, String keyword, String columnSearch){
		List<Persona> listadoPersonas = new ArrayList<Persona>();
		List<PersonasLivianoDTO> listadoPersonasLiviano = new ArrayList<PersonasLivianoDTO>();
		
		listadoPersonas = personaDAO.findPersonas(paginado, sortField, sortOrder,keyword,columnSearch);
		
		for(Persona persona : listadoPersonas){
			listadoPersonasLiviano.add(mapper.map(persona, PersonasLivianoDTO.class));
		}
		
		return listadoPersonasLiviano;
	}
	
	public int getPersonasCantidad(){
		return personaDAO.getPersonasCantidad();
	}
	
	public int getPersonasCantidad(String keyword, String columnSearch){
		return personaDAO.getPersonasCantidad(keyword, columnSearch);
	}
	
	public String save(PersonaDTO tmpPersona){
		Persona personaEntity = mapper.map(tmpPersona, Persona.class);
		
		if(tmpPersona.getCuentasBancarias() != null){
			if (tmpPersona.getCuentasBancarias().size() > 0){
				for(CuentaBancaria cuentaBancaria : personaEntity.getCuentasBancarias()){
					cuentaBancaria.setPersona(personaEntity);					
				}
			}
		}
		
		return personaDAO.save(personaEntity);
	}
	
	public List<String> validarDatos(PersonaDTO persona){
		
		List<String> listadoValidaciones = new ArrayList<String>();
		
		if (ValidadorDatos.esVacio(persona.getNombre()))
			listadoValidaciones.add("Nombre Vacío");
    	if (!ValidadorDatos.esAlfabetico(persona.getNombre()))
    		listadoValidaciones.add("Nombre Inválido");
    	if (!ValidadorDatos.verificarLongitud(persona.getNombre(), 30))
    		listadoValidaciones.add("Nombre con longitud inválida");
    	
    	if (ValidadorDatos.esVacio(persona.getApellido()))
    		listadoValidaciones.add("Apellido Vacío"); 	
    	if (!ValidadorDatos.esAlfabetico(persona.getApellido()))
    		listadoValidaciones.add("Apellido Inválido");
    	if (!ValidadorDatos.verificarLongitud(persona.getApellido(), 50))
    		listadoValidaciones.add("Apellido con longitud inválida");
    	
    	if (ValidadorDatos.esVacio(Integer.toString(persona.getNro_doc())))
    		listadoValidaciones.add("Número de Documento Vacío");
    	if (!ValidadorDatos.esNumerico(persona.getNro_doc()))
    		listadoValidaciones.add("Número de Documento Inválido");
    	if (!ValidadorDatos.verificarLongitud(Integer.toString(persona.getNro_doc()), 8))
    		listadoValidaciones.add("Número con longitud inválida");
    	
    	if (ValidadorDatos.esVacio(persona.getFecha_nacimiento().toString()))
    		listadoValidaciones.add("Fecha de Nacimiento Vacía");
    	if(!ValidadorDatos.validarFecha(persona.getFecha_nacimiento()))
    		listadoValidaciones.add("Fecha de Nacimiento Inválida");
    	
    	return listadoValidaciones;
    	
	}
 
	public String delete(PersonaDTO selectedPersona){
		Persona personaEntity = mapper.map(selectedPersona, Persona.class);
		return personaDAO.delete(personaEntity);
	}
	
	public PersonaDTO getById(int idPersona) {
		
		Persona persona = personaDAO.getById(idPersona);
		PersonaDTO unaPersonaDTO = mapper.map(persona, PersonaDTO.class);
		
		return unaPersonaDTO;  
	}	

}
