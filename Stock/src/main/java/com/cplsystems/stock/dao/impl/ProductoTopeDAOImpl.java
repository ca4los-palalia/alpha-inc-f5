package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ProductoTopeDAO;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTope;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoTopeDAOImpl extends HibernateDAOSuportUtil implements ProductoTopeDAO {
	public void save(ProductoTope productoTope) {
	}

	public void update(ProductoTope productoTope) {
	}

	public void delete(ProductoTope productoTope) {
	}

	public ProductoTope getById(Long idProductoTope) {
		return null;
	}

	public List<ProductoTope> getAll() {
		return null;
	}

	public List<ProductoTope> getByProducto(Producto producto) {
		return null;
	}

	public List<ProductoTope> getByLugar(Lugar lugar) {
		return null;
	}
}
