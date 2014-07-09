/**
 * 
 */
package com.cplsystems.stock.app.utils;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class StockConstants {

	public final static String MODAL_VIEW_PRODUCTOS = "/modulos/productos/utils/buscarProducto.zul";
	public final static String MODAL_VIEW_DETALLES_REQUISICION = "/modulos/requisicion/requisicionDetalles.zul";
	public final static String CURRENCY_FORMAT = "###,###,###.00";

	public final static class GLOBAL_PAGES {
		public static final String HOME_URL = "/home.zul";
		public static final String LOGIN_URL = "/login.zul";
		public static final String PRODUCTOS = "/modulos/productos/productos.zul";
		public static final String PRODUCTOS_BUSCADOR = "/modulos/productos/productosBuscador.zul";
		public static final String PRODUCTOS_REPORTE_ARTICULOS = "/modulos/productos/reportesArticulos.zul";

		public static final String PRODUCTOS_REPORTE_CLASIFICACION = "/modulos/productos/reportesClasificaciones.zul";
		public static final String PRODUCTOS_REPORTE_MULTIPLES_CODIGOS = "/modulos/productos/reportesMultiplesCodigos.zul";
		public static final String PRODUCTOS_REPORTE_COSTO = "/modulos/productos/reportesActualizacionCosto.zul";
		public static final String PRODUCTOS_REPORTE_PRECIO = "/modulos/productos/reportesActualizacionPrecio.zul";
		public static final String PRODUCTOS_REPORTE_PRECIO_COSTO_MASIVA = "/modulos/productos/reportesActualizacionPrecioCostoMasiva.zul";
		public static final String PRODUCTOS_REPORTE_ACTUALIZACION_RAPIDA = "/modulos/productos/reportesActualizacionRapida.zul";
		public static final String PRODUCTOS_REPORTE_AJUSTE_EXISTENCIA = "/modulos/productos/reportesAjusteExistencia.zul";
		public static final String PRODUCTOS_REPORTE_ARTICULO_SUSTITUTO = "/modulos/productos/reportesArticuloSustituto.zul";
		public static final String PRODUCTOS_REPORTE_TIPO_MOVIMIENTO = "/modulos/productos/reportesTipoMovimiento.zul";
		public static final String PRODUCTOS_REPORTE_REGISTRO_MOVIMIENTO = "/modulos/productos/reportesRegistroMovimiento.zul";
		public static final String PRODUCTOS_REPORTE_KARDEX = "/modulos/productos/reportesKardex.zul";
		public static final String PROVEEDORES = "/modulos/proveedores/proveedores.zul";
		
		public static final String PROVEEDORES_BUSCADOR = "/modulos/proveedores/proveedoresBuscador.zul";
		public static final String PROVEEDORES_PRODUCTO = "/modulos/proveedores/proveedoresProducto.zul";

		public static final String REQUISICION = "/modulos/requisicion/requisicion.zul";
		public static final String REQUISICION_BUSCADOR = "/modulos/requisicion/requisicionBuscador.zul";
		public static final String CONCENTRADO = "/modulos/requisicion/concentrado.zul";
		public static final String COTIZACION = "/modulos/requisicion/cotizacion.zul";
		public static final String COTIZACION_BUSCADOR = "/modulos/requisicion/cotizacionBuscador.zul";
		public static final String USUARIOS = "/modulos/usuarios/usuario.zul";
		public static final String CONTROL_PANEL = "/modulos/controlPanel/controlPanel.zul";
		public static final String REPORTS = "/modulos/reportes/reportes.zul";
		public static final String ORDERS = "/modulos/ordenCompra/ordenCompra.zul";
	}

	public static final String TOOL_TIP_SAVE_AREA = "Actualizar o salvar área";
	public static final String TOOL_TIP_SAVE_PUESTO = "Actualizar o salvar puesto";
	public static final String TOOL_TIP_SAVE_BANCO = "Actualizar o salvar banco";
	public static final String TOOL_TIP_SAVE_MONEDA = "Actualizar o salvar moneda";
	public static final String TOOL_TIP_SAVE_PRODUCTO = "Actualizar o salvar tipo de productos";

	public static final String TOOL_TIP_DELETE_AREA = "Eliminar área";
	public static final String TOOL_TIP_DELETE_PUESTO = "Eliminar puesto";
	public static final String TOOL_TIP_DELETE_BANCO = "Eliminar banco";
	public static final String TOOL_TIP_DELETE_MONEDA = "Eliminar moneda";
	public static final String TOOL_TIP_DELETE_PRODUCTO = "Eliminar productos";

	public static final String TOOL_TIP_ROW_SELECTED_AREA = "Seleccionar un área";
	public static final String TOOL_TIP_ROW_SELECTED_PUESTO = "Seleccionar un puesto";
	public static final String TOOL_TIP_ROW_SELECTED_BANCO = "Seleccionar un banco";
	public static final String TOOL_TIP_ROW_SELECTED_MONEDA = "Seleccionar una moneda";
	public static final String TOOL_TIP_ROW_SELECTED_TIPO_PRODUCTO = "Seleccionar un tipo de producto";
	public static final String TOOL_TIP_ROW_EDICION_NOMBRE = "Clic sobre esta columna para editar nombre";

	public static final String REPORT_PROVEEDOR_NAME_FILE = "C:\\reportProveedores.pdf";
	public static final String REPORT_PROVEEDOR_PARAM1 = "parameter1";
	public static final String REPORT_PROVEEDOR_NOMBRE_EMPRESA = "empresaTitle";
	public static final String REPORT_VARIABLE_PRODUCTO_NAME_FILE = "C:\\reportProductos.pdf";
	public static final String REPORT_VARIABLE_REQUISICION_NAME_FILE = "C:\\requisicion.pdf";

	public static final String NOMBRE_TEMPORAL_USUARIO_SYSTEMA = "NOMBRE TEMPORAL DE LA EMPRESA";
	public static final Object BENUTZER = "BENUTZER";
	public static final String FIRMA = "FIRMA";
}
