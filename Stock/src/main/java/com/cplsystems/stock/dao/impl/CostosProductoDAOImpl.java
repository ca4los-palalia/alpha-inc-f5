/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.CostosProductoDAO;
import com.cplsystems.stock.domain.CostosProducto;
import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */

@Repository
public class CostosProductoDAOImpl extends HibernateDAOSuportUtil implements
CostosProductoDAO {

	@Override
	@Transactional
	public void save(CostosProducto costosProducto) {
		getHibernateTemplate().saveOrUpdate(costosProducto);
	}

	@Override
	@Transactional
	public void delete(CostosProducto costosProducto) {
		getHibernateTemplate().delete(costosProducto);
	}

	@Override
	@SuppressWarnings({"unchecked" })
	@Transactional(readOnly = true)
	public CostosProducto getById(Long idCostosProducto) {
		List<CostosProducto> codigos = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CostosProducto.class);
		criteria.add(Restrictions.eq("idCostosProducto", idCostosProducto));
		codigos = criteria.list();
		return codigos.size() > 0 ? codigos.get(0) : null;
	}

	@Override
	@SuppressWarnings({"unchecked" })
	@Transactional(readOnly = true)
	public List<CostosProducto> getAll() {
		List<CostosProducto> codigos = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CostosProducto.class);
		codigos = criteria.list();
		return codigos.size() > 0 ? codigos : null;
	}

	@Override
	@SuppressWarnings({"unchecked" })
	@Transactional(readOnly = true)
	public CostosProducto getByProducto(Producto producto) {
		List<CostosProducto> codigos = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(CostosProducto.class);
		criteria.add(Restrictions.eq("producto", producto));
		codigos = criteria.list();
		return codigos.size() > 0 ? codigos.get(0) : null;
	}
}
