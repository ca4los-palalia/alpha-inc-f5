/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Partida;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionPartida;

/**
 * @author Carlos Palalía López
 */
public interface RequisicionPartidaDAO {
	public void save(RequisicionPartida requisicionPartida);

	public void delete(RequisicionPartida requisicionPartida);

	public RequisicionPartida getById(Long idRequisicionPartida);

	public List<RequisicionPartida> getByPartida(Partida partida);

	public List<RequisicionPartida> getByRequisicion(Requisicion requisicion);

	public List<RequisicionPartida> getAll();
}
