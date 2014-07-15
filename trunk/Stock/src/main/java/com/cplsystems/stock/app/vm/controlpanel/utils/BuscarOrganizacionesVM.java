/**
 * 
 */
package com.cplsystems.stock.app.vm.controlpanel.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class BuscarOrganizacionesVM extends BuscarOrganizacionesVariables {

	private static final long serialVersionUID = 7041693171502316281L;

	@Wire("#companiasModalDialog")
	private Window companiasModalDialog;
	private List<Organizacion> companias;
	private Organizacion organizacionSeleccionada;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange({ "companias" })
	public void buscarCompania() {
		if (compania != null && !compania.isEmpty()) {
			if (rfc != null && !rfc.isEmpty()) {
				companias = organizacionService.getCompaniasByNombreRFC(
						compania, rfc);
			} else {
				companias = organizacionService.getCompaniasByNombre(compania);
			}
		} else {
			if (rfc != null && !rfc.isEmpty()) {
				companias = organizacionService.getCompaniasByRFC(rfc);
			} else {
				companias = organizacionService.getAll();
			}
		}
	}

	@Command
	public void transferirCompania() {
		if (organizacionSeleccionada == null) {
			StockUtils.showSuccessmessage(
					"Por favor seleccione un elemento antes de continuar",
					Clients.NOTIFICATION_TYPE_WARNING, 4500, null);
			return;
		}
		companiasModalDialog.detach();
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("organizacionSeleccionada", organizacionSeleccionada);
		BindUtils.postGlobalCommand(null, null, "loadDatosCompania", args);
	}

	@Command
	public void closeDialog() {
		if (companiasModalDialog != null) {
			companiasModalDialog.detach();
		}
	}

	public List<Organizacion> getCompanias() {
		return companias;
	}

	public void setCompanias(List<Organizacion> companias) {
		this.companias = companias;
	}

	public Organizacion getOrganizacionSeleccionada() {
		return organizacionSeleccionada;
	}

	public void setOrganizacionSeleccionada(
			Organizacion organizacionSeleccionada) {
		this.organizacionSeleccionada = organizacionSeleccionada;
	}

}
