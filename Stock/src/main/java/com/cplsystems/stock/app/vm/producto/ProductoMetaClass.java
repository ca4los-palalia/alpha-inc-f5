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
import com.cplsystems.stock.app.vm.producto.utils.FuncionesModificacion;
import com.cplsystems.stock.app.vm.producto.utils.ProductoVariables;
import com.cplsystems.stock.domain.CodigoBarrasProducto;
import com.cplsystems.stock.domain.CostosProducto;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.FamiliasProducto;
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

	public void initObjects() {
		producto = new Producto();
		buscarProducto = new Producto();
		productoTipoSelected = new ProductoTipo();
		cotizacionDB = new ArrayList<Cotizacion>();
		precios = new ArrayList<ClasificacionPrecios>();
		precioSelected = new ClasificacionPrecios();
		familiasProductos = new ArrayList<FamiliasProducto>();
		codigosBarrasProductos = new ArrayList<CodigoBarrasProducto>();
		codigoBarrasProducto = new CodigoBarrasProducto();
		costosProductoNuevo = new CostosProducto();
		costosProductos = new ArrayList<CostosProducto>();
	}

	public void initProperties() {
		monedasDB = monedaService.getAll();
		productoTipoDB = productoTipoService.getAll();
		unidadesDB = unidadService.getAll();
		productosNaturalezas = productoNaturalezaService.getAll();
		readJasper = generarUrlString("jasperTemplates/reportProduct.jasper");
		//enableComboBoxUnidades = true;
		cargarListaPrecios();
		crearFuncionesModificaciones();
		
	}

	private void crearFuncionesModificaciones(){
		funcionesModificaciones = new ArrayList<FuncionesModificacion>();
		FuncionesModificacion item1 = new FuncionesModificacion();
		item1.setIdentificador(1);
		item1.setNombre("Margen o factor");
		
		FuncionesModificacion item2 = new FuncionesModificacion();
		item2.setIdentificador(2);
		item2.setNombre("Precio");
		
		FuncionesModificacion item3 = new FuncionesModificacion();
		item3.setIdentificador(3);
		item3.setNombre("Máximo costo");
		
		FuncionesModificacion item4 = new FuncionesModificacion();
		item4.setIdentificador(4);
		item4.setNombre("Incremento precio por porcentaje");
		
		FuncionesModificacion item5 = new FuncionesModificacion();
		item5.setIdentificador(5);
		item5.setNombre("Incrementar precios en valor");
		
		FuncionesModificacion item6 = new FuncionesModificacion();
		item6.setIdentificador(6);
		item6.setNombre("Incrementar máximo costo por porcentaje");
		
		FuncionesModificacion item7 = new FuncionesModificacion();
		item7.setIdentificador(7);
		item7.setNombre("Incrementar máximo costo en valor");
		
		funcionesModificaciones.add(item1);
		funcionesModificaciones.add(item2);
		funcionesModificaciones.add(item3);
		funcionesModificaciones.add(item4);
		funcionesModificaciones.add(item5);
		funcionesModificaciones.add(item6);
		funcionesModificaciones.add(item7);
	}
	
	private void cargarListaPrecios() {
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
	
	public String validarNuevoProducto() {
		String validacion = "";
		if (producto.getClave() != null && !producto.getClave().isEmpty())
			if (producto.getNombre() != null && !producto.getNombre().isEmpty())
				if (producto.getMarca() != null
						&& !producto.getMarca().isEmpty())
					if (producto.getModelo() != null
							&& !producto.getModelo().isEmpty())
						if (producto.getUnidad() != null)
							System.err.println();
						else
							validacion = "La unidad de medida del producto es requerido";
					else
						validacion = "El modelo del producto es requerido";
				else
					validacion = "La marca del producto es requerido";
			else
				validacion = "El nombre del producto es requerido";
		else
			validacion = "La clave del producto es requerido";
		
		return validacion;
	}

	public String getReadJasperReconstruccion(String urlInicial, String file) {
		String nuevaUrl = "";

		String[] numerosComoArray = urlInicial.split("/");

		for (int i = 0; i < numerosComoArray.length; i++) {
			if (i == (numerosComoArray.length - 1))
				nuevaUrl += file;
			else
				nuevaUrl += numerosComoArray[i];
		}

		return nuevaUrl;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String generarReportePrductos(List<HashMap> listaHashsParametros,
			List<AplicacionExterna> aplicaciones, List<Producto> lista) {
		String mensaje = "";

		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);

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
			
			e.printStackTrace();
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
