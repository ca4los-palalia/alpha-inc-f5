/**
 * 
 */
package com.cplsystems.stock.app.vm.proveedor;

import java.util.Date;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.BasicStructure;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public abstract class ProveedorVariables extends BasicStructure {

	private static final long serialVersionUID = -806765252363100225L;

	protected boolean guardadoEmailProveedor;
	protected boolean guardadoEmailContacto;
	protected boolean guardadoTelefonoProveedor;
	protected boolean guardadoTelefonoContacto;
	protected boolean guardadoContactoProveedor;
	protected boolean guardadoContactoContacto;
	protected boolean guardadoDireccionProveedor;
	protected boolean guardadorepResentanteLegalProveedor;
	protected boolean guardadoPersonaContacto;
	protected boolean guardadoNuevoProveedor;
	protected boolean guardadoCuentaPago;

	protected Date contratoVigenciaInicio;
	protected Date contratoVigenciaFin;

	public Date getContratoVigenciaInicio() {
		return contratoVigenciaInicio;
	}

	public void setContratoVigenciaInicio(Date contratoVigenciaInicio) {
		if (contratoVigenciaInicio != null)
			contrato.setFechaVigenciaInicio(new StockUtils().convertirDateToCalendar(contratoVigenciaInicio));
			
		this.contratoVigenciaInicio = contratoVigenciaInicio;
	}

	public Date getContratoVigenciaFin() {
		return contratoVigenciaFin;
	}

	public void setContratoVigenciaFin(Date contratoVigenciaFin) {
		if (contratoVigenciaFin != null) 
			contrato.setFechaVigenciaFin(new StockUtils().convertirDateToCalendar(contratoVigenciaFin));
		
		this.contratoVigenciaFin = contratoVigenciaFin;
	}

}
