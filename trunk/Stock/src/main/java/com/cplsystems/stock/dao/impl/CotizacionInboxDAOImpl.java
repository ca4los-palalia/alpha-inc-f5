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
import com.cplsystems.stock.domain.Organizacion;

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
	public List<CotizacionInbox> getAllNews(final Organizacion organizacion) {
		return getHibernateTemplate().find(
				"FROM CotizacionInbox as c LEFT JOIN FETCH "
						+ "c.cotizacion as o "
						+ "LEFT JOIN FETCH o.proveedor as p "
						+ "LEFT JOIN FETCH p.direccionFiscal as d "
						+ "LEFT JOIN FETCH d.pais as a "
						+ "LEFT JOIN FETCH d.estado as e "
						+ "LEFT JOIN FETCH d.municipio as m "
						+ "LEFT JOIN FETCH o.requisicion as r "
						+ "WHERE c.leido = ? AND o.organizacion = ?", false,
				organizacion);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CotizacionInbox> getAll(final Organizacion organizacion) {
		return getHibernateTemplate().find(
				"FROM CotizacionInbox as c LEFT JOIN FETCH c.cotizacion as o "
						+ "LEFT JOIN FETCH o.proveedor as p "
						+ "LEFT JOIN FETCH p.direccionFiscal as d "
						+ "LEFT JOIN FETCH d.pais as a "
						+ "LEFT JOIN FETCH d.estado as e "
						+ "LEFT JOIN FETCH d.municipio as m "						
						+ "LEFT JOIN FETCH o.requisicion as r "
						+ "WHERE o.organizacion = ? ", organizacion);
	}

}
