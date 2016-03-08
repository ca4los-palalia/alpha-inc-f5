package com.cplsystems.stock.app.vm.direccion;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver({ DelegatingVariableResolver.class })
public class DireccionVM {
	@Init
	public void init() {
	}
}
