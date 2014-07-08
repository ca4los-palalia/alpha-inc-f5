/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Modulos;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public interface ModulosDAO {

	void save(Modulos modulos);

	void delete(Modulos modulos);

	List<Modulos> getAll();

}
