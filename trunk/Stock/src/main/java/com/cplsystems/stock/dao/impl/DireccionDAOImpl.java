/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cplsystems.stock.dao.DireccionDAO;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;

/**
 * @author Carlos Palalía López
 */

@Repository
public class DireccionDAOImpl implements DireccionDAO{

	public void save(Direccion direccion) {
		// TODO Auto-generated method stub
		
	}

	public void update(Direccion direccion) {
		// TODO Auto-generated method stub
		
	}

	public Direccion getById(Long direccion) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Direccion> getByCodigoPostalId(String codigoPostal) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Direccion> getByEstado(Estado estado) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Direccion> getByMunicipio(Municipio municipio) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Direccion> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

   
}
