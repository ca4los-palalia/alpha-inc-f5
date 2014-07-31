/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.OrganizacionDAO;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Service
public class OrganizacionService {

	@Autowired
	private OrganizacionDAO organizacionDAO;

	public void save(final Organizacion organizacion) {
		organizacionDAO.save(organizacion);
	}

	public void delete(final Organizacion organizacion) {
		organizacionDAO.delete(organizacion);
	}

	public List<Organizacion> getOrganizaciones() {
		return organizacionDAO.getOrganizaciones();
	}

	public List<Organizacion> getCompaniasByNombreRFC(String compania,
			String rfc) {
		return organizacionDAO.getCompaniasByNombreRFC(compania, rfc);
	}

	public List<Organizacion> getCompaniasByNombre(String compania) {
		return organizacionDAO.getCompaniasByNombre(compania);
	}

	public List<Organizacion> getCompaniasByRFC(String rfc) {
		return organizacionDAO.getCompaniasByRFC(rfc);
	}

	public List<Organizacion> getAll() {
		return organizacionDAO.getAll();
	}
	
	public Organizacion getById(Long idOrganizacion){
		return organizacionDAO.getById(idOrganizacion);
	}
}
