/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionInbox;
import com.cplsystems.stock.domain.RequisicionProducto;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class RequisicionVM extends RequisicionMetaClass {

	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	@Init
	public void init() {
		super.init();
		disabledBuscadorFolio = true;
		disabledBuscadorArea = true;
		areaBuscar = new Area();

		if (requisicion == null)
			requisicion = new Requisicion();

		requisicion.setFecha(Calendar.getInstance());
		posiciones = posicionService.getAll();

		loadItemsKeys();
		initDefaultValues();
		loadRequisionesInbox();
	}

	private void loadRequisionesInbox() {
		requisicionesInbox = requisicionInboxService
				.getAllNews((Organizacion) sessionUtils
						.getFromSession(SessionUtils.FIRMA));
		for (RequisicionInbox rqInbox : requisicionesInbox) {
			if (rqInbox.getLeido() != null && !rqInbox.getLeido()) {
				rqInbox.setIcono(RequisicionInbox.NUEVO);
			}
		}
	}

	private void loadItemsKeys() {
		productosTemporalModel = productoService.getAllKeys();
		if (productosTemporalModel != null) {
			productosModel = new SimpleListModel<String>(productosTemporalModel);
		}
	}

	@NotifyChange("*")
	@Command
	public void transferirRequsicionToFormular(
			@BindingParam("index") Integer index) {
		if (index != null) {
			RequisicionInbox requisicionInbox = requisicionesInbox.get(index);
			if (!requisicionInbox.getLeido()) {
				requisicionInbox.setLeido(true);
				requisicionInbox.setIcono(RequisicionInbox.LEIDO);
				requisicionInboxService.save(requisicionInboxSeleccionada);
			}
			Requisicion buscarRequisicion = requisicionInbox.getRequisicion();
			if (buscarRequisicion != null) {
				buscarRequisicion.setBuscarRequisicion(buscarRequisicion
						.getFolio());
				requisicion = buscarRequisicion;
				requisicionProductos = requisicionProductoService
						.getByRequisicion(buscarRequisicion);
				readOnly = true;
			}
		}
	}

	@SuppressWarnings("static-access")
	@NotifyChange("*")
	@Command
	public void saveChanges() {
		if (readOnly) {
			stockUtils
					.showSuccessmessage(
							"Los controles han sido desactivados para evitar la edición de esta requisición"
									+ "para activarlos cree una nueva requisición",
							Clients.NOTIFICATION_TYPE_INFO, 3500, null);
			return;
		}
		String validacion = validarCapturaRequisicion();
		if (validacion.isEmpty()) {
			if (validateBill()) {
				if (estatusRequisicion == null)
					estatusRequisicion = new EstatusRequisicion();

				if (requisicion.getIdRequisicion() == null) {// NUEVO REGISTRO
					estatusRequisicion = estatusRequisicionService
							.getByClave(StockConstants.ESTADO_REQUISICION_NUEVA);
					requisicion.setEstatusRequisicion(estatusRequisicion);
					requisicion.setOrganizacion((Organizacion) sessionUtils
							.getFromSession(SessionUtils.FIRMA));
					requisicion.setUsuario((Usuarios) sessionUtils
							.getFromSession(SessionUtils.USUARIO));
					requisicion.setFecha(Calendar.getInstance());
					personaService.save(requisicion.getPersona());
					requisicionService.save(requisicion);

					RequisicionInbox inbox = new RequisicionInbox();
					inbox.setRequisicion(requisicion);
					inbox.setLeido(false);
					inbox.setFechaRegistro(stockUtils
							.convertirCalendarToDate(Calendar.getInstance()));
					requisicionInboxService.save(inbox);

					String productosNoGuardados = "";
					for (int i = 0; i < requisicionProductos.size(); i++) {
						RequisicionProducto requisicionProducto = requisicionProductos
								.get(i);
						requisicionProducto.setRequisicion(requisicion);

						if (requisicionProducto.getProducto() != null
								&& requisicionProducto.getProducto()
										.getIdProducto() != null) {
							requisicionProducto.setEntregados(0L);
							requisicionProducto.setOrganizacion((Organizacion) sessionUtils
									.getFromSession(SessionUtils.FIRMA));
							requisicionProducto.setUsuario((Usuarios) sessionUtils
									.getFromSession(SessionUtils.USUARIO));
							requisicionProductoService
									.save(requisicionProducto);
						} else {// INTENTAR SALVAR
							List<Producto> p = productoService
									.getByClaveNombre(requisicionProducto
											.getProducto().getClave());
							if (p != null) {
								requisicionProducto.setProducto(p.get(0));
								requisicionProducto.setEntregados(0L);
								requisicionProducto.setOrganizacion((Organizacion) sessionUtils
										.getFromSession(SessionUtils.FIRMA));
								requisicionProducto.setUsuario((Usuarios) sessionUtils
										.getFromSession(SessionUtils.USUARIO));
								requisicionProductoService
										.save(requisicionProducto);
							} else
								productosNoGuardados += "||"
										+ requisicionProducto.getProducto()
												.getClave() + "|| ";
						}

					}
					String mensajeError = "";
					if (!productosNoGuardados.isEmpty()) {
						mensajeError = "Los siguientes artículos no se guardaron: \n"
								+ productosNoGuardados
								+ ". Posible causa, la clave del producto que se ingreso no es la correcta";
					}

					stockUtils.showSuccessmessage(
									"La requisición con fólio ["
											+ requisicion.getFolio()
											+ "] ha sído creada y se ha enviado un email para su notificación "
											+ mensajeError,
									Clients.NOTIFICATION_TYPE_INFO, 0, null);
					List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
					for (Privilegios privilegio : privilegios) {
						if (privilegio.getUsuarios().getPersona().getContacto() != null) {
							mailService.sendMail(
									privilegio.getUsuarios().getPersona()
											.getContacto().getEmail()
											.getEmail(),
									"csr.plz@gmail.com",
									"Nueva requisición",
									"La requisición con fólio ["
											+ requisicion.getFolio()
											+ "] ha sído creada. ");
						}
					}
					limpiarFormulario();
				} else {// ACTUALIZACIOND E REQUISICION
					requisicion.setFecha(Calendar.getInstance());
					requisicionService.save(requisicion);

					String productosNoGuardados = "";

					for (int i = 0; i < requisicionProductos.size(); i++) {
						RequisicionProducto requisicionProducto = requisicionProductos
								.get(i);
						requisicionProducto.setRequisicion(requisicion);

						if (requisicionProducto.getProducto() != null
								&& requisicionProducto.getProducto()
										.getIdProducto() != null)
							requisicionProductoService
									.save(requisicionProducto);
						else {// INTENTAR SALVAR
							List<Producto> p = productoService
									.getByClaveNombre(requisicionProducto
											.getProducto().getClave());
							if (p != null) {
								requisicionProducto.setProducto(p.get(0));
								requisicionProductoService
										.save(requisicionProducto);
							} else
								productosNoGuardados += "||"
										+ requisicionProducto.getProducto()
												.getClave() + "|| ";
						}

					}
					String mensajeError = "";
					if (!productosNoGuardados.isEmpty()) {
						mensajeError = "Los siguientes artículos no se guardaron: \n"
								+ productosNoGuardados
								+ ". Posible causa, la clave del producto que se ingreso no es la correcta";
					}

					stockUtils.showSuccessmessage("La requisición con fólio ["
							+ requisicion.getFolio()
							+ "] ha sído actualizada. " + mensajeError,
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
					requisicion = new Requisicion();
					requisicionProductos = new ArrayList<RequisicionProducto>();
					addNewItemToBill();
				}

			}
		} else
			stockUtils.showSuccessmessage(validacion,
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	private String validarCapturaRequisicion() {
		String mensaje = "";

		if (requisicion.getArea() != null) {
			if (requisicion.getCofiaProg() != null) {
				if (requisicion.getCofiaPy() != null) {
					if (requisicion.getCofiaFuenteFinanciamiento() != null) {
						if (requisicion.getPersona() != null
								&& ((requisicion.getPersona().getNombre() != null && !requisicion
										.getPersona().getNombre().isEmpty())
										&& (requisicion.getPersona()
												.getApellidoPaterno() != null && !requisicion
												.getPersona()
												.getApellidoPaterno().isEmpty()) && (requisicion
										.getPersona().getApellidoMaterno() != null && !requisicion
										.getPersona().getApellidoMaterno()
										.isEmpty()))) {
							if (requisicion.getPosicion() != null) {
								if (requisicion.getJustificacion() != null) {
									if (requisicion.getNumeroInventario() != null) {

										if (requisicionProductos != null
												&& requisicionProductos.size() > 0) {
											boolean verificar = true;

											for (RequisicionProducto item : requisicionProductos) {
												if (item.getProducto() == null
														|| item.getCantidad() == null) {
													verificar = false;
													break;
												}
											}
											if (!verificar)
												mensaje = "Es requerido agregar al menos un producto en la lista de productos";
										} else
											mensaje = "Es requerido agregar al menos un producto en la lista de productos";

									} else
										mensaje = "Es requerido ingresar el número de inventario";
								} else
									mensaje = "Es requerido ingresar una justificación para la requisición";
							} else
								mensaje = "Es requerido ingresar el puesto que ocupa " +requisicion.getPersona()
										.getNombre() + " "+ requisicion.getPersona()
										.getApellidoPaterno() + " " +requisicion.getPersona()
										.getApellidoMaterno() + " dentro de la organización";
						} else 
							mensaje = "Es requerido el nombre completo del solicitante (Nombre, apellido paterno-materno)";
					} else
						mensaje = "Es requerido seleccionar una fuente de financiamiento";
				} else
					mensaje = "Es requerido seleccionar una opcion PY";
			} else
				mensaje = "Es requerido seleccionar una opcion PROG";
		} else
			mensaje = "Es requerido ingresar el área quien solicita la requisición";
		return mensaje;
	}

	@NotifyChange("*")
	private void initDefaultValues() {
		cofiaPartidaGenericas = cofiaPartidaGenericaService.getAll();
		cofiaProgs = cofiaProgService.getAll();
		cofiaPys = cofiaPyService.getAll();
		cofiaFuenteFinanciamientos = cofiaFuenteFinanciamientoService.getAll();
		areas = areaService.getAll();
		requisicion.setPersona(new Persona());

		if (areas != null && areas.size() > 0) {
			requisicion.setArea(areas.get(0));
		}

		if (cofiaProgs != null && cofiaProgs.size() > 0) {
			requisicion.setCofiaProg(cofiaProgs.get(0));
		}

		if (cofiaPys != null && cofiaPys.size() > 0) {
			requisicion.setCofiaPy(cofiaPys.get(0));
		}

		if (cofiaFuenteFinanciamientos != null
				&& cofiaFuenteFinanciamientos.size() > 0) {
			requisicion.setCofiaFuenteFinanciamiento(cofiaFuenteFinanciamientos
					.get(0));
		}

		if (posiciones != null && posiciones.size() > 0) {
			requisicion.setPosicion(posiciones.get(0));
		}

		addNewItemToBill();
		requisicion.setFolio(StockConstants.CLAVE_FOLIO_REQUISICION
				+ requisicionService.getUltimoFolio());

	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange({ "requisicionProductos", "itemsOnList" })
	@Command
	public void addNewItemToBill() {
		RequisicionProducto objeto = new RequisicionProducto();
		objeto.setCofiaPartidaGenerica(new CofiaPartidaGenerica());

		if (requisicionProductos == null)
			requisicionProductos = new ArrayList<RequisicionProducto>();
		requisicionProductos.add(objeto);
		itemsOnList = requisicionProductos.size();
	}

	@NotifyChange({ "requisicionProductos", "itemsOnList", "importeTotal" })
	@Command
	public void removeElementFromBill() {
		if (requisicionProductos != null && !requisicionProductos.isEmpty()) {
			if (requisicionProductoSeleccionado != null) {
				if (requisicionProductos
						.contains(requisicionProductoSeleccionado)) {
					
					if(requisicion.getIdRequisicion() != null)
						requisicionProductoService.delete(requisicionProductoSeleccionado);
					
					requisicionProductos.remove(requisicionProductoSeleccionado);
					itemsOnList = requisicionProductos.size();
					updateTotal();
				}
			}
		}
	}

	@NotifyChange({ "importeTotal", "itemsOnList" })
	@Command
	public void updateTotal() {
		if (requisicionProductos != null) {
			Double total = 0.0;
			for (RequisicionProducto requisicionProducto : requisicionProductos) {
				if (requisicionProducto.getImporte() != null) {
					total += requisicionProducto.getImporte();
				}
			}
			importeTotal = stockUtils.formatCurrency(total);
		}
	}

	@Command
	public void search(@BindingParam("index") Integer index) {
		if (index != null) {
			Map<String, Object> inputParams = new HashMap<String, Object>();
			inputParams.put(REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE,
					"updateRecordFromRequisitionWithSelectedItem");
			Window productoModalView = stockUtils.createModelDialogWithParams(
					StockConstants.MODAL_VIEW_PRODUCTOS, inputParams);
			productoModalView.doModal();
			requisicionProductoSeleccionado = requisicionProductos.get(index
					.intValue());
		}

	}

	@SuppressWarnings("static-access")
	@GlobalCommand
	@NotifyChange("*")
	public void updateRecordFromRequisitionWithSelectedItem(
			@BindingParam("productoSeleccionado") Producto productoSeleccionado) {
		if (productoSeleccionado != null) {
			if (!verifyItemsInRequisition(productoSeleccionado)) {
				if (requisicionProductoSeleccionado == null)
					requisicionProductoSeleccionado = new RequisicionProducto();
				requisicionProductoSeleccionado
						.setProducto(productoSeleccionado);
			} else {
				stockUtils.showSuccessmessage(
						"Ya existe un producto registrado con esta clave",
						Clients.NOTIFICATION_TYPE_WARNING, 4000, null);
			}
		}
	}

	@SuppressWarnings("static-access")
	private boolean validateBill() {
		boolean continuar = true;
		for (RequisicionProducto requisicionProducto : requisicionProductos) {
			if (!verifyItemsInRequisition(requisicionProducto.getProducto())) {
				stockUtils.showSuccessmessage(
						"Ya existe un pxroducto registrado con esta clave "
								+ requisicionProducto.getProducto().getClave(),
						Clients.NOTIFICATION_TYPE_WARNING, 4000, null);
				continuar = true;
				break;
			}
		}
		return continuar;
	}

	private boolean verifyItemsInRequisition(Producto productoSeleccionado) {
		for (RequisicionProducto requisicionProducto : requisicionProductos) {
			if (requisicionProducto.getProducto().getIdProducto() != null
					&& requisicionProducto.getProducto().getClave() != null
					&& requisicionProducto.getProducto().getClave()
							.equalsIgnoreCase(productoSeleccionado.getClave())) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void buscarRequisicionFolio() {

		if (requisicion != null
				&& (requisicion.getBuscarRequisicion() != null && !requisicion
						.getBuscarRequisicion().isEmpty())) {
			Requisicion buscarRequisicion = requisicionService
					.getByFolio(requisicion.getBuscarRequisicion());

			if (buscarRequisicion != null) {
				requisicion = buscarRequisicion;
				readOnly = false;
				requisicionProductos = requisicionProductoService.getByRequisicion(buscarRequisicion);
				stockUtils.showSuccessmessage(
						"Requisicion con folio [" + requisicion.getBuscarRequisicion() + "] ha sido encontrada",
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} else
				stockUtils.showSuccessmessage(
						"No se encontro alguna coincidencia con la busqueda ["
								+ requisicion.getBuscarRequisicion() + "]",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		} else
			stockUtils
					.showSuccessmessage(
							"el campo de busqueda es requerido. asegurece de ingresar alguna palabra",
							Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void buscarRequisicionArea() {
		if (areaBuscar != null) {
			requisiciones = requisicionService
					.getByUnidadResponsable(areaBuscar);
			if (requisiciones != null) {
				String mensaje = "";
				if (requisiciones.size() == 1)
					mensaje = requisiciones.size() + " encontrada";
				else
					mensaje = requisiciones.size() + " encontradas";

				readOnly = false;
				stockUtils.showSuccessmessage("Requisiciones del Area(UR) ["
						+ areaBuscar.getNombre() + "]: " + mensaje,
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} else
				stockUtils.showSuccessmessage(
						"No se encontraron Requisiciones del Area(UR) ["
								+ areaBuscar.getNombre() + "]",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
	}

	@Command
	@NotifyChange("*")
	public void limpiarFormulario() {
		requisicionProductos = new ArrayList<RequisicionProducto>();
		requisicion = new Requisicion();
		loadItemsKeys();
		initDefaultValues();
		readOnly = false;
		requisiciones = new ArrayList<Requisicion>();
		requisicion.setFolio(StockConstants.CLAVE_FOLIO_REQUISICION
				+ requisicionService.getUltimoFolio());
	}

	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	@Command
	@NotifyChange("*")
	public void imprimirRequisicion() {

		if (requisicion != null && requisicion.getIdRequisicion() != null) {
			if (requisicionProductos == null)
				requisicionProductos = new ArrayList<RequisicionProducto>();

			if (requisicionProductos != null) {
				
				Organizacion org = (Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA);
				File archivoLogotipo = new File(StockConstants.CARPETA_ARCHIVOS_LOGOTIPOS + org.getLogotipo());
				FileInputStream streamLogotipo =  null;
				if(archivoLogotipo.exists()){
					try {
						streamLogotipo = new FileInputStream(archivoLogotipo);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				
				HashMap mapa = new HashMap();
				mapa.put("folio",requisicion.getFolio());
				mapa.put("area",requisicion.getArea().getNombre());
				mapa.put("prog",requisicion.getCofiaProg().getNombre());
				mapa.put("py",requisicion.getCofiaPy().getNombre());
				mapa.put("fuente",requisicion.getCofiaFuenteFinanciamiento().getNombre());
				mapa.put("solicitante",requisicion.getPersona().getApellidoPaterno()
						+ " " + requisicion.getPersona().getApellidoMaterno()
						+ " " + requisicion.getPersona().getNombre());
				mapa.put("puesto",requisicion.getPosicion().getNombre());
				mapa.put("descripcion",requisicion.getAdscripcion());
				mapa.put("justificacion",requisicion.getJustificacion());
				mapa.put("NoInventario",requisicion.getNumeroInventario());
				mapa.put("logotipo", streamLogotipo);
				mapa.put("nombreEmpresa",org.getNombre());
								
				List<HashMap> listaHashsParametros = new ArrayList<HashMap>();
				listaHashsParametros.add(mapa);

				List<AplicacionExterna> aplicaciones = new ArrayList<AplicacionExterna>();
				AplicacionExterna aplicacion = new AplicacionExterna();
				aplicacion.setNombre("PDFXCview");
				aplicaciones.add(aplicacion);
				stockUtils.showSuccessmessage(
						generarRequisicionJasper(listaHashsParametros,
								aplicaciones, requisicionProductos),
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} else {
				stockUtils
						.showSuccessmessage(
								"No existe algún resultado de búsqueda para generar el reporte (PDF)",
								Clients.NOTIFICATION_TYPE_ERROR, 0, null);
			}
		}
	}

	@Command
	@NotifyChange("*")
	public void seleccionarOpcionBuscarArea() {
		disabledBuscadorFolio = true;
		disabledBuscadorArea = false;
		requisicion.setBuscarRequisicion("");
		;
	}

	@Command
	@NotifyChange("*")
	public void seleccionarOpcionBuscarFolio() {
		areaBuscar = new Area();
		disabledBuscadorFolio = false;
		disabledBuscadorArea = true;
	}

	@Command
	@NotifyChange("*")
	public void selectedResultadoRequisiciones() {
		if (requisicionProductos == null)
			requisicionProductos = new ArrayList<RequisicionProducto>();
		requisicionProductos = requisicionProductoService
				.getByRequisicion(requisicion);
	}

	private List<Privilegios> getEmailsUsuariosCotizacion() {
		List<Privilegios> usuarios = privilegioService
				.getUsuariosByPrivilegio(UserPrivileges.COTIZAR_AUTORIZAR);
		return usuarios;
	}
}
