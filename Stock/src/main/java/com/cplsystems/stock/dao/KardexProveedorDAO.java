package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.KardexProveedor;
import com.cplsystems.stock.domain.Proveedor;

public abstract interface KardexProveedorDAO {

	public void save(KardexProveedor kardexProveedor);

	public void delete(KardexProveedor kardexProveedor);

	public KardexProveedor getById(Long idKardexProveedor);

	public List<KardexProveedor> getAll();

	public List<KardexProveedor> getByEstatus(EstatusRequisicion estatus);

	public List<KardexProveedor> getByProveedorEstatus(Proveedor proveedor, EstatusRequisicion estatus);
}
