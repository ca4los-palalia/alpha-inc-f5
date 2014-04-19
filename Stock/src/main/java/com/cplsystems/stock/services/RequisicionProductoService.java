/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	public void save(RequisicionProducto requisicionProducto)throws DataAccessException {
		requisicionProductoDAO.save(requisicionProducto);
	}

	public void delete(RequisicionProducto requisicionProducto)throws DataAccessException {
		requisicionProductoDAO.delete(requisicionProducto);
	}

	public RequisicionProducto getById(Long idRequisicionProducto)throws DataAccessException {
		return requisicionProductoDAO.getById(idRequisicionProducto);
	}

	public List<RequisicionProducto> getByProducto(Contacto contacto)throws DataAccessException {
		return requisicionProductoDAO.getByProducto(contacto);
	}

	public List<RequisicionProducto> getByRequisicion(Contacto contacto)throws DataAccessException {
		return requisicionProductoDAO.getByRequisicion(contacto);
	}

	public List<RequisicionProducto> getByProveedor(Proveedor proveedor)throws DataAccessException {
		return requisicionProductoDAO.getByProveedor(proveedor);
	}

	public List<RequisicionProducto> getByLugar(Lugar lugar) throws DataAccessException{
		return requisicionProductoDAO.getByLugar(lugar);
	}

	public List<RequisicionProducto> getAll()throws DataAccessException {
		return requisicionProductoDAO.getAll();
	}

}
