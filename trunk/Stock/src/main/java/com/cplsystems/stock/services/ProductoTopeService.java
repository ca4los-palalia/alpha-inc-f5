/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.ProductoTopeDAO;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTope;

/**
 * @author Carlos Palalía López
 */

@Service
public class ProductoTopeService {

	@Autowired
	private ProductoTopeDAO productoTopeDAO;

	public void save(ProductoTope productoTope){
		productoTopeDAO.save(productoTope);
	}
	public void update(ProductoTope productoTope){
		productoTopeDAO.update(productoTope);
	}
	public void delete(ProductoTope productoTope){
		productoTopeDAO.delete(productoTope);
	}
	public ProductoTope getById(Long idProductoTope){
		return productoTopeDAO.getById(idProductoTope);
	}
	public List<ProductoTope> getAll(){
		return productoTopeDAO.getAll();
	}
	public List<ProductoTope> getByProducto(Producto producto){
		return productoTopeDAO.getByProducto(producto);
	}
	public List<ProductoTope> getByLugar(Lugar lugar){
		return productoTopeDAO.getByLugar(lugar);
	}
	
}
