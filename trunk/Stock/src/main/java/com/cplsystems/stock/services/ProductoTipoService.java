/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.ProductoTipoDAO;
import com.cplsystems.stock.domain.ProductoTipo;

/**
 * @author Carlos Palalía López
 */

@Service
public class ProductoTipoService {

	@Autowired
	private ProductoTipoDAO productoTipoDAO;

	public void saveOrUpdate(final ProductoTipo productoTipo) throws DataAccessException {
		productoTipoDAO.saveOrUpdate(productoTipo);
	}
	
	public void save(final ProductoTipo productoTipo){
		productoTipoDAO.save(productoTipo);
	}
	
	public void update(final ProductoTipo productoTipo){
		productoTipoDAO.update(productoTipo);
	}
	
	public void delete(final ProductoTipo productoTipo) throws DataAccessException {
		productoTipoDAO.delete(productoTipo);
	}
	public ProductoTipo getById(final Long idProductoTipo) throws DataAccessException {
		return productoTipoDAO.getById(idProductoTipo);
	}
	public List<ProductoTipo> getAll() throws DataAccessException {
		return productoTipoDAO.getAll();
	}
	public ProductoTipo getByNombre(final String nombre) throws DataAccessException {
		return productoTipoDAO.getByNombre(nombre);
	}

}
