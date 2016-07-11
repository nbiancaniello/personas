package com.mercel.personas.persistence;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.mercel.personas.model.Persona;

public class PersonaXMLDAOImpl extends PersonaDAOImpl{
	public List<Persona> personas = new ArrayList<Persona>();
	
	public PersonaXMLDAOImpl() {
				
        try {
        	ClassLoader classLoader = getClass().getClassLoader();
			File inputFile = new File(classLoader.getResource("data/temp.xml").getFile());
            SAXReader reader = new SAXReader();
            Document document = reader.read( inputFile );
            
			@SuppressWarnings("unchecked")
			List<Node> nodes = document.selectNodes("/class/persona" );
			
		    for (Node node : nodes) {
//		    	personas.add(new Persona (Integer.parseInt(node.valueOf("@id")), node.selectSingleNode("nombre").getText(), node.selectSingleNode("apellido").getText(),
//		    				node.selectSingleNode("tipo_doc").getText(), node.selectSingleNode("nro_doc").getText(), node.selectSingleNode("fecha_nacimiento").getText()));
		   }
        } catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}

	@Override
	public List<Persona> getListaPersonas() {
		return personas;
	}

	@Override
	public String save(Persona tmpPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Persona personaToRemove) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona getById(int idPersona) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPersonasCantidad(String keyword, String columnSearch) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
