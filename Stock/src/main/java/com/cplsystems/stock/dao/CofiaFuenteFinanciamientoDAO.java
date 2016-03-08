package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.CofiaFuenteFinanciamiento;
import java.util.List;

public abstract interface CofiaFuenteFinanciamientoDAO {
	public abstract void save(CofiaFuenteFinanciamiento paramCofiaFuenteFinanciamiento);

	public abstract void delete(CofiaFuenteFinanciamiento paramCofiaFuenteFinanciamiento);

	public abstract CofiaFuenteFinanciamiento getById(Long paramLong);

	public abstract List<CofiaFuenteFinanciamiento> getAll();
}
