/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.dao.UsuarioDAO;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class UsuarioDAOImpl extends HibernateDaoSupport implements UsuarioDAO {

	@Autowired
	public void init(final SessionFactory sessionFactory)
			throws DataAccessException {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Usuarios getUsuarioByCredentials(String usuario, String password) {
		List<Usuarios> user = getHibernateTemplate()
				.find("FROM Usuarios as u WHERE u.benutzer = ? "
						+ "AND u.kennwort = ?", usuario, password);
		return user.size() > 0 ? user.get(0) : null;
	}
}
