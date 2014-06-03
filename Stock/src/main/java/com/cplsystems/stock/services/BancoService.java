/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.BancoDAO;
import com.cplsystems.stock.domain.Banco;

/**
 * @author Carlos Palalía López
 */

@Service
public class BancoService {

	@Autowired
	private BancoDAO bancoDAO;

	public void saveOrUpdate(Banco banco) throws DataAccessException {
		bancoDAO.saveOrUpdate(banco);
	}
	
	public void save(Banco banco) throws DataAccessException {
		bancoDAO.save(banco);
	}
	
	public void update(Banco banco) throws DataAccessException {
		bancoDAO.update(banco);
	}
	

	public void delete(Banco banco) throws DataAccessException {
		bancoDAO.delete(banco);
	}

	public Banco getById(Long idBanco) throws DataAccessException {
		return bancoDAO.getById(idBanco);
	}

	public List<Banco> getAll() throws DataAccessException {
		return bancoDAO.getAll();
	}

}
