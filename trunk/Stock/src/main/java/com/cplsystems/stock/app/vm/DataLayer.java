/**
 * 
 */
package com.cplsystems.stock.app.vm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.NotifyChange;

import com.cplsystems.stock.app.vm.producto.TabInfo;
import com.cplsystems.stock.app.vm.proveedor.utils.MenuButtonsActivated;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Banco;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.CuentaPago;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Moneda;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.Pais;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Posicion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.Telefono;
import com.cplsystems.stock.domain.Usuario;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class DataLayer implements Serializable {

	private static final long serialVersionUID = -828756372536148348L;
	
	protected Banco bancoSeleccionado;
	protected Contacto contactoContacto;
	protected Contacto contactoProveedor;
	protected Contrato contrato;
	protected CuentaPago cuentaPago;
	protected Direccion direccionProveedor;
	protected Email emailContacto;
	protected Email emailProveedor;
	protected Estado estadoProveedor;
	protected MenuButtonsActivated botonMuenu1;
	protected MenuButtonsActivated botonMuenu2;
	protected MenuButtonsActivated botonMuenu3;
	protected MenuButtonsActivated botonMuenu4;
	protected MenuButtonsActivated botonMuenu5;
	protected MenuButtonsActivated botonMuenu6;
	protected MenuButtonsActivated botonMuenu7;
	protected Moneda monedaSeleccionada;
	protected Municipio municipioProveedor;
	protected Proveedor buscarProveedor;
	protected Proveedor buscarProveedorAsociar;
	protected Proveedor nuevoProveedor;
	protected Pais paisProveedor;
	protected Persona personaContacto;
	protected Producto producto;
	protected Producto buscarProducto;
	protected ProductoTipo productoTipoSelected;
	protected Proveedor proveedorSelected;
	protected Proveedor proveedoresAsociacionSelected;
	protected Persona representanteLegal;
	protected Requisicion requisicion;
	protected Telefono telefonoContacto;
	protected Telefono telefonoProveedor;
	protected Usuario usuarioProveedor;
	protected ProveedorProducto proveedorProducto;
	protected Area area;
	protected Posicion posicion;
	
	protected List<ProductoTipo> productoTipoDB;
	protected List<Proveedor> proveedoresLista;
	protected List<Proveedor> proveedoresAsociacion;
	protected static List<Banco> bancosDB;
	private List<ProductoTipo> productoTipo;
	protected List<Moneda> monedasDB;
	protected List<ProveedorProducto> proveedorProductos;
	
	protected List<Contrato> contratos;
	protected List<Estado> estados;
	protected List<Pais> paises;
	protected List<Municipio> municipios;
	protected List<Producto> productosDB;
	
	protected List<Area> areas;
	protected List<Posicion> posiciones;

	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public List<Proveedor> getProveedoresLista() {
		return proveedoresLista;
	}
	public void setProveedoresLista(List<Proveedor> proveedoresLista) {
		this.proveedoresLista = proveedoresLista;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	public Proveedor getProveedorSelected() {
		
		if(proveedorSelected != null && proveedorSelected.getProveedorProducto() != null){
			List<ProductoTipo> tipo = new ArrayList<ProductoTipo>();
			
			for (ProveedorProducto pp : proveedorSelected.getProveedorProducto()) {
				if(!tipo.contains(pp.getProducto().getProductoTipo()))
					tipo.add(pp.getProducto().getProductoTipo());
			}
			proveedorSelected.setTipoProductos(tipo);
		}
		return proveedorSelected;
	}
	public void setProveedorSelected(Proveedor proveedorSelected) {
		this.proveedorSelected = proveedorSelected;
	}
	public Requisicion getRequisicion() {
		return requisicion;
	}
	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}
	public Municipio getMunicipioProveedor() {
		return municipioProveedor;
	}
	public void setMunicipioProveedor(Municipio municipioProveedor) {
		this.municipioProveedor = municipioProveedor;
	}
	public Estado getEstadoProveedor() {
		return estadoProveedor;
	}
	public void setEstadoProveedor(Estado estadoProveedor) {
		this.estadoProveedor = estadoProveedor;
	}
	public Pais getPaisProveedor() {
		return paisProveedor;
	}
	public void setPaisProveedor(Pais paisProveedor) {
		this.paisProveedor = paisProveedor;
	}
	public Telefono getTelefonoProveedor() {
		return telefonoProveedor;
	}
	public void setTelefonoProveedor(Telefono telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}
	public Email getEmailProveedor() {
		return emailProveedor;
	}
	public void setEmailProveedor(Email emailProveedor) {
		this.emailProveedor = emailProveedor;
	}
	public Contacto getContactoProveedor() {
		return contactoProveedor;
	}
	public void setContactoProveedor(Contacto contactoProveedor) {
		this.contactoProveedor = contactoProveedor;
	}
	public Direccion getDireccionProveedor() {
		return direccionProveedor;
	}
	public void setDireccionProveedor(Direccion direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
	}
	public Proveedor getNuevoProveedor() {
		return nuevoProveedor;
	}
	public void setNuevoProveedor(Proveedor nuevoProveedor) {
		this.nuevoProveedor = nuevoProveedor;
	}
	public Persona getRepresentanteLegal() {
		return representanteLegal;
	}
	public void setRepresentanteLegal(Persona representanteLegal) {
		this.representanteLegal = representanteLegal;
	}
	public Persona getPersonaContacto() {
		return personaContacto;
	}
	public void setPersonaContacto(Persona personaContacto) {
		this.personaContacto = personaContacto;
	}
	public Email getEmailContacto() {
		return emailContacto;
	}
	public void setEmailContacto(Email emailContacto) {
		this.emailContacto = emailContacto;
	}
	public Telefono getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(Telefono telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	public Usuario getUsuarioProveedor() {
		return usuarioProveedor;
	}
	public void setUsuarioProveedor(Usuario usuarioProveedor) {
		this.usuarioProveedor = usuarioProveedor;
	}
	public Contacto getContactoContacto() {
		return contactoContacto;
	}
	public void setContactoContacto(Contacto contactoContacto) {
		this.contactoContacto = contactoContacto;
	}
	public List<ProductoTipo> getProductoTipoDB() {
		return productoTipoDB;
	}
	public void setProductoTipoDB(List<ProductoTipo> productoTipoDB) {
		this.productoTipoDB = productoTipoDB;
	}
	public ProductoTipo getProductoTipoSelected() {
		return productoTipoSelected;
	}
	public void setProductoTipoSelected(ProductoTipo productoTipoSelected) {
		this.productoTipoSelected = productoTipoSelected;
	}
	public Banco getBancoSeleccionado() {
		return bancoSeleccionado;
	}
	public void setBancoSeleccionado(Banco bancoSeleccionado) {
		this.bancoSeleccionado = bancoSeleccionado;
	}
	public Moneda getMonedaSeleccionada() {
		return monedaSeleccionada;
	}
	public void setMonedaSeleccionada(Moneda monedaSeleccionada) {
		this.monedaSeleccionada = monedaSeleccionada;
	}
	public List<Banco> getBancosDB() {
		return bancosDB;
	}
	public void setBancosDB(List<Banco> bancosDB) {
		this.bancosDB = bancosDB;
	}
	public List<Moneda> getMonedasDB() {
		return monedasDB;
	}
	public void setMonedasDB(List<Moneda> monedasDB) {
		this.monedasDB = monedasDB;
	}
	public CuentaPago getCuentaPago() {
		return cuentaPago;
	}
	public void setCuentaPago(CuentaPago cuentaPago) {
		this.cuentaPago = cuentaPago;
	}
	public List<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	public List<Pais> getPaises() {
		return paises;
	}
	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}
	public List<Municipio> getMunicipios() {
		return municipios;
	}
	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}
	public List<Producto> getProductosDB() {
		return productosDB;
	}
	public void setProductosDB(List<Producto> productosDB) {
		this.productosDB = productosDB;
	}
	public List<Proveedor> getProveedoresAsociacion() {
		return proveedoresAsociacion;
	}
	public void setProveedoresAsociacion(List<Proveedor> proveedoresAsociacion) {
		this.proveedoresAsociacion = proveedoresAsociacion;
	}
	public Proveedor getProveedoresAsociacionSelected() {
		return proveedoresAsociacionSelected;
	}
	public void setProveedoresAsociacionSelected(
			Proveedor proveedoresAsociacionSelected) {
		this.proveedoresAsociacionSelected = proveedoresAsociacionSelected;
	}
	public Proveedor getBuscarProveedor() {
		return buscarProveedor;
	}
	public void setBuscarProveedor(Proveedor buscarProveedor) {
		this.buscarProveedor = buscarProveedor;
	}
	public Producto getBuscarProducto() {
		return buscarProducto;
	}
	public void setBuscarProducto(Producto buscarProducto) {
		this.buscarProducto = buscarProducto;
	}
	public ProveedorProducto getProveedorProducto() {
		return proveedorProducto;
	}
	public void setProveedorProducto(ProveedorProducto proveedorProducto) {
		this.proveedorProducto = proveedorProducto;
	}
	public List<ProveedorProducto> getProveedorProductos() {
		return proveedorProductos;
	}
	public void setProveedorProductos(List<ProveedorProducto> proveedorProductos) {
		this.proveedorProductos = proveedorProductos;
	}
	public Proveedor getBuscarProveedorAsociar() {
		return buscarProveedorAsociar;
	}
	public void setBuscarProveedorAsociar(Proveedor buscarProveedorAsociar) {
		this.buscarProveedorAsociar = buscarProveedorAsociar;
	}
	public MenuButtonsActivated getBotonMuenu1() {
		return botonMuenu1;
	}
	public void setBotonMuenu1(MenuButtonsActivated botonMuenu1) {
		this.botonMuenu1 = botonMuenu1;
	}
	public MenuButtonsActivated getBotonMuenu2() {
		return botonMuenu2;
	}
	public void setBotonMuenu2(MenuButtonsActivated botonMuenu2) {
		this.botonMuenu2 = botonMuenu2;
	}
	public MenuButtonsActivated getBotonMuenu3() {
		return botonMuenu3;
	}
	public void setBotonMuenu3(MenuButtonsActivated botonMuenu3) {
		this.botonMuenu3 = botonMuenu3;
	}
	public MenuButtonsActivated getBotonMuenu4() {
		return botonMuenu4;
	}
	public void setBotonMuenu4(MenuButtonsActivated botonMuenu4) {
		this.botonMuenu4 = botonMuenu4;
	}
	public MenuButtonsActivated getBotonMuenu5() {
		return botonMuenu5;
	}
	public void setBotonMuenu5(MenuButtonsActivated botonMuenu5) {
		this.botonMuenu5 = botonMuenu5;
	}
	public MenuButtonsActivated getBotonMuenu6() {
		return botonMuenu6;
	}
	public void setBotonMuenu6(MenuButtonsActivated botonMuenu6) {
		this.botonMuenu6 = botonMuenu6;
	}
	public MenuButtonsActivated getBotonMuenu7() {
		return botonMuenu7;
	}
	public void setBotonMuenu7(MenuButtonsActivated botonMuenu7) {
		this.botonMuenu7 = botonMuenu7;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public Posicion getPosicion() {
		return posicion;
	}
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	public List<Area> getAreas() {
		return areas;
	}
	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}
	public List<Posicion> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<Posicion> posiciones) {
		this.posiciones = posiciones;
	}
	
}