/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.ProductoNaturalezaDAO;
import com.cplsystems.stock.domain.ProductoNaturaleza;

/**
 * @author Carlos Palalía López
 */

@Service
public class ProductoNaturalezaService {

	@Autowired
	private ProductoNaturalezaDAO productoNaturalezaDAO;

	public void save(final ProductoNaturaleza productoNaturaleza){
		productoNaturalezaDAO.save(productoNaturaleza);
	}
	public void update(final ProductoNaturaleza productoNaturaleza){
		productoNaturalezaDAO.update(productoNaturaleza);
	}
	public void delete(final ProductoNaturaleza productoNaturaleza){
		productoNaturalezaDAO.delete(productoNaturaleza);
	}
	public ProductoNaturaleza getById(final Long idProductoNaturaleza){
		return productoNaturalezaDAO.getById(idProductoNaturaleza);
	}
	public List<ProductoNaturaleza> getAll(){
		return productoNaturalezaDAO.getAll();
	}
	public ProductoNaturaleza getByNombre(final String nombre){
		return productoNaturalezaDAO.getByNombre(nombre);
	}
	public ProductoNaturaleza getBySimbolo(final String simbolo){
		return productoNaturalezaDAO.getBySimbolo(simbolo);
	}

}
