package com.cplsystems.stock.app.vm.reportes;

import com.cplsystems.stock.services.ProveedorService;
import java.net.URL;
import java.util.Collection;
import org.zkoss.bind.annotation.Init;

public abstract class ReporteMetaClass extends ReporteVariables {
	private static final long serialVersionUID = -2648381799748071319L;

	@Init
	public void init() {
	}

	public Collection findReportData() {
		this.proveedoresLista = this.proveedorService.getAll();
		return this.proveedoresLista;
	}

	public String generarUrlString(String phat) {
		URL ruta = getClass().getClassLoader().getResource(phat);
		phat = ruta.getPath();
		return phat;
	}
}
