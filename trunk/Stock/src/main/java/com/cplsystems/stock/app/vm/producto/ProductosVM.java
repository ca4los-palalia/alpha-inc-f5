/**
 * 
 */
package com.cplsystems.stock.app.vm.producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;


@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProductosVM extends ProductoVariables {

	private static final long serialVersionUID = 313977001812349337L;
	private int counter = 1;

	@Init
	public void init() {
		super.init();
		productoTipoDB = productoTipoService.getAll();
		productoTipoSelected = new ProductoTipo();
		cotizacionDB = new ArrayList<Cotizacion>();

		// ------------------
		tabListClasificacionProductos = new ArrayList<TabInfo>();
		construirTabsClasificacionProductos(productoTipoDB);
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
		stockUtils.showSuccessmessage(
				"La información del producto " + producto.getNombre()
						+ " se ha actualizado correctamente",
				Clients.NOTIFICATION_TYPE_INFO, 3000);
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

	private void construirTabsClasificacionProductos(
			List<ProductoTipo> clasificacion) {
		if (clasificacion != null) {
			ProductoTipo tipo = null;
			TabInfo tabInfo = null;
			int i = 0;

			for (ProductoTipo productoTipo : clasificacion) {
				if (i == 0)
					tipo = productoTipo;
				String icono = "lock32.png";
				if (productoTipo.getIcono() != null
						&& !productoTipo.getIcono().equals(""))
					icono = productoTipo.getIcono();
				
				tabInfo = new TabInfo();
				tabInfo.setProductoTipo(productoTipo);
				tabInfo.setIndex(i);
				tabInfo.setPath("modulos/productos/utils/detallesProducto.zul");
				tabInfo.setIcono("images/toolbar/" + icono);
				
				tabListClasificacionProductos.add(tabInfo);
				i++;
			}
		}
	}

	@Command
	@NotifyChange("*")
	public void selectDynamic(@BindingParam("tabs") TabInfo tabs) {
		if (tabs != null) {
			productoDB = productoService.getByTipo(tabs.getProductoTipo());
		}
		
	}

	@Command
	@NotifyChange("*")
	public void selectTabCompras() {
		cotizacionDB = cotizacionService.getTopCompras();
	}
	
	
}
