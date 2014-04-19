/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;

/**
 * @author Carlos Palalía López
 */

public interface ProveedorProductoDAO {

	public void save(ProveedorProducto proveedorProducto);

	public void delete(ProveedorProducto proveedorProducto);

	public ProveedorProducto getById(Long idProveedorProducto);

	public List<ProveedorProducto> getByProveedor(Proveedor Proveedor);

	public List<ProveedorProducto> getByProducto(Producto Producto);

	public List<ProveedorProducto> getAll();
}
