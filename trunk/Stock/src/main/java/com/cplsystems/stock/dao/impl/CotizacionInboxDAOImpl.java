/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.CotizacionInboxDAO;
import com.cplsystems.stock.domain.CotizacionInbox;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class CotizacionInboxDAOImpl extends HibernateDAOSuportUtil implements
		CotizacionInboxDAO {

	@Transactional
	public void save(CotizacionInbox cotizacionInbox) {
		getHibernateTemplate().saveOrUpdate(cotizacionInbox);
	}

	@Transactional
	public void delete(CotizacionInbox cotizacionInbox) {
		getHibernateTemplate().delete(cotizacionInbox);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CotizacionInbox> getAllNews() {
		return getHibernateTemplate().find(
				"FROM CotizacionInbox as c LEFT JOIN FETCH "
						+ "c.cotizacion WHERE c.leido = ?", false);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CotizacionInbox> getAll() {
		return getHibernateTemplate().find(
				"FROM CotizacionInbox as c LEFT JOIN FETCH ");
	}

}
