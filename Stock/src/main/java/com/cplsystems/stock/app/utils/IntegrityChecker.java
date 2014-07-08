/**
 * 
 */
package com.cplsystems.stock.app.utils;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.Initiator;

import com.cplsystems.stock.domain.Organizacion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class IntegrityChecker implements Initiator {
	private Logger log = LogManager.getLogger(this.getClass().getName());

	public void doInit(Page page, Map<String, Object> args) throws Exception {
		SessionUtils sessionUtils = null;
		Object session = args.get(StockConstants.BENUTZER);
		if (session instanceof SessionUtils) {
			sessionUtils = (SessionUtils) session;
			Organizacion organizacion = (Organizacion) sessionUtils
					.getFromSession(StockConstants.FIRMA);
			if (organizacion == null) {
				Executions.sendRedirect(StockConstants.GLOBAL_PAGES.LOGIN_URL);
				return;
			}
		} else {
			StockUtils.showSuccessmessage("Session isn't context application",
					Clients.NOTIFICATION_TYPE_ERROR, 0, null);
			log.error("Session isn't context application");
			Executions.sendRedirect(StockConstants.GLOBAL_PAGES.LOGIN_URL);
			return;
		}
	}
}
