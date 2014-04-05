/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public void save(RequisicionPartida requisicionPartida){
		requisicionPartidaDAO.save(requisicionPartida);
	}
	public void update(RequisicionPartida requisicionPartida){
		requisicionPartidaDAO.update(requisicionPartida);
	}
	public void delete(RequisicionPartida requisicionPartida){
		requisicionPartidaDAO.delete(requisicionPartida);
	}
	public RequisicionPartida getById(Long idRequisicionPartida){
		return requisicionPartidaDAO.getById(idRequisicionPartida);
	}
	public List<RequisicionPartida> getByPartida(Partida partida){
		return requisicionPartidaDAO.getByPartida(partida);
	}
	public List<RequisicionPartida> getByRequisicion(Requisicion requisicion){
		return requisicionPartidaDAO.getByRequisicion(requisicion);
	}
	public List<RequisicionPartida> getAll(){
		return requisicionPartidaDAO.getAll();
	}

	
}
