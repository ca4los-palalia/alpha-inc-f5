package com.cplsystems.stock.app.vm.reportes;

import com.cplsystems.stock.app.vm.BasicStructure;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public abstract class ReporteVariables extends BasicStructure {
	private static final long serialVersionUID = 7810843192652008397L;
	protected String readJasper;
	protected HashMap param;
	protected JasperPrint print;
	protected JasperViewer jviewer;
}
