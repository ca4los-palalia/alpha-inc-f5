package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.RequisicionProductoDAO;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RequisicionProductoService {
	@Autowired
	private RequisicionProductoDAO requisicionProductoDAO;

	public void save(RequisicionProducto requisicionProducto) throws DataAccessException {
		this.requisicionProductoDAO.save(requisicionProducto);
	}

	public void delete(RequisicionProducto requisicionProducto) throws DataAccessException {
		this.requisicionProductoDAO.delete(requisicionProducto);
	}

	public RequisicionProducto getById(Long idRequisicionProducto) throws DataAccessException {
		return this.requisicionProductoDAO.getById(idRequisicionProducto);
	}

	public List<RequisicionProducto> getByProducto(Producto producto) throws DataAccessException {
		return this.requisicionProductoDAO.getByProducto(producto);
	}

	public List<RequisicionProducto> getByRequisicion(Requisicion requisicion) throws DataAccessException {
		return this.requisicionProductoDAO.getByRequisicion(requisicion);
	}

	public List<RequisicionProducto> getByProveedor(Proveedor proveedor) throws DataAccessException {
		return this.requisicionProductoDAO.getByProveedor(proveedor);
	}

	public List<RequisicionProducto> getByLugar(Lugar lugar) throws DataAccessException {
		return this.requisicionProductoDAO.getByLugar(lugar);
	}

	public List<RequisicionProducto> getAll() throws DataAccessException {
		return this.requisicionProductoDAO.getAll();
	}

	public List<RequisicionProducto> getAllRequisiciones() throws DataAccessException {
		return this.requisicionProductoDAO.getAllRequisiciones();
	}

	public List<RequisicionProducto> getRequisicionesConEstadoEspecifico(EstatusRequisicion estatusRequisicion)
			throws DataAccessException {
		return this.requisicionProductoDAO.getRequisicionesConEstadoEspecifico(estatusRequisicion);
	}

	public List<Proveedor> getAllDistinctByProveedor() {
		return this.requisicionProductoDAO.getAllDistinctByProveedor();
	}

	public List<RequisicionProducto> getByConfiaPartidaGenerica(CofiaPartidaGenerica cofiaPartidaGenerica) {
		return this.requisicionProductoDAO.getByConfiaPartidaGenerica(cofiaPartidaGenerica);
	}

	public List<RequisicionProducto> getByCotizacion(Cotizacion cotizacion) {
		return this.requisicionProductoDAO.getByCotizacion(cotizacion);
	}
}
