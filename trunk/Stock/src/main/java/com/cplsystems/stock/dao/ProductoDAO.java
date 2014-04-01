/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */

public interface ProductoDAO {

	public void save(Producto producto);
	public void update(Producto producto);
	public void delete(Producto producto);
	public Producto getById(Long idProducto);
	public List<Producto> getAll();
	
}
