package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Posicion;
import java.util.List;

public abstract interface PosicionDAO {
	public abstract void saveOrUpdate(Posicion paramPosicion);

	public abstract void update(Posicion paramPosicion);

	public abstract void save(Posicion paramPosicion);

	public abstract void delete(Posicion paramPosicion);

	public abstract Posicion getById(Long paramLong);

	public abstract List<Posicion> getAll();
}
