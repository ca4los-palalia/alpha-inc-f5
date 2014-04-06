/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	public void save(OrdenCompra ordenCompra){
		ordenCompraDAO.save(ordenCompra);
	}
    public void update(OrdenCompra ordenCompra){
    	ordenCompraDAO.update(ordenCompra);
    }
    public void delete(OrdenCompra ordenCompra){
    	ordenCompraDAO.delete(ordenCompra);
    }
    public OrdenCompra getById(Long idOrdenCompra){
    	return ordenCompraDAO.getById(idOrdenCompra);
    }
    public List<OrdenCompra> getAll(){
    	return ordenCompraDAO.getAll();
    }
    public List<OrdenCompra> getByCotizacion(Cotizacion cotizacion){
    	return ordenCompraDAO.getByCotizacion(cotizacion);
    }
	
}
