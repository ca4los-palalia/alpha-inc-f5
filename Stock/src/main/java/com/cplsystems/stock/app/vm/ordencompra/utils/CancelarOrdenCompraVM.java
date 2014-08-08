/**
 * 
 */
package com.cplsystems.stock.app.vm.ordencompra.utils;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import java.util.Calendar;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class CancelarOrdenCompraVM extends RequisicionVariables {

	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	@Wire("#modalDialog")
	private Window win;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("orden") OrdenCompra ct) {
		super.init();
		if (ordenCompra == null)
			ordenCompra = new OrdenCompra();
		ordenCompra = ct;
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void save() {
		if (ordenCompra != null
				&& (ordenCompra.getCancelarDescripcion() != null && !ordenCompra
						.getCancelarDescripcion().isEmpty())) {
			try {
				EstatusRequisicion estado = estatusRequisicionService
						.getByClave(StockConstants.ESTADO_ORDEN_COMPRA_CANCELADA);
				ordenCompra.setEstatusRequisicion(estado);
				ordenCompraService.save(ordenCompra);
				
				/*OrdenCompraInbox inbox = new OrdenCompraInbox();
				inbox.setFechaCreacion(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
				inbox.setOrdenCompra(ordenCompra);
				inbox.setLeido(false);
				ordenCompraInboxService.save(inbox);*/
				win.detach();

			} catch (Exception e) {
				// TODO: handle exception
			}

		} else
			stockUtils.showSuccessmessage(
					"Por favor ingrese el motivo de cancelación",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	@Command
	public void discart(){
		if(win != null)
			win.detach();
	}
}
