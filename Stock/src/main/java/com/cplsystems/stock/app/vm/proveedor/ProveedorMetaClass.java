package com.cplsystems.stock.app.vm.proveedor;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.proveedor.utils.MenuButtonsActivated;
import com.cplsystems.stock.domain.Banco;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.CuentaPago;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Giro;
import com.cplsystems.stock.domain.Moneda;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Pais;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;
import com.cplsystems.stock.domain.Telefono;
import com.cplsystems.stock.services.BancoService;
import com.cplsystems.stock.services.ContactoService;
import com.cplsystems.stock.services.ContratoService;
import com.cplsystems.stock.services.CuentasPagoService;
import com.cplsystems.stock.services.DireccionService;
import com.cplsystems.stock.services.EmailService;
import com.cplsystems.stock.services.EstadoService;
import com.cplsystems.stock.services.GiroService;
import com.cplsystems.stock.services.MonedaService;
import com.cplsystems.stock.services.MunicipioService;
import com.cplsystems.stock.services.PaisService;
import com.cplsystems.stock.services.PersonaService;
import com.cplsystems.stock.services.ProveedorProductoService;
import com.cplsystems.stock.services.ProveedorService;
import com.cplsystems.stock.services.TelefonoService;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public abstract class ProveedorMetaClass extends ProveedorVariables {
	private static final long serialVersionUID = -4078164796340868446L;

	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects() {
		
		nuevoProveedor = new Proveedor();
		municipioProveedor = new Municipio();
		estadoProveedor = new Estado();
		paisProveedor = new Pais();
		telefonoProveedor = new Telefono();
		telefonoContacto = new Telefono();
		emailProveedor = new Email();
		emailContacto = new Email();
		contactoProveedor = new Contacto();
		contactoContacto = new Contacto();
		direccionProveedor = new Direccion();
		nuevoProveedor = new Proveedor();
		representanteLegal = new Persona();
		personaContacto = new Persona();
		monedaSeleccionada = new Moneda();
		bancoSeleccionado = new Banco();
		contrato = new Contrato();
		cuentaPago = new CuentaPago();
		buscarProveedor = new Proveedor();
		buscarProducto = new Producto();
		proveedorProducto = new ProveedorProducto();
		buscarProveedorAsociar = new Proveedor();
		botonMuenu1 = new MenuButtonsActivated();
		botonMuenu2 = new MenuButtonsActivated();
		botonMuenu3 = new MenuButtonsActivated();
		botonMuenu4 = new MenuButtonsActivated();
		botonMuenu5 = new MenuButtonsActivated();
		botonMuenu6 = new MenuButtonsActivated();
		botonMuenu7 = new MenuButtonsActivated();
		botonMuenu8 = new MenuButtonsActivated();
		proveedorSelected = new Proveedor();
		proveedorSelected.setDireccionFiscal(new Direccion());
		Contacto contact = new Contacto();
		Email correo = new Email();
		contact.setTelefono(new Telefono());
		contact.setEmail(correo);
		proveedorSelected.setContacto(contact);

		Persona p = new Persona();

		Contacto contactPersona = new Contacto();
		Email correoPersona = new Email();
		Telefono telefonoPersona = new Telefono();
		contactPersona.setTelefono(telefonoPersona);
		contactPersona.setEmail(correoPersona);

		p.setContacto(contactPersona);
		proveedorSelected.setRepresentanteAteCliente(p);
	}

	public void initProperties() {
		this.contratos = this.contratoService.getAll();
		this.estados = this.estadoService.getAll();
		this.paises = this.paisService.getAll();
		this.municipios = this.municipioService.getAll();
		bancosDB = this.bancoService.getAll();
		this.monedasDB = this.monedaService.getAll();
		this.paisProveedor = this.paisService.findById(Long.valueOf(157L));
		this.giros = this.giroService.getAll();

		generarBotonesMenu();
	}

	private void generarBotonesMenu() {
		this.botonMuenu1.setNombre("Limpiar Formulario");
		this.botonMuenu1.setVisible(true);
		this.botonMuenu1.setNumero(Integer.valueOf(1));
		this.botonMuenu2.setNombre("Guardar proveedor");
		this.botonMuenu2.setVisible(true);
		this.botonMuenu2.setNumero(Integer.valueOf(2));
		this.botonMuenu3.setNombre("Eliminar proveedor");
		this.botonMuenu3.setNumero(Integer.valueOf(3));
		this.botonMuenu4.setNombre("Actualizar proveedor");
		this.botonMuenu4.setNumero(Integer.valueOf(4));
		this.botonMuenu5.setNombre("Buscar proveedor");
		this.botonMuenu5.setNumero(Integer.valueOf(5));
		this.botonMuenu6.setNombre("Agregar producto");
		this.botonMuenu6.setNumero(Integer.valueOf(6));
		this.botonMuenu7.setNombre("Quitar producto");
		this.botonMuenu7.setNumero(Integer.valueOf(7));
		this.botonMuenu8.setNombre("Exportar");
		this.botonMuenu8.setNumero(Integer.valueOf(8));
		this.proveedoresAsociacionSelected = new Proveedor();
	}

	public String validarEntradaDatosProveedor() {
		String mensajes = "";
		if ((this.nuevoProveedor.getNombre().equals(null)) || (this.nuevoProveedor.getNombre().isEmpty())) {
			mensajes = "Es necesario ingresar el nombre del proveedor";
		} else if ((this.paisProveedor.getNombre() == null) || (this.paisProveedor.getNombre().isEmpty())) {
			mensajes = "Es necesario seleccionar un pa�s para la direcci�n del proveedor";
		} else if ((this.estadoProveedor.getNombre() == null) || (this.estadoProveedor.getNombre().isEmpty())) {
			mensajes = "Es necesario seleccionar un estado para la direcci�n del proveedor";
		} else if ((this.municipioProveedor.getNombre() == null) || (this.municipioProveedor.getNombre().isEmpty())) {
			mensajes = "Es necesario seleccionar un municipio para la direcci�n del proveedor";
		} else if ((this.nuevoProveedor.getDireccionFiscal().getColonia() == null)
				|| (this.nuevoProveedor.getDireccionFiscal().getColonia().isEmpty())) {
			mensajes = "Es necesario ingresar el nombre de una colonia para la direcci�n del proveedor";
		} else if ((this.nuevoProveedor.getDireccionFiscal().getCalle() == null)
				|| (this.nuevoProveedor.getDireccionFiscal().getCalle().isEmpty())) {
			mensajes = "Es necesario ingresar el nombre de una calle para la direcci�n del proveedor";
		} else if ((this.nuevoProveedor.getDireccionFiscal().getNumExt() == null)
				|| (this.nuevoProveedor.getDireccionFiscal().getNumExt().isEmpty())) {
			mensajes = "Es necesario ingresar un numero externo domiciliario para la direcci�n del proveedor";
		} else if ((this.nuevoProveedor.getContacto().getEmail().getEmail() == null)
				|| (this.nuevoProveedor.getContacto().getEmail().getEmail().isEmpty())) {
			mensajes = "Es necesario ingresar un correo electronico para contactar al proveedor";
		} else if ((this.nuevoProveedor.getContacto().getTelefono().getNumero() == null)
				|| (this.nuevoProveedor.getContacto().getTelefono().getNumero().isEmpty())) {
			mensajes = "Es necesario ingresar un n�mero telefonico para contactar al proveedor";
		} else if ((this.nuevoProveedor.getRepresentanteAteCliente().getApellidoPaterno() == null)
				|| (this.nuevoProveedor.getRepresentanteAteCliente().getApellidoPaterno().isEmpty())) {
			mensajes = "Es necesario ingresar apellido paterno del contacto";
		} else if ((this.nuevoProveedor.getRepresentanteAteCliente().getApellidoMaterno() == null)
				|| (this.nuevoProveedor.getRepresentanteAteCliente().getApellidoMaterno().isEmpty())) {
			mensajes = "Es necesario ingresar apellido materno del contacto";
		} else if ((this.nuevoProveedor.getRepresentanteAteCliente().getNombre() == null)
				|| (this.nuevoProveedor.getRepresentanteAteCliente().getNombre().isEmpty())) {
			mensajes = "Es necesario ingresar nombre de pila del contacto";
		} else if ((this.nuevoProveedor.getRepresentanteAteCliente().getContacto().getTelefono().getNumero() == null)
				|| (this.nuevoProveedor.getRepresentanteAteCliente().getContacto().getTelefono().getNumero()
						.isEmpty())) {
			mensajes = "Es necesario ingresar un n�mero telefonico del contacto";
		} else if ((this.nuevoProveedor.getRepresentanteAteCliente().getContacto().getEmail().getEmail() == null)
				|| (this.nuevoProveedor.getRepresentanteAteCliente().getContacto().getEmail().getEmail().isEmpty())) {
			mensajes = "Es necesario ingresar un correo electr�nico del contacto";
		} else if (this.monedaSeleccionada.equals(null)) {
			mensajes = "Es necesario seleccionar un tipo de moneda para la cuenta pago";
		} else if (this.bancoSeleccionado.equals(null)) {
			mensajes = "Es necesario seleccionar un banco para la cuenta pago";
		} else if (this.cuentaPago.getCuentaBancaria().equals(null)) {
			mensajes = "Es necesario ingresar un n�mero de cuenta bancaria";
		}
		return mensajes;
	}

	@Command
	@NotifyChange({ "*" })
	public void nuevoProveedor() {
		this.municipioProveedor = new Municipio();
		this.paisProveedor = new Pais();
		this.estadoProveedor = new Estado();
		this.emailContacto = new Email();
		this.emailProveedor = new Email();
		this.estadoProveedor = new Estado();
		this.monedaSeleccionada = new Moneda();
		this.municipioProveedor = new Municipio();
		this.nuevoProveedor = new Proveedor();
		this.paisProveedor = new Pais();
		this.personaContacto = new Persona();
		this.telefonoContacto = new Telefono();
		this.telefonoProveedor = new Telefono();
	}

	public void guardarProveedor() {
		this.nuevoProveedor.getContacto().getEmail().getEmail();
		if (!this.guardadoEmailProveedor) {
			this.emailService.save(this.nuevoProveedor.getContacto().getEmail());
			this.guardadoEmailProveedor = true;
		}
		if (!this.guardadoEmailContacto) {
			this.emailService.save(this.nuevoProveedor.getRepresentanteAteCliente().getContacto().getEmail());
			this.guardadoEmailContacto = true;
		}
		if (!this.guardadoTelefonoProveedor) {
			this.telefonoService.save(this.nuevoProveedor.getContacto().getTelefono());
			this.guardadoTelefonoProveedor = true;
		}
		if (!this.guardadoTelefonoContacto) {
			this.telefonoService.save(this.nuevoProveedor.getRepresentanteAteCliente().getContacto().getTelefono());
			this.guardadoTelefonoContacto = true;
		}
		if (!this.guardadoContactoProveedor) {
			this.contactoProveedor.setTelefono(this.nuevoProveedor.getContacto().getTelefono());
			this.contactoProveedor.setEmail(this.nuevoProveedor.getContacto().getEmail());
			this.contactoService.save(this.contactoProveedor);
			this.guardadoContactoProveedor = true;
		}
		if (!this.guardadoContactoContacto) {
			this.contactoContacto.setEmail(this.nuevoProveedor.getRepresentanteAteCliente().getContacto().getEmail());
			this.contactoContacto
					.setTelefono(this.nuevoProveedor.getRepresentanteAteCliente().getContacto().getTelefono());
			this.contactoService.save(this.contactoContacto);
			this.guardadoContactoContacto = true;
		}
		if (!this.guardadoDireccionProveedor) {
			this.direccionProveedor.setMunicipio(this.municipioProveedor);
			this.direccionProveedor.setEstado(this.estadoProveedor);
			this.direccionProveedor.setPais(this.paisProveedor);
			this.direccionProveedor.setNumExt(this.nuevoProveedor.getDireccionFiscal().getNumExt().toUpperCase());
			if (this.nuevoProveedor.getDireccionFiscal().getNumInt() != null) {
				this.direccionProveedor.setNumInt(this.nuevoProveedor.getDireccionFiscal().getNumInt().toUpperCase());
			}
			this.direccionService.save(this.direccionProveedor);
			this.guardadoDireccionProveedor = true;
		}
		if (!this.guardadorepResentanteLegalProveedor) {
			this.representanteLegal.setNombre(this.nuevoProveedor.getNombre());
			this.representanteLegal.setContacto(this.contactoProveedor);
			this.representanteLegal.setDireccion(this.direccionProveedor);
			this.personaService.save(this.representanteLegal);
			this.guardadorepResentanteLegalProveedor = true;
		}
		if (!this.guardadoPersonaContacto) {
			this.personaContacto.setCurp(this.proveedorSelected.getRepresentanteAteCliente().getCurp().toUpperCase());
			this.personaContacto.setRfc(this.proveedorSelected.getRepresentanteAteCliente().getRfc().toUpperCase());
			this.personaContacto.setContacto(this.contactoContacto);
			this.personaService.save(this.personaContacto);
			this.guardadoPersonaContacto = true;
		}
		if (!this.guardadoNuevoProveedor) {
			this.nuevoProveedor.setRfc(this.nuevoProveedor.getRfc().toUpperCase());
			this.nuevoProveedor.setRepresentanteAteCliente(this.personaContacto);
			this.nuevoProveedor.setContacto(this.contactoContacto);
			this.nuevoProveedor.setRepresentanteLegal(this.representanteLegal);
			this.nuevoProveedor.setDireccionFiscal(this.direccionProveedor);
			this.nuevoProveedor.setClave(this.nuevoProveedor.getNombre().substring(0, 2).toUpperCase()
					+ this.nuevoProveedor.getRfc().substring(this.nuevoProveedor.getRfc().length() - 2).toUpperCase()
					+ this.estadoProveedor.getNombre().substring(0, 1).toUpperCase());

			this.nuevoProveedor.setFechaActualizacion(Calendar.getInstance());
			this.proveedorService.save(this.nuevoProveedor);
			this.guardadoNuevoProveedor = true;
		}
		if (!this.guardadoCuentaPago) {
			this.cuentaPago.setProveedor(this.nuevoProveedor);
			this.cuentaPago.setBanco(this.bancoSeleccionado);
			this.cuentaPago.setMoneda(this.monedaSeleccionada);
			this.cuentasPagoService.save(this.cuentaPago);
			this.guardadoCuentaPago = true;
		}
		if ((this.contrato != null) && (this.contrato.getFechaVigenciaInicio() != null)
				&& (this.contrato.getFechaVigenciaFin() != null)) {
			this.contratoService.save(this.contrato);
			this.nuevoProveedor.setContrato(this.contrato);
		}
	}

	public void actualizarProveedorCambios() {
		if (this.proveedorSelected != null) {
			Organizacion org = (Organizacion) this.sessionUtils.getFromSession("FIRMA");
			this.proveedorSelected.setOrganizacion(org);
			this.proveedorService.save(this.proveedorSelected);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void asignarProductosAProveedor() {
		Integer actualizados = Integer.valueOf(0);
		Integer noActualizados = Integer.valueOf(0);
		String mensaje = "";
		String mensajeNoActualizado = "";
		if (this.proveedoresAsociacionSelected != null) {
			if (this.productosDB != null) {
				for (Producto productoSeleccionado : this.productosDB) {
					if (productoSeleccionado.isSeleccionar()) {
						List<ProveedorProducto> proveedorProducto = this.proveedorProductoService
								.getByProductoProveedor(productoSeleccionado, this.proveedoresAsociacionSelected);

						boolean guardarProducto = true;
						if ((proveedorProducto != null) && (proveedorProducto.size() > 0)) {
							guardarProducto = false;
						}
						Integer localInteger1;
						if (guardarProducto) {
							nuevoProveedorProducto = new ProveedorProducto();
							nuevoProveedorProducto.setProveedor(this.proveedoresAsociacionSelected);

							nuevoProveedorProducto.setProducto(productoSeleccionado);

							this.proveedorProductoService.save(nuevoProveedorProducto);

							localInteger1 = actualizados;
							Integer localInteger2 = actualizados = Integer.valueOf(actualizados.intValue() + 1);
						} else {
							//nuevoProveedorProducto = noActualizados;
							localInteger1 = noActualizados = Integer.valueOf(noActualizados.intValue() + 1);
						}
					}
				}
				ProveedorProducto nuevoProveedorProducto;
				for (Producto producto : this.productosDB) {
					if (producto.isSeleccionar()) {
						producto.setSeleccionar(false);
					}
				}
				this.proveedorProductos = this.proveedorProductoService
						.getByProveedor(this.proveedoresAsociacionSelected);
				if (noActualizados.intValue() > 0) {
					mensajeNoActualizado = "Se detectaron productos existentes para este proveedor [" + noActualizados
							+ "].";
				}
				mensaje = getMensajeAsignacionProductoAProveedor(actualizados, "asignado") + ". " + mensajeNoActualizado
						+ "";

				StockUtils.showSuccessmessage(mensaje, "info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("La lista de productos se encuentra vacia", "error", Integer.valueOf(0),
						null);
			}
		} else {
			StockUtils.showSuccessmessage("No ha sido seleccionado un proveedor", "warning", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void quitarProductosDeProveedor() {
		Integer registrosRemovidos = Integer.valueOf(0);
		if (this.proveedoresAsociacionSelected != null) {
			if ((this.proveedorProductos != null) && (this.proveedorProductos.size() > 0)) {
				List<ProveedorProducto> removerProductos = new ArrayList();
				for (ProveedorProducto proveedorProducto : this.proveedorProductos) {
					Integer localInteger1;
					if (proveedorProducto.getProducto().isSeleccionar()) {
						removerProductos.add(proveedorProducto);

						this.proveedorProductoService.delete(proveedorProducto);
						localInteger1 = registrosRemovidos;
						Integer localInteger2 = registrosRemovidos = Integer.valueOf(registrosRemovidos.intValue() + 1);
					}
					proveedorProducto.getProducto().setSeleccionar(false);
				}
				this.proveedorProductos = this.proveedorProductoService
						.getByProveedor(this.proveedoresAsociacionSelected);

				String mensaje = "";
				if (registrosRemovidos.intValue() > 0) {
					mensaje = "Se removieron " + registrosRemovidos + " productos del proveedor -"
							+ this.proveedoresAsociacionSelected.getNombre() + "-";
				} else {
					mensaje = "Ningun producto fue removido para el proveedor -"
							+ this.proveedoresAsociacionSelected.getNombre() + "-";
				}
				StockUtils.showSuccessmessage(mensaje, "info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("-" + this.proveedoresAsociacionSelected.getNombre()
						+ "- no cuenta con productos. Nada que eliminar", "warning", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("No ha sido seleccionado un proveedor", "warning", Integer.valueOf(0), null);
		}
	}

	private String getMensajeAsignacionProductoAProveedor(Integer contador, String nombreAccionSingular) {
		String mensaje = "";
		if (contador.intValue() > 1) {
			mensaje = contador + " productos han sido " + nombreAccionSingular + "s al proveedor -"
					+ this.proveedoresAsociacionSelected.getNombre() + "-";
		} else if (contador.intValue() == 0) {
			mensaje = "Ning�n producto fue " + nombreAccionSingular + " al proveedor -"
					+ this.proveedoresAsociacionSelected.getNombre() + "-";
		} else if (contador.intValue() == 1) {
			mensaje = contador + " producto ha sido " + nombreAccionSingular + " al proveedor -"
					+ this.proveedoresAsociacionSelected.getNombre() + "-";
		}
		return mensaje;
	}

	public String generarReporteProveedor(List<HashMap> listaHashsParametros, List<AplicacionExterna> aplicaciones) {
		String mensaje = "";

		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);
		try {
			this.print = JasperFillManager.fillReport(this.readJasper, hashParametros,
					new JRBeanCollectionDataSource(this.proveedoresLista));

			this.jviewer = new JasperViewer(this.print, false);
			JasperExportManager.exportReportToPdfFile(this.print, StockConstants.REPORT_PROVEEDOR_NAME_FILE);

			openPdf(StockConstants.REPORT_PROVEEDOR_NAME_FILE);
			mensaje = "PDF del reporte generado: " + StockConstants.REPORT_PROVEEDOR_NAME_FILE;
		} catch (JRException e) {
			for (AplicacionExterna aplicacion : aplicaciones) {
				closePdf(aplicacion.getNombre());
			}
			try {
				JasperExportManager.exportReportToPdfFile(this.print, StockConstants.REPORT_PROVEEDOR_NAME_FILE);

				openPdf(StockConstants.REPORT_PROVEEDOR_NAME_FILE);
				mensaje = "PDF del reporte generado: " + StockConstants.REPORT_PROVEEDOR_NAME_FILE;
			} catch (JRException e1) {
			}
		}
		return mensaje;
	}

	@Command
	@NotifyChange({ "*" })
	public void obtenerInformacionProveedorBuscado() {
		if (this.proveedorSelected != null) {
			List<CuentaPago> cp = this.cuentasPagoService.getByProveedor(this.proveedorSelected);
			if (cp != null) {
				this.cuentaPago = ((CuentaPago) cp.get(0));
				this.monedaSeleccionada = this.cuentaPago.getMoneda();
				this.bancoSeleccionado = this.cuentaPago.getBanco();
			}
			if (this.proveedorSelected.getContrato() != null) {
				this.contratoVigenciaInicio = new StockUtils()
						.convertirCalendarToDate(this.proveedorSelected.getContrato().getFechaVigenciaInicio());

				this.contratoVigenciaFin = new StockUtils()
						.convertirCalendarToDate(this.proveedorSelected.getContrato().getFechaVigenciaFin());
			}
			if (this.proveedorProductos == null) {
				this.proveedorProductos = new ArrayList();
			}
			this.proveedorProductos = this.proveedorProductoService.getByProveedor(this.proveedorSelected);
		}
	}

	@Command
	@NotifyChange({ "proveedorProductos" })
	public void seleccionarProveedorRelacionProducto() {
		if (this.proveedoresAsociacionSelected != null) {
			this.proveedorProductos = this.proveedorProductoService.getByProveedor(this.proveedoresAsociacionSelected);
		}
	}
	
	
	
	/*

	public void leerDatosDesdeExcel(InputStream inPutStream, int indiceSheet) {
		boolean escribirDatos = true;
		List<Proveedor> productoNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = 0;
			try {
				int j;
				while (rowIterator.hasNext()) {
					Proveedor proveedorNuevo = new Proveedor();
					XSSFRow hssfRow = (XSSFRow) rowIterator.next();
					Iterator iterator = hssfRow.cellIterator();
					if (i > 0) {
						j = 0;
						XSSFCell hssfCell;
						while ((iterator.hasNext()) && (j < 30)) {
							hssfCell = (XSSFCell) iterator.next();
							proveedorNuevo = crearProveedor(proveedorNuevo, hssfCell, j);
							j++;
						}
						proveedorNuevo.setOrganizacion((Organizacion) sessionUtils.getFromSession("FIRMA"));

						productoNuevosExcel.add(proveedorNuevo);
					}
					j = i;
					
				}
			} catch (Exception e) {
				escribirDatos = false;
				File borrarArchivo = new File(fileName);
				borrarArchivo.delete();
			}
			File borrarArchivo = new File(fileName);
			Organizacion org = null;
			if ((borrarArchivo.delete()) && (escribirDatos)) {
				org = (Organizacion) this.sessionUtils.getFromSession("FIRMA");
				for (Proveedor item : productoNuevosExcel) {
					Direccion direccionSave = item.getDireccionFiscal();
					this.direccionService.save(direccionSave);

					Contacto contactoSave = item.getContacto();
					Telefono telefonoSave = contactoSave.getTelefono();
					Email emailSave = contactoSave.getEmail();
					this.emailService.save(emailSave);
					this.telefonoService.save(telefonoSave);
					this.contactoService.save(contactoSave);

					Persona representanteLegal = item.getRepresentanteLegal();
					contactoSave = representanteLegal.getContacto();
					telefonoSave = contactoSave.getTelefono();
					emailSave = contactoSave.getEmail();
					this.emailService.save(emailSave);
					this.telefonoService.save(telefonoSave);
					this.contactoService.save(contactoSave);
					this.personaService.save(representanteLegal);

					Contrato contratoSave = item.getContrato();
					contratoSave.setOrganizacion((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

					contratoSave
							.setFechaActualizacion(this.stockUtils.convertirCalendarToString(Calendar.getInstance()));

					this.contratoService.save(contratoSave);

					item.setProveedorActivo(true);
					item.setFechaActualizacion(Calendar.getInstance());
					item.setClave(item.getNombre().substring(0, 2).toUpperCase()
							+ item.getRfc().substring(item.getRfc().length() - 2).toUpperCase()
							+ item.getDireccionFiscal().getEstado().getNombre().substring(0, 1).toUpperCase());

					item.setOrganizacion(org);
					this.proveedorService.save(item);

					CuentaPago cuentaPagoSave = item.getCuentaPago();
					cuentaPagoSave.setProveedor(item);
				}
			}
		} catch (Exception e) {
			Organizacion org;
			escribirDatos = false;
			e.printStackTrace();
		}
	}

	*/
	private Proveedor crearProveedor(Proveedor nuevoProveedor, XSSFCell valorDePropiedad, int indice) {
		String valor = "";
		switch (indice) {
		case 0:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				nuevoProveedor.setNombre(valor);
			}
			break;
		case 1:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				Giro giroEntrada = this.giroService.getById(Long.valueOf(valor));
				nuevoProveedor.setGiro(giroEntrada);
			}
			break;
		case 2:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				nuevoProveedor.setRazonSocial(valor);
			}
			break;
		case 3:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				nuevoProveedor.setRfc(valor);
			}
			break;
		case 4:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				Pais paisEntrada = this.paisService.findById(Long.valueOf(valor));
				direccionFiscal.setPais(paisEntrada);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 5:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				Estado estadoEntrada = this.estadoService.getById(Long.valueOf(valor));
				direccionFiscal.setEstado(estadoEntrada);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 6:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				direccionFiscal.setCuidad(valor);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 7:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				Municipio municipioEntrada = this.municipioService.getById(Long.valueOf(valor));

				direccionFiscal.setMunicipio(municipioEntrada);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 8:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				direccionFiscal.setColonia(valor);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 9:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				direccionFiscal.setCalle(valor);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 10:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				try {
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				direccionFiscal.setNumExt(valor);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 11:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				try {
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				direccionFiscal.setNumInt(valor);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 12:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Direccion direccionFiscal = nuevoProveedor.getDireccionFiscal();
				if (direccionFiscal == null) {
					direccionFiscal = new Direccion();
				}
				direccionFiscal.setCp(valor);
				nuevoProveedor.setDireccionFiscal(direccionFiscal);
			}
			break;
		case 13:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Contacto contactoEntrada = nuevoProveedor.getContacto();
				Telefono telefonoEntrada;
				if (contactoEntrada != null) {
					telefonoEntrada = contactoEntrada.getTelefono();
					if (telefonoEntrada == null) {
						telefonoEntrada = new Telefono();
					}
				} else {
					contactoEntrada = new Contacto();
					telefonoEntrada = new Telefono();
				}
				try {
					valor = String.valueOf(valorDePropiedad);
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				telefonoEntrada.setNumero(valor);
				contactoEntrada.setTelefono(telefonoEntrada);
				nuevoProveedor.setContacto(contactoEntrada);
			}
			break;
		case 14:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Contacto contactoEntrada = nuevoProveedor.getContacto();
				Telefono telefonoEntrada;
				if (contactoEntrada != null) {
					telefonoEntrada = contactoEntrada.getTelefono();
					if (telefonoEntrada == null) {
						telefonoEntrada = new Telefono();
					}
				} else {
					contactoEntrada = new Contacto();
					telefonoEntrada = new Telefono();
				}
				try {
					valor = String.valueOf(valorDePropiedad);
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				telefonoEntrada.setFax(valor);
				contactoEntrada.setTelefono(telefonoEntrada);
				nuevoProveedor.setContacto(contactoEntrada);
			}
			break;
		case 15:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Contacto contactoEntrada = nuevoProveedor.getContacto();
				Email emailEntrada;
				if (contactoEntrada != null) {
					emailEntrada = contactoEntrada.getEmail();
					if (emailEntrada == null) {
						emailEntrada = new Email();
					}
				} else {
					contactoEntrada = new Contacto();
					emailEntrada = new Email();
				}
				try {
					valor = String.valueOf(valorDePropiedad);
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				emailEntrada.setEmail(valor);
				contactoEntrada.setEmail(emailEntrada);
				nuevoProveedor.setContacto(contactoEntrada);
			}
			break;
		case 16:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				nuevoProveedor.setPaginaWeb(String.valueOf(valorDePropiedad));
			}
			break;
		case 17:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				if (personaEntrada == null) {
					personaEntrada = new Persona();
				}
				personaEntrada.setApellidoPaterno(String.valueOf(valorDePropiedad));

				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 18:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				if (personaEntrada == null) {
					personaEntrada = new Persona();
				}
				personaEntrada.setApellidoMaterno(String.valueOf(valorDePropiedad));

				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 19:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				if (personaEntrada == null) {
					personaEntrada = new Persona();
				}
				personaEntrada.setNombre(String.valueOf(valorDePropiedad));
				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 20:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				if (personaEntrada == null) {
					personaEntrada = new Persona();
				}
				personaEntrada.setRfc(String.valueOf(valorDePropiedad));
				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 21:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				if (personaEntrada == null) {
					personaEntrada = new Persona();
				}
				personaEntrada.setCurp(String.valueOf(valorDePropiedad));
				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 22:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				Telefono telefonoContacto;
				Contacto contactoContacto;
				if (personaEntrada != null) {
					contactoContacto = personaEntrada.getContacto();
					if (contactoContacto != null) {
						telefonoContacto = contactoContacto.getTelefono();
						if (telefonoContacto == null) {
							telefonoContacto = new Telefono();
						}
					} else {
						contactoContacto = new Contacto();
						telefonoContacto = new Telefono();
					}
				} else {
					personaEntrada = new Persona();
					contactoContacto = new Contacto();
					telefonoContacto = new Telefono();
				}
				try {
					valor = String.valueOf(valorDePropiedad);
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				telefonoContacto.setNumero(valor);
				contactoContacto.setTelefono(telefonoContacto);
				personaEntrada.setContacto(contactoContacto);
				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 23:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				Email correoContacto;
				Contacto contactoContacto;
				if (personaEntrada != null) {
					contactoContacto = personaEntrada.getContacto();
					if (contactoContacto != null) {
						correoContacto = contactoContacto.getEmail();
						if (correoContacto == null) {
							correoContacto = new Email();
						}
					} else {
						contactoContacto = new Contacto();
						correoContacto = new Email();
					}
				} else {
					personaEntrada = new Persona();
					contactoContacto = new Contacto();
					correoContacto = new Email();
				}
				correoContacto.setEmail(String.valueOf(valorDePropiedad));
				contactoContacto.setEmail(correoContacto);
				personaEntrada.setContacto(contactoContacto);
				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 24:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Persona personaEntrada = nuevoProveedor.getRepresentanteLegal();
				Telefono telefonoContacto;
				Contacto contactoContacto;
				if (personaEntrada != null) {
					contactoContacto = personaEntrada.getContacto();
					if (contactoContacto != null) {
						telefonoContacto = contactoContacto.getTelefono();
						if (telefonoContacto == null) {
							telefonoContacto = new Telefono();
						}
					} else {
						contactoContacto = new Contacto();
						telefonoContacto = new Telefono();
					}
				} else {
					personaEntrada = new Persona();
					contactoContacto = new Contacto();
					telefonoContacto = new Telefono();
				}
				try {
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				telefonoContacto.setCelular(valor);
				contactoContacto.setTelefono(telefonoContacto);
				personaEntrada.setContacto(contactoContacto);
				nuevoProveedor.setRepresentanteLegal(personaEntrada);
			}
			break;
		case 25:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Contrato contratoEntrada = nuevoProveedor.getContrato();
				if (contratoEntrada == null) {
					contratoEntrada = new Contrato();
				}
				valor = String.valueOf(valorDePropiedad);
				String dia = "";
				String mes = "";
				String anyo = "";

				int counter = 0;
				for (int i = 0; i < valor.length(); i++) {
					String caracter = valor.substring(i, i + 1);
					boolean concatenar = true;
					if (caracter.equals(".")) {
						concatenar = false;
						counter++;
					}
					switch (counter) {
					case 0:
						if (concatenar) {
							dia = dia + caracter;
						}
						break;
					case 1:
						if (concatenar) {
							mes = mes + caracter;
						}
						break;
					case 2:
						if (concatenar) {
							anyo = anyo + caracter;
						}
						break;
					}
				}
				Integer diaInteger = Integer.valueOf(Integer.parseInt(dia));
				Integer mesInteger = Integer.valueOf(Integer.parseInt(mes));
				Integer anyoInteger = Integer.valueOf(Integer.parseInt(anyo));
				contratoEntrada.setFechaVigenciaInicio(
						this.stockUtils.convertirStringToCalendar(diaInteger, mesInteger, anyoInteger));

				nuevoProveedor.setContrato(contratoEntrada);
			}
			break;
		case 26:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				Contrato contratoEntrada = nuevoProveedor.getContrato();
				if (contratoEntrada == null) {
					contratoEntrada = new Contrato();
				}
				valor = String.valueOf(valorDePropiedad);
				String dia = "";
				String mes = "";
				String anyo = "";

				int counter = 0;
				for (int i = 0; i < valor.length(); i++) {
					String caracter = valor.substring(i, i + 1);
					boolean concatenar = true;
					if (caracter.equals(".")) {
						concatenar = false;
						counter++;
					}
					switch (counter) {
					case 0:
						if (concatenar) {
							dia = dia + caracter;
						}
						break;
					case 1:
						if (concatenar) {
							mes = mes + caracter;
						}
						break;
					case 2:
						if (concatenar) {
							anyo = anyo + caracter;
						}
						break;
					}
				}
				Integer diaInteger = Integer.valueOf(Integer.parseInt(dia));
				Integer mesInteger = Integer.valueOf(Integer.parseInt(mes));
				Integer anyoInteger = Integer.valueOf(Integer.parseInt(anyo));
				contratoEntrada.setFechaVigenciaFin(
						this.stockUtils.convertirStringToCalendar(diaInteger, mesInteger, anyoInteger));

				nuevoProveedor.setContrato(contratoEntrada);
			}
			break;
		case 27:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				CuentaPago cuentaPagoEntrada = nuevoProveedor.getCuentaPago();
				if (cuentaPagoEntrada == null) {
					cuentaPagoEntrada = new CuentaPago();
				}
				try {
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				Moneda monedaEntrada = this.monedaService.getById(Long.valueOf(valor));

				cuentaPagoEntrada.setMoneda(monedaEntrada);
				nuevoProveedor.setCuentaPago(cuentaPagoEntrada);
			}
			break;
		case 28:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				CuentaPago cuentaPagoEntrada = nuevoProveedor.getCuentaPago();
				if (cuentaPagoEntrada == null) {
					cuentaPagoEntrada = new CuentaPago();
				}
				try {
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				Banco bancoEntrada = this.bancoService.getById(Long.valueOf(valor));
				cuentaPagoEntrada.setBanco(bancoEntrada);
				nuevoProveedor.setCuentaPago(cuentaPagoEntrada);
			}
			break;
		case 29:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				CuentaPago cuentaPagoEntrada = nuevoProveedor.getCuentaPago();
				if (cuentaPagoEntrada == null) {
					cuentaPagoEntrada = new CuentaPago();
				}
				try {
					if (valor.contains(".0")) {
						valor = removerPuntoCero(valor);
					}
					Integer a = Integer.valueOf(Integer.parseInt(valor));
					valor = String.valueOf(a);
				} catch (Exception e) {
					valor = String.valueOf(valorDePropiedad);
				}
				cuentaPagoEntrada.setCuentaBancaria(valor);
				nuevoProveedor.setCuentaPago(cuentaPagoEntrada);
			}
			break;
		case 30:
			valor = String.valueOf(valorDePropiedad);
			if ((!valor.equalsIgnoreCase("NULL")) && (!valor.isEmpty())) {
				nuevoProveedor.setComentario(valor);
			}
			break;
		}
		return nuevoProveedor;
	}
}
