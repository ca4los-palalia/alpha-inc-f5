package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.TelefonoDAO;
import com.cplsystems.stock.domain.Telefono;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TelefonoService {
	@Autowired
	private TelefonoDAO telefonoDAO;

	public void save(Telefono telefono) throws DataAccessException {
		this.telefonoDAO.save(telefono);
	}

	public void delete(Telefono telefono) throws DataAccessException {
		this.telefonoDAO.delete(telefono);
	}

	public Telefono getById(Long idTelefono) throws DataAccessException {
		return this.telefonoDAO.getById(idTelefono);
	}

	public List<Telefono> getAll() throws DataAccessException {
		return this.telefonoDAO.getAll();
	}

	public Telefono getUltimoregistroEmail() throws DataAccessException {
		return this.telefonoDAO.getUltimoregistroEmail();
	}
}
