package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.DireccionEntregaDAO;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.DireccionEntrega;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DireccionEntregaService {
	@Autowired
	private DireccionEntregaDAO direccionEntregaDAO;

	public void save(DireccionEntrega direccion) throws DataAccessException {
		this.direccionEntregaDAO.save(direccion);
	}

	public void delete(DireccionEntrega direccion) throws DataAccessException {
		this.direccionEntregaDAO.delete(direccion);
	}

	public DireccionEntrega getById(Long direccion) throws DataAccessException {
		return this.direccionEntregaDAO.getById(direccion);
	}

	public List<DireccionEntrega> getByDireccion(Direccion direccion) throws DataAccessException {
		return this.direccionEntregaDAO.getByDireccion(direccion);
	}

	public List<DireccionEntrega> getAll() throws DataAccessException {
		return this.direccionEntregaDAO.getAll();
	}
}
