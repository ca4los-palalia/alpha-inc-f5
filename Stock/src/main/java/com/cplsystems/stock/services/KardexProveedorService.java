package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.KardexProveedorDAO;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.KardexProveedor;
import com.cplsystems.stock.domain.Proveedor;

@Service
public class KardexProveedorService {
	@Autowired
	private KardexProveedorDAO kardexProveedorProveedorDAO;

	public void save(KardexProveedor kardexProveedor) throws DataAccessException {
		this.kardexProveedorProveedorDAO.save(kardexProveedor);
	}

	public void delete(KardexProveedor kardexProveedor) throws DataAccessException {
		this.kardexProveedorProveedorDAO.delete(kardexProveedor);
	}

	public KardexProveedor getById(Long idKardexProveedor) throws DataAccessException {
		return this.kardexProveedorProveedorDAO.getById(idKardexProveedor);
	}

	public List<KardexProveedor> getAll() throws DataAccessException {
		return this.kardexProveedorProveedorDAO.getAll();
	}
	public List<KardexProveedor> getByEstatus(EstatusRequisicion estatus) throws DataAccessException {
		return this.kardexProveedorProveedorDAO.getByEstatus(estatus);
	}
	public List<KardexProveedor> getByProveedorEstatus(Proveedor proveedor, EstatusRequisicion estatus) throws DataAccessException {
		return this.kardexProveedorProveedorDAO.getByProveedorEstatus(proveedor, estatus);
	}
}
