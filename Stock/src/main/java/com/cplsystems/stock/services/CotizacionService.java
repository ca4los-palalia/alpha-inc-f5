package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.CotizacionDAO;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CotizacionService {
	@Autowired
	private CotizacionDAO cotizacionDAO;

	public void save(Cotizacion cotizacion) throws DataAccessException {
		this.cotizacionDAO.save(cotizacion);
	}

	public void delete(Cotizacion cotizacion) throws DataAccessException {
		this.cotizacionDAO.delete(cotizacion);
	}

	public Cotizacion getById(Long idCotizacion) throws DataAccessException {
		return this.cotizacionDAO.getById(idCotizacion);
	}

	public List<Cotizacion> getAll() throws DataAccessException {
		return this.cotizacionDAO.getAll();
	}

	public List<Cotizacion> getByFechaEnvioCotizacion(Calendar fechaEnvioSolucion) throws DataAccessException {
		return this.cotizacionDAO.getByFechaEnvioCotizacion(fechaEnvioSolucion);
	}

	public List<Cotizacion> getByFechaResolicion(Calendar fechaResolucion) throws DataAccessException {
		return this.cotizacionDAO.getByFechaResolicion(fechaResolucion);
	}

	public List<Cotizacion> getByStatus(Integer status) throws DataAccessException {
		return this.cotizacionDAO.getByStatus(status);
	}

	public List<Cotizacion> getByProveedor(Proveedor proveedor) throws DataAccessException {
		return this.cotizacionDAO.getByProveedor(proveedor);
	}

	public List<Cotizacion> getByRequisicion(Requisicion requisicion) throws DataAccessException {
		return this.cotizacionDAO.getByRequisicion(requisicion);
	}

	public List<Cotizacion> getTopCompras() {
		return this.cotizacionDAO.getTopCompras();
	}

	public Long getCountRowsCotizacion() {
		return this.cotizacionDAO.getCountRowsCotizacion();
	}

	public Cotizacion getCotizacionByFolio(String folioCotizacion) {
		return this.cotizacionDAO.getCotizacionByFolio(folioCotizacion);
	}

	public List<Cotizacion> getCotizacionesByEstatusRequisicionAndFolioOrProveedorByFolio(String folioCotizacion,
			List<Proveedor> proveedores, List<EstatusRequisicion> estatus) {
		return this.cotizacionDAO.getCotizacionesByEstatusRequisicionAndFolioOrProveedorByFolio(folioCotizacion,
				proveedores, estatus);
	}

	public Cotizacion getCotizacionByRequisicionProveedorAndProducto(Requisicion requisicion, Proveedor proveedor,
			Producto producto) {
		return this.cotizacionDAO.getCotizacionByRequisicionProveedorAndProducto(requisicion, proveedor, producto);
	}

	public List<Cotizacion> getByProveedorFolioCotizacionNueva(Proveedor proveedor, String folio,
			EstatusRequisicion estatusRequisicion) {
		return this.cotizacionDAO.getByProveedorFolioCotizacionNueva(proveedor, folio, estatusRequisicion);
	}
}
