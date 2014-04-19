/**
 * 
 */
package com.cplsystems.stock.app.vm.producto;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.BasicStructure;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProductosVM extends BasicStructure {

	private static final long serialVersionUID = 2584088569805199520L;
	private Window proveedoresModalView;

	@Init
	public void init() {
		super.init();
	}

	@Command
	@Override
	public void newRecord() {
		productoService.save(producto);
		System.err.println("generate a new record");

	}

	@Command
	@Override
	public void deleteRecord() {
		System.err.println("delete a record");
	}

	@Command
	@Override
	public void saveChanges() {
		System.err.println("save changes that were made");
	}

	@Override
	public void performSerch() {
		proveedoresModalView = stockUtils
				.createModelDialog(StockConstants.MODAL_VIEW_PRODUCTOS);
	}
}
