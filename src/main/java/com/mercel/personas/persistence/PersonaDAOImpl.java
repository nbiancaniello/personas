package com.mercel.personas.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.inssjp.commons.paging.PagingLoadConfig;
import com.mercel.personas.model.Persona;

public abstract class PersonaDAOImpl implements PersonaDAO{
	public int cantidad;
	
	public PersonaDAOImpl() {
		
	}
	
	public abstract List<Persona> getListaPersonas();
	
	public List<Persona> findPersonas(PagingLoadConfig paginado, String sortField, String sortOrder, String keyword, String columnSearch){
		
		List<Persona> lista = getListaPersonas();
		
		if (columnSearch.length() != 0 && keyword.length() != 0)
			lista = filtrarLista(lista,keyword,columnSearch);
		
		if ((paginado.getOffset() + paginado.getLimit()) > lista.size() &&
				paginado.getOffset() > 0)
			paginado.setLimit(lista.size() - paginado.getLimit());
			
		if (lista.size() < paginado.getLimit())
			paginado.setLimit(lista.size());
		
		ordenarPersonas(lista, sortField, sortOrder);
		
		setCantidad(lista.size());
		
		return lista.subList(paginado.getOffset(), paginado.getOffset() + paginado.getLimit());
	}
	
	public void ordenarPersonas(List<Persona> personas, String sortField, String sortOrder){
		
		switch (sortField.toString()) {
        case "id":
        	if (sortOrder == "asc")
        		Collections.sort(personas, Persona.COMPARAR_POR_ID);
        	else
        		Collections.sort(personas, Persona.COMPARAR_POR_ID_DESC);
        	break;
        case "nombre":
        	if (sortOrder == "asc")
        		Collections.sort(personas, Persona.COMPARAR_POR_NOMBRE);
        	else
        		Collections.sort(personas, Persona.COMPARAR_POR_NOMBRE_DESC);
        	break;
        case "apellido":
        	if (sortOrder == "asc")
        		Collections.sort(personas, Persona.COMPARAR_POR_APELLIDO);
        	else
        		Collections.sort(personas, Persona.COMPARAR_POR_APELLIDO_DESC);
        	break;
		//        case "tipo_doc":
		//        	if (sortOrder == "asc")
		//        		Collections.sort(personas, Persona.COMPARAR_POR_TIPODOC);
		//        	else
		//        		Collections.sort(personas, Persona.COMPARAR_POR_TIPODOC_DESC);
		//        	break;
        case "nro_doc":
        	if (sortOrder == "asc")
        		Collections.sort(personas, Persona.COMPARAR_POR_NRODOC);
        	else
        		Collections.sort(personas, Persona.COMPARAR_POR_NRODOC_DESC);
        	break;
		//        case "fecha_nacimiento":
		//        	if (sortOrder == "asc")
		//        		Collections.sort(personas, Persona.COMPARAR_POR_FECHANAC);
		//        	else
		//        		Collections.sort(personas, Persona.COMPARAR_POR_FECHANAC_DESC);
		//        	break;
        default:
        	if (sortOrder == "asc")
        		Collections.sort(personas, Persona.COMPARAR_POR_ID);
        	else
        		Collections.sort(personas, Persona.COMPARAR_POR_ID_DESC);
        	break;
        }
	}
	
	public List<Persona> filtrarLista(List<Persona> personas, String keyword, String columnSearch){
		int cant = personas.size() - 1;
		List<Persona> listaTemp = new ArrayList<Persona>();
		
		if (columnSearch.trim() == "id"){
			for(int i=0;i<=cant;i++){
				if (personas.get(i).getId() == Integer.parseInt(keyword))
					listaTemp.add(personas.get(i));
			}
		}
		
		if (columnSearch.trim() == "nombre"){
			for(int i=0;i<=cant;i++){
				if (personas.get(i).getNombre().toLowerCase().contains(keyword.toLowerCase()))
					listaTemp.add(personas.get(i));
			}
		}
		
		if (columnSearch.trim() == "apellido"){
			for(int i=0;i<=cant;i++){
				if (personas.get(i).getApellido().toLowerCase().contains(keyword.toLowerCase()))
					listaTemp.add(personas.get(i));
			}
		}
		
		//		if (columnSearch.trim() == "tipo_doc"){
		//			for(int i=0;i<=cant;i++){
		//				if (personas.get(i).getTipo_doc().toLowerCase().contains(keyword.toLowerCase()))
		//					listaTemp.add(personas.get(i));
		//			}
		//		}
		
		if (columnSearch.trim() == "nro_doc"){
			for(int i=0;i<=cant;i++){
				if (personas.get(i).getNro_doc() == Integer.parseInt(keyword))
					listaTemp.add(personas.get(i));
			}
		}
		
		if (columnSearch.trim() == "fecha_nacimiento"){
			for(int i=0;i<=cant;i++){
				if (personas.get(i).getFecha_nacimiento().toString().contains(keyword.toLowerCase()))
					listaTemp.add(personas.get(i));
			}
		}
		
		return listaTemp;
	}
	
	public int getPersonasCantidad(){
		return cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}