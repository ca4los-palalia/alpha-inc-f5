package com.cplsystems.stock.app.vm.controlpanel.utils;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.ProductoTipo;
import java.util.List;

public abstract class ControlPanelVariables extends BasicStructure {
	private static final long serialVersionUID = -806765252363100225L;
	protected SelectedTabsControlPanel selectTab;
	protected String mensajeDeCambios;

	public SelectedTabsControlPanel getSelectTab() {
		return this.selectTab;
	}

	public void setSelectTab(SelectedTabsControlPanel selectTab) {
		this.selectTab = selectTab;
	}

	public String getMensajeDeCambios() {
		return this.mensajeDeCambios;
	}

	public void setMensajeDeCambios(String mensajeDeCambios) {
		this.mensajeDeCambios = mensajeDeCambios;
	}

	public List<ProductoTipo> getProductoTipoDB() {
		return this.productoTipoDB;
	}

	public void setProductoTipoDB(List<ProductoTipo> productoTipoDB) {
		this.productoTipoDB = productoTipoDB;
	}
}
