/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.RequisicionProveedorDAO;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProveedor;


/**
 * @author Carlos Palalía López
 */

@Service
public class RequisicionProveedorService {

	@Autowired
	private RequisicionProveedorDAO requisicionProveedorDAO;
	
	public void save(RequisicionProveedor requisicionProveedor){
		requisicionProveedorDAO.save(requisicionProveedor);
	}
	public void update(RequisicionProveedor requisicionProveedor){
		requisicionProveedorDAO.update(requisicionProveedor);
	}
	public void delete(RequisicionProveedor requisicionProveedor){
		requisicionProveedorDAO.delete(requisicionProveedor);
	}
	public RequisicionProveedor getById(Long idRequisicionProveedor){
		return requisicionProveedorDAO.getById(idRequisicionProveedor);
	}
	
	public List<RequisicionProveedor> getByRequisicion(Requisicion requisicion){
		return requisicionProveedorDAO.getByRequisicion(requisicion);
	}
	public List<RequisicionProveedor> getByProveedor(Proveedor Proveedor){
		return requisicionProveedorDAO.getByProveedor(Proveedor);
	}
	public List<RequisicionProveedor> getAll(){
		return requisicionProveedorDAO.getAll();
	}

	
}
