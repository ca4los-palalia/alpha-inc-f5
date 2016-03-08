package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.ClaveArmonizadaDAO;
import com.cplsystems.stock.domain.ClaveArmonizada;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ClaveArmonizadaService {
	@Autowired
	private ClaveArmonizadaDAO claveArmonizadaDAO;

	public void save(ClaveArmonizada claveArmonizada) throws DataAccessException {
		this.claveArmonizadaDAO.save(claveArmonizada);
	}

	public void delete(ClaveArmonizada claveArmonizada) throws DataAccessException {
		this.claveArmonizadaDAO.delete(claveArmonizada);
	}

	public ClaveArmonizada getById(Long idClaveArmonizada) {
		return this.claveArmonizadaDAO.getById(idClaveArmonizada);
	}

	public List<ClaveArmonizada> getAll() {
		return this.claveArmonizadaDAO.getAll();
	}

	public List<ClaveArmonizada> getByGrupo(Integer grupo) {
		return this.claveArmonizadaDAO.getByGrupo(grupo);
	}

	public List<ClaveArmonizada> getBySubGrupo(Integer subGrupo) {
		return this.claveArmonizadaDAO.getBySubGrupo(subGrupo);
	}

	public List<ClaveArmonizada> getByClase(Integer clase) {
		return this.claveArmonizadaDAO.getByClase(clase);
	}

	public ClaveArmonizada getByClave(String clave) {
		return this.claveArmonizadaDAO.getByClave(clave);
	}

	public ClaveArmonizada getByDescripcion(String descripcion) {
		return this.claveArmonizadaDAO.getByDescripcion(descripcion);
	}
}
