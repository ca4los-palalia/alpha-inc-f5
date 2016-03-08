package com.cplsystems.stock.app.vm.requisicion;

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
import com.cplsystems.stock.services.AreaService;
import com.cplsystems.stock.services.CofiaPartidaGenericaService;
import com.cplsystems.stock.services.CotizacionInboxService;
import com.cplsystems.stock.services.CotizacionService;
import com.cplsystems.stock.services.EstatusRequisicionService;
import com.cplsystems.stock.services.ProductoService;
import com.cplsystems.stock.services.ProveedorProductoService;
import com.cplsystems.stock.services.RequisicionProductoService;
import com.cplsystems.stock.services.RequisicionService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;

@VariableResolver({ DelegatingVariableResolver.class })
public class concentradoVM extends RequisicionVariables {
	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	@Init
	public void init() {
		super.init();
		requisicion = new Requisicion();
		areas = areaService.getAll();
		areaBuscar = new Area();
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
			}else{
				StockUtils.showSuccessmessage(
						"No hay proveedores que surtan este producto",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
				// END nuevo codigo
			}
		}
	}

	@Command
	@NotifyChange({ "requisicionProductos" })
	public void seleccionarProveedor() {
		if (this.proveedorProducto != null) {
			for (RequisicionProducto item : this.requisicionProductos) {
				if (this.requisicionProductoSeleccionado.equals(item)) {
					item.setProveedor(this.proveedorProducto.getProveedor());
					break;
				}
			}
		}
	}

	private List<RequisicionProducto> buscarPorClaveProducto(String buscar) {
		List<RequisicionProducto> rp = null;
		Producto pr = this.productoService.getByClave(buscar);
		if (pr != null) {
			rp = this.requisicionProductoService.getByProducto(pr);
		}
		return rp;
	}

	private List<RequisicionProducto> buscarPorFolioRequisicion(String buscar) {
		List<RequisicionProducto> rp = null;
		Requisicion rq = this.requisicionService.getByFolio(buscar);
		if (rq != null) {
			rp = this.requisicionProductoService.getByRequisicion(rq);
		}
		return rp;
	}

	private List<RequisicionProducto> buscarPorPartidaGenerica(String buscar) {
		List<RequisicionProducto> rp = null;
		CofiaPartidaGenerica cpg = this.cofiaPartidaGenericaService.getByNombre(buscar);
		if (cpg != null) {
			rp = this.requisicionProductoService.getByConfiaPartidaGenerica(cpg);
		}
		return rp;
	}

	
	@Command
	@NotifyChange({ "requisicionProductos" })
	public void buscarPorArearequisicion(){
		List<RequisicionProducto> rp = null;
		
		List<Requisicion> requisicionesTemp = requisicionService.getByUnidadResponsable(areaBuscar);
		if(requisicionesTemp != null){
			requisicionProductos = new ArrayList<RequisicionProducto>();
			List<RequisicionProducto> productosTemp = new ArrayList<RequisicionProducto>();
			for (Requisicion item : requisicionesTemp) {
				productosTemp = this.requisicionProductoService.getByRequisicion(item);
				if(productosTemp != null){
					for (RequisicionProducto item2 : productosTemp) {
						requisicionProductos.add(item2);
					}
				}
			}
			if(requisicionProductos.size() == 0){
				requisicionProductos = new ArrayList<RequisicionProducto>();
				StockUtils.showSuccessmessage(
						"No se Encontraron productos en las requisiciones",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		}else{
			requisicionProductos = new ArrayList<RequisicionProducto>();
			StockUtils.showSuccessmessage(
					"No Encontraron productos para el area de " + areaBuscar.getNombre(),
					Clients.NOTIFICATION_TYPE_WARNING, Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "requisicionProductos" })
	public void filtrarProductoPorRequisicion() {
		String mensaje = "";
		if (requisicion == null) {
			requisicion = new Requisicion();
		}
		if ((requisicion.getBuscarRequisicion() != null) && (!requisicion.getBuscarRequisicion().isEmpty())) {
			requisicionProductos = buscarPorClaveProducto(requisicion.getBuscarRequisicion());
			if (requisicionProductos == null) {
				requisicionProductos = buscarPorFolioRequisicion(requisicion.getBuscarRequisicion());
				if (requisicionProductos == null) {
					requisicionProductos = buscarPorPartidaGenerica(requisicion.getBuscarRequisicion());
				}
			}
			if (requisicionProductos == null) {
				StockUtils.showSuccessmessage(
						"Tu busqueda -" + requisicion.getBuscarRequisicion() + "- no encontro resultados", "info",
						Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage(
					"No se ingreso algun parametro de busque, asegurese de escribir alguna de las formas de busqueda recomendadas",
					"info", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void removerProductoDeListaGeneralDeProductos(@BindingParam("index") Integer index) {
		if (this.requisicionProductoSeleccionado == null) {
			this.requisicionProductoSeleccionado = ((RequisicionProducto) this.requisicionProductos
					.get(index.intValue()));
		}
		this.requisicionProductoService.delete(this.requisicionProductoSeleccionado);
		this.requisicionProductos.remove(this.requisicionProductoSeleccionado);

		StockUtils.showSuccessmessage(
				"El producto -" + this.requisicionProductoSeleccionado.getProducto().getNombre()
						+ "- ha sido removido de la requisici�n -"
						+ this.requisicionProductoSeleccionado.getRequisicion().getFolio() + "-",
				"info", Integer.valueOf(0), null);
	}

	@Command
	@NotifyChange({ "*" })
	public void cancelarRequisicion() {
		EstatusRequisicion estado = this.estatusRequisicionService.getByClave("RQC");

		Requisicion rq = this.requisicionProductoSeleccionado.getRequisicion();
		rq.setEstatusRequisicion(estado);
		this.requisicionService.save(rq);

		StockUtils.showSuccessmessage("La requisici�n -"
				+ this.requisicionProductoSeleccionado.getRequisicion().getFolio() + "- ha sido cancelada", "info",
				Integer.valueOf(0), null);

		estado = this.estatusRequisicionService.getByClave("RQN");

		this.requisiciones = this.requisicionService.getByEstatusRequisicion(estado);
		this.requisicionProductos = this.requisicionProductoService.getRequisicionesConEstadoEspecifico(estado);
	}

	@NotifyChange({ "proveedorProducto" })
	@Command
	public void proveedorCheckBox(@BindingParam("index") Integer index) {
		ProveedorProducto pp = (ProveedorProducto) this.proveedorProductos.get(index.intValue());
		if (this.cotizacionesList == null) {
			this.cotizacionesList = new ArrayList();
		}
		Cotizacion nuevaCotizacion = new Cotizacion();
		nuevaCotizacion.setRequisicion(this.requisicionProductoSeleccionado.getRequisicion());

		nuevaCotizacion.setProveedor(pp.getProveedor());
		nuevaCotizacion.setProducto(this.requisicionProductoSeleccionado.getProducto());

		nuevaCotizacion.setRequisicionProducto(this.requisicionProductoSeleccionado);

		boolean agregar = true;
		if (pp.isSeleccionar()) {
			for (Cotizacion item : this.cotizacionesList) {
				if ((item.getProveedor().equals(nuevaCotizacion.getProveedor()))
						&& (item.getRequisicion().equals(nuevaCotizacion.getRequisicion()))
						&& (nuevaCotizacion.getProducto().equals(this.requisicionProductoSeleccionado.getProducto()))) {
					agregar = false;
					break;
				}
			}
		}
		if ((agregar) && (pp.isSeleccionar())) {
			this.cotizacionesList.add(nuevaCotizacion);
		}
		if (!pp.isSeleccionar()) {
			for (Cotizacion item : this.cotizacionesList) {
				if ((item.getProveedor().getNombre().equals(nuevaCotizacion.getProveedor().getNombre()))
						&& (item.getRequisicion().getFolio().equals(nuevaCotizacion.getRequisicion().getFolio()))
						&& (nuevaCotizacion.getProducto().getNombre()
								.equals(this.requisicionProductoSeleccionado.getProducto().getNombre()))) {
					this.cotizacionesList.remove(item);
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

	private String obtenerFolio(List<Cotizacion> listaCotizacionesFolio, Cotizacion cotizacionEntrada) {
		String folio = "";
		for (Cotizacion item : listaCotizacionesFolio) {
			if (item.getProveedor().getNombre().equals(cotizacionEntrada.getProveedor().getNombre())) {
				folio = item.getFolioCotizacion();
				break;
			}
		}
		return folio;
	}

	private Cotizacion obtenerCotizacion(Proveedor proRequisicion, List<Cotizacion> prosCotizaciones) {
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
			folio = "FCO";
			if (String.valueOf(countRows.toString().length()).equals("1")) {
				folio = folio + "00" + countRows;
			} else if (String.valueOf(countRows.toString().length()).equals("2")) {
				folio = folio + "0" + countRows;
			} else if (String.valueOf(countRows.toString().length()).equals("3")) {
				folio = folio + countRows;
			}
		}
		return folio;
	}

	@Command
	public void autorizar() {
		salvarCotizacion();
	}

	@Command
	public void guardarCambios() {
		for (RequisicionProducto item : this.requisicionProductos) {
			this.requisicionProductoService.save(item);
		}
		StockUtils.showSuccessmessage("Los cambios han sido guardados", "info", Integer.valueOf(0), null);
	}
}
