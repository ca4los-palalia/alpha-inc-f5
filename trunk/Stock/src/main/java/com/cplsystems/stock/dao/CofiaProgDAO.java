/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.CofiaProg;

/**
 * @author Carlos Palalía López
 */

public interface CofiaProgDAO {

	public void save(CofiaProg cofiaProg);

	public void delete(CofiaProg cofiaProg);
	
	public CofiaProg getById(Long idCofiaProg);

	public List<CofiaProg> getAll();
	
}
