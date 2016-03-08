package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Area;
import java.util.List;

public abstract interface AreaDAO {
	public abstract void save(Area paramArea);

	public abstract void update(Area paramArea);

	public abstract void delete(Area paramArea);

	public abstract Area getById(Long paramLong);

	public abstract List<Area> getAll();

	public abstract Area getByNombre(String paramString);
}
