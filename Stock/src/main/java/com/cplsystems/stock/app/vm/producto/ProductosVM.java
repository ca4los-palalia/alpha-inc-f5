/**
 * 
 */
package com.cplsystems.stock.app.vm.producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.domain.CodigoBarrasProducto;
import com.cplsystems.stock.domain.CostosProducto;
import com.cplsystems.stock.domain.FamiliasProducto;
import com.cplsystems.stock.domain.Producto;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProductosVM extends ProductoMetaClass {

	private static final long serialVersionUID = 313977001812349337L;

	private Button clasificacionButton;
	private Button saveButton;

	@Init
	public void init() {
		super.init();
	}
	
	
	

	@Command
	@NotifyChange("*")
	public void nuevaCaptura() {
		producto = new Producto();
		productosDB = new ArrayList<Producto>();
		enableComboBoxUnidades = false;
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void newRecord() {

		if (producto != null && producto.getIdProducto() == null) {
			String validar = validarNuevoProducto();
			if (validar.isEmpty()) {
				productoService.save(producto);

				for (FamiliasProducto fp : familiasProductos) {
					familiasProductoService.save(fp);
				}
				stockUtils.showSuccessmessage("Un nuevo producto con nombre "
						+ producto.getNombre() + " ha sido creado.",
						Clients.NOTIFICATION_TYPE_INFO, 0, saveButton);
				producto = new Producto();
				familiasProductos = new ArrayList<FamiliasProducto>();
			} else
				stockUtils.showSuccessmessage(validar,
						Clients.NOTIFICATION_TYPE_WARNING, 0, saveButton);

		} else {

		}

		/*
		 * if (producto != null && producto.getIdProducto() != null) {
		 * Messagebox.show(
		 * "Se han detectado cambios que no han sido confirmados, " +
		 * "¿Está seguro de crear un nuevo registro?", "Question", Messagebox.OK
		 * | Messagebox.CANCEL, Messagebox.QUESTION, new EventListener<Event>()
		 * { public void onEvent(Event event) throws Exception { if (((Integer)
		 * event.getData()).intValue() == Messagebox.OK) { Map<String, Object>
		 * args = new HashMap<String, Object>();
		 * args.put("productoSeleccionado", new Producto());
		 * BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem",
		 * args); return; } } }); } if (producto == null) { producto = new
		 * Producto(); }
		 */
	}

	@Command
	public void deleteRecord() {
		if (producto.getIdProducto() == null) {
			Messagebox.show("El producto no puede ser eliminado "
					+ "Asegurese de haber seleccionado un producto");
			return;
		}
		Messagebox.show("¿Está seguro de remover este producto?, "
				+ "esta acción es irreversible", "Question", Messagebox.OK
				| Messagebox.CANCEL, Messagebox.QUESTION,
				new EventListener<Event>() {
					@SuppressWarnings("static-access")
					public void onEvent(Event event) throws Exception {
						if (((Integer) event.getData()).intValue() == Messagebox.OK) {
							/*
							 * Map<String, Object> args = new HashMap<String,
							 * Object>(); args.put("producto", producto);
							 * BindUtils.postGlobalCommand(null, null,
							 * "deleteProduct", args);
							 */
							productoService.delete(producto);
							productosDB.remove(producto);

							familiasProductos = familiasProductoService
									.getByProducto(producto);
							if (familiasProductos != null) {
								for (FamiliasProducto fp : familiasProductos)
									familiasProductoService.delete(fp);
							}
							familiasProductos = new ArrayList<FamiliasProducto>();
							stockUtils.showSuccessmessage("el producto -"
									+ producto.getNombre()
									+ "- ha sido eliminado",
									Clients.NOTIFICATION_TYPE_INFO, 0, null);
							producto = null;
						} else {
							stockUtils.showSuccessmessage(
									"La eliminacion del producto -"
											+ producto.getNombre()
											+ "- ha sido cancelada",
									Clients.NOTIFICATION_TYPE_INFO, 0, null);
							producto = productoService.getById(producto
									.getIdProducto());
						}
					}
				});

	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("producto")
	public void saveChanges() {
		if (producto != null && producto.getIdProducto() != null) {
			Messagebox.show(
					"Se han detectado cambios que no han sido confirmados, "
							+ "¿Está seguro de crear un nuevo registro?",
					"Question", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION, new EventListener<Event>() {
						public void onEvent(Event event) throws Exception {
							if (((Integer) event.getData()).intValue() == Messagebox.OK) {
								/*
								 * Map<String, Object> args = new
								 * HashMap<String, Object>();
								 * args.put("productoSeleccionado", new
								 * Producto());
								 * BindUtils.postGlobalCommand(null, null,
								 * "updateFromSelectedItem", args);
								 */

								productoService.save(producto);

								for (FamiliasProducto fp : familiasProductos)
									if (fp.getIdFamiliasProducto() == null)
										familiasProductoService.save(fp);

								stockUtils.showSuccessmessage("el producto -"
										+ producto.getNombre()
										+ "- ha sido actualizado",
										Clients.NOTIFICATION_TYPE_INFO, 0, null);
								return;
							} else {
								unidad = null;
								stockUtils.showSuccessmessage(
										"La actualizacion para el producto -"
												+ producto.getNombre()
												+ "- ha sido cancelada",
										Clients.NOTIFICATION_TYPE_INFO, 0, null);
								producto = productoService.getById(producto
										.getIdProducto());
							}
						}
					});
		}
		if (producto == null) {
			producto = new Producto();
		}
		/*
		 * productoService.save(producto); stockUtils.showSuccessmessage(
		 * "La información del producto " + producto.getNombre() +
		 * " se ha actualizado correctamente", Clients.NOTIFICATION_TYPE_INFO,
		 * 3000, null); producto = new Producto();
		 */
	}

	@Command
	public void search() {
		Window productoModalView = stockUtils
				.createModelDialog(StockConstants.MODAL_VIEW_PRODUCTOS);
		productoModalView.doModal();
	}

	@GlobalCommand
	@NotifyChange("producto")
	public void deleteProduct(@BindingParam("producto") Producto producto) {
		if (producto != null) {
			productoService.delete(producto);
			newRecord();
		}
	}

	@GlobalCommand
	@NotifyChange("producto")
	public void updateFromSelectedItem(
			@BindingParam("productoSeleccionado") Producto productoSeleccionado) {
		if (productoSeleccionado != null) {
			producto = productoSeleccionado;
		}
	}

	@Command
	@NotifyChange("*")
	public void selectDynamic(@BindingParam("tabs") TabInfo tabs) {
		if (tabs != null) {
			productoDB = productoService.getByTipo(tabs.getProductoTipo());
		}

	}

	@NotifyChange("*")
	@Command
	public void activarComboBoxUnidades() {
		if (producto.getIdProducto() != null) {
			if (producto.isSeleccionar())
				enableComboBoxUnidades = false;
			else {
				unidad = null;
				enableComboBoxUnidades = true;
				if (producto.getIdProducto() != null)
					producto.setUnidad(productoService.getById(
							producto.getIdProducto()).getUnidad());
				else
					producto.setUnidad(null);
			}
		} else {
			producto.setSeleccionar(false);
			stockUtils
					.showSuccessmessage(
							"No se puede realizar una modificacion sobre un producto que aun no existe",
							Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}

	}

	@NotifyChange("*")
	@Command
	public void actualizaNombreUnidad() {
		if (unidad != null)
			producto.setUnidad(unidad);
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void findProductos() {
		if (buscarProducto.getNombre() != null
				&& !buscarProducto.getNombre().isEmpty()) {
			if (buscarProducto.getNombre().equals("*")) {
				// productosDB = productoService.getAll();
			} else {
				productosDB = productoService.getByClaveNombre(buscarProducto
						.getNombre());
			}

			if (productosDB != null) {
				String mensaje = "";
				if (productosDB.size() == 1)
					mensaje = "producto";
				else if (productosDB.size() > 1)
					mensaje = "productos";

				if (buscarProducto.getNombre().equals("*"))
					stockUtils.showSuccessmessage(
							"Tu búsqueda obtuvo todos los proveedores",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				else
					stockUtils.showSuccessmessage("Tu búsqueda -"
							+ buscarProducto.getNombre() + "- obtuvo "
							+ productosDB.size() + " " + mensaje,
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				buscarProducto
						.setDescripcion(String.valueOf(productosDB.size()));
				producto = new Producto();
				enableComboBoxUnidades = true;

			} else
				stockUtils.showSuccessmessage(
						"Tu búsqueda -" + buscarProducto.getNombre()
								+ "- no obtuvo ningún resultado",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);

		} else {
			stockUtils.showSuccessmessage(
					"Tu búsqueda no obtuvo ningún resultado",
					Clients.NOTIFICATION_TYPE_ERROR, 0, null);
		}
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void findProductoPrecioCosto() {
		if (buscarProducto.getNombre() != null
				&& !buscarProducto.getNombre().isEmpty()) {
			if (buscarProducto.getNombre().equals("*")) {
				// productosDB = productoService.getAll();
			} else {
				producto = productoService
						.getByClaveNombrePrecioCosto(buscarProducto.getNombre());
			}

			if (producto != null) {
				codigosBarrasProductos = codigoBarrasProductoService
						.getByProducto(producto);
				String mensaje = "";

				stockUtils.showSuccessmessage("-" + buscarProducto.getNombre()
						+ "- Ha sido encontrado.",
						Clients.NOTIFICATION_TYPE_INFO, 0, null);

				/*
				 * buscarProducto
				 * .setDescripcion(String.valueOf(productosDB.size()));
				 */
				costosProducto = costosProductoService.getByProducto(producto);
				if (costosProducto == null)
					costosProducto = new CostosProducto();
			} else {
				producto = new Producto();
				stockUtils.showSuccessmessage(
						"Tu búsqueda -" + buscarProducto.getNombre()
								+ "- no obtuvo ningún resultado",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}

		} else {
			producto = new Producto();
			stockUtils.showSuccessmessage(
					"Tu búsqueda no obtuvo ningún resultado",
					Clients.NOTIFICATION_TYPE_ERROR, 0, null);
		}
	}

	@SuppressWarnings("static-access")
	@NotifyChange("*")
	@Command
	public void changeComboClasificacion() {
		String mensaje = "Ningun producto encontrado de tipo: "
				+ productoTipoSelected.getNombre();

		productoDB = productoService.getByTipo(productoTipoSelected);

		if (productoDB != null) {
			if (productoDB.size() == 1)
				mensaje = "Se encontro " + productoDB.size()
						+ " producto de tipo "
						+ productoTipoSelected.getNombre();
			else
				mensaje = "Se encontraron " + productoDB.size()
						+ " productos de tipo "
						+ productoTipoSelected.getNombre();

			stockUtils.showSuccessmessage(mensaje,
					Clients.NOTIFICATION_TYPE_INFO, 0, clasificacionButton);
		}

		else
			stockUtils.showSuccessmessage(mensaje,
					Clients.NOTIFICATION_TYPE_WARNING, 0, clasificacionButton);

	}

	@NotifyChange("*")
	@Command
	public void changeComboPrecio() {
		if (precioSelected != null && precioSelected.getTipo().equals("MAX"))
			productoDB = productoService.getPreciosMaximos();
		else if (precioSelected != null
				&& precioSelected.getTipo().equals("MIN"))
			productoDB = productoService.getPreciosMinimos();
		else if (precioSelected != null
				&& precioSelected.getTipo().equals("PRO"))
			productoDB = productoService.getPreciosPromedio();
	}

	@SuppressWarnings("static-access")
	@NotifyChange("*")
	@Command
	public void buscarPorPrecioPersonalizado() {
		if (precioSelected != null && precioSelected.getTipo() != null) {

			if (precioSelected.getTipo() != null
					&& precioSelected.getTipo().equals("PER")) {
				String mensaje = "Ningun producto encontrado de tipo: "
						+ productoTipoSelected.getNombre();
				boolean numeroAceptado = false;
				if (precioSelected.getPrecio() != null) {
					try {
						Integer.parseInt(precioSelected.getPrecio());
						numeroAceptado = true;
					} catch (Exception e) {
					}

					if (numeroAceptado) {
						productoDB = productoService.getByPrecio(precioSelected
								.getPrecio());
						if (productoDB != null) {
							if (productoDB.size() == 1)
								mensaje = "Se encontro " + productoDB.size()
										+ " producto con precio: "
										+ precioSelected.getNombre();
							else
								mensaje = "Se encontraron " + productoDB.size()
										+ " productos con precio "
										+ precioSelected.getNombre();

						}
						stockUtils.showSuccessmessage(mensaje,
								Clients.NOTIFICATION_TYPE_INFO, 0, null);
					} else
						stockUtils
								.showSuccessmessage(
										"El valor debe ser numerico, INTENTE NUEVAMENTE",
										Clients.NOTIFICATION_TYPE_ERROR, 0,
										null);
				} else
					stockUtils.showSuccessmessage(
							"Campo requerido para realizar la busqueda",
							Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			} else {
				stockUtils
						.showSuccessmessage(
								"La busqueda que desea realizar corresponde solo a la categoria PRECIO PERSONALIZADO",
								Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else
			stockUtils
					.showSuccessmessage(
							"Es necesario seleccionar una de las categorias de los precios",
							Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	@Command
	@NotifyChange("*")
	public void reporteProductos() {
		if (productosDB != null) {

			HashMap mapa = new HashMap();
			mapa.put(StockConstants.REPORT_PROVEEDOR_PARAM1,
					"REPORTE DE PRODUCTOS");
			mapa.put(StockConstants.REPORT_PROVEEDOR_NOMBRE_EMPRESA,
					"PROVEEDORA DE MATERIAL ELECTRICO Y PLOMERIA S.A. de C.V.");
			List<HashMap> listaHashsParametros = new ArrayList<HashMap>();
			listaHashsParametros.add(mapa);

			List<AplicacionExterna> aplicaciones = new ArrayList<AplicacionExterna>();
			AplicacionExterna aplicacion = new AplicacionExterna();
			aplicacion.setNombre("PDFXCview");
			aplicaciones.add(aplicacion);

			stockUtils.showSuccessmessage(
					generarReportePrductos(listaHashsParametros, aplicaciones,
							productosDB), Clients.NOTIFICATION_TYPE_INFO, 0,
					null);
		} else {
			stockUtils
					.showSuccessmessage(
							"NO existe algún resultado de busqueda para generar el reporte (PDF)",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
		}
	}

	@SuppressWarnings({ "static-access", "rawtypes", "unchecked" })
	@Command
	@NotifyChange("*")
	public void reporteProductosClasificacion() {
		if (productoDB != null) {

			HashMap mapa = new HashMap();
			mapa.put(StockConstants.REPORT_PROVEEDOR_PARAM1,
					"REPORTE PRODUCTOS DE : ''"
							+ productoTipoSelected.getNombre().toUpperCase()
							+ "''");
			mapa.put(StockConstants.REPORT_PROVEEDOR_NOMBRE_EMPRESA,
					"PROVEEDORA DE MATERIAL ELECTRICO Y PLOMERIA S.A. de C.V.");
			List<HashMap> listaHashsParametros = new ArrayList<HashMap>();
			listaHashsParametros.add(mapa);

			List<AplicacionExterna> aplicaciones = new ArrayList<AplicacionExterna>();
			AplicacionExterna aplicacion = new AplicacionExterna();
			aplicacion.setNombre("PDFXCview");
			aplicaciones.add(aplicacion);

			// readJasper = getReadJasperReconstruccion(readJasper, file)

			stockUtils.showSuccessmessage(
					generarReportePrductos(listaHashsParametros, aplicaciones,
							productoDB), Clients.NOTIFICATION_TYPE_INFO, 0,
					null);
		} else {
			stockUtils
					.showSuccessmessage(
							"NO existe algún resultado de busqueda para generar el reporte (PDF)",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
		}
	}

	@Command
	@NotifyChange("*")
	public void agregaFamiliaADerecha() {
		if (productoTipoSelected != null
				&& productoTipoSelected.getIdProductoTipo() != null) {
			boolean agregarProductoTipo = true;
			if (familiasProductos != null && familiasProductos.size() > 0) {
				for (int i = 0; i < familiasProductos.size(); i++) {
					if (productoTipoSelected.equals(familiasProductos.get(i)
							.getProductoTipo())) {
						agregarProductoTipo = false;
						break;
					}
				}
			} else {
			}

			if (agregarProductoTipo) {
				if (familiasProductos == null)
					familiasProductos = new ArrayList<FamiliasProducto>();
				FamiliasProducto familiasProducto = new FamiliasProducto();
				familiasProducto.setProductoTipo(productoTipoSelected);
				familiasProducto.setProducto(producto);
				familiasProductos.add(familiasProducto);
			}

		}
	}

	@Command
	@NotifyChange("*")
	public void quitarFamiliaAIzquierda() {

		if (familiasProductos != null && familiasProductos.size() > 0) {
			if (familiasProducto != null) {

				if (familiasProducto.getIdFamiliasProducto() != null)
					familiasProductoService.delete(familiasProducto);
				familiasProductos.remove(familiasProducto);
			}
		}
	}

	@Command
	@NotifyChange("*")
	public void obtenerListaFamiliasProducto() {
		if (producto != null)
			familiasProductos = familiasProductoService.getByProducto(producto);
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void addNewItemCodigo() {
		if (producto != null && producto.getIdProducto() != null) {
			if (codigosBarrasProductos == null)
				codigosBarrasProductos = new ArrayList<CodigoBarrasProducto>();

			if (codigosBarrasProductos.size() > 0) {

				if (codigosBarrasProductos.get(
						codigosBarrasProductos.size() - 1)
						.getIdCodigoBarrasProducto() == null
						&& codigosBarrasProductos.get(
								codigosBarrasProductos.size() - 1).getCodigo() == null) {

				} else {
					CodigoBarrasProducto nuevoItem = new CodigoBarrasProducto();
					nuevoItem.setProducto(producto);
					codigosBarrasProductos.add(nuevoItem);
				}

			} else {
				CodigoBarrasProducto nuevoItem = new CodigoBarrasProducto();
				nuevoItem.setProducto(producto);
				codigosBarrasProductos.add(nuevoItem);
			}

		} else {
			stockUtils
					.showSuccessmessage(
							"para poder agregar un codigo, asegurese de haber cargado un producto",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
		}

	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void delItemCodigo() {
		if (codigoBarrasProducto != null && codigoBarrasProducto.isSelected()) {
			if (codigosBarrasProductos.size() > 0) {
				codigosBarrasProductos.remove(codigoBarrasProducto);
				if (codigoBarrasProducto.getIdCodigoBarrasProducto() != null)
					codigoBarrasProductoService.delete(codigoBarrasProducto);

				if (codigoBarrasProducto.getCodigo() != null)
					stockUtils.showSuccessmessage(
							codigoBarrasProducto.getCodigo()
									+ " ha sido eliminado",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);

				codigoBarrasProducto = new CodigoBarrasProducto();
			}
		}
	}

	@Command
	public void codigoSeleccionado() {
		codigoBarrasProducto.setSelected(true);
		codigoBarrasProducto.setProducto(producto);
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void guardarCodigosBarras() {
		if (codigosBarrasProductos != null && codigosBarrasProductos.size() > 0) {
			for (CodigoBarrasProducto item : codigosBarrasProductos) {
				item.setProducto(producto);
				if (item.getCodigo() != null && !item.getCodigo().isEmpty()){
					item.setCodigo(item.getCodigo().toUpperCase());
					codigoBarrasProductoService.save(item);
				}
					
			}
			stockUtils.showSuccessmessage(
					"Nuevos codigos han sido guardados para el producto "
							+ producto.getNombre(),
					Clients.NOTIFICATION_TYPE_INFO, 0, null);
		} else
			stockUtils.showSuccessmessage("No se llevo a cabo ningun cambio",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void actualizarPrecioCosto() {
		
		if (producto != null && producto.getIdProducto() != null) {
			String mensaje = "";
			if (costosProductoNuevo.getReposicionUnitario() != null
					&& costosProductoNuevo.getReposicionActualizado() != null
					&& costosProductoNuevo.getMaximoUnitario() != null
					&& costosProductoNuevo.getMaximoActualizado() != null) {
				costosProductoNuevo.setProducto(producto);
				costosProductoService.save(costosProductoNuevo);
				mensaje += "costos";
			}

			if(!mensaje.equals(""))
				mensaje += " y precios han sido actualizados";
			else
				mensaje += " los precios han sido actualizados";
			productoService.save(producto);

			costosProducto = costosProductoService.getByProducto(producto);
			if (costosProducto == null)
				costosProducto = new CostosProducto();

			costosProductoNuevo = new CostosProducto();
			
			stockUtils.showSuccessmessage(
					mensaje,
					Clients.NOTIFICATION_TYPE_INFO, 0, null);

		} else
			stockUtils.showSuccessmessage(
					"Asegurese de haber cargado primero un producto",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void precioMasivoFamilia() {
		if(productoTipoDB == null || productoTipoDB.size() == 0)
			productoTipoDB = productoTipoService.getAll();
		hiddeFamilia = true;
		hiddeProveedor = false;
		
		System.err.println("familia");
	}
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void precioMasivoProveedor() {
		System.err.println("proveedor");
		hiddeFamilia = false;
		hiddeProveedor = true;
	}
}
