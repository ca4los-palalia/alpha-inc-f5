package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;
import java.util.List;

public abstract interface DireccionDAO {
	public abstract void save(Direccion paramDireccion);

	public abstract Direccion getById(Long paramLong);

	public abstract Direccion getUltimoRegistroDireccion();

	public abstract List<Direccion> getByCodigoPostalId(String paramString);

	public abstract List<Direccion> getByEstado(Estado paramEstado);

	public abstract List<Direccion> getByMunicipio(Municipio paramMunicipio);

	public abstract List<Direccion> getAll();
}
