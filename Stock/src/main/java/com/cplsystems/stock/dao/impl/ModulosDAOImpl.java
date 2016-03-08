package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ModulosDAO;
import com.cplsystems.stock.domain.Modulos;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ModulosDAOImpl extends HibernateDAOSuportUtil implements ModulosDAO {
	@Transactional
	public void save(Modulos modulos) {
		getHibernateTemplate().saveOrUpdate(modulos);
	}

	@Transactional
	public void delete(Modulos modulos) {
		getHibernateTemplate().delete(modulos);
	}

	@Transactional(readOnly = true)
	public List<Modulos> getAll() {
		return getHibernateTemplate().find("FROM Modulos as m");
	}
}
