package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.CofiaPyDAO;
import com.cplsystems.stock.domain.CofiaPy;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CofiaPyService {
	@Autowired
	private CofiaPyDAO cofiaPyDAO;

	public void save(CofiaPy cofiaPy) {
		this.cofiaPyDAO.save(cofiaPy);
	}

	public void delete(CofiaPy cofiaPy) {
		this.cofiaPyDAO.delete(cofiaPy);
	}

	public CofiaPy getById(Long idCofiaPy) {
		return this.cofiaPyDAO.getById(idCofiaPy);
	}

	public List<CofiaPy> getAll() {
		return this.cofiaPyDAO.getAll();
	}
}
