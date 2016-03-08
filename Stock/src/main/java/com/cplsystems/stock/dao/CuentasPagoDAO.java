package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.CuentaPago;
import com.cplsystems.stock.domain.Proveedor;
import java.util.List;

public abstract interface CuentasPagoDAO {
	public abstract void save(CuentaPago paramCuentaPago);

	public abstract void delete(CuentaPago paramCuentaPago);

	public abstract CuentaPago getById(Long paramLong);

	public abstract List<CuentaPago> getAll();

	public abstract List<CuentaPago> getByProveedor(Proveedor paramProveedor);
}
