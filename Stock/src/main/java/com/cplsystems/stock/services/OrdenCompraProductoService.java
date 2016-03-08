package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.OrdenCompraProductoDAO;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraProducto;
import com.cplsystems.stock.domain.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class OrdenCompraProductoService {
	@Autowired
	private OrdenCompraProductoDAO ordenCompraProductoDAO;

	public void save(OrdenCompraProducto ordenCompraProducto) throws DataAccessException {
		this.ordenCompraProductoDAO.save(ordenCompraProducto);
	}

	public void delete(OrdenCompraProducto ordenCompraProducto) throws DataAccessException {
		this.ordenCompraProductoDAO.delete(ordenCompraProducto);
	}

	public OrdenCompraProducto getById(Long idOrdenCompraProducto) throws DataAccessException {
		return this.ordenCompraProductoDAO.getById(idOrdenCompraProducto);
	}

	public List<OrdenCompraProducto> getAll() throws DataAccessException {
		return this.ordenCompraProductoDAO.getAll();
	}

	public List<OrdenCompraProducto> getByOrdenCopra(OrdenCompra ordenCompra) throws DataAccessException {
		return this.ordenCompraProductoDAO.getByOrdenCopra(ordenCompra);
	}

	public List<OrdenCompraProducto> getByProducto(Producto producto) throws DataAccessException {
		return this.ordenCompraProductoDAO.getByProducto(producto);
	}
}
