package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Proyecto;
import java.util.List;

public abstract interface ProyectoDAO {
	public abstract void save(Proyecto paramProyecto);

	public abstract void delete(Proyecto paramProyecto);

	public abstract Proyecto getById(Long paramLong);

	public abstract List<Proyecto> getAll();

	public abstract Proyecto getById(String paramString);
}
