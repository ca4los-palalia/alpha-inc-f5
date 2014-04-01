/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Partida;

/**
 * @author Carlos Palalía López
 */

public interface PartidaDAO {

	public void save(Partida partida);
	public void update(Partida partida);
	public void delete(Partida partida);
	public Partida getById(Long idPartida);
	public List<Partida> getAll();
}
