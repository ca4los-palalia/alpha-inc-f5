package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.OrganizacionDAO;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizacionService {
	@Autowired
	private OrganizacionDAO organizacionDAO;

	public void save(Organizacion organizacion) {
		this.organizacionDAO.save(organizacion);
	}

	public void delete(Organizacion organizacion) {
		this.organizacionDAO.delete(organizacion);
	}

	public List<Organizacion> getOrganizaciones() {
		return this.organizacionDAO.getOrganizaciones();
	}

	public List<Organizacion> getCompaniasByNombreRFC(String compania, String rfc) {
		return this.organizacionDAO.getCompaniasByNombreRFC(compania, rfc);
	}

	public List<Organizacion> getCompaniasByNombre(String compania) {
		return this.organizacionDAO.getCompaniasByNombre(compania);
	}

	public List<Organizacion> getCompaniasByRFC(String rfc) {
		return this.organizacionDAO.getCompaniasByRFC(rfc);
	}

	public List<Organizacion> getAll() {
		return this.organizacionDAO.getAll();
	}

	public Organizacion getById(Long idOrganizacion) {
		return this.organizacionDAO.getById(idOrganizacion);
	}
}
