/**
 * 
 */
package com.cplsystems.stock.app.vm.ordencompra.utils;

import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class OrdenCompraMetaclass extends OrdenCompraVariables {

	private static final long serialVersionUID = 5093877120990395398L;

	// protected List<RequisicionProducto> requisicionProductos;
	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects() {
		loadOrdenesCompraInbox();
	}

	public void initProperties() {
		readJasper = generarUrlString(StockConstants.ARCHIVO_JASPER_ORDEN_COMPRA_FORMATO);
	}

	private void loadOrdenesCompraInbox() {
		ordenesCompraInbox = ordenCompraInboxService
				.getAll((Organizacion) sessionUtils
						.getFromSession(SessionUtils.FIRMA));
		ordenCompraInboxSeleccionada = new OrdenCompraInbox();
		for (OrdenCompraInbox compraInbox : ordenesCompraInbox) {
			if (compraInbox.getLeido() != null && !compraInbox.getLeido()) {
				compraInbox.setIcono(OrdenCompraInbox.NUEVO);
			}
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
	public void checkAceptada() {
		if (!checkBuscarAceptada)
			checkBuscarAceptada = true;
		else
			checkBuscarAceptada = false;

	}

	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	@Command
	public String generarOrdenCompraJasper(List<HashMap> listaHashsParametros,
			List<AplicacionExterna> aplicaciones, List<Cotizacion> lista) {
		String mensaje = "";
		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);

		try {
			print = JasperFillManager.fillReport(readJasper, hashParametros,
					new JRBeanCollectionDataSource(lista));

			JasperExportManager.exportReportToPdfFile(
					print,
					StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA
							+ hashParametros.get("ordenCompra")
							+ stockUtils.getFechaActualConHora()
							+ StockConstants.EXTENCION_PDF);
			openPdf(StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);
			mensaje = "Orden de Compra Exportada a PDF "
					+ StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA;

		} catch (JRException e) {
			e.printStackTrace();
			for (AplicacionExterna aplicacion : aplicaciones)
				closePdf(aplicacion.getNombre());

			try {
				JasperExportManager.exportReportToPdfFile(print,
						StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);
				openPdf(StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);
				mensaje = "Se ha generado un PDF: "
						+ StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA;
			} catch (JRException e1) {
				e1.printStackTrace();
			}
		}
		return mensaje;
	}

}
