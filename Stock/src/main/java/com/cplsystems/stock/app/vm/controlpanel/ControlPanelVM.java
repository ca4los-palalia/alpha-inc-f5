package com.cplsystems.stock.app.vm.controlpanel;

import com.cplsystems.stock.app.utils.SessionUtils;
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
import com.cplsystems.stock.services.AreaService;
import com.cplsystems.stock.services.BancoService;
import com.cplsystems.stock.services.ContratoService;
import com.cplsystems.stock.services.GiroService;
import com.cplsystems.stock.services.MonedaService;
import com.cplsystems.stock.services.PosicionService;
import com.cplsystems.stock.services.ProductoNaturalezaService;
import com.cplsystems.stock.services.ProductoTipoService;
import com.cplsystems.stock.services.UnidadService;
import java.io.File;
import java.io.FileInputStream;
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
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
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
		this.selectTab = new SelectedTabsControlPanel();
		this.selectTab.setVisibleButtonSave(true);
		activarBotonesAreas();
		this.areas = this.areaService.getAll();
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		if (this.selectTab.isTabAreas()) {
			guardarArea();
		} else if (this.selectTab.isTabBancos()) {
			guardarBanco();
		} else if (this.selectTab.isTabConffya()) {
			guardarConffya();
		} else if (this.selectTab.isTabContratos()) {
			guardarContratos();
		} else if (this.selectTab.isTabDivisas()) {
			guardarMoneda();
		} else if (this.selectTab.isTabProductos()) {
			guardarProductos();
		} else if (this.selectTab.isTabProveedores()) {
			guardarProveedores();
		} else if (this.selectTab.isTabPuestos()) {
			guardarPuesto();
		} else if (this.selectTab.isTabTipoProductos()) {
			guardarProductoTipo();
		} else if (this.selectTab.isTabUnidades()) {
			guardarUnidades();
		} else if (this.selectTab.isTabGiros()) {
			guardarGiros();
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void delete() {
	}

	private void guardarArea() {
		for (Area areaRecord : this.areas) {
			try {
				areaRecord.setToolTipIndice("Seleccionar �rea");
				areaRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");
				if (areaRecord.isNuevoRegistro()) {
					areaRecord.setNuevoRegistro(false);
					areaRecord.setIdArea(null);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(this.stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					areaRecord.setFechaActualizacion(date);
					areaRecord.setOrganizacion((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

					areaRecord.setUsuario((Usuarios) this.sessionUtils.getFromSession("usuario"));

					this.areaService.save(areaRecord);
				} else if (!areaRecord.getNombre().equals("")) {
					this.areaService.update(areaRecord);
				}
			} catch (Exception e) {
			}
		}
		this.areas.clear();
		this.areas = this.areaService.getAll();

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
					String date = sdf.format(this.stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					bancoRecord.setFechaActualizacion(date);
					bancoRecord.setOrganizacion((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

					bancoRecord.setUsuario((Usuarios) this.sessionUtils.getFromSession("usuario"));

					this.bancoService.save(bancoRecord);
				}
			} catch (Exception e) {
			}
		}
		bancosDB.clear();
		bancosDB = this.bancoService.getAll();

		StockUtils.showSuccessmessage("Catalogo de bancos actualizados", "info", Integer.valueOf(0), null);
	}

	private void guardarConffya() {
	}

	private void guardarContratos() {
		for (Contrato contratoRecord : this.contratos) {
			try {
				if ((contratoRecord.getNombre() != null) && (!contratoRecord.getNombre().equals(""))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(this.stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					contratoRecord.setFechaActualizacion(date);
					contratoRecord.setOrganizacion((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

					contratoRecord.setUsuario((Usuarios) this.sessionUtils.getFromSession("usuario"));

					this.contratoService.save(contratoRecord);
				}
			} catch (Exception e) {
			}
		}
		this.contratos.clear();
		this.contratos = this.contratoService.getAll();
		StockUtils.showSuccessmessage("El catalogo de contratos ha sido actualizado", "info", Integer.valueOf(0), null);
	}

	private void guardarMoneda() {
		for (Moneda monedaRecord : this.monedasDB) {
			try {
				monedaRecord.setToolTipIndice("Seleccionar una moneda");

				monedaRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");
				if ((monedaRecord.getNombre() != null) && (!monedaRecord.getNombre().equals(""))) {
					monedaRecord.setNuevoRegistro(false);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(this.stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					monedaRecord.setFechaActualizacion(date);
					monedaRecord.setOrganizacion((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

					monedaRecord.setUsuario((Usuarios) this.sessionUtils.getFromSession("usuario"));

					this.monedaService.save(monedaRecord);
				}
			} catch (Exception e) {
			}
		}
		this.monedasDB.clear();
		this.monedasDB = this.monedaService.getAll();
		StockUtils.showSuccessmessage("El catalogo de divisas ha sido actualizado", "info", Integer.valueOf(0), null);
	}

	private void guardarProductos() {
	}

	private void guardarProveedores() {
	}

	private void guardarPuesto() {
		for (Posicion posicionRecord : this.posiciones) {
			try {
				posicionRecord.setToolTipIndice("Seleccionar puesto");
				posicionRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(this.stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				posicionRecord.setFechaActualizacion(date);
				posicionRecord.setOrganizacion((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

				posicionRecord.setUsuario((Usuarios) this.sessionUtils.getFromSession("usuario"));
				if (posicionRecord.isNuevoRegistro()) {
					posicionRecord.setNuevoRegistro(false);
					posicionRecord.setIdPosicion(null);
					this.posicionService.save(posicionRecord);
				} else if (!posicionRecord.getNombre().equals("")) {
					this.posicionService.update(posicionRecord);
				}
			} catch (Exception e) {
			}
		}
		this.posiciones.clear();
		this.posiciones = this.posicionService.getAll();

		StockUtils.showSuccessmessage("Catalogo de puestos actualizados", "info", Integer.valueOf(0), null);
	}

	private void guardarProductoTipo() {
		for (ProductoTipo productoTipoRecord : this.productoTipoDB) {
			try {
				productoTipoRecord.setToolTipIndice("Seleccionar un tipo de producto");

				productoTipoRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(this.stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				productoTipoRecord.setFechaActualizacion(date);
				productoTipoRecord.setOrganizacion((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

				productoTipoRecord.setUsuario((Usuarios) this.sessionUtils.getFromSession("usuario"));
				if (productoTipoRecord.isNuevoRegistro()) {
					productoTipoRecord.setNuevoRegistro(false);
					productoTipoRecord.setIdProductoTipo(null);
					this.productoTipoService.save(productoTipoRecord);
				} else if (!productoTipoRecord.getNombre().equals("")) {
					this.productoTipoService.update(productoTipoRecord);
				}
			} catch (Exception e) {
			}
		}
		this.productoTipoDB.clear();
		this.productoTipoDB = this.productoTipoService.getAll();

		StockUtils.showSuccessmessage("Catalogo de tipo de productos actualizado", "info", Integer.valueOf(0), null);
	}

	private void guardarUnidades() {
		if ((this.unidadesDB != null) && (this.unidadesDB.size() > 0)) {
			for (Unidad item : this.unidadesDB) {
				if ((item.getNombre() != null) && (!item.getNombre().isEmpty())) {
					item.setFechaActualizacion(Calendar.getInstance());
					item.setOrganizacion((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

					item.setUsuario((Usuarios) this.sessionUtils.getFromSession("usuario"));

					this.unidadService.save(item);
				}
			}
			StockUtils.showSuccessmessage("Se han realizado cambios en el catalogo de -Unidades de medida-", "info",
					Integer.valueOf(0), null);

			this.mensajeDeCambios = "";
		} else {
			StockUtils.showSuccessmessage(
					"No se puede llevar a cabo una actualizacion en el catalogo -Unidades de medida-, catalogo vacio",
					"warning", Integer.valueOf(0), null);
		}
	}

	private void guardarGiros() {
		if ((this.giros != null) && (this.giros.size() > 0)) {
			for (Giro item : this.giros) {
				if ((item.getNombre() != null) && (!item.getNombre().isEmpty())) {
					Organizacion org = (Organizacion) this.sessionUtils.getFromSession("FIRMA");
					item.setOrganizacion(org);
					this.giroService.save(item);
				}
			}
			StockUtils.showSuccessmessage("Se han realizado cambios en el catalogo de -Giros-", "info",
					Integer.valueOf(0), null);

			this.mensajeDeCambios = "";
		} else {
			StockUtils.showSuccessmessage("No se puede llevar la actualizacion en el catalogo -Giros-, catalogo vacio",
					"warning", Integer.valueOf(0), null);
		}
	}

	private void eliminarArea() {
		if (this.area != null) {
			this.areaService.delete(this.area);

			this.areas.clear();
			this.areas = this.areaService.getAll();
			if ((this.areas != null) && (!((Area) this.areas.get(this.areas.size() - 1)).getNombre().equals(""))) {
				this.areas.add(crearColumnaVaciaArea());
			} else {
				Area nuevo = crearColumnaVaciaArea();
				this.areas.add(nuevo);
			}
			StockUtils.showSuccessmessage(this.area.getNombre() + " ha sido eliminado", "info", Integer.valueOf(0),
					null);
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un �rea para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void eliminarAreaIndex(@BindingParam("index") Integer index) {
		this.area = ((Area) this.areas.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (this.areas != null) {
			try {
				this.areaService.delete(this.area);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				this.areas.clear();
				this.areas = this.areaService.getAll();

				StockUtils.showSuccessmessage("El �rea -" + this.area.getNombre() + "- ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"El Banco -" + this.area.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
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
		this.bancoSeleccionado = ((Banco) bancosDB.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (bancosDB != null) {
			try {
				this.bancoService.delete(this.bancoSeleccionado);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				bancosDB.clear();
				bancosDB = this.bancoService.getAll();

				StockUtils.showSuccessmessage("El Banco -" + this.bancoSeleccionado.getNombre() + "- ha sido eliminado",
						"info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("El Banco -" + this.bancoSeleccionado.getNombre()
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
		this.contrato = ((Contrato) this.contratos.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (this.contratos != null) {
			try {
				this.contratoService.delete(this.contrato);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				this.contratos.clear();
				this.contratos = this.contratoService.getAll();

				StockUtils.showSuccessmessage("El contrato -" + this.contrato.getNombre() + "- ha sido eliminado",
						"info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"El Contrato -" + this.contrato.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
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
		this.monedaSeleccionada = ((Moneda) this.monedasDB.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (this.monedasDB != null) {
			try {
				this.monedaService.delete(this.monedaSeleccionada);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				this.monedasDB.clear();
				this.monedasDB = this.monedaService.getAll();

				StockUtils.showSuccessmessage(
						"La divisa -" + this.monedaSeleccionada.getNombre() + "- ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("La divisa -" + this.monedaSeleccionada.getSimbolo() + " "
						+ this.monedaSeleccionada.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
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
		this.posicion = ((Posicion) this.posiciones.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (this.posicion != null) {
			try {
				this.posicionService.delete(this.posicion);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				this.posiciones.clear();
				this.posiciones = this.posicionService.getAll();
				StockUtils.showSuccessmessage(this.posicion.getNombre() + " ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"El puesto -" + this.posicion.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
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
		this.productoTipoSelected = ((ProductoTipo) this.productoTipoDB.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (this.productoTipoDB != null) {
			try {
				this.productoTipoService.delete(this.productoTipoSelected);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				this.productoTipoDB.clear();
				this.productoTipoDB = this.productoTipoService.getAll();

				StockUtils.showSuccessmessage(this.productoTipoSelected.getNombre() + " ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("La familia -" + this.productoTipoSelected.getNombre()
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
		this.unidad = ((Unidad) this.unidadesDB.get(index.intValue()));
		try {
			this.unidadService.delete(this.unidad);
		} catch (Exception e) {
			continuarEliminacion = false;
		}
		if (continuarEliminacion) {
			this.unidadesDB.remove(this.unidad);
			StockUtils.showSuccessmessage("La unidad de medida ha sido eliminada", "info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("La unidad de medida -" + this.unidad.getNombre()
					+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void removerGiro(@BindingParam("index") Integer index) {
		boolean continuarEliminacion = true;
		this.giro = ((Giro) this.giros.get(index.intValue()));
		try {
			this.giroService.delete(this.giro);
		} catch (Exception e) {
			continuarEliminacion = false;
		}
		if (continuarEliminacion) {
			this.giros.remove(this.giro);
			this.giros = new ArrayList();
			this.giros = this.giroService.getAll();
			StockUtils.showSuccessmessage("El giro -" + this.giro.getNombre() + "- ha sido eliminado del catalogo",
					"info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage(
					"El giro -" + this.giro.getNombre() + "- esta siendo utilizado. No puede ser eliminado", "error",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void removerNaturaleza(@BindingParam("index") Integer index) {
		boolean continuarEliminacion = true;
		this.productoNaturaleza = ((ProductoNaturaleza) this.productosNaturalezas.get(index.intValue()));
		try {
			this.productoNaturalezaService.delete(this.productoNaturaleza);
		} catch (Exception e) {
			continuarEliminacion = false;
		}
		if (continuarEliminacion) {
			this.productosNaturalezas.remove(this.productoNaturaleza);
			this.productosNaturalezas = new ArrayList();
			this.productosNaturalezas = this.productoNaturalezaService.getAll();
			StockUtils.showSuccessmessage("La naturaleza producto -" + this.productoNaturaleza.getNombre()
					+ "- ha sido eliminado del catalogo", "info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("La naturaleza producto -" + this.productoNaturaleza.getNombre()
					+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
		}
	}

	private Area crearColumnaVaciaArea() {
		Area areaVacia = new Area();
		if (this.areas != null) {
			areaVacia.setIdArea(Long.valueOf(String.valueOf(this.areas.size() + 1)));
		} else {
			this.areas = new ArrayList();
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
		if (this.posiciones != null) {
			objeto.setIdPosicion(Long.valueOf(String.valueOf(this.posiciones.size() + 1)));
		} else {
			this.posiciones = new ArrayList();
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
		if (this.productoTipoDB != null) {
			objeto.setIdProductoTipo(Long.valueOf(String.valueOf(this.productoTipoDB.size() + 1)));
		} else {
			this.productoTipoDB = new ArrayList();
			objeto.setIdProductoTipo(Long.valueOf(1L));
		}
		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar un tipo de producto");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	private void recargarAreas() {
		if (this.areas == null) {
			this.areas.add(crearColumnaVaciaArea());
		} else {
			try {
				if (!((Area) this.areas.get(this.areas.size() - 1)).getNombre().equals("")) {
					this.areas.add(crearColumnaVaciaArea());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void recargarPosiciones() {
		if ((this.posiciones == null) || (this.posiciones.size() == 0)) {
			try {
				this.posiciones = new ArrayList();
				this.posiciones.add(crearColumnaVaciaP());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (!((Posicion) this.posiciones.get(this.posiciones.size() - 1)).getNombre().equals("")) {
					this.posiciones.add(crearColumnaVaciaP());
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
		if ((this.monedasDB == null) || (this.monedasDB.size() == 0)) {
			try {
				this.monedasDB = new ArrayList();
				this.monedasDB.add(crearColumnaVaciaMonedas());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (!((Moneda) this.monedasDB.get(this.monedasDB.size() - 1)).getNombre().equals("")) {
					this.monedasDB.add(crearColumnaVaciaMonedas());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void recargarProductoTipo() {
		if ((this.productoTipoDB == null) || (this.productoTipoDB.size() == 0)) {
			try {
				this.productoTipoDB = new ArrayList();
				this.productoTipoDB.add(crearColumnaVaciaTipoProducto());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (!((ProductoTipo) this.productoTipoDB.get(this.productoTipoDB.size() - 1)).getNombre().equals("")) {
					this.productoTipoDB.add(crearColumnaVaciaTipoProducto());
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
		this.areas = this.areaService.getAll();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabBanco() {
		activarBotonesBancos();
		bancosDB = this.bancoService.getAll();
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
		this.contratos = this.contratoService.getAll();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabMoneda() {
		activarBotonesMonedas();
		this.monedasDB = this.monedaService.getAll();
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
		this.posiciones = this.posicionService.getAll();
		activarBotonesPuestos();
	}

	@Command
	@NotifyChange({ "productoTipoDB" })
	public void selectTabTiposProducto() {
		this.productoTipoDB = this.productoTipoService.getAll();
		activarBotonesTiposProductos();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabUnidades() {
		activarBotonesUnidades();

		this.unidadesDB = this.unidadService.getAll();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabGiro() {
		activarBotonesGiros();
		this.giros = this.giroService.getAll();
	}

	@Command
	@NotifyChange({ "*" })
	public void selectTabNaturaleza() {
		activarBotonesNaturaleza();
		this.productosNaturalezas = this.productoNaturalezaService.getAll();
	}

	private void activarBotonesAreas() {
		this.selectTab.setTabAreas(true);
		this.selectTab.setTabBancos(false);
		this.selectTab.setTabConffya(false);
		this.selectTab.setTabContratos(false);
		this.selectTab.setTabDivisas(false);
		this.selectTab.setTabProductos(false);
		this.selectTab.setTabProveedores(false);
		this.selectTab.setTabPuestos(false);
		this.selectTab.setTabTipoProductos(false);
		this.selectTab.setTabUnidades(false);
		this.selectTab.setActivarButtonDelete(false);
		this.selectTab.setActivarButtonSave(false);
		this.selectTab.setVisibleButtonSave(true);
		this.selectTab.setToolTipSave("Actualizar/Guardar �rea");
		this.selectTab.setToolTipDelete("Eliminar �rea");
	}

	private void activarBotonesBancos() {
		this.selectTab.setTabAreas(false);
		this.selectTab.setTabBancos(true);
		this.selectTab.setTabConffya(false);
		this.selectTab.setTabContratos(false);
		this.selectTab.setTabDivisas(false);
		this.selectTab.setTabProductos(false);
		this.selectTab.setTabProveedores(false);
		this.selectTab.setTabPuestos(false);
		this.selectTab.setTabTipoProductos(false);
		this.selectTab.setTabUnidades(false);
		this.selectTab.setActivarButtonDelete(false);
		this.selectTab.setActivarButtonSave(false);
		this.selectTab.setVisibleButtonSave(true);
		this.selectTab.setToolTipSave("Actualizar/Guardar banco");
		this.selectTab.setToolTipDelete("Eliminar banco");
	}

	private void activarBotonesConffya() {
		this.selectTab.setTabAreas(false);
		this.selectTab.setTabBancos(false);
		this.selectTab.setTabConffya(true);
		this.selectTab.setTabContratos(false);
		this.selectTab.setTabDivisas(false);
		this.selectTab.setTabProductos(false);
		this.selectTab.setTabProveedores(false);
		this.selectTab.setTabPuestos(false);
		this.selectTab.setTabTipoProductos(false);
		this.selectTab.setTabUnidades(false);
		this.selectTab.setActivarButtonDelete(true);
		this.selectTab.setActivarButtonSave(true);
		this.selectTab.setVisibleButtonSave(false);
		this.selectTab.setToolTipSave("Actualizar/Guardar banco");
		this.selectTab.setToolTipDelete("Eliminar banco");
	}

	private void activarBotonesContrato() {
		this.selectTab.setTabAreas(false);
		this.selectTab.setTabBancos(false);
		this.selectTab.setTabConffya(false);
		this.selectTab.setTabContratos(true);
		this.selectTab.setTabDivisas(false);
		this.selectTab.setTabProductos(false);
		this.selectTab.setTabProveedores(false);
		this.selectTab.setTabPuestos(false);
		this.selectTab.setTabTipoProductos(false);
		this.selectTab.setTabUnidades(false);
		this.selectTab.setActivarButtonDelete(false);
		this.selectTab.setActivarButtonSave(false);
		this.selectTab.setVisibleButtonSave(true);
		this.selectTab.setToolTipSave("Actualizar/Guardar contrato");
		this.selectTab.setToolTipDelete("Eliminar contrato");
	}

	private void activarBotonesMonedas() {
		this.selectTab.setTabAreas(false);
		this.selectTab.setTabBancos(false);
		this.selectTab.setTabConffya(false);
		this.selectTab.setTabContratos(false);
		this.selectTab.setTabDivisas(true);
		this.selectTab.setTabProductos(false);
		this.selectTab.setTabProveedores(false);
		this.selectTab.setTabPuestos(false);
		this.selectTab.setTabTipoProductos(false);
		this.selectTab.setTabUnidades(false);
		this.selectTab.setActivarButtonDelete(false);
		this.selectTab.setActivarButtonSave(false);
		this.selectTab.setVisibleButtonSave(true);
		this.selectTab.setToolTipSave("Actualizar/Guardar divisa");
		this.selectTab.setToolTipDelete("Eliminar divisa");
	}

	private void activarBotonesProductos() {
		this.selectTab.setTabAreas(false);
		this.selectTab.setTabBancos(false);
		this.selectTab.setTabConffya(false);
		this.selectTab.setTabContratos(false);
		this.selectTab.setTabDivisas(false);
		this.selectTab.setTabProductos(true);
		this.selectTab.setTabProveedores(false);
		this.selectTab.setTabPuestos(false);
		this.selectTab.setTabTipoProductos(false);
		this.selectTab.setTabUnidades(false);
		this.selectTab.setActivarButtonDelete(false);
		this.selectTab.setActivarButtonSave(false);
		this.selectTab.setVisibleButtonSave(false);
	}

	private void activarBotonesProveedores() {
		this.selectTab.setTabAreas(false);
		this.selectTab.setTabBancos(false);
		this.selectTab.setTabConffya(false);
		this.selectTab.setTabContratos(false);
		this.selectTab.setTabDivisas(false);
		this.selectTab.setTabProductos(false);
		this.selectTab.setTabProveedores(true);
		this.selectTab.setTabPuestos(false);
		this.selectTab.setTabTipoProductos(false);
		this.selectTab.setTabUnidades(false);
		this.selectTab.setActivarButtonDelete(false);
		this.selectTab.setActivarButtonSave(false);
		this.selectTab.setVisibleButtonSave(false);
	}

	private void activarBotonesPuestos() {
		this.selectTab.setTabAreas(false);
		this.selectTab.setTabBancos(false);
		this.selectTab.setTabConffya(false);
		this.selectTab.setTabContratos(false);
		this.selectTab.setTabDivisas(false);
		this.selectTab.setTabProductos(false);
		this.selectTab.setTabProveedores(false);
		this.selectTab.setTabPuestos(true);
		this.selectTab.setTabTipoProductos(false);
		this.selectTab.setTabUnidades(false);
		this.selectTab.setActivarButtonDelete(false);
		this.selectTab.setActivarButtonSave(false);
		this.selectTab.setVisibleButtonSave(true);
		this.selectTab.setToolTipSave("Actualizar/Guardar puesto");
		this.selectTab.setToolTipDelete("Eliminar puesto");
	}

	private void activarBotonesTiposProductos() {
		this.selectTab.setTabAreas(false);
		this.selectTab.setTabBancos(false);
		this.selectTab.setTabConffya(false);
		this.selectTab.setTabContratos(false);
		this.selectTab.setTabDivisas(false);
		this.selectTab.setTabProductos(false);
		this.selectTab.setTabProveedores(false);
		this.selectTab.setTabPuestos(false);
		this.selectTab.setTabTipoProductos(true);
		this.selectTab.setTabUnidades(false);
		this.selectTab.setActivarButtonDelete(false);
		this.selectTab.setActivarButtonSave(false);
		this.selectTab.setVisibleButtonSave(true);
		this.selectTab.setToolTipSave("Actualizar/Guardar tipo de productos");
		this.selectTab.setToolTipDelete("Eliminar productos");
	}

	private void activarBotonesUnidades() {
		this.selectTab.setTabAreas(false);
		this.selectTab.setTabBancos(false);
		this.selectTab.setTabConffya(false);
		this.selectTab.setTabContratos(false);
		this.selectTab.setTabDivisas(false);
		this.selectTab.setTabProductos(false);
		this.selectTab.setTabProveedores(false);
		this.selectTab.setTabPuestos(false);
		this.selectTab.setTabTipoProductos(false);
		this.selectTab.setTabUnidades(true);
		this.selectTab.setActivarButtonDelete(true);
		this.selectTab.setActivarButtonSave(false);
		this.selectTab.setVisibleButtonSave(true);
		this.selectTab.setToolTipSave("Actualizar/Guardar unidad de medida");
		this.selectTab.setToolTipDelete("Eliminar productos");
	}

	private void activarBotonesGiros() {
		this.selectTab.setTabAreas(false);
		this.selectTab.setTabBancos(false);
		this.selectTab.setTabConffya(false);
		this.selectTab.setTabContratos(false);
		this.selectTab.setTabDivisas(false);
		this.selectTab.setTabProductos(false);
		this.selectTab.setTabProveedores(false);
		this.selectTab.setTabPuestos(false);
		this.selectTab.setTabTipoProductos(false);
		this.selectTab.setTabUnidades(false);
		this.selectTab.setTabGiros(true);
		this.selectTab.setActivarButtonDelete(true);
		this.selectTab.setActivarButtonSave(false);
		this.selectTab.setVisibleButtonSave(true);
		this.selectTab.setToolTipSave("Actualizar/Guardar giro");
		this.selectTab.setToolTipDelete("Eliminar giro");
	}

	private void activarBotonesNaturaleza() {
		this.selectTab.setTabAreas(false);
		this.selectTab.setTabBancos(false);
		this.selectTab.setTabConffya(false);
		this.selectTab.setTabContratos(false);
		this.selectTab.setTabDivisas(false);
		this.selectTab.setTabProductos(false);
		this.selectTab.setTabProveedores(false);
		this.selectTab.setTabPuestos(false);
		this.selectTab.setTabTipoProductos(false);
		this.selectTab.setTabUnidades(false);
		this.selectTab.setTabGiros(false);
		this.selectTab.setTabNaturaleza(true);
		this.selectTab.setActivarButtonDelete(true);
		this.selectTab.setActivarButtonSave(false);
		this.selectTab.setVisibleButtonSave(true);
		this.selectTab.setToolTipSave("Actualizar/Guardar giro");
		this.selectTab.setToolTipDelete("Eliminar giro");
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevaArea() {
		if (this.areas == null) {
			this.areas = new ArrayList();
		}
		this.areas.add(crearColumnaVaciaAreaEstandar());
		this.mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoBanco() {
		if (bancosDB == null) {
			bancosDB = new ArrayList();
		}
		bancosDB.add(crearColumnaVaciaBanco());
		this.mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoContrato() {
		if (this.contratos == null) {
			this.contratos = new ArrayList();
		}
		this.contratos.add(crearColumnaVaciaContrato());
		this.mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevaDivisa() {
		if (this.monedasDB == null) {
			this.monedasDB = new ArrayList();
		}
		this.monedasDB.add(crearColumnaVaciaMonedas());
		this.mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoPuesto() {
		if (this.posiciones == null) {
			this.posiciones = new ArrayList();
		}
		this.posiciones.add(crearColumnaVaciaP());
		this.mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoTipoProducto() {
		if (this.productoTipoDB == null) {
			this.productoTipoDB = new ArrayList();
		}
		this.productoTipoDB.add(crearColumnaVaciaTipoProducto());
		this.mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevaUnidad() {
		if (this.unidadesDB == null) {
			this.unidadesDB = new ArrayList();
		}
		Unidad nuevaUnidad = new Unidad();
		this.unidadesDB.add(nuevaUnidad);
		this.mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoGiro() {
		if (this.giros == null) {
			this.giros = new ArrayList();
		}
		Giro nueoGiro = new Giro();
		this.giros.add(nueoGiro);
		this.mensajeDeCambios = "No olvide salvar sus cambios";
	}

	@Command
	@NotifyChange({ "*" })
	public void agregarNuevaNaturaleza() {
		if (this.productosNaturalezas == null) {
			this.productosNaturalezas = new ArrayList();
		}
		ProductoNaturaleza nueoGiro = new ProductoNaturaleza();
		this.productosNaturalezas.add(nueoGiro);
		this.mensajeDeCambios = "No olvide salvar sus cambios";
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
					String date = sdf.format(this.stockUtils.convertirCalendarToDate(Calendar.getInstance()));

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
