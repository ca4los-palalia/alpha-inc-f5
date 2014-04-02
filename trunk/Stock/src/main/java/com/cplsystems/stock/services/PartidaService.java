/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.PartidaDAO;
import com.cplsystems.stock.domain.Partida;


/**
 * @author Carlos Palalía López
 */

@Service
public class PartidaService {

	@Autowired
	private PartidaDAO partidaDAO;

	public void save(Partida partida){
		partidaDAO.save(partida);
	}
	public void update(Partida partida){
		partidaDAO.update(partida);
	}
	public void delete(Partida partida){
		partidaDAO.delete(partida);
	}
	public Partida getById(Long idPartida){
		return partidaDAO.getById(idPartida);
	}
	public List<Partida> getAll(){
		return partidaDAO.getAll();
	}
	
}
