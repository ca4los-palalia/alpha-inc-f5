/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Proyecto;

/**
 * @author Carlos Palalía López
 */

public interface LugarDAO {

	public void save(Lugar lugar);

	public void delete(Lugar lugar);

	public Lugar getById(Long idLugar);

	public Lugar getByIdProyecto(Proyecto proyecto);

	public Lugar getByNombre(String nombre);

	public List<Lugar> getAll();
}
