package com.cplsystems.stock.app.vm.requisicion;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.cplsystems.stock.app.vm.BasicStructure;

@VariableResolver({ DelegatingVariableResolver.class })
public class ControlVM extends BasicStructure {
	
	private static final long serialVersionUID = -5173601216372375544L;

	@Init
	public void init() {
		super.init();
	}	
}
