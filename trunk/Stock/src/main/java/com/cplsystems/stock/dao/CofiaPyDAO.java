/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.CofiaPy;

/**
 * @author Carlos Palalía López
 */

public interface CofiaPyDAO {

	public void save(CofiaPy cofiaPy);

	public void delete(CofiaPy cofiaPy);
	
	public CofiaPy getById(Long idCofiaPy);

	public List<CofiaPy> getAll();
	
}
