/**
 * 
 */
package com.cplsystems.stock.app.vm.menu;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.BasicStructure;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MenuVM extends BasicStructure {
	public static final String PAGE_TO_RENDER = "pageToRender";
	private Map<String, Object> args;
	
	@Init
	public void init() {
		args = new HashMap<String, Object>();
	}
	
	@Command
	public void showProducts() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
		System.out.println("MenuVM.showProducts()");
	}
	
	@Command
	public void showBuscadorProductos() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_BUSCADOR);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}
	
	@Command
	public void showProductsReportesArticulos() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_ARTICULOS);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}
	
	@Command
	public void showProductsReportesClasificacion() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_CLASIFICACION);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
		System.out.println("MenuVM.showProductsReportesArticulos()");
	}
	
	@Command
	public void showProductsReportesCodigos() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_MULTIPLES_CODIGOS);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
		System.out.println("MenuVM.showProductsReportesArticulos()");
	}
	
	@Command
	public void showProductsReportesCosto() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_COSTO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}
	
	@Command
	public void showProductsReportesPrecio() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_PRECIO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}
	
	@Command
	public void showProductsReportesPrecioCostoMasiva() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_PRECIO_COSTO_MASIVA);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}
	
	@Command
	public void showProductsReportesActualizacionRapida() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_ACTUALIZACION_RAPIDA);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
		System.out.println("MenuVM.showProductsReportesArticulos()");
	}
	@Command
	public void showProductsReportesAjusteExistencia() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_AJUSTE_EXISTENCIA);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
		System.out.println("MenuVM.showProductsReportesArticulos()");
	}
	@Command
	public void showProductsReportesArticuloSustituto() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_ARTICULO_SUSTITUTO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
		System.out.println("MenuVM.showProductsReportesArticulos()");
	}
	@Command
	public void showProductsReportesTipoMovimiento() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_TIPO_MOVIMIENTO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
		System.out.println("MenuVM.showProductsReportesArticulos()");
	}
	@Command
	public void showProductsReportesRregistroMovimiento() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_REGISTRO_MOVIMIENTO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}
	@Command
	public void showProductsReportesKardex() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PRODUCTOS_REPORTE_KARDEX);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}
	
	@Command
	public void showProviders() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PROVEEDORES);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);		
	}
	
	@Command
	public void showProvidersSearch() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PROVEEDORES_BUSCADOR);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);		
	}
	
	@Command
	public void showProvidersProduct() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PROVEEDORES_PRODUCTO);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);		
	}
	
	@Command
	public void showRequisitions() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.REQUISICION);
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
	public void showOrders() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.ORDERS);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);		
		System.out.println("MenuVM.showOrders()");
	}

	@Command
	public void showReports() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.REPORTS);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);		
		System.out.println("MenuVM.showReports()");
	}

	@Command
	public void showControlPanel() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.CONTROL_PANEL);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);		
		System.out.println("MenuVM.showControlPanel()");
	}
	
	@Command
	public void showDemo() {
				
		System.out.println("Demos");
	}

}
