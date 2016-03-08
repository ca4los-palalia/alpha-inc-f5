package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.CostosProductoDAO;
import com.cplsystems.stock.domain.CostosProducto;
import com.cplsystems.stock.domain.Producto;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CostosProductoDAOImpl extends HibernateDAOSuportUtil implements CostosProductoDAO {
	@Transactional
	public void save(CostosProducto costosProducto) {
		getHibernateTemplate().saveOrUpdate(costosProducto);
	}

	@Transactional
	public void delete(CostosProducto costosProducto) {
		getHibernateTemplate().delete(costosProducto);
	}

	@Transactional(readOnly = true)
	public CostosProducto getById(Long idCostosProducto) {
		List<CostosProducto> codigos = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(CostosProducto.class);

		criteria.add(Restrictions.eq("idCostosProducto", idCostosProducto));
		codigos = criteria.list();
		return codigos.size() > 0 ? (CostosProducto) codigos.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<CostosProducto> getAll() {
		List<CostosProducto> codigos = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(CostosProducto.class);

		codigos = criteria.list();
		return codigos.size() > 0 ? codigos : null;
	}

	@Transactional(readOnly = true)
	public CostosProducto getByProducto(Producto producto) {
		List<CostosProducto> codigos = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(CostosProducto.class);

		criteria.add(Restrictions.eq("producto", producto));
		codigos = criteria.list();
		return codigos.size() > 0 ? (CostosProducto) codigos.get(0) : null;
	}
}
