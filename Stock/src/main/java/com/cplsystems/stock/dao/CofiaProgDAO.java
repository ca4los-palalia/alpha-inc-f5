package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.CofiaProg;
import java.util.List;

public abstract interface CofiaProgDAO {
	public abstract void save(CofiaProg paramCofiaProg);

	public abstract void delete(CofiaProg paramCofiaProg);

	public abstract CofiaProg getById(Long paramLong);

	public abstract List<CofiaProg> getAll();
}
