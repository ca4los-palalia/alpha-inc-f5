/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.requisicion.utils.DesgloceTotal;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.RequisicionProducto;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class concentradoVM extends RequisicionVariables {

	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	@Init
	public void init() {
		super.init();
		
		requisiciones = requisicionService.getAll();
		requisicionProductos = requisicionProductoService.getAll();
		Float subtotal = new Float(0.0);
		
		if(requisicionProductos !=null ){
			desgloceTotal = new DesgloceTotal();
			
			for (RequisicionProducto sumar : requisicionProductos) {
				subtotal += sumar.getTotalProductoPorUnidad();
			}

			desgloceTotal.setSubtotal(subtotal);
			desgloceTotal.setIva(subtotal * new Float(0.16));
			desgloceTotal.setTotal((subtotal) + (subtotal * new Float(0.16)));
		}
			
		
		
	}
	@Command
	@NotifyChange("*")
	public void cargarProveedoresProducto(){
		if(requisicionProductoSeleccionado != null){
			if(requisicionProductoSeleccionado.getProducto() != null)
				proveedorProductos = proveedorProductoService.getByProducto(requisicionProductoSeleccionado.getProducto());
			
		}
	}
	
	@SuppressWarnings("static-access")
	@Command
	public void autorizar() {
		
		
	}

	
}
