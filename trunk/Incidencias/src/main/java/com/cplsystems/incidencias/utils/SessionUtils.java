/**
 * 
 */
package com.cplsystems.incidencias.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class SessionUtils {

	private Logger log = LogManager.getLogger(this.getClass().getName());

	public boolean addToSession(final String keyAttribute, final Object object) {

		Session session = Executions.getCurrent().getSession();
		if (session == null) {
			log.error("FATAL ERROR: zk session is null");
			return false;
		}
		session.setAttribute(keyAttribute, object);
		log.info(keyAttribute + " added to session");
		return true;
	}

	public Object getFromSession(final String keyAttribute) {
		Session session = Executions.getCurrent().getSession();
		if (session == null) {
			log.error("FATAL ERROR: zk session is null");
			return null;
		}
		return session.getAttribute(keyAttribute);
	}

	public boolean logOut() {
		Session session = Executions.getCurrent().getSession();
		if (session == null) {
			log.error("FATAL ERROR: zk session is null");
			return false;
		}
		session.invalidate();
		log.info("user key removed from session");
		return true;
	}

}
