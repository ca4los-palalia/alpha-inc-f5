package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.ProductoTipo;
import java.util.List;

public abstract interface ProductoTipoDAO {
	public abstract void saveOrUpdate(ProductoTipo paramProductoTipo);

	public abstract void save(ProductoTipo paramProductoTipo);

	public abstract void update(ProductoTipo paramProductoTipo);

	public abstract void delete(ProductoTipo paramProductoTipo);

	public abstract ProductoTipo getById(Long paramLong);

	public abstract List<ProductoTipo> getAll();

	public abstract ProductoTipo getByNombre(String paramString);
}
