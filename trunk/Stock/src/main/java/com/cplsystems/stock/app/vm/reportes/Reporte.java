/**
 * 
 */
package com.cplsystems.stock.app.vm.reportes;

import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;


/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class Reporte  extends ReporteMetaClass{

	private static final long serialVersionUID = 2445512192837362588L;
	
	
	
	@Init
	public void init() {
		
		readJasper = generarUrlString("jasperTemplates/reportProductos.jasper");
		
	}

	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	@NotifyChange("*")
	public void gererarReporte() {

		param = new HashMap();
		param.put("parameter1", "REPORTE DE PRODUCTOS");
		param.put("urlImagen", "C:\\logo.jpeg");

		try {
			
			/*
			 * JasperPrint print = JasperFillManager.fillReport(fileName, param,
			 * new JREmptyDataSource());
			 */
			print = JasperFillManager.fillReport(readJasper, param,
					new JRBeanCollectionDataSource(findReportData()));

			jviewer = new JasperViewer(print, false);
			JasperExportManager.exportReportToPdfFile(print,
					"D:\\reportProductos.pdf");
			jviewer.setVisible(true);

		} catch (JRException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	// --------------------------
	
	
}
