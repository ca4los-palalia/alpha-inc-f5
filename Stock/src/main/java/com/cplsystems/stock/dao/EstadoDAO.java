/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Estado;

/**
 * @author Carlos Palalía López
 */
public interface EstadoDAO {
	public void save(Estado estado);

	public void delete(Estado estado);

	public Estado getById(Long idEstado);

	public List<Estado> getAll();
}
