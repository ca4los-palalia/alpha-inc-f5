/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public void save(Proveedor proveedor){
		proveedorDAO.save(proveedor);
	}
	public void update(Proveedor proveedor){
		proveedorDAO.update(proveedor);
	}
	public void delete(Proveedor proveedor){
		proveedorDAO.delete(proveedor);
	}
	public Proveedor getById(Long idProveedor){
		return proveedorDAO.getById(idProveedor);
	}
	public List<Proveedor> getByContacto(Contacto contacto){
		return proveedorDAO.getByContacto(contacto);
	}
	public List<Proveedor> getByContrato(Contrato contrato){
		return proveedorDAO.getByContrato(contrato);
	}
	public List<Proveedor> getByDireccionDevolucion(Direccion direccion){
		return proveedorDAO.getByDireccionDevolucion(direccion);
	}
	public List<Proveedor> getByDireccionFiscal(Direccion direccion){
		return proveedorDAO.getByDireccionFiscal(direccion);
	}
	public List<Proveedor> getByGerenteFinanzas(Persona persona){
		return proveedorDAO.getByGerenteFinanzas(persona);
	}
	public List<Proveedor> getByGerenteVentas(Persona persona){
		return proveedorDAO.getByGerenteVentas(persona);
	}
	public List<Proveedor> getByRepresentanteLegal(Persona persona){
		return proveedorDAO.getByRepresentanteLegal(persona);
	}
	public List<Proveedor> getByRepresentanteClientes(Persona persona){
		return proveedorDAO.getByRepresentanteClientes(persona);
	}
	public List<Proveedor> getAll(){
		return proveedorDAO.getAll();
	}

	
	
}
