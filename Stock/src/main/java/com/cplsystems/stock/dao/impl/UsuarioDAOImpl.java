/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.dao.UsuarioDAO;
import com.cplsystems.stock.domain.Organizacion;
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
				.find("FROM Usuarios as u "
						+ "LEFT JOIN FETCH u.organizacion as o WHERE u.benutzer = ? "
						+ "AND u.kennwort = ?", usuario, password);
		return user.size() > 0 ? user.get(0) : null;
	}

	@Transactional
	public void save(Usuarios usuarios) {
		getHibernateTemplate().saveOrUpdate(usuarios);
	}

	@Transactional
	public void delete(Usuarios usuario) {
		getHibernateTemplate().delete(usuario);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Usuarios> getUsuariosByOrganizacion(Organizacion organizacion) {
		List<Usuarios> usuarios = getHibernateTemplate()
				.find("FROM Usuarios as u "
						+ "LEFT JOIN FETCH u.organizacion as o WHERE u.organizacion = ?",
						organizacion);
		return usuarios.size() > 0 ? usuarios : null;
	}

	public boolean verificarNombreUsuario(String benutzer, Long idUsuario) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.openSession().createCriteria(Usuarios.class);
		criteria.add(Restrictions.like("benutzer", "%" + benutzer + "%"));
		criteria.add(Restrictions.ne("idUsuario", idUsuario));
		return criteria.list().size() > 0 ? true : false;
	}
}
