/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
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
public class RequisicionVM extends ProductoVariables {

	private static final long serialVersionUID = 2584088569805199520L;
	@Wire("#proveedoresModalView")
	private Window proveedoresModalView;
	private Producto producto;

	@Init
	public void init() {
		super.init();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	public void newRecord() {
		producto = new Producto();
	}

	@Command
	@NotifyChange("*")
	public void deleteRecord() {
		if (producto.getIdProducto() == null) {
			Messagebox.show("El producto no puede ser eliminado "
					+ "ya que aún no ha sido registrado");
			return;
		}
		Messagebox.show("¿Está seguro de remover este producto?, "
				+ "esta acción es irreversible", "Question", Messagebox.OK
				| Messagebox.CANCEL, Messagebox.QUESTION,
				new EventListener<Event>() {
					public void onEvent(Event event) throws Exception {
						if (((Integer) event.getData()).intValue() == Messagebox.OK) {
							deleteProduct();
							return;
						}
					}
				});

	}

	@Command
	@NotifyChange("*")
	public void saveChanges() {
		productoService.save(producto);
		Messagebox.show("Se ha agregado correctamente el producto "
				+ producto.getNombre() + " a la base de datos");
		producto = new Producto();
	}

	@Command
	public void search() {
		Window productoModalView = stockUtils
				.createModelDialog(StockConstants.MODAL_VIEW_PRODUCTOS);
		productoModalView.doModal();
	}

	@Command
	public void deleteProduct() {
		productoService.delete(producto);
	}

	@NotifyChange("productoDB")
	@Command
	public void searchItemByKeyOrName() {
		
	}

	@NotifyChange("*")
	@Command
	public void closeSearchDialog() {
		if (proveedoresModalView != null) {
			proveedoresModalView.detach();
		}
	}

	@NotifyChange("*")
	@Command
	public void transferProductFromSearchDialog() {
		
	}

}
