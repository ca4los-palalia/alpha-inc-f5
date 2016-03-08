package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.CodigoBarrasProductoDAO;
import com.cplsystems.stock.domain.CodigoBarrasProducto;
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
public class CodigoBarrasProductoDAOImpl extends HibernateDAOSuportUtil implements CodigoBarrasProductoDAO {
	@Transactional
	public void save(CodigoBarrasProducto codigoBarrasProducto) {
		getHibernateTemplate().saveOrUpdate(codigoBarrasProducto);
	}

	@Transactional
	public void delete(CodigoBarrasProducto codigoBarrasProducto) {
		getHibernateTemplate().delete(codigoBarrasProducto);
	}

	@Transactional(readOnly = true)
	public CodigoBarrasProducto getById(Long idCodigoBarrasProducto) {
		List<CodigoBarrasProducto> codigos = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(CodigoBarrasProducto.class);

		criteria.add(Restrictions.eq("idCodigoBarrasProducto", idCodigoBarrasProducto));
		codigos = criteria.list();
		return codigos.size() > 0 ? (CodigoBarrasProducto) codigos.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<CodigoBarrasProducto> getAll() {
		List<CodigoBarrasProducto> codigos = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(CodigoBarrasProducto.class);

		codigos = criteria.list();
		return codigos.size() > 0 ? codigos : null;
	}

	@Transactional(readOnly = true)
	public List<CodigoBarrasProducto> getByCodigo(String codigo) {
		List<CodigoBarrasProducto> codigos = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(CodigoBarrasProducto.class);

		criteria.add(Restrictions.eq("codigo", codigo));
		codigos = criteria.list();
		return codigos.size() > 0 ? codigos : null;
	}

	@Transactional(readOnly = true)
	public List<CodigoBarrasProducto> getByProducto(Producto producto) {
		List<CodigoBarrasProducto> codigos = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(CodigoBarrasProducto.class);

		criteria.add(Restrictions.eq("producto", producto));
		codigos = criteria.list();
		return codigos.size() > 0 ? codigos : null;
	}
}
