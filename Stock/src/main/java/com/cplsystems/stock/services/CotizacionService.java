/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.CotizacionDAO;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;


/**
 * @author Carlos Palalía López
 */

@Service
public class CotizacionService {

	@Autowired
	private CotizacionDAO cotizacionDAO;
	
	public void save(Cotizacion cotizacion){
		cotizacionDAO.save(cotizacion);
	}
	public void update(Cotizacion cotizacion){
		cotizacionDAO.update(cotizacion);
	}
	public void delete(Cotizacion cotizacion){
		cotizacionDAO.delete(cotizacion);
	}
	public Cotizacion getById(Long idCotizacion){
		return cotizacionDAO.getById(idCotizacion);
	}
	public List<Cotizacion> getAll(){
		return cotizacionDAO.getAll();
	}
	public List<Cotizacion> getByFechaEnvioCotizacion(Calendar fechaEnvioSolucion){
		return cotizacionDAO.getByFechaEnvioCotizacion(fechaEnvioSolucion);
	}
	public List<Cotizacion> getByFechaResolicion(Calendar fechaResolucion){
		return cotizacionDAO.getByFechaResolicion(fechaResolucion);
	}
	public List<Cotizacion> getByStatus(Integer status){
		return cotizacionDAO.getByStatus(status);
	}
	public List<Cotizacion> getByProveedor(Proveedor proveedor){
		return cotizacionDAO.getByProveedor(proveedor);
	}
	public List<Cotizacion> getByRequisicion(Requisicion requisicion){
		return cotizacionDAO.getByRequisicion(requisicion);
	}
	
}
