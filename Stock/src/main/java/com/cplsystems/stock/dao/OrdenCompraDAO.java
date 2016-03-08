package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompra;
import java.util.Calendar;
import java.util.List;

public abstract interface OrdenCompraDAO {
	public abstract void save(OrdenCompra paramOrdenCompra);

	public abstract void delete(OrdenCompra paramOrdenCompra);

	public abstract OrdenCompra getById(Long paramLong);

	public abstract List<OrdenCompra> getAll();

	public abstract List<OrdenCompra> getByCotizacion(Cotizacion paramCotizacion);

	public abstract OrdenCompra getByCodigo(String paramString);

	public abstract List<OrdenCompra> getByFechaOrden(Calendar paramCalendar);

	public abstract String getCodigoDeOrden();

	public abstract List<OrdenCompra> getOrdenesByEstatusAndFolioOr(String paramString,
			List<EstatusRequisicion> paramList);
}
