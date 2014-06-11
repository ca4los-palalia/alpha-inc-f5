/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion.utils;

import java.util.Calendar;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.SimpleListModel;

import com.cplsystems.stock.domain.Persona;
/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class RequisicionMetaclass extends RequisicionVariables {

	private static final long serialVersionUID = 5093877120990395398L;

	//protected List<RequisicionProducto> requisicionProductos;
	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects(){
		
	}
	
	public void initProperties(){
		
	}
	
	
}
