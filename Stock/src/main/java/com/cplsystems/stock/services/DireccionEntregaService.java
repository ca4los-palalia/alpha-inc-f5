/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.DireccionEntregaDAO;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.DireccionEntrega;

/**
 * @author Carlos Palalía López
 */

@Service
public class DireccionEntregaService {

	@Autowired
	private DireccionEntregaDAO direccionEntregaDAO;
		
	public void save(DireccionEntrega direccion){
		direccionEntregaDAO.save(direccion);
	}
    public void update(DireccionEntrega direccion){
    	direccionEntregaDAO.update(direccion);
    }
    public void delete(DireccionEntrega direccion){
    	direccionEntregaDAO.delete(direccion);
    }
    public DireccionEntrega getById(Long direccion){
    	return direccionEntregaDAO.getById(direccion);
    }
    public List<DireccionEntrega> getByDireccion(Direccion  direccion){
    	return direccionEntregaDAO.getByDireccion(direccion);
    }
    public List<DireccionEntrega> getAll(){
    	return direccionEntregaDAO.getAll();
    }
	
}
