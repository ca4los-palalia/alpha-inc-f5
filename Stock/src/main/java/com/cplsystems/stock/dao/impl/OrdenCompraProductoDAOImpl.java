/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cplsystems.stock.dao.OrdenCompraProductoDAO;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraProducto;
import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */

@Repository
public class OrdenCompraProductoDAOImpl implements OrdenCompraProductoDAO{

	public void save(OrdenCompraProducto ordenCompraProducto) {
		// TODO Auto-generated method stub
		
	}

	public void update(OrdenCompraProducto ordenCompraProducto) {
		// TODO Auto-generated method stub
		
	}

	public void delete(OrdenCompraProducto ordenCompraProducto) {
		// TODO Auto-generated method stub
		
	}

	public OrdenCompraProducto getById(Long idOrdenCompraProducto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrdenCompraProducto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrdenCompraProducto> getByOrdenCopra(OrdenCompra ordenCompra) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<OrdenCompraProducto> getByProducto(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

}
