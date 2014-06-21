/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.FamiliasProducto;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;

/**
 * @author Carlos Palalía López
 */

public interface FamiliasProductoDAO {

	public void save(FamiliasProducto familiasProducto);
	
	public void update(FamiliasProducto familiasProducto);

	public void delete(FamiliasProducto familiasProducto);

	public FamiliasProducto getById(Long idFamiliasProducto);

	public List<FamiliasProducto> getAll();

	public List<FamiliasProducto> getByProducto(Producto producto);
	
	public List<FamiliasProducto> getByFamilia(ProductoTipo productoTipo);

}
