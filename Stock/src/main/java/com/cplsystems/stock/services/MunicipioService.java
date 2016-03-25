package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.MunicipioDAO;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MunicipioService {
	@Autowired
	private MunicipioDAO municipioDAO;

	public void save(Municipio estado) throws DataAccessException {
		this.municipioDAO.save(estado);
	}

	public void delete(Municipio estado) throws DataAccessException {
		this.municipioDAO.delete(estado);
	}

	public Municipio getById(Long idMunicipio) throws DataAccessException {
		return this.municipioDAO.getById(idMunicipio);
	}

	public List<Municipio> getAll() throws DataAccessException {
		return this.municipioDAO.getAll();
	}

	public List<Municipio> getByEstado(Estado estado) throws DataAccessException {
		return municipioDAO.getByEstado(estado);
	}
	
	public Municipio getByName(String name){
		return municipioDAO.getByName(name);
	}
}
