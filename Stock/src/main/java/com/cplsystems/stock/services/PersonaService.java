package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.PersonaDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {
	@Autowired
	private PersonaDAO personaDAO;

	public void save(Persona persona) throws DataAccessException {
		this.personaDAO.save(persona);
	}

	public Persona getById(Long persona) throws DataAccessException {
		return this.personaDAO.getById(persona);
	}

	public List<Persona> getAll() throws DataAccessException {
		return this.personaDAO.getAll();
	}

	public List<Persona> getBySexo(Long sexo) throws DataAccessException {
		return this.personaDAO.getBySexo(sexo);
	}

	public void delete(Persona persona) throws DataAccessException {
		this.personaDAO.delete(persona);
	}

	public List<Persona> getByDireccion(Direccion direccion) throws DataAccessException {
		return this.personaDAO.getByDireccion(direccion);
	}

	public List<Persona> getByContacto(Contacto contacto) throws DataAccessException {
		return this.personaDAO.getByContacto(contacto);
	}

	public Persona getUltimoRegistroPersona() throws DataAccessException {
		return this.personaDAO.getUltimoRegistroPersona();
	}
}
