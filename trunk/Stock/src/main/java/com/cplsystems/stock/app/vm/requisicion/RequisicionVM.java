/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.RequisicionProducto;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class RequisicionVM extends RequisicionVariables {

	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	@Init
	public void init() {
		super.init();
		initDefaultValues();
		loadItemsKeys();
	}

	private void initDefaultValues() {
		addNewItemToBill();
	}

	private void loadItemsKeys() {
		productosTemporalModel = productoService.getAllKeys();
		if (productosTemporalModel != null) {
			productosModel = new SimpleListModel<String>(productosTemporalModel);
		}
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange({ "requisicionProductos", "itemsOnList" })
	@Command
	public void addNewItemToBill() {
		requisicionProductos.add(new RequisicionProducto());
		itemsOnList = requisicionProductos.size();
	}

	@NotifyChange({ "requisicionProductos", "itemsOnList", "importeTotal" })
	@Command
	public void removeElementFromBill() {
		if (requisicionProductos != null && !requisicionProductos.isEmpty()) {
			if (requisicionProductoSeleccionado != null) {
				if (requisicionProductos
						.contains(requisicionProductoSeleccionado)) {
					requisicionProductos
							.remove(requisicionProductoSeleccionado);
					itemsOnList = requisicionProductos.size();
					updateTotal();
				}
			}
		}
	}

	@NotifyChange({ "importeTotal", "itemsOnList" })
	@Command
	public void updateTotal() {
		if (requisicionProductos != null) {
			Double total = 0.0;
			for (RequisicionProducto requisicionProducto : requisicionProductos) {
				if (requisicionProducto.getImporte() != null) {
					total += requisicionProducto.getImporte();
				}
			}
			importeTotal = stockUtils.formatCurrency(total);
		}
	}

	@GlobalCommand
	public void search(@BindingParam("index)") Integer index) {
		if (index != null) {
			System.err.println(index);
		}
		Map<String, Object> inputParams = new HashMap<String, Object>();
		inputParams.put(REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE,
				"updateRecordFromRequisitionWithSelectedItem");
		Window productoModalView = stockUtils.createModelDialogWithParams(
				StockConstants.MODAL_VIEW_PRODUCTOS, inputParams);
		productoModalView.doModal();
	}

	@GlobalCommand
	@NotifyChange("*")
	public void updateRecordFromRequisitionWithSelectedItem(
			@BindingParam("productoSeleccionado") Producto productoSeleccionado) {
		if (productoSeleccionado != null) {
			System.err.println("actualizar valor de la requisición");
		}
	}

}
