package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.RequisicionPartidaDAO;
import com.cplsystems.stock.domain.Partida;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionPartida;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RequisicionPartidaService {
	@Autowired
	private RequisicionPartidaDAO requisicionPartidaDAO;

	public void save(RequisicionPartida requisicionPartida) throws DataAccessException {
		this.requisicionPartidaDAO.save(requisicionPartida);
	}

	public void delete(RequisicionPartida requisicionPartida) throws DataAccessException {
		this.requisicionPartidaDAO.delete(requisicionPartida);
	}

	public RequisicionPartida getById(Long idRequisicionPartida) throws DataAccessException {
		return this.requisicionPartidaDAO.getById(idRequisicionPartida);
	}

	public List<RequisicionPartida> getByPartida(Partida partida) throws DataAccessException {
		return this.requisicionPartidaDAO.getByPartida(partida);
	}

	public List<RequisicionPartida> getByRequisicion(Requisicion requisicion) throws DataAccessException {
		return this.requisicionPartidaDAO.getByRequisicion(requisicion);
	}

	public List<RequisicionPartida> getAll() throws DataAccessException {
		return this.requisicionPartidaDAO.getAll();
	}
}
