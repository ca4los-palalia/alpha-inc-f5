/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.DireccionEntrega;



/**
 * @author Carlos Palalía López
 */

public interface DireccionEntregaDAO {

    public void save(DireccionEntrega direccion);
    public void update(DireccionEntrega direccion);
    public void delete(DireccionEntrega direccion);
    public DireccionEntrega getById(Long direccion);
    public List<DireccionEntrega> getByDireccion(Direccion  direccion);
    public List<DireccionEntrega> getAll();
}
