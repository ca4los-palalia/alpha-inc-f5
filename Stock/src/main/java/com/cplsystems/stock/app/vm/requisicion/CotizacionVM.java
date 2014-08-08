/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

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
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.app.vm.requisicion.utils.CotizacionListaExcelFile;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.RequisicionInbox;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CotizacionVM extends RequisicionVariables {

	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	@Wire
	private Checkbox c1;

	@Init
	public void init() {
		super.init();
		loadCotizacionesInbox();
		readJasper = generarUrlString(StockConstants.ARCHIVO_JASPER_COTIZACION_FORMATO);
	}

	private void loadCotizacionesInbox() {
		cotizacionesInbox = cotizacionInboxService
				.getAllNews((Organizacion) sessionUtils
						.getFromSession(SessionUtils.FIRMA));
		for (CotizacionInbox ctInbox : cotizacionesInbox) {
			if (ctInbox.getLeido() != null && !ctInbox.getLeido()) {
				ctInbox.setIcono(RequisicionInbox.NUEVO);
			}
		}
	}

	@NotifyChange("*")
	@Command
	public void transferirCotizacionToFormular(
			@BindingParam("index") Integer index) {
		if (index != null) {
			CotizacionInbox toLoad = cotizacionesInbox.get(index);
			if (toLoad.getLeido() != null && !toLoad.getLeido()) {
				toLoad.setLeido(true);
				cotizacionInboxService.save(toLoad);
				toLoad.setIcono(CotizacionInbox.LEIDO);
			}
			cotizacionesList.clear();
			cotizacionesList.add(toLoad.getCotizacion());

			Cotizacion cotizacion = cotizacionService.getById(toLoad
					.getCotizacion().getIdCotizacion());
			requisicionProductos = requisicionProductoService
					.getByCotizacion(cotizacion);
		}
	}

	@Command
	@NotifyChange("*")
	public void obtenerListaDeProductosProveedorSeleccionado() {
		if (proveedorSelected != null) {
			requisicionProductos = requisicionProductoService
					.getByProveedor(proveedorSelected);
		}
	}

	@Command
	public void checkNueva() {
		if (!checkBuscarNueva)
			checkBuscarNueva = true;
		else
			checkBuscarNueva = false;
	}

	@Command
	public void checkCancelada() {
		if (!checkBuscarCancelada)
			checkBuscarCancelada = true;
		else
			checkBuscarCancelada = false;
	}

	@Command
	public void checkEnviada() {
		if (!checkBuscarEnviada)
			checkBuscarEnviada = true;
		else
			checkBuscarEnviada = false;
	}

	@Command
	public void checkAceptada() {
		if (!checkBuscarAceptada)
			checkBuscarAceptada = true;
		else
			checkBuscarAceptada = false;

	}

	private List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;

		if (checkBuscarNueva || checkBuscarCancelada || checkBuscarEnviada
				|| checkBuscarAceptada) {
			lista = new ArrayList<EstatusRequisicion>();

			if (checkBuscarNueva)
				lista.add(estatusRequisicionService
						.getByClave(StockConstants.ESTADO_COTIZACION_NUEVA));
			if (checkBuscarCancelada)
				lista.add(estatusRequisicionService
						.getByClave(StockConstants.ESTADO_COTIZACION_CANCELADA));
			if (checkBuscarEnviada)
				lista.add(estatusRequisicionService
						.getByClave(StockConstants.ESTADO_COTIZACION_ENVIADA));
			if (checkBuscarAceptada)
				lista.add(estatusRequisicionService
						.getByClave(StockConstants.ESTADO_COTIZACION_ACEPTADA));
		}

		return lista;
	};

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void buscarPCotizacion() {
		if ((checkBuscarNueva || checkBuscarCancelada || checkBuscarEnviada || checkBuscarAceptada)
				|| (requisicion != null && (requisicion.getBuscarRequisicion() != null && !requisicion
						.getBuscarRequisicion().isEmpty()))) {

			List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();

			cotizacionesList = cotizacionService
					.getCotizacionesByEstatusRequisicionAndFolioOrProveedorByFolio(
							requisicion.getBuscarRequisicion(), null,
							listaEstatus);

			List<Cotizacion> cotizacionesArreglo = new ArrayList<Cotizacion>();
			if (cotizacionesList != null) {
				for (Cotizacion cotizacionArreglo : cotizacionesList) {
					boolean agregar = true;
					for (Cotizacion item : cotizacionesArreglo) {
						if (item.getProveedor()
								.getNombre()
								.equals(cotizacionArreglo.getProveedor()
										.getNombre())) {
							agregar = false;
							break;
						}
					}
					if (agregar) {
						cotizacionesArreglo.add(cotizacionArreglo);
					}
				}
				cotizacionesList.clear();
				cotizacionesList = cotizacionesArreglo;
			}

			if (cotizacionesList != null) {
				if (requisicionProductos != null)
					requisicionProductos.clear();
			}

		} else
			stockUtils
					.showSuccessmessage(
							"Debe elegir algun criterio para realizar la busqueda de cotizaciones (nueva, cancelada, enviada o aceptada) o (ingresar palabra en el buscador)",
							Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	@Command
	@NotifyChange("*")
	public void mostrarProductosCotizacion() {
		if (cotizacionSelected != null) {
			if (cotizacionesConProductos == null)
				cotizacionesConProductos = new ArrayList<Cotizacion>();
			cotizacionesConProductos = cotizacionService
					.getByProveedorFolioCotizacionNueva(
							cotizacionSelected.getProveedor(),
							cotizacionSelected.getFolioCotizacion(),
							cotizacionSelected.getEstatusRequisicion());
		}
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void enviarCotizacion() {
		if (cotizacionSelected != null) {
			if (cotizacionSelected.getEstatusRequisicion().getClave()
					.equals(StockConstants.ESTADO_COTIZACION_NUEVA)) {
				crearArchivoExcel();
				CotizacionInbox inbox = new CotizacionInbox();
				inbox.setCotizacion(cotizacionSelected);
				inbox.setLeido(false);
				inbox.setFechaRegistro(stockUtils
						.convertirCalendarToDate(Calendar.getInstance()));
				cotizacionInboxService.save(inbox);

				// Imprimir y enviar PDF
				imprimirCotizacion();
				List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
				for (Privilegios privilegio : privilegios) {
					if (privilegio.getUsuarios().getPersona().getContacto() != null) {
						mailService.sendMail(
								privilegio.getUsuarios().getPersona()
										.getContacto().getEmail().getEmail(),
								"1nn3rgy@gmail.com",
								"Cotización enviada con proveedor",
								"La cotizacion "
										+ cotizacionSelected
												.getFolioCotizacion()
										+ "Ha sido enviada con el proveedor "
										+ cotizacionSelected.getProveedor()
												.getNombre(), rutaPdfGenerado);
					}
				}// END Imprimir y enviar PDF

				stockUtils.showSuccessmessage("La cotizacion con folio ["
						+ cotizacionSelected.getFolioCotizacion()
						+ "] ha sido enviada", Clients.NOTIFICATION_TYPE_INFO,
						0, null);
			} else
				stockUtils.showSuccessmessage("La cotizacion con folio ["
						+ cotizacionSelected.getFolioCotizacion()
						+ "] no puede ser reenviada nuevamente",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		} else
			stockUtils.showSuccessmessage(
					"Es necesario seleccionar primero una cotización",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);

	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void aceptarCotizacion() {
		if (cotizacionSelected != null) {
			if (cotizacionSelected.getEstatusRequisicion().getClave()
					.equals(StockConstants.ESTADO_COTIZACION_ENVIADA)) {
				EstatusRequisicion estado = estatusRequisicionService
						.getByClave(StockConstants.ESTADO_COTIZACION_ACEPTADA);

				cotizacionSelected.setEstatusRequisicion(estado);
				for (int i = 0; i < cotizacionesConProductos.size(); i++) {
					Cotizacion item = cotizacionesConProductos.get(i);
					item.setEstatusRequisicion(estado);
					cotizacionService.save(item);
				}

				OrdenCompra compra = new OrdenCompra();

				estado = estatusRequisicionService
						.getByClave(StockConstants.ESTADO_ORDEN_COMPRA_NUEVA);
				compra.setEstatusRequisicion(estado);
				compra.setOrganizacion((Organizacion) sessionUtils
						.getFromSession(SessionUtils.FIRMA));
				compra.setUsuario((Usuarios) sessionUtils
						.getFromSession(SessionUtils.USUARIO));
				compra.setCodigo(StockConstants.CLAVE_FOLIO_ORDEN_COMPRA
						+ ordenCompraService.getCodigoDeOrden());
				compra.setCotizacion(cotizacionSelected);
				compra.setFechaOrden(Calendar.getInstance());
				ordenCompraService.save(compra);

				OrdenCompraInbox inbox = new OrdenCompraInbox();
				inbox.setOrdenCompra(compra);
				inbox.setLeido(false);
				inbox.setFechaCreacion(new StockUtils()
						.convertirCalendarToDate(Calendar.getInstance()));
				ordenCompraInboxService.save(inbox);

				// enviar notificacion
				List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
				for (Privilegios privilegio : privilegios) {
					if (privilegio.getUsuarios().getPersona().getContacto() != null) {
						mailService.sendMail(
								privilegio.getUsuarios().getPersona()
										.getContacto().getEmail().getEmail(),
								"1nn3rgy@gmail.com",
								"Cotización Aceptada",
								"La cotizacion "
										+ cotizacionSelected
												.getFolioCotizacion()
										+ " del proveedor proveedor "
										+ cotizacionSelected.getProveedor()
												.getNombre()
										+ " ha sido aceptada");
					}
				}

				stockUtils.showSuccessmessage("La cotizacion con folio ["
						+ cotizacionSelected.getFolioCotizacion()
						+ "] ha sido Aceptada", Clients.NOTIFICATION_TYPE_INFO,
						0, null);
			} else
				stockUtils.showSuccessmessage("La cotizacion con folio ["
						+ cotizacionSelected.getFolioCotizacion()
						+ "] nu puede ser aceptada bajo este estatus ("
						+ cotizacionSelected.getEstatusRequisicion()
								.getNombre() + ")",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);

		} else
			stockUtils.showSuccessmessage(
					"Es necesario seleccionar primero una cotización",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void abrirVentanaCancelacion() {
		if (cotizacionSelected != null) {
			if (cotizacionSelected.getEstatusRequisicion().getClave()
					.equals(StockConstants.ESTADO_COTIZACION_ENVIADA)
					|| cotizacionSelected.getEstatusRequisicion().getClave()
							.equals(StockConstants.ESTADO_COTIZACION_NUEVA)) {

				final HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("cotizacion", cotizacionSelected);
				Executions.createComponents(
						"/modulos/requisicion/cancelacionCotizacion.zul", null,
						map);

			} else
				stockUtils.showSuccessmessage("La cotización con folio ["
						+ cotizacionSelected.getFolioCotizacion()
						+ "] no puede ser cancelada bajo este estatus ("
						+ cotizacionSelected.getEstatusRequisicion()
								.getNombre() + ")",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);

		} else
			stockUtils.showSuccessmessage(
					"Es necesario seleccionar primero una cotización",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);

	}

	@SuppressWarnings({ "deprecation", "unused" })
	private void crearArchivoExcel() {
		boolean excelGenerado = false;
		HSSFSheet hoja;
		hoja = libro.createSheet();

		// crear encabezado
		HSSFRow row = hoja.createRow(0);// Crear row
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
				value = new HSSFRichTextString("Partida genérica");
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
				break;
			}
			cell.setCellValue(value);
		}
		// END crear encabezado

		for (int i = 0; i < cotizacionesConProductos.size(); i++) {
			Cotizacion item = cotizacionesConProductos.get(i);

			HSSFRow fila = hoja.createRow(i + 1);// Crear row
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
					texto = new HSSFRichTextString(item.getProducto()
							.getClave());
					break;
				case 2:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(item.getProducto()
							.getNombre());
					break;
				case 3:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(item
							.getRequisicionProducto().getCofiaPartidaGenerica()
							.getNombre());
					break;
				case 4:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(item
							.getRequisicionProducto().getCantidad().toString());
					break;
				case 5:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(item.getProducto()
							.getUnidad().getNombre());
					break;
				case 6:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(String.valueOf(""));
					break;
				case 7:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(String.valueOf(""));
					break;
				}
				celda.setCellValue(texto);
			}
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(String.valueOf(cotizacionSelected.getIdCotizacion())
					.getBytes());
			byte[] mb = md.digest();

			String hash = String.valueOf(Hex.encode(mb));
			fileOutputStream = new FileOutputStream(
					StockConstants.CARPETA_ARCHIVOS_COTIZACIONES + hash
							+ StockConstants.EXTENCION_EXCEL);
			libro.write(fileOutputStream);

			fileOutputStream.close();
			excelGenerado = true;
			EstatusRequisicion estado = estatusRequisicionService
					.getByClave(StockConstants.ESTADO_COTIZACION_ENVIADA);
			cotizacionSelected.setEstatusRequisicion(estado);
			cotizacionSelected.setExcelFile(hash);

			for (int i = 0; i < cotizacionesConProductos.size(); i++) {
				Cotizacion item = cotizacionesConProductos.get(i);
				item.setEstatusRequisicion(estado);
				item.setExcelFile(hash);
				cotizacionService.save(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "static-access" })
	@Command
	@NotifyChange("requisicionProductos")
	public void abrirExcel(@BindingParam("index") Integer index) {
		cotizacionSelected = cotizacionesList.get(index);
		List<CotizacionListaExcelFile> listaExcel = new ArrayList<CotizacionListaExcelFile>();
		boolean lecturaCorrecta = true;
		if (cotizacionSelected != null
				&& (cotizacionSelected.getExcelFile() != null && !cotizacionSelected
						.getExcelFile().isEmpty())) {
			File fileNameExcel = new File(
					StockConstants.CARPETA_ARCHIVOS_COTIZACIONES
							+ cotizacionSelected.getExcelFile()
							+ StockConstants.EXTENCION_EXCEL);

			if (fileNameExcel.exists()) {
				try {
					fileInputStream = new FileInputStream(fileNameExcel);
					libro = new HSSFWorkbook(fileInputStream);

					HSSFSheet my_worksheet = libro.getSheetAt(0);
					Iterator<Row> rowIterator = my_worksheet.iterator();

					while (rowIterator.hasNext()) {
						Row row = rowIterator.next();

						if (row.getRowNum() > 0) {
							Iterator<Cell> cellIterator = row.cellIterator();
							CotizacionListaExcelFile filaRecuperacion = new CotizacionListaExcelFile();
							Integer columna = 0;
							while (cellIterator.hasNext()) {
								Cell cell = cellIterator.next(); // Fetch CELL

								switch (columna) {
								case 0:
									filaRecuperacion
											.setNo(obtenerVAlorDeCeldaDeExcel(cell));
									break;
								case 1:
									filaRecuperacion
											.setClave(obtenerVAlorDeCeldaDeExcel(cell));
									break;
								case 2:
									filaRecuperacion
											.setNombreProducto(obtenerVAlorDeCeldaDeExcel(cell));
									break;
								case 3:
									filaRecuperacion
											.setPartidaGenericaNombre(obtenerVAlorDeCeldaDeExcel(cell));
									break;
								case 4:
									filaRecuperacion
											.setCantidad(obtenerVAlorDeCeldaDeExcel(cell));
									break;
								case 5:
									filaRecuperacion
											.setUnidadMedida(obtenerVAlorDeCeldaDeExcel(cell));
									break;
								case 6:
									filaRecuperacion
											.setPrecioUnitario(obtenerVAlorDeCeldaDeExcel(cell));
									break;
								case 7:
									filaRecuperacion
											.setTotal(obtenerVAlorDeCeldaDeExcel(cell));
									break;
								}
								columna++;
							}
							listaExcel.add(filaRecuperacion);
						}
					}
					fileInputStream.close();
				} catch (FileNotFoundException e) {
					lecturaCorrecta = false;
				} catch (IOException e) {
					lecturaCorrecta = false;
				}

				if (lecturaCorrecta) {
					actualizarPreciosProductosLeidos(listaExcel);
					stockUtils
							.showSuccessmessage(
									"se ha leido correctamente la información de un archivo de excel",
									Clients.NOTIFICATION_TYPE_INFO, 0, null);
				} else
					stockUtils.showSuccessmessage(
							"Ocurrio un error en la lectura sobre el archivo ["
									+ cotizacionSelected.getExcelFile() + "]",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
			} else
				stockUtils.showSuccessmessage("El archivo ["
						+ cotizacionSelected.getExcelFile() + "] NO existe",
						Clients.NOTIFICATION_TYPE_ERROR, 0, null);
		}
	}

	private void actualizarPreciosProductosLeidos(
			List<CotizacionListaExcelFile> listaExcel) {

		Integer i = 0;
		for (Cotizacion item : cotizacionesConProductos) {
			CotizacionListaExcelFile rowExcel = listaExcel.get(i);
			item.getRequisicionProducto().setCantidad(
					Float.parseFloat(rowExcel.getCantidad()));
			if (rowExcel.getTotal() != null && !rowExcel.getTotal().isEmpty())
				item.getRequisicionProducto().setTotalProductoPorUnidad(
						Float.parseFloat(rowExcel.getTotal()));
			else
				item.getRequisicionProducto().setTotalProductoPorUnidad(0F);
			Producto producto = item.getProducto();

			if (rowExcel.getPrecioUnitario() != null
					&& !rowExcel.getPrecioUnitario().isEmpty())
				producto.setPrecio(Float.parseFloat(rowExcel
						.getPrecioUnitario()));
			else
				producto.setPrecio(0F);
			productoService.save(producto);
			cotizacionService.save(item);

			i++;
		}

	}

	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	@Command
	public void imprimirCotizacion() {

		if (cotizacionSelected != null) {
			if (cotizacionesConProductos != null
					&& cotizacionesConProductos.size() > 0) {
				Organizacion org = (Organizacion) sessionUtils
						.getFromSession(SessionUtils.FIRMA);

				HashMap mapa = new HashMap();
				mapa.put("logotipo", stockUtils
						.getLogotipoDeOrganizacionParaJasper(org.getLogotipo()));
				mapa.put("nombreEmpresa", org.getNombre());
				mapa.put("proveedor", cotizacionSelected.getProveedor()
						.getNombre());
				mapa.put("direccion", cotizacionSelected.getProveedor()
						.getDireccionFiscal().getCalle()
						+ "|"
						+ cotizacionSelected.getProveedor()
								.getDireccionFiscal().getNumExt()
						+ "|"
						+ cotizacionSelected.getProveedor()
								.getDireccionFiscal().getColonia()
						+ "|"
						+ cotizacionSelected.getProveedor()
								.getDireccionFiscal().getCuidad()
						+ "|"
						+ cotizacionSelected.getProveedor()
								.getDireccionFiscal().getEstado().getNombre()
						+ "|"
						+ cotizacionSelected.getProveedor()
								.getDireccionFiscal().getPais().getNombre());

				String telefonoMap = "";

				if (cotizacionSelected.getProveedor().getContacto() != null
						&& cotizacionSelected.getProveedor().getContacto()
								.getTelefono() != null)
					telefonoMap = cotizacionSelected.getProveedor()
							.getContacto().getTelefono().getNumero();
				mapa.put("telefono", telefonoMap);

				String representanteLegal = "";
				if (cotizacionSelected.getProveedor().getRepresentanteLegal() != null)
					representanteLegal = cotizacionSelected.getProveedor()
							.getRepresentanteLegal().getNombreCompleto();
				mapa.put("atencion", representanteLegal);
				mapa.put("folio", cotizacionSelected.getFolioCotizacion());

				Calendar fechaMap = Calendar.getInstance();
				if (cotizacionSelected.getFechaEnvioCotizacion() != null)
					fechaMap = cotizacionSelected.getFechaEnvioCotizacion();
				mapa.put("fecha",
						stockUtils.convertirCalendarToString(fechaMap));

				String emailMap = "";
				if (cotizacionSelected.getProveedor().getContacto() != null
						&& cotizacionSelected.getProveedor().getContacto()
								.getEmail() != null)
					emailMap = cotizacionSelected.getProveedor().getContacto()
							.getEmail().getEmail();
				mapa.put("email", emailMap);

				Float total = 0F;

				for (Cotizacion item : cotizacionesConProductos) {
					total += item.getRequisicionProducto()
							.getTotalProductoPorUnidad();
				}
				mapa.put("total", String.valueOf(total));

				List<HashMap> listaHashsParametros = new ArrayList<HashMap>();
				listaHashsParametros.add(mapa);

				List<AplicacionExterna> aplicaciones = new ArrayList<AplicacionExterna>();
				AplicacionExterna aplicacion = new AplicacionExterna();
				aplicacion.setNombre("PDFXCview");
				aplicaciones.add(aplicacion);
				stockUtils.showSuccessmessage(
						generarCotizacionJasper(listaHashsParametros,
								aplicaciones, cotizacionesConProductos),
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
			}

		} else
			stockUtils.showSuccessmessage(
					"Es necesario seleccionar primero una cotización",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	@Command
	public String generarCotizacionJasper(List<HashMap> listaHashsParametros,
			List<AplicacionExterna> aplicaciones, List<Cotizacion> lista) {
		String mensaje = "";
		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);

		rutaPdfGenerado = StockConstants.CARPETA_ARCHIVOS_COTIZACIONES
				+ hashParametros.get("folio")
				+ stockUtils.getFechaActualConHora()
				+ StockConstants.EXTENCION_PDF;
		try {

			print = JasperFillManager.fillReport(readJasper, hashParametros,
					new JRBeanCollectionDataSource(lista));
			JasperExportManager.exportReportToPdfFile(print, rutaPdfGenerado);
			mensaje = "Cotizacion rxportado a PDF "
					+ StockConstants.CARPETA_ARCHIVOS_COTIZACIONES;

		} catch (JRException e) {
			e.printStackTrace();
			for (AplicacionExterna aplicacion : aplicaciones)
				closePdf(aplicacion.getNombre());

			try {
				JasperExportManager.exportReportToPdfFile(print,
						StockConstants.CARPETA_ARCHIVOS_COTIZACIONES);
				openPdf(StockConstants.CARPETA_ARCHIVOS_COTIZACIONES);
				mensaje = "Se ha generado un PDF: "
						+ StockConstants.CARPETA_ARCHIVOS_COTIZACIONES;
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
		List<Privilegios> usuarios = privilegioService
				.getUsuariosByPrivilegio(UserPrivileges.COTIZAR_AUTORIZAR);
		return usuarios;
	}
}
