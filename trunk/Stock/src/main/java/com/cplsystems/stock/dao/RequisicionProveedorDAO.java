/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProveedor;

/**
 * @author Carlos Palalía López
 */
public interface RequisicionProveedorDAO {
	public void save(RequisicionProveedor requisicionProveedor);
	public void update(RequisicionProveedor requisicionProveedor);
	public void delete(RequisicionProveedor requisicionProveedor);
	public RequisicionProveedor getById(Long idRequisicionProveedor);
	
	public List<RequisicionProveedor> getByRequisicion(Requisicion requisicion);
	public List<RequisicionProveedor> getByProveedor(Proveedor Proveedor);
	public List<RequisicionProveedor> getAll();
}
