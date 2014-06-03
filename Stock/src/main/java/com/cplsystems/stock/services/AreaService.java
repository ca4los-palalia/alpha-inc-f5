/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.AreaDAO;
import com.cplsystems.stock.domain.Area;

/**
 * @author Carlos Palalía López
 */

@Service
public class AreaService {

	@Autowired
	private AreaDAO areaDAO;
 

	public void save(Area area) throws DataAccessException {
		areaDAO.save(area);
	}
	
	public void update(Area area) throws DataAccessException {
		areaDAO.update(area);
	}

	public void delete(Area area) throws DataAccessException {
		areaDAO.delete(area);
	}

	public Area getById(Long idArea) throws DataAccessException {
		return areaDAO.getById(idArea);
	}

	public List<Area> getAll() throws DataAccessException {
		return areaDAO.getAll();
	}

}
