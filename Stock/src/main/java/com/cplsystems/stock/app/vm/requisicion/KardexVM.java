package com.cplsystems.stock.app.vm.requisicion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.AlmacenEntrada;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Kardex;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class KardexOrdenCompraVM extends ControlMetaclass {
	private static final long serialVersionUID = -1363727052274326014L;

	@Wire("#winMain")
	private Window winMain;

	@Wire("#winMain listbox")
	private Listbox listItems;

	@Init
	public void init() {
		super.init();
		requisicion = new Requisicion();
		areas = areaService.getAll();
		area = new Area();

		checkBuscarAceptada = true;
		ordenesCompras = buscarOrdenCompra();//Lista de ordenes de compra
		checkBuscarAceptada = false;
		
		
		almacenEntrada = new AlmacenEntrada();
		almacenesList = almacenService.getAll();

		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = usuario.getOrganizacion();
		
		estadoKardex = estatusRequisicionService.getByClave("KXN");
		kardexProveedorList = kardexProveedorService.getByEstatus(estadoKardex);
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange({ "kardexList", "kardexProveedor"})
	public void mostrarProductosOrdenCompra() {
		
		kardexList = kardexService.getByKardexProveedorEstatus(kardexProveedor, estadoKardex);
		
		
		/*
		cotizacionSelected = ordenCompra.getCotizacion();
		if (cotizacionSelected != null) {
			cotizacionesConProductos = cotizacionService.getByProveedorFolioCotizacionNueva(
					cotizacionSelected.getProveedor(), cotizacionSelected.getFolioCotizacion(),
					cotizacionSelected.getEstatusRequisicion());

			numeroProductos = cotizacionesConProductos.size();
			precioTotal = 0.0F;

			kardexList = buildKardexList(cotizacionesConProductos);
		}
		*/
	}

	private List<Kardex> buildKardexList(List<Cotizacion> listOrdenCompra) {
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
				
				
				if(temObject.getIcon() == null)
					temObject.setIcon("/images/toolbar/infoxOrange16.png");
				else
					try {
						temObject.setIcon(stockUtils.Desencriptar(temObject.getIcon()));
					} catch (Exception e) {}
				
				tempList.add(temObject);
			}
		}
		return tempList;
	}

	@Command
	@NotifyChange({ "almacenEntrada" })
	public void agreagarAlmacen(@BindingParam("index2") Integer index) {
		almacenEntrada.setActivarCantidad(false);
		cotizacionSelected.setAlmacen(almacenEntrada.getAlmacen());
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

	/*
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada) || (checkBuscarAceptada)) {
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

	@Command
	public void checkNueva() {
		if (!checkBuscarNueva) {
			checkBuscarNueva = true;
		} else {
			checkBuscarNueva = false;
		}
	}

	@Command
	public void checkCancelada() {
		if (!checkBuscarCancelada) {
			checkBuscarCancelada = true;
		} else {
			checkBuscarCancelada = false;
		}
	}

	@Command
	public void checkAceptada() {
		if (!checkBuscarAceptada) {
			checkBuscarAceptada = true;
		} else {
			checkBuscarAceptada = false;
		}
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
	@NotifyChange({ "*" })
	public void abrirVentanaCancelacion() {
		if (ordenCompra != null) {
			if (ordenCompra.getEstatusRequisicion().getClave().equals("OCN")) {
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

				List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
				for (Privilegios privilegio : privilegios) {
					if (privilegio.getUsuarios().getPersona().getContacto() != null) {
						mailService.sendMail(privilegio.getUsuarios().getPersona().getContacto().getEmail().getEmail(),
								"1nn3rgy@gmail.com", "Orden de compra aceptada",
								"La cotizacion " + ordenCompra.getCodigo() + " ha sido aceptada");
					}
				}
				StockUtils.showSuccessmessage("La orden de compra [" + ordenCompra.getCodigo() + "] ha sido Aceptada",
						"info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"La orden de compra [" + ordenCompra.getCodigo() + "] no puede ser aceptada bajo este estatus ("
								+ ordenCompra.getEstatusRequisicion().getNombre() + ")",
						"warning", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una orden de compra", "warning",
					Integer.valueOf(0), null);
		}
	}

	@SuppressWarnings({ "rawtypes", "rawtypes", "rawtypes" })
	@Command
	public void imprimirOrdenCompra() {
		if (ordenCompra != null) {
			cotizacionSelected = ordenCompra.getCotizacion();
			if ((cotizacionSelected != null) && (cotizacionesConProductos != null)
					&& (cotizacionesConProductos.size() > 0)) {
				@SuppressWarnings("rawtypes")
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
						generarOrdenCompraJasper(listaHashsParametros, aplicaciones, cotizacionesConProductos), "info",
						Integer.valueOf(0), null);

				((Cotizacion) cotizacionesConProductos.get(0)).getProducto().getClave();
				((Cotizacion) cotizacionesConProductos.get(0)).getProducto().getNombre();
				((Cotizacion) cotizacionesConProductos.get(0)).getProducto().getUnidad().getNombre();

				((Cotizacion) cotizacionesConProductos.get(0)).getProducto().getPrecio();
				((Cotizacion) cotizacionesConProductos.get(0)).getRequisicionProducto().getCofiaPartidaGenerica()
						.getNombre();

				((Cotizacion) cotizacionesConProductos.get(0)).getRequisicionProducto().getCantidad();

				((Cotizacion) cotizacionesConProductos.get(0)).getRequisicionProducto().getTotalProductoPorUnidad();
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
	
	*/

	@Command
	public void showModal(Event e) {
		areas = areaService.getAll();

		Map<String, Object> inputParams = new HashMap();
		inputParams.put("listaKey", cotizacionesConProductos);

		inputParams.put("areasKey", areas);

		Window window = (Window) Executions.createComponents("/modulos/productos/utils/configurarEnvioProductos.zul",
				null, inputParams);

		window.doModal();
	}

	private List<AlmacenEntrada> prepararAlmacenList(List<AlmacenEntrada> lista) {
		for (AlmacenEntrada item2 : lista) {
			item2.setOrdenCompra(kardexProveedor.getOrdenCompra());
			item2.setOrganizacion(organizacion);
			item2.setUsuario(usuario);
			item2.setProducto(kardex.getProducto());
			item2.setProveedor(kardexProveedor.getProveedor());
		}
		kardex.setIcon(stockUtils.Encriptar("/images/toolbar/infoxGreen16.png"));
		kardex.setConf(true);
		return lista;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void configEnvio(@BindingParam("index") Integer index) {
		kardex = kardexList.get(index);
		if (kardex.getEntradaCantidad() != null) {
			kardex.getProducto().setEnExistencia(kardex.getEntradaCantidad());
			kardex.setAlmacenEntradaList(almacenEntradaService.getByOrdenCompraProductoProveedor(kardexProveedor.getOrdenCompra(),
					kardex.getProducto(), kardexProveedor.getProveedor()));

			Map<String, Object> inputParams = new HashMap();
			inputParams.put("updateCommandFromItemFinder", "getListaEnvioItemsProductos");
			inputParams.put("kardexInput", kardex);
			
			Window productoModalView = this.stockUtils.createModelDialogWithParams(
					"/modulos/productos/utils/kardexConfiguracionEnvioProducto.zul", inputParams);
			productoModalView.doModal();
		} else
			StockUtils.showSuccessmessage("debe ingresar cantidad de productos para configurar el envio",
					Clients.NOTIFICATION_TYPE_WARNING, 0, listItems);

	}

	@GlobalCommand
	@NotifyChange({ "kardexList", "kardex" })
	public void getListaEnvioItemsProductos(
			@BindingParam("almacenEntradaList") List<AlmacenEntrada> almacenEntradaList) {
		try {
			almacenEntradaList = prepararAlmacenList(almacenEntradaList);
			for (AlmacenEntrada item2 : almacenEntradaList)
				almacenEntradaService.save(item2);
			kardexService.save(kardex);
			StockUtils.showSuccessmessage("Los cambios han sido guardados", Clients.NOTIFICATION_TYPE_INFO, 0,
					listItems);
		} catch (Exception e) {
			StockUtils.showSuccessmessage("Ha habido un error al escribir en la base de datos: " + e.getMessage(),
					Clients.NOTIFICATION_TYPE_ERROR, 0, listItems);
		}	
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void search(@BindingParam("index") Integer index) {
		if (index != null) {
			Map<String, Object> inputParams = new HashMap();
			inputParams.put("updateCommandFromItemFinder", "updateRecordFromSearchSelectedItem");
			Window productoModalView = this.stockUtils
					.createModelDialogWithParams("/modulos/productos/utils/buscarProducto.zul", inputParams);
			productoModalView.doModal();
			kardex = kardexList.get(index);
		}
	}

	@GlobalCommand
	@NotifyChange({ "kardexList", "kardex" })
	public void updateRecordFromSearchSelectedItem(
			@BindingParam("productoSeleccionado") Producto productoSeleccionado) {
		if (productoSeleccionado != null) {
			kardex.setProducto(productoSeleccionado);
			productoSeleccionado.getNombre();
		}
	}
	
	
	@Command
	public void save(){
		if(kardexList != null && kardexList.size() > 0){
			for (Kardex item : kardexList) {
				item.setUsuario(usuario);
				item.setOrganizacion(organizacion);
				kardexService.save(item);
			}
			StockUtils.showSuccessmessage("Los cambios han sido guardados", Clients.NOTIFICATION_TYPE_INFO, 0,
					listItems);
		}
	}

	@NotifyChange({ "kardexList" })
	@Command
	public void calcularDebe(@BindingParam("index") Integer index) {
		Kardex item = kardexList.get(index);
		if (item.getEntradaCantidad() != null
				&& (item.getProducto() != null && item.getProducto().getPrecio() != null)) {

			int i = 0;
			for (Kardex loop : kardexList) {
				if (i == index) {
					Float multi = item.getEntradaCantidad() * item.getProducto().getPrecio();
					loop.setDebe(multi);
					loop.setSaldo(multi);
					break;
				}
				i++;
			}
		}
	}

	@NotifyChange({ "kardexList" })
	@Command
	public void agregarItem() {
		if (kardexList == null)
			kardexList = new ArrayList<>();

		if (hayItemsVacios())
			kardexList.add(crearKardexVacio());
		else
			StockUtils.showSuccessmessage("Aun hay elementos en blanco. No se puede agregar nuevo item",
					Clients.NOTIFICATION_TYPE_WARNING, 0, listItems);
	}

	private boolean hayItemsVacios() {
		boolean exit = true;
		if (kardexList != null && kardexList.size() > 0) {
			for (Kardex item : kardexList) {
				if (item.getProducto() == null) {
					exit = false;
					break;
				} else {
					if (item.getProducto().getNombre() == null) {
						exit = false;
						break;
					} else {
						if (item.getProducto().getNombre().equals("")) {
							exit = false;
							break;
						}
					}
				}
			}
		}
		return exit;
	}

	private Kardex crearKardexVacio() {
		Kardex item = new Kardex();
		item.setFechaEntrada(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
		item.setBotonBuscarProducto(true);
		return item;
	}

	private List<OrdenCompra> buscarOrdenCompra() {
		List<OrdenCompra> listaExtraer = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada) || (checkBuscarAceptada)
				|| ((requisicion != null) && (requisicion.getBuscarRequisicion() != null)
						&& (!requisicion.getBuscarRequisicion().isEmpty()))) {
			List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();
			listaExtraer = ordenCompraService.getOrdenesByEstatusAndFolioOr(requisicion.getBuscarRequisicion(),
					listaEstatus);
			if (cotizacionesList == null) {
			}
		} else {
			StockUtils.showSuccessmessage(
					"Debe elegir algun criterio para realizar la busqueda de ordenes de compra (nueva, cancelada o aceptada) o (ingresar palabra en el buscador)",
					"warning", Integer.valueOf(0), null);
		}
		return listaExtraer;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada) || (checkBuscarAceptada)) {
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
}
