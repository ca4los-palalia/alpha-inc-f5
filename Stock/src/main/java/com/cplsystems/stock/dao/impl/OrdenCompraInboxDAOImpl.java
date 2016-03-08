package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.OrdenCompraInboxDAO;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrdenCompraInboxDAOImpl extends HibernateDAOSuportUtil implements OrdenCompraInboxDAO {
	@Transactional
	public void save(OrdenCompraInbox ordenCompraInbox) {
		getHibernateTemplate().saveOrUpdate(ordenCompraInbox);
	}

	@Transactional
	public void delete(OrdenCompraInbox ordenCompraInbox) {
		getHibernateTemplate().delete(ordenCompraInbox);
	}

	@Transactional(readOnly = true)
	public List<OrdenCompraInbox> getAllNews(Organizacion organizacion) {
		return getHibernateTemplate().find(
				"FROM OrdenCompraInbox as o LEFT JOIN FETCH o.ordenCompra as c LEFT JOIN FETCH c.cotizacion as k LEFT JOIN FETCH k.proveedor as p WHERE o.leido = ? AND c.organizacion = ?",
				new Object[] { Boolean.valueOf(false), organizacion });
	}

	@Transactional(readOnly = true)
	public List<OrdenCompraInbox> getAll(Organizacion organizacion) {
		return getHibernateTemplate().find(
				"FROM OrdenCompraInbox as o LEFT JOIN FETCH o.ordenCompra as c LEFT JOIN FETCH c.cotizacion as k LEFT JOIN FETCH k.proveedor as p WHERE c.organizacion = ?",
				organizacion);
	}
}
