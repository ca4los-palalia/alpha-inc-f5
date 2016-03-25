package com.cplsystems.stock.app.vm.controlpanel;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.controlpanel.utils.ControlPanelVariables;
import com.cplsystems.stock.app.vm.controlpanel.utils.SelectedTabsControlPanel;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Button;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

@VariableResolver({ DelegatingVariableResolver.class })
public class ControlPanelVM extends ControlPanelVariables {
	private static final long serialVersionUID = -8141487067470696501L;
	private Media media;
	public Button clasificacionButton;
	public Button saveButton;

	@Init
	public void init() {
		selectTab = new SelectedTabsControlPanel();
		selectTab.setVisibleButtonSave(true);
		activarBotonesAreas();
		areas = areaService.getAll();
	}

	@Command
	@NotifyChange({ "almacenesList" })
	public void getALmacenesDeArea(){
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

	private void guardarArea() {
		for (Area areaRecord : areas) {
			try {
				areaRecord.setToolTipIndice("Seleccionar �rea");
				areaRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");
				if (areaRecord.isNuevoRegistro()) {
					areaRecord.setNuevoRegistro(false);
					areaRecord.setIdArea(null);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					areaRecord.setFechaActualizacion(date);
					areaRecord.setOrganizacion((Organizacion) sessionUtils.getFromSession("FIRMA"));

					areaRecord.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));

					areaService.save(areaRecord);
				} else if (!areaRecord.getNombre().equals("")) {
					areaService.update(areaRecord);
				}
			} catch (Exception e) {
			}
		}
		areas.clear();
		areas = areaService.getAll();

		StockUtils.showSuccessmessage("Catalogo de �reas actualizado", "info", Integer.valueOf(0), null);
	}

	private void guardarBanco() {
		for (Banco bancoRecord : bancosDB) {
			try {
				bancoRecord.setToolTipIndice("Seleccionar un banco");

				bancoRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");
				if ((bancoRecord.getNombre() != null) && (!bancoRecord.getNombre().isEmpty())) {
					bancoRecord.setNuevoRegistro(false);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					bancoRecord.setFechaActualizacion(date);
					bancoRecord.setOrganizacion((Organizacion) sessionUtils.getFromSession("FIRMA"));

					bancoRecord.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));

					bancoService.save(bancoRecord);
				}
			} catch (Exception e) {
			}
		}
		bancosDB.clear();
		bancosDB = bancoService.getAll();

		StockUtils.showSuccessmessage("Catalogo de bancos actualizados", "info", Integer.valueOf(0), null);
	}

	private void guardarConffya() {
	}

	private void guardarContratos() {
		for (Contrato contratoRecord : contratos) {
			try {
				if ((contratoRecord.getNombre() != null) && (!contratoRecord.getNombre().equals(""))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					contratoRecord.setFechaActualizacion(date);
					contratoRecord.setOrganizacion((Organizacion) sessionUtils.getFromSession("FIRMA"));

					contratoRecord.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));

					contratoService.save(contratoRecord);
				}
			} catch (Exception e) {
			}
		}
		contratos.clear();
		contratos = contratoService.getAll();
		StockUtils.showSuccessmessage("El catalogo de contratos ha sido actualizado", "info", Integer.valueOf(0), null);
	}

	private void guardarMoneda() {
		for (Moneda monedaRecord : monedasDB) {
			try {
				monedaRecord.setToolTipIndice("Seleccionar una moneda");

				monedaRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");
				if ((monedaRecord.getNombre() != null) && (!monedaRecord.getNombre().equals(""))) {
					monedaRecord.setNuevoRegistro(false);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					monedaRecord.setFechaActualizacion(date);
					monedaRecord.setOrganizacion((Organizacion) sessionUtils.getFromSession("FIRMA"));

					monedaRecord.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));

					monedaService.save(monedaRecord);
				}
			} catch (Exception e) {
			}
		}
		monedasDB.clear();
		monedasDB = monedaService.getAll();
		StockUtils.showSuccessmessage("El catalogo de divisas ha sido actualizado", "info", Integer.valueOf(0), null);
	}

	private void guardarProductos() {
	}

	private void guardarProveedores() {
	}

	private void guardarPuesto() {
		for (Posicion posicionRecord : posiciones) {
			try {
				posicionRecord.setToolTipIndice("Seleccionar puesto");
				posicionRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				posicionRecord.setFechaActualizacion(date);
				posicionRecord.setOrganizacion((Organizacion) sessionUtils.getFromSession("FIRMA"));

				posicionRecord.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));
				if (posicionRecord.isNuevoRegistro()) {
					posicionRecord.setNuevoRegistro(false);
					posicionRecord.setIdPosicion(null);
					posicionService.save(posicionRecord);
				} else if (!posicionRecord.getNombre().equals("")) {
					posicionService.update(posicionRecord);
				}
			} catch (Exception e) {
			}
		}
		posiciones.clear();
		posiciones = posicionService.getAll();

		StockUtils.showSuccessmessage("Catalogo de puestos actualizados", "info", Integer.valueOf(0), null);
	}

	private void guardarProductoTipo() {
		for (ProductoTipo productoTipoRecord : productoTipoDB) {
			try {
				productoTipoRecord.setToolTipIndice("Seleccionar un tipo de producto");

				productoTipoRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				productoTipoRecord.setFechaActualizacion(date);
				productoTipoRecord.setOrganizacion((Organizacion) sessionUtils.getFromSession("FIRMA"));

				productoTipoRecord.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));
				if (productoTipoRecord.isNuevoRegistro()) {
					productoTipoRecord.setNuevoRegistro(false);
					productoTipoRecord.setIdProductoTipo(null);
					productoTipoService.save(productoTipoRecord);
				} else if (!productoTipoRecord.getNombre().equals("")) {
					productoTipoService.update(productoTipoRecord);
				}
			} catch (Exception e) {
			}
		}
		productoTipoDB.clear();
		productoTipoDB = productoTipoService.getAll();

		StockUtils.showSuccessmessage("Catalogo de tipo de productos actualizado", "info", Integer.valueOf(0), null);
	}

	private void guardarUnidades() {
		if ((unidadesDB != null) && (unidadesDB.size() > 0)) {
			for (Unidad item : unidadesDB) {
				if ((item.getNombre() != null) && (!item.getNombre().isEmpty())) {
					item.setFechaActualizacion(Calendar.getInstance());
					item.setOrganizacion((Organizacion) sessionUtils.getFromSession("FIRMA"));

					item.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));

					unidadService.save(item);
				}
			}
			StockUtils.showSuccessmessage("Se han realizado cambios en el catalogo de -Unidades de medida-", "info",
					Integer.valueOf(0), null);

			mensajeDeCambios = "";
		} else {
			StockUtils.showSuccessmessage(
					"No se puede llevar a cabo una actualizacion en el catalogo -Unidades de medida-, catalogo vacio",
					"warning", Integer.valueOf(0), null);
		}
	}

	private void guardarGiros() {
		if ((giros != null) && (giros.size() > 0)) {
			for (Giro item : giros) {
				if ((item.getNombre() != null) && (!item.getNombre().isEmpty())) {
					Organizacion org = (Organizacion) sessionUtils.getFromSession("FIRMA");
					item.setOrganizacion(org);
					giroService.save(item);
				}
			}
			StockUtils.showSuccessmessage("Se han realizado cambios en el catalogo de -Giros-", "info",
					Integer.valueOf(0), null);

			mensajeDeCambios = "";
		} else {
			StockUtils.showSuccessmessage("No se puede llevar la actualizacion en el catalogo -Giros-, catalogo vacio",
					"warning", Integer.valueOf(0), null);
		}
	}

	private void eliminarArea() {
		if (area != null) {
			areaService.delete(area);

			areas.clear();
			areas = areaService.getAll();
			if ((areas != null) && (!((Area) areas.get(areas.size() - 1)).getNombre().equals(""))) {
				areas.add(crearColumnaVaciaArea());
			} else {
				Area nuevo = crearColumnaVaciaArea();
				areas.add(nuevo);
			}
			StockUtils.showSuccessmessage(area.getNombre() + " ha sido eliminado", "info", Integer.valueOf(0),
					null);
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un �rea para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
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
						"El Banco -" + area.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
						"error", Integer.valueOf(0), null);
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

				StockUtils.showSuccessmessage("El contrato -" + contrato.getNombre() + "- ha sido eliminado",
						"info", Integer.valueOf(0), null);
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

				StockUtils.showSuccessmessage(
						"La divisa -" + monedaSeleccionada.getNombre() + "- ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("La divisa -" + monedaSeleccionada.getSimbolo() + " "
						+ monedaSeleccionada.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
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
				StockUtils.showSuccessmessage(posicion.getNombre() + " ha sido eliminado", "info",
						Integer.valueOf(0), null);
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
			StockUtils.showSuccessmessage("La unidad de medida -" + unidad.getNombre()
					+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
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
			StockUtils.showSuccessmessage("El giro -" + giro.getNombre() + "- ha sido eliminado del catalogo",
					"info", Integer.valueOf(0), null);
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
			StockUtils.showSuccessmessage("La naturaleza producto -" + productoNaturaleza.getNombre()
					+ "- ha sido eliminado del catalogo", "info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("La naturaleza producto -" + productoNaturaleza.getNombre()
					+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
		}
	}

	private Area crearColumnaVaciaArea() {
		Area areaVacia = new Area();
		if (areas != null) {
			areaVacia.setIdArea(Long.valueOf(String.valueOf(areas.size() + 1)));
		} else {
			areas = new ArrayList();
			areaVacia.setIdArea(Long.valueOf(1L));
		}
		areaVacia.setNuevoRegistro(true);
		areaVacia.setNombre("");
		areaVacia.setToolTipIndice("Seleccionar �rea");
		areaVacia.setToolTipNombre("Clic sobre esta columna para editar nombre");

		return areaVacia;
	}

	private Posicion crearColumnaVaciaP() {
		Posicion objeto = new Posicion();
		if (posiciones != null) {
			objeto.setIdPosicion(Long.valueOf(String.valueOf(posiciones.size() + 1)));
		} else {
			posiciones = new ArrayList();
			objeto.setIdPosicion(Long.valueOf(1L));
		}
		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar una posicion");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	private Area crearColumnaVaciaAreaEstandar() {
		Area objeto = new Area();

		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar un �rea");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	private Banco crearColumnaVaciaBanco() {
		Banco objeto = new Banco();

		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar un banco");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	private Contrato crearColumnaVaciaContrato() {
		Contrato objeto = new Contrato();
		return objeto;
	}

	private Moneda crearColumnaVaciaMonedas() {
		Moneda objeto = new Moneda();
		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar un tipo de producto");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	private ProductoTipo crearColumnaVaciaTipoProducto() {
		ProductoTipo objeto = new ProductoTipo();
		if (productoTipoDB != null) {
			objeto.setIdProductoTipo(Long.valueOf(String.valueOf(productoTipoDB.size() + 1)));
		} else {
			productoTipoDB = new ArrayList();
			objeto.setIdProductoTipo(Long.valueOf(1L));
		}
		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar un tipo de producto");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	private void recargarAreas() {
		if (areas == null) {
			areas.add(crearColumnaVaciaArea());
		} else {
			try {
				if (!((Area) areas.get(areas.size() - 1)).getNombre().equals("")) {
					areas.add(crearColumnaVaciaArea());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void recargarPosiciones() {
		if ((posiciones == null) || (posiciones.size() == 0)) {
			try {
				posiciones = new ArrayList();
				posiciones.add(crearColumnaVaciaP());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (!((Posicion) posiciones.get(posiciones.size() - 1)).getNombre().equals("")) {
					posiciones.add(crearColumnaVaciaP());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void recargarBanco() {
		if ((bancosDB == null) || (bancosDB.size() == 0)) {
			try {
				bancosDB = new ArrayList();
				bancosDB.add(crearColumnaVaciaBanco());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (!((Banco) bancosDB.get(bancosDB.size() - 1)).getNombre().equals("")) {
					bancosDB.add(crearColumnaVaciaBanco());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void recargarMonedas() {
		if ((monedasDB == null) || (monedasDB.size() == 0)) {
			try {
				monedasDB = new ArrayList();
				monedasDB.add(crearColumnaVaciaMonedas());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (!((Moneda) monedasDB.get(monedasDB.size() - 1)).getNombre().equals("")) {
					monedasDB.add(crearColumnaVaciaMonedas());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void recargarProductoTipo() {
		if ((productoTipoDB == null) || (productoTipoDB.size() == 0)) {
			try {
				productoTipoDB = new ArrayList();
				productoTipoDB.add(crearColumnaVaciaTipoProducto());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (!((ProductoTipo) productoTipoDB.get(productoTipoDB.size() - 1)).getNombre().equals("")) {
					productoTipoDB.add(crearColumnaVaciaTipoProducto());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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

	private void activarBotonesAreas() {
		selectTab.setTabAreas(true);
		selectTab.setTabBancos(false);
		selectTab.setTabConffya(false);
		selectTab.setTabContratos(false);
		selectTab.setTabDivisas(false);
		selectTab.setTabProductos(false);
		selectTab.setTabProveedores(false);
		selectTab.setTabPuestos(false);
		selectTab.setTabTipoProductos(false);
		selectTab.setTabUnidades(false);
		selectTab.setActivarButtonDelete(false);
		selectTab.setActivarButtonSave(false);
		selectTab.setVisibleButtonSave(true);
		selectTab.setToolTipSave("Actualizar/Guardar �rea");
		selectTab.setToolTipDelete("Eliminar �rea");
	}

	private void activarBotonesBancos() {
		selectTab.setTabAreas(false);
		selectTab.setTabBancos(true);
		selectTab.setTabConffya(false);
		selectTab.setTabContratos(false);
		selectTab.setTabDivisas(false);
		selectTab.setTabProductos(false);
		selectTab.setTabProveedores(false);
		selectTab.setTabPuestos(false);
		selectTab.setTabTipoProductos(false);
		selectTab.setTabUnidades(false);
		selectTab.setActivarButtonDelete(false);
		selectTab.setActivarButtonSave(false);
		selectTab.setVisibleButtonSave(true);
		selectTab.setToolTipSave("Actualizar/Guardar banco");
		selectTab.setToolTipDelete("Eliminar banco");
	}

	private void activarBotonesConffya() {
		selectTab.setTabAreas(false);
		selectTab.setTabBancos(false);
		selectTab.setTabConffya(true);
		selectTab.setTabContratos(false);
		selectTab.setTabDivisas(false);
		selectTab.setTabProductos(false);
		selectTab.setTabProveedores(false);
		selectTab.setTabPuestos(false);
		selectTab.setTabTipoProductos(false);
		selectTab.setTabUnidades(false);
		selectTab.setActivarButtonDelete(true);
		selectTab.setActivarButtonSave(true);
		selectTab.setVisibleButtonSave(false);
		selectTab.setToolTipSave("Actualizar/Guardar banco");
		selectTab.setToolTipDelete("Eliminar banco");
	}

	private void activarBotonesContrato() {
		selectTab.setTabAreas(false);
		selectTab.setTabBancos(false);
		selectTab.setTabConffya(false);
		selectTab.setTabContratos(true);
		selectTab.setTabDivisas(false);
		selectTab.setTabProductos(false);
		selectTab.setTabProveedores(false);
		selectTab.setTabPuestos(false);
		selectTab.setTabTipoProductos(false);
		selectTab.setTabUnidades(false);
		selectTab.setActivarButtonDelete(false);
		selectTab.setActivarButtonSave(false);
		selectTab.setVisibleButtonSave(true);
		selectTab.setToolTipSave("Actualizar/Guardar contrato");
		selectTab.setToolTipDelete("Eliminar contrato");
	}

	private void activarBotonesMonedas() {
		selectTab.setTabAreas(false);
		selectTab.setTabBancos(false);
		selectTab.setTabConffya(false);
		selectTab.setTabContratos(false);
		selectTab.setTabDivisas(true);
		selectTab.setTabProductos(false);
		selectTab.setTabProveedores(false);
		selectTab.setTabPuestos(false);
		selectTab.setTabTipoProductos(false);
		selectTab.setTabUnidades(false);
		selectTab.setActivarButtonDelete(false);
		selectTab.setActivarButtonSave(false);
		selectTab.setVisibleButtonSave(true);
		selectTab.setToolTipSave("Actualizar/Guardar divisa");
		selectTab.setToolTipDelete("Eliminar divisa");
	}

	private void activarBotonesProductos() {
		selectTab.setTabAreas(false);
		selectTab.setTabBancos(false);
		selectTab.setTabConffya(false);
		selectTab.setTabContratos(false);
		selectTab.setTabDivisas(false);
		selectTab.setTabProductos(true);
		selectTab.setTabProveedores(false);
		selectTab.setTabPuestos(false);
		selectTab.setTabTipoProductos(false);
		selectTab.setTabUnidades(false);
		selectTab.setActivarButtonDelete(false);
		selectTab.setActivarButtonSave(false);
		selectTab.setVisibleButtonSave(false);
	}

	private void activarBotonesProveedores() {
		selectTab.setTabAreas(false);
		selectTab.setTabBancos(false);
		selectTab.setTabConffya(false);
		selectTab.setTabContratos(false);
		selectTab.setTabDivisas(false);
		selectTab.setTabProductos(false);
		selectTab.setTabProveedores(true);
		selectTab.setTabPuestos(false);
		selectTab.setTabTipoProductos(false);
		selectTab.setTabUnidades(false);
		selectTab.setActivarButtonDelete(false);
		selectTab.setActivarButtonSave(false);
		selectTab.setVisibleButtonSave(false);
	}

	private void activarBotonesPuestos() {
		selectTab.setTabAreas(false);
		selectTab.setTabBancos(false);
		selectTab.setTabConffya(false);
		selectTab.setTabContratos(false);
		selectTab.setTabDivisas(false);
		selectTab.setTabProductos(false);
		selectTab.setTabProveedores(false);
		selectTab.setTabPuestos(true);
		selectTab.setTabTipoProductos(false);
		selectTab.setTabUnidades(false);
		selectTab.setActivarButtonDelete(false);
		selectTab.setActivarButtonSave(false);
		selectTab.setVisibleButtonSave(true);
		selectTab.setToolTipSave("Actualizar/Guardar puesto");
		selectTab.setToolTipDelete("Eliminar puesto");
	}

	private void activarBotonesTiposProductos() {
		selectTab.setTabAreas(false);
		selectTab.setTabBancos(false);
		selectTab.setTabConffya(false);
		selectTab.setTabContratos(false);
		selectTab.setTabDivisas(false);
		selectTab.setTabProductos(false);
		selectTab.setTabProveedores(false);
		selectTab.setTabPuestos(false);
		selectTab.setTabTipoProductos(true);
		selectTab.setTabUnidades(false);
		selectTab.setActivarButtonDelete(false);
		selectTab.setActivarButtonSave(false);
		selectTab.setVisibleButtonSave(true);
		selectTab.setToolTipSave("Actualizar/Guardar tipo de productos");
		selectTab.setToolTipDelete("Eliminar productos");
	}

	private void activarBotonesUnidades() {
		selectTab.setTabAreas(false);
		selectTab.setTabBancos(false);
		selectTab.setTabConffya(false);
		selectTab.setTabContratos(false);
		selectTab.setTabDivisas(false);
		selectTab.setTabProductos(false);
		selectTab.setTabProveedores(false);
		selectTab.setTabPuestos(false);
		selectTab.setTabTipoProductos(false);
		selectTab.setTabUnidades(true);
		selectTab.setActivarButtonDelete(true);
		selectTab.setActivarButtonSave(false);
		selectTab.setVisibleButtonSave(true);
		selectTab.setToolTipSave("Actualizar/Guardar unidad de medida");
		selectTab.setToolTipDelete("Eliminar productos");
	}

	private void activarBotonesGiros() {
		selectTab.setTabAreas(false);
		selectTab.setTabBancos(false);
		selectTab.setTabConffya(false);
		selectTab.setTabContratos(false);
		selectTab.setTabDivisas(false);
		selectTab.setTabProductos(false);
		selectTab.setTabProveedores(false);
		selectTab.setTabPuestos(false);
		selectTab.setTabTipoProductos(false);
		selectTab.setTabUnidades(false);
		selectTab.setTabGiros(true);
		selectTab.setActivarButtonDelete(true);
		selectTab.setActivarButtonSave(false);
		selectTab.setVisibleButtonSave(true);
		selectTab.setToolTipSave("Actualizar/Guardar giro");
		selectTab.setToolTipDelete("Eliminar giro");
	}

	private void activarBotonesNaturaleza() {
		selectTab.setTabAreas(false);
		selectTab.setTabBancos(false);
		selectTab.setTabConffya(false);
		selectTab.setTabContratos(false);
		selectTab.setTabDivisas(false);
		selectTab.setTabProductos(false);
		selectTab.setTabProveedores(false);
		selectTab.setTabPuestos(false);
		selectTab.setTabTipoProductos(false);
		selectTab.setTabUnidades(false);
		selectTab.setTabGiros(false);
		selectTab.setTabNaturaleza(true);
		selectTab.setActivarButtonDelete(true);
		selectTab.setActivarButtonSave(false);
		selectTab.setVisibleButtonSave(true);
		selectTab.setToolTipSave("Actualizar/Guardar giro");
		selectTab.setToolTipDelete("Eliminar giro");
	}

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
	
	//---------------------------------------------------------------------------------------------
	
	private Area crearArea(Area area, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				area.setNombre(valor);
		}
		return area;
	}
	
	private Banco crearBanco(Banco banco, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				banco.setNombre(valor);
		}
		return banco;
	}
	
	private Moneda crearMoneda(Moneda moneda, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				moneda.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				moneda.setSimbolo(valor);
		}
		return moneda;
	}
	
	private Posicion crearPosicion(Posicion puesto, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				puesto.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				puesto.setDescripcion(valor);
		}
		return puesto;
	}

	private ProductoTipo crearTipoProductos(ProductoTipo productoTipo, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				productoTipo.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				productoTipo.setDescripcion(valor);
		}
		return productoTipo;
	}
	
	private Unidad crearUnidadMedida(Unidad unidad, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
			case 0:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
					unidad.setNombre(valor);
				break;
			case 1:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
					unidad.setAbreviatura(valor);
		}
		return unidad;
	}
	
	private Giro crearGiro(Giro giro, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		
			case 0:
				break;
			case 1:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
					giro.setNombre(valor);
				break;
			case 2:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
					giro.setDescripcion(valor);
		}
		return giro;
	}
	
	private ProductoNaturaleza crearNaturaleza(ProductoNaturaleza productoNaturaleza, XSSFCell valorDePropiedad,
			int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
			case 0:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
					productoNaturaleza.setNombre(valor);
				break;
			case 1:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
					productoNaturaleza.setSimbolo(valor);
				
				break;
		}
		return productoNaturaleza;
	}
	
	//---------------------------------------------------------------------------------------------
	
	@NotifyChange({ "areas" })
	@Command
	public void onUploadExcelArea(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		areas = leerDatosDesdeExcelArea(getStreamMediaExcel(ctx), 0);
		if(areas.size() > 0){
			for (Area item : areas) {
				areaService.save(item);
			}
			Messagebox.show(areas.size() + " Areas Importadas");
		}else
			Messagebox.show("No se importaron areas. El documento esta vacio");
	}

	@Command
	@NotifyChange({ "bancosDB" })
	public void onUploadExcelBanco(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		bancosDB = leerDatosDesdeExcelBanco(getStreamMediaExcel(ctx), 0);
		if(bancosDB.size() > 0){
			for (Banco item : bancosDB) {
				bancoService.save(item);
			}
			Messagebox.show(bancosDB.size() + " Bancos Importados");
		}else
			Messagebox.show("No se importaron bancos. El documento esta vacio");	
	}
	
	@Command
	@NotifyChange({ "monedasDB" })
	public void onUploadExcelMoneda(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		monedasDB = leerDatosDesdeExcelMoneda(getStreamMediaExcel(ctx), 0);
		if(monedasDB.size() > 0){
			for (Moneda item : monedasDB) {
				monedaService.save(item);
			}
			Messagebox.show(monedasDB.size() + " Divisas Importadas");
		}else
			Messagebox.show("No se importaron divisas. El documento esta vacio");
	}
	
	@Command
	@NotifyChange({ "posiciones" })
	public void onUploadExcelPosicion(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		posiciones = leerDatosDesdeExcelPosicion(getStreamMediaExcel(ctx), 0);
		if(posiciones.size() > 0){
			for (Posicion item : posiciones) {
				posicionService.save(item);
			}
			Messagebox.show(posiciones.size() + " Puestos Importados");
		}else
			Messagebox.show("No se importaron posiciones. El documento esta vacio");
	}
	
	@Command
	@NotifyChange({ "productoTipoDB" })
	public void onUploadExcelTipoProducto(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		productoTipoDB = leerDatosDesdeExcelTipoProductos(getStreamMediaExcel(ctx), 0);
		if(productoTipoDB.size() > 0){
			for (ProductoTipo item : productoTipoDB) {
				productoTipoService.save(item);
			}
			Messagebox.show(productoTipoDB.size() + " Tipos de producto Importados");
		}else
			Messagebox.show("No se importaron Tipos de producto. El documento esta vacio");
	}
	
	@Command
	@NotifyChange({ "unidadesDB" })
	public void onUploadExcelUnidadMedida(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		unidadesDB = leerDatosDesdeExcelUnidadMedida(getStreamMediaExcel(ctx), 0);
		if(unidadesDB.size() > 0){
			for (Unidad item : unidadesDB) {
				unidadService.save(item);
			}
			Messagebox.show(unidadesDB.size() + " Unidades de medida Importados");
		}else
			Messagebox.show("No se importaron Unidades de medida. El documento esta vacio");
	}
	
	@Command
	@NotifyChange({ "giros" })
	public void onUploadExcelGiro(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		giros = leerDatosDesdeExcelGiros(getStreamMediaExcel(ctx), 0);
		if(giros.size() > 0){
			for (Giro item : giros) {
				giroService.save(item);
			}
			Messagebox.show(giros.size() + " Giros Importados");
		}else
			Messagebox.show("No se importaron Giros. El documento esta vacio");
	}
	
	@Command
	@NotifyChange({ "productosNaturalezas" })
	public void onUploadExcelNaturaleza(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		productosNaturalezas = leerDatosDesdeExcelNaturaleza(getStreamMediaExcel(ctx), 0);
		if(productosNaturalezas.size() > 0){
			for (ProductoNaturaleza item : productosNaturalezas) {
				productoNaturalezaService.save(item);
			}
			Messagebox.show(productosNaturalezas.size() + " Naturalezas de producto Importados");
		}else
			Messagebox.show("No se importaron Naturalezas de producto. El documento esta vacio");
	}

	//---------------------------------------------------------------------------------------------
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Area> leerDatosDesdeExcelArea(InputStream inPutStream, int indiceSheet) {
		Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		List<Area> areaNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = 0;
			int j;
			SimpleDateFormat sdf;
			while (rowIterator.hasNext()) {
				Area nuevoArea = new Area();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 1)) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						nuevoArea = crearArea(nuevoArea, hssfCell, j);
						j++;
					}
					nuevoArea.setOrganizacion(organizacion);
					nuevoArea.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));

					sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					nuevoArea.setFechaActualizacion(date);
					nuevoArea.setToolTipIndice("Seleccionar Area");
					nuevoArea.setToolTipNombre("Clic sobre esta columna para editar nombre");
					areaNuevosExcel.add(nuevoArea);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return areaNuevosExcel;
	}
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Banco> leerDatosDesdeExcelBanco(InputStream inPutStream, int indiceSheet) {
		Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		List<Banco> bancoNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			SimpleDateFormat sdf;
			while (rowIterator.hasNext()) {
				Banco nuevoBanco = new Banco();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 1)) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						nuevoBanco = crearBanco(nuevoBanco, hssfCell, j);
						j++;
					}
					nuevoBanco.setOrganizacion(organizacion);
					nuevoBanco.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));

					sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					nuevoBanco.setFechaActualizacion(date);
					nuevoBanco.setToolTipIndice("Seleccionar moneda");
					nuevoBanco.setToolTipNombre("Clic sobre esta columna para editar nombre");
					bancoNuevosExcel.add(nuevoBanco);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bancoNuevosExcel;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Moneda> leerDatosDesdeExcelMoneda(InputStream inPutStream, int indiceSheet) {
		Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		List<Moneda> monedaNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			SimpleDateFormat sdf;
			while (rowIterator.hasNext()) {
				Moneda nuevoMoneda = new Moneda();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 2)) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						nuevoMoneda = crearMoneda(nuevoMoneda, hssfCell, j);

						j++;
					}
					nuevoMoneda.setOrganizacion(organizacion);
					nuevoMoneda.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));

					sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					nuevoMoneda.setFechaActualizacion(date);
					nuevoMoneda.setToolTipIndice("Seleccionar moneda");
					nuevoMoneda.setToolTipNombre("Clic sobre esta columna para editar nombre");
					monedaNuevosExcel.add(nuevoMoneda);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monedaNuevosExcel;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Posicion> leerDatosDesdeExcelPosicion(InputStream inPutStream, int indiceSheet) {
		Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		List<Posicion> posicionNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			SimpleDateFormat sdf;
			while (rowIterator.hasNext()) {
				Posicion nuevoPosicion = new Posicion();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 2)) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						nuevoPosicion = crearPosicion(nuevoPosicion, hssfCell, j);

						j++;
					}
					nuevoPosicion.setOrganizacion(organizacion);
					nuevoPosicion.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));

					sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					nuevoPosicion.setFechaActualizacion(date);
					nuevoPosicion.setToolTipIndice("Seleccionar puesto");
					nuevoPosicion.setToolTipNombre("Clic sobre esta columna para editar nombre");
					posicionNuevosExcel.add(nuevoPosicion);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return posicionNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<ProductoTipo> leerDatosDesdeExcelTipoProductos(InputStream inPutStream, int indiceSheet) {
		Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		List<ProductoTipo> productoTipoNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			SimpleDateFormat sdf;
			while (rowIterator.hasNext()) {
				ProductoTipo nuevoProductoTipo = new ProductoTipo();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 2)) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						nuevoProductoTipo = crearTipoProductos(nuevoProductoTipo, hssfCell, j);
						j++;
					}
					nuevoProductoTipo.setOrganizacion(organizacion);
					nuevoProductoTipo.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));

					sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					nuevoProductoTipo.setFechaActualizacion(date);
					productoTipoNuevosExcel.add(nuevoProductoTipo);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productoTipoNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Unidad> leerDatosDesdeExcelUnidadMedida(InputStream inPutStream, int indiceSheet) {
		Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		List<Unidad> unidadNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			XSSFCell hssfCell;
			while (rowIterator.hasNext()) {
				Unidad nuevoUnidad = new Unidad();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 2)) {
						hssfCell = (XSSFCell) iterator.next();
						nuevoUnidad = crearUnidadMedida(nuevoUnidad, hssfCell, j);
						j++;
					}
					nuevoUnidad.setOrganizacion(organizacion);
					nuevoUnidad.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));
					nuevoUnidad.setFechaActualizacion(Calendar.getInstance());
					unidadNuevosExcel.add(nuevoUnidad);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unidadNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Giro> leerDatosDesdeExcelGiros(InputStream inPutStream, int indiceSheet) {
		Organizacion organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		List<Giro> giroNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			XSSFCell hssfCell;
			while (rowIterator.hasNext()) {
				Giro nuevoGiro = new Giro();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 3)) {
						hssfCell = (XSSFCell) iterator.next();
						nuevoGiro = crearGiro(nuevoGiro, hssfCell, j);
						j++;
					}
					nuevoGiro.setOrganizacion(organizacion);
					giroNuevosExcel.add(nuevoGiro);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return giroNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<ProductoNaturaleza> leerDatosDesdeExcelNaturaleza(InputStream inPutStream, int indiceSheet) {
		List<ProductoNaturaleza> productoNaturalezaNuevosExcel = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(inPutStream);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			Integer i = Integer.valueOf(0);
			int j;
			XSSFCell hssfCell;
			while (rowIterator.hasNext()) {
				ProductoNaturaleza nuevoProductoNaturaleza = new ProductoNaturaleza();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i.intValue() > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 3)) {
						hssfCell = (XSSFCell) iterator.next();
						nuevoProductoNaturaleza = crearNaturaleza(nuevoProductoNaturaleza, hssfCell, j);
						j++;
					}
					productoNaturalezaNuevosExcel.add(nuevoProductoNaturaleza);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productoNaturalezaNuevosExcel;
	}

	//---------------------------------------------------------------------------------------------
	
	
}
