package com.cplsystems.stock.app.vm.requisicion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.domain.AlmacenEntrada;
import com.cplsystems.stock.domain.AplicacionExterna;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class ControlSalidaVM extends ControlMetaclass {
	private static final long serialVersionUID = -1363727052274326014L;
	
	@Init
	public void init() {
		super.init();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = usuario.getOrganizacion();
	}
	
	
	
	
}
