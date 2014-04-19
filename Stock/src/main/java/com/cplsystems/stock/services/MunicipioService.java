/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.MunicipioDAO;
import com.cplsystems.stock.domain.Municipio;

/**
 * @author Carlos Palalía López
 */

@Service
public class MunicipioService {

	@Autowired
	private MunicipioDAO municipioDAO;

	public void save(Municipio estado) throws DataAccessException {
		municipioDAO.save(estado);
	}

	public void delete(Municipio estado) throws DataAccessException {
		municipioDAO.delete(estado);
	}

	public Municipio getById(Long idMunicipio) throws DataAccessException {
		return municipioDAO.getById(idMunicipio);
	}

	public List<Municipio> getAll() throws DataAccessException {
		return municipioDAO.getAll();
	}

}
