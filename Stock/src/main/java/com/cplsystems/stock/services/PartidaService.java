package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.PartidaDAO;
import com.cplsystems.stock.domain.Partida;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {
	@Autowired
	private PartidaDAO partidaDAO;

	public void save(Partida partida) throws DataAccessException {
		this.partidaDAO.save(partida);
	}

	public void delete(Partida partida) throws DataAccessException {
		this.partidaDAO.delete(partida);
	}

	public Partida getById(Long idPartida) throws DataAccessException {
		return this.partidaDAO.getById(idPartida);
	}

	public List<Partida> getAll() throws DataAccessException {
		return this.partidaDAO.getAll();
	}
}
