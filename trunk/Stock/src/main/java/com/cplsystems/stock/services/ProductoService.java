/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	public void save(Producto producto) throws DataAccessException {
		productoDAO.save(producto);
	}

	public void delete(Producto producto) throws DataAccessException {
		productoDAO.delete(producto);
	}

	public Producto getById(Long idProducto) throws DataAccessException {
		return productoDAO.getById(idProducto);
	}

	public List<Producto> getAll() throws DataAccessException {
		return productoDAO.getAll();
	}

	public List<Producto> getItemByKeyOrName(String claveProducto,
			String nombreProducto) {
		return productoDAO.getItemByKeyOrName(claveProducto, nombreProducto);
	}

}
