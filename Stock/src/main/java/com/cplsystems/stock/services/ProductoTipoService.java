package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.ProductoTipoDAO;
import com.cplsystems.stock.domain.ProductoTipo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProductoTipoService {
	@Autowired
	private ProductoTipoDAO productoTipoDAO;

	public void saveOrUpdate(ProductoTipo productoTipo) throws DataAccessException {
		this.productoTipoDAO.saveOrUpdate(productoTipo);
	}

	public void save(ProductoTipo productoTipo) {
		this.productoTipoDAO.save(productoTipo);
	}

	public void update(ProductoTipo productoTipo) {
		this.productoTipoDAO.update(productoTipo);
	}

	public void delete(ProductoTipo productoTipo) throws DataAccessException {
		this.productoTipoDAO.delete(productoTipo);
	}

	public ProductoTipo getById(Long idProductoTipo) throws DataAccessException {
		return this.productoTipoDAO.getById(idProductoTipo);
	}

	public List<ProductoTipo> getAll() throws DataAccessException {
		return this.productoTipoDAO.getAll();
	}

	public ProductoTipo getByNombre(String nombre) throws DataAccessException {
		return this.productoTipoDAO.getByNombre(nombre);
	}
}
