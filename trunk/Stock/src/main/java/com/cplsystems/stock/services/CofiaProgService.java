/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.CofiaProgDAO;
import com.cplsystems.stock.domain.CofiaProg;

/**
 * @author Carlos Palalía López
 */

@Service
public class CofiaProgService {

	@Autowired
	private CofiaProgDAO cofiaProgDAO;

	public void save(CofiaProg cofiaProg){
		cofiaProgDAO.save(cofiaProg);
	}

	public void delete(CofiaProg cofiaProg){
		cofiaProgDAO.delete(cofiaProg);
	}
	
	public CofiaProg getById(Long idCofiaProg){
		return cofiaProgDAO.getById(idCofiaProg);
	}

	public List<CofiaProg> getAll(){
		return cofiaProgDAO.getAll();
	}
}
