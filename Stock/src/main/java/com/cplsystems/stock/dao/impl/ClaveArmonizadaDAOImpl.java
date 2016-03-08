package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ClaveArmonizadaDAO;
import com.cplsystems.stock.domain.ClaveArmonizada;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ClaveArmonizadaDAOImpl extends HibernateDAOSuportUtil implements ClaveArmonizadaDAO {
	@Transactional
	public void save(ClaveArmonizada claveArmonizada) {
		getHibernateTemplate().saveOrUpdate(claveArmonizada);
	}

	@Transactional
	public void delete(ClaveArmonizada claveArmonizada) {
		getHibernateTemplate().delete(claveArmonizada);
	}

	@Transactional(readOnly = true)
	public ClaveArmonizada getById(Long idClaveArmonizada) {
		return null;
	}

	@Transactional(readOnly = true)
	public List<ClaveArmonizada> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ClaveArmonizada.class);

		List<ClaveArmonizada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ClaveArmonizada> getByGrupo(Integer grupo) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ClaveArmonizada.class);

		criteria.add(Restrictions.eq("grupo", grupo));
		List<ClaveArmonizada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ClaveArmonizada> getBySubGrupo(Integer subGrupo) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ClaveArmonizada.class);

		criteria.add(Restrictions.eq("subGrupo", subGrupo));
		List<ClaveArmonizada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ClaveArmonizada> getByClase(Integer clase) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ClaveArmonizada.class);

		criteria.add(Restrictions.eq("clase", clase));
		List<ClaveArmonizada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public ClaveArmonizada getByClave(String clave) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ClaveArmonizada.class);

		criteria.add(Restrictions.eq("clave", clave));
		List<ClaveArmonizada> lista = criteria.list();
		return lista.size() > 0 ? (ClaveArmonizada) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public ClaveArmonizada getByDescripcion(String descripcion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(ClaveArmonizada.class);

		criteria.add(Restrictions.eq("descripcion", descripcion));
		List<ClaveArmonizada> lista = criteria.list();
		return lista.size() > 0 ? (ClaveArmonizada) lista.get(0) : null;
	}
}
