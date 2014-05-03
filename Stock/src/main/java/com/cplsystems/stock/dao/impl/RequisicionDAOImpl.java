/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.RequisicionDAO;
import com.cplsystems.stock.domain.Requisicion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class RequisicionDAOImpl extends HibernateDAOSuportUtil implements
		RequisicionDAO {

	@Transactional
	public void save(Requisicion requisicion) {
		getHibernateTemplate().saveOrUpdate(requisicion);
	}

}
