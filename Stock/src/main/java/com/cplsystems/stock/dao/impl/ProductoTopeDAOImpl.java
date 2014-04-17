/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ProductoTopeDAO;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTope;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ProductoTopeDAOImpl extends HibernateDAOSuportUtil implements ProductoTopeDAO{

	public void save(ProductoTope productoTope) {
		// TODO Auto-generated method stub
		
	}

	public void update(ProductoTope productoTope) {
		// TODO Auto-generated method stub
		
	}

	public void delete(ProductoTope productoTope) {
		// TODO Auto-generated method stub
		
	}

	public ProductoTope getById(Long idProductoTope) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductoTope> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductoTope> getByProducto(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductoTope> getByLugar(Lugar lugar) {
		// TODO Auto-generated method stub
		return null;
	}

	
   
}
