package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Producto;
import java.util.List;

public abstract interface ProductoDAO {
	public abstract void save(Producto paramProducto);

	public abstract void delete(Producto paramProducto);

	public abstract Producto getById(Long paramLong);

	public abstract List<Producto> getAll();

	public abstract List<Producto> getItemByKeyOrName(String paramString1, String paramString2);

	public abstract List<String> getAllKeys();

	public abstract List<Producto> getByClaveNombre(String paramString);

	public abstract Producto getByClaveNombrePrecioCosto(String paramString);

	public abstract List<Producto> getPreciosMaximos();

	public abstract List<Producto> getPreciosMinimos();

	public abstract List<Producto> getPreciosPromedio();

	public abstract List<Producto> getByPrecio(String paramString);

	public abstract Producto getByClave(String paramString);

	public abstract List<Producto> getAllLimited();

	public abstract List<Producto> getAllNativeSQL();
}
