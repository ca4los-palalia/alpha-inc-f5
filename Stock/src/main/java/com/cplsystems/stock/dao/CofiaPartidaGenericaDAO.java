package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import java.util.List;

public abstract interface CofiaPartidaGenericaDAO {
	public abstract void save(CofiaPartidaGenerica paramCofiaPartidaGenerica);

	public abstract void delete(CofiaPartidaGenerica paramCofiaPartidaGenerica);

	public abstract CofiaPartidaGenerica getById(Long paramLong);

	public abstract List<CofiaPartidaGenerica> getAll();

	public abstract CofiaPartidaGenerica getByNombre(String paramString);
}
