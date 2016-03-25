package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Estado;
import java.util.List;

public abstract interface EstadoDAO {
	public abstract void save(Estado paramEstado);

	public abstract void delete(Estado paramEstado);

	public abstract Estado getById(Long paramLong);

	public abstract List<Estado> getAll();

	public abstract Estado getByName(String name);
}
