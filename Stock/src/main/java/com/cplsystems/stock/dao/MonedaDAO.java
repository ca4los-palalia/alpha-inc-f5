package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Moneda;
import java.util.List;

public abstract interface MonedaDAO {
	public abstract void save(Moneda paramMoneda);

	public abstract void delete(Moneda paramMoneda);

	public abstract Moneda getById(Long paramLong);

	public abstract List<Moneda> getAll();
}
