/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Moneda;


/**
 * @author Carlos Palalía López
 */
public interface MonedaDAO {
	
	public void save(Moneda moneda);

	public void delete(Moneda moneda);

	public Moneda getById(Long idMoneda);

	public List<Moneda> getAll();
}
