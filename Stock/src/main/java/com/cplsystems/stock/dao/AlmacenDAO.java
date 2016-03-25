package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Almacen;
import com.cplsystems.stock.domain.Area;

public abstract interface AlmacenDAO {

	public void save(Almacen almacen);

	void delete(Almacen almacen);

	public Almacen getById(Long idAlmacen);

	public List<Almacen> getAll();

	public List<Almacen> getByArea(Area area);
	
}
