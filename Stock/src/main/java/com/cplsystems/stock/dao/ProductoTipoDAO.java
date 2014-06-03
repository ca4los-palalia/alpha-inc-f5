/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.ProductoTipo;

/**
 * @author Carlos Palalía López
 */

public interface ProductoTipoDAO {

	public void saveOrUpdate(final ProductoTipo productoTipo);
	public void save(final ProductoTipo productoTipo);
	public void update(final ProductoTipo productoTipo);
	public void delete(final ProductoTipo productoTipo);
	public ProductoTipo getById(final Long idProductoTipo);
	public List<ProductoTipo> getAll();
	public ProductoTipo getByNombre(final String nombre);

}
