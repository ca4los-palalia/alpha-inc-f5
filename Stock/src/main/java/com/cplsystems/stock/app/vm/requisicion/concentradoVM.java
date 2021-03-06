package com.cplsystems.stock.app.vm.requisicion;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Privilegios;
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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
	}

	@Command
	@NotifyChange("proveedorProductos")
	public void cargarProveedoresProducto() {
		if (requisicionProductoSeleccionado != null) {
			if (requisicionProductoSeleccionado.getProducto() != null)
				proveedorProductos = proveedorProductoService
						.getByProducto(requisicionProductoSeleccionado.getProducto());

			if (proveedorProductos != null) {// nuevo codigo
				for (ProveedorProducto item : proveedorProductos) {
					Cotizacion itemCotizacion = cotizacionService.getCotizacionByRequisicionProveedorAndProducto(
							requisicionProductoSeleccionado.getRequisicion(), item.getProveedor(), item.getProducto());
					if (itemCotizacion != null)
						item.setSeleccionar(true);

					for (Cotizacion item2 : cotizacionesList) {
						if (item.getProducto().getClave().equals(item2.getProducto().getClave())
								&& item.getProveedor().getNombre().equals(item2.getProveedor().getNombre())) {
							item.setSeleccionar(true);
							break;
						}
					}
				}
			} else {
				StockUtils.showSuccessmessage("No hay proveedores que surtan este producto",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
				// END nuevo codigo
			}
		}
	}

	@Command
	@NotifyChange({ "requisicionProductos" })
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
		if (pr != null) {
			rp = requisicionProductoService.getByProducto(pr);
		}
		return rp;
	}

	private List<RequisicionProducto> buscarPorFolioRequisicion(String buscar) {
		List<RequisicionProducto> rp = null;
		Requisicion rq = requisicionService.getByFolio(buscar);
		if (rq != null) {
			rp = requisicionProductoService.getByRequisicion(rq);
		}
		return rp;
	}

	private List<RequisicionProducto> buscarPorPartidaGenerica(String buscar) {
		List<RequisicionProducto> rp = null;
		CofiaPartidaGenerica cpg = cofiaPartidaGenericaService.getByNombre(buscar);
		if (cpg != null) {
			rp = requisicionProductoService.getByConfiaPartidaGenerica(cpg);
		}
		return rp;
	}

	@Command
	@NotifyChange({ "requisicionProductos" })
	public void buscarPorArearequisicion() {
		List<RequisicionProducto> rp = null;

		List<Requisicion> requisicionesTemp = requisicionService.getByUnidadResponsable(areaBuscar);
		if (requisicionesTemp != null) {
			requisicionProductos = new ArrayList<RequisicionProducto>();
			List<RequisicionProducto> productosTemp = new ArrayList<RequisicionProducto>();
			for (Requisicion item : requisicionesTemp) {
				productosTemp = requisicionProductoService.getByRequisicion(item);
				if (productosTemp != null) {
					for (RequisicionProducto item2 : productosTemp) {
						requisicionProductos.add(item2);
					}
				}
			}
			if (requisicionProductos.size() == 0) {
				requisicionProductos = new ArrayList<RequisicionProducto>();
				StockUtils.showSuccessmessage("No se Encontraron productos en las requisiciones",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else {
			requisicionProductos = new ArrayList<RequisicionProducto>();
			StockUtils.showSuccessmessage("No Encontraron productos para el area de " + areaBuscar.getNombre(),
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
					Clients.NOTIFICATION_TYPE_INFO, 0, null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void removerProductoDeListaGeneralDeProductos(@BindingParam("index") Integer index) {
		if (requisicionProductoSeleccionado == null) {
			requisicionProductoSeleccionado = ((RequisicionProducto) requisicionProductos
					.get(index.intValue()));
		}
		requisicionProductoService.delete(requisicionProductoSeleccionado);
		requisicionProductos.remove(requisicionProductoSeleccionado);

		StockUtils.showSuccessmessage(
				"El producto -" + requisicionProductoSeleccionado.getProducto().getNombre()
						+ "- ha sido removido de la requisici�n -"
						+ requisicionProductoSeleccionado.getRequisicion().getFolio() + "-",
				Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

	@Command
	@NotifyChange({ "*" })
	public void cancelarRequisicion() {
		EstatusRequisicion estado = estatusRequisicionService.getByClave("RQC");

		Requisicion rq = requisicionProductoSeleccionado.getRequisicion();
		rq.setEstatusRequisicion(estado);
		requisicionService.save(rq);

		StockUtils.showSuccessmessage("La requisici�n -"
				+ requisicionProductoSeleccionado.getRequisicion().getFolio() + "- ha sido cancelada", "info",
				Integer.valueOf(0), null);

		estado = estatusRequisicionService.getByClave("RQN");

		requisiciones = requisicionService.getByEstatusRequisicion(estado);
		requisicionProductos = requisicionProductoService.getRequisicionesConEstadoEspecifico(estado);
	}

	@NotifyChange({ "proveedorProducto" })
	@Command
	public void proveedorCheckBox(@BindingParam("index") Integer index) {
		ProveedorProducto pp = (ProveedorProducto) proveedorProductos.get(index.intValue());
		if (cotizacionesList == null) {
			cotizacionesList = new ArrayList();
		}
		Cotizacion nuevaCotizacion = new Cotizacion();
		nuevaCotizacion.setRequisicion(requisicionProductoSeleccionado.getRequisicion());

		nuevaCotizacion.setProveedor(pp.getProveedor());
		nuevaCotizacion.setProducto(requisicionProductoSeleccionado.getProducto());

		nuevaCotizacion.setRequisicionProducto(requisicionProductoSeleccionado);

		boolean agregar = true;
		if (pp.isSeleccionar()) {
			for (Cotizacion item : cotizacionesList) {
				if ((item.getProveedor().equals(nuevaCotizacion.getProveedor()))
						&& (item.getRequisicion().equals(nuevaCotizacion.getRequisicion()))
						&& (nuevaCotizacion.getProducto().equals(requisicionProductoSeleccionado.getProducto()))) {
					agregar = false;
					break;
				}
			}
		}
		if ((agregar) && (pp.isSeleccionar())) {
			cotizacionesList.add(nuevaCotizacion);
		}
		if (!pp.isSeleccionar()) {
			for (Cotizacion item : cotizacionesList) {
				if ((item.getProveedor().getNombre().equals(nuevaCotizacion.getProveedor().getNombre()))
						&& (item.getRequisicion().getFolio().equals(nuevaCotizacion.getRequisicion().getFolio()))
						&& (nuevaCotizacion.getProducto().getNombre()
								.equals(requisicionProductoSeleccionado.getProducto().getNombre()))) {
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
			for (RequisicionProducto item : requisicionProductos)// SALVAR
																	// CANTIDAD
																	// DE
																	// PRODUCTO
				requisicionProductoService.save(item);

			if (cotizacionesList != null && cotizacionesList.size() > 0) {
				List<Cotizacion> cotizacionesInbox = new ArrayList<Cotizacion>();

				// GENERAR COTIZACIONES (PROVEDORES NO DUPLICADOS) + FOLIO
				List<Cotizacion> preCotizacionConFolio = new ArrayList<Cotizacion>();
				String folio = generarFolioCotizacion(cotizacionService.getCountRowsCotizacion());
				Long i = 1L;
				for (Cotizacion item : cotizacionesList) {
					boolean agregar = true;
					for (Cotizacion ctzConFolio : preCotizacionConFolio) {
						if (item.getProveedor().getNombre().equals(ctzConFolio.getProveedor().getNombre())) {
							agregar = false;
							break;
						}
					}
					if (agregar) {
						item.setFolioCotizacion(folio);
						preCotizacionConFolio.add(item);
						folio = generarFolioCotizacion(i + 1);
						i++;
					}
				} // FIN GENERAR COTIZACIONES (PROVEDORES NO DUPLICADOS) + FOLIO

				// SALVAR COTIZACIONES
				for (Cotizacion item : cotizacionesList) {
					Cotizacion verificarCotizacion = cotizacionService.getCotizacionByRequisicionProveedorAndProducto(
							item.getRequisicion(), item.getProveedor(), item.getProducto());

					if (verificarCotizacion == null) {
						EstatusRequisicion estado = estatusRequisicionService
								.getByClave(StockConstants.ESTADO_COTIZACION_NUEVA);

						item.setFolioCotizacion(obtenerFolio(preCotizacionConFolio, item));
						item.setOrganizacion((Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA));
						item.setUsuario((Usuarios) sessionUtils.getFromSession(SessionUtils.USUARIO));
						item.setEstatusRequisicion(estado);
						item.setFechaEnvioCotizacion(Calendar.getInstance());
						cotizacionService.save(item);
						cotizacionesInbox.add(item);
					}
				} // FIN SALVAR COTIZACIONES

				// GENERAR INBOX DE COTIZACIONES
				for (Cotizacion cotizacion2 : cotizacionesInbox) {
					boolean salvar = true;
					for (Cotizacion cotizacionInbox : cotizacionReturn) {
						if (cotizacion2.getProveedor().getNombre().equals(cotizacionInbox.getProveedor().getNombre())) {
							salvar = false;
							break;
						}
					}
					if (salvar) {
						CotizacionInbox inbox = new CotizacionInbox();
						inbox.setLeido(false);
						inbox.setCotizacion(cotizacion2);
						inbox.setFechaRegistro(new StockUtils().convertirCalendarToDate(Calendar.getInstance()));
						cotizacionInboxService.save(inbox);
						cotizacionReturn.add(cotizacion2);
					}
				} // FIN GENERAR INBOX DE COTIZACIONES
				
				StockUtils.showSuccessmessage("Se han generado " + cotizacionesList.size() + " cotizaciones" , Clients.NOTIFICATION_TYPE_INFO, 0, null);
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
		for (RequisicionProducto item : requisicionProductos) {
			requisicionProductoService.save(item);
		}
		StockUtils.showSuccessmessage("Los cambios han sido guardados", Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

	


}
