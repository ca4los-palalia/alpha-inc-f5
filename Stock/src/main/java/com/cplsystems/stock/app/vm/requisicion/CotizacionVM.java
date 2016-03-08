package com.cplsystems.stock.app.vm.requisicion;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.app.vm.requisicion.utils.CotizacionListaExcelFile;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Pais;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;
import com.cplsystems.stock.domain.Telefono;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.CotizacionInboxService;
import com.cplsystems.stock.services.CotizacionService;
import com.cplsystems.stock.services.EstatusRequisicionService;
import com.cplsystems.stock.services.MailService;
import com.cplsystems.stock.services.OrdenCompraInboxService;
import com.cplsystems.stock.services.OrdenCompraService;
import com.cplsystems.stock.services.PrivilegioService;
import com.cplsystems.stock.services.ProductoService;
import com.cplsystems.stock.services.RequisicionProductoService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.bouncycastle.util.encoders.Hex;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Window;

@VariableResolver({ DelegatingVariableResolver.class })
public class CotizacionVM extends RequisicionVariables {
	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";
	@Wire
	private Checkbox c1;

	@Init
	public void init() {
		super.init();
		loadCotizacionesInbox();
		this.readJasper = generarUrlString("jasperTemplates/cotizacionFormato.jasper");
	}

	private void loadCotizacionesInbox() {
		this.cotizacionesInbox = this.cotizacionInboxService
				.getAllNews((Organizacion) this.sessionUtils.getFromSession("FIRMA"));
		for (CotizacionInbox ctInbox : this.cotizacionesInbox) {
			if ((ctInbox.getLeido() != null) && (!ctInbox.getLeido().booleanValue())) {
				ctInbox.setIcono("/images/toolbar/newEmail.png");
			}
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void transferirCotizacionToFormular(@BindingParam("index") Integer index) {
		if (index != null) {
			CotizacionInbox toLoad = (CotizacionInbox) this.cotizacionesInbox.get(index.intValue());
			if ((toLoad.getLeido() != null) && (!toLoad.getLeido().booleanValue())) {
				toLoad.setLeido(Boolean.valueOf(true));
				this.cotizacionInboxService.save(toLoad);
				toLoad.setIcono("/images/toolbar/openedEmail.png");
			}
			this.cotizacionesList.clear();
			this.cotizacionesList.add(toLoad.getCotizacion());

			Cotizacion cotizacion = this.cotizacionService.getById(toLoad.getCotizacion().getIdCotizacion());

			this.requisicionProductos = this.requisicionProductoService.getByCotizacion(cotizacion);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void obtenerListaDeProductosProveedorSeleccionado() {
		if (this.proveedorSelected != null) {
			this.requisicionProductos = this.requisicionProductoService.getByProveedor(this.proveedorSelected);
		}
	}

	@Command
	public void checkNueva() {
		if (!this.checkBuscarNueva) {
			this.checkBuscarNueva = true;
		} else {
			this.checkBuscarNueva = false;
		}
	}

	@Command
	public void checkCancelada() {
		if (!this.checkBuscarCancelada) {
			this.checkBuscarCancelada = true;
		} else {
			this.checkBuscarCancelada = false;
		}
	}

	@Command
	public void checkEnviada() {
		if (!this.checkBuscarEnviada) {
			this.checkBuscarEnviada = true;
		} else {
			this.checkBuscarEnviada = false;
		}
	}

	@Command
	public void checkAceptada() {
		if (!this.checkBuscarAceptada) {
			this.checkBuscarAceptada = true;
		} else {
			this.checkBuscarAceptada = false;
		}
	}

	private List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;
		if ((this.checkBuscarNueva) || (this.checkBuscarCancelada) || (this.checkBuscarEnviada)
				|| (this.checkBuscarAceptada)) {
			lista = new ArrayList();
			if (this.checkBuscarNueva) {
				lista.add(this.estatusRequisicionService.getByClave("CON"));
			}
			if (this.checkBuscarCancelada) {
				lista.add(this.estatusRequisicionService.getByClave("COC"));
			}
			if (this.checkBuscarEnviada) {
				lista.add(this.estatusRequisicionService.getByClave("COE"));
			}
			if (this.checkBuscarAceptada) {
				lista.add(this.estatusRequisicionService.getByClave("COA"));
			}
		}
		return lista;
	}

	@Command
	@NotifyChange({ "*" })
	public void buscarPCotizacion() {
		if ((this.checkBuscarNueva) || (this.checkBuscarCancelada) || (this.checkBuscarEnviada)
				|| (this.checkBuscarAceptada)
				|| ((this.requisicion != null) && (this.requisicion.getBuscarRequisicion() != null)
						&& (!this.requisicion.getBuscarRequisicion().isEmpty()))) {
			List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();

			this.cotizacionesList = this.cotizacionService
					.getCotizacionesByEstatusRequisicionAndFolioOrProveedorByFolio(
							this.requisicion.getBuscarRequisicion(), null, listaEstatus);

			List<Cotizacion> cotizacionesArreglo = new ArrayList();
			if (this.cotizacionesList != null) {
				for (Cotizacion cotizacionArreglo : this.cotizacionesList) {
					boolean agregar = true;
					for (Cotizacion item : cotizacionesArreglo) {
						if (item.getProveedor().getNombre().equals(cotizacionArreglo.getProveedor().getNombre())) {
							agregar = false;
							break;
						}
					}
					if (agregar) {
						cotizacionesArreglo.add(cotizacionArreglo);
					}
				}
				this.cotizacionesList.clear();
				this.cotizacionesList = cotizacionesArreglo;
			}
			if ((this.cotizacionesList != null) && (this.requisicionProductos != null)) {
				this.requisicionProductos.clear();
			}
		} else {
			StockUtils.showSuccessmessage(
					"Debe elegir algun criterio para realizar la busqueda de cotizaciones (nueva, cancelada, enviada o aceptada) o (ingresar palabra en el buscador)",
					"warning", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void mostrarProductosCotizacion() {
		if (this.cotizacionSelected != null) {
			if (this.cotizacionesConProductos == null) {
				this.cotizacionesConProductos = new ArrayList();
			}
			this.cotizacionesConProductos = this.cotizacionService.getByProveedorFolioCotizacionNueva(
					this.cotizacionSelected.getProveedor(), this.cotizacionSelected.getFolioCotizacion(),
					this.cotizacionSelected.getEstatusRequisicion());
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void enviarCotizacion() {
		if (this.cotizacionSelected != null) {
			if (this.cotizacionSelected.getEstatusRequisicion().getClave().equals("CON")) {
				crearArchivoExcel();
				CotizacionInbox inbox = new CotizacionInbox();
				inbox.setCotizacion(this.cotizacionSelected);
				inbox.setLeido(Boolean.valueOf(false));
				inbox.setFechaRegistro(this.stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				this.cotizacionInboxService.save(inbox);

				imprimirCotizacion();
				List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
				for (Privilegios privilegio : privilegios) {
					if (privilegio.getUsuarios().getPersona().getContacto() != null) {
						this.mailService.sendMail(
								privilegio.getUsuarios().getPersona().getContacto().getEmail().getEmail(),
								"1nn3rgy@gmail.com", "Cotizaci�n enviada con proveedor",
								"La cotizacion " + this.cotizacionSelected.getFolioCotizacion()
										+ "Ha sido enviada con el proveedor "
										+ this.cotizacionSelected.getProveedor().getNombre(),
								this.rutaPdfGenerado);
					}
				}
				StockUtils.showSuccessmessage("La cotizacion con folio [" + this.cotizacionSelected.getFolioCotizacion()
						+ "] ha sido enviada", "info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("La cotizacion con folio [" + this.cotizacionSelected.getFolioCotizacion()
						+ "] no puede ser reenviada nuevamente", "warning", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una cotizaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void aceptarCotizacion() {
		if (this.cotizacionSelected != null) {
			if (this.cotizacionSelected.getEstatusRequisicion().getClave().equals("COE")) {
				EstatusRequisicion estado = this.estatusRequisicionService.getByClave("COA");

				this.cotizacionSelected.setEstatusRequisicion(estado);
				for (int i = 0; i < this.cotizacionesConProductos.size(); i++) {
					Cotizacion item = (Cotizacion) this.cotizacionesConProductos.get(i);
					item.setEstatusRequisicion(estado);
					this.cotizacionService.save(item);
				}
				OrdenCompra compra = new OrdenCompra();

				estado = this.estatusRequisicionService.getByClave("OCN");

				compra.setEstatusRequisicion(estado);
				compra.setOrganizacion((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

				compra.setUsuario((Usuarios) this.sessionUtils.getFromSession("usuario"));

				compra.setCodigo("FOC" + this.ordenCompraService.getCodigoDeOrden());

				compra.setCotizacion(this.cotizacionSelected);
				compra.setFechaOrden(Calendar.getInstance());
				this.ordenCompraService.save(compra);

				OrdenCompraInbox inbox = new OrdenCompraInbox();
				inbox.setOrdenCompra(compra);
				inbox.setLeido(Boolean.valueOf(false));
				inbox.setFechaCreacion(new StockUtils().convertirCalendarToDate(Calendar.getInstance()));

				this.ordenCompraInboxService.save(inbox);

				List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
				for (Privilegios privilegio : privilegios) {
					if (privilegio.getUsuarios().getPersona().getContacto() != null) {
						this.mailService.sendMail(
								privilegio.getUsuarios().getPersona().getContacto().getEmail().getEmail(),
								"1nn3rgy@gmail.com", "Cotizaci�n Aceptada",
								"La cotizacion " + this.cotizacionSelected.getFolioCotizacion()
										+ " del proveedor proveedor "
										+ this.cotizacionSelected.getProveedor().getNombre() + " ha sido aceptada");
					}
				}
				StockUtils.showSuccessmessage("La cotizacion con folio [" + this.cotizacionSelected.getFolioCotizacion()
						+ "] ha sido Aceptada", "info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"La cotizacion con folio [" + this.cotizacionSelected.getFolioCotizacion()
								+ "] nu puede ser aceptada bajo este estatus ("
								+ this.cotizacionSelected.getEstatusRequisicion().getNombre() + ")",
						"warning", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una cotizaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void abrirVentanaCancelacion() {
		if (this.cotizacionSelected != null) {
			if ((this.cotizacionSelected.getEstatusRequisicion().getClave().equals("COE"))
					|| (this.cotizacionSelected.getEstatusRequisicion().getClave().equals("CON"))) {
				HashMap<String, Object> map = new HashMap();
				map.put("cotizacion", this.cotizacionSelected);
				Executions.createComponents("/modulos/requisicion/cancelacionCotizacion.zul", null, map);
			} else {
				StockUtils.showSuccessmessage(
						"La cotizaci�n con folio [" + this.cotizacionSelected.getFolioCotizacion()
								+ "] no puede ser cancelada bajo este estatus ("
								+ this.cotizacionSelected.getEstatusRequisicion().getNombre() + ")",
						"warning", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una cotizaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	private void crearArchivoExcel() {
		boolean excelGenerado = false;

		HSSFSheet hoja = this.libro.createSheet();

		HSSFRow row = hoja.createRow(0);
		HSSFCell cell = null;
		HSSFRichTextString value = null;
		for (int j = 0; j < 8; j++) {
			switch (j) {
			case 0:
				cell = row.createCell((short) j);
				value = new HSSFRichTextString("No");
				break;
			case 1:
				cell = row.createCell((short) j);
				value = new HSSFRichTextString("Clave");
				break;
			case 2:
				cell = row.createCell((short) j);
				value = new HSSFRichTextString("Producto");
				break;
			case 3:
				cell = row.createCell((short) j);
				value = new HSSFRichTextString("Partida gen�rica");
				break;
			case 4:
				cell = row.createCell((short) j);
				value = new HSSFRichTextString("Cantidad");
				break;
			case 5:
				cell = row.createCell((short) j);
				value = new HSSFRichTextString("U/M");
				break;
			case 6:
				cell = row.createCell((short) j);
				value = new HSSFRichTextString("Precio unitario");
				break;
			case 7:
				cell = row.createCell((short) j);
				value = new HSSFRichTextString("TOTAL");
			}
			cell.setCellValue(value);
		}
		for (int i = 0; i < this.cotizacionesConProductos.size(); i++) {
			Cotizacion item = (Cotizacion) this.cotizacionesConProductos.get(i);

			HSSFRow fila = hoja.createRow(i + 1);
			HSSFCell celda = null;
			HSSFRichTextString texto = null;
			for (int j = 0; j < 8; j++) {
				switch (j) {
				case 0:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(String.valueOf(i + 1));
					break;
				case 1:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(item.getProducto().getClave());

					break;
				case 2:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(item.getProducto().getNombre());

					break;
				case 3:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(item.getRequisicionProducto().getCofiaPartidaGenerica().getNombre());

					break;
				case 4:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(item.getRequisicionProducto().getCantidad().toString());

					break;
				case 5:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(item.getProducto().getUnidad().getNombre());

					break;
				case 6:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(String.valueOf(""));
					break;
				case 7:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(String.valueOf(""));
				}
				celda.setCellValue(texto);
			}
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(String.valueOf(this.cotizacionSelected.getIdCotizacion()).getBytes());

			byte[] mb = md.digest();

			String hash = String.valueOf(Hex.encode(mb));
			this.fileOutputStream = new FileOutputStream(StockConstants.CARPETA_ARCHIVOS_COTIZACIONES + hash + ".xls");

			this.libro.write(this.fileOutputStream);

			this.fileOutputStream.close();
			excelGenerado = true;
			EstatusRequisicion estado = this.estatusRequisicionService.getByClave("COE");

			this.cotizacionSelected.setEstatusRequisicion(estado);
			this.cotizacionSelected.setExcelFile(hash);
			for (int i = 0; i < this.cotizacionesConProductos.size(); i++) {
				Cotizacion item = (Cotizacion) this.cotizacionesConProductos.get(i);
				item.setEstatusRequisicion(estado);
				item.setExcelFile(hash);
				this.cotizacionService.save(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	@NotifyChange({ "requisicionProductos" })
	public void abrirExcel(@BindingParam("index") Integer index) {
		this.cotizacionSelected = ((Cotizacion) this.cotizacionesList.get(index.intValue()));
		List<CotizacionListaExcelFile> listaExcel = new ArrayList();
		boolean lecturaCorrecta = true;
		if ((this.cotizacionSelected != null) && (this.cotizacionSelected.getExcelFile() != null)
				&& (!this.cotizacionSelected.getExcelFile().isEmpty())) {
			File fileNameExcel = new File(
					StockConstants.CARPETA_ARCHIVOS_COTIZACIONES + this.cotizacionSelected.getExcelFile() + ".xls");
			if (fileNameExcel.exists()) {
				try {
					this.fileInputStream = new FileInputStream(fileNameExcel);
					this.libro = new HSSFWorkbook(this.fileInputStream);

					HSSFSheet my_worksheet = this.libro.getSheetAt(0);
					Iterator<Row> rowIterator = my_worksheet.iterator();
					while (rowIterator.hasNext()) {
						Row row = (Row) rowIterator.next();
						if (row.getRowNum() > 0) {
							Iterator<Cell> cellIterator = row.cellIterator();
							CotizacionListaExcelFile filaRecuperacion = new CotizacionListaExcelFile();
							Integer columna = Integer.valueOf(0);
							Integer localInteger1;
							Integer localInteger2;
							for (; cellIterator
									.hasNext(); localInteger2 = columna = Integer.valueOf(columna.intValue() + 1)) {
								Cell cell = (Cell) cellIterator.next();
								switch (columna.intValue()) {
								case 0:
									filaRecuperacion.setNo(obtenerVAlorDeCeldaDeExcel(cell));

									break;
								case 1:
									filaRecuperacion.setClave(obtenerVAlorDeCeldaDeExcel(cell));

									break;
								case 2:
									filaRecuperacion.setNombreProducto(obtenerVAlorDeCeldaDeExcel(cell));

									break;
								case 3:
									filaRecuperacion.setPartidaGenericaNombre(obtenerVAlorDeCeldaDeExcel(cell));

									break;
								case 4:
									filaRecuperacion.setCantidad(obtenerVAlorDeCeldaDeExcel(cell));

									break;
								case 5:
									filaRecuperacion.setUnidadMedida(obtenerVAlorDeCeldaDeExcel(cell));

									break;
								case 6:
									filaRecuperacion.setPrecioUnitario(obtenerVAlorDeCeldaDeExcel(cell));

									break;
								case 7:
									filaRecuperacion.setTotal(obtenerVAlorDeCeldaDeExcel(cell));
								}
								localInteger1 = columna;
							}
							listaExcel.add(filaRecuperacion);
						}
					}
					this.fileInputStream.close();
				} catch (FileNotFoundException e) {
					lecturaCorrecta = false;
				} catch (IOException e) {
					lecturaCorrecta = false;
				}
				if (lecturaCorrecta) {
					actualizarPreciosProductosLeidos(listaExcel);
					StockUtils.showSuccessmessage("se ha leido correctamente la informaci�n de un archivo de excel",
							"info", Integer.valueOf(0), null);
				} else {
					StockUtils.showSuccessmessage("Ocurrio un error en la lectura sobre el archivo ["
							+ this.cotizacionSelected.getExcelFile() + "]", "error", Integer.valueOf(0), null);
				}
			} else {
				StockUtils.showSuccessmessage("El archivo [" + this.cotizacionSelected.getExcelFile() + "] NO existe",
						"error", Integer.valueOf(0), null);
			}
		}
	}

	private void actualizarPreciosProductosLeidos(List<CotizacionListaExcelFile> listaExcel) {
		Integer i = Integer.valueOf(0);
		Integer localInteger1;
		Integer localInteger2;
		for (Iterator i$ = this.cotizacionesConProductos.iterator(); i$
				.hasNext(); localInteger2 = i = Integer.valueOf(i.intValue() + 1)) {
			Cotizacion item = (Cotizacion) i$.next();
			CotizacionListaExcelFile rowExcel = (CotizacionListaExcelFile) listaExcel.get(i.intValue());
			item.getRequisicionProducto().setCantidad(Float.valueOf(Float.parseFloat(rowExcel.getCantidad())));
			if ((rowExcel.getTotal() != null) && (!rowExcel.getTotal().isEmpty())) {
				item.getRequisicionProducto()
						.setTotalProductoPorUnidad(Float.valueOf(Float.parseFloat(rowExcel.getTotal())));
			} else {
				item.getRequisicionProducto().setTotalProductoPorUnidad(Float.valueOf(0.0F));
			}
			Producto producto = item.getProducto();
			if ((rowExcel.getPrecioUnitario() != null) && (!rowExcel.getPrecioUnitario().isEmpty())) {
				producto.setPrecio(Float.valueOf(Float.parseFloat(rowExcel.getPrecioUnitario())));
			} else {
				producto.setPrecio(Float.valueOf(0.0F));
			}
			this.productoService.save(producto);
			this.cotizacionService.save(item);

			localInteger1 = i;
		}
	}

	@Command
	public void imprimirCotizacion() {
		if (this.cotizacionSelected != null) {
			if ((this.cotizacionesConProductos != null) && (this.cotizacionesConProductos.size() > 0)) {
				Organizacion org = (Organizacion) this.sessionUtils.getFromSession("FIRMA");

				HashMap mapa = new HashMap();
				mapa.put("logotipo", this.stockUtils.getLogotipoDeOrganizacionParaJasper(org.getLogotipo()));

				mapa.put("nombreEmpresa", org.getNombre());
				mapa.put("proveedor", this.cotizacionSelected.getProveedor().getNombre());

				mapa.put("direccion", this.cotizacionSelected.getProveedor().getDireccionFiscal().getCalle() + "|"
						+ this.cotizacionSelected.getProveedor().getDireccionFiscal().getNumExt() + "|"
						+ this.cotizacionSelected.getProveedor().getDireccionFiscal().getColonia() + "|"
						+ this.cotizacionSelected.getProveedor().getDireccionFiscal().getCuidad() + "|"
						+ this.cotizacionSelected.getProveedor().getDireccionFiscal().getEstado().getNombre() + "|"
						+ this.cotizacionSelected.getProveedor().getDireccionFiscal().getPais().getNombre());

				String telefonoMap = "";
				try {
					if ((this.cotizacionSelected.getProveedor().getContacto() != null)
							&& (this.cotizacionSelected.getProveedor().getContacto().getTelefono() != null)) {
						telefonoMap = this.cotizacionSelected.getProveedor().getContacto().getTelefono().getNumero();
					}
					mapa.put("telefono", telefonoMap);
				} catch (Exception e) {
				}
				String representanteLegal = "";
				if (this.cotizacionSelected.getProveedor().getRepresentanteLegal() != null) {
					representanteLegal = this.cotizacionSelected.getProveedor().getRepresentanteLegal()
							.getNombreCompleto();
				}
				mapa.put("atencion", representanteLegal);
				mapa.put("folio", this.cotizacionSelected.getFolioCotizacion());

				Calendar fechaMap = Calendar.getInstance();
				if (this.cotizacionSelected.getFechaEnvioCotizacion() != null) {
					fechaMap = this.cotizacionSelected.getFechaEnvioCotizacion();
				}
				mapa.put("fecha", this.stockUtils.convertirCalendarToString(fechaMap));

				String emailMap = "";
				if ((this.cotizacionSelected.getProveedor().getContacto() != null)
						&& (this.cotizacionSelected.getProveedor().getContacto().getEmail() != null)) {
					emailMap = this.cotizacionSelected.getProveedor().getContacto().getEmail().getEmail();
				}
				mapa.put("email", emailMap);

				Float total = Float.valueOf(0.0F);
				for (Cotizacion item : this.cotizacionesConProductos) {
					if (item.getRequisicionProducto().getTotalProductoPorUnidad() != null) {
						total = Float.valueOf(total.floatValue()
								+ item.getRequisicionProducto().getTotalProductoPorUnidad().floatValue());
					}
				}
				mapa.put("total", String.valueOf(total));

				List<HashMap> listaHashsParametros = new ArrayList();
				listaHashsParametros.add(mapa);

				List<AplicacionExterna> aplicaciones = new ArrayList();
				AplicacionExterna aplicacion = new AplicacionExterna();
				aplicacion.setNombre("PDFXCview");
				aplicaciones.add(aplicacion);
				StockUtils.showSuccessmessage(
						generarCotizacionJasper(listaHashsParametros, aplicaciones, this.cotizacionesConProductos),
						"info", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una cotizaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	public String generarCotizacionJasper(List<HashMap> listaHashsParametros, List<AplicacionExterna> aplicaciones,
			List<Cotizacion> lista) {
		String mensaje = "";
		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);

		this.rutaPdfGenerado = (StockConstants.CARPETA_ARCHIVOS_COTIZACIONES + hashParametros.get("folio")
				+ StockUtils.getFechaActualConHora() + ".pdf");
		try {
			this.print = JasperFillManager.fillReport(this.readJasper, hashParametros,
					new JRBeanCollectionDataSource(lista));

			JasperExportManager.exportReportToPdfFile(this.print, this.rutaPdfGenerado);
			mensaje = "Cotizacion rxportado a PDF " + StockConstants.CARPETA_ARCHIVOS_COTIZACIONES;
		} catch (JRException e) {
			e.printStackTrace();
			for (AplicacionExterna aplicacion : aplicaciones) {
				closePdf(aplicacion.getNombre());
			}
			try {
				JasperExportManager.exportReportToPdfFile(this.print, StockConstants.CARPETA_ARCHIVOS_COTIZACIONES);

				openPdf(StockConstants.CARPETA_ARCHIVOS_COTIZACIONES);
				mensaje = "Se ha generado un PDF: " + StockConstants.CARPETA_ARCHIVOS_COTIZACIONES;
			} catch (JRException e1) {
				e1.printStackTrace();
			}
		}
		return mensaje;
	}

	@Command
	public void abrirPDF(@BindingParam("index") Integer index) {
	}

	private List<Privilegios> getEmailsUsuariosCotizacion() {
		List<Privilegios> usuarios = this.privilegioService.getUsuariosByPrivilegio(UserPrivileges.COTIZAR_AUTORIZAR);

		return usuarios;
	}

	@Command
	public void notificacionBandeja(@BindingParam("index") Integer index) {
		Map<String, Object> inputParams = new HashMap();
		Window productoModalView = this.stockUtils.createModelDialogWithParams("/modulos//utilidades/bandeja.zul",
				inputParams);

		productoModalView.doModal();
	}
}
