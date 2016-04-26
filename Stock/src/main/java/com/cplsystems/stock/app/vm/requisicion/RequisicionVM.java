package com.cplsystems.stock.app.vm.requisicion;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.domain.AplicacionExterna;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.CofiaFuenteFinanciamiento;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.CofiaProg;
import com.cplsystems.stock.domain.CofiaPy;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Posicion;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionInbox;
import com.cplsystems.stock.domain.RequisicionProducto;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.AreaService;
import com.cplsystems.stock.services.CofiaFuenteFinanciamientoService;
import com.cplsystems.stock.services.CofiaPartidaGenericaService;
import com.cplsystems.stock.services.CofiaProgService;
import com.cplsystems.stock.services.CofiaPyService;
import com.cplsystems.stock.services.ContratoService;
import com.cplsystems.stock.services.EstatusRequisicionService;
import com.cplsystems.stock.services.MailService;
import com.cplsystems.stock.services.PersonaService;
import com.cplsystems.stock.services.PosicionService;
import com.cplsystems.stock.services.PrivilegioService;
import com.cplsystems.stock.services.ProductoService;
import com.cplsystems.stock.services.ProveedorService;
import com.cplsystems.stock.services.RequisicionInboxService;
import com.cplsystems.stock.services.RequisicionProductoService;
import com.cplsystems.stock.services.RequisicionService;
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
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Window;

@VariableResolver({ DelegatingVariableResolver.class })
public class RequisicionVM extends RequisicionMetaClass {
	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	@Init
	public void init() {
		super.init();
		areaBuscar = new Area();
		contratos = contratoService.getAll();
		proveedoresLista = proveedorService.getAll();
		if (requisicion == null) {
			requisicion = new Requisicion();
		}
		requisicion.setFecha(Calendar.getInstance());
		posiciones = posicionService.getAll();

		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");

		loadItemsKeys();
		initDefaultValues();
		loadRequisionesInbox();
		
		List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
		if(privilegios != null)
			for (Privilegios privilegios2 : privilegios) {
				System.err.println(privilegios2);
			}
	}

	private void loadRequisionesInbox() {
		this.requisicionesInbox = this.requisicionInboxService
				.getAllNews((Organizacion) this.sessionUtils.getFromSession("FIRMA"));
		for (RequisicionInbox rqInbox : this.requisicionesInbox) {
			if ((rqInbox.getLeido() != null) && (!rqInbox.getLeido().booleanValue())) {
				rqInbox.setIcono("/images/toolbar/newEmail.png");
			}
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void transferirRequsicionToFormular(@BindingParam("index") Integer index) {
		if (index != null) {
			RequisicionInbox requisicionInbox = (RequisicionInbox) this.requisicionesInbox.get(index.intValue());
			if (!requisicionInbox.getLeido().booleanValue()) {
				requisicionInbox.setLeido(Boolean.valueOf(true));
				requisicionInbox.setIcono("/images/toolbar/openedEmail.png");
				this.requisicionInboxService.save(this.requisicionInboxSeleccionada);
			}
			Requisicion buscarRequisicion = requisicionInbox.getRequisicion();
			if (buscarRequisicion != null) {
				buscarRequisicion.setBuscarRequisicion(buscarRequisicion.getFolio());

				this.requisicion = buscarRequisicion;
				this.requisicionProductos = this.requisicionProductoService.getByRequisicion(buscarRequisicion);

				this.readOnly = true;
			}
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void saveChanges() {
		if (this.readOnly) {
			StockUtils.showSuccessmessage(
					"Los controles han sido desactivados para evitar la edici�n de esta requisici�npara activarlos cree una nueva requisici�n",
					"info", Integer.valueOf(3500), null);

			return;
		}
		String validacion = validarCapturaRequisicion();
		if (validacion.isEmpty()) {
			if (validateBill()) {
				if (this.estatusRequisicion == null) {
					this.estatusRequisicion = new EstatusRequisicion();
				}
				if (this.requisicion.getIdRequisicion() == null) {
					
					String validaListaProductos = validarListaProductos();
					if(validaListaProductos.equals("")){
						this.estatusRequisicion = this.estatusRequisicionService.getByClave("RQN");

						this.requisicion.setEstatusRequisicion(this.estatusRequisicion);
						this.requisicion.setOrganizacion((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

						this.requisicion.setUsuario((Usuarios) this.sessionUtils.getFromSession("usuario"));

						this.requisicion.setFecha(Calendar.getInstance());
						this.personaService.save(this.requisicion.getPersona());
						this.requisicionService.save(this.requisicion);

						RequisicionInbox inbox = new RequisicionInbox();
						inbox.setRequisicion(this.requisicion);
						inbox.setLeido(Boolean.valueOf(false));
						inbox.setFechaRegistro(this.stockUtils.convertirCalendarToDate(Calendar.getInstance()));

						this.requisicionInboxService.save(inbox);

						String productosNoGuardados = "";
						for (int i = 0; i < this.requisicionProductos.size(); i++) {
							RequisicionProducto requisicionProducto = (RequisicionProducto) this.requisicionProductos
									.get(i);

							requisicionProducto.setRequisicion(this.requisicion);
							if ((requisicionProducto.getProducto() != null)
									&& (requisicionProducto.getProducto().getIdProducto() != null)) {
								requisicionProducto.setEntregados(Long.valueOf(0L));
								requisicionProducto
										.setOrganizacion((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

								requisicionProducto.setUsuario((Usuarios) this.sessionUtils.getFromSession("usuario"));

								this.requisicionProductoService.save(requisicionProducto);
							} else {
								List<Producto> p = this.productoService
										.getByClaveNombre(requisicionProducto.getProducto().getClave());
								if (p != null) {
									requisicionProducto.setProducto((Producto) p.get(0));
									requisicionProducto.setEntregados(Long.valueOf(0L));
									requisicionProducto.setOrganizacion(organizacion);

									requisicionProducto.setUsuario(usuario);

									this.requisicionProductoService.save(requisicionProducto);
								} else {
									productosNoGuardados = productosNoGuardados + "||"
											+ requisicionProducto.getProducto().getClave() + "|| ";
								}
							}
						}
						String mensajeError = "";
						if (!productosNoGuardados.isEmpty()) {
							mensajeError = "Los siguientes artículos no se guardaron: \n" + productosNoGuardados
									+ ". Posible causa, la clave del producto que se ingreso no es la correcta";
						}

						String mensaje = "La requisición con fólio [" + requisicion.getFolio() + "] ha sído creada ";
						
						if(organizacion.getDevelopmentTool() != null){
							List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
							if(privilegios != null){
								enviarCorreos(usuario, organizacion, privilegios, mensaje, "Nueva requisicion", null);
								mensaje += " y se ha enviado un email para su notificación "; 
							}
							
						}else
							mensaje += ". No se pudo enviar un email para la notificación";
						

						StockUtils.showSuccessmessage(
								mensaje + " " + mensajeError,
								"info", Integer.valueOf(0), null);
						limpiarFormulario();
					}else
						StockUtils.showSuccessmessage(validaListaProductos, "warning", Integer.valueOf(0), null);
						
				} else {
					String validaListaProductos = validarListaProductos();
					if(validaListaProductos.equals("")){
						requisicion.setFecha(Calendar.getInstance());
						requisicionService.save(requisicion);

						String productosNoGuardados = "";
						for (int i = 0; i < requisicionProductos.size(); i++) {
							RequisicionProducto requisicionProducto = (RequisicionProducto) requisicionProductos
									.get(i);

							requisicionProducto.setRequisicion(requisicion);
							if ((requisicionProducto.getProducto() != null)
									&& (requisicionProducto.getProducto().getIdProducto() != null)) {
								requisicionProductoService.save(requisicionProducto);
							} else {
								List<Producto> p = this.productoService
										.getByClaveNombre(requisicionProducto.getProducto().getClave());
								if (p != null) {
									requisicionProducto.setProducto((Producto) p.get(0));
									this.requisicionProductoService.save(requisicionProducto);
								} else {
									productosNoGuardados = productosNoGuardados + "||"
											+ requisicionProducto.getProducto().getClave() + "|| ";
								}
							}
						}
						String mensajeError = "";
						if (!productosNoGuardados.isEmpty()) {
							mensajeError = "Los siguientes art�culos no se guardaron: \n" + productosNoGuardados
									+ ". Posible causa, la clave del producto que se ingreso no es la correcta";
						}
						StockUtils.showSuccessmessage("La requisici�n con f�lio [" + this.requisicion.getFolio()
								+ "] ha s�do actualizada. " + mensajeError, "info", Integer.valueOf(0), null);

						this.requisicion = new Requisicion();
						this.requisicionProductos = new ArrayList();
						addNewItemToBill();
					}else
						StockUtils.showSuccessmessage(validaListaProductos, "warning", Integer.valueOf(0), null);
					
				}
			}
		} else {
			StockUtils.showSuccessmessage(validacion, "warning", Integer.valueOf(0), null);
		}
	}

	private String validarCapturaRequisicion() {
		String mensaje = "";
		if (this.requisicion.getArea() != null) {
			if (this.requisicion.getCofiaProg() != null) {
				if (this.requisicion.getCofiaPy() != null) {
					if (this.requisicion.getCofiaFuenteFinanciamiento() != null) {
						if ((this.requisicion.getPersona() != null)
								&& (this.requisicion.getPersona().getNombre() != null)
								&& (!this.requisicion.getPersona().getNombre().isEmpty())
								&& (this.requisicion.getPersona().getApellidoPaterno() != null)
								&& (!this.requisicion.getPersona().getApellidoPaterno().isEmpty())
								&& (this.requisicion.getPersona().getApellidoMaterno() != null)
								&& (!this.requisicion.getPersona().getApellidoMaterno().isEmpty())) {
							if (this.requisicion.getPosicion() != null) {
								if (this.requisicion.getJustificacion() != null) {
									if (this.requisicion.getNumeroInventario() != null) {
										if ((this.requisicionProductos != null)
												&& (this.requisicionProductos.size() > 0)) {
											boolean verificar = true;
											for (RequisicionProducto item : this.requisicionProductos) {
												if ((item.getProducto() == null) || (item.getCantidad() == null)) {
													verificar = false;
													break;
												}
											}
											if (!verificar) {
												mensaje = "Es requerido agregar al menos un producto en la lista de productos";
											}
										} else {
											mensaje = "Es requerido agregar al menos un producto en la lista de productos";
										}
									} else {
										mensaje = "Es requerido ingresar el n�mero de inventario";
									}
								} else {
									mensaje = "Es requerido ingresar una justificaci�n para la requisici�n";
								}
							} else {
								mensaje = "Es requerido ingresar el puesto que ocupa "
										+ this.requisicion.getPersona().getNombre() + " "
										+ this.requisicion.getPersona().getApellidoPaterno() + " "
										+ this.requisicion.getPersona().getApellidoMaterno()
										+ " dentro de la organizaci�n";
							}
						} else {
							mensaje = "Es requerido el nombre completo del solicitante (Nombre, apellido paterno-materno)";
						}
					} else {
						mensaje = "Es requerido seleccionar una fuente de financiamiento";
					}
				} else {
					mensaje = "Es requerido seleccionar una opcion PY";
				}
			} else {
				mensaje = "Es requerido seleccionar una opcion PROG";
			}
		} else {
			mensaje = "Es requerido ingresar el �rea quien solicita la requisici�n";
		}
		return mensaje;
	}

	private String validarListaProductos(){
		String mensaje = "";
		for (RequisicionProducto item : requisicionProductos) {
			if(item.getProducto() == null){
				mensaje = "faltan productos en la lista de productos";
				break;
			}
			if(item.getCofiaPartidaGenerica() == null){
				mensaje = "falta partida generica del producto " + item.getProducto().getNombre() + " en la lista de productos";
				break;
			}else{
				if(item.getCofiaPartidaGenerica().getIdCofiaPartidaGenerica() == null){
					mensaje = "falta partida generica del producto " + item.getProducto().getNombre() + " en la lista de productos";
					break;
				}	
			}
			if(item.getCantidad() == null){
				mensaje = "falta cantidad del producto " + item.getProducto().getNombre() + "en la lista de productos";
				break;
			}
		}
		return mensaje;
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange({ "requisicionProductos", "itemsOnList", "importeTotal" })
	@Command
	public void removeElementFromBill() {
		if ((this.requisicionProductos != null) && (!this.requisicionProductos.isEmpty())
				&& (this.requisicionProductoSeleccionado != null)
				&& (this.requisicionProductos.contains(this.requisicionProductoSeleccionado))) {
			if (this.requisicion.getIdRequisicion() != null) {
				this.requisicionProductoService.delete(this.requisicionProductoSeleccionado);
			}
			this.requisicionProductos.remove(this.requisicionProductoSeleccionado);

			this.itemsOnList = Integer.valueOf(this.requisicionProductos.size());
			updateTotal();
		}
	}

	@NotifyChange({ "importeTotal", "itemsOnList" })
	@Command
	public void updateTotal() {
		if (this.requisicionProductos != null) {
			Double total = Double.valueOf(0.0D);
			for (RequisicionProducto requisicionProducto : this.requisicionProductos) {
				if (requisicionProducto.getImporte() != null) {
					total = Double.valueOf(total.doubleValue() + requisicionProducto.getImporte().floatValue());
				}
			}
			this.importeTotal = this.stockUtils.formatCurrency(total);
		}
	}

	@Command
	public void search(@BindingParam("index") Integer index) {
		if (index != null) {
			Map<String, Object> inputParams = new HashMap();
			inputParams.put("updateCommandFromItemFinder", "updateRecordFromRequisitionWithSelectedItem");

			Window productoModalView = this.stockUtils
					.createModelDialogWithParams("/modulos/productos/utils/buscarProducto.zul", inputParams);

			productoModalView.doModal();
			this.requisicionProductoSeleccionado = ((RequisicionProducto) this.requisicionProductos
					.get(index.intValue()));
		}
	}

	@GlobalCommand
	@NotifyChange({ "*" })
	public void updateRecordFromRequisitionWithSelectedItem(
			@BindingParam("productoSeleccionado") Producto productoSeleccionado) {
		if (productoSeleccionado != null) {
			if (!verifyItemsInRequisition(productoSeleccionado)) {
				if (requisicionProductoSeleccionado == null) {
					requisicionProductoSeleccionado = new RequisicionProducto();
				}
				requisicionProductoSeleccionado.setProducto(productoSeleccionado);
				requisicionProductoSeleccionado.setDescripcion(productoSeleccionado.getNombre());
			} else {
				StockUtils.showSuccessmessage("Ya existe un producto registrado con esta clave", "warning",
						Integer.valueOf(4000), null);
			}
		}
	}

	private boolean validateBill() {
		boolean continuar = true;
		for (RequisicionProducto requisicionProducto : this.requisicionProductos) {
			if (!verifyItemsInRequisition(requisicionProducto.getProducto())) {
				StockUtils.showSuccessmessage("Ya existe un pxroducto registrado con esta clave "
						+ requisicionProducto.getProducto().getClave(), "warning", Integer.valueOf(4000), null);

				continuar = true;
				break;
			}
		}
		return continuar;
	}

	private boolean verifyItemsInRequisition(Producto productoSeleccionado) {
		for (RequisicionProducto requisicionProducto : this.requisicionProductos) {
			if ((requisicionProducto.getProducto().getIdProducto() != null)
					&& (requisicionProducto.getProducto().getClave() != null) && (requisicionProducto.getProducto()
							.getClave().equalsIgnoreCase(productoSeleccionado.getClave()))) {
				return true;
			}
		}
		return false;
	}

	@Command
	@NotifyChange({ "*" })
	public void imprimirRequisicion() {
		if ((this.requisicion != null) && (this.requisicion.getIdRequisicion() != null)) {
			if (this.requisicionProductos == null) {
				this.requisicionProductos = new ArrayList();
			}
			if (this.requisicionProductos != null) {
				Organizacion org = (Organizacion) this.sessionUtils.getFromSession("FIRMA");

				File archivoLogotipo = new File(StockConstants.CARPETA_ARCHIVOS_LOGOTIPOS + org.getLogotipo());

				FileInputStream streamLogotipo = null;
				if (archivoLogotipo.exists()) {
					try {
						streamLogotipo = new FileInputStream(archivoLogotipo);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				HashMap mapa = new HashMap();
				mapa.put("folio", this.requisicion.getFolio());
				mapa.put("area", this.requisicion.getArea().getNombre());
				mapa.put("prog", this.requisicion.getCofiaProg().getNombre());
				mapa.put("py", this.requisicion.getCofiaPy().getNombre());
				mapa.put("fuente", this.requisicion.getCofiaFuenteFinanciamiento().getNombre());

				mapa.put("solicitante",
						this.requisicion.getPersona().getApellidoPaterno() + " "
								+ this.requisicion.getPersona().getApellidoMaterno() + " "
								+ this.requisicion.getPersona().getNombre());

				mapa.put("puesto", this.requisicion.getPosicion().getNombre());
				mapa.put("descripcion", this.requisicion.getAdscripcion());
				mapa.put("justificacion", this.requisicion.getJustificacion());
				mapa.put("NoInventario", this.requisicion.getNumeroInventario());
				mapa.put("logotipo", streamLogotipo);
				mapa.put("nombreEmpresa", org.getNombre());

				List<HashMap> listaHashsParametros = new ArrayList();
				listaHashsParametros.add(mapa);

				List<AplicacionExterna> aplicaciones = new ArrayList();
				AplicacionExterna aplicacion = new AplicacionExterna();
				aplicacion.setNombre("PDFXCview");
				aplicaciones.add(aplicacion);
				StockUtils.showSuccessmessage(
						generarRequisicionJasper(listaHashsParametros, aplicaciones, this.requisicionProductos), "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("No existe alg�n resultado de b�squeda para generar el reporte (PDF)",
						"error", Integer.valueOf(0), null);
			}
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void seleccionarOpcionBuscarArea() {
		this.requisicion.setBuscarRequisicion("");
	}

	@Command
	@NotifyChange({ "*" })
	public void selectedResultadoRequisiciones() {
		if (this.requisicionProductos == null) {
			this.requisicionProductos = new ArrayList();
		}
		this.requisicionProductos = this.requisicionProductoService.getByRequisicion(this.requisicion);
	}

	private List<Privilegios> getEmailsUsuariosCotizacion() {
		List<Privilegios> usuarios = this.privilegioService.getUsuariosByPrivilegio(UserPrivileges.COTIZAR_AUTORIZAR);

		return usuarios;
	}

}
