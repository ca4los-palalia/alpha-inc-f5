package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Partida;
import java.util.List;

public abstract interface PartidaDAO {
	public abstract void save(Partida paramPartida);

	public abstract void delete(Partida paramPartida);

	public abstract Partida getById(Long paramLong);

	public abstract List<Partida> getAll();
}
