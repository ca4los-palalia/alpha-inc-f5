package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.ProveedorDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Proveedor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {
	@Autowired
	private ProveedorDAO proveedorDAO;

	public void save(Proveedor proveedor) throws DataAccessException {
		this.proveedorDAO.save(proveedor);
	}

	public void update(Proveedor proveedor) throws DataAccessException {
		this.proveedorDAO.update(proveedor);
	}

	public void delete(Proveedor proveedor) throws DataAccessException {
		this.proveedorDAO.delete(proveedor);
	}

	public Proveedor getById(Long idProveedor) {
		return this.proveedorDAO.getById(idProveedor);
	}

	public List<Proveedor> getByContacto(Contacto contacto) throws DataAccessException {
		return this.proveedorDAO.getByContacto(contacto);
	}

	public List<Proveedor> getByContrato(Contrato contrato) throws DataAccessException {
		return this.proveedorDAO.getByContrato(contrato);
	}

	public List<Proveedor> getByDireccionDevolucion(Direccion direccion) throws DataAccessException {
		return this.proveedorDAO.getByDireccionDevolucion(direccion);
	}

	public List<Proveedor> getByDireccionFiscal(Direccion direccion) throws DataAccessException {
		return this.proveedorDAO.getByDireccionFiscal(direccion);
	}

	public List<Proveedor> getByGerenteFinanzas(Persona persona) throws DataAccessException {
		return this.proveedorDAO.getByGerenteFinanzas(persona);
	}

	public List<Proveedor> getByGerenteVentas(Persona persona) throws DataAccessException {
		return this.proveedorDAO.getByGerenteVentas(persona);
	}

	public List<Proveedor> getByRepresentanteLegal(Persona persona) throws DataAccessException {
		return this.proveedorDAO.getByRepresentanteLegal(persona);
	}

	public List<Proveedor> getByRepresentanteClientes(Persona persona) throws DataAccessException {
		return this.proveedorDAO.getByRepresentanteClientes(persona);
	}

	public List<Proveedor> getAll() throws DataAccessException {
		return this.proveedorDAO.getAll();
	}

	public List<Proveedor> getBysClaveNombreRfc(String buscarTexto) {
		return this.proveedorDAO.getBysClaveNombreRfc(buscarTexto);
	}

	public List<Proveedor> getByNombre(String nombre) {
		return this.proveedorDAO.getByNombre(nombre);
	}

	public List<Proveedor> getProveedoresById(List<Long> idsProveedores) {
		return this.proveedorDAO.getProveedoresById(idsProveedores);
	}
}
