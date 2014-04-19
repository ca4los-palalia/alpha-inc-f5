/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.EmailDAO;
import com.cplsystems.stock.dao.TelefonoDAO;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Telefono;

/**
 * @author Carlos Palalía López
 */

@Service
public class TelefonoService {

	@Autowired
	private TelefonoDAO telefonoDAO;

	public void save(Telefono telefono) throws DataAccessException {
		telefonoDAO.save(telefono);
	}

	public void delete(Telefono telefono) throws DataAccessException {
		telefonoDAO.delete(telefono);
	}

	public Telefono getById(Long idTelefono) throws DataAccessException {
		return telefonoDAO.getById(idTelefono);
	}

	public List<Telefono> getAll() throws DataAccessException {
		return telefonoDAO.getAll();
	}

}
