package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.LugarDAO;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Proyecto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LugarService {
	@Autowired
	private LugarDAO lugarDAO;

	public void save(Lugar lugar) throws DataAccessException {
		this.lugarDAO.save(lugar);
	}

	public void delete(Lugar lugar) throws DataAccessException {
		this.lugarDAO.delete(lugar);
	}

	public Lugar getById(Long idLugar) throws DataAccessException {
		return this.lugarDAO.getById(idLugar);
	}

	public Lugar getByIdProyecto(Proyecto proyecto) throws DataAccessException {
		return this.lugarDAO.getByIdProyecto(proyecto);
	}

	public Lugar getByNombre(String nombre) throws DataAccessException {
		return this.lugarDAO.getByNombre(nombre);
	}

	public List<Lugar> getAll() throws DataAccessException {
		return this.lugarDAO.getAll();
	}
}
