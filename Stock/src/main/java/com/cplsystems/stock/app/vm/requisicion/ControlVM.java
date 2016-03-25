package com.cplsystems.stock.app.vm.requisicion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.domain.AlmacenEntrada;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class ControlVM extends ControlMetaclass {
	private static final long serialVersionUID = -1363727052274326014L;
	
	@Init
	public void init() {
		super.init();
		requisicion = new Requisicion();
		areas = areaService.getAll();
		area = new Area();
		
		checkBuscarAceptada = true;
		ordenesCompras = buscarOrdenCompra();
		checkBuscarAceptada = false;
		almacenEntrada = new AlmacenEntrada();
		almacenesList = almacenService.getAll();
		
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = usuario.getOrganizacion();
	}
	
	
	@Command
	@NotifyChange({ "cotizacionSelected", "cotizacionesConProductos", "numeroProductos", "precioTotal", "ordenCompra" })
	public void mostrarProductosOrdenCompra() {
		cotizacionSelected = ordenCompra.getCotizacion();
		if (cotizacionSelected != null) {
			cotizacionesConProductos = cotizacionService.getByProveedorFolioCotizacionNueva(
					cotizacionSelected.getProveedor(), cotizacionSelected.getFolioCotizacion(),
					cotizacionSelected.getEstatusRequisicion());

			numeroProductos = Integer.valueOf(cotizacionesConProductos.size());
			precioTotal = 0.0F;
			
			for (Cotizacion item : cotizacionesConProductos) {
				item.getRequisicionProducto().setTotalProductoPorUnidad(
						Float.valueOf(item.getRequisicionProducto().getCantidad().floatValue()
								* item.getProducto().getPrecio().floatValue()));

				precioTotal = Float.valueOf(precioTotal.floatValue()
						+ item.getRequisicionProducto().getTotalProductoPorUnidad().floatValue());
				boolean botonesControl = false;
				List<AlmacenEntrada> entradas = almacenEntradaService.getByOrdenCompra(ordenCompra);
				int restan = (int) Math.round(item.getRequisicionProducto().getTotalProductoPorUnidad());
				if(entradas == null){
					entradas = new ArrayList<>();
					entradas.add(crearAlmacenEntradaVacia(item, ordenCompra));
					item.setRestan(restan);
				}else{
					int count = 0;
					for (AlmacenEntrada almacenEntrada : entradas) {
						almacenEntrada.setArea(getAreaFromList(almacenEntrada.getArea().getIdArea()));
						
						almacenEntrada.getArea().setAlmacenesList(getAlmacenesByAreaFromList(almacenesList, almacenEntrada.getArea()));
						almacenEntrada.setAlmacen(getAlmacenFromList(almacenEntrada.getAlmacen().getIdAlmacen(), almacenEntrada.getArea().getAlmacenesList()));
						
						almacenEntrada.setActivarCantidad(true);
						count += almacenEntrada.getCantidad();
						
					}
					item.setRestan(restan - count);
					botonesControl = true;
				}
				item.setActivarBotonesControl(botonesControl);
				item.setAlmacenEntradaList(entradas);
			}
		}
	}
	
	
	
	@Command
	@NotifyChange({ "almacenEntrada", "cotizacion" })
	public void buscarAlmacenesDeAreaSeleccionada(@BindingParam("index") Integer index){
		if(cotizacion != null){
			almacenEntrada = cotizacion.getAlmacenEntradaList().get(index);
			almacenEntrada.getArea().setAlmacenesList(almacenService.getByArea(almacenEntrada.getArea()));
		}else
			StockUtils.showSuccessmessage("Seleccione un producto por favor", Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		
	}
	
	@Command
	@NotifyChange({ "almacenEntrada"})
	public void agreagarAlmacen(@BindingParam("index2") Integer index){
		almacenEntrada.setActivarCantidad(false);
		cotizacionSelected.setAlmacen(almacenEntrada.getAlmacen());
	}
	@Command
	@NotifyChange({ "cotizacion"})
	public void validarMaximoProductos(){
		int maximoTotal = (int) Math.round(cotizacion.getRequisicionProducto().getTotalProductoPorUnidad());
		if(almacenEntrada.getCantidad() > maximoTotal){
			StockUtils.showSuccessmessage("La cantidad debe ser menor al restante de productos", Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			almacenEntrada.setCantidad(null);
		}else if(almacenEntrada.getCantidad() != null && almacenEntrada.getCantidad() < 1){
			StockUtils.showSuccessmessage("La cantidad debe ser mayor a 0", Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			almacenEntrada.setCantidad(null);
		}else if(excedeMaximo(maximoTotal)){
			StockUtils.showSuccessmessage("La cantidad de productos excede, con el maximo de " + maximoTotal, Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			almacenEntrada.setCantidad(null);
		}
	}
	
	private boolean excedeMaximo(int maximo){
		int count = 0;
		for (AlmacenEntrada item : cotizacion.getAlmacenEntradaList()) {
			if(item.getCantidad() != null && item.getCantidad() > 0)
				count += item.getCantidad();
		}
		if(count > maximo)
			return true;
		else{
			cotizacion.setRestan(maximo - count);
			return false;
		}
	}
	
	@Command
	@NotifyChange({ "cotizacion" })
	public void agregarNuevoAlmacenParaEnvioDeProducto() {
		
		if(cotizacion != null && cotizacion.getIdCotizacion() != null){
			if (cotizacion.getAlmacenEntradaList() == null)
				cotizacion.setAlmacenEntradaList(new ArrayList<AlmacenEntrada>());
			if(cotizacion.getRestan() == null){
				int value = (int)Math.round(cotizacion.getRequisicionProducto().getTotalProductoPorUnidad());
				cotizacion.setRestan(value);
			}
			
			if(cotizacion.getRestan()!= null && cotizacion.getRestan() > 0)
				cotizacion.getAlmacenEntradaList().add(crearAlmacenEntradaVacia(cotizacion, ordenCompra));
			else
				StockUtils.showSuccessmessage("No se pueden agregar mas envíos. Se ha agotado el limite", Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}else
			StockUtils.showSuccessmessage("Debe seleccionar un producto para poder agregar envios", Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}
	
	
	
	@Command
	@NotifyChange({ "almacenEntradaList", "almacenEntrada", "cotizacion"})
	public void quitarAlmacenEntradaParaEnvioDeProducto(@BindingParam("index3") Integer index) {
		List<AlmacenEntrada> clonacion = new ArrayList<>();
		for (AlmacenEntrada item : cotizacion.getAlmacenEntradaList()) {
			clonacion.add(item);
		} 
		
		AlmacenEntrada delete = clonacion.get(index);
		if(delete.getCantidad() != null && delete.getCantidad() > 0)
			cotizacion.setRestan(cotizacion.getRestan() + delete.getCantidad());
		if (clonacion != null && clonacion.size() > 0)
			clonacion.remove(delete);
		
		cotizacion.getAlmacenEntradaList().clear();
		cotizacion.setAlmacenEntradaList(clonacion);
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
						mailService.sendMail(
								privilegio.getUsuarios().getPersona().getContacto().getEmail().getEmail(),
								"1nn3rgy@gmail.com", "Orden de compra aceptada",
								"La cotizacion " + ordenCompra.getCodigo() + " ha sido aceptada");
					}
				}
				StockUtils.showSuccessmessage(
						"La orden de compra [" + ordenCompra.getCodigo() + "] ha sido Aceptada", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"La orden de compra [" + ordenCompra.getCodigo()
								+ "] no puede ser aceptada bajo este estatus ("
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
				//mapa.put("logotipo", stockUtils.getLogotipoDeOrganizacionParaJasper(org.getLogotipo()));

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

	@Command
	public void salvarEnvios() {
		for (Cotizacion item : cotizacionesConProductos) {
			for (AlmacenEntrada item2: item.getAlmacenEntradaList()) {
				item2.setOrganizacion(organizacion);
				item2.setUsuario(usuario);
				almacenEntradaService.save(item2);
			}
			//cotizacionService.save(item);
		}
		StockUtils.showSuccessmessage("Los cambios han sido guardados", "info", Integer.valueOf(0), null);
	}
	
	
	
	
	
	
}
