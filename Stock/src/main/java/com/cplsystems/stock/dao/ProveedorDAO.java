package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Proveedor;
import java.util.List;

public abstract interface ProveedorDAO {
	public abstract void save(Proveedor paramProveedor);

	public abstract void update(Proveedor paramProveedor);

	public abstract void delete(Proveedor paramProveedor);

	public abstract Proveedor getById(Long paramLong);

	public abstract List<Proveedor> getProveedoresById(List<Long> paramList);

	public abstract List<Proveedor> getByContacto(Contacto paramContacto);

	public abstract List<Proveedor> getByContrato(Contrato paramContrato);

	public abstract List<Proveedor> getByDireccionDevolucion(Direccion paramDireccion);

	public abstract List<Proveedor> getByDireccionFiscal(Direccion paramDireccion);

	public abstract List<Proveedor> getByGerenteFinanzas(Persona paramPersona);

	public abstract List<Proveedor> getByGerenteVentas(Persona paramPersona);

	public abstract List<Proveedor> getByRepresentanteLegal(Persona paramPersona);

	public abstract List<Proveedor> getByRepresentanteClientes(Persona paramPersona);

	public abstract List<Proveedor> getAll();

	public abstract List<Proveedor> getBysClaveNombreRfc(String paramString);

	public abstract List<Proveedor> getByNombre(String paramString);
}
