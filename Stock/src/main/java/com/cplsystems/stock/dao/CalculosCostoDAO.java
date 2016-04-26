package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.CalculosCosto;

public abstract interface CalculosCostoDAO {
	
	public abstract void save(CalculosCosto calculosCosto);

	public abstract void delete(CalculosCosto calculosCosto);

	public abstract CalculosCosto getById(Long idCalculosCosto);

	public abstract List<CalculosCosto> getAll();
}
