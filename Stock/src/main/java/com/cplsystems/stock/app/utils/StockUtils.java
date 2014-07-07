/**
 * 
 */
package com.cplsystems.stock.app.utils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class StockUtils {

	private DecimalFormat format = new DecimalFormat(
			StockConstants.CURRENCY_FORMAT);

	/**
	 * Create a window programmatically and use it as a modal dialog. eg
	 * /widgets/window/modal_dialog/employee_dialog.zul
	 */

	public Window createModelDialog(final String locationView) {
		Window window = (Window) Executions.createComponents(locationView,
				null, null);
		return window;
	}

	/**
	 * Create a window programmatically and use it as a modal dialog. eg
	 * /widgets/window/modal_dialog/employee_dialog.zul
	 */

	public Window createModelDialogWithParams(final String locationView,
			Map<String, Object> params) {
		Window window = (Window) Executions.createComponents(locationView,
				null, params);
		return window;
	}

	/** Redirect to a new web page eg /login.zul */
	public void redirect(final String page) {
		Executions.getCurrent().sendRedirect(page);
	}

	/**
	 * Notificador de mensajes en vista
	 * 
	 * @param Mensaje
	 * @param Clients
	 *            .NOTIFICATION_TYPE_INFO
	 */
	public static void showSuccessmessage(String mensaje, String tipo,
			Integer duracionEnVista, Component componente) {
		Clients.showNotification(mensaje, tipo, componente, null, duracionEnVista);
	}

	public String formatCurrency(Double quantity) {
		if (quantity != null) {
			return format.format(quantity);
		}
		return null;
	}
	
	
	public Calendar convertirDateToCalendar(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}
	
	public Date convertirCalendarToDate(Calendar calendar){
		Date date = calendar.getTime();
		return date;
	}
}
