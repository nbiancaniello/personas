package com.mercel.personas.service;

import java.util.List;

import com.inssjp.commons.paging.PagingLoadConfig;
import com.mercel.personas.dto.PersonaDTO;
import com.mercel.personas.dto.PersonasLivianoDTO;

public interface PersonaService {

	public int getPersonasCantidad();
	
	public List<PersonasLivianoDTO> findPersonas(PagingLoadConfig paginado, String sortField, String sortOrder, String keyword, String columnSearch);
	
	public String save(PersonaDTO tmpPersona);
	
	public String delete(PersonaDTO personaToRemove);
}
