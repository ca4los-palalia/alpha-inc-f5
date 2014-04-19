/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.ProveedorDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Proveedor;

/**
 * @author Carlos Palalía López
 */

@Service
public class ProveedorService {

	@Autowired
	private ProveedorDAO proveedorDAO;

	public void save(Proveedor proveedor) throws DataAccessException {
		proveedorDAO.save(proveedor);
	}

	public void delete(Proveedor proveedor) throws DataAccessException {
		proveedorDAO.delete(proveedor);
	}

	public Proveedor getById(Long idProveedor) throws DataAccessException {
		return proveedorDAO.getById(idProveedor);
	}

	public List<Proveedor> getByContacto(Contacto contacto)
			throws DataAccessException {
		return proveedorDAO.getByContacto(contacto);
	}

	public List<Proveedor> getByContrato(Contrato contrato)
			throws DataAccessException {
		return proveedorDAO.getByContrato(contrato);
	}

	public List<Proveedor> getByDireccionDevolucion(Direccion direccion)
			throws DataAccessException {
		return proveedorDAO.getByDireccionDevolucion(direccion);
	}

	public List<Proveedor> getByDireccionFiscal(Direccion direccion)
			throws DataAccessException {
		return proveedorDAO.getByDireccionFiscal(direccion);
	}

	public List<Proveedor> getByGerenteFinanzas(Persona persona)
			throws DataAccessException {
		return proveedorDAO.getByGerenteFinanzas(persona);
	}

	public List<Proveedor> getByGerenteVentas(Persona persona)
			throws DataAccessException {
		return proveedorDAO.getByGerenteVentas(persona);
	}

	public List<Proveedor> getByRepresentanteLegal(Persona persona)
			throws DataAccessException {
		return proveedorDAO.getByRepresentanteLegal(persona);
	}

	public List<Proveedor> getByRepresentanteClientes(Persona persona)
			throws DataAccessException {
		return proveedorDAO.getByRepresentanteClientes(persona);
	}

	public List<Proveedor> getAll() throws DataAccessException {
		return proveedorDAO.getAll();
	}

}
