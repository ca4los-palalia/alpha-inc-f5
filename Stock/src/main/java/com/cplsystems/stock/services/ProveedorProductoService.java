package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.ProveedorProductoDAO;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProveedorProductoService {
	@Autowired
	private ProveedorProductoDAO proveedorProductoDAO;

	public void save(ProveedorProducto proveedorProducto) throws DataAccessException {
		this.proveedorProductoDAO.save(proveedorProducto);
	}

	public void delete(ProveedorProducto proveedorProducto) throws DataAccessException {
		this.proveedorProductoDAO.delete(proveedorProducto);
	}

	public ProveedorProducto getById(Long idProveedorProducto) throws DataAccessException {
		return this.proveedorProductoDAO.getById(idProveedorProducto);
	}

	public List<ProveedorProducto> getByProveedor(Proveedor proveedor) throws DataAccessException {
		return this.proveedorProductoDAO.getByProveedor(proveedor);
	}

	public List<ProveedorProducto> getByProducto(Producto producto) throws DataAccessException {
		return this.proveedorProductoDAO.getByProducto(producto);
	}

	public List<ProveedorProducto> getAll() throws DataAccessException {
		return this.proveedorProductoDAO.getAll();
	}

	public List<ProveedorProducto> getByProductoProveedor(Producto producto, Proveedor proveedor) {
		return this.proveedorProductoDAO.getByProductoProveedor(producto, proveedor);
	}
}
