/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.domain.PrivilegioDAO;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class PrivilegioDAOImpl extends HibernateDAOSuportUtil implements
		PrivilegioDAO {

	@Transactional
	public void save(Privilegios privilegios) {
		getHibernateTemplate().saveOrUpdate(privilegios);
	}

	@Transactional
	public void delete(Privilegios privilegios) {
		getHibernateTemplate().delete(privilegios);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Privilegios> getPrivilegiosByUsuario(Usuarios usuarios) {
		List<Privilegios> privilegios = getHibernateTemplate().find(
				"FROM Privilegios as p LEFT JOIN FETCH p.usuarios as u "
						+ "WHERE p.usuarios = ?", usuarios);
		return privilegios;
	}

}
