package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTope;
import java.util.List;

public abstract interface ProductoTopeDAO {
	public abstract void save(ProductoTope paramProductoTope);

	public abstract void delete(ProductoTope paramProductoTope);

	public abstract ProductoTope getById(Long paramLong);

	public abstract List<ProductoTope> getAll();

	public abstract List<ProductoTope> getByProducto(Producto paramProducto);

	public abstract List<ProductoTope> getByLugar(Lugar paramLugar);
}
