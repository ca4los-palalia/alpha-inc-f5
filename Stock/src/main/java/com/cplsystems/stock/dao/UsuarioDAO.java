package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;
import java.util.List;

public abstract interface UsuarioDAO {
	public abstract Usuarios getUsuarioByCredentials(String paramString1, String paramString2);

	public abstract void save(Usuarios paramUsuarios);

	public abstract void delete(Usuarios paramUsuarios);

	public abstract List<Usuarios> getUsuariosByOrganizacion(Organizacion paramOrganizacion);

	public abstract boolean verificarNombreUsuario(String paramString, Long paramLong);

	public abstract Usuarios getClienteByOrganizacion(Organizacion paramOrganizacion);

	public abstract Usuarios getOwner(Organizacion paramOrganizacion);

	public abstract List<Usuarios> getUsuariosByOrganizacionAll(Organizacion paramOrganizacion);

	public abstract List<Usuarios> getAll();
}
