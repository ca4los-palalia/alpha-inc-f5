package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Telefono;
import java.util.List;

public abstract interface TelefonoDAO {
	public abstract void save(Telefono paramTelefono);

	public abstract void delete(Telefono paramTelefono);

	public abstract Telefono getById(Long paramLong);

	public abstract List<Telefono> getAll();

	public abstract Telefono getUltimoregistroEmail();
}
