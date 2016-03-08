package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;
import java.util.List;

public abstract interface MunicipioDAO {
	public abstract void save(Municipio paramMunicipio);

	public abstract void delete(Municipio paramMunicipio);

	public abstract Municipio getById(Long paramLong);

	public abstract List<Municipio> getAll();

	public abstract List<Municipio> getByEstado(Estado paramEstado);
}
