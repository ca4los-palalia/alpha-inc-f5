/**
 * 
 */
package com.cplsystems.stock.app.vm.producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.zkoss.bind.annotation.Init;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.ClasificacionPrecios;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.producto.utils.ProductoVariables;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;

/**
 * @author Carlos Palalia
 * 
 */
public abstract class ProductoMetaClass extends ProductoVariables {

	private static final long serialVersionUID = -4078164796340868446L;

	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects(){
		producto = new Producto();
		buscarProducto = new Producto();
		productoTipoSelected = new ProductoTipo();
		cotizacionDB = new ArrayList<Cotizacion>();
		precios = new ArrayList<ClasificacionPrecios>();
		precioSelected = new ClasificacionPrecios();
	}
	
	public void initProperties(){
		monedasDB = monedaService.getAll();
		productoTipoDB = productoTipoService.getAll();
		unidadesDB = unidadService.getAll();
		readJasper = generarUrlString("jasperTemplates/reportProduct.jasper");
		cargarListaPrecios();
	}
	
	private void cargarListaPrecios(){
		ClasificacionPrecios precioMinimo = new ClasificacionPrecios();
		precioMinimo.setNombre("Precio Minimo");
		precioMinimo.setTipo("MIN");
		ClasificacionPrecios precioMaximo = new ClasificacionPrecios();
		precioMaximo.setNombre("Precio Maximo");
		precioMaximo.setTipo("MAX");
		ClasificacionPrecios precioPromedio = new ClasificacionPrecios();
		precioPromedio.setNombre("Precio Promedio");
		precioPromedio.setTipo("PRO");
		ClasificacionPrecios precioPersonalizado = new ClasificacionPrecios();
		precioPersonalizado.setNombre("Precio Personalizado");
		precioPersonalizado.setTipo("PER");
		
		precios.add(precioMinimo);
		precios.add(precioMaximo);
		precios.add(precioPromedio);
		precios.add(precioPersonalizado);
	}
	public String getReadJasperReconstruccion(String urlInicial, String file){
		String nuevaUrl = "";
		
		String[] numerosComoArray = urlInicial.split("/");
		
		for (int i = 0; i < numerosComoArray.length; i++) {
			if(i == (numerosComoArray.length -1))
				nuevaUrl += file;
			else
			nuevaUrl += numerosComoArray[i];
		}
		
		return nuevaUrl;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String generarReportePrductos(List<HashMap> listaHashsParametros, List<AplicacionExterna> aplicaciones, List<Producto> lista) {
		String mensaje = "";

		HashMap hashParametros =  
				construirHashMapParametros(listaHashsParametros);

		try {

			print = JasperFillManager.fillReport(readJasper, hashParametros,
					new JRBeanCollectionDataSource(lista));

			jviewer = new JasperViewer(print, false);
			JasperExportManager.exportReportToPdfFile(print,
					StockConstants.REPORT_PRODUCTO_NAME_FILE);
			openPdf(StockConstants.REPORT_PRODUCTO_NAME_FILE);
			mensaje = "PDF del reporte generado: "
					+ StockConstants.REPORT_PRODUCTO_NAME_FILE;
			// jviewer.setVisible(true);

		} catch (JRException e) {
			
			for (AplicacionExterna aplicacion : aplicaciones)
				closePdf(aplicacion.getNombre());

			try {
				JasperExportManager.exportReportToPdfFile(print,
						StockConstants.REPORT_PRODUCTO_NAME_FILE);
				openPdf(StockConstants.REPORT_PRODUCTO_NAME_FILE);
				mensaje = "PDF del reporte generado: "
						+ StockConstants.REPORT_PRODUCTO_NAME_FILE;
			} catch (JRException e1) {
			}
		}
		return mensaje;
	}
	
}
