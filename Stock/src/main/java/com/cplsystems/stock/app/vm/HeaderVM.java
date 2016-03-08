package com.cplsystems.stock.app.vm;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Organizacion;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver({ DelegatingVariableResolver.class })
public class HeaderVM extends BasicStructure {
	private static final long serialVersionUID = -1635442587326363484L;
	private String compania;
	private String usuario;
	private int totalTransacciones;
	private Organizacion organizacion;

	@Init
	public void init() {
		this.organizacion = ((Organizacion) this.sessionUtils.getFromSession("FIRMA"));
		if (this.organizacion != null) {
			this.compania = this.organizacion.getNombre();
		}
	}

	@Command
	public void logOut() {
		this.sessionUtils.logOut();
		this.stockUtils.redirect("/login.zul");
	}

	public String getCompania() {
		return this.compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getTotalTransacciones() {
		return this.totalTransacciones;
	}

	public void setTotalTransacciones(int totalTransacciones) {
		this.totalTransacciones = totalTransacciones;
	}
}
