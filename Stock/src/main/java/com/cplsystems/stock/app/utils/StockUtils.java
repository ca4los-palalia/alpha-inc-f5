/**
 * 
 */
package com.cplsystems.stock.app.utils;

import org.springframework.stereotype.Repository;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class StockUtils {

	/**
	 * Create a window programmatically and use it as a modal dialog. eg
	 * /widgets/window/modal_dialog/employee_dialog.zul
	 */
	public Window createModelDialog(final String locationView) {
		Window window = (Window) Executions.createComponents(locationView,
				null, null);
		return window;
	}

	/**Redirect to a new web page eg /login.zul*/
	public void redirect(final String page) {
		Executions.getCurrent().sendRedirect(page);
	}
}
