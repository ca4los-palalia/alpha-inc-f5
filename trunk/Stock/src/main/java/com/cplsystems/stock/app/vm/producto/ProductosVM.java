/**
 * 
 */
package com.cplsystems.stock.app.vm.producto;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.producto.utils.ProductoVariables;
import com.cplsystems.stock.domain.Producto;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProductosVM extends ProductoVariables {

	private static final long serialVersionUID = 313977001812349337L;

	@Init
	public void init() {
		super.init();
	}

	@Command
	public void newRecord() {
		if (producto != null && producto.getIdProducto() != null) {
			Messagebox.show(
					"Se han detectado cambios que no han sido confirmados, "
							+ "¿Está seguro de crear un nuevo registro?",
					"Question", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION, new EventListener<Event>() {
						public void onEvent(Event event) throws Exception {
							if (((Integer) event.getData()).intValue() == Messagebox.OK) {
								Map<String, Object> args = new HashMap<String, Object>();
								args.put("productoSeleccionado", new Producto());
								BindUtils.postGlobalCommand(null, null,
										"updateFromSelectedItem", args);
								return;
							}
						}
					});
		}
		if (producto == null) {
			producto = new Producto();
		}
	}

	@Command
	public void deleteRecord() {
		if (producto.getIdProducto() == null) {
			Messagebox.show("El producto no puede ser eliminado "
					+ "ya que se han detectado cambios sin confirmar");
			return;
		}
		Messagebox.show("¿Está seguro de remover este producto?, "
				+ "esta acción es irreversible", "Question", Messagebox.OK
				| Messagebox.CANCEL, Messagebox.QUESTION,
				new EventListener<Event>() {
					public void onEvent(Event event) throws Exception {
						if (((Integer) event.getData()).intValue() == Messagebox.OK) {
							Map<String, Object> args = new HashMap<String, Object>();
							args.put("producto", producto);
							BindUtils.postGlobalCommand(null, null,
									"deleteProduct", args);
						}
					}
				});

	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("producto")
	public void saveChanges() {
		productoService.save(producto);
		stockUtils.showSuccessmessage("La información del producto " + producto.getNombre()
				+ " se ha actualizado correctamente", Clients.NOTIFICATION_TYPE_INFO, 3000);
		producto = new Producto();
	}

	@Command
	public void search() {
		Window productoModalView = stockUtils
				.createModelDialog(StockConstants.MODAL_VIEW_PRODUCTOS);
		productoModalView.doModal();
	}

	@GlobalCommand
	@NotifyChange("producto")
	public void deleteProduct(@BindingParam("producto") Producto producto) {
		if (producto != null) {
			productoService.delete(producto);
			newRecord();
		}
	}

	@GlobalCommand
	@NotifyChange("producto")
	public void updateFromSelectedItem(
			@BindingParam("productoSeleccionado") Producto productoSeleccionado) {
		if (productoSeleccionado != null) {
			producto = productoSeleccionado;
		}
	}

}
