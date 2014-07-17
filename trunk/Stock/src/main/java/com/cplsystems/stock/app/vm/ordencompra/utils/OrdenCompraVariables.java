/**
 * 
 */
package com.cplsystems.stock.app.vm.ordencompra.utils;

import java.util.List;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.OrdenCompraInbox;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class OrdenCompraVariables extends BasicStructure {

	private static final long serialVersionUID = -5741444614581908156L;
	protected List<OrdenCompraInbox> ordenesCompraInbox;
	protected OrdenCompraInbox ordenCompraInboxSeleccionada;

	public List<OrdenCompraInbox> getOrdenesCompraInbox() {
		return ordenesCompraInbox;
	}

	public void setOrdenesCompraInbox(List<OrdenCompraInbox> ordenesCompraInbox) {
		this.ordenesCompraInbox = ordenesCompraInbox;
	}

	public OrdenCompraInbox getOrdenCompraInboxSeleccionada() {
		return ordenCompraInboxSeleccionada;
	}

	public void setOrdenCompraInboxSeleccionada(
			OrdenCompraInbox ordenCompraInboxSeleccionada) {
		this.ordenCompraInboxSeleccionada = ordenCompraInboxSeleccionada;
	}

}
