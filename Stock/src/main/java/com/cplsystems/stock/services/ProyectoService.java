package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.ProyectoDAO;
import com.cplsystems.stock.domain.Proyecto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProyectoService {
	@Autowired
	private ProyectoDAO proyectopAO;

	public void save(Proyecto proyecto) throws DataAccessException {
		this.proyectopAO.save(proyecto);
	}

	public Proyecto getById(Long proyecto) throws DataAccessException {
		return this.proyectopAO.getById(proyecto);
	}

	public List<Proyecto> getAll() throws DataAccessException {
		return this.proyectopAO.getAll();
	}
}
