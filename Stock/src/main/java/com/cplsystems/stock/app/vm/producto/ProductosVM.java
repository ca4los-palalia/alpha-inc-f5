package com.cplsystems.stock.app.vm.producto;

import com.cplsystems.stock.app.utils.ClasificacionPrecios;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.producto.utils.ModoDeBusqueda;
import com.cplsystems.stock.domain.AplicacionExterna;
import com.cplsystems.stock.domain.ClaveArmonizada;
import com.cplsystems.stock.domain.CodigoBarrasProducto;
import com.cplsystems.stock.domain.CostosProducto;
import com.cplsystems.stock.domain.FamiliasProducto;
import com.cplsystems.stock.domain.Moneda;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoNaturaleza;
import com.cplsystems.stock.domain.ProductoTipo;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.ClaveArmonizadaService;
import com.cplsystems.stock.services.CodigoBarrasProductoService;
import com.cplsystems.stock.services.CostosProductoService;
import com.cplsystems.stock.services.FamiliasProductoService;
import com.cplsystems.stock.services.MonedaService;
import com.cplsystems.stock.services.ProductoNaturalezaService;
import com.cplsystems.stock.services.ProductoService;
import com.cplsystems.stock.services.ProductoTipoService;
import com.cplsystems.stock.services.UnidadService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

@VariableResolver({ DelegatingVariableResolver.class })
public class ProductosVM extends ProductoMetaClass {
	private static final long serialVersionUID = 313977001812349337L;
	private Button clasificacionButton;
	public Button saveButton;

	@Init
	public void init() {
		super.init();
		producto.setCambioNaturaleza(true);
		
		productoTipoDB = productoTipoService.getAll();
		productoDB = productoService.getAllNativeSQL();
	}

	@Command({ "upload" })
	@NotifyChange({ "imagenProducto" })
	public void onImageUpload(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		processImageUpload(ctx.getTriggerEvent());
	}

	@Command
	public void onUploadExcel(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		claveArmonizadaList = claveArmonizadaService.getAll();
		unidadesDB = unidadService.getAll();
		productosNaturalezas = productoNaturalezaService.getAll();
		monedasDB = monedaService.getAll();
		
		productosDB = leerDatosDesdeExcel(getStreamMediaExcel(ctx));
		if(productosDB.size() > 0){
			for (Producto item : productosDB) {
				productoService.save(item);
			}
			Messagebox.show(productosDB.size() + " Productos Importados");
		}else
			Messagebox.show("No se importaron Productos. El documento esta vacio");
	}

	@SuppressWarnings("rawtypes")
	private List<Producto> leerDatosDesdeExcel(InputStream inPutStream) {
		List<Producto> productoNuevosExcel = new ArrayList<Producto>();
		Usuarios usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(0);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = 0;
			while (rowIterator.hasNext()) {
				Producto producto = new Producto();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i > 0) {
					int j = 0;
					while (iterator.hasNext()) {
						if (j < 25) {
							XSSFCell hssfCell = (XSSFCell) iterator.next();
							producto = crearProducto(producto, hssfCell, j);
						} else
							break;
						j++;
					}
					producto.setUsuario(usuario);
					producto.setOrganizacion(organizacion);
					productoNuevosExcel.add(producto);
				}
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productoNuevosExcel;

	}

	@SuppressWarnings("null")
	private Producto crearProducto(Producto producto, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if(valor != null && !valor.equals("NULL")){
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				
				producto.setClaveArmonizada(getClaveArminizadaFromList(valor));
				producto.setClave(valor);
			}
			break;
		case 1:
			if(valor != null && !valor.equals("NULL"))
				producto.setNombre(valor);
			break;
		case 2:
			if(valor != null && !valor.equals("NULL"))
				producto.setMarca(valor);
			break;
		case 3:
			if(valor != null && !valor.equals("NULL"))
				producto.setModelo(valor);
			break;
		case 4:
			if(valor != null && !valor.equals("NULL"))
				producto.setCodigoBarras(valor);
			break;
		case 5:
			if(valor != null && !valor.equals("NULL")){
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				producto.setUnidad(getUnidadFromList(Long.parseLong(valor)));
			}
				
			break;
		case 6:
			if(valor != null && !valor.equals("NULL"))
				producto.setDescripcion(valor);
			break;
		case 7:
			if(valor != null || !valor.equals("NULL")){
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				if(valor.equals("1"))
					producto.setActivo(true);
				else
					producto.setActivo(false);
			}
			
			break;
		case 8:
			if(valor != null && !valor.equals("NULL")){
				valor = String.valueOf(valorDePropiedad);
				if (valor.contains(".0")) {
					String[] splitter = valor.split(".0");
					valor = splitter[0];
				}
				producto.setProductoNaturaleza(getProductoNaturalezaFromList(Long.parseLong(valor)));
			}
				
			break;
		case 9:
			if(valor != null && !valor.equals("NULL")){
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				producto.setEnExistencia(Integer.parseInt(valor));
			}
				
			break;
			
		case 10:
			if(valor != null && !valor.equals("NULL")){
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				producto.setMinimo(Long.valueOf(valor));
			}
			break;
		case 11:
			if(valor != null && !valor.equals("NULL")){
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				producto.setMaximo(Long.valueOf(valor));
			}
			break;
		case 12:
			if(valor != null && !valor.equals("NULL")){
				//Clasificacion
			}
			break;
		case 13:
			if(valor != null && !valor.equals("NULL")){
				valor = String.valueOf(valorDePropiedad);
				if (valor.contains(".0")) {
					String[] splitter = valor.split(".0");
					valor = splitter[0];
				}
				producto.setMoneda(getMonedaFromList(Long.parseLong(valor)));
			}
			break;
		case 14:
			if(valor != null && !valor.equals("NULL")){
				//EXCENTO
			}
			
			break;
		case 15:
			if(valor != null && !valor.equals("NULL")){
				// % Impuesto 1
			}
			
			break;
		case 16:
			if(valor != null && !valor.equals("NULL")){
				// % Impuesto 2
			}
			break;
		case 17:
			valor = String.valueOf(valorDePropiedad);
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				producto.setPrecio(Float.valueOf(Float.parseFloat(String.valueOf(valorDePropiedad))));
			}
			break;
		case 18:
			valor = String.valueOf(valorDePropiedad);
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				producto.setPrecio2(Float.valueOf(Float.parseFloat(String.valueOf(valorDePropiedad))));
			}
			break;
		case 19:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				producto.setPrecio3(Float.valueOf(Float.parseFloat(String.valueOf(valorDePropiedad))));
			}
			break;
		case 20:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				producto.setPrecio4(Float.valueOf(Float.parseFloat(String.valueOf(valorDePropiedad))));
			}
			break;
		case 21:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				producto.setPrecio5(Float.valueOf(Float.parseFloat(String.valueOf(valorDePropiedad))));
			}
			break;
		case 22:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				producto.setCostoMaximo(Float.valueOf(Float.parseFloat(String.valueOf(valorDePropiedad))));
			}
			break;
		case 23:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				producto.setCostoReposicion(Float.valueOf(Float.parseFloat(String.valueOf(valorDePropiedad))));
			}
			break;
		case 24:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				producto.setCostoPromedio(Float.valueOf(Float.parseFloat(String.valueOf(valorDePropiedad))));
			}
			break;
		}
		return producto;
	}


	@Command
	@NotifyChange({ "*" })
	private void goProgress(String mensaje, Integer value) {
		this.progressValueLabel = mensaje;
		this.progressValue = value;
	}

	@Command
	@NotifyChange({ "imagenProducto" })
	public void generarBar() {
	}

	@Command
	@NotifyChange({ "*" })
	public void nuevaCaptura() {
		this.producto = new Producto();
		this.claveArmonizada = new ClaveArmonizada();
		this.productosDB = new ArrayList();
		this.enableComboBoxUnidades = false;
		this.producto.setClaveArmonizada(this.claveArmonizada);
	}

	@Command
	@NotifyChange({ "*" })
	public void newRecord() {
		if ((this.producto != null) && (this.producto.getIdProducto() == null)) {
			String validar = validarNuevoProducto();
			if (validar.isEmpty()) {
				Organizacion org = (Organizacion) this.sessionUtils.getFromSession("FIRMA");

				this.producto.setOrganizacion(org);
				this.productoService.save(this.producto);
				for (FamiliasProducto fp : this.familiasProductos) {
					this.familiasProductoService.save(fp);
				}
				StockUtils.showSuccessmessage(
						"Un nuevo producto con nombre " + this.producto.getNombre() + " ha sido creado.", "info",
						Integer.valueOf(0), this.saveButton);

				this.producto = new Producto();
				this.familiasProductos = new ArrayList();
			} else {
				Clients.showNotification(validar, "warning", this.saveButton, null, 0);
			}
		} else {
			this.productoService.save(this.producto);
			StockUtils.showSuccessmessage(this.producto.getNombre() + " ha sido actualizado.", "info",
					Integer.valueOf(0), this.saveButton);
		}
	}

	@Command
	public void deleteRecord() {
		if (this.producto.getIdProducto() == null) {
			Messagebox.show("El producto no puede ser eliminado Asegurese de haber seleccionado un producto");

			return;
		}
		String validarProducto = detectarEliminacionDeProducto(this.producto);
		if (validarProducto.equals("")) {
			Messagebox.show("�Est� seguro de remover este producto?, esta acci�n es irreversible", "Question", 3,
					"z-msgbox z-msgbox-question", new EventListener() {
						public void onEvent(Event event) throws Exception {
							if (((Integer) event.getData()).intValue() == 1) {
								ProductosVM.this.productoService.delete(ProductosVM.this.producto);
								ProductosVM.this.productoDB.remove(ProductosVM.this.producto);

								ProductosVM.this.familiasProductos = ProductosVM.this.familiasProductoService
										.getByProducto(ProductosVM.this.producto);
								if (ProductosVM.this.familiasProductos != null) {
									for (FamiliasProducto fp : ProductosVM.this.familiasProductos) {
										ProductosVM.this.familiasProductoService.delete(fp);
									}
								}
								ProductosVM.this.familiasProductos = new ArrayList();

								StockUtils.showSuccessmessage(
										"el producto -" + ProductosVM.this.producto.getNombre() + "- ha sido eliminado",
										"info", Integer.valueOf(0), null);

								ProductosVM.this.producto = null;
							} else {
								StockUtils.showSuccessmessage("La eliminacion del producto -"
										+ ProductosVM.this.producto.getNombre() + "- ha sido cancelada", "info",
										Integer.valueOf(0), null);

								ProductosVM.this.producto = ProductosVM.this.productoService
										.getById(ProductosVM.this.producto.getIdProducto());
							}
						}
					});
		} else {
			StockUtils.showSuccessmessage(validarProducto, "info", Integer.valueOf(0), null);
		}
		this.producto = this.productoService.getById(this.producto.getIdProducto());
	}

	@Command
	@NotifyChange({ "producto" })
	public void saveChanges() {
		if ((this.producto != null) && (this.producto.getIdProducto() != null)) {
			this.productoService.save(this.producto);

			StockUtils.showSuccessmessage("el producto -" + this.producto.getNombre() + "- ha sido actualizado", "info",
					Integer.valueOf(0), null);

			return;
		}
		if (this.producto == null) {
			this.producto = new Producto();
		}
	}

	@Command
	public void search() {
		Window productoModalView = this.stockUtils.createModelDialog("/modulos/productos/utils/buscarProducto.zul");

		productoModalView.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "producto" })
	public void deleteProduct(@BindingParam("producto") Producto producto) {
		if (producto != null) {
			this.productoService.delete(producto);
			newRecord();
		}
	}

	@GlobalCommand
	@NotifyChange({ "producto" })
	public void updateFromSelectedItem(@BindingParam("productoSeleccionado") Producto productoSeleccionado) {
		if (productoSeleccionado != null) {
			this.producto = productoSeleccionado;
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void activarComboBoxUnidades() {
		if (this.producto.getIdProducto() != null) {
			if (this.producto.isSeleccionar()) {
				this.enableComboBoxUnidades = false;
			} else {
				this.unidad = null;
				this.enableComboBoxUnidades = true;
				if (this.producto.getIdProducto() != null) {
					this.producto.setUnidad(this.productoService.getById(this.producto.getIdProducto()).getUnidad());
				} else {
					this.producto.setUnidad(null);
				}
			}
		} else {
			this.producto.setSeleccionar(false);
			StockUtils.showSuccessmessage("No se puede realizar una modificacion sobre un producto que aun no existe",
					"warning", Integer.valueOf(0), null);
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void actualizaNombreUnidad() {
		if (this.unidad != null) {
			this.producto.setUnidad(this.unidad);
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void copiarClave() {
		if ((this.producto != null) && (this.producto.getClaveArmonizada() != null)) {
			String clave = String.valueOf(this.producto.getClaveArmonizada().getGrupo())
					+ String.valueOf(this.producto.getClaveArmonizada().getSubGrupo())
					+ String.valueOf(this.producto.getClaveArmonizada().getClase());
			if ((this.producto.getClaveArmonizada().getSubclase() != null)
					&& (this.producto.getClaveArmonizada().getTipoDeBien() != null)) {
				clave = clave + this.producto.getClaveArmonizada().getSubclase()
						+ this.producto.getClaveArmonizada().getTipoDeBien();
			}
			this.producto.setClave(clave);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void findProductos() {
		if ((buscarProducto.getNombre() != null) && (!buscarProducto.getNombre().isEmpty())) {
			if (!buscarProducto.getNombre().equals("*")) {
				productoDB = productoService.getByClaveNombre(buscarProducto.getNombre());
			}else if (buscarProducto.getNombre().equals("*")) {
				productoDB = productoService.getAllNativeSQL();
			}
			if (productoDB != null) {
				String mensaje = "";
				if (productoDB.size() == 1) {
					mensaje = "producto";
				} else if (productoDB.size() > 1) {
					mensaje = "productos";
				}
				
				if (buscarProducto.getNombre().equals("*")) {
					StockUtils.showSuccessmessage(
							"Tu búsqueda -" + buscarProducto.getNombre() + "- no obtuvo ning�n resultado",
							"warning", Integer.valueOf(0), null);
				} else {
					StockUtils.showSuccessmessage("Tu b�squeda -" + buscarProducto.getNombre() + "- obtuvo "
							+ productoDB.size() + " " + mensaje, "info", Integer.valueOf(0), null);
				}
				buscarProducto.setDescripcion(String.valueOf(productoDB.size()));

				producto = new Producto();
				enableComboBoxUnidades = true;
			} else {
				productoDB = new ArrayList<>();
				StockUtils.showSuccessmessage(
						"Tu b�squeda -" + buscarProducto.getNombre() + "- no obtuvo ning�n resultado", "warning",
						Integer.valueOf(0), null);
			}
		} else {
			productoDB = new ArrayList<>();
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void findProductoPrecioCosto() {
		if ((this.buscarProducto.getNombre() != null) && (!this.buscarProducto.getNombre().isEmpty())) {
			if (!this.buscarProducto.getNombre().equals("*")) {
				this.producto = this.productoService.getByClaveNombrePrecioCosto(this.buscarProducto.getNombre());
			}
			if (this.producto != null) {
				this.codigosBarrasProductos = this.codigoBarrasProductoService.getByProducto(this.producto);

				String mensaje = "";

				StockUtils.showSuccessmessage("-" + this.buscarProducto.getNombre() + "- Ha sido encontrado.", "info",
						Integer.valueOf(0), null);

				this.costosProducto = this.costosProductoService.getByProducto(this.producto);
				if (this.costosProducto == null) {
					this.costosProducto = new CostosProducto();
				}
			} else {
				this.producto = new Producto();
				StockUtils.showSuccessmessage(
						"Tu b�squeda -" + this.buscarProducto.getNombre() + "- no obtuvo ning�n resultado", "warning",
						Integer.valueOf(0), null);
			}
		} else {
			this.producto = new Producto();
			StockUtils.showSuccessmessage("Tu b�squeda no obtuvo ning�n resultado", "error", Integer.valueOf(0), null);
		}
	}

	@NotifyChange({ "productoDB" })
	@Command
	public void changeComboClasificacion() {
		String mensaje = "Ningun producto encontrado de tipo: " + productoTipoSelected.getNombre();

		familiasProductos = familiasProductoService.getByFamilia(productoTipoSelected);
		if (familiasProductos != null) {
			if (familiasProductos.size() == 1) {
				mensaje = "Se encontro " + familiasProductos.size() + " producto de tipo "
						+ productoTipoSelected.getNombre();
			} else
				mensaje = "Se encontraron " + familiasProductos.size() + " productos de tipo "
						+ productoTipoSelected.getNombre();
			
			
			productoDB = new ArrayList<>();
			for (FamiliasProducto item : familiasProductos) {
				productoDB.add(item.getProducto());
			}
			
			StockUtils.showSuccessmessage(mensaje, "info", Integer.valueOf(0), this.clasificacionButton);
		} else {
			StockUtils.showSuccessmessage(mensaje, "warning", Integer.valueOf(0), this.clasificacionButton);
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void changeComboPrecio() {
		if ((this.precioSelected != null) && (this.precioSelected.getTipo().equals("MAX"))) {
			this.productoDB = this.productoService.getPreciosMaximos();
		} else if ((this.precioSelected != null) && (this.precioSelected.getTipo().equals("MIN"))) {
			this.productoDB = this.productoService.getPreciosMinimos();
		} else if ((this.precioSelected != null) && (this.precioSelected.getTipo().equals("PRO"))) {
			this.productoDB = this.productoService.getPreciosPromedio();
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void buscarPorPrecioPersonalizado() {
		if ((this.precioSelected != null) && (this.precioSelected.getTipo() != null)) {
			if ((this.precioSelected.getTipo() != null) && (this.precioSelected.getTipo().equals("PER"))) {
				String mensaje = "Ningun producto encontrado de tipo: " + this.productoTipoSelected.getNombre();

				boolean numeroAceptado = false;
				if (this.precioSelected.getPrecio() != null) {
					try {
						Integer.parseInt(this.precioSelected.getPrecio());
						numeroAceptado = true;
					} catch (Exception e) {
					}
					if (numeroAceptado) {
						this.productoDB = this.productoService.getByPrecio(this.precioSelected.getPrecio());
						if (this.productoDB != null) {
							if (this.productoDB.size() == 1) {
								mensaje = "Se encontro " + this.productoDB.size() + " producto con precio: "
										+ this.precioSelected.getNombre();
							} else {
								mensaje = "Se encontraron " + this.productoDB.size() + " productos con precio "
										+ this.precioSelected.getNombre();
							}
						}
						StockUtils.showSuccessmessage(mensaje, "info", Integer.valueOf(0), null);
					} else {
						StockUtils.showSuccessmessage("El valor debe ser numerico, INTENTE NUEVAMENTE", "error",
								Integer.valueOf(0), null);
					}
				} else {
					StockUtils.showSuccessmessage("Campo requerido para realizar la busqueda", "warning",
							Integer.valueOf(0), null);
				}
			} else {
				StockUtils.showSuccessmessage(
						"La busqueda que desea realizar corresponde solo a la categoria PRECIO PERSONALIZADO",
						"warning", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar una de las categorias de los precios", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void reporteProductos() {
		if (this.productoDB != null) {
			HashMap mapa = new HashMap();
			mapa.put("parameter1", "REPORTE DE PRODUCTOS");

			mapa.put("empresaTitle", "PROVEEDORA DE MATERIAL ELECTRICO Y PLOMERIA S.A. de C.V.");

			List<HashMap> listaHashsParametros = new ArrayList();
			listaHashsParametros.add(mapa);

			List<AplicacionExterna> aplicaciones = new ArrayList();
			AplicacionExterna aplicacion = new AplicacionExterna();
			aplicacion.setNombre("PDFXCview");
			aplicaciones.add(aplicacion);

			StockUtils.showSuccessmessage(generarReportePrductos(listaHashsParametros, aplicaciones, this.productoDB),
					"info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("NO existe alg�n resultado de busqueda para generar el reporte (PDF)",
					"error", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void reporteProductosClasificacion() {
		if (this.productoDB != null) {
			HashMap mapa = new HashMap();
			mapa.put("parameter1",
					"REPORTE PRODUCTOS DE : ''" + this.productoTipoSelected.getNombre().toUpperCase() + "''");

			mapa.put("empresaTitle", "PROVEEDORA DE MATERIAL ELECTRICO Y PLOMERIA S.A. de C.V.");

			List<HashMap> listaHashsParametros = new ArrayList();
			listaHashsParametros.add(mapa);

			List<AplicacionExterna> aplicaciones = new ArrayList();
			AplicacionExterna aplicacion = new AplicacionExterna();
			aplicacion.setNombre("PDFXCview");
			aplicaciones.add(aplicacion);

			StockUtils.showSuccessmessage(generarReportePrductos(listaHashsParametros, aplicaciones, this.productoDB),
					"info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("NO existe alg�n resultado de busqueda para generar el reporte (PDF)",
					"error", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void agregaFamiliaADerecha() {
		if ((this.productoTipoSelected != null) && (this.productoTipoSelected.getIdProductoTipo() != null)) {
			boolean agregarProductoTipo = true;
			if ((this.familiasProductos != null) && (this.familiasProductos.size() > 0)) {
				for (int i = 0; i < this.familiasProductos.size(); i++) {
					if (this.productoTipoSelected
							.equals(((FamiliasProducto) this.familiasProductos.get(i)).getProductoTipo())) {
						agregarProductoTipo = false;
						break;
					}
				}
			}
			if (agregarProductoTipo) {
				if (this.familiasProductos == null) {
					this.familiasProductos = new ArrayList();
				}
				FamiliasProducto familiasProducto = new FamiliasProducto();
				familiasProducto.setProductoTipo(this.productoTipoSelected);
				familiasProducto.setProducto(this.producto);
				this.familiasProductos.add(familiasProducto);
			}
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void quitarFamiliaAIzquierda() {
		if ((this.familiasProductos != null) && (this.familiasProductos.size() > 0)
				&& (this.familiasProducto != null)) {
			if (this.familiasProducto.getIdFamiliasProducto() != null) {
				this.familiasProductoService.delete(this.familiasProducto);
			}
			this.familiasProductos.remove(this.familiasProducto);
		}
	}

	@Command
	@NotifyChange({ "claveAr1" })
	public void obtenerListaFamiliasProducto() {
		if (producto != null) {
			producto = productoService.getById(producto.getIdProducto());
			familiasProductos = familiasProductoService.getByProducto(producto);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void addNewItemCodigo() {
		if ((this.producto != null) && (this.producto.getIdProducto() != null)) {
			if (this.codigosBarrasProductos == null) {
				this.codigosBarrasProductos = new ArrayList();
			}
			if (this.codigosBarrasProductos.size() > 0) {
				if ((((CodigoBarrasProducto) this.codigosBarrasProductos.get(this.codigosBarrasProductos.size() - 1))
						.getIdCodigoBarrasProducto() != null)
						|| (((CodigoBarrasProducto) this.codigosBarrasProductos
								.get(this.codigosBarrasProductos.size() - 1)).getCodigo() != null)) {
					CodigoBarrasProducto nuevoItem = new CodigoBarrasProducto();
					nuevoItem.setProducto(this.producto);
					this.codigosBarrasProductos.add(nuevoItem);
				}
			} else {
				CodigoBarrasProducto nuevoItem = new CodigoBarrasProducto();
				nuevoItem.setProducto(this.producto);
				this.codigosBarrasProductos.add(nuevoItem);
			}
		} else {
			StockUtils.showSuccessmessage("para poder agregar un codigo, asegurese de haber cargado un producto",
					"error", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void delItemCodigo() {
		if ((this.codigoBarrasProducto != null) && (this.codigoBarrasProducto.isSelected())
				&& (this.codigosBarrasProductos.size() > 0)) {
			this.codigosBarrasProductos.remove(this.codigoBarrasProducto);
			if (this.codigoBarrasProducto.getIdCodigoBarrasProducto() != null) {
				this.codigoBarrasProductoService.delete(this.codigoBarrasProducto);
			}
			if (this.codigoBarrasProducto.getCodigo() != null) {
				StockUtils.showSuccessmessage(this.codigoBarrasProducto.getCodigo() + " ha sido eliminado", "info",
						Integer.valueOf(0), null);
			}
			this.codigoBarrasProducto = new CodigoBarrasProducto();
		}
	}

	@Command
	public void codigoSeleccionado() {
		this.codigoBarrasProducto.setSelected(true);
		this.codigoBarrasProducto.setProducto(this.producto);
	}

	@Command
	@NotifyChange({ "*" })
	public void guardarCodigosBarras() {
		if ((this.codigosBarrasProductos != null) && (this.codigosBarrasProductos.size() > 0)) {
			for (CodigoBarrasProducto item : this.codigosBarrasProductos) {
				item.setProducto(this.producto);
				if ((item.getCodigo() != null) && (!item.getCodigo().isEmpty())) {
					item.setCodigo(item.getCodigo().toUpperCase());
					this.codigoBarrasProductoService.save(item);
				}
			}
			StockUtils.showSuccessmessage(
					"Nuevos codigos han sido guardados para el producto " + this.producto.getNombre(), "info",
					Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("No se llevo a cabo ningun cambio", "warning", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void actualizarPrecioCosto() {
		if ((this.producto != null) && (this.producto.getIdProducto() != null)) {
			String mensaje = "";
			if ((this.costosProductoNuevo.getReposicionUnitario() != null)
					&& (this.costosProductoNuevo.getReposicionActualizado() != null)
					&& (this.costosProductoNuevo.getMaximoUnitario() != null)
					&& (this.costosProductoNuevo.getMaximoActualizado() != null)) {
				this.costosProductoNuevo.setProducto(this.producto);
				this.costosProductoService.save(this.costosProductoNuevo);
				mensaje = mensaje + "costos";
			}
			if (!mensaje.equals("")) {
				mensaje = mensaje + " y precios han sido actualizados";
			} else {
				mensaje = mensaje + " los precios han sido actualizados";
			}
			this.productoService.save(this.producto);

			this.costosProducto = this.costosProductoService.getByProducto(this.producto);
			if (this.costosProducto == null) {
				this.costosProducto = new CostosProducto();
			}
			this.costosProductoNuevo = new CostosProducto();

			StockUtils.showSuccessmessage(mensaje, "info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("Asegurese de haber cargado primero un producto", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void precioMasivoFamilia() {
		if ((this.productoTipoDB == null) || (this.productoTipoDB.size() == 0)) {
			this.productoTipoDB = this.productoTipoService.getAll();
		}
		this.hiddeFamilia = true;
		this.hiddeProveedor = false;

		System.err.println("familia");
	}

	@Command
	@NotifyChange({ "*" })
	public void precioMasivoProveedor() {
		System.err.println("proveedor");
		this.hiddeFamilia = false;
		this.hiddeProveedor = true;
	}

	@Command
	@NotifyChange({ "*" })
	public void seleccionNaturalezaProducto() {
		if (this.productoNaturaleza != null) {
			if (this.productoNaturaleza.getNombre().equals("Producto")) {
				this.producto.setCambioNaturaleza(false);
				this.producto.setEnExistencia(null);
			} else {
				this.producto.setCambioNaturaleza(true);
				this.producto.setEnExistencia(null);
			}
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void buscarPorFamilia() {
		this.modoDeBusqueda.setOcultarFamilia(false);
		this.modoDeBusqueda.setOcultarPersonalizado(true);
		this.modoDeBusqueda.setTipoFamilia(false);
		this.modoDeBusqueda.setTipoPersonalizado(true);
		this.familiasProductos = new ArrayList();
		this.familiasProducto = new FamiliasProducto();
		this.producto = new Producto();
	}

	@Command
	@NotifyChange({ "*" })
	public void buscarPorPerzonalizado() {
		this.modoDeBusqueda.setOcultarFamilia(true);
		this.modoDeBusqueda.setOcultarPersonalizado(false);
		this.modoDeBusqueda.setTipoFamilia(true);
		this.modoDeBusqueda.setTipoPersonalizado(false);
		this.productoDB = new ArrayList();
		this.producto = new Producto();
	}

	@Command
	@NotifyChange({ "*" })
	public void reporteProductosClasificacionSubmenu() {
		if ((this.familiasProductos != null) && (this.familiasProductos.size() > 0)) {
			if (this.productoDB == null) {
				this.productoDB = new ArrayList();
			}
			for (FamiliasProducto fp : this.familiasProductos) {
				this.productoDB.add(fp.getProducto());
			}
			if (this.productoDB != null) {
				HashMap mapa = new HashMap();
				mapa.put("parameter1",
						"REPORTE PRODUCTOS DE : ''" + this.productoTipoSelected.getNombre().toUpperCase() + "''");

				mapa.put("empresaTitle", "PROVEEDORA DE MATERIAL ELECTRICO Y PLOMERIA S.A. de C.V.");

				List<HashMap> listaHashsParametros = new ArrayList();
				listaHashsParametros.add(mapa);

				List<AplicacionExterna> aplicaciones = new ArrayList();
				AplicacionExterna aplicacion = new AplicacionExterna();
				aplicacion.setNombre("PDFXCview");
				aplicaciones.add(aplicacion);

				StockUtils.showSuccessmessage(
						generarReportePrductos(listaHashsParametros, aplicaciones, this.productoDB), "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("NO existe alg�n resultado de busqueda para generar el reporte (PDF)",
						"error", Integer.valueOf(0), null);
			}
		}
	}

	public Button getSaveButton() {
		return this.saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabPrecio() {
	}

	/*
	@Command
	@NotifyChange({ "*" })
	public void cargarListaProductos() {
		productoDB = productoService.getAll();
		if (this.productoDB != null) {
			this.modoDeBusqueda.setOcultarFamilia(true);
			//posiblemente eliminar variable
		}
	}
	*/
}
