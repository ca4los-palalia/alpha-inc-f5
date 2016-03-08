package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Proyecto;
import java.util.List;

public abstract interface LugarDAO {
	public abstract void save(Lugar paramLugar);

	public abstract void delete(Lugar paramLugar);

	public abstract Lugar getById(Long paramLong);

	public abstract Lugar getByIdProyecto(Proyecto paramProyecto);

	public abstract Lugar getByNombre(String paramString);

	public abstract List<Lugar> getAll();
}
