/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Banco;


/**
 * @author Carlos Palalía López
 */
public interface BancoDAO {

	public void saveOrUpdate(Banco banco);
	
	public void save(Banco banco);
	
	public void update(Banco banco);

	public void delete(Banco banco);

	public Banco getById(Long idBanco);

	public List<Banco> getAll();
}
