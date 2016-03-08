package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.CotizacionProducto;
import java.util.List;

public abstract interface CotizacionProductoDAO {
	public abstract void save(CotizacionProducto paramCotizacionProducto);

	public abstract void delete(CotizacionProducto paramCotizacionProducto);

	public abstract CotizacionProducto getById(Long paramLong);

	public abstract List<CotizacionProducto> getAll();
}
