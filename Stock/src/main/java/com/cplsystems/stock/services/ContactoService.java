/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.ContactoDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Telefono;

/**
 * @author Carlos Palalía López
 */

@Service
public class ContactoService {

	@Autowired
	private ContactoDAO contactoDAO;

	public void save(Contacto contacto){
		contactoDAO.save(contacto);
	}
	public void update(Contacto contacto){
		contactoDAO.update(contacto);
	}
	public void delete(Contacto contacto){
		contactoDAO.delete(contacto);
	}
	public Contacto getById(Long idContacto){
		return contactoDAO.getById(idContacto);
	}
	public Contacto getByTelefono(Telefono telefono){
		return contactoDAO.getByTelefono(telefono);
	}
	public Contacto getByIdEmail(Email email){
		return contactoDAO.getByIdEmail(email);
	}
	public List<Contacto> getAll(){
		return contactoDAO.getAll();
	}
	
}
