/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.MunicipioDAO;
import com.cplsystems.stock.domain.Municipio;


/**
 * @author Carlos Palalía López
 */

@Service
public class MunicipioService {

	@Autowired
	private MunicipioDAO municipioDAO;
	
	public void save(Municipio estado){
		municipioDAO.save(estado);
	}
	public void update(Municipio estado){
		municipioDAO.update(estado);
	}
	public void delete(Municipio estado){
		municipioDAO.delete(estado);
	}
	public Municipio getById(Long idMunicipio){
		return municipioDAO.getById(idMunicipio);
	}
	public List<Municipio> getAll(){
		return municipioDAO.getAll();
	}
	
}
