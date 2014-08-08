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

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.app.vm.ordencompra.utils.OrdenCompraMetaclass;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Requisicion;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class OrdenCompraVM extends OrdenCompraMetaclass {

	private static final long serialVersionUID = 999672890629004080L;

	@Init
	public void init() {
		super.init();
		requisicion = new Requisicion();
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

			cotizacionesConProductos = cotizacionService
					.getByProveedorFolioCotizacionNueva(
							cotizacionSelected.getProveedor(),
							cotizacionSelected.getFolioCotizacion(),
							cotizacionSelected.getEstatusRequisicion());

			numeroProductos = cotizacionesConProductos.size();
			precioTotal = 0F;
			for (Cotizacion item : cotizacionesConProductos) {
				item.getRequisicionProducto().setTotalProductoPorUnidad(
						item.getRequisicionProducto().getCantidad()
								* item.getProducto().getPrecio());
				precioTotal += item.getRequisicionProducto()
						.getTotalProductoPorUnidad();
			}

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
/*
				OrdenCompraInbox inbox = new OrdenCompraInbox();
				inbox.setOrdenCompra(ordenCompra);
				inbox.setLeido(false);
				inbox.setFechaCreacion(new StockUtils()
						.convertirCalendarToDate(Calendar.getInstance()));
				ordenCompraInboxService.save(inbox);*/

				// enviar notificacion
				List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
				for (Privilegios privilegio : privilegios) {
					if (privilegio.getUsuarios().getPersona().getContacto() != null) {
						mailService.sendMail(
								privilegio.getUsuarios().getPersona()
										.getContacto().getEmail().getEmail(),
								"1nn3rgy@gmail.com",
								"Orden de compra aceptada",
								"La cotizacion "
										+ ordenCompra
												.getCodigo()
										+ " ha sido aceptada");
					}
				}			
				stockUtils.showSuccessmessage("La orden de compra ["
						+ ordenCompra.getCodigo() + "] ha sido Aceptada",
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} else
				stockUtils.showSuccessmessage(
						"La orden de compra ["
								+ ordenCompra.getCodigo()
								+ "] no puede ser aceptada bajo este estatus ("
								+ ordenCompra.getEstatusRequisicion()
										.getNombre() + ")",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);

		} else
			stockUtils.showSuccessmessage(
					"Es necesario seleccionar primero una orden de compra",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	@SuppressWarnings({ "rawtypes", "static-access", "unchecked" })
	@Command
	public void imprimirOrdenCompra() {
		if (ordenCompra != null) {

			cotizacionSelected = ordenCompra.getCotizacion();
			if (cotizacionSelected != null) {
				if (cotizacionesConProductos != null
						&& cotizacionesConProductos.size() > 0) {
					Organizacion org = (Organizacion) sessionUtils
							.getFromSession(SessionUtils.FIRMA);

					HashMap mapa = new HashMap();
					mapa.put("logotipo", stockUtils
							.getLogotipoDeOrganizacionParaJasper(org
									.getLogotipo()));
					mapa.put("nombreEmpresa", org.getNombre());
					mapa.put("proveedor", cotizacionSelected.getProveedor()
							.getNombre());
					mapa.put("ur", cotizacionSelected.getRequisicion()
							.getArea().getNombre());
					mapa.put("comentarios",
							cotizacionSelected.getDetallesExtras());
					mapa.put("ordenCompra", ordenCompra.getCodigo());
					mapa.put("fechaOC", stockUtils
							.convertirCalendarToString(ordenCompra
									.getFechaOrden()));
					mapa.put("claveCotizacion",
							cotizacionSelected.getFolioCotizacion());

					Float total = 0F;

					for (Cotizacion item : cotizacionesConProductos) {
						total += item.getRequisicionProducto()
								.getTotalProductoPorUnidad();
					}
					mapa.put("total", String.valueOf(total));

					mapa.put("entregarEn", "");
					mapa.put("dependencia", "");
					mapa.put("tiempoEntrega", "");

					List<HashMap> listaHashsParametros = new ArrayList<HashMap>();
					listaHashsParametros.add(mapa);

					List<AplicacionExterna> aplicaciones = new ArrayList<AplicacionExterna>();
					AplicacionExterna aplicacion = new AplicacionExterna();
					aplicacion.setNombre("PDFXCview");
					aplicaciones.add(aplicacion);
					stockUtils.showSuccessmessage(
							generarOrdenCompraJasper(listaHashsParametros,
									aplicaciones, cotizacionesConProductos),
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
					cotizacionesConProductos.get(0).getProducto().getClave();
					cotizacionesConProductos.get(0).getProducto().getNombre();
					cotizacionesConProductos.get(0).getProducto().getUnidad()
							.getNombre();
					cotizacionesConProductos.get(0).getProducto().getPrecio();
					cotizacionesConProductos.get(0).getRequisicionProducto()
							.getCofiaPartidaGenerica().getNombre();
					cotizacionesConProductos.get(0).getRequisicionProducto()
							.getCantidad();
					cotizacionesConProductos.get(0).getRequisicionProducto()
							.getTotalProductoPorUnidad();
				}

			}
		} else
			stockUtils.showSuccessmessage(
					"Es necesario seleccionar primero una orden de compra",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	private List<Privilegios> getEmailsUsuariosCotizacion() {
		List<Privilegios> usuarios = privilegioService
				.getUsuariosByPrivilegio(UserPrivileges.COTIZAR_AUTORIZAR);
		return usuarios;
	}
}
