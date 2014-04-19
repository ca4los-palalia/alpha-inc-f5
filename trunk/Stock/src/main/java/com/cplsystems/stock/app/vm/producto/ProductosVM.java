/**
 * 
 */
package com.cplsystems.stock.app.vm.producto;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Producto;

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

	@Override
	public void newRecord() {
		producto = new Producto();
		super.newRecord();
	}

	@Command
	@NotifyChange("*")
	@Override
	public void deleteRecord() {
		Messagebox
				.show("¿Está seguro de remover este producto?, esta acción es irreversible",
						"Question", Messagebox.OK | Messagebox.CANCEL,
						Messagebox.QUESTION, new EventListener<Event>() {
							public void onEvent(Event event) throws Exception {
								if (((Integer) event.getData()).intValue() == Messagebox.OK) {/*
																							 * BindUtils
																							 * .
																							 * postGlobalCommand
																							 * (
																							 * null
																							 * ,
																							 * null
																							 * ,
																							 * "performCustomerExportation"
																							 * ,
																							 * null
																							 * )
																							 * ;
																							 */
									deleteProduct();
									return;
								}
							}
						});

	}

	@Command
	@NotifyChange("*")
	@Override
	public void saveChanges() {
		productoService.save(producto);
		Messagebox.show("Se ha agregado correctamente el producto "
				+ producto.getNombre() + " a la base de datos");
		producto = new Producto();
	}

	@Override
	public void performSerch() {
		proveedoresModalView = stockUtils
				.createModelDialog(StockConstants.MODAL_VIEW_PRODUCTOS);
	}

	public void deleteProduct() {
		if (producto.getIdProducto() == null) {
			Messagebox.show("El producto no puede ser eliminado "
					+ "ya que aún no ha sido registrado");
			return;
		}
		productoService.delete(producto);
	}
}
