/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.OrdenCompraProductoDAO;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraProducto;
import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */

@Service
public class OrdenCompraProductoService {

	@Autowired
	private OrdenCompraProductoDAO ordenCompraProductoDAO;

	public void save(OrdenCompraProducto ordenCompraProducto)
			throws DataAccessException {
		ordenCompraProductoDAO.save(ordenCompraProducto);
	}

	public void delete(OrdenCompraProducto ordenCompraProducto)
			throws DataAccessException {
		ordenCompraProductoDAO.delete(ordenCompraProducto);
	}

	public OrdenCompraProducto getById(Long idOrdenCompraProducto)
			throws DataAccessException {
		return ordenCompraProductoDAO.getById(idOrdenCompraProducto);
	}

	public List<OrdenCompraProducto> getAll() throws DataAccessException {
		return ordenCompraProductoDAO.getAll();
	}

	public List<OrdenCompraProducto> getByOrdenCopra(OrdenCompra ordenCompra)
			throws DataAccessException {
		return ordenCompraProductoDAO.getByOrdenCopra(ordenCompra);
	}

	public List<OrdenCompraProducto> getByProducto(Producto producto)
			throws DataAccessException {
		return ordenCompraProductoDAO.getByProducto(producto);
	}

}
