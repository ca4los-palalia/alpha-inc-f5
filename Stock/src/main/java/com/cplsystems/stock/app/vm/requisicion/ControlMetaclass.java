package com.cplsystems.stock.app.vm.requisicion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Almacen;
import com.cplsystems.stock.domain.AlmacenEntrada;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ControlMetaclass extends ControlVariables {
	private static final long serialVersionUID = 5093877120990395398L;

	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects() {
		loadOrdenesCompraInbox();
	}

	public void initProperties() {
		this.readJasper = generarUrlString("jasperTemplates/ordenCompraFormato.jasper");
	}

	private void loadOrdenesCompraInbox() {
		this.ordenesCompraInbox = this.ordenCompraInboxService
				.getAll((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

		this.ordenCompraInboxSeleccionada = new OrdenCompraInbox();
		for (OrdenCompraInbox compraInbox : this.ordenesCompraInbox) {
			if ((compraInbox.getLeido() != null) && (!compraInbox.getLeido().booleanValue())) {
				compraInbox.setIcono("/images/toolbar/newEmail.png");
			}
		}
	}

	

	@Command
	public String generarOrdenCompraJasper(List<HashMap> listaHashsParametros, List<AplicacionExterna> aplicaciones,
			List<Cotizacion> lista) {
		String mensaje = "";
		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);
		try {
			this.print = JasperFillManager.fillReport(this.readJasper, hashParametros,
					new JRBeanCollectionDataSource(lista));

			JasperExportManager.exportReportToPdfFile(this.print, StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA
					+ hashParametros.get("ordenCompra") + StockUtils.getFechaActualConHora() + ".pdf");

			openPdf(StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);
			mensaje = "Orden de Compra Exportada a PDF " + StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA;
		} catch (JRException e) {
			e.printStackTrace();
			for (AplicacionExterna aplicacion : aplicaciones) {
				closePdf(aplicacion.getNombre());
			}
			try {
				JasperExportManager.exportReportToPdfFile(this.print, StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);

				openPdf(StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);
				mensaje = "Se ha generado un PDF: " + StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA;
			} catch (JRException e1) {
				e1.printStackTrace();
			}
		}
		return mensaje;
	}
	
	public AlmacenEntrada crearAlmacenEntradaVacia(Cotizacion cotiz, OrdenCompra ordCompra) {
		AlmacenEntrada objeto = new AlmacenEntrada();
		objeto.setFechaEntrada(Calendar.getInstance());
		objeto.setArea(new Area());
		objeto.setAlmacen(new Almacen());
		objeto.setCotizacion(cotiz);
		objeto.setActivarCantidad(true);
		objeto.setOrdenCompra(ordCompra);
		if(cotiz.getProducto() != null)
			objeto.setProducto(cotiz.getProducto());
		return objeto;
	}
	
	public Area getAreaFromList(Long id){
		Area extraccion = null;
		for (Area item : areas) {
			if(item.getIdArea().equals(id)){
				extraccion = item;
				break;
			}
		}
		return extraccion;
	}
	public Almacen getAlmacenFromList(Long id, List<Almacen> almacenList){
		Almacen extraccion = null;
		for (Almacen item : almacenList) {
			if(item.getIdAlmacen().equals(id)){
				extraccion = item;
				break;
			}
		}
		return extraccion;
	}
	public List<Almacen> getAlmacenesByAreaFromList(List<Almacen> almacenList, Area area){
		List<Almacen> extraccion = new ArrayList<>();
		for (Almacen item : almacenList) {
			if(item.getArea().getIdArea().equals(area.getIdArea()))
				extraccion.add(item);
		}
		return extraccion;
	}
}
