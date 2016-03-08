package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Destino;
import java.util.List;

public abstract interface DestinoDAO {
	public abstract void save(Destino paramDestino);

	public abstract Destino getById(Long paramLong);

	public abstract Destino getByNombre(String paramString);

	public abstract List<Destino> getAll();
}
