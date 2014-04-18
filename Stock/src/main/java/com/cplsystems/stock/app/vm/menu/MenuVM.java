/**
 * 
 */
package com.cplsystems.stock.app.vm.menu;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Messagebox;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Usuario;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MenuVM extends BasicStructure {

	private String user;
	private String password;

}
