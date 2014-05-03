/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;

/**
 * @author Carlos Palalía López
 */

public interface DireccionDAO {

	public void save(Direccion direccion);

	public Direccion getById(Long direccion);
	
	public Direccion getUltimoRegistroDireccion();

	public List<Direccion> getByCodigoPostalId(String codigoPostal);

	public List<Direccion> getByEstado(Estado estado);

	public List<Direccion> getByMunicipio(Municipio municipio);

	public List<Direccion> getAll();
}
