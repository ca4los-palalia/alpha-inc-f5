package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.OrdenCompraDAO;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompra;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class OrdenCompraService {
	@Autowired
	private OrdenCompraDAO ordenCompraDAO;

	public void save(OrdenCompra ordenCompra) throws DataAccessException {
		this.ordenCompraDAO.save(ordenCompra);
	}

	public void delete(OrdenCompra ordenCompra) throws DataAccessException {
		this.ordenCompraDAO.delete(ordenCompra);
	}

	public OrdenCompra getById(Long idOrdenCompra) throws DataAccessException {
		return this.ordenCompraDAO.getById(idOrdenCompra);
	}

	public List<OrdenCompra> getAll() throws DataAccessException {
		return this.ordenCompraDAO.getAll();
	}

	public List<OrdenCompra> getByCotizacion(Cotizacion cotizacion) throws DataAccessException {
		return this.ordenCompraDAO.getByCotizacion(cotizacion);
	}

	public OrdenCompra getByCodigo(String codigo) {
		return this.ordenCompraDAO.getByCodigo(codigo);
	}

	public List<OrdenCompra> getByFechaOrden(Calendar fechaOrden) {
		return this.ordenCompraDAO.getByFechaOrden(fechaOrden);
	}

	public String getCodigoDeOrden() {
		return this.ordenCompraDAO.getCodigoDeOrden();
	}

	public List<OrdenCompra> getOrdenesByEstatusAndFolioOr(String folioOrdenCompra, List<EstatusRequisicion> estatus) {
		return this.ordenCompraDAO.getOrdenesByEstatusAndFolioOr(folioOrdenCompra, estatus);
	}
}
