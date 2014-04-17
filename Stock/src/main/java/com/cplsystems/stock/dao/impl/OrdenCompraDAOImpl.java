/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.OrdenCompraDAO;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.OrdenCompra;

/**
 * @author Carlos Palalía López
 */

@Repository
public class OrdenCompraDAOImpl extends HibernateDAOSuportUtil implements OrdenCompraDAO{

	public void save(OrdenCompra ordenCompra) {
		// TODO Auto-generated method stub
		
	}

	public void update(OrdenCompra ordenCompra) {
		// TODO Auto-generated method stub
		
	}

	public void delete(OrdenCompra ordenCompra) {
		// TODO Auto-generated method stub
		
	}

	public OrdenCompra getById(Long idOrdenCompra) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrdenCompra> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrdenCompra> getByCotizacion(Cotizacion cotizacion) {
		// TODO Auto-generated method stub
		return null;
	}

}
