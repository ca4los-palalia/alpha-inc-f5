/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.CofiaPartidaGenericaDAO;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;

/**
 * @author Carlos Palalía López
 */

@Service
public class CofiaPartidaGenericaService {

	@Autowired
	private CofiaPartidaGenericaDAO cofiaPartidaGenericaDAO;

	public void save(CofiaPartidaGenerica cofiaPartidaGenerica){
		cofiaPartidaGenericaDAO.save(cofiaPartidaGenerica);
	}

	public void delete(CofiaPartidaGenerica cofiaPartidaGenerica){
		cofiaPartidaGenericaDAO.delete(cofiaPartidaGenerica);
	}
	
	public CofiaPartidaGenerica getById(Long idCofiaPartidaGenerica){
		return cofiaPartidaGenericaDAO.getById(idCofiaPartidaGenerica);
	}

	public List<CofiaPartidaGenerica> getAll(){
		return cofiaPartidaGenericaDAO.getAll();
	}
}
