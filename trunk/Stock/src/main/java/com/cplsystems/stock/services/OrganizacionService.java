/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.OrganizacionDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

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

	public Organizacion getOrganizacionByUsuario(Usuarios usuario) {
		return organizacionDAO.getOrganizacionByUsuario(usuario);
	}
}
