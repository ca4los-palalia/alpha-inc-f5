/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.OrdenCompraDAO;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.OrdenCompra;

/**
 * @author Carlos Palalía López
 */

@Service
public class OrdenCompraService {

	@Autowired
	private OrdenCompraDAO ordenCompraDAO;

	public void save(OrdenCompra ordenCompra) throws DataAccessException {
		ordenCompraDAO.save(ordenCompra);
	}

	public void delete(OrdenCompra ordenCompra) throws DataAccessException {
		ordenCompraDAO.delete(ordenCompra);
	}

	public OrdenCompra getById(Long idOrdenCompra) throws DataAccessException {
		return ordenCompraDAO.getById(idOrdenCompra);
	}

	public List<OrdenCompra> getAll() throws DataAccessException {
		return ordenCompraDAO.getAll();
	}

	public List<OrdenCompra> getByCotizacion(Cotizacion cotizacion)
			throws DataAccessException {
		return ordenCompraDAO.getByCotizacion(cotizacion);
	}

}