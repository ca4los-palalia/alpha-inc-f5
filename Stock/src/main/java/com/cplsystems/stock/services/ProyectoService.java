/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.ProyectoDAO;
import com.cplsystems.stock.domain.Proyecto;

/**
 * @author Carlos Palalía López
 */

@Service
public class ProyectoService {

	@Autowired
	private ProyectoDAO proyectopAO;

	public void save(Proyecto proyecto) throws DataAccessException {
		proyectopAO.save(proyecto);
	}

	public Proyecto getById(Long proyecto) throws DataAccessException {
		return proyectopAO.getById(proyecto);
	}

	public List<Proyecto> getAll() throws DataAccessException {
		return proyectopAO.getAll();
	}

}
