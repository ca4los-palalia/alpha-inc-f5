/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;

/**
 * @author Carlos Palalía López
 */

public interface RequisicionProductoDAO {

	public void save(RequisicionProducto requisicionProducto);

	public void delete(RequisicionProducto requisicionProducto);

	public RequisicionProducto getById(Long idRequisicionProducto);

	public List<RequisicionProducto> getByProducto(Producto producto);

	public List<RequisicionProducto> getByRequisicion(Requisicion requisicion);
	
	public List<RequisicionProducto> getRequisicionesConEstadoEspecifico(EstatusRequisicion estatusRequisicion);

	public List<RequisicionProducto> getByProveedor(Proveedor proveedor);

	public List<RequisicionProducto> getByLugar(Lugar lugar);

	public List<RequisicionProducto> getAll();
	
	public List<RequisicionProducto> getAllRequisiciones();
	
	public List<Proveedor> getAllDistinctByProveedor();
	
	public List<RequisicionProducto> getByConfiaPartidaGenerica(CofiaPartidaGenerica cofiaPartidaGenerica);
	
	
	
}
