/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.EmailDAO;
import com.cplsystems.stock.dao.TelefonoDAO;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Telefono;

/**
 * @author Carlos Palalía López
 */

@Service
public class TelefonoService {

	@Autowired
	private TelefonoDAO telefonoDAO;

	public void save(Telefono telefono){
		telefonoDAO.save(telefono);
	}
	public void update(Telefono telefono){
		telefonoDAO.update(telefono);
	}
	public void delete(Telefono telefono){
		telefonoDAO.delete(telefono);
	}
	public Telefono getById(Long idTelefono){
		return telefonoDAO.getById(idTelefono);
	}
	public List<Telefono> getAll(){
		return telefonoDAO.getAll();
	}
	
}
