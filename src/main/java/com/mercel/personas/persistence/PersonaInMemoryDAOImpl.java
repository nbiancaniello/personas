package com.mercel.personas.persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.mercel.personas.model.CuentaBancaria;
import com.mercel.personas.model.Persona;
import com.mercel.personas.model.TipoDocumento;

public class PersonaInMemoryDAOImpl extends PersonaDAOImpl{
	public List<Persona> personas = new ArrayList<Persona>();
	
	public PersonaInMemoryDAOImpl() {
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date date = formatter.parse("01/09/1986");
			
			List<CuentaBancaria> cuentas = new ArrayList<CuentaBancaria>();
			cuentas.add(new CuentaBancaria(1,123,"ICBC"));
			
			personas.add(new Persona(1,"Nicolas","Biancaniello",new TipoDocumento(1,"DNI"),32483157,date, cuentas));		
			date = formatter.parse("10/12/1993");
			personas.add(new Persona(2,"Nicolas","Lobruno",new TipoDocumento(1,"DNI"),38902437,date,null));
			date = formatter.parse("29/10/1985");
			personas.add(new Persona(3,"Alejandro","Acuña",new TipoDocumento(1,"DNI"),31992477,date,null));
			date = formatter.parse("22/08/1990");
			personas.add(new Persona(4,"Alejandro","Valencia",new TipoDocumento(1,"DNI"),35441820,date,null));
			date = formatter.parse("31/03/1980");
			personas.add(new Persona(5,"Alejo","Naveira",new TipoDocumento(1,"DNI"),27465109,date,null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Persona> getListaPersonas() {
		return personas;
	}
	
	public String save(Persona tmpPersona){
		
		if (tmpPersona.getId() == 0){
			tmpPersona.setId(personas.get(personas.size()-1).getId()+1);
			
			List<Persona> personaAdd = new ArrayList<Persona>();
			personaAdd.add(tmpPersona);			
			personas.addAll(personaAdd);
			
		}else{
			for (int i=0;i<=personas.size()-1;i++){
				if (personas.get(i).getId() == tmpPersona.getId()) {					
					personas.set(i, tmpPersona);
					break;
				}
			}
		}
		
		return "ok";
	}
	
	public String delete(Persona personaToRemove){
		
		for (Iterator<Persona> iter = personas.listIterator(); iter.hasNext(); ) {
		    Persona persona = iter.next();
		    if (persona.getId() == personaToRemove.getId()) {
		        iter.remove();
		        
		        System.out.println("Deleted id: " + persona.getId());
		        System.out.println("size " + personas.size());
		    }
		}
		return "ok";
	}

	public Persona getById(int idPersona) {

		for(Persona persona : personas){
			if(persona.getId() == idPersona)
			return persona;		
		}
		return null;
	}

	@Override
	public int getPersonasCantidad(String keyword, String columnSearch) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
