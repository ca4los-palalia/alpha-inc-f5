/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTope;

/**
 * @author Carlos Palalía López
 */

public interface ProductoTopeDAO {

	public void save(ProductoTope productoTope);
	public void update(ProductoTope productoTope);
	public void delete(ProductoTope productoTope);
	public ProductoTope getById(Long idProductoTope);
	public List<ProductoTope> getAll();
	public List<ProductoTope> getByProducto(Producto producto);
	public List<ProductoTope> getByLugar(Lugar lugar);
	
}
