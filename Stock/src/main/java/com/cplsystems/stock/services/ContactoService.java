package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.ContactoDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Telefono;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ContactoService {
	@Autowired
	private ContactoDAO contactoDAO;

	public void save(Contacto contacto) throws DataAccessException {
		this.contactoDAO.save(contacto);
	}

	public void delete(Contacto contacto) throws DataAccessException {
		this.contactoDAO.delete(contacto);
	}

	public Contacto getById(Long idContacto) throws DataAccessException {
		return this.contactoDAO.getById(idContacto);
	}

	public Contacto getByTelefono(Telefono telefono) throws DataAccessException {
		return this.contactoDAO.getByTelefono(telefono);
	}

	public Contacto getByIdEmail(Email email) throws DataAccessException {
		return this.contactoDAO.getByIdEmail(email);
	}

	public List<Contacto> getAll() throws DataAccessException {
		return this.contactoDAO.getAll();
	}

	public Contacto getUltimoRegistroContacto() {
		return this.contactoDAO.getUltimoRegistroContacto();
	}

	public Contacto getContactoByEmail(Email email) {
		return this.contactoDAO.getContactoByEmail(email);
	}
}
