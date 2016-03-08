package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.PartidaDAO;
import com.cplsystems.stock.domain.Partida;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class PartidaDAOImpl extends HibernateDAOSuportUtil implements PartidaDAO {
	public void save(Partida partida) {
	}

	public void update(Partida partida) {
	}

	public void delete(Partida partida) {
	}

	public Partida getById(Long idPartida) {
		return null;
	}

	public List<Partida> getAll() {
		return null;
	}
}
