/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.OrdenCompraProductoDAO;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraProducto;
import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */

@Service
public class OrdenCompraProductoService {

	@Autowired
	private OrdenCompraProductoDAO ordenCompraProductoDAO;

	public void save(OrdenCompraProducto ordenCompraProducto){
		ordenCompraProductoDAO.save(ordenCompraProducto);
	}
    public void update(OrdenCompraProducto ordenCompraProducto){
    	ordenCompraProductoDAO.update(ordenCompraProducto);
    }
    public void delete(OrdenCompraProducto ordenCompraProducto){
    	ordenCompraProductoDAO.delete(ordenCompraProducto);
    }
    public OrdenCompraProducto getById(Long idOrdenCompraProducto){
    	return ordenCompraProductoDAO.getById(idOrdenCompraProducto);
    }
    public List<OrdenCompraProducto> getAll(){
    	return ordenCompraProductoDAO.getAll();
    }
    public List<OrdenCompraProducto> getByOrdenCopra(OrdenCompra ordenCompra){
    	return ordenCompraProductoDAO.getByOrdenCopra(ordenCompra);
    }
    public List<OrdenCompraProducto> getByProducto(Producto producto){
    	return ordenCompraProductoDAO.getByProducto(producto);
    }
	
}
