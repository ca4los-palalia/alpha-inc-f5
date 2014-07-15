/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.UnidadDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author Carlos Palalía López
 */

@Service
public class  UnidadService{

	@Autowired
	private UnidadDAO unidadDAO;

	public void save(final Unidad unidad)throws DataAccessException{
		unidadDAO.save(unidad);
	}
	public void update(final Unidad unidad)throws DataAccessException{
		unidadDAO.update(unidad);
	}
	public void delete(final Unidad unidad)throws DataAccessException{
		unidadDAO.delete(unidad);
	}
	public Unidad getById(final Long idUnidad)throws DataAccessException{
		return unidadDAO.getById(idUnidad);
	}
	public List<Unidad> getAll()throws DataAccessException{
		return unidadDAO.getAll();
	}
	public Unidad getByNombre(final String nombre)throws DataAccessException{
		return unidadDAO.getByNombre(nombre);
	}
	
	public List<Unidad> getByOrganizacion(Organizacion organizacion){
		return unidadDAO.getByOrganizacion(organizacion);
	}
	
	public List<Unidad> getByUsuario(Usuarios usuarios){
		return unidadDAO.getByUsuario(usuarios);
	}

}
