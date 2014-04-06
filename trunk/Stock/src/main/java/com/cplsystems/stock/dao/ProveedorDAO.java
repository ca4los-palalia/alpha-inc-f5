/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Proveedor;


/**
 * @author Carlos Palalía López
 */

public interface ProveedorDAO {

	public void save(Proveedor proveedor);
	public void update(Proveedor proveedor);
	public void delete(Proveedor proveedor);
	public Proveedor getById(Long idProveedor);
	public List<Proveedor> getByContacto(Contacto contacto);
	public List<Proveedor> getByContrato(Contrato contrato);
	public List<Proveedor> getByDireccionDevolucion(Direccion direccion);
	public List<Proveedor> getByDireccionFiscal(Direccion direccion);
	public List<Proveedor> getByGerenteFinanzas(Persona persona);
	public List<Proveedor> getByGerenteVentas(Persona persona);
	public List<Proveedor> getByRepresentanteLegal(Persona persona);
	public List<Proveedor> getByRepresentanteClientes(Persona persona);
	public List<Proveedor> getAll();
}
