/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.CostosProductoDAO;
import com.cplsystems.stock.domain.CostosProducto;
import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */

@Service
public class CostosProductoService {

	@Autowired
	private CostosProductoDAO costosProductoDAO;

	public void save(CostosProducto costosProducto){
		costosProductoDAO.save(costosProducto);
	}

	public void delete(CostosProducto costosProducto){
		costosProductoDAO.delete(costosProducto);
	}

	public CostosProducto getById(Long idCodigoBarrasProducto){
		return costosProductoDAO.getById(idCodigoBarrasProducto);
	}

	public List<CostosProducto> getAll(){
		return costosProductoDAO.getAll();
	}

	public CostosProducto getByProducto(Producto producto){
		return costosProductoDAO.getByProducto(producto);
	}
}
