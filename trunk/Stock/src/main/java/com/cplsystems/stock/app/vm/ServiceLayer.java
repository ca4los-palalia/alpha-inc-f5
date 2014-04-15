/**
 * 
 */
package com.cplsystems.stock.app.vm;

import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.services.PersonaService;
import com.cplsystems.stock.services.UsuarioService;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public abstract class ServiceLayer {

	@WireVariable
	protected StockUtils stockUtils;

	@WireVariable
	protected PersonaService personaService;

	@WireVariable
	protected SessionUtils sessionUtils;

	@WireVariable
	protected UsuarioService usuarioService;
	
}
