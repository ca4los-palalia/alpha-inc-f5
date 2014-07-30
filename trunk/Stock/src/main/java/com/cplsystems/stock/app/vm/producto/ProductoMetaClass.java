/**
 * 
 */
package com.cplsystems.stock.app.vm.producto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zul.Fileupload;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.ClasificacionPrecios;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.producto.utils.FuncionesModificacion;
import com.cplsystems.stock.app.vm.producto.utils.ModoDeBusqueda;
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
		modoDeBusqueda = new ModoDeBusqueda();
	}

	public void initProperties() {
		monedasDB = monedaService.getAll();
		productoTipoDB = productoTipoService.getAll();
		unidadesDB = unidadService.getAll();
		productosNaturalezas = productoNaturalezaService.getAll();
		readJasper = generarUrlString("jasperTemplates/reportProduct.jasper");
		// enableComboBoxUnidades = true;
		cargarListaPrecios();
		crearFuncionesModificaciones();
		modoDeBusqueda.setTipoFamilia(true);
		modoDeBusqueda.setTipoPersonalizado(true);
	}

	private void crearFuncionesModificaciones() {
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
							if (producto.getActivo())
								if (producto.getProductoNaturaleza() != null) {

									if (producto.getProductoNaturaleza()
											.getSimbolo().equals("PRO")) {
										if ((producto.getMaximo() != null && producto
												.getMaximo() > 0)
												&& (producto.getMinimo() != null && producto
														.getMinimo() > 0)) {
											if (producto.getMaximo() < producto
													.getMinimo())
												validacion = "Existencia maxima no puede ser menor al minimo";
											else if (producto.getMinimo() > producto
													.getMaximo())
												validacion = "Existencia minima no puede ser mayor al maximo";
										} else
											validacion = "Es requerido el minimo y maximo de existencia para un producto";
									}

									if (validacion.isEmpty()) {
										if (familiasProductos != null
												&& familiasProductos.size() > 0) {

											if ((producto.getPrecio() != null && producto
													.getPrecio() > 1)
													|| (producto.getPrecio2() != null && producto
															.getPrecio2() > 1)
													|| (producto.getPrecio3() != null && producto
															.getPrecio3() > 1)
													|| (producto.getPrecio4() != null && producto
															.getPrecio4() > 1)
													|| (producto.getPrecio5() != null && producto
															.getPrecio5() > 1)

											) {

											} else
												validacion = "Es necesario asignar al menos un precio para el articulo/servicio";
										} else
											validacion = "El nuevo registro debe ser agregado al menos a una familia";
									}

								} else
									validacion = "Es necesario marcar la naturaleza del nuevo registro (producto/servicio)";
							else
								validacion = "Es necesario marcar com producto activo";
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
			
			JasperExportManager.exportReportToPdfFile(print,
					StockConstants.REPORT_VARIABLE_PRODUCTO_NAME_FILE);
			openPdf(StockConstants.REPORT_VARIABLE_PRODUCTO_NAME_FILE);
			mensaje = "PDF del reporte generado: "
					+ StockConstants.REPORT_VARIABLE_PRODUCTO_NAME_FILE;

		} catch (JRException e) {

			e.printStackTrace();
			for (AplicacionExterna aplicacion : aplicaciones)
				closePdf(aplicacion.getNombre());

			try {
				JasperExportManager.exportReportToPdfFile(print,
						StockConstants.REPORT_VARIABLE_PRODUCTO_NAME_FILE);
				openPdf(StockConstants.REPORT_VARIABLE_PRODUCTO_NAME_FILE);
				mensaje = "PDF del reporte generado: "
						+ StockConstants.REPORT_VARIABLE_PRODUCTO_NAME_FILE;
			} catch (JRException e1) {
			}
		}
		return mensaje;
	}

	public void processImageUpload(Object event) {

		boolean processFile = false;

		UploadEvent upEvent = null;
		Object objUploadEvent = event;
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			int lengthofImage = media.getByteData().length;
			if (media instanceof Image) {
				/*
				 * if (lengthofSignature > 500 * 1024) {
				 * showInfo("Please Select a Image of size less than 500Kb.");
				 * return; } else {
				 */
				imagenProducto = (AImage) media;// Initialize the bind object to
				// show image in zul page and
				// Notify it also

				copiarArchivo(media, "C:\\Stock\\Users\\"
						+ upEvent.getMedia().getName());
				// }
			}
		}
	}

	public void copiarArchivo(Media media, String destino) {

		try {
			File dst = new File(destino);
			Files.copy(dst, media.getStreamData());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public String detectarEliminacionDeProducto(Producto producto) {
		String mensaje = "";

		if (requisicionProductoService.getByProducto(producto) != null)
			mensaje = producto.getNombre()
					+ " se encuentra en el catalogo de requisicion producto";

		if (mensaje.equals(""))
			if (proveedorProductoService.getByProducto(producto) != null)
				mensaje = producto.getNombre()
						+ " se encuentra en el catalogo de proveedor producto";

		if (mensaje.equals(""))
			if (familiasProductoService.getByProducto(producto) != null)
				mensaje = producto.getNombre()
						+ " esta asignado al catalogo familias";

		if (mensaje.equals(""))
			if (codigoBarrasProductoService.getByProducto(producto) != null)
				mensaje = producto.getNombre()
						+ " tiene asignado codigos de barras";

		if (mensaje.equals(""))
			if (costosProductoService.getByProducto(producto) != null)
				mensaje = producto.getNombre() + " tiene asignado costos";

		// ELIMMINAR DEL PRODUCTO LOS CATALOGO DE COSTOS
		// CODIGOS DE BARRAS
		return mensaje;
	}
}
