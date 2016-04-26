package com.cplsystems.stock.app.vm.controlpanel;

import java.io.File;
import java.io.FileNotFoundException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Filedownload;

import com.cplsystems.stock.app.vm.controlpanel.utils.ControlPanelMetaclass;

@VariableResolver({ DelegatingVariableResolver.class })
public class CatalogoLayoutVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;

	@Init
	public void init() {

	}

	@Command
	public void openLayoutAreas() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutArea.xlsx"), false)), null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutBancos() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutBanco.xlsx"), false)), null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutGiros() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutGiro.xlsx"), false)), null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutMonedas() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutMoneda.xlsx"), false)), null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutPosiciones() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(
					new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutPosicion(Puestos).xlsx"), false)), null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutProducto() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutProductos.xlsx"), false)),
					null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutProductoTipo() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutProductoTipo.xlsx"), false)),
					null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutProveedores() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutProveedores.xlsx"), false)),
					null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutUnidades() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutUnidad.xlsx"), false)), null);
		} catch (FileNotFoundException e) {
		}
	}
}
