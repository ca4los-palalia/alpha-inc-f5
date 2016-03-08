package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Giro;
import java.util.List;

public abstract interface GiroDAO {
	public abstract void save(Giro paramGiro);

	public abstract void delete(Giro paramGiro);

	public abstract Giro getById(Long paramLong);

	public abstract List<Giro> getAll();

	public abstract Giro getByNombre(String paramString);
}
