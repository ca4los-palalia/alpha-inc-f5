/**
 * 
 */
package com.cplsystems.stock.app.vm;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProductosVM extends BasicStructure {


	private Window proveedoresModalView;

	@Command
	public void buscarProveedor() {
		proveedoresModalView = stockUtils
				.createModelDialog(StockConstants.MODAL_VIEW_PRODUCTOS);
	}

	@Command
	public void newRecord() {
		System.err.println("generate a new record");

	}

	@Command
	public void deleteRecord() {
		System.err.println("delete a record");
	}

	@Command
	public void saveChanges() {
		System.err.println("save changes that were made");
	}

}
