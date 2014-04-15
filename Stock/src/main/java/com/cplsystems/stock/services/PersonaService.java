/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.PersonaDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Persona;

@Service
public class PersonaService {

	@Autowired
	private PersonaDAO personaDAO;

	public void save(final Persona persona) {
		personaDAO.save(persona);
	}

	public void update(final Persona persona) {
		personaDAO.update(persona);
	}

	public Persona getById(final Long persona) {
		return personaDAO.getById(persona);
	}

	public List<Persona> getAll() {
		return personaDAO.getAll();
	}

	public List<Persona> getBySexo(final Long sexo) {
		return personaDAO.getBySexo(sexo);
	}

	public void delete(final Persona persona) {
		personaDAO.delete(persona);
	}

	public List<Persona> getByDireccion(final Direccion direccion) {
		return personaDAO.getByDireccion(direccion);
	}

	public List<Persona> getByContacto(final Contacto contacto) {
		return personaDAO.getByContacto(contacto);
	}

}
