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
	public void showProviders() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.PROVEEDORES);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);		
		System.out.println("MenuVM.showProviders()");
	}

	@Command
	public void showRequisitions() {
		args.put(PAGE_TO_RENDER, StockConstants.GLOBAL_PAGES.REQUISICION);
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);		
		System.out.println("MenuVM.showRequisitions()");
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

}
