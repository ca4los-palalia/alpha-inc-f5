/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.CuentaPago;
import com.cplsystems.stock.domain.Proveedor;


/**
 * @author Carlos Palalía López
 */
public interface CuentasPagoDAO {
	public void save(CuentaPago cuentaPago);
	public void update(CuentaPago cuentaPago);
	public void delete(CuentaPago cuentaPago);
	public CuentaPago getById(Long idCuentaPago);
	public List<CuentaPago> getAll();
	public List<CuentaPago> getByProveedor(Proveedor proveedor);
}
