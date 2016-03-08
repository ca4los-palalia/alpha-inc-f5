package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.ModulosOrganizacion;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;

public abstract interface ModulosOrganizacionDAO {
	public abstract void delete(ModulosOrganizacion paramModulosOrganizacion);

	public abstract List<ModulosOrganizacion> getModulosByOrganizacion(Organizacion paramOrganizacion);

	public abstract void save(ModulosOrganizacion paramModulosOrganizacion);
}
