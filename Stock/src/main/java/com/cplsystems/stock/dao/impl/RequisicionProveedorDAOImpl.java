/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.RequisicionProveedorDAO;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProveedor;

/**
 * @author Carlos Palalía López
 */

@Repository
public class RequisicionProveedorDAOImpl extends HibernateDAOSuportUtil implements RequisicionProveedorDAO{


	public void save(RequisicionProveedor requisicionProveedor) {
		// TODO Auto-generated method stub
		
	}

	public void update(RequisicionProveedor requisicionProveedor) {
		// TODO Auto-generated method stub
		
	}

	
	public void delete(RequisicionProveedor requisicionProveedor) {
		// TODO Auto-generated method stub
		
	}

	public RequisicionProveedor getById(Long idRequisicionProveedor) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RequisicionProveedor> getByRequisicion(Requisicion requisicion) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RequisicionProveedor> getByProveedor(Proveedor Proveedor) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RequisicionProveedor> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
   
}
