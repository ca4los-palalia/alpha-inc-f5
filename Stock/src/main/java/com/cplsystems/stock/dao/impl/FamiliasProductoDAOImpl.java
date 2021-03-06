package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.FamiliasProductoDAO;
import com.cplsystems.stock.domain.FamiliasProducto;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class FamiliasProductoDAOImpl extends HibernateDAOSuportUtil implements FamiliasProductoDAO {
	@Transactional
	public void save(FamiliasProducto familiasProducto) {
		getHibernateTemplate().saveOrUpdate(familiasProducto);
	}

	@Transactional
	public void update(FamiliasProducto familiasProducto) {
		getHibernateTemplate().update(familiasProducto);
	}

	@Transactional
	public void delete(FamiliasProducto familiasProducto) {
		getHibernateTemplate().delete(familiasProducto);
	}

	@Transactional(readOnly = true)
	public FamiliasProducto getById(Long idFamiliasProducto) {
		List<FamiliasProducto> producto = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(FamiliasProducto.class);

		criteria.add(Restrictions.eq("idFamiliasProducto", idFamiliasProducto));
		producto = criteria.list();
		return producto.size() > 0 ? (FamiliasProducto) producto.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<FamiliasProducto> getAll() {
		List<FamiliasProducto> producto = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(FamiliasProducto.class);

		producto = criteria.list();
		return producto.size() > 0 ? producto : null;
	}

	@Transactional(readOnly = true)
	public List<FamiliasProducto> getByProducto(Producto producto) {
		List<FamiliasProducto> familiasProductoList = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(FamiliasProducto.class);

		criteria.add(Restrictions.eq("producto", producto));
		familiasProductoList = criteria.list();
		return familiasProductoList.size() > 0 ? familiasProductoList : null;
	}

	@Transactional(readOnly = true)
	public List<FamiliasProducto> getByFamilia(ProductoTipo productoTipo) {
		List<FamiliasProducto> familiasProductoList = null;
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(FamiliasProducto.class);

		criteria.add(Restrictions.eq("productoTipo", productoTipo));
		familiasProductoList = criteria.list();
		return familiasProductoList.size() > 0 ? familiasProductoList : null;
	}
}
