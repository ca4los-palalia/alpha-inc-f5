package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Kardex;
import com.cplsystems.stock.domain.KardexProveedor;
import com.cplsystems.stock.domain.Proveedor;

public abstract interface KardexDAO {

	public void save(Kardex kardex);

	void delete(Kardex kardex);

	public Kardex getById(Long idKardex);

	public List<Kardex> getAll();

	public List<Kardex> getByEstatus(EstatusRequisicion estatus);

	public List<Kardex> getByKardexProveedorEstatus(KardexProveedor kardexProveedor, EstatusRequisicion estatus);
	
}
