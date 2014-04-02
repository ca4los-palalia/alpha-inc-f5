/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Destino;


/**
 * @author Carlos Palalía López
 */

public interface DestinoDAO {

    public void save(Destino destino);
    public void update(Destino destino);
    public Destino getById(Long idDestino);
    public Destino getByNombre(String lugar);
    public List<Destino> getAll();
}
