/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ProveedorDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Proveedor;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ProveedorDAOImpl extends HibernateDAOSuportUtil implements ProveedorDAO{

	public void save(Proveedor proveedor) {
		// TODO Auto-generated method stub
		
	}

	public void update(Proveedor proveedor) {
		// TODO Auto-generated method stub
		
	}

	public void delete(Proveedor proveedor) {
		// TODO Auto-generated method stub
		
	}

	public Proveedor getById(Long idProveedor) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Proveedor> getByContacto(Contacto contacto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Proveedor> getByContrato(Contrato contrato) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Proveedor> getByDireccionDevolucion(Direccion direccion) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Proveedor> getByDireccionFiscal(Direccion direccion) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Proveedor> getByGerenteFinanzas(Persona persona) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Proveedor> getByGerenteVentas(Persona persona) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Proveedor> getByRepresentanteLegal(Persona persona) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Proveedor> getByRepresentanteClientes(Persona persona) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Proveedor> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
