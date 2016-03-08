package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Partida;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionPartida;
import java.util.List;

public abstract interface RequisicionPartidaDAO {
	public abstract void save(RequisicionPartida paramRequisicionPartida);

	public abstract void delete(RequisicionPartida paramRequisicionPartida);

	public abstract RequisicionPartida getById(Long paramLong);

	public abstract List<RequisicionPartida> getByPartida(Partida paramPartida);

	public abstract List<RequisicionPartida> getByRequisicion(Requisicion paramRequisicion);

	public abstract List<RequisicionPartida> getAll();
}
