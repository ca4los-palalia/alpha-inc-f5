package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.DevelopmentToolDAO;
import com.cplsystems.stock.domain.DevelopmentTool;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DevelopmentToolDAOImpl extends HibernateDAOSuportUtil implements DevelopmentToolDAO {
	@Transactional
	public void save(DevelopmentTool developmentTool) {
		getHibernateTemplate().saveOrUpdate(developmentTool);
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DevelopmentTool getById(Long developmentTool) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(DevelopmentTool.class);

		criteria.add(Restrictions.eq("idDevelopmentTool", developmentTool));
		List<DevelopmentTool> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (DevelopmentTool) lista.get(0) : null;
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<DevelopmentTool> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(DevelopmentTool.class);
		List<DevelopmentTool> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
