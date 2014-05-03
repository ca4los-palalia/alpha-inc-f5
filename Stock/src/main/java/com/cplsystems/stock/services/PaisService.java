/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.PaisDAO;
import com.cplsystems.stock.domain.Pais;


/**
 * @author Carlos Palalía López
 */

@Service
public class PaisService {

	@Autowired
	private PaisDAO paisDAO;
	
	
	public void save(Pais pais){
		paisDAO.save(pais);
	}
	public void delete(Pais pais){
		paisDAO.delete(pais);
	}
	public List<Pais> getAll(){
		return paisDAO.getAll();
	}
	public Pais findById(Long idPais){
		return paisDAO.findById(idPais);
	}
	
}
