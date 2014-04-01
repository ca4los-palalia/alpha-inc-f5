/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Municipio;

/**
 * @author Carlos Palalía López
 */
public interface MunicipioDAO {

	public void save(Municipio estado);
	public void update(Municipio estado);
	public void delete(Municipio estado);
	public Municipio getById(Long idMunicipio);
	public List<Municipio> getAll();
}
