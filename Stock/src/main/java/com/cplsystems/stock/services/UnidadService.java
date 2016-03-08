package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.UnidadDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UnidadService {
	@Autowired
	private UnidadDAO unidadDAO;

	public void save(Unidad unidad) throws DataAccessException {
		this.unidadDAO.save(unidad);
	}

	public void update(Unidad unidad) throws DataAccessException {
		this.unidadDAO.update(unidad);
	}

	public void delete(Unidad unidad) throws DataAccessException {
		this.unidadDAO.delete(unidad);
	}

	public Unidad getById(Long idUnidad) throws DataAccessException {
		return this.unidadDAO.getById(idUnidad);
	}

	public List<Unidad> getAll() throws DataAccessException {
		return this.unidadDAO.getAll();
	}

	public Unidad getByNombre(String nombre) throws DataAccessException {
		return this.unidadDAO.getByNombre(nombre);
	}

	public List<Unidad> getByOrganizacion(Organizacion organizacion) {
		return this.unidadDAO.getByOrganizacion(organizacion);
	}

	public List<Unidad> getByUsuario(Usuarios usuarios) {
		return this.unidadDAO.getByUsuario(usuarios);
	}
}
