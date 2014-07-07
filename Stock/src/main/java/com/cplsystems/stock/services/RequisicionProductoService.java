/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.RequisicionProductoDAO;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
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

	public List<RequisicionProducto> getByProducto(Producto producto)throws DataAccessException {
		return requisicionProductoDAO.getByProducto(producto);
	}

	public List<RequisicionProducto> getByRequisicion(Requisicion requisicion)throws DataAccessException {
		return requisicionProductoDAO.getByRequisicion(requisicion);
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
	
	public List<RequisicionProducto> getAllRequisiciones()throws DataAccessException {
		return requisicionProductoDAO.getAllRequisiciones();
	}
	
	public List<RequisicionProducto> getRequisicionesConEstadoEspecifico(EstatusRequisicion estatusRequisicion)throws DataAccessException {
		return requisicionProductoDAO.getRequisicionesConEstadoEspecifico(estatusRequisicion);
	}
	
	public List<Proveedor> getAllDistinctByProveedor(){
		return requisicionProductoDAO.getAllDistinctByProveedor();
	}
}
