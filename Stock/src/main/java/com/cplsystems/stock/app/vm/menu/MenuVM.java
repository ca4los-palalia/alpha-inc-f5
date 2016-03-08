package com.cplsystems.stock.app.vm.menu;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.PrivilegioService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver({ DelegatingVariableResolver.class })
public class MenuVM extends BasicStructure {
	public static final String PAGE_TO_RENDER = "pageToRender";
	private Map<String, Object> args;
	private Usuarios usuario;
	private boolean mostrarRequisiones;
	private boolean mostrarConcentrados;
	private boolean mostrarCotizacionesAutorizaciones;
	private boolean mostrarOrdenesCompra;
	private boolean mostrarPanelControl;
	private boolean ownerOptions;
	private boolean clientOptions;

	@Init
	public void init() {
		this.args = new HashMap();
		loadPrivileges();
	}

	@NotifyChange({ "mostrarPanelControl", "mostrarConcentrados", "mostrarCotizacionesAutorizaciones",
			"mostrarOrdenesCompra", "mostrarRequisiones" })
	public void loadPrivileges() {
		this.usuario = ((Usuarios) this.sessionUtils.getFromSession("usuario"));
		if (this.usuario != null) {
			if ((this.usuario.getClient() != null) && (this.usuario.getClient().booleanValue())) {
				this.mostrarConcentrados = true;
				this.mostrarCotizacionesAutorizaciones = true;
				this.mostrarOrdenesCompra = true;
				this.mostrarRequisiones = true;
				this.mostrarPanelControl = true;
				this.ownerOptions = false;
				this.clientOptions = true;
				return;
			}
			if ((this.usuario.getOwner() != null) && (this.usuario.getOwner().booleanValue())) {
				this.mostrarConcentrados = true;
				this.mostrarCotizacionesAutorizaciones = true;
				this.mostrarOrdenesCompra = true;
				this.mostrarRequisiones = true;
				this.mostrarPanelControl = true;
				this.ownerOptions = true;
				this.clientOptions = true;
				return;
			}
			List<Privilegios> privilegios = this.privilegioService.getPrivilegiosByUsuario(this.usuario);
			if (privilegios != null) {
				for (Privilegios privilegio : privilegios) {
					switch (privilegio.getUserPrivileges()) {
					case CONCENTRAR:
						this.mostrarConcentrados = true;
						break;
					case COTIZAR_AUTORIZAR:
						this.mostrarCotizacionesAutorizaciones = true;
						break;
					case ORDEN_COMPRA:
						this.mostrarOrdenesCompra = true;
						break;
					case REQUISION:
						this.mostrarRequisiones = true;
						break;
					case PANEL_CONTROL:
						this.mostrarPanelControl = true;
					}
				}
			}
		}
	}

	@Command
	public void showProducts() {
		this.args.put("pageToRender", "/modulos/productos/productos.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void showBuscadorProductos() {
		this.args.put("pageToRender", "/modulos/productos/productosBuscador.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void showProvidersSearch() {
		this.args.put("pageToRender", "/modulos/proveedores/proveedoresBuscador.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void showRequisitions() {
		this.args.put("pageToRender", "/modulos/requisicion/requisicion.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void showRequsicionBuscador() {
		this.args.put("pageToRender", "/modulos/requisicion/requisicionBuscador.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void showConcentrado() {
		this.args.put("pageToRender", "/modulos/requisicion/concentrado.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void showControl() {
		this.args.put("pageToRender", "/modulos/requisicion/control.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void showCotizacion() {
		this.args.put("pageToRender", "/modulos/requisicion/cotizacion.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void showCotizacionBuscador() {
		this.args.put("pageToRender", "/modulos/requisicion/cotizacionBuscador.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void showOrders() {
		this.args.put("pageToRender", "/modulos/ordenCompra/ordenCompra.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void showReports() {
		this.args.put("pageToRender", "/modulos/reportes/reportes.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void showControlPanel() {
		this.args.put("pageToRender", "/modulos/controlPanel/controlPanel.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void mostrarConfiguracionUsuario() {
		this.args.put("pageToRender", "/modulos/controlPanel/usuario.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	@Command
	public void configurarUsuariosNegocio() {
		this.args.put("pageToRender", "/modulos/controlPanel/usuariosCliente.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", this.args);
	}

	public boolean isMostrarRequisiones() {
		return this.mostrarRequisiones;
	}

	public void setMostrarRequisiones(boolean mostrarRequisiones) {
		this.mostrarRequisiones = mostrarRequisiones;
	}

	public boolean isMostrarConcentrados() {
		return this.mostrarConcentrados;
	}

	public void setMostrarConcentrados(boolean mostrarConcentrados) {
		this.mostrarConcentrados = mostrarConcentrados;
	}

	public boolean isMostrarCotizacionesAutorizaciones() {
		return this.mostrarCotizacionesAutorizaciones;
	}

	public void setMostrarCotizacionesAutorizaciones(boolean mostrarCotizacionesAutorizaciones) {
		this.mostrarCotizacionesAutorizaciones = mostrarCotizacionesAutorizaciones;
	}

	public boolean isMostrarOrdenesCompra() {
		return this.mostrarOrdenesCompra;
	}

	public void setMostrarOrdenesCompra(boolean mostrarOrdenesCompra) {
		this.mostrarOrdenesCompra = mostrarOrdenesCompra;
	}

	public boolean isMostrarPanelControl() {
		return this.mostrarPanelControl;
	}

	public void setMostrarPanelControl(boolean mostrarPanelControl) {
		this.mostrarPanelControl = mostrarPanelControl;
	}

	public boolean isOwnerOptions() {
		return this.ownerOptions;
	}

	public void setOwnerOptions(boolean ownerOptions) {
		this.ownerOptions = ownerOptions;
	}

	public boolean isClientOptions() {
		return this.clientOptions;
	}

	public void setClientOptions(boolean clientOptions) {
		this.clientOptions = clientOptions;
	}
}
