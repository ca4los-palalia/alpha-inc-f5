package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Pais;
import java.util.List;

public abstract interface PaisDAO {
	public abstract void save(Pais paramPais);

	public abstract void delete(Pais paramPais);

	public abstract List<Pais> getAll();

	public abstract Pais findById(Long paramLong);
}
