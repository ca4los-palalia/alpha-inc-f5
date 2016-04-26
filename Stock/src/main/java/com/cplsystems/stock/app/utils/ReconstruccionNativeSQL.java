package com.cplsystems.stock.app.utils;

import java.util.Calendar;

import com.cplsystems.stock.domain.Moneda;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoNaturaleza;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;

public class ReconstruccionNativeSQL {

	@SuppressWarnings("unused")
	public Producto getProducto(Object[] row) {
		Producto salida = new Producto();
		int i = 0;
		for (Object object : row) {
			String valor = "";

			if (object != null) {
				switch (i) {
				case 0:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = removerPuntoCero(valor);
						}
						salida.setIdProducto(Long.parseLong(valor));
					}
					break;
				case 1:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.equals("1"))
							salida.setActivo(true);
						else
							salida.setActivo(false);
					}
					break;
				case 2:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						salida.setClave(valor);
					}
					break;
				case 3:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						salida.setCodigoBarras(valor);
					}
					break;
				case 4:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = removerPuntoCero(valor);
						}
						salida.setCostoCapa(Float.parseFloat(valor));
					}
					break;
				case 5:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						StockUtils util = new StockUtils();
						//util.convertirStringToCalendar(dia, mes, anyo)
						salida.setCostoCapaFecha(Calendar.getInstance());
					}
					break;
				case 6:

					break;
				case 7:

					break;
				case 8:
					break;
				case 9:
					break;
				case 10:
					break;
				case 11:
					break;
				case 12:
					break;
				case 13:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						salida.setDescripcion(valor);
					}
					break;
				case 14:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = removerPuntoCero(valor);
						}
						salida.setEnExistencia(Integer.parseInt(valor));
					}
					break;
				case 15:
					break;
				case 16:
					break;
				case 17:
					break;
				case 18:
					break;
				case 19:
					break;
				case 20:
					break;
				case 21:
					break;
				case 22:
					break;
				case 23:
					break;
				case 24:
					break;
				case 25:
					break;
				case 26:
					break;
				case 27:
					break;
				case 28:
					break;
				case 29:
					break;
				case 30:
					break;
				case 31:
					break;
				case 32:
					break;
				case 33:
					break;
				case 34:
					break;
				case 35:
					break;
				case 36:
					break;
				case 37:
					break;
				case 38:
					break;
				case 39:
					break;
				case 40:
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						salida.setNombre(valor);
					}
					break;
				case 41:
					break;
				case 42:
					break;
				case 43:
					break;
				case 44:
					break;
				case 45:
					break;
				case 46:
					break;
				case 47:
					break;
				case 48:
					break;
				case 49:
					break;
				case 50:
					break;
				case 51:
					break;
				case 52:
					break;
				case 53:
					break;
				case 54://Moneda
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = removerPuntoCero(valor);
						}
						Moneda obj = new Moneda();
						obj.setIdMoneda(Long.parseLong(valor));
						salida.setMoneda(obj);
					}
					break;
				case 55://Organizacion
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = removerPuntoCero(valor);
						}
						Organizacion obj = new Organizacion();
						obj.setIdOrganizacion(Long.parseLong(valor));
						salida.setOrganizacion(obj);
					}
					break;
				case 56://Producto Naturaleza
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = removerPuntoCero(valor);
						}
						ProductoNaturaleza obj = new ProductoNaturaleza();
						obj.setIdProductoNaturaleza(Long.parseLong(valor));
						salida.setProductoNaturaleza(obj);
					}
					break;
				case 57://Unidad
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = removerPuntoCero(valor);
						}
						Unidad obj = new Unidad();
						obj.setIdUnidad(Long.parseLong(valor));
						salida.setUnidad(obj);
					}
					break;
				case 58://Usuario
					valor = object.toString();
					if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) {
						if (valor.contains(".0")) {
							valor = removerPuntoCero(valor);
						}
						Usuarios obj = new Usuarios();
						obj.setIdUsuario(Long.parseLong(valor));
						salida.setUsuario(obj);
					}
					break;

				}
			}

			i++;
		}

		return salida;
	}

	public String removerPuntoCero(String valor) {
		String salida = "";
		for (int i = 0; i < valor.length(); i++) {
			String caracter = valor.substring(i, i + 1);
			if (caracter.equals(".")) {
				break;
			}
			salida = salida + caracter;
		}
		return salida;
	}
}
