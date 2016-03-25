package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.AlmacenDAO;
import com.cplsystems.stock.domain.Almacen;
import com.cplsystems.stock.domain.Area;

@Service
public class AlmacenService {
	@Autowired
	private AlmacenDAO almacenDAO;

	public void save(Almacen almacen) throws DataAccessException {
		this.almacenDAO.save(almacen);
	}

	public void delete(Almacen almacen) throws DataAccessException {
		this.almacenDAO.delete(almacen);
	}

	public Almacen getById(Long idAlmacen) throws DataAccessException {
		return this.almacenDAO.getById(idAlmacen);
	}

	public List<Almacen> getAll() throws DataAccessException {
		return this.almacenDAO.getAll();
	}
	public List<Almacen> getByArea(Area area) throws DataAccessException {
		return this.almacenDAO.getByArea(area);
	}
}
