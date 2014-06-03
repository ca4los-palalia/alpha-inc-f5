/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;
import com.cplsystems.stock.domain.Proveedor;

/**
 * @author Carlos Palalía López
 */

public interface ProductoDAO {

	public void save(Producto producto);

	public void delete(Producto producto);

	public Producto getById(Long idProducto);

	public List<Producto> getAll();

	public List<Producto> getItemByKeyOrName(String claveProducto,
			String nombreProducto);

	public List<String> getAllKeys();
	
	public List<Producto> getByClaveNombre(String buscarTexto);
	
	public List<Producto> getByTipo(ProductoTipo productoTipo);
}
