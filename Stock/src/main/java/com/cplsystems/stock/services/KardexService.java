package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.KardexDAO;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Kardex;
import com.cplsystems.stock.domain.KardexProveedor;

@Service
public class KardexService {
	@Autowired
	private KardexDAO kardexDAO;

	public void save(Kardex kardex) throws DataAccessException {
		this.kardexDAO.save(kardex);
	}

	public void delete(Kardex kardex) throws DataAccessException {
		this.kardexDAO.delete(kardex);
	}

	public Kardex getById(Long idKardex) throws DataAccessException {
		return this.kardexDAO.getById(idKardex);
	}

	public List<Kardex> getAll() throws DataAccessException {
		return this.kardexDAO.getAll();
	}
	public List<Kardex> getByEstatus(EstatusRequisicion estatus) throws DataAccessException {
		return this.kardexDAO.getByEstatus(estatus);
	}
	
	public List<Kardex> getByKardexProveedorEstatus(KardexProveedor kardexProveedor, EstatusRequisicion estatus) throws DataAccessException {
		return this.kardexDAO.getByKardexProveedorEstatus(kardexProveedor, estatus);
	}
}
