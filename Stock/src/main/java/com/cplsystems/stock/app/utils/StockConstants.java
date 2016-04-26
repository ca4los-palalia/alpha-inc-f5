package com.cplsystems.stock.app.utils;

import com.cplsystems.stock.domain.SistemaOperativo;

public class StockConstants {
	public static final String MODAL_VIEW_PRODUCTOS = "/modulos/productos/utils/buscarProducto.zul";
	public static final String MODAL_VIEW_ENVIO_PRODUCTOS = "/modulos/productos/utils/configurarEnvioProductos.zul";
	public static final String MODAL_VIEW_BANDEJA = "/modulos//utilidades/bandeja.zul";
	public static final String MODAL_VIEW_DETALLES_REQUISICION = "/modulos/requisicion/requisicionDetalles.zul";
	public static final String CURRENCY_FORMAT = "###,###,###.00";
	public static final String TOOL_TIP_SAVE_AREA = "Actualizar/Guardar �rea";
	public static final String TOOL_TIP_SAVE_BANCO = "Actualizar/Guardar banco";
	public static final String TOOL_TIP_SAVE_CONFFYA = "Actualizar/Guardar catalogo conffya";
	public static final String TOOL_TIP_SAVE_PUESTO = "Actualizar/Guardar puesto";
	public static final String TOOL_TIP_SAVE_CONTRATO = "Actualizar/Guardar contrato";
	public static final String TOOL_TIP_SAVE_MONEDA = "Actualizar/Guardar divisa";
	public static final String TOOL_TIP_SAVE_PRODUCTO = "Actualizar/Guardar tipo de productos";
	public static final String TOOL_TIP_SAVE_UNIDAD = "Actualizar/Guardar unidad de medida";
	public static final String TOOL_TIP_SAVE_GIRO = "Actualizar/Guardar giro";
	public static final String TOOL_TIP_DELETE_AREA = "Eliminar �rea";
	public static final String TOOL_TIP_DELETE_PUESTO = "Eliminar puesto";
	public static final String TOOL_TIP_DELETE_BANCO = "Eliminar banco";
	public static final String TOOL_TIP_DELETE_MONEDA = "Eliminar divisa";
	public static final String TOOL_TIP_DELETE_PRODUCTO = "Eliminar productos";
	public static final String TOOL_TIP_DELETE_CONTRATO = "Eliminar contrato";
	public static final String TOOL_TIP_DELETE_UNIDAD = "Eliminar unidad de medida";
	public static final String TOOL_TIP_DELETE_GIRO = "Eliminar giro";
	public static final String TOOL_TIP_ROW_SELECTED_AREA = "Seleccionar un �rea";
	public static final String TOOL_TIP_ROW_SELECTED_PUESTO = "Seleccionar un puesto";
	public static final String TOOL_TIP_ROW_SELECTED_BANCO = "Seleccionar un banco";
	public static final String TOOL_TIP_ROW_SELECTED_MONEDA = "Seleccionar una moneda";
	public static final String TOOL_TIP_ROW_SELECTED_TIPO_PRODUCTO = "Seleccionar un tipo de producto";
	public static final String TOOL_TIP_ROW_EDICION_NOMBRE = "Clic sobre esta columna para editar nombre";
	public static final String ESTADO_REQUISICION_NUEVA = "RQN";
	public static final String ESTADO_REQUISICION_PENDIENTE = "RQP";
	public static final String ESTADO_REQUISICION_TERMINADA = "RQT";
	public static final String ESTADO_REQUISICION_CANCELADA = "RQC";
	public static final String ESTADO_ORDEN_COMPRA_TERMINADA = "OCT";
	public static final String ESTADO_ORDEN_COMPRA_NUEVA = "OCN";
	public static final String ESTADO_ORDEN_COMPRA_CANCELADA = "OCC";
	public static final String ESTADO_COTIZACION_NUEVA = "CON";
	public static final String ESTADO_COTIZACION_ENVIADA = "COE";
	public static final String ESTADO_COTIZACION_CANCELADA = "COC";
	public static final String ESTADO_COTIZACION_ACEPTADA = "COA";
	public static final String BUSCAR_TODO = "*";
	public static final String CLAVE_FOLIO_REQUISICION = "FRQ";
	public static final String CLAVE_FOLIO_COTIZACION = "FCO";
	public static final String CLAVE_FOLIO_ORDEN_COMPRA = "FOC";
	public static final String ARCHIVO_JASPER_REQUISICION_FORMATO = "jasperTemplates/requisicionFormato.jasper";
	public static final String ARCHIVO_JASPER_COTIZACION_FORMATO = "jasperTemplates/cotizacionFormato.jasper";
	public static final String ARCHIVO_JASPER_ORDEN_COMPRA_FORMATO = "jasperTemplates/ordenCompraFormato.jasper";
	public static final String REPORT_PROVEEDOR_NAME_FILE = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Proveedores"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "reportProveedores.pdf";
	public static final String REPORT_PROVEEDOR_PARAM1 = "parameter1";
	public static final String REPORT_PROVEEDOR_NOMBRE_EMPRESA = "empresaTitle";
	public static final String REPORT_VARIABLE_PRODUCTO_NAME_FILE = new SistemaOperativo()
			.getDirectorioDeInicioDelUsuario() + new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Productos"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "reportProductos.pdf";
	public static final String NOMBRE_TEMPORAL_USUARIO_SYSTEMA = "NOMBRE TEMPORAL DE LA EMPRESA";
	public static final Object BENUTZER = "BENUTZER";
	public static final String FIRMA = "FIRMA";
	public static final String CARPETA_ARCHIVOS_REQUISICIONES = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Requisiciones"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_COTIZACIONES = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Cotizaciones"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_ORDEN_COMPRA = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "OrdenCompra"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_LOGOTIPOS = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Logotipos"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_PRODUCTOS = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Productos"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_PROVEEDORES = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Proveedores"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_USUARIOS = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Users"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String LAYOUT = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Layout"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String EXTENCION_EXCEL = ".xls";
	public static final String EXTENCION_PDF = ".pdf";
	public static final String OS_WIN = "WIN";
	public static final String OS_LIN = "*";
	public static final String OS_MAC = "MAC";
	public static final String LAYOUT_EXCEL_AREA = "LayoutArea.xlsx";
	public static final String LAYOUT_EXCEL_BANCO = "LayoutBanco.xlsx";
	public static final String LAYOUT_EXCEL_GIRO = "LayoutGiro.xlsx";
	public static final String LAYOUT_EXCEL_MONEDA = "LayoutMoneda.xlsx";
	public static final String LAYOUT_EXCEL_POSICION = "LayoutPosicion(Puestos).xlsx";
	public static final String LAYOUT_EXCEL_PRODUCTO = "LayoutProductos.xlsx";
	public static final String LAYOUT_EXCEL_PRODUCTO_TIPO = "LayoutProductoTipo.xlsx";
	public static final String LAYOUT_EXCEL_PROVEEDOR = "LayoutProveedores.xlsx";
	public static final String LAYOUT_EXCEL_UNIDAD = "LayoutUnidad.xlsx";
	public static final String LAYOUT_EXCEL_SCRIPT = "Script.xlsx";

	public static final class GLOBAL_PAGES {
		public static final String HOME_URL = "/home.zul";
		public static final String LOGIN_URL = "/login.zul";
		public static final String PRODUCTOS = "/modulos/productos/productos.zul";
		public static final String PRODUCTOS_BUSCADOR = "/modulos/productos/productosBuscador.zul";
		public static final String PROVEEDORES_BUSCADOR = "/modulos/proveedores/proveedoresBuscador.zul";
		public static final String REQUISICION = "/modulos/requisicion/requisicion.zul";
		public static final String REQUISICION_BUSCADOR = "/modulos/requisicion/requisicionBuscador.zul";
		public static final String CONCENTRADO = "/modulos/requisicion/concentrado.zul";
		public static final String CONTROL = "/modulos/requisicion/control.zul";
		public static final String COTIZACION = "/modulos/requisicion/cotizacion.zul";
		public static final String COTIZACION_BUSCADOR = "/modulos/requisicion/cotizacionBuscador.zul";
		public static final String USUARIOS = "/modulos/usuarios/usuario.zul";
		public static final String CONTROL_PANEL = "/modulos/controlPanel/controlPanel.zul";
		public static final String REPORTS = "/modulos/reportes/reportes.zul";
		public static final String ORDERS = "/modulos/ordenCompra/ordenCompra.zul";
		public static final String CONTROL_PANEL_COFIGURACION_USUARIO = "/modulos/controlPanel/usuario.zul";
		public static final String MODAL_VIEW_COMPANIA = "/modulos/controlPanel/utils/buscarOrganizaciones.zul";
		public static final String CONTROL_PANEL_USUARIOS_NEGOCIO = "/modulos/controlPanel/usuariosCliente.zul";
	}
}
