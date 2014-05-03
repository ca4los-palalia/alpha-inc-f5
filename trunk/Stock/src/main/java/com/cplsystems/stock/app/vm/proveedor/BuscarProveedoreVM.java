/**
 * 
 */
package com.cplsystems.stock.app.vm.proveedor;


import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class BuscarProveedoreVM extends ProveedorMetaClass {

	private static final long serialVersionUID = -4963362932578502507L;

	@Init
	public void init(){
		
	}
	
	
	@Command
	@NotifyChange("*")
	public void mostrarTodosLosProveedores(){
		
	}
	
	
}
