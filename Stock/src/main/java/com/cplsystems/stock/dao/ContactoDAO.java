package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Telefono;
import java.util.List;

public abstract interface ContactoDAO {
	public abstract void save(Contacto paramContacto);

	public abstract void delete(Contacto paramContacto);

	public abstract Contacto getById(Long paramLong);

	public abstract Contacto getByTelefono(Telefono paramTelefono);

	public abstract Contacto getByIdEmail(Email paramEmail);

	public abstract List<Contacto> getAll();

	public abstract Contacto getUltimoRegistroContacto();

	public abstract Contacto getContactoByEmail(Email paramEmail);
}
