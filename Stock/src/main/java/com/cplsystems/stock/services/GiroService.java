package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.GiroDAO;
import com.cplsystems.stock.domain.Giro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GiroService {
	@Autowired
	private GiroDAO giroDAO;

	public void save(Giro giro) {
		this.giroDAO.save(giro);
	}

	public void delete(Giro giro) {
		this.giroDAO.delete(giro);
	}

	public Giro getById(Long idGiro) {
		return this.giroDAO.getById(idGiro);
	}

	public List<Giro> getAll() {
		return this.giroDAO.getAll();
	}

	public Giro getByNombre(String nombre) {
		return this.giroDAO.getByNombre(nombre);
	}
}
