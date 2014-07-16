/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.RequisicionInboxDAO;
import com.cplsystems.stock.domain.RequisicionInbox;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class RequisicionInboxDAOImpl extends HibernateDAOSuportUtil implements
		RequisicionInboxDAO {

	@Transactional
	public void save(RequisicionInbox requisicionInbox) {
		getHibernateTemplate().saveOrUpdate(requisicionInbox);
	}

	@Transactional
	public void delete(RequisicionInbox requisicionInbox) {
		getHibernateTemplate().delete(requisicionInbox);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionInbox> getAllNews() {
		return getHibernateTemplate().find(
				"FROM RequisicionInbox as r "
						+ "LEFT JOIN FETCH r.requisicion WHERE r.leido = ?",
				false);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<RequisicionInbox> getAll() {
		return getHibernateTemplate()
				.find("FROM RequisicionInbox as r "
						+ "LEFT JOIN FETCH r.requisicion");
	}

}
