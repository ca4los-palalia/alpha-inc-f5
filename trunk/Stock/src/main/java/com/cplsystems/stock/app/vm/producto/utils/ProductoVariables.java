/**
 * 
 */
package com.cplsystems.stock.app.vm.producto.utils;

import java.util.List;

import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.NotifyChange;

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
	protected List<Producto> productoDB;
	protected List<Cotizacion> cotizacionDB;
	protected Producto productoSeleccionado;
	protected List<ClasificacionPrecios> precios;
	protected ClasificacionPrecios precioSelected;
	
	
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
	
}
