package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.CalculosCostoDAO;
import com.cplsystems.stock.domain.CalculosCosto;

@Service
public class CalculosCostoService {
	@Autowired
	private CalculosCostoDAO calculosCostoDAO;

	public void save(CalculosCosto calculosCosto) throws DataAccessException {
		this.calculosCostoDAO.save(calculosCosto);
	}

	public void delete(CalculosCosto calculosCosto) throws DataAccessException {
		this.calculosCostoDAO.delete(calculosCosto);
	}

	public CalculosCosto getById(Long idCalculosCosto) throws DataAccessException {
		return this.calculosCostoDAO.getById(idCalculosCosto);
	}

	public List<CalculosCosto> getAll() throws DataAccessException {
		return this.calculosCostoDAO.getAll();
	}
}
