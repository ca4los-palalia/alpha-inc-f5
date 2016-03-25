package com.cplsystems.stock.app.vm.menu;

import org.zkoss.bind.annotation.Init;

public class MenuMetaclass extends MenuVariables {
	private static final long serialVersionUID = 5093877120990395398L;

	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects() {
		PAGE_TO_RENDER = "pageToRender";
	}

	public void initProperties() {

	}

}
