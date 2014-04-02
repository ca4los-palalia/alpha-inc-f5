/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.ProductoDAO;
import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */

@Service
public class ProductoService {

	@Autowired
	private ProductoDAO productoDAO;

	public void save(Producto producto){
		productoDAO.save(producto);
	}
	public void update(Producto producto){
		productoDAO.update(producto);
	}
	public void delete(Producto producto){
		productoDAO.delete(producto);
	}
	public Producto getById(Long idProducto){
		return productoDAO.getById(idProducto);
	}
	public List<Producto> getAll(){
		return productoDAO.getAll();
	}
	
}
