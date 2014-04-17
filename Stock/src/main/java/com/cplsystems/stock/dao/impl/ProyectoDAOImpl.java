/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ProyectoDAO;
import com.cplsystems.stock.domain.Proyecto;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ProyectoDAOImpl extends HibernateDAOSuportUtil implements ProyectoDAO{

	public void save(Proyecto proyecto) {
		
	}

	public void update(Proyecto proyecto) {
		
	}

	public void delete(Proyecto proyecto) {
		
	}

	public Proyecto getById(Long idProyecto) {
		return null;
	}

	public List<Proyecto> getAll() {
		return null;
	}

	public Proyecto getById(String nombre) {
		return null;
	}


   
}
