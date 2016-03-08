package com.cplsystems.stock.app.vm.producto;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.ClasificacionPrecios;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.producto.utils.FuncionesModificacion;
import com.cplsystems.stock.app.vm.producto.utils.ModoDeBusqueda;
import com.cplsystems.stock.app.vm.producto.utils.ProductoVariables;
import com.cplsystems.stock.domain.ClaveArmonizada;
import com.cplsystems.stock.domain.CodigoBarrasProducto;
import com.cplsystems.stock.domain.CostosProducto;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoNaturaleza;
import com.cplsystems.stock.domain.ProductoTipo;
import com.cplsystems.stock.services.ClaveArmonizadaService;
import com.cplsystems.stock.services.CodigoBarrasProductoService;
import com.cplsystems.stock.services.CostosProductoService;
import com.cplsystems.stock.services.FamiliasProductoService;
import com.cplsystems.stock.services.MonedaService;
import com.cplsystems.stock.services.ProductoNaturalezaService;
import com.cplsystems.stock.services.ProductoTipoService;
import com.cplsystems.stock.services.ProveedorProductoService;
import com.cplsystems.stock.services.RequisicionProductoService;
import com.cplsystems.stock.services.UnidadService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.zkoss.bind.annotation.Init;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;

public abstract class ProductoMetaClass extends ProductoVariables {
	private static final long serialVersionUID = -4078164796340868446L;

	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects() {
		this.producto = new Producto();
		this.buscarProducto = new Producto();
		this.productoTipoSelected = new ProductoTipo();
		this.cotizacionDB = new ArrayList();
		this.precios = new ArrayList();
		this.precioSelected = new ClasificacionPrecios();
		this.familiasProductos = new ArrayList();
		this.codigosBarrasProductos = new ArrayList();
		this.codigoBarrasProducto = new CodigoBarrasProducto();
		this.costosProductoNuevo = new CostosProducto();
		this.costosProductos = new ArrayList();
		this.modoDeBusqueda = new ModoDeBusqueda();
		this.claveArmonizada = new ClaveArmonizada();
	}

	public void initProperties() {
		this.claveArmonizadaList = this.claveArmonizadaService.getAll();
		this.monedasDB = this.monedaService.getAll();
		this.productoTipoDB = this.productoTipoService.getAll();
		this.unidadesDB = this.unidadService.getAll();
		this.productosNaturalezas = this.productoNaturalezaService.getAll();
		this.readJasper = generarUrlString("jasperTemplates/reportProduct.jasper");

		this.producto.setClaveArmonizada(this.claveArmonizada);
		cargarListaPrecios();
		crearFuncionesModificaciones();
		this.modoDeBusqueda.setTipoFamilia(true);
		this.modoDeBusqueda.setTipoPersonalizado(true);
	}

	private void crearFuncionesModificaciones() {
		this.funcionesModificaciones = new ArrayList();
		FuncionesModificacion item1 = new FuncionesModificacion();
		item1.setIdentificador(Integer.valueOf(1));
		item1.setNombre("Margen o factor");

		FuncionesModificacion item2 = new FuncionesModificacion();
		item2.setIdentificador(Integer.valueOf(2));
		item2.setNombre("Precio");

		FuncionesModificacion item3 = new FuncionesModificacion();
		item3.setIdentificador(Integer.valueOf(3));
		item3.setNombre("M�ximo costo");

		FuncionesModificacion item4 = new FuncionesModificacion();
		item4.setIdentificador(Integer.valueOf(4));
		item4.setNombre("Incremento precio por porcentaje");

		FuncionesModificacion item5 = new FuncionesModificacion();
		item5.setIdentificador(Integer.valueOf(5));
		item5.setNombre("Incrementar precios en valor");

		FuncionesModificacion item6 = new FuncionesModificacion();
		item6.setIdentificador(Integer.valueOf(6));
		item6.setNombre("Incrementar m�ximo costo por porcentaje");

		FuncionesModificacion item7 = new FuncionesModificacion();
		item7.setIdentificador(Integer.valueOf(7));
		item7.setNombre("Incrementar m�ximo costo en valor");

		this.funcionesModificaciones.add(item1);
		this.funcionesModificaciones.add(item2);
		this.funcionesModificaciones.add(item3);
		this.funcionesModificaciones.add(item4);
		this.funcionesModificaciones.add(item5);
		this.funcionesModificaciones.add(item6);
		this.funcionesModificaciones.add(item7);
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

		this.precios.add(precioMinimo);
		this.precios.add(precioMaximo);
		this.precios.add(precioPromedio);
		this.precios.add(precioPersonalizado);
	}

	public String validarNuevoProducto() {
		String validacion = "";
		if ((this.producto.getClave() != null) && (!this.producto.getClave().isEmpty())) {
			if ((this.producto.getNombre() != null) && (!this.producto.getNombre().isEmpty())) {
				if ((this.producto.getMarca() != null) && (!this.producto.getMarca().isEmpty())) {
					if ((this.producto.getModelo() != null) && (!this.producto.getModelo().isEmpty())) {
						if (this.producto.getUnidad() != null) {
							if (this.producto.getActivo()) {
								if (this.producto.getProductoNaturaleza() != null) {
									if (this.producto.getProductoNaturaleza().getSimbolo().equals("PRO")) {
										if ((this.producto.getMaximo() != null)
												&& (this.producto.getMaximo().longValue() > 0L)
												&& (this.producto.getMinimo() != null)
												&& (this.producto.getMinimo().longValue() > 0L)) {
											if (this.producto.getMaximo().longValue() < this.producto.getMinimo()
													.longValue()) {
												validacion = "Existencia maxima no puede ser menor al minimo";
											} else if (this.producto.getMinimo().longValue() > this.producto.getMaximo()
													.longValue()) {
												validacion = "Existencia minima no puede ser mayor al maximo";
											}
										} else {
											validacion = "Es requerido el minimo y maximo de existencia para un producto";
										}
									}
									if (validacion.isEmpty()) {
										if ((this.familiasProductos != null) && (this.familiasProductos.size() > 0)) {
											if (((this.producto.getPrecio() == null)
													|| (this.producto.getPrecio().floatValue() <= 1.0F))
													&& ((this.producto.getPrecio2() == null)
															|| (this.producto.getPrecio2().floatValue() <= 1.0F))
													&& ((this.producto.getPrecio3() == null)
															|| (this.producto.getPrecio3().floatValue() <= 1.0F))
													&& ((this.producto.getPrecio4() == null)
															|| (this.producto.getPrecio4().floatValue() <= 1.0F))
													&& ((this.producto.getPrecio5() == null)
															|| (this.producto.getPrecio5().floatValue() <= 1.0F))) {
												validacion = "Es necesario asignar al menos un precio para el articulo/servicio";
											}
										} else {
											validacion = "El nuevo registro debe ser agregado al menos a una familia";
										}
									}
								} else {
									validacion = "Es necesario marcar la naturaleza del nuevo registro (producto/servicio)";
								}
							} else {
								validacion = "Es necesario marcar com producto activo";
							}
						} else {
							validacion = "La unidad de medida del producto es requerido";
						}
					} else {
						validacion = "El modelo del producto es requerido";
					}
				} else {
					validacion = "La marca del producto es requerido";
				}
			} else {
				validacion = "El nombre del producto es requerido";
			}
		} else {
			validacion = "La clave del producto es requerido";
		}
		return validacion;
	}

	public String getReadJasperReconstruccion(String urlInicial, String file) {
		String nuevaUrl = "";

		String[] numerosComoArray = urlInicial.split("/");
		for (int i = 0; i < numerosComoArray.length; i++) {
			if (i == numerosComoArray.length - 1) {
				nuevaUrl = nuevaUrl + file;
			} else {
				nuevaUrl = nuevaUrl + numerosComoArray[i];
			}
		}
		return nuevaUrl;
	}

	public String generarReportePrductos(List<HashMap> listaHashsParametros, List<AplicacionExterna> aplicaciones,
			List<Producto> lista) {
		String mensaje = "";

		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);
		try {
			this.print = JasperFillManager.fillReport(this.readJasper, hashParametros,
					new JRBeanCollectionDataSource(lista));

			JasperExportManager.exportReportToPdfFile(this.print, StockConstants.REPORT_VARIABLE_PRODUCTO_NAME_FILE);

			openPdf(StockConstants.REPORT_VARIABLE_PRODUCTO_NAME_FILE);
			mensaje = "PDF del reporte generado: " + StockConstants.REPORT_VARIABLE_PRODUCTO_NAME_FILE;
		} catch (JRException e) {
			e.printStackTrace();
			for (AplicacionExterna aplicacion : aplicaciones) {
				closePdf(aplicacion.getNombre());
			}
			try {
				JasperExportManager.exportReportToPdfFile(this.print,
						StockConstants.REPORT_VARIABLE_PRODUCTO_NAME_FILE);

				openPdf(StockConstants.REPORT_VARIABLE_PRODUCTO_NAME_FILE);
				mensaje = "PDF del reporte generado: " + StockConstants.REPORT_VARIABLE_PRODUCTO_NAME_FILE;
			} catch (JRException e1) {
			}
		}
		return mensaje;
	}

	public void processImageUpload(Object event) {
		boolean processFile = false;

		UploadEvent upEvent = null;
		Object objUploadEvent = event;
		if ((objUploadEvent != null) && ((objUploadEvent instanceof UploadEvent))) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			int lengthofImage = media.getByteData().length;
			if ((media instanceof Image)) {
				this.imagenProducto = ((AImage) media);

				copiarArchivo(media, "C:\\Stock\\Users\\" + upEvent.getMedia().getName());
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
		if (this.requisicionProductoService.getByProducto(producto) != null) {
			mensaje = producto.getNombre() + " se encuentra en el catalogo de requisicion producto";
		}
		if ((mensaje.equals("")) && (this.proveedorProductoService.getByProducto(producto) != null)) {
			mensaje = producto.getNombre() + " se encuentra en el catalogo de proveedor producto";
		}
		if ((mensaje.equals("")) && (this.familiasProductoService.getByProducto(producto) != null)) {
			mensaje = producto.getNombre() + " esta asignado al catalogo familias";
		}
		if ((mensaje.equals("")) && (this.codigoBarrasProductoService.getByProducto(producto) != null)) {
			mensaje = producto.getNombre() + " tiene asignado codigos de barras";
		}
		if ((mensaje.equals("")) && (this.costosProductoService.getByProducto(producto) != null)) {
			mensaje = producto.getNombre() + " tiene asignado costos";
		}
		return mensaje;
	}
}
