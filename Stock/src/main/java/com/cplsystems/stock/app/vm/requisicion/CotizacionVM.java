package com.cplsystems.stock.app.vm.requisicion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.app.vm.requisicion.utils.CotizacionListaExcelFile;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.AplicacionExterna;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.FileBean;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Usuarios;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
		readJasper = generarUrlString("jasperTemplates/cotizacionFormato.jasper");
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");

	}

	private void loadCotizacionesInbox() {
		cotizacionesInbox = cotizacionInboxService.getAllNews((Organizacion) sessionUtils.getFromSession("FIRMA"));
		for (CotizacionInbox ctInbox : cotizacionesInbox) {
			if ((ctInbox.getLeido() != null) && (!ctInbox.getLeido().booleanValue())) {
				ctInbox.setIcono("/images/toolbar/newEmail.png");
			}
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void transferirCotizacionToFormular(@BindingParam("index") Integer index) {
		if (index != null) {
			CotizacionInbox toLoad = (CotizacionInbox) cotizacionesInbox.get(index.intValue());
			if ((toLoad.getLeido() != null) && (!toLoad.getLeido().booleanValue())) {
				toLoad.setLeido(Boolean.valueOf(true));
				cotizacionInboxService.save(toLoad);
				toLoad.setIcono("/images/toolbar/openedEmail.png");
			}
			cotizacionesList.clear();
			cotizacionesList.add(toLoad.getCotizacion());

			Cotizacion cotizacion = cotizacionService.getById(toLoad.getCotizacion().getIdCotizacion());

			requisicionProductos = requisicionProductoService.getByCotizacion(cotizacion);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void obtenerListaDeProductosProveedorSeleccionado() {
		if (proveedorSelected != null) {
			requisicionProductos = requisicionProductoService.getByProveedor(proveedorSelected);
		}
	}

	@Command
	@NotifyChange({ "cotizacionesList" })
	public void checkNueva() {
		checkBuscarNueva = true;

		cotizacionesList = buscarPCotizacion();
		if (cotizacionesList == null || cotizacionesList.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron cotizaciones nuevas", Clients.NOTIFICATION_TYPE_WARNING, 0,
					null);
		}
		checkBuscarNueva = false;
	}

	@Command
	@NotifyChange({ "cotizacionesList" })
	public void checkCancelada() {
		checkBuscarCancelada = true;
		cotizacionesList = buscarPCotizacion();
		if (cotizacionesList == null || cotizacionesList.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron cotizaciones canceladas",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
		checkBuscarCancelada = false;
	}

	@Command
	@NotifyChange({ "cotizacionesList" })
	public void checkEnviada() {
		checkBuscarEnviada = true;
		cotizacionesList = buscarPCotizacion();
		if (cotizacionesList == null || cotizacionesList.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron cotizaciones enviadas", Clients.NOTIFICATION_TYPE_WARNING,
					0, null);
		}
		checkBuscarEnviada = false;

	}

	@Command
	@NotifyChange({ "cotizacionesList" })
	public void checkAceptada() {
		checkBuscarAceptada = true;
		cotizacionesList = buscarPCotizacion();
		if (cotizacionesList == null || cotizacionesList.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron cotizaciones Aceptadas", Clients.NOTIFICATION_TYPE_WARNING,
					0, null);
		}
		checkBuscarAceptada = false;
	}

	@Command
	@NotifyChange({ "cotizacionesList" })
	public void buscaraCotizacionCadena() {
		if (requisicion.getBuscarRequisicion() != null && !requisicion.getBuscarRequisicion().equals("")) {
			cotizacionesList = buscarPCotizacion();
			if (cotizacionesList == null || cotizacionesList.size() == 0) {
				StockUtils.showSuccessmessage("No se encontraron cotizaciones con: "
						+ requisicion.getBuscarRequisicion() + ". Intente nuevamente",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else
			StockUtils.showSuccessmessage("Ingrese un criterio de busqueda.", Clients.NOTIFICATION_TYPE_ERROR, 0, null);
	}

	private List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada) || (checkBuscarAceptada)) {
			lista = new ArrayList();
			if (checkBuscarNueva) {
				lista.add(estatusRequisicionService.getByClave("CON"));
			}
			if (checkBuscarCancelada) {
				lista.add(estatusRequisicionService.getByClave("COC"));
			}
			if (checkBuscarEnviada) {
				lista.add(estatusRequisicionService.getByClave("COE"));
			}
			if (checkBuscarAceptada) {
				lista.add(estatusRequisicionService.getByClave("COA"));
			}
		}
		return lista;
	}

	private List<Cotizacion> buscarPCotizacion() {
		List<Cotizacion> cotizacionesArreglo = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada) || (checkBuscarAceptada)
				|| ((requisicion != null) && (requisicion.getBuscarRequisicion() != null)
						&& (!requisicion.getBuscarRequisicion().isEmpty()))) {
			List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();

			cotizacionesList = cotizacionService.getCotizacionesByEstatusRequisicionAndFolioOrProveedorByFolio(
					requisicion.getBuscarRequisicion(), null, listaEstatus);

			cotizacionesArreglo = new ArrayList();
			if (cotizacionesList != null) {
				for (Cotizacion cotizacionArreglo : cotizacionesList) {
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
				// cotizacionesList.clear();
			}
			if ((cotizacionesList != null) && (requisicionProductos != null)) {
				requisicionProductos.clear();
			}
		} else {
			StockUtils.showSuccessmessage(
					"Debe elegir algun criterio para realizar la busqueda de cotizaciones (nueva, cancelada, enviada o aceptada) o (ingresar palabra en el buscador)",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
		return cotizacionesArreglo;
	}

	@Command
	@NotifyChange({ "*" })
	public void mostrarProductosCotizacion() {
		if (cotizacionSelected != null) {
			getProductosCotizacion();
		}
	}

	private void getProductosCotizacion() {
		if (cotizacionesConProductos == null) {
			cotizacionesConProductos = new ArrayList();
		}
		cotizacionesConProductos = cotizacionService.getByProveedorFolioCotizacionNueva(
				cotizacionSelected.getProveedor(), cotizacionSelected.getFolioCotizacion(),
				cotizacionSelected.getEstatusRequisicion());
	}

	@Command
	@NotifyChange({ "cotizacionSelected" })
	public void enviarCotizacion() {
		if (cotizacionSelected != null) {
			if (cotizacionSelected.getEstatusRequisicion().getClave().equals("CON")) {
				CotizacionInbox inbox = new CotizacionInbox();
				inbox.setCotizacion(cotizacionSelected);
				inbox.setLeido(Boolean.valueOf(false));
				inbox.setFechaRegistro(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				cotizacionInboxService.save(inbox);

				imprimirCotizacion();
				String mensaje = "";
				// -------------------------------------------------------------------------------
				File file = crearArchivoExcel();
				if (file != null) {
					
					if (organizacion.getDevelopmentTool() != null) {
						List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
						if (privilegios != null && privilegios.size() > 0) {
							enviarCorreos(usuario, organizacion, privilegios, mensaje, "Solicitud de cotizacion", file);
							mensaje += " Por favor devolver la cotizacion con la misma estructura del archivo de excel que se envia. Saludos.";
						}

					} else
						mensaje += ". No se pudo enviar un email para la notificación";
					
					if (file.exists()) {
						if (file.delete()) {
							System.err.println("Archivo temporal eliminado");
						}
					}

				}

				// -------------------------------------------------------------------------------
				StockUtils.showSuccessmessage(
						"La cotizacion con folio [" + cotizacionSelected.getFolioCotizacion() + "] ha sido generada " + mensaje,
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} else {
				StockUtils.showSuccessmessage("La cotizacion con folio [" + cotizacionSelected.getFolioCotizacion()
						+ "] no puede ser reenviada nuevamente", Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una cotizaci�n",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
	}

	public boolean comprobarConexionAInternet() {
		String dirWeb = "www.legajo-virtual.com";
		boolean centinela = false;
		int puerto = 80;
		try {
			Socket s = new Socket(dirWeb, puerto);
			centinela = true;
		} catch (Exception e) {
		}
		return centinela;
	}

	@Command
	@NotifyChange({ "*" })
	public void aceptarCotizacion() {
		if (cotizacionSelected != null) {
			if (cotizacionSelected.getEstatusRequisicion().getClave().equals("COE")) {

				EstatusRequisicion estado = estatusRequisicionService.getByClave("COA");

				cotizacionSelected.setEstatusRequisicion(estado);
				for (int i = 0; i < cotizacionesConProductos.size(); i++) {
					Cotizacion item = (Cotizacion) cotizacionesConProductos.get(i);
					item.setEstatusRequisicion(estado);
					cotizacionService.save(item);
				}
				OrdenCompra compra = new OrdenCompra();

				estado = estatusRequisicionService.getByClave("OCN");

				compra.setEstatusRequisicion(estado);
				compra.setOrganizacion((Organizacion) sessionUtils.getFromSession("FIRMA"));

				compra.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));

				compra.setCodigo("FOC" + ordenCompraService.getCodigoDeOrden());

				compra.setCotizacion(cotizacionSelected);
				compra.setFechaOrden(Calendar.getInstance());
				ordenCompraService.save(compra);

				OrdenCompraInbox inbox = new OrdenCompraInbox();
				inbox.setOrdenCompra(compra);
				inbox.setLeido(Boolean.valueOf(false));
				inbox.setFechaCreacion(new StockUtils().convertirCalendarToDate(Calendar.getInstance()));

				ordenCompraInboxService.save(inbox);

				String mensaje = "Cotizacion [" + cotizacionSelected.getFolioCotizacion() + "] ha sído aceptada ";
				// --------------------------------------------------------

				if (organizacion.getDevelopmentTool() != null) {
					if (comprobarConexionAInternet()) {
						List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
						if (privilegios != null) {
							enviarCorreos(usuario, organizacion, privilegios, mensaje,
									"Cotización Aceptada " + cotizacionSelected.getProveedor().getNombre(), null);
							mensaje += " y se ha enviado un email para su notificación ";
						}
					} else
						mensaje += ". No es posible enviar email. Compruebe su conexion a internet";
				} else
					mensaje += ". No se pudo enviar un email para la notificación";

				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
				// --------------------------------------------------------

			} else {
				StockUtils.showSuccessmessage(
						"La cotizacion con folio [" + cotizacionSelected.getFolioCotizacion()
								+ "] nu puede ser aceptada bajo este estatus ("
								+ cotizacionSelected.getEstatusRequisicion().getNombre() + ")",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una cotizaci�n",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void abrirVentanaCancelacion() {
		if (cotizacionSelected != null) {
			if ((cotizacionSelected.getEstatusRequisicion().getClave().equals("COE"))
					|| (cotizacionSelected.getEstatusRequisicion().getClave().equals("CON"))) {
				HashMap<String, Object> map = new HashMap();
				map.put("cotizacion", cotizacionSelected);
				Executions.createComponents("/modulos/requisicion/cancelacionCotizacion.zul", null, map);
			} else {
				StockUtils.showSuccessmessage(
						"La cotizaci�n con folio [" + cotizacionSelected.getFolioCotizacion()
								+ "] no puede ser cancelada bajo este estatus ("
								+ cotizacionSelected.getEstatusRequisicion().getNombre() + ")",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una cotizaci�n",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
	}

	private File crearArchivoExcel() {
		boolean excelGenerado = false;
		File file = null;
		HSSFSheet hoja = libro.createSheet();

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
		for (int i = 0; i < cotizacionesConProductos.size(); i++) {
			Cotizacion item = (Cotizacion) cotizacionesConProductos.get(i);

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
			String encryptName = stockUtils.Encriptar(String.valueOf(cotizacionSelected.getIdCotizacion()));

			String hash = StockConstants.CARPETA_ARCHIVOS_COTIZACIONES + encryptName + ".xls";
			fileOutputStream = new FileOutputStream(hash);
			libro.write(fileOutputStream);
			file = new File(hash);

			EstatusRequisicion estado = estatusRequisicionService.getByClave("COE");

			cotizacionSelected.setEstatusRequisicion(estado);
			cotizacionSelected.setExcelFile(hash);
			for (int i = 0; i < cotizacionesConProductos.size(); i++) {
				Cotizacion item = (Cotizacion) cotizacionesConProductos.get(i);
				item.setEstatusRequisicion(estado);
				item.setExcelFile(hash);
				cotizacionService.save(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	// ----------------------------------------------

	public static final String COTIZACION_GLOBALCOMMAND_NAsME_FOR_UPDATE = "itemFinder";

	@Command
	public void openFileChooser(@BindingParam("index") Integer index) {
		cotizacionSelected = ((Cotizacion) cotizacionesList.get(index.intValue()));
		if (index != null) {
			Map<String, Object> inputParams = new HashMap();
			inputParams.put("itemFinder", "updateRecordFromFileWithSelectedItem");

			Window productoModalView = stockUtils
					.createModelDialogWithParams("/modulos/productos/utils/fileExplorer.zul", inputParams);
			productoModalView.doModal();
		}
	}

	// "requisicionProductos"
	@GlobalCommand
	@NotifyChange({ "cotizacionesList", "cotizacionSelected", "cotizacionesConProductos" })
	public void updateRecordFromFileWithSelectedItem(@BindingParam("fileSelected") FileBean fileSelected) {
		if (fileSelected != null) {
			if (isArchivoExcel(fileSelected.get_file().getName())) {
				String mensaje = abrirExcel(fileSelected.get_file());
				if (mensaje.equals(""))
					StockUtils.showSuccessmessage(
							"La informacion del archivo " + fileSelected.getName() + " ha sido cargada",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				else
					StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			} else {
				StockUtils.showSuccessmessage("Formato de archivo incorrecto. Seleccione un archivo de Excel",
						Clients.NOTIFICATION_TYPE_ERROR, 0, null);
			}
		}
	}

	private boolean isArchivoExcel(String archivo) {
		boolean centinela = false;
		List<String> parts = getExtensionArchivo(archivo, ".");
		if (parts.size() > 0) {
			String extension = parts.get(parts.size() - 1);
			if (extension.equalsIgnoreCase("xlsx") || extension.equalsIgnoreCase("xls"))
				centinela = true;
		}
		return centinela;
	}

	// ------------------------------------------------------------------------------------------------------
	private String abrirExcel(File fileNameExcel) {
		String mensaje = "";
		List<CotizacionListaExcelFile> listaExcel = new ArrayList();
		boolean lecturaCorrecta = true;

		if (fileNameExcel.exists()) {
			try {
				fileInputStream = new FileInputStream(fileNameExcel);
				libro = new HSSFWorkbook(fileInputStream);

				HSSFSheet my_worksheet = libro.getSheetAt(0);
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
				fileInputStream.close();
			} catch (Exception e) {
				lecturaCorrecta = false;
			}
			if (lecturaCorrecta) {
				actualizarPreciosProductosLeidos(listaExcel);
			} else
				mensaje = "Ocurrio un error en la lectura sobre el archivo [" + cotizacionSelected.getExcelFile() + "]";
		} else
			mensaje = "El archivo [" + cotizacionSelected.getExcelFile() + "] NO existe";
		return mensaje;
	}

	private void actualizarPreciosProductosLeidos(List<CotizacionListaExcelFile> listaExcel) {
		Integer i = 0;
		if (cotizacionesConProductos == null) {
			getProductosCotizacion();
		}

		for (Cotizacion item : cotizacionesConProductos) {
			CotizacionListaExcelFile rowExcel = listaExcel.get(i);
			item.getRequisicionProducto().setCantidad(Float.parseFloat(rowExcel.getCantidad()));
			if (rowExcel.getTotal() != null && !rowExcel.getTotal().isEmpty())
				item.getRequisicionProducto().setTotalProductoPorUnidad(Float.parseFloat(rowExcel.getTotal()));
			else
				item.getRequisicionProducto().setTotalProductoPorUnidad(0F);
			Producto producto = item.getProducto();

			if (rowExcel.getPrecioUnitario() != null && !rowExcel.getPrecioUnitario().isEmpty())
				producto.setPrecio(Float.parseFloat(rowExcel.getPrecioUnitario()));
			else
				producto.setPrecio(0F);
			productoService.save(producto);
			cotizacionService.save(item);

			i++;
		}
	}

	@Command
	public void imprimirCotizacion() {
		if (cotizacionSelected != null) {
			if ((cotizacionesConProductos != null) && (cotizacionesConProductos.size() > 0)) {
				Organizacion org = (Organizacion) sessionUtils.getFromSession("FIRMA");

				HashMap mapa = new HashMap();
				// mapa.put("logotipo",
				// stockUtils.getLogotipoDeOrganizacionParaJasper(org.getLogotipo()));

				mapa.put("nombreEmpresa", org.getNombre());
				mapa.put("proveedor", cotizacionSelected.getProveedor().getNombre());

				mapa.put("direccion",
						cotizacionSelected.getProveedor().getDireccionFiscal().getCalle() + "|"
								+ cotizacionSelected.getProveedor().getDireccionFiscal().getNumExt() + "|"
								+ cotizacionSelected.getProveedor().getDireccionFiscal().getColonia() + "|"
								+ cotizacionSelected.getProveedor().getDireccionFiscal().getCuidad() + "|"
								+ cotizacionSelected.getProveedor().getDireccionFiscal().getEstado().getNombre() + "|"
								+ cotizacionSelected.getProveedor().getDireccionFiscal().getPais().getNombre());

				String telefonoMap = "";
				try {
					if ((cotizacionSelected.getProveedor().getContacto() != null)
							&& (cotizacionSelected.getProveedor().getContacto().getTelefono() != null)) {
						telefonoMap = cotizacionSelected.getProveedor().getContacto().getTelefono().getNumero();
					}
					mapa.put("telefono", telefonoMap);
				} catch (Exception e) {
				}
				String representanteLegal = "";
				if (cotizacionSelected.getProveedor().getRepresentanteLegal() != null) {
					representanteLegal = cotizacionSelected.getProveedor().getRepresentanteLegal().getNombreCompleto();
				}
				mapa.put("atencion", representanteLegal);
				mapa.put("folio", cotizacionSelected.getFolioCotizacion());

				Calendar fechaMap = Calendar.getInstance();
				if (cotizacionSelected.getFechaEnvioCotizacion() != null) {
					fechaMap = cotizacionSelected.getFechaEnvioCotizacion();
				}
				mapa.put("fecha", stockUtils.convertirCalendarToString(fechaMap));

				String emailMap = "";
				if ((cotizacionSelected.getProveedor().getContacto() != null)
						&& (cotizacionSelected.getProveedor().getContacto().getEmail() != null)) {
					emailMap = cotizacionSelected.getProveedor().getContacto().getEmail().getEmail();
				}
				mapa.put("email", emailMap);

				Float total = Float.valueOf(0.0F);
				for (Cotizacion item : cotizacionesConProductos) {
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
						generarCotizacionJasper(listaHashsParametros, aplicaciones, cotizacionesConProductos), "info",
						0, null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una cotizaci�n",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
	}

	@Command
	public String generarCotizacionJasper(List<HashMap> listaHashsParametros, List<AplicacionExterna> aplicaciones,
			List<Cotizacion> lista) {
		String mensaje = "";
		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);

		rutaPdfGenerado = (StockConstants.CARPETA_ARCHIVOS_COTIZACIONES + hashParametros.get("folio")
				+ StockUtils.getFechaActualConHora() + ".pdf");
		try {
			print = JasperFillManager.fillReport(readJasper, hashParametros, new JRBeanCollectionDataSource(lista));

			JasperExportManager.exportReportToPdfFile(print, rutaPdfGenerado);
			mensaje = "Cotizacion rxportado a PDF " + StockConstants.CARPETA_ARCHIVOS_COTIZACIONES;
		} catch (JRException e) {
			e.printStackTrace();
			for (AplicacionExterna aplicacion : aplicaciones) {
				closePdf(aplicacion.getNombre());
			}
			try {
				JasperExportManager.exportReportToPdfFile(print, StockConstants.CARPETA_ARCHIVOS_COTIZACIONES);

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
		List<Privilegios> usuarios = privilegioService.getUsuariosByPrivilegio(UserPrivileges.COTIZAR_AUTORIZAR);

		return usuarios;
	}

	@Command
	public void notificacionBandeja(@BindingParam("index") Integer index) {
		Map<String, Object> inputParams = new HashMap();
		Window productoModalView = stockUtils.createModelDialogWithParams("/modulos//utilidades/bandeja.zul",
				inputParams);

		productoModalView.doModal();
	}
}
