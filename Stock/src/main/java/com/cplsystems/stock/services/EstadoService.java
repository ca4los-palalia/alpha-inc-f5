/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.EstadoDAO;
import com.cplsystems.stock.domain.Estado;

/**
 * @author Carlos Palalía López
 */

@Service
public class EstadoService {

	@Autowired
	private EstadoDAO estadoDAO;

	public void save(Estado estado) throws DataAccessException {
		estadoDAO.save(estado);
	}

	public void delete(Estado estado) throws DataAccessException {
		estadoDAO.delete(estado);
	}

	public Estado getById(Long estado) throws DataAccessException {
		return estadoDAO.getById(estado);
	}

	public List<Estado> getAll() throws DataAccessException {
		return estadoDAO.getAll();
	}

}
