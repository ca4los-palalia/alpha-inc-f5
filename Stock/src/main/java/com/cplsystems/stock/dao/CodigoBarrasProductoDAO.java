/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.CodigoBarrasProducto;
import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */

public interface CodigoBarrasProductoDAO {

	public void save(CodigoBarrasProducto codigoBarrasProducto);

	public void delete(CodigoBarrasProducto codigoBarrasProducto);

	public CodigoBarrasProducto getById(Long idCodigoBarrasProducto);

	public List<CodigoBarrasProducto> getAll();

	public List<CodigoBarrasProducto> getByCodigo(String codigo);
	
	public List<CodigoBarrasProducto> getByProducto(Producto producto);
}
