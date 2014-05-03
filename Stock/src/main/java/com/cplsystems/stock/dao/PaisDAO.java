/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Pais;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 *
 */
public interface PaisDAO {

	public void save(Pais pais);
	public void delete(Pais pais);
	public List<Pais> getAll();
	public Pais findById(Long idPais);
}
