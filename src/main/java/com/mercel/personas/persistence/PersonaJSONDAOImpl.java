package com.mercel.personas.persistence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.mercel.personas.model.Persona;

public class PersonaJSONDAOImpl extends PersonaDAOImpl{
	public List<Persona> personas = new ArrayList<Persona>();
	
	public PersonaJSONDAOImpl() {
		
		try {
			JsonFactory f = new MappingJsonFactory();
			//JsonParser jp = f.createParser(new File("C://temp.json"));
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("data/temp.json").getFile());
			JsonParser jp = f.createParser(file);
			
		    JsonToken current = jp.nextToken();
		    if (current != JsonToken.START_OBJECT) {
		      System.out.println("Error: root should be object: quiting.");
		      return;
		    }

		    while (jp.nextToken() != JsonToken.END_OBJECT) {
		      String fieldName = jp.getCurrentName();
		      // move from field name to field value
		      current = jp.nextToken();
		      if (fieldName.equals("personas")) {
		        if (current == JsonToken.START_ARRAY) {
		          // For each of the records in the array
		          while (jp.nextToken() != JsonToken.END_ARRAY) {
		            // read the record into a tree model,
		            // this moves the parsing position to the end of it
		            JsonNode node = jp.readValueAsTree();
		            // And now we have random access to everything in the object
//		            personas.add(new Persona(node.get("id").asInt(), node.get("nombre").asText(), node.get("apellido").asText(), 
//		            						node.get("tipo_doc").asText(), node.get("nro_doc").asText(), node.get("fecha_nacimiento").asText()));
		          }
		        } else {
		          System.out.println("Error: records should be an array: skipping.");
		          jp.skipChildren();
		        }
		      } else {
		        System.out.println("Unprocessed property: " + fieldName);
		        jp.skipChildren();
		      }
		    }
		}catch (Exception e){
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
