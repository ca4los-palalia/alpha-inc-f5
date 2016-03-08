package com.cplsystems.stock.app.vm.requisicion;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Cotizacion;
import java.io.PrintStream;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver({ DelegatingVariableResolver.class })
public class ConfigurarEnvioProductosVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#envioModalDialog")
	private Window envioModalDialog;
	private List<Cotizacion> cotizacionesConProductos;
	private List<Area> areas;
	private Area areaSelected;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("listaKey") List<Cotizacion> listaCotizaciones,
			@ExecutionArgParam("areasKey") List<Area> listaAreas) {
		Selectors.wireComponents(view, this, false);
		this.cotizacionesConProductos = listaCotizaciones;
		this.areas = listaAreas;
		this.areaSelected = new Area();
		if ((this.areas != null) && (this.areas.size() > 0)) {
			this.areaSelected = ((Area) this.areas.get(0));
		}
	}

	public List<Cotizacion> getCotizacionesConProductos() {
		return this.cotizacionesConProductos;
	}

	public void setCotizacionesConProductos(List<Cotizacion> cotizacionesConProductos) {
		this.cotizacionesConProductos = cotizacionesConProductos;
	}

	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public Area getAreaSelected() {
		return this.areaSelected;
	}

	public void setAreaSelected(Area areaSelected) {
		this.areaSelected = areaSelected;
	}

	@Command
	public void listenerCombo() {
		System.err.println();
	}
}
