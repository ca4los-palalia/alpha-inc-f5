/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.RequisicionProductoDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.RequisicionProducto;


/**
 * @author Carlos Palalía López
 */

@Repository
public class RequisicionProductoDAOImpl extends HibernateDAOSuportUtil implements RequisicionProductoDAO{

	public void save(RequisicionProducto requisicionProducto) {
		// TODO Auto-generated method stub
		
	}

	public void update(RequisicionProducto requisicionProducto) {
		// TODO Auto-generated method stub
		
	}

	public void delete(RequisicionProducto requisicionProducto) {
		// TODO Auto-generated method stub
		
	}
	public RequisicionProducto getById(Long idRequisicionProducto) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<RequisicionProducto> getByProducto(Contacto contacto) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<RequisicionProducto> getByRequisicion(Contacto contacto) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<RequisicionProducto> getByProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<RequisicionProducto> getByLugar(Lugar lugar) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<RequisicionProducto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
