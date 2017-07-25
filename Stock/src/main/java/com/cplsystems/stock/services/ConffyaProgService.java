package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.CofiaProgDAO;
import com.cplsystems.stock.domain.CofiaProg;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CofiaProgService {
	@Autowired
	private CofiaProgDAO cofiaProgDAO;

	public void save(CofiaProg cofiaProg) {
		this.cofiaProgDAO.save(cofiaProg);
	}

	public void delete(CofiaProg cofiaProg) {
		this.cofiaProgDAO.delete(cofiaProg);
	}

	public CofiaProg getById(Long idCofiaProg) {
		return this.cofiaProgDAO.getById(idCofiaProg);
	}

	public List<CofiaProg> getAll() {
		return this.cofiaProgDAO.getAll();
	}
}
