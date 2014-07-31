/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class concentradoVM extends RequisicionVariables {

	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	@Init
	public void init() {
		super.init();
		requisicion = new Requisicion();
	}

	@Command
	@NotifyChange("proveedorProductos")
	public void cargarProveedoresProducto() {
		if (requisicionProductoSeleccionado != null) {
			if (requisicionProductoSeleccionado.getProducto() != null)
				proveedorProductos = proveedorProductoService
						.getByProducto(requisicionProductoSeleccionado
								.getProducto());

			if (proveedorProductos != null) {// nuevo codigo
				for (ProveedorProducto item : proveedorProductos) {
					Cotizacion itemCotizacion = cotizacionService
							.getCotizacionByRequisicionProveedorAndProducto(
									requisicionProductoSeleccionado
											.getRequisicion(), item
											.getProveedor(), item.getProducto());
					if (itemCotizacion != null)
						item.setSeleccionar(true);

					for (Cotizacion item2 : cotizacionesList) {
						if (item.getProducto().getClave()
								.equals(item2.getProducto().getClave())
								&& item.getProveedor()
										.getNombre()
										.equals(item2.getProveedor()
												.getNombre())) {
							item.setSeleccionar(true);
							break;
						}
					}
				}
			}// END nuevo codigo
		}
	}

	@Command
	@NotifyChange("requisicionProductos")
	public void seleccionarProveedor() {
		if (proveedorProducto != null) {
			for (RequisicionProducto item : requisicionProductos) {
				if (requisicionProductoSeleccionado.equals(item)) {
					item.setProveedor(proveedorProducto.getProveedor());
					break;
				}
			}
		}
	}

	private List<RequisicionProducto> buscarPorClaveProducto(String buscar) {
		List<RequisicionProducto> rp = null;
		Producto pr = productoService.getByClave(buscar);
		if (pr != null)
			rp = requisicionProductoService.getByProducto(pr);
		return rp;
	}

	private List<RequisicionProducto> buscarPorFolioRequisicion(String buscar) {
		List<RequisicionProducto> rp = null;
		Requisicion rq = requisicionService.getByFolio(buscar);
		if (rq != null)
			rp = requisicionProductoService.getByRequisicion(rq);
		return rp;
	}

	private List<RequisicionProducto> buscarPorPartidaGenerica(String buscar) {
		List<RequisicionProducto> rp = null;
		CofiaPartidaGenerica cpg = cofiaPartidaGenericaService
				.getByNombre(buscar);
		if (cpg != null)
			rp = requisicionProductoService

			.getByConfiaPartidaGenerica(cpg);
		return rp;
	}

	private List<RequisicionProducto> buscarPorAreaUr(String buscar) {
		List<RequisicionProducto> rp = null;
		Area areaBuscar = areaService.getByNombre(buscar);
		if (areaBuscar != null) {
			Requisicion rq = requisicionService.getByFolio(buscar);
			rq = requisicionService.getByUnidadResponsable(areaBuscar).get(0);
			requisicionProductos = requisicionProductoService
					.getByRequisicion(rq);
		}
		return rp;
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("requisicionProductos")
	public void filtrarProductoPorRequisicion() {
		String mensaje = "";
		if (requisicion == null)
			requisicion = new Requisicion();

		if (requisicion.getBuscarRequisicion() != null
				&& !requisicion.getBuscarRequisicion().isEmpty()) {

			requisicionProductos = buscarPorClaveProducto(requisicion
					.getBuscarRequisicion());

			if (requisicionProductos == null) {
				requisicionProductos = buscarPorFolioRequisicion(requisicion
						.getBuscarRequisicion());

				if (requisicionProductos == null) {
					requisicionProductos = buscarPorPartidaGenerica(requisicion
							.getBuscarRequisicion());
					if (requisicionProductos == null) {
						requisicionProductos = buscarPorAreaUr(requisicion
								.getBuscarRequisicion());
					}
				}
			}
			if (requisicionProductos == null)
				stockUtils.showSuccessmessage(
						"Tu busqueda -" + requisicion.getBuscarRequisicion()
								+ "- no encontro resultados",
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
		} else
			stockUtils
					.showSuccessmessage(
							"No se ingreso algun parametro de busque, asegurese de escribir alguna de las formas de busqueda recomendadas",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);

	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void removerProductoDeListaGeneralDeProductos(
			@BindingParam("index") Integer index) {
		if (requisicionProductoSeleccionado == null)
			requisicionProductoSeleccionado = requisicionProductos.get(index);

		requisicionProductoService.delete(requisicionProductoSeleccionado);
		requisicionProductos.remove(requisicionProductoSeleccionado);

		stockUtils.showSuccessmessage("El producto -"
				+ requisicionProductoSeleccionado.getProducto().getNombre()
				+ "- ha sido removido de la requisición -"
				+ requisicionProductoSeleccionado.getRequisicion().getFolio()
				+ "-", Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void cancelarRequisicion() {
		EstatusRequisicion estado = estatusRequisicionService
				.getByClave(StockConstants.ESTADO_REQUISICION_CANCELADA);
		Requisicion rq = requisicionProductoSeleccionado.getRequisicion();
		rq.setEstatusRequisicion(estado);
		requisicionService.save(rq);

		stockUtils.showSuccessmessage("La requisición -"
				+ requisicionProductoSeleccionado.getRequisicion().getFolio()
				+ "- ha sido cancelada", Clients.NOTIFICATION_TYPE_INFO, 0,
				null);
		estado = estatusRequisicionService
				.getByClave(StockConstants.ESTADO_REQUISICION_NUEVA);
		requisiciones = requisicionService.getByEstatusRequisicion(estado);
		requisicionProductos = requisicionProductoService
				.getRequisicionesConEstadoEspecifico(estado);
	}

	@NotifyChange("proveedorProducto")
	@Command
	public void proveedorCheckBox(@BindingParam("index") Integer index) {
		ProveedorProducto pp = proveedorProductos.get(index);
		if (cotizacionesList == null)
			cotizacionesList = new ArrayList<Cotizacion>();

		Cotizacion nuevaCotizacion = new Cotizacion();
		nuevaCotizacion.setRequisicion(requisicionProductoSeleccionado
				.getRequisicion());
		nuevaCotizacion.setProveedor(pp.getProveedor());
		nuevaCotizacion.setProducto(requisicionProductoSeleccionado
				.getProducto());
		nuevaCotizacion.setRequisicionProducto(requisicionProductoSeleccionado);
		
		boolean agregar = true;
		if (pp.isSeleccionar()) {
			for (Cotizacion item : cotizacionesList) {
				if (item.getProveedor().equals(nuevaCotizacion.getProveedor())
						&& item.getRequisicion().equals(
								nuevaCotizacion.getRequisicion())
						&& nuevaCotizacion.getProducto().equals(
								requisicionProductoSeleccionado.getProducto())) {
					agregar = false;
					break;
				}
			}
		}

		if (agregar && pp.isSeleccionar())
			cotizacionesList.add(nuevaCotizacion);
		if (!pp.isSeleccionar()) {
			for (Cotizacion item : cotizacionesList) {
				if (item.getProveedor().getNombre()
						.equals(nuevaCotizacion.getProveedor().getNombre())
						&& item.getRequisicion()
								.getFolio()
								.equals(nuevaCotizacion.getRequisicion()
										.getFolio())
						&& nuevaCotizacion
								.getProducto()
								.getNombre()
								.equals(requisicionProductoSeleccionado
										.getProducto().getNombre())) {
					cotizacionesList.remove(item);
					break;
				}
			}
		}

	}

	private List<Cotizacion> salvarCotizacion() {
		List<Cotizacion> cotizacionReturn = null;
		if (requisicionProductos != null && requisicionProductos.size() > 0) {
			cotizacionReturn = new ArrayList<Cotizacion>();
			for (RequisicionProducto item : requisicionProductos)//SALVAR CANTIDAD DE PRODUCTO
					requisicionProductoService.save(item);

			if (cotizacionesList != null && cotizacionesList.size() > 0) {
				List<Cotizacion> cotizacionesInbox = new ArrayList<Cotizacion>();
				
				//GENERAR COTIZACIONES (PROVEDORES NO DUPLICADOS) + FOLIO
				List<Cotizacion> preCotizacionConFolio = new  ArrayList<Cotizacion>();
				String folio = generarFolioCotizacion(cotizacionService.getCountRowsCotizacion());
				Long i = 1L;
				for (Cotizacion item : cotizacionesList) {
					boolean agregar = true;
					for (Cotizacion ctzConFolio : preCotizacionConFolio) {
						if(item.getProveedor().getNombre().equals(ctzConFolio.getProveedor().getNombre())){
							agregar = false;
							break;
						}
					}
					if(agregar){
						item.setFolioCotizacion(folio);
						preCotizacionConFolio.add(item);
						folio = generarFolioCotizacion(i+1);
						i ++;
					}
				}//FIN GENERAR COTIZACIONES (PROVEDORES NO DUPLICADOS) + FOLIO
				
				//SALVAR COTIZACIONES
				for (Cotizacion item : cotizacionesList) {
					Cotizacion verificarCotizacion = cotizacionService
							.getCotizacionByRequisicionProveedorAndProducto(
									item.getRequisicion(), 
									item.getProveedor(),
									item.getProducto());

					System.err.println(item.getRequisicion().getFolio() + "\t"
							+ item.getProducto().getClave() + "\t\t"
							+ item.getProveedor().getNombre());

					if (verificarCotizacion == null) {
						EstatusRequisicion estado = estatusRequisicionService
								.getByClave(StockConstants.ESTADO_COTIZACION_NUEVA);

						item.setFolioCotizacion(obtenerFolio(preCotizacionConFolio, item));
						item.setOrganizacion((Organizacion) sessionUtils
								.getFromSession(SessionUtils.FIRMA));
						item.setUsuario((Usuarios) sessionUtils
								.getFromSession(SessionUtils.USUARIO));
						item.setEstatusRequisicion(estado);
						item.setFechaEnvioCotizacion(Calendar.getInstance());
						cotizacionService.save(item);
						cotizacionesInbox.add(item);
					}
				}//FIN SALVAR COTIZACIONES
				
				//GENERAR INBOX DE COTIZACIONES
				for (Cotizacion cotizacion2 : cotizacionesInbox) {
					boolean salvar = true;
					for (Cotizacion cotizacionInbox : cotizacionReturn) {
						if(cotizacion2.getProveedor().getNombre().equals(cotizacionInbox.getProveedor().getNombre())){
							salvar = false;
							break;
						}
					}
					if(salvar){
						CotizacionInbox inbox = new CotizacionInbox();
						inbox.setLeido(false);
						inbox.setCotizacion(cotizacion2);
						inbox.setFechaRegistro(new StockUtils()
								.convertirCalendarToDate(Calendar.getInstance()));
						cotizacionInboxService.save(inbox);
						cotizacionReturn.add(cotizacion2);
					}
				}//FIN GENERAR INBOX DE COTIZACIONES
			}
		}
		return cotizacionReturn;
	}

	private String obtenerFolio(List<Cotizacion> listaCotizacionesFolio, Cotizacion cotizacionEntrada){
		String folio = ""; 
		for (Cotizacion item : listaCotizacionesFolio) {
			if(item.getProveedor().getNombre().equals(cotizacionEntrada.getProveedor().getNombre())){
				folio =item.getFolioCotizacion();
				break;
			}
		}
		return folio;
	}
	
	private Cotizacion obtenerCotizacion(Proveedor proRequisicion,
			List<Cotizacion> prosCotizaciones) {
		Cotizacion resultado = null;
		for (Cotizacion cotizacion : prosCotizaciones) {
			if (cotizacion.getProveedor().equals(proRequisicion)) {
				resultado = cotizacion;
				break;
			}
		}
		return resultado;
	}

	private String generarFolioCotizacion(Long countRows) {
		String folio = null;
		if (countRows != null) {
			folio = StockConstants.CLAVE_FOLIO_COTIZACION;
			if (String.valueOf(countRows.toString().length()).equals("1"))
				folio += "00" + countRows;
			else if (String.valueOf(countRows.toString().length()).equals("2"))
				folio += "0" + countRows;
			else if (String.valueOf(countRows.toString().length()).equals("3"))
				folio += countRows;
		}

		return folio;
	}

	@Command
	public void autorizar() {
		salvarCotizacion();
	}

	@SuppressWarnings("static-access")
	@Command
	public void guardarCambios() {
		for (RequisicionProducto item : requisicionProductos) {
			requisicionProductoService.save(item);
		}
		stockUtils.showSuccessmessage("Los cambios han sido guardados",
				Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

}
