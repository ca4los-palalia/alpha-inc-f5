/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.CofiaPyDAO;
import com.cplsystems.stock.domain.CofiaPy;

/**
 * @author Carlos Palalía López
 */

@Service
public class CofiaPyService {

	@Autowired
	private CofiaPyDAO cofiaPyDAO;

	public void save(CofiaPy cofiaPy){
		cofiaPyDAO.save(cofiaPy);
	}

	public void delete(CofiaPy cofiaPy){
		cofiaPyDAO.delete(cofiaPy);
	}
	
	public CofiaPy getById(Long idCofiaPy){
		return cofiaPyDAO.getById(idCofiaPy);
	}

	public List<CofiaPy> getAll(){
		return cofiaPyDAO.getAll();
	}
}
