package com.cplsystems.stock.app.utils;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.services.CotizacionInboxService;
import com.cplsystems.stock.services.CotizacionService;
import com.cplsystems.stock.services.RequisicionProductoService;
import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
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

@VariableResolver({ DelegatingVariableResolver.class })
public class BandejaVM extends BasicStructure {
	private static final long serialVersionUID = -8003358398924247613L;
	@Wire("#bandejaInboxModalDialog")
	private String globalCommandName;
	protected List<CotizacionInbox> cotizacionesInbox;
	protected CotizacionInbox cotizacionInboxSeleccionada;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder) {
		Selectors.wireComponents(view, this, false);
		this.globalCommandName = updateCommandFromItemFinder;

		super.init();
		loadCotizacionesInbox();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	public List<CotizacionInbox> getCotizacionesInbox() {
		return this.cotizacionesInbox;
	}

	public void setCotizacionesInbox(List<CotizacionInbox> cotizacionesInbox) {
		this.cotizacionesInbox = cotizacionesInbox;
	}

	public CotizacionInbox getCotizacionInboxSeleccionada() {
		return this.cotizacionInboxSeleccionada;
	}

	public void setCotizacionInboxSeleccionada(CotizacionInbox cotizacionInboxSeleccionada) {
		this.cotizacionInboxSeleccionada = cotizacionInboxSeleccionada;
	}

	private void loadCotizacionesInbox() {
		this.cotizacionesInbox = this.cotizacionInboxService
				.getAllNews((Organizacion) this.sessionUtils.getFromSession("FIRMA"));
		for (CotizacionInbox ctInbox : this.cotizacionesInbox) {
			if ((ctInbox.getLeido() != null) && (!ctInbox.getLeido().booleanValue())) {
				ctInbox.setIcono("/images/toolbar/newEmail.png");
			}
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void transferirCotizacionToFormular(@BindingParam("index") Integer index) {
		if (index != null) {
			CotizacionInbox toLoad = (CotizacionInbox) this.cotizacionesInbox.get(index.intValue());
			if ((toLoad.getLeido() != null) && (!toLoad.getLeido().booleanValue())) {
				toLoad.setLeido(Boolean.valueOf(true));
				this.cotizacionInboxService.save(toLoad);
				toLoad.setIcono("/images/toolbar/openedEmail.png");
			}
			this.cotizacionesList.clear();
			this.cotizacionesList.add(toLoad.getCotizacion());

			Cotizacion cotizacion = this.cotizacionService.getById(toLoad.getCotizacion().getIdCotizacion());

			this.requisicionProductos = this.requisicionProductoService.getByCotizacion(cotizacion);
		}
	}
}
