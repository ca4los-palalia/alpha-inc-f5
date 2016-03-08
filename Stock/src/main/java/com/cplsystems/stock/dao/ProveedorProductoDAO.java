package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;
import java.util.List;

public abstract interface ProveedorProductoDAO {
	public abstract void save(ProveedorProducto paramProveedorProducto);

	public abstract void delete(ProveedorProducto paramProveedorProducto);

	public abstract ProveedorProducto getById(Long paramLong);

	public abstract List<ProveedorProducto> getByProveedor(Proveedor paramProveedor);

	public abstract List<ProveedorProducto> getByProducto(Producto paramProducto);

	public abstract List<ProveedorProducto> getByProductoProveedor(Producto paramProducto, Proveedor paramProveedor);

	public abstract List<ProveedorProducto> getAll();
}
