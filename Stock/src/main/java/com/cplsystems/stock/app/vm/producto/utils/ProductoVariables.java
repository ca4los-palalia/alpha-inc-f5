package com.cplsystems.stock.app.vm.producto.utils;

import com.cplsystems.stock.app.utils.ClasificacionPrecios;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.app.vm.producto.TabInfo;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.NotifyChange;

public abstract class ProductoVariables extends BasicStructure {
	private static final long serialVersionUID = -6626407102060983517L;
	protected String claveProducto;
	protected String nombreProducto;
	protected boolean hiddeFamilia;
	protected boolean hiddeProveedor;
	protected List<Producto> productoDB;
	protected List<Cotizacion> cotizacionDB;
	protected Producto productoSeleccionado;
	protected List<ClasificacionPrecios> precios;
	protected List<FuncionesModificacion> funcionesModificaciones;
	protected FuncionesModificacion funcionesModificacione;
	protected ClasificacionPrecios precioSelected;
	private Date costoMaximoFechaDate;
	private Date costoReposicionFechaDate;
	private Date costoCapaFechaDate;
	private Date costoUltimoFechaDate;
	protected boolean enableComboBoxUnidades;
	protected ModoDeBusqueda modoDeBusqueda;
	protected Integer progressValue;
	protected String progressValueLabel;
	protected TabInfo tabSelected;
	protected Validator productoValidator;
	protected Organizacion organizacion;

	public String getClaveProducto() {
		return this.claveProducto;
	}

	public void setClaveProducto(String claveProducto) {
		this.claveProducto = claveProducto;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public List<Producto> getProductoDB() {
		return this.productoDB;
	}

	public void setProductoDB(List<Producto> productoDB) {
		this.productoDB = productoDB;
	}

	public Producto getProductoSeleccionado() {
		return this.productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public Validator getProductoValidator() {
		return this.productoValidator;
	}

	public List<ProductoTipo> getProductoTipoDB() {
		return this.productoTipoDB;
	}

	public void setProductoTipoDB(List<ProductoTipo> productoTipoDB) {
		this.productoTipoDB = productoTipoDB;
	}

	public void setTabSelected(TabInfo tabSelected) {
		this.tabSelected = tabSelected;
	}

	@NotifyChange({ "*" })
	public TabInfo getTabSelected() {
		return this.tabSelected;
	}

	public List<Cotizacion> getCotizacionDB() {
		return this.cotizacionDB;
	}

	public void setCotizacionDB(List<Cotizacion> cotizacionDB) {
		this.cotizacionDB = cotizacionDB;
	}

	public List<ClasificacionPrecios> getPrecios() {
		return this.precios;
	}

	public void setPrecios(List<ClasificacionPrecios> precios) {
		this.precios = precios;
	}

	public ClasificacionPrecios getPrecioSelected() {
		return this.precioSelected;
	}

	public void setPrecioSelected(ClasificacionPrecios precioSelected) {
		this.precioSelected = precioSelected;
	}

	public Date getCostoMaximoFechaDate() {
		Calendar cal = Calendar.getInstance();
		return this.costoMaximoFechaDate;
	}

	public void setCostoMaximoFechaDate(Date costoMaximoFechaDate) {
		if (costoMaximoFechaDate != null) {
			Calendar fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(costoMaximoFechaDate);
			this.producto.setCostoMaximoFecha(fechaCalendar);
		}
		this.costoMaximoFechaDate = costoMaximoFechaDate;
	}

	public Date getCostoReposicionFechaDate() {
		Calendar cal = Calendar.getInstance();
		return this.costoReposicionFechaDate;
	}

	public void setCostoReposicionFechaDate(Date costoReposicionFechaDate) {
		if (costoReposicionFechaDate != null) {
			Calendar fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(costoReposicionFechaDate);
			this.producto.setCostoReposicionFecha(fechaCalendar);
		}
		this.costoReposicionFechaDate = costoReposicionFechaDate;
	}

	public Date getCostoCapaFechaDate() {
		return this.costoCapaFechaDate;
	}

	public void setCostoCapaFechaDate(Date costoCapaFechaDate) {
		if (costoCapaFechaDate != null) {
			Calendar fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(costoCapaFechaDate);
			this.producto.setCostoCapaFecha(fechaCalendar);
		}
		this.costoCapaFechaDate = costoCapaFechaDate;
	}

	public Date getCostoUltimoFechaDate() {
		return this.costoUltimoFechaDate;
	}

	public void setCostoUltimoFechaDate(Date costoUltimoFechaDate) {
		if (costoUltimoFechaDate != null) {
			Calendar fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(costoUltimoFechaDate);
			this.producto.setCostoUltimoFecha(fechaCalendar);
		}
		this.costoUltimoFechaDate = costoUltimoFechaDate;
	}

	public boolean isEnableComboBoxUnidades() {
		return this.enableComboBoxUnidades;
	}

	public void setEnableComboBoxUnidades(boolean enableComboBoxUnidades) {
		this.enableComboBoxUnidades = enableComboBoxUnidades;
	}

	public boolean isHiddeFamilia() {
		return this.hiddeFamilia;
	}

	public void setHiddeFamilia(boolean hiddeFamilia) {
		this.hiddeFamilia = hiddeFamilia;
	}

	public boolean isHiddeProveedor() {
		return this.hiddeProveedor;
	}

	public void setHiddeProveedor(boolean hiddeProveedor) {
		this.hiddeProveedor = hiddeProveedor;
	}

	public List<FuncionesModificacion> getFuncionesModificaciones() {
		return this.funcionesModificaciones;
	}

	public void setFuncionesModificaciones(List<FuncionesModificacion> funcionesModificaciones) {
		this.funcionesModificaciones = funcionesModificaciones;
	}

	public FuncionesModificacion getFuncionesModificacione() {
		return this.funcionesModificacione;
	}

	public void setFuncionesModificacione(FuncionesModificacion funcionesModificacione) {
		this.funcionesModificacione = funcionesModificacione;
	}

	public ModoDeBusqueda getModoDeBusqueda() {
		return this.modoDeBusqueda;
	}

	public void setModoDeBusqueda(ModoDeBusqueda modoDeBusqueda) {
		this.modoDeBusqueda = modoDeBusqueda;
	}

	public Integer getProgressValue() {
		return this.progressValue;
	}

	public void setProgressValue(Integer progressValue) {
		this.progressValue = progressValue;
	}

	public String getProgressValueLabel() {
		return this.progressValueLabel;
	}

	public void setProgressValueLabel(String progressValueLabel) {
		this.progressValueLabel = progressValueLabel;
	}
}
