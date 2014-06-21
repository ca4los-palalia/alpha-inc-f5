/**
 * 
 */
package com.cplsystems.stock.app.vm.producto.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.TreeModel;

import com.cplsystems.stock.app.utils.ClasificacionPrecios;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.app.vm.producto.TabInfo;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
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
	
	
	//protected List<TabInfo> tabListClasificacionProductos;
	protected TabInfo tabSelected;
	protected Validator productoValidator;
	
	
	
	public String getClaveProducto() {
		return claveProducto;
	}

	public void setClaveProducto(String claveProducto) {
		this.claveProducto = claveProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public List<Producto> getProductoDB() {
		return productoDB;
	}

	public void setProductoDB(List<Producto> productoDB) {
		this.productoDB = productoDB;
	}

	public Producto getProductoSeleccionado() {
		return productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public Validator getProductoValidator() {
		return productoValidator;
	}
	
	public List<ProductoTipo> getProductoTipoDB() {
		return productoTipoDB;
	}
	
	public void setProductoTipoDB(List<ProductoTipo> productoTipoDB) {
		this.productoTipoDB = productoTipoDB;
	}
	public void setTabSelected(TabInfo tabSelected) {
		this.tabSelected = tabSelected;
	}
	
	@NotifyChange("*")
	public TabInfo getTabSelected() {
		if(tabSelected != null)
			productoDB = productoService.getByTipo(tabSelected.getProductoTipo());
		return tabSelected;
	}

	public List<Cotizacion> getCotizacionDB() {
		return cotizacionDB;
	}

	public void setCotizacionDB(List<Cotizacion> cotizacionDB) {
		this.cotizacionDB = cotizacionDB;
	}

	public List<ClasificacionPrecios> getPrecios() {
		return precios;
	}

	public void setPrecios(List<ClasificacionPrecios> precios) {
		this.precios = precios;
	}

	public ClasificacionPrecios getPrecioSelected() {
		return precioSelected;
	}

	public void setPrecioSelected(ClasificacionPrecios precioSelected) {
		this.precioSelected = precioSelected;
	}

	
	
	
	
	
	
	
	
	
	
	public Date getCostoMaximoFechaDate() {
		Calendar cal=Calendar.getInstance();
		return costoMaximoFechaDate;
	}

	public void setCostoMaximoFechaDate(Date costoMaximoFechaDate) {
		if(costoMaximoFechaDate != null){
			Calendar 
				fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(costoMaximoFechaDate);
			producto.setCostoMaximoFecha(fechaCalendar);
		}	
		this.costoMaximoFechaDate = costoMaximoFechaDate;
	}

	public Date getCostoReposicionFechaDate() {
		Calendar cal=Calendar.getInstance();
		return costoReposicionFechaDate;
	}

	public void setCostoReposicionFechaDate(Date costoReposicionFechaDate) {
		if(costoReposicionFechaDate != null){
			Calendar 
				fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(costoReposicionFechaDate);
			producto.setCostoReposicionFecha(fechaCalendar);
		}
		this.costoReposicionFechaDate = costoReposicionFechaDate;
	}

	public Date getCostoCapaFechaDate() {
		/*Calendar cal=Calendar.getInstance();
		return costoCapaFechaDate = cal.getTime();*/
		return costoCapaFechaDate;
	}

	public void setCostoCapaFechaDate(Date costoCapaFechaDate) {
		if(costoCapaFechaDate != null){
			Calendar 
				fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(costoCapaFechaDate);
			producto.setCostoCapaFecha(fechaCalendar);
		}
		this.costoCapaFechaDate = costoCapaFechaDate;
	}

	public Date getCostoUltimoFechaDate() {
		/*Calendar cal=Calendar.getInstance();
		return costoUltimoFechaDate = cal.getTime();*/
		return costoUltimoFechaDate;
	}

	public void setCostoUltimoFechaDate(Date costoUltimoFechaDate) {
		if(costoUltimoFechaDate != null){
			Calendar 
				fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(costoUltimoFechaDate);
			producto.setCostoUltimoFecha(fechaCalendar);
		}
		this.costoUltimoFechaDate = costoUltimoFechaDate;
	}

	public boolean isEnableComboBoxUnidades() {
		return enableComboBoxUnidades;
	}

	public void setEnableComboBoxUnidades(boolean enableComboBoxUnidades) {
		this.enableComboBoxUnidades = enableComboBoxUnidades;
	}

	public boolean isHiddeFamilia() {
		return hiddeFamilia;
	}

	public void setHiddeFamilia(boolean hiddeFamilia) {
		this.hiddeFamilia = hiddeFamilia;
	}

	public boolean isHiddeProveedor() {
		return hiddeProveedor;
	}

	public void setHiddeProveedor(boolean hiddeProveedor) {
		this.hiddeProveedor = hiddeProveedor;
	}

	public List<FuncionesModificacion> getFuncionesModificaciones() {
		return funcionesModificaciones;
	}

	public void setFuncionesModificaciones(
			List<FuncionesModificacion> funcionesModificaciones) {
		this.funcionesModificaciones = funcionesModificaciones;
	}

	public FuncionesModificacion getFuncionesModificacione() {
		return funcionesModificacione;
	}

	public void setFuncionesModificacione(
			FuncionesModificacion funcionesModificacione) {
		this.funcionesModificacione = funcionesModificacione;
	}
	
}
