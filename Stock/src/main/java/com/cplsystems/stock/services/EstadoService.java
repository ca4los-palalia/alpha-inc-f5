package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.EstadoDAO;
import com.cplsystems.stock.domain.Estado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
	@Autowired
	private EstadoDAO estadoDAO;

	public void save(Estado estado) throws DataAccessException {
		this.estadoDAO.save(estado);
	}

	public void delete(Estado estado) throws DataAccessException {
		this.estadoDAO.delete(estado);
	}

	public Estado getById(Long estado) throws DataAccessException {
		return this.estadoDAO.getById(estado);
	}

	public List<Estado> getAll() throws DataAccessException {
		return this.estadoDAO.getAll();
	}
	
	public Estado getByName(String name) throws DataAccessException {
		return this.estadoDAO.getByName(name);
	}
}
