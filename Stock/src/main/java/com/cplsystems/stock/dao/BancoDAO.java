package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Banco;
import java.util.List;

public abstract interface BancoDAO {
	public abstract void save(Banco paramBanco);

	public abstract void delete(Banco paramBanco);

	public abstract Banco getById(Long paramLong);

	public abstract List<Banco> getAll();
}
