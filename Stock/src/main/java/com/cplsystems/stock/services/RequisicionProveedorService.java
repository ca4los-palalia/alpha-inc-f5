package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.RequisicionProveedorDAO;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProveedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RequisicionProveedorService {
	@Autowired
	private RequisicionProveedorDAO requisicionProveedorDAO;

	public void save(RequisicionProveedor requisicionProveedor) throws DataAccessException {
		this.requisicionProveedorDAO.save(requisicionProveedor);
	}

	public void delete(RequisicionProveedor requisicionProveedor) throws DataAccessException {
		this.requisicionProveedorDAO.delete(requisicionProveedor);
	}

	public RequisicionProveedor getById(Long idRequisicionProveedor) throws DataAccessException {
		return this.requisicionProveedorDAO.getById(idRequisicionProveedor);
	}

	public List<RequisicionProveedor> getByRequisicion(Requisicion requisicion) throws DataAccessException {
		return this.requisicionProveedorDAO.getByRequisicion(requisicion);
	}

	public List<RequisicionProveedor> getByProveedor(Proveedor Proveedor) throws DataAccessException {
		return this.requisicionProveedorDAO.getByProveedor(Proveedor);
	}

	public List<RequisicionProveedor> getAll() throws DataAccessException {
		return this.requisicionProveedorDAO.getAll();
	}
}
