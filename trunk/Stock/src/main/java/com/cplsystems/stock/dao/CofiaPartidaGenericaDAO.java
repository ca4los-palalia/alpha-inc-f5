/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.CofiaPartidaGenerica;

/**
 * @author Carlos Palalía López
 */

public interface CofiaPartidaGenericaDAO {

	public void save(CofiaPartidaGenerica cofiaPartidaGenerica);

	public void delete(CofiaPartidaGenerica cofiaPartidaGenerica);
	
	public CofiaPartidaGenerica getById(Long idCofiaPartidaGenerica);

	public List<CofiaPartidaGenerica> getAll();
	
}
