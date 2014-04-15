/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.dao.PersonaDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Persona;

/**
 * @author Carlos Palalía López
 */

@Repository
public class PersonaDAOImpl extends HibernateDaoSupport implements PersonaDAO {

	public void save(Persona persona) {

	}

	public void update(Persona persona) {

	}

	public Persona getById(Long persona) {
		return null;
	}

	public List<Persona> getAll() {
		return null;
	}

	public List<Persona> getBySexo(Long sexo) {
		return null;
	}

	public void delete(Persona persona) {
		// TODO Auto-generated method stub

	}

	public List<Persona> getByDireccion(Direccion direccion) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Persona> getByContacto(Contacto contacto) {
		// TODO Auto-generated method stub
		return null;
	}

}
