package com.cplsystems.stock.app.vm;

import java.util.List;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;

@VariableResolver({ DelegatingVariableResolver.class })
public class HomeVM {
	@Wire("#header")
	private Div header;
	@Wire("#menu")
	private Div menu;
	@Wire("#content")
	private Div content;
	@Wire("#footer")
	private Div footer;

	@Init
	public void init() {
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange({ "*" })
	@GlobalCommand
	public void updateWorkArea(@BindingParam("pageToRender") String pageToRender) {
		List<Component> components = this.content.getChildren();
		if (components != null) {
			components.clear();
			this.content.appendChild(new Include(pageToRender));
		}
	}
}
