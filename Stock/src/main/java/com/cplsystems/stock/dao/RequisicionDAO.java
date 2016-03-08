package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Requisicion;
import java.util.List;

public abstract interface RequisicionDAO {
	public abstract void save(Requisicion paramRequisicion);

	public abstract void update(Requisicion paramRequisicion);

	public abstract void delete(Requisicion paramRequisicion);

	public abstract Requisicion getById(Long paramLong);

	public abstract Requisicion getByPersona(Persona paramPersona);

	public abstract String getUltimoFolio();

	public abstract List<Requisicion> getAll();

	public abstract List<Requisicion> getByEstatusRequisicion(EstatusRequisicion paramEstatusRequisicion);

	public abstract Requisicion getByFolio(String paramString);

	public abstract List<Requisicion> getByUnidadResponsable(Area paramArea);

	public abstract List<Requisicion> getRequisicionesConListaDeEstatusFolioArea(List<EstatusRequisicion> paramList,
			String paramString, Area paramArea);
}
