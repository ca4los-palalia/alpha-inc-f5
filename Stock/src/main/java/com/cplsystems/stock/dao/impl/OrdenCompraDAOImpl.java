/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.dao.OrdenCompraDAO;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author Carlos Palalía López
 */

@Repository
public class OrdenCompraDAOImpl extends HibernateDAOSuportUtil implements OrdenCompraDAO{

	@Autowired
	private SessionUtils sessionUtils;
	
	private Organizacion getOrganizacion(){
		return (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
	}
	
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
