/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.RequisicionProducto;

/**
 * @author Carlos Palalia
 * 
 */
public abstract class RequisicionMetaClass extends RequisicionVariables {

	private static final long serialVersionUID = -4078164796340868446L;

	@Init
	public void init() {
		readJasper = generarUrlString(StockConstants.ARCHIVO_JASPER_REQUISICION_FORMATO);
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
	
	public List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;

		if (checkBuscarNueva || checkBuscarCancelada || checkBuscarEnviada
				|| checkBuscarAceptada) {
			lista = new ArrayList<EstatusRequisicion>();

			if (checkBuscarNueva)
				lista.add(estatusRequisicionService
						.getByClave(StockConstants.ESTADO_REQUISICION_NUEVA));
			if (checkBuscarCancelada)
				lista.add(estatusRequisicionService
						.getByClave(StockConstants.ESTADO_REQUISICION_CANCELADA));
			if (checkBuscarAceptada)
				lista.add(estatusRequisicionService
						.getByClave(StockConstants.ESTADO_REQUISICION_TERMINADA));
		}

		return lista;
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "static-access" })
	@Command
	public String generarRequisicionJasper(List<HashMap> listaHashsParametros,
			List<AplicacionExterna> aplicaciones,
			List<RequisicionProducto> lista) {
		String mensaje = "";
		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);

		try {
			print = JasperFillManager.fillReport(readJasper, hashParametros,
					new JRBeanCollectionDataSource(lista));

			JasperExportManager.exportReportToPdfFile(print,
					StockConstants.CARPETA_ARCHIVOS_REQUISICIONES + hashParametros.get("folio") + stockUtils.getFechaActualConHora() +  StockConstants.EXTENCION_PDF);
			openPdf(StockConstants.CARPETA_ARCHIVOS_REQUISICIONES);
			mensaje = "REQUISICIÓN GENERADA EN PDF"
					+ StockConstants.CARPETA_ARCHIVOS_REQUISICIONES;

		} catch (JRException e) {
			e.printStackTrace();
			for (AplicacionExterna aplicacion : aplicaciones)
				closePdf(aplicacion.getNombre());

			try {
				JasperExportManager.exportReportToPdfFile(print,
						StockConstants.CARPETA_ARCHIVOS_REQUISICIONES);
				openPdf(StockConstants.CARPETA_ARCHIVOS_REQUISICIONES);
				mensaje = "Se ha generado un PDF: "
						+ StockConstants.CARPETA_ARCHIVOS_REQUISICIONES;
			} catch (JRException e1) {
				e1.printStackTrace();
			}
		}
		return mensaje;
	}
}
