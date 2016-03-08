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
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver({ DelegatingVariableResolver.class })
public class Reporte extends ReporteMetaClass {
	private static final long serialVersionUID = 2445512192837362588L;

	@Init
	public void init() {
		this.readJasper = generarUrlString("jasperTemplates/reportProductos.jasper");
	}

	@Command
	@NotifyChange({ "*" })
	public void gererarReporte() {
		this.param = new HashMap();
		this.param.put("parameter1", "REPORTE DE PRODUCTOS");
		this.param.put("urlImagen", "C:\\logo.jpeg");
		try {
			this.print = JasperFillManager.fillReport(this.readJasper, this.param,
					new JRBeanCollectionDataSource(findReportData()));

			this.jviewer = new JasperViewer(this.print, false);
			JasperExportManager.exportReportToPdfFile(this.print, "D:\\reportProductos.pdf");

			this.jviewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
