/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cplsystems.stock.dao.ContactoDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Telefono;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ContactoDAOImpl implements ContactoDAO{

	public void save(Contacto contacto) {
		// TODO Auto-generated method stub
		
	}

	public void update(Contacto contacto) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Contacto contacto) {
		// TODO Auto-generated method stub
		
	}

	public Contacto getById(Long idContacto) {
		// TODO Auto-generated method stub
		return null;
	}

	public Contacto getByTelefono(Telefono telefono) {
		// TODO Auto-generated method stub
		return null;
	}

	public Contacto getByIdEmail(Email email) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contacto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
   
}
