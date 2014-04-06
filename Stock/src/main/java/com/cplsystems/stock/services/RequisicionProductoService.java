/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.RequisicionProductoDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.RequisicionProducto;


/**
 * @author Carlos Palalía López
 */

@Service
public class RequisicionProductoService {

	@Autowired
	private RequisicionProductoDAO requisicionProductoDAO;
	
	public void save(RequisicionProducto requisicionProducto){
		requisicionProductoDAO.save(requisicionProducto);
	}
	public void update(RequisicionProducto requisicionProducto){
		requisicionProductoDAO.update(requisicionProducto);
	}
	public void delete(RequisicionProducto requisicionProducto){
		requisicionProductoDAO.delete(requisicionProducto);
	}
	public RequisicionProducto getById(Long idRequisicionProducto){
		return requisicionProductoDAO.getById(idRequisicionProducto);
	}
	public List<RequisicionProducto> getByProducto(Contacto contacto){
		return requisicionProductoDAO.getByProducto(contacto);
	}
	public List<RequisicionProducto> getByRequisicion(Contacto contacto){
		return requisicionProductoDAO.getByRequisicion(contacto);
	}
	public List<RequisicionProducto> getByProveedor(Proveedor proveedor){
		return requisicionProductoDAO.getByProveedor(proveedor);
	}
	public List<RequisicionProducto> getByLugar(Lugar lugar){
		return requisicionProductoDAO.getByLugar(lugar);
	}
	public List<RequisicionProducto> getAll(){
		return requisicionProductoDAO.getAll();
	}
	
	
}
