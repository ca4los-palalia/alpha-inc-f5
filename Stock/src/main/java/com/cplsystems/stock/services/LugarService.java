/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.LugarDAO;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Proyecto;

/**
 * @author Carlos Palalía López
 */

@Service
public class LugarService {

	@Autowired
	private LugarDAO lugarDAO;

	public void save(Lugar lugar){
		lugarDAO.save(lugar);
	}
	public void update(Lugar lugar){
		lugarDAO.update(lugar);
	}
	public void delete(Lugar lugar){
		lugarDAO.delete(lugar);
	}
	public Lugar getById(Long idLugar){
		return lugarDAO.getById(idLugar);
	}
	public Lugar getByIdProyecto(Proyecto proyecto){
		return lugarDAO.getByIdProyecto(proyecto);
	}
	public Lugar getByNombre(String nombre){
		return lugarDAO.getByNombre(nombre);
	}
	public List<Lugar> getAll(){
		return lugarDAO.getAll();	
	}
	
}
