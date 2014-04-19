/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.RequisicionProducto;

/**
 * @author Carlos Palalía López
 */

public interface RequisicionProductoDAO {

	public void save(RequisicionProducto requisicionProducto);

	public void delete(RequisicionProducto requisicionProducto);

	public RequisicionProducto getById(Long idRequisicionProducto);

	public List<RequisicionProducto> getByProducto(Contacto contacto);

	public List<RequisicionProducto> getByRequisicion(Contacto contacto);

	public List<RequisicionProducto> getByProveedor(Proveedor proveedor);

	public List<RequisicionProducto> getByLugar(Lugar lugar);

	public List<RequisicionProducto> getAll();
}
