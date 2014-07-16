/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion.bandeja;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionBandejaVariables;
import com.cplsystems.stock.domain.Requisicion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class RequisicionBandejaVM extends RequisicionBandejaVariables {

	private static final long serialVersionUID = 3854968949208776845L;

	@Init
	public void init() {
		super.init();
	}

	@Command
	public void transferirRequsicionToFormular(
			@BindingParam("index") Integer index) {
		if (index != null) {
			Requisicion toTransfer = requisiciones.get(index);
			toTransfer.setBuscarRequisicion(toTransfer.getFolio());
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("requisicion", toTransfer);
			BindUtils.postGlobalCommand(null, null, "loadRequisicionFromInbox",
					args);
			stockUtils.redirect(StockConstants.GLOBAL_PAGES.REQUISICION);
		}
	}

}
