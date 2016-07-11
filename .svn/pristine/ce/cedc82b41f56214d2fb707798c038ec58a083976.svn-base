package com.mercel.personas.persistence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionFactory {
	private static Logger log = LoggerFactory.getLogger(ConnectionFactory.class);

	public static Connection getConexion() {
		
		Connection conn = null;
		Conexion conexion = ConnectionFactory.generaConexion();
		try{
			Class.forName(conexion.getDriver());		
			conn = DriverManager.getConnection(conexion.getUrl(), conexion.getUser(), conexion.getPassword());
			conn.setAutoCommit(Boolean.FALSE);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return conn;
	}
	
	private static Conexion generaConexion() {
		
		try {
			log.info("Generando Conexion");
	    	InputStream inputFile = ConnectionFactory.class.getResourceAsStream("/connection.xml");
	    	SAXReader reader = new SAXReader();
	        Document document = reader.read( inputFile );
	        Element root = document.getRootElement();

    		HashMap<String, String> map = new HashMap<String, String>();
            
	        for ( Iterator<?> i = root.elementIterator(); i.hasNext(); ) {
	        	Element nodoConnection = (Element) i.next();
	        	
	        	log.info("Elemento: "+nodoConnection.getName());
	        	
	        	for ( Iterator<?> j = nodoConnection.elementIterator(); j.hasNext(); ) {
	        		Element nodosHijo = (Element) j.next();
	        		
	        		log.info("Sub Elemento: "+nodosHijo.getName()+" - ");
	        		log.info("Valor: "+nodosHijo.getText());	
	        		
	        		map.put(nodosHijo.getName(),nodosHijo.getText());		          
	        	} 
	        }	        
	        
            Conexion conexion = new Conexion(map.get("connector"),map.get("url"),map.get("user"),map.get("pass"));
            return conexion;
	    } catch(Exception e) {
	    	log.error("Exception",e.getMessage());
	    }
		return null;	      
	}
}
