package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.PosicionDAO;
import com.cplsystems.stock.domain.Posicion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PosicionService {
	@Autowired
	private PosicionDAO posicionDAO;

	public void saveOrUpdate(Posicion posicion) throws DataAccessException {
		this.posicionDAO.saveOrUpdate(posicion);
	}

	public void update(Posicion posicion) throws DataAccessException {
		this.posicionDAO.update(posicion);
	}

	public void save(Posicion posicion) throws DataAccessException {
		this.posicionDAO.save(posicion);
	}

	public void delete(Posicion posicion) throws DataAccessException {
		this.posicionDAO.delete(posicion);
	}

	public Posicion getById(Long idPosicion) throws DataAccessException {
		return this.posicionDAO.getById(idPosicion);
	}

	public List<Posicion> getAll() throws DataAccessException {
		return this.posicionDAO.getAll();
	}
}
