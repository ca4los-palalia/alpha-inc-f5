/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ProductoDAO;
import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ProductoDAOImpl extends HibernateDAOSuportUtil implements ProductoDAO{

	public void save(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	public void update(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Producto producto) {
		// TODO Auto-generated method stub
		
	}

	public Producto getById(Long idProducto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
   
}
