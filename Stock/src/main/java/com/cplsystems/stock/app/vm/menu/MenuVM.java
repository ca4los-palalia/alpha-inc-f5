/**
 * 
 */
package com.cplsystems.stock.app.vm.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MenuVM extends BasicStructure {
	public static final String PAGE_TO_RENDER = "pageToRender";
	private Map<String, Object> args;
	private Usuarios usuario;

	private boolean mostrarRequisiones;
	private boolean mostrarConcentrados;
	private boolean mostrarCotizacionesAutorizaciones;
	private boolean mostrarOrdenesCompra;

	private boolean mostrarPanelControl;
	private boolean mostrarProductos;
	private boolean mostrarProveedores;

	private boolean ownerOptions;

	@Init
	public void init() {
		args = new HashMap<String, Object>();
		loadPrivileges();
	}

	@NotifyChange({ "mostrarPanelControl", "mostrarProveedores",
			"mostrarProductos", "mostrarConcentrados",
			"mostrarCotizacionesAutorizaciones", "mostrarOrdenesCompra",
			"mostrarRequisiones" })
	public void loadPrivileges() {
		usuario = (Usuarios) sessionUtils.getFromSession(SessionUtils.USUARIO);
		if (usuario != null) {
			if (usuario.getClient() != null && usuario.getClient()) {
				mostrarConcentrados = true;
				mostrarCotizacionesAutorizaciones = true;
				mostrarOrdenesCompra = true;
				mostrarRequisiones = true;
				mostrarProductos = true;
				mostrarProveedores = true;
				mostrarPanelControl = true;
				ownerOptions = false;
				return;
			}
			if (usuario.getOwner() != null && usuario.getOwner()) {
				mostrarConcentrados = true;
				mostrarCotizacionesAutorizaciones = true;
				mostrarOrdenesCompra = true;
				mostrarRequisiones = true;
				mostrarProductos = true;
				mostrarProveedores = true;
				mostrarPanelControl = true;
				ownerOptions = true;
				return;
			}
			List<Privilegios> privilegios = privilegioService
					.getPrivilegiosByUsuario(usuario);
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
					case PRODUCTOS:
						mostrarProductos = true;
						break;
					case PROVEEDORES:
						mostrarProveedores = true;
						break;
					case PANEL_CONTROL:
						mostrarPanelControl = true;
						break;
					}
				}
			}
		}
	}

	@Command
	public void showProducts() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showBuscadorProductos() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_BUSCADOR);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProductsReportesArticulos() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_ARTICULOS);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProductsReportesClasificacion() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_CLASIFICACION);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProductsReportesCodigos() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_MULTIPLES_CODIGOS);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProductsReportesCosto() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_COSTO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProductsReportesPrecio() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_PRECIO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProductsReportesPrecioCostoMasiva() {
		args.put(
				PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_PRECIO_COSTO_MASIVA);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProductsReportesActualizacionRapida() {
		args.put(
				PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_ACTUALIZACION_RAPIDA);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProductsReportesAjusteExistencia() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_AJUSTE_EXISTENCIA);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProductsReportesArticuloSustituto() {
		args.put(
				PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_ARTICULO_SUSTITUTO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProductsReportesTipoMovimiento() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_TIPO_MOVIMIENTO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProductsReportesRregistroMovimiento() {
		args.put(
				PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_REGISTRO_MOVIMIENTO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProductsReportesKardex() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_KARDEX);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProviders() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PROVEEDORES);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProvidersSearch() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PROVEEDORES_BUSCADOR);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showProvidersProduct() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.PROVEEDORES_PRODUCTO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showRequisitions() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.REQUISICION);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showRequsicionBuscador() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.REQUISICION_BUSCADOR);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showConcentrado() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.CONCENTRADO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showCotizacion() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.COTIZACION);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showCotizacionBuscador() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.COTIZACION_BUSCADOR);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showOrders() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.ORDERS);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showReports() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.REPORTS);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showControlPanel() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.CONTROL_PANEL);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void mostrarConfiguracionUsuario() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.CONTROL_PANEL_COFIGURACION_USUARIO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void configurarUsuariosNegocio() {
		args.put(PAGE_TO_RENDER,
				StockConstants.GLOBAL_PAGES.CONTROL_PANEL_USUARIOS_NEGOCIO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	public boolean isMostrarRequisiones() {
		return mostrarRequisiones;
	}

	public void setMostrarRequisiones(boolean mostrarRequisiones) {
		this.mostrarRequisiones = mostrarRequisiones;
	}

	public boolean isMostrarConcentrados() {
		return mostrarConcentrados;
	}

	public void setMostrarConcentrados(boolean mostrarConcentrados) {
		this.mostrarConcentrados = mostrarConcentrados;
	}

	public boolean isMostrarCotizacionesAutorizaciones() {
		return mostrarCotizacionesAutorizaciones;
	}

	public void setMostrarCotizacionesAutorizaciones(
			boolean mostrarCotizacionesAutorizaciones) {
		this.mostrarCotizacionesAutorizaciones = mostrarCotizacionesAutorizaciones;
	}

	public boolean isMostrarOrdenesCompra() {
		return mostrarOrdenesCompra;
	}

	public void setMostrarOrdenesCompra(boolean mostrarOrdenesCompra) {
		this.mostrarOrdenesCompra = mostrarOrdenesCompra;
	}

	public boolean isMostrarPanelControl() {
		return mostrarPanelControl;
	}

	public void setMostrarPanelControl(boolean mostrarPanelControl) {
		this.mostrarPanelControl = mostrarPanelControl;
	}

	public boolean isMostrarProductos() {
		return mostrarProductos;
	}

	public void setMostrarProductos(boolean mostrarProductos) {
		this.mostrarProductos = mostrarProductos;
	}

	public boolean isMostrarProveedores() {
		return mostrarProveedores;
	}

	public void setMostrarProveedores(boolean mostrarProveedores) {
		this.mostrarProveedores = mostrarProveedores;
	}

	public boolean isOwnerOptions() {
		return ownerOptions;
	}

	public void setOwnerOptions(boolean ownerOptions) {
		this.ownerOptions = ownerOptions;
	}

}
