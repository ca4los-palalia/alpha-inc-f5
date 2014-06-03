/**
 * 
 */
package com.cplsystems.stock.app.vm;

import java.io.Serializable;

import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.services.AreaService;
import com.cplsystems.stock.services.BancoService;
import com.cplsystems.stock.services.ContactoService;
import com.cplsystems.stock.services.ContratoService;
import com.cplsystems.stock.services.CotizacionService;
import com.cplsystems.stock.services.CuentasPagoService;
import com.cplsystems.stock.services.DireccionService;
import com.cplsystems.stock.services.EmailService;
import com.cplsystems.stock.services.EstadoService;
import com.cplsystems.stock.services.MonedaService;
import com.cplsystems.stock.services.MunicipioService;
import com.cplsystems.stock.services.PaisService;
import com.cplsystems.stock.services.PersonaService;
import com.cplsystems.stock.services.PosicionService;
import com.cplsystems.stock.services.ProductoTipoService;
import com.cplsystems.stock.services.ProveedorProductoService;
import com.cplsystems.stock.services.ProveedorService;
import com.cplsystems.stock.services.ProductoService;
import com.cplsystems.stock.services.RequisicionProductoService;
import com.cplsystems.stock.services.RequisicionService;
import com.cplsystems.stock.services.TelefonoService;
import com.cplsystems.stock.services.UsuarioService;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
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
}
