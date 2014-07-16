/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.OrdenCompraInboxDAO;
import com.cplsystems.stock.domain.OrdenCompraInbox;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class OrdenCompraInboxDAOImpl extends HibernateDAOSuportUtil implements
		OrdenCompraInboxDAO {

	@Transactional
	public void save(OrdenCompraInbox ordenCompraInbox) {
		getHibernateTemplate().saveOrUpdate(ordenCompraInbox);
	}

	@Transactional
	public void delete(OrdenCompraInbox ordenCompraInbox) {
		getHibernateTemplate().delete(ordenCompraInbox);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OrdenCompraInbox> getAllNews() {
		return getHibernateTemplate().find(
				"FROM OrdenCompraInbox as o LEFT JOIN FETCH o.ordenCompra "
						+ "WHERE o.leido = ?", false);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<OrdenCompraInbox> getAll() {
		return getHibernateTemplate().find(
				"FROM OrdenCompraInbox as o LEFT JOIN FETCH o.ordenCompra");
	}

}
