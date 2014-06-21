/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.ProductoNaturaleza;

/**
 * @author Carlos Palalía López
 */

public interface ProductoNaturalezaDAO {

	public void save(final ProductoNaturaleza productoNaturaleza);
	public void update(final ProductoNaturaleza productoNaturaleza);
	public void delete(final ProductoNaturaleza productoNaturaleza);
	public ProductoNaturaleza getById(final Long idProductoNaturaleza);
	public List<ProductoNaturaleza> getAll();
	public ProductoNaturaleza getByNombre(final String nombre);
	public ProductoNaturaleza getBySimbolo(final String simbolo);

}
