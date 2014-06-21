/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

import java.util.Calendar;
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
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.Persona;
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
		
		areas = areaService.getAll();
		requisicion.setPersona(new Persona());
		requisicion.setFecha(Calendar.getInstance());
		posiciones = posicionService.getAll();
		
		loadItemsKeys();
		initDefaultValues();
		
		
		
	}

	private void loadItemsKeys() {
		productosTemporalModel = productoService.getAllKeys();
		if (productosTemporalModel != null) {
			productosModel = new SimpleListModel<String>(productosTemporalModel);
		}
	}
	
	@SuppressWarnings("static-access")
	@Command
	public void saveChanges() {
		
		personaService.save(requisicion.getPersona());
		requisicion.setFecha(Calendar.getInstance());
		requisicion.setStatus("En espera");
		
		if (validateBill()) {
			requisicionService.save(requisicion);
			for (int i = 0; i < requisicionProductos.size(); i++) {
				RequisicionProducto requisicionProducto = requisicionProductos.get(i);
				requisicionProducto.setRequisicion(requisicion);
				requisicionProductoService.save(requisicionProducto);
			}
		}
		
		stockUtils.showSuccessmessage(
				"Una nueva requisicion ha sido creada"
						+ requisicion.getFolio(),
				Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

	private void initDefaultValues() {
		addNewItemToBill();
		String folio = "F" + requisicionService.getUltimoFolio();
		requisicion.setFolio(folio);
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

	@Command
	public void search(@BindingParam("index") Integer index) {
		if (index != null) {
			Map<String, Object> inputParams = new HashMap<String, Object>();
			inputParams.put(REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE,
					"updateRecordFromRequisitionWithSelectedItem");
			Window productoModalView = stockUtils.createModelDialogWithParams(
					StockConstants.MODAL_VIEW_PRODUCTOS, inputParams);
			productoModalView.doModal();
			requisicionProductoSeleccionado = requisicionProductos.get(index
					.intValue());
		}

	}

	@SuppressWarnings("static-access")
	@GlobalCommand
	@NotifyChange("*")
	public void updateRecordFromRequisitionWithSelectedItem(
			@BindingParam("productoSeleccionado") Producto productoSeleccionado) {
		if (productoSeleccionado != null) {
			if (!verifyItemsInRequisition(productoSeleccionado)) {
				if(requisicionProductoSeleccionado == null)
					requisicionProductoSeleccionado = new RequisicionProducto();
				requisicionProductoSeleccionado
						.setProducto(productoSeleccionado);
			} else {
				stockUtils.showSuccessmessage(
						"Ya existe un producto registrado con esta clave",
						Clients.NOTIFICATION_TYPE_WARNING, 4000, null);
			}
		}
	}

	

	@SuppressWarnings("static-access")
	private boolean validateBill() {
		boolean continuar = true;
		for (RequisicionProducto requisicionProducto : requisicionProductos) {
			if (!verifyItemsInRequisition(requisicionProducto.getProducto())) {
				stockUtils.showSuccessmessage(
						"Ya existe un producto registrado con esta clave "
								+ requisicionProducto.getProducto().getClave(),
						Clients.NOTIFICATION_TYPE_WARNING, 4000, null);
				continuar = true;
				break;
			}
		}
		return continuar;
	}
	
	private boolean verifyItemsInRequisition(Producto productoSeleccionado) {
		for (RequisicionProducto requisicionProducto : requisicionProductos) {
			if (requisicionProducto.getProducto().getIdProducto() != null
					&& requisicionProducto.getProducto().getClave() != null
					&& requisicionProducto.getProducto().getClave()
							.equalsIgnoreCase(productoSeleccionado.getClave())) {
				return true;
			}
		}
		return false;
	}

}
