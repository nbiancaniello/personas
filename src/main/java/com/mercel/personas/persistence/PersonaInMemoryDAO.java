package com.mercel.personas.persistence;

import java.util.List;

import com.mercel.personas.model.Persona;

public interface PersonaInMemoryDAO {
		
	public List<Persona> getListaPersonas();
	
	public String save(Persona persona);
	
	public String delete(Persona personaToRemove);
	
}
