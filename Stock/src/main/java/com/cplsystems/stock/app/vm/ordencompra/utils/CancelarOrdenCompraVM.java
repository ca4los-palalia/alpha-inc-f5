package com.cplsystems.stock.app.vm.ordencompra.utils;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.services.EstatusRequisicionService;
import com.cplsystems.stock.services.OrdenCompraService;
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
import org.zkoss.zul.Window;

public class CancelarOrdenCompraVM extends RequisicionVariables {
	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";
	@Wire("#modalDialog")
	private Window win;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("orden") OrdenCompra ct) {
		super.init();
		if (this.ordenCompra == null) {
			this.ordenCompra = new OrdenCompra();
		}
		this.ordenCompra = ct;
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		if ((this.ordenCompra != null) && (this.ordenCompra.getCancelarDescripcion() != null)
				&& (!this.ordenCompra.getCancelarDescripcion().isEmpty())) {
			try {
				EstatusRequisicion estado = this.estatusRequisicionService.getByClave("OCC");

				this.ordenCompra.setEstatusRequisicion(estado);
				this.ordenCompraService.save(this.ordenCompra);

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
