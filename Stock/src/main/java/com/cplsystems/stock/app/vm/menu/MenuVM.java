package com.cplsystems.stock.app.vm.menu;

import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class MenuVM extends MenuMetaclass {
	private static final long serialVersionUID = -2153432633385920494L;
	private static final String PROCESSING_TEXT = "Processing...";

	@Wire("#busyWin")
	private Window busyWin;

	@Wire("#server")
	private Groupbox server;

	@Wire("#timer")
	private Timer timer;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		super.init();
		args = new HashMap();
		loadPrivileges();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = usuario.getOrganizacion();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange({ "mostrarPanelControl", "mostrarConcentrados", "mostrarCotizacionesAutorizaciones",
			"mostrarOrdenesCompra", "mostrarRequisiones" })
	public void loadPrivileges() {
		usuario = ((Usuarios) sessionUtils.getFromSession("usuario"));
		if (usuario != null) {
			if ((usuario.getClient() != null) && (usuario.getClient().booleanValue())) {
				mostrarConcentrados = true;
				mostrarCotizacionesAutorizaciones = true;
				mostrarOrdenesCompra = true;
				mostrarRequisiones = true;
				mostrarPanelControl = true;
				ownerOptions = false;
				clientOptions = true;
				return;
			}
			if ((usuario.getOwner() != null) && (usuario.getOwner().booleanValue())) {
				mostrarConcentrados = true;
				mostrarCotizacionesAutorizaciones = true;
				mostrarOrdenesCompra = true;
				mostrarRequisiones = true;
				mostrarPanelControl = true;
				ownerOptions = true;
				clientOptions = true;
				return;
			}
			List<Privilegios> privilegios = privilegioService.getPrivilegiosByUsuario(usuario);
			if (privilegios != null) {
				for (Privilegios privilegio : privilegios) {
					switch (privilegio.getUserPrivileges()) {
					case CONCENTRAR:
						mostrarConcentrados = true;
						break;
					case COTIZAR_AUTORIZAR:
						mostrarCotizacionesAutorizaciones = true;
						break;
					case ORDEN_COMPRA:
						mostrarOrdenesCompra = true;
						break;
					case REQUISION:
						mostrarRequisiones = true;
						break;
					case PANEL_CONTROL:
						mostrarPanelControl = true;
					}
				}
			}
		}
	}

	@Command
	public void showProducts() {
		args.put("pageToRender", "/modulos/productos/productos.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showBuscadorProductos() {
		args.put("pageToRender", "/modulos/productos/productosBuscador.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);

	}

	@Listen("onAddNameEvent = #timer")
	public void processingFiles() {
		timer.start();
	}

	@GlobalCommand
	@NotifyChange({ "*" })
	public void updateRecordFromRequisitionWithSelectedItem(
			@BindingParam("productoSeleccionado") Producto productoSeleccionado) {
		System.err.println("retornando");
	}

	@Command
	public void showProvidersSearch() {
		args.put("pageToRender", "/modulos/proveedores/proveedoresBuscador.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showRequisitions() {
		args.put("pageToRender", "/modulos/requisicion/requisicion.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showRequsicionBuscador() {
		args.put("pageToRender", "/modulos/requisicion/requisicionBuscador.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showConcentrado() {
		args.put("pageToRender", "/modulos/requisicion/concentrado.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showControl() {
		args.put("pageToRender", "/modulos/requisicion/control.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showCotizacion() {
		args.put("pageToRender", "/modulos/requisicion/cotizacion.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showCotizacionBuscador() {
		args.put("pageToRender", "/modulos/requisicion/cotizacionBuscador.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showOrders() {
		args.put("pageToRender", "/modulos/ordenCompra/ordenCompra.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showPerfiles() {
		args.put("pageToRender", "/modulos/perfil/perfil.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showControlPanel() {
		args.put("pageToRender", "/modulos/controlPanel/controlPanel.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void mostrarConfiguracionUsuario() {
		args.put("pageToRender", "/modulos/controlPanel/usuario.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void configurarUsuariosNegocio() {
		args.put("pageToRender", "/modulos/controlPanel/usuariosCliente.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

}
