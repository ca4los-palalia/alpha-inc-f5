/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion.utils;

import java.util.List;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Requisicion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class RequisicionBandejaVariables extends BasicStructure {

	private static final long serialVersionUID = -2506064720678078791L;

	protected List<Requisicion> requisiciones;
	protected Requisicion requisicionSeleccionada;

	public void init() {

		// requisiciones =
		// requisicionService.getByEstatusRequisicion(estatusRequisicionService.getByClave(StockConstants.ESTADO_COTIZACION_CANCELADA));
		// requisiciones =
		// requisicionService.getByEstatusRequisicion(estatusRequisicionService.getByClave(StockConstants.ESTADO_COTIZACION_ENVIADA));
		requisicionSeleccionada = new Requisicion();
	}

	public List<Requisicion> getRequisiciones() {
		return requisiciones;
	}

	public void setRequisiciones(List<Requisicion> requisiciones) {
		this.requisiciones = requisiciones;
	}

	public Requisicion getRequisicionSeleccionada() {
		return requisicionSeleccionada;
	}

	public void setRequisicionSeleccionada(Requisicion requisicionSeleccionada) {
		this.requisicionSeleccionada = requisicionSeleccionada;
	}

}
