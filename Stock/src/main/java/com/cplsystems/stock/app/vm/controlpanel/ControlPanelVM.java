package com.cplsystems.stock.app.vm.controlpanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.controlpanel.utils.ControlPanelMetaclass;
import com.cplsystems.stock.app.vm.controlpanel.utils.SelectedTabsControlPanel;
import com.cplsystems.stock.domain.Almacen;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Banco;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.Giro;
import com.cplsystems.stock.domain.Moneda;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Posicion;
import com.cplsystems.stock.domain.ProductoNaturaleza;
import com.cplsystems.stock.domain.ProductoTipo;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class ControlPanelVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;
	public Button clasificacionButton;
	public Button saveButton;

	@Init
	public void init() {
		selectTab = new SelectedTabsControlPanel();
		selectTab.setVisibleButtonSave(true);
		activarBotonesAreas();
		areas = areaService.getAll();
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
	}

	@Command
	@NotifyChange({ "almacenesList" })
	public void getALmacenesDeArea() {
		almacenesList = almacenService.getByArea(area);
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		if (selectTab.isTabAreas()) {
			guardarArea();
		} else if (selectTab.isTabBancos()) {
			guardarBanco();
		} else if (selectTab.isTabConffya()) {
			guardarConffya();
		} else if (selectTab.isTabContratos()) {
			guardarContratos();
		} else if (selectTab.isTabDivisas()) {
			guardarMoneda();
		} else if (selectTab.isTabProductos()) {
			guardarProductos();
		} else if (selectTab.isTabProveedores()) {
			guardarProveedores();
		} else if (selectTab.isTabPuestos()) {
			guardarPuesto();
		} else if (selectTab.isTabTipoProductos()) {
			guardarProductoTipo();
		} else if (selectTab.isTabUnidades()) {
			guardarUnidades();
		} else if (selectTab.isTabGiros()) {
			guardarGiros();
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void delete() {
		
	}

	

	

	@Command
	@NotifyChange({ "*" })
	public void eliminarAreaIndex(@BindingParam("index") Integer index) {
		area = ((Area) areas.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (areas != null) {
			try {
				areaService.delete(area);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				areas.clear();
				areas = areaService.getAll();

				StockUtils.showSuccessmessage("El �rea -" + area.getNombre() + "- ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"El Banco -" + area.getNombre() + "- esta siendo utilizado. No puede ser eliminado", "error",
						Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un �rea para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void eliminarBanco(@BindingParam("index") Integer index) {
		bancoSeleccionado = ((Banco) bancosDB.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (bancosDB != null) {
			try {
				bancoService.delete(bancoSeleccionado);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				bancosDB.clear();
				bancosDB = bancoService.getAll();

				StockUtils.showSuccessmessage("El Banco -" + bancoSeleccionado.getNombre() + "- ha sido eliminado",
						"info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("El Banco -" + bancoSeleccionado.getNombre()
						+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un banco para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void eliminarContrato(@BindingParam("index") Integer index) {
		contrato = ((Contrato) contratos.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (contratos != null) {
			try {
				contratoService.delete(contrato);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				contratos.clear();
				contratos = contratoService.getAll();

				StockUtils.showSuccessmessage("El contrato -" + contrato.getNombre() + "- ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"El Contrato -" + contrato.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
						"error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un contrato para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void eliminarMoneda(@BindingParam("index") Integer index) {
		monedaSeleccionada = ((Moneda) monedasDB.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (monedasDB != null) {
			try {
				monedaService.delete(monedaSeleccionada);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				monedasDB.clear();
				monedasDB = monedaService.getAll();

				StockUtils.showSuccessmessage("La divisa -" + monedaSeleccionada.getNombre() + "- ha sido eliminado",
						"info", Integer.valueOf(0), null);
			} else {
				StockUtils
						.showSuccessmessage(
								"La divisa -" + monedaSeleccionada.getSimbolo() + " " + monedaSeleccionada.getNombre()
										+ "- esta siendo utilizado. No puede ser eliminado",
								"error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar una divisa para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void eliminarPuesto(@BindingParam("index") Integer index) {
		posicion = ((Posicion) posiciones.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (posicion != null) {
			try {
				posicionService.delete(posicion);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				posiciones.clear();
				posiciones = posicionService.getAll();
				StockUtils.showSuccessmessage(posicion.getNombre() + " ha sido eliminado", "info", Integer.valueOf(0),
						null);
			} else {
				StockUtils.showSuccessmessage(
						"El puesto -" + posicion.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
						"error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un puesto para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "productoTipoDB, mensajeDeCambios" })
	public void eliminarTipoProducto(@BindingParam("index") Integer index) {
		productoTipoSelected = ((ProductoTipo) productoTipoDB.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (productoTipoDB != null) {
			try {
				productoTipoService.delete(productoTipoSelected);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				productoTipoDB.clear();
				productoTipoDB = productoTipoService.getAll();

				StockUtils.showSuccessmessage(productoTipoSelected.getNombre() + " ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("La familia -" + productoTipoSelected.getNombre()
						+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un tipo de producto para proceder con la eliminaci�n",
					"warning", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void removerUnidad(@BindingParam("index") Integer index) {
		boolean continuarEliminacion = true;
		unidad = ((Unidad) unidadesDB.get(index.intValue()));
		try {
			unidadService.delete(unidad);
		} catch (Exception e) {
			continuarEliminacion = false;
		}
		if (continuarEliminacion) {
			unidadesDB.remove(unidad);
			StockUtils.showSuccessmessage("La unidad de medida ha sido eliminada", "info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage(
					"La unidad de medida -" + unidad.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
					"error", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void removerGiro(@BindingParam("index") Integer index) {
		boolean continuarEliminacion = true;
		giro = ((Giro) giros.get(index.intValue()));
		try {
			giroService.delete(giro);
		} catch (Exception e) {
			continuarEliminacion = false;
		}
		if (continuarEliminacion) {
			giros.remove(giro);
			giros = new ArrayList();
			giros = giroService.getAll();
			StockUtils.showSuccessmessage("El giro -" + giro.getNombre() + "- ha sido eliminado del catalogo", "info",
					Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage(
					"El giro -" + giro.getNombre() + "- esta siendo utilizado. No puede ser eliminado", "error",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void removerNaturaleza(@BindingParam("index") Integer index) {
		boolean continuarEliminacion = true;
		productoNaturaleza = ((ProductoNaturaleza) productosNaturalezas.get(index.intValue()));
		try {
			productoNaturalezaService.delete(productoNaturaleza);
		} catch (Exception e) {
			continuarEliminacion = false;
		}
		if (continuarEliminacion) {
			productosNaturalezas.remove(productoNaturaleza);
			productosNaturalezas = new ArrayList();
			productosNaturalezas = productoNaturalezaService.getAll();
			StockUtils.showSuccessmessage(
					"La naturaleza producto -" + productoNaturaleza.getNombre() + "- ha sido eliminado del catalogo",
					"info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("La naturaleza producto -" + productoNaturaleza.getNombre()
					+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
		}
	}

	

	

	@Command
	@NotifyChange({ "*" })
	public void selectTabArea() {
		activarBotonesAreas();
		areas = areaService.getAll();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabBanco() {
		activarBotonesBancos();
		bancosDB = bancoService.getAll();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabConffya() {
		activarBotonesConffya();

	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabContratos() {
		activarBotonesContrato();
		contratos = contratoService.getAll();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabMoneda() {
		activarBotonesMonedas();
		monedasDB = monedaService.getAll();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabProducto() {
		activarBotonesProductos();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabProveedores() {
		activarBotonesProveedores();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabPuesto() {
		posiciones = posicionService.getAll();
		activarBotonesPuestos();
	}

	@Command
	@NotifyChange({ "productoTipoDB" })
	public void selectTabTiposProducto() {
		productoTipoDB = productoTipoService.getAll();
		activarBotonesTiposProductos();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabUnidades() {
		activarBotonesUnidades();

		unidadesDB = unidadService.getAll();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabGiro() {
		activarBotonesGiros();
		giros = giroService.getAll();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabNaturaleza() {
		activarBotonesNaturaleza();
		productosNaturalezas = productoNaturalezaService.getAll();
	}

	// ***************************************************
		

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevaArea() {
		if (areas == null) {
			areas = new ArrayList();
		}
		areas.add(crearColumnaVaciaAreaEstandar());
		mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoBanco() {
		if (bancosDB == null) {
			bancosDB = new ArrayList();
		}
		bancosDB.add(crearColumnaVaciaBanco());
		mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoContrato() {
		if (contratos == null) {
			contratos = new ArrayList();
		}
		contratos.add(crearColumnaVaciaContrato());
		mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevaDivisa() {
		if (monedasDB == null) {
			monedasDB = new ArrayList();
		}
		monedasDB.add(crearColumnaVaciaMonedas());
		mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoPuesto() {
		if (posiciones == null) {
			posiciones = new ArrayList();
		}
		posiciones.add(crearColumnaVaciaP());
		mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoTipoProducto() {
		if (productoTipoDB == null) {
			productoTipoDB = new ArrayList();
		}
		productoTipoDB.add(crearColumnaVaciaTipoProducto());
		mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevaUnidad() {
		if (unidadesDB == null) {
			unidadesDB = new ArrayList();
		}
		Unidad nuevaUnidad = new Unidad();
		unidadesDB.add(nuevaUnidad);
		mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoGiro() {
		if (giros == null) {
			giros = new ArrayList();
		}
		Giro nueoGiro = new Giro();
		giros.add(nueoGiro);
		mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevaNaturaleza() {
		if (productosNaturalezas == null) {
			productosNaturalezas = new ArrayList();
		}
		ProductoNaturaleza nueoGiro = new ProductoNaturaleza();
		productosNaturalezas.add(nueoGiro);
		mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	public void openLayoutAreas() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutArea.xlsx"), false)), null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutBancos() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutBanco.xlsx"), false)), null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutGiros() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutGiro.xlsx"), false)), null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutMonedas() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutMoneda.xlsx"), false)), null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutPosiciones() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(
					new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutPosicion(Puestos).xlsx"), false)), null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutProducto() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutProductos.xlsx"), false)),
					null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutProductoTipo() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutProductoTipo.xlsx"), false)),
					null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutProveedores() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutProveedores.xlsx"), false)),
					null);
		} catch (FileNotFoundException e) {
		}
	}

	@Command
	public void openLayoutUnidades() {
		Execution exec = Executions.getCurrent();
		try {
			Filedownload.save(new File(exec.toAbsoluteURI(generarUrlString("layout/LayoutUnidad.xlsx"), false)), null);
		} catch (FileNotFoundException e) {
		}
	}

	// ---------------------------------------------------------------------------------------------

	

	// ---------------------------------------------------------------------------------------------

	@NotifyChange({ "areas", "almacenesList" })
	@Command
	public void onUploadExcelArea(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		areas = leerDatosDesdeExcelArea(getDataExcel(getStreamMediaExcel(ctx), 0));
		if (areas.size() > 0) {
			for (Area item : areas) {
				areaService.save(item);
			}
			almacenesList = leerDatosDesdeExcelAlmacenes(getDataExcel(getStreamMediaExcel(ctx), 1));
			if (almacenesList.size() > 0) {
				for (Almacen item : almacenesList) {
					item.setOrganizacion(organizacion);
					almacenService.save(item);
				}
			}
			Messagebox.show(areas.size() + " Areas Importadas con sus respectivos almacenes");
		} else
			Messagebox.show("No se importaron areas. El documento esta vacio");
	}

	@Command
	@NotifyChange({ "bancosDB" })
	public void onUploadExcelBanco(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		bancosDB = leerDatosDesdeExcelBanco(getStreamMediaExcel(ctx), 0);
		if (bancosDB.size() > 0) {
			for (Banco item : bancosDB) {
				bancoService.save(item);
			}
			Messagebox.show(bancosDB.size() + " Bancos Importados");
		} else
			Messagebox.show("No se importaron bancos. El documento esta vacio");
	}

	@Command
	@NotifyChange({ "monedasDB" })
	public void onUploadExcelMoneda(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		monedasDB = leerDatosDesdeExcelMoneda(getStreamMediaExcel(ctx), 0);
		if (monedasDB.size() > 0) {
			for (Moneda item : monedasDB) {
				monedaService.save(item);
			}
			Messagebox.show(monedasDB.size() + " Divisas Importadas");
		} else
			Messagebox.show("No se importaron divisas. El documento esta vacio");
	}

	@Command
	@NotifyChange({ "posiciones" })
	public void onUploadExcelPosicion(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		posiciones = leerDatosDesdeExcelPosicion(getStreamMediaExcel(ctx), 0);
		if (posiciones.size() > 0) {
			for (Posicion item : posiciones) {
				posicionService.save(item);
			}
			Messagebox.show(posiciones.size() + " Puestos Importados");
		} else
			Messagebox.show("No se importaron posiciones. El documento esta vacio");
	}

	@Command
	@NotifyChange({ "productoTipoDB" })
	public void onUploadExcelTipoProducto(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		productoTipoDB = leerDatosDesdeExcelTipoProductos(getStreamMediaExcel(ctx), 0);
		if (productoTipoDB.size() > 0) {
			for (ProductoTipo item : productoTipoDB) {
				productoTipoService.save(item);
			}
			Messagebox.show(productoTipoDB.size() + " Tipos de producto Importados");
		} else
			Messagebox.show("No se importaron Tipos de producto. El documento esta vacio");
	}

	@Command
	@NotifyChange({ "unidadesDB" })
	public void onUploadExcelUnidadMedida(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		unidadesDB = leerDatosDesdeExcelUnidadMedida(getStreamMediaExcel(ctx), 0);
		if (unidadesDB.size() > 0) {
			for (Unidad item : unidadesDB) {
				unidadService.save(item);
			}
			Messagebox.show(unidadesDB.size() + " Unidades de medida Importados");
		} else
			Messagebox.show("No se importaron Unidades de medida. El documento esta vacio");
	}

	@Command
	@NotifyChange({ "giros" })
	public void onUploadExcelGiro(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		giros = leerDatosDesdeExcelGiros(getStreamMediaExcel(ctx), 0);
		if (giros.size() > 0) {
			for (Giro item : giros) {
				giroService.save(item);
			}
			Messagebox.show(giros.size() + " Giros Importados");
		} else
			Messagebox.show("No se importaron Giros. El documento esta vacio");
	}

	@Command
	@NotifyChange({ "productosNaturalezas" })
	public void onUploadExcelNaturaleza(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		productosNaturalezas = leerDatosDesdeExcelNaturaleza(getStreamMediaExcel(ctx), 0);
		if (productosNaturalezas.size() > 0) {
			for (ProductoNaturaleza item : productosNaturalezas) {
				productoNaturalezaService.save(item);
			}
			Messagebox.show(productosNaturalezas.size() + " Naturalezas de producto Importados");
		} else
			Messagebox.show("No se importaron Naturalezas de producto. El documento esta vacio");
	}

	
	

	// ---------------------------------------------------------------------------------------------

}
