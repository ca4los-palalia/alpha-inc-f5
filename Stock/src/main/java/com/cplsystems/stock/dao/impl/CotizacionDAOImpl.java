/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cplsystems.stock.dao.CotizacionDAO;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;



/**
 * @author Carlos Palalía López
 */

@Repository
public class CotizacionDAOImpl implements CotizacionDAO{

	public void save(Cotizacion cotizacion) {
		// TODO Auto-generated method stub
		
	}

	public void update(Cotizacion cotizacion) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Cotizacion cotizacion) {
		// TODO Auto-generated method stub
		
	}

	public Cotizacion getById(Long idCotizacion) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cotizacion> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cotizacion> getByFechaEnvioCotizacion(
			Calendar fechaEnvioSolucion) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cotizacion> getByFechaResolicion(Calendar fechaResolucion) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cotizacion> getByStatus(Integer status) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cotizacion> getByProveedor(Proveedor proveedor) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Cotizacion> getByRequisicion(Requisicion requisicion) {
		// TODO Auto-generated method stub
		return null;
	}

}
