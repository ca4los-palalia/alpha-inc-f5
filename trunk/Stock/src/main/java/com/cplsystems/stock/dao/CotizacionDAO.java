/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.Calendar;
import java.util.List;

import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;

/**
 * @author Carlos Palalía López
 */

public interface CotizacionDAO {

	public void save(Cotizacion cotizacion);

	public void delete(Cotizacion cotizacion);

	public Cotizacion getById(Long idCotizacion);

	public List<Cotizacion> getAll();

	public List<Cotizacion> getByFechaEnvioCotizacion(
			Calendar fechaEnvioSolucion);

	public List<Cotizacion> getByFechaResolicion(Calendar fechaResolucion);

	public List<Cotizacion> getByStatus(Integer status);

	public List<Cotizacion> getByProveedor(Proveedor proveedor);

	public List<Cotizacion> getByRequisicion(Requisicion requisicion);
	
	public List<Cotizacion> getTopCompras();
	
	public Long getCountRowsCotizacion();
	
	public Cotizacion getCotizacionByFolio(String folioCotizacion);
}
