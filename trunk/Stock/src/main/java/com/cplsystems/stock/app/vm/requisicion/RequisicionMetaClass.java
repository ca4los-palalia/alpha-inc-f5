/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

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
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.RequisicionInbox;
import com.cplsystems.stock.domain.RequisicionProducto;

/**
 * @author Carlos Palalia
 * 
 */
public abstract class RequisicionMetaClass extends RequisicionVariables {

	private static final long serialVersionUID = -4078164796340868446L;

	@Init
	public void init() {
		readJasper = generarUrlString("jasperTemplates/requisicionFormato.jasper");
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
					StockConstants.REPORT_VARIABLE_REQUISICION_NAME_FILE);
			openPdf(StockConstants.REPORT_VARIABLE_REQUISICION_NAME_FILE);
			mensaje = "REQUISICIÓN GENERADA EN PDF"
					+ StockConstants.REPORT_VARIABLE_REQUISICION_NAME_FILE;

		} catch (JRException e) {
			e.printStackTrace();
			for (AplicacionExterna aplicacion : aplicaciones)
				closePdf(aplicacion.getNombre());

			try {
				JasperExportManager.exportReportToPdfFile(print,
						StockConstants.REPORT_VARIABLE_REQUISICION_NAME_FILE);
				openPdf(StockConstants.REPORT_VARIABLE_REQUISICION_NAME_FILE);
				mensaje = "Se ha generado un PDFPDF del reporte generado: "
						+ StockConstants.REPORT_VARIABLE_REQUISICION_NAME_FILE;
			} catch (JRException e1) {
				e1.printStackTrace();
			}
		}
		return mensaje;
	}
}
