/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.ContratoDAO;
import com.cplsystems.stock.domain.Contrato;

/**
 * @author Carlos Palalía López
 */

@Service
public class ContratoService {

	@Autowired
	private ContratoDAO contratoDAO;

	public void save(Contrato contrato) throws DataAccessException{
		contratoDAO.save(contrato);
	}
	public void update(Contrato contrato) throws DataAccessException{
		contratoDAO.save(contrato);
	}
	public void delete(Contrato contrato) throws DataAccessException{
		contratoDAO.delete(contrato);
	}
	public Contrato getById(Long idContrato) throws DataAccessException{
		return contratoDAO.getById(idContrato);
	}
	public List<Contrato> getAll() throws DataAccessException{
		return contratoDAO.getAll();
	}
	
}
