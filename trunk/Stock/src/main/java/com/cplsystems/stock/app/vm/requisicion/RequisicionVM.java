/**
 * 
 */
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
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionInbox;
import com.cplsystems.stock.domain.RequisicionProducto;

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
		areas = areaService.getAll();
		if (requisicion == null)
			requisicion = new Requisicion();

		requisicion.setFecha(Calendar.getInstance());
		posiciones = posicionService.getAll();

		loadItemsKeys();
		initDefaultValues();

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
	@Command
	public void saveChanges() {
		if (readOnly) {
			StockUtils
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
							.getByClave("RQ");
					requisicion.setEstatusRequisicion(estatusRequisicion);
					requisicion.setFecha(Calendar.getInstance());
					personaService.save(requisicion.getPersona());
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

					stockUtils.showSuccessmessage("La requisición con fólio -"
							+ requisicion.getFolio() + "- ha sído creada. "
							+ mensajeError, Clients.NOTIFICATION_TYPE_INFO, 0,
							null);
					requisicion = new Requisicion();
					requisicionProductos = new ArrayList<RequisicionProducto>();
					addNewItemToBill();
				} else {// ACTUALIZACIOND E REQUISICION
					requisicion.setFecha(Calendar.getInstance());
					requisicionService.save(requisicion);

					String productosNoGuardados = "";

					// ELIMINAR ANTERIORES PRODUCTOS DE ESTA REQUISICION
					/*
					 * List<RequisicionProducto> rpl =
					 * requisicionProductoService.getByRequisicion(requisicion);
					 * if(rpl != null){ for (RequisicionProducto item : rpl) {
					 * requisicionProductoService.delete(item); } }
					 */// ------------------------------

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

					stockUtils.showSuccessmessage("La requisición con fólio -"
							+ requisicion.getFolio()
							+ "- ha sído actualizada. " + mensajeError,
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

	private void initDefaultValues() {
		cofiaPartidaGenericas = cofiaPartidaGenericaService.getAll();
		cofiaPartidaGenericas = cofiaPartidaGenericaService.getAll();
		cofiaProgs = cofiaProgService.getAll();
		cofiaPys = cofiaPyService.getAll();
		cofiaFuenteFinanciamientos = cofiaFuenteFinanciamientoService.getAll();

		requisicion.setPersona(new Persona());

		// EstatusRequisicion estatus =
		// estatusRequisicionService.getByClave("RQ");
		// requisiciones = requisicionService.getByEstatusRequisicion(estatus);

		addNewItemToBill();
		String folio = "F" + requisicionService.getUltimoFolio();
		requisicion.setFolio(folio);

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
				requisicionProductos = requisicionProductoService
						.getByRequisicion(buscarRequisicion);
			} else
				stockUtils.showSuccessmessage(
						"No se encontro alguna coincidencia con la busqueda -"
								+ requisicion.getBuscarRequisicion() + "-",
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
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
			areaBuscar = areaService.getById(areaBuscar.getIdArea());

			if (areaBuscar != null) {
				if (requisicion == null)
					requisicion = new Requisicion();

				requisicion = requisicionService
						.getByUnidadResponsable(areaBuscar);

				if (requisicion != null)
					requisicionProductos = requisicionProductoService
							.getByRequisicion(requisicion);
				else
					stockUtils.showSuccessmessage(
							"No se encontró la requisición con búsqueda de área(UR) -"
									+ areaBuscar.getNombre() + "-",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
			}
		} else {
			stockUtils
					.showSuccessmessage(
							"No existen áreas(UR) para realizar la búsqueda sobre este tipo",
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
	}

	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	@Command
	@NotifyChange("*")
	public void imprimirRequisicion() {

		if (requisicion != null && requisicion.getIdRequisicion() != null) {
			if (requisicionProductos == null)
				requisicionProductos = new ArrayList<RequisicionProducto>();

			if (requisicionProductos != null) {

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

}
