package com.cplsystems.stock.app.vm.requisicion;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.RequisicionProducto;
import com.cplsystems.stock.services.CotizacionInboxService;
import com.cplsystems.stock.services.CotizacionService;
import com.cplsystems.stock.services.EstatusRequisicionService;
import com.cplsystems.stock.services.RequisicionProductoService;
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
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver({ DelegatingVariableResolver.class })
public class CancelarCotizacionVM extends RequisicionVariables {
	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";
	@Wire("#modalDialog")
	private Window win;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("cotizacion") Cotizacion ct) {
		super.init();
		if (this.cotizacionSelected == null) {
			this.cotizacionSelected = new Cotizacion();
		}
		this.cotizacionSelected = ct;
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		if ((this.cotizacionSelected != null) && (this.cotizacionSelected.getCancelarDescripcion() != null)
				&& (!this.cotizacionSelected.getCancelarDescripcion().isEmpty())) {
			try {
				EstatusRequisicion estado = this.estatusRequisicionService.getByClave("COC");

				this.cotizacionSelected.setEstatusRequisicion(estado);

				this.cotizacionService.save(this.cotizacionSelected);
				CotizacionInbox inbox = new CotizacionInbox();
				inbox.setFechaRegistro(this.stockUtils.convertirCalendarToDate(Calendar.getInstance()));
				inbox.setCotizacion(this.cotizacionSelected);
				inbox.setLeido(Boolean.valueOf(false));
				this.cotizacionInboxService.save(inbox);

				this.requisicionProductos = this.requisicionProductoService
						.getByRequisicion(this.cotizacionSelected.getRequisicion());
				for (RequisicionProducto item : this.requisicionProductos) {
					item.setDescripcion("Cotizaci�n cancelada");
					this.requisicionProductoService.save(item);
				}
				this.win.detach();
			} catch (Exception e) {
			}
		} else {
			StockUtils.showSuccessmessage("Por favor ingrese el motivo de cancelaci�n", "warning", Integer.valueOf(0),
					null);
		}
	}

	@Command
	public void discart() {
		if (this.win != null) {
			this.win.detach();
		}
	}
}
