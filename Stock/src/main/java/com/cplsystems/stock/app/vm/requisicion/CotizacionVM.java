/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
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

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.RequisicionInbox;
import com.cplsystems.stock.domain.RequisicionProducto;
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
		// cotizacionesList = new ArrayList<Cotizacion>();
		if ((checkBuscarNueva || checkBuscarCancelada || checkBuscarEnviada || checkBuscarAceptada)
				|| (requisicion != null && (requisicion.getBuscarRequisicion() != null && !requisicion
						.getBuscarRequisicion().isEmpty()))) {

			List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();

			cotizacionesList = cotizacionService
					.getCotizacionesByEstatusRequisicionAndFolioOrProveedorByFolio(
							requisicion.getBuscarRequisicion(), null,
							listaEstatus);

			if (cotizacionesList != null) {

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
			Cotizacion cotizacion = cotizacionService
					.getById(cotizacionSelected.getIdCotizacion());
			requisicionProductos = requisicionProductoService
					.getByCotizacion(cotizacion);
		}
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void enviarCotizacion() {
		EstatusRequisicion estado = null;
		if (cotizacionSelected != null) {
			if (cotizacionSelected.getEstatusRequisicion().getClave()
					.equals(StockConstants.ESTADO_COTIZACION_NUEVA)) {
				estado = estatusRequisicionService
						.getByClave(StockConstants.ESTADO_COTIZACION_ENVIADA);
				crearArchivoExcel();
				cotizacionSelected.setEstatusRequisicion(estado);
				cotizacionService.save(cotizacionSelected);
				CotizacionInbox inbox = new CotizacionInbox();
				inbox.setCotizacion(cotizacionSelected);
				inbox.setLeido(false);
				inbox.setFechaRegistro(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
				cotizacionInboxService.save(inbox);
				
				// GENERAR PDF
				// ENVIAR CORREO

				stockUtils.showSuccessmessage("La cotizacion con folio -"
						+ cotizacionSelected.getFolioCotizacion()
						+ "- ha sido enviada", Clients.NOTIFICATION_TYPE_INFO,
						0, null);
			} else
				stockUtils.showSuccessmessage("La cotizacion con folio -"
						+ cotizacionSelected.getFolioCotizacion()
						+ "- no puede ser reenviada nuevamente",
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
				cotizacionService.save(cotizacionSelected);

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
				ordenCompraService.save(compra);

				OrdenCompraInbox inbox = new OrdenCompraInbox();
				inbox.setOrdenCompra(compra);
				inbox.setLeido(false);
				inbox.setFechaCreacion(new StockUtils()
						.convertirCalendarToDate(Calendar.getInstance()));
				ordenCompraInboxService.save(inbox);

				stockUtils.showSuccessmessage("La cotizacion con folio -"
						+ cotizacionSelected.getFolioCotizacion()
						+ "- ha sido Aceptada", Clients.NOTIFICATION_TYPE_INFO,
						0, null);
			} else
				stockUtils.showSuccessmessage("La cotizacion con folio -"
						+ cotizacionSelected.getFolioCotizacion()
						+ "- nu puede ser aceptada bajo este estatus ("
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
				Executions
						.createComponents(
								"/modulos/requisicion/cancelacionCotizacion.zul",
								null, map);

			} else
				stockUtils.showSuccessmessage("La cotizacion con folio -"
						+ cotizacionSelected.getFolioCotizacion()
						+ "- nu puede ser cancelada bajo este estatus ("
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

		for (int i = 0; i < requisicionProductos.size(); i++) {
			RequisicionProducto item = requisicionProductos.get(i);

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
							.getCofiaPartidaGenerica().getNombre());
					break;
				case 4:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(item.getCantidad()
							.toString());
					break;
				case 5:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(item.getProducto()
							.getUnidad().getNombre());
					break;
				case 6:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(String.valueOf(item
							.getProducto().getPrecio()));
					break;
				case 7:
					celda = fila.createCell((short) j);
					texto = new HSSFRichTextString(String.valueOf(item
							.getTotalProductoPorUnidad()));
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
			FileOutputStream elFichero = new FileOutputStream("C://" + hash
					+ ".xls");
			libro.write(elFichero);

			elFichero.close();
			excelGenerado = true;
			cotizacionSelected.setExcelFile(hash);
			cotizacionService.save(cotizacionSelected);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
