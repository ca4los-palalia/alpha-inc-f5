/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.DireccionEntregaDAO;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.DireccionEntrega;

/**
 * @author Carlos Palalía López
 */

@Service
public class DireccionEntregaService {

	@Autowired
	private DireccionEntregaDAO direccionEntregaDAO;

	public void save(DireccionEntrega direccion) throws DataAccessException {
		direccionEntregaDAO.save(direccion);
	}

	public void delete(DireccionEntrega direccion) throws DataAccessException {
		direccionEntregaDAO.delete(direccion);
	}

	public DireccionEntrega getById(Long direccion) throws DataAccessException {
		return direccionEntregaDAO.getById(direccion);
	}

	public List<DireccionEntrega> getByDireccion(Direccion direccion)
			throws DataAccessException {
		return direccionEntregaDAO.getByDireccion(direccion);
	}

	public List<DireccionEntrega> getAll() throws DataAccessException {
		return direccionEntregaDAO.getAll();
	}

}
