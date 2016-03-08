package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;
import java.util.List;

public abstract interface UnidadDAO {
	public abstract void save(Unidad paramUnidad);

	public abstract void update(Unidad paramUnidad);

	public abstract void delete(Unidad paramUnidad);

	public abstract Unidad getById(Long paramLong);

	public abstract List<Unidad> getAll();

	public abstract Unidad getByNombre(String paramString);

	public abstract List<Unidad> getByOrganizacion(Organizacion paramOrganizacion);

	public abstract List<Unidad> getByUsuario(Usuarios paramUsuarios);
}
