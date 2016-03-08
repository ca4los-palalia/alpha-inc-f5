package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProveedor;
import java.util.List;

public abstract interface RequisicionProveedorDAO {
	public abstract void save(RequisicionProveedor paramRequisicionProveedor);

	public abstract void delete(RequisicionProveedor paramRequisicionProveedor);

	public abstract RequisicionProveedor getById(Long paramLong);

	public abstract List<RequisicionProveedor> getByRequisicion(Requisicion paramRequisicion);

	public abstract List<RequisicionProveedor> getByProveedor(Proveedor paramProveedor);

	public abstract List<RequisicionProveedor> getAll();
}
