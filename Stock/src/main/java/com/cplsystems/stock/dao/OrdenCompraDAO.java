/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.Calendar;
import java.util.List;

import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.Proveedor;

/**
 * @author Carlos Palalía López
 */
public interface OrdenCompraDAO {

	public void save(OrdenCompra ordenCompra);

	public void delete(OrdenCompra ordenCompra);

	public OrdenCompra getById(Long idOrdenCompra);

	public List<OrdenCompra> getAll();

	public List<OrdenCompra> getByCotizacion(Cotizacion cotizacion);
	
	public OrdenCompra getByCodigo(String codigo);
	
	public List<OrdenCompra> getByFechaOrden(Calendar fechaOrden);

	public String getCodigoDeOrden();
	
	public List<OrdenCompra> getOrdenesByEstatusAndFolioOr(
			String folioOrdenCompra, List<EstatusRequisicion> estatus);
}
