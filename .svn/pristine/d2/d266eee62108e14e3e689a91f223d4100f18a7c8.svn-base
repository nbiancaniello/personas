package com.mercel.personas.persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inssjp.commons.paging.PagingLoadConfig;
import com.mercel.personas.model.CuentaBancaria;
import com.mercel.personas.model.Persona;
import com.mercel.personas.model.TipoDocumento;

public class PersonaJDBCDaoImpl implements PersonaDAO {
	
	private Logger log = LoggerFactory.getLogger(PersonaJDBCDaoImpl.class);

	@Override
	public List<Persona> findPersonas(PagingLoadConfig paginado, String sortField, String sortOrder, String keyword, String columnSearch) {
		List<Persona> personas = new ArrayList<Persona>();
		Connection conexion = ConnectionFactory.getConexion();		
		CallableStatement stmt = null;
		
		try{
			int param = 1;
			stmt = conexion.prepareCall("BEGIN PERSONA_PACKAGE_NICO.GETPERSONAS(?,?,?,?,?,?,?,?); END;");
			
			if (keyword == null || keyword.trim() == "")
				stmt.setNull(param++, java.sql.Types.CHAR);
			else
				stmt.setString(param++, keyword.trim().toUpperCase());
			
			if (columnSearch == null || columnSearch.trim() == "")
				stmt.setNull(param++, java.sql.Types.CHAR);
			else
				stmt.setString(param++, columnSearch.trim());
			
			if (sortField == null || sortField.trim() == "")
				stmt.setNull(param++, java.sql.Types.CHAR);
			else
				stmt.setString(param++, sortField.trim());
			
			if (sortOrder == null || sortOrder.trim() == "")
				stmt.setNull(param++, java.sql.Types.CHAR);
			else
				stmt.setString(param++, sortOrder.trim());
			
			if (paginado.getOffset() == null)
				stmt.setNull(param++, java.sql.Types.INTEGER);
			else
				stmt.setInt(param++, paginado.getOffset());
			
			if (paginado.getLimit() == null)
				stmt.setNull(param++, java.sql.Types.INTEGER);
			else
				stmt.setInt(param++, paginado.getLimit());
			
			stmt.registerOutParameter(param++, OracleTypes.CURSOR);
			stmt.registerOutParameter(param, OracleTypes.VARCHAR);
			
			stmt.execute();
			
			ResultSet rset = ((OracleCallableStatement) stmt).getCursor(7);
			
			if(rset != null){
				while(rset.next()){
					Persona persona = new Persona();
					personas.add(generarPersona(persona,rset));
				}
				return personas;
			}else {
				log.info("No hay resultados");
			}
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conexion != null) conexion.close();
			}
			catch(SQLException ex) {
				log.error("Error al cerrar la conexión", ex);
			}
		}
		
		return null;
	}
	
	public Persona generarPersona(Persona persona, ResultSet rset){
		
		try{
			persona.setId(rset.getInt(1));
			persona.setNombre(rset.getString(2));
			persona.setApellido(rset.getString(3));
			persona.setTipo_doc(new TipoDocumento(rset.getInt(4),rset.getString(5)));
			persona.setNro_doc(rset.getInt(6));
			persona.setFecha_nacimiento(rset.getDate(7));
		}catch (Exception e){
			e.printStackTrace();
		}
		return persona;
	}

	public int getPersonasCantidad(String keyword, String columnSearch) {
		Connection conexion = ConnectionFactory.getConexion();		
		CallableStatement stmt = null;
		
		try{
			int param = 1;
			stmt = conexion.prepareCall("BEGIN PERSONA_PACKAGE_NICO.GETPERSONASCANTIDAD(?,?,?); END;");
			
			if (keyword == null)
				stmt.setNull(param++, java.sql.Types.CHAR);
			else
				stmt.setString(param++, keyword.trim().toUpperCase());
			
			if (columnSearch == null)
				stmt.setNull(param++, java.sql.Types.CHAR);
			else
				stmt.setString(param++, columnSearch.trim());
			
			stmt.registerOutParameter(param++, OracleTypes.INTEGER);
			
			stmt.execute();
			return stmt.getInt(3);
			
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conexion != null) conexion.close();
			}
			catch(SQLException ex) {
				log.error("Error al cerrar la conexión", ex);
			}
		}
		
		return 0;
	}

	@Override
	public String save(Persona tmpPersona) {
		Connection conexion = ConnectionFactory.getConexion();
		CallableStatement stmt = null;
		int paramPersona = 1;
		String resultado;
		
			try {
				// Inserto en la tabla temporal de cuentas bancarias
				
				if (tmpPersona.getCuentasBancarias() != null){
				
					for( CuentaBancaria ctaBancaria : tmpPersona.getCuentasBancarias()){
						
						try{ 
							stmt = conexion.prepareCall("Begin PERSONA_PACKAGE_NICO.saveTempCtas(?,?,?,?); End;");
							
							stmt.setInt(paramPersona++, tmpPersona.getId()); //1
							stmt.setInt(paramPersona++, ctaBancaria.getNroCuenta()); //2
							stmt.setString(paramPersona++, ctaBancaria.getNombreBanco()); //3
							
							stmt.registerOutParameter(paramPersona, OracleTypes.VARCHAR);  // 4
							
							stmt.execute();
							
							resultado = stmt.getString(4);
	//						if(!StringUtils.isBlank(resultado)){
	//							log.error(resultado);
	//							throw new AppErrorException(resultado);
	//						}	
							
							paramPersona = 1;
						} catch (SQLException e) {
							log.error("Error en savePersona " + e.getMessage());										
						}finally{
							if (stmt != null) stmt.close();
						}
					}
				}
				
				stmt = conexion.prepareCall("Begin PERSONA_PACKAGE_NICO.savePersona(?,?,?,?,?,?,?); End;");
				stmt.setInt(paramPersona++, tmpPersona.getId()); //1
				stmt.setString(paramPersona++, tmpPersona.getNombre()); //2 
				stmt.setString(paramPersona++, tmpPersona.getApellido()); //3
				stmt.setInt(paramPersona++, tmpPersona.getTipo_doc().getId()); //4
				stmt.setDate(paramPersona++, new java.sql.Date(tmpPersona.getFecha_nacimiento().getTime())); //5
				stmt.setInt(paramPersona++, tmpPersona.getNro_doc()); //6
				
				stmt.registerOutParameter(paramPersona++, OracleTypes.VARCHAR);  // 7
				
				stmt.execute();
				
				resultado = null;
				resultado = stmt.getString(7);
//				if(!StringUtils.isBlank(resultado)){
//					log.error(resultado);
//					throw new AppErrorException(resultado);
//				}	
				
			
			} catch (SQLException e) {
				log.error("Error en savePersona " + e.getMessage());
			}finally {	/* cierro la conexión */
				try {
					if (stmt != null) stmt.close();
					if (conexion != null) conexion.close();
				}
				catch(SQLException ex) {
					log.error("Error al cerrar la conexión", ex);
				}
			}
		return null;
	}

	@Override
	public String delete(Persona personaToRemove) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persona getById(int idPersona) {
		Connection conexion = ConnectionFactory.getConexion();		
		CallableStatement stmt = null;
		
		try{
			int param = 1;
			stmt = conexion.prepareCall("BEGIN PERSONA_PACKAGE_NICO.GETPERSONASBYID(?,?,?,?); END;");
			stmt.setInt(param++, idPersona);		
			stmt.registerOutParameter(param++, OracleTypes.CURSOR);
			stmt.registerOutParameter(param++, OracleTypes.CURSOR);
			stmt.registerOutParameter(param, OracleTypes.VARCHAR);
			
			stmt.execute();
			
			ResultSet rset = ((OracleCallableStatement) stmt).getCursor(2);
			
			if(rset != null){
				while(rset.next()){
					Persona persona = new Persona();
					persona = generarPersona(persona,rset);
					
					ResultSet rsetcta = ((OracleCallableStatement) stmt).getCursor(3);
					
					List<CuentaBancaria> ctas = new ArrayList<CuentaBancaria>();
					
					if(rsetcta != null){
						while(rsetcta.next()){
							CuentaBancaria cta = new CuentaBancaria();
							ctas.add(generarCuentaBancaria(cta,rsetcta));
						}
						
					
					}else {
						log.info("No hay resultados");
					}
					
					persona.setCuentasBancarias(ctas);
					return persona;
				}
				
			}else {
				log.info("No hay resultados");
			}
			
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conexion != null) conexion.close();
			}
			catch(SQLException ex) {
				log.error("Error al cerrar la conexión", ex);
			}
		}
		
		return null;
	}
	
	public CuentaBancaria generarCuentaBancaria(CuentaBancaria cta, ResultSet rset){
		
		try{
			cta.setId(rset.getInt(1));
			cta.setNroCuenta(rset.getInt(2));
			cta.setNombreBanco (rset.getString(3));
		}catch (Exception e){
			e.printStackTrace();
		}
		return cta;
	}

	@Override
	public int getPersonasCantidad() {
		// TODO Auto-generated method stub
		return 0;
	}

}


	