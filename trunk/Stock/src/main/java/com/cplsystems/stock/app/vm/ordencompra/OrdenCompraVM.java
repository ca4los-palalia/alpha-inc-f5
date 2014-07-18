/**
 * 
 */
package com.cplsystems.stock.app.vm.ordencompra;

import java.util.ArrayList;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import com.cplsystems.stock.app.vm.ordencompra.utils.OrdenCompraMetaclass;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrdenCompraVM extends OrdenCompraMetaclass {

	private static final long serialVersionUID = 999672890629004080L;

	@Init
	public void init() {
		super.init();
		requisicionProductos = requisicionProductoService.getAllRequisiciones();
		if (requisicionProductos == null) {
			requisicionProductos = new ArrayList<RequisicionProducto>();
		}
		requisiciones = requisicionService.getAll();
		if (requisiciones == null) {
			requisiciones = new ArrayList<Requisicion>();
		}
	}

	@Command
	public void transferirOrdenCompraToFormulario(
			@BindingParam("index") Integer index) {
		if (index != null) {
			OrdenCompraInbox toLoad = ordenesCompraInbox.get(index);
			if (toLoad.getLeido() != null && !toLoad.getLeido()) {
				toLoad.setLeido(true);
				ordenCompraInboxService.save(toLoad);
				toLoad.setIcono(OrdenCompraInbox.LEIDO);
			}
		}

	}

}
