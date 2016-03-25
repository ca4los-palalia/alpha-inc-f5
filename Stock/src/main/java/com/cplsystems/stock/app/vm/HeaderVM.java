package com.cplsystems.stock.app.vm;

import com.cplsystems.stock.domain.Usuarios;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;

@VariableResolver({ DelegatingVariableResolver.class })
public class HeaderVM extends BasicStructure {
	private static final long serialVersionUID = -1635442587326363484L;
	
	private int totalTransacciones;

	@Init
	public void init() {
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = usuario.getOrganizacion();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void logOut() {

		Messagebox.show("¿Desea terminar su session de trabajo?", "Salir del sistema", 3,
				"z-msgbox z-msgbox-question", new EventListener() {
					public void onEvent(Event event) throws Exception {
						if (((Integer) event.getData()).intValue() == 1) {
							sessionUtils.logOut();
							stockUtils.redirect("/login.zul");
						} else {
							
						}
					}
				});
	}

	public int getTotalTransacciones() {
		return totalTransacciones;
	}

	public void setTotalTransacciones(int totalTransacciones) {
		totalTransacciones = totalTransacciones;
	}
}
