package com.cplsystems.stock.app.vm.controlpanel.utils;

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
import org.zkoss.bind.annotation.Init;

import com.cplsystems.stock.app.utils.StockUtils;
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

public class ControlPanelMetaclass extends ControlPanelVariables {
	public static final long serialVersionUID = 5093877120990395398L;

	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects() {
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
	}

	public void initProperties() {

	}
	
	//********************************************************************************************
	public void guardarArea() {
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
					areaRecord.setOrganizacion(organizacion);

					areaRecord.setUsuario(usuario);

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

	public void guardarBanco() {
		for (Banco bancoRecord : bancosDB) {
			try {
				bancoRecord.setToolTipIndice("Seleccionar un banco");

				bancoRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");
				if ((bancoRecord.getNombre() != null) && (!bancoRecord.getNombre().isEmpty())) {
					bancoRecord.setNuevoRegistro(false);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					bancoRecord.setFechaActualizacion(date);
					bancoRecord.setOrganizacion(organizacion);

					bancoRecord.setUsuario(usuario);

					bancoService.save(bancoRecord);
				}
			} catch (Exception e) {
			}
		}
		bancosDB.clear();
		bancosDB = bancoService.getAll();

		StockUtils.showSuccessmessage("Catalogo de bancos actualizados", "info", Integer.valueOf(0), null);
	}

	public void guardarConffya() {
	}

	public void guardarContratos() {
		for (Contrato contratoRecord : contratos) {
			try {
				if ((contratoRecord.getNombre() != null) && (!contratoRecord.getNombre().equals(""))) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					contratoRecord.setFechaActualizacion(date);
					contratoRecord.setOrganizacion(organizacion);
					contratoRecord.setUsuario(usuario);

					contratoService.save(contratoRecord);
				}
			} catch (Exception e) {
			}
		}
		contratos.clear();
		contratos = contratoService.getAll();
		StockUtils.showSuccessmessage("El catalogo de contratos ha sido actualizado", "info", Integer.valueOf(0), null);
	}

	public void guardarMoneda() {
		for (Moneda monedaRecord : monedasDB) {
			try {
				monedaRecord.setToolTipIndice("Seleccionar una moneda");

				monedaRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");
				if ((monedaRecord.getNombre() != null) && (!monedaRecord.getNombre().equals(""))) {
					monedaRecord.setNuevoRegistro(false);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
					String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

					monedaRecord.setFechaActualizacion(date);
					monedaRecord.setOrganizacion(organizacion);

					monedaRecord.setUsuario(usuario);

					monedaService.save(monedaRecord);
				}
			} catch (Exception e) {
			}
		}
		monedasDB.clear();
		monedasDB = monedaService.getAll();
		StockUtils.showSuccessmessage("El catalogo de divisas ha sido actualizado", "info", Integer.valueOf(0), null);
	}

	public void guardarProductos() {
	}

	public void guardarProveedores() {
	}

	public void guardarPuesto() {
		for (Posicion posicionRecord : posiciones) {
			try {
				posicionRecord.setToolTipIndice("Seleccionar puesto");
				posicionRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				posicionRecord.setFechaActualizacion(date);
				posicionRecord.setOrganizacion(organizacion);

				posicionRecord.setUsuario(usuario);
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

	public void guardarProductoTipo() {
		for (ProductoTipo productoTipoRecord : productoTipoDB) {
			try {
				productoTipoRecord.setToolTipIndice("Seleccionar un tipo de producto");

				productoTipoRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
				String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));

				productoTipoRecord.setFechaActualizacion(date);
				productoTipoRecord.setOrganizacion(organizacion);

				productoTipoRecord.setUsuario(usuario);
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

	public void guardarUnidades() {
		if ((unidadesDB != null) && (unidadesDB.size() > 0)) {
			for (Unidad item : unidadesDB) {
				if ((item.getNombre() != null) && (!item.getNombre().isEmpty())) {
					item.setFechaActualizacion(Calendar.getInstance());
					item.setOrganizacion(organizacion);

					item.setUsuario(usuario);

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

	public void guardarGiros() {
		if ((giros != null) && (giros.size() > 0)) {
			for (Giro item : giros) {
				if ((item.getNombre() != null) && (!item.getNombre().isEmpty())) {
					item.setOrganizacion(organizacion);
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
	
	public void guardarProductoNaturaleza() {
		if ((productosNaturalezas != null) && (productosNaturalezas.size() > 0)) {
			for (ProductoNaturaleza item : productosNaturalezas) {
				if ((item.getNombre() != null) && (!item.getNombre().isEmpty())) {
					productoNaturalezaService.save(item);
				}
			}
			StockUtils.showSuccessmessage("Se han realizado cambios en el catalogo de -Naturaleza de productos-", "info",
					Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("No se puede llevar la actualizacion en el catalogo -Naturaleza de productos-, catalogo vacio",
					"warning", Integer.valueOf(0), null);
		}
	}
	
	//*********************************************************************************************
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Area crearColumnaVaciaArea() {
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Posicion crearColumnaVaciaP() {
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

	public Area crearColumnaVaciaAreaEstandar() {
		Area objeto = new Area();

		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar un �rea");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	public Banco crearColumnaVaciaBanco() {
		Banco objeto = new Banco();

		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar un banco");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	public Contrato crearColumnaVaciaContrato() {
		Contrato objeto = new Contrato();
		return objeto;
	}

	public Moneda crearColumnaVaciaMonedas() {
		Moneda objeto = new Moneda();
		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar un tipo de producto");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ProductoTipo crearColumnaVaciaTipoProducto() {
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
	
	//**********************************************************************************************
	public void recargarAreas() {
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void recargarPosiciones() {
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void recargarBanco() {
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void recargarMonedas() {
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void recargarProductoTipo() {
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
	//**********************************************************************************************
	public void activarBotonesAreas() {
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

	public void activarBotonesBancos() {
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

	public void activarBotonesConffya() {
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

	public void activarBotonesContrato() {
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

	public void activarBotonesMonedas() {
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

	public void activarBotonesProductos() {
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

	public void activarBotonesProveedores() {
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

	public void activarBotonesPuestos() {
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

	public void activarBotonesTiposProductos() {
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

	public void activarBotonesUnidades() {
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

	public void activarBotonesGiros() {
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

	public void activarBotonesNaturaleza() {
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
	
	//************************************** CREAR OBJETOS ********************************************************
	public Area crearArea(Area area, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				area.setNombre(valor);
		}
		return area;
	}

	public Almacen crearAlmacen(Almacen nuevoAlmacen, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				nuevoAlmacen.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				nuevoAlmacen.setDescripcion(valor);
			break;
		case 2:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
				if (valor.contains(".0")) {
					valor = removerPuntoCero(valor);
				}
				nuevoAlmacen.setArea(getAreasFromList(Long.valueOf(Long.parseLong(valor))));
			}
			break;
		}
		return nuevoAlmacen;
	}

	public Banco crearBanco(Banco banco, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL")))
				banco.setNombre(valor);
		}
		return banco;
	}

	public Moneda crearMoneda(Moneda moneda, XSSFCell valorDePropiedad, int indice) {
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

	public Posicion crearPosicion(Posicion puesto, XSSFCell valorDePropiedad, int indice) {
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

	public ProductoTipo crearTipoProductos(ProductoTipo productoTipo, XSSFCell valorDePropiedad, int indice) {
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

	public Unidad crearUnidadMedida(Unidad unidad, XSSFCell valorDePropiedad, int indice) {
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

	public Giro crearGiro(Giro giro, XSSFCell valorDePropiedad, int indice) {
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

	public ProductoNaturaleza crearNaturaleza(ProductoNaturaleza productoNaturaleza, XSSFCell valorDePropiedad,
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
	//******************************************** FIN CREAR OBJETOS ***************************************************************
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Area> leerDatosDesdeExcelArea(Iterator rowIterator) {
		List<Area> areaNuevosExcel = new ArrayList();
		try {
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
					nuevoArea.setUsuario(usuario);

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
	public List<Almacen> leerDatosDesdeExcelAlmacenes(Iterator rowIterator) {
		List<Almacen> almacenNuevosExcel = new ArrayList();
		try {
			Integer i = 0;
			int j;
			while (rowIterator.hasNext()) {
				Almacen nuevoAlmacen = new Almacen();
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				if (i > 0) {
					j = 0;
					while ((iterator.hasNext()) && (j < 3)) {
						XSSFCell hssfCell = (XSSFCell) iterator.next();
						nuevoAlmacen = crearAlmacen(nuevoAlmacen, hssfCell, j);
						j++;
					}
					nuevoAlmacen.setOrganizacion(organizacion);
					almacenNuevosExcel.add(nuevoAlmacen);
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return almacenNuevosExcel;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Banco> leerDatosDesdeExcelBanco(InputStream inPutStream, int indiceSheet) {
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
					nuevoBanco.setUsuario(usuario);

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
	public List<Moneda> leerDatosDesdeExcelMoneda(InputStream inPutStream, int indiceSheet) {
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
					nuevoMoneda.setUsuario(usuario);

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
	public List<Posicion> leerDatosDesdeExcelPosicion(InputStream inPutStream, int indiceSheet) {
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
					nuevoPosicion.setUsuario(usuario);

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
	public List<ProductoTipo> leerDatosDesdeExcelTipoProductos(InputStream inPutStream, int indiceSheet) {
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
					nuevoProductoTipo.setUsuario(usuario);

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
	public List<Unidad> leerDatosDesdeExcelUnidadMedida(InputStream inPutStream, int indiceSheet) {
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
					nuevoUnidad.setUsuario(usuario);
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
	public List<Giro> leerDatosDesdeExcelGiros(InputStream inPutStream, int indiceSheet) {
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
	public List<ProductoNaturaleza> leerDatosDesdeExcelNaturaleza(InputStream inPutStream, int indiceSheet) {
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
	
	//***************************************************************************
	public void eliminarArea() {
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
			StockUtils.showSuccessmessage(area.getNombre() + " ha sido eliminado", "info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un �rea para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

}
