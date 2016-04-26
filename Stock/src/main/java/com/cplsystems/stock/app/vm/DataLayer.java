package com.cplsystems.stock.app.vm;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;

import com.cplsystems.stock.app.vm.proveedor.utils.MenuButtonsActivated;
import com.cplsystems.stock.domain.Almacen;
import com.cplsystems.stock.domain.AlmacenEntrada;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Banco;
import com.cplsystems.stock.domain.CalculosCosto;
import com.cplsystems.stock.domain.Kardex;
import com.cplsystems.stock.domain.KardexProveedor;
import com.cplsystems.stock.domain.ClaveArmonizada;
import com.cplsystems.stock.domain.CodigoBarrasProducto;
import com.cplsystems.stock.domain.CofiaFuenteFinanciamiento;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.CofiaProg;
import com.cplsystems.stock.domain.CofiaPy;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.CostosProducto;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.CuentaPago;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.FamiliasProducto;
import com.cplsystems.stock.domain.Giro;
import com.cplsystems.stock.domain.Moneda;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Pais;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Posicion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoNaturaleza;
import com.cplsystems.stock.domain.ProductoTipo;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;
import com.cplsystems.stock.domain.SistemaOperativo;
import com.cplsystems.stock.domain.Telefono;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class DataLayer extends SelectorComposer<Component> implements Serializable  {
	private static final long serialVersionUID = -828756372536148348L;
	protected Banco bancoSeleccionado;
	protected Contacto contactoContacto;
	protected Contacto contactoProveedor;
	protected Cotizacion cotizacionSelected;
	protected Cotizacion cotizacion;
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
	protected MenuButtonsActivated botonMuenu8;
	protected Moneda monedaSeleccionada;
	protected Municipio municipioProveedor;
	protected Pais pais;
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
	protected CofiaPartidaGenerica cofiaPartidaGenerica;
	protected CofiaProg cofiaProg;
	protected CofiaPy cofiaPy;
	protected CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento;
	protected Telefono telefonoContacto;
	protected Telefono telefonoProveedor;
	protected Usuarios usuarioProveedor;
	protected Unidad unidadSelected;
	protected ProveedorProducto proveedorProducto;
	protected RequisicionProducto requisicionProducto;
	protected Area area;
	protected Area areaBuscar;
	protected Unidad unidad;
	protected OrdenCompra ordenCompra;
	protected Posicion posicion;
	protected FamiliasProducto familiasProducto;
	protected ProductoNaturaleza productoNaturaleza;
	protected CodigoBarrasProducto codigoBarrasProducto;
	protected CostosProducto costosProducto;
	protected CostosProducto costosProductoNuevo;
	protected Giro giro;
	protected EstatusRequisicion estatusRequisicion;
	protected SistemaOperativo sistemaOperativo;
	protected ClaveArmonizada claveArmonizada;
	protected HSSFWorkbook libro;
	protected FileInputStream fileInputStream;
	protected FileOutputStream fileOutputStream;
	protected String readJasper;
	protected String readLayoutProductos;
	protected String readLayoutProveedores;
	protected JasperPrint print;
	protected JasperViewer jviewer;
	protected AImage imagenProducto;
	protected List<CofiaFuenteFinanciamiento> cofiaFuenteFinanciamientos;
	protected List<CofiaPartidaGenerica> cofiaPartidaGenericas;
	protected List<CofiaProg> cofiaProgs;
	protected List<CofiaPy> cofiaPys;
	protected List<Cotizacion> cotizacionesList;
	protected List<Cotizacion> cotizacionesConProductos;
	protected List<CostosProducto> costosProductos;
	protected List<CodigoBarrasProducto> codigosBarrasProductos;
	protected List<FamiliasProducto> familiasProductos;
	protected List<ProductoTipo> productoTipoDB;
	protected List<Proveedor> proveedoresLista;
	protected List<Proveedor> proveedoresAsociacion;
	protected List<Banco> bancosDB;
	private List<ProductoTipo> productoTipo;
	protected List<Moneda> monedasDB;
	protected List<ProveedorProducto> proveedorProductos;
	protected List<Unidad> unidadesDB;
	protected List<EstatusRequisicion> estatusRequisiciones;
	protected List<Contrato> contratos;
	protected List<Estado> estados;
	protected List<Pais> paises;
	protected List<Municipio> municipios;
	protected List<Producto> productosDB;
	protected List<RequisicionProducto> requisicionProductos;
	protected List<Requisicion> requisiciones;
	protected List<Area> areas;
	protected List<Posicion> posiciones;
	protected List<ProductoNaturaleza> productosNaturalezas;
	protected List<Giro> giros;
	protected List<OrdenCompra> ordenesCompras;
	protected List<ClaveArmonizada> claveArmonizadaList;
	protected List<Direccion> direccionesList;
	protected List<Almacen> almacenesList;
	protected Almacen almacenSelected;
	protected AlmacenEntrada almacenEntrada;
	//protected List<AlmacenEntrada> almacenEntradaList;
	protected CalculosCosto calculosCosto;
	protected List<CalculosCosto> calculosCostoList;
	protected Usuarios usuario;
	protected Organizacion organizacion;
	protected Kardex kardex;
	protected List<Kardex> kardexList;
	protected KardexProveedor kardexProveedor;
	protected List<KardexProveedor> kardexProveedorList;
	protected EstatusRequisicion estadoKardex;
	
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Proveedor> getProveedoresLista() {
		return this.proveedoresLista;
	}

	public void setProveedoresLista(List<Proveedor> proveedoresLista) {
		this.proveedoresLista = proveedoresLista;
	}

	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Proveedor getProveedorSelected() {
		return this.proveedorSelected;
	}

	public void setProveedorSelected(Proveedor proveedorSelected) {
		this.proveedorSelected = proveedorSelected;
	}

	public Requisicion getRequisicion() {
		return this.requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}

	public Municipio getMunicipioProveedor() {
		return this.municipioProveedor;
	}

	public void setMunicipioProveedor(Municipio municipioProveedor) {
		this.municipioProveedor = municipioProveedor;
	}

	public Estado getEstadoProveedor() {
		return this.estadoProveedor;
	}

	public void setEstadoProveedor(Estado estadoProveedor) {
		this.estadoProveedor = estadoProveedor;
	}

	public Pais getPaisProveedor() {
		return this.paisProveedor;
	}

	public void setPaisProveedor(Pais paisProveedor) {
		this.paisProveedor = paisProveedor;
	}

	public Telefono getTelefonoProveedor() {
		return this.telefonoProveedor;
	}

	public void setTelefonoProveedor(Telefono telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}

	public Email getEmailProveedor() {
		return this.emailProveedor;
	}

	public void setEmailProveedor(Email emailProveedor) {
		this.emailProveedor = emailProveedor;
	}

	public Contacto getContactoProveedor() {
		return this.contactoProveedor;
	}

	public void setContactoProveedor(Contacto contactoProveedor) {
		this.contactoProveedor = contactoProveedor;
	}

	public Direccion getDireccionProveedor() {
		return this.direccionProveedor;
	}

	public void setDireccionProveedor(Direccion direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
	}

	public Proveedor getNuevoProveedor() {
		return this.nuevoProveedor;
	}

	public void setNuevoProveedor(Proveedor nuevoProveedor) {
		this.nuevoProveedor = nuevoProveedor;
	}

	public Persona getRepresentanteLegal() {
		return this.representanteLegal;
	}

	public void setRepresentanteLegal(Persona representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public Persona getPersonaContacto() {
		return this.personaContacto;
	}

	public void setPersonaContacto(Persona personaContacto) {
		this.personaContacto = personaContacto;
	}

	public Email getEmailContacto() {
		return this.emailContacto;
	}

	public void setEmailContacto(Email emailContacto) {
		this.emailContacto = emailContacto;
	}

	public Telefono getTelefonoContacto() {
		return this.telefonoContacto;
	}

	public void setTelefonoContacto(Telefono telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public Usuarios getUsuarioProveedor() {
		return this.usuarioProveedor;
	}

	public void setUsuarioProveedor(Usuarios usuarioProveedor) {
		this.usuarioProveedor = usuarioProveedor;
	}

	public Contacto getContactoContacto() {
		return this.contactoContacto;
	}

	public void setContactoContacto(Contacto contactoContacto) {
		this.contactoContacto = contactoContacto;
	}

	public List<ProductoTipo> getProductoTipoDB() {
		return this.productoTipoDB;
	}

	public void setProductoTipoDB(List<ProductoTipo> productoTipoDB) {
		this.productoTipoDB = productoTipoDB;
	}

	public ProductoTipo getProductoTipoSelected() {
		return this.productoTipoSelected;
	}

	public void setProductoTipoSelected(ProductoTipo productoTipoSelected) {
		this.productoTipoSelected = productoTipoSelected;
	}

	public Banco getBancoSeleccionado() {
		return this.bancoSeleccionado;
	}

	public void setBancoSeleccionado(Banco bancoSeleccionado) {
		this.bancoSeleccionado = bancoSeleccionado;
	}

	public Moneda getMonedaSeleccionada() {
		return this.monedaSeleccionada;
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
		return this.monedasDB;
	}

	public void setMonedasDB(List<Moneda> monedasDB) {
		this.monedasDB = monedasDB;
	}

	public CuentaPago getCuentaPago() {
		return this.cuentaPago;
	}

	public void setCuentaPago(CuentaPago cuentaPago) {
		this.cuentaPago = cuentaPago;
	}

	public List<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public List<Estado> getEstados() {
		return this.estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Pais> getPaises() {
		return this.paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public List<Municipio> getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public List<Producto> getProductosDB() {
		return this.productosDB;
	}

	public void setProductosDB(List<Producto> productosDB) {
		this.productosDB = productosDB;
	}

	public List<Proveedor> getProveedoresAsociacion() {
		return this.proveedoresAsociacion;
	}

	public void setProveedoresAsociacion(List<Proveedor> proveedoresAsociacion) {
		this.proveedoresAsociacion = proveedoresAsociacion;
	}

	public Proveedor getProveedoresAsociacionSelected() {
		return this.proveedoresAsociacionSelected;
	}

	public void setProveedoresAsociacionSelected(Proveedor proveedoresAsociacionSelected) {
		this.proveedoresAsociacionSelected = proveedoresAsociacionSelected;
	}

	public Proveedor getBuscarProveedor() {
		return this.buscarProveedor;
	}

	public void setBuscarProveedor(Proveedor buscarProveedor) {
		this.buscarProveedor = buscarProveedor;
	}

	public Producto getBuscarProducto() {
		return this.buscarProducto;
	}

	public void setBuscarProducto(Producto buscarProducto) {
		this.buscarProducto = buscarProducto;
	}

	public ProveedorProducto getProveedorProducto() {
		return this.proveedorProducto;
	}

	public void setProveedorProducto(ProveedorProducto proveedorProducto) {
		this.proveedorProducto = proveedorProducto;
	}

	public List<ProveedorProducto> getProveedorProductos() {
		return this.proveedorProductos;
	}

	public void setProveedorProductos(List<ProveedorProducto> proveedorProductos) {
		this.proveedorProductos = proveedorProductos;
	}

	public Proveedor getBuscarProveedorAsociar() {
		return this.buscarProveedorAsociar;
	}

	public void setBuscarProveedorAsociar(Proveedor buscarProveedorAsociar) {
		this.buscarProveedorAsociar = buscarProveedorAsociar;
	}

	public MenuButtonsActivated getBotonMuenu1() {
		return this.botonMuenu1;
	}

	public void setBotonMuenu1(MenuButtonsActivated botonMuenu1) {
		this.botonMuenu1 = botonMuenu1;
	}

	public MenuButtonsActivated getBotonMuenu2() {
		return this.botonMuenu2;
	}

	public void setBotonMuenu2(MenuButtonsActivated botonMuenu2) {
		this.botonMuenu2 = botonMuenu2;
	}

	public MenuButtonsActivated getBotonMuenu3() {
		return this.botonMuenu3;
	}

	public void setBotonMuenu3(MenuButtonsActivated botonMuenu3) {
		this.botonMuenu3 = botonMuenu3;
	}

	public MenuButtonsActivated getBotonMuenu4() {
		return this.botonMuenu4;
	}

	public void setBotonMuenu4(MenuButtonsActivated botonMuenu4) {
		this.botonMuenu4 = botonMuenu4;
	}

	public MenuButtonsActivated getBotonMuenu5() {
		return this.botonMuenu5;
	}

	public void setBotonMuenu5(MenuButtonsActivated botonMuenu5) {
		this.botonMuenu5 = botonMuenu5;
	}

	public MenuButtonsActivated getBotonMuenu6() {
		return this.botonMuenu6;
	}

	public void setBotonMuenu6(MenuButtonsActivated botonMuenu6) {
		this.botonMuenu6 = botonMuenu6;
	}

	public MenuButtonsActivated getBotonMuenu7() {
		return this.botonMuenu7;
	}

	public void setBotonMuenu7(MenuButtonsActivated botonMuenu7) {
		this.botonMuenu7 = botonMuenu7;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Posicion getPosicion() {
		return this.posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public List<Posicion> getPosiciones() {
		return this.posiciones;
	}

	public void setPosiciones(List<Posicion> posiciones) {
		this.posiciones = posiciones;
	}

	public MenuButtonsActivated getBotonMuenu8() {
		return this.botonMuenu8;
	}

	public void setBotonMuenu8(MenuButtonsActivated botonMuenu8) {
		this.botonMuenu8 = botonMuenu8;
	}

	public String getReadJasper() {
		return this.readJasper;
	}

	public void setReadJasper(String readJasper) {
		this.readJasper = readJasper;
	}

	public List<ProductoTipo> getProductoTipo() {
		return this.productoTipo;
	}

	public void setProductoTipo(List<ProductoTipo> productoTipo) {
		this.productoTipo = productoTipo;
	}

	public JasperPrint getPrint() {
		return this.print;
	}

	public void setPrint(JasperPrint print) {
		this.print = print;
	}

	public JasperViewer getJviewer() {
		return this.jviewer;
	}

	public void setJviewer(JasperViewer jviewer) {
		this.jviewer = jviewer;
	}

	public List<RequisicionProducto> getRequisicionProductos() {
		return this.requisicionProductos;
	}

	public void setRequisicionProductos(List<RequisicionProducto> requisicionProductos) {
		this.requisicionProductos = requisicionProductos;
	}

	public RequisicionProducto getRequisicionProducto() {
		return this.requisicionProducto;
	}

	public void setRequisicionProducto(RequisicionProducto requisicionProducto) {
		this.requisicionProducto = requisicionProducto;
	}

	public List<Requisicion> getRequisiciones() {
		return this.requisiciones;
	}

	public void setRequisiciones(List<Requisicion> requisiciones) {
		this.requisiciones = requisiciones;
	}

	public Unidad getUnidadSelected() {
		return this.unidadSelected;
	}

	public void setUnidadSelected(Unidad unidadSelected) {
		this.unidadSelected = unidadSelected;
	}

	public List<Unidad> getUnidadesDB() {
		return this.unidadesDB;
	}

	public void setUnidadesDB(List<Unidad> unidadesDB) {
		this.unidadesDB = unidadesDB;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public ProductoNaturaleza getProductoNaturaleza() {
		return this.productoNaturaleza;
	}

	public void setProductoNaturaleza(ProductoNaturaleza productoNaturaleza) {
		this.productoNaturaleza = productoNaturaleza;
	}

	public List<ProductoNaturaleza> getProductosNaturalezas() {
		return this.productosNaturalezas;
	}

	public void setProductosNaturalezas(List<ProductoNaturaleza> productosNaturalezas) {
		this.productosNaturalezas = productosNaturalezas;
	}

	public FamiliasProducto getFamiliasProducto() {
		return this.familiasProducto;
	}

	@NotifyChange({ "producto" })
	public void setFamiliasProducto(FamiliasProducto familiasProducto) {
		if (familiasProducto != null) {
			this.producto = familiasProducto.getProducto();
		}
		this.familiasProducto = familiasProducto;
	}

	public List<FamiliasProducto> getFamiliasProductos() {
		return this.familiasProductos;
	}

	public void setFamiliasProductos(List<FamiliasProducto> familiasProductos) {
		this.familiasProductos = familiasProductos;
	}

	public CodigoBarrasProducto getCodigoBarrasProducto() {
		return this.codigoBarrasProducto;
	}

	public void setCodigoBarrasProducto(CodigoBarrasProducto codigoBarrasProducto) {
		this.codigoBarrasProducto = codigoBarrasProducto;
	}

	public List<CodigoBarrasProducto> getCodigosBarrasProductos() {
		return this.codigosBarrasProductos;
	}

	public void setCodigosBarrasProductos(List<CodigoBarrasProducto> codigosBarrasProductos) {
		this.codigosBarrasProductos = codigosBarrasProductos;
	}

	public CostosProducto getCostosProducto() {
		return this.costosProducto;
	}

	public void setCostosProducto(CostosProducto costosProducto) {
		this.costosProducto = costosProducto;
	}

	public List<CostosProducto> getCostosProductos() {
		return this.costosProductos;
	}

	public void setCostosProductos(List<CostosProducto> costosProductos) {
		this.costosProductos = costosProductos;
	}

	public CostosProducto getCostosProductoNuevo() {
		return this.costosProductoNuevo;
	}

	public void setCostosProductoNuevo(CostosProducto costosProductoNuevo) {
		this.costosProductoNuevo = costosProductoNuevo;
	}

	public AImage getImagenProducto() {
		return this.imagenProducto;
	}

	public void setImagenProducto(AImage imagenProducto) {
		this.imagenProducto = imagenProducto;
	}

	public Giro getGiro() {
		return this.giro;
	}

	public void setGiro(Giro giro) {
		this.giro = giro;
	}

	public List<Giro> getGiros() {
		return this.giros;
	}

	public void setGiros(List<Giro> giros) {
		this.giros = giros;
	}

	public CofiaPartidaGenerica getCofiaPartidaGenerica() {
		return this.cofiaPartidaGenerica;
	}

	public void setCofiaPartidaGenerica(CofiaPartidaGenerica cofiaPartidaGenerica) {
		this.cofiaPartidaGenerica = cofiaPartidaGenerica;
	}

	public CofiaProg getCofiaProg() {
		return this.cofiaProg;
	}

	public void setCofiaProg(CofiaProg cofiaProg) {
		this.cofiaProg = cofiaProg;
	}

	public CofiaPy getCofiaPy() {
		return this.cofiaPy;
	}

	public void setCofiaPy(CofiaPy cofiaPy) {
		this.cofiaPy = cofiaPy;
	}

	public List<CofiaPartidaGenerica> getCofiaPartidaGenericas() {
		return this.cofiaPartidaGenericas;
	}

	public void setCofiaPartidaGenericas(List<CofiaPartidaGenerica> cofiaPartidaGenericas) {
		this.cofiaPartidaGenericas = cofiaPartidaGenericas;
	}

	public List<CofiaProg> getCofiaProgs() {
		return this.cofiaProgs;
	}

	public void setCofiaProgs(List<CofiaProg> cofiaProgs) {
		this.cofiaProgs = cofiaProgs;
	}

	public List<CofiaPy> getCofiaPys() {
		return this.cofiaPys;
	}

	public void setCofiaPys(List<CofiaPy> cofiaPys) {
		this.cofiaPys = cofiaPys;
	}

	public CofiaFuenteFinanciamiento getCofiaFuenteFinanciamiento() {
		return this.cofiaFuenteFinanciamiento;
	}

	public void setCofiaFuenteFinanciamiento(CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento) {
		this.cofiaFuenteFinanciamiento = cofiaFuenteFinanciamiento;
	}

	public List<CofiaFuenteFinanciamiento> getCofiaFuenteFinanciamientos() {
		return this.cofiaFuenteFinanciamientos;
	}

	public void setCofiaFuenteFinanciamientos(List<CofiaFuenteFinanciamiento> cofiaFuenteFinanciamientos) {
		this.cofiaFuenteFinanciamientos = cofiaFuenteFinanciamientos;
	}

	public Area getAreaBuscar() {
		return this.areaBuscar;
	}

	public void setAreaBuscar(Area areaBuscar) {
		this.areaBuscar = areaBuscar;
	}

	public Cotizacion getCotizacionSelected() {
		return this.cotizacionSelected;
	}

	public void setCotizacionSelected(Cotizacion cotizacionSelected) {
		this.cotizacionSelected = cotizacionSelected;
	}

	public List<Cotizacion> getCotizacionesList() {
		return this.cotizacionesList;
	}

	public void setCotizacionesList(List<Cotizacion> cotizacionesList) {
		this.cotizacionesList = cotizacionesList;
	}

	public OrdenCompra getOrdenCompra() {
		return this.ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public List<OrdenCompra> getOrdenesCompras() {
		return this.ordenesCompras;
	}

	public void setOrdenesCompras(List<OrdenCompra> ordenesCompras) {
		this.ordenesCompras = ordenesCompras;
	}

	public List<Cotizacion> getCotizacionesConProductos() {
		return this.cotizacionesConProductos;
	}

	public void setCotizacionesConProductos(List<Cotizacion> cotizacionesConProductos) {
		this.cotizacionesConProductos = cotizacionesConProductos;
	}

	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public ClaveArmonizada getClaveArmonizada() {
		return this.claveArmonizada;
	}

	public void setClaveArmonizada(ClaveArmonizada claveArmonizada) {
		this.claveArmonizada = claveArmonizada;
	}

	public List<ClaveArmonizada> getClaveArmonizadaList() {
		return this.claveArmonizadaList;
	}

	public void setClaveArmonizadaList(List<ClaveArmonizada> claveArmonizadaList) {
		this.claveArmonizadaList = claveArmonizadaList;
	}

	public List<Direccion> getDireccionesList() {
		return direccionesList;
	}

	public void setDireccionesList(List<Direccion> direccionesList) {
		this.direccionesList = direccionesList;
	}

	public EstatusRequisicion getEstatusRequisicion() {
		return estatusRequisicion;
	}

	public void setEstatusRequisicion(EstatusRequisicion estatusRequisicion) {
		this.estatusRequisicion = estatusRequisicion;
	}

	public SistemaOperativo getSistemaOperativo() {
		return sistemaOperativo;
	}

	public void setSistemaOperativo(SistemaOperativo sistemaOperativo) {
		this.sistemaOperativo = sistemaOperativo;
	}

	public HSSFWorkbook getLibro() {
		return libro;
	}

	public void setLibro(HSSFWorkbook libro) {
		this.libro = libro;
	}

	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public FileOutputStream getFileOutputStream() {
		return fileOutputStream;
	}

	public void setFileOutputStream(FileOutputStream fileOutputStream) {
		this.fileOutputStream = fileOutputStream;
	}

	public String getReadLayoutProductos() {
		return readLayoutProductos;
	}

	public void setReadLayoutProductos(String readLayoutProductos) {
		this.readLayoutProductos = readLayoutProductos;
	}

	public String getReadLayoutProveedores() {
		return readLayoutProveedores;
	}

	public void setReadLayoutProveedores(String readLayoutProveedores) {
		this.readLayoutProveedores = readLayoutProveedores;
	}

	public List<EstatusRequisicion> getEstatusRequisiciones() {
		return estatusRequisiciones;
	}

	public void setEstatusRequisiciones(List<EstatusRequisicion> estatusRequisiciones) {
		this.estatusRequisiciones = estatusRequisiciones;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public List<Almacen> getAlmacenesList() {
		return almacenesList;
	}

	public void setAlmacenesList(List<Almacen> almacenesList) {
		this.almacenesList = almacenesList;
	}

	public Almacen getAlmacenSelected() {
		return almacenSelected;
	}

	public void setAlmacenSelected(Almacen almacenSelected) {
		this.almacenSelected = almacenSelected;
	}

	public AlmacenEntrada getAlmacenEntrada() {
		return almacenEntrada;
	}

	public void setAlmacenEntrada(AlmacenEntrada almacenEntrada) {
		this.almacenEntrada = almacenEntrada;
	}
/*
	public List<AlmacenEntrada> getAlmacenEntradaList() {
		return almacenEntradaList;
	}

	public void setAlmacenEntradaList(List<AlmacenEntrada> almacenEntradaList) {
		this.almacenEntradaList = almacenEntradaList;
	}*/

	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public CalculosCosto getCalculosCosto() {
		return calculosCosto;
	}

	public void setCalculosCosto(CalculosCosto calculosCosto) {
		this.calculosCosto = calculosCosto;
	}

	public List<CalculosCosto> getCalculosCostoList() {
		return calculosCostoList;
	}

	public void setCalculosCostoList(List<CalculosCosto> calculosCostoList) {
		this.calculosCostoList = calculosCostoList;
	}

	public Kardex getKardex() {
		return kardex;
	}

	public void setKardex(Kardex kardex) {
		this.kardex = kardex;
	}

	public List<Kardex> getKardexList() {
		return kardexList;
	}

	public void setKardexList(List<Kardex> kardexList) {
		this.kardexList = kardexList;
	}

	public KardexProveedor getKardexProveedor() {
		return kardexProveedor;
	}

	public void setKardexProveedor(KardexProveedor kardexProveedor) {
		this.kardexProveedor = kardexProveedor;
	}

	public List<KardexProveedor> getKardexProveedorList() {
		return kardexProveedorList;
	}

	public void setKardexProveedorList(List<KardexProveedor> kardexProveedorList) {
		this.kardexProveedorList = kardexProveedorList;
	}
	
}
