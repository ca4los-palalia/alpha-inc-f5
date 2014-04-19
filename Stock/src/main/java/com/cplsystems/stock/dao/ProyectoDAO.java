/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Proyecto;

/**
 * @author Carlos Palalía López
 */
public interface ProyectoDAO {

	public void save(Proyecto proyecto);

	public void delete(Proyecto proyecto);

	public Proyecto getById(Long idProyecto);

	public List<Proyecto> getAll();

	public Proyecto getById(String nombre);

}
