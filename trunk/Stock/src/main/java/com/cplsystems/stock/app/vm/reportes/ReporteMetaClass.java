/**
 * 
 */
package com.cplsystems.stock.app.vm.reportes;

import java.net.URL;
import java.util.Collection;

import org.zkoss.bind.annotation.Init;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 *
 */
public abstract class ReporteMetaClass extends ReporteVariables{

	private static final long serialVersionUID = -2648381799748071319L;

	@Init
	public void init(){
		
	}
	
	@SuppressWarnings("rawtypes")
	public Collection findReportData() {
		proveedoresLista = proveedorService.getAll();
		return proveedoresLista;
	}

	public String generarUrlString(String phat) {
		URL ruta = getClass().getClassLoader().getResource(phat);
		phat = ruta.getPath();
		return phat;
	}
}
