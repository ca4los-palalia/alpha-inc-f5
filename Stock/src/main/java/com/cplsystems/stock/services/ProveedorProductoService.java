/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.ProveedorProductoDAO;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;

/**
 * @author Carlos Palalía López
 */

@Service
public class ProveedorProductoService {

	@Autowired
	private ProveedorProductoDAO proveedorProductoDAO;
	
	public void save(ProveedorProducto proveedorProducto){
		proveedorProductoDAO.save(proveedorProducto);
	}
	public void update(ProveedorProducto proveedorProducto){
		proveedorProductoDAO.update(proveedorProducto);
	}
	public void delete(ProveedorProducto proveedorProducto){
		proveedorProductoDAO.delete(proveedorProducto);
	}
	public ProveedorProducto getById(Long idProveedorProducto){
		return proveedorProductoDAO.getById(idProveedorProducto);
	}
	public List<ProveedorProducto> getByProveedor(Proveedor Proveedor){
		return proveedorProductoDAO.getByProveedor(Proveedor);
	}
	public List<ProveedorProducto> getByProducto(Producto Producto){
		return proveedorProductoDAO.getByProducto(Producto);
	}
	public List<ProveedorProducto> getAll(){
		return proveedorProductoDAO.getAll();
	}
}
