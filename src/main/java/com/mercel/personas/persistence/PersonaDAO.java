package com.mercel.personas.persistence;

import com.inssjp.commons.paging.PagingLoadConfig;
import com.mercel.personas.model.*;

import java.util.List;

public interface PersonaDAO {
	
	public abstract List<Persona> findPersonas(PagingLoadConfig paginado, String sortField, String sortOrder, String keyword, String columnSearch);
	
	public int getPersonasCantidad();
	
	public int getPersonasCantidad(String keyword, String columnSearch);
	
	public Persona getById(int idPersona);

	public String save(Persona tmpPersona);

	public String delete(Persona personaToRemove);
	
}
