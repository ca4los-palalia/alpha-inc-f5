package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.RequisicionInboxDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.RequisicionInbox;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RequisicionInboxDAOImpl extends HibernateDAOSuportUtil implements RequisicionInboxDAO {
	@Transactional
	public void save(RequisicionInbox requisicionInbox) {
		getHibernateTemplate().saveOrUpdate(requisicionInbox);
	}

	@Transactional
	public void delete(RequisicionInbox requisicionInbox) {
		getHibernateTemplate().delete(requisicionInbox);
	}

	@Transactional(readOnly = true)
	public List<RequisicionInbox> getAllNews(Organizacion organizacion) {
		return getHibernateTemplate().find(
				"FROM RequisicionInbox as r LEFT JOIN FETCH r.requisicion as e WHERE r.leido = ?AND e.organizacion = ?",
				new Object[] { Boolean.valueOf(false), organizacion });
	}

	@Transactional(readOnly = true)
	public List<RequisicionInbox> getAll(Organizacion organizacion) {
		return getHibernateTemplate().find(
				"FROM RequisicionInbox as r LEFT JOIN FETCH r.requisicion as e WHERE e.organizacion = ?", organizacion);
	}
}
