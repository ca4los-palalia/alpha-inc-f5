/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.MonedaDAO;
import com.cplsystems.stock.domain.Moneda;

/**
 * @author Carlos Palalía López
 */

@Service
public class MonedaService {

	@Autowired
	private MonedaDAO monedaDAO;

	public void saveOrUpdate(Moneda moneda) throws DataAccessException {
		monedaDAO.saveOrUpdate(moneda);
	}
	
	public void save(Moneda moneda) throws DataAccessException {
		monedaDAO.save(moneda);
	}
	
	public void update(Moneda moneda) throws DataAccessException {
		monedaDAO.update(moneda);
	}

	public void delete(Moneda moneda) throws DataAccessException {
		monedaDAO.delete(moneda);
	}

	public Moneda getById(Long idMoneda) throws DataAccessException {
		return monedaDAO.getById(idMoneda);
	}

	public List<Moneda> getAll() throws DataAccessException {
		return monedaDAO.getAll();
	}

}
