/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.CofiaFuenteFinanciamiento;

/**
 * @author Carlos Palalía López
 */

public interface CofiaFuenteFinanciamientoDAO {

	public void save(CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento);

	public void delete(CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento);
	
	public CofiaFuenteFinanciamiento getById(Long idCofiaFuenteFinanciamiento);

	public List<CofiaFuenteFinanciamiento> getAll();
	
}
