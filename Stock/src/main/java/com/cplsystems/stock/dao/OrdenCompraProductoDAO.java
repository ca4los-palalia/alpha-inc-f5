/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraProducto;
import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */
public interface OrdenCompraProductoDAO {

    public void save(OrdenCompraProducto ordenCompraProducto);
    public void update(OrdenCompraProducto ordenCompraProducto);
    public void delete(OrdenCompraProducto ordenCompraProducto);
    public OrdenCompraProducto getById(Long idOrdenCompraProducto);
    public List<OrdenCompraProducto> getAll();
    public List<OrdenCompraProducto> getByOrdenCopra(OrdenCompra ordenCompra);
    public List<OrdenCompraProducto> getByProducto(Producto producto);

}
