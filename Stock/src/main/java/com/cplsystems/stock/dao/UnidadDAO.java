/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Unidad;

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

}
