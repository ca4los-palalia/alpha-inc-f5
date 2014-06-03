/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	public void save(Cotizacion cotizacion) throws DataAccessException {
		cotizacionDAO.save(cotizacion);
	}

	public void delete(Cotizacion cotizacion) throws DataAccessException {
		cotizacionDAO.delete(cotizacion);
	}

	public Cotizacion getById(Long idCotizacion) throws DataAccessException {
		return cotizacionDAO.getById(idCotizacion);
	}

	public List<Cotizacion> getAll() throws DataAccessException {
		return cotizacionDAO.getAll();
	}

	public List<Cotizacion> getByFechaEnvioCotizacion(
			Calendar fechaEnvioSolucion) throws DataAccessException {
		return cotizacionDAO.getByFechaEnvioCotizacion(fechaEnvioSolucion);
	}

	public List<Cotizacion> getByFechaResolicion(Calendar fechaResolucion)
			throws DataAccessException {
		return cotizacionDAO.getByFechaResolicion(fechaResolucion);
	}

	public List<Cotizacion> getByStatus(Integer status)
			throws DataAccessException {
		return cotizacionDAO.getByStatus(status);
	}

	public List<Cotizacion> getByProveedor(Proveedor proveedor)
			throws DataAccessException {
		return cotizacionDAO.getByProveedor(proveedor);
	}

	public List<Cotizacion> getByRequisicion(Requisicion requisicion)
			throws DataAccessException {
		return cotizacionDAO.getByRequisicion(requisicion);
	}

	public List<Cotizacion> getTopCompras(){
		return cotizacionDAO.getTopCompras();	
	}
}
