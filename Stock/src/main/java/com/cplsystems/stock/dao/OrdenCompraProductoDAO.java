package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraProducto;
import com.cplsystems.stock.domain.Producto;
import java.util.List;

public abstract interface OrdenCompraProductoDAO {
	public abstract void save(OrdenCompraProducto paramOrdenCompraProducto);

	public abstract void delete(OrdenCompraProducto paramOrdenCompraProducto);

	public abstract OrdenCompraProducto getById(Long paramLong);

	public abstract List<OrdenCompraProducto> getAll();

	public abstract List<OrdenCompraProducto> getByOrdenCopra(OrdenCompra paramOrdenCompra);

	public abstract List<OrdenCompraProducto> getByProducto(Producto paramProducto);
}
