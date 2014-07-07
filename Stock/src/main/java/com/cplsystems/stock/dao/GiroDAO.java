/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Giro;

/**
 * @author Carlos Palalía López
 */

public interface GiroDAO {

	public void save(Giro giro);

	public void delete(Giro giro);

	public Giro getById(Long idGiro);

	public List<Giro> getAll();
	
	public Giro getByNombre(String nombre);
	
}
