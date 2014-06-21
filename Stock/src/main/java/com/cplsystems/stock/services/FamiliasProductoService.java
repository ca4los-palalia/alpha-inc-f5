/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.FamiliasProductoDAO;
import com.cplsystems.stock.domain.FamiliasProducto;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;

/**
 * @author Carlos Palalía López
 */

@Service
public class FamiliasProductoService {

	@Autowired
	private FamiliasProductoDAO familiasProductoDAO;

	public void save(FamiliasProducto familiasProducto){
		familiasProductoDAO.save(familiasProducto);
	}

	public void delete(FamiliasProducto familiasProducto){
		familiasProductoDAO.delete(familiasProducto);
	}

	public FamiliasProducto getById(Long idFamiliasProducto){
		return familiasProductoDAO.getById(idFamiliasProducto);
	}

	public List<FamiliasProducto> getAll(){
		return familiasProductoDAO.getAll();
	}

	public List<FamiliasProducto> getByProducto(Producto producto){
		return familiasProductoDAO.getByProducto(producto);
	}
	
	public List<FamiliasProducto> getByFamilia(ProductoTipo productoTipo){
		return familiasProductoDAO.getByFamilia(productoTipo);
	}
}
