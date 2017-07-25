package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.CofiaPartidaGenericaDAO;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CofiaPartidaGenericaService {
	@Autowired
	private CofiaPartidaGenericaDAO cofiaPartidaGenericaDAO;

	public void save(CofiaPartidaGenerica cofiaPartidaGenerica) {
		this.cofiaPartidaGenericaDAO.save(cofiaPartidaGenerica);
	}

	public void delete(CofiaPartidaGenerica cofiaPartidaGenerica) {
		this.cofiaPartidaGenericaDAO.delete(cofiaPartidaGenerica);
	}

	public CofiaPartidaGenerica getById(Long idCofiaPartidaGenerica) {
		return this.cofiaPartidaGenericaDAO.getById(idCofiaPartidaGenerica);
	}

	public List<CofiaPartidaGenerica> getAll() {
		return this.cofiaPartidaGenericaDAO.getAll();
	}

	public CofiaPartidaGenerica getByNombre(String nombre) {
		return this.cofiaPartidaGenericaDAO.getByNombre(nombre);
	}
}
