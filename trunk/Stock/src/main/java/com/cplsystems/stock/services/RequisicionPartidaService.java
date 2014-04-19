/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.RequisicionPartidaDAO;
import com.cplsystems.stock.domain.Partida;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionPartida;

/**
 * @author Carlos Palalía López
 */

@Service
public class RequisicionPartidaService {

	@Autowired
	private RequisicionPartidaDAO requisicionPartidaDAO;

	public void save(RequisicionPartida requisicionPartida)throws DataAccessException {
		requisicionPartidaDAO.save(requisicionPartida);
	}

	public void delete(RequisicionPartida requisicionPartida)throws DataAccessException {
		requisicionPartidaDAO.delete(requisicionPartida);
	}

	public RequisicionPartida getById(Long idRequisicionPartida)throws DataAccessException {
		return requisicionPartidaDAO.getById(idRequisicionPartida);
	}

	public List<RequisicionPartida> getByPartida(Partida partida) throws DataAccessException{
		return requisicionPartidaDAO.getByPartida(partida);
	}

	public List<RequisicionPartida> getByRequisicion(Requisicion requisicion)throws DataAccessException {
		return requisicionPartidaDAO.getByRequisicion(requisicion);
	}

	public List<RequisicionPartida> getAll()throws DataAccessException {
		return requisicionPartidaDAO.getAll();
	}

}
