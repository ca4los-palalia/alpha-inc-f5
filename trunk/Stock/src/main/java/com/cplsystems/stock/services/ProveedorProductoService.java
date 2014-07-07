/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.ProveedorProductoDAO;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;

/**
 * @author Carlos Palalía López
 */

@Service
public class ProveedorProductoService {

	@Autowired
	private ProveedorProductoDAO proveedorProductoDAO;

	public void save(ProveedorProducto proveedorProducto)
			throws DataAccessException {
		proveedorProductoDAO.save(proveedorProducto);
	}

	public void delete(ProveedorProducto proveedorProducto)
			throws DataAccessException {
		proveedorProductoDAO.delete(proveedorProducto);
	}

	public ProveedorProducto getById(Long idProveedorProducto)
			throws DataAccessException {
		return proveedorProductoDAO.getById(idProveedorProducto);
	}

	public List<ProveedorProducto> getByProveedor(Proveedor proveedor)
			throws DataAccessException {
		return proveedorProductoDAO.getByProveedor(proveedor);
	}

	public List<ProveedorProducto> getByProducto(Producto producto)
			throws DataAccessException {
		return proveedorProductoDAO.getByProducto(producto);
	}

	public List<ProveedorProducto> getAll() throws DataAccessException {
		return proveedorProductoDAO.getAll();
	}
	
	public List<ProveedorProducto> getByProductoProveedor(Producto producto, Proveedor proveedor){
		return proveedorProductoDAO.getByProductoProveedor(producto, proveedor);
	}
}
