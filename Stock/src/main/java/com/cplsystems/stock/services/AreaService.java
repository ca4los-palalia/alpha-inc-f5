package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.AreaDAO;
import com.cplsystems.stock.domain.Area;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AreaService {
	@Autowired
	private AreaDAO areaDAO;

	public void save(Area area) throws DataAccessException {
		this.areaDAO.save(area);
	}

	public void update(Area area) throws DataAccessException {
		this.areaDAO.update(area);
	}

	public void delete(Area area) throws DataAccessException {
		this.areaDAO.delete(area);
	}

	public Area getById(Long idArea) throws DataAccessException {
		return this.areaDAO.getById(idArea);
	}

	public List<Area> getAll() throws DataAccessException {
		return this.areaDAO.getAll();
	}

	public Area getByNombre(String nombre) {
		return this.areaDAO.getByNombre(nombre);
	}
}
