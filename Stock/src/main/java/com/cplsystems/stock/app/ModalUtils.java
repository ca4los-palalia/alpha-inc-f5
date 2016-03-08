package com.cplsystems.stock.app;

import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.services.EstadoService;
import com.cplsystems.stock.services.ProveedorService;
import java.io.PrintStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

public class ModalUtils extends SelectorComposer<Component> {
	private static final long serialVersionUID = 7430264817059488060L;
	@Autowired
	private ProveedorService proveedorService;
	@WireVariable
	private EstadoService estadoService;

	@Listen("onClick = #dirButton")
	public void showModal(Event e) {
		Window window = (Window) Executions.createComponents("/modulos/ShowModalDetails.zul", null, null);

		window.doModal();
	}

	@Listen("onClick = #contactoId")
	public void getInfoormacion(Event e) {
		Estado estado = this.estadoService.getById(Long.valueOf(1L));
		System.err.println(estado.getNombre());
	}
}
