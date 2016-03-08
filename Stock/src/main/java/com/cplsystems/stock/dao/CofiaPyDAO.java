package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.CofiaPy;
import java.util.List;

public abstract interface CofiaPyDAO {
	public abstract void save(CofiaPy paramCofiaPy);

	public abstract void delete(CofiaPy paramCofiaPy);

	public abstract CofiaPy getById(Long paramLong);

	public abstract List<CofiaPy> getAll();
}
