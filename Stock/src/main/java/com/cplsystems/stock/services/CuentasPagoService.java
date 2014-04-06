/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.CuentasPagoDAO;
import com.cplsystems.stock.domain.CuentaPago;
import com.cplsystems.stock.domain.Proveedor;

/**
 * @author Carlos Palalía López
 */

@Service
public class CuentasPagoService {

	@Autowired
	private CuentasPagoDAO cuentasPagoDAO;

	public void save(CuentaPago cuentaPago){
		cuentasPagoDAO.save(cuentaPago);
	}
	public void update(CuentaPago cuentaPago){
		cuentasPagoDAO.update(cuentaPago);
	}
	public void delete(CuentaPago cuentaPago){
		cuentasPagoDAO.delete(cuentaPago);
	}
	public CuentaPago getById(Long idCuentaPago){
		return cuentasPagoDAO.getById(idCuentaPago);
	}
	public List<CuentaPago> getAll(){
		return cuentasPagoDAO.getAll();
	}
	public List<CuentaPago> getByProveedor(Proveedor proveedor){
		return cuentasPagoDAO.getByProveedor(proveedor);
}
	
}
