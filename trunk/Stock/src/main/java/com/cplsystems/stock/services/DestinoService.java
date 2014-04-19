/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.DestinoDAO;
import com.cplsystems.stock.domain.Destino;

/**
 * @author Carlos Palalía López
 */

@Service
public class DestinoService {

	@Autowired
	private DestinoDAO destinoDAO;

	public void save(Destino destino) throws DataAccessException {
		destinoDAO.save(destino);
	}

	public Destino getById(Long idDestino) throws DataAccessException {
		return destinoDAO.getById(idDestino);
	}

	public Destino getByNombre(String lugar) throws DataAccessException {
		return destinoDAO.getByNombre(lugar);
	}

	public List<Destino> getAll() throws DataAccessException {
		return destinoDAO.getAll();
	}

}
