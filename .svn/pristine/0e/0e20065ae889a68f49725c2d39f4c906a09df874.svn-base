package com.mercel.personas.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import com.mercel.personas.dto.TipoDocumentoDTO;
import com.mercel.personas.model.TipoDocumento;
import com.mercel.personas.persistence.TipoDocumentoDAO;
import com.mercel.personas.persistence.TipoDocumentoDAOImpl;

public class TipoDocumentoServiceImpl {

	TipoDocumentoDAO tipoDocumentoDAO = new TipoDocumentoDAOImpl();
	Mapper mapper = new DozerBeanMapper();
	
	public List<TipoDocumentoDTO> getTipoDocumentos() {
		List<TipoDocumentoDTO> tiposDoc = new ArrayList<TipoDocumentoDTO>();
		for(TipoDocumento tipoDocEntity : tipoDocumentoDAO.getTipoDocumentos()){
			tiposDoc.add(mapper.map(tipoDocEntity, TipoDocumentoDTO.class)); 
		}
		
		return tiposDoc;
	}
}