package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.EstatusRequisicionDAO;
import com.cplsystems.stock.domain.EstatusRequisicion;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EstatusRequisicionDAOImpl extends HibernateDAOSuportUtil implements EstatusRequisicionDAO {
	@Transactional
	public void save(EstatusRequisicion EstatusRequisicion) {
		getHibernateTemplate().saveOrUpdate(EstatusRequisicion);
	}

	@Transactional
	public void delete(EstatusRequisicion EstatusRequisicion) {
		getHibernateTemplate().delete(EstatusRequisicion);
	}

	@Transactional(readOnly = true)
	public EstatusRequisicion getById(Long idEstatusRequisicion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(EstatusRequisicion.class);

		criteria.add(Restrictions.eq("idEstatusRequisicion", idEstatusRequisicion));
		List<EstatusRequisicion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (EstatusRequisicion) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<EstatusRequisicion> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(EstatusRequisicion.class);

		List<EstatusRequisicion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public EstatusRequisicion getByNombre(String nombre) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(EstatusRequisicion.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<EstatusRequisicion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (EstatusRequisicion) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public EstatusRequisicion getByClave(String clave) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession()
				.createCriteria(EstatusRequisicion.class);

		criteria.add(Restrictions.eq("clave", clave));
		List<EstatusRequisicion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (EstatusRequisicion) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public EstatusRequisicion getByEstado(boolean estado) {
		return null;
	}
}
