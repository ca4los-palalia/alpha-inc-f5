package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.BancoDAO;
import com.cplsystems.stock.domain.Banco;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BancoService {
	@Autowired
	private BancoDAO bancoDAO;

	public void save(Banco banco) throws DataAccessException {
		this.bancoDAO.save(banco);
	}

	public void delete(Banco banco) throws DataAccessException {
		this.bancoDAO.delete(banco);
	}

	public Banco getById(Long idBanco) throws DataAccessException {
		return this.bancoDAO.getById(idBanco);
	}

	public List<Banco> getAll() throws DataAccessException {
		return this.bancoDAO.getAll();
	}
}
