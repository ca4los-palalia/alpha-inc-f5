package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.ModulosOrganizacionDAO;
import com.cplsystems.stock.domain.ModulosOrganizacion;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModulosOrganizacionService {
	@Autowired
	private ModulosOrganizacionDAO modulosOrganizacionDAO;

	public void save(ModulosOrganizacion modulosOrganizacion) {
		this.modulosOrganizacionDAO.save(modulosOrganizacion);
	}

	public void delete(ModulosOrganizacion modulosOrganizacion) {
		this.modulosOrganizacionDAO.delete(modulosOrganizacion);
	}

	public List<ModulosOrganizacion> getModulosByOrganizacion(Organizacion organizacion) {
		return this.modulosOrganizacionDAO.getModulosByOrganizacion(organizacion);
	}
}
