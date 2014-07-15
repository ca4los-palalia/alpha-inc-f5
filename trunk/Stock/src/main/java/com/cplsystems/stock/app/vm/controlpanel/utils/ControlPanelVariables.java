/**
 * 
 */
package com.cplsystems.stock.app.vm.controlpanel.utils;

import com.cplsystems.stock.app.vm.BasicStructure;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 *
 */
public abstract class ControlPanelVariables extends BasicStructure {

	private static final long serialVersionUID = -806765252363100225L;

	protected SelectedTabsControlPanel selectTab;
	protected String mensajeDeCambios;
	
	public SelectedTabsControlPanel getSelectTab() {
		return selectTab;
	}

	public void setSelectTab(SelectedTabsControlPanel selectTab) {
		this.selectTab = selectTab;
	}

	public String getMensajeDeCambios() {
		return mensajeDeCambios;
	}

	public void setMensajeDeCambios(String mensajeDeCambios) {
		this.mensajeDeCambios = mensajeDeCambios;
	}
	
	
}
