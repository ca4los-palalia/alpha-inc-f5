package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.FamiliasProducto;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;
import java.util.List;

public abstract interface FamiliasProductoDAO {
	public abstract void save(FamiliasProducto paramFamiliasProducto);

	public abstract void update(FamiliasProducto paramFamiliasProducto);

	public abstract void delete(FamiliasProducto paramFamiliasProducto);

	public abstract FamiliasProducto getById(Long paramLong);

	public abstract List<FamiliasProducto> getAll();

	public abstract List<FamiliasProducto> getByProducto(Producto paramProducto);

	public abstract List<FamiliasProducto> getByFamilia(ProductoTipo paramProductoTipo);
}
