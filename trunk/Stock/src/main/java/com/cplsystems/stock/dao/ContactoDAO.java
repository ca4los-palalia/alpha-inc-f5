
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Telefono;


/**
 * @author Carlos Palalía López
 */

public interface ContactoDAO {

	public void save(Contacto contacto );
	public void update(Contacto contacto );
	public void delete(Contacto contacto );
	public Contacto getById(Long idContacto);
	public Contacto getByTelefono(Telefono telefono);
	public Contacto getByIdEmail(Email email);
	
	public List<Contacto> getAll();
}
