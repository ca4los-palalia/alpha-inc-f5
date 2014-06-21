/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.CostosProducto;
import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */

public interface CostosProductoDAO {

	public void save(CostosProducto costosProducto);

	public void delete(CostosProducto costosProducto);

	public CostosProducto getById(Long idCostosProducto);

	public List<CostosProducto> getAll();

	public CostosProducto getByProducto(Producto producto);
}
