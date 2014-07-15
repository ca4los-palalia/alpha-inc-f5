/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author Carlos Palalía López
 */

public interface UnidadDAO {

	
	public void save(final Unidad unidad);
	public void update(final Unidad unidad);
	public void delete(final Unidad unidad);
	public Unidad getById(final Long idUnidad);
	public List<Unidad> getAll();
	public Unidad getByNombre(final String nombre);
	public List<Unidad> getByOrganizacion(Organizacion organizacion);
	public List<Unidad> getByUsuario(Usuarios usuarios);

}
