package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Persona;
import java.util.List;

public abstract interface PersonaDAO {
	public abstract void save(Persona paramPersona);

	public abstract void delete(Persona paramPersona);

	public abstract List<Persona> getByDireccion(Direccion paramDireccion);

	public abstract List<Persona> getByContacto(Contacto paramContacto);

	public abstract Persona getById(Long paramLong);

	public abstract List<Persona> getAll();

	public abstract List<Persona> getBySexo(Long paramLong);

	public abstract Persona getUltimoRegistroPersona();
}
