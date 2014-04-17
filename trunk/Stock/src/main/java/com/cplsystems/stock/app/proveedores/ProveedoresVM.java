/**
 * 
 */
package com.cplsystems.stock.app.proveedores;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import com.cplsystems.stock.domain.Estado;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProveedoresVM extends ProveedorVariables {

	
	/*
	@Autowired
	private Button resetButton;
	static String[] dictionary = { "abacus", "abase", "abate", "abbess", "abbey"};*/
	
	@Command
    public void guardarProveedor(){
		//proveedorService.getById(idProveedor)
		Estado estado = estadoService.getById(1L);
		System.err.println(nombreProveedor + " " + estado.getNombre());
    }
	
	/*
	public static String[] getDirectory() {
		return dictionary;
	}*/
}
