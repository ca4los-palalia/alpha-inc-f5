package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.MonedaDAO;
import com.cplsystems.stock.domain.Moneda;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MonedaService {
	@Autowired
	private MonedaDAO monedaDAO;

	public void save(Moneda moneda) throws DataAccessException {
		this.monedaDAO.save(moneda);
	}

	public void delete(Moneda moneda) throws DataAccessException {
		this.monedaDAO.delete(moneda);
	}

	public Moneda getById(Long idMoneda) throws DataAccessException {
		return this.monedaDAO.getById(idMoneda);
	}

	public List<Moneda> getAll() throws DataAccessException {
		return this.monedaDAO.getAll();
	}
}
