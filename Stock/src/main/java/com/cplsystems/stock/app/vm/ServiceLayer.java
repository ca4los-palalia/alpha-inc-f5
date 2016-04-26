package com.cplsystems.stock.app.vm;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.services.AlmacenEntradaService;
import com.cplsystems.stock.services.AlmacenService;
import com.cplsystems.stock.services.AreaService;
import com.cplsystems.stock.services.BancoService;
import com.cplsystems.stock.services.CalculosCostoService;
import com.cplsystems.stock.services.ClaveArmonizadaService;
import com.cplsystems.stock.services.CodigoBarrasProductoService;
import com.cplsystems.stock.services.CofiaFuenteFinanciamientoService;
import com.cplsystems.stock.services.CofiaPartidaGenericaService;
import com.cplsystems.stock.services.CofiaProgService;
import com.cplsystems.stock.services.CofiaPyService;
import com.cplsystems.stock.services.ContactoService;
import com.cplsystems.stock.services.ContratoService;
import com.cplsystems.stock.services.CostosProductoService;
import com.cplsystems.stock.services.CotizacionInboxService;
import com.cplsystems.stock.services.CotizacionService;
import com.cplsystems.stock.services.CuentasPagoService;
import com.cplsystems.stock.services.DevelopmentToolService;
import com.cplsystems.stock.services.DireccionService;
import com.cplsystems.stock.services.EmailService;
import com.cplsystems.stock.services.EstadoService;
import com.cplsystems.stock.services.EstatusRequisicionService;
import com.cplsystems.stock.services.FamiliasProductoService;
import com.cplsystems.stock.services.GiroService;
import com.cplsystems.stock.services.KardexProveedorService;
import com.cplsystems.stock.services.KardexService;
import com.cplsystems.stock.services.MailService;
import com.cplsystems.stock.services.MonedaService;
import com.cplsystems.stock.services.MunicipioService;
import com.cplsystems.stock.services.OrdenCompraInboxService;
import com.cplsystems.stock.services.OrdenCompraService;
import com.cplsystems.stock.services.OrganizacionService;
import com.cplsystems.stock.services.PaisService;
import com.cplsystems.stock.services.PersonaService;
import com.cplsystems.stock.services.PosicionService;
import com.cplsystems.stock.services.PrivilegioService;
import com.cplsystems.stock.services.ProductoNaturalezaService;
import com.cplsystems.stock.services.ProductoService;
import com.cplsystems.stock.services.ProductoTipoService;
import com.cplsystems.stock.services.ProveedorProductoService;
import com.cplsystems.stock.services.ProveedorService;
import com.cplsystems.stock.services.RequisicionInboxService;
import com.cplsystems.stock.services.RequisicionProductoService;
import com.cplsystems.stock.services.RequisicionService;
import com.cplsystems.stock.services.TelefonoService;
import com.cplsystems.stock.services.UnidadService;
import com.cplsystems.stock.services.UsuarioService;
import org.zkoss.zk.ui.select.annotation.WireVariable;

public abstract class ServiceLayer extends DataLayer {
	private static final long serialVersionUID = 2608753945339387415L;
	@WireVariable
	protected StockUtils stockUtils;
	@WireVariable
	protected PersonaService personaService;
	@WireVariable
	protected SessionUtils sessionUtils;
	@WireVariable
	protected UsuarioService usuarioService;
	@WireVariable
	protected ProductoService productoService;
	@WireVariable
	protected ProductoTipoService productoTipoService;
	@WireVariable
	protected EstadoService estadoService;
	@WireVariable
	protected ProveedorService proveedorService;
	@WireVariable
	protected ContratoService contratoService;
	@WireVariable
	protected PaisService paisService;
	@WireVariable
	protected DireccionService direccionService;
	@WireVariable
	protected MunicipioService municipioService;
	@WireVariable
	protected EmailService emailService;
	@WireVariable
	protected ContactoService contactoService;
	@WireVariable
	protected TelefonoService telefonoService;
	@WireVariable
	protected RequisicionService requisicionService;
	@WireVariable
	protected RequisicionProductoService requisicionProductoService;
	@WireVariable
	protected BancoService bancoService;
	@WireVariable
	protected MonedaService monedaService;
	@WireVariable
	protected CuentasPagoService cuentasPagoService;
	@WireVariable
	protected ProveedorProductoService proveedorProductoService;
	@WireVariable
	protected CotizacionService cotizacionService;
	@WireVariable
	protected PosicionService posicionService;
	@WireVariable
	protected AreaService areaService;
	@WireVariable
	protected UnidadService unidadService;
	@WireVariable
	protected ProductoNaturalezaService productoNaturalezaService;
	@WireVariable
	protected FamiliasProductoService familiasProductoService;
	@WireVariable
	protected CodigoBarrasProductoService codigoBarrasProductoService;
	@WireVariable
	protected CostosProductoService costosProductoService;
	@WireVariable
	protected GiroService giroService;
	@WireVariable
	protected CofiaPartidaGenericaService cofiaPartidaGenericaService;
	@WireVariable
	protected CofiaProgService cofiaProgService;
	@WireVariable
	protected CofiaPyService cofiaPyService;
	@WireVariable
	protected CofiaFuenteFinanciamientoService cofiaFuenteFinanciamientoService;
	@WireVariable
	protected EstatusRequisicionService estatusRequisicionService;
	@WireVariable
	protected OrganizacionService organizacionService;
	@WireVariable
	protected PrivilegioService privilegioService;
	@WireVariable
	protected RequisicionInboxService requisicionInboxService;
	@WireVariable
	protected CotizacionInboxService cotizacionInboxService;
	@WireVariable
	protected OrdenCompraService compraService;
	@WireVariable
	protected OrdenCompraInboxService ordenCompraInboxService;
	@WireVariable
	protected MailService mailService;
	@WireVariable
	protected OrdenCompraService ordenCompraService;
	@WireVariable
	protected ClaveArmonizadaService claveArmonizadaService;
	@WireVariable
	protected DevelopmentToolService developmentToolService;
	@WireVariable
	protected AlmacenService almacenService;
	@WireVariable
	protected AlmacenEntradaService almacenEntradaService;
	@WireVariable
	protected CalculosCostoService calculosCostoService;
	@WireVariable
	protected KardexService kardexService;
	@WireVariable
	protected KardexProveedorService kardexProveedorService;

	public UsuarioService getUsuarioService() {
		return this.usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
}
