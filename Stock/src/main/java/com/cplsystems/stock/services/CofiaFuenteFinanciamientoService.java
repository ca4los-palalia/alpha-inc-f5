/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.CofiaFuenteFinanciamientoDAO;
import com.cplsystems.stock.domain.CofiaFuenteFinanciamiento;

/**
 * @author Carlos Palalía López
 */

@Service
public class CofiaFuenteFinanciamientoService {

	@Autowired
	private CofiaFuenteFinanciamientoDAO CofiaFuenteFinanciamientoDAO;

	public void save(CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento){
		CofiaFuenteFinanciamientoDAO.save(cofiaFuenteFinanciamiento);
	}

	public void delete(CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento){
		CofiaFuenteFinanciamientoDAO.delete(cofiaFuenteFinanciamiento);
	}
	
	public CofiaFuenteFinanciamiento getById(Long idCofiaFuenteFinanciamiento){
		return CofiaFuenteFinanciamientoDAO.getById(idCofiaFuenteFinanciamiento);
	}

	public List<CofiaFuenteFinanciamiento> getAll(){
		return CofiaFuenteFinanciamientoDAO.getAll();
	}
}
