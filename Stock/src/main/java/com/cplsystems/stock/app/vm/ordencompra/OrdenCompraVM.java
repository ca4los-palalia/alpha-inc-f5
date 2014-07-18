/**
 * 
 */
package com.cplsystems.stock.app.vm.ordencompra;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.ordencompra.utils.OrdenCompraMetaclass;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrdenCompraVM extends OrdenCompraMetaclass {

	private static final long serialVersionUID = 999672890629004080L;

	@Init
	public void init() {
		super.init();
		loadInbox();
		requisicion = new Requisicion();
	}

	private void loadInbox() {
		requisicionProductos = requisicionProductoService.getAllRequisiciones();
		if (requisicionProductos == null) {
			requisicionProductos = new ArrayList<RequisicionProducto>();
		}
		requisiciones = requisicionService.getAll();
		if (requisiciones == null) {
			requisiciones = new ArrayList<Requisicion>();
		}
	}

	@Command
	public void transferirOrdenCompraToFormulario(
			@BindingParam("index") Integer index) {
		if (index != null) {
			OrdenCompraInbox toLoad = ordenesCompraInbox.get(index);
			if (toLoad.getLeido() != null && !toLoad.getLeido()) {
				toLoad.setLeido(true);
				ordenCompraInboxService.save(toLoad);
				toLoad.setIcono(OrdenCompraInbox.LEIDO);
			}
		}

	}

	private List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;

		if (checkBuscarNueva || checkBuscarCancelada || checkBuscarEnviada
				|| checkBuscarAceptada) {
			lista = new ArrayList<EstatusRequisicion>();

			if (checkBuscarNueva)
				lista.add(estatusRequisicionService
						.getByClave(StockConstants.ESTADO_ORDEN_COMPRA_NUEVA));
			if (checkBuscarCancelada)
				lista.add(estatusRequisicionService
						.getByClave(StockConstants.ESTADO_ORDEN_COMPRA_CANCELADA));
			if (checkBuscarEnviada)
				lista.add(estatusRequisicionService
						.getByClave(StockConstants.ESTADO_ORDEN_COMPRA_TERMINADA));
		}

		return lista;
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void buscarOrdenCompra() {
		// cotizacionesList = new ArrayList<Cotizacion>();
		if ((checkBuscarNueva || checkBuscarCancelada || checkBuscarEnviada || checkBuscarAceptada)
				|| (requisicion != null && (requisicion.getBuscarRequisicion() != null && !requisicion
						.getBuscarRequisicion().isEmpty()))) {

			List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();
			ordenesCompras = ordenCompraService.getOrdenesByEstatusAndFolioOr(
					requisicion.getBuscarRequisicion(), listaEstatus);

			if (cotizacionesList != null) {

			}

		} else
			stockUtils
					.showSuccessmessage(
							"Debe elegir algun criterio para realizar la busqueda de ordenes de compra (nueva, cancelada o aceptada) o (ingresar palabra en el buscador)",
							Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	@Command
	@NotifyChange("*")
	public void mostrarProductosOrdenCompra() {
		cotizacionSelected = ordenCompra.getCotizacion();
		if (cotizacionSelected != null) {
			Cotizacion cotizacion = cotizacionService
					.getById(cotizacionSelected.getIdCotizacion());
			requisicionProductos = requisicionProductoService
					.getByCotizacion(cotizacion);
		}
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void abrirVentanaCancelacion() {

		if (ordenCompra != null) {
			if (ordenCompra.getEstatusRequisicion().getClave()
					.equals(StockConstants.ESTADO_ORDEN_COMPRA_NUEVA)) {

				final HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("orden", ordenCompra);
				Executions.createComponents(
						"/modulos/ordenCompra/cancelacionOrdenCompra.zul",
						null, map);
			} else
				stockUtils
						.showSuccessmessage(
								"La cotizacion con folio -"
										+ ordenCompra.getCodigo()
										+ "- nu puede ser cancelada bajo este estatus ("
										+ ordenCompra.getEstatusRequisicion()
												.getNombre() + ")",
								Clients.NOTIFICATION_TYPE_WARNING, 0, null);

		} else
			stockUtils.showSuccessmessage(
					"Es necesario seleccionar primero una orden de compra",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void aceptarOrdenCompra() {
		if (ordenCompra != null) {
			if (ordenCompra.getEstatusRequisicion().getClave()
					.equals(StockConstants.ESTADO_ORDEN_COMPRA_NUEVA)) {
				
				EstatusRequisicion estado = estatusRequisicionService
						.getByClave(StockConstants.ESTADO_ORDEN_COMPRA_TERMINADA);

				ordenCompra.setEstatusRequisicion(estado);
				ordenCompraService.save(ordenCompra);

				

				OrdenCompraInbox inbox = new OrdenCompraInbox();
				inbox.setOrdenCompra(ordenCompra);
				inbox.setLeido(false);
				inbox.setFechaCreacion(new StockUtils()
						.convertirCalendarToDate(Calendar.getInstance()));
				ordenCompraInboxService.save(inbox);

				stockUtils.showSuccessmessage("La orden de compra -"
						+ ordenCompra.getCodigo()
						+ "- ha sido Aceptada", Clients.NOTIFICATION_TYPE_INFO,
						0, null);
			} else
				stockUtils.showSuccessmessage("La orden de compra -"
						+ ordenCompra.getCodigo()
						+ "- no puede ser aceptada bajo este estatus ("
						+ ordenCompra.getEstatusRequisicion()
								.getNombre() + ")",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);

		} else
			stockUtils.showSuccessmessage(
					"Es necesario seleccionar primero una orden de compra",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}
	

}
