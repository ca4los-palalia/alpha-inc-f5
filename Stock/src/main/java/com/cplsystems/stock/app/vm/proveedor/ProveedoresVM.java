/**
 * 
 */
package com.cplsystems.stock.app.vm.proveedor;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import com.cplsystems.stock.domain.Banco;
import com.cplsystems.stock.domain.Proveedor;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProveedoresVM extends ProveedorMetaClass {

	private static final long serialVersionUID = -4963362932578502507L;
	private static List<Banco> completeBancos;

	@Init
	public void init() {
		super.init();
		completeBancos = bancosDB;
	}

	@Command
	@NotifyChange("*")
	public void selectTab1() {
		botonMuenu1.setVisible(true);
		botonMuenu2.setVisible(true);
		botonMuenu3.setVisible(false);
		botonMuenu4.setVisible(false);
		botonMuenu5.setVisible(false);
		botonMuenu6.setVisible(false);
		botonMuenu7.setVisible(false);
	}
	
	@Command
	@NotifyChange("*")
	public void selectTab2() {
		botonMuenu1.setVisible(false);
		botonMuenu2.setVisible(false);
		botonMuenu3.setVisible(true);
		botonMuenu4.setVisible(true);
		botonMuenu5.setVisible(true);
		botonMuenu6.setVisible(false);
		botonMuenu7.setVisible(false);
	}
	
	@Command
	@NotifyChange("*")
	public void selectTab3() {
		botonMuenu1.setVisible(false);
		botonMuenu2.setVisible(false);
		botonMuenu3.setVisible(false);
		botonMuenu4.setVisible(false);
		botonMuenu5.setVisible(false);
		botonMuenu6.setVisible(true);
		botonMuenu7.setVisible(true);
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void newRecord() {

		if (nuevoProveedor.getNombre() != null) {
			List<Proveedor> validarExistencia = proveedorService
					.getByNombre(nuevoProveedor.getNombre());

			if (validarExistencia == null) {
				String mensajeValidacion = validarEntradaDatosProveedor();
				if (mensajeValidacion.equals("")) {
					guardarProveedor();
					initObjects();
					stockUtils.showSuccessmessage(nuevoProveedor.getNombre()
							+ " ha sido guardado",
							Clients.NOTIFICATION_TYPE_INFO, 0);
				} else
					stockUtils.showSuccessmessage(
							"Los campos marcados con (*) son requeridos: \n"
									+ mensajeValidacion,
							Clients.NOTIFICATION_TYPE_WARNING, 0);
			} else
				stockUtils.showSuccessmessage(nuevoProveedor.getNombre()
						+ " ya se encuentra registrado",
						Clients.NOTIFICATION_TYPE_WARNING, 0);
		} else
			stockUtils.showSuccessmessage("Nombre del proveedor requerido",
					Clients.NOTIFICATION_TYPE_WARNING, 0);

	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void saveChanges() {

		if (proveedoresLista != null && proveedoresLista.size() > 0) {
			actualizarProveedorCambios();
			stockUtils.showSuccessmessage(
					"La lista de proveedores ha sido actualizada",
					Clients.NOTIFICATION_TYPE_INFO, 0);
		} else
			stockUtils.showSuccessmessage("La lista no contiene proveedores",
					Clients.NOTIFICATION_TYPE_WARNING, 0);

	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void deleteRecord() {
		if (proveedorSelected != null) {

			Messagebox
					.show("¿Está seguro de remover este proveedor?, esta acción es irreversible",
							"Question", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION, new EventListener<Event>() {
								public void onEvent(Event event)
										throws Exception {
									if (((Integer) event.getData()).intValue() == Messagebox.OK) {

										proveedorSelected
												.setProveedorActivo(false);
										proveedorService
												.update(proveedorSelected);
										proveedoresLista
												.remove(proveedorSelected);
										stockUtils.showSuccessmessage(
												proveedorSelected.getNombre()
														+ " ha sido eliminado",
												Clients.NOTIFICATION_TYPE_INFO,
												0);
										proveedorSelected = null;
										return;
									}
								}
							});

		} else {
			stockUtils.showSuccessmessage(
					"Seleccione un proveedor para llevar acabo la eliminación",
					Clients.NOTIFICATION_TYPE_WARNING, 0);
		}
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void performSearch() {
		if (buscarProveedor.getNombre() != null
				&& !buscarProveedor.getNombre().isEmpty()) {
			if (buscarProveedor.getNombre().equals("*"))
				proveedoresLista = proveedorService.getAll();
			else
				proveedoresLista = proveedorService
						.getBysClaveNombreRfc(buscarProveedor.getNombre());
			if (proveedoresLista != null) {
				String mensaje = "";
				if (proveedoresLista.size() == 1)
					mensaje = "proveedor";
				else if (proveedoresLista.size() > 1)
					mensaje = "proveedores";

				if (buscarProveedor.getNombre().equals("*"))
					stockUtils.showSuccessmessage(
							"Tu búsqueda obtuvo todos los proveedores",
							Clients.NOTIFICATION_TYPE_INFO, 0);
				else
					stockUtils.showSuccessmessage("Tu búsqueda -"
							+ buscarProveedor.getNombre() + "- obtuvo "
							+ proveedoresLista.size() + " " + mensaje,
							Clients.NOTIFICATION_TYPE_INFO, 0);
				buscarProveedor.setComentario(proveedoresLista.size() + " "
						+ mensaje);

			} else
				stockUtils.showSuccessmessage(
						"Tu búsqueda -" + buscarProveedor.getNombre()
								+ "- no obtuvo ningún resultado",
						Clients.NOTIFICATION_TYPE_WARNING, 0);

		} else {
			stockUtils.showSuccessmessage(
					"Tu búsqueda no obtuvo ningún resultado",
					Clients.NOTIFICATION_TYPE_ERROR, 0);
		}
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void performSearchProveedorAsociacion() {
		proveedoresAsociacionSelected = null;

		if (buscarProveedorAsociar.getNombre() != null
				&& !buscarProveedorAsociar.getNombre().isEmpty()) {
			if (buscarProveedorAsociar.getNombre().equals("*"))
				proveedoresAsociacion = proveedorService.getAll();
			else
				proveedoresAsociacion = proveedorService
						.getBysClaveNombreRfc(buscarProveedorAsociar
								.getNombre());
			if (proveedoresAsociacion != null) {
				String mensaje = "";
				if (proveedoresAsociacion.size() == 1)
					mensaje = "proveedor";
				else if (proveedoresAsociacion.size() > 1)
					mensaje = "proveedores";

				if (buscarProveedorAsociar.getNombre().equals("*"))
					stockUtils.showSuccessmessage(
							"Tu búsqueda obtuvo todos los proveedores",
							Clients.NOTIFICATION_TYPE_INFO, 0);
				else
					stockUtils.showSuccessmessage("Tu búsqueda -"
							+ buscarProveedorAsociar.getNombre() + "- obtuvo "
							+ proveedoresAsociacion.size() + " " + mensaje,
							Clients.NOTIFICATION_TYPE_INFO, 0);
				buscarProveedorAsociar.setComentario(String
						.valueOf(proveedoresAsociacion.size()));

			} else
				stockUtils.showSuccessmessage(
						"Tu búsqueda -" + buscarProveedor.getNombre()
								+ "- no obtuvo ningún resultado",
						Clients.NOTIFICATION_TYPE_WARNING, 0);

		} else {
			stockUtils.showSuccessmessage(
					"Tu búsqueda no obtuvo ningún resultado",
					Clients.NOTIFICATION_TYPE_ERROR, 0);
		}
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void performSearchProductoAsociacion() {

		if (buscarProducto.getNombre() != null
				&& !buscarProducto.getNombre().isEmpty()) {
			if (buscarProducto.getNombre().equals("*")) {
				// productosDB = productoService.getAll();
			} else
				productosDB = productoService.getByClaveNombre(buscarProducto
						.getNombre());
			if (productosDB != null) {
				String mensaje = "";
				if (productosDB.size() == 1)
					mensaje = "producto";
				else if (productosDB.size() > 1)
					mensaje = "productos";

				if (buscarProducto.getNombre().equals("*"))
					stockUtils.showSuccessmessage(
							"Tu búsqueda obtuvo todos los proveedores",
							Clients.NOTIFICATION_TYPE_INFO, 0);
				else
					stockUtils.showSuccessmessage("Tu búsqueda -"
							+ buscarProducto.getNombre() + "- obtuvo "
							+ productosDB.size() + " " + mensaje,
							Clients.NOTIFICATION_TYPE_INFO, 0);
				buscarProducto
						.setDescripcion(String.valueOf(productosDB.size()));

			} else
				stockUtils.showSuccessmessage(
						"Tu búsqueda -" + buscarProducto.getNombre()
								+ "- no obtuvo ningún resultado",
						Clients.NOTIFICATION_TYPE_WARNING, 0);

		} else {
			stockUtils.showSuccessmessage(
					"Tu búsqueda no obtuvo ningún resultado",
					Clients.NOTIFICATION_TYPE_ERROR, 0);
		}

	}

	@Command
	@NotifyChange("*")
	public void mostrarProductosDeProveedor() {
		proveedorProductos = proveedorProductoService
				.getByProveedor(proveedoresAsociacionSelected);
	}

	public static List<Banco> getCompleteBancos() {
		return completeBancos;
	}

	
}
