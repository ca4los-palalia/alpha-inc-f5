package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import java.util.Calendar;
import java.util.List;

public abstract interface CotizacionDAO {
	public abstract void save(Cotizacion paramCotizacion);

	public abstract void delete(Cotizacion paramCotizacion);

	public abstract Cotizacion getById(Long paramLong);

	public abstract List<Cotizacion> getAll();

	public abstract List<Cotizacion> getByFechaEnvioCotizacion(Calendar paramCalendar);

	public abstract List<Cotizacion> getByFechaResolicion(Calendar paramCalendar);

	public abstract List<Cotizacion> getByStatus(Integer paramInteger);

	public abstract List<Cotizacion> getByProveedor(Proveedor paramProveedor);

	public abstract List<Cotizacion> getByRequisicion(Requisicion paramRequisicion);

	public abstract List<Cotizacion> getTopCompras();

	public abstract Long getCountRowsCotizacion();

	public abstract Cotizacion getCotizacionByFolio(String paramString);

	public abstract List<Cotizacion> getCotizacionesByEstatusRequisicionAndFolioOrProveedorByFolio(String paramString,
			List<Proveedor> paramList, List<EstatusRequisicion> paramList1);

	public abstract Cotizacion getCotizacionByRequisicionProveedorAndProducto(Requisicion paramRequisicion,
			Proveedor paramProveedor, Producto paramProducto);

	public abstract List<Cotizacion> getByProveedorFolioCotizacionNueva(Proveedor paramProveedor, String paramString,
			EstatusRequisicion paramEstatusRequisicion);
}
