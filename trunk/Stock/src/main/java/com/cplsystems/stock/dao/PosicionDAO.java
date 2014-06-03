/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Posicion;


/**
 * @author Carlos Palalía López
 */
public interface PosicionDAO {

	public void saveOrUpdate(Posicion posicion);

	public void update(Posicion posicion);
	
	public void save(Posicion posicion);
	
	public void delete(Posicion posicion);

	public Posicion getById(Long idPosicion);

	public List<Posicion> getAll();
}
