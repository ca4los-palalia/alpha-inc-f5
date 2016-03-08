package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.CostosProducto;
import com.cplsystems.stock.domain.Producto;
import java.util.List;

public abstract interface CostosProductoDAO {
	public abstract void save(CostosProducto paramCostosProducto);

	public abstract void delete(CostosProducto paramCostosProducto);

	public abstract CostosProducto getById(Long paramLong);

	public abstract List<CostosProducto> getAll();

	public abstract CostosProducto getByProducto(Producto paramProducto);
}
