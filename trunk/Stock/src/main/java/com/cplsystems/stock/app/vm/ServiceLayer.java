/**
 * 
 */
package com.cplsystems.stock.app.vm;

import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.services.EstadoService;
import com.cplsystems.stock.services.PersonaService;
import com.cplsystems.stock.services.ProveedorService;
import com.cplsystems.stock.services.ProductoService;
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
	protected EstadoService estadoService;
	
	@WireVariable
	protected ProveedorService proveedorService;
		
	
}
