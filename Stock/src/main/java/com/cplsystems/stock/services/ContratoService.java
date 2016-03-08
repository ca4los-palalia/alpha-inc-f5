package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.ContratoDAO;
import com.cplsystems.stock.domain.Contrato;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ContratoService {
	@Autowired
	private ContratoDAO contratoDAO;

	public void save(Contrato contrato) throws DataAccessException {
		this.contratoDAO.save(contrato);
	}

	public void delete(Contrato contrato) throws DataAccessException {
		this.contratoDAO.delete(contrato);
	}

	public Contrato getById(Long idContrato) throws DataAccessException {
		return this.contratoDAO.getById(idContrato);
	}

	public List<Contrato> getAll() throws DataAccessException {
		return this.contratoDAO.getAll();
	}
}
