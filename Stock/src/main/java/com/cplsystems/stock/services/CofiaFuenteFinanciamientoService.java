package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.CofiaFuenteFinanciamientoDAO;
import com.cplsystems.stock.domain.CofiaFuenteFinanciamiento;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CofiaFuenteFinanciamientoService {
	@Autowired
	private CofiaFuenteFinanciamientoDAO CofiaFuenteFinanciamientoDAO;

	public void save(CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento) {
		this.CofiaFuenteFinanciamientoDAO.save(cofiaFuenteFinanciamiento);
	}

	public void delete(CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento) {
		this.CofiaFuenteFinanciamientoDAO.delete(cofiaFuenteFinanciamiento);
	}

	public CofiaFuenteFinanciamiento getById(Long idCofiaFuenteFinanciamiento) {
		return this.CofiaFuenteFinanciamientoDAO.getById(idCofiaFuenteFinanciamiento);
	}

	public List<CofiaFuenteFinanciamiento> getAll() {
		return this.CofiaFuenteFinanciamientoDAO.getAll();
	}
}
