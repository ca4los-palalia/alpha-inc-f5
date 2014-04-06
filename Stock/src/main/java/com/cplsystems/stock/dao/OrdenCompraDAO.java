/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.OrdenCompra;

/**
 * @author Carlos Palalía López
 */
public interface OrdenCompraDAO {

    public void save(OrdenCompra ordenCompra);
    public void update(OrdenCompra ordenCompra);
    public void delete(OrdenCompra ordenCompra);
    public OrdenCompra getById(Long idOrdenCompra);
    public List<OrdenCompra> getAll();
    public List<OrdenCompra> getByCotizacion(Cotizacion cotizacion);

}
