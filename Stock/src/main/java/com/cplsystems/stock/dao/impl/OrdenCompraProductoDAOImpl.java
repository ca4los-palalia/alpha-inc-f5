package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.dao.OrdenCompraProductoDAO;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraProducto;
import com.cplsystems.stock.domain.Producto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class OrdenCompraProductoDAOImpl implements OrdenCompraProductoDAO {
	public void save(OrdenCompraProducto ordenCompraProducto) {
	}

	public void update(OrdenCompraProducto ordenCompraProducto) {
	}

	public void delete(OrdenCompraProducto ordenCompraProducto) {
	}

	public OrdenCompraProducto getById(Long idOrdenCompraProducto) {
		return null;
	}

	public List<OrdenCompraProducto> getAll() {
		return null;
	}

	public List<OrdenCompraProducto> getByOrdenCopra(OrdenCompra ordenCompra) {
		return null;
	}

	public List<OrdenCompraProducto> getByProducto(Producto producto) {
		return null;
	}
}
