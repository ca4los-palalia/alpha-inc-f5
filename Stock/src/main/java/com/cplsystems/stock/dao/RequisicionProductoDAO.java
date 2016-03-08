package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;
import java.util.List;

public abstract interface RequisicionProductoDAO {
	public abstract void save(RequisicionProducto paramRequisicionProducto);

	public abstract void delete(RequisicionProducto paramRequisicionProducto);

	public abstract RequisicionProducto getById(Long paramLong);

	public abstract List<RequisicionProducto> getByProducto(Producto paramProducto);

	public abstract List<RequisicionProducto> getByRequisicion(Requisicion paramRequisicion);

	public abstract List<RequisicionProducto> getRequisicionesConEstadoEspecifico(
			EstatusRequisicion paramEstatusRequisicion);

	public abstract List<RequisicionProducto> getByProveedor(Proveedor paramProveedor);

	public abstract List<RequisicionProducto> getByLugar(Lugar paramLugar);

	public abstract List<RequisicionProducto> getAll();

	public abstract List<RequisicionProducto> getAllRequisiciones();

	public abstract List<Proveedor> getAllDistinctByProveedor();

	public abstract List<RequisicionProducto> getByConfiaPartidaGenerica(
			CofiaPartidaGenerica paramCofiaPartidaGenerica);

	public abstract List<RequisicionProducto> getByCotizacion(Cotizacion paramCotizacion);
}
