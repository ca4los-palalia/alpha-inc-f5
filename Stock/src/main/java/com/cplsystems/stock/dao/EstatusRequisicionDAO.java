package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.EstatusRequisicion;
import java.util.List;

public abstract interface EstatusRequisicionDAO {
	public abstract void save(EstatusRequisicion paramEstatusRequisicion);

	public abstract void delete(EstatusRequisicion paramEstatusRequisicion);

	public abstract EstatusRequisicion getById(Long paramLong);

	public abstract List<EstatusRequisicion> getAll();

	public abstract EstatusRequisicion getByNombre(String paramString);

	public abstract EstatusRequisicion getByClave(String paramString);

	public abstract EstatusRequisicion getByEstado(boolean paramBoolean);
}
