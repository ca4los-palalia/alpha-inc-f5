/**
 * 
 */
package com.cplsystems.stock.app.vm.proveedor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.domain.Banco;
import com.cplsystems.stock.domain.Proveedor;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProveedoresVM extends ProveedorMetaClass {

	private static final long serialVersionUID = -4963362932578502507L;
	private static List<Banco> completeBancos;

	@Init
	public void init() {
		super.init();
		completeBancos = bancosDB;
		readJasper = generarUrlString("jasperTemplates/reportProductos.jasper");
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
					nuevoProveedor.setProveedorActivo(true);
					guardarProveedor();
					stockUtils.showSuccessmessage(nuevoProveedor.getNombre()
							+ " ha sido guardado",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
					initObjects();
				} else
					stockUtils.showSuccessmessage(
							"Los campos marcados con (*) son requeridos: \n"
									+ mensajeValidacion,
							Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			} else
				stockUtils.showSuccessmessage(nuevoProveedor.getNombre()
						+ " ya se encuentra registrado",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		} else
			stockUtils.showSuccessmessage("Nombre del proveedor requerido",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);

	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void saveChanges() {

		if (proveedoresLista != null && proveedoresLista.size() > 0) {
			actualizarProveedorCambios();
			stockUtils.showSuccessmessage(
					"La lista de proveedores ha sido actualizada",
					Clients.NOTIFICATION_TYPE_INFO, 0, null);
		} else
			stockUtils.showSuccessmessage("La lista no contiene proveedores",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);

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
												.save(proveedorSelected);
										proveedoresLista
												.remove(proveedorSelected);
										stockUtils.showSuccessmessage(
												proveedorSelected.getNombre()
														+ " ha sido eliminado",
												Clients.NOTIFICATION_TYPE_INFO,
												0, null);
										proveedorSelected = null;
										return;
									}
								}
							});

		} else {
			stockUtils.showSuccessmessage(
					"Seleccione un proveedor para llevar acabo la eliminación",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
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
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				else
					stockUtils.showSuccessmessage("Tu búsqueda -"
							+ buscarProveedor.getNombre() + "- obtuvo "
							+ proveedoresLista.size() + " " + mensaje,
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				buscarProveedor.setComentario(proveedoresLista.size() + " "
						+ mensaje);

			} else{
				stockUtils.showSuccessmessage(
						"Tu búsqueda -" + buscarProveedor.getNombre()
								+ "- no obtuvo ningún resultado",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
				proveedorSelected = new Proveedor();
			}
				

		} else {
			stockUtils.showSuccessmessage(
					"Tu búsqueda no obtuvo ningún resultado",
					Clients.NOTIFICATION_TYPE_ERROR, 0, null);
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
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				else
					stockUtils.showSuccessmessage("Tu búsqueda -"
							+ buscarProveedorAsociar.getNombre() + "- obtuvo "
							+ proveedoresAsociacion.size() + " " + mensaje,
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				buscarProveedorAsociar.setComentario(String
						.valueOf(proveedoresAsociacion.size()));

			} else
				stockUtils.showSuccessmessage(
						"Tu búsqueda -" + buscarProveedor.getNombre()
								+ "- no obtuvo ningún resultado",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);

		} else {
			stockUtils.showSuccessmessage(
					"Tu búsqueda no obtuvo ningún resultado",
					Clients.NOTIFICATION_TYPE_ERROR, 0, null);
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
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				else
					stockUtils.showSuccessmessage("Tu búsqueda -"
							+ buscarProducto.getNombre() + "- obtuvo "
							+ productosDB.size() + " " + mensaje,
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				buscarProducto
						.setDescripcion(String.valueOf(productosDB.size()));

			} else
				stockUtils.showSuccessmessage(
						"Tu búsqueda -" + buscarProducto.getNombre()
								+ "- no obtuvo ningún resultado",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);

		} else {
			stockUtils.showSuccessmessage(
					"Tu búsqueda no obtuvo ningún resultado",
					Clients.NOTIFICATION_TYPE_ERROR, 0, null);
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

	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	@Command
	@NotifyChange("*")
	public void reporteProveedores() {
		if (proveedoresLista != null) {
			
			HashMap mapa = new HashMap();
			mapa.put(StockConstants.REPORT_PROVEEDOR_PARAM1,
					"REPORTE DE PROVEEDORES");
			mapa.put(StockConstants.REPORT_PROVEEDOR_NOMBRE_EMPRESA, "PROVEEDORA DE MATERIAL ELECTRICO Y PLOMERIA S.A. de C.V.");
			List<HashMap> listaHashsParametros = new ArrayList<HashMap>();
			listaHashsParametros.add(mapa);
			
			
			List<AplicacionExterna> aplicaciones = new ArrayList<AplicacionExterna>();
			AplicacionExterna aplicacion = new AplicacionExterna();
			aplicacion.setNombre("PDFXCview");
			aplicaciones.add(aplicacion);
			
			stockUtils
					.showSuccessmessage(
							generarReporteProveedor(listaHashsParametros,
									aplicaciones),
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
		} else {
			stockUtils
					.showSuccessmessage(
							"NO existe algún resultado de busqueda para generar el reporte (PDF)",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
		}
	}
}
