/**
 * 
 */
package com.cplsystems.stock.app.vm.ordencompra.utils;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class OrdenCompraMetaclass extends OrdenCompraVariables {

	private static final long serialVersionUID = 5093877120990395398L;

	// protected List<RequisicionProducto> requisicionProductos;
	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects() {
		loadOrdenesCompraInbox();
	}

	public void initProperties() {
		
	}

	private void loadOrdenesCompraInbox() {
		ordenesCompraInbox = ordenCompraInboxService
				.getAll((Organizacion) sessionUtils
						.getFromSession(SessionUtils.FIRMA));
		ordenCompraInboxSeleccionada = new OrdenCompraInbox();
		for (OrdenCompraInbox compraInbox : ordenesCompraInbox) {
			if (compraInbox.getLeido() != null && !compraInbox.getLeido()) {
				compraInbox.setIcono(OrdenCompraInbox.NUEVO);
			}
		}
	}
	
	@Command
	public void checkNueva() {
		if (!checkBuscarNueva)
			checkBuscarNueva = true;
		else
			checkBuscarNueva = false;
	}

	@Command
	public void checkCancelada() {
		if (!checkBuscarCancelada)
			checkBuscarCancelada = true;
		else
			checkBuscarCancelada = false;
	}

	@Command
	public void checkAceptada() {
		if (!checkBuscarAceptada)
			checkBuscarAceptada = true;
		else
			checkBuscarAceptada = false;

	}

}
