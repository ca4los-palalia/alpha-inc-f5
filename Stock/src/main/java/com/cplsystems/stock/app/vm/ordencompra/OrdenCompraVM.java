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
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.app.vm.ordencompra.utils.OrdenCompraMetaclass;
import com.cplsystems.stock.domain.AplicacionExterna;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Kardex;
import com.cplsystems.stock.domain.KardexProveedor;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class OrdenCompraVM extends OrdenCompraMetaclass {
	private static final long serialVersionUID = 999672890629004080L;

	@Init
	public void init() {
		super.init();
		requisicion = new Requisicion();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
	}

	@Command
	@NotifyChange({ "ordenesCompras" })
	public void checkNueva() {
		checkBuscarNueva = true;
		ordenesCompras = buscarOrdenCompra();
		if (ordenesCompras == null || ordenesCompras.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron Ordenes de compra nuevas", Clients.NOTIFICATION_TYPE_WARNING, 0,
					null);
		}
		checkBuscarNueva = false;
		
	}

	@Command
	@NotifyChange({ "ordenesCompras" })
	public void checkCancelada() {
		checkBuscarCancelada = true;
		ordenesCompras = buscarOrdenCompra();
		if (ordenesCompras == null || ordenesCompras.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron Ordenes de compra Canceladas", Clients.NOTIFICATION_TYPE_WARNING, 0,
					null);
		}
		checkBuscarCancelada = false;
		
	}

	@Command
	@NotifyChange({ "ordenesCompras" })
	public void checkAceptada() {
		checkBuscarAceptada = true;
		ordenesCompras = buscarOrdenCompra();
		if (ordenesCompras == null || ordenesCompras.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron Ordenes de compra aceptadas", Clients.NOTIFICATION_TYPE_WARNING, 0,
					null);
		}
		checkBuscarAceptada = false;
		
	}
	
	@Command
	@NotifyChange({ "ordenesCompras" })
	public void fidByString() {
		if (requisicion.getBuscarRequisicion() != null && !requisicion.getBuscarRequisicion().equals("")) {
			ordenesCompras = buscarOrdenCompra();
			if (ordenesCompras == null || ordenesCompras.size() == 0) {
				StockUtils.showSuccessmessage("No se encontraron Ordenes de compra con: "
						+ requisicion.getBuscarRequisicion() + ". Intente nuevamente",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else
			StockUtils.showSuccessmessage("Ingrese un criterio de busqueda.", Clients.NOTIFICATION_TYPE_ERROR, 0, null);
		
	}
	
	@Command
	public void transferirOrdenCompraToFormulario(@BindingParam("index") Integer index) {
		if (index != null) {
			OrdenCompraInbox toLoad = (OrdenCompraInbox) ordenesCompraInbox.get(index.intValue());
			if ((toLoad.getLeido() != null) && (!toLoad.getLeido().booleanValue())) {
				toLoad.setLeido(Boolean.valueOf(true));
				ordenCompraInboxService.save(toLoad);
				toLoad.setIcono("/images/toolbar/openedEmail.png");
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada)
				|| (checkBuscarAceptada)) {
			lista = new ArrayList();
			if (checkBuscarNueva) {
				lista.add(estatusRequisicionService.getByClave("OCN"));
			}
			if (checkBuscarCancelada) {
				lista.add(estatusRequisicionService.getByClave("OCC"));
			}
			if (checkBuscarAceptada) {
				lista.add(estatusRequisicionService.getByClave("OCT"));
			}
		}
		return lista;
	}

	
	private List<OrdenCompra> buscarOrdenCompra() {
		List<OrdenCompra> listaExtraer = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada)
				|| (checkBuscarAceptada)
				|| ((requisicion != null) && (requisicion.getBuscarRequisicion() != null)
						&& (!requisicion.getBuscarRequisicion().isEmpty()))) {
			List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();
			listaExtraer = ordenCompraService
					.getOrdenesByEstatusAndFolioOr(requisicion.getBuscarRequisicion(), listaEstatus);
			if (cotizacionesList == null) {
			}
		} else {
			StockUtils.showSuccessmessage(
					"Debe elegir algun criterio para realizar la busqueda de ordenes de compra (nueva, cancelada o aceptada) o (ingresar palabra en el buscador)",
					"warning", Integer.valueOf(0), null);
		}
		return listaExtraer;
	}

	@Command
	@NotifyChange({ "*" })
	public void mostrarProductosOrdenCompra() {
		cotizacionSelected = ordenCompra.getCotizacion();
		if (cotizacionSelected != null) {
			cotizacionesConProductos = cotizacionService.getByProveedorFolioCotizacionNueva(
					cotizacionSelected.getProveedor(), cotizacionSelected.getFolioCotizacion(),
					cotizacionSelected.getEstatusRequisicion());

			numeroProductos = Integer.valueOf(cotizacionesConProductos.size());
			precioTotal = Float.valueOf(0.0F);
			for (Cotizacion item : cotizacionesConProductos) {
				item.getRequisicionProducto().setTotalProductoPorUnidad(
						Float.valueOf(item.getRequisicionProducto().getCantidad().floatValue()
								* item.getProducto().getPrecio().floatValue()));

				precioTotal = Float.valueOf(precioTotal.floatValue()
						+ item.getRequisicionProducto().getTotalProductoPorUnidad().floatValue());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Command
	@NotifyChange({ "*" })
	public void abrirVentanaCancelacion() {
		if (ordenCompra != null) {
			if (ordenCompra.getEstatusRequisicion().getClave().equals("OCN")) {
				@SuppressWarnings("unchecked")
				HashMap<String, Object> map = new HashMap();
				map.put("orden", ordenCompra);
				Executions.createComponents("/modulos/ordenCompra/cancelacionOrdenCompra.zul", null, map);
			} else {
				StockUtils.showSuccessmessage(
						"La cotizacion con folio -" + ordenCompra.getCodigo()
								+ "- nu puede ser cancelada bajo este estatus ("
								+ ordenCompra.getEstatusRequisicion().getNombre() + ")",
						"warning", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una orden de compra", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void aceptarOrdenCompra() {
		if (ordenCompra != null) {
			if (ordenCompra.getEstatusRequisicion().getClave().equals("OCN")) {
				EstatusRequisicion estado = estatusRequisicionService.getByClave("OCT");

				ordenCompra.setEstatusRequisicion(estado);
				ordenCompraService.save(ordenCompra);
				
				
				buildKardexList(ordenCompra);
				
				//-----------------------------------------------------
				String mensaje = "La orden de compra " + ordenCompra.getCodigo() + " ha sido aceptada.";
				if (organizacion.getDevelopmentTool() != null) {
					List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
					if (privilegios != null && privilegios.size() > 0) {
						enviarCorreos(usuario, organizacion, privilegios, mensaje, "Orden de compra aceptada ", null);
					}
				} else
					mensaje += ". No se pudo enviar un email para la notificación";
				
				
				StockUtils.showSuccessmessage(mensaje + " ", Clients.NOTIFICATION_TYPE_INFO, 0, null);
				
				
				//-----------------------------------------------------
			} else {
				StockUtils.showSuccessmessage(
						"La orden de compra [" + ordenCompra.getCodigo()
								+ "] no puede ser aceptada bajo este estatus ("
								+ ordenCompra.getEstatusRequisicion().getNombre() + ")",
								Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una orden de compra", Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
	}
	
	//---------------------------------------------------
	private List<Kardex> buildKardexList(OrdenCompra ordenCompra) {
		Cotizacion cotizacionItem = ordenCompra.getCotizacion();
		
		List<Cotizacion> listOrdenCompra = cotizacionService.getByProveedorFolioCotizacionNueva(
				cotizacionItem.getProveedor(), cotizacionItem.getFolioCotizacion(),
				cotizacionItem.getEstatusRequisicion());
		
		List<Kardex> tempList = null;
		if (listOrdenCompra != null) {
			tempList = new ArrayList<>();
			for (Cotizacion item : listOrdenCompra) {
				Kardex temObject = new Kardex();
				temObject.setFechaEntrada(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
				temObject.setProducto(item.getProducto());
				temObject.setEntradaCantidad(Math.round(item.getRequisicionProducto().getCantidad()));
				temObject.setDebe(
						Integer.parseInt(String.valueOf(Math.round(item.getRequisicionProducto().getCantidad())))
								* item.getProducto().getPrecio());
				temObject.setIcon(stockUtils.Encriptar("/images/toolbar/infoxOrange16.png"));
				
				tempList.add(temObject);
			}
			if(tempList != null && tempList.size() > 0){
				EstatusRequisicion estadoKardex = estatusRequisicionService.getByClave("KXN");
				
				KardexProveedor kardexProveedor = new KardexProveedor();
				kardexProveedor.setOrganizacion(organizacion);
				kardexProveedor.setUsuario(usuario);
				kardexProveedor.setProveedor(cotizacionItem.getProveedor());
				kardexProveedor.setEstatusRequisicion(estadoKardex);
				kardexProveedor.setOrdenCompra(ordenCompra);
				kardexProveedorService.save(kardexProveedor);
				
				for (Kardex kardexItem : tempList) {
					kardexItem.setEstatusRequisicion(estadoKardex);
					kardexItem.setUsuario(usuario);
					kardexItem.setOrganizacion(organizacion);
					kardexItem.setKardexProveedor(kardexProveedor);
					kardexService.save(kardexItem);
				}
				
			}
		}
		return tempList;
	}
	//---------------------------------------------------

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	public void imprimirOrdenCompra() {
		if (ordenCompra != null) {
			cotizacionSelected = ordenCompra.getCotizacion();
			if ((cotizacionSelected != null) && (cotizacionesConProductos != null)
					&& (cotizacionesConProductos.size() > 0)) {
				Organizacion org = (Organizacion) sessionUtils.getFromSession("FIRMA");

				HashMap mapa = new HashMap();
				// mapa.put("logotipo",
				// stockUtils.getLogotipoDeOrganizacionParaJasper(org.getLogotipo()));

				mapa.put("nombreEmpresa", org.getNombre());
				mapa.put("proveedor", cotizacionSelected.getProveedor().getNombre());

				mapa.put("ur", cotizacionSelected.getRequisicion().getArea().getNombre());

				mapa.put("comentarios", cotizacionSelected.getDetallesExtras());

				mapa.put("ordenCompra", ordenCompra.getCodigo());
				mapa.put("fechaOC", stockUtils.convertirCalendarToString(ordenCompra.getFechaOrden()));

				mapa.put("claveCotizacion", cotizacionSelected.getFolioCotizacion());

				Float total = Float.valueOf(0.0F);
				for (Cotizacion item : cotizacionesConProductos) {
					total = Float.valueOf(total.floatValue()
							+ item.getRequisicionProducto().getTotalProductoPorUnidad().floatValue());
				}
				mapa.put("total", String.valueOf(total));

				mapa.put("entregarEn", "");
				mapa.put("dependencia", "");
				mapa.put("tiempoEntrega", "");

				List<HashMap> listaHashsParametros = new ArrayList();
				listaHashsParametros.add(mapa);

				List<AplicacionExterna> aplicaciones = new ArrayList();
				AplicacionExterna aplicacion = new AplicacionExterna();
				aplicacion.setNombre("PDFXCview");
				aplicaciones.add(aplicacion);
				StockUtils.showSuccessmessage(
						generarOrdenCompraJasper(listaHashsParametros, aplicaciones, cotizacionesConProductos),
						"info", Integer.valueOf(0), null);

				((Cotizacion) cotizacionesConProductos.get(0)).getProducto().getClave();
				((Cotizacion) cotizacionesConProductos.get(0)).getProducto().getNombre();
				((Cotizacion) cotizacionesConProductos.get(0)).getProducto().getUnidad().getNombre();

				((Cotizacion) cotizacionesConProductos.get(0)).getProducto().getPrecio();
				((Cotizacion) cotizacionesConProductos.get(0)).getRequisicionProducto().getCofiaPartidaGenerica()
						.getNombre();

				((Cotizacion) cotizacionesConProductos.get(0)).getRequisicionProducto().getCantidad();

				((Cotizacion) cotizacionesConProductos.get(0)).getRequisicionProducto()
						.getTotalProductoPorUnidad();
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una orden de compra", "warning",
					Integer.valueOf(0), null);
		}
	}

	private List<Privilegios> getEmailsUsuariosCotizacion() {
		List<Privilegios> usuarios = privilegioService.getUsuariosByPrivilegio(UserPrivileges.COTIZAR_AUTORIZAR);

		return usuarios;
	}
}
