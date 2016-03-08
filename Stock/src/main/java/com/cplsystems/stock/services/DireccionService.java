package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.DireccionDAO;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class DireccionService {
	@Autowired
	private DireccionDAO direccionDAO;

	public void save(Direccion direccion) throws DataAccessException {
		this.direccionDAO.save(direccion);
	}

	public Direccion getById(Long direccion) throws DataAccessException {
		return this.direccionDAO.getById(direccion);
	}

	public Direccion getUltimoRegistroDireccion() {
		return this.direccionDAO.getUltimoRegistroDireccion();
	}

	public List<Direccion> getByCodigoPostalId(String codigoPostal) throws DataAccessException {
		return this.direccionDAO.getByCodigoPostalId(codigoPostal);
	}

	public List<Direccion> getByEstado(Estado estado) throws DataAccessException {
		return this.direccionDAO.getByEstado(estado);
	}

	public List<Direccion> getByMunicipio(Municipio municipio) throws DataAccessException {
		return this.direccionDAO.getByMunicipio(municipio);
	}

	public List<Direccion> getAll() throws DataAccessException {
		return this.direccionDAO.getAll();
	}
}
