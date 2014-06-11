/**
 * 
 */
package com.cplsystems.stock.app.vm.ordencompra;


import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import com.cplsystems.stock.app.vm.ordencompra.utils.OrdenCompraMetaclass;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrdenCompraVM extends OrdenCompraMetaclass{
	
	private static final long serialVersionUID = 999672890629004080L;

	@Init
	public void init() {
		super.init();
		requisicionProductos =requisicionProductoService.getAllRequisiciones();
		requisiciones = requisicionService.getAll();
	}
}
