/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.GiroDAO;
import com.cplsystems.stock.domain.Giro;

/**
 * @author Carlos Palalía López
 */

@Service
public class GiroService {

	@Autowired
	private GiroDAO giroDAO;

	public void save(Giro giro){
		giroDAO.save(giro);
	}

	public void delete(Giro giro){
		giroDAO.delete(giro);
	}

	public Giro getById(Long idGiro){
		return giroDAO.getById(idGiro);
	}

	public List<Giro> getAll(){
		return giroDAO.getAll();
	}
	
	public Giro getByNombre(String nombre){
		return giroDAO.getByNombre(nombre);
	}
}
