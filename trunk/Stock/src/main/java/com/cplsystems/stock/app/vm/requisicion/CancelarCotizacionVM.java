/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

import java.util.Calendar;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.RequisicionProducto;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CancelarCotizacionVM extends RequisicionVariables {

	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	@Wire("#modalDialog")
	private Window win;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("cotizacion") Cotizacion ct) {
		super.init();
		if (cotizacionSelected == null)
			cotizacionSelected = new Cotizacion();
		cotizacionSelected = ct;
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void save() {
		if (cotizacionSelected != null
				&& (cotizacionSelected.getCancelarDescripcion() != null && !cotizacionSelected
						.getCancelarDescripcion().isEmpty())) {
			try {
				EstatusRequisicion estado = estatusRequisicionService
						.getByClave(StockConstants.ESTADO_COTIZACION_CANCELADA);
				cotizacionSelected.setEstatusRequisicion(estado);
				
				cotizacionService.save(cotizacionSelected);
				CotizacionInbox inbox = new CotizacionInbox();
				inbox.setFechaRegistro(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
				inbox.setCotizacion(cotizacionSelected);
				inbox.setLeido(false);
				cotizacionInboxService.save(inbox);
				
				//NOTIFICAR AL USUARIO DE LA REQUISICION
				requisicionProductos = requisicionProductoService.getByRequisicion(cotizacionSelected.getRequisicion());
				for (RequisicionProducto item : requisicionProductos) {
					item.setDescripcion("Cotización cancelada");
					requisicionProductoService.save(item);
				}
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
