/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.DestinoDAO;
import com.cplsystems.stock.domain.Destino;


/**
 * @author Carlos Palalía López
 */

@Service
public class DestinoService {

	@Autowired
	private DestinoDAO destinoDAO;

	public void save(Destino destino){
		destinoDAO.save(destino);
	}
    public void update(Destino destino){
    	destinoDAO.update(destino);
    }
    public Destino getById(Long idDestino){
    	return destinoDAO.getById(idDestino);
    }
    public Destino getByNombre(String lugar){
    	return destinoDAO.getByNombre(lugar);
    }
    public List<Destino> getAll(){
    	return destinoDAO.getAll();
    }
	
}
