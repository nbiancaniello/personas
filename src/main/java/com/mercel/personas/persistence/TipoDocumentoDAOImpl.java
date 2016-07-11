package com.mercel.personas.persistence;

import java.util.ArrayList;
import java.util.List;

import com.mercel.personas.model.TipoDocumento;

public class TipoDocumentoDAOImpl implements TipoDocumentoDAO {
	
	public List<TipoDocumento> tipoDocumentos = new ArrayList<TipoDocumento>();
	
	public TipoDocumentoDAOImpl(){
		tipoDocumentos.add(new TipoDocumento(1,"DNI"));
		tipoDocumentos.add(new TipoDocumento(2,"LE"));
		tipoDocumentos.add(new TipoDocumento(3,"LC"));
	}

	@Override
	public List<TipoDocumento> getTipoDocumentos() {
		return tipoDocumentos;
	}
	
}
