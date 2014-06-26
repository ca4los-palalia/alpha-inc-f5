/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;

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
	
	public Producto getByClaveNombrePrecioCosto(String buscarTexto);
	
	public List<Producto> getPreciosMaximos();
	
	public List<Producto> getPreciosMinimos();
	
	public List<Producto> getPreciosPromedio();
	
	public List<Producto> getByPrecio(String precio);
}
